package mx.com.amx.unotv.adminservice.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;



// TODO: Auto-generated Javadoc
/**
 * The persistent class for the uno_n_nota database table.
 * 
 */
/**
 * @author Jesus A. Macias Benitez
 *
 */
public class NNota implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The date format. */
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	
	/** The fc id contenido. */
	private String fcIdContenido;

	/** The cl galeria. */
	private String clGaleria;

	/** The cl rtf contenido. */
	private String clRtfContenido;

	/** The fc alternative text ooyala. */
	private String fcAlternativeTextOoyala;

	/** The fc content id ooyala. */
	private String fcContentIdOoyala;

	/** The fc descripcion. */
	private String fcDescripcion;

	/** The fc duration ooyala. */
	private String fcDurationOoyala;

	/** The fc escribio. */
	private String fcEscribio;

	/** The fc file size ooyala. */
	private String fcFileSizeOoyala;

	/** The fc friendly url. */
	private String fcFriendlyUrl;

	/** The fc fuente. */
	private String fcFuente;

	/** The fc id categoria. */
	private String fcIdCategoria;

	/** The fc id estatus. */
	private String fcIdEstatus;

	/** The fc id pcode. */
	private String fcIdPcode;

	/** The fc id tipo nota. */
	private String fcIdTipoNota;

	/** The fc id usuario. */
	private String fcIdUsuario;

	/** The fc imagen. */
	private String fcImagen;

	/** The fc keywords. */
	private String fcKeywords;

	/** The fc lugar. */
	private String fcLugar;

	/** The fc player id ooyala. */
	private String fcPlayerIdOoyala;

	/** The fc source ooyala. */
	private String fcSourceOoyala;

	/** The fc titulo. */
	private String fcTitulo;

	/** The fc video youtube. */
	private String fcVideoYoutube;

	/** The fd fecha modificacion. */
	private String fdFechaModificacion = dateFormat.format(new Date());

	/** The fd fecha publicacion. */
	private String fdFechaPublicacion = dateFormat.format(new Date());

	/** The fi ban infinito home. */
	private int fiBanInfinitoHome;

	/** The fi ban msn. */
	private int fiBanMsn;

	/** The fi ban otros. */
	private int fiBanOtros;

	/**
	 * Instantiates a new n nota.
	 */
	public NNota() {
	}

	/**
	 * Gets the fc id contenido.
	 *
	 * @return the fc id contenido
	 */
	public String getFcIdContenido() {
		return this.fcIdContenido;
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
	 * Gets the cl galeria.
	 *
	 * @return the cl galeria
	 */
	public String getClGaleria() {
		return this.clGaleria;
	}

	/**
	 * Sets the cl galeria.
	 *
	 * @param clGaleria the new cl galeria
	 */
	public void setClGaleria(String clGaleria) {
		this.clGaleria = clGaleria;
	}

	/**
	 * Gets the cl rtf contenido.
	 *
	 * @return the cl rtf contenido
	 */
	public String getClRtfContenido() {
		return this.clRtfContenido;
	}

	/**
	 * Sets the cl rtf contenido.
	 *
	 * @param clRtfContenido the new cl rtf contenido
	 */
	public void setClRtfContenido(String clRtfContenido) {
		this.clRtfContenido = clRtfContenido;
	}

	/**
	 * Gets the fc alternative text ooyala.
	 *
	 * @return the fc alternative text ooyala
	 */
	public String getFcAlternativeTextOoyala() {
		return this.fcAlternativeTextOoyala;
	}

	/**
	 * Sets the fc alternative text ooyala.
	 *
	 * @param fcAlternativeTextOoyala the new fc alternative text ooyala
	 */
	public void setFcAlternativeTextOoyala(String fcAlternativeTextOoyala) {
		this.fcAlternativeTextOoyala = fcAlternativeTextOoyala;
	}

	/**
	 * Gets the fc content id ooyala.
	 *
	 * @return the fc content id ooyala
	 */
	public String getFcContentIdOoyala() {
		return this.fcContentIdOoyala;
	}

	/**
	 * Sets the fc content id ooyala.
	 *
	 * @param fcContentIdOoyala the new fc content id ooyala
	 */
	public void setFcContentIdOoyala(String fcContentIdOoyala) {
		this.fcContentIdOoyala = fcContentIdOoyala;
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
	 * Gets the fc duration ooyala.
	 *
	 * @return the fc duration ooyala
	 */
	public String getFcDurationOoyala() {
		return this.fcDurationOoyala;
	}

	/**
	 * Sets the fc duration ooyala.
	 *
	 * @param fcDurationOoyala the new fc duration ooyala
	 */
	public void setFcDurationOoyala(String fcDurationOoyala) {
		this.fcDurationOoyala = fcDurationOoyala;
	}

	/**
	 * Gets the fc escribio.
	 *
	 * @return the fc escribio
	 */
	public String getFcEscribio() {
		return this.fcEscribio;
	}

	/**
	 * Sets the fc escribio.
	 *
	 * @param fcEscribio the new fc escribio
	 */
	public void setFcEscribio(String fcEscribio) {
		this.fcEscribio = fcEscribio;
	}

	/**
	 * Gets the fc file size ooyala.
	 *
	 * @return the fc file size ooyala
	 */
	public String getFcFileSizeOoyala() {
		return this.fcFileSizeOoyala;
	}

	/**
	 * Sets the fc file size ooyala.
	 *
	 * @param fcFileSizeOoyala the new fc file size ooyala
	 */
	public void setFcFileSizeOoyala(String fcFileSizeOoyala) {
		this.fcFileSizeOoyala = fcFileSizeOoyala;
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
	 * Gets the fc fuente.
	 *
	 * @return the fc fuente
	 */
	public String getFcFuente() {
		return this.fcFuente;
	}

	/**
	 * Sets the fc fuente.
	 *
	 * @param fcFuente the new fc fuente
	 */
	public void setFcFuente(String fcFuente) {
		this.fcFuente = fcFuente;
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
	 * Gets the fc imagen.
	 *
	 * @return the fc imagen
	 */
	public String getFcImagen() {
		return this.fcImagen;
	}

	/**
	 * Sets the fc imagen.
	 *
	 * @param fcImagen the new fc imagen
	 */
	public void setFcImagen(String fcImagen) {
		this.fcImagen = fcImagen;
	}

	/**
	 * Gets the fc keywords.
	 *
	 * @return the fc keywords
	 */
	public String getFcKeywords() {
		return this.fcKeywords;
	}

	/**
	 * Sets the fc keywords.
	 *
	 * @param fcKeywords the new fc keywords
	 */
	public void setFcKeywords(String fcKeywords) {
		this.fcKeywords = fcKeywords;
	}

	/**
	 * Gets the fc lugar.
	 *
	 * @return the fc lugar
	 */
	public String getFcLugar() {
		return this.fcLugar;
	}

	/**
	 * Sets the fc lugar.
	 *
	 * @param fcLugar the new fc lugar
	 */
	public void setFcLugar(String fcLugar) {
		this.fcLugar = fcLugar;
	}

	/**
	 * Gets the fc player id ooyala.
	 *
	 * @return the fc player id ooyala
	 */
	public String getFcPlayerIdOoyala() {
		return this.fcPlayerIdOoyala;
	}

	/**
	 * Sets the fc player id ooyala.
	 *
	 * @param fcPlayerIdOoyala the new fc player id ooyala
	 */
	public void setFcPlayerIdOoyala(String fcPlayerIdOoyala) {
		this.fcPlayerIdOoyala = fcPlayerIdOoyala;
	}

	/**
	 * Gets the fc source ooyala.
	 *
	 * @return the fc source ooyala
	 */
	public String getFcSourceOoyala() {
		return this.fcSourceOoyala;
	}

	/**
	 * Sets the fc source ooyala.
	 *
	 * @param fcSourceOoyala the new fc source ooyala
	 */
	public void setFcSourceOoyala(String fcSourceOoyala) {
		this.fcSourceOoyala = fcSourceOoyala;
	}

	/**
	 * Gets the fc titulo.
	 *
	 * @return the fc titulo
	 */
	public String getFcTitulo() {
		return this.fcTitulo;
	}

	/**
	 * Sets the fc titulo.
	 *
	 * @param fcTitulo the new fc titulo
	 */
	public void setFcTitulo(String fcTitulo) {
		this.fcTitulo = fcTitulo;
	}

	/**
	 * Gets the fc video youtube.
	 *
	 * @return the fc video youtube
	 */
	public String getFcVideoYoutube() {
		return this.fcVideoYoutube;
	}

	/**
	 * Sets the fc video youtube.
	 *
	 * @param fcVideoYoutube the new fc video youtube
	 */
	public void setFcVideoYoutube(String fcVideoYoutube) {
		this.fcVideoYoutube = fcVideoYoutube;
	}


	
	
	/**
	 * Gets the fd fecha modificacion.
	 *
	 * @return the fd fecha modificacion
	 */
	public String getFdFechaModificacion() {
		return fdFechaModificacion;
	}

	/**
	 * Sets the fd fecha modificacion.
	 *
	 * @param fdFechaModificacion the new fd fecha modificacion
	 */
	public void setFdFechaModificacion(String fdFechaModificacion) {
		this.fdFechaModificacion = fdFechaModificacion;
	}

	/**
	 * Gets the fd fecha publicacion.
	 *
	 * @return the fd fecha publicacion
	 */
	public String getFdFechaPublicacion() {
		return fdFechaPublicacion;
	}

	/**
	 * Sets the fd fecha publicacion.
	 *
	 * @param fdFechaPublicacion the new fd fecha publicacion
	 */
	public void setFdFechaPublicacion(String fdFechaPublicacion) {
		this.fdFechaPublicacion = fdFechaPublicacion;
	}

	/**
	 * Gets the fi ban infinito home.
	 *
	 * @return the fi ban infinito home
	 */
	public int getFiBanInfinitoHome() {
		return this.fiBanInfinitoHome;
	}

	/**
	 * Sets the fi ban infinito home.
	 *
	 * @param fiBanInfinitoHome the new fi ban infinito home
	 */
	public void setFiBanInfinitoHome(int fiBanInfinitoHome) {
		this.fiBanInfinitoHome = fiBanInfinitoHome;
	}

	/**
	 * Gets the fi ban msn.
	 *
	 * @return the fi ban msn
	 */
	public int getFiBanMsn() {
		return this.fiBanMsn;
	}

	/**
	 * Sets the fi ban msn.
	 *
	 * @param fiBanMsn the new fi ban msn
	 */
	public void setFiBanMsn(int fiBanMsn) {
		this.fiBanMsn = fiBanMsn;
	}

	/**
	 * Gets the fi ban otros.
	 *
	 * @return the fi ban otros
	 */
	public int getFiBanOtros() {
		return this.fiBanOtros;
	}

	/**
	 * Sets the fi ban otros.
	 *
	 * @param fiBanOtros the new fi ban otros
	 */
	public void setFiBanOtros(int fiBanOtros) {
		this.fiBanOtros = fiBanOtros;
	}

}