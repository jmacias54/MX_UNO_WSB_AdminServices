/*
*
 * 
 */
package mx.com.amx.unotv.adminservice.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import mx.com.amx.unotv.adminservice.bo.exception.DetailBOException;
import mx.com.amx.unotv.adminservice.model.HNota;
import mx.com.amx.unotv.adminservice.model.NNota;
import mx.com.amx.unotv.adminservice.model.ParametrosDTO;
import mx.com.amx.unotv.adminservice.util.PropertiesUtils;
import mx.com.amx.unotv.adminservice.util.Utils;
import mx.com.amx.unotv.adminservice.ws.DetailCallWS;

/**
 * @author Jesus A. Macias Benitez
 *
 */
public class DetailBO {

	private static Logger logger = Logger.getLogger(DetailBO.class);

	@Autowired
	DetailCallWS detailCallWS;
	@Autowired
	NotaBO notaBO;

	public int saveItem(NNota nota) throws DetailBOException {
		logger.debug("*** Inicia saveItem [ DetailBO ] ***");

		ParametrosDTO parametrosDTO = null;
		PropertiesUtils properties = new PropertiesUtils();
		List<String> listError = new ArrayList<String>();
		boolean success= false;

		String id_facebook = "";
		String carpetaContenido = "";
		String urlNota = "";
		int res = 0;

		// Obtenemos archivo de propiedades
		try {
			parametrosDTO = properties.obtenerPropiedades();

			// ruta con dominio wwww.unotv.com ó http://dev-unotv.tmx-internacional.net
			logger.info("Frendy URL: " + nota.getFcFriendlyUrl());
			urlNota = parametrosDTO.getDominio() + "/" + Utils.getRutaContenido(nota, parametrosDTO);
			logger.info("URL: " + urlNota);

			// Ruta donde se va guardar el html servidor var/www/share_wwwww
			carpetaContenido = parametrosDTO.getPathFiles() + Utils.getRutaContenido(nota, parametrosDTO);
			logger.info("carpetaContenido: " + carpetaContenido);

			// Validamos si la nota contiene video de ooyala
			logger.debug("**TIPO DE NOTA: " + nota.getFcIdTipoNota());

			// Guardamos o actualizamos la nota en la base de datos.
			res = notaBO.saveOrUpdate(nota);
			// res = detailCallWS.insertNota(nota);

			if (res > 0) {
				// Guardamos o actualizamos los Tags de la nota

				// Creamos estrcutura de directorios
				success = Utils.createFolders(carpetaContenido);

				if(success)
					Utils.createPlantilla(parametrosDTO, nota, urlNota);

			}

		} catch (Exception e) {
			logger.error("Exception saveItem  [ DetailBO ]: ", e);
			throw new DetailBOException(e.getMessage());
		}

		return res;

	}

	public HNota findNotaById(String idContenido) throws DetailBOException {

		HNota nota = null;

		try {
			nota = detailCallWS.findNotaById(idContenido);
		} catch (Exception e) {
			logger.error("Exception findNotaById  [ DetailBO ]: ", e);
			throw new DetailBOException(e.getMessage());
		}

		return nota;
	}

	public List<HNota> findAllNota() throws DetailBOException {
		List<HNota> lista = null;

		try {
			lista = detailCallWS.findAllNota();
		} catch (Exception e) {
			logger.error("Exception findAllNota  [ DetailBO ]: ", e);
			throw new DetailBOException(e.getMessage());
		}

		return lista;

	}

}
