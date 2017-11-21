/**
 * 
 */
package mx.com.amx.unotv.adminservice.bo;

import org.apache.log4j.Logger;
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

	private static Logger logger = Logger.getLogger(NotaBO.class);

	@Autowired
	DetailCallWS detailCallWS;
	@Autowired
	NNotaCallWS nNotaCallWS;
	@Autowired
	HNotaCallWS hNotaCallWS;
	@Autowired
	TagBO tagBO;
	@Autowired
	INotaTagBO iNotaTagBO;

	public int saveOrUpdate(NNota nota, String tags) throws NotaBOException {
		logger.debug(" --- saveOrUpdate [ NotaBO ] --- ");
		int res = 0;

		try {

			if (validateIfExistHNota(nota.getFcIdContenido())) {

				res = update(nota, tags);

			} else {

				res = insert(nota, tags);

			}

		} catch (Exception e) {
			logger.error("Exception  saveOrUpdate [ NotaBO  ] : " + e.getMessage());
			throw new NotaBOException(e.getMessage());
		}

		return res;
	}

	public int expireItem(NNota nota) throws NotaBOException {
		logger.debug(" --- expireItem [ NotaBO ] --- ");
		int res = 0;

		try {

			iNotaTagBO.delete(nota.getFcIdContenido());
			res = nNotaCallWS.delete(nota.getFcIdContenido());
			if (res > 0) {
				nota.setFcIdEstatus("CAD");
				res = hNotaCallWS.update(nota);
			}

		} catch (Exception e) {
			logger.error("Exception  expireItem [ NotaBO  ] : " + e.getMessage());
			throw new NotaBOException(e.getMessage());
		}

		return res;
	}

	public int reviewItem(NNota nota, String tags) throws NotaBOException {
		logger.debug(" --- reviewItem [ NotaBO ] --- ");
		int res = 0;

		try {

			nota.setFcIdEstatus("REV");
			res = saveOrUpdate(nota, tags);

		} catch (Exception e) {

			logger.error("Exception  reviewItem [ NotaBO  ] : " + e.getMessage());
			throw new NotaBOException(e.getMessage());
		}

		return res;
	}

	public boolean validateIfExistNNota(String idContenido) throws NotaBOException {
		logger.debug(" --- validateIfExistNNota [ NotaBO ] --- ");
		NNota res = null;

		try {
			res = nNotaCallWS.findById(idContenido);
		} catch (Exception e) {
			logger.error("--- Exception  validateIfExistNNota [ NotaBO  ] : " + e.getMessage());
			throw new NotaBOException(e.getMessage());
		}
		return ((res == null) ? false : true);
	}

	public boolean validateIfExistHNota(String idContenido) throws NotaBOException {
		logger.debug(" --- validateIfExistHNota [ NotaBO ] --- ");
		HNota res = null;

		try {
			res = hNotaCallWS.findById(idContenido);
		} catch (Exception e) {
			logger.error("--- Exception  validateIfExistHNota [ NotaBO  ] : " + e.getMessage());
			throw new NotaBOException(e.getMessage());
		}
		return ((res == null) ? false : true);
	}

	private int insert(NNota nota, String tags) throws NotaBOException {
		logger.debug(" ---private insert [ NotaBO ] --- ");

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
			logger.error("--- Exception private insert [ NotaBO  ] : " + e.getMessage());
			throw new NotaBOException(e.getMessage());
		}

		return res;
	}

	private int update(NNota nota, String tags) throws NotaBOException {
		logger.debug(" ---private update [ NotaBO ] --- ");

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
			logger.error("--- Exception private update [ NotaBO  ] : " + e.getMessage());
			throw new NotaBOException(e.getMessage());
		}

		return res;
	}

}
