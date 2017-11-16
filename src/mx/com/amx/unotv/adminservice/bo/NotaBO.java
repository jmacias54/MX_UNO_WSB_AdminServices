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
		HNota hNota = null;
		NNota nNota = null;

		try {
			hNota = detailCallWS.findNotaById(nota.getFcIdContenido());
			nNota = detailCallWS.findNNotaById(nota.getFcIdContenido());

			// si no hay nota en HNota
			if (hNota == null) {
				// inserta NNota

				res = nNotaCallWS.insertNota(nota);

				// valida inserccion NNota
				if (res > 0) {

					// inserta HNota
					res = hNotaCallWS.insertNota(nota);
				}

			} else {// si hay nota en HNota

				// si no hay nota en NNota
				if (nNota == null) {

					// inserta NNota
					res = nNotaCallWS.insertNota(nota);

				} else {

					// actualiza NNota
					res = nNotaCallWS.updateNota(nota);

				}

				if (res > 0)

					// actualiza HNota
					res = hNotaCallWS.updateNota(nota);
			}

		} catch (Exception e) {

			throw new NotaBOException(e.getMessage());
		}

		return res;
	}

}
