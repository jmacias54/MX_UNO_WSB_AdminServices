/**
 * 
 */
package mx.com.amx.unotv.adminservice.model;

/**
 * @author Jesus A. Macias Benitez
 *
 */
public class main_image {
	
	private String  src;
	private String  pie;
	private String  fuente;
	
	private Cordenadas cordenadas_facebook;
	private Cordenadas cordenadas_miniatura;
	
	
	
	/**
	 * 
	 */
	public main_image() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @param src
	 * @param pie
	 * @param fuente
	 * @param cordenadas_facebook
	 * @param cordenadas_miniatura
	 */
	public main_image(String src, String pie, String fuente, Cordenadas cordenadas_facebook,
			Cordenadas cordenadas_miniatura) {
		super();
		this.src = src;
		this.pie = pie;
		this.fuente = fuente;
		this.cordenadas_facebook = cordenadas_facebook;
		this.cordenadas_miniatura = cordenadas_miniatura;
	}


	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getPie() {
		return pie;
	}
	public void setPie(String pie) {
		this.pie = pie;
	}
	public String getFuente() {
		return fuente;
	}
	public void setFuente(String fuente) {
		this.fuente = fuente;
	}


	public Cordenadas getCordenadas_facebook() {
		return cordenadas_facebook;
	}


	public void setCordenadas_facebook(Cordenadas cordenadas_facebook) {
		this.cordenadas_facebook = cordenadas_facebook;
	}


	public Cordenadas getCordenadas_miniatura() {
		return cordenadas_miniatura;
	}


	public void setCordenadas_miniatura(Cordenadas cordenadas_miniatura) {
		this.cordenadas_miniatura = cordenadas_miniatura;
	}

	
	
	

}
