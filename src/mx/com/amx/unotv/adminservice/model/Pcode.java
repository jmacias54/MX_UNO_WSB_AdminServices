package mx.com.amx.unotv.adminservice.model;

import java.io.Serializable;
// TODO: Auto-generated Javadoc

/**
 * The Class Pcode.
 *
 * @author Jesus A. Macias Benitez
 */
public class Pcode implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	
	/** The fc id pcode. */
	private String fcIdPcode;

	
	/** The fc pcode. */
	private String fcPcode;


	/** The fi estatus. */
	private int fiEstatus;

	/**
	 * Instantiates a new pcode.
	 */
	public Pcode() {
	}

	/**
	 * Gets the fc id pcode.
	 *
	 * @return the fc id pcode
	 */
	public String getFcIdPcode() {
		return this.fcIdPcode;
	}

	/**
	 * Sets the fc id pcode.
	 *
	 * @param fcIdPcode the new fc id pcode
	 */
	public void setFcIdPcode(String fcIdPcode) {
		this.fcIdPcode = fcIdPcode;
	}

	/**
	 * Gets the fc pcode.
	 *
	 * @return the fc pcode
	 */
	public String getFcPcode() {
		return this.fcPcode;
	}

	/**
	 * Sets the fc pcode.
	 *
	 * @param fcPcode the new fc pcode
	 */
	public void setFcPcode(String fcPcode) {
		this.fcPcode = fcPcode;
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
