/**
 * 
 */
package mx.com.amx.unotv.adminservice.bo;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import mx.com.amx.unotv.adminservice.bo.exception.INotaTagBOException;
import mx.com.amx.unotv.adminservice.model.INotaTag;
import mx.com.amx.unotv.adminservice.model.Tag;
import mx.com.amx.unotv.adminservice.ws.INotaTagCallWS;

/**
 * @author Jesus A. Macias Benitez
 *
 */
public class INotaTagBO {
	
	private static Logger logger = Logger.getLogger(INotaTagBO.class);
	
	@Autowired
	INotaTagCallWS iNotaTagCallWS;
	
	
	public void delete(String idContenido) {
		logger.debug(" --- delete [ INotaTagBO ] --- ");

		try {
			iNotaTagCallWS.delete(idContenido);
			
		} catch (Exception e) {
			logger.error("Exception delete  [ INotaTagBO ]: ", e);
			new INotaTagBOException(e.getMessage());
		}

	}
	
	
	public int insert(String idContenido, String idTag) {
		logger.debug(" --- insert [ INotaTagBO ] --- ");
		int res = 0;
		try {
			
			INotaTag iNotaTag = new INotaTag();
			iNotaTag.setFcIdContenido(idContenido);
			iNotaTag.setFcIdTag(idTag);
			res = iNotaTagCallWS.insert(iNotaTag);
			
			
		} catch (Exception e) {
			logger.error("Exception insert  [ INotaTagBO ]: ", e);
			new INotaTagBOException(e.getMessage());
		}

		return res;
	}
	
	public List<Tag> getByIdContenido(String idContenido){
		logger.debug(" --- getByIdContenido [ INotaTagBO ] --- ");

		 List<Tag> lista = null;
		try {
			
			
			lista = iNotaTagCallWS.getByIdContenido(idContenido);
			
			
		} catch (Exception e) {
			logger.error("Exception getByIdContenido  [ INotaTagBO ]: ", e);
			new INotaTagBOException(e.getMessage());
		}
		
		return lista;

	}


}
