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


import mx.com.amx.unotv.adminservice.bo.MagazineBO;

import mx.com.amx.unotv.adminservice.controller.exception.ControllerException;
import mx.com.amx.unotv.adminservice.model.IMagazineNota;
import mx.com.amx.unotv.adminservice.model.Magazine;
import mx.com.amx.unotv.adminservice.model.request.MagazineRequest;

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

	private static Logger logger = Logger.getLogger(MagazineController.class);
	
	@Autowired
	MagazineBO magazineBO;
	


	/**
	 * Gets the list items by magazine.
	 *
	 * @param String
	 *            idMagazine
	 * @return List<ItemsResponse>
	 * @throws ControllerException
	 */
	@RequestMapping(value = "/get_magazine/{idMagazine}", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	@ResponseBody
	public List<IMagazineNota> getListItemsByMagazine(@PathVariable String idMagazine) throws ControllerException {

		try {
			return magazineBO.findById(idMagazine);
		} catch (Exception e) {
			logger.error("Exception getListItemsByMagazine  [ MagazineController ]: ", e);
			throw new ControllerException(e.getMessage());
		}

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

		try {
			return magazineBO.getListMagazine();
		} catch (Exception e) {
			logger.error("Exception getListMagazine  [ MagazineController ]: ", e);
			throw new ControllerException(e.getMessage());
		}

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
			
			logger.error("Exception save_magazine  [ MagazineController ]: ", e);
			throw new ControllerException(e.getMessage());
		}

	}

}
