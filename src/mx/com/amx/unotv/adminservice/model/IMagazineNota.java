package mx.com.amx.unotv.adminservice.model;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the uno_i_magazine_nota database table.
 * 
 *//**
 * @author Jesus A. Macias Benitez
*
*/

public class IMagazineNota implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The fc id magazine. */
	private int fcIdMagazine;

	/** The fc id contenido. */
	private String fcIdContenido;
	
	/** The fc url externa. */
	private String fcUrlExterna;
	
	/** The fi orden. */
	private Integer fiOrden;

	/**
	 * Instantiates a new i magazine nota.
	 */
	public IMagazineNota() {
	}

	/**
	 * Gets the fc url externa.
	 *
	 * @return the fc url externa
	 */
	public String getFcUrlExterna() {
		return fcUrlExterna;
	}

	/**
	 * Sets the fc url externa.
	 *
	 * @param fcUrlExterna the new fc url externa
	 */
	public void setFcUrlExterna(String fcUrlExterna) {
		this.fcUrlExterna = fcUrlExterna;
	}

	/**
	 * Gets the fi orden.
	 *
	 * @return the fi orden
	 */
	public Integer getFiOrden() {
		return fiOrden;
	}

	/**
	 * Sets the fi orden.
	 *
	 * @param fiOrden the new fi orden
	 */
	public void setFiOrden(Integer fiOrden) {
		this.fiOrden = fiOrden;
	}

	/**
	 * Gets the fc id magazine.
	 *
	 * @return the fc id magazine
	 */
	public int getFcIdMagazine() {
		return fcIdMagazine;
	}

	/**
	 * Sets the fc id magazine.
	 *
	 * @param fcIdMagazine the new fc id magazine
	 */
	public void setFcIdMagazine(int fcIdMagazine) {
		this.fcIdMagazine = fcIdMagazine;
	}

	/**
	 * Gets the fc id contenido.
	 *
	 * @return the fc id contenido
	 */
	public String getFcIdContenido() {
		return fcIdContenido;
	}

	/**
	 * Sets the fc id contenido.
	 *
	 * @param fcIdContenido the new fc id contenido
	 */
	public void setFcIdContenido(String fcIdContenido) {
		this.fcIdContenido = fcIdContenido;
	}

}