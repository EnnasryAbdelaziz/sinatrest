package eai.devass.gsr.appli.usecase.metier.dossierRente;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.commun.appli.uc.SimpleBaseUC;
import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.utile.CommonGsrUtils;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.RentierVO;

/**
 * 
 * @author KCH
 *
 */
public class ChercherCinRentierUC  extends SimpleBaseUC {

	protected void execute(IValueObject entite, HashMap params) throws Exception {

		RentierVO rentierVO = (RentierVO) entite;
		if(CommonUtils.isEmpty(rentierVO.getId())) {
			throw new Exception("L'identifiant du rentier est obligatoire !!");
		}
		
		Rentier rentier = (Rentier) getSession().get(Rentier.class,
				Long.valueOf(rentierVO.getId()));
		
		if(rentier == null) {
			throw new Exception("Imossible de récuperer le rentie avec ID : " 
					+ rentierVO.getId());
		}
		
		String numCin = rentier.getNumeroCIN();
		if(CommonUtils.isEmpty(numCin)) {
			// Appligquer la regle SOCIETE-NUMRENTE-CLASSE si num cin est vide
			String classe = String.valueOf(rentier.getLienParente());
			String numRente = null;
			String refSociete = null;
			DossierRente dossierRente = rentier.getRefDossierRente();
			if(dossierRente != null) {
				numRente = String.valueOf(dossierRente.getNumeroRente());
				refSociete = CommonGsrUtils.getInstance().getRefSociter(
						dossierRente.getCompagnieRente());
				numCin = refSociete + "-" + numRente + "-" + classe;
				
			}
		}
		
		rentier = new Rentier();
		rentier.setNumeroCIN(numCin);
		this.addResultItem(rentier);
		
	}
	
	
	
	
	

}
