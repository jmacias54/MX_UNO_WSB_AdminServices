/**
 * 
 */
package mx.com.amx.unotv.adminservice.bo;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import mx.com.amx.unotv.adminservice.bo.exception.ItemsBOException;
import mx.com.amx.unotv.adminservice.model.IHNotaUsuario;
import mx.com.amx.unotv.adminservice.model.request.ItemsFilterRequest;
import mx.com.amx.unotv.adminservice.model.request.ItemsRequest;
import mx.com.amx.unotv.adminservice.model.request.ItemsRequestByTitle;
import mx.com.amx.unotv.adminservice.model.response.ItemsResponse;
import mx.com.amx.unotv.adminservice.ws.ItemsCallWS;

/**
 * @author Jesus A. Macias Benitez
 *
 */
public class ItemsBO {

	private static Logger logger = Logger.getLogger(ItemsBO.class);

	@Autowired
	ItemsCallWS itemsCallWS;
	@Autowired
	IHNotaUsuarioBO iHNotaUsuarioBO;

	public List<ItemsResponse> getListItemsByFilter(ItemsFilterRequest req) throws ItemsBOException {
		List<ItemsResponse> lista = null;

		logger.error("--- getListItemsByFilter ---  [ ItemsBO ]: ");
		try {
			lista = itemsCallWS.getListItemsByFilter(req);

			if (lista != null && !lista.isEmpty()) {

				// obtenemos el ultimo usuario que actualizo en H_NOTA y lo agregamos al objeto
				// response
				for (ItemsResponse itemsResponse : lista) {

					IHNotaUsuario usuarioIntermedio = iHNotaUsuarioBO.findByIdContenido(itemsResponse.getId());
					if (usuarioIntermedio != null) {
						itemsResponse.setUser(usuarioIntermedio.getFcIdUsuario());
					} else {
						itemsResponse.setUser("");
					}

				}
			}

		} catch (Exception e) {
			logger.error("Exception getListItemsByFilter  [ ItemsBO ]: ", e);
			throw new ItemsBOException(e.getMessage());
		}

		return lista;

	}

	public List<ItemsResponse> getListItems(ItemsRequest req) throws ItemsBOException {
		List<ItemsResponse> lista = null;
		IHNotaUsuario usuarioIntermedio = null;
		logger.error("--- getListItems ---  [ ItemsBO ]: ");
		try {
			lista = itemsCallWS.getListItems(req);

			if (lista != null && !lista.isEmpty()) {

				// obtenemos el ultimo usuario que actualizo en H_NOTA y lo agregamos al objeto
				// response
				
				for (ItemsResponse itemsResponse : lista) {

					 usuarioIntermedio = iHNotaUsuarioBO.findByIdContenido(itemsResponse.getId());
					if (usuarioIntermedio != null) {
						itemsResponse.setUser(usuarioIntermedio.getFcIdUsuario());
					} else {
						itemsResponse.setUser("");
					}

				}
			}

		} catch (Exception e) {
			logger.error("Exception getListItems  [ ItemsBO ]: ", e);
			throw new ItemsBOException(e.getMessage());
		}

		return lista;
	}

	public List<ItemsResponse> getListItemsByTitle(ItemsRequestByTitle req) throws ItemsBOException {
		List<ItemsResponse> lista = null;
		logger.error("--- getListItemsByTitle ---  [ ItemsBO ]: ");
		try {
			lista = itemsCallWS.getListItemsByTitle(req);

			if (lista != null && !lista.isEmpty()) {

				// obtenemos el ultimo usuario que actualizo en H_NOTA y lo agregamos al objeto
				// response
				for (ItemsResponse itemsResponse : lista) {

					IHNotaUsuario usuarioIntermedio = iHNotaUsuarioBO.findByIdContenido(itemsResponse.getId());
					if (usuarioIntermedio != null) {
						itemsResponse.setUser(usuarioIntermedio.getFcIdUsuario());
					} else {
						itemsResponse.setUser("");
					}

				}
			}

		} catch (Exception e) {
			logger.error("Exception getListItemsByTitle  [ ItemsBO ]: ", e);
			throw new ItemsBOException(e.getMessage());
		}
		return lista;
	}

}
