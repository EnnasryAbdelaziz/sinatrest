/**
 * 
 */
package eai.devass.gsr.appli.reglegestion.validation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.commun.appli.rg.ASkipRG;
import eai.devass.commun.appli.util.DateUtils;
import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtDecesRentier;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.EtatMvt;
import eai.devass.gsr.appli.modele.parametrage.EtatRentier;
import eai.devass.gsr.appli.modele.parametrage.MotifDecesRentier;
import eai.devass.gsr.appli.modele.parametrage.SituationDossierRentier;
import eai.devass.gsr.appli.modele.parametrage.SituationEtatRentier;
import eai.devass.gsr.appli.prm.EtatMouvement;
import eai.devass.gsr.appli.prm.EtatRente;
import eai.devass.gsr.appli.prm.MotifEtat;
import eai.devass.gsr.appli.reglegestion.BaseRG;
import eai.devass.gsr.appli.utile.IMessageException;

/**
 * @author elfaismo
 *
 */
public class MvtDecesRentierRG extends BaseRG {
	
	
	private Rentier rentier = null;
	private Rentier rentierBD = null;
	List<Rentier> ayantsDroit = null;

	
	
	/**
	 * RG4/FGSR-10.7: Quittances d’équilibre à générer à la validation du mouvement. Le montant correspondant:

RG4.1/FGSR-10.7:
Soit a = âge du défunt à son décès,
et b = âge du défunt au (31/12 de l’exercice du décès – 1)
Si âge 1= âge 2, alors :
Montant des quittances d’équilibre = Capital (a)-([Capital (b-1)-capital (b)]*Nb jours réglés au cours de l^' année du décès)/360
Si âge 1≠âge 2, alors :
Montant des quittances d’équilibre = Capital (a)-([Capital (a-1)-capital (a)]*Nb jours réglés au cours de l^' année du décès)/360

	 */
	


	
	/**
	 * Regle de gestion fictif : juste pour avoir le rentier
	 * @param entiteBO
	 * @param params
	 * @throws ExceptionMetier
	 */
		public void regleGestion000AlimenterRentier(EntiteBO entiteBO,
				Map params) throws ExceptionMetier {
		
		MvtDecesRentier mouvement = (MvtDecesRentier) entiteBO;
		rentier = mouvement.getRefRentier();

		if (rentier == null || rentier.getId() <= 0) {
			throw new ExceptionMetier(IMessageException.EXP_RENTIER_INTROUVALE);
		}

		rentierBD = rentierManager.getRentierByID(rentier.getId());
		
		

		}
	
	/**
	 * RG4/FGSR-10.7: Quittances d’équilibre à générer à la validation du mouvement. 
	 * Le montant correspondant.
	 */
	
	@ASkipRG(property="emissionQuittanceDeces", value="false", isEmty="true")
	public void regleGestion001GenererQuittanceEquilibre(EntiteBO entiteBO,
			Map params) throws ExceptionMetier {

		MvtDecesRentier mouvement = (MvtDecesRentier) entiteBO;
		rentier = mouvement.getRefRentier();

		if (rentier == null || rentier.getId() <= 0) {
			throw new ExceptionMetier(IMessageException.EXP_RENTIER_INTROUVALE);
		}

		rentierBD = rentierManager.getRentierByID(rentier.getId());
		Double mntEquilibre = 0D;
		
		try {
			mntEquilibre = calculerMntEquilibre(rentierBD, mouvement.getDateDeces());
		
		} catch(ExceptionMetier e) {
			return;
		}

		// Faire appelle quittance d'�quilibre, pour le rentie sortant !
		if (mntEquilibre.equals(0D)) {
			return;
		}
		
		QuittanceGsr quittanceGsr = new QuittanceGsr();
		quittanceGsr.setMontant(-mntEquilibre);
		mouvement.addQuittanceEquilibre(quittanceGsr);
		
		EtatMvt etatMvt = mouvement.getRefEtatMvt();
		
		if(etatMvt.getId()==EtatMouvement.Validee.getId()){
			
			
			eai.devass.gsr.appli.reglegestion.modification.MvtDecesRentierRG mvtDeces = new eai.devass.gsr.appli.reglegestion.modification.MvtDecesRentierRG();
			mvtDeces.regleGestion995AlimenterListHeritier(entiteBO, params);

			getSession().saveOrUpdate(mouvement);
			
			MouvementRG mvtValidationRg = new MouvementRG();
			
			Mouvement	mvtDB = null;
			
			try {
				mvtDB = (Mouvement) getSession().get(Mouvement.class,
						mouvement.getId());
				
				
				if (mvtDB == null) {
					throw new ExceptionMetier("Mouvement non trouv� !!");
				}

			} catch (Exception e) {
				throw new ExceptionMetier(e.getMessage());
			}

			mvtDB.setRefRentier(rentierBD);
			mvtDB.setRefsQuittance(getListeQuittanceBD(mvtDB.getRefsQuittance()));
			mvtValidationRg.setMouvementDB(mvtDB);

			if(mouvement.getMntGlobalVersee()!=null && mouvement.getMntGlobalVersee()>0)
			{	
				mvtValidationRg.regleGestion991PrepareEmissionQuittanceGsr(entiteBO, params);
			}
			mvtValidationRg.regleGestion993QuittanceEquilibre(entiteBO, params);
			mvtValidationRg.regleGestion994SetEtatQuittance(entiteBO, params);
			mvtValidationRg.regleGestion995EmissionQuittanceGSR(entiteBO, params);
			mvtValidationRg.regleGestion996EmissionQuittanceATEquilibre(entiteBO, params);
		}else{
			if(mouvement.getRefsQuittance()!=null && mouvement.getRefsQuittance().size()>0)
			{
			mouvement.setEmissionQuittance(true);
			}
		}
		
	}
	
	
	/**
	 * RG11/FGSR-10.7 Mise à jour du Rentier avec :
	 * Etat du rentier : décès
	 * @param entiteBO
	 * @param params
	 * @throws ExceptionMetier
	 */
	public void regleGestion002SetEtatRentier(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		Mouvement mouvement = (Mouvement) entiteBO;
		EtatRentier etatRentier = new EtatRentier();
		etatRentier.setId(EtatRente.Deces.getCode());
		rentierBD.setRefEtatRentier(etatRentier);
		rentierBD.setDateEtat(mouvement.getDatEtat());
		SituationEtatRentier situationEtatRentier = new SituationEtatRentier();		
		situationEtatRentier.setRefEtatRentier(etatRentier);
		situationEtatRentier.setRefRentier(rentierBD);
		situationEtatRentier.setDateEtat(new GregorianCalendar().getTime());
		situationEtatRentier.setOperateur(entiteBO.getOperateur());	
		getSession().save(situationEtatRentier);
	}


	/**
	 * RG11/FGSR-10.7 Mise à jour du dossier Rente avec :
	 * Etat du dossier GSR « Clôturé » avec motif « décès »
	 * @param entiteBO
	 * @param params
	 * @throws ExceptionMetier
	 */
	public void regleGestion003SetEtatDossierRente(EntiteBO entiteBO, Map params) throws ExceptionMetier {
	
		DossierRente dossierRente = null;		
		dossierRente = rentierBD.getRefDossierRente();		
		Long classeRentier = rentierBD.getLienParente();		
		if (classeRentier == null) { 
			throw new ExceptionMetier(IMessageException.EXP_CLASSE_RENTIER_INTROUVALE);
		}
	    
		ayantsDroit = dossierRente.getRefsRentier();
		boolean statutDernierAyantDroit = false;
		
		//Réflechir sur le dernier ayant droit « en cours », 		
		if(ayantsDroit != null && ayantsDroit.size() >= 1){
			for(Rentier ayantDroit:ayantsDroit){
				Long idEtatRentier = ayantDroit.getRefEtatRentier().getId();
				if (idEtatRentier == null) {
					throw new ExceptionMetier(IMessageException.EXP_ETAT_RENTIER_NULL);
				}
				
				if ((idEtatRentier.compareTo(EtatRente.Valide_En_Cours.getCode()) == 0)
						&& ayantDroit.getId() == rentierBD.getId()) {
					statutDernierAyantDroit = true;
					break;
				}
			}
		}
		
		//Si le rentier est victime
		//Ou Si le dernier ayant droit en cours est décedé
		if(classeRentier.equals(new Long("0")) || statutDernierAyantDroit){
		
		dossierRente.setRefEtatRentier(new EtatRentier(EtatRente.Cloture.getCode()));
		
		rentierBD.setRefDossierRente(dossierRente);
		
		SituationDossierRentier situationDossierRentier = new SituationDossierRentier();
		MotifDecesRentier motifRente = new MotifDecesRentier();
		
	
		
		EtatRentier etatRentier = new EtatRentier();
		etatRentier.setId(EtatRente.Cloture.getCode());
		//rentierBD.setRefEtatRentier(etatRentier);
		
		motifRente.setCode(String.valueOf(MotifEtat.Deces_rentier.getCode()));
		
		situationDossierRentier.setRefMotifSituationEtat(motifRente);
		situationDossierRentier.setRefEtatRentier(etatRentier);
		situationDossierRentier.setRefDossierRente(dossierRente);
		situationDossierRentier.setDateEtat(new GregorianCalendar().getTime());
		situationDossierRentier.setOperateur(entiteBO.getOperateur());

		getSession().save(situationDossierRentier);
		}
	}

	
	/**
	 * RG4/FGSR-10.7: Quittances d’équilibre à générer à la validation du mouvement. 
	 * Le montant correspondant.
	 * 
	 * Si trop pe�u (ajouter le montant comme trop per�u).
	 */
	
	public void regleGestion004Redistribution(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		MvtDecesRentier mouvement = (MvtDecesRentier) entiteBO;
		
		if(mouvement.getMntGlobalVersee()!=null && mouvement.getMntGlobalVersee()<0)
		{
			mouvement.setMntTropPercu(Math.abs(mouvement.getMntGlobalVersee()));
		}
		mouvement.setDateCalculRedistribution(mouvement.getDateDeces());	
		
	}
	
	

	

	
	
//	public void nonregleGestion005Redistribution(EntiteBO entiteBO, Map params)
//			throws ExceptionMetier {
//
//		MvtDecesRentier mouvement = (MvtDecesRentier) entiteBO;
//		
//		// Vérifier qu'il s'agit d'un dossier contenant des ayants droits
//		// Modifier l'état du rentier sujet du dèces
//		// Faire appel à la méthode contenant la nouvelle liste des rentiers
//		// Fournir cette liste au service de calul de la nouvelle rente pour
//		// chaque rentier
//		// Calculer le montant d'équilibre de chaque rentier
//		// rajouter autant de quittance que de rentier.
//		
//		// Recuperer le rentier
//		Rentier rentier = mouvement.getRefRentier();
//		Rentier rentierBD = rentierManager.getRentierByID(rentier.getId());
//		List<Rentier> listRentier = rentierBD.getRefDossierRente().getRefsRentier();
//		if(commonGsrUtils.isEmpty(listRentier) || listRentier.size() == 1) {
//			return;
//		}
//		
//		List<Rentier> ayantsDroitActifs = getListRentierActif();
//		try {
//			transverseManager.calculerNouvelleRente(ayantsDroitActifs);
//
//		} catch (FonctionnelleException e) {
//			// TODO Auto-generated catch block
//			logger.error("probl�me technique",e);
//		}
//		
//		Double mntEquilibreRentier = 0D;
//		for (Rentier ayantDroit : ayantsDroitActifs) {
//			mntEquilibreRentier = 0D;
//			mntEquilibreRentier = calculerMntEquilibreParAyantDroit(
//					ayantDroit, mouvement.getDateDeces());
//
//			// Faire appelle quittance d'�quilibre
//			if (!(mntEquilibreRentier.compareTo(0D) == 0)) {
//				QuittanceGsr quittanceGsr = new QuittanceGsr();
//				quittanceGsr.setMontant(mntEquilibreRentier);
//				mouvement.addQuittanceEquilibre(quittanceGsr);
//			}
//		}
//		
//		
//		if (ayantsDroit != null && ayantsDroit.size() >= 1) {
////			for (Rentier ayantDroit : ayantsDroit) {
////				if(Long.valueOf(DegreParente.Victime.getCode()).equals(
////						(ayantDroit.getLienParente()))) {
////					return;
////				}
////			}
//
//			
//		}
//	}
	
//	private Double calculerMntEquilibreParAyantDroit(Rentier rentierIn, Calendar dateDeces)throws ExceptionMetier{
//		
//		Double mntQuittanceEquilibre = 0D;		
//		Double ancienneRente = rentierIn.getMntAncienneRente();		
//		Double nouvelleRente = rentierIn.getMontantRenteTrimestrielle();		
//		Double coeffDeces = transverseManager.getCoefficientAge(rentierIn, dateDeces.getTime());		
//		mntQuittanceEquilibre= (nouvelleRente- ancienneRente) * coeffDeces;					
//		return mntQuittanceEquilibre;
//			
//	}
	
	private List<QuittanceGsr> getListeQuittanceBD(List<QuittanceGsr> listQuittanceMvt) throws ExceptionMetier{
		
		
		List<QuittanceGsr> listeQuittanceBD= new ArrayList<QuittanceGsr>();
		if(listQuittanceMvt!=null && listQuittanceMvt.size()>0 )
		{
		for(QuittanceGsr quittanceGsr:listQuittanceMvt){
			if(quittanceGsr!= null ){
			QuittanceGsr quiGsrDB = quittanceManager.getQuittanceByID(quittanceGsr.getId());
			listeQuittanceBD.add(quiGsrDB);
			}
		}
		}
		return listeQuittanceBD;
	}
	
	
	private Double calculerMntEquilibre(Rentier rentier,Calendar dateDeces)throws ExceptionMetier{
		Double mntQuittanceEquilibre = 0D;
		Integer nbreJoursRegles =0;
		try {
			nbreJoursRegles = transverseManager.getNombreQuittancesReglees(rentier,getDateAnneeEncous(dateDeces),dateDeces);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ExceptionMetier(IMessageException.EXP_NBRE_JOURS_REGLES);
		}
		Double renteTrimestrielle = rentier.getMontantRenteTrimestrielle();
		Double coeffDeces = transverseManager.getCoefficientAge(rentier, dateDeces.getTime());
		Double coeffDeces_1 = transverseManager.getCoefficientAge(rentier, getDateAnneePrecedente(dateDeces));
		Double coeffDeces_2 = transverseManager.getCoefficientAge(rentier, getDateAnneeAvantDerniere(dateDeces));
		Double capitaleDeces = transverseManager.calculerCapitalPercu(renteTrimestrielle, coeffDeces);
		Double capitaleDeces_1 = transverseManager.calculerCapitalPercu(renteTrimestrielle, coeffDeces_1);
		Double capitaleDeces_2 = transverseManager.calculerCapitalPercu(renteTrimestrielle, coeffDeces_2);
		if(coeffDeces.compareTo(coeffDeces_1)==0){
			mntQuittanceEquilibre= capitaleDeces - ((capitaleDeces_2-capitaleDeces_1)*nbreJoursRegles)/360;
		}else{
			mntQuittanceEquilibre= capitaleDeces - ((capitaleDeces_1-capitaleDeces)*nbreJoursRegles)/360;
		}
		
			return mntQuittanceEquilibre;

	}
		

		
		
		
		private Calendar getDateAnneeEncous(Calendar dateDeces){
			
			Date dateAnneeEncours = null;
			
			Integer anneeEncours = dateDeces.get(Calendar.YEAR);

			String exerciceEncours = "01/01/"+anneeEncours.toString();
			
			dateAnneeEncours =  DateUtils.getDate("dd/MM/yyyy",exerciceEncours);
			
			Calendar result=Calendar.getInstance();
			result.setTime(dateAnneeEncours);
			
			return 	result;
		}
		
		
		private Date getDateAnneePrecedente(Calendar dateDeces){
			
			Date dateDeces_1 = null;

			Integer anneeDeces_1 = dateDeces.get(Calendar.YEAR)- 1;

			String exercicePrecedent = "31/12/"+anneeDeces_1.toString();
			
			 dateDeces_1 =  DateUtils.getDate("dd/MM/yyyy",exercicePrecedent);
			
			return dateDeces_1;
			
		}
		
		
		private Date getDateAnneeAvantDerniere(Calendar dateDeces){
			
			Date dateDeces_2 = null;
			
			Integer anneeDeces_2 = dateDeces.get(Calendar.YEAR) - 2;

			String exercicePrecedent = "31/12/"+anneeDeces_2.toString();
			
			 dateDeces_2 =  DateUtils.getDate("dd/MM/yyyy",exercicePrecedent);
			
			return dateDeces_2;
			
		}
}
