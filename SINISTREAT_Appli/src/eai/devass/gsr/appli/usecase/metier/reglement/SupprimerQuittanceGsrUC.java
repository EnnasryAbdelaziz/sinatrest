package eai.devass.gsr.appli.usecase.metier.reglement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import com.rmawatanya.reglement.application.metier.valueobjects.MouvementQuittanceVO;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.uc.SimpleBaseUC;
import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.SituationQuittanceGsr;
import eai.devass.gsr.appli.prm.EtatQuittance;
import eai.devass.gsr.appli.valueobjects.metier.reglement.QuittanceGsrVO;
import eai.devass.services.ServicesExternes;
import eai.devass.services.impl.AppelServiceManager;
import eai.devass.sinistreat.appli.utils.IConstantes;

public class SupprimerQuittanceGsrUC extends SimpleBaseUC {
	
	protected void execute(IValueObject entite, HashMap params) throws Exception {
		
		QuittanceGsrVO quittanceGsrVO = (QuittanceGsrVO) entite;
		if(quittanceGsrVO == null) {
			throw new ExceptionMetier("La quittance à traiter est obligatoire !!!");
		}
		
		String operateur = quittanceGsrVO.getOperateur();
		if(quittanceGsrVO.getId() == 0) {
			throw new ExceptionMetier("L'identifiant de la quittance est obligaoire");
		}

		// Motif
		String motif = ((QuittanceGsrVO) entite).getDetailMotif();
		if(CommonUtils.isEmpty(motif)) {
			throw new ExceptionMetier("Le motif d'annulation de la quittance est obligaoire");
		}
		
		QuittanceGsr quittanceGsr = (QuittanceGsr) getSession().get(QuittanceGsr.class,
				quittanceGsrVO.getId());
		
		long idEtatQtc = quittanceGsr.getRefEtatQtc().getId();
		SituationQuittanceGsr situationQuittanceGsr = quittanceGsr
				.getCurSituationQuittanceGsr(EtatQuittance.Annulee);
		situationQuittanceGsr.setOperateur(operateur);
		quittanceGsr.setDatEmission(new GregorianCalendar());
		getSession().save(situationQuittanceGsr);
		getSession().flush();
		
		// Si la quittance est reglée ou emise !!, il faut annuler la quittance comptable
		if(EtatQuittance.Reglee.getId() == idEtatQtc 
				|| EtatQuittance.En_instance.getId() == idEtatQtc ) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String date = dateFormat.format(new Date());
			MouvementQuittanceVO mouvementQuittanceVO = new MouvementQuittanceVO();
			mouvementQuittanceVO.setNumQuittance(quittanceGsr.getNumeroQuittance());
			mouvementQuittanceVO.setMotifEtat(motif);
			mouvementQuittanceVO.setDatEtat(date);

			// Appel de service
			AppelServiceManager appelService = new AppelServiceManager();
			appelService.setConvert(false);
			appelService.appelService(ServicesExternes.ANNULER_QUITTANCE,
					mouvementQuittanceVO, IConstantes.PROFIL_ANNULATION);
		}
		
	}

	
	
	@Override
	public boolean isTransactionnal() {
		return true;
	}

}


