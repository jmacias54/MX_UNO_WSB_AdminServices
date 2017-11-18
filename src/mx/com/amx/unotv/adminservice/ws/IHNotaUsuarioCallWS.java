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
import mx.com.amx.unotv.adminservice.model.IHNotaUsuario;
import mx.com.amx.unotv.adminservice.ws.exception.IHNotaUsuarioCallWSException;

/**
 * @author Jesus A. Macias Benitez
 *
 */
public class IHNotaUsuarioCallWS {
	
	
	private static Logger logger = Logger.getLogger(NNotaCallWS.class);

	private RestTemplate restTemplate;
	private String URL_WS_BASE = "";
	private String URL_WS_I_H_NOTA_USUARIO = "/iHNotaUsuario";
	private HttpHeaders headers = new HttpHeaders();
	private final Properties props = new Properties();

	public IHNotaUsuarioCallWS() {
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
			logger.error("[ IHNotaUsuarioCallWS ::init]Error al iniciar y cargar arhivo de propiedades." + e.getMessage());

		}
		String ambiente = props.getProperty("ambiente");
		URL_WS_BASE = props.getProperty(ambiente + ".url.ws.base");
		//URL_WS_I_H_NOTA_USUARIO = props.getProperty(ambiente + "url.ws.i.hnotausuario");
	}
	
	
	public IHNotaUsuario findByIdContenido(String idContenido ) throws IHNotaUsuarioCallWSException {
		IHNotaUsuario user = null;
		
	
		String URL_WS = URL_WS_BASE + URL_WS_I_H_NOTA_USUARIO + "/"+idContenido;

		logger.info("--- pcodeFindAll --- [ CatalogsCallWS ] --- ");
		logger.info("--- URL : "+URL_WS);
		


		try {
			logger.info("URL_WS: " + URL_WS);

			HttpEntity<String> entity = new HttpEntity<String>("Accept=application/json; charset=utf-8", headers);
			user = restTemplate.postForObject(URL_WS , entity, IHNotaUsuario.class);

			logger.info(" Registros obtenidos --> " +user.toString());
			

		} catch (RestClientResponseException rre) {
			logger.error("RestClientResponseException findByIdContenido [ IHNotaUsuarioCallWS ]: " + rre.getResponseBodyAsString());
			logger.error("RestClientResponseException findByIdContenido[ IHNotaUsuarioCallWS ]: ", rre);
			throw new IHNotaUsuarioCallWSException(rre.getResponseBodyAsString());
		} catch (Exception e) {
			logger.error("Exception findByIdContenido  [ CatalogsCallWS ]: ", e);
			throw new IHNotaUsuarioCallWSException(e.getMessage());
		}
		
	
		
		return user;
	}
	


}
