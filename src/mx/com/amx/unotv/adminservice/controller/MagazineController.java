package mx.com.amx.unotv.adminservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.com.amx.unotv.adminservice.bo.MagazineBO;

import mx.com.amx.unotv.adminservice.controller.exception.ControllerException;
import mx.com.amx.unotv.adminservice.model.Magazine;
import mx.com.amx.unotv.adminservice.model.request.MagazineRequest;
import mx.com.amx.unotv.adminservice.model.response.ItemsResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class MagazineController.
 */
/**
 * @author Jesus A. Macias Benitez
 *
 */
@Controller
@RequestMapping("magazine")
public class MagazineController {

	@Autowired
	MagazineBO magazineBO;
   
	
	
	/**
	 * Gets the list items by magazine.
	 *
	 * @param String idMagazine
	 * @return List<ItemsResponse>
	 * @throws ControllerException 
	 */
	@RequestMapping(value = "/get_magazine/{idMagazine}", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public List<ItemsResponse> getListItemsByMagazine(@PathVariable String idMagazine) throws ControllerException {
		List<ItemsResponse> lista = null;

		try {
			lista = magazineBO.getListItemsByMagazine(idMagazine);
		} catch (Exception e) {
			new ControllerException(e.getMessage());
		}

		return lista;
	}

	/**
	 * Gets the list magazine.
	 *
	 * @return List<Magazine>
	 * @throws ControllerException 
	 */
	@RequestMapping(value = "/get_list_magazine", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public List<Magazine> getListMagazine() throws ControllerException {
		List<Magazine> lista = null;

		try {
			lista = magazineBO.getListMagazine();
		} catch (Exception e) {
			new ControllerException(e.getMessage());
		}

		return lista;
	}
	
	
	/**
	 * Save magazine.
	 *
	 * @param MagazineRequest
	 * @throws ControllerException 
	 */
	@RequestMapping(value = "/save_magazine", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public void saveMagazine(@RequestBody MagazineRequest req) throws ControllerException {
	

		try {
			magazineBO.saveMagazine(req);
		} catch (Exception e) {
			new ControllerException(e.getMessage());
		}

		
	}
	
	
	

}
