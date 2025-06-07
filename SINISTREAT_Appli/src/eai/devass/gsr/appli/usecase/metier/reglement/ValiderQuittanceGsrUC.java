package eai.devass.gsr.appli.usecase.metier.reglement;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rmawatanya.reglement.application.metier.valueobjects.QuittanceVO;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.commun.appli.uc.SimpleBaseUC;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.SituationQuittanceGsr;
import eai.devass.gsr.appli.prm.EtatQuittance;
import eai.devass.gsr.appli.prm.NatureQuittance;
import eai.devass.gsr.appli.reglegestion.BaseRG;
import eai.devass.gsr.appli.valueobjects.metier.reglement.QuittanceGsrVO;
import eai.devass.gsr.appli.valueobjects.metier.reglement.QuittancePositionnementVO;
import eai.devass.services.IAppelService;
import eai.devass.services.ServicesExternes;
import eai.devass.services.impl.AppelServiceManager;
import eai.devass.sinistreat.appli.utils.entites.IParam;

public class ValiderQuittanceGsrUC extends SimpleBaseUC {
	
	protected void execute(IValueObject entite, HashMap params) throws Exception {
		
		QuittancePositionnementVO quittancePositionnementVO = (QuittancePositionnementVO) entite;
		if(quittancePositionnementVO == null) {
			throw new ExceptionMetier("La liste des quittances à traiter est obligatoire !!!");
		}
		
		String operateur = quittancePositionnementVO.getOperateur();
		List<QuittanceGsrVO> listQuittance = quittancePositionnementVO.getListQuittanceGsrVO();
		if(commonUtils.isEmpty(listQuittance)) {
			throw new ExceptionMetier("La liste des quittances à traiter est obligatoire !!!");
		}
		
		// New session
		Session session = getNewSession();
		try {
			IAppelService appelService = new AppelServiceManager();
			QuittanceGsr quittanceGsr = null;
			BaseRG baseRG = new BaseRG();
			Transaction tx = null;
			Mouvement mouvement = new Mouvement();
			baseRG.setMouvementDB(mouvement);
			QuittanceVO quittanceRes = null;
			SituationQuittanceGsr situationQuittanceGsr = null;
			for(QuittanceGsrVO curQuittanceGsrVO : listQuittance) {
				
				try {
					tx = session.beginTransaction();
					quittanceGsr = (QuittanceGsr) getSession().get(QuittanceGsr.class,
							curQuittanceGsrVO.getId());
					mouvement.setRefRentier(quittanceGsr.getRefRentier());
					baseRG.setQuittanceGsr(quittanceGsr);
					
					// Set etat de la quittance
					situationQuittanceGsr = quittanceGsr
							.getCurSituationQuittanceGsr(EtatQuittance.En_instance);
					situationQuittanceGsr.setOperateur(operateur);
					quittanceGsr.setDatEmission(new GregorianCalendar());
					session.save(situationQuittanceGsr);
					session.flush();
					
					// Appel de service emission quittance
					//171014 : Poour la cas prothese, le service est celui de l'AT
					if(NatureQuittance.Prothese.getId() == quittanceGsr.getRefNatureQuittance().getId()) {
						quittanceGsr
								.setCodeServiceOrdonnateur(IParam.CODE_SERVICE_ORDONNATEUR_AT_GRAVE);
					}
					quittanceRes = (QuittanceVO) appelService.appelService(
							ServicesExternes.EMISSION_QUITTANCE,
							(EntiteBO) quittanceGsr, "1");
	
					// Set num quittance
					quittanceGsr.setNumeroQuittance(quittanceRes.getNumQuittance());
					tx.commit();
				
				} catch(Exception e) {
					tx.rollback();
				} 
				
			}
		
		} catch(Exception e) {
			logger.error("problème technique",e);
			
		} finally {
			session.close();
		}
		
		
	}

	
	
	@Override
	public boolean isTransactionnal() {
		return false;
	}

}


