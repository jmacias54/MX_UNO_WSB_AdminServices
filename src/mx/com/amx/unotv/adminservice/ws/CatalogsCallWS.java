/**
 * 
 */
package mx.com.amx.unotv.adminservice.ws;

import java.util.ArrayList;
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

import mx.com.amx.unotv.adminservice.model.Categoria;
import mx.com.amx.unotv.adminservice.model.Pcode;
import mx.com.amx.unotv.adminservice.model.response.CatalogResponse;
import mx.com.amx.unotv.adminservice.model.response.CategoriaSeccionResponse;
import mx.com.amx.unotv.adminservice.model.response.CategoriaSeccionWSResponse;
import mx.com.amx.unotv.adminservice.model.response.CategoriaWSResponse;
import mx.com.amx.unotv.adminservice.model.response.PcodeListResponse;
import mx.com.amx.unotv.adminservice.model.response.UserResponse;
import mx.com.amx.unotv.adminservice.ws.exception.CatalogsCallWSException;


/**
 * @author Jesus A. Macias Benitez
 *
 */
public class CatalogsCallWS {
	

	private static Logger logger = Logger.getLogger(CatalogsCallWS.class);

	private RestTemplate restTemplate;
	private String URL_WS_BASE = "";
	private String URL_WS_CATALOGS = "";
	private HttpHeaders headers = new HttpHeaders();
	private final Properties props = new Properties();

	public CatalogsCallWS() {
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
			logger.error("[ConsumeWS::init]Error al iniciar y cargar arhivo de propiedades." + e.getMessage());

		}
		String ambiente = props.getProperty("ambiente");
		URL_WS_BASE = props.getProperty(ambiente + ".url.ws.base");
		URL_WS_CATALOGS = props.getProperty(ambiente + ".url.ws.catalogs");
	}
	
	
	public List<Pcode> pcodeFindAll() throws CatalogsCallWSException {
		String metodo = "/get_video_pcode";
		String URL_WS = URL_WS_BASE + URL_WS_CATALOGS + metodo;

		logger.info("--- pcodeFindAll --- [ CatalogsCallWS ] --- ");
		logger.info("--- URL : "+URL_WS);
		
		PcodeListResponse request = new PcodeListResponse();

		try {
			logger.info("URL_WS: " + URL_WS);

			HttpEntity<String> entity = new HttpEntity<String>("Accept=application/json; charset=utf-8", headers);
			request = restTemplate.postForObject(URL_WS + "/", entity, PcodeListResponse.class);

			logger.info(" Registros obtenidos --> " + request.getLista().toString());
			logger.info(" Total Registros obtenidos --> " + request.getLista().size());

		} catch (RestClientResponseException rre) {
			logger.error("RestClientResponseException pcodeFindAll [ CatalogsCallWS ]: " + rre.getResponseBodyAsString());
			logger.error("RestClientResponseException pcodeFindAll[ CatalogsCallWS ]: ", rre);
			throw new CatalogsCallWSException(rre.getResponseBodyAsString());
		} catch (Exception e) {
			logger.error("Exception pcodeFindAll  [ CatalogsCallWS ]: ", e);
			throw new CatalogsCallWSException(e.getMessage());
		}
		
		

		return request.getLista();

	}

	public List<CategoriaSeccionResponse> categoriesFindAllByIdSeccion(String idSeccion) throws CatalogsCallWSException {
		
		String metodo = "/get_categories";
		String URL_WS = URL_WS_BASE + URL_WS_CATALOGS + metodo;

		logger.info("--- categoriesFindAllByIdSeccion --- [ CatalogsCallWS ] --- ");
		logger.info("--- URL : "+URL_WS);
		
		CategoriaSeccionWSResponse request = new CategoriaSeccionWSResponse();

		try {
			logger.info("URL_WS: " + URL_WS);

			HttpEntity<String> entity = new HttpEntity<String>("Accept=application/json; charset=utf-8", headers);
			request = restTemplate.postForObject(URL_WS + "/"+idSeccion, entity, CategoriaSeccionWSResponse.class);

			logger.info(" Registros obtenidos --> " + request.getLista().toString());
			logger.info(" Total Registros obtenidos --> " + request.getLista().size());

		} catch (RestClientResponseException rre) {
			logger.error("RestClientResponseException categoriesFindAllByIdSeccion [ CatalogsCallWS ]: " + rre.getResponseBodyAsString());
			logger.error("RestClientResponseException categoriesFindAllByIdSeccion [ CatalogsCallWS ]: ", rre);
			throw new CatalogsCallWSException(rre.getResponseBodyAsString());
		} catch (Exception e) {
			logger.error("Exception categoriesFindAllByIdSeccion  [ CatalogsCallWS ]: ", e);
			throw new CatalogsCallWSException(e.getMessage());
		}
		
		

		return request.getLista();
	
	}

	public List<Categoria> categoriesFindAll() throws CatalogsCallWSException {
	
		
		CategoriaWSResponse response = new CategoriaWSResponse();

		String metodo = "/get_categories";
		String URL_WS = URL_WS_BASE + URL_WS_CATALOGS + metodo;

		logger.info("--- categoriesFindAllByIdSeccion --- [ CatalogsCallWS ] --- ");
		logger.info("--- URL : "+URL_WS);
		
		

		try {
			logger.info("URL_WS: " + URL_WS);

			HttpEntity<String> entity = new HttpEntity<String>("Accept=application/json; charset=utf-8", headers);
			response = restTemplate.postForObject(URL_WS , entity, CategoriaWSResponse.class);

			logger.info(" Registros obtenidos --> " + response.getLista().toString());
			logger.info(" Total Registros obtenidos --> " + response.getLista().size());

		} catch (RestClientResponseException rre) {
			logger.error("RestClientResponseException categoriesFindAllByIdSeccion [ CatalogsCallWS ]: " + rre.getResponseBodyAsString());
			logger.error("RestClientResponseException categoriesFindAllByIdSeccion [ CatalogsCallWS ]: ", rre);
			throw new CatalogsCallWSException(rre.getResponseBodyAsString());
		} catch (Exception e) {
			logger.error("Exception categoriesFindAllByIdSeccion  [ CatalogsCallWS ]: ", e);
			throw new CatalogsCallWSException(e.getMessage());
		}
		
		

		return response.getLista();
	}

	public List<UserResponse> getAllUsers() {
		List<UserResponse> lista = null;

		return lista;

	}

	public List<CategoriaSeccionResponse> getAllSection() {
		List<CategoriaSeccionResponse> lista = null;

		return lista;

	}

	public List<CatalogResponse> getAllTags() {
		List<CatalogResponse> lista = null;

		return lista;
	}


}
