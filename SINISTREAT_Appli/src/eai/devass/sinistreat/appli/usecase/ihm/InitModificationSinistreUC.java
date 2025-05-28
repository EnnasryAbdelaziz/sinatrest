package eai.devass.sinistreat.appli.usecase.ihm;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Conciliation;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;

public class InitModificationSinistreUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {

		// Récupérer la class de l'objet BO correspondant à l'objet VO
		SinistreVO sinvo = (SinistreVO) entite;
		try {

			List listTypeAccident = (List) parametrageManager
					.getListTypeAccident(null);
			this.addResultItem(listTypeAccident);
			List listGarantie = (List) parametrageManager.getListGarantie(null);
			this.addResultItem(listGarantie);
			List listZone = (List) parametrageManager.getListZone(null);
			this.addResultItem(listZone);
			List listCause = (List) parametrageManager.getListCause(null);
			this.addResultItem(listCause);
			List listVille = (List) parametrageManager.getListVille(null);
			this.addResultItem(listVille);
			List listTypeMaldie = (List) parametrageManager
					.getListTypeMaladie(null);
			this.addResultItem(listTypeMaldie);
			Sinistre sin = (Sinistre) sinistreManager.getSinistreByNum(sinvo
					.getNumeroSinistre());
			
			// verifier Jugement 
			if (sinistreManager.verifierAudiance(sinvo.getNumeroSinistre())) {
				sin.setModifiable(true);
			}	
			//Evo Version prod N: 5
			// verifier certificat Guerison et/ou certificat Justice
			if(sinistreManager.verifierCertificat(String.valueOf(sin.getId()))){
				sin.setIppCertificat(true);
			}
			//Evo: vérifier si décision = classement provisoire
			if(sinistreManager.verifierDecisionCP(sinvo.getNumeroSinistre())){
				sin.setDecisionCP(true);
			}
			
			this.addResultItem(sin);
			List listPoliceUniv = (List) parametrageManager
					.getlistPoliceUnivers(null);
			this.addResultItem(listPoliceUniv);
			List listSituation = (List) parametrageManager
					.getlistSituation(null);
			this.addResultItem(listSituation);
			List listNationalite = (List) parametrageManager
					.getListNationalite(null);
			this.addResultItem(listNationalite);
			List listPays = (List)parametrageManager.getListPays(null);
			this.addResultItem(listPays);
			List listTypeSuivi = (List)parametrageManager.getListTypeSuivi();
			this.addResultItem(listTypeSuivi);
			List listTypeDeclaration = (List)parametrageManager.getListTypeDeclaration();
			this.addResultItem(listTypeDeclaration);
			List listSourceDeclaration = (List)parametrageManager.getListSourceDeclaration();
			this.addResultItem(listSourceDeclaration);
			//Evol LOT1 : 02/12/2021 : control salaire Annuel : get la liste conciliation by id pour faire le controle sur l'offre
			List<Conciliation> listConciliation = (List) conciliationManager.getConciliationByIdSinistre(sin);
			this.addResultItem(listConciliation);
			//Fin evol LOT1
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);

		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

	}

	public boolean isTransactionnal() {
		return false;
	}

}
