package mx.com.amx.unotv.adminservice.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.com.amx.unotv.adminservice.bo.ItemsBO;
import mx.com.amx.unotv.adminservice.controller.exception.ControllerException;
import mx.com.amx.unotv.adminservice.model.request.ItemsFilterRequest;
import mx.com.amx.unotv.adminservice.model.request.ItemsRequest;
import mx.com.amx.unotv.adminservice.model.request.ItemsRequestByTitle;
import mx.com.amx.unotv.adminservice.model.response.ItemsResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemsController.
 */
/**
 * @author Jesus A. Macias Benitez
 *
 */
@Controller
@RequestMapping("items")
public class ItemsController {

	/** The nota BO. */
	@Autowired
	ItemsBO itemsBO;

	/** The logger. */
	private static Logger logger = Logger.getLogger(ItemsController.class);

	/**
	 * regresa un listado de notas segun los filtros .
	 *
	 * @param ItemsFilterRequest
	 * @return List<ItemsResponse>
	 * @throws ControllerException the controller exception
	 */
	@RequestMapping(value = "/get_list_items_filter", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public List<ItemsResponse> getListItemsByFilter(@RequestBody ItemsFilterRequest req) throws ControllerException {
		logger.info("---getListItemsByFilter [ItemsController] ----");
		List<ItemsResponse> lista = null;

		try {
			lista = itemsBO.getListItemsByFilter(req);
		} catch (Exception e) {
			new ControllerException(e.getMessage());
		}

		return lista;
	}
	

	/**
	 *  regresa una lista de notas.
	 *
	* @param ItemsFilterRequest
	 * @return List<ItemsResponse>
	 * @throws ControllerException the controller exception
	 */
	@RequestMapping(value = "/get_list_items", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public List<ItemsResponse> getListItems(@RequestBody ItemsRequest req) throws ControllerException {
		logger.info("---get_list_items [ItemsController] ----");
		List<ItemsResponse> lista = null;

		try {
			lista = itemsBO.getListItems(req);
		} catch (Exception e) {
			new ControllerException(e.getMessage());
		}

		return lista;
	}
	
	/**
	 * regresa un listado de notas segun los filtros .
	 *
	 * @param ItemsFilterRequest
	 * @return List<ItemsResponse>
	 * @throws ControllerException the controller exception
	 */
	@RequestMapping(value = "/get_list_items_search", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public List<ItemsResponse> getListItemsByTitle(@RequestBody ItemsRequestByTitle req) throws ControllerException {
		logger.info("---get_list_items [ItemsController] ----");
		List<ItemsResponse> lista = null;

		try {
			lista = itemsBO.getListItemsByTitle(req);
		} catch (Exception e) {
			new ControllerException(e.getMessage());
		}

		return lista;
	}



}
