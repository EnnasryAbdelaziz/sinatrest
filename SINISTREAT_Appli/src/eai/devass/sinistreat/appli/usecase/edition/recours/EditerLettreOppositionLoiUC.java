package eai.devass.sinistreat.appli.usecase.edition.recours;

import java.util.HashMap;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.dao.PersistenceException;
import eai.devass.edition.Utils.IConstantes;
import eai.devass.edition.valueobjects.EditionVO;
import eai.devass.sinistreat.appli.modele.parametrage.CompagnieAdverse;
import eai.devass.sinistreat.appli.usecase.edition.EditerJrxmlUC;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.RecoursVO;

public class EditerLettreOppositionLoiUC extends EditerJrxmlUC{

	@Override
	protected String getCodeTemplate() {
		return IConstantes.CODE_TEMPLATE_LETTRE_OPPOSITION_LOI;
	}

	@Override
	protected boolean imprimer(IValueObject vo) {
		return false;
	}
	
	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo){
		
		Map<String, Object> params = new HashMap<String, Object>();
		EditionVO editionVO = (EditionVO) vo;
		RecoursVO recoursVO = editionVO.getRefRecoursVO();
		CompagnieAdverse compagnieAdverse = null;
		try {
			compagnieAdverse = parametrageManager.getCompagnieAdverseByCode(recoursVO.getRefCompagnie().getCode());
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(compagnieAdverse != null) {
			params.put("compagnieAdverse", compagnieAdverse.getLibelle());
		    params.put("adresseCmpAdv", compagnieAdverse.getAdresse());
		    params.put("villeCmpAdv", compagnieAdverse.getVille());
		}else {
			params.put("compagnieAdverse", recoursVO.getRefCompagnie().getLibelle());
			params.put("adresseCmpAdv", " Avenue Adresse ");
			params.put("villeCmpAdv", " Ville ");
		}
		
        params.put("vRef", "Pol.N° "
                + recoursVO.getRefSinistre().getNumeroPolice());
        params.put("nRef", "N° "
                + recoursVO.getRefSinistre().getNumeroSinistre()
                + "/"
                + recoursVO.getRefSinistre().getRefEvenement()
                        .getDateAccident().substring(6, 10) + " X "
                + recoursVO.getRefSinistre().getNumeroGrave());
        params.put("objet", "AT du "
                + recoursVO.getRefSinistre().getRefEvenement()
                        .getDateAccident());
        params.put("societeAssure", recoursVO.getRefSinistre()
                .getNomIntermediaire());
		params.put("utilisateur", recoursVO.getUserCreateur());
        params.put("nomVictime", recoursVO.getRefSinistre().getRefVictime()
                .getNomprenom());
        params.put("dateAccident", recoursVO.getRefSinistre().getRefEvenement()
                .getDateAccident());
		params.put("marqueVehicule", recoursVO.getMarque());
		params.put("matricule", recoursVO.getImmatriculation());
		params.put("nomConducteur", recoursVO.getNomConducteur());
		
		return params;
	}
}
