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
import mx.com.amx.unotv.adminservice.model.IMagazineNota;
import mx.com.amx.unotv.adminservice.model.response.IMagazineNotaListResponse;
import mx.com.amx.unotv.adminservice.ws.exception.IMagazineNotaCallWSException;

/**
 * @author Jesus A. Macias Benitez
 *
 */
public class IMagazineNotaCallWS {

	private static Logger logger = Logger.getLogger(MagazineCallWS.class);

	private RestTemplate restTemplate;
	private String URL_WS_BASE = "";
	private String URL_WS_IMAGAZINE_NOTA = "";
	private HttpHeaders headers = new HttpHeaders();
	private final Properties props = new Properties();

	public IMagazineNotaCallWS() {
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
			logger.error(
					"[IMagazineNotaCallWS::init]Error al iniciar y cargar arhivo de propiedades." + e.getMessage());

		}
		String ambiente = props.getProperty("ambiente");
		URL_WS_BASE = props.getProperty(ambiente + ".url.ws.base");
		URL_WS_IMAGAZINE_NOTA = props.getProperty(ambiente + ".url.ws.imagazine.nota");
	}

	public List<IMagazineNota> getAll() throws IMagazineNotaCallWSException {

		String metodo = "/";
		String URL_WS = URL_WS_BASE + URL_WS_IMAGAZINE_NOTA + metodo;

		logger.info("--- getAll --- [ MagazineCallWS ] --- ");
		logger.info("--- URL : " + URL_WS);

		IMagazineNotaListResponse response = null;

		try {
			logger.info("URL_WS: " + URL_WS);

			HttpEntity<String> entity = new HttpEntity<String>("Accept=application/json; charset=utf-8", headers);
			response = restTemplate.postForObject(URL_WS, entity, IMagazineNotaListResponse.class);

			if (response != null)
				logger.info(" Registros obtenidos --> " + response.getLista().toString());

		} catch (RestClientResponseException rre) {
			logger.error(
					"RestClientResponseException getAll [ IMagazineNotaCallWS ]: " + rre.getResponseBodyAsString());
			logger.error("RestClientResponseException getAll[ IMagazineNotaCallWS ]: ", rre);
			throw new IMagazineNotaCallWSException(rre.getResponseBodyAsString());
		} catch (Exception e) {
			logger.error("Exception getAll  [ IMagazineNotaCallWS ]: ", e);
			throw new IMagazineNotaCallWSException(e.getMessage());
		}

		return response.getLista();

	}

	public List<IMagazineNota> findById(String idMagazine) throws IMagazineNotaCallWSException {

		String metodo = "/";
		String URL_WS = URL_WS_BASE + URL_WS_IMAGAZINE_NOTA + metodo + idMagazine;

		logger.info("--- findById --- [ MagazineCallWS ] --- ");
		logger.info("--- URL : " + URL_WS);

		IMagazineNotaListResponse response = null;

		try {
			logger.info("URL_WS: " + URL_WS);

			HttpEntity<String> entity = new HttpEntity<String>("Accept=application/json; charset=utf-8", headers);
			response = restTemplate.postForObject(URL_WS, entity, IMagazineNotaListResponse.class);

			if (response != null)
				logger.info(" Registros obtenidos --> " + response.getLista().toString());

		} catch (RestClientResponseException rre) {
			logger.error(
					"RestClientResponseException findById [ IMagazineNotaCallWS ]: " + rre.getResponseBodyAsString());
			logger.error("RestClientResponseException findById[ IMagazineNotaCallWS ]: ", rre);
			throw new IMagazineNotaCallWSException(rre.getResponseBodyAsString());
		} catch (Exception e) {
			logger.error("Exception findById  [ IMagazineNotaCallWS ]: ", e);
			throw new IMagazineNotaCallWSException(e.getMessage());
		}

		return response.getLista();

	}
}
