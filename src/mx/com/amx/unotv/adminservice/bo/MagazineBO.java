/**
 * 
 */
package mx.com.amx.unotv.adminservice.bo;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import mx.com.amx.unotv.adminservice.bo.exception.MagazineBOException;
import mx.com.amx.unotv.adminservice.model.Magazine;
import mx.com.amx.unotv.adminservice.model.request.MagazineRequest;
import mx.com.amx.unotv.adminservice.model.response.ItemsResponse;
import mx.com.amx.unotv.adminservice.ws.MagazineCallWS;

/**
 * @author Jesus A. Macias Benitez
 *
 */
public class MagazineBO {

	@Autowired
	MagazineCallWS magazineCallWS;
	
	private static Logger logger = Logger.getLogger(MagazineBO.class);

	public List<ItemsResponse> getListItemsByMagazine(String idMagazine) throws MagazineBOException {

		List<ItemsResponse> lista = null;
		
		try {
			lista = magazineCallWS.getListItemsByMagazine(idMagazine);
		} catch (Exception e) {
			logger.error("Exception getListItemsByMagazine  [ MagazineBO ]: ", e);
			throw new MagazineBOException(e.getMessage());
		}


		return lista;

	}

	public List<Magazine> getListMagazine() throws MagazineBOException {

		List<Magazine> lista = null;

		try {
			lista = magazineCallWS.getListMagazine();
		} catch (Exception e) {
			logger.error("Exception getListMagazine  [ MagazineBO ]: ", e);
			throw new MagazineBOException(e.getMessage());
		}

		return lista;

	}
	
	public void saveMagazine(MagazineRequest req) throws MagazineBOException {
		

		try {
			magazineCallWS.saveMagazine(req);
		} catch (Exception e) {
			logger.error("Exception saveMagazine  [ MagazineBO ]: ", e);
			throw new MagazineBOException(e.getMessage());
		}

	}
	
	
	
}
