/**
 * 
 */
package mx.com.amx.unotv.adminservice.bo;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import mx.com.amx.unotv.adminservice.bo.exception.IHNotaTagBOException;
import mx.com.amx.unotv.adminservice.model.IHNotaTag;
import mx.com.amx.unotv.adminservice.model.Tag;
import mx.com.amx.unotv.adminservice.ws.IHNotaTagCallWS;

/**
 * @author Jesus A. Macias Benitez
 *
 */
public class IHNotaTagBO {
	
	
	
	private static Logger logger = Logger.getLogger(IHNotaTagBO.class);

	@Autowired
	IHNotaTagCallWS iHNotaTagCallWS;
	
	
	public void delete(String idContenido) {
		logger.debug(" --- delete [ IHNotaTagBO ] --- ");

		try {
			iHNotaTagCallWS.delete(idContenido);
			
		} catch (Exception e) {
			logger.error("Exception delete  [ IHNotaTagBO ]: ", e);
			new IHNotaTagBOException(e.getMessage());
		}

	}
	
	
	public int insert(String idContenido, String idTag) {
		logger.debug(" --- insert [ IHNotaTagBO ] --- ");

		int res = 0;
		try {
			
			IHNotaTag iHNotaTag = new IHNotaTag();
			iHNotaTag.setFcIdContenido(idContenido);
			iHNotaTag.setFcIdTag(idTag);
			res = iHNotaTagCallWS.insert(iHNotaTag);
			
			
		} catch (Exception e) {
			logger.error("Exception insert  [ IHNotaTagBO ]: ", e);
			new IHNotaTagBOException(e.getMessage());
		}
		
		return res;

	}
	
	public List<Tag> getByIdContenido(String idContenido){
		logger.debug(" --- getByIdContenido [ IHNotaTagBO ] --- ");

		 List<Tag> lista = null;
		try {
			
			
			lista = iHNotaTagCallWS.getByIdContenido(idContenido);
			
			
		} catch (Exception e) {
			logger.error("Exception getByIdContenido  [ IHNotaTagBO ]: ", e);
			new IHNotaTagBOException(e.getMessage());
		}
		
		return lista;

	}

}
