package eai.devass.gsr.appli.usecase.metier.dossierRente;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVO;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.DossierRenteVO;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.DossierRenteVOConverter;

/**
 * Converter du use case de recherche
 * 
 * @author Nom Prenom (email)
 */

public class DossierRenteGetRenteNoValiderUCConverter extends
		ValueObjectConverterAbst {

	DossierRenteVOConverter dossierRenteVOConverter = new DossierRenteVOConverter();
	private Logger logger = Logger.getLogger("loggerSINAT");
	/**
	 * Methode qui convertit une entité à un Value Object
	 * 
	 * @param item
	 *            entité à convertir
	 * @returns Value Object resultat converti
	 */
	
	public Object doConvertItemsToValueObject(List item) {
		RechListeVO rechListeVO = new RechListeVO();
		try
		{
			List<DossierRente> listDossierRent = (List<DossierRente>)item.get(0);
			List<DossierRenteVO> listDossierRentVO = new ArrayList<DossierRenteVO>();
			for(DossierRente curRent : listDossierRent)
			{
				DossierRenteVO vo = new DossierRenteVO();
				dossierRenteVOConverter.itemToVoWithoutDetail(vo, curRent);
				listDossierRentVO.add(vo);
			}
			rechListeVO.setCurrentRes(listDossierRentVO);
			rechListeVO.setNbResultats((Integer)item.get(1));
			rechListeVO.setNumPage((Integer)item.get(2));
			rechListeVO.setPageSize((Integer)item.get(3));
		} catch (Exception e) {
			logger.error("probl�me technique",e);
		}
		return rechListeVO;
	}

	/**
	 * Methode qui convertit un Value Object à une liste d' entités
	 * 
	 * @param ovo
	 *            value object à convertir
	 * @returns Listes des entités resultats convertis
	 * @throws ValidationException
	 *             Probleme lors de la validation unitaire d' une entité
	 */
	public List<DossierRente> doConvertValueObjectToItems(Object ovo)
			throws ValidationException {
		DossierRenteVO vo = (DossierRenteVO) ovo;
		DossierRente item = new DossierRente();
		dossierRenteVOConverter.voToItem(vo, item);
		List<DossierRente> res = new ArrayList<DossierRente>();
		res.add(item);
		return res;
	}
}
