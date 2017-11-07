package mx.com.amx.unotv.adminservice.model;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Categoria.
 *
 * @author Jesus A. Macias Benitez
 */

public class Categoria implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;


	/** The fc id categoria. */
	private String fcIdCategoria;

	
	/** The fc descripcion. */
	private String fcDescripcion;

	
	/** The fc friendly url. */
	private String fcFriendlyUrl;

	
	/** The fc ruta dfp. */
	private String fcRutaDfp;

	
	/** The fi estatus. */
	private Integer fiEstatus;

	
	/** The fi registros. */
	private String fiRegistros;

	
	/** The fc id seccion. */
	private String fcIdSeccion;

	

	/**
	 * Instantiates a new categoria.
	 */
	public Categoria() {
	}

	/**
	 * Gets the fc id categoria.
	 *
	 * @return the fc id categoria
	 */
	public String getFcIdCategoria() {
		return this.fcIdCategoria;
	}

	/**
	 * Sets the fc id categoria.
	 *
	 * @param fcIdCategoria the new fc id categoria
	 */
	public void setFcIdCategoria(String fcIdCategoria) {
		this.fcIdCategoria = fcIdCategoria;
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
	 * Gets the fc ruta dfp.
	 *
	 * @return the fc ruta dfp
	 */
	public String getFcRutaDfp() {
		return this.fcRutaDfp;
	}

	/**
	 * Sets the fc ruta dfp.
	 *
	 * @param fcRutaDfp the new fc ruta dfp
	 */
	public void setFcRutaDfp(String fcRutaDfp) {
		this.fcRutaDfp = fcRutaDfp;
	}

	/**
	 * Gets the fi estatus.
	 *
	 * @return the fi estatus
	 */
	public Integer getFiEstatus() {
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
	 * Gets the fi registros.
	 *
	 * @return the fi registros
	 */
	public String getFiRegistros() {
		return this.fiRegistros;
	}

	/**
	 * Sets the fi registros.
	 *
	 * @param fiRegistros the new fi registros
	 */
	public void setFiRegistros(String fiRegistros) {
		this.fiRegistros = fiRegistros;
	}

	/**
	 * Gets the fc id seccion.
	 *
	 * @return the fc id seccion
	 */
	public String getFcIdSeccion() {
		return fcIdSeccion;
	}

	/**
	 * Sets the fc id seccion.
	 *
	 * @param fcIdSeccion the new fc id seccion
	 */
	public void setFcIdSeccion(String fcIdSeccion) {
		this.fcIdSeccion = fcIdSeccion;
	}

	@Override
	public String toString() {
		return "Categoria [fcIdCategoria=" + fcIdCategoria + ", fcDescripcion=" + fcDescripcion + ", fcFriendlyUrl="
				+ fcFriendlyUrl + ", fcRutaDfp=" + fcRutaDfp + ", fiEstatus=" + fiEstatus + ", fiRegistros="
				+ fiRegistros + ", fcIdSeccion=" + fcIdSeccion + "]";
	}

	
	
	
}