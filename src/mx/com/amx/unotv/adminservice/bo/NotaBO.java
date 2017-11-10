/**
 * 
 */
package mx.com.amx.unotv.adminservice.bo;

import org.springframework.beans.factory.annotation.Autowired;

import mx.com.amx.unotv.adminservice.bo.exception.NotaBOException;
import mx.com.amx.unotv.adminservice.model.HNota;
import mx.com.amx.unotv.adminservice.model.NNota;
import mx.com.amx.unotv.adminservice.ws.DetailCallWS;
import mx.com.amx.unotv.adminservice.ws.ItemsCallWS;
import mx.com.amx.unotv.adminservice.ws.exception.DetailCallWSException;

/**
 * @author Jesus A. Macias Benitez
 *
 */
public class NotaBO {

	@Autowired
	DetailCallWS detailCallWS;

	public int saveOrUpdate(NNota nota) {
		int res = 0;
		HNota notaRes = null;

		try {
			notaRes = detailCallWS.findNotaById(nota.getFcIdContenido());

			if (notaRes != null) {

				res = detailCallWS.updateNota(nota);
			} else {

				res = detailCallWS.insertNota(nota);
			}
			
			
		} catch (Exception e) {

			new NotaBOException(e.getMessage());
		}

		return res;
	}

}
