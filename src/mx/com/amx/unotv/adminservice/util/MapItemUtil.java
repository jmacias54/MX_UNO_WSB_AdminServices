/**
 * 
 */
package mx.com.amx.unotv.adminservice.util;

import mx.com.amx.unotv.adminservice.model.aditional_information;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


import mx.com.amx.unotv.adminservice.model.Categoria;
import mx.com.amx.unotv.adminservice.model.Cordenadas;
import mx.com.amx.unotv.adminservice.model.HNota;
import mx.com.amx.unotv.adminservice.model.IHNotaUsuario;
import mx.com.amx.unotv.adminservice.model.main_image;
import mx.com.amx.unotv.adminservice.model.main_video;
import mx.com.amx.unotv.adminservice.model.NNota;
import mx.com.amx.unotv.adminservice.model.Tag;
import mx.com.amx.unotv.adminservice.model.response.Item;
import mx.com.amx.unotv.adminservice.util.exception.MapItemUtilException;
import mx.com.amx.unotv.adminservice.ws.CatalogsCallWS;
import mx.com.amx.unotv.adminservice.ws.IHNotaTagCallWS;
import mx.com.amx.unotv.adminservice.ws.IHNotaUsuarioCallWS;


/**
 * @author Jesus A. Macias Benitez
 *
 */


public class MapItemUtil {
	
	private static Logger logger = Logger.getLogger(MapItemUtil.class);
	
	@Autowired
	IHNotaTagCallWS iHNotaTagCallWS;
	@Autowired
	CatalogsCallWS catalogsCallWS;
	@Autowired
	IHNotaUsuarioCallWS iHNotaUsuarioCallWS;

	public NNota MapItemToNota(Item item) {
		NNota nota = new NNota();

		if (item == null) {
			return new NNota();
		}

		nota.setClRtfContenido((item.getContenido_nota()== null) ? "" : item.getContenido_nota());

		nota.setFcContentIdOoyala(
				(item.getMain_video().getContent_id() == null) ? "" : item.getMain_video().getContent_id());
		nota.setFcDescripcion((item.getDescription() == null) ? "" : item.getDescription());

		nota.setFcEscribio((item.getAditional_information().getAuthor() == null) ? ""
				: item.getAditional_information().getAuthor());

		nota.setFcFuente((item.getAditional_information().getSource() == null) ? ""
				: item.getAditional_information().getSource());
		nota.setFcIdCategoria((item.getCategory() == null) ? "" : item.getCategory());
		nota.setFcIdContenido((item.getId() == null) ? "" : item.getId());

		nota.setFcIdPcode((item.getMain_video().getP_code() == null) ? "" : item.getMain_video().getP_code());

		nota.setFcIdUsuario((item.getId_user() == null) ? "" : item.getId_user());
		nota.setFcImagen((item.getMain_image().getSrc() == null) ? "" : item.getMain_image().getSrc());

		nota.setFcLugar(
				(item.getAditional_information().getPlace() == null) ? "" : item.getAditional_information().getPlace());
		nota.setFcPlayerIdOoyala(
				(item.getMain_video().getPlayer_id() == null) ? "" : item.getMain_video().getPlayer_id());
		nota.setFcSourceOoyala("");
		nota.setFcTitulo((item.getTitle() == null) ? "" : item.getTitle());
		nota.setFcVideoYoutube(
				(item.getMain_video().getVideo_youtube_id() == null) ? "" : item.getMain_video().getVideo_youtube_id());
		nota.setFdFechaModificacion((item.getDate() == null) ? "" : item.getDate());
		nota.setFdFechaPublicacion((item.getDate() == null) ? "" : item.getDate());
		nota.setFcIdTipoNota((item.getType_nota() == null) ? "" : item.getType_nota());
		nota.setFcFriendlyUrl((item.getFriendly_url() == null) ? "" : item.getFriendly_url());

		nota.setFcPieImagen((item.getMain_image().getPie() == null) ? "" : item.getMain_image().getPie());
		nota.setFcFuenteImagen((item.getMain_image().getFuente() == null) ? "" : item.getMain_image().getFuente());
		nota.setFcCoordenadasFb( ((item.getMain_image().getCordenadas_facebook().getX() == null) ? "" : item.getMain_image().getCordenadas_facebook().getX() )+","+((item.getMain_image().getCordenadas_facebook().getY() == null) ? "" : item.getMain_image().getCordenadas_facebook().getY() ));
		nota.setFcCoordenadasMiniatura(((item.getMain_image().getCordenadas_miniatura().getX() == null) ? "" : item.getMain_image().getCordenadas_miniatura().getX() )+","+((item.getMain_image().getCordenadas_miniatura().getY() == null) ? "" : item.getMain_image().getCordenadas_miniatura().getY() ));
		
		nota.setFiBanInfinitoHome(0);
		nota.setFiBanMsn(0);
		nota.setFiBanOtros(0);
		nota.setFcKeywords("");

		nota.setFcIdEstatus("PUB");
		nota.setFcFileSizeOoyala("");

		nota.setFcDurationOoyala("");
		nota.setFcAlternativeTextOoyala("");
		nota.setClGaleria("");

		return nota;
	}

	public Item MapNotaToItem(NNota nota) {

		if (nota == null) {
			return new Item();
		}

		Item item = new Item();
		aditional_information aditionalInformation = new aditional_information();
		aditionalInformation.setAuthor((nota.getFcEscribio() == null) ? "" : nota.getFcEscribio());
		aditionalInformation.setPlace((nota.getFcLugar() == null) ? "" : nota.getFcLugar());
		aditionalInformation.setSource((nota.getFcFuente() == null) ? "" : nota.getFcFuente());

		main_image mainImage = new main_image();
		mainImage.setSrc((nota.getFcImagen() == null) ? "" : nota.getFcImagen());
		mainImage.setPie((nota.getFcPieImagen() == null) ? "" : nota.getFcPieImagen());
		mainImage.setFuente((nota.getFcFuenteImagen()== null) ? "" : nota.getFcFuenteImagen()); // fcFuente

		if (nota.getFcCoordenadasFb() != null && !nota.getFcCoordenadasFb().equals("")) {
			Cordenadas cordenadas_facebook = new Cordenadas();
			String[] fb = nota.getFcCoordenadasFb().split(",");
			cordenadas_facebook.setX(Integer.parseInt(fb[0]));
			cordenadas_facebook.setY(Integer.parseInt(fb[1]));
			mainImage.setCordenadas_facebook(cordenadas_facebook);
		}

		if (nota.getFcCoordenadasMiniatura() != null && !nota.getFcCoordenadasMiniatura().equals("")) {

			Cordenadas cordenadas_miniatura = new Cordenadas();
			String[] min = nota.getFcCoordenadasMiniatura().split(",");
			cordenadas_miniatura.setX(Integer.parseInt(min[0]));
			cordenadas_miniatura.setY(Integer.parseInt(min[1]));

			mainImage.setCordenadas_miniatura(cordenadas_miniatura);

		}
		main_video mainVideo = new main_video();
		mainVideo.setContent_id((nota.getFcContentIdOoyala() == null) ? "" : nota.getFcContentIdOoyala());
		mainVideo.setP_code((nota.getFcIdPcode() == null) ? "" : nota.getFcIdPcode());
		mainVideo.setPlayer_id((nota.getFcPlayerIdOoyala() == null) ? "" : nota.getFcPlayerIdOoyala());
		mainVideo.setVideo_youtube_id((nota.getFcVideoYoutube() == null) ? "" : nota.getFcVideoYoutube());

		if (nota.getFcVideoYoutube() != null && !nota.getFcVideoYoutube().equals("")
				&& (nota.getFcPlayerIdOoyala() == null || nota.getFcPlayerIdOoyala().equals(""))) {
			mainVideo.setType("youtube");
		} else if (nota.getFcPlayerIdOoyala() != null && !nota.getFcPlayerIdOoyala().equals("")) {
			mainVideo.setType("ooyala");
		} else {
			mainVideo.setType("");
		}

		item.setAditional_information(aditionalInformation);
		item.setMain_image(mainImage);
		item.setMain_video(mainVideo);
		item.setCategory((nota.getFcIdCategoria() == null) ? "" : nota.getFcIdCategoria());
		item.setType_nota((nota.getFcIdTipoNota() == null) ? "" : nota.getFcIdTipoNota());
		item.setFriendly_url((nota.getFcFriendlyUrl() == null) ? "" : nota.getFcFriendlyUrl());

		return item;
	}

	public Item MapNotaToItem(HNota nota) throws  MapItemUtilException{

		if (nota == null) {
			return new Item();
		}

		Item item = new Item();
		aditional_information aditionalInformation = new aditional_information();
		aditionalInformation.setAuthor((nota.getFcEscribio() == null) ? "" : nota.getFcEscribio());
		aditionalInformation.setPlace((nota.getFcLugar() == null) ? "" : nota.getFcLugar());
		aditionalInformation.setSource((nota.getFcFuente() == null) ? "" : nota.getFcFuente());

		main_image mainImage = new main_image();
		mainImage.setSrc((nota.getFcImagen() == null) ? "" : nota.getFcImagen());
		mainImage.setPie((nota.getFcPieImagen() == null) ? "" : nota.getFcPieImagen());
		mainImage.setFuente((nota.getFcFuenteImagen() == null) ? "" : nota.getFcFuenteImagen()); // fcFuente

		if (nota.getFcCoordenadasFb() != null && !nota.getFcCoordenadasFb().equals("")) {
			Cordenadas cordenadas_facebook = new Cordenadas();
			String[] fb = nota.getFcCoordenadasFb().split(",");
			cordenadas_facebook.setX(Integer.parseInt(fb[0]));
			cordenadas_facebook.setY(Integer.parseInt(fb[1]));
			mainImage.setCordenadas_facebook(cordenadas_facebook);
		}

		if (nota.getFcCoordenadasMiniatura() != null && !nota.getFcCoordenadasMiniatura().equals("")) {

			Cordenadas cordenadas_miniatura = new Cordenadas();
			String[] min = nota.getFcCoordenadasMiniatura().split(",");
			cordenadas_miniatura.setX(Integer.parseInt(min[0]));
			cordenadas_miniatura.setY(Integer.parseInt(min[1]));

			mainImage.setCordenadas_miniatura(cordenadas_miniatura);

		}

		main_video mainVideo = new main_video();
		mainVideo.setContent_id((nota.getFcContentIdOoyala() == null) ? "" : nota.getFcContentIdOoyala());
		mainVideo.setP_code((nota.getFcIdPcode() == null) ? "" : nota.getFcIdPcode());
		mainVideo.setPlayer_id((nota.getFcPlayerIdOoyala() == null) ? "" : nota.getFcPlayerIdOoyala());
		mainVideo.setVideo_youtube_id((nota.getFcVideoYoutube() == null) ? "" : nota.getFcVideoYoutube());

		if (nota.getFcVideoYoutube() != null && !nota.getFcVideoYoutube().equals("")
				&& (nota.getFcPlayerIdOoyala() == null || nota.getFcPlayerIdOoyala().equals(""))) {
			mainVideo.setType("youtube");
		} else if (nota.getFcPlayerIdOoyala() != null && !nota.getFcPlayerIdOoyala().equals("")) {
			mainVideo.setType("ooyala");
		} else {
			mainVideo.setType("");
		}

		item.setAditional_information(aditionalInformation);
		item.setMain_image(mainImage);
		item.setMain_video(mainVideo);

		item.setCategory((nota.getFcIdCategoria() == null) ? "" : nota.getFcIdCategoria());
		item.setContenido_nota((nota.getClRtfContenido() == null) ? "" : nota.getClRtfContenido());
		item.setId((nota.getFcIdContenido() == null) ? "" : nota.getFcIdContenido());
		item.setDescription((nota.getFcDescripcion() == null) ? "" : nota.getFcDescripcion());
		item.setImage((nota.getFcImagen() == null) ? "" : nota.getFcImagen());
		item.setDate((nota.getFdFechaModificacion() == null) ? "" : nota.getFdFechaModificacion());
	
		item.setFriendly_url((nota.getFcFriendlyUrl() == null) ? "" : nota.getFcFriendlyUrl());
		item.setType_nota((nota.getFcIdTipoNota() == null) ? "" : nota.getFcIdTipoNota());
		item.setTitle((nota.getFcTitulo() == null) ? "" : nota.getFcTitulo());

		Categoria categoria = null;
		IHNotaUsuario usuario = null;
		String idSeccion = "";
		String idUsuario = "";
		List<Tag> listaTags = null;
		String tags = "";
		
		
		try {
			
			usuario = iHNotaUsuarioCallWS.findByIdContenido(nota.getFcIdContenido());
			idUsuario = usuario.getFcIdUsuario();
    		listaTags = iHNotaTagCallWS.getByIdContenido(nota.getFcIdContenido());

			if (listaTags != null && !listaTags.isEmpty()) {
				for (Tag tag : listaTags) {

					tags = tags + tag.getFcIdTag() + ",";
				}

			}
			
			categoria = catalogsCallWS.getCategorieById(nota.getFcIdCategoria());
			idSeccion = categoria.getFcIdSeccion();
		}catch (Exception e) {
			logger.error(" --- Exception MapItemUtil  [ MapNotaToItem  ]: ", e);
			new MapItemUtilException(e.getMessage());
		}	
		

		item.setSection(idSeccion);
		item.setTags(tags);
        item.setId_user(idUsuario);
		item.setPath_nota("");
		
		

		return item;
	}

}
