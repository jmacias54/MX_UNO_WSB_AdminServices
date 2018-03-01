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

	private String fcIdCategoria;
	private String fcIdSeccion;
	private String fcDescripcion;
	private String fcFriendlyUrl;
	private Integer fcRegistros;
	private Integer fcEstatus;
	private Integer fcidApps;
	private String fcRutaDfpApp;
	private String fcImagenPodcast;

	/**
	 * Instantiates a new categoria.
	 */
	public Categoria() {
	}

	public Integer getFcRegistros() {
		return fcRegistros;
	}

	public void setFcRegistros(Integer fcRegistros) {
		if(fcRegistros  == null) {
			fcEstatus =0;
		}
		this.fcRegistros = fcRegistros;
	}

	public Integer getFcEstatus() {
		return fcEstatus;
	}

	public void setFcEstatus(Integer fcEstatus) {
		if(fcEstatus  == null) {
			fcEstatus =0;
		}
		this.fcEstatus = fcEstatus;
	}

	public Integer getFcidApps() {
		return fcidApps;
	}

	public void setFcidApps(Integer fcidApps) {
		if(fcidApps  == null) {
			fcidApps =0;
		}
		this.fcidApps = fcidApps;
	}

	public String getFcRutaDfpApp() {
		return fcRutaDfpApp;
	}

	public void setFcRutaDfpApp(String fcRutaDfpApp) {
		this.fcRutaDfpApp = fcRutaDfpApp;
	}

	public String getFcImagenPodcast() {
		return fcImagenPodcast;
	}

	public void setFcImagenPodcast(String fcImagenPodcast) {
		this.fcImagenPodcast = fcImagenPodcast;
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
	 * @param fcIdCategoria
	 *            the new fc id categoria
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
	 * @param fcDescripcion
	 *            the new fc descripcion
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
	 * @param fcFriendlyUrl
	 *            the new fc friendly url
	 */
	public void setFcFriendlyUrl(String fcFriendlyUrl) {
		this.fcFriendlyUrl = fcFriendlyUrl;
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
	 * @param fcIdSeccion
	 *            the new fc id seccion
	 */
	public void setFcIdSeccion(String fcIdSeccion) {
		this.fcIdSeccion = fcIdSeccion;
	}

	@Override
	public String toString() {
		return "Categoria [fcIdCategoria=" + fcIdCategoria + ", fcIdSeccion=" + fcIdSeccion + ", fcDescripcion="
				+ fcDescripcion + ", fcFriendlyUrl=" + fcFriendlyUrl + ", fcRegistros=" + fcRegistros + ", fcEstatus="
				+ fcEstatus + ", fcidApps=" + fcidApps + ", fcRutaDfpApp=" + fcRutaDfpApp + ", fcImagenPodcast="
				+ fcImagenPodcast + "]";
	}

}