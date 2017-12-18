/*
*
 * 
 */
package mx.com.amx.unotv.adminservice.bo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import mx.com.amx.unotv.adminservice.bo.exception.DetailBOException;
import mx.com.amx.unotv.adminservice.bo.exception.JsonBOException;
import mx.com.amx.unotv.adminservice.dto.ContentDTO;
import mx.com.amx.unotv.adminservice.dto.ParametrosDTO;
import mx.com.amx.unotv.adminservice.model.Categoria;
import mx.com.amx.unotv.adminservice.model.HNota;
import mx.com.amx.unotv.adminservice.model.NNota;
import mx.com.amx.unotv.adminservice.model.Seccion;
import mx.com.amx.unotv.adminservice.model.request.ImageRequest;
import mx.com.amx.unotv.adminservice.model.response.ImageResponse;
import mx.com.amx.unotv.adminservice.model.response.Item;
import mx.com.amx.unotv.adminservice.util.MapItemUtil;
import mx.com.amx.unotv.adminservice.util.PropertiesUtils;
import mx.com.amx.unotv.adminservice.util.Utils;
import mx.com.amx.unotv.adminservice.ws.CatalogsCallWS;
import mx.com.amx.unotv.adminservice.ws.DetailCallWS;
import mx.com.amx.unotv.adminservice.ws.FacebookCallWS;
import mx.com.amx.unotv.adminservice.ws.HNotaCallWS;
import mx.com.amx.unotv.adminservice.ws.NNotaCallWS;
import mx.com.amx.unotv.adminservice.ws.UploadImgCallWS;

/**
 * @author Jesus A. Macias Benitez
 *
 */
public class DetailBO {

	private static Logger logger = Logger.getLogger(DetailBO.class);

	@Autowired
	DetailCallWS detailCallWS;
	@Autowired
	NotaBO notaBO;
	@Autowired
	NNotaCallWS nNotaCallWS;
	@Autowired
	JsonBO jsonBO;
	@Autowired
	CatalogsCallWS catalogsCallWS;
	@Autowired
	FacebookCallWS facebookCallWS;
	@Autowired
	UploadImgCallWS uploadImgCallWS;
	@Autowired
	HNotaCallWS hNotaCallWS;
	@Autowired
	MapItemUtil mapItemUtil;

	public int saveItem(Item item) throws DetailBOException {
		logger.debug("*** Inicia saveItem [ DetailBO ] ***");

		ParametrosDTO parametrosDTO = null;
		PropertiesUtils properties = new PropertiesUtils();

		boolean success = false;

		String id_facebook = "";
		String carpetaContenido = "";
		String urlNota = "";
		int res = 0;
		SimpleDateFormat dateFormat;

		NNota nota = null;
		ImageRequest imgRequest = null;

		// Obtenemos archivo de propiedades
		try {

			parametrosDTO = properties.obtenerPropiedades();

			nota = mapItemUtil.MapItemToNota(item);

			dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			nota.setFdFechaPublicacion(dateFormat.format(new Date()));
			nota.setFdFechaModificacion(dateFormat.format(new Date()));
			nota.setFcIdEstatus("PUB");

			Categoria categoria = catalogsCallWS.getCategorieById(nota.getFcIdCategoria());
			parametrosDTO.setNombreCategoria(categoria.getFcDescripcion());
			parametrosDTO.setFcIdSeccion(categoria.getFcIdSeccion());
			// Crop Images Facebook , Miniatura

			try {
				imgRequest = new ImageRequest();
				imgRequest.setType("FB");
				imgRequest.setNameImage(item.getMain_image().getSrc());
				imgRequest.setxPosition(item.getMain_image().getCordenadas_facebook().getX());
				imgRequest.setyPosition(item.getMain_image().getCordenadas_facebook().getY());

				ImageResponse responseFB = uploadImgCallWS.cropImage(imgRequest, parametrosDTO.getUrlCropImage());

				logger.debug(" ImageResponse [ responseFB ]: " + responseFB.toString());

				imgRequest.setType("cuadrada");
				imgRequest.setxPosition(item.getMain_image().getCordenadas_miniatura().getX());
				imgRequest.setyPosition(item.getMain_image().getCordenadas_miniatura().getY());

				ImageResponse responseCuadrada = uploadImgCallWS.cropImage(imgRequest, parametrosDTO.getUrlCropImage());

				logger.debug(" ImageResponse [ responseCuadrada ]: " + responseCuadrada.toString());

			} catch (Exception e) {
				logger.error("--- Exception  createPlantillaAMP [ saveItem  ] : " + e.getMessage());
				throw new DetailBOException(e.getMessage());
			}

			// ruta con dominio wwww.unotv.com ó http://dev-unotv.tmx-internacional.net
			logger.info("Frendy URL: " + nota.getFcFriendlyUrl());
			urlNota = parametrosDTO.getDominio() + "/" + Utils.getRutaContenido(nota, parametrosDTO);
			logger.info("URL: " + urlNota);

			// Ruta donde se va guardar el html servidor var/www/share_wwwww
			carpetaContenido = parametrosDTO.getPathFiles() + Utils.getRutaContenido(nota, parametrosDTO);
			logger.info("carpetaContenido: " + carpetaContenido);

			// Validamos si la nota contiene video de ooyala
			logger.debug("**TIPO DE NOTA: " + nota.getFcIdTipoNota());

			try {
				// Guardamos o actualizamos la nota en la base de datos.
				/*
				 * se manda item ya que cuando se inserta la nota en las tablas hnota y nnnota ,
				 * se tienen que insertar en la tabla tags
				 */
				res = notaBO.saveOrUpdate(nota, item.getTags());

			} catch (Exception e) {
				logger.error("--- Exception  saveOrUpdate [ saveItem  ] : " + e.getMessage());
				throw new DetailBOException(e.getMessage());
			}

			if (res > 0) {
				// Guardamos o actualizamos los Tags de la nota

				// Creamos estrcutura de directorios
				success = Utils.createFolders(carpetaContenido);

				if (success)
					success = Utils.createPlantilla(parametrosDTO, nota, carpetaContenido);
				if (success) {
					// Generamos el json del detalle para la app.
					try {
						jsonBO.generaDetalleJson(nota, parametrosDTO, carpetaContenido);
					} catch (JsonBOException je) {
						logger.error("--- Exception  generaDetalleJson [ saveItem  ] : " + je.getMessage());
						throw new DetailBOException(je.getMessage());
					}

					try {
						// String html_amp=Utils.createPlantillaAMP(parametrosDTO, nota);
						Utils.createPlantillaAMP(parametrosDTO, nota);

						// Enviamos push de AMP
						/*
						 * if(!html_amp.equals("")){
						 * 
						 * SimpleDateFormat dateFormat = new
						 * SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS"); Date parsedDate =
						 * dateFormat.parse(nota.getFdFechaPublicacion()); Timestamp timestamp = new
						 * java.sql.Timestamp(parsedDate.getTime());
						 * 
						 * 
						 * logger.info("Enviamos PUSH al AMP"); PushAmpDTO pushAMP=new PushAmpDTO();
						 * pushAMP.setFcIdCategoria(nota.getFcIdCategoria());
						 * pushAMP.setFcIdContenido(nota.getFcIdContenido());
						 * pushAMP.setFcNombre(nota.getFcFriendlyUrl());
						 * pushAMP.setFcSeccion(nota.getFcIdSeccion()); //
						 * pushAMP.setFcTipoSeccion(contentDTO.getFcTipoSeccion());
						 * pushAMP.setFcTitulo(nota.getFcTitulo());
						 * pushAMP.setFdFechaPublicacion(timestamp); pushAMP.setHtmlAMP(html_amp);
						 * //RespuestaWSAmpDTO respuestaWSAMP=llamadasWSBO.sendPushAMP(pushAMP,
						 * parametrosDTO); //LOG.info("Respuesta AMP: "+respuestaWSAMP.getRespuesta());
						 * }
						 */

					} catch (Exception ampe) {
						logger.error("--- Exception  createPlantillaAMP [ saveItem  ] : " + ampe.getMessage());
						throw new DetailBOException(ampe.getMessage());
					}

					// Enviamos Push de Instant Article

					ContentDTO contentDTO = new ContentDTO();

					Seccion seccion = catalogsCallWS.getSeccionById(categoria.getFcIdSeccion());

					Date parsedDate = dateFormat.parse(nota.getFdFechaPublicacion());
					Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

					contentDTO.setClGaleriaImagenes((nota.getClGaleria() == null) ? "" : nota.getClGaleria());
					contentDTO.setClRtfContenido((nota.getClRtfContenido() == null) ? "" : nota.getClRtfContenido());
					contentDTO.setFcEscribio((nota.getFcEscribio() == null) ? "" : nota.getFcEscribio());
					contentDTO.setFcIdCategoria((nota.getFcIdCategoria() == null) ? "" : nota.getFcIdCategoria());
					contentDTO.setFcSeccion((categoria.getFcIdSeccion() == null) ? "" : categoria.getFcIdSeccion()); // categoria.getFcIdSeccion()
					contentDTO.setFcAlternateTextVideo(
							(nota.getFcAlternativeTextOoyala() == null) ? "" : nota.getFcAlternativeTextOoyala());
					contentDTO
							.setFcDurationVideo((nota.getFcDurationOoyala() == null) ? "" : nota.getFcDurationOoyala());
					contentDTO.setFcFuente((nota.getFcFuente() == null) ? "" : nota.getFcFuente());
					contentDTO.setFcIdContenido((nota.getFcIdContenido() == null) ? "" : nota.getFcIdContenido());
					contentDTO.setFcIdPlayerOoyala(
							(nota.getFcPlayerIdOoyala() == null) ? "" : nota.getFcPlayerIdOoyala());
					contentDTO.setFcIdTipoNota((nota.getFcIdTipoNota() == null) ? "" : nota.getFcIdTipoNota());
					contentDTO.setFcIdVideoOoyala(
							(nota.getFcContentIdOoyala() == null) ? "" : nota.getFcContentIdOoyala());
					contentDTO.setFcIdVideoYouTube((nota.getFcVideoYoutube() == null) ? "" : nota.getFcVideoYoutube());
					contentDTO.setFcImgPrincipal((nota.getFcImagen() == null) ? "" : nota.getFcImagen());
					contentDTO.setFcKeywords((nota.getFcKeywords() == null) ? "" : nota.getFcKeywords());
					contentDTO.setFcLugar((nota.getFcLugar() == null) ? "" : nota.getFcLugar());
					contentDTO.setFcSourceVideo((nota.getFcSourceOoyala() == null) ? "" : nota.getFcSourceOoyala());
					contentDTO.setFcNombre((nota.getFcFriendlyUrl() == null) ? "" : nota.getFcFriendlyUrl());
					contentDTO.setFcNombreCategoria(
							(categoria.getFcDescripcion() == null) ? "" : categoria.getFcDescripcion());
					contentDTO.setFcPCode((nota.getFcIdPcode() == null) ? "" : nota.getFcIdPcode());
					contentDTO.setFcSeccion((seccion.getFcDescripcion() == null) ? "" : seccion.getFcDescripcion());
					contentDTO.setFdFechaPublicacion(timestamp);
					contentDTO.setFiBanInfinito(nota.getFiBanInfinitoHome());
					contentDTO.setFcFriendlyURLCategoria(
							(categoria.getFcFriendlyUrl() == null) ? "" : categoria.getFcFriendlyUrl());
					contentDTO.setFcFriendlyURLSeccion(
							(seccion.getFcFriendlyUrl() == null) ? "" : seccion.getFcFriendlyUrl());

					try {
						// id_facebook = facebookCallWS.insertUpdateArticleFB(contentDTO,
						// parametrosDTO);
						logger.debug("id_facebook: " + id_facebook);
					} catch (Exception boe) {
						logger.error("--- Exception  facebookCallWS  [ saveItem  ] : " + boe.getMessage());
						throw new DetailBOException(boe.getMessage());
					}

				}

			}

		} catch (Exception e) {
			logger.error("--- Exception saveItem  [ DetailBO ]: ", e);
			throw new DetailBOException(e.getMessage());
		}

		return res;

	}

	public int reviewItem(Item item) throws DetailBOException {

		int res = 0;
		NNota nota = null;

		try {

			nota = mapItemUtil.MapItemToNota(item);
			res = notaBO.reviewItem(nota, item.getTags());

		} catch (Exception e) {
			logger.error("--- Exception reviewItem  [ DetailBO ]: ", e);
			throw new DetailBOException(e.getMessage());
		}
		return res;
	}

	public int expireItem(Item item) throws DetailBOException {

		int res = 0;
		NNota nota = null;

		ParametrosDTO parametrosDTO = null;
		PropertiesUtils properties = null;

		try {

			properties = new PropertiesUtils();
			parametrosDTO = properties.obtenerPropiedades();
			nota = mapItemUtil.MapItemToNota(item);

			Categoria categoria = catalogsCallWS.getCategorieById(nota.getFcIdCategoria());
			parametrosDTO.setFcIdSeccion(categoria.getFcIdSeccion());

			// Ruta para borrar html
			String carpetaContenido = parametrosDTO.getPathFiles() + Utils.getRutaContenido(nota, parametrosDTO);
			logger.debug("carpetaContenido: " + carpetaContenido);
			// Borramos html
			boolean delteHTML = Utils.deleteHTML(carpetaContenido);
			logger.debug("Se borro direcorio: " + delteHTML);

			if (delteHTML) {

				res = notaBO.expireItem(nota);

			}

		} catch (Exception e) {
			logger.error(" --- Exception expireItem  [ DetailBO ]: ", e);
			throw new DetailBOException(e.getMessage());
		}
		return res;
	}

	public Item findNotaById(String idContenido) throws DetailBOException {

		HNota nota = null;

		try {

			nota = hNotaCallWS.findById(idContenido);
			return mapItemUtil.MapNotaToItem(nota);

		} catch (Exception e) {
			logger.error(" --- Exception findNotaById  [ DetailBO ]: ", e);
			throw new DetailBOException(e.getMessage());
		}

	}

	public List<HNota> findAllNota() throws DetailBOException {

		try {
			return detailCallWS.findAllNota();
		} catch (Exception e) {
			logger.error(" --- Exception findAllNota  [ DetailBO ]: ", e);
			throw new DetailBOException(e.getMessage());
		}

	}

}
