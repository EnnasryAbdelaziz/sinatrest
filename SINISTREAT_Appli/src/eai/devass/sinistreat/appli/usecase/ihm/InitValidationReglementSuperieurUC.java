package eai.devass.sinistreat.appli.usecase.ihm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import com.rmawatanya.moyenpaiement.application.metier.valueobjects.OrdOrdonnoncementVO;
import com.rmawatanya.moyenpaiement.application.metier.valueobjects.PositionnementVO;

import eai.devass.services.ServicesExternes;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.ReglementVO;

@SuppressWarnings("rawtypes")
public class InitValidationReglementSuperieurUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		logger.info("Debut UC InitValidationReglementSuperieurUC");
		// Récupérer la class de l'objet BO correspondant à l'objet VO
		Reglement reglement = (Reglement) this.getItem(Reglement.class);
		List<Reglement> listOrdByMode = new ArrayList();
		try {

			// Recuperer le code SAS sup
			ReglementVO reglementVO = (ReglementVO) entite;			
			String codeSasSup = reglementVO.getCodeSasSup();
			if(Fonctions.isEmpty(codeSasSup)) {
				codeSasSup = reglementManager.getCodeSasSup(reglement);
			}
			
			List<Reglement> listReg = (List<Reglement>) reglementManager
					.ExtraireOrdonnecementSup(reglement, codeSasSup);
			
			
			//List<Reglement> listReglement = reglementManager.getListReglementByModeReglment(listReg, reglement.getModeReglement());
			
			// Debut filrage Mode Reglement 
						if(listReg != null ) {
			                if(reglement.getModeReglement() != null) {
			                if(reglement.getModeReglement().equals("6")) {
			                for(Reglement reg: listReg) {
			                       if(reg.getModeReglement().equals("6")){
			                            listOrdByMode.add(reg);
			                       }
			                }
			                }
			         } else {
			                for(Reglement reg: listReg) {
			                       if(!reg.getModeReglement().equals("6")){
			                            listOrdByMode.add(reg);
			                       }
			                }
			         }
			  }
						listReg = listOrdByMode;

						
						//Fin Filtrage 
			
			
			this.addResultItem(listReg);
			this.addResultItem(codeSasSup);
			
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);

		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}
		logger.info("Fin UC InitValidationReglementSuperieurUC");
	}

	public boolean isTransactionnal() {
		return false;
	}

}
