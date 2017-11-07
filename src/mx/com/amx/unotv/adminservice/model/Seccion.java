package mx.com.amx.unotv.adminservice.model;

import java.io.Serializable;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the uno_c_seccion database table.
 * 
 */
/**
 * @author Jesus A. Macias Benitez
 *
 */
public class Seccion implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The fc id seccion. */
	private String fcIdSeccion;
	
	/** The fc descripcion. */
	private String fcDescripcion;
	
	/** The fc friendly url. */
	private String fcFriendlyUrl;
	
	/** The fi estatus. */
	private int fiEstatus;

	/**
	 * Instantiates a new seccion.
	 */
	public Seccion() {
	}

	/**
	 * Gets the fc id seccion.
	 *
	 * @return the fc id seccion
	 */
	public String getFcIdSeccion() {
		return this.fcIdSeccion;
	}

	/**
	 * Sets the fc id seccion.
	 *
	 * @param fcIdSeccion the new fc id seccion
	 */
	public void setFcIdSeccion(String fcIdSeccion) {
		this.fcIdSeccion = fcIdSeccion;
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
	 * Gets the fc friendly url.
	 *
	 * @return the fc friendly url
	 */
	public String getFcFriendlyUrl() {
		return this.fcFriendlyUrl;
	}

	/**
	 * Sets the fc friendly url.
	 *
	 * @param fcFriendlyUrl the new fc friendly url
	 */
	public void setFcFriendlyUrl(String fcFriendlyUrl) {
		this.fcFriendlyUrl = fcFriendlyUrl;
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
	



}