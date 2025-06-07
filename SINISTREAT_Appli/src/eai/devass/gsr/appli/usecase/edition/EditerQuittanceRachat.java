package eai.devass.gsr.appli.usecase.edition;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.utile.DateUtile;
import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.commun.appli.util.DateUtils;
import eai.devass.edition.Utils.IConstantes;
import eai.devass.edition.beans.QuittanceRachatBean;
import eai.devass.edition.valueobjects.EditionVO;
import eai.devass.gsr.appli.manager.metier.reglement.QuittanceGsrManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.utile.ConvertierMontantEnLettre;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.contentieux.ProcedureJudiciaire;
import eai.devass.sinistreat.appli.usecase.edition.EditerJavaBeanJrxmlUC;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;

public class EditerQuittanceRachat extends EditerJavaBeanJrxmlUC{

	@Override
	protected String getCodeTemplate() {
		return IConstantes.CODE_TEMPLATE_QTC_RACAHAT;
	}

	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo){
		Map<String, Object> params = new HashMap<String, Object>();
		return params;
	}

	@Override
	protected List<QuittanceRachatBean> pupulateBeans(IValueObject vo){
		
		
		List<QuittanceRachatBean> results = null;
		try {

			EditionVO editionVO = (EditionVO)vo;
			String numeroRente = editionVO.getNumRente();
			Calendar dateDebut = null;
			if(editionVO.getDateDebut() != null && !"".equals(editionVO.getDateDebut())){
				dateDebut = DateUtile.getCalendar(editionVO.getDateDebut());
			}
			Calendar dateFin = null;
			if(editionVO.getDateFin() != null && !"".equals(editionVO.getDateFin())){
				dateFin = DateUtile.getCalendar(editionVO.getDateFin());
			}
			

			
			QuittanceGsrManager qtcManager = (QuittanceGsrManager) ServicesFactory.getService(QuittanceGsrManager.class);
			
			List<QuittanceGsr> listQtcRachat= null;
			
			if(numeroRente!=null && !"".equals(numeroRente)){
				listQtcRachat= qtcManager.getQuittancesRachat(numeroRente, null, null);
			}else if(dateDebut!=null && dateFin!=null){
				
				listQtcRachat= qtcManager.getQuittancesRachat(null, dateDebut, dateFin);
			}

			
			if(listQtcRachat!=null && listQtcRachat.size()>0){
				
				results= new ArrayList<QuittanceRachatBean>();
				for(QuittanceGsr qtcRachat:listQtcRachat){
					
					QuittanceRachatBean qtcEditionRachat= new QuittanceRachatBean();
					
					qtcEditionRachat.setNumeroRente(qtcRachat.getNumeroRente());
					qtcEditionRachat.setNomPrenomVictime(qtcRachat.getRefRentier().getNomComplet());
					qtcEditionRachat.setNomAssure(qtcRachat.getRefRentier().getNomComplet());
					qtcEditionRachat.setMontant(CommonUtils.formatterMontant(qtcRachat.getMontant()));
					qtcEditionRachat.setMontantEnLettre(ConvertierMontantEnLettre.montantToLettre(String.valueOf(qtcRachat.getMontant()), "dirhams", "centimes"));
					qtcEditionRachat.setDateQuittance(DateUtils.dateToString("dd/MM/yyyy",qtcRachat.getDatEtat().getTime()));
					alimenterProcJudiciaire(qtcRachat,qtcEditionRachat);
					results.add(qtcEditionRachat);
				}
			}
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		return results;
	}
	/**
	 * @author elfaismo
	 * @param qtcGsr
	 * @param qtcEdition
	 */
	private void alimenterProcJudiciaire(QuittanceGsr qtcGsr,QuittanceRachatBean qtcEdition){
		Rentier rentier = qtcGsr.getRefRentier();
		DossierRente dossierRente = rentier.getRefDossierRente();
		
		try {
			//données de Tests
//			Sinistre sinTest = new Sinistre();	
//			sinTest.setId(new Long("1338"));	
//			ProcedureJudiciaire procedure = (ProcedureJudiciaire) contentieuxManager.getDerniereAudienceProcedure(sinTest, "55555555");
			//ProcedureJudiciaire procedure = (ProcedureJudiciaire) contentieuxManager.getDerniereAudienceProcedure(dossierRente.getRefSinistre(), mouvement.getRefJudiciaire());
			ProcedureJudiciaire procedure = (ProcedureJudiciaire) contentieuxManager.getDerniereAudienceProcedure(dossierRente.getRefSinistre());
			if(procedure!=null){
				String numDossier = procedure.getNumeroDossierTribunal();
				qtcEdition.setReferenceDossierTribunal((numDossier!=null) ? numDossier:"" );
				Date dateAccident = dossierRente.getRefSinistre().getRefEvenement().getDateAccident();
				dateAccident = (dateAccident!=null) ? dateAccident:null;
				qtcEdition.setDateAccident( DateUtils.dateToString("dd/MM/yyyy", dateAccident));
				Date dateDecision = procedure.getDerniereAudience().getDateDecision();
				dateDecision = (dateDecision!=null) ? dateDecision:null;
				if(dateDecision!= null){
					qtcEdition.setDateDecision(DateUtils.dateToString("dd/MM/yyyy", dateDecision ));
				}else{
					qtcEdition.setDateDecision("      ");
				}
				String ville = procedure.getRefJuridiction().getRefVille().getLibelle();
				qtcEdition.setVilleTribunal((ville!=null) ? ville.trim():"" );
			}
			
		} catch (FonctionnelleException e) {
			logger.error(IMessageException.EXP_PROCEDURE_INEXISTANTE, e);
		}	
		catch (Exception e) {
			logger.error("Une erreur s'est produite :", e);

		}
		
	}
	
	@Override
	protected boolean imprimer(IValueObject vo) {
		return false;
	}
}
