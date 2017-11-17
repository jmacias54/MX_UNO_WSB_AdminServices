/**
 * 
 */
package mx.com.amx.unotv.adminservice.ws;

import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import mx.com.amx.unotv.adminservice.model.INotaTag;
import mx.com.amx.unotv.adminservice.model.Tag;
import mx.com.amx.unotv.adminservice.model.response.ListTag;
import mx.com.amx.unotv.adminservice.ws.exception.INotaTagCallWSException;

/**
 * @author Jesus A. Macias Benitez
 *
 */
public class INotaTagCallWS {
	
	private static Logger logger = Logger.getLogger(INotaTagCallWS.class);

	private RestTemplate restTemplate;
	private String URL_WS_BASE = "";
	private String URL_WS_I_NOTA_TAG = "";
	private HttpHeaders headers = new HttpHeaders();
	private final Properties props = new Properties();

	public INotaTagCallWS() {
		super();
		restTemplate = new RestTemplate();
		ClientHttpRequestFactory factory = restTemplate.getRequestFactory();

		if (factory instanceof SimpleClientHttpRequestFactory) {
			((SimpleClientHttpRequestFactory) factory).setConnectTimeout(15 * 1000);
			((SimpleClientHttpRequestFactory) factory).setReadTimeout(15 * 1000);
			System.out.println("Inicializando rest template 1");
		} else if (factory instanceof HttpComponentsClientHttpRequestFactory) {
			((HttpComponentsClientHttpRequestFactory) factory).setReadTimeout(15 * 1000);
			((HttpComponentsClientHttpRequestFactory) factory).setConnectTimeout(15 * 1000);
			System.out.println("Inicializando rest template 2");
		}

		restTemplate.setRequestFactory(factory);
		headers.setContentType(MediaType.APPLICATION_JSON);

		try {
			props.load(this.getClass().getResourceAsStream("/general.properties"));
		} catch (Exception e) {
			logger.error("[ INotaTagCallWS ::init]Error al iniciar y cargar arhivo de propiedades." + e.getMessage());

		}
		String ambiente = props.getProperty("ambiente");
		URL_WS_BASE = props.getProperty(ambiente + ".url.ws.base");
		
		URL_WS_I_NOTA_TAG  = props.getProperty(ambiente + ".url.ws.i.nota.tag");
	}
	
	
	public int insert(INotaTag tag)  {

		int res = 0;
		String metodo = "/insert";
		String URL_WS = URL_WS_BASE + URL_WS_I_NOTA_TAG + metodo;

		logger.info("--- insert --- [ INotaTagCallWS ] --- ");
		logger.info("--- URL : " + URL_WS);

		try {
			

			res = restTemplate.postForObject(URL_WS, tag, Integer.class);

			logger.info(" Registros obtenidos --> " + res);

		} catch (RestClientResponseException rre) {
			logger.error("RestClientResponseException insert [ INotaTagCallWS ]: " + rre.getResponseBodyAsString());
			logger.error("RestClientResponseException insert[ INotaTagCallWS ]: ", rre);
			 new INotaTagCallWSException(rre.getResponseBodyAsString());
		} catch (Exception e) {
			logger.error("Exception insert  [ INotaTagCallWS ]: ", e);
			 new INotaTagCallWSException(e.getMessage());
		}

		return res;

	}
	
	public int delete(String idContenido)  {

		int res = 0;
		String metodo = "/delete";
		String URL_WS = URL_WS_BASE + URL_WS_I_NOTA_TAG + metodo+"/"+idContenido;

		logger.info("--- delete --- [ INotaTagCallWS ] --- ");
		logger.info("--- URL : " + URL_WS);

		try {
			
			HttpEntity<String> entity = new HttpEntity<String>("Accept=application/json; charset=utf-8", headers);
			res = restTemplate.postForObject(URL_WS , entity, Integer.class);

			logger.info(" Registros obtenidos --> " + res);

		} catch (RestClientResponseException rre) {
			logger.error("RestClientResponseException delete [ INotaTagCallWS ]: " + rre.getResponseBodyAsString());
			logger.error("RestClientResponseException delete[ INotaTagCallWS ]: ", rre);
			 new INotaTagCallWSException(rre.getResponseBodyAsString());
		} catch (Exception e) {
			logger.error("Exception delete  [ INotaTagCallWS ]: ", e);
			 new INotaTagCallWSException(e.getMessage());
		}

		return res;

	}
	
	public List<Tag> getByIdContenido(String idContenido) throws INotaTagCallWSException {

		int res = 0;
		ListTag response = null;
		
		String URL_WS = URL_WS_BASE + URL_WS_I_NOTA_TAG +"/"+idContenido;

		logger.info("--- getByIdContenido --- [ INotaTagCallWS ] --- ");
		logger.info("--- URL : " + URL_WS);

		try {
			
			HttpEntity<String> entity = new HttpEntity<String>("Accept=application/json; charset=utf-8", headers);
			response = restTemplate.postForObject(URL_WS , entity, ListTag.class);

			logger.info(" Registros obtenidos --> " + res);

		} catch (RestClientResponseException rre) {
			logger.error("RestClientResponseException getByIdContenido [ INotaTagCallWS ]: " + rre.getResponseBodyAsString());
			logger.error("RestClientResponseException getByIdContenido[ INotaTagCallWS ]: ", rre);
			throw new INotaTagCallWSException(rre.getResponseBodyAsString());
		} catch (Exception e) {
			logger.error("Exception getByIdContenido  [ INotaTagCallWS ]: ", e);
			throw new INotaTagCallWSException(e.getMessage());
		}

		return response.getLista();

	}

}
