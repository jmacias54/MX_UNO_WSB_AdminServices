/**
 * 
 */
package mx.com.amx.unotv.adminservice.model.response;

import java.util.List;

import mx.com.amx.unotv.adminservice.model.Categoria;

/**
 * @author Jesus A. Macias Benitez
 * @param <T>
 *
 */
public class CategoriaWSResponse {

	
	List<Categoria> lista;

	public List<Categoria> getLista() {
		return lista;
	}

	public void setLista(List<Categoria> lista) {
		this.lista = lista;
	}
	
	
	
}
