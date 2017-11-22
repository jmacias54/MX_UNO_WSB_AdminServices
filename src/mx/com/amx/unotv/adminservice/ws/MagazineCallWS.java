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

import mx.com.amx.unotv.adminservice.model.Magazine;
import mx.com.amx.unotv.adminservice.model.request.MagazineRequest;
import mx.com.amx.unotv.adminservice.model.response.ListMagazine;
import mx.com.amx.unotv.adminservice.ws.exception.MagazineCallWSException;

/**
 * @author Jesus A. Macias Benitez
 *
 */
public class MagazineCallWS {
	
	
	private static Logger logger = Logger.getLogger(MagazineCallWS.class);

	private RestTemplate restTemplate;
	private String URL_WS_BASE = "";
	private String URL_WS_MAGAZINE = "";
	private HttpHeaders headers = new HttpHeaders();
	private final Properties props = new Properties();

	public MagazineCallWS() {
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
			logger.error("[MagazineCallWS::init]Error al iniciar y cargar arhivo de propiedades." + e.getMessage());

		}
		String ambiente = props.getProperty("ambiente");
		URL_WS_BASE = props.getProperty(ambiente + ".url.ws.base");
		URL_WS_MAGAZINE = props.getProperty(ambiente + ".url.ws.magazine");
	}
	
	
	public Magazine getById(String idMagazine) throws MagazineCallWSException {

		String metodo = "/get_magazine";
		String URL_WS = URL_WS_BASE + URL_WS_MAGAZINE+ metodo;

		logger.info("--- getById --- [ MagazineCallWS ] --- ");
		logger.info("--- URL : "+URL_WS);
		
		Magazine response = null;

		try {
			logger.info("URL_WS: " + URL_WS);

			HttpEntity<String> entity = new HttpEntity<String>("Accept=application/json; charset=utf-8", headers);
			response = restTemplate.postForObject(URL_WS + "/"+idMagazine, entity, Magazine.class);

			logger.info(" Registros obtenidos --> " + response.toString());
		

		} catch (RestClientResponseException rre) {
			logger.error("RestClientResponseException getById [ MagazineCallWS ]: " + rre.getResponseBodyAsString());
			logger.error("RestClientResponseException getById[ MagazineCallWS ]: ", rre);
			throw new MagazineCallWSException(rre.getResponseBodyAsString());
		} catch (Exception e) {
			logger.error("Exception getById  [ MagazineCallWS ]: ", e);
			throw new MagazineCallWSException(e.getMessage());
		}
		
		return response;

	}

	public List<Magazine> getListMagazine() throws MagazineCallWSException {

	
		String metodo = "/get_list_magazine";
		String URL_WS = URL_WS_BASE + URL_WS_MAGAZINE+ metodo;

		logger.info("--- getListMagazine --- [ MagazineCallWS ] --- ");
		logger.info("--- URL : "+URL_WS);
		
		ListMagazine response = new ListMagazine();

		try {
			logger.info("URL_WS: " + URL_WS);

			HttpEntity<String> entity = new HttpEntity<String>("Accept=application/json; charset=utf-8", headers);
			response = restTemplate.postForObject(URL_WS , entity, ListMagazine.class);

			logger.info(" Registros obtenidos --> " + response.getLista().toString());
			logger.info(" Total Registros obtenidos --> " + response.getLista().size());

		} catch (RestClientResponseException rre) {
			logger.error("RestClientResponseException getListMagazine [ MagazineCallWS ]: " + rre.getResponseBodyAsString());
			logger.error("RestClientResponseException getListMagazine[ MagazineCallWS ]: ", rre);
			throw new MagazineCallWSException(rre.getResponseBodyAsString());
		} catch (Exception e) {
			logger.error("Exception getListMagazine  [ MagazineCallWS ]: ", e);
			throw new MagazineCallWSException(e.getMessage());
		}
		
		return response.getLista();


	}
	
	public void saveMagazine(MagazineRequest req) throws MagazineCallWSException {
		
		String metodo = "/save_magazine";
		String URL_WS = URL_WS_BASE + URL_WS_MAGAZINE+metodo;

		logger.info("--- saveMagazine --- [ MagazineCallWS ] --- ");
		logger.info("--- URL : "+URL_WS);
		
		try {
			logger.info("URL_WS: " + URL_WS);

			
			restTemplate.postForObject(URL_WS , req, MagazineRequest.class);
 

		} catch (RestClientResponseException rre) {
			logger.error("RestClientResponseException saveMagazine [ MagazineCallWS ]: " + rre.getResponseBodyAsString());
			logger.error("RestClientResponseException saveMagazine[ MagazineCallWS ]: ", rre);
			throw new MagazineCallWSException(rre.getResponseBodyAsString());
		} catch (Exception e) {
			logger.error("Exception saveMagazine  [ MagazineCallWS ]: ", e);
			throw new MagazineCallWSException(e.getMessage());
		}
		
		
	}

}
