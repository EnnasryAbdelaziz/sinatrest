package eai.devass.gsr.appli.usecase.metier.mouvements;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVO;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.entites.IEntite;
import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.modele.parametrage.SituationMouvement;
import eai.devass.gsr.appli.utile.CommonGsrUtils;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.DossierRenteVO;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.DossierRenteVOConverter;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.MouvementVO;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.MouvementVOConverter;
import eai.devass.sinistreat.appli.modele.parametrage.Situation;

/**
Converter du use case de recherche
@author Nom Prenom (email)
*/

public class MouvementRechercherByDossierUCConverter extends
		ValueObjectConverterAbst {

	DossierRenteVOConverter dossierRenteVOConverter = new DossierRenteVOConverter();
	MouvementVOConverter mouvementVOConverter = new MouvementVOConverter();
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
			List<IEntite> listMvt = (List<IEntite>) item.get(0);
			if(CommonGsrUtils.getInstance().isEmpty(listMvt)) {
				return rechListeVO;
			}
			
			List<IValueObject> listVO = mouvementVOConverter.convertItemsToVos(listMvt);
			
			for(IValueObject object : listVO) {
				
				
				MouvementVO vo = (MouvementVO) object;
				
				vo.setOperateur(getOperateur(listMvt,vo.getId()));

			}
			
			rechListeVO.setCurrentRes(listVO);
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
	
	private String getOperateur(List<IEntite> listMvt, long idMvt) {
		
		List<SituationMouvement> listSitMvt = null;
		for(IEntite curEntite : listMvt) {
			if(idMvt == ((Mouvement) curEntite).getId()) {
				listSitMvt = ((Mouvement) curEntite).getRefSituationMouvement();
				if(!CommonGsrUtils.getInstance().isEmpty(listSitMvt)) {
					return listSitMvt.get(0).getOperateur();
				}
			}
		}
		
		return null;
	}

}
