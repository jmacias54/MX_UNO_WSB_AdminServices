package mx.com.amx.unotv.adminservice.model;

import java.io.Serializable;

import java.sql.Timestamp;



// TODO: Auto-generated Javadoc
/**
 * The persistent class for the uno_c_usuarios database table.
 * 
 */
/**
 * @author Jesus A. Macias Benitez
 *
 */
public class Usuario implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;


	/** The fc id usuario. */
	private String fcIdUsuario;


	/** The fc nombre. */
	private String fcNombre;

	
	/** The fd fecha registro. */
	private Timestamp fdFechaRegistro;


	/** The fi estatus. */
	private int fiEstatus;

	

	/**
	 * Instantiates a new usuario.
	 */
	public Usuario() {
	}

	/**
	 * Gets the fc id usuario.
	 *
	 * @return the fc id usuario
	 */
	public String getFcIdUsuario() {
		return this.fcIdUsuario;
	}

	/**
	 * Sets the fc id usuario.
	 *
	 * @param fcIdUsuario the new fc id usuario
	 */
	public void setFcIdUsuario(String fcIdUsuario) {
		this.fcIdUsuario = fcIdUsuario;
	}

	/**
	 * Gets the fc nombre.
	 *
	 * @return the fc nombre
	 */
	public String getFcNombre() {
		return this.fcNombre;
	}

	/**
	 * Sets the fc nombre.
	 *
	 * @param fcNombre the new fc nombre
	 */
	public void setFcNombre(String fcNombre) {
		this.fcNombre = fcNombre;
	}

	/**
	 * Gets the fd fecha registro.
	 *
	 * @return the fd fecha registro
	 */
	public Timestamp getFdFechaRegistro() {
		return this.fdFechaRegistro;
	}

	/**
	 * Sets the fd fecha registro.
	 *
	 * @param fdFechaRegistro the new fd fecha registro
	 */
	public void setFdFechaRegistro(Timestamp fdFechaRegistro) {
		this.fdFechaRegistro = fdFechaRegistro;
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