/**
 * 
 */
package mx.com.amx.unotv.adminservice.bo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import mx.com.amx.unotv.adminservice.bo.exception.TagBOException;


/**
 * @author Jesus A. Macias Benitez
 *
 */
public class TagBO {

	private static Logger logger = Logger.getLogger(TagBO.class);
	

	@Autowired
	INotaTagBO iNotaTagBO;
	@Autowired
	IHNotaTagBO iHNotaTagBO;

	/*
	 * Borra informacion en las tablas intermedias uno_i_nota_tags ,
	 * uno_i_h_nota_tags
	 * 
	 */
	public void deleteIntermediateTags(String idContenido) {
		logger.debug(" --- deleteIntermediateTags [ TagBO ] --- ");
		logger.debug(" --- Se Elimina  Informacion de las tablas I_NOTA_TAG , I_H_NOTA_TAG --- ");

		try {
			iNotaTagBO.delete(idContenido);
			iHNotaTagBO.delete(idContenido);
		} catch (Exception e) {
			logger.error("--- Exception deleteIntermediateTags  [ TagBO ]: ", e);
			new TagBOException(e.getMessage());
		}

	}

	/*
	 * Inserta tags uno por uno en las tablas intermedias uno_i_nota_tags ,
	 * uno_i_h_nota_tags
	 */
	public void insertIntermediateTags(String idContenido, String tags) {
		logger.debug(" ---insertIntermediateTags [ TagBO ] --- ");
		logger.debug(" --- Se Inserta Informacion en las  tablas I_NOTA_TAG , I_H_NOTA_TAG  --- ");

		int res = 0;
		String[] output = null;

		if (tags != null && !tags.equals("")) {

			try {
				output = tags.split(",");
				for (int i = 0; i < output.length; i++) {

					res = iNotaTagBO.insert(idContenido, output[i]);
					if (res > 0) {

						iHNotaTagBO.insert(idContenido, output[i]);
					}

				}
			} catch (Exception e) {
				logger.error("--- Exception insertIntermediateTags  [ TagBO ]: ", e);
				new TagBOException(e.getMessage());
			}
		}

	}

}
