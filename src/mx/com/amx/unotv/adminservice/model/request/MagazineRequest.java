package mx.com.amx.unotv.adminservice.model.request;
/**
 * @author Jesus A. Macias Benitez
 *
 */
import java.util.List;

import mx.com.amx.unotv.adminservice.model.IMagazineNota;

// TODO: Auto-generated Javadoc
/**
 * The Class MagazineRequest.
 */
public class MagazineRequest {

	/** The id magazine. */
	String id_magazine;

	/** The lista. */
	List<IMagazineNota> lista;

	/**
	 * Gets the id magazine.
	 *
	 * @return the id magazine
	 */
	public String getId_magazine() {
		return id_magazine;
	}

	/**
	 * Sets the id magazine.
	 *
	 * @param id_magazine the new id magazine
	 */
	public void setId_magazine(String id_magazine) {
		this.id_magazine = id_magazine;
	}

	/**
	 * Gets the lista.
	 *
	 * @return the lista
	 */
	public List<IMagazineNota> getLista() {
		return lista;
	}

	/**
	 * Sets the lista.
	 *
	 * @param lista the new lista
	 */
	public void setLista(List<IMagazineNota> lista) {
		this.lista = lista;
	}
	
	
	
	

}
