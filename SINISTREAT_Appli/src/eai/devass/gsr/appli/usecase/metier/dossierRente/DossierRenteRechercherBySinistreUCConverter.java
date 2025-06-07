package eai.devass.gsr.appli.usecase.metier.dossierRente;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVO;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.entites.IEntite;
import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.DossierRenteVO;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.DossierRenteVOConverter;

/**
 * Converter du use case de recherche
 * 
 * @author Nom Prenom (email)
 */

public class DossierRenteRechercherBySinistreUCConverter extends
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
			List<IValueObject> listVO = dossierRenteVOConverter.convertItemsToVos((List<IEntite>) item.get(0));
			rechListeVO.setCurrentRes(listVO);
			rechListeVO.setNbResultats((Integer)item.get(1));
			rechListeVO.setNumPage((Integer)item.get(2));
			rechListeVO.setPageSize((Integer)item.get(3));
			
			// Nbr de pages
			long nbrPages = 0;
			if(rechListeVO.getNbResultats() > 20 && rechListeVO.getPageSize()> 0) {
				nbrPages = ((long) (rechListeVO.getNbResultats()/rechListeVO.getPageSize())) + 1;
				rechListeVO.setCritere(String.valueOf(nbrPages));
			}
			
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
