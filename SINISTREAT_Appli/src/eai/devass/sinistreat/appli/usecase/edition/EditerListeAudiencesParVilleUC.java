package eai.devass.sinistreat.appli.usecase.edition;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.dao.PersistenceException;
import eai.devass.edition.Utils.IConstantes;
import eai.devass.edition.valueobjects.EditionVO;
import eai.devass.sinistreat.appli.modele.parametrage.Ville;

public class EditerListeAudiencesParVilleUC extends EditerEtatUC{

	@Override
	protected String getCodeRapport() {
		return IConstantes.CODE_RAPPORT_LISTE_AUDIENCES_PAR_VILLE;
	}

	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo) {
		EditionVO editionVO = (EditionVO) vo;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nomDirection", "Direction Prestation AT");
		try {
			if(editionVO.getVille() != null && !"".equals(editionVO.getVille())){
				Session session = (Session) dao.getSession();
				Ville villeDB = (Ville) session.get(Ville.class, editionVO.getVille());
				params.put("titre", "AGENDA DES AUDIENCES : " + villeDB.getLibelle().toUpperCase());
				params.put("ville", editionVO.getVille() + "%");
				params.put("codeVille", editionVO.getVille());
				params.put("dateDebut", editionVO.getDateDebut());
				params.put("dateFin", editionVO.getDateFin());
			}
			
		} catch (PersistenceException e) {
			logger.error("problème technique",e);
		}
		
		return params;
	}

}
