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

import mx.com.amx.unotv.adminservice.model.HNota;
import mx.com.amx.unotv.adminservice.model.NNota;
import mx.com.amx.unotv.adminservice.model.response.HNotaWSResponse;
import mx.com.amx.unotv.adminservice.ws.exception.DetailCallWSException;

/**
 * @author Jesus A. Macias Benitez
 *
 */
public class DetailCallWS {

	private static Logger logger = Logger.getLogger(DetailCallWS.class);

	private RestTemplate restTemplate;
	private String URL_WS_BASE = "";
	private String URL_WS_DETAIL = "";
	private HttpHeaders headers = new HttpHeaders();
	private final Properties props = new Properties();

	public DetailCallWS() {
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
			logger.error("[DetailCallWS::init]Error al iniciar y cargar arhivo de propiedades." + e.getMessage());

		}
		String ambiente = props.getProperty("ambiente");
		URL_WS_BASE = props.getProperty(ambiente + ".url.ws.base");
		URL_WS_DETAIL = props.getProperty(ambiente + ".url.ws.detail");
	}
	
	
	
	
	public int expireItem(NNota nota) throws DetailCallWSException {

		int res = 0;
		String metodo = "/expire_item";
		String URL_WS = URL_WS_BASE + URL_WS_DETAIL + metodo;

		logger.info("--- expireItem --- [ DetailCallWS ] --- ");
		logger.info("--- URL : " + URL_WS);

		try {
	

			res = restTemplate.postForObject(URL_WS, nota, Integer.class);

			logger.info(" Registros obtenidos --> " + res);

		} catch (RestClientResponseException rre) {
			logger.error("RestClientResponseException expireItem [ DetailCallWS ]: " + rre.getResponseBodyAsString());
			logger.error("RestClientResponseException expireItem[ DetailCallWS ]: ", rre);
			throw new DetailCallWSException(rre.getResponseBodyAsString());
		} catch (Exception e) {
			logger.error("Exception expireItem  [ DetailCallWS ]: ", e);
			throw new DetailCallWSException(e.getMessage());
		}

		return res;

	}


	



	
	

	public List<HNota> findAllNota() throws DetailCallWSException {
	

		String metodo = "/get_item";
		String URL_WS = URL_WS_BASE + URL_WS_DETAIL + metodo;

		logger.info("--- findAllNota --- [ DetailCallWS ] --- ");
		logger.info("--- URL : " + URL_WS);
		
		HNotaWSResponse response = new HNotaWSResponse();

		

		try {
			logger.info("URL_WS: " + URL_WS);
			HttpEntity<String> entity = new HttpEntity<String>("Accept=application/json; charset=utf-8", headers);
			response = restTemplate.postForObject(URL_WS , entity, HNotaWSResponse.class);

			logger.info(" Registros obtenidos --> " + response.getLista().toString());
			logger.info(" Total Registros obtenidos --> " + response.getLista().size());

		} catch (RestClientResponseException rre) {
			logger.error("RestClientResponseException findAllNota [ DetailCallWS ]: " + rre.getResponseBodyAsString());
			logger.error("RestClientResponseException findAllNota[ DetailCallWS ]: ", rre);
			throw new DetailCallWSException(rre.getResponseBodyAsString());
		} catch (Exception e) {
			logger.error("Exception findAllNota  [ DetailCallWS ]: ", e);
			throw new DetailCallWSException(e.getMessage());
		}

		return response.getLista();
	
	}

}
