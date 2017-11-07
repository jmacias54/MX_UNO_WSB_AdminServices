package mx.com.amx.unotv.adminservice.model;

import java.io.Serializable;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the uno_c_tipo_nota database table.
 * 
 */
/**
 * @author Jesus A. Macias Benitez
 *
 */
public class TipoNota implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The fc id tipo nota. */
	private String fcIdTipoNota;

	/** The fc descripcion. */
	private String fcDescripcion;

	/** The fi estatus. */
	private int fiEstatus;

	/**
	 * Instantiates a new tipo nota.
	 */
	public TipoNota() {
	}

	/**
	 * Gets the fc id tipo nota.
	 *
	 * @return the fc id tipo nota
	 */
	public String getFcIdTipoNota() {
		return this.fcIdTipoNota;
	}

	/**
	 * Sets the fc id tipo nota.
	 *
	 * @param fcIdTipoNota the new fc id tipo nota
	 */
	public void setFcIdTipoNota(String fcIdTipoNota) {
		this.fcIdTipoNota = fcIdTipoNota;
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