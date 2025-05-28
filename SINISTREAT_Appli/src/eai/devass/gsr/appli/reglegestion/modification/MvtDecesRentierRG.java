/**
 * 
 */
package eai.devass.gsr.appli.reglegestion.modification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.commun.appli.rg.ASkipRG;
import eai.devass.gsr.appli.modele.metier.mouvements.Heritier;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtDecesRentier;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.EtatQtc;
import eai.devass.gsr.appli.modele.parametrage.NatureQtcGsr;
import eai.devass.gsr.appli.modele.parametrage.SituationQuittanceGsr;
import eai.devass.gsr.appli.prm.EnumStatutHeritier;
import eai.devass.gsr.appli.prm.EtatQuittance;
import eai.devass.gsr.appli.prm.NatureQuittance;
import eai.devass.sinistreat.appli.utils.Message.IMessage;

/**
 * @author elfaismo
 *
 */
public class MvtDecesRentierRG extends MouvementRG {
	
	/**
	 * RG11/FGSR-10.7: Quittances d’équilibre à générer à la création du mouvement. 
	 * Le montant correspondant.
	 */
	
	MvtDecesRentier mouvement =null ;
	Boolean isQuittancesVide = false;

	@ASkipRG(property="genererQuittance", value="true")
	public void regleGestion000AnnulerQuittanceReglement(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
	/*	
		mouvement = (MvtDecesRentier) entiteBO;
		//rentier = mouvement.getRefRentier();

		//vérifier les info de qtc et de reglement
		listQtc = mouvement.getRefsQuittance();

		List<Heritier> listHeritier = mouvement.getRefsHeritier();
		
		if(commonGsrUtils.isEmpty(listHeritier)) {
			
			return;
		}
		
		List<QuittanceGsr> listQuittanceAnnule = new ArrayList<QuittanceGsr>();
		for(Heritier heritier:listHeritier){
			

			if(heritier.getRefStatutHeritier()!= null && heritier.getRefStatutHeritier().getId()==4)
			{
				
				if(heritier.getRefQuittanceGSR()!=null) {
					listQuittanceAnnule.add(heritier.getRefQuittanceGSR());
				}
			}

			
		}

		int nbreQuitanceAnnulles = listQuittanceAnnule.size();
		
		if(nbreQuitanceAnnulles==listQtc.size()){
			
			isQuittancesVide = true;

		}
		if(listQuittanceAnnule != null && listQuittanceAnnule.size()>0 ){
		annulerQuittances(listQuittanceAnnule);
		}
	*/
	}
	
	
	public void regleGestion001GenererQuittanceReglement(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		mouvement = (MvtDecesRentier) entiteBO;
		//rentier = mouvement.getRefRentier();

		//vérifier les info de qtc et de reglement
		listQtc = mouvement.getRefsQuittance();

	
	//	Boolean isQuittancesVide = false;
		if(commonGsrUtils.isEmpty(listQtc)) {
			
			isQuittancesVide = true;
		}

		//for(QuittanceGsr quittanceGsr : listQtc) {
			 
			//Alimenter le beneficiaire de la quittance.

		//}
		
		if(mouvement.getMntGlobalVersee()>=0 && !isQuittancesVide){
			
			mouvement.setGenererQuittance(true);
			
			for(QuittanceGsr quittanceGsr:listQtc){

				NatureQtcGsr natureQtc = new NatureQtcGsr(NatureQuittance.Prorata_rente.getId());
				quittanceGsr.setRefNatureQuittance(natureQtc);
				quittanceGsr.setCodePrestation(NatureQuittance.Prorata_rente.getRubrique());

		}
		

		}else{
			mouvement.setGenererQuittance(false);
			
			//generer Message
			//throw new ExceptionMetier(IMessageException.EXP_TROP_PRECU);
			//ecrire message d'information
			((Map) params.get("outMapMessage")).put(IMessage.INFO,IMessage.MSG_GSR_TROP_PERCU );
			
		}

	
	}
	
	
	/**
	 * Annuler la liste des quittances en entrée.
	 * @param quittancesAnnules
	 * @throws ExceptionMetier
	 */
	private void annulerQuittances(List<QuittanceGsr> quittancesAnnules) throws ExceptionMetier{
		
		if(!commonGsrUtils.isEmpty(quittancesAnnules) && quittancesAnnules.size()>0){
		for(QuittanceGsr quittanceGsr : quittancesAnnules) {
			QuittanceGsr quittanceGsrDB = null;

			try {
				 quittanceGsrDB = quittanceManager.getQuittanceByID(quittanceGsr.getId());
				
			} catch (Exception e) {
				throw new ExceptionMetier("Impossible de trouver la quittance");
				
			}
			SituationQuittanceGsr situationQuittance = new SituationQuittanceGsr();
			EtatQtc refEtatQtc=new EtatQtc();
			
			situationQuittance.setDateEtat(new GregorianCalendar().getTime());
			refEtatQtc.setId(EtatQuittance.Annulee.getId());
			refEtatQtc.setDateCreation(new GregorianCalendar());
			situationQuittance.setRefEtatQtc(refEtatQtc);
			situationQuittance.setOperateur(mouvement.getOperateur());
			situationQuittance.setRefQuittanceGsr(quittanceGsrDB);
		
			quittanceGsrDB.setDatEtat(new GregorianCalendar());
			quittanceGsrDB.setRefEtatQtc(refEtatQtc);

			getSession().save(situationQuittance);

			}

		
	}
	}
	
	

//	/**
//	 * Supprimer les herities
//	 * @param quittancesAnnules
//	 * @throws ExceptionMetier
//	 */
//	private void supprimerHeritiers(List<Heritier> heritiersSupprimes) throws ExceptionMetier{
//		HeritierManager heritierManager = new HeritierManager();
//		if(!commonGsrUtils.isEmpty(heritiersSupprimes) && heritiersSupprimes.size()>0){
//		for(Heritier heritier : heritiersSupprimes) {
//			Heritier heritierBD = null;
//			try {
//				heritierBD = heritierManager.getHeritierById(heritier.getId());
//				
//			} catch (Exception e) {
//				throw new ExceptionMetier("Impossible de trouver la quittance");
//				
//			}
//			StatutHeritier refStatutHeritier = new StatutHeritier();
//			refStatutHeritier.setId(4);
//			heritierBD.setRefStatutHeritier(refStatutHeritier);
//
//			}
//		}
//	}
	
	
	public void regleGestion993ViderListHeritier(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		MvtDecesRentier mouvement = (MvtDecesRentier) entiteBO;
		List<Heritier> newListHeritiers = new ArrayList<Heritier>();

			List<Heritier> listHeritiers = mouvement.getRefsHeritier();
			if(commonGsrUtils.isEmpty(listHeritiers)) {
				return;
			}	

		for(Heritier heritier:listHeritiers ){
			
			if(heritier.getRefStatutHeritier().getId()==EnumStatutHeritier.Supprimee.getId()){
			getSession().delete(heritier);
			//listHeritiers.remove(heritier);
			}else{
				newListHeritiers.add(heritier);
			}

	
		}	
			mouvement.setRefsHeritier(newListHeritiers);

	}

	
	public void regleGestion994ViderListQtc(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		QuittanceGsr quittanceGsrDB = null;
		List<QuittanceGsr> newListQtc	= new ArrayList<QuittanceGsr>();

		for (QuittanceGsr quittanceGsr : listQtc){
			if(quittanceGsr.getRefEtatQtc().getId() ==EtatQuittance.Supprimee.getId()){
				
				try {
					 quittanceGsrDB = quittanceManager.getQuittanceByID(quittanceGsr.getId());
					
				} catch (Exception e) {
					throw new ExceptionMetier("Impossible de trouver la quittance");
					
				}
				quittanceGsrDB.setRefEtatQtc(new EtatQtc(EtatQuittance.Supprimee.getId()));
			//	getSession().saveOrUpdate(quittanceGsrDB);
				if(quittanceGsrDB.getRefSituationQuittanceGsr()!=null && quittanceGsrDB.getRefSituationQuittanceGsr().size()>0){
					for(SituationQuittanceGsr situationQtc :quittanceGsrDB.getRefSituationQuittanceGsr()){
						
						getSession().delete(situationQtc);
					}
				}
				getSession().delete(quittanceGsrDB);

				}else{
					newListQtc.add(quittanceGsr);
					
				}
			
		}
		mouvement.setRefsQuittance(newListQtc);
	}
	/**
	 * rataché chaque heritier à la quittance correspondante
	 * @param entiteBO
	 * @param params
	 * @throws ExceptionMetier
	 */
	
	public void regleGestion995AlimenterListHeritier(EntiteBO entiteBO, Map params) throws ExceptionMetier {

		MvtDecesRentier mouvement = (MvtDecesRentier) entiteBO;
			List<Heritier> listHeritiers = mouvement.getRefsHeritier();
			List<QuittanceGsr> listQtc = mouvement.getRefsQuittance();
			if(commonGsrUtils.isEmpty(listHeritiers)) {
				return;
			}
			if(!isQuittancesVide){
				
				Collections.sort(listHeritiers);
				Collections.sort(listQtc);
				
				for(int i = 0; i < listHeritiers.size(); i++ ){
					
					Heritier heritier = listHeritiers.get(i);
					
					//System.out.println("itération numéro :" + i);
					logger.info("itération numéro :" + i);
					//System.out.println("Quittance numéro :" + listQtc.get(i));
					logger.info("Quittance numéro :" + listQtc.get(i));
					//System.out.println("Heritier ID :" + heritier.getId());
					logger.info("Heritier ID :" + heritier.getId());
					
					if( heritier.getRefQuittanceGSR()!= null
							&& heritier.getRefQuittanceGSR().getId()>0){
						
						//System.out.println("Quittance heritier ID : " + heritier.getRefQuittanceGSR().getId());
						logger.info("Quittance heritier ID : " + heritier.getRefQuittanceGSR().getId());
						
						if(listQtc.contains(heritier.getRefQuittanceGSR())){
							

							//System.out.println("Quittance existe : " + heritier.getRefQuittanceGSR().getId());
							logger.info("Quittance existe : " + heritier.getRefQuittanceGSR().getId());
							
							QuittanceGsr quittanceGsr = quittanceManager.getQuittanceByID(heritier.getRefQuittanceGSR().getId());
							
							heritier.setRefQuittanceGSR(quittanceGsr);
							heritier.setRefMvtDecesRentier(mouvement);
							getSession().update(heritier);
							getSession().update(quittanceGsr);

							}

					}else{
						for (int j = 0; j < listQtc.size(); j++) {
								QuittanceGsr quittanceGsr = listQtc.get(j);
								if(quittanceGsr.getOrdre().equals(heritier.getOrdreQtc())){
									heritier.setRefQuittanceGSR(quittanceGsr);
									heritier.setRefMvtDecesRentier(mouvement);
									getSession().save(heritier);
									getSession().save(quittanceGsr);	
									break;
								}
						}	//System.out.println("Quittance numéro :" + quittanceGsr.getId());

					}

				}
			}
	}
	
	
	
	
	/**
	 * rataché chaque heritier à la quittance correspondante
	 * @param entiteBO
	 * @param params
	 * @throws ExceptionMetier
	 */
	/*
	public void regleGestion9930MAJListHeritier(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
	
		
		MvtDecesRentier mouvement = (MvtDecesRentier) entiteBO;
		
		List<Heritier> listHeritiers = mouvement.getRefsHeritier();
		
			if(commonGsrUtils.isEmpty(listHeritiers)) {
				return;
			}
			if(!isQuittancesVide){
			
			for (int i = 0; i < listQtc.size(); i++) {
				System.out.println("iteration quitance :"+ i);
				System.out.println(listQtc.get(i));
				QuittanceGsr quittanceGsr = listQtc.get(i);
				
				System.out.println("quitance ID : "+ quittanceGsr.getId());
				
				for (int j = 0; j < listHeritiers.size(); j++) {
					System.out.println("iteration Heritier :"+ j);
					System.out.println("Heritier ID : " + listHeritiers.get(i).getId());
					
					Heritier heritier = listHeritiers.get(i);
					System.out.println("Heritier ID :" + heritier.getId());
					System.out.println("Quittance heritier ID : " + heritier.getRefQuittanceGSR().getId());
					
					if(heritier.getRefQuittanceGSR() != null && quittanceGsr != null &&
							heritier.getRefQuittanceGSR().getId()==quittanceGsr.getId())
					{
						heritier.setRefQuittanceGSR(quittanceGsr);
						heritier.setRefMvtDecesRentier(mouvement);
						getSession().saveOrUpdate(heritier);
						getSession().saveOrUpdate(quittanceGsr);
						
						break;
					}else{
						if(i==j){

								heritier.setRefQuittanceGSR(quittanceGsr);
								heritier.setRefMvtDecesRentier(mouvement);
								getSession().saveOrUpdate(heritier);
								getSession().saveOrUpdate(quittanceGsr);

						}
					}
				}
			}

}
	}*/
}
