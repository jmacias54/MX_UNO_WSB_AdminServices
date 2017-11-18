/**
 * 
 */
package mx.com.amx.unotv.adminservice.ws;

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

import mx.com.amx.unotv.adminservice.model.NNota;
import mx.com.amx.unotv.adminservice.ws.exception.NNotaCallWSException;

/**
 * @author Jesus A. Macias Benitez
 *
 */
public class NNotaCallWS {
	
	private static Logger logger = Logger.getLogger(NNotaCallWS.class);

	private RestTemplate restTemplate;
	private String URL_WS_BASE = "";
	private String URL_WS_NNOTA = "";
	private HttpHeaders headers = new HttpHeaders();
	private final Properties props = new Properties();

	public NNotaCallWS() {
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
		URL_WS_NNOTA = props.getProperty(ambiente + ".url.ws.nnota");
	}
	
	

	
	public int insert(NNota nota) throws NNotaCallWSException {

		int res = 0;
		String metodo = "/insert";
		String URL_WS = URL_WS_BASE + URL_WS_NNOTA + metodo;

		logger.info("--- insert --- [ NNotaCallWS ] --- ");
		logger.info("--- URL : " + URL_WS);

		try {
			

			res = restTemplate.postForObject(URL_WS, nota, Integer.class);

			logger.info(" Registros obtenidos --> " + res);

		} catch (RestClientResponseException rre) {
			logger.error("RestClientResponseException insert [ NNotaCallWS ]: " + rre.getResponseBodyAsString());
			logger.error("RestClientResponseException insert[ NNotaCallWS ]: ", rre);
			throw new NNotaCallWSException(rre.getResponseBodyAsString());
		} catch (Exception e) {
			logger.error("Exception insert  [ NNotaCallWS ]: ", e);
			throw new NNotaCallWSException(e.getMessage());
		}

		return res;

	}
	
	
	
	public int delete(String idContenido) throws NNotaCallWSException {

		int res = 0;
		String metodo = "/delete";
		String URL_WS = URL_WS_BASE + URL_WS_NNOTA + metodo+"/"+idContenido;

		logger.info("--- delete --- [ NNotaCallWS ] --- ");
		logger.info("--- URL : " + URL_WS);

		try {
			
			HttpEntity<String> entity = new HttpEntity<String>("Accept=application/json; charset=utf-8", headers);
			res = restTemplate.postForObject(URL_WS, entity, Integer.class);

			logger.info(" Registros obtenidos --> " + res);

		} catch (RestClientResponseException rre) {
			logger.error("RestClientResponseException delete [ NNotaCallWS ]: " + rre.getResponseBodyAsString());
			logger.error("RestClientResponseException delete[ NNotaCallWS ]: ", rre);
			throw new NNotaCallWSException(rre.getResponseBodyAsString());
		} catch (Exception e) {
			logger.error("Exception delete  [ NNotaCallWS ]: ", e);
			throw new NNotaCallWSException(e.getMessage());
		}

		return res;

	}
	
	
	public int update(NNota nota) throws NNotaCallWSException {

		int res = 0;
		String metodo = "/update";
		String URL_WS = URL_WS_BASE + URL_WS_NNOTA + metodo;

		logger.info("--- update --- [ NNotaCallWS ] --- ");
		logger.info("--- URL : " + URL_WS);

		try {
			logger.info("URL_WS: " + URL_WS);

			res = restTemplate.postForObject(URL_WS, nota, Integer.class);

			logger.info(" Registros obtenidos --> " + res);

		} catch (RestClientResponseException rre) {
			logger.error("RestClientResponseException update [ NNotaCallWS ]: " + rre.getResponseBodyAsString());
			logger.error("RestClientResponseException update[ NNotaCallWS ]: ", rre);
			throw new NNotaCallWSException(rre.getResponseBodyAsString());
		} catch (Exception e) {
			logger.error("Exception update  [ NNotaCallWS ]: ", e);
			throw new NNotaCallWSException(e.getMessage());
		}

		return res;

	}
	
	
	public NNota findById(String idContenido) throws NNotaCallWSException {


		String URL_WS = URL_WS_BASE + URL_WS_NNOTA ;

		logger.info("--- findById --- [ NNotaCallWS ] --- ");
		logger.info("--- URL : " + URL_WS);

		NNota nota = null;

		try {
			logger.info("URL_WS: " + URL_WS);
			HttpEntity<String> entity = new HttpEntity<String>("Accept=application/json; charset=utf-8", headers);
			nota = restTemplate.postForObject(URL_WS + "/" + idContenido, entity, NNota.class);

			logger.info(" Registros obtenidos --> " + nota.toString());

		} catch (NullPointerException npe) {
			
			return null;
		}catch (RestClientResponseException rre) {
			logger.error("RestClientResponseException findById [ NNotaCallWS ]: " + rre.getResponseBodyAsString());
			logger.error("RestClientResponseException findById [ NNotaCallWS ]: ", rre);
			throw new NNotaCallWSException(rre.getResponseBodyAsString());
		} catch (Exception e) {
			logger.error("Exception findById  [ NNotaCallWS ]: ", e);
			throw new NNotaCallWSException(e.getMessage());
		}

		return nota;
	}

	
	

}
