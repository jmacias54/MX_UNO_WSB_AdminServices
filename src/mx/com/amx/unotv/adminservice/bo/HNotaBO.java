/**
 * 
 */
package mx.com.amx.unotv.adminservice.bo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import mx.com.amx.unotv.adminservice.bo.exception.HNotaBOException;
import mx.com.amx.unotv.adminservice.ws.HNotaCallWS;

/**
 * @author Jesus A. Macias Benitez
 *
 */
public class HNotaBO {
	
	private static Logger logger = Logger.getLogger(HNotaBO.class);

	@Autowired
	HNotaCallWS hNotaCallWS;

	public Integer getNoNotas(String date) throws HNotaBOException {
		logger.debug(" --- getNoNotas [ HNotaBO ] --- ");
		Integer total = 0;
		try {
			total = hNotaCallWS.getNoNotas(date);
		} catch (Exception e) {
			logger.error("Exception  getNoNotas [ HNotaBO  ] : " + e.getMessage());
			throw new HNotaBOException(e.getMessage());
		}

		return total;
	}

}
