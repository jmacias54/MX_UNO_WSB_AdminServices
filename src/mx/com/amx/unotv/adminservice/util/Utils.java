/**
 * 
 */
package mx.com.amx.unotv.adminservice.util;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TimeZone;

import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import mx.com.amx.unotv.adminservice.dto.RedSocialEmbedPostDTO;
import mx.com.amx.unotv.adminservice.model.NNota;
import mx.com.amx.unotv.adminservice.model.ParametrosDTO;


/**
 * @author Jesus A. Macias Benitez
 *
 */
public class Utils {

	// LOG
	private static Logger LOG = Logger.getLogger(Utils.class);

	public static String getRutaContenido(NNota nota, ParametrosDTO parametrosDTO) throws Exception {
		LOG.debug("**** Inicia getRutaContenido[Utils]");
		String rutaContenido = "";

		try {

			rutaContenido =  nota.getFcIdSeccion() + "/" + nota.getFcIdCategoria()
					+ "/detalle/" + nota.getFcFriendlyUrl() + "/";

			LOG.debug("rutaContenido: " + rutaContenido);
		} catch (Exception e) {
			LOG.error("Error getRutaContenido: ", e);
			throw new Exception(e.getMessage());
		}
		return rutaContenido;
	}

	public static boolean createFolders(String carpetaContenido) throws Exception {
		boolean success = false;
		try {
			File carpetas = new File(carpetaContenido);
			if (!carpetas.exists()) {
				success = carpetas.mkdirs();
			} else
				success = true;
		} catch (Exception e) {
			success = false;
			LOG.error("Ocurrio error al crear las carpetas: ", e);
			throw new Exception(e.getMessage());
		}
		return success;
	}

	public static boolean createPlantilla(ParametrosDTO parametrosDTO, NNota contentDTO, String urlNota)
			throws Exception {
		LOG.debug("*** Inicia createPlantilla [Utils]");

		boolean success = false;
		Document doc = null;
		String tipoSeccion = "";
		String id_categoria = "";
		String id_seccion = "";
		try {

			try {

				tipoSeccion = contentDTO.getFcIdTipoNota();

				id_categoria = contentDTO.getFcIdCategoria();

				id_seccion = contentDTO.getFcIdSeccion();

				// Nos conectamos a la plantilla detalle-prerender de portal
				LOG.info("Conectandose a : " + parametrosDTO.getTemplateHtml());
				doc = Jsoup.connect(parametrosDTO.getTemplateHtml()).timeout(120000).get();
				success = true;
			} catch (Exception e) {
				LOG.error("Ocurrio error al Obtener el HTML de : ", e);
				success = false;
			}

			LOG.debug("success  : "+success);
			if (success) {

				
				LOG.debug("Plantilla  : "+doc.toString());
				String HTML = doc.html();

				// Creamos el HTML con base a la plantilla
				HTML = reemplazaPlantilla(HTML, contentDTO, parametrosDTO);

				String rutaHTML = urlNota;
				LOG.info("Ruta HTML: " + rutaHTML);
				success = writeHTML(rutaHTML, HTML);
				LOG.info("Genero HTML Local: " + success);
			}
		} catch (Exception e) {
			LOG.error("Error al obtener HTML de Plantilla: ", e);
			throw new Exception(e.getMessage());
		}
		return success;
	}

	public static boolean writeHTML(String rutaHMTL, String HTML) {
		boolean success = false;
		try {
			FileWriter fichero = null;
			PrintWriter pw = null;
			try {
				fichero = new FileWriter(rutaHMTL);
				pw = new PrintWriter(fichero);
				pw.println(HTML);
				pw.close();
				success = true;
			} catch (Exception e) {
				LOG.error("Error al obtener la plantilla " + rutaHMTL + ": ", e);
				success = false;
			} finally {
				try {
					if (null != fichero)
						fichero.close();
				} catch (Exception e2) {
					success = false;
					LOG.error("Error al cerrar el file: ", e2);
				}
			}
		} catch (Exception e) {
			success = false;
			LOG.error("Fallo al crear la plantilla: ", e);
		}
		return success;
	}

	private static String reemplazaPlantilla(String HTML, NNota contentDTO, ParametrosDTO parametrosDTO) {
		LOG.debug("Inicia reemplazaPlantilla");

		// numberUnoCross
		try {

			String[] pala = contentDTO.getFcFriendlyUrl().split("-");
			String id = "";
			if (pala.length > 1) {
				id = pala[pala.length - 1];
			}
			HTML = HTML.replace("numberUnoCross", id.trim());
		} catch (Exception e) {
			HTML = HTML.replace("numberUnoCross", "");
			LOG.error("Error al remplazar numberUnoCross");
		}

		// $WCM_TITULO_COMENTARIO$
		try {
			String titulo_comentario = contentDTO.getFcTitulo() == null || contentDTO.getFcTitulo().equals("")
					? "Â¿QuÃ© opinas?"
					: contentDTO.getFcTitulo();
			HTML = HTML.replace("$WCM_TITULO_COMENTARIO$", StringEscapeUtils.escapeHtml(titulo_comentario));
		} catch (Exception e) {
			HTML = HTML.replace("$WCM_TITULO_COMENTARIO$", "");
			LOG.error("Error al sustituir $WCM_TITULO_COMENTARIO$");
		}

		// Remplaza comscore
		try {
			HTML = HTML.replace("$WCM_NAVEGACION_COMSCORE$",
					contentDTO.getFcIdTipoNota() + "." + contentDTO.getFcIdSeccion() + "."
							+ contentDTO.getFcIdCategoria() + "." + parametrosDTO.getPathDetalle() + "."
							+ contentDTO.getFcFriendlyUrl());
		} catch (Exception e) {
			LOG.error("Error al sustituir navegacion  comscore");
		}

		// $WCM_DESCRIPCION_CONTENIDO$
		try {
			HTML = HTML.replace("$WCM_DESCRIPCION_CONTENIDO$", htmlEncode(contentDTO.getFcDescripcion().trim()));
		} catch (Exception e) {
			HTML = HTML.replace("$WCM_DESCRIPCION_CONTENIDO$", "");
			LOG.error("Error al remplazar $WCM_DESCRIPCION_CONTENIDO$");
		}

		// $WCM_ID_CATEGORIA$
		try {
			HTML = HTML.replace("$WCM_ID_CATEGORIA$", contentDTO.getFcIdCategoria().trim());
		} catch (Exception e) {
			HTML = HTML.replace("$WCM_ID_CATEGORIA$", "");
			LOG.error("Error al remplazar $WCM_ID_CATEGORIA$");
		}

		// $WCM_TITLE_CONTENIDO$
		try {
			HTML = HTML.replace("$WCM_TITLE_CONTENIDO$", StringEscapeUtils.escapeHtml(contentDTO.getFcTitulo().trim()));
		} catch (Exception e) {
			HTML = HTML.replace("$WCM_TITLE_CONTENIDO$", "");
			LOG.error("Error al remplazar $WCM_TITLE_CONTENIDO$");
		}

		// $WCM_FECHA$
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			HTML = HTML.replace("$WCM_FECHA$", format.format(contentDTO.getFdFechaPublicacion()));
		} catch (Exception e) {
			HTML = HTML.replace("$WCM_FECHA$", "");
			LOG.error("Error al remplazar $WCM_FECHA$");
		}

		// $WCM_HORA$
		try {
			
		
			Date time = new Date();
			int hours = time.getHours();
			int minutes = time.getMinutes();
		
			
			HTML = HTML.replace("$WCM_HORA$", hours +":"+minutes);
		} catch (Exception e) {
			HTML = HTML.replace("$WCM_HORA$", "");
			LOG.error("Error al remplazar $WCM_HORA$");
		}

		// $WCM_AUTOR$
		try {
			String autor = contentDTO.getFcEscribio() == null ? "" : contentDTO.getFcEscribio();
			HTML = HTML.replace("$WCM_AUTOR$", StringEscapeUtils.escapeHtml(autor).trim());
		} catch (Exception e) {
			HTML = HTML.replace("$WCM_AUTOR$", "");
			LOG.error("Error al remplazar $WCM_AUTOR$");
		}

		// $WCM_LUGAR$
		try {
			String lugar = contentDTO.getFcLugar() == null ? "" : contentDTO.getFcLugar();
			HTML = HTML.replace("$WCM_LUGAR$", StringEscapeUtils.escapeHtml(lugar));
		} catch (Exception e) {
			HTML = HTML.replace("$WCM_LUGAR$", "");
			LOG.error("Error al remplazar $WCM_LUGAR$");
		}

		// $WCM_CATEGORIA$
		try {
			String nombreCategoria = contentDTO.getFcIdCategoria() == null ? "" : contentDTO.getFcIdCategoria();
			HTML = HTML.replace("$WCM_CATEGORIA$", StringEscapeUtils.escapeHtml(nombreCategoria));
		} catch (Exception e) {
			HTML = HTML.replace("$WCM_CATEGORIA$", "");
			LOG.error("Error al remplazar $WCM_CATEGORIA$");
		}

		// $WCM_FUENTE$
		try {
			String fuente = contentDTO.getFcFuente() == null ? "" : contentDTO.getFcFuente();
			HTML = HTML.replace("$WCM_FUENTE$", StringEscapeUtils.escapeHtml(fuente));
		} catch (Exception e) {
			HTML = HTML.replace("$WCM_FUENTE$", "");
			LOG.error("Error al remplazar $WCM_FUENTE$");
		}

		// Apartados multimedia
		try {

			boolean tieneGaleria = contentDTO.getClGaleria() != null && !contentDTO.getClGaleria().equals("") ? true
					: false;
			boolean tieneVideo = contentDTO.getFcContentIdOoyala() != null
					&& !contentDTO.getFcContentIdOoyala().equals("") ? true : false;

			if (tieneGaleria && tieneVideo)// Tipo Multimedia
			{
				LOG.info("Tipo de Nota multimedia, se reemplaza tanto la galeria, como el video");
				HTML = HTML.replace("$WCM_MEDIA_CONTENT$", getMediaContent(contentDTO, parametrosDTO));
				if (contentDTO.getClGaleria().equalsIgnoreCase("arriba")) {
					HTML = HTML.replace("$WCM_TIENE_GALERIA_UP$", cambiaCaracteres(contentDTO.getClGaleria()));
					HTML = HTML.replace("$WCM_TIENE_GALERIA_DOWN$", "");
				} else {
					HTML = HTML.replace("$WCM_TIENE_GALERIA_UP$", "");
					HTML = HTML.replace("$WCM_TIENE_GALERIA_DOWN$", cambiaCaracteres(contentDTO.getClGaleria()));
				}
			} else if (tieneGaleria) // Nota con galeria
			{
				LOG.info("Tipo de Nota Galeria, se reemplaza solo la galeria");
				// LOG.info("contentDTO.getPlaceGallery(): "+contentDTO.getPlaceGallery());
				HTML = HTML.replace("$WCM_MEDIA_CONTENT$", "");
				if (contentDTO.getClGaleria().equalsIgnoreCase("arriba")) {
					HTML = HTML.replace("$WCM_TIENE_GALERIA_UP$", cambiaCaracteres(contentDTO.getClGaleria()));
					HTML = HTML.replace("$WCM_TIENE_GALERIA_DOWN$", "");
				} else {
					HTML = HTML.replace("$WCM_TIENE_GALERIA_UP$", "");
					HTML = HTML.replace("$WCM_TIENE_GALERIA_DOWN$", cambiaCaracteres(contentDTO.getClGaleria()));
				}
			}

			else {
				LOG.info("Tipo de Nota video o default, se reemplaza solo el media Content");
				HTML = HTML.replace("$WCM_MEDIA_CONTENT$", getMediaContent(contentDTO, parametrosDTO));
				HTML = HTML.replace("$WCM_TIENE_GALERIA_UP$", "");
				HTML = HTML.replace("$WCM_TIENE_GALERIA_DOWN$", "");
			}
		} catch (Exception e) {
			HTML = HTML.replace("$WCM_MEDIA_CONTENT$", "");
			HTML = HTML.replace("$WCM_TIENE_GALERIA_DOWN$", "");
			HTML = HTML.replace("$WCM_TIENE_GALERIA_UP$", "");
			LOG.error("Error al remplazar $WCM_MEDIA_CONTENT$ y $WCM_TIENE_GALERIA$");
		}

		// $WCM_RTF_CONTENIDO$
		try {
			HTML = HTML.replace("$WCM_RTF_CONTENIDO$", cambiaCaracteres(getEmbedPost(contentDTO.getClRtfContenido())));
		} catch (Exception e) {
			HTML = HTML.replace("$WCM_RTF_CONTENIDO$", "");
			LOG.error("Error al remplazar $WCM_RTF_CONTENIDO$");
		}

		// $WCM_URL_PAGE$"
		try {
			String url = getRutaContenido(contentDTO, parametrosDTO);
			HTML = HTML.replace("$WCM_URL_PAGE$", url);
			HTML = HTML.replace("$URL_PAGE$", "https://www.unotv.com/" + url + "/");
		} catch (Exception e) {
			HTML = HTML.replace("$WCM_URL_PAGE$", "");
			LOG.error("Error al remplazar $WCM_URL_PAGE$");
		}

		// Notas relacionadas
		try {

			LOG.debug("No hay notas relacionadas: ");
			HTML = HTML.replace("$WCM_ELEMENTOS_INFINITO$", "");

		} catch (Exception e) {
			HTML = HTML.replace("$WCM_ELEMENTOS_INFINITO$", "");
			LOG.error("Error al remplazar $WCM_ELEMENTOS_INFINITO$", e);
		}

		// Remplaza metas
		HTML = remplazaMetas(HTML, contentDTO, parametrosDTO);

		// Base URL
		try {
			String valorBase[] = HTML.split("<base");
			valorBase[0] = valorBase[1].substring(0, valorBase[1].indexOf("/>"));
			String tmp[] = valorBase[0].split("href=\"");
			String base = tmp[1].substring(0, tmp[1].indexOf("\""));
			HTML = HTML.replace(base, parametrosDTO.getBaseURL());
		} catch (Exception e) {
			LOG.debug("No tiene base URL");
		}

		HTML = HTML.replace(parametrosDTO.getBasePagesPortal(), "");

		return HTML;
	}

	private static String htmlEncode(final String string) {
		final StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < string.length(); i++) {
			final Character character = string.charAt(i);
			if (CharUtils.isAscii(character)) {
				// Encode common HTML equivalent characters
				stringBuffer.append(org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(character.toString()));
			} else {
				// Why isn't this done in escapeHtml4()?
				stringBuffer.append(String.format("&#x%x;", Character.codePointAt(string, i)));
			}
		}
		return stringBuffer.toString();
	}

	private static String getMediaContentAMP(NNota dto) {
		String media = "";
		if (!dto.getFcVideoYoutube().trim().equals("") || !dto.getFcPlayerIdOoyala().trim().equals("")) {
			media = getVideoAMP(dto);
		} else {
			media = getImagenAMP(dto);
		}
		return media;
	}

	private static String getMediaContent(NNota dto, ParametrosDTO parametrosDTO) {
		String media = "";
		if (!dto.getFcVideoYoutube().trim().equals("") || !dto.getFcPlayerIdOoyala().trim().equals("")) {
			media = getVideo(dto);
		} else {
			media = getImagen(dto);
		}
		return media;
	}

	/**
	 * Se lleva a cabo el reemplazo del Media Content de la nota, de tipo imagen, se
	 * valida si la nota es de tipo galerÃ­a, infografia o default
	 * 
	 * @param ContentDTO
	 *            Instancia con la informaciÃ³n necesaria para reemplazar
	 * @return String Se devuelve una cadena con el Media Content de tipo imagen
	 * @author jesus
	 */
	private static String getImagen(NNota dto) {
		StringBuffer mediaImage = new StringBuffer("");
		String imgPrincipal = dto.getFcImagen() == null ? "" : dto.getFcImagen();

		String galeria = dto.getClGaleria() == null ? "" : dto.getClGaleria();

		if (!galeria.trim().equals("")) {
			mediaImage.append(cambiaCaracteres(galeria));
		}
		return mediaImage.toString();
	}

	/**
	 * Se lleva a cabo el reemplazo del Media Content de la nota, de tipo video, se
	 * valida si es youtube u ooyala
	 * 
	 * @param ContentDTO
	 *            Instancia con la informaciÃ³n necesaria para reemplazar
	 * @return String Se devuelve una cadena con el Media Content de tipo Video
	 * @author jesus
	 */
	private static String getVideo(NNota dto) {

		StringBuffer mediaContent = new StringBuffer();
		String IdVideoYouTube = dto.getFcVideoYoutube() == null ? "" : dto.getFcVideoYoutube().trim();
		String IdVideoOoyala = dto.getFcSourceOoyala()== null ? "" : dto.getFcSourceOoyala().trim();
		String IdPlayerVideoOoyala = dto.getFcPlayerIdOoyala() == null ? "" : dto.getFcPlayerIdOoyala().trim();

		if (!IdVideoYouTube.trim().equals("")) {
			mediaContent.append(" <div class=\"video\"> \n");
			mediaContent.append(
					"<iframe id=\"ytplayer\" type=\"text/html\" width=\"640\" height=\"360\" src=\"https://www.youtube.com/embed/"
							+ IdVideoYouTube + "\" frameborder=\"0\" allowfullscreen></iframe>\n");
			mediaContent.append(" </div> \n");
		} else if (!IdVideoOoyala.trim().equals("") && !IdPlayerVideoOoyala.trim().equals("")) {
			mediaContent.append(" <div class=\"video\"> \n");
			mediaContent.append(" <div id=\"ooyalaplayer\"></div> \n");
			mediaContent.append(" </div> \n");

			// VERSION 4
			mediaContent.append(" <!-- Ooyala V4--> \n");
			mediaContent.append(" <div class=\"video\"> \n");
			mediaContent.append("   <div id=\"ooyalaplayer\"></div> \n");
			mediaContent.append(" </div> \n");
			mediaContent.append(
					" <link rel=\"stylesheet\" href=\"//player.ooyala.com/static/v4/stable/4.13.5/skin-plugin/html5-skin.min.css\"> \n");
			mediaContent
					.append(" <script src=\"//player.ooyala.com/static/v4/stable/4.13.5/core.min.js\"></script> \n");
			mediaContent.append(
					" <script src=\"//player.ooyala.com/static/v4/stable/4.13.5/video-plugin/bit_wrapper.min.js\"></script> \n");
			mediaContent.append(
					" <script src=\"//player.ooyala.com/static/v4/stable/4.13.5/video-plugin/main_html5.min.js\"></script> \n");
			mediaContent.append(
					" <script src=\"//player.ooyala.com/static/v4/stable/4.13.5/skin-plugin/html5-skin.min.js\"></script> \n");
			mediaContent.append(
					" <script src=\"//player.ooyala.com/static/v4/stable/4.13.5/ad-plugin/google_ima.min.js\"></script> \n");
			mediaContent.append(" <script> \n");
			mediaContent.append("   var playerParam = { \n");
			mediaContent.append("     'pcode': '" + dto.getFcPCode() + "', \n");
			mediaContent.append("     'playerBrandingId': \"" + IdPlayerVideoOoyala + "\", \n");
			mediaContent.append("     'skin': { \n");
			mediaContent.append("       'config': '/ooyala/4.13.5/skin.json' \n");
			mediaContent.append("     } \n");
			mediaContent.append("   }; \n");
			mediaContent.append("   OO.ready(function() { \n");
			mediaContent.append(
					"     window.pp = OO.Player.create('ooyalaplayer', \"" + IdVideoOoyala + "\", playerParam); \n");
			mediaContent.append("   }); \n");
			mediaContent.append(" </script> \n");
		}
		return mediaContent.toString();
	}

	private static String getImagenAMP(NNota dto) {
		StringBuffer mediaImage = new StringBuffer("");
		String imgPrincipal = dto.getFcImagen() == null ? "" : dto.getFcImagen();

		mediaImage.append(
				"<amp-img width=\"545\" height=\"360\" layout=\"responsive\" src=\"" + imgPrincipal + "\"></amp-img>");
		return mediaImage.toString();
	}

	private static String getVideoAMP(NNota dto) {

		StringBuffer mediaContent = new StringBuffer();
		String IdVideoYouTube = dto.getFcVideoYoutube() == null ? "" : dto.getFcVideoYoutube().trim();
		String IdVideoOoyala = dto.getFcSourceOoyala() == null ? "" : dto.getFcSourceOoyala().trim();
		String IdPlayerVideoOoyala = dto.getFcPlayerIdOoyala() == null ? "" : dto.getFcPlayerIdOoyala().trim();

		if (!IdVideoYouTube.trim().equals("")) {
			mediaContent.append(
					"<amp-iframe class=\"video\" width=\"16\" height=\"9\" layout=\"responsive\" sandbox=\"allow-scripts allow-same-origin\" src=\"https://www.youtube.com/embed/"
							+ IdVideoYouTube + "\"></amp-iframe>");
		} else if (!IdVideoOoyala.trim().equals("") && !IdPlayerVideoOoyala.trim().equals("")) {
			mediaContent.append(
					"<amp-iframe class=\"video\" width=\"16\" height=\"9\" layout=\"responsive\" sandbox=\"allow-scripts allow-same-origin\" src=\"https://player.ooyala.com/iframe.html?ec="
							+ IdVideoOoyala + "&amp;pbid=" + IdPlayerVideoOoyala + "&amp;platform=html5\">\n");
			mediaContent.append("<amp-img layout=\"fill\" src=\"" + dto.getFcImagen() + "\" placeholder></amp-img>\n");
			mediaContent.append("</amp-iframe>\n");

		}
		return mediaContent.toString();
	}

	private static String cambiaCaracteres(String texto) {

		texto = texto.replaceAll("Ã¡", "&#225;");
		texto = texto.replaceAll("Ã©", "&#233;");
		texto = texto.replaceAll("Ã­", "&#237;");
		texto = texto.replaceAll("Ã³", "&#243;");
		texto = texto.replaceAll("Ãº", "&#250;");
		texto = texto.replaceAll("Ã�", "&#193;");
		texto = texto.replaceAll("Ã‰", "&#201;");
		texto = texto.replaceAll("Ã�", "&#205;");
		texto = texto.replaceAll("Ã“", "&#211;");
		texto = texto.replaceAll("Ãš", "&#218;");
		texto = texto.replaceAll("Ã‘", "&#209;");
		texto = texto.replaceAll("Ã±", "&#241;");
		texto = texto.replaceAll("Âª", "&#170;");
		texto = texto.replaceAll("Ã¤", "&#228;");
		texto = texto.replaceAll("Ã«", "&#235;");
		texto = texto.replaceAll("Ã¯", "&#239;");
		texto = texto.replaceAll("Ã¶", "&#246;");
		texto = texto.replaceAll("Ã¼", "&#252;");
		texto = texto.replaceAll("Ã„", "&#196;");
		texto = texto.replaceAll("Ã‹", "&#203;");
		texto = texto.replaceAll("Ã�", "&#207;");
		texto = texto.replaceAll("Ã–", "&#214;");
		texto = texto.replaceAll("Ãœ", "&#220;");
		texto = texto.replaceAll("Â¿", "&#191;");
		texto = texto.replaceAll("Â“", "&#8220;");
		texto = texto.replaceAll("Â”", "&#8221;");
		texto = texto.replaceAll("Â‘", "&#8216;");
		texto = texto.replaceAll("Â’", "&#8217;");
		texto = texto.replaceAll("Â…", "...");
		texto = texto.replaceAll("Â¡", "&#161;");
		texto = texto.replaceAll("Â¿", "&#191;");
		texto = texto.replaceAll("Â°", "&#176;");

		texto = texto.replaceAll("Â“", "&#147;");
		texto = texto.replaceAll("Â”", "&#148;");

		texto = texto.replaceAll("Â–", "&#8211;");
		texto = texto.replaceAll("Â—", "&#8212;");

		// texto = texto.replaceAll("\"", "&#34;");
		return texto;
	}

	private static String getEmbedPost(String RTFContenido) {
		try {
			String rtfContenido = RTFContenido;

			String url, cadenaAReemplazar;
			StringBuffer embedCode;
			HashMap<String, ArrayList<RedSocialEmbedPostDTO>> MapAReemplazar = new HashMap<String, ArrayList<RedSocialEmbedPostDTO>>();
			int num_post_embebidos;
			int contador;

			if (rtfContenido.contains("[instagram")) {
				// LOG.info("Embed Code instagram");
				ArrayList<RedSocialEmbedPostDTO> listRedSocialEmbedInstagram = new ArrayList<RedSocialEmbedPostDTO>();
				num_post_embebidos = rtfContenido.split("\\[instagram=").length - 1;
				contador = 1;
				do {
					RedSocialEmbedPostDTO embebedPost = new RedSocialEmbedPostDTO();
					String cadenas = devuelveCadenasPost("instagram", rtfContenido);
					cadenaAReemplazar = cadenas.split("\\|")[0];
					url = cadenas.split("\\|")[1];
					rtfContenido = rtfContenido.replace(cadenaAReemplazar, "");
					embedCode = new StringBuffer();
					embedCode.append(" <div class=\"instagram-post\"> \n");
					embedCode.append(
							" <blockquote data-instgrm-captioned data-instgrm-version=\"6\" class=\"instagram-media\"> \n");
					embedCode.append(" <div> \n");
					embedCode.append(" 	<p><a href=\"" + url + "\"></a></p> \n");
					embedCode.append(" </div> \n");
					embedCode.append(" </blockquote> \n");
					embedCode.append(
							" <script async defer src=\"//platform.instagram.com/en_US/embeds.js\"></script> \n");
					embedCode.append(" </div> \n");

					embebedPost.setCadena_que_sera_reemplazada(cadenaAReemplazar);
					embebedPost.setRed_social("instagram");
					embebedPost.setCodigo_embebido(embedCode.toString());

					listRedSocialEmbedInstagram.add(embebedPost);
					contador++;
				} while (contador <= num_post_embebidos);

				MapAReemplazar.put("instagram", listRedSocialEmbedInstagram);
			}
			if (rtfContenido.contains("[twitter")) {
				// LOG.info("Embed Code twitter");
				ArrayList<RedSocialEmbedPostDTO> listRedSocialEmbedTwitter = new ArrayList<RedSocialEmbedPostDTO>();
				num_post_embebidos = rtfContenido.split("\\[twitter=").length - 1;
				contador = 1;
				do {
					RedSocialEmbedPostDTO embebedPost = new RedSocialEmbedPostDTO();
					String cadenas = devuelveCadenasPost("twitter", rtfContenido);
					cadenaAReemplazar = cadenas.split("\\|")[0];
					url = cadenas.split("\\|")[1];
					rtfContenido = rtfContenido.replace(cadenaAReemplazar, "");
					embedCode = new StringBuffer();
					embedCode.append(" <div class=\"tweeet-post\"> \n");
					embedCode.append(
							" 		<blockquote data-width=\"500\" lang=\"es\" class=\"twitter-tweet\"><a href=\"" + url
									+ "\"></a></blockquote> \n");
					embedCode.append(
							" 		<script type=\"text/javascript\" async defer src=\"//platform.twitter.com/widgets.js\" id=\"twitter-wjs\"></script> \n");
					embedCode.append(" </div> \n");

					embebedPost.setCadena_que_sera_reemplazada(cadenaAReemplazar);
					embebedPost.setRed_social("twitter");
					embebedPost.setCodigo_embebido(embedCode.toString());

					listRedSocialEmbedTwitter.add(embebedPost);
					contador++;
				} while (contador <= num_post_embebidos);

				MapAReemplazar.put("twitter", listRedSocialEmbedTwitter);

			}
			if (rtfContenido.contains("[facebook")) {
				// LOG.info("Embed Code facebook");
				ArrayList<RedSocialEmbedPostDTO> listRedSocialEmbedFacebook = new ArrayList<RedSocialEmbedPostDTO>();
				num_post_embebidos = rtfContenido.split("\\[facebook=").length - 1;
				contador = 1;
				do {
					RedSocialEmbedPostDTO embebedPost = new RedSocialEmbedPostDTO();
					String cadenas = devuelveCadenasPost("facebook", rtfContenido);
					cadenaAReemplazar = cadenas.split("\\|")[0];
					url = cadenas.split("\\|")[1];
					rtfContenido = rtfContenido.replace(cadenaAReemplazar, "");
					embedCode = new StringBuffer();
					embedCode = new StringBuffer();
					embedCode.append(" <div class=\"facebook-post\"> \n");
					embedCode.append(
							" 		<div data-href=\"" + url + "\" data-width=\"500\" class=\"fb-post\"></div> \n");
					embedCode.append(" </div> \n");

					embebedPost.setCadena_que_sera_reemplazada(cadenaAReemplazar);
					embebedPost.setRed_social("facebook");
					embebedPost.setCodigo_embebido(embedCode.toString());

					listRedSocialEmbedFacebook.add(embebedPost);
					contador++;
					;
				} while (contador <= num_post_embebidos);

				MapAReemplazar.put("facebook", listRedSocialEmbedFacebook);
			}
			if (rtfContenido.contains("[giphy")) {
				// LOG.info("Embed Code giphy");
				ArrayList<RedSocialEmbedPostDTO> listRedSocialEmbedGiphy = new ArrayList<RedSocialEmbedPostDTO>();
				num_post_embebidos = rtfContenido.split("\\[giphy=").length - 1;
				contador = 1;
				do {
					RedSocialEmbedPostDTO embebedPost = new RedSocialEmbedPostDTO();
					String cadenas = devuelveCadenasPost("giphy", rtfContenido);
					cadenaAReemplazar = cadenas.split("\\|")[0];
					url = cadenas.split("\\|")[1];
					rtfContenido = rtfContenido.replace(cadenaAReemplazar, "");
					embedCode = new StringBuffer();
					embedCode = new StringBuffer();
					embedCode.append(" <img src=\"" + url.split("\\,")[1] + "\" class=\"giphy\"> \n");
					embedCode.append(" <span>V&iacute;a  \n");
					embedCode.append(" 	<a href=\"" + url.split("\\,")[0] + "\" target=\"_blank\">Giphy</a> \n");
					embedCode.append("  </span> \n");

					embebedPost.setCadena_que_sera_reemplazada(cadenaAReemplazar);
					embebedPost.setRed_social("giphy");
					embebedPost.setCodigo_embebido(embedCode.toString());

					listRedSocialEmbedGiphy.add(embebedPost);
					contador++;
				} while (contador <= num_post_embebidos);

				MapAReemplazar.put("giphy", listRedSocialEmbedGiphy);
			}

			if (!MapAReemplazar.isEmpty()) {
				Iterator<String> iterator_red_social = MapAReemplazar.keySet().iterator();
				String red_social = "", codigo_embebido = "", cadena_que_sera_reemplazada = "";
				while (iterator_red_social.hasNext()) {
					red_social = iterator_red_social.next();
					if (red_social.equalsIgnoreCase("twitter") || red_social.equalsIgnoreCase("facebook")
							|| red_social.equalsIgnoreCase("instagram") || red_social.equalsIgnoreCase("giphy")) {
						ArrayList<RedSocialEmbedPostDTO> listEmbebidos = MapAReemplazar.get(red_social);
						for (RedSocialEmbedPostDTO redSocialEmbedPost : listEmbebidos) {
							cadena_que_sera_reemplazada = redSocialEmbedPost.getCadena_que_sera_reemplazada();
							codigo_embebido = redSocialEmbedPost.getCodigo_embebido();
							RTFContenido = RTFContenido.replace(cadena_que_sera_reemplazada, codigo_embebido);
						}

					}
				}
			}
			return RTFContenido;
		} catch (Exception e) {
			LOG.error("Error getEmbedPost: ", e);
			return RTFContenido;
		}
	}

	private static String devuelveCadenasPost(String id_red_social, String rtfContenido) {
		String url = "", cadenaAReemplazar = "", salida = "";
		try {
			cadenaAReemplazar = rtfContenido.substring(rtfContenido.indexOf("[" + id_red_social + "="),
					rtfContenido.indexOf("=" + id_red_social + "]")) + "=" + id_red_social + "]";
			url = cadenaAReemplazar.replace("[" + id_red_social + "=", "").replace("=" + id_red_social + "]", "");
			salida = cadenaAReemplazar + "|" + url;
		} catch (Exception e) {
			LOG.error("Error devuelveCadenasPost: ", e);
			return "|";
		}
		return salida;
	}

	/**
	 * Metodo que remplaza los metas de una nota
	 * 
	 * @param HTML
	 * @param ContentDTO
	 * @param ParametrosDTO
	 * @return boolean
	 * @author jesus
	 */
	private static String remplazaMetas(String HTML, NNota contentDTO, ParametrosDTO parametrosDTO) {
		TimeZone tz = TimeZone.getTimeZone("America/Mexico_City");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		df.setTimeZone(tz);
		Date date = new Date();

		// Remplaza og:image
		try {
			String imageFija = "/utils/img/default-noticias.png";
			HTML = HTML.replace(imageFija, contentDTO.getFcImagen().trim());
		} catch (Exception e) {
			LOG.error("Error al reemplazar meta og:image");
		}

		// Remplaza twitter:image
		try {
			HTML = HTML.replace("twitter:image", "twitter:image:src");
		} catch (Exception e) {
			LOG.error("Error al reemplazar meta twitter:image");
		}

		try {
			HTML = HTML.replace("$WCM_KEYWORDS$", cambiaCaracteres(contentDTO.getFcKeywords().trim()));
		} catch (Exception e) {
			HTML = HTML.replace("$WCM_KEYWORDS$", "");
			LOG.error("Error al remplazar $WCM_KEYWORDS$");
		}

		// Remplaza nota:published_time [$META_PUBLISHED_TIME$]
		try {
			String fechaS = df.format(contentDTO.getFdFechaPublicacion());
			fechaS = fechaS.substring(0, fechaS.length() - 2) + ":00";
			HTML = HTML.replace("$META_PUBLISHED_TIME$", fechaS);
		} catch (Exception e) {
			HTML = HTML.replace("$META_PUBLISHED_TIME$", "");
			LOG.error("Error al remplazar $META_PUBLISHED_TIME$");
		}

		// Remplaza nota:modified_time [$META_MODIFIED_TIME$]
		try {
			String fechaS = df.format(date);
			fechaS = fechaS.substring(0, fechaS.length() - 2) + ":00";
			HTML = HTML.replace("$META_MODIFIED_TIME$", fechaS);
		} catch (Exception e) {
			HTML = HTML.replace("$META_MODIFIED_TIME$", "");
			LOG.error("Error al remplazar $META_MODIFIED_TIME$");
		}
		// Remplaza nota:tipo [$META_CONTENT_ID$]
		try {
			HTML = HTML.replace("$META_CONTENT_ID$", contentDTO.getFcIdContenido());
		} catch (Exception e) {
			HTML = HTML.replace("$META_CONTENT_ID$", "");
			LOG.error("Error al remplazar $META_CONTENT_ID$");
		}
		// Remplaza nota:tipo [$META_FRIENDLY_URL$]
		try {
			HTML = HTML.replace("$META_FRIENDLY_URL$", contentDTO.getFcFriendlyUrl());
		} catch (Exception e) {
			HTML = HTML.replace("$META_FRIENDLY_URL$", "");
			LOG.error("Error al remplazar $META_FRIENDLY_URL$");
		}
		// Remplaza nota:tipo [$META_TIPO$]
		try {
			HTML = HTML.replace("$META_TIPO$", contentDTO.getFcIdTipoNota());
		} catch (Exception e) {
			HTML = HTML.replace("$META_TIPO$", "");
			LOG.error("Error al remplazar $META_TIPO$");
		}

		// Remplaza nota:tipo_seccion [$META_TIPO_SECCION$]
		try {
			HTML = HTML.replace("$META_TIPO_SECCION$", contentDTO.getFcIdSeccion());
		} catch (Exception e) {
			HTML = HTML.replace("$META_TIPO_SECCION$", "");
			LOG.error("Error al remplazar $META_TIPO_SECCION$");
		}

		// Remplaza nota:seccion [$META_SECCION$]
		try {
			HTML = HTML.replace("$META_SECCION$", contentDTO.getFcIdSeccion());
		} catch (Exception e) {
			HTML = HTML.replace("$META_SECCION$", "");
			LOG.error("Error al remplazar $META_SECCION$");
		}

		// Remplaza nota:categoria [$META_CATEGORIA$]
		try {
			HTML = HTML.replace("$META_CATEGORIA$", contentDTO.getFcIdCategoria());
		} catch (Exception e) {
			HTML = HTML.replace("$META_CATEGORIA$", "");
			LOG.error("Error al remplazar $META_CATEGORIA$");
		}

		// Remplaza nota:tags [$META_TAGS$]
		/*try {
			HTML = HTML.replace("$META_TAGS$", cambiaCaracteres(contentDTO.getf));
		} catch (Exception e) {
			HTML = HTML.replace("$META_TAGS$", "");
			LOG.error("Error al remplazar $META_TAGS$");
		}*/

		// Remplaza nota:tags [$META_IMG$]
		try {
			HTML = HTML.replace("$META_IMG$", contentDTO.getFcImagen());
		} catch (Exception e) {
			HTML = HTML.replace("$META_IMG$", "");
			LOG.error("Error al remplazar $META_IMG$");
		}

		// Remplaza nota:tags [$META_TITULO$]
		try {
			HTML = HTML.replace("$META_TITULO$", htmlEncode(contentDTO.getFcTitulo().trim()));
		} catch (Exception e) {
			HTML = HTML.replace("$META_TITULO$", "");
			LOG.error("Error al remplazar $META_TITULO$");
		}

		// Remplaza los metas de video ooyala
		if (!contentDTO.getFcPlayerIdOoyala().equals("") && !contentDTO.getFcPlayerIdOoyala().equals("")) {
			try {
				HTML = HTML.replace("$OG_VIDEO$",
						parametrosDTO.getMetaVideo().replace("$ID_VIDEO$", contentDTO.getFcSourceOoyala())
								.replace("$ID_VIDEO_PLAYER$", contentDTO.getFcPlayerIdOoyala()));
				HTML = HTML.replace("$OG_VIDEO_SECURE$",
						parametrosDTO.getMetaVideoSecureUrl().replace("$ID_VIDEO$", contentDTO.getFcSourceOoyala())
								.replace("$ID_VIDEO_PLAYER$", contentDTO.getFcPlayerIdOoyala()));
			} catch (Exception e) {
				HTML = HTML.replace("$OG_VIDEO_SECURE$", "");
				HTML = HTML.replace("$OG_VIDEO$", "");
				LOG.error("Error al sustituir metas de Video $OG_VIDEO$ y $OG_VIDEO_SECURE$");
			}
		} else {
			HTML = HTML.replace("<meta property=\"og:video\" content=\"$OG_VIDEO$\" />", "");
			HTML = HTML.replace("<meta property=\"og:video:secure_url\" content=\"$OG_VIDEO_SECURE$\" />", "");
			HTML = HTML.replace("<meta property=\"og:video:type\" content=\"application/x-shockwave-flash\" />", "");
			HTML = HTML.replace("<meta property=\"og:type\" content=\"video.other\" />", "");
			HTML = HTML.replace("<meta property=\"og:video:height\" content=\"480\" />", "");
			HTML = HTML.replace("<meta property=\"og:video:width\" content=\"640\" />", "");
		}

		// Remplaza nota:tags [$META_IMG$]
		/*try {

			LlamadasWSBO llamadasWSBO = new LlamadasWSBO();
			// String
			// cad="version_styles,$VERSION_STYLES$|version_scripts,$VERSION_SCRIPT$|version_libs,$VERSION_LIBS$|version_gas_json,$VERSION_GAS_JSON$|version_banner_json,$VERSION_BANNER_JSON$";
			String[] params = parametrosDTO.getCatalogoParametros().split("\\|");
			String valor = "";
			String cad_a_reemplazar = "";
			for (int i = 0; i < params.length; i++) {
				valor = llamadasWSBO.getParameter(params[i].split("\\,")[0], parametrosDTO);
				cad_a_reemplazar = params[i].split("\\,")[1];
				if (valor != null && !valor.equals("")) {
					HTML = HTML.replace(cad_a_reemplazar, valor);
				} else {
					HTML = HTML.replace(cad_a_reemplazar, "");
				}
			}
		} catch (Exception e) {
			LOG.error("Error al remplazar version de estilos" + e.getLocalizedMessage());
		} */

		return HTML;
	}

}
