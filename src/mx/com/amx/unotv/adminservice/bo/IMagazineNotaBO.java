/**
 * 
 */
package mx.com.amx.unotv.adminservice.bo;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import mx.com.amx.unotv.adminservice.bo.exception.IMagazineNotaBOException;
import mx.com.amx.unotv.adminservice.model.IMagazineNota;
import mx.com.amx.unotv.adminservice.ws.IMagazineNotaCallWS;

/**
 * @author Jesus A. Macias Benitez
 *
 */
public class IMagazineNotaBO {

	private static Logger logger = Logger.getLogger(IMagazineNotaBO.class);

	@Autowired
	IMagazineNotaCallWS iMagazineNotaCallWS;

	public List<IMagazineNota> getAll() throws IMagazineNotaBOException {

		try {
			return iMagazineNotaCallWS.getAll();
		} catch (Exception e) {
			logger.error("Exception getAll  [ IMagazineNotaBO ]: ", e);
			throw new IMagazineNotaBOException(e.getMessage());
		}

	}

	public List<IMagazineNota> findById(String idMagazine) throws IMagazineNotaBOException {
		logger.info("--  findById  [ IMagazineNotaBO ] ----");

		try {
			return iMagazineNotaCallWS.findById(idMagazine);
		} catch (Exception e) {
			logger.error("Exception findById  [ IMagazineNotaBO ]: ", e);
			throw new IMagazineNotaBOException(e.getMessage());
		}

	}

}
