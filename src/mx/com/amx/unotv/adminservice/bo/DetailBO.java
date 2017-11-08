/*
*
 * 
 */
package mx.com.amx.unotv.adminservice.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import mx.com.amx.unotv.adminservice.model.HNota;
import mx.com.amx.unotv.adminservice.model.NNota;
import mx.com.amx.unotv.adminservice.ws.DetailCallWS;

/**
 * @author Jesus A. Macias Benitez
 *
 */
public class DetailBO {

	@Autowired
	DetailCallWS detailCallWS;

	public int insertNota(NNota nota) {

		int res = 0;

		return res;

	}

	public HNota findNotaById(String idContenido) {

		return new HNota();
	}

	public List<HNota> findAllNota() {
		List<HNota> lista = null;

		return lista;

	}

}
