package eai.devass.gsr.appli.manager.metier.reglement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IResult;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.entites.EntiteException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.ProcessEntiteException;
import ma.co.omnidata.framework.services.entites.ValidateEntiteException;
import ma.co.omnidata.framework.utile.DateUtile;

import org.hibernate.Query;
import org.hibernate.Session;

import com.rmawatanya.reglement.application.metier.valueobjects.QuittanceRRVO;
import com.rmawatanya.reglement.application.metier.valueobjects.QuittanceVO;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.gsr.appli.manager.CommunManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.modele.metier.reglement.ComplementQuitatnce;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.EtatQtc;
import eai.devass.gsr.appli.modele.parametrage.NatureQtcGsr;
import eai.devass.gsr.appli.prm.EtatQuittance;
import eai.devass.gsr.appli.prm.NatureQuittance;
import eai.devass.gsr.appli.prm.TypeMouvementGsr;
import eai.devass.gsr.appli.prm.TypeQuittance;
import eai.devass.gsr.appli.reglegestion.validation.MouvementRG;
import eai.devass.gsr.appli.utile.CommonGsrUtils;
import eai.devass.gsr.appli.utile.TypeConverter;
import eai.devass.gsr.appli.valueobjects.metier.reglement.QuittanceGsrVO;
import eai.devass.services.IAppelService;
import eai.devass.services.ServicesExternes;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.utils.RmiTools;
import eai.devass.sinistreat.appli.utils.entites.IDate;
import eai.devass.sinistreat.appli.utils.entites.IParam;

/**
 * Manager de l ' entitÃ© Quittance
 * 
 * @author Nom Prenom (email)
 */

@SuppressWarnings("all")
public class QuittanceGsrManager extends CommunManager {

	public RmiTools rmiTools = RmiTools.getInstance();
	public DateFormat format = new SimpleDateFormat(IDate.FORMAT_HHMM);
	protected CommonGsrUtils commonGsrUtils = CommonGsrUtils.getInstance();
	/**
	 * Completude de l ' objet avant sa creation
	 * 
	 * @param entite
	 *            lentitÃ© Ã  completer
	 * @return returns lentitÃ© completÃ©
	 * @throws ProcessEntiteException
	 *             problÃ¨me lors de la complÃ©tude de l' entitÃ©
	 * @throws EntiteException
	 */
	protected IEntite doProcessCreateEntity(IEntite entite)
			throws ProcessEntiteException, EntiteException {
		QuittanceGsr quittance = (QuittanceGsr) entite;

		quittance.setDateCreation(DateUtile.dateCalendarCourante());

		return quittance;

	}

	/**
	 * Validation de l'object avant sa suppression
	 * 
	 * @param entite
	 *            l' entitÃ© Ã  valider avant sa suppression
	 * @throws ValidateEntiteException
	 *             Probleme lors de la validation de l 'entitÃ©
	 * @throws EntiteException
	 *             Probleme lors de la persistence
	 */
	protected void doValidateDeleteEntity(IEntite entite)
			throws ValidateEntiteException, EntiteException {
	}

	/**
	 * Methode qui habille l' entitÃ© avant sa creation
	 * 
	 * @param entite
	 *            L 'entite Ã  habiller
	 * @return l' entitÃ© habillÃ©
	 * @throws ProcessEntiteException
	 *             problÃ¨me lors de la complÃ©tude de l' entitÃ©
	 * @throws EntiteException
	 *             Probleme lors de la persistence
	 */
	protected IEntite doRevampCreateEntity(IEntite entite)
			throws ProcessEntiteException, EntiteException {
		return entite;// this.habiller(entite);
	}

	/**
	 * Completude de l' objet avant sa modification
	 * 
	 * @param entite
	 *            L' entitÃ© Ã  completer avant sa modification
	 * @returns L' entitÃ© completÃ©
	 * @throws ProcessEntiteException
	 *             problÃ¨me lors de la complÃ©tude de l' entitÃ©
	 * @throws EntiteException
	 *             Probleme lors de la persistence
	 */
	protected IEntite doProcessModifyEntity(IEntite entite)
			throws ProcessEntiteException, EntiteException {
		QuittanceGsr quittance = (QuittanceGsr) entite;

		return quittance;

	}

	public QuittanceRRVO generateQuittance(QuittanceGsrVO qtc, String user,
			String numSinistre) {
		// Prerglt
		// numsinistre---> voir comment le récupérer: implementer une methode

		return new QuittanceRRVO();
	}

	public QuittanceRRVO generateQuittanceTrim(QuittanceGsrVO qtc, String user,
			String numSinistre, String periode) {
		// implementer un batch
		return new QuittanceRRVO();
	}

	/**
	 * Génération de quittance par le service cible
	 * 
	 */

	public QuittanceVO generateQuittance(QuittanceGsr quittance, String codeUser)
			throws Exception {
		
		//ParametrageManager parametrageManager = (ParametrageManager) ServicesFactory.getService(ParametrageManager.class);
		IResult result;
		NatureQuittance natureQtcGsr = null;
		QuittanceGsr quittanceGsr = new QuittanceGsr();
		Sinistre sinistre = quittance.getRefRentier().getRefDossierRente().getRefSinistre();
		eai.devass.gsr.appli.modele.parametrage.TypeQuittance typeQuittance = new eai.devass.gsr.appli.modele.parametrage.TypeQuittance();
		typeQuittance.setId(TypeQuittance.Reglement.getId());
		typeQuittance.setCode(TypeQuittance.Reglement.getCode());
		quittanceGsr.setRefTypeQuittance(typeQuittance);
		quittanceGsr.setNumeroSinistre(sinistre.getNumeroSinistre());
		quittanceGsr.setNumeroContrat(sinistre.getNumeroPolice());
		quittanceGsr.setCodeSousTypeQuittance(String.valueOf(NatureQuittance.Rente_periodique.getId()));
		quittanceGsr.setDatePositionnement(quittance.getDatePositionnement());
		//Evo Nature Qtc
		natureQtcGsr = NatureQuittance.Rente_periodique;
	    quittanceGsr.setRefNatureQuittance(new NatureQtcGsr(natureQtcGsr.getId()));
		long natureQtc = quittanceGsr.getRefNatureQuittance().getId();
		String codePrestation = getCodePrestataion(natureQtc);
		quittanceGsr.setCodePrestation(codePrestation);
		//Fin
		if (sinistre.getCodeClient() != null) {
			quittanceGsr.setNumeroClient(sinistre.getCodeClient());
		} else {
			quittanceGsr.setNumeroClient("9999");
		}
		if (sinistre.getNomClient() != null) {
			quittanceGsr.setRaisonSociale(sinistre.getNomClient());
		} else {
			quittanceGsr.setRaisonSociale("9999");
		}
//		if (quittanceGsr.getCodeIntermediaire() != null	&& !quittanceGsr.getCodeIntermediaire().equals("")) {
//			if (((Intermediaire) parametrageManager
//					.getIntermediaireByCode(quittanceGsr.getCodeIntermediaire())).getCodeTypeIntermediaire() != null
//					&& !((Intermediaire) parametrageManager
//							.getIntermediaireByCode(quittanceGsr
//									.getCodeIntermediaire()))
//							.getCodeTypeIntermediaire().equals("")
//					&& ((Intermediaire) parametrageManager
//							.getIntermediaireByCode(quittanceGsr
//									.getCodeIntermediaire()))
//							.getCodeTypeIntermediaire().length() == 5) {
//				quittanceGsr
//						.setCodeIntermediaire(((Intermediaire) parametrageManager
//								.getIntermediaireByCode(quittanceGsr
//										.getCodeIntermediaire()))
//								.getCodeTypeIntermediaire().substring(1, 5));
//				quittanceGsr
//						.setTypeIntermediaire(((Intermediaire) parametrageManager
//								.getIntermediaireByCode(quittanceGsr
//										.getCodeIntermediaire()))
//								.getCodeTypeIntermediaire().substring(0, 1));
//			} else {
//				quittanceGsr.setCodeIntermediaire("9999");
//				quittanceGsr.setTypeIntermediaire("A");
//			}
//		} else {
//			quittanceGsr.setCodeIntermediaire("9999");
//			quittanceGsr.setTypeIntermediaire("A");
//		}
		if (!commonGsrUtils.isEmpty(sinistre.getCodeIntermediaire())
				&& sinistre.getCodeIntermediaire().length() == 4) {

			quittanceGsr.setCodeIntermediaire(sinistre.getCodeIntermediaire());
			quittanceGsr.setTypeIntermediaire(sinistre.getTypeIntermediaire());
			
		} else {
			quittanceGsr.setCodeIntermediaire("9999");
			quittanceGsr.setTypeIntermediaire("A");
		}
		
		quittanceGsr.setCodeBranche(IParam.CODE_BRANCHE_AT);
		quittanceGsr.setCodeCategorie("1");
		quittanceGsr.setCodeUtilisateur(codeUser);
		quittanceGsr.setCodeServiceOrdonnateur(IParam.CODE_SERVICE_ORDONNATEUR_GSR);
		quittanceGsr.setCodeBeneficiaire("0");
		quittanceGsr.setTypeBeneficiare(quittance.getClasse());
		quittanceGsr.setCodeProduit(IParam.CODE_BRANCHE_AT);
		quittanceGsr.setMontant(quittance.getMontant());

		Format forma = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date d = Calendar.getInstance().getTime();
		String datjour = forma.format(d);
		quittanceGsr.setDatEmission(TypeConverter.getInstance()
				.stringToCalendar("dd/MM/yyyy HH:mm:ss", datjour));
		quittanceGsr.setBeneficiaire(quittance.getBeneficiaire());
		quittanceGsr.setRefRentier(quittance.getRefRentier());
		IAppelService servicesExternes = (IAppelService) ServicesFactory
				.getService(IAppelService.class);
		servicesExternes.setConvert(true);
		Object resultObject = servicesExternes.appelService(
				ServicesExternes.EMISSION_QUITTANCE, quittanceGsr, "1");
		QuittanceVO qtc = (QuittanceVO) resultObject;
		return qtc;
	}


	public QuittanceGsr convertQtcVOToRglBO(QuittanceGsr quittance,
			QuittanceRRVO qtc) {
		try {
			quittance.setNumeroQuittance(qtc.getNumeroQuittance());
			quittance.setDatEmission(TypeConverter.getInstance()
					.stringToCalendar(qtc.getDateEmission()));
		} catch (Exception e) {
			return null;
		}

		return quittance;
	}

	public QuittanceGsr getLastQuittanceByMouvement(Mouvement mouvement)
			throws Exception {

		StringBuilder hql = new StringBuilder(
				"select qtc from eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr qtc")
				.append(" join qtc.refMouvement mvt where mvt=? order by qtc.id desc");

		Query query = getSession().createQuery(hql.toString())
				.setParameter(0, mouvement).setMaxResults(1);

		return (QuittanceGsr) query.uniqueResult();
	}
	
	public List<QuittanceGsr> getListQuittanceNotAnnuler(Mouvement mouvement) throws Exception {

		StringBuilder hql = new StringBuilder("select qtc from QuittanceGsr qtc")
				.append(" join qtc.refMouvement mvt where mvt=:mouv and qtc.refEtatQtc")
				.append(" not in (:etat)  order by qtc.id desc");

		Query query = getSession().createQuery(hql.toString()).setParameter("mouv",
				mouvement).setParameterList("etat", new EtatQtc[] { new EtatQtc(EtatQuittance.Annulee.getId()), 
						new EtatQtc(EtatQuittance.Supprimee.getId())}) ;
		return query.list();
	}
	
	
	public List<QuittanceGsr> getLastQuittancesTrimistrielle(Rentier rentier,
			int maxRsult) throws Exception {

		StringBuilder hql = new StringBuilder("select qtc from QuittanceGsr qtc")
				.append(" where qtc.refRentier=? and qtc.refMouvement is null and qtc.refNatureQuittance=?")
				.append(" order by qtc.id desc");
		
		Query query = getSession().createQuery(hql.toString())
				.setParameter(0, rentier).setParameter(1, new NatureQtcGsr(
					NatureQuittance.Rente_periodique.getId())).setMaxResults(maxRsult);
		
		return (List) query.uniqueResult();
	}
	
	/**
	 * Récuparation de la liste des quittances trimestrielles
	 * à l'état crée [1] ou émise[4] après la date de validation
	 * @param rentier
	 * @param dateValidation
	 * @return
	 * @throws Exception
	 */
	public List<QuittanceGsr> getQuittancesTrimestrielleNonRegles(Rentier rentier
			,Calendar dateValidation) throws Exception {

		StringBuilder hql = new StringBuilder("select qtc from QuittanceGsr qtc")
				.append(" where qtc.refRentier=? and qtc.refMouvement is null and qtc.refNatureQuittance=?")
				.append(" and qtc.datEtat >=? and qtc.refEtatQtc in (:etatQtc)")
				.append(" order by qtc.id desc");
		
		List etatQtcList = new ArrayList<EtatQtc>();
		etatQtcList.add(new EtatQtc(EtatQuittance.Cree.getId()));
		etatQtcList.add(new EtatQtc(EtatQuittance.En_instance.getId()));
		
		Query query = getSession().createQuery(hql.toString())
				.setParameter(0, rentier).setParameter(1, new NatureQtcGsr(
					NatureQuittance.Rente_periodique.getId())).
					setCalendar(2, dateValidation).
						setParameterList("etatQtc", etatQtcList);

		
		return (List) query.list();
	}
	
	/**
	 * Récupère l'ensemble des quittances d'un mouvement
	 * @param mouvement
	 * @return
	 * @throws Exception
	 */
	
	public List<QuittanceGsr> getListQuittanceByMouvement(Mouvement mouvement)
	throws Exception {

		StringBuilder hql = new StringBuilder(
				"select qtc from eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr qtc")
				.append(" join qtc.refMouvement mvt where mvt=? and qtc.refEtatQtc in (:etatQtc)")
				.append(" order by qtc.id desc");
		
		List etatQtcList = new ArrayList<EtatQtc>();
		etatQtcList.add(new EtatQtc(EtatQuittance.Cree.getId()));
		etatQtcList.add(new EtatQtc(EtatQuittance.En_instance.getId()));
		
		Query query = getSession().createQuery(hql.toString())
				.setParameter(0, mouvement).
					setParameterList("etatQtc", etatQtcList);
		
		return  (List) query.list();
}
	
	
	
	public Double getSommeQuittancesTrimestrielle(Rentier rentier,Calendar dateValidation) throws Exception {

		StringBuilder hql = new StringBuilder("select sum(COALESCE(qtc.montant,0)) from QuittanceGsr qtc")
				.append(" where qtc.refRentier=? and qtc.refMouvement is null and qtc.refNatureQuittance=?")
				.append(" and qtc.refEtatQtc =? and qtc.datEtat >=?")
				//ajouter date réglement inférieur à la date de validation de mouvement.
				.append(" order by qtc.id desc");
		
		Query query = getSession().createQuery(hql.toString())
				.setParameter(0, rentier).setParameter(1, new NatureQtcGsr(
					NatureQuittance.Rente_periodique.getId())).
						setParameter(2,new EtatQtc(EtatQuittance.Reglee.getId())).
							setCalendar(3, dateValidation).
								setMaxResults(1);
		
		return (Double) query.uniqueResult();
	}
	
	
	
	
	public List<QuittanceGsr> getListQuittancesDefautRemise(Rentier rentier) throws Exception {

		StringBuilder hql = new StringBuilder("select {qtc.*} from GSR_QUITTANCE qtc")
				.append(" join BATCH_QUITTANCE_DEFAUTREMISE b on B.REF_REGLEMENT=QTC.REF_REGLEMENT")
				.append(" join GSR_RENTIER r on R.ID=QTC.IDSRENTIER")
				.append(" where QTC.IDSRENTIER =? and QTC.IDSMOUVEMENT is null and QTC.IDSNATURE_QTC=?")
				.append(" and qtc.IDSETAT_QTC=? and b.DAT_REMISSION is null order by qtc.id desc");
		
		Query query = getSession().createSQLQuery(hql.toString()).addEntity("qtc", QuittanceGsr.class)
				.setParameter(0, rentier).setParameter(1, new NatureQtcGsr(
						NatureQuittance.Rente_periodique.getId()))
				.setParameter(2, new EtatQtc(EtatQuittance.Annulee.getId()));					
		
		return (List) query.list();
	}
	
	public void updateQuittancesDefautRemise(String refReglement) throws Exception {

		StringBuilder hql = new StringBuilder("update BATCH_QUITTANCE_DEFAUTREMISE set")
		   .append(" DAT_REMISSION=sysdate where REF_REGLEMENT=?");
		
		getSession().createSQLQuery(hql.toString())
				.setString(0, refReglement).executeUpdate();					
	}
	
	
	public QuittanceGsr getQuittanceByID(long id) throws ExceptionMetier {
		QuittanceGsr quittanceGsr = null;
		try {

			Class clazz = Class
					.forName("eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr");
			quittanceGsr = (QuittanceGsr) getSession().get(clazz, id);

		} catch (Exception e) {
			throw new ExceptionMetier(e.getMessage());
		}

		if (quittanceGsr == null) {
			throw new ExceptionMetier("La quittance ["	+ id + "] est introuvable");
		}

		return quittanceGsr;
	}
	
	public QuittanceGsr getQuittanceGsrByNumero(String numeroQuittance) throws Exception {
		if (numeroQuittance == null || numeroQuittance.equals("")) {
			return null;
		}
		String hql = " from eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr quittanceGsr where quittanceGsr.numeroQuittance = '"
				+ numeroQuittance + "'";
		return (QuittanceGsr) getSession().createQuery(hql).uniqueResult();
	}
	
	public void updateDateReglement(Long idQtc, Date dateReglement) throws Exception {
		
		String hql = "update QuittanceGsr set datePositionnement=? where id=?";
		if(dateReglement == null) {
			hql = "update QuittanceGsr set datePositionnement='' where id=?";
		}
		Query query = getSession().createQuery(hql);
		if(dateReglement == null) {
			query.setLong(0, idQtc).executeUpdate();
		} else {
			query.setParameter(0, dateReglement).setLong(1, idQtc).executeUpdate();
		}
		
				
	}
	
	public void updateEtatQuittance(Long idQtc, EtatQtc etatQtc, String refReglement, Session session) throws Exception {
		
		session.createQuery("update QuittanceGsr set refEtatQtc=?,refReglement=?,datePositionnement=?,datEtat=? where id=?")
				.setParameter(0, etatQtc).setString(1, refReglement)
				.setDate(2, new Date()).setDate(3, new Date()).setLong(4, idQtc).executeUpdate();
				
	}
	
	/**
	 * Génération de quittance  d'equilibrepar par le service cible
	 * 
	 */

	public QuittanceVO generateQuittanceEquilibre(QuittanceGsr quittance, String codeUser,String operateur)
			throws Exception {

		
		MouvementRG mouvementRG = new MouvementRG();
		Mouvement mouvement = new Mouvement();
		mouvement.setRefRentier(quittance.getRefRentier());
		// Ajouter la quittance d 'equilibre
		QuittanceGsr quittanceGsr = new QuittanceGsr();
		quittanceGsr.setMontant(quittance.getRefRentier().getCapitalConstitutif());
		mouvement.addQuittanceEquilibre(quittanceGsr);
		mouvementRG.setMouvementDB(mouvement);
		
		EntiteBO entiteBO = new EntiteBO();
		entiteBO.setOperateur(operateur);	
		
		// Preparer les donnees pour l'emission de la quittance QUITTANCEMENT
		mouvementRG.setContexteMvt(false);
		 
		//MAJ Cumule Sinistre
		mouvementRG.regleGestion9921MAJCumuleSinistre(entiteBO, null);
		
		mouvementRG.regleGestion993QuittanceEquilibre(entiteBO, null);
		
		// Generation des situations quittances !!!
		mouvementRG.regleGestion994SetEtatQuittance(entiteBO,null);
		
		// Emission Quittance GSR
		mouvementRG.regleGestion995EmissionQuittanceGSR(null,null);
		
		// Emission Quittance AT
		mouvementRG.regleGestion996EmissionQuittanceATEquilibre(null,null);
		
		
		return null;
	}

	private String getCodePrestataion(Long natureQtc) {
		
		NatureQuittance[] natureQuittances = NatureQuittance.class.getEnumConstants();
		for (NatureQuittance curNatQtc : natureQuittances) {
			if(curNatQtc.getId() == natureQtc) {
				return curNatQtc.getRubrique();
			}
		}
		
		return null;
	}
	
	
	/**
	 * Récuparation de la liste des quittances réglées du mouvement rachat
	 * @param NumeroRente
	 * @param dateDebut
	 * @param dateFin
	 * @return List<QuittanceGsr>
	 * @throws Exception
	 */
	public List<QuittanceGsr> getQuittancesRachat(String NumeroRente,Calendar dateDebut
			,Calendar dateFin) throws Exception {
		
		
		StringBuilder hql = null;
		
		Query query = null;
		
		List natureQtcList = new ArrayList<NatureQtcGsr>();
		natureQtcList.add(new NatureQtcGsr(NatureQuittance.rachat_apres_constitution_recours_victime.getId()));
		natureQtcList.add(new NatureQtcGsr(NatureQuittance.rachat_apres_constitution_recours_compagnie.getId()));
		//natureQtcList.add(new NatureQtcGsr(NatureQuittance.Rembourssement.getId()));
		
		eai.devass.gsr.appli.modele.parametrage.TypeQuittance typeQtc = new eai.devass.gsr.appli.modele.parametrage.TypeQuittance(TypeQuittance.Reglement.getId());
		
		if(NumeroRente!=null && dateDebut==null && dateFin ==null ){
			
			hql = new StringBuilder("select qtc from QuittanceGsr qtc")
			.append(" join qtc.refMouvement mvt")
			.append(" where mvt.refTypeMouvement.id=?")
			.append(" and qtc.numeroRente = ?")
			.append(" and qtc.refTypeQuittance= ?")
			.append(" and qtc.refNatureQuittance in (:natureQtc)")
			.append(" order by qtc.id desc");
			
			 query = getSession().createQuery(hql.toString()).
			 			setLong(0,TypeMouvementGsr.RACHAT.getId()).
								setString(1, NumeroRente).
									setParameter(2, typeQtc).
										setParameterList("natureQtc", natureQtcList);
			 
//			 
//			 
//			 
//			 final String logEtat = String.format("etat quittance [%s] ", EtatQuittance.Reglee.getId());  
//			 final String logNumeroRente = String.format("NumeroRente [%s] ", NumeroRente);  
//	
//			 
//			 logger.info(logEtat);
//			 logger.info(logNumeroRente);
			
			
		}else if (dateDebut!=null && dateFin !=null && NumeroRente==null){
			
			if(dateDebut.compareTo(dateFin) == 0)
			{
				dateFin.add(Calendar.DAY_OF_YEAR, 1);
			}
			
			hql = new StringBuilder("select qtc from QuittanceGsr qtc")
			.append(" join qtc.refMouvement mvt")
			.append(" where mvt.refTypeMouvement.id=?")
			.append(" and qtc.datEtat between ? and ? ")
			.append(" and qtc.refTypeQuittance= ?")
			.append(" and qtc.refNatureQuittance in (:natureQtc)")
			.append(" order by qtc.id desc");
			
			
			query = getSession().createQuery(hql.toString()).
						setLong(0,TypeMouvementGsr.RACHAT.getId()).
								setCalendar(1, dateDebut).
									setCalendar(2, dateFin).
										setParameter(3, typeQtc).
											setParameterList("natureQtc", natureQtcList);
		}

		
		if(query!=null && query.list()!=null){
			List listQtc = (List) query.list();
		 if(listQtc.size()>0){
				return listQtc;
			}
		 else{
				return null;
			}
		}
		else{
			return null;
		}
		
	
	
	}
	

	public void updateNumQuittanceReglemen(Long idRgl, String numQuittance)
			throws Exception {

		// property name="numeroQuittance" update="false", du coup on peut pas
		// faire une mise à jour !!!
		// Mauvaise facon, en attendant !!
		Connection connection = null;
		PreparedStatement preparedStatement =null;
		try {
			connection = getSession().connection();
			 preparedStatement = connection
					.prepareStatement("update SIN_REGLEMENT set NUMEROQUITTANCE=? where ID=?");
			preparedStatement.setString(1, numQuittance);
			preparedStatement.setLong(2, idRgl);
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			logger.error("Erreur base de donnée", e);
			throw new FonctionnelleException(e.getMessage());
		} 
		
		finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();

				} catch (SQLException e2) {
					logger.error("Erreur base de donnée", e2);

				}
				preparedStatement = null;
			}

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e2) {
					logger.error("Erreur base de donnée", e2);
					throw new FonctionnelleException(e2.getMessage());
				} finally {
					connection = null;
				}
			}
		}
	}
	
	public List<QuittanceGsr> getListQuittanceManuelle(ComplementQuitatnce complementQuitatnce, 
			QuittanceGsr quittanceGsr) throws Exception {
		//wmos 13012015: Evo creation quittance manuelle add contrôle état quittance.
		return getSession().createQuery("select q from QuittanceGsr q where"
					+ " q.refNatureQuittance=? and q.refRentier=?" 
					+ " and ((q.dateDebutQtc between :dateDebut and :dateFin)" 
					+ " or (q.dateFinQtc between :dateDebut and :dateFin))"
					+ " and q.refEtatQtc in (:etat)")
				.setParameter(0, quittanceGsr.getRefNatureQuittance())
				.setParameter(1, quittanceGsr.getRefRentier())
				.setDate("dateDebut", complementQuitatnce.getDateDebut())
				.setDate("dateFin", complementQuitatnce.getDateFin())
				.setParameterList("etat", new EtatQtc[] { new EtatQtc(EtatQuittance.Cree.getId()),new EtatQtc(EtatQuittance.En_instance.getId()),
						new EtatQtc(EtatQuittance.Reglee.getId()), new EtatQtc(EtatQuittance.Attente_validation_supp.getId())}).list();
	}
			
	
	
}