/**
 * 
 */
package mx.com.amx.unotv.adminservice.model;

/**
 * @author Jesus A. Macias Benitez
 *
 */
public class IHNotaUsuario {

	private String fcIdUsuario;
	private String fcIdContenido;
	private String fdFechaModificacion;
	private String fcIdEstatus;



	/**
	 * 
	 */
	public IHNotaUsuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public String getFcIdEstatus() {
		return fcIdEstatus;
	}



	public void setFcIdEstatus(String fcIdEstatus) {
		this.fcIdEstatus = fcIdEstatus;
	}



	public String getFcIdUsuario() {
		return fcIdUsuario;
	}

	public void setFcIdUsuario(String fcIdUsuario) {
		this.fcIdUsuario = fcIdUsuario;
	}

	public String getFcIdContenido() {
		return fcIdContenido;
	}

	public void setFcIdContenido(String fcIdContenido) {
		this.fcIdContenido = fcIdContenido;
	}

	public String getFdFechaModificacion() {
		return fdFechaModificacion;
	}

	public void setFdFechaModificacion(String fdFechaModificacion) {
		this.fdFechaModificacion = fdFechaModificacion;
	}

}
