package mx.com.amx.unotv.adminservice.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.com.amx.unotv.adminservice.bo.CatalogsBO;
import mx.com.amx.unotv.adminservice.controller.exception.ControllerException;
import mx.com.amx.unotv.adminservice.model.Categoria;
import mx.com.amx.unotv.adminservice.model.Pcode;
import mx.com.amx.unotv.adminservice.model.response.CatalogResponse;
import mx.com.amx.unotv.adminservice.model.response.CategoriaSeccionResponse;
import mx.com.amx.unotv.adminservice.model.response.UserResponse;


/**
 * The Class CatalogsController.
 */
/**
 * @author Jesus A. Macias Benitez
 *
 */
@Controller
@RequestMapping("catalogs")
public class CatalogsController {

	/** The logger. */
	private static Logger logger = Logger.getLogger(CatalogsController.class);

	@Autowired
	CatalogsBO catalogsBO;
	
	
	/**
	 * Pcode find all Pcode
	 *
	 * @return List<Pcode>
	 * @throws ControllerException
	 */
	@RequestMapping(value = "/get_video_pcode", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public List<Pcode> pcodeFindAll() throws ControllerException {
		logger.info("--- ItemsController  [ WSB ] -----");
		logger.info("--- get_video_pcode -----");

		List<Pcode> lista = null;
		try {
			lista = catalogsBO.pcodeFindAll();

		} catch (Exception e) {
			logger.error(" -- Error  get_video_pcode [WSB-CatalogsController]  [ WSB ] :", e);
			throw new ControllerException(e.getMessage());
		}

		return lista;
	}

	/**
	 * Find all by id seccion.
	 *
	 * @param  String idSeccion 
	 * @return List<CategoriaSeccionResponse>  
	 * @throws ControllerException
	 */
	@RequestMapping(value = "/get_categories/{idSeccion}", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public List<CategoriaSeccionResponse> categoriesFindAllByIdSeccion(@PathVariable String idSeccion)
			throws ControllerException {
		logger.info("--- ItemsController  [ WSB ]-----");
		logger.info("--- categoriesFindAllByIdSeccion  -----");

		List<CategoriaSeccionResponse> lista = null;

		try {
			lista = catalogsBO.categoriesFindAllByIdSeccion(idSeccion);
		} catch (Exception e) {
			logger.error(" -- Error  categoriesFindAllByIdSeccion [CatalogsController] [ WSB ] :", e);
			throw new ControllerException(e.getMessage()); 
		}
		return lista;
	}

	/**
	 * Find all Categoria.
	 *
	 * @return List<Categoria> 
	 * @throws ControllerException
	 */
	@RequestMapping(value = "/get_categories", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public List<Categoria> categoriesFindAll() throws ControllerException {
		logger.info("--- ItemsController  [ WSB ]-----");
		logger.info("--- categoriesFindAll -----");

		List<Categoria> lista = null;
		try {
			lista = catalogsBO.categoriesFindAll();

		} catch (Exception e) {
			logger.error(" -- Error  get_categories [CatalogsController]  [ WSB ] :", e);
			throw new ControllerException(e.getMessage());
		}

		return lista;
	}

	/**
	 * Gets the users.
	 *
	 * @return List<UserResponse>
	 * @throws ControllerException 
	 */
	@RequestMapping(value = "/get_users", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public List<UserResponse> getAllUsers() throws ControllerException {
		logger.info("--- ItemsController  [ WSB ] -----");
		logger.info("--- get_users -----");

		List<UserResponse> lista = null;
		try {

			lista = catalogsBO.getAllUsers();
		} catch (Exception e) {
			logger.error(" -- Error  get_users [CatalogsController]  [ WSB ] :", e);
			throw new ControllerException(e.getMessage());
		}
		return lista;
	}

	/**
	 * Gets the section.
	 *
	 * @return List<CategoriaSeccionResponse>
	 * @throws ControllerException
	 */
	@RequestMapping(value = "/get_section", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public List<CategoriaSeccionResponse> getAllSection() throws ControllerException {
		logger.info("--- ItemsController  [ WSB ]-----");
		logger.info("--- get_section -----");
		List<CategoriaSeccionResponse> lista = null;

		try {
			lista = catalogsBO.getAllSection();
		} catch (Exception e) {
			logger.error(" -- Error  get_section [CatalogsController]  [ WSB ] :", e);
			throw new ControllerException(e.getMessage());
		}

		return lista;
	}

	/**
	 * Gets the tags.
	 *
	 * @return List<CatalogResponse> 
	 * @throws ControllerException
	 */
	@RequestMapping(value = "/get_tags", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public List<CatalogResponse> getAllTags() throws ControllerException {
		logger.info("--- ItemsController  [ WSB ] -----");
		logger.info("--- get_tags -----");

		List<CatalogResponse> lista = null;

		try {
			lista = catalogsBO.getAllTags();
		} catch (Exception e) {
			logger.error(" -- Error  get_tags [CatalogsController]  [ WSB ] :", e);
			throw new ControllerException(e.getMessage());
		}

		return lista;
	}

}
