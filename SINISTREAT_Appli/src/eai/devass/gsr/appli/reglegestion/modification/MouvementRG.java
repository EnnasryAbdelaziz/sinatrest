package eai.devass.gsr.appli.reglegestion.modification;

import java.util.GregorianCalendar;
import java.util.Map;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.commun.appli.rg.ASkipRG;
import eai.devass.commun.appli.rg.ContextRegleGestion;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.modele.metier.reglement.Prerglt;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.SituationMouvement;
import eai.devass.gsr.appli.modele.parametrage.SituationQuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.TypeApprobation;
import eai.devass.gsr.appli.prm.EtatMouvement;
import eai.devass.gsr.appli.prm.EtatQuittance;

/** @author kchakib */


@SuppressWarnings("all")
public class MouvementRG extends eai.devass.gsr.appli.reglegestion.creation.MouvementRG {

	@Override
	public void regleGestion001VerifyMouvementEnCours(EntiteBO entiteBO,
			Map params) throws ExceptionMetier {
		
	}
	
	@Override
	@ASkipRG(property="genererQuittance", value="false", isEmty="true")
	public void regleGestion991SetEtatQuittance(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		if(commonGsrUtils.isEmpty(listQtc)) {
			return;
		}
		
		// Generation des situations quittances !!!
		SituationQuittanceGsr situationQuittanceGsr = null;
		for(QuittanceGsr quittanceGsr : listQtc) {
			if(quittanceGsr.getRefEtatQtc().getId() != EtatQuittance.Supprimee.getId()){
			situationQuittanceGsr = quittanceGsr.getCurSituationQuittanceGsr(EtatQuittance.Cree);
			situationQuittanceGsr.setOperateur(entiteBO.getOperateur());
			getSession().saveOrUpdate(situationQuittanceGsr);		
			}
		}
		
	}

	
	@Override	
	@ASkipRG(property="genererQuittance", value="false", isEmty="true")
	public void regleGestion990GeneratePreQuittanceGsr(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		Mouvement mouvement = (Mouvement) entiteBO;
		
		//vérifier les info de qtc et de reglement
		listQtc = mouvement.getRefsQuittance();
		if(commonGsrUtils.isEmpty(listQtc)) {
			throw new ExceptionMetier("Les informations de réglement sont obligatoires");
		}
			
		QuittanceGsr firstQuittanceGsr = listQtc.get(0);
		Prerglt prerglt0 = firstQuittanceGsr.getRefPrerglt();
		prerglt0.setDateCreation(new GregorianCalendar());
		
		// Met à jour le  pre-reglement
		getSession().saveOrUpdate(prerglt0);
		Long oldIdPrereglement = prerglt0.getId();
		
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
			quittanceGsr.setRefRentier(rentierDB);
			quittanceGsr.setDateCreation(new GregorianCalendar());
			quittanceGsr.setDatEtat(new GregorianCalendar());
			quittanceGsr.setRefMouvement(mouvement);
			mouvement.setQuittanceGsr(quittanceGsr);
			
			
			if(oldIdPrereglement != prerglt.getId()){
				// Met à jour le  pre-reglement
				prerglt.setDateCreation(new GregorianCalendar());
				getSession().saveOrUpdate(prerglt);
			}
			
			getSession().saveOrUpdate(quittanceGsr);
		}
	}
	
	@Override
	@ASkipRG(property="refEtatMvt.id", value="2")
	public void regleGestion992SetEtatMouvement(EntiteBO entiteBO, Map params)
			throws ExceptionMetier {
		Mouvement mouvement = (Mouvement) entiteBO;
		ContextRegleGestion ctxMouvement = mouvement
				.getContextRegleGestionEnum();
		if(ctxMouvement.equals(ContextRegleGestion.DEFAULT)) {
			return;
		}
		
		mouvement.setDateCreation(new GregorianCalendar());
		SituationMouvement situationMouvement = mouvement
				.getCurSituationMouvement(EtatMouvement.Cree, null);
		situationMouvement.setOperateur(entiteBO.getOperateur());
		getSession().save(situationMouvement);	
	}
}
