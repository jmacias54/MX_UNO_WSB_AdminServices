/**
 * 
 */
package mx.com.amx.unotv.adminservice.bo;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import mx.com.amx.unotv.adminservice.bo.exception.CatalogsBOException;
import mx.com.amx.unotv.adminservice.model.Categoria;
import mx.com.amx.unotv.adminservice.model.Pcode;
import mx.com.amx.unotv.adminservice.model.response.CatalogResponse;
import mx.com.amx.unotv.adminservice.model.response.CategoriaSeccionResponse;
import mx.com.amx.unotv.adminservice.model.response.UserResponse;
import mx.com.amx.unotv.adminservice.ws.CatalogsCallWS;

/**
 * @author Jesus A. Macias Benitez
 *
 */
public class CatalogsBO {
	private static Logger logger = Logger.getLogger(CatalogsBO.class);
	
	@Autowired
	CatalogsCallWS catalogsCallWS;

	public List<Pcode> pcodeFindAll() throws CatalogsBOException {
		logger.error("--- get_video_pcode ---  [ CatalogsBO ]: ");
		List<Pcode> lista = null;

		try {
			lista = catalogsCallWS.pcodeFindAll();
		} catch (Exception e) {
			logger.error("Exception pcodeFindAll  [ CatalogsBO ]: ", e);
			throw new CatalogsBOException(e.getMessage());
		}
		return lista;

	}

	public List<CategoriaSeccionResponse> categoriesFindAllByIdSeccion(String idSeccion) throws CatalogsBOException {
		List<CategoriaSeccionResponse> lista = null;
		

		try {
			lista = catalogsCallWS.categoriesFindAllByIdSeccion(idSeccion);
		} catch (Exception e) {
			logger.error("Exception categoriesFindAllByIdSeccion  [ CatalogsBO ]: ", e);
			throw new CatalogsBOException(e.getMessage());
		}

		return lista;
	}

	public List<Categoria> categoriesFindAll() throws CatalogsBOException {
		List<Categoria> lista = null;

		try {
			lista = catalogsCallWS.categoriesFindAll();
		} catch (Exception e) {
			logger.error("Exception categoriesFindAll  [ CatalogsBO ]: ", e);
			throw new CatalogsBOException(e.getMessage());
		}
		return lista;
	}

	public List<UserResponse> getAllUsers() throws CatalogsBOException {
		List<UserResponse> lista = null;

		
		try {
			lista = catalogsCallWS.getAllUsers();
		} catch (Exception e) {
			logger.error("Exception getAllUsers  [ CatalogsBO ]: ", e);
			throw new CatalogsBOException(e.getMessage());
		}
		return lista;

	}

	public List<CategoriaSeccionResponse> getAllSection() throws CatalogsBOException {
		List<CategoriaSeccionResponse> lista = null;

		try {
			lista = catalogsCallWS.getAllSection();
		} catch (Exception e) {
			logger.error("Exception getAllSection  [ CatalogsBO ]: ", e);
			throw new CatalogsBOException(e.getMessage());
		}
		return lista;

	}

	public List<CatalogResponse> getAllTags() throws CatalogsBOException {
		List<CatalogResponse> lista = null;

		try {
			lista = catalogsCallWS.getAllTags();
		} catch (Exception e) {
			logger.error("Exception getAllSection  [ CatalogsBO ]: ", e);
			throw new CatalogsBOException(e.getMessage());
		}
		return lista;
	}

}
