package mx.com.amx.unotv.adminservice.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.com.amx.unotv.adminservice.bo.DetailBO;
import mx.com.amx.unotv.adminservice.controller.exception.ControllerException;
import mx.com.amx.unotv.adminservice.model.HNota;
import mx.com.amx.unotv.adminservice.model.response.Item;

// TODO: Auto-generated Javadoc
/**
 * The Class DetailController.
 */
/**
 * @author Jesus A. Macias Benitez
 *
 */
@Controller
@RequestMapping("detail")
public class DetailController {

	/** The logger. */
	private static Logger logger = Logger.getLogger(DetailController.class);

	/** The nota BO. */
	@Autowired
	DetailBO detailBO;

	/**
	 * Inserta la nota en las tablas NNota y HNota
	 *
	 * @param Item
	 * @return int
	 * @throws ControllerException 
	 */
	@RequestMapping(value = "/save_item", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public int saveItem(@RequestBody Item item) throws ControllerException {
		logger.info("--- ItemsController-----");
		logger.info("--- saveItem -----");

		
		try {

			return detailBO.saveItem(item);

		} catch (Exception e) {
			logger.error(" -- Error  saveNota [ItemsController]:", e);
			throw new ControllerException(e.getMessage());
		}

		
	}
	
	
	@RequestMapping(value = "/expire_item", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public int expireItem(@RequestBody Item item) throws ControllerException {
		logger.info("--- ItemsController-----");
		logger.info("--- expire_item -----");

	
		try {

			return detailBO.expireItem(item);

		} catch (Exception e) {
			logger.error(" -- Error  expire_item [ItemsController]:", e);
			throw new ControllerException(e.getMessage());
		}

	}
	
	@RequestMapping(value = "/review_item", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public int reviewItem(@RequestBody Item item) throws ControllerException {
		logger.info("--- ItemsController-----");
		logger.info("--- review_item -----");


		try {

			return detailBO.reviewItem(item);

		} catch (Exception e) {
			logger.error(" -- Error  review_item [ItemsController]:", e);
			throw new ControllerException(e.getMessage());
		}

		
	}
	


	/**
	 * obtiene informacion de la tabla HNota.
	 *
	 * @param String idContenido 
	 * @return HNota
	 * @throws ControllerException 
	 */
	@RequestMapping(value = "/get_item/{idContenido}", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public Item findNotaById(@PathVariable String idContenido) throws ControllerException {
		logger.info("--- ItemsController-----");
		logger.info("--- save_item -----");

		
		try {

			return detailBO.findNotaById(idContenido);

		} catch (Exception e) {
			logger.error(" -- Error  getItem by IdContenido [ItemsController]:", e);
			throw new ControllerException(e.getMessage());
		}

		
	}

	/**
	 * obtiene la lista de las nota en la tabla HNota.
	 *
	 * @return List<HNota>
	 * @throws ControllerException 
	 */
	@RequestMapping(value = "/get_item", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public List<HNota> findAllNota() throws ControllerException {
		logger.info("--- ItemsController-----");
		logger.info("--- getItem -----");


		try {

			return detailBO.findAllNota();

		} catch (Exception e) {
			logger.error(" -- Error  getItem [ItemsController]:", e);
			throw new ControllerException(e.getMessage());
		}

	}

}
