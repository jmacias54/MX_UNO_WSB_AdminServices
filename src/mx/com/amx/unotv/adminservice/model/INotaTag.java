package mx.com.amx.unotv.adminservice.model;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the uno_i_nota_tags database table.
 * 
 */
/**
 * @author Jesus A. Macias Benitez
 *
 */
public class INotaTag implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The fc id contenido. */
	private String fcIdContenido;

	/** The fc id tag. */
	private String fcIdTag;

	/**
	 * Instantiates a new i nota tag.
	 */
	public INotaTag() {
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

	/**
	 * Gets the fc id tag.
	 *
	 * @return the fc id tag
	 */
	public String getFcIdTag() {
		return fcIdTag;
	}

	/**
	 * Sets the fc id tag.
	 *
	 * @param fcIdTag the new fc id tag
	 */
	public void setFcIdTag(String fcIdTag) {
		this.fcIdTag = fcIdTag;
	}

}