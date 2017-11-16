/**
 * 
 */
package mx.com.amx.unotv.adminservice.ws;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import mx.com.amx.unotv.adminservice.model.NNota;
import mx.com.amx.unotv.adminservice.ws.exception.HNotaCallWSException;

/**
 * @author Jesus A. Macias Benitez
 *
 */
public class HNotaCallWS {
	
	private static Logger logger = Logger.getLogger(HNotaCallWS.class);

	private RestTemplate restTemplate;
	private String URL_WS_BASE = "";
	private String URL_WS_DETAIL = "";
	private HttpHeaders headers = new HttpHeaders();
	private final Properties props = new Properties();

	public HNotaCallWS() {
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
			logger.error("[HNotaCallWS::init]Error al iniciar y cargar arhivo de propiedades." + e.getMessage());

		}
		String ambiente = props.getProperty("ambiente");
		URL_WS_BASE = props.getProperty(ambiente + ".url.ws.base");
		URL_WS_DETAIL = props.getProperty(ambiente + ".url.ws.detail");
	}
	
	
	public int insertNota(NNota nota) throws HNotaCallWSException {

		int res = 0;
		String metodo = "/save_h_nota";
		String URL_WS = URL_WS_BASE + URL_WS_DETAIL + metodo;

		logger.info("--- insertNota --- [ HNotaCallWS ] --- ");
		logger.info("--- URL : " + URL_WS);

		try {
			

			res = restTemplate.postForObject(URL_WS, nota, Integer.class);

			logger.info(" Registros obtenidos --> " + res);

		} catch (RestClientResponseException rre) {
			logger.error("RestClientResponseException insertNota [ HNotaCallWS ]: " + rre.getResponseBodyAsString());
			logger.error("RestClientResponseException insertNota[ HNotaCallWS ]: ", rre);
			throw new HNotaCallWSException(rre.getResponseBodyAsString());
		} catch (Exception e) {
			logger.error("Exception insertNota  [ HNotaCallWS ]: ", e);
			throw new HNotaCallWSException(e.getMessage());
		}

		return res;

	}
	
	
	public int updateNota(NNota nota) throws HNotaCallWSException {

		int res = 0;
		String metodo = "/update_h_nota";
		String URL_WS = URL_WS_BASE + URL_WS_DETAIL + metodo;

		logger.info("--- updateNota --- [ NNotaCallWS ] --- ");
		logger.info("--- URL : " + URL_WS);

		try {
			logger.info("URL_WS: " + URL_WS);

			res = restTemplate.postForObject(URL_WS, nota, Integer.class);

			logger.info(" Registros obtenidos --> " + res);

		} catch (RestClientResponseException rre) {
			logger.error("RestClientResponseException updateNota [ NNotaCallWS ]: " + rre.getResponseBodyAsString());
			logger.error("RestClientResponseException updateNota[ NNotaCallWS ]: ", rre);
			throw new HNotaCallWSException(rre.getResponseBodyAsString());
		} catch (Exception e) {
			logger.error("Exception updateNota  [ NNotaCallWS ]: ", e);
			throw new HNotaCallWSException(e.getMessage());
		}

		return res;

	}
	
	

}
