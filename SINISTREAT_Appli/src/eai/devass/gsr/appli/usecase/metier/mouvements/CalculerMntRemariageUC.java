package eai.devass.gsr.appli.usecase.metier.mouvements;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.util.DateUtils;
import eai.devass.gsr.appli.manager.metier.transverse.TransverseManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtRemariage;
import eai.devass.gsr.appli.utile.CommonGsrUtils;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.MvtRemariageVO;

public class CalculerMntRemariageUC extends FacadeServiceUseCase {

	
	protected void doExecuter(IValueObject entite, HashMap params)
			throws Exception {

		TransverseManager transverseManager = (TransverseManager) ServicesFactory
				.getService(TransverseManager.class);
		
		MvtRemariageVO remariageVO = (MvtRemariageVO) entite;
		if(remariageVO == null) {
			throw new ExceptionMetier("Le mouvement ne peut être null !!");
		}
		
		String idRentier = remariageVO.getRefRentier();
		if(!CommonGsrUtils.isNumeric(idRentier)) {
			throw new ExceptionMetier("L'identifient du rentier ne peut être null !!");
		}
		
		String dateEcheanceSt = remariageVO.getEcheanceEffective();
		String mntRente = remariageVO.getMntRente();
		if(!CommonGsrUtils.isNumeric(mntRente)) {
			throw new ExceptionMetier("Le montant de la rente est obligatoire !!");
		}
		
		mntRente = mntRente.replaceAll(",", ".").replaceAll(" ", "");
		Double mntRachatAnnuitee = Double.valueOf(mntRente) * 12;
		MvtRemariage mvtRemariage = new MvtRemariage();
		mvtRemariage.setMntRachatAnnuitee(mntRachatAnnuitee);
		if(!DateUtils.isDate(dateEcheanceSt)) {
			throw new Exception("La date écheance non valide");
		}
		
		Calendar dateEcheance = new GregorianCalendar();
		dateEcheance.setTime(DateUtils.parseDate(dateEcheanceSt));
		String dateRemariageSt = remariageVO.getDatRemariage();
		if(!DateUtils.isDate(dateRemariageSt)) {
			throw new ExceptionMetier("La date du remariage est obligatoire !!");
		}
		
		Calendar dateRemariage = new GregorianCalendar();
		dateRemariage.setTime(DateUtils.parseDate(dateRemariageSt));
		Rentier rentier = new Rentier(Long.valueOf(idRentier));
		
		// Recuperer la liste des quittances regler entre dateRemariage et dateEcheance 
		Double mntIndu = transverseManager.sommeQuittancesReglees(rentier.getId(), dateRemariage);
		mntIndu = (mntIndu == null) ? 0D : mntIndu;		
		
		mvtRemariage.setMntIndu(mntIndu);
		mvtRemariage.setMntARegler(mntRachatAnnuitee - mntIndu);		
		this.addResultItem(mvtRemariage);
	}

	

}