package eai.devass.gsr.appli.usecase.metier.reglement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import eai.devass.gsr.appli.manager.metier.transverse.TransverseManager;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;

public class SuppressionMontantInduUC extends FacadeServiceUseCase {
	
	protected Map<String, Object> paramToConverter = new HashMap<String, Object>();
	/**
	 * Methode qui execute le use case de consultation d' un objet
	 * 
	 * @param entite
	 *            l' objet Ã  consulter
	 * @param params
	 *            paramatere additionel qu 'on peut passer au Use Case
	 * @throws Exception
	 *             Probleme lors de l' execution du Use case
	 */
	protected void doExecuter(IValueObject entite, HashMap params)
			throws Exception {
		QuittanceGsr qtc = (QuittanceGsr) this.getItem(QuittanceGsr.class);

		QuittanceGsr totalQTC = new QuittanceGsr();
		TransverseManager transverseManager = new TransverseManager();
		Double sommeQuittancesReglees = 0D;
		Long nombreTrimestre = 0L;

		Object[] listDoubles = transverseManager.sommeQuittancesTrimReglees(qtc
				.getRefRentier().getId(),qtc.getDateCreation());
		if (listDoubles != null) {
			sommeQuittancesReglees = (listDoubles[0] == null) ? 0D
					: (Double) listDoubles[0];
			nombreTrimestre = (listDoubles[1] == null) ? 0L : (Long) listDoubles[1];
		}
		//Anomalie 92
		Calendar calLastMaxDate = transverseManager
		.getMaxDateQuittancesTrimReglees(qtc.getRefRentier().getId());
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		paramToConverter.put("maxDateQuittancesTrimReglees", (calLastMaxDate == null) ? ""
				: dateFormat.format(calLastMaxDate.getTime()));
		//Fin
		// somme Arrerage regle au niveau du sinistre
		Double sumArrerageRegle = transverseManager.doSommeArrerageRegle(qtc.getRefRentier().getId());
		sommeQuittancesReglees = sommeQuittancesReglees + sumArrerageRegle;
		totalQTC.setMontant(sommeQuittancesReglees);
		totalQTC.setExercice(String.valueOf(nombreTrimestre));
		//anomali 92
//		totalQTC.setExercice(TypeConverter.getInstance().calendarToString(calLastMaxDate));
		//Fin
		
		//Recuperer la derniere echeance réglée avant une date
		if(qtc.getRefRentier() != null && qtc.getDateCreation() != null){
			Calendar derniereEchance = transverseManager.getDerniereEcheanceRegleeAvantUneDate(qtc.getRefRentier().getId(), qtc.getDateCreation());
			totalQTC.setDatEmission(derniereEchance);
		}
		
		this.addResultItem(totalQTC);
	}

	/**
	 * Methode pour activer le service de transaction
	 * 
	 * @returns soit true pour activer le service de transaction ou false pour
	 *          le desactiver
	 */
	public boolean isTransactionnal() {
		return false;
	}

	/**
	 * Methode pour activer le service de trace
	 * 
	 * @returns soit true pour activer le service de trace ou false pour le
	 *          desactiver
	 */
	public boolean isTracable() {
		return false;
	}

}
