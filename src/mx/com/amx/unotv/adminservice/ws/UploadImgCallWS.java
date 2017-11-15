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

import mx.com.amx.unotv.adminservice.model.request.ImageRequest;
import mx.com.amx.unotv.adminservice.model.response.ImageResponse;
import mx.com.amx.unotv.adminservice.ws.exception.UploadImgCallWSException;

/**
 * @author Jesus A. Macias Benitez
 *
 */
public class UploadImgCallWS {

	private static Logger logger = Logger.getLogger(UploadImgCallWS.class);

	private RestTemplate restTemplate;
	private HttpHeaders headers = new HttpHeaders();
	private final Properties props = new Properties();

	public UploadImgCallWS() {
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
		
		
	}

	public ImageResponse cropImage(ImageRequest request, String Url) throws UploadImgCallWSException {
		logger.info("--- cropImage --- [ UploadImgCallWS ] --- ");
		logger.info("--- URL : " + Url);

		ImageResponse response = null;

		try {

			// HttpEntity<String> entity = new HttpEntity<String>("Accept=application/json;
			// charset=utf-8", headers);
			response = restTemplate.postForObject(Url, request, ImageResponse.class);

			logger.info(" Registros obtenidos --> " + response.toString());

		} catch (RestClientResponseException rre) {
			logger.error("RestClientResponseException cropImage [ UploadImgCallWS ]: " + rre.getResponseBodyAsString());
			logger.error("RestClientResponseException cropImage[ UploadImgCallWS ]: ", rre);
			throw new UploadImgCallWSException(rre.getResponseBodyAsString());
		} catch (Exception e) {
			logger.error("Exception cropImage  [ UploadImgCallWS ]: ", e);
			throw new UploadImgCallWSException(e.getMessage());
		}

		return response;

	}

}
