/**
 * 
 */
package mx.com.amx.unotv.adminservice.bo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import mx.com.amx.unotv.adminservice.bo.exception.TagBOException;
import mx.com.amx.unotv.adminservice.model.IHNotaTag;
import mx.com.amx.unotv.adminservice.model.INotaTag;
import mx.com.amx.unotv.adminservice.ws.IHNotaTagCallWS;
import mx.com.amx.unotv.adminservice.ws.INotaTagCallWS;

/**
 * @author Jesus A. Macias Benitez
 *
 */
public class TagBO {

	private static Logger logger = Logger.getLogger(TagBO.class);

	@Autowired
	INotaTagCallWS iNotaTagCallWS;
	@Autowired
	IHNotaTagCallWS iHNotaTagCallWS;

	/*
	 * Borra informacion en las tablas intermedias uno_i_nota_tags ,
	 * uno_i_h_nota_tags
	 * 
	 */
	public void deleteIntermediateTags(String idContenido) {

		try {
			iNotaTagCallWS.delete(idContenido);
			iHNotaTagCallWS.delete(idContenido);
		} catch (Exception e) {
			logger.error("Exception deleteIntermediateTags  [ TagBO ]: ", e);
			new TagBOException(e.getMessage());
		}

	}

	/*
	 * Inserta tags uno por uno en las tablas intermedias uno_i_nota_tags ,
	 * uno_i_h_nota_tags
	 */
	public void insertIntermediateTags(String idContenido, String tags) {

		int res = 0;
		String[] output = null;

		if (tags != null && !tags.equals("")) {

			try {
				output = tags.split(",");
				for (int i = 0; i < output.length; i++) {

					INotaTag iNotaTag = new INotaTag();
					iNotaTag.setFcIdContenido(idContenido);
					iNotaTag.setFcIdTag(output[i]);
					res = iNotaTagCallWS.insert(iNotaTag);
					if (res > 0) {
						IHNotaTag iHNotaTag = new IHNotaTag();
						iHNotaTag.setFcIdContenido(idContenido);
						iHNotaTag.setFcIdTag(output[i]);
						res = iHNotaTagCallWS.insert(iHNotaTag);
					}

				}
			} catch (Exception e) {
				logger.error("Exception insertIntermediateTags  [ TagBO ]: ", e);
				new TagBOException(e.getMessage());
			}
		}

	}

}
