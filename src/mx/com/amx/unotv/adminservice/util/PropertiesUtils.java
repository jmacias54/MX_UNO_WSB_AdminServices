package mx.com.amx.unotv.adminservice.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import mx.com.amx.unotv.adminservice.dto.ParametrosDTO;


public class PropertiesUtils {

	// LOG
	private static Logger logger = Logger.getLogger(PropertiesUtils.class);

	/**
	 * Metodo encargado de generar el DTO con la informacion del properties.
	 * 
	 * @return ParametrosDTO
	 * @throws ProcesoWorkflowException
	 */
	public  ParametrosDTO obtenerPropiedades() throws Exception 
	{	
		logger.debug("Inicia obtenerPropiedades en PropertiesUtils");
		ParametrosDTO parametrosDTO = new ParametrosDTO();		 
		try {	    		
			//Properties war
			Properties propsWAR = new Properties();
			propsWAR.load(this.getClass().getResourceAsStream( "/general.properties" ));
			String ambiente = propsWAR.getProperty("ambiente");
			String rutaProperties = propsWAR.getProperty("ambiente.resources.properties".replace("ambiente", ambiente));
			
			//Properties server
			Properties propsServer = new Properties();		
			propsServer.load(new FileInputStream(new File(rutaProperties)));
			
			
			parametrosDTO.setDominio(propsServer.getProperty("dominio"));
			parametrosDTO.setPathFiles(propsServer.getProperty("pathFiles"));
			
			parametrosDTO.setTemplateHtml(propsServer.getProperty("templateHtml"));
			//urlCropImage
			parametrosDTO.setUrlCropImage(propsServer.getProperty("url_crop_image"));
			
			
			
			parametrosDTO.setURL_WEBSERVER_AMP(propsServer.getProperty("URL_WEBSERVER_AMP"));
			parametrosDTO.setURL_WEBSERVER_CSS_AMP(propsServer.getProperty("URL_WEBSERVER_CSS_AMP"));
			parametrosDTO.setURL_WS_FB(propsServer.getProperty("URL_WS_FB"));
			
			parametrosDTO.setCarpetaResources(propsServer.getProperty("carpetaResources"));

			parametrosDTO.setBasePaginaPlantilla(propsServer.getProperty("basePaginaPlantilla"));			
			
			parametrosDTO.setNameHTML(propsServer.getProperty("nameHTML"));
			parametrosDTO.setBaseTheme(propsServer.getProperty("baseTheme"));		
			parametrosDTO.setBaseURL(propsServer.getProperty("baseURL"));
			
			parametrosDTO.setBasePagesPortal(propsServer.getProperty("basePagesPortal"));	
			parametrosDTO.setPathDetalle(propsServer.getProperty("pathDetalle"));
			parametrosDTO.setDominio(propsServer.getProperty("dominio"));
			parametrosDTO.setAmbiente(propsServer.getProperty("ambiente"));
			parametrosDTO.setMetaVideo(propsServer.getProperty("metaVideo"));
			parametrosDTO.setMetaVideoSecureUrl(propsServer.getProperty("metaVideoSecureUrl"));
			parametrosDTO.setPathFilesTest(propsServer.getProperty("pathFilesTest"));
			parametrosDTO.setBaseURLTest(propsServer.getProperty("baseURLTest"));
			
			parametrosDTO.setCatalogoParametros(propsServer.getProperty("catalogoParametros"));			
			
			//
			parametrosDTO.setUrl_dominio_app(propsServer.getProperty("url_dominio_app"));
			parametrosDTO.setUrl_dominio_dat(propsServer.getProperty("url_dominio_dat"));
			
			parametrosDTO.setUrl_wsd_WorkFlow(propsServer.getProperty("url_wsd_WorkFlow"));
			parametrosDTO.setUrl_wsb_WorkFlowUtils(propsServer.getProperty("url_wsb_WorkFlowUtils"));
			parametrosDTO.setUrl_wsb_Utils(propsServer.getProperty("url_wsb_Utils"));
			parametrosDTO.setUrl_wsb_BackOffice(propsServer.getProperty("url_wsb_BackOffice"));
			parametrosDTO.setUrl_wsb_FB(propsServer.getProperty("url_wsb_FB"));
			
			parametrosDTO.setMet_wsb_FB_insertUpdateArticle2(propsServer.getProperty("met_wsb_FB_insertUpdateArticle2"));
			parametrosDTO.setMet_wsb_FB_deleteArticle(propsServer.getProperty("met_wsb_FB_deleteArticle"));
			parametrosDTO.setMet_wsb_BackOffice_sendPushAMP(propsServer.getProperty("met_wsb_BackOffice_sendPushAMP"));
			parametrosDTO.setMet_wsb_WorkFlowUtils_getInfoVideo(propsServer.getProperty("met_wsb_WorkFlowUtils_getInfoVideo"));
			parametrosDTO.setMet_wsd_WorkFlow_insertNotaBD(propsServer.getProperty("met_wsd_WorkFlow_insertNotaBD"));
			parametrosDTO.setMet_wsd_WorkFlow_insertNotaHistoricoBD(propsServer.getProperty("met_wsd_WorkFlow_insertNotaHistoricoBD"));
			parametrosDTO.setMet_wsd_WorkFlow_updateNotaBD(propsServer.getProperty("met_wsd_WorkFlow_updateNotaBD"));
			parametrosDTO.setMet_wsd_WorkFlow_updateNotaHistoricoBD(propsServer.getProperty("met_wsd_WorkFlow_updateNotaHistoricoBD"));
			parametrosDTO.setMet_wsd_WorkFlow_existeNotaRegistrada(propsServer.getProperty("met_wsd_WorkFlow_existeNotaRegistrada"));
			parametrosDTO.setMet_wsd_WorkFlow_getIdNotaByName(propsServer.getProperty("met_wsd_WorkFlow_getIdNotaByName"));
			parametrosDTO.setMet_wsd_WorkFlow_insertNotaTag(propsServer.getProperty("met_wsd_WorkFlow_insertNotaTag"));
			parametrosDTO.setMet_wsd_WorkFlow_deleteNotaTag(propsServer.getProperty("met_wsd_WorkFlow_deleteNotaTag"));
			parametrosDTO.setMet_wsd_WorkFlow_deleteNotaBD(propsServer.getProperty("met_wsd_WorkFlow_deleteNotaBD"));
			parametrosDTO.setMet_wsd_WorkFlow_deleteNotaHistoricoBD(propsServer.getProperty("met_wsd_WorkFlow_deleteNotaHistoricoBD"));
			parametrosDTO.setMet_wsd_WorkFlow_getNotasMagazine(propsServer.getProperty("met_wsd_WorkFlow_getNotasMagazine"));
			parametrosDTO.setMet_wsd_WorkFlow_getRelacionadasbyIdCategoria(propsServer.getProperty("met_wsd_WorkFlow_getRelacionadasbyIdCategoria"));
			parametrosDTO.setMet_wsd_WorkFlow_getExtraInfoContent(propsServer.getProperty("met_wsd_WorkFlow_getExtraInfoContent"));			
			parametrosDTO.setMet_wsb_Utils_getParameter(propsServer.getProperty("met_wsb_Utils_getParameter"));						
			
			parametrosDTO.setCorreo_error_para(propsServer.getProperty("correo_error_para"));
			parametrosDTO.setCorreo_error_asunto(propsServer.getProperty("correo_error_asunto"));			
			parametrosDTO.setCorreo_error_cuerpo(propsServer.getProperty("correo_error_cuerpo"));
			parametrosDTO.setCorreo_error_smtpsender(propsServer.getProperty("correo_error_smtpsender"));
						
		} catch (Exception ex) {
			parametrosDTO = new ParametrosDTO();
			logger.error("No se encontro el Archivo de propiedades: ", ex);			
			throw new Exception(ex.getMessage());
		}
		return parametrosDTO;
    }

}// FIN CLASE
