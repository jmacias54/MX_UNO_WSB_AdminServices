/**
 * 
 */
package mx.com.amx.unotv.adminservice.bo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import mx.com.amx.unotv.adminservice.bo.exception.IHNotaUsuarioBOException;
import mx.com.amx.unotv.adminservice.model.IHNotaUsuario;
import mx.com.amx.unotv.adminservice.ws.IHNotaUsuarioCallWS;

/**
 * @author Jesus A. Macias Benitez
 *
 */
public class IHNotaUsuarioBO {
	private static Logger logger = Logger.getLogger(IHNotaUsuarioBO.class);
	
	@Autowired
	IHNotaUsuarioCallWS iHNotaUsuarioCallWS;
	
	
	public IHNotaUsuario findByIdContenido(String idContenido ) throws IHNotaUsuarioBOException {
		
		IHNotaUsuario obj = null;
		
		try {
			obj = iHNotaUsuarioCallWS.findByIdContenido(idContenido);
		}catch (Exception e) {
			logger.error(" --- Exception findByIdContenido  [ IHNotaUsuarioBO  ]: ", e);
			throw new IHNotaUsuarioBOException(e.getMessage());
		}
		
		return obj;
		
	}

}
