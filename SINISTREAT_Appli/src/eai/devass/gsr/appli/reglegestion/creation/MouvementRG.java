package eai.devass.gsr.appli.reglegestion.creation;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.commun.appli.rg.ASkipRG;
import eai.devass.commun.appli.rg.ContextRegleGestion;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.modele.metier.reglement.Prerglt;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.EtatMvt;
import eai.devass.gsr.appli.modele.parametrage.SituationMouvement;
import eai.devass.gsr.appli.modele.parametrage.SituationQuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.TypeApprobation;
import eai.devass.gsr.appli.prm.EtatMouvement;
import eai.devass.gsr.appli.prm.EtatQuittance;
import eai.devass.gsr.appli.reglegestion.BaseRG;
import eai.devass.gsr.appli.utile.IMessageException;
/** @author kchakib */

@SuppressWarnings("all")
public class MouvementRG extends BaseRG {
	
	
	protected List<QuittanceGsr> listQtc = null;
	
	public void regleGestion000verifierRente(EntiteBO entiteBO, Map params)
			throws ExceptionMetier {

		Mouvement mouvement = (Mouvement) entiteBO;	
		rentierDB = mouvement.getRefRentier();
		if (rentierDB == null || rentierDB.getId() <= 0) {
			throw new ExceptionMetier(IMessageException.EXP_RENTIER_INTROUVALE);
		}
				
		try {
			rentierDB = (Rentier) rentierManager.getEntite(rentierDB);
			
			// Verifier le numero de sinistre			
			if(rentierDB.getRefDossierRente() == null) {
				throw new ExceptionMetier("Le dossier rente est obligatoire !!");
			}
			
			if (rentierDB.getValidation() != true) {
				throw new ExceptionMetier(IMessageException.EXP_RENTE_NON_VALIDE);
			}
			
			if(rentierDB.getRefDossierRente()
					.getRefSinistre() == null) {
				throw new ExceptionMetier("Le dossier sinistre est obligatoire !!");
			}
			
			if(rentierDB.getRefDossierRente()
					.getRefSinistre().getNumeroSinistre() == null) {
				throw new ExceptionMetier("Le numéro de sinistre est obligatoire !!");
			}
			
			// Pour le besoin de l'historisation
			mouvement.setRefRentier(rentierDB);
			
		} catch (Exception e) {
			throw new ExceptionMetier(e.getMessage());
		}

		
	}
	
	public void regleGestion001VerifyMouvementEnCours(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		try {
			EtatMvt etatMvt = mouvementManager.getLastEtatMouvement(rentierDB);
			if(etatMvt == null) {
				//throw new ExceptionMetier("Impossible de récupérer l'etat du dérnier mouvement");
				return;
			}
			
			if (EtatMouvement.Validee.getId() != etatMvt.getId()
					&& EtatMouvement.Annulee.getId() != etatMvt.getId()
					&& EtatMouvement.Supprimer.getId() != etatMvt.getId()) {
				throw new ExceptionMetier(
						"Impossible de crée un nouveau Mouvement avant validation du dernier crée.");
			}
		
		} catch(Exception e) {
			throw new ExceptionMetier(e.getMessage());
		}
		
	}

	
	
	@ASkipRG(property="genererQuittance", value="false", isEmty="true")
	public void regleGestion990GeneratePreQuittanceGsr(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		Mouvement mouvement = (Mouvement) entiteBO;
				
		//vérifier les info de qtc et de reglement
		listQtc = mouvement.getRefsQuittance();
		if(commonGsrUtils.isEmpty(listQtc)) {
			throw new ExceptionMetier("Les informations de réglement sont obligatoires");
		}
		
		// App
		Boolean approb = rentierDB.getApprobationQuittance();
		TypeApprobation typeApprobation = new TypeApprobation(eai.devass.gsr.appli.prm.TypeApprobation.Non_approuvee.getId());
		if(commonGsrUtils.isTrue(approb)) {
			typeApprobation.setId(eai.devass.gsr.appli.prm.TypeApprobation.Approuvee.getId());
		} 
				
		// Numero de sinistre et class quittance
		String numSinistre = rentierDB.getRefDossierRente()
				.getRefSinistre().getNumeroSinistre();
		String classRentier = null;
		if(rentierDB.getLienParente() != null) {
			classRentier = String.valueOf(rentierDB.getLienParente());
		}
		
		String beneficiare = rentierDB.getNom() + " " + rentierDB.getPrenom();
		for(QuittanceGsr quittanceGsr : listQtc) {
			if(quittanceGsr == null) {
				throw new ExceptionMetier("La quittance est obligatoire pour la génération");
			}
			
			if(quittanceGsr.getRefModeReglement() == null) {
				throw new ExceptionMetier("Le mode de réglement de la quittance est obligatoire");
			}
			
			if(quittanceGsr.getRefNatureQuittance() == null) {
				throw new ExceptionMetier("La nature de réglement de la quittance est obligatoire");
			}
			
			if(quittanceGsr.getRefTypeQuittance() == null) {
				throw new ExceptionMetier("Le type de la quittance est obligatoire");
			}
			
			if(quittanceGsr.getRefTypeReglement() == null) {
				throw new ExceptionMetier("Le type réglement de la quittance est obligatoire");
			}
			
			Prerglt prerglt = quittanceGsr.getRefPrerglt();
			if(prerglt == null) {
				throw new ExceptionMetier("Le pre-réglement ne peut être null !!");
			}
			
			// BEneficiaire
			if(commonGsrUtils.isEmpty(quittanceGsr.getBeneficiaire())){
				quittanceGsr.setBeneficiaire(beneficiare);
			}
			
			quittanceGsr.setNumeroSinistre(numSinistre);
			quittanceGsr.setClasse(classRentier);
			String trimistre = commonGsrUtils.getCurrentTrimestre();		
			quittanceGsr.setNumeroRente(Long.toString(rentierDB.getRefDossierRente().getNumeroRente()));
			quittanceGsr.setExercice(trimistre);
			quittanceGsr.setRefTypeApprobation(typeApprobation);
			quittanceGsr.setRefRentier(rentierDB);
			quittanceGsr.setDateCreation(new GregorianCalendar());
			quittanceGsr.setDatEtat(new GregorianCalendar());
			quittanceGsr.setRefMouvement(mouvement);
			
			// Debut et fin trimestre
			quittanceGsr.setDateDebutQtc(commonGsrUtils.getDateDebutCurrentTrimestre());
			quittanceGsr.setDateFinQtc(commonGsrUtils.getDateFinCurrentTrimestre());
			
			mouvement.setQuittanceGsr(quittanceGsr);
			
			// Enregistre le pre-reglement
			prerglt.setDateCreation(new GregorianCalendar());
			getSession().saveOrUpdate(prerglt);
			getSession().saveOrUpdate(quittanceGsr);
		}
		
	}
	
	@ASkipRG(property="genererQuittance", value="false", isEmty="true")
	public void regleGestion991SetEtatQuittance(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		if(commonGsrUtils.isEmpty(listQtc)) {
			return;
		}
		
		// Generation des situations quittances !!!
		SituationQuittanceGsr situationQuittanceGsr = null;
		for(QuittanceGsr quittanceGsr : listQtc) {
			situationQuittanceGsr = quittanceGsr.getCurSituationQuittanceGsr(EtatQuittance.Cree);
			situationQuittanceGsr.setOperateur(entiteBO.getOperateur());
			getSession().saveOrUpdate(situationQuittanceGsr);		
		}
		
	}
	@ASkipRG(property="refEtatMvt.id", value="2")
	public void regleGestion992SetEtatMouvement(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		Mouvement mouvement = (Mouvement) entiteBO;
		ContextRegleGestion ctxMouvement = mouvement
				.getContextRegleGestionEnum();
		if(ctxMouvement.equals(ContextRegleGestion.DEFAULT)) {
			return;
		}
		
		// A verifier !!!
		if(!ContextRegleGestion.CREATION.equals(ctxMouvement)) {
			throw new ExceptionMetier("Context CREATION non respecter !!!");
		} 	
		
		mouvement.setDateCreation(new GregorianCalendar());
		SituationMouvement situationMouvement = mouvement
				.getCurSituationMouvement(EtatMouvement.Cree, null);
		situationMouvement.setOperateur(entiteBO.getOperateur());
		getSession().saveOrUpdate(situationMouvement);		
		
	}
	
	
	
	
	
	
}
