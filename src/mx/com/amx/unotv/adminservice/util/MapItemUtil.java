/**
 * 
 */
package mx.com.amx.unotv.adminservice.util;

import mx.com.amx.unotv.adminservice.model.aditional_information;
import mx.com.amx.unotv.adminservice.model.Cordenadas;
import mx.com.amx.unotv.adminservice.model.HNota;
import mx.com.amx.unotv.adminservice.model.main_image;
import mx.com.amx.unotv.adminservice.model.main_video;
import mx.com.amx.unotv.adminservice.model.NNota;
import mx.com.amx.unotv.adminservice.model.response.Item;

/**
 * @author Jesus A. Macias Benitez
 *
 */
public class MapItemUtil {
	
	public NNota MapItemToNota (Item item) {
		NNota nota = new NNota();
		
		if(item == null) {
			return new NNota();
		}
	
		nota.setClRtfContenido((item.getCategory() ==  null ) ? "" : item.getCategory());

		nota.setFcContentIdOoyala((item.getMain_video().getContent_id() ==  null ) ? "" :item.getMain_video().getContent_id());
		nota.setFcDescripcion((item.getDescription() ==  null ) ? "" :  item.getDescription());
		
		nota.setFcEscribio((item.getAditional_information().getAuthor() ==  null ) ? "" :  item.getAditional_information().getAuthor());
	
		nota.setFcFuente((item.getAditional_information().getSource() ==  null ) ? "" : item.getAditional_information().getSource() );
		nota.setFcIdCategoria((item.getCategory()==  null ) ? "" : item.getCategory());
		nota.setFcIdContenido((item.getId()==  null ) ? "" : item.getId());
		
		nota.setFcIdPcode((item.getMain_video().getP_code()==  null ) ? "" : item.getMain_video().getP_code());
		nota.setFcIdSeccion((item.getSection()==  null ) ? "" :  item.getSection());
		
		nota.setFcIdUsuario((item.getId_user()==  null ) ? "" :  item.getId_user());
		nota.setFcImagen((item.getMain_image().getSrc()==  null ) ? "" :  item.getMain_image().getSrc());

		nota.setFcLugar((item.getAditional_information().getPlace()==  null ) ? "" :  item.getAditional_information().getPlace());
		nota.setFcPlayerIdOoyala((item.getMain_video().getPlayer_id()==  null ) ? "" :  item.getMain_video().getPlayer_id());
		nota.setFcSourceOoyala("");
		nota.setFcTitulo((item.getTitle()==  null ) ? "" :  item.getTitle());
		nota.setFcVideoYoutube((item.getMain_video().getVideo_youtube_id()==  null ) ? "" :  item.getMain_video().getVideo_youtube_id());
		nota.setFdFechaModificacion((item.getDate()==  null ) ? "" :  item.getDate());
		nota.setFdFechaPublicacion((item.getDate()==  null ) ? "" :item.getDate());
		nota.setFcIdTipoNota((item.getType_nota()==  null ) ? "" :item.getType_nota());
		nota.setFcFriendlyUrl((item.getFriendly_url()==  null ) ? "" :item.getFriendly_url());
		
		
		nota.setFiBanInfinitoHome(0);
		nota.setFiBanMsn(0);
		nota.setFiBanOtros(0);
		nota.setFcKeywords("");
		
		nota.setFcIdEstatus("PUB");
		nota.setFcFileSizeOoyala("");
		
		nota.setFcDurationOoyala("");
		nota.setFcAlternativeTextOoyala("");
		nota.setClGaleria("");
		
		
		return nota ;
	}
	
	public Item MapNotaToItem ( NNota nota) {
		
		if(nota == null) {
			return new Item();
		}
		
		Item item = new Item();
		aditional_information aditionalInformation = new aditional_information();
		aditionalInformation.setAuthor((nota.getFcEscribio()==  null ) ? "" : nota.getFcEscribio());
		aditionalInformation.setPlace((nota.getFcLugar()==  null ) ? "" :  nota.getFcLugar());
		aditionalInformation.setSource((nota.getFcFuente()==  null ) ? "" :  nota.getFcFuente());
		
		Cordenadas cordenadasFacebook = new Cordenadas(0,0);
		Cordenadas cordenadasMiniatura = new Cordenadas(0,0);
		
		main_image mainImage = new main_image("","","",cordenadasFacebook,cordenadasMiniatura);
		mainImage.setSrc((nota.getFcImagen()==  null ) ? "" : nota.getFcImagen());
		
		main_video mainVideo = new main_video();
		mainVideo.setContent_id((nota.getFcContentIdOoyala()==  null ) ? "" :  nota.getFcContentIdOoyala());
		mainVideo.setP_code((nota.getFcIdPcode()==  null ) ? "" : nota.getFcIdPcode());
		mainVideo.setPlayer_id((nota.getFcPlayerIdOoyala()==  null ) ? "" : nota.getFcPlayerIdOoyala());
		mainVideo.setVideo_youtube_id((nota.getFcVideoYoutube()==  null ) ? "" :  nota.getFcVideoYoutube());
		
		if(nota.getFcVideoYoutube() != null && !nota.getFcVideoYoutube().equals("") && (nota.getFcPlayerIdOoyala() == null || nota.getFcPlayerIdOoyala().equals(""))) {
			mainVideo.setType("youtube");
		}else if (nota.getFcPlayerIdOoyala() != null && !nota.getFcPlayerIdOoyala().equals("")) {
			mainVideo.setType("ooyala");
		}else {
			mainVideo.setType("");
		}
		
		item.setAditional_information(aditionalInformation);
		item.setMain_image(mainImage);
		item.setMain_video(mainVideo);
		item.setCategory((nota.getFcIdCategoria()==  null ) ? "" :  nota.getFcIdCategoria() );
		item.setType_nota((nota.getFcIdTipoNota()==  null ) ? "" :   nota.getFcIdTipoNota());
		item.setFriendly_url((nota.getFcFriendlyUrl()==  null ) ? "" :   nota.getFcFriendlyUrl());
		
		
		return item ;
	}
	
	public Item MapNotaToItem ( HNota nota) {
		
		if(nota == null) {
			return new Item();
		}
		
		Item item = new Item();
		aditional_information aditionalInformation = new aditional_information();
		aditionalInformation.setAuthor((nota.getFcEscribio()==  null ) ? "" : nota.getFcEscribio());
		aditionalInformation.setPlace((nota.getFcLugar()==  null ) ? "" :  nota.getFcLugar());
		aditionalInformation.setSource((nota.getFcFuente()==  null ) ? "" :  nota.getFcFuente());
		
		Cordenadas cordenadasFacebook = new Cordenadas(0,0);
		Cordenadas cordenadasMiniatura = new Cordenadas(0,0);
		
		main_image mainImage = new main_image("","","",cordenadasFacebook,cordenadasMiniatura);
		mainImage.setSrc((nota.getFcImagen()==  null ) ? "" : nota.getFcImagen());
		
		main_video mainVideo = new main_video();
		mainVideo.setContent_id((nota.getFcContentIdOoyala()==  null ) ? "" :  nota.getFcContentIdOoyala());
		mainVideo.setP_code((nota.getFcIdPcode()==  null ) ? "" : nota.getFcIdPcode());
		mainVideo.setPlayer_id((nota.getFcPlayerIdOoyala()==  null ) ? "" : nota.getFcPlayerIdOoyala());
		mainVideo.setVideo_youtube_id((nota.getFcVideoYoutube()==  null ) ? "" :  nota.getFcVideoYoutube());
		
		if(nota.getFcVideoYoutube() != null && !nota.getFcVideoYoutube().equals("") && (nota.getFcPlayerIdOoyala() == null || nota.getFcPlayerIdOoyala().equals(""))) {
			mainVideo.setType("youtube");
		}else if (nota.getFcPlayerIdOoyala() != null && !nota.getFcPlayerIdOoyala().equals("")) {
			mainVideo.setType("ooyala");
		}else {
			mainVideo.setType("");
		}
		
		item.setAditional_information(aditionalInformation);
		item.setMain_image(mainImage);
		item.setMain_video(mainVideo);
	
		item.setCategory((nota.getFcIdCategoria()==  null ) ? "" :  nota.getFcIdCategoria() );
		item.setContenido_nota((nota.getClRtfContenido()==  null ) ? "" :  nota.getClRtfContenido());
		item.setId((nota.getFcIdContenido()==  null ) ? "" :  nota.getFcIdContenido());
		item.setDescription((nota.getFcDescripcion()==  null ) ? "" : nota.getFcDescripcion());
		item.setImage((nota.getFcImagen()==  null ) ? "" :  nota.getFcImagen());
		item.setDate((nota.getFdFechaModificacion()==  null ) ? "" :   nota.getFdFechaModificacion());
		item.setId_user("");
		item.setPath_nota("");
		item.setSection("");
		item.setTags("");
		item.setFriendly_url((nota.getFcFriendlyUrl()==  null ) ? "" :   nota.getFcFriendlyUrl());
		item.setType_nota((nota.getFcIdTipoNota()==  null ) ? "" :   nota.getFcIdTipoNota());
		item.setTitle((nota.getFcTitulo()==  null ) ? "" :   nota.getFcTitulo());
		
		
		return item ;
	}

}
