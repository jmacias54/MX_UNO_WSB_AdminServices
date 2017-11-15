/**
 * 
 */
package mx.com.amx.unotv.adminservice.ws;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import mx.com.amx.unotv.adminservice.dto.ContentDTO;
import mx.com.amx.unotv.adminservice.dto.PushAmpDTO;
import mx.com.amx.unotv.adminservice.dto.RespuestaWSAmpDTO;
import mx.com.amx.unotv.adminservice.model.ParametrosDTO;
import mx.com.amx.unotv.adminservice.ws.exception.FacebookCallWSException;



/**
 * @author Jesus A. Macias Benitez
 *
 */
public class FacebookCallWS {

	
	//LOG
		private final Logger LOG = Logger.getLogger(this.getClass().getName());
		
		private RestTemplate restTemplate;
		private HttpHeaders headers = new HttpHeaders();
		
		public FacebookCallWS() 
		{
			super();
			restTemplate = new RestTemplate();
			ClientHttpRequestFactory factory = restTemplate.getRequestFactory();

		        if ( factory instanceof SimpleClientHttpRequestFactory)
		        {
		            ((SimpleClientHttpRequestFactory) factory).setConnectTimeout( 50 * 1000 );
		            ((SimpleClientHttpRequestFactory) factory).setReadTimeout( 50 * 1000 );
		        }
		        else if ( factory instanceof HttpComponentsClientHttpRequestFactory)
		        {
		            ((HttpComponentsClientHttpRequestFactory) factory).setReadTimeout( 50 * 1000);
		            ((HttpComponentsClientHttpRequestFactory) factory).setConnectTimeout( 50 * 1000);
		            
		        }
		        restTemplate.setRequestFactory( factory );
		        headers.setContentType(MediaType.APPLICATION_JSON);	        		
		}
		
		
		
		/**
		 * Metodo que inserta o actualiza el Instant Article
		 * @param contentDTO
		 * @param ParametrosDTO
		 * @return String
		 * @throws LlamadasWSBOException
		 * */
		public String insertUpdateArticleFB (ContentDTO contentDTO, ParametrosDTO parametrosDTO) throws FacebookCallWSException
		{
			LOG.debug("Inicia insertUpdateArticleFB en LlamadasWSBOTest");
			try {
				String URL_WS=parametrosDTO.getUrl_dominio_app()+parametrosDTO.getUrl_wsb_FB()+parametrosDTO.getMet_wsb_FB_insertUpdateArticle2();
				LOG.debug("LLamado a "+URL_WS);
				HttpEntity<ContentDTO> entity = new HttpEntity<ContentDTO>( contentDTO );
				return restTemplate.postForObject(URL_WS, contentDTO, String.class);
			}catch(RestClientResponseException rre){
				LOG.error("RestClientResponseException insertUpdateArticleFB [FacebookCallWS]: " + rre.getResponseBodyAsString());
				LOG.error("RestClientResponseException insertUpdateArticleFB [FacebookCallWS]: ", rre);
				throw new FacebookCallWSException(rre.getMessage());			
			} catch(Exception e) {
				LOG.error("Error insertUpdateArticle - FB [FacebookCallWS]: ",e);
				throw new FacebookCallWSException(e.getMessage());
			}
		}
		
		
		/**
		 * Metodo que borra un Instant Article
		 * @param articleId
		 * @param ParametrosDTO
		 * @return String
		 * @throws LlamadasWSBOException
		 * */
		public String deleteArticleFB (String articleId, ParametrosDTO parametrosDTO) throws FacebookCallWSException
		{
			LOG.debug("Inicia deleteArticleFB en LlamadasWSBOTest");
			LOG.debug("articleId: "+articleId);

			try {
				String URL_WS=parametrosDTO.getUrl_dominio_app()+parametrosDTO.getUrl_wsb_FB()+parametrosDTO.getMet_wsb_FB_deleteArticle();
				LOG.debug("LLamado a "+URL_WS);
				HttpEntity<String> entity = new HttpEntity<String>( articleId );
				return restTemplate.postForObject(URL_WS, entity, String.class);
			}catch(RestClientResponseException rre){
				LOG.error("RestClientResponseException deleteArticleFB [FacebookCallWS]: " + rre.getResponseBodyAsString());
				LOG.error("RestClientResponseException deleteArticleFB [FacebookCallWS]: ", rre);
				throw new FacebookCallWSException(rre.getResponseBodyAsString());
			} catch(Exception e) {
				LOG.error("Exception deleteArticle - FB [WS BO]: ",e);
				throw new FacebookCallWSException(e.getMessage());
			}
		}
		
		
		
		/**
		 * Llama el api para AMP
		 * @param pushAmpDTO
		 * @param ParametrosDTO
		 * @return RespuestaWSAmpDTO
		 * @throws LlamadasWSBOException
		 * */
		public RespuestaWSAmpDTO sendPushAMP(PushAmpDTO pushAmpDTO, ParametrosDTO parametrosDTO) throws FacebookCallWSException 
		{
			LOG.debug("Inicia sendPushAMP en LlamadasWSBOTest");
			LOG.debug("pushAmpDTO: "+pushAmpDTO);					
			try {		
				String URL_WS=parametrosDTO.getUrl_dominio_app()+parametrosDTO.getUrl_wsb_BackOffice()+parametrosDTO.getMet_wsb_BackOffice_sendPushAMP();
				LOG.debug("LLamado a "+URL_WS);
				HttpEntity<PushAmpDTO> entity = new HttpEntity<PushAmpDTO>( pushAmpDTO );
				return restTemplate.postForObject(URL_WS, entity, RespuestaWSAmpDTO.class);	
			}catch(RestClientResponseException rre){
				LOG.error("RestClientResponseException sendPushAMP [FacebookCallWS]: " + rre.getResponseBodyAsString());
				LOG.error("RestClientResponseException sendPushAMP [FacebookCallWS]: ", rre);
				throw new FacebookCallWSException(rre.getResponseBodyAsString());
			} catch(Exception e) {
				LOG.error("Exception sendPushAMP [FacebookCallWS]: ",e);
				throw new FacebookCallWSException(e.getMessage());
			}			
		}
		
}
