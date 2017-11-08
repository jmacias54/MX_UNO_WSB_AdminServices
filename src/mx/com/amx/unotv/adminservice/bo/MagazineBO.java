/**
 * 
 */
package mx.com.amx.unotv.adminservice.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	

	public List<ItemsResponse> getListItemsByMagazine(String idMagazine) {

		List<ItemsResponse> lista = null;

		return lista;

	}

	public List<Magazine> getListMagazine() {

		List<Magazine> lista = null;

		return lista;

	}
	
	public void saveMagazine(MagazineRequest req) {
		
	}
	
	
	
}
