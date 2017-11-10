package mx.com.amx.unotv.adminservice.model;

import java.io.Serializable;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the uno_c_magazine database table.
 * 
 */
/**
 * @author Jesus A. Macias Benitez
 *
 */
public class Magazine implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The fc id magazine. */
	private String fcIdMagazine;
	
	/** The fc descripcion. */
	private String fcDescripcion;
	
	/** The fi fiRegistros. */
	private int fiRegistros;
	
	/** The fi estatus. */
	private int fiEstatus;

	/**
	 * Instantiates a new magazine.
	 */
	public Magazine() {
	}

	/**
	 * Gets the fc id magazine.
	 *
	 * @return the fc id magazine
	 */
	public String getFcIdMagazine() {
		return this.fcIdMagazine;
	}

	/**
	 * Sets the fc id magazine.
	 *
	 * @param fcIdMagazine the new fc id magazine
	 */
	public void setFcIdMagazine(String fcIdMagazine) {
		this.fcIdMagazine = fcIdMagazine;
	}

	/**
	 * Gets the fc descripcion.
	 *
	 * @return the fc descripcion
	 */
	public String getFcDescripcion() {
		return this.fcDescripcion;
	}

	/**
	 * Sets the fc descripcion.
	 *
	 * @param fcDescripcion the new fc descripcion
	 */
	public void setFcDescripcion(String fcDescripcion) {
		this.fcDescripcion = fcDescripcion;
	}

	/**
	 * Gets the fi estatus.
	 *
	 * @return the fi estatus
	 */
	public int getFiEstatus() {
		return this.fiEstatus;
	}

	/**
	 * Sets the fi estatus.
	 *
	 * @param fiEstatus the new fi estatus
	 */
	public void setFiEstatus(int fiEstatus) {
		this.fiEstatus = fiEstatus;
	}

	/**
	 * Gets the fiRegistros.
	 *
	 * @return the fiRegistros
	 */
	public int getFiRegistros() {
		return this.fiRegistros;
	}

	/**
	 * Sets the fiRegistros.
	 *
	 * @param fiRegistris the new fiRegistros
	 */
	public void setFiRegistros(int fiRegistros) {
		this.fiRegistros = fiRegistros;
	}

	

}