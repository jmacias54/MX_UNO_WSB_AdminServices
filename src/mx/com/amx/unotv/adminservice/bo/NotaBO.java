/**
 * 
 */
package mx.com.amx.unotv.adminservice.bo;

import org.springframework.beans.factory.annotation.Autowired;

import mx.com.amx.unotv.adminservice.bo.exception.NotaBOException;
import mx.com.amx.unotv.adminservice.model.HNota;
import mx.com.amx.unotv.adminservice.model.NNota;
import mx.com.amx.unotv.adminservice.ws.DetailCallWS;
import mx.com.amx.unotv.adminservice.ws.HNotaCallWS;
import mx.com.amx.unotv.adminservice.ws.NNotaCallWS;

/**
 * @author Jesus A. Macias Benitez
 *
 */
public class NotaBO {

	@Autowired
	DetailCallWS detailCallWS;
	@Autowired
	NNotaCallWS nNotaCallWS;
	@Autowired
	HNotaCallWS hNotaCallWS;

	public int saveOrUpdate(NNota nota) throws NotaBOException {
		int res = 0;

		try {

			if (validateIfExistHNota(nota.getFcIdContenido())) {
				if (validateIfExistNNota(nota.getFcIdContenido())) {

					res = nNotaCallWS.update(nota);
				} else {

					res = nNotaCallWS.insert(nota);
				}

				if (res > 0)
					res = hNotaCallWS.updateNota(nota);

			} else {

				res = insert(nota);

			}

		} catch (Exception e) {

			throw new NotaBOException(e.getMessage());
		}

		return res;
	}

	public boolean validateIfExistNNota(String idContenido) throws NotaBOException {
		NNota res = null;

		try {
			res = nNotaCallWS.findById(idContenido);
		} catch (Exception e) {
			throw new NotaBOException(e.getMessage());
		}
		return ((res == null) ? false : true);
	}

	public boolean validateIfExistHNota(String idContenido) throws NotaBOException {
		HNota res = null;

		try {
			res = hNotaCallWS.findById(idContenido);
		} catch (Exception e) {
			throw new NotaBOException(e.getMessage());
		}
		return ((res == null) ? false : true);
	}

	public int insert(NNota nota) throws NotaBOException {

		// inserta NNota

		int res = 0;

		try {

			res = nNotaCallWS.insert(nota);
			if (res > 0)

				res = hNotaCallWS.insertNota(nota);

		} catch (Exception e) {
			throw new NotaBOException(e.getMessage());
		}

		return res;
	}

}
