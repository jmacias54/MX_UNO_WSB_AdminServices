/*
*
 * 
 */
package mx.com.amx.unotv.adminservice.bo;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import mx.com.amx.unotv.adminservice.bo.exception.DetailBOException;
import mx.com.amx.unotv.adminservice.model.HNota;
import mx.com.amx.unotv.adminservice.model.NNota;
import mx.com.amx.unotv.adminservice.ws.DetailCallWS;

/**
 * @author Jesus A. Macias Benitez
 *
 */
public class DetailBO {

	private static Logger logger = Logger.getLogger(DetailBO.class);

	@Autowired
	DetailCallWS detailCallWS;

	public int insertNota(NNota nota) throws DetailBOException {

		int res = 0;

		try {
			res = detailCallWS.insertNota(nota);
		} catch (Exception e) {
			logger.error("Exception insertNota  [ DetailBO ]: ", e);
			throw new DetailBOException(e.getMessage());
		}
		return res;

	}

	public HNota findNotaById(String idContenido) throws DetailBOException {

		HNota nota = null;

		try {
			nota = detailCallWS.findNotaById(idContenido);
		} catch (Exception e) {
			logger.error("Exception findNotaById  [ DetailBO ]: ", e);
			throw new DetailBOException(e.getMessage());
		}

		return nota;
	}

	public List<HNota> findAllNota() throws DetailBOException {
		List<HNota> lista = null;

		try {
			lista = detailCallWS.findAllNota();
		} catch (Exception e) {
			logger.error("Exception findAllNota  [ DetailBO ]: ", e);
			throw new DetailBOException(e.getMessage());
		}

		return lista;

	}

}
