package eai.devass.gsr.appli.reglegestion.suppression;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.commun.appli.rg.ASkipRG;
import eai.devass.commun.appli.rg.RegleGestionUtils;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.SituationMouvement;
import eai.devass.gsr.appli.modele.parametrage.SituationQuittanceGsr;
import eai.devass.gsr.appli.prm.EtatMouvement;
import eai.devass.gsr.appli.prm.EtatQuittance;
import eai.devass.gsr.appli.reglegestion.BaseRG;
import eai.devass.gsr.appli.utile.IMessageException;
/** @author kchakib */


@SuppressWarnings("all")
public class MouvementRG extends BaseRG {
	
	private List<QuittanceGsr> listQuittanceGsr = null;	
	
	public void regleGestion000RunRegleGestion(EntiteBO entiteBO, Map params)
			throws ExceptionMetier {
		
		Mouvement mouvement = (Mouvement) entiteBO;
		long idMouvement = mouvement.getId();
		if (idMouvement == 0) {
			throw new ExceptionMetier("L'identifiant du mouvement ne peut pas être null !!");
		}
		
		try {
			mouvementDB = (Mouvement) getSession().get(Mouvement.class,
					idMouvement);
			
			if (mouvementDB == null) {
				throw new ExceptionMetier("Mouvement non trouvé !!");
			}
		
		} catch (Exception e) {
			throw new ExceptionMetier(e.getMessage());
		}
		
		// Recupere le rentier depuis la BD
		rentierDB = mouvementDB.getRefRentier();		
		if(rentierDB == null) {
			throw new ExceptionMetier(IMessageException.EXP_RENTIER_INTROUVALE);
		}
	}
	
	
	public void regleGestion001RunRegleGestion(EntiteBO entiteBO, Map params)
			throws ExceptionMetier {

		Mouvement mouvement = (Mouvement) entiteBO;
		mouvement.setPersistObject(false);

		// Dans le cas ou la RG specifique du mouvement est non implementer,
		// risque d'un traitement cyclique!!
		if (mouvementDB.equals(mouvement)) {
			throw new ExceptionMetier("Il faut définir la RG validation associé au mouvement : "
							+ mouvementDB.getClass().getSimpleName());
		}

		Map map = new HashMap();
		mouvementDB.setContextRegleGestion(mouvement
				.getContextRegleGestionEnum().getContext());
		map.put(mouvementDB, mouvementDB);
		if (params.get("outMapMessage") != null
				&& map.get("outMapMessage") == null) {
			map.put("outMapMessage", params.get("outMapMessage"));
		}
		try {
			RegleGestionUtils.getInstance().runRegleGestionObjet(map);
		
		} catch (InvocationTargetException e) {
			throw new ExceptionMetier(e.getCause().getMessage());

		} catch (Exception e) {
			throw new ExceptionMetier(e.getMessage());
		}
	}
	
	
	public void regleGestion002getQuittanceGsr(EntiteBO entiteBO, Map params)
			throws ExceptionMetier {

		try {
			listQuittanceGsr = mouvementDB.getRefsQuittance();
			
		} catch (Exception e) {
			throw new ExceptionMetier(e.getMessage());
		}

	}
	
	public void regleGestion003SupprimerMouvement(EntiteBO entiteBO, Map params) throws ExceptionMetier {
//		// Recuperer le mouvement 
//		Mouvement mouvementDB = (Mouvement) getSession().get(Mouvement.class, mouvement.getId());
//		if(mouvementDB == null) {
//			throw new ExceptionMetier("Impossible de récupérer le mouvement id[" + mouvement.getId() + "]");
//		}
		
		SituationMouvement situationMouvement = mouvementDB
				.getCurSituationMouvement(EtatMouvement.Supprimer, null);
		
//		EtatMvt etatMvt = new EtatMvt(EtatMouvement.Supprimer.getId());
//		etatMvt.setDateCreation(new GregorianCalendar());		
//		mouvementDB.setRefEtatMvt(etatMvt);
//		mouvementDB.setDatEtat(new GregorianCalendar());
//		mouvement.setPersistObject(false);
//		// Situatrion mouvement
//		List<SituationMouvement> list = mouvement.getRefSituationMouvement();
//		SituationMouvement situationMouvement = null;
//		if(!commonGsrUtils.isEmpty(list)) {
//			situationMouvement = list.get(0);
//			
//		} else {
//			situationMouvement = new SituationMouvement();
//		}
//		
		situationMouvement.setOperateur(entiteBO.getOperateur());
//		situationMouvement.setRefEtatMvt(etatMvt);
//		situationMouvement.setDateEtat(new Date());
//		situationMouvement.setRefMouvement(mouvement);
		getSession().save(situationMouvement);		
		
	}
	
	@ASkipRG(property="listQuittanceGsr", isEmty="true", bean="this")
	public void regleGestion004SupprimerQuittanceGSR(EntiteBO entiteBO, Map params) throws ExceptionMetier {

		SituationQuittanceGsr situationQuittanceGsr = null;
		for(QuittanceGsr qtc : listQuittanceGsr){
//			EtatQtc etatQtc= new EtatQtc(EtatQuittance.Supprimee.getId());
//			etatQtc.setDateCreation(new GregorianCalendar());
//			qtc.setRefEtatQtc(etatQtc);
			situationQuittanceGsr = qtc.getCurSituationQuittanceGsr(EtatQuittance.Supprimee);
			situationQuittanceGsr.setOperateur(entiteBO.getOperateur());
//			situationQuittanceGsr.setRefEtatQtc(etatQtc);
//			situationQuittanceGsr.setDateEtat(new Date());
//			situationQuittanceGsr.setRefQuittanceGsr(qtc);
			getSession().save(situationQuittanceGsr);		
		}
		
		
	}

	// Pour @ASkipRG !! BEanUtils
	public List<QuittanceGsr> getListQuittanceGsr() {
		return listQuittanceGsr;
	}
	
	
	
}
