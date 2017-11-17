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
	@Autowired
	TagBO tagBO;

	public int saveOrUpdate(NNota nota, String tags) throws NotaBOException {
		int res = 0;

		try {

			if (validateIfExistHNota(nota.getFcIdContenido())) {

				res = update(nota, tags);

			} else {

				res = insert(nota, tags);

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

	private int insert(NNota nota, String tags) throws NotaBOException {

		int res = 0;

		try {

			// se borran tags en las 2tablas intermedias para Negocio e Historico
			tagBO.deleteIntermediateTags(nota.getFcIdContenido());
			// se insertan tags en las 2tablas intermedias para Negocio e Historico
			tagBO.insertIntermediateTags(nota.getFcIdContenido(), tags);

			res = nNotaCallWS.insert(nota);
			if (res > 0) {
				res = hNotaCallWS.insert(nota);
			}

		} catch (Exception e) {
			throw new NotaBOException(e.getMessage());
		}

		return res;
	}

	private int update(NNota nota, String tags) throws NotaBOException {

		int res = 0;

		try {

			// se borran tags en las 2tablas intermedias para Negocio e Historico
			tagBO.deleteIntermediateTags(nota.getFcIdContenido());
			// se insertan tags en las 2tablas intermedias para Negocio e Historico
			tagBO.insertIntermediateTags(nota.getFcIdContenido(), tags);

			/* si existe informacion en NNota , actualiza */
			if (validateIfExistNNota(nota.getFcIdContenido())) {

				res = nNotaCallWS.update(nota);
			} else {/* si no existe informacion en NNota , inserta */

				res = nNotaCallWS.insert(nota);
			}

			/*
			 * si se inserto o actualizo informacion en NNota , Inserta informacion en HNota
			 */
			if (res > 0) {
				res = hNotaCallWS.update(nota);
			}

		} catch (Exception e) {
			throw new NotaBOException(e.getMessage());
		}

		return res;
	}

}
