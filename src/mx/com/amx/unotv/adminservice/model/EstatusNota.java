package mx.com.amx.unotv.adminservice.model;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class EstatusNota.
 *
 * @author Jesus A. Macias Benitez
 */

/**
 * The persistent class for the uno_c_estatus_nota database table.
 * 
 */

public class EstatusNota implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The fc id estatus. */
	private String fcIdEstatus;

	/** The fc descripcion. */
	private String fcDescripcion;

	/** The fi estatus. */
	private int fiEstatus;

	/**
	 * Instantiates a new estatus nota.
	 */
	public EstatusNota() {
	}

	/**
	 * Gets the fc id estatus.
	 *
	 * @return the fc id estatus
	 */
	public String getFcIdEstatus() {
		return this.fcIdEstatus;
	}

	/**
	 * Sets the fc id estatus.
	 *
	 * @param fcIdEstatus the new fc id estatus
	 */
	public void setFcIdEstatus(String fcIdEstatus) {
		this.fcIdEstatus = fcIdEstatus;
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

}