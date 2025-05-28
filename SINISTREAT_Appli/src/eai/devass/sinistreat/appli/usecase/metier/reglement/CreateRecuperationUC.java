package eai.devass.sinistreat.appli.usecase.metier.reglement;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.parametrage.EtatRgl;
import eai.devass.sinistreat.appli.modele.parametrage.NatureRecuperation;
import eai.devass.sinistreat.appli.modele.parametrage.TypeQuittance;
import eai.devass.sinistreat.appli.modele.parametrage.TypeReglement;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.utils.entites.IParam;

public class CreateRecuperationUC extends BaseUC  {
	
    protected void executerUC(IValueObject entite, HashMap params)
            throws Exception {
		Reglement reglement = (Reglement)this.getItem(Reglement.class);
		try{
			
			// set info reglement.
			setInfoReglementRecuperation(reglement);			
            reglement = (Reglement) reglementManager
                    .creerRecuperation(reglement);
			
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}
		
		addResultItem(reglement);
	}
	
	public boolean isTransactionnal() {
		return true;
	}
	
    private void setInfoReglementRecuperation(Reglement reglement)
            throws Exception {

		// set reglement recuperation
		reglement.setService(IParam.CODE_SERVICE_ORDONNATEUR_AT);
		reglement.setTypeBeneficiaire("");
		reglement.setRefEtatReglement(new EtatRgl(
				ETAT_REGLEMENT_EN_INSTANCE_DE_VALIDATION));
		reglement.setCodeBranche(IParam.CODE_BRANCHE_AT);
        reglement.setRefTypeReglement(new TypeReglement(
                TYPE_REGLEMENT_RECUPERATION));
		
        NatureRecuperation natureRecuperation = reglement
                .getRefNatureRecuperation();
		if(natureRecuperation == null) {
			throw new Exception("Nature récupération est obligatoire !!");
		}
		
		// Debours !!
		String codeTypeQuitatnce = null;
		String codeSousTypeQtc = null;
		String typeBeneficiaire = BENEFICIAIRE_VICTIME;
		
		if(NATURE_DEBOURS_LOI.equals(natureRecuperation.getCode())) {
            if (reglement.getNumeroSinistre().equals("")
                    || reglement.getNumeroRemise() == null) {
				throw new Exception("numero de remise est obligatoire");
			}
			codeTypeQuitatnce = TYPE_QUITTANCE_RECUPERATION_DEBOURSLOIS;
			codeSousTypeQtc = SOUS_TYPE_QUITTANCE_RECUPERATION_DEBOURSLOIS;
			
		} else if(NATURE_TROP_PERCU.equals(natureRecuperation.getCode())) {
            if (reglement.getNumeroSinistre().equals("")
                    || reglement.getNumeroRemise() == null) {
				throw new Exception("numero de remise est obligatoire");
			}
			codeTypeQuitatnce = TYPE_QUITTANCE_RECUPERATION_TROPPERCU;
			codeSousTypeQtc = SOUS_TYPE_QUITTANCE_RECUPERATION_TROPPERCU;
			
		} else if(NATURE_ANNULATION.equals(natureRecuperation.getCode())) {	
			codeTypeQuitatnce = TYPE_QUITTANCE_RECUPERATION_ANNULATION;
			codeSousTypeQtc = SOUS_TYPE_QUITTANCE_RECUPERATION_ANNULATION;
			
		} else {
			throw new Exception("Nature quittance non valide !!");
		}
		
		reglement.setRefTypeQuittance(new TypeQuittance(codeTypeQuitatnce));
		reglement.setSousTypeQuittance(codeSousTypeQtc);
		
		// Type beneficiare
		if("AVOCAT ADVERSE".equals(reglement.getEmetteur())) {
			typeBeneficiaire = BENEFICIAIRE_AVOCAT;
			
		} else if("INTERMEDIAIRE".equals(reglement.getEmetteur())) {
			typeBeneficiaire = BENEFICIAIRE_INTERMEDIAIRE;
		} 
		reglement.setTypeBeneficiaire(typeBeneficiaire);
		reglement.setModeReglement(IConstantes.MODE_REGLEMENT_CHEQUEPRIME);
		// A VALIDER
		reglement.setNomBeneficiaire("RMA WATANYA");
	}
	
}
