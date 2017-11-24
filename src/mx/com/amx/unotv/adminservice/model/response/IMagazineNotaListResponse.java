/**
 * 
 */
package mx.com.amx.unotv.adminservice.model.response;

import java.util.List;

import mx.com.amx.unotv.adminservice.model.IMagazineNota;

/**
 * @author Jesus A. Macias Benitez
 *
 */
public class IMagazineNotaListResponse {
	
	List<IMagazineNota> lista ;
	
	

	/**
	 * @param lista
	 */
	public IMagazineNotaListResponse(List<IMagazineNota> lista) {
		super();
		this.lista = lista;
	}

	/**
	 * 
	 */
	public IMagazineNotaListResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<IMagazineNota> getLista() {
		return lista;
	}

	public void setLista(List<IMagazineNota> lista) {
		this.lista = lista;
	}
	

}
