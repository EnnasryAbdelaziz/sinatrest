package eai.devass.gsr.appli.usecase.parametrage.ihm.mouvement;

import java.sql.Blob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.core.ServicesFactory;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.gsr.appli.manager.metier.mouvements.MouvementManager;
import eai.devass.gsr.appli.manager.metier.reglement.QuittanceGsrManager;
import eai.devass.gsr.appli.manager.metier.transverse.TransverseManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtAnnulation;
import eai.devass.gsr.appli.modele.metier.mouvements.RentierMvt;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.EtatRentier;
import eai.devass.gsr.appli.modele.parametrage.MvtEtatRentier;
import eai.devass.gsr.appli.modele.parametrage.SituationMouvement;
import eai.devass.gsr.appli.prm.NatureQuittance;
import eai.devass.gsr.appli.usecase.parametrage.ihm.InitAbstractMouvementUC;
import eai.devass.gsr.appli.valueobjects.parametrage.BeanDTOVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;


@SuppressWarnings("all")
public class InitMvtAnnulationUC extends InitAbstractMouvementUC {

	//private int nbrQtcAnnule = 0;
	//private Date dateDebut = null;
	//private Date dateFin = null;
	//private Double totalMntQtc = 0D;
	
	HashMap<Long,Rentier> mapRentiers =new HashMap<Long, Rentier>();
	
	List<QuittanceGsr> listQuittance =null;
	
	public void executeUC(IValueObject entite, HashMap params) throws Exception {

		TransverseManager transverseManager = (TransverseManager) ServicesFactory
				.getService(TransverseManager.class);
		
		MouvementManager mouvementManager = (MouvementManager) ServicesFactory
		.getService(MouvementManager.class);
		
		QuittanceGsrManager quittanceManager = (QuittanceGsrManager) ServicesFactory
		.getService(QuittanceGsrManager.class);
		try {
			
			BeanDTOVO beanDTOVO = (BeanDTOVO) entite;
			String idRentier = beanDTOVO.getIdRentier();
			if(!commonUtils.isNumeric(idRentier)) {
				throw new ExceptionMetier("Le rentier ne peut être vide (null)");
			}
			// Recuperer la date de la dernier echeance regle
			Rentier rentierPrincipale = new Rentier(Long.valueOf(idRentier));		

			Mouvement mouvement = mouvementManager.getDernierMouvementValidee(rentierPrincipale.getId());
			
			if(mouvement == null){
				throw new ExceptionMetier("Annulation impossible, aucun mouvement n'a été validé auparavant.");
			}
			
			
			List<SituationMouvement> listSitMvt = mouvement.getRefSituationMouvement();
			String operateur = null;
			if(!commonUtils.isEmpty(listSitMvt)) {
				operateur = listSitMvt.get(0).getOperateur();
			}
			
			Calendar calLastEcheance = transverseManager
					.getDateDerniereEcheanceRegleeParDossier(Long.valueOf(idRentier));
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			paramToConverter.put("dateDerniereEcheance", (calLastEcheance == null) ? "" 
					: dateFormat.format(calLastEcheance.getTime()));
			List<Rentier> listeRentiers = transverseManager.getListRentierByRentier(idRentier);
			
			String idMouvement = String.valueOf(mouvement.getId());
			
		//	String idMouvement = "1567";
			//récupération du dernier mouvement avant validation
			Blob objetBlob = null;
			try {
				
			objetBlob = (Blob)mouvementManager.getAvantDernierMouvement(idMouvement);
			}
			catch(HibernateException e){
				throw new ExceptionMetier("Un problème est survenu lors de la récupération du mouvement archivé. ");	
			}
			if(objetBlob==null){
				throw new ExceptionMetier("le mouvement à annuler n'a pas été archivé correctement.");	
			}
			
			Mouvement mvtHistorise = (Mouvement)CommonUtils.deserialise(objetBlob.getBinaryStream());
			if(mvtHistorise!=null && mvtHistorise.getRefsQuittance()!=null){
				listQuittance = mvtHistorise.getRefsQuittance();
			}
//			if(rentier.getLienParente()==0){
//				
//				//dans le cas d'une victime
//				Double mntRenteAncienne = mvtHistorise.getRefRentier().getMontantRenteTrimestrielle();
//				//Montant percu	
//				Double mntPercu= getMontantPercu(mvtHistorise.getRefsQuittance());
//				//Montant quittance réglés
//				Double mntRegle = quittanceManager.getSommeQuittancesTrimistrielle(rentier,mouvement.getDatEtat());
//				
//				rentier.setMntAncienneRente(mntAncienneRente);
//				rentier.setMontantPercu(mntPercu);
//				rentier.setMntQuittancesRegles(mntRegle);
//				
//				
//			}

			//dans le cas de dossier rente
			//correction sonar Dead store to local variable.
			List<Rentier> listAncienRentiers=new ArrayList<Rentier>();
			if(mvtHistorise!=null && mvtHistorise.getRefRentier()!=null 
					&& mvtHistorise.getRefRentier().getRefDossierRente()!=null && mvtHistorise.getRefRentier().getRefDossierRente().getRefsRentier()!=null){
			listAncienRentiers = mvtHistorise.getRefRentier().getRefDossierRente().getRefsRentier();
			}
			List<Rentier> allRentiers = getAllRentiers(listAncienRentiers,listeRentiers);
			List<Rentier> list = new ArrayList<Rentier>();
			//correction sonar Dead store to local variable.
			for(Rentier rentier:allRentiers){
				
		        if(rentier!=null){
				Rentier ancienRentier = mapRentiers.get(rentier.getId());
				
				rentier.setMntAncienneRente(ancienRentier.getMontantRenteTrimestrielle());
				rentier.setMontantPercu(getMontantPercu(listQuittance,rentier.getId()));
				rentier.setMntQuittancesRegles(quittanceManager.getSommeQuittancesTrimestrielle(rentier,mvtHistorise.getDatEtat()));
		        }
//				if(rentier.getTuteur()) {
//					
//					rentier.getRefTuteur().getNom()
//					
//					
//				}else{
//					
//					
//				}
				Rentier rentierNew = new Rentier();
				DossierRente dossierRente = new DossierRente();
				dossierRente.setId((rentier!=null && rentier.getRefDossierRente()!=null) ? rentier.getRefDossierRente().getId(): 0l);
				
				EtatRentier etatRentier = null;
				if(rentier!=null && rentier.getRefDossierRente() != null 
						&& rentier.getRefDossierRente().getRefEtatRentier() != null) {
					etatRentier = rentier.getRefDossierRente().getRefEtatRentier();
				}
				
				dossierRente.setRefEtatRentier(etatRentier);
				BeanUtilsBean.getInstance().copyProperties(rentierNew,rentier );
				rentierNew.setRefDossierRente(dossierRente);
				rentierNew.setRefsProtheses(null);
				list.add(rentierNew);
				
			}
			
			List<RentierMvt> RentierMvtList = new ArrayList<RentierMvt>();
		//	Rentier rentierNew = null;
			
			for(Rentier rentier:list){
				
				RentierMvt rentierMvt = new RentierMvt(new MvtAnnulation(mouvement.getId()));				
			//	rentierNouveau = new Rentier();
				rentierMvt.setRefRentier(rentier);
			//	rentierMvt.setRefMouvement(new Mouvement(mouvement.getId()));
				
				//rentierMvt.setAncienIPP();
				rentierMvt.setNouveauIPP(rentier.getIppTauxRente());
				rentierMvt.setMntNouvelleRente(rentier.getMontantRenteTrimestrielle());
				rentierMvt.setMntAncienneRente(rentier.getMntAncienneRente());
				
				EtatRentier etatRentier = null;
				if (rentier.getRefDossierRente() != null
						&& rentier.getRefDossierRente().getRefEtatRentier() != null) {
					etatRentier = rentier.getRefDossierRente().getRefEtatRentier();
				}
				
				long ancienEtatDossierRente = (etatRentier != null) ? etatRentier.getId() : 0l;
				rentierMvt.setAncienEtatDossierRente(ancienEtatDossierRente);
				rentierMvt.setAncienneReserveMathematique(rentier.getReserveMathematique());
				rentierMvt.setRefAncienEtatRentier(new MvtEtatRentier(rentier.getRefEtatRentier().getId()));
				
				rentierMvt.setMontantPercu(rentier.getMontantPercu());		
				rentierMvt.setMntQuittancesRegles(rentier.getMntQuittancesRegles());
				
				rentierMvt.setMntTropMoinsPercu(0D);
				
				RentierMvtList.add(rentierMvt);
			}
			


			paramToConverter.put("refsRentierMvt", RentierMvtList);
			paramToConverter.put("refTypeMouvement", mouvement.getRefTypeMouvement());
			paramToConverter.put("refMouvement", mouvement.getId());
			paramToConverter.put("datEtat",( mouvement.getDatEtat()== null) ? "" 
					: dateFormat.format(mouvement.getDatEtat().getTime())); 
			paramToConverter.put("operateur", operateur);  
			
		} 
		
		catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		}

	}
	public boolean isTransactionnal() {
		return false;
	}

	private Double getMontantPercu(List<QuittanceGsr> quittances,Long idRentier){
		
		Double montantPercu = 0D;
		if(quittances!= null && quittances.size()>0){
			for(QuittanceGsr quittance:quittances){
				
				//quittance d'équilibre exclue
				if(quittance.getRefRentier()!=null && quittance.getRefNatureQuittance()!=null )
				{
					if(quittance.getRefRentier().getId()==idRentier &&
					quittance.getBeneficiaire()!="AT"
					&& quittance.getRefNatureQuittance().getId()!=NatureQuittance.Rente_periodique.getId()	
					){
					
					montantPercu = montantPercu + quittance.getMontant();
					}
				}
			}
		}
		return montantPercu;

		
	// faire une méthode pour récupere le montant de quittance réglé après une date donnée.	
	}
	
	
	
	
	private HashMap alimenterRentier(List<Rentier> listRentiers){
		
		//HashMap<Long,Rentier> mapRentiers = new HashMap<Long, Rentier>();
		
		if(listRentiers.isEmpty() || listRentiers.size()==0){
			
			return null;
			
		}else{
			for(Rentier rentier:listRentiers){
				mapRentiers.put(rentier.getId(), rentier);
			}
		}
		
		return mapRentiers;

	}
	
	private List<Rentier> getAllRentiers(List<Rentier> listAncienRentiers, List<Rentier> listRentiers){
		
		
		List<Rentier> allRentiers = new ArrayList<Rentier>();
		
		int sizeAncienneList = listAncienRentiers.size();
		int sizeList = listRentiers.size();
		
		if(sizeAncienneList>sizeList){	

			 for (Rentier rentier : listAncienRentiers){
			    if (!listRentiers.contains(rentier)){
			    	listRentiers.add(rentier);
			    }
			    
			    mapRentiers.put(Long.valueOf(rentier.getId()), rentier);
			 }
			
		}else if(sizeAncienneList<sizeList){
			
			 for (Rentier rentier : listRentiers){
				    if (!listAncienRentiers.contains(rentier)){
				    	listAncienRentiers.add(rentier);
				    	mapRentiers.put(Long.valueOf(rentier.getId()), new Rentier(rentier.getId()));
				    }
				 }
			
		}else if(sizeAncienneList==sizeList){
			
			 for (Rentier rentier : listAncienRentiers){	
				 //wmos: correction sonar 26/09/2014
				    mapRentiers.put(Long.valueOf(rentier.getId()), rentier);
				 }
			
		}
		allRentiers.addAll(listRentiers);
		
		return allRentiers;
		
	}
	
	
	
	
	
	
	
	
}