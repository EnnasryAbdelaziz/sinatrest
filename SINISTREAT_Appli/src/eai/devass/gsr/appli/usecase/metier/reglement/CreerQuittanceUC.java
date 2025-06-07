package eai.devass.gsr.appli.usecase.metier.reglement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.uc.SimpleBaseUC;
import eai.devass.gsr.appli.businessrule.ModeReglementBR;
import eai.devass.gsr.appli.manager.metier.reglement.QuittanceGsrManager;
import eai.devass.gsr.appli.modele.metier.reglement.ComplementQuitatnce;
import eai.devass.gsr.appli.modele.metier.reglement.Prerglt;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.MotifCreationQuittance;
import eai.devass.gsr.appli.modele.parametrage.NatureQtcGsr;
import eai.devass.gsr.appli.modele.parametrage.SituationQuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.TypeApprobation;
import eai.devass.gsr.appli.prm.EtatQuittance;
import eai.devass.gsr.appli.prm.NatureQuittance;
import eai.devass.gsr.appli.prm.TypeQuittance;
import eai.devass.gsr.appli.utile.CommonGsrUtils;
import eai.devass.gsr.appli.valueobjects.metier.reglement.QuittanceGsrVO;



public class CreerQuittanceUC extends SimpleBaseUC {
	
	private DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	
	protected void execute(IValueObject entite, HashMap params) throws Exception {
		
		QuittanceGsr quittanceGsr = (QuittanceGsr) getItem(QuittanceGsr.class);
		QuittanceGsrVO quittanceGsrVO = (QuittanceGsrVO) entite;
		
		// valider le reglement
		ModeReglementBR modeReglementBR = new ModeReglementBR(quittanceGsr);
		modeReglementBR.valideModeReglement();			
		
		// Num sinistre
		if(CommonGsrUtils.isEmpty(quittanceGsr.getNumeroSinistre())) {
			throw new ExceptionMetier("Le numéro de sinistre est obligatoire !!");
		}
		
		// Etat de la quittance
		SituationQuittanceGsr situationQuittanceGsr = quittanceGsr
				.getCurSituationQuittanceGsr(EtatQuittance.Cree);
		situationQuittanceGsr.setOperateur(quittanceGsrVO.getOperateur());
		if(!CommonGsrUtils.isEmpty(quittanceGsrVO.getCodeMotifCreation())) {
			MotifCreationQuittance motifCreationQuittance = new MotifCreationQuittance();
			motifCreationQuittance.setCode(quittanceGsrVO.getCodeMotifCreation());
			situationQuittanceGsr.setRefMotifSituationEtat(motifCreationQuittance);
		}
		
		if(quittanceGsr.getId() == 0) {
			quittanceGsr.setDateCreation(new GregorianCalendar());
		}
		
		quittanceGsr.setExercice(CommonGsrUtils.getInstance().getCurrentTrimestre(dateFormat.parse(quittanceGsrVO
				.getDateFin())));
		quittanceGsr.setRefTypeApprobation(new TypeApprobation(
				eai.devass.gsr.appli.prm.TypeApprobation.Approuvee.getId()));
		quittanceGsr.setRefTypeQuittance(
				new eai.devass.gsr.appli.modele.parametrage.TypeQuittance(TypeQuittance.Reglement.getId()));
		
		// Construire le complement quittance
		ComplementQuitatnce complementQuitatnce = new ComplementQuitatnce();
		if(quittanceGsrVO.getIdComplementQuittance() != null) {
			complementQuitatnce.setIdentifiant(Long.valueOf(quittanceGsrVO
					.getIdComplementQuittance()));
		}
		
		complementQuitatnce.setDetailMotif(quittanceGsrVO.getDetailMotif());		
		if(CommonGsrUtils.isNumeric(quittanceGsrVO.getMontantTaxeCommission())) {
			complementQuitatnce.setMontantCommission(Double
					.valueOf(quittanceGsrVO.getMontantTaxeCommission().replaceAll(",", ".")));
		}
		
		if(quittanceGsrVO.getDateDebut() != null) {
			complementQuitatnce.setDateDebut(dateFormat.parse(quittanceGsrVO
					.getDateDebut()));
			quittanceGsr.setDateDebutQtc(complementQuitatnce.getDateDebut());
		}
		if(quittanceGsrVO.getDateFin() != null) {
			complementQuitatnce.setDateFin(dateFormat.parse(quittanceGsrVO
					.getDateFin()));
			quittanceGsr.setDateFinQtc(complementQuitatnce.getDateFin());
		}
		quittanceGsr.setRefComplementQuitatnce(complementQuitatnce);
		
		// Evo QC11 GSR, seulement pour le cas quittance trim (nature=25)
		if(NatureQuittance.Rente_periodique.getId() == quittanceGsr.getRefNatureQuittance().getId()) {
			QuittanceGsrManager quittanceGsrManager = (QuittanceGsrManager) ServicesFactory
					.getService(QuittanceGsrManager.class);
			List<QuittanceGsr> listQtc = quittanceGsrManager.getListQuittanceManuelle(complementQuitatnce, 
					quittanceGsr);
			if(!commonUtils.isEmpty(listQtc)) {
				throw new ExceptionMetier("Une quittance est déjà crée avec la même nature et pour la même période");
			}
		
		}
		
		Prerglt prerglt = quittanceGsr.getRefPrerglt();
		prerglt.setDateCreation(new GregorianCalendar());
		getSession().saveOrUpdate(quittanceGsr.getRefPrerglt());
		getSession().save(situationQuittanceGsr);
		getSession().saveOrUpdate(complementQuitatnce);
		getSession().saveOrUpdate(quittanceGsr);
		
	}

	
	
	

}


