package mx.com.amx.unotv.adminservice.bo;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import mx.com.amx.unotv.adminservice.bo.exception.JsonBOException;

import mx.com.amx.unotv.adminservice.dto.RedSocialEmbedPost;
import mx.com.amx.unotv.adminservice.model.Categoria;
import mx.com.amx.unotv.adminservice.model.NNota;
import mx.com.amx.unotv.adminservice.model.ParametrosDTO;
import mx.com.amx.unotv.adminservice.model.Seccion;
import mx.com.amx.unotv.adminservice.ws.CatalogsCallWS;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;


public class JsonBO {

	//LOG
	private static Logger LOG = Logger.getLogger(JsonBO.class);
	
	
	@Autowired
	CatalogsCallWS catalogsCallWS;
	
	
	/**
	 * 
	 * */
	public void generaDetalleJson(NNota nota, ParametrosDTO parametrosDTO, String carpetaContenido) throws JsonBOException
	{
		LOG.debug("Inicia generaDetalleJson");
		
		Seccion seccion = null;
		Categoria categoria = null;
		
		try {
			
			
			categoria = catalogsCallWS.getCategorieById(nota.getFcIdCategoria());
			seccion = catalogsCallWS.getSeccionById(categoria.getFcIdSeccion());
			//ExtraInfoContentDTO extraInfoContentDTO = llamadasWSDAO._getExtraInfoContent(contentDTO.getFcNombre(), parametrosDTO);
			//LOG.debug("extraInfoContentDTO: "+extraInfoContentDTO);
						
			JSONObject jsonDetalle = new JSONObject();
			JSONObject jsonNota = new JSONObject();			
			
			jsonNota.put("url_nota", nota.getFcFriendlyUrl()); // se arma con el dominio dev...
			jsonNota.put("id_contenido", nota.getFcIdContenido());			
			jsonNota.put("id_categoria", nota.getFcIdCategoria());
			jsonNota.put("nombre", nota.getFcFriendlyUrl().trim());			
			jsonNota.put("titulo", nota.getFcTitulo().trim().replaceAll("\n", "").replaceAll("\r", ""));
			jsonNota.put("descripcion", nota.getFcDescripcion().trim().replaceAll("/\r?\n/g", "").replaceAll("\n", "").replaceAll("\r", ""));
			jsonNota.put("escribio", nota.getFcEscribio());
			
			jsonNota.put("lugar", nota.getFcLugar());
			jsonNota.put("fuente", nota.getFcFuente());
			jsonNota.put("id_tipo_nota", nota.getFcIdTipoNota());
			jsonNota.put("imagen_principal", nota.getFcImagen());
			//jsonNota.put("pie_imagen", contentDTO.getFcPieFoto());
			jsonNota.put("video_youtube", nota.getFcVideoYoutube());
			jsonNota.put("id_video_content", nota.getFcPlayerIdOoyala());
			jsonNota.put("id_video_player", nota.getFcPlayerIdOoyala());
			jsonNota.put("id_video_pcode", nota.getFcIdPcode());
			jsonNota.put("galeria", nota.getClGaleria());
			
			jsonNota.put("contenido_nota","<div class=\"content-article "+nota.getFcIdCategoria()+"\">"+ cambiaCaracteres(getContenidoRFC(nota.getClRtfContenido()))+"<div>");						
			jsonNota.put("fecha_publicacion", nota.getFdFechaPublicacion());
			jsonNota.put("fecha_modificacion", nota.getFdFechaModificacion());			
			
			jsonNota.put("adSetCode", "");
			jsonNota.put("ruta_dfp", categoria.getFcRutaDfp());
			jsonNota.put("desc_categoria", categoria.getFcDescripcion());
			jsonNota.put("desc_seccion", seccion.getFcDescripcion());
			
			
			
			jsonNota.put("desc_categoria",nota.getFcIdCategoria());
			jsonNota.put("desc_seccion", categoria.getFcIdSeccion());
			//jsonNota.put("posicion_galeria", contentDTO.getPlaceGallery());
			
			jsonDetalle.put("noticia", jsonNota);
			jsonDetalle.put("mensaje", "OK");
			jsonDetalle.put("codigo", "0");
			jsonDetalle.put("causa_error", "");				
			
			LOG.info("Ruta json: "+carpetaContenido+"/detalle.json");			
			writeJson(carpetaContenido+"/detalle.json", jsonDetalle.toString());			
		
		} catch (Exception e) {
			LOG.error("Exception en generaDetalleJson: ",e);
			throw new JsonBOException(e.getMessage());
		}		
	}
	
	

	
	
	/**
	 * 
	 * */
	public static String getContenidoRFC(String RTFContenido)
	{
		LOG.debug("Inicia getNoticiaJson..");
		try {
		
			String ini="<p dir=\"ltr\" style=\"text-align: justify;\">";
			String ini2="<p dir=\"ltr\">";
			String fin="</p>";
			
			LOG.debug("RTFContenido: "+RTFContenido);
			//Limpia Red Social
			LOG.debug("ini: "+ini);
			LOG.debug("fin: "+fin);
			
			RTFContenido=limpiaRedSocial(ini, fin, "instagram", RTFContenido);
			RTFContenido=limpiaRedSocial(ini, fin, "twitter", RTFContenido);
			RTFContenido=limpiaRedSocial(ini, fin, "facebook", RTFContenido);
			RTFContenido=limpiaRedSocial(ini, fin, "giphy", RTFContenido);
			
			
			LOG.debug("ini2: "+ini2);
			
			RTFContenido=limpiaRedSocial(ini2, fin, "instagram", RTFContenido);
			RTFContenido=limpiaRedSocial(ini2, fin, "twitter", RTFContenido);
			RTFContenido=limpiaRedSocial(ini2, fin, "facebook", RTFContenido);
			RTFContenido=limpiaRedSocial(ini2, fin, "giphy", RTFContenido);
			
			String rtfContenido=RTFContenido;
			
			String url, cadenaAReemplazar;
			StringBuffer embedCode;
			
			HashMap<String,ArrayList<RedSocialEmbedPost>> MapAReemplazar = new HashMap<String,ArrayList<RedSocialEmbedPost>>();
			int num_post_embebidos;
			int contador;
			
			if(rtfContenido.contains("[instagram")){
				LOG.debug("Embed Code instagram");
				ArrayList<RedSocialEmbedPost> listRedSocialEmbedInstagram=new ArrayList<RedSocialEmbedPost>();
				num_post_embebidos=rtfContenido.split("\\[instagram=").length-1;
				contador=1;
				do{
					RedSocialEmbedPost embebedPost=new RedSocialEmbedPost();
					String cadenas=devuelveCadenasPost("instagram", rtfContenido);
					cadenaAReemplazar=cadenas.split("\\|")[0];
					url=cadenas.split("\\|")[1];
					rtfContenido=rtfContenido.replace(cadenaAReemplazar, "");
					embedCode=new StringBuffer();
					embedCode.append("<embed-instagram data-insta=\""+url+"\"></embed-instagram>\n");

					embebedPost.setCadena_que_sera_reemplazada(cadenaAReemplazar);
					embebedPost.setRed_social("instagram");
					embebedPost.setCodigo_embebido(embedCode.toString());
					
					listRedSocialEmbedInstagram.add(embebedPost);
					contador ++;
				}while(contador <= num_post_embebidos);
				
				MapAReemplazar.put("instagram", listRedSocialEmbedInstagram);
			}
			if(rtfContenido.contains("[twitter")){
				LOG.debug("Embed Code twitter");
				ArrayList<RedSocialEmbedPost> listRedSocialEmbedTwitter=new ArrayList<RedSocialEmbedPost>();
				num_post_embebidos=rtfContenido.split("\\[twitter=").length-1;
				contador=1;
				do{
					RedSocialEmbedPost embebedPost=new RedSocialEmbedPost();
					String cadenas=devuelveCadenasPost("twitter", rtfContenido);
					cadenaAReemplazar=cadenas.split("\\|")[0];
					url=cadenas.split("\\|")[1];
					rtfContenido=rtfContenido.replace(cadenaAReemplazar, "");
					embedCode=new StringBuffer();
							
					embedCode.append(" <embed-tweet data-twett=\""+url+"\"></embed-tweet> \n");

					embebedPost.setCadena_que_sera_reemplazada(cadenaAReemplazar);
					embebedPost.setRed_social("twitter");
					embebedPost.setCodigo_embebido(embedCode.toString());
					
					listRedSocialEmbedTwitter.add(embebedPost);
					contador ++;
				}while(contador <= num_post_embebidos);
				
				MapAReemplazar.put("twitter", listRedSocialEmbedTwitter);
			
			}
			if(rtfContenido.contains("[facebook")){
				LOG.debug("Embed Code facebook");
				ArrayList<RedSocialEmbedPost> listRedSocialEmbedFacebook=new ArrayList<RedSocialEmbedPost>();
				num_post_embebidos=rtfContenido.split("\\[facebook=").length-1;
				contador=1;
				do{
					RedSocialEmbedPost embebedPost=new RedSocialEmbedPost();
					String cadenas=devuelveCadenasPost("facebook", rtfContenido);
					cadenaAReemplazar=cadenas.split("\\|")[0];
					url=cadenas.split("\\|")[1];
					rtfContenido=rtfContenido.replace(cadenaAReemplazar, "");
					embedCode=new StringBuffer();
										
					embedCode.append(" <embed-fb data-fb=\""+url+"\"></embed-fb> \n");
					
					embebedPost.setCadena_que_sera_reemplazada(cadenaAReemplazar);
					embebedPost.setRed_social("facebook");
					embebedPost.setCodigo_embebido(embedCode.toString());
					
					listRedSocialEmbedFacebook.add(embebedPost);
					contador++;;
				}while(contador <= num_post_embebidos);
				
				MapAReemplazar.put("facebook", listRedSocialEmbedFacebook);
			}
			if(rtfContenido.contains("[giphy")){
				LOG.debug("Embed Code giphy");
				ArrayList<RedSocialEmbedPost> listRedSocialEmbedGiphy=new ArrayList<RedSocialEmbedPost>();
				num_post_embebidos=rtfContenido.split("\\[giphy=").length-1;
				contador=1;
				do{
					RedSocialEmbedPost embebedPost=new RedSocialEmbedPost();
					String cadenas=devuelveCadenasPost("giphy", rtfContenido);
					//cadenas giphy: [giphy=http://giphy.com/gifs/sassy-batman-ZuM7gif8TCvqU,http://i.giphy.com/rgg2PJ6VJTyPC.gif=giphy]|http://giphy.com/gifs/sassy-batman-ZuM7gif8TCvqU,http://i.giphy.com/rgg2PJ6VJTyPC.gif
					//cadenas giphy: [giphy=http://giphy.com/gifs/superman-funny-wdh1SvEn0E06I,http://i.giphy.com/wdh1SvEn0E06I.gif=giphy]|http://giphy.com/gifs/superman-funny-wdh1SvEn0E06I,http://i.giphy.com/wdh1SvEn0E06I.gif

					cadenaAReemplazar=cadenas.split("\\|")[0];
					url=cadenas.split("\\|")[1];
					rtfContenido=rtfContenido.replace(cadenaAReemplazar, "");
					embedCode=new StringBuffer();
					
					embedCode.append(" <embed-giphy data-giphy=\""+url.split("\\,")[1]+"\"></embed-giphy> \n");
					
					embebedPost.setCadena_que_sera_reemplazada(cadenaAReemplazar);
					embebedPost.setRed_social("giphy");
					embebedPost.setCodigo_embebido(embedCode.toString());
					
					listRedSocialEmbedGiphy.add(embebedPost);
					contador ++;
				}while(contador <= num_post_embebidos);
				
				MapAReemplazar.put("giphy", listRedSocialEmbedGiphy);
			}
			
			if(!MapAReemplazar.isEmpty()){
				Iterator<String> iterator_red_social = MapAReemplazar.keySet().iterator();
				String red_social="", codigo_embebido="", cadena_que_sera_reemplazada="";
				while(iterator_red_social.hasNext()){
					red_social = iterator_red_social.next();
			        if(red_social.equalsIgnoreCase("twitter") || red_social.equalsIgnoreCase("facebook") || red_social.equalsIgnoreCase("instagram") 
			        		|| red_social.equalsIgnoreCase("giphy")){
			        	ArrayList<RedSocialEmbedPost> listEmbebidos=MapAReemplazar.get(red_social);
			        	for (RedSocialEmbedPost redSocialEmbedPost : listEmbebidos) {
				        	cadena_que_sera_reemplazada=redSocialEmbedPost.getCadena_que_sera_reemplazada();
				        	codigo_embebido=redSocialEmbedPost.getCodigo_embebido();
				        	RTFContenido=RTFContenido.replace(cadena_que_sera_reemplazada, codigo_embebido);
						}
			        	
			        }
			    } 
			}
			try {
				
				RTFContenido = RTFContenido.replace("href=","data-href=");
			} catch (Exception e) {
				LOG.error("Error al sustituir styles");
			}
			
			return RTFContenido;
		} catch (Exception e) {
			LOG.debug("Exception en getNoticiaJson: ",e);
			return RTFContenido;
		} 
	}
	
	
	
	/*
	 * */
	private static String devuelveCadenasPost(String id_red_social, String rtfContenido){
		String url="", cadenaAReemplazar="", salida="";
		try {
			cadenaAReemplazar=rtfContenido.substring(rtfContenido.indexOf("["+id_red_social+"="), rtfContenido.indexOf("="+id_red_social+"]"))+"="+id_red_social+"]";
			url=cadenaAReemplazar.replace("["+id_red_social+"=", "").replace("="+id_red_social+"]", "");
			salida=cadenaAReemplazar+"|"+url;
		} catch (Exception e) {
			LOG.error("Error devuelveCadenasPost: ",e);
			return "|";
		}
		return salida;
	}
	
	
	/*
	 *
	 * */
	private static String limpiaRedSocial(String inicioBusqueda, String finBusqueda, String id_red_social, String rtfContenido){
		try {
			LOG.debug("Inicia limpiaRedSocial "+id_red_social);
			String [] arrayBetween = StringUtils.substringsBetween(rtfContenido, inicioBusqueda, finBusqueda);			
			ArrayList<String> cadenasAReemplazarFirst=new ArrayList<String>();

			if(arrayBetween!=null)
			{
				for (String cadena : arrayBetween) {
					 if(cadena.contains("["+id_red_social)){
						 cadenasAReemplazarFirst.add(inicioBusqueda + cadena + finBusqueda + "|"+cadena);
					 };
				 }
				
				 for (String string : cadenasAReemplazarFirst) {
					 rtfContenido=rtfContenido.replace(string.split("\\|")[0], string.split("\\|")[1]);
				}
			}
		} catch (Exception e) {
			LOG.error("Error limpiaRedSocial: ",e);
			return rtfContenido;
		}
		return rtfContenido;
	}
	
	
public static String cambiaCaracteres(String texto) {
		
		texto = texto.replaceAll("á", "&#225;");
        texto = texto.replaceAll("é", "&#233;");
        texto = texto.replaceAll("í", "&#237;");
        texto = texto.replaceAll("ó", "&#243;");
        texto = texto.replaceAll("ú", "&#250;");  
        texto = texto.replaceAll("�?", "&#193;");
        texto = texto.replaceAll("É", "&#201;");
        texto = texto.replaceAll("�?", "&#205;");
        texto = texto.replaceAll("Ó", "&#211;");
        texto = texto.replaceAll("Ú", "&#218;");
        texto = texto.replaceAll("Ñ", "&#209;");
        texto = texto.replaceAll("ñ", "&#241;");        
        texto = texto.replaceAll("ª", "&#170;");          
        texto = texto.replaceAll("ä", "&#228;");
        texto = texto.replaceAll("ë", "&#235;");
        texto = texto.replaceAll("ï", "&#239;");
        texto = texto.replaceAll("ö", "&#246;");
        texto = texto.replaceAll("ü", "&#252;");    
        texto = texto.replaceAll("Ä", "&#196;");
        texto = texto.replaceAll("Ë", "&#203;");
        texto = texto.replaceAll("�?", "&#207;");
        texto = texto.replaceAll("Ö", "&#214;");
        texto = texto.replaceAll("Ü", "&#220;");
        texto = texto.replaceAll("¿", "&#191;");
        texto = texto.replaceAll("“", "&#8220;");        
        texto = texto.replaceAll("�?", "&#8221;");
        texto = texto.replaceAll("‘", "&#8216;");
        texto = texto.replaceAll("’", "&#8217;");
		texto = texto.replaceAll("…", "...");
		texto = texto.replaceAll("¡", "&#161;");
		texto = texto.replaceAll("¿", "&#191;");
		texto = texto.replaceAll("°", "&#176;");
        
        texto = texto.replaceAll("–", "&#8211;");
        texto = texto.replaceAll("—", "&#8212;");
        
		texto = texto.replaceAll("ç", "&#231;");
		texto = texto.replaceAll("Ç", "&#199;");
		return texto;
	}
	
	/**
	 * 
	 * */
	private void writeJson(String parRuta, String json)
	{		
		
		try {							
			Writer wt = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(parRuta),"UTF-8"));
			try {
				wt.write(json);
			} finally {
				wt.close();
			}						
		} catch (Exception e) {
			LOG.error("Exception en writeJson: ",e);
		}
	}



}//FIN CLASE
