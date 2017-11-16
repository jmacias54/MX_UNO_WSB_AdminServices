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

	private String fdFechaModificacion = dateFormat.format(new Date());

	/** The fd fecha publicacion. */
	private String fdFechaPublicacion = dateFormat.format(new Date());

	private String fcIdContenido;
	private String clGaleria;
	private String clRtfContenido;
	private String fcAlternativeTextOoyala;
	private String fcContentIdOoyala;
	private String fcDescripcion;
	private String fcDurationOoyala;
	private String fcEscribio;
	private String fcFileSizeOoyala;
	private String fcFriendlyUrl;
	private String fcFuente;
	private String fcFuenteImagen;
	private String fcIdCategoria;
	private String fcIdEstatus;
	private String fcIdPcode;
	private String fcIdTipoNota;
	private String fcIdUsuario;
	private String fcImagen;
	private String fcKeywords;
	private String fcLugar;
	private String fcPieImagen;
	private String fcPlayerIdOoyala;
	private String fcSourceOoyala;
	private String fcTitulo;
	private String fcVideoYoutube;
	private Integer fiBanInfinitoHome;
	private Integer fiBanMsn;
	private Integer fiBanOtros;

	private String fcCoordenadasFb;
	private String fcCoordenadasMiniatura;

	/**
	 * Instantiates a new n nota.
	 */
	public NNota() {
	}

	public String getFcCoordenadasFb() {
		return fcCoordenadasFb;
	}

	public void setFcCoordenadasFb(String fcCoordenadasFb) {
		this.fcCoordenadasFb = fcCoordenadasFb;
	}

	public String getFcCoordenadasMiniatura() {
		return fcCoordenadasMiniatura;
	}

	public void setFcCoordenadasMiniatura(String fcCoordenadasMiniatura) {
		this.fcCoordenadasMiniatura = fcCoordenadasMiniatura;
	}


	public String getFdFechaModificacion() {
		return fdFechaModificacion;
	}

	public void setFdFechaModificacion(String fdFechaModificacion) {
		this.fdFechaModificacion = fdFechaModificacion;
	}

	public String getFdFechaPublicacion() {
		return fdFechaPublicacion;
	}

	public void setFdFechaPublicacion(String fdFechaPublicacion) {
		this.fdFechaPublicacion = fdFechaPublicacion;
	}

	public String getFcIdContenido() {
		return fcIdContenido;
	}

	public void setFcIdContenido(String fcIdContenido) {
		this.fcIdContenido = fcIdContenido;
	}

	public String getClGaleria() {
		return clGaleria;
	}

	public void setClGaleria(String clGaleria) {
		this.clGaleria = clGaleria;
	}

	public String getClRtfContenido() {
		return clRtfContenido;
	}

	public void setClRtfContenido(String clRtfContenido) {
		this.clRtfContenido = clRtfContenido;
	}

	public String getFcAlternativeTextOoyala() {
		return fcAlternativeTextOoyala;
	}

	public void setFcAlternativeTextOoyala(String fcAlternativeTextOoyala) {
		this.fcAlternativeTextOoyala = fcAlternativeTextOoyala;
	}

	public String getFcContentIdOoyala() {
		return fcContentIdOoyala;
	}

	public void setFcContentIdOoyala(String fcContentIdOoyala) {
		this.fcContentIdOoyala = fcContentIdOoyala;
	}

	public String getFcDescripcion() {
		return fcDescripcion;
	}

	public void setFcDescripcion(String fcDescripcion) {
		this.fcDescripcion = fcDescripcion;
	}

	public String getFcDurationOoyala() {
		return fcDurationOoyala;
	}

	public void setFcDurationOoyala(String fcDurationOoyala) {
		this.fcDurationOoyala = fcDurationOoyala;
	}

	public String getFcEscribio() {
		return fcEscribio;
	}

	public void setFcEscribio(String fcEscribio) {
		this.fcEscribio = fcEscribio;
	}

	public String getFcFileSizeOoyala() {
		return fcFileSizeOoyala;
	}

	public void setFcFileSizeOoyala(String fcFileSizeOoyala) {
		this.fcFileSizeOoyala = fcFileSizeOoyala;
	}

	public String getFcFriendlyUrl() {
		return fcFriendlyUrl;
	}

	public void setFcFriendlyUrl(String fcFriendlyUrl) {
		this.fcFriendlyUrl = fcFriendlyUrl;
	}

	public String getFcFuente() {
		return fcFuente;
	}

	public void setFcFuente(String fcFuente) {
		this.fcFuente = fcFuente;
	}

	public String getFcFuenteImagen() {
		return fcFuenteImagen;
	}

	public void setFcFuenteImagen(String fcFuenteImagen) {
		this.fcFuenteImagen = fcFuenteImagen;
	}

	public String getFcIdCategoria() {
		return fcIdCategoria;
	}

	public void setFcIdCategoria(String fcIdCategoria) {
		this.fcIdCategoria = fcIdCategoria;
	}

	public String getFcIdEstatus() {
		return fcIdEstatus;
	}

	public void setFcIdEstatus(String fcIdEstatus) {
		this.fcIdEstatus = fcIdEstatus;
	}

	public String getFcIdPcode() {
		return fcIdPcode;
	}

	public void setFcIdPcode(String fcIdPcode) {
		this.fcIdPcode = fcIdPcode;
	}

	public String getFcIdTipoNota() {
		return fcIdTipoNota;
	}

	public void setFcIdTipoNota(String fcIdTipoNota) {
		this.fcIdTipoNota = fcIdTipoNota;
	}

	public String getFcIdUsuario() {
		return fcIdUsuario;
	}

	public void setFcIdUsuario(String fcIdUsuario) {
		this.fcIdUsuario = fcIdUsuario;
	}

	public String getFcImagen() {
		return fcImagen;
	}

	public void setFcImagen(String fcImagen) {
		this.fcImagen = fcImagen;
	}

	public String getFcKeywords() {
		return fcKeywords;
	}

	public void setFcKeywords(String fcKeywords) {
		this.fcKeywords = fcKeywords;
	}

	public String getFcLugar() {
		return fcLugar;
	}

	public void setFcLugar(String fcLugar) {
		this.fcLugar = fcLugar;
	}

	public String getFcPieImagen() {
		return fcPieImagen;
	}

	public void setFcPieImagen(String fcPieImagen) {
		this.fcPieImagen = fcPieImagen;
	}

	public String getFcPlayerIdOoyala() {
		return fcPlayerIdOoyala;
	}

	public void setFcPlayerIdOoyala(String fcPlayerIdOoyala) {
		this.fcPlayerIdOoyala = fcPlayerIdOoyala;
	}

	public String getFcSourceOoyala() {
		return fcSourceOoyala;
	}

	public void setFcSourceOoyala(String fcSourceOoyala) {
		this.fcSourceOoyala = fcSourceOoyala;
	}

	public String getFcTitulo() {
		return fcTitulo;
	}

	public void setFcTitulo(String fcTitulo) {
		this.fcTitulo = fcTitulo;
	}

	public String getFcVideoYoutube() {
		return fcVideoYoutube;
	}

	public void setFcVideoYoutube(String fcVideoYoutube) {
		this.fcVideoYoutube = fcVideoYoutube;
	}

	public Integer getFiBanInfinitoHome() {
		return fiBanInfinitoHome;
	}

	public void setFiBanInfinitoHome(Integer fiBanInfinitoHome) {
		this.fiBanInfinitoHome = fiBanInfinitoHome;
	}

	public Integer getFiBanMsn() {
		return fiBanMsn;
	}

	public void setFiBanMsn(Integer fiBanMsn) {
		this.fiBanMsn = fiBanMsn;
	}

	public Integer getFiBanOtros() {
		return fiBanOtros;
	}

	public void setFiBanOtros(Integer fiBanOtros) {
		this.fiBanOtros = fiBanOtros;
	}

}