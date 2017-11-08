/**
 * 
 */
package mx.com.amx.unotv.adminservice.ws;

import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import mx.com.amx.unotv.adminservice.model.Magazine;
import mx.com.amx.unotv.adminservice.model.response.ItemsResponse;

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
	
	
	public List<ItemsResponse> getListItemsByMagazine(String idMagazine) {

		List<ItemsResponse> lista = null;

		return lista;

	}

	public List<Magazine> getListMagazine() {

		List<Magazine> lista = null;

		return lista;

	}

}
