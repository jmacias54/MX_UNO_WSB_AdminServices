package mx.com.amx.unotv.adminservice.model;

import java.io.Serializable;



// TODO: Auto-generated Javadoc
/**
 * The persistent class for the uno_c_tags database table.
 * 
 */
/**
 * @author Jesus A. Macias Benitez
 *
 */
public class Tag implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	
	/** The fc id tag. */
	private String fcIdTag;

	/** The fc descripcion. */
	private String fcDescripcion;

	/** The fi estatus. */
	private int fiEstatus;
	
	private String fcDfp;

	/**
	 * Instantiates a new tag.
	 */
	public Tag() {
	}

	/**
	 * Gets the fc id tag.
	 *
	 * @return the fc id tag
	 */
	public String getFcIdTag() {
		return this.fcIdTag;
	}

	/**
	 * Sets the fc id tag.
	 *
	 * @param fcIdTag the new fc id tag
	 */
	public void setFcIdTag(String fcIdTag) {
		this.fcIdTag = fcIdTag;
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

	public String getFcDfp() {
		return fcDfp;
	}

	public void setFcDfp(String fcDfp) {
		this.fcDfp = fcDfp;
	}

	

}