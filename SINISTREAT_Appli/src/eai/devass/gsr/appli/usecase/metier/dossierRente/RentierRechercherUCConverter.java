package eai.devass.gsr.appli.usecase.metier.dossierRente;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVO;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.entites.IEntite;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.RentierVO;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.RentierVOConverter;

/**
 * Converter du use case de recherche
 * 
 * @author Nom Prenom (email)
 */

public class RentierRechercherUCConverter extends ValueObjectConverterAbst {

	RentierVOConverter rentierVOConverter = new RentierVOConverter();
	private Logger logger = Logger.getLogger("loggerSINAT");
	/**
	 * Methode qui convertit un Value Object à une liste d' entités
	 * 
	 * @param ovo
	 *            value object à convertir
	 * @returns Listes des entités resultats convertis
	 * @throws ValidationException
	 *             Probleme lors de la validation unitaire d' une entité
	 */

	@Override
	public Object doConvertItemsToValueObject(List item) {
		//List listOut = new ArrayList();
		RechListeVO rechListeVO = new RechListeVO();
		try {
			List<IValueObject> listVO = rentierVOConverter.convertItemsToVos((List<IEntite>) item.get(0));
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
			// TODO Auto-generated catch block
			logger.error("probl�me technique",e);
		}
		return rechListeVO;
	}
	
	@Override
	public List <Rentier> doConvertValueObjectToItems(Object ovo)
			throws ValidationException {
		RentierVO vo = (RentierVO) ovo;
		Rentier item = new Rentier();
		rentierVOConverter.voToItem(vo, item);
		List<Rentier> res = new ArrayList<Rentier>();
		res.add(item);
		return res;
	}

}
