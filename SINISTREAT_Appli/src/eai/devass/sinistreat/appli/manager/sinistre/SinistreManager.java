package eai.devass.sinistreat.appli.manager.sinistre;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.Condition;
import ma.co.omnidata.framework.services.dao.IPersistenceService;
import ma.co.omnidata.framework.services.dao.PersistenceException;
import ma.co.omnidata.framework.services.entites.EntiteException;
import ma.co.omnidata.framework.services.entites.EntiteManagerAbst;
import ma.co.omnidata.framework.services.entites.ProcessEntiteException;
import ma.co.omnidata.framework.utile.DateUtile;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import eai.devass.commun.appli.converter.ReplaceSpecialCharacter;
import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.gsr.appli.manager.metier.dossierRente.DossierRenteManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.manager.ParametrageManager;
import eai.devass.sinistreat.appli.manager.RegleGestionManager;
import eai.devass.sinistreat.appli.manager.fichemedicale.CertificatMedicalManager;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Conciliation;
import eai.devass.sinistreat.appli.modele.metier.conciliation.SinAnterieurOffre;
import eai.devass.sinistreat.appli.modele.metier.contentieux.AudienceJugement;
import eai.devass.sinistreat.appli.modele.metier.contentieux.ProcedureJudiciaire;
import eai.devass.sinistreat.appli.modele.metier.contentieux.Recours;
import eai.devass.sinistreat.appli.modele.metier.instruction.Instruction;
import eai.devass.sinistreat.appli.modele.metier.recours.RecoursAmiable;
import eai.devass.sinistreat.appli.modele.metier.reglement.EtatReglement;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.AyantDroit;
import eai.devass.sinistreat.appli.modele.metier.sinistre.ComplementQuittanceView;
import eai.devass.sinistreat.appli.modele.metier.sinistre.EtatSinistre;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Evenement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Mouvement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.NotificationSms;
import eai.devass.sinistreat.appli.modele.metier.sinistre.SinAnterieurVictime;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Victime;
import eai.devass.sinistreat.appli.modele.parametrage.DegreParente;
import eai.devass.sinistreat.appli.modele.parametrage.EtatRgl;
import eai.devass.sinistreat.appli.modele.parametrage.EtatSin;
import eai.devass.sinistreat.appli.modele.parametrage.GestionnaireRelance;
import eai.devass.sinistreat.appli.modele.parametrage.MotifMouvement;
import eai.devass.sinistreat.appli.utils.Config;
import eai.devass.sinistreat.appli.utils.ConverterMetier;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.utils.entites.IDate;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.utils.exception.IMessageInfo;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.AlgoPoliceVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.PoliceVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;

public class SinistreManager extends EntiteManagerAbst implements IConstantes,
		IMessageException, IMessageInfo {

	private ReplaceSpecialCharacter replaceSpecialCharacter = new ReplaceSpecialCharacter();
	private IPersistenceService dao = (IPersistenceService) ServicesFactory
			.getService(IPersistenceService.class);
	public IPersistenceService getDao() {
		return dao;
	}

	public void setDao(IPersistenceService dao) {
		this.dao = dao;
	}

	private SimpleDateFormat dateFormat = new SimpleDateFormat(
			IDate.FORMAT_SIMPLE);
	private ConverterMetier converterMetier = ConverterMetier.getInstance();
	private RegleGestionManager regleManager = (RegleGestionManager) ServicesFactory
			.getService(RegleGestionManager.class);
	private ParametrageManager parametrageManager = (ParametrageManager) ServicesFactory
			.getService(ParametrageManager.class);
	private CertificatMedicalManager certificatMedicalManager = (CertificatMedicalManager) ServicesFactory
			.getService(CertificatMedicalManager.class);

    private static final Logger logger = Logger.getLogger("loggerSINAT");
	private final static String TYPE_BGD = "B";
	private final static String CODE_DECENTRALISATION_EXRMA = "9700";
	private final static String EX_RMA = "1";
	private Session session;
	private Boolean nombreSinistre;

	/**
	 * Creation Sinistre
	 * 
	 * @param sinistre
	 * @return
	 * @throws FonctionnelleException
	 */
	public Sinistre creerSinistre(Sinistre sinistre)
			throws FonctionnelleException {

		try {
			if (sinistre != null) {
				Evenement even = sinistre.getRefEvenement();
				Victime vic = (Victime) sinistre.getRefVictime();
				Date dateAccident = null;
				//RBAD  -- Evol Lot 1 - 17/11/2021 Controle Format CIN
				if (vic.getNumeroCIN() != null
						&& !StringUtils.isEmpty(vic.getNumeroCIN())) {
					
					 /*for(char c :vic.getNumeroCIN().toCharArray()){
					        if (c == ' ') {
					        	throw new FonctionnelleException(
										"Le Numero CIN ne doit pas contenir un espace");
					        };
					    }*/
					vic.setNumeroCIN(vic.getNumeroCIN().replaceAll(" ", ""));
					
				}
				//Fin Controle CIN
				if (even != null && even.getDateAccident() != null) {
					dateAccident = even.getDateAccident();
				}

				// Date accident obligatoire !!
				if (dateAccident == null) {
					throw new FonctionnelleException(
							"La date accident ne peut être vide !!!");
				}

				// Code societe et type sinistre
				StringBuilder prefix = new StringBuilder(
						IParam.PREFIX_NUM_SINISTRE);

				// EVO : Code decentralisation QC : 510
				String codeDecentralisation = IParam.CODE_DECENTRALISATION_NUM_SINISTRE;
				Calendar calAccident = new GregorianCalendar();
				calAccident.setTime(dateAccident);
				int annee = calAccident.get(Calendar.YEAR);
				String typeInter = sinistre.getTypeIntermediaire();
				if (annee >= 2005) {
					if (CommonUtils.isEmpty(typeInter)) {
						throw new FonctionnelleException(
								"Le type intermediaire est obligatoire pour le code dï¿½centralisation, "
										+ "l'année de l'accident est postï¿½rieur ï¿½ 2005");
					}
					if (TYPE_BGD.equals(typeInter)) {
						if (CommonUtils
								.isEmpty(sinistre.getCodeIntermediaire())) {
							throw new FonctionnelleException(
									"Le code intermediaire est obligatoire pour le code dï¿½centralisation,"
											+ " l'annï¿½e de l'accident est postï¿½rieur ï¿½ 2005 ");
						}
						if (sinistre.getCodeIntermediaire().length() != 4) {
							throw new FonctionnelleException(
									"Le code intermediaire est sur 4 positions, et non "
											+ sinistre.getCodeIntermediaire());
						}
						codeDecentralisation = sinistre.getCodeIntermediaire();
					}
				} else {
					if (isExRma(sinistre)) {
						codeDecentralisation = CODE_DECENTRALISATION_EXRMA;
					}
				}
				prefix.append(codeDecentralisation);

				// Categorie sinistre
				String categoriePolice = "201";
				if (sinistre.getNumeroPolice() != null
						&& !StringUtils.isEmpty(sinistre.getNumeroPolice())
						&& sinistre.getNumeroPolice().length() == 15) {
					categoriePolice = sinistre.getNumeroPolice()
							.substring(4, 7);
				}
				prefix.append(categoriePolice);

				// num ordre / annï¿½e
				sinistre.setNumeroSinistre(String.valueOf(getNumeroSinistre(
						dateAccident, prefix.toString())));
				if (isSinistreGrave(sinistre)) {
					sinistre.setDateOuvertureGrave(new Date());
					sinistre.setNumeroGrave(getNumeroGrave(dateAccident));
				}
				sinistre.addEtatSinistre(getEtatSinistre("0",
						ETAT_CREATION_SINISTRE, sinistre.getUserCreateur()));
				sinistre.setDateCreation(new Date());
				sinistre.setDateModification(new Date());

				if (sinistre.getRefVictime() != null) {
					vic.setDateCreation(new Date());
					// si dï¿½cï¿½s ajout des ayant droit s'ils existent
					List<AyantDroit> listAY = vic.getListAyantDroit();
					if (listAY != null) {
						List<AyantDroit> listAyantDroit = new ArrayList<AyantDroit>();
						for (AyantDroit ay : listAY) {
							ay.setRefVictime(vic);
							ay.setDateCreation(new Date());
							listAyantDroit.add(ay);
						}
						vic.setListAyantDroit(listAyantDroit);
					}
					dao.createObject(vic);
					
					//Evolution 05/12/2021 Sinistre antrieurs victime
					List<SinAnterieurVictime> listSinAnt = vic.getListSinistreAnterieur();			
					if (listSinAnt != null) {
						for (SinAnterieurVictime sin : listSinAnt) {
							sin.setRefVictime(vic);
							dao.createObject(sin);
						}
					}
					///fin evol
					
					
					sinistre.setRefVictime(vic);
				}
				sinistre.setMigFlag("1");
				dao.createObject(sinistre);
			}
			return sinistre;
		} catch (FonctionnelleException e) {
			logger.error(EXP_CREATION_SINISTRE, e);
			throw new FonctionnelleException(EXP_CREATION_SINISTRE, e);
		} catch (PersistenceException e) {
			logger.error(EXP_CREATION_SINISTRE, e);
			throw new FonctionnelleException(EXP_CREATION_SINISTRE, e);
		}
	}

	/**
	 * Vï¿½rifier Si le Sinistre est grave
	 * 
	 * @param sinistre
	 * @return
	 */
	private boolean isSinistreGrave(Sinistre sinistre) {
		boolean grave = false;
		if ((sinistre.getIpp() != null && sinistre.getIpp().compareTo(
				Double.valueOf(0)) != 0)
				|| (sinistre.getRefVictime() != null && sinistre
						.getRefVictime().getDeces())) {
			grave = true;
		}
		return grave;
	}

	/**
	 * Modification du sinistre
	 * 
	 * @param sinistre
	 * @return
	 * @throws FonctionnelleException
	 */
	public Sinistre modifierSinistre(Sinistre sinistre)
			throws FonctionnelleException {
		Sinistre sindb = null;
		Date dateAvantModif=null;
		List listAyantDroit=null;
		Date dateApresModif=null;
		Boolean traitementSuppressionAYDroit=Boolean.FALSE;
		List refEtatSinistres = null;
		try {
			if (sinistre != null) {

				Evenement even = sinistre.getRefEvenement();
				Victime vic = (Victime) sinistre.getRefVictime();
				if (sinistre.getId() == 0) {
					if (sinistre.getNumeroSinistre() == null
							|| "".equals(sinistre.getNumeroSinistre())) {
						throw new FonctionnelleException(EXP_NUM_SIN_REQUIRED);
					}
					sindb = getSinistreByNum(sinistre.getNumeroSinistre());
				} else {
					sindb = getSinistreById(sinistre.getId());
				}
				if (sindb == null) {
					throw new FonctionnelleException(EXP_SINISTRE_INEXISTANT);
				}
				sindb.getRefEvenement().getDateAccident();
				Evenement evenDB = sindb.getRefEvenement();

				// modifier les info evenement
				even.setNumeroEvenement(validateEvenement(sinistre, even));
				if (evenDB != null) {
					dateAvantModif=evenDB.getDateAccident();
					dateApresModif=even.getDateAccident();
					try {
						converterMetier.copyEvenement(evenDB, even);
					} catch (Exception e) {
						logger.error(EXP_MODIF_SINISTRE, e);
						throw new FonctionnelleException(EXP_MODIF_SINISTRE, e);
					}
					even = evenDB;
				}
				sinistre.setRefEvenement(even);
				// modifier les info victime
				Victime vicdb = sindb.getRefVictime();
				
				supprimerSinAnterieur(sindb,sinistre);
				/*
				List<SinAnterieurVictime> listSinAnts = vicdb.getListSinistreAnterieur();
				
				for(SinAnterieurVictime ss : listSinAnts){
					try {
						dao.delete(ss);
					} catch (PersistenceException e) {
                            logger.error(
                                    EXP_SUPPRESSION_AYANT_DROIT, e);
					}
				}
				
              	*/		
				if (vicdb != null) {
					
                    if (sindb.getRefVictime().getDeces()
                            && "0".equals(sindb.getRefEtatSinistre()
                                    .getRefEtat().getCode())) {
						 String aux = "22/01/2015";
                        DateFormat dateFormatLocal = new SimpleDateFormat(
                                "dd/MM/yyyy");
				 		Date dateChangement;
						try {
                            dateChangement = dateFormatLocal.parse(aux);
						} catch (ParseException e) {
                            throw new FonctionnelleException(
                                    "Erreur parsing0...");
						}
                        if (dateAvantModif.compareTo(dateChangement) >= 0
                                && dateApresModif.compareTo(dateChangement) < 0
                                || dateAvantModif.compareTo(dateChangement) < 0
                                && dateApresModif.compareTo(dateChangement) >= 0) {
                            // traitement special si nouvelle date est ant/post
                            // a 22/01/2015
                            List<AyantDroit> listAY = sindb.getRefVictime()
                                    .getListAyantDroit();
							if(listAY!=null){
							for(AyantDroit ay : listAY){
								try {
									dao.delete(ay);
								} catch (PersistenceException e) {
                                        logger.error(
                                                EXP_SUPPRESSION_AYANT_DROIT, e);
								}
							}
							}
							sindb.getRefVictime().getListAyantDroit().clear();
							sinistre.setReserveGrave(0D);
                            String useCase = "Modification";
                            sindb.getRefVictime()
                                    .setTraitementSpecialDeletAyDroit(
                                            Boolean.TRUE);
							traitementSuppressionAYDroit=Boolean.TRUE;
								}
					}
					
					if(!traitementSuppressionAYDroit){
					List<AyantDroit> listAY = vic.getListAyantDroit();
					if (listAY != null && !listAY.isEmpty()) {
						for (AyantDroit ay : listAY) {
							vicdb.addAyant(ay);
						}
						vic.setListAyantDroit(vicdb.getListAyantDroit());
					}
					}
					 listAyantDroit = vicdb.getListAyantDroit();
					try {
						//vic.setListSinistreAnterieur(vicdb.getListSinistreAnterieur());
						converterMetier.copyVictime(vicdb, vic);
					} catch (Exception e) {
						logger.error(EXP_MODIF_SINISTRE, e);
						throw new FonctionnelleException(EXP_MODIF_SINISTRE, e);
					}
					
					vic = vicdb;
					vic.setListSinistreAnterieur(sinistre.getRefVictime().getListSinistreAnterieur());
				}
				
				//sin anterieur 2022
				/*
				List<SinAnterieurVictime> listSinAnt = vic.getListSinistreAnterieur();
				if (listSinAnt != null) {
				//sinistre.getRefVictime().setListSinistreAnterieur(listSinAnt);
				for(SinAnterieurVictime ss : listSinAnt){
					try {						
							dao.createObject(ss);
					} catch (PersistenceException e) {
                            logger.error(
                                    EXP_SUPPRESSION_AYANT_DROIT, e);
					}
				}
				}else {
					vic.setListSinistreAnterieur(new ArrayList<SinAnterieurVictime>());
				}
				/*
				List<SinAnterieurVictime> listSinAnt = vic.getListSinistreAnterieur();
				if (listSinAnt != null) {
					for (SinAnterieurVictime sin : listSinAnt) {
						sin.setRefVictime(vicdb);
					}
					vicdb.setListSinistreAnterieur(listSinAnt);
				}else {
					vic.setListSinistreAnterieur(new ArrayList<SinAnterieurVictime>());
				}
				*/
				sinistre.setRefVictime(vic);
				// Numero grave
				if (StringUtils.isEmpty(sinistre.getNumeroGrave())
						&& isSinistreGrave(sinistre)) {
					if (sinistre.getRefEvenement().getDateAccident() != null) {
						sinistre.setDateOuvertureGrave(new Date());
						sinistre.setNumeroGrave(getNumeroGrave(sinistre
								.getRefEvenement().getDateAccident()));
					}
				} else {
					sinistre.setDateOuvertureGrave(sindb
							.getDateOuvertureGrave());
				}

				sinistre.setDateModification(new Date());
				refEtatSinistres = sindb.getRefEtatSinistres();

				try {
					Double cumulExercice = sindb.getCumulReglementAnne();
					Date dateVal = sindb.getDateValidation();
					Boolean renteCreee = sindb.getRenteCreee();
					
					converterMetier.copySinistre(sindb, sinistre);
					sindb.getRefVictime().setListAyantDroit(listAyantDroit);
					sindb.setCumulReglementAnne(cumulExercice);
					sindb.setDateValidation(dateVal);
					sindb.setRenteCreee(renteCreee);

				} catch (Exception e) {
					logger.error(EXP_MODIF_SINISTRE, e);
					throw new FonctionnelleException(EXP_MODIF_SINISTRE, e);
				}
				sindb.setRefEtatSinistres(refEtatSinistres);
				sinistre = sindb;
				dao.updateObject(sinistre);
			}
			if(traitementSuppressionAYDroit){
                sindb.getRefVictime().setTraitementSpecialDeletAyDroit(
                        Boolean.TRUE);
			}
			return sindb;
		} catch (FonctionnelleException e) {
			logger.error(EXP_MODIF_SINISTRE, e);
			throw new FonctionnelleException(EXP_MODIF_SINISTRE, e);
		} catch (PersistenceException e) {
			logger.error(EXP_MODIF_SINISTRE, e);
			throw new FonctionnelleException(EXP_MODIF_SINISTRE, e);
		}
	}

	/**
	 * Modification Sinistre suite a la crï¿½ation d'un certificat
	 * 
	 * @param sinistre
	 * @return
	 * @throws FonctionnelleException
	 */
	public Sinistre modifierSinistreCertificat(Sinistre sinistre)
			throws FonctionnelleException {

		try {
			Sinistre sindb = null;
            if (sinistre.getId() == 0 && sinistre.getNumeroSinistre() == null
						|| "".equals(sinistre.getNumeroSinistre())) {
					throw new FonctionnelleException(EXP_NUM_SIN_REQUIRED);
				}
			sindb = getSinistreById(sinistre.getId());
			Evenement evenDB = sindb.getRefEvenement();
			if (evenDB != null) {
				evenDB.setDateAccident(sinistre.getRefEvenement()
						.getDateAccident());
			}
			Victime vicdb = sindb.getRefVictime();
			if (vicdb != null) {
				vicdb.setSalaireAnnuel(sinistre.getRefVictime()
						.getSalaireAnnuel());
				vicdb.setSalaireAnnEstime(sinistre.getRefVictime()
						.getSalaireAnnEstime());
				vicdb.setDateNaissEstime(sinistre.getRefVictime()
						.getDateNaissEstime());
				vicdb.setDateNaissance(sinistre.getRefVictime()
						.getDateNaissance());
				vicdb.setDeces(sinistre.getRefVictime().getDeces());
			}
			dao.updateObject(sindb);
			return sindb;
		} catch (FonctionnelleException e) {
			logger.error(EXP_MODIF_SINISTRE, e);
			throw new FonctionnelleException(EXP_MODIF_SINISTRE, e);
		} catch (PersistenceException e) {
			logger.error(EXP_MODIF_SINISTRE, e);
			throw new FonctionnelleException(EXP_MODIF_SINISTRE, e);
		}
	}

	/**
	 * Rï¿½cupï¿½rer la liste des ï¿½tats du sinistre
	 * 
	 * @param codeEtat
	 * @param motif
	 * @param userCreateur
	 * @return
	 */
	public EtatSinistre getEtatSinistre(String codeEtat, String motif,
			String userCreateur) {

		EtatSinistre etatSinistre = new EtatSinistre(new EtatSin(codeEtat));
		etatSinistre.setDateEtat(new Date());
		etatSinistre.setMotifEtat(motif);
		etatSinistre.setUserCreateur(userCreateur);
		return etatSinistre;
	}

	/**
	 * Crï¿½ation Ayant Droit
	 * 
	 * @param ayantDroit
	 * @param numSin
	 * @return
	 * @throws FonctionnelleException
	 * @throws PersistenceException
	 */
	public AyantDroit creerAyantDroit(AyantDroit ayantDroit, String numSin)
			throws FonctionnelleException, PersistenceException {

		AyantDroit ay = null;
		Sinistre sin = getSinistreByNum(numSin);
		if (sin == null) {
			throw new FonctionnelleException(EXP_SINISTRE_INEXISTANT);
		}
		Victime vic = sin.getRefVictime();

		ayantDroit.setRefVictime(vic);
		ayantDroit.setDateCreation(new Date());

		ay = ayantDroit;
		dao.createObject(ay);

		return ay;

	}

	/**
	 * Modification Ayant Droit
	 * 
	 * @param ay
	 * @return
	 * @throws PersistenceException
	 * @throws FonctionnelleException
	 */
	public AyantDroit modifierAyantDroit(AyantDroit ay)
			throws PersistenceException, FonctionnelleException {

		AyantDroit ayDB = getAyantDroitById(ay.getId());
		if (ayDB == null) {
			throw new FonctionnelleException(EXP_AY_NOT_EXIST);
		}
		try {
			converterMetier.copyAyD(ayDB, ay);
		} catch (Exception e) {
			logger.error(EXP_STAND_MESS, e);
			throw new FonctionnelleException(EXP_STAND_MESS, e);
		}
		ay = ayDB;
		dao.updateObject(ay);
		return ay;

	}

	/**
	 * Supprimer Ayant Droit
	 * 
	 * @param ay
	 * @return
	 * @throws PersistenceException
	 * @throws FonctionnelleException
	 */
	public AyantDroit supprimerAyantDroit(AyantDroit ay)
			throws PersistenceException, FonctionnelleException {

		AyantDroit ayDB = getAyantDroitById(ay.getId());
		if (ayDB == null) {
			throw new FonctionnelleException(EXP_AY_NOT_EXIST);
		}
		
		ayDB.getRefVictime().getListAyantDroit().remove(ayDB);
		dao.delete(ayDB);

		return ay;

	}
	
	/**
	 * Valider les numï¿½ro d'ï¿½vï¿½nement saisi
	 * 
	 * @param sin
	 * @param even
	 * @return
	 * @throws FonctionnelleException
	 * @throws PersistenceException
	 */
	public String validateEvenement(Sinistre sin, Evenement even)
			throws FonctionnelleException, PersistenceException {

		String numEven = null;
		Integer numtest;

		if (sin.getEvenement() == true) {
			if (even.getFirstEvenement()) {
				if (even.getNumeroEvenement() != null
                        && !"".equals(even.getNumeroEvenement())) {
					numEven = even.getNumeroEvenement();
				} else {
					numEven = String.valueOf(
							getNumeroEvenement(even.getDateAccident()))
							.substring(6, 9);
				}
			} else {
				if (even.getNumeroEvenement() == null
						|| "".equals(even.getNumeroEvenement())) {
					throw new FonctionnelleException(EXP_NUM_EVEN_REQUIRED);
				}
				if (SinistreByNumEvenExiste(even)) {
					numEven = even.getNumeroEvenement();
				} else {
					throw new FonctionnelleException(EXP_NUMEVEN_EXISTE);
				}

			}
		}
		if (numEven != null && !numEven.equals("")) {
			numtest = Integer.parseInt(numEven);
			numEven = numtest.toString();
		}
		return numEven;
	}

	/**
	 * Creation Sinistre
	 * 
	 * @param sinistre
	 * @param useCase
	 * @param codeMotif
	 * @return
	 * @throws FonctionnelleException
	 */

    public Sinistre reCalculResGravProthesAvantCreerMouvement(
            Sinistre sinistre, boolean rachat) throws FonctionnelleException {

		try {
			
            if (!rachat) {
			sinistre.setReserveGrave(regleManager.getReserveGrave(sinistre));
            }
			sinistre.setReserveProthese(regleManager
					.getReserveProthese(sinistre));
		}

		catch (Exception e) {
			logger.error(EXP_CALCUL_RESERVE, e);
			throw new FonctionnelleException(EXP_CALCUL_RESERVE, e);
		}
		return sinistre;

	}

	public Sinistre calculSalAnnuelEtUtilAvantCreerMouvement(Sinistre sinistre)
			throws FonctionnelleException {

		try {
			Victime victime = sinistre.getRefVictime();
			Double salaireAnnuel = new BigDecimal(
					regleManager.calculSalaireAnnuel(sinistre)).setScale(2,
					BigDecimal.ROUND_HALF_EVEN).doubleValue();
			Double salaireUtile = new BigDecimal(
					regleManager.calculSalaireUtile(sinistre)).setScale(2,
					BigDecimal.ROUND_HALF_EVEN).doubleValue();

			if (victime != null) {
				victime.setSalaireAnnuel(salaireAnnuel);
				victime.setSalaireUtile(salaireUtile);
			}

		} catch (Exception e) {
			logger.error(EXP_CALCUL_RESERVE, e);
			throw new FonctionnelleException(EXP_CALCUL_RESERVE, e);
		}
		return sinistre;

	}

	public Mouvement creerMouvement(Sinistre sinistre, String useCase,
			long codeMotif) throws FonctionnelleException {
		try {
			Mouvement mvt = new Mouvement();
			MotifMouvement motifMouv = new MotifMouvement();
			Victime victime = sinistre.getRefVictime();
			Evenement evenement = sinistre.getRefEvenement();
			if (evenement != null) {
				mvt.setEvenementDec(evenement.getNumeroEvenement());
				mvt.setDateAccidentNew(evenement.getDateAccident());
			}
			if (victime != null) {
				mvt.setDateNaissanceNew(victime.getDateNaissance());
				mvt.setSalaireAnnuelNew(victime.getSalaireAnnuel());
				mvt.setSalaireUtileNew(victime.getSalaireUtile());
			}

			mvt.setIppNew(sinistre.getIpp());
			mvt.setIttNew(sinistre.getItt());
			mvt.setReserveGrave(sinistre.getReserveGrave());
			mvt.setReserveOrd(sinistre.getReserveOrdinaire());
			mvt.setReserveProthese(sinistre.getReserveProthese());
			mvt.setUserCreateur(sinistre.getUserCreateur());
			if (sinistre.getUserModificateur() != null) {
				mvt.setUserCreateur(sinistre.getUserModificateur());
			}
			mvt.setDateCreation(new Date());
			mvt.setRefSinistre(sinistre);
			motifMouv.setId(codeMotif);
			mvt.setRefMotif(motifMouv);
			mvt.setRefEtatSinistre(sinistre.getRefEtatSinistre());
			if (sinistre.getRefEtatSinistre() != null
					&& sinistre.getRefEtatSinistre().getRefEtat() != null) {
				mvt.setCodeEtatNew(Long.valueOf(sinistre.getRefEtatSinistre()
						.getRefEtat().getCode()));
			}
			// archiver les old valeurs
			Mouvement mvtOld = (Mouvement) getMvtByIdSinistre(sinistre);
			mvt.setByOld(mvtOld);
			dao.createObject(mvt);
			return mvt;
		} catch (FonctionnelleException e) {
			logger.error(EXP_CREER_MOUVEMENT, e);
			throw new FonctionnelleException(EXP_CREER_MOUVEMENT, e);
		} catch (PersistenceException e) {
			logger.error(EXP_CREER_MOUVEMENT, e);
			throw new FonctionnelleException(EXP_CREER_MOUVEMENT, e);
		} catch (Exception e) {
			logger.error(EXP_STAND_MESS, e);
			throw new FonctionnelleException(EXP_STAND_MESS, e);
		}

	}

	/**
	 * Verifier l'existance d'un sinsistre avec le num d'
	 * 
	 * @param numEven
	 * @return
	 * @throws FonctionnelleException
	 */
	public boolean SinistreByNumEvenExiste(Evenement even)
			throws FonctionnelleException {
		Boolean existe = false;
		StringBuffer hql = new StringBuffer(
				" select even from Evenement even where numeroEvenement='")
				.append(even.getNumeroEvenement().replaceAll(" ", "")).append(
						"'");

		try {
			List resultat = getSession().createQuery(hql.toString()).list();
            if (resultat != null && !resultat.isEmpty()) {
				for (int i = 0; i < resultat.size(); i++) {
					{
						Evenement evenement = (Evenement) resultat.get(i);
						int comparaison = compareAnne(
								evenement.getDateAccident(),
								even.getDateAccident());
						if (comparaison == 0) {
							existe = true;
						}
					}
				}
			}
			return existe;
		} catch (PersistenceException e) {
			logger.error(EXP_RECHERCHE_SINISTRE, e);
			throw new FonctionnelleException(EXP_RECHERCHE_SINISTRE, e);
		}

	}

	public int compareAnne(Date date1, Date date2) {
		Calendar toDay = DateUtile.dateCalendarCourante();
		toDay.setTime(date1);
		Integer annee = toDay.get(Calendar.YEAR);
		toDay.setTime(date2);
		Integer annee2 = toDay.get(Calendar.YEAR);
		return annee.compareTo(annee2);
	}

	/**
	 * Creer evenement reprise sinsitre
	 * 
	 * @param sinistre
	 * @return
	 * @throws FonctionnelleException
	 */
	public Mouvement creerMouvementReprise(Sinistre sinistre)
			throws FonctionnelleException {
		try {
			Mouvement mvt = new Mouvement();

			Victime victime = sinistre.getRefVictime();
			Evenement evenement = sinistre.getRefEvenement();

			mvt.setIppNew(sinistre.getIpp());
			mvt.setIttNew(sinistre.getItt());
			mvt.setDateNaissanceNew(victime.getDateNaissance());
			mvt.setDateAccidentNew(evenement.getDateAccident());
			mvt.setSalaireAnnuelNew(victime.getSalaireAnnuel());
			mvt.setSalaireUtileNew(victime.getSalaireUtile());
			mvt.setReserveGrave(sinistre.getReserveGrave());
			mvt.setReserveOrd(sinistre.getReserveOrdinaire());
			mvt.setReserveProthese(sinistre.getReserveProthese());
			mvt.setUserCreateur(sinistre.getUserModificateur());
			mvt.setDateCreation(new Date());
			mvt.setRefSinistre(sinistre);
			if (sinistre.getRefEtatSinistre() != null
					&& sinistre.getRefEtatSinistre().getRefEtat() != null) {
				mvt.setCodeEtatNew(Long.valueOf(sinistre.getRefEtatSinistre()
						.getRefEtat().getCode()));
			}
			// archiver les old valeurs
			Mouvement mvtOld = (Mouvement) getMvtByIdSinistre(sinistre);// Le
																		// sinistre
			mvt.setByOld(mvtOld);

			dao.createObject(mvt);

			return mvt;
		} catch (PersistenceException e) {
			logger.error(EXP_CREER_MOUVEMENT, e);
			throw new FonctionnelleException(EXP_CREER_MOUVEMENT, e);
		}
	}

	/**
	 * Creer Mouvement Etat
	 * 
	 * @param sinistre
	 * @param sin
	 * @param codeMotif
	 * @return
	 * @throws PersistenceException
	 * @throws FonctionnelleException
	 */
	public Mouvement creerMouvementEtat(Sinistre sinistre, Sinistre sin,
			long codeMotif) throws PersistenceException, FonctionnelleException {
		try {
			Mouvement mvt = new Mouvement();
			MotifMouvement motifMouv = new MotifMouvement();
			Victime victime = sinistre.getRefVictime();
			Evenement evenement = sinistre.getRefEvenement();

			sinistre.setReserveOrdinaire(sin.getReserveOrdinaire());
			sinistre.setReserveGrave(sin.getReserveGrave());
			sinistre.setReserveProthese(sin.getReserveProthese());
			sinistre.setUserModificateur(sin.getUserModificateur());
			sinistre.setMotifModification(sin.getMotifModification());

			mvt.setIppNew(sinistre.getIpp());
			mvt.setIttNew(sinistre.getItt());
			mvt.setDateNaissanceNew(victime.getDateNaissance());
			mvt.setDateAccidentNew(evenement.getDateAccident());
			mvt.setSalaireAnnuelNew(victime.getSalaireAnnuel());
			mvt.setSalaireUtileNew(victime.getSalaireUtile());
			mvt.setReserveGrave(sinistre.getReserveGrave());
			mvt.setReserveOrd(sinistre.getReserveOrdinaire());
			mvt.setReserveProthese(sinistre.getReserveProthese());
			mvt.setUserCreateur(sinistre.getUserModificateur());
			motifMouv.setId(codeMotif);
			mvt.setRefMotif(motifMouv);
			mvt.setMotifEtat(sinistre.getMotifModification());
			mvt.setDateCreation(new Date());
			mvt.setRefEtatSinistre(sinistre.getRefEtatSinistre());
			mvt.setRefSinistre(sinistre);
			if (sinistre.getRefEtatSinistre() != null
					&& sinistre.getRefEtatSinistre().getRefEtat() != null) {
				mvt.setCodeEtatNew(Long.valueOf(sinistre.getRefEtatSinistre()
						.getRefEtat().getCode()));
			}
			// archiver les old valeurs
			Mouvement mvtOld = (Mouvement) getMvtByIdSinistre(sinistre);
			// Le dernier mouvement correspond ï¿½ ce sinistre
			mvt.setByOld(mvtOld);

			dao.createObject(mvt);

			return mvt;
		} catch (PersistenceException e) {
			logger.error(EXP_CREER_MOUVEMENT, e);
			throw new FonctionnelleException(EXP_CREER_MOUVEMENT, e);
		}
	}

	/**
	 * Creer Mouvement Ayant droit
	 * 
	 * @param sinistre
	 * @param codeMotif
	 * @return
	 * @throws FonctionnelleException
	 * @throws ParseException 
	 */
	public Mouvement creerMouvementAY(Sinistre sinistre, long codeMotif)
			throws FonctionnelleException{
		try {
			Mouvement mvt = new Mouvement();
			MotifMouvement motifMouv = new MotifMouvement();
			Victime victime = sinistre.getRefVictime();
			Evenement evenement = sinistre.getRefEvenement();

			Double salaireAnnuelActuel = regleManager
					.calculSalaireAnnuel(sinistre);
			salaireAnnuelActuel = new BigDecimal(salaireAnnuelActuel).setScale(
					2, BigDecimal.ROUND_HALF_EVEN).doubleValue();
			victime.setSalaireAnnuelActuel(salaireAnnuelActuel);
			victime.setSalaireAnnuel(salaireAnnuelActuel);
			Double salaireUtileActuel;
			try {
				salaireUtileActuel = regleManager.calculSalaireUtile(sinistre);
			} catch (Exception e2) {
				logger.error(EXP_STAND_MESS, e2);
				throw new FonctionnelleException(EXP_STAND_MESS, e2);
			}
			salaireUtileActuel = new BigDecimal(salaireUtileActuel).setScale(2,
					BigDecimal.ROUND_HALF_EVEN).doubleValue();
			victime.setSalaireUtileActuel(salaireUtileActuel);
			victime.setSalaireUtile(salaireUtileActuel);

			List<AyantDroit> listAyantDroit = getListAyantDroitBySinistre(sinistre
					.getNumeroSinistre());
			Double reserveTotal = Double.valueOf(0) ;

			// calcul du taux de rente des ayant droits
			if (listAyantDroit != null) {
				
				// calcul du taux de rente des ayant droits
                // La nouvelle loi pour tout sinistre survenu après le
                // 22/01/2015 :
				String dateSurvenu = "22/01/2015";
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date dateSur;
				try {
					dateSur = sdf.parse(dateSurvenu);
				} catch (Exception e1) {
					logger.error(EXP_STAND_MESS, e1);
					throw new FonctionnelleException(EXP_STAND_MESS, e1);
				}
				try {
					
                    if (sinistre.getRefEvenement().getDateAccident()
                            .compareTo(dateSur) < 0) {
						listAyantDroit = regleManager.calculTauxRente(
								listAyantDroit, sinistre);
                    } else if (sinistre.getRefEvenement().getDateAccident()
                            .compareTo(dateSur) >= 0) {
						listAyantDroit = regleManager.calculTauxRenteR(
								listAyantDroit, sinistre);
					}

				} catch (Exception e1) {
					logger.error(EXP_STAND_MESS, e1);
					throw new FonctionnelleException(EXP_STAND_MESS, e1);
				}
			}
			// calcule reserve grave , ordinaire et prothese
			if (listAyantDroit != null) {
				
				for (int i = 0; i < listAyantDroit.size(); i++) {
					AyantDroit ay = (AyantDroit) listAyantDroit.get(i);
					Double reserve =  ay.getReserve();
					try {
						if (sinistre.getNumeroSinistre() != null) {
							// au moment de la modification
							// QC producation N° 160 : si jugement ne pas
							// recalculer la reserve grave			
                            Sinistre sini = (Sinistre) getSinistreByNum(sinistre
                                    .getNumeroSinistre());
                            if (!verifierAudiance(sinistre.getNumeroSinistre())
                                    && sini.getNumeroRente() == null) {
								reserve = regleManager.getReserveGraveAY(ay,
										sinistre);
								reserve = new BigDecimal(reserve).setScale(2,
										BigDecimal.ROUND_HALF_EVEN)
										.doubleValue();
								ay.setReserve(reserve);
								reserveTotal += reserve;
							} else {
								reserveTotal = sinistre.getReserveGrave();
							}
						} else {
							// au moment de la creation
							reserve = regleManager.getReserveGraveAY(ay,
									sinistre);
							reserve = new BigDecimal(reserve).setScale(2,
									BigDecimal.ROUND_HALF_EVEN).doubleValue();
							ay.setReserve(reserve);
							reserveTotal += reserve;

						}
					} catch (Exception e) {
						logger.error(EXP_CALCUL_RESERVE, e);
						throw new FonctionnelleException(EXP_CALCUL_RESERVE, e);
					}
				}
				victime.setListAyantDroit(listAyantDroit);
			}
			Double rord;
			if (sinistre.getReserveOrdinaire() != null) {
				rord = sinistre.getReserveOrdinaire();
			} else {
				try {
					rord = regleManager.getReserveOrdinaire(sinistre);
				} catch (Exception e) {
					logger.error(EXP_CALCUL_RESERVE, e);
					throw new FonctionnelleException(EXP_CALCUL_RESERVE, e);
				}
			}
			reserveTotal = new BigDecimal(reserveTotal).setScale(2,
					BigDecimal.ROUND_HALF_EVEN).doubleValue();
			sinistre.setReserveGrave(reserveTotal);
			sinistre.setReserveOrdinaire(rord);

			mvt.setIppNew(sinistre.getIpp());
			mvt.setIttNew(sinistre.getItt());
			mvt.setDateNaissanceNew(victime.getDateNaissance());
			mvt.setDateAccidentNew(evenement.getDateAccident());
			mvt.setSalaireAnnuelNew(victime.getSalaireAnnuel());
			mvt.setSalaireUtileNew(victime.getSalaireUtile());
			mvt.setReserveGrave(sinistre.getReserveGrave());
			mvt.setReserveOrd(sinistre.getReserveOrdinaire());
			mvt.setReserveProthese(sinistre.getReserveProthese());
			mvt.setUserCreateur(sinistre.getUserCreateur());
			if (sinistre.getUserModificateur() != null) {
				mvt.setUserCreateur(sinistre.getUserModificateur());
			}
			mvt.setDateCreation(new Date());
			motifMouv.setId(codeMotif);
			mvt.setRefEtatSinistre(sinistre.getRefEtatSinistre());
			mvt.setRefMotif(motifMouv);
			mvt.setRefSinistre(sinistre);
			if (sinistre.getRefEtatSinistre() != null
					&& sinistre.getRefEtatSinistre().getRefEtat() != null) {
				mvt.setCodeEtatNew(Long.valueOf(sinistre.getRefEtatSinistre()
						.getRefEtat().getCode()));
			}

			// archiver les old valeurs
            Mouvement mvtOld = (Mouvement) getMvtByIdSinistre(sinistre);
            // Le dernier mouvement correspond Ã¯Â¿Â½ // ce ï¿½ ce

			// pour une creation de sinistre le mouvement ayant droit ecrase le
			// mouvement creation sinistre
			if (mvtOld != null
					&& mvtOld.getRefMotif() != null
					&& mvtOld.getRefMotif().getId() == IConstantes.MOTIF_CREATION_SINISTRE) {
				logger.info("ecraser l'ancien mouvement");
				mvt.setByOld(mvtOld);
				mvtOld = mouvementCopy(mvtOld, mvt);
				dao.updateObject(mvtOld);
			} else {
				// sinistre
				mvt.setByOld(mvtOld);
				dao.createObject(mvt);
			}
			return mvt;
		} catch (FonctionnelleException e) {
			logger.error(EXP_CREER_MOUVEMENT, e);
			throw new FonctionnelleException(EXP_CREER_MOUVEMENT, e);
		} catch (PersistenceException e) {
			logger.error(EXP_CREER_MOUVEMENT, e);
			throw new FonctionnelleException(EXP_CREER_MOUVEMENT, e);
		}
	}

	/**
	 * Creer Mouvement Certificat
	 * 
	 * @param sinistre
	 * @return
	 * @throws FonctionnelleException
	 */
	public Mouvement creerMouvementCertificat(Sinistre sinistre)
			throws FonctionnelleException {
		try {

			Sinistre sindb = null;
            if (sinistre.getId() == 0 && sinistre.getNumeroSinistre() == null
						|| "".equals(sinistre.getNumeroSinistre())) {
					throw new FonctionnelleException(EXP_NUM_SIN_REQUIRED);
				}
			sindb = getSinistreById(sinistre.getId());

			Mouvement mvt = new Mouvement();

			Victime victime = sindb.getRefVictime();
			Evenement evenement = sindb.getRefEvenement();

			Double salaireUtile;
			try {
				salaireUtile = regleManager.calculSalaireUtile(sindb);
			} catch (Exception e1) {
				logger.error(EXP_STAND_MESS, e1);
				throw new FonctionnelleException(EXP_STAND_MESS, e1);
			}
			victime.setSalaireUtile(salaireUtile);
			Double salaireJournalier = regleManager
					.getSalaireJournalierByYear(sindb.getRefVictime()
							.getSalaireAnnuel());
			victime.setSalaireJournalier(salaireJournalier);

			// calcule reserve grave

			Double rgv;
			try {
				rgv = regleManager.getReserveGrave(sinistre);
			} catch (Exception e) {
				logger.error(EXP_CALCUL_RESERVE, e);
				throw new FonctionnelleException(EXP_CALCUL_RESERVE, e);
			}
			Double rord = regleManager.getReserveOrdinaireByITT(sinistre);

			sinistre.setReserveGrave(rgv);
			sinistre.setReserveOrdinaire(rord);

			mvt.setIppNew(sinistre.getIpp());
			mvt.setIttNew(sinistre.getItt());
			mvt.setDateNaissanceNew(victime.getDateNaissance());
			mvt.setDateAccidentNew(evenement.getDateAccident());
			mvt.setSalaireAnnuelNew(victime.getSalaireAnnuel());
			mvt.setSalaireUtileNew(victime.getSalaireUtile());
			mvt.setReserveGrave(sinistre.getReserveGrave());
			mvt.setReserveOrd(sinistre.getReserveOrdinaire());
			mvt.setReserveProthese(sinistre.getReserveProthese());
			mvt.setDateCreation(new Date());
			mvt.setRefSinistre(sinistre);

			// archiver les old valeurs
			Mouvement mvtOld = (Mouvement) getMvtByIdSinistre(sinistre);// Le
			if (sinistre.getRefEtatSinistre() != null
					&& sinistre.getRefEtatSinistre().getRefEtat() != null) {
				mvt.setCodeEtatNew(Long.valueOf(sinistre.getRefEtatSinistre()
						.getRefEtat().getCode()));
			} // dernier
				// mouvement
				// correspond
				// ï¿½ ce
				// sinistre
			mvt.setByOld(mvtOld);

			dao.createObject(mvt);

			return mvt;
		} catch (FonctionnelleException e) {
			logger.error(EXP_CREER_MOUVEMENT, e);
			throw new FonctionnelleException(EXP_CREER_MOUVEMENT, e);
		} catch (PersistenceException e) {
			logger.error(EXP_CREER_MOUVEMENT, e);
			throw new FonctionnelleException(EXP_CREER_MOUVEMENT, e);
		}
	}

	/**
	 * Calcul Reserve
	 * 
	 * @param sinistre
	 * @return
	 * @throws FonctionnelleException
	 */
	public Sinistre calculReserve(Sinistre sinistre, Boolean vargrave,
			Boolean varprothe,boolean rachat) throws FonctionnelleException {
		Sinistre sini= null;
		try {
			
			Victime victime = sinistre.getRefVictime();

			Double salaireAnnuelActuel = regleManager
					.calculSalaireAnnuel(sinistre);
			salaireAnnuelActuel = new BigDecimal(salaireAnnuelActuel).setScale(
					2, BigDecimal.ROUND_HALF_EVEN).doubleValue();
			victime.setSalaireAnnuelActuel(salaireAnnuelActuel);
			victime.setSalaireAnnuel(salaireAnnuelActuel);
			Double salaireUtileActuel;
			try {
				salaireUtileActuel = regleManager.calculSalaireUtile(sinistre);
			} catch (Exception e) {
				logger.error(EXP_STAND_MESS, e);
				throw new FonctionnelleException(EXP_STAND_MESS, e);
			}
			salaireUtileActuel = new BigDecimal(salaireUtileActuel).setScale(2,
					BigDecimal.ROUND_HALF_EVEN).doubleValue();
			victime.setSalaireUtileActuel(salaireUtileActuel);
			victime.setSalaireUtile(salaireUtileActuel);

			Double reserveGraveActuel = sinistre.getReserveGrave();
			if (vargrave) {
				try {
					// au moment de la modification
					if (sinistre.getNumeroSinistre() != null) {
						
                        // QC producation N° 160 : si jugement ne pas recalculer
                        // la reserve grave
                        // QC producation N° 117 : si une rente existe sur le
                        // sinistre ne pas recalculer la reserve grave
                        sini = (Sinistre) getSinistreByNum(sinistre
                                .getNumeroSinistre());
                        if (!verifierAudiance(sinistre.getNumeroSinistre())
                                && sini.getNumeroRente() == null && !rachat) {
							reserveGraveActuel = regleManager
									.getReserveGrave(sinistre);
						}
					} else {                                                      
						// au moment de la creation
						reserveGraveActuel = regleManager
								.getReserveGrave(sinistre);
					}

				} catch (Exception e) {
					logger.error(EXP_CALCUL_RESERVE, e);
					throw new FonctionnelleException(EXP_CALCUL_RESERVE, e);
				}
			} else {
				reserveGraveActuel = sinistre.getReserveGrave();
			}
			// si le champ rï¿½serve ordinaire est renseignï¿½ on prend la
			// valeur
			// renseignï¿½e
			// comme rï¿½serve ordinaire, sinon on garde celle de l'actuariat.

			Double reserveOrdinaireActuel;
			if (sinistre.getReserveOrdinaire() != null) {
				reserveOrdinaireActuel = sinistre.getReserveOrdinaire();
			} else {
				try {
					reserveOrdinaireActuel = regleManager
							.getReserveOrdinaire(sinistre);
				} catch (Exception e) {
					logger.error(EXP_CALCUL_RESERVE, e);
					throw new FonctionnelleException(EXP_CALCUL_RESERVE, e);
				}
			}
			Double reserveProtheseActuel;

			if (varprothe) {
				try {
					reserveProtheseActuel = regleManager
							.getReserveProthese(sinistre);
				} catch (Exception e) {
					logger.error(EXP_CALCUL_RESERVE, e);
					throw new FonctionnelleException(EXP_CALCUL_RESERVE, e);
				}
			} else {

				reserveProtheseActuel = sinistre.getReserveProthese();
			}

			sinistre.setReserveGraveActuel(reserveGraveActuel);
			sinistre.setReserveOrdinaireActuel(reserveOrdinaireActuel);
			sinistre.setReserveProtheseActuel(reserveProtheseActuel);

			if (sinistre.getReserveOrdinaire() == null) {
				sinistre.setReserveOrdinaire(Double.valueOf(0));
			}
			Double cumulReserve = sinistre.getReserveGrave()
					+ sinistre.getReserveOrdinaire()
					+ sinistre.getReserveProthese();
			sinistre.setCumulReserve(cumulReserve);

			Double cumulReserveActuel = sinistre.getReserveGraveActuel()
					+ sinistre.getReserveOrdinaireActuel()
					+ sinistre.getReserveProtheseActuel();
			sinistre.setCumulReserveActuel(cumulReserveActuel);

			return sinistre;

		} catch (FonctionnelleException e) {
			logger.error(EXP_CALCUL_RESERVE, e);
			throw new FonctionnelleException(EXP_CALCUL_RESERVE, e);
		}
	}

	/**
	 * Calcul Reserve Ayant Droit
	 * 
	 * @param sinistre
	 * @return
	 * @throws FonctionnelleException
	 * @throws ParseException 
	 */
	public Sinistre calculReserveAY(Sinistre sinistre)
			throws FonctionnelleException {
		try {
			Victime victime = sinistre.getRefVictime();

			Double salaireAnnuelActuel = regleManager
					.calculSalaireAnnuel(sinistre);
			salaireAnnuelActuel = new BigDecimal(salaireAnnuelActuel).setScale(
					2, BigDecimal.ROUND_HALF_EVEN).doubleValue();
			victime.setSalaireAnnuelActuel(salaireAnnuelActuel);
			victime.setSalaireAnnuel(salaireAnnuelActuel);
			Double salaireUtileActuel;
			try {
				salaireUtileActuel = regleManager.calculSalaireUtile(sinistre);
			} catch (Exception e) {
				logger.error(EXP_STAND_MESS, e);
				throw new FonctionnelleException(EXP_STAND_MESS, e);
			}
			salaireUtileActuel = new BigDecimal(salaireUtileActuel).setScale(2,
					BigDecimal.ROUND_HALF_EVEN).doubleValue();
			victime.setSalaireUtileActuel(salaireUtileActuel);
			victime.setSalaireUtile(salaireUtileActuel);

			List listAyantDroit = null;
			if (!StringUtils.isEmpty(sinistre.getNumeroSinistre())) {
				listAyantDroit = getListAyantDroitBySinistre(sinistre
						.getNumeroSinistre());
			}
			Double reserveTotal = Double.valueOf(0);
			// calcul du taux de rente des ayant droits
			if (listAyantDroit != null) {
				
				// calcul du taux de rente des ayant droits
                // La nouvelle loi pour tout sinistre survenu après le
                // 22/01/2015 :
				String dateSurvenu = "22/01/2015";
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date dateSur;
				try {
					dateSur = sdf.parse(dateSurvenu);
				} catch (Exception e1) {
					logger.error(EXP_STAND_MESS, e1);
					throw new FonctionnelleException(EXP_STAND_MESS, e1);
				}
				try {
					
                    if (sinistre.getRefEvenement().getDateAccident()
                            .compareTo(dateSur) < 0) {
						listAyantDroit = regleManager.calculTauxRente(
								listAyantDroit, sinistre);
                    } else if (sinistre.getRefEvenement().getDateAccident()
                            .compareTo(dateSur) >= 0) {
						listAyantDroit = regleManager.calculTauxRenteR(
								listAyantDroit, sinistre);
					}

				} catch (Exception e1) {
					logger.error(EXP_STAND_MESS, e1);
					throw new FonctionnelleException(EXP_STAND_MESS, e1);
				}
			}
			// calcule reserve grave , ordinaire et prothese
			if (listAyantDroit != null) {
                Sinistre sini = (Sinistre) getSinistreByNum(sinistre
                        .getNumeroSinistre());
                boolean modifierRG = false;
                if (!verifierAudiance(sinistre.getNumeroSinistre())
                        && sini.getNumeroRente() == null) {
                    modifierRG = true;
				}
				for (int i = 0; i < listAyantDroit.size(); i++) {
					AyantDroit ay = (AyantDroit) listAyantDroit.get(i);
					Double reserve = ay.getReserve();
					try {
						// au moment de la modification
						if (sinistre.getNumeroSinistre() != null) {
							// QC producation N° 160 : si jugement ne pas
							// recalculer la reserve grave
                            if (modifierRG) {
								reserve = regleManager.getReserveGraveAY(ay,
										sinistre);
							}
						} else {
							// au moment de la creation
							reserve = regleManager.getReserveGraveAY(ay,
									sinistre);
						}
					} catch (Exception e) {
						logger.error(EXP_CALCUL_RESERVE, e);
						throw new FonctionnelleException(EXP_CALCUL_RESERVE, e);
					}
					reserve = new BigDecimal(reserve).setScale(2,
							BigDecimal.ROUND_HALF_EVEN).doubleValue();
					ay.setReserve(reserve);
					reserveTotal += reserve;

				}
			}
			// si le champ rï¿½serve ordinaire est renseignï¿½ on prend la
			// valeur
			// renseignï¿½e
			// comme rï¿½serve ordinaire, sinon on garde celle de l'actuariat.
			Double rord;
			if (sinistre.getReserveOrdinaire() != null) {
				rord = sinistre.getReserveOrdinaire();
			} else {
				try {
					rord = regleManager.getReserveOrdinaire(sinistre);
				} catch (Exception e) {
					logger.error(EXP_CALCUL_RESERVE, e);
					throw new FonctionnelleException(EXP_CALCUL_RESERVE, e);
				}
			}

			reserveTotal = new BigDecimal(reserveTotal).setScale(2,
					BigDecimal.ROUND_HALF_EVEN).doubleValue();
			sinistre.setReserveGraveActuel(reserveTotal);
			sinistre.setReserveOrdinaireActuel(rord);

			Double reserveGrave = sinistre.getReserveGrave();
			reserveGrave = new BigDecimal(reserveGrave).setScale(2,
					BigDecimal.ROUND_HALF_EVEN).doubleValue();
			sinistre.setReserveGrave(reserveGrave);

			if (sinistre.getReserveOrdinaire() == null) {
				sinistre.setReserveOrdinaire(Double.valueOf(0));
			}
			Double cumulReserve = sinistre.getReserveGrave()
					+ sinistre.getReserveOrdinaire()
					+ sinistre.getReserveProthese();
			sinistre.setCumulReserve(cumulReserve);

			Double cumulReserveActuel = sinistre.getReserveGraveActuel()
					+ sinistre.getReserveOrdinaireActuel()
					+ sinistre.getReserveProtheseActuel();
			sinistre.setCumulReserveActuel(cumulReserveActuel);

			return sinistre;

		} catch (FonctionnelleException e) {
			logger.error(EXP_CALCUL_RESERVE, e);
			throw new FonctionnelleException(EXP_CALCUL_RESERVE, e);
		} catch (PersistenceException e) {
			logger.error(EXP_CALCUL_RESERVE, e);
			throw new FonctionnelleException(EXP_CALCUL_RESERVE, e);
		}
	}

	/**
	 * Calcul Cummul Rï¿½glement
	 * 
	 * @param sinistre
	 * @return
	 * @throws FonctionnelleException
	 */
	public Sinistre calculCumulReglement(Sinistre sinistre)
			throws FonctionnelleException {

		try {

			// Reserve
			Double reserveGrave = sinistre.getReserveGrave();
			reserveGrave = new BigDecimal(reserveGrave).setScale(2,
					BigDecimal.ROUND_HALF_EVEN).doubleValue();
			sinistre.setReserveGrave(reserveGrave);

			Double cumulReserve = sinistre.getReserveGrave()
					+ sinistre.getReserveOrdinaire()
					+ sinistre.getReserveProthese();

			cumulReserve = new BigDecimal(cumulReserve).setScale(2,
					BigDecimal.ROUND_HALF_EVEN).doubleValue();

			sinistre.setCumulReserve(cumulReserve);

			Double cumulReserveActuel = sinistre.getReserveGraveActuel()
					+ sinistre.getReserveOrdinaireActuel()
					+ sinistre.getReserveProtheseActuel();

			cumulReserveActuel = new BigDecimal(cumulReserveActuel).setScale(2,
					BigDecimal.ROUND_HALF_EVEN).doubleValue();

			sinistre.setCumulReserveActuel(cumulReserveActuel);

			Double coutAncien = sinistre.getCumulReserve()
					+ sinistre.getCumulReglementAnne()
					+ sinistre.getCumulReglementAnnePrec();

			coutAncien = new BigDecimal(coutAncien).setScale(2,
					BigDecimal.ROUND_HALF_EVEN).doubleValue();

			sinistre.setCoutSinistreAncien(coutAncien);

			Double cumulReglement = sinistre.getCumulReglementAnne()
					+ sinistre.getCumulReglementAnnePrec();

			cumulReglement = new BigDecimal(cumulReglement).setScale(2,
					BigDecimal.ROUND_HALF_EVEN).doubleValue();

			sinistre.setCumulReglement(cumulReglement);

			Double coutNouveau = sinistre.getCumulReserveActuel()
					+ sinistre.getCumulReglementAnne()
					+ sinistre.getCumulReglementAnnePrec();

			coutNouveau = new BigDecimal(coutNouveau).setScale(2,
					BigDecimal.ROUND_HALF_EVEN).doubleValue();

			sinistre.setCoutSinistreNouveau(coutNouveau);
			
			//supprimer sin anterieur
					
			//

			dao.updateObject(sinistre);

			return sinistre;
		} catch (PersistenceException e) {
			logger.error(EXP_CALCUL_CUMUL_REG, e);
			throw new FonctionnelleException(EXP_CALCUL_RESERVE, e);
		}
	}

	/**
	 * Calcul Cummul Reserve
	 * 
	 * @param sinistre
	 * @return
	 * @throws FonctionnelleException
	 */

	/**
	 * Recherche sinistre par Numï¿½ro sinistre
	 * 
	 * @param numSin
	 * @return
	 * @throws FonctionnelleException
	 */
	public Sinistre getSinistreByNum(String numSin)
			throws FonctionnelleException {
		Sinistre sinistre = null;
		try {
			if (numSin == null) {
				return null;
			}
			String hql = " from Sinistre sin where sin.numeroSinistre like '%"
					+ numSin.replaceAll(" ", "") + "%'";

			sinistre = (Sinistre) getSession().createQuery(hql).uniqueResult();
			if (sinistre != null) {
				DossierRente dossier = new DossierRente();
				dossier.setRefSinistre(new Sinistre());
				dossier.getRefSinistre().setId(sinistre.getId());
				List<DossierRente> dossierRentes;

				dossierRentes = new DossierRenteManager()
						.doGetDossierRenteBySinistre(dossier, 0, 0);
				if (dossierRentes != null && !dossierRentes.isEmpty()
						&& dossierRentes.get(0).getValidation() != null
						&& dossierRentes.get(0).getValidation()) {
					sinistre.setNumeroRente(String.valueOf(dossierRentes.get(0)
							.getNumeroRente()));
				}
			}
		} catch (Exception e) {
			logger.error(EXP_RECHERCHE_SINISTRE + "NUM SIN : "+ numSin, e);
			throw new FonctionnelleException(EXP_RECHERCHE_SINISTRE, e);
		}
		return sinistre;
	}

	/**
	 * Recherche Liste sinistre Par Numï¿½ro Sinistre
	 * 
	 * @param numSin
	 * @return
	 * @throws FonctionnelleException
	 */
	public List<Sinistre> RechercheSinistreByNum(String numSin)
			throws FonctionnelleException {
		try {
			if (numSin == null) {
                return new ArrayList<Sinistre>();
			}
			String hql = " from Sinistre sin where sin.numeroSinistre like'%"
					+ numSin.replaceAll(" ", "") + "%'";

			Query query = getSession().createQuery(hql.toString());
			return query.list();

		} catch (Exception e) {
			logger.error(EXP_RECHERCHE_SINISTRE, e);
			throw new FonctionnelleException(EXP_RECHERCHE_SINISTRE, e);
		}
	}

	/**
	 * Vï¿½rifier s'il ya des audiance jugement sur le dossier sinistre en
	 * question
	 * 
	 * @param numSin
	 * @return
	 * @throws FonctionnelleException
	 */
	public Boolean verifierAudiance(String numSin)
			throws FonctionnelleException {
		Boolean justice = false;
		try {
			if (numSin == null) {
				return null;
			}
			StringBuffer hql = new StringBuffer(
					" select a from AudienceJugement a,ProcedureJudiciaire p, "
							+ " Tribunal t,Recours r,Sinistre s ");

			hql.append(" where 1=1 ");
			hql.append(" and a.refProcedureJudiciaire.id=p.id ");
			hql.append(" and p.refRecours.id=r.id");
			hql.append(" and p.refJuridiction.id=t.id");
			hql.append(" and r.refSinistre.id=s.id");
			hql.append(" and s.numeroSinistre like'%")
					.append(numSin.replaceAll(" ", "")).append("%' ");
			Query query = getSession().createQuery(hql.toString());
			List<AudienceJugement> audiance = query.list();
			for (AudienceJugement audienceJugement : audiance) {
				if (Jugement(audienceJugement)) {
					justice = true;
				}
			}

		} catch (Exception e) {
			logger.error(EXP_RECHERCHE_SINISTRE, e);
			throw new FonctionnelleException(EXP_RECHERCHE_SINISTRE, e);
		}
		return justice;
	}

	/**
	 * Verifier l'existance d'un jugement
	 * 
	 * @param idSinistre
	 * @return
	 * @throws HibernateException
	 * @throws PersistenceException
	 */
	public Boolean Jugement(AudienceJugement audienceJugement)
			throws HibernateException, PersistenceException {
		boolean existe = false;
		// IPP
		if (audienceJugement.getIppJugement() != null) {
			existe = true;
		}
		// Date Depart rentes
		if (audienceJugement.getDateDepartRente() != null) {
			existe = true;
		}
		// Salaire Jugement
		if (audienceJugement.getSalaireJugement() != null) {
			existe = true;
		}
		// Reserve Grave
		if (audienceJugement.getReserveGrave() != null) {
			existe = true;
		}
		// Reserve Ordianaire
		if (audienceJugement.getReserveOrdinaire() != null) {
			existe = true;
		}
		return existe;
	}

	/**
	 * Vérifier l'existance d'un certificat
	 * 
	 * @param idSinistre
	 * @return
	 * @throws HibernateException
	 * @throws PersistenceException
	 */
	public Boolean verifierCertificat(String idSinistre)
			throws HibernateException, PersistenceException {
		boolean existe = false;
		Long idSin = Long.valueOf(idSinistre);
		List listCertifGuerisson = certificatMedicalManager
				.getListCertificatsByType("2", idSin);
		List listCertifJudiciaire = certificatMedicalManager
				.getListCertificatsByType("6", idSin);
		if (!listCertifGuerisson.isEmpty() || !listCertifJudiciaire.isEmpty()) {
			existe = true;
		}
		return existe;
	}

	/**
	 * Recherche Sinistre par ID
	 * 
	 * @param id
	 * @return
	 * @throws FonctionnelleException
	 */
	public Sinistre getSinistreById(Object... objects)
			throws FonctionnelleException {

		Sinistre sinistre = null;
        Session sessionLocal = null;
		if (objects == null) {
			return null;
		}

		try {
			Long id = null;
			if (objects.length == 2) {
				id = (Long) objects[0];
                sessionLocal = (Session) objects[1];

			} else {
				id = (Long) objects[0];
                sessionLocal = getSession();
			}

            sinistre = (Sinistre) sessionLocal.get(Sinistre.class, id);
			if (sinistre != null) {
				DossierRente dossier = new DossierRente();
				dossier.setRefSinistre(new Sinistre());
				dossier.getRefSinistre().setId(sinistre.getId());
				List<DossierRente> dossierRentes;

				dossierRentes = new DossierRenteManager()
						.doGetDossierRenteBySinistre(dossier, 0, 0);
				if (dossierRentes != null && !dossierRentes.isEmpty()
						&& dossierRentes.get(0).getValidation() != null
						&& dossierRentes.get(0).getValidation()) {
					sinistre.setNumeroRente(String.valueOf(dossierRentes.get(0)
							.getNumeroRente()));
				}
			}

		} catch (Exception e) {
			logger.error(EXP_RECHERCHE_SINISTRE, e);
			throw new FonctionnelleException(EXP_RECHERCHE_SINISTRE, e);
		}

		return sinistre;
	}

	/**
	 * Recherche Eveneemnt par ID
	 * 
	 * @param idEvenement
	 * @return
	 * @throws PersistenceException
	 */
	public Evenement getEvenementById(Long idEvenement)
			throws PersistenceException {

		if (idEvenement == null) {
			return null;
		}
		String hql = " from Evenement evenement where evenement.id="
				+ idEvenement;
		return (Evenement) getSession().createQuery(hql).uniqueResult();

	}

	public AudienceJugement getAUdienceById(Long id)
			throws PersistenceException {

		if (id == null) {
			return null;
		}
		String hql = " from AudienceJugement audience where audience.id=" + id;
		return (AudienceJugement) getSession().createQuery(hql).uniqueResult();

	}

	/**
	 * Recherche Victime Par ID
	 * 
	 * @param idVictime
	 * @return
	 * @throws PersistenceException
	 */
	public Victime getVictimeById(long idVictime) throws PersistenceException {

		if (idVictime == 0) {
			return null;
		}

		String hql = "from Victime v where v.id=" + idVictime;
		return (Victime) getSession().createQuery(hql).uniqueResult();

	}

	/**
	 * Recherche Ayant Droit par ID
	 * 
	 * @param idAY
	 * @return
	 * @throws PersistenceException
	 */
	public AyantDroit getAyantDroitById(long idAY) throws PersistenceException {

		if (idAY == 0) {
			return null;
		}

		String hql = " from AyantDroit ayantDroit where ayantDroit.id=" + idAY;
		return (AyantDroit) getSession().createQuery(hql).uniqueResult();

	}

	/**
	 * 
	 * @param sin
	 * @return
	 * @throws PersistenceException
	 * @throws FonctionnelleException
	 */
	public Mouvement getMvtByIdSinistre(Object... objects)
			throws PersistenceException, FonctionnelleException {
		String hql = null;

		Sinistre sin = null;
		Session sessionLocale = null;
		if (objects == null) {
			return null;
		}
		if (objects.length == 2) {
			sin = (Sinistre) objects[0];
			sessionLocale = (Session) objects[1];

		} else {
			sin = (Sinistre) objects[0];
			sessionLocale = getSession();
		}

		if (sin.getId() == 0) {
			if (sin.getNumeroSinistre() == null
					|| "".equals(sin.getNumeroSinistre())) {
				throw new FonctionnelleException(EXP_NUM_SIN_REQUIRED);
			} else {
				hql = " from Mouvement mvt where  mvt.refSinistre.numeroSinistre='"
						+ sin.getNumeroSinistre() + "' order by mvt.id desc";
			}
		} else {
			hql = " from Mouvement mvt where  mvt.refSinistre=" + sin.getId()
					+ " order by mvt.id desc";
		}
		Query query = sessionLocale.createQuery(hql);
		if (!query.list().isEmpty()) {
			return (Mouvement) query.list().get(0);
		}
		return null;
	}

	private String getNumeroSinistre(Date dateAccident, String prefix)
			throws FonctionnelleException {
		Calendar curCal = new GregorianCalendar();
		if (dateAccident != null) {
			curCal.setTime(dateAccident);
		}
		return getSequenceNumSinistre(curCal.get(Calendar.YEAR), prefix);
	}

	public String getNumeroGrave(Date dateAccident)
			throws FonctionnelleException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateAccident);
		String numGrave = getSequenceNumGrave(cal.get(Calendar.YEAR));
		return numGrave;
	}

	public Sinistre modifierEtatSinistre(Sinistre sinistre)
			throws FonctionnelleException {

		Sinistre sin = null;
		try {
			if (sinistre.getId() == 0) {
				if (sinistre.getNumeroSinistre() == null
						|| "".equals(sinistre.getNumeroSinistre())) {
					throw new FonctionnelleException(EXP_NUM_DOSSIER_NOT_VALIDE);
				}
				sin = getSinistreByNum(sinistre.getNumeroSinistre());

			} else {
				sin = getSinistreById(sinistre.getId());
			}

			if (sin == null) {
				throw new FonctionnelleException(EXP_SINISTRE_INEXISTANT);
			}

			String codetat = sin.getRefEtatSinistre().getRefEtat().getCode();
			String codetatcible = sinistre.getEtatCible();

			if (StringUtils.isEmpty(codetatcible)) {
				throw new FonctionnelleException(EXP_ETAT_REQUIRED);
			}
			String cas = "1";
            if ("1".equals(codetat) || "2".equals(codetat)) {
				cas = "1";
            } else if ("3".equals(codetat) || "4".equals(codetat)) {
				cas = "2";
			}
			Double valide;
			try {
				valide = parametrageManager.getAuthorisationEtatSQL(codetat,
						codetatcible, cas);
			} catch (Exception e) {
				logger.error(EXP_STAND_MESS, e);
				throw new FonctionnelleException(EXP_STAND_MESS, e);
			}
			if (Double.valueOf(1).compareTo(valide) != 0) {
				throw new FonctionnelleException(CHANGEMENT_ETAT_NON_AUTHORISE);
			}

			EtatSinistre etatsinistre = getEtatSinistre(codetatcible,
					sinistre.getMotifModification(),
					sinistre.getUserModificateur());
			etatsinistre.setRefSinistre(sin);
			dao.createObject(etatsinistre);
			sin.addEtatSinistre(etatsinistre);
			sin.setDateModification(new Date());
			sin.setUserModificateur(sinistre.getUserModificateur());
			dao.updateObject(sin);

			return sin;
		} catch (FonctionnelleException e) {
			logger.error(EXP_MODIF_ETAT, e);
			throw new FonctionnelleException(EXP_MODIF_ETAT, e);
		} catch (PersistenceException e) {
			logger.error(EXP_MODIF_ETAT, e);
			throw new FonctionnelleException(EXP_MODIF_ETAT, e);
		}
	}

	public Sinistre verifierReserveSinistre(Sinistre sinistre)
			throws FonctionnelleException {

		Sinistre sin = null;
		try {
			if (sinistre.getId() == 0) {
				if (sinistre.getNumeroSinistre() == null
						|| "".equals(sinistre.getNumeroSinistre())) {
					throw new FonctionnelleException(EXP_NUM_DOSSIER_NOT_VALIDE);
				}
				sin = getSinistreByNum(sinistre.getNumeroSinistre());

			} else {
				sin = getSinistreById(sinistre.getId());
			}

			if (sin == null) {
				throw new FonctionnelleException(EXP_SINISTRE_INEXISTANT);
			}

			String codetat = sin.getRefEtatSinistre().getRefEtat().getCode();
			String codetatcible = sinistre.getEtatCible();
			if (sinistre.getReserveOrdinaire() == null) {
				sinistre.setReserveOrdinaire(Double.valueOf(0));
			}

			if (StringUtils.isEmpty(codetatcible)) {
				throw new FonctionnelleException(EXP_ETAT_REQUIRED);
			}
			String cas = "1";
            if ("1".equals(codetat) || "2".equals(codetat)) {
				cas = "1";
            } else if ("3".equals(codetat) || "4".equals(codetat)) {
				cas = "2";
			}
			Double valide;
			try {
				valide = parametrageManager.getAuthorisationEtatSQL(codetat,
						codetatcible, cas);
			} catch (Exception e) {
				logger.error(EXP_STAND_MESS, e);
				throw new FonctionnelleException(EXP_STAND_MESS, e);
			}
			if (Double.valueOf(1).compareTo(valide) != 0) {
				throw new FonctionnelleException(CHANGEMENT_ETAT_NON_AUTHORISE);
			}

            if ("2".equals(codetatcible)) {
				if (sinistre.getReserveGrave() != 0
						|| sinistre.getReserveProthese() != 0) {
					throw new FonctionnelleException(
							EXP_RESERVES_GRAVE_PRO_NON_ZERO);
				}
				if (sinistre.getReserveOrdinaire() == 0) {
					throw new FonctionnelleException(EXP_RESERVE_ORD_CERO);
				}

            } else if ("3".equals(codetatcible) || "4".equals(codetatcible)) {
				if (sinistre.getReserveGrave() != 0
						|| sinistre.getReserveOrdinaire() != 0
						|| sinistre.getReserveProthese() != 0) {
					throw new FonctionnelleException(EXP_RESERVES_NON_ZERO);
				}

            } else if ("1".equals(codetatcible)
                    && sinistre.getReserveGrave() == 0
						&& sinistre.getReserveOrdinaire() == 0
						&& sinistre.getReserveProthese() == 0) {
                throw new FonctionnelleException(EXP_RESERVE_NON_ZERO_REQUIRED);

			}
			return sin;
		} catch (FonctionnelleException e) {
			logger.error(EXP_MODIF_ETAT_VERIF_RESERVES, e);
			throw new FonctionnelleException(EXP_MODIF_ETAT_VERIF_RESERVES, e);
		}
	}

	private Sinistre modifierSinistreRgl(Object... objects)
			throws FonctionnelleException {
		Sinistre sinistre = null;
		Session sessionLocale = null;
		try {
			if (objects.length == 2) {
				sinistre = (Sinistre) objects[0];
				sessionLocale = (Session) objects[1];
			} else {
				sinistre = (Sinistre) objects[0];
				sessionLocale = getSession();
			}

			Sinistre sinDB = getSinistreById(sinistre.getId(), sessionLocale);
			String codetatcible = sinistre.getEtatCible();

			if (!StringUtils.isEmpty(codetatcible)) {
				EtatSinistre etatsinistre = getEtatSinistre(codetatcible,
						MOTIF_MOUVEMENT_SIN_REG, sinistre.getUserModificateur());
				etatsinistre.setRefSinistre(sinDB);
				dao.createObject(etatsinistre);
				sinDB.addEtatSinistre(etatsinistre);
			}
			sinDB.setReserveGrave(sinistre.getReserveGrave());
			sinDB.setReserveOrdinaire(sinistre.getReserveOrdinaire());
			sinDB.setReserveProthese(sinistre.getReserveProthese());
			sinDB.setDateModification(new Date());
			sinDB.setUserModificateur(sinistre.getUserModificateur());
			EtatSinistre refEtatSinistre = sinDB.getRefEtatSinistre();
			if (sinistre.getRefEtatSinistre() != null) {
				refEtatSinistre.setRefEtat((sinistre.getRefEtatSinistre())
						.getRefEtat());
				sinDB.setRefEtatSinistre(refEtatSinistre);
			}
			return sinDB;
		} catch (PersistenceException e) {
			logger.error(EXP_MODIF_SIN_REG, e);
			throw new FonctionnelleException(EXP_MODIF_SIN_REG, e);
		}
	}

	private Sinistre modifierSinistreAudiance(Sinistre sinistre,
			AudienceJugement audiance) throws FonctionnelleException {
		Sinistre sinDB = getSinistreById(sinistre.getId());
		sinDB.setReserveGrave(audiance.getReserveGrave());
		sinDB.setReserveOrdinaire(audiance.getReserveOrdinaire());
		sinDB.setReserveProthese(audiance.getReserveProthese());
		sinDB.setDateModification(new Date());

		sinDB.setUserModificateur(sinistre.getUserModificateur());
		if (audiance.getSalaireJugement() != null) {
			sinDB.setSalaireJugement(audiance.getSalaireJugement());
			sinDB.getRefVictime()
					.setSalaireUtile(audiance.getSalaireJugement());
			sinDB.getRefVictime().setSalaireAnnuel(
					audiance.getSalaireJugement());
		}
		// MFBO@Ano : 557
		if (audiance.getIppJugement() != null) {
			sinDB.setIpp(audiance.getIppJugement());
			sinDB.setIppJugement(audiance.getIppJugement().doubleValue());
		}
		sinDB.setDateDepartRente(audiance.getDateDepartRente());

		return sinDB;
	}

	public boolean modifierEtatListSinistre(List<SinistreVO> listSin,
            Map param) throws FonctionnelleException {

		if (listSin == null || listSin.isEmpty()) {
			return false;
		}

		try {

			for (SinistreVO sinvo : (List<SinistreVO>) listSin) {
				Sinistre sin = null;
				if (sinvo.getId() == null) {
					if (sinvo.getNumeroSinistre() == null
							|| "".equals(sinvo.getNumeroSinistre())) {
						continue;
					}
					sin = getSinistreByNum(sinvo.getNumeroSinistre());
				} else {
					sin = new Sinistre(Long.valueOf(sinvo.getId()));
				}
				EtatSinistre etatSin = new EtatSinistre();
                if ("0".equals(sin.getTypeCreation())) {
					etatSin = getEtatSinistre("1", MOTIF_ETAT1,
							(String) param.get(CONSTANTE_USER_MODIFICATEUR));
					sin.setDateValidation(new Date());
                } else if ("1".equals(sin.getTypeCreation())) {
					if (sin.getRefEtatSinistre() != null
							&& (sin.getRefEtatSinistre()).getRefEtat() != null
                            && "5".equals(((sin.getRefEtatSinistre())
                                    .getRefEtat()).getCode())) {
						etatSin = getEtatSinistre("1", MOTIF_ETAT1,
								(String) param.get(CONSTANTE_USER_MODIFICATEUR));
						sin.setDateValidation(new Date());
					} else {
						etatSin = getEtatSinistre("5", MOTIF_ETAT5,
								(String) param.get(CONSTANTE_USER_MODIFICATEUR));
					}
				}
				etatSin.setRefSinistre(sin);
				dao.createObject(etatSin);
				sin.setUserModificateur((String) param
						.get(IConstantes.CONSTANTE_USER_MODIFICATEUR));
				sin.setDateModification(new Date());
				sin.setRefEtatSinistre(etatSin);
				// operation a revoir si on doit les supprimer
				creerMouvement(sin, null,
						IConstantes.MOTIF_MODIFICATION_SINISTRE);
			}

			return true;
		} catch (PersistenceException e) {
			logger.error(EXP_MODIF_LIST_ETAT_SIN, e);
			throw new FonctionnelleException(EXP_MODIF_LIST_ETAT_SIN, e);
		}
	}

	public synchronized String getSequenceNumSinistre(int annee, String prefix)
			throws FonctionnelleException {
		CallableStatement call = null;
		Connection connection = null;
		try {
			connection = getSession().connection();
			String sql = "{call PROC_NUM_SINISTRE_AT(?,?,?,?)}";
			call = connection.prepareCall(sql);
			call.setString(1, String.valueOf(annee));
			call.setString(2, "0");
			call.setString(3, prefix);
			call.registerOutParameter(4, Types.VARCHAR);
			call.execute();
			return String.valueOf(call.getString(4));

		} catch (SQLException e) {
			logger.error(EXP_SQL, e);
			throw new FonctionnelleException(EXP_SQL, e);
		} catch (HibernateException e) {
			logger.error(EXP_STAND_MESS, e);
			throw new FonctionnelleException(EXP_STAND_MESS, e);
		} catch (PersistenceException e) {
			logger.error(EXP_STAND_MESS, e);
			throw new FonctionnelleException(EXP_STAND_MESS, e);
		} finally {

			if (call != null) {
				try {
					call.close();
				} catch (SQLException e) {
					logger.error(EXP_SQL, e);
					throw new FonctionnelleException(EXP_SQL, e);
				} finally {
					call = null;
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e2) {
					logger.error("Erreur base de donnï¿½e", e2);
					throw new FonctionnelleException("Erreur base de donnÃ©e",
							e2);
				} finally {
					connection = null;
				}
			}
		}

	}

	public synchronized String getSequenceNumGrave(int annee)
			throws FonctionnelleException {
		CallableStatement call = null;
		Connection connection = null;
		try {
			connection = getSession().connection();
			String sql = "{call PROC_NUM_GRAVE_AT(?,?)}";

			call = connection.prepareCall(sql);
			call.setString(1, String.valueOf(annee));
			call.registerOutParameter(2, Types.VARCHAR);
			call.execute();
			return String.valueOf(call.getString(2));

		} catch (SQLException e) {
			logger.error(EXP_SQL, e);
			throw new FonctionnelleException(EXP_SQL, e);
		} catch (HibernateException e) {
			logger.error(EXP_STAND_MESS, e);
			throw new FonctionnelleException(EXP_STAND_MESS, e);
		} catch (PersistenceException e) {
			logger.error(EXP_STAND_MESS, e);
			throw new FonctionnelleException(EXP_STAND_MESS, e);
		} finally {

			if (call != null) {
				try {
					call.close();
				} catch (SQLException e) {
					logger.error(EXP_SQL, e);
					throw new FonctionnelleException(EXP_SQL, e);
				} finally {
					call = null;
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e2) {
					logger.error("Erreur base de donnï¿½e", e2);
					throw new FonctionnelleException("Erreur base de donnï¿½e",
							e2);
				} finally {
					connection = null;
				}
			}
		}
	}

	public synchronized String getSequenceNumSinistreBis(int annee, int mois)
			throws PersistenceException {

		String sql = "select numerosinistre from SIN_SINISTRE where id=(select Max(id) from SIN_SINISTRE)";
		List q = dao.executeSqlQuery(sql);

		if (q == null || q.isEmpty()) {
			return null;
		}
		String num = ((String) q.get(0)).substring(19);
		return ((String) q.get(0)).substring(0, 19)
				+ String.valueOf(Long.valueOf(num) + 1);
	}

	private Long getNumeroEvenement(Date dateAccident)
			throws FonctionnelleException {
		Calendar curCal = new GregorianCalendar();
		curCal.setTime(dateAccident);
		Long numEvenement = getSequenceNumEvenement(curCal.get(Calendar.YEAR));

		return numEvenement;
	}

	public synchronized Long getSequenceNumEvenement(int annee)
			throws FonctionnelleException {
		CallableStatement call = null;
		Connection connection = null;
		try {
			connection = getSession().connection();
			String sql = "{call PROC_NUM_EVENEMENT(?,'0',?)}";
			call = connection.prepareCall(sql);
			call.setString(1, String.valueOf(annee));
			call.registerOutParameter(2, Types.VARCHAR);
			call.execute();
			return Long.valueOf(call.getString(2));

		} catch (SQLException e) {
			logger.error(EXP_SQL, e);
			throw new FonctionnelleException(EXP_SQL, e);
		} catch (HibernateException e) {
			logger.error(EXP_STAND_MESS, e);
			throw new FonctionnelleException(EXP_STAND_MESS, e);
		} catch (PersistenceException e) {
			logger.error(EXP_STAND_MESS, e);
			throw new FonctionnelleException(EXP_STAND_MESS, e);
		} finally {

			if (call != null) {
				try {
					call.close();
				} catch (SQLException e) {
					logger.error(EXP_SQL, e);
					throw new FonctionnelleException(EXP_SQL, e);
				} finally {
					call = null;
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e2) {
					logger.error("Erreur base de donnï¿½e", e2);
					throw new FonctionnelleException("Erreur base de donnï¿½e",
							e2);
				} finally {
					connection = null;
				}
			}
		}
	}

	public synchronized Long getSequenceNumEvenementBis(int annee, int mois)
			throws PersistenceException {

		String sql = "select numeroevenement from SIN_EVENEMENT where id=(select Max(id) from SIN_EVENEMENT)";
		List q = dao.executeSqlQuery(sql);

		if (q == null || q.isEmpty()) {
			return null;
		}
		String num = ((String) q.get(0)).substring(6);
		return Long.valueOf(((String) q.get(0)).substring(0, 6)
				+ String.valueOf(Long.valueOf(num) + 1));
	}

    public List<Sinistre> getListSinistre(Sinistre sinistre, Map map,
			PagerVO pagerVO) throws FonctionnelleException {
		if (sinistre == null) {
            return new ArrayList<Sinistre>();
		}
        Query query = this.getListSinistreQuery(sinistre, (HashMap) map);
		if (pagerVO != null) {
			Integer numpage = 0;
			if (Integer.valueOf(pagerVO.getNumPage()).intValue() > 0) {
				numpage = Integer.valueOf(pagerVO.getNumPage()) - 1;
			}
			return this.getPartCollectionByCondition(query, numpage,
					Integer.valueOf(pagerVO.getPageSize()));
		} else {
			return query.list();
		}
	}

    public List<Sinistre> getListAyantDroit(AyantDroit ay, Map map,
			PagerVO pagerVO) throws FonctionnelleException {

		if (ay == null) {
            return new ArrayList<Sinistre>();
		}
        Query query = this.getListAyantDroitQuery(ay, (HashMap) map);
		if (pagerVO != null) {
			return this.getPartCollectionByCondition(query,
					Integer.valueOf(pagerVO.getNumPage()),
					Integer.valueOf(pagerVO.getPageSize()));
		} else {
			return query.list();
		}
	}

    private Query getListSinistreQuery(Sinistre sinistre, Map paramsDate)
			throws FonctionnelleException {

		nombreSinistre = false;
		
		if (sinistre == null) {
			throw new FonctionnelleException(EXP_SIN_NULL);
		}
		try {
			Query query = getSession().createQuery(
                    getListSinistreSql(sinistre, nombreSinistre))
                    .setMaxResults(
					Long.valueOf(IParam.MAX_SINISTRE).intValue() + 1);
			return query;
		} catch (PersistenceException e) {
			logger.error(EXP_STAND_MESS, e);
			throw new FonctionnelleException(EXP_STAND_MESS, e);
		}
	}

	private String getListSinistreSql(Sinistre sinistre,Boolean nombreSinistre)
			throws FonctionnelleException {

		if (sinistre == null) {
			throw new FonctionnelleException(EXP_SIN_NULL);
		}
		try {
			
			StringBuffer hql = null;
            if (nombreSinistre == true) {
                hql = new StringBuffer(
                        "select count(*) from Sinistre sinistre  ");
            } else {
				hql = new StringBuffer("from Sinistre sinistre  ");
			}
			
			hql.append(" where 1=1 ");
			// id
			if (sinistre.getId() != 0) {
				hql.append(" and sinistre.id=").append(sinistre.getId())
						.append(" ");
			}
			// numSinistre
			if (!StringUtils.isEmpty(sinistre.getNumeroSinistre())
					&& !StringUtils.isEmpty(sinistre.getNumeroSinistre()
							.replaceAll(" ", ""))) {
				hql.append(" and sinistre.numeroSinistre like '%")
						.append((sinistre.getNumeroSinistre().trim())
								.replaceAll(" ", "")).append("%' ");
			}
			if (sinistre.getRefVictime() != null) {
			if (!StringUtils.isEmpty(sinistre.getRefVictime()
					.getNumeroCIN())
					&& !StringUtils.isEmpty(sinistre.getRefVictime()
							.getNumeroCIN().trim())) {
				hql.append(" and sinistre.refVictime.numeroCIN = '")
						.append(sinistre.getRefVictime().getNumeroCIN()
								.replaceAll(" ", "")).append("' ");
				/*hql.append(" and sinistre.refVictime.numeroCIN = '")
				.append(sinistre.getRefVictime().getNumeroCIN()).append("' ");*/
			}
			}
			

			if (!StringUtils.isEmpty(sinistre.getNumeroGrave())
					&& !StringUtils.isEmpty(sinistre.getNumeroGrave().trim())) {
				hql.append(" and sinistre.numeroGrave='")
						.append(sinistre.getNumeroGrave().trim()).append("' ");
			}

			if (!StringUtils.isEmpty(sinistre.getNumeroPolice())
					&& !StringUtils.isEmpty(sinistre.getNumeroPolice().trim())) {
				hql.append(" and sinistre.numeroPolice like '%")
						.append(sinistre.getNumeroPolice().replaceAll(" ", ""))
						.append("%' ");
			}

			if (sinistre.getRefVictime() != null) {
				if (!StringUtils.isEmpty(sinistre.getRefVictime().getNom())
						&& !StringUtils.isEmpty(sinistre.getRefVictime()
								.getNom().trim())) {

					hql.append(" and upper(sinistre.refVictime.nom) like '%")
							.append(StringUtils.upperCase(sinistre
									.getRefVictime().getNom())).append("%' ");

				}
				//Evo 32
				if (!StringUtils.isEmpty(sinistre.getRefVictime().getPrenom())
						&& !StringUtils.isEmpty(sinistre.getRefVictime()
								.getPrenom().trim())) {
					hql.append(" and upper(sinistre.refVictime.prenom) like '%")
							.append(StringUtils.upperCase(sinistre
                                    .getRefVictime().getPrenom()))
                            .append("%' ");
				}
				
				//Fin Evo
				
			}
			if (!StringUtils.isEmpty(sinistre.getNomIntermediaire())
					&& !StringUtils.isEmpty(sinistre.getNomIntermediaire()
							.trim())) {
				hql.append(" and upper(sinistre.nomIntermediaire) like '%")
						.append(StringUtils.upperCase(sinistre
								.getNomIntermediaire())).append("%' ");
			}

			if (!StringUtils.isEmpty(sinistre.getCodeIntermediaire())
					&& !StringUtils.isEmpty(sinistre.getCodeIntermediaire()
							.trim())) {
				hql.append(" and upper(sinistre.codeIntermediaire)like'%")
						.append(StringUtils.upperCase(sinistre
								.getCodeIntermediaire().replaceAll(" ", "")))
						.append("%' ");
			}

			if (sinistre.getRefEvenement() != null
					&& !StringUtils.isEmpty(sinistre.getRefEvenement()
							.getNumeroEvenement())
					&& !StringUtils.isEmpty(sinistre.getRefEvenement()
							.getNumeroEvenement().trim())) {
				hql.append(" and sinistre.refEvenement.numeroEvenement ='")
						.append(sinistre.getRefEvenement().getNumeroEvenement()
								.replaceAll(" ", "")).append("' ");
			}

			if (sinistre.getRefEvenement() != null
					&& sinistre.getRefEvenement().getDateAccident() != null) {

				Date dateAccident = sinistre.getRefEvenement()
						.getDateAccident();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

				if (dateAccident != null) {
					hql.append(" and sinistre.refEvenement.dateAccident = to_date('"
							+ sdf.format(dateAccident.getTime())
							+ "', 'dd/MM/yyyy HH24:MI:SS')");
				}

			}

			if (sinistre.getNumeroRecours() != null) {

				hql.append(" and sinistre.idRecours=")
						.append(sinistre.getNumeroRecours()).append(" ");
			}
			if (!StringUtils.isEmpty(sinistre.getNumeroTribunal())
					&& !StringUtils.isEmpty(sinistre.getNumeroTribunal()
							.replaceAll(" ", ""))) {
				hql.append(
						" and sinistre.id in (select p.refRecours.refSinistre.id "
								+ " from ProcedureJudiciaire p where p.numeroDossierTribunal = '")
						.append(sinistre.getNumeroTribunal()
								.replaceAll(" ", "")).append("') ");
			}
			if (!StringUtils.isEmpty(sinistre.getCivilResponsable())
					&& !StringUtils.isEmpty(sinistre.getCivilResponsable()
							.trim())) {
				hql.append(
						" and sinistre.id in (select r.refSinistre.id "
								+ " from Recours r where upper(r.nomResponsableCivile) like '%")
						.append(StringUtils.upperCase(sinistre
								.getCivilResponsable().replaceAll(" ", "")))
						.append("%') ");
			}
			/** Les dates */
			if (sinistre.getRefEvenement() != null) {
				hql.append(construireDcDate(sinistre.getRefEvenement()
						.getDateAccidentDebut(), sinistre.getRefEvenement()
						.getDateAccidentFin(),
						"sinistre.refEvenement.dateAccident"));
			}

			if (sinistre.getDateCreation() != null) {
				String dateC = dateFormat.format(sinistre.getDateCreation());
				hql.append(" and dateCreation between to_date('").append(dateC)
						.append("  00:00:00','").append(IDate.FORMAT_HHMM_SQL)
						.append("')").append(" and  to_date('").append(dateC)
						.append("  23:59:59','").append(IDate.FORMAT_HHMM_SQL)
						.append("')");

			}

			/*** limiter le nombre de ligne ï¿½ 50 ***/
			// A eviter : probleme dans WAS !!
			//hql.append(" order by substring(sinistre.numeroSinistre,12,4) DESC,substring(sinistre.numeroSinistre,16) ASC");
			
			//Evol MAD ordre de tri 06/12/2021 il faut s'assurer que refEvenement est renseigné pour les autres menu de recherche
			//Evol MAD ordre de tri 06/12/2021
			if(sinistre.getRefEvenement() != null) {
			if((sinistre.getRefEvenement().getDateAccident() == null && sinistre.getNumeroPolice() == null) ||
					(sinistre.getRefEvenement().getDateAccident() != null && sinistre.getNumeroPolice() != null)) {
				hql.append(" order by substring(sinistre.numeroSinistre,12,4) DESC,substring(sinistre.numeroSinistre,16) ASC");
				}
			//Filtre choix par date accident
			if(sinistre.getRefEvenement().getDateAccident() != null && sinistre.getNumeroPolice() == null) {
			hql.append("order by substring(sinistre.numeroPolice,8,4) DESC, sinistre.numeroGrave ASC");
			}
			//Filtre choix N police 
			if(sinistre.getNumeroPolice() != null && sinistre.getRefEvenement().getDateAccident() == null) {
			hql.append("order by  sinistre.refEvenement.dateAccident DESC, sinistre.numeroGrave ASC");
			}
			} else { //le cas ou refevenement null
				if(sinistre.getNumeroPolice() != null) {//ordonner par police
					hql.append("order by  sinistre.refEvenement.dateAccident DESC, sinistre.numeroGrave ASC");
				} else {//dans les autres cas ordonner par numero sinistre
					hql.append(" order by substring(sinistre.numeroSinistre,12,4) DESC,substring(sinistre.numeroSinistre,16) ASC");
				}
			}
			return hql.toString();

		} catch (PersistenceException e) {
			logger.error(EXP_STAND_MESS, e);
			throw new FonctionnelleException(EXP_STAND_MESS, e);
		}
	}

	public List<Sinistre> getListSinistreByAllName(Sinistre sinistre)
			throws FonctionnelleException {

		if (sinistre == null) {
			throw new FonctionnelleException(EXP_SIN_NULL);
		}
		try {

			StringBuffer hql = new StringBuffer("from Sinistre sinistre  ");

			hql.append(" where 1=1 ");

			if (sinistre.getRefVictime() != null) {
				if (!StringUtils.isEmpty(sinistre.getRefVictime().getNom())) {
					hql.append(" and upper(sinistre.refVictime.nom)='")
							.append(StringUtils.upperCase(sinistre
									.getRefVictime().getNom().trim()))
							.append("'");
				}
				if (!StringUtils.isEmpty(sinistre.getRefVictime().getPrenom())) {
					hql.append(" and upper(sinistre.refVictime.prenom)='")
							.append(StringUtils.upperCase(sinistre
									.getRefVictime().getPrenom().trim()))
							.append("' ");
				}
			}
			Query query = getSession().createQuery(hql.toString())
					.setMaxResults(12);

			return query.list();
		} catch (PersistenceException e) {
			logger.error(EXP_STAND_MESS, e);
			throw new FonctionnelleException(EXP_STAND_MESS, e);
		}

	}

    private Query getListAyantDroitQuery(AyantDroit ay, Map param)
			throws FonctionnelleException {

		if (ay == null) {
			throw new FonctionnelleException(EXP_SIN_NULL);
		}
		try {

			StringBuffer hql = new StringBuffer(
					" select ay from AyantDroit ay ,Sinistre sin ");
			hql.append(" where 1=1 ");

			String numSin = (String) param.get(IParam.NUM_SINISTRE);
			if (numSin != null) {
				hql.append(" and sin.numeroSinistre='").append(numSin)
						.append("' ");
			}
			hql.append(" and sin.refVictime.id=ay.refVictime.id").append(" ");
			if (ay.getNom() != null) {
				hql.append(" and ay.nom='").append(ay.getNom()).append("' ");
			}
			if (ay.getPrenom() != null) {
				hql.append(" and ay.prenom='").append(ay.getPrenom())
						.append("' ");
			}
			if (ay.getNumeroCIN() != null) {
				hql.append(" and ay.numeroCIN='").append(ay.getNumeroCIN())
						.append("' ");
			}
			if (ay.getDateNaissance() != null) {
				hql.append(" and ay.dateNaissance='")
						.append(ay.getDateNaissance()).append("' ");
			}
			if (ay.getDateDeces() != null) {
				hql.append(" and ay.dateDeces='").append(ay.getDateDeces())
						.append("' ");
			}

			/*** limiter le nombre de ligne ï¿½ 50 ***/

			Query query = getSession().createQuery(hql.toString())
					.setMaxResults(21);

			return query;
		} catch (PersistenceException e) {
			logger.error(EXP_STAND_MESS, e);
			throw new FonctionnelleException(EXP_STAND_MESS, e);
		}
	}

	private String construireDcDate(Date dateDebut, Date dateFin,
			String property) throws PersistenceException {

		String whereBetween = "";
		try {
			if (dateDebut != null && dateFin != null) {
				// betwen
				whereBetween += " and " + property + " between to_date('"
						+ dateFormat.format(dateDebut) + "  00:00:00','"
						+ IDate.FORMAT_HHMM_SQL + "') " + "and to_date('"
						+ dateFormat.format(dateFin) + " 23:59:59','"
						+ IDate.FORMAT_HHMM_SQL + "')";
			} else {
				if (dateFin != null) {
					// inferiere a daateFin
					whereBetween += " and " + property + " between to_date('"
							+ IDate.DATE_MIN + " 00:00:00','"
							+ IDate.FORMAT_HHMM_SQL + "') " + "and to_date('"
							+ dateFormat.format(dateFin) + " 23:59:59','"
							+ IDate.FORMAT_HHMM_SQL + "')";

				} else if (dateDebut != null) {
					// superier ï¿½ dateDebut
					whereBetween += " and " + property + " between to_date('"
							+ dateFormat.format(dateDebut) + " 00:00:00','"
							+ IDate.FORMAT_HHMM_SQL + "') " + "and to_date('"
							+ IDate.DATE_MAX + " 23:59:59','"
							+ IDate.FORMAT_HHMM_SQL + "')";
				}
			}

		} catch (Exception e) {
			throw new PersistenceException(this.getClass(), "",
					PersistenceException.SYSTEM, e);
		}
		return whereBetween;
	}

	public List getPartCollectionByCondition(Query query, int page, int pageSize) {
		if (query != null) {
			query.setFirstResult(page * pageSize);
			query.setMaxResults(pageSize);
			return query.list();
		} else {
            return new ArrayList();
		}
	}

	/**
	 * Rï¿½cupï¿½rer la Session hibernate via la DAO
	 * 
	 * @return
	 * @throws PersistenceException
	 */
	public Session getSession() throws PersistenceException {
		if (session != null) {
			return session;
		} else {
			return (Session) dao.getSession();
		}
	}

    public Long getNombreSinistre(Sinistre sinistre) throws Exception {
		nombreSinistre = true;
		String hql = getListSinistreSql(sinistre,nombreSinistre);
		return (Long) getSession().createQuery(hql).uniqueResult();

	}

	public void copySinistre(Sinistre sinDB, Sinistre sinistre) {
		sinDB.setCodeClient(sinistre.getCodeClient());
		sinDB.setCodeIntermediaire(sinistre.getCodeIntermediaire());
		sinDB.setDiagnostique(sinistre.getDiagnostique());
		sinDB.setIdRecours(sinistre.getIdRecours());
		sinDB.setIdTransaction(sinistre.getIdTransaction());
		sinDB.setRecours(sinistre.getRecours());
		sinDB.setNomClient(sinistre.getNomClient());
		sinDB.setObservation(sinistre.getObservation());
		sinDB.setUserCreateur(sinistre.getUserCreateur());
	}

	public void copyEvenement(Evenement evenDB, Evenement even) {
		evenDB.setNumeroEvenement(even.getNumeroEvenement());
		evenDB.setRefTypeAccident(even.getRefTypeAccident());
		evenDB.setDateAccident(even.getDateAccident());
		evenDB.setFirstEvenement(even.getFirstEvenement());
		evenDB.setLieuAccident(even.getLieuAccident());
		evenDB.setRefCause(even.getRefCause());
		evenDB.setRefZone(even.getRefZone());
	}

	public void copyVictime(Victime vicDB, Victime vic) {
		vicDB.setAdresse(vic.getAdresse());
		vicDB.setDateDeces(vic.getDateDeces());
		vicDB.setDateNaissance(vic.getDateNaissance());
		vicDB.setDeces(vic.getDeces());
		vicDB.setNom(vic.getNom());
		vicDB.setNumeroCIN(vic.getNumeroCIN());
		vicDB.setPrenom(vic.getPrenom());
	}

	public void copyAyD(AyantDroit ayd, AyantDroit ay) {
		ayd.setAdresse(ay.getAdresse());
		ayd.setDateDeces(ay.getDateNaissance());
		ayd.setRefDegreParente(new DegreParente(ay.getRefDegreParente()
				.getCode()));
		ayd.setDeces(ay.getDeces());
		ayd.setNom(ay.getNom());
		ayd.setNumeroCIN(ay.getNumeroCIN());
		ayd.setPrenom(ay.getPrenom());
	}

	public void setListAyantDroit(Victime vicDB, Victime vic)
			throws PersistenceException {
		List listAyantDroit = new ArrayList<AyantDroit>();
		List listAY = (List) vic.getListAyantDroit();
		for (AyantDroit ay : (List<AyantDroit>) listAY) {
			if (ay.getId() == 0) {
				ay.setRefVictime(vicDB);
				dao.createObject(ay);
			} else {
				AyantDroit ayd = getAyantDroitById(Long.valueOf(ay.getId()));
				try {
					converterMetier.copyAyD(ayd, ay);
				} catch (Exception e) {
                    logger.log(Level.ERROR, "Exception", e);
				}
				ay = ayd;
			}
			listAyantDroit.add(ay);
		}
		vicDB.setListAyantDroit(listAyantDroit);
	}

	public List getListSinistreByEtat(String etatSinistre,
			String intermediaire, String typeCreation, PagerVO pager)
			throws PersistenceException {

		boolean ok = false;
		StringBuffer hql = new StringBuffer(
				" select distinct sinistre from Sinistre sinistre , VEtatSinistre vEtat ");

		hql.append(" where 1=1 ");
		// id
		if (etatSinistre != null) {
			hql.append(" and vEtat.refEtat=?");
			hql.append(" and sinistre.id=vEtat.refSinistre.id");
			ok = true;
		}
        if (intermediaire != null && !"".equals(intermediaire)) {
			hql.append(" and sinistre.codeIntermediaire like '%")
					.append(intermediaire).append("%' ");
		}
        if (typeCreation != null && !"".equals(typeCreation)) {
			hql.append(" and sinistre.typeCreation='").append(typeCreation)
					.append("' ");
		}

		hql.append(" order by (sinistre.numeroSinistre) ");
		Query query = getSession().createQuery(hql.toString());
		if (ok) {
			query.setParameter(0, new EtatSin(etatSinistre));
		}

		if (pager != null) {
			Integer numpage = Integer.valueOf(0);
			if (Integer.valueOf(pager.getNumPage()).intValue() > 0) {
				numpage = Integer.valueOf(pager.getNumPage()) - 1;
			}
			return this.getPartCollectionByCondition(query, numpage,
					Integer.valueOf(pager.getPageSize()));
		} else {
			return query.list();
		}
	}

	@SuppressWarnings("rawtypes")
	public List getListAyantDroitBySinistre(String numeroSinistre)
			throws PersistenceException {

		StringBuffer hql = new StringBuffer(
				" select victime.listAyantDroit from Sinistre sin ");
		hql.append(" join sin.refVictime as victime ");
		hql.append(" where 1=1 ");
		// id
		if (numeroSinistre != null) {
			hql.append(" and sin.numeroSinistre='").append(numeroSinistre)
					.append("'");
		}

		Query query = getSession().createQuery(hql.toString())
				.setMaxResults(20);
		return query.list();
	}

	public List getListAyantDroitByIdSinistre(Long id)
			throws FonctionnelleException {
		try {
			StringBuffer hql = new StringBuffer(
					" select ay from AyantDroit ay where ay.refVictime.id = (select sin.refVictime.id from Sinistre sin where sin.id="
							+ id + ")");
			return getSession().createQuery(hql.toString()).list();
		} catch (PersistenceException e) {
			logger.error(EXP_RECHERCHE + " "
					+ AyantDroit.class.getSimpleName().toUpperCase(), e);
			throw new FonctionnelleException(EXP_RECHERCHE + " "
					+ AyantDroit.class.getSimpleName().toUpperCase(), e);
		}
	}

	@SuppressWarnings("rawtypes")
	public List getListReglementBySinistre(String numeroSinistre)
			throws PersistenceException {

		StringBuffer hql = new StringBuffer(" from Reglement rgl ");
		hql.append(" where 1=1 ");
		// id
		if (numeroSinistre != null) {
			hql.append(" and rgl.refSinistre.numeroSinistre='")
					.append(numeroSinistre).append("'");
		}

		Query query = getSession().createQuery(hql.toString())
				.setMaxResults(50);

		return query.list();
	}

	@SuppressWarnings("rawtypes")
    public List<PoliceVO> getListPolice(PoliceVO police, Map map)
			throws FonctionnelleException {

		if (police == null) {
            return new ArrayList<PoliceVO>();
		}

		List<PoliceVO> listPolice = null;

		Map<String, String> mappolice = new HashMap<String, String>();
		if (!StringUtils.isEmpty(police.getNumeroPolice())) {
			mappolice.put("PLCNUMPLCE", police.getNumeroPolice().trim());
		}
		if (!StringUtils.isEmpty(police.getEtat().trim())) {
			mappolice.put("PLCETATPLC", police.getEtat());
		}
		if (!StringUtils.isEmpty(police.getCodeIntermediaire().trim())) {
			mappolice.put("INTCODINTR", police.getCodeIntermediaire().trim());
		}
		if (!StringUtils.isEmpty(police.getNomIntermediaire().trim())) {
			mappolice.put("INTNOMINTR", police.getNomIntermediaire().trim());
		}
		if (!StringUtils.isEmpty(police.getCodeClient().trim())) {
			mappolice.put("PLCNUMCLIE", police.getCodeClient().trim());
		}
		if (!StringUtils.isEmpty(police.getNomClient().trim())) {
			mappolice.put("PLCNOMCLNT", police.getNomClient().trim());
		}
		if (!StringUtils.isEmpty(police.getDateOuvertuteSinistre().trim())) {
			mappolice.put("DATEOUVERTURE", police.getDateOuvertuteSinistre()
					.trim());
		}
		if (!StringUtils.isEmpty(police.getDateSurvenanceSinistre().trim())) {
			mappolice.put("DATESURVENACE", police.getDateSurvenanceSinistre()
					.trim());
		}

		// EX RMA
		mappolice.put("EXRMA", police.getNumeroPoliceExRMA());

		mappolice.put("nbrLignes", "20");

		List<Map<String, Object>> mapres = null;
		List<Map<String, Object>> mapResultat = null;
		mapres = getListPoliceInfo(mappolice);
		mapResultat = getListPoliceMultiRisqueInfo(mappolice);

		mapres.addAll(0, mapResultat);

		if (mapres != null) {
			listPolice = new ArrayList<PoliceVO>();
			for (Map<String, Object> mpp : (List<Map<String, Object>>) mapres) {

				PoliceVO policeRes = new PoliceVO();

				policeRes.setNumeroPolice((String) mpp.get("NumeroPolice"));
				policeRes.setCodeClient(String.valueOf((Long) mpp
						.get("NumeroClient")));

				if (mpp.get("forfaitOuRevis") != null) {
					try {
						String test = ((String) mpp.get("forfaitOuRevis"))
								.trim();
						test = URLEncoder.encode(test, "UTF-8").replaceAll(
								"%1A", "");
						test = replaceSpecialCharacter
								.getSpecialCharacter(test);
						policeRes.setForfaitOurevis(test);
					} catch (UnsupportedEncodingException e) {
                        logger.log(Level.ERROR, "Exception", e);
						throw new IllegalArgumentException();
					}

				}

				if (mpp.get("NomClient") != null) {

					try {
						String test = ((String) mpp.get("NomClient")).trim();
						test = URLEncoder.encode(test, "UTF-8").replaceAll(
								"%1A", "");
						test = replaceSpecialCharacter
								.getSpecialCharacter(test);
						policeRes.setNomClient(test);
					} catch (UnsupportedEncodingException e) {
                        logger.log(Level.ERROR, "Exception", e);
						throw new IllegalArgumentException();
					}

				}
				if (mpp.get("CodeClassification") != null) {
					try {
						String test = ((String) mpp.get("CodeClassification"))
								.trim();
						test = URLEncoder.encode(test, "UTF-8").replaceAll(
								"%1A", "");
						test = replaceSpecialCharacter
								.getSpecialCharacter(test);
						policeRes.setCodeClassification(test);
					} catch (UnsupportedEncodingException e) {
                        logger.log(Level.ERROR, "Exception", e);
						throw new IllegalArgumentException();
					}

				}
				if (mpp.get("AdresseAssure") != null) {

					try {
						String test = ((String) mpp.get("AdresseAssure"))
								.trim();
						test = URLEncoder.encode(test, "UTF-8").replaceAll(
								"%1A", "");
						test = replaceSpecialCharacter
								.getSpecialCharacter(test);
						policeRes.setAdresseAssure(test);
					} catch (UnsupportedEncodingException e) {
                        logger.log(Level.ERROR, "Exception", e);
						throw new IllegalArgumentException();
					}

				}
				if (mpp.get("CodeActivite") != null) {
					try {
						String test = ((String) mpp.get("CodeActivite")).trim();
						test = URLEncoder.encode(test, "UTF-8").replaceAll(
								"%1A", "");
						test = replaceSpecialCharacter
								.getSpecialCharacter(test);
						policeRes.setCodeActivite(test);
					} catch (UnsupportedEncodingException e) {
                        logger.log(Level.ERROR, "Exception", e);
						throw new IllegalArgumentException();
					}

				}
				if (mpp.get("TypeIntermediaire") != null) {
					try {
						String test = ((String) mpp.get("TypeIntermediaire"))
								.trim();
						test = URLEncoder.encode(test, "UTF-8").replaceAll(
								"%1A", "");
						test = replaceSpecialCharacter
								.getSpecialCharacter(test);
						policeRes.setTypeIntermediaire(test);
					} catch (UnsupportedEncodingException e) {
                        logger.log(Level.ERROR, "Exception", e);
						throw new IllegalArgumentException();
					}

				}
				if (mpp.get("CodeIntermediaire") != null) {
					policeRes.setCodeIntermediaire(String.valueOf(mpp
							.get("CodeIntermediaire")));
				}

				if (mpp.get("NomIntermediaire") != null) {
					try {
						String test = ((String) mpp.get("NomIntermediaire"))
								.trim();
						test = URLEncoder.encode(test, "UTF-8").replaceAll(
								"%1A", "");
						test = replaceSpecialCharacter
								.getSpecialCharacter(test);
						policeRes.setNomIntermediaire(test);
					} catch (UnsupportedEncodingException e) {
                        logger.log(Level.ERROR, "Exception", e);
						throw new IllegalArgumentException();
					}

				}
				// etat et date etat acctuel

				if (mpp.get("Etat") != null) {
					String etat = "";
                    if ("1".equals(String.valueOf((Long) mpp.get("Etat")))) {
						etat = "En cours";
                    } else if ("2"
                            .equals(String.valueOf((Long) mpp.get("Etat")))) {
						etat = "Suspendue";
                    } else if ("3"
                            .equals(String.valueOf((Long) mpp.get("Etat")))) {
						etat = "Remise en vigeur";
                    } else if ("4"
                            .equals(String.valueOf((Long) mpp.get("Etat")))) {
						etat = "Résilié";
                    } else if ("5"
                            .equals(String.valueOf((Long) mpp.get("Etat")))) {
						etat = "Supprimé";
					} else {
						etat = "E";
					}
					policeRes.setCodeEtatOuverture(etat);
					policeRes.setEtat(etat);
					policeRes.setDateEtat((String) mpp.get("DateOccurence"));

				}
				policeRes.setCodeEntrepriseParticulier(String.valueOf(mpp
						.get("CodeEntrepriseParticulier")));

				policeRes.setDateEtat((String) mpp.get("DateEtat"));
				policeRes.setDateEtatOuverture((String) mpp.get("DateEtat"));
				policeRes.setDateEffet((String) mpp.get("DateEffet"));
				policeRes.setDateEcheance((String) mpp.get("DateEcheance"));
				policeRes.setDateExpiration((String) mpp.get("DateExpiration"));
				policeRes.setDateOccurence((String) mpp.get("DateOccurence"));
				if (mpp.get("TypeContrat") != null) {
					String typecontrat = "";
                    if ("T".equals(((String) mpp.get("TypeContrat")))) {
						typecontrat = "Terme";
                    } else if ("F".equals((String) mpp.get("TypeContrat"))) {
						typecontrat = "Ferme";
					}
					policeRes.setTypeContrat(typecontrat);
				}

				// Police ex RMA
				policeRes.setNumeroPoliceExRMA(police.getNumeroPoliceExRMA());
				listPolice.add(policeRes);
			}
		}

		return listPolice;
	}

	public PoliceVO getPoliceByNum(PoliceVO polvo)
			throws FonctionnelleException {
		Map<String, Object> etatSurvenance = new HashMap<String, Object>();

		if (StringUtils.isEmpty(polvo.getNumeroPolice().trim())) {
			return null;
		}
		Map<String, Object> mappolice = getPoliceInfo(polvo);
		// MFBO@EVO 611:ajout des polices multirisque
		if (mappolice == null || mappolice.isEmpty()) {

			mappolice = getPoliceMultiRisqueInfo(polvo);
		}
		if (mappolice == null) {
			return null;
		}
		if (StringUtils.isEmpty((String) mappolice.get("NumeroPolice"))) {
			return null;
		}

		if (!polvo.getNumeroPolice().trim()
				.equals((String) mappolice.get("NumeroPolice"))) {
			return null;
		}

		PoliceVO policeRes = new PoliceVO();

		if (mappolice.get("CodeClassification") != null) {
			policeRes.setCodeClassification(((String) mappolice
					.get("CodeClassification")).trim());
		}
		if (mappolice.get("AdresseAssure") != null) {
			try {
				String test = ((String) mappolice.get("AdresseAssure")).trim();
				test = URLEncoder.encode(test, "UTF-8").replaceAll("%1A", "");
				test = replaceSpecialCharacter.getSpecialCharacter(test);
				policeRes.setAdresseAssure(test);
			} catch (UnsupportedEncodingException e) {
                logger.log(Level.ERROR, "Exception", e);
				throw new IllegalArgumentException();
			}
		}
		if (mappolice.get("CodeActivite") != null) {
			try {
				String test = ((String) mappolice.get("CodeActivite")).trim();
				test = URLEncoder.encode(test, "UTF-8").replaceAll("%1A", "");
				test = replaceSpecialCharacter.getSpecialCharacter(test);
				policeRes.setCodeActivite(test);
			} catch (UnsupportedEncodingException e) {
                logger.log(Level.ERROR, "Exception", e);
				throw new IllegalArgumentException();
			}

		}
		if (mappolice.get("forfaitOuRevis") != null) {
			try {
				String test = ((String) mappolice.get("forfaitOuRevis")).trim();
				test = URLEncoder.encode(test, "UTF-8").replaceAll("%1A", "");
				test = replaceSpecialCharacter.getSpecialCharacter(test);
				policeRes.setForfaitOurevis(test);
			} catch (UnsupportedEncodingException e) {
                logger.log(Level.ERROR, "Exception", e);
				throw new IllegalArgumentException();
			}

		}

		policeRes.setNumeroPolice((String) mappolice.get("NumeroPolice"));

		policeRes.setCodeClient(String.valueOf((Long) (mappolice
				.get("NumeroClient"))));

		try {
			String test = ((String) mappolice.get("NomClient")).trim();
			test = URLEncoder.encode(test, "UTF-8").replaceAll("%1A", "");
			test = replaceSpecialCharacter.getSpecialCharacter(test);
			policeRes.setNomClient(test);
		} catch (UnsupportedEncodingException e) {
            logger.log(Level.ERROR, "Exception", e);
			throw new IllegalArgumentException();
		}

		if (mappolice.get("CodeIntermediaire") != null) {
			policeRes.setCodeIntermediaire(String.valueOf(mappolice
					.get("CodeIntermediaire")));
		}

		try {
			String test = ((String) mappolice.get("TypeIntermediaire")).trim();
			test = URLEncoder.encode(test, "UTF-8").replaceAll("%1A", "");
			test = replaceSpecialCharacter.getSpecialCharacter(test);
			policeRes.setTypeIntermediaire(test);
		} catch (UnsupportedEncodingException e) {
            logger.log(Level.ERROR, "Exception", e);
			throw new IllegalArgumentException();
		}

		try {
			String test = ((String) mappolice.get("NomIntermediaire")).trim();
			test = URLEncoder.encode(test, "UTF-8").replaceAll("%1A", "");
			test = replaceSpecialCharacter.getSpecialCharacter(test);
			policeRes.setNomIntermediaire(test);
		} catch (UnsupportedEncodingException e) {
            logger.log(Level.ERROR, "Exception", e);
			throw new IllegalArgumentException();
		}

		if (mappolice.get("Etat") != null) {
			String etat = "";
            if ("1".equals(String.valueOf((Long) mappolice.get("Etat")))) {
				etat = "En cours";
            } else if ("2".equals(String.valueOf((Long) mappolice.get("Etat")))) {
				etat = "Suspendue";
            } else if ("3".equals(String.valueOf((Long) mappolice.get("Etat")))) {
				etat = "Remise en vigeur";
            } else if ("4".equals(String.valueOf((Long) mappolice.get("Etat")))) {
				etat = "Résilié";
            } else if ("5".equals(String.valueOf((Long) mappolice.get("Etat")))) {
				etat = "Supprimé";
			} else {
				etat = "E";
			}
			policeRes.setEtat(etat);
		}
		policeRes.setDateEtat((String) mappolice.get("DateEtat"));

		if (!StringUtils.isEmpty(polvo.getDateSurvenanceSinistre().trim())) {
			policeRes.setDateSurvenanceSinistre(polvo
					.getDateSurvenanceSinistre().trim());
			try {
				etatSurvenance = getEtatSurv(policeRes);
			} catch (ParseException e) {
				logger.error("Erreur technique getEtatSurv", e);
			}
		}
		if (etatSurvenance != null
				&& etatSurvenance.get("ETATSURVENANCE") != null) {
			String etat = "";
            if ("1".equals(String.valueOf(etatSurvenance.get("ETATSURVENANCE")))) {
				etat = "En cours";
            } else if ("2".equals(String.valueOf(etatSurvenance
                    .get("ETATSURVENANCE")))) {
				etat = "Suspendue";
            } else if ("3".equals(String.valueOf(etatSurvenance
                    .get("ETATSURVENANCE")))) {
				etat = "Remise en vigeur";
            } else if ("4".equals(String.valueOf(etatSurvenance
                    .get("ETATSURVENANCE")))) {
				etat = "Résilié";
            } else if ("5".equals(String.valueOf(etatSurvenance
                    .get("ETATSURVENANCE")))) {
				etat = "Supprimé";
			} else {
				etat = "E";
			}
			policeRes.setEtatSurvenance(etat);
			policeRes.setDateSurvenance((String) etatSurvenance
					.get("DATESURVENANCE"));
		}
		policeRes.setDateEffet((String) mappolice.get("DateEffet"));
		policeRes.setDateEcheance((String) mappolice.get("DateEcheance"));
		policeRes.setDateExpiration((String) mappolice.get("DateExpiration"));
		if (mappolice.get("TypeContrat") != null) {
			String typecontrat = "";
            if ("T".equals((String) mappolice.get("TypeContrat"))) {
				typecontrat = "Terme";
            } else if ("F".equals((String) mappolice.get("TypeContrat"))) {
				typecontrat = "Ferme";
			}
			policeRes.setTypeContrat(typecontrat);
		}
		policeRes.setCodeEntrepriseParticulier(String.valueOf(mappolice
				.get("CodeEntrepriseParticulier")));
		return policeRes;
	}

	private Map<String, Object> getEtatSurv(PoliceVO policeVO)
			throws FonctionnelleException, ParseException {
		Map<String, Object> etatSurvenance = null;
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement stmt = null;
		List<Map<String, Object>> resultMap = new ArrayList<Map<String, Object>>();
		Integer j = null;
		List<AlgoPoliceVO> resultList = new ArrayList<AlgoPoliceVO>();

		try {
			DriverManager
					.registerDriver(new com.ibm.as400.access.AS400JDBCDriver());

			conn = DriverManager.getConnection(Config.getAdresseAs00SinAT(),
					Config.getAdresseAs00SinATLog(),
					Config.getAdresseAs00SinATPass());

			if (etatSurvenance == null) {
				StringBuilder sql2 = new StringBuilder(
						"select t1.PLCDJJOCCU ||'/'|| t1.PLCDMMOCCU ||'/'|| t1.PLCDAAOCCU PLCDATETAT_SURV , t1.PLCETATPLC "
								+ " from BIBLATPRO9.atpplcpp00 t1"
								+ " where 1=1");
				if (policeVO.getNumeroPolice() != null
						&& !policeVO.getNumeroPolice().trim().equals("")) {
					sql2.append(" and PLCNUMPLCE like '%")
							.append(policeVO.getNumeroPolice().trim())
							.append("' ");
				}

				sql2.append(" order by PLCDATETAT_SURV desc");
				stmt = conn.prepareStatement(sql2.toString());
				resultSet = stmt.executeQuery();
				int compteur2 = 0;
				while (resultSet.next()) {
					AlgoPoliceVO polres = new AlgoPoliceVO();
					etatSurvenance = new HashMap<String, Object>();
					etatSurvenance.put("ETATSURVENANCE",
							resultSet.getString("PLCETATPLC"));
					polres.setEtat(resultSet.getString("PLCETATPLC"));
					etatSurvenance.put("DATESURVENANCE",
							resultSet.getString("PLCDATETAT_SURV"));
					Date date0 = dateFormat.parse(resultSet.getString(
							"PLCDATETAT_SURV").trim());
					String s = dateFormat.format(date0);
					polres.setDateOccurence(s);
					polres.setNum(compteur2);

					resultList.add(polres);
					resultMap.add(compteur2, etatSurvenance);
					compteur2++;
				}
				String date = policeVO.getDateSurvenanceSinistre().trim();
				String annee = date.substring(0, 4);
				String mois = date.substring(4, 6);
				String jour = date.substring(6);
				String date0 = jour + "/" + mois + "/" + annee;
				j = chercherEtatETDateOccuPoliceAlasurvenance(resultList,
						dateFormat.parse(date0));
			}

            if (resultList.isEmpty()) {

				StringBuilder sql3 = new StringBuilder(
						"select t1.PLCDJJOCUR ||'/'|| t1.PLCDMMOCUR ||'/'|| t1.PLCDAAOCUR PLCDATETAT_SURV , t1.PLCETAPLCE "
								+ " from BIBLMRPRO9.mrpplcpp00 t1"
								+ " left join BIBLMRPRO9.MRPPLGPP00 t4 on t1.PLCCODPLCE=t4.PLGCODPLCE"
								+ " where 1=1 and  t4.PLGCODGART  = 5");
				if (policeVO.getNumeroPolice() != null
						&& !policeVO.getNumeroPolice().trim().equals("")) {
					sql3.append(" and PLCCODPLCE like '%")
							.append(policeVO.getNumeroPolice().trim())
							.append("' ");
				}

				sql3.append(" order by PLCDATETAT_SURV desc");
				final String query = sql3.toString() ;
				stmt = conn.prepareStatement(query);
				resultSet = stmt.executeQuery();
				int compteur2 = 0;
				while (resultSet.next()) {
					AlgoPoliceVO polres = new AlgoPoliceVO();
					etatSurvenance = new HashMap<String, Object>();
					etatSurvenance.put("ETATSURVENANCE",
							resultSet.getString("PLCETAPLCE"));
					polres.setEtat(resultSet.getString("PLCETAPLCE"));
					etatSurvenance.put("DATESURVENANCE",
							resultSet.getString("PLCDATETAT_SURV"));
					Date date0 = dateFormat.parse(resultSet.getString(
							"PLCDATETAT_SURV").trim());
					String s = dateFormat.format(date0);
					polres.setDateOccurence(s);
					polres.setNum(compteur2);

					resultList.add(polres);
					resultMap.add(compteur2, etatSurvenance);
					compteur2++;
				}
				String date = policeVO.getDateSurvenanceSinistre().trim();
				String annee = date.substring(0, 4);
				String mois = date.substring(4, 6);
				String jour = date.substring(6);
				String date0 = jour + "/" + mois + "/" + annee;
				j = chercherEtatETDateOccuPoliceAlasurvenance(resultList,
						dateFormat.parse(date0));

			}

		} catch (SQLException e) {
			throw new FonctionnelleException(e.getMessage(), e);
        } finally {
			if (resultSet != null) {
				try {
					resultSet.close();

				} catch (SQLException e2) {
					logger.error("Erreur base de donnï¿½e", e2);

				}
				resultSet = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e2) {
					logger.error("Erreur base de donnï¿½e", e2);
				} finally {
					stmt = null;
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e2) {
					logger.error("Erreur base de donnï¿½e", e2);
					throw new FonctionnelleException("Erreur base de donnï¿½e",
							e2);
				} finally {
					conn = null;
				}
			}
		}

		if (j != null) {
			return resultMap.get(j);
		} else {
			return null;
		}

	}

	public boolean isMvt(Mouvement mvt, Sinistre sin) {
		return true;
	}

	public Mouvement mouvementCopy(Mouvement mvtTarget, Mouvement mvtSource) {

		if (mvtSource != null) {
			mvtTarget.setIppNew(mvtSource.getIppNew());
			mvtTarget.setIttNew(mvtSource.getIttNew());
			mvtTarget.setDateNaissanceNew(mvtSource.getDateNaissanceNew());
			mvtTarget.setDateAccidentNew(mvtSource.getDateAccidentNew());
			mvtTarget.setSalaireAnnuelNew(mvtSource.getSalaireAnnuelNew());
			mvtTarget.setSalaireUtileNew(mvtSource.getSalaireUtileNew());
			mvtTarget.setReserveGrave(mvtSource.getReserveGrave());
			mvtTarget.setReserveOrd(mvtSource.getReserveOrd());
			mvtTarget.setCodeEtatNew(mvtSource.getCodeEtatNew());
			mvtTarget.setReserveProthese(mvtSource.getReserveProthese());

			mvtTarget.setIppOld(mvtSource.getIppOld());
			mvtTarget.setIttOld(mvtSource.getIttOld());
			mvtTarget.setDateNaissanceOld(mvtSource.getDateNaissanceOld());
			mvtTarget.setDateAccidentOld(mvtSource.getDateAccidentOld());
			mvtTarget.setSalaireAnnuelOld(mvtSource.getSalaireAnnuelOld());
			mvtTarget.setSalaireUtileOld(mvtSource.getSalaireUtileOld());
			mvtTarget.setReserveGraveOld(mvtSource.getReserveGraveOld());
			mvtTarget.setReserveOrdOld(mvtSource.getReserveOrdOld());
			mvtTarget.setCodeEtatOld(mvtSource.getCodeEtatOld());
			mvtTarget.setReserveProtheseOld(mvtSource.getReserveProtheseOld());

			mvtTarget.setUserCreateur(mvtSource.getUserCreateur());
			mvtTarget.setDateCreation(mvtSource.getDateCreation());
			mvtTarget.setMotifEtat(mvtSource.getMotifEtat());
			mvtTarget.setRefEtatSinistre(mvtSource.getRefEtatSinistre());
			mvtTarget.setRefMotif(mvtSource.getRefMotif());

		}
		return mvtTarget;
	}

	public Sinistre creerSinistreReprise(Sinistre sinistre)
			throws FonctionnelleException {

		Evenement even = sinistre.getRefEvenement();
		Victime vic = (Victime) sinistre.getRefVictime();

		try {

			if (isSinistreGrave(sinistre) && even != null
					&& even.getDateAccident() != null) {
				sinistre.setNumeroGrave(getNumeroGrave(even.getDateAccident()));
			}
			sinistre.addEtatSinistre(getEtatSinistre("0",
					MOTIF_CREATION_SINISTRE1, sinistre.getUserModificateur()));
			sinistre.setDateCreation(new Date());

			if (sinistre.getRefEvenement() != null) {

				even.setNumeroEvenement(validateEvenement(sinistre, even));
				dao.createObject(even);
				sinistre.setRefEvenement(even);
			}

			if (sinistre.getRefVictime() != null) {
				vic.setDateCreation(new Date());
				List listAY = (List) vic.getListAyantDroit();
				if (listAY != null) {
					List listAyantDroit = new ArrayList<AyantDroit>();
					for (AyantDroit ay : (List<AyantDroit>) listAY) {
						ay.setRefVictime(vic);
						ay.setDateCreation(new Date());
						listAyantDroit.add(ay);
					}
					vic.setListAyantDroit(listAyantDroit);
				}

				dao.createObject(vic);
				sinistre.setRefVictime(vic);
			}
			dao.createObject(sinistre);
			return sinistre;
		} catch (Exception e) {
			logger.error(EXP_CREATION_SINISTRE, e);
			throw new FonctionnelleException(EXP_CREATION_SINISTRE, e);
		}
	}

	public Sinistre modifierSinistreReglement(Sinistre sinistre)
			throws FonctionnelleException {

		try {
			if (sinistre.getEtatCible() != null
					&& !sinistre.getEtatCible().equals("")) {

				EtatSinistre etatSinistre = new EtatSinistre();
				etatSinistre.setDateEtat(new Date());
				etatSinistre.setMotifEtat(MOTIF_MOUVEMENT_SIN_REG);
				etatSinistre.setRefSinistre(sinistre);
				etatSinistre.setRefEtat(new EtatSin());
				etatSinistre.getRefEtat().setCode(sinistre.getEtatCible());
				sinistre.addEtatSinistre(etatSinistre);
				dao.updateObject(sinistre);
			}
			return sinistre;
		} catch (Exception e) {
			logger.error(EXP_MODIF_SIN_REG, e);
			throw new FonctionnelleException(EXP_MODIF_SIN_REG, e);
		}
	}

	public Map getPoliceInfo(PoliceVO polvo) throws FonctionnelleException {

		String logsinat = "getPoliceInfo";
		Map<String, Object> result = null;
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement stmt = null;
		List<Map<String, Object>> resultMap = new ArrayList<Map<String, Object>>();
		Integer j = null;

		if (StringUtils.isEmpty(polvo.getNumeroPolice().trim())) {
			return null;
		}
		String numPolice = polvo.getNumeroPolice().trim();

		String codeInter = polvo.getCodeIntermediaire();
		String typeIter = polvo.getTypeIntermediaire();
		try {
			DriverManager
					.registerDriver(new com.ibm.as400.access.AS400JDBCDriver());

			conn = DriverManager.getConnection(Config.getAdresseAs00SinAT(),
					Config.getAdresseAs00SinATLog(),
					Config.getAdresseAs00SinATPass());

			// from biblmrpro9.mrpplcpp00
			// driver@machineName:port:SID , userid, password
			StringBuilder sql = new StringBuilder(
					"select distinct t4.RSQLISTENO,t1.PLCNUMPLCE ,t1.PLCNUMCLIE,t1.PLCNOMCLNT,"
							+ "t2.INTNATINTR || t2.INTCODINTR INTERMEDIAIRE,t2.INTNOMINTR,"
							+ " t1.PLCDJJETAB ||'/'|| t1.PLCDMMETAB ||'/'|| t1.PLCDAAETAB PLCDATETAB,"
							+ " t1.PLCDJJECHE ||'/'|| t1.PLCDMMECHE ||'/'|| t1.PLCDAAECHE PLCDATECHE,"
							+ " t1.PLCDJJEFET ||'/'|| t1.PLCDMMEFET ||'/'|| t1.PLCDAAEFET PLCDATEFET,"
							+ " t1.PLCDJJEXPR ||'/'|| t1.PLCDMMEXPR ||'/'|| t1.PLCDAAEXPR PLCDATEXPR,"
							+ " t1.PLCETATPLC,"
							+ " t1.PLCDJJOCCU ||'/'|| t1.PLCDMMOCCU ||'/'|| t1.PLCDAAOCCU PLCDATOCCU, "
							+ " t1.PLCNATCNTR ,t1.PLCDJJTRAN , t1.PLCCODOCUR,t1.PLCACTVITE ,t1.PLCCODCLAS,t1.PLCADRCLNT"
							+ " from BIBLATPRO9.atpplcpp00 t1 "
							+ " left join BIBLIARD.GNPINTPP00 t2 "
							+ "on t1.PLCCODINTR=t2.INTCODINTR "
							+ " left join biblatpro9.ATPRSQPP00 t4 on t1.PLCNUMPLCE=t4.RSQNUMPLCE"
							+ " where 1=1 "
							+ " and t4.RSQCODOCCU=(select max(t5.RSQCODOCCU) from biblatpro9.ATPRSQPP00 t5 where t1.PLCNUMPLCE=t5.RSQNUMPLCE)");
			sql.append("and date(t1.PLCDAAOCCU || '-'|| t1.PLCDMMOCCU || '-'||t1.PLCDJJOCCU) =");
			sql.append(" ( ");
			sql.append(" select max(date(t3.PLCDAAOCCU || '-'|| t3.PLCDMMOCCU || '-'||t3.PLCDJJOCCU)) from BIBLATPRO9.atpplcpp00 t3 ");
			sql.append(" where t3.PLCNUMPLCE=t1.PLCNUMPLCE ");
			sql.append(" ) ");
			stmt = (PreparedStatement) conn.prepareStatement(sql.toString());
            if (numPolice != null && !"".equals(numPolice.trim())) {
				sql.append(" and PLCNUMPLCE like ?");
				stmt = (PreparedStatement) conn
						.prepareStatement(sql.toString());
				stmt.setString(1, numPolice.trim());
                if (codeInter != null && !"".equals(codeInter.trim())) {
					sql.append(" and INTCODINTR like ? ");
					stmt = (PreparedStatement) conn.prepareStatement(sql
							.toString());
					stmt.setString(1, numPolice.trim());
					stmt.setString(2, codeInter);
                    if (typeIter != null && !"".equals(typeIter.trim())) {
						sql.append(" and INTNATINTR = ?");
						stmt = (PreparedStatement) conn.prepareStatement(sql
								.toString());
						stmt.setString(1, numPolice.trim());
						stmt.setString(2, codeInter);
						stmt.setString(3, typeIter);
					}
				}

			}
			resultSet = stmt.executeQuery();

			List<AlgoPoliceVO> resultList = new ArrayList<AlgoPoliceVO>();

			int i = 0;
			while (resultSet.next()) {
				AlgoPoliceVO polres = new AlgoPoliceVO();
				result = new HashMap<String, Object>();
				result.put("forfaitOuRevis", resultSet.getString("RSQLISTENO"));
				if (resultSet.getString("PLCCODCLAS") != null) {
					result.put("CodeClassification",
							resultSet.getString("PLCCODCLAS"));
				}
				if (resultSet.getString("PLCADRCLNT") != null) {
					result.put("AdresseAssure",
							resultSet.getString("PLCADRCLNT"));
				}
				if (resultSet.getString("PLCACTVITE") != null) {
					result.put("CodeActivite",
							resultSet.getString("PLCACTVITE"));
				}

				result.put("NumeroPolice", resultSet.getString("PLCNUMPLCE"));
				result.put("NumeroClient",
						new Long(resultSet.getString("PLCNUMCLIE")));
				result.put("NomClient", resultSet.getString("PLCNOMCLNT")
						.trim());
				if (resultSet.getString("INTERMEDIAIRE") != null
						&& resultSet.getString("INTERMEDIAIRE").length() == 5) {
					result.put("TypeIntermediaire",
							resultSet.getString("INTERMEDIAIRE")
									.substring(0, 1));
					result.put("CodeIntermediaire", new Long(resultSet
							.getString("INTERMEDIAIRE").substring(1, 5)));
				}
				result.put("NomIntermediaire",
						resultSet.getString("INTNOMINTR"));
				result.put("DateEtablissement",
						resultSet.getString("PLCDATETAB"));
				result.put("DateEcheance", resultSet.getString("PLCDATECHE")
						.trim());
				result.put("DateEffet", resultSet.getString("PLCDATEFET")
						.trim());
				result.put("DateExpiration", resultSet.getString("PLCDATEXPR")
						.trim());
				result.put("Etat", new Long(resultSet.getString("PLCETATPLC")));
				polres.setEtat(resultSet.getString("PLCETATPLC"));
				result.put("DateEtat", resultSet.getString("PLCDATOCCU").trim());
				result.put("DateOccurence", resultSet.getString("PLCDATOCCU")
						.trim());

				String date = resultSet.getString("PLCDATOCCU").trim();

				Date date0 = dateFormat.parse(resultSet.getString("PLCDATOCCU")
						.trim());
				String s = dateFormat.format(date0);
				polres.setDateOccurence(s);
				result.put("TypeContrat", resultSet.getString("PLCNATCNTR")
						.trim());
				result.put("CodeEntrepriseParticulier",
						resultSet.getString("PLCDJJTRAN").trim());
				polres.setNum(i);
				resultList.add(polres);
				resultMap.add(i, result);
				i++;

			}

            if (resultList == null || resultList.isEmpty()) {
				return null;
            }

			j = chercherEtatETDateOccuPolice(resultList);

		} catch (Exception e) {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e1) {
					logger.error(logsinat, e1);
					throw new FonctionnelleException(logsinat, e1);
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e2) {
					logger.error(logsinat, e2);
					throw new FonctionnelleException(logsinat, e2);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e3) {
					logger.error(logsinat, e3);
					throw new FonctionnelleException(logsinat, e3);
				}
			}
			throw new FonctionnelleException(logsinat, e);

		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e4) {
					throw new FonctionnelleException(logsinat, e4);
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e5) {
					throw new FonctionnelleException(logsinat, e5);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e6) {
					throw new FonctionnelleException(logsinat, e6);
				}
			}
		}

		if (j != null) {
			return resultMap.get(j);
		} else {
			return null;
		}
	}

	public Integer chercherEtatETDateOccuPolice(List<AlgoPoliceVO> list)
			throws ParseException {
		Integer res = null;
		Collections.sort(list);
		String dateOuverture = dateFormat.format(new Date());
		Date dateOuve = dateFormat.parse(dateOuverture);
        String etat = "";
		Boolean bool = true;
		for (PoliceVO p : list) {

			Date datep = dateFormat.parse(p.getDateOccurence());
			if (datep.compareTo(dateOuve) <= 0) {
				if (bool) {
                    etat = p.getEtat();
				}
				bool = false;
                if (!etat.equals(p.getEtat())) {
					return res;
				}
				res = p.getNum();
			}

		}
        if (res == null && !list.isEmpty()) {
				res = list.get(0).getNum();
			}
		return res;
	}

	public Integer chercherEtatETDateOccuPoliceAlasurvenance(
            List<AlgoPoliceVO> list, Date dateSurv) throws ParseException {
		Integer res = null;
		Collections.sort(list);
        String etat = "";
		Boolean bool = true;
		for (PoliceVO p : list) {

			Date datep = dateFormat.parse(p.getDateOccurence());
            if (datep.compareTo(dateSurv) <= 0) {
				if (bool) {
                    etat = p.getEtat();
				}
				bool = false;
                if (!etat.equals(p.getEtat())) {
					return res;
				}
				res = p.getNum();
			}
		}
		return res;
	}

	/**
	 * MFBO@EVO 611:ajout des polices multirisque
	 * 
	 * @param polvo
	 * @return
	 * @throws FonctionnelleException
	 */
	public Map getPoliceMultiRisqueInfo(PoliceVO polvo)
			throws FonctionnelleException {

		Map<String, Object> result = null;
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement stmt = null;
		Integer j = null;
		List<Map<String, Object>> resultMap = new ArrayList<Map<String, Object>>();

		if (StringUtils.isEmpty(polvo.getNumeroPolice().trim())) {
			return null;
		}
		String numPolice = polvo.getNumeroPolice().trim();

		String codeInter = polvo.getCodeIntermediaire();
		String typeIter = polvo.getTypeIntermediaire();
		List<AlgoPoliceVO> resultList = new ArrayList<AlgoPoliceVO>();
		try {
			DriverManager
					.registerDriver(new com.ibm.as400.access.AS400JDBCDriver());

			conn = DriverManager.getConnection(Config.getAdresseAs00SinAT(),
					Config.getAdresseAs00SinATLog(),
					Config.getAdresseAs00SinATPass());

			// from biblmrpro9.mrpplcpp00
			// driver@machineName:port:SID , userid, password
			StringBuilder sql = new StringBuilder(
					"select distinct t1.PLCCODPLCE ,t1.PLCCODCLNT,t1.PLCNOMASSU,"
							+ "t2.INTNATINTR || t2.INTCODINTR INTERMEDIAIRE,t2.INTNOMINTR,"
							+ " t1.PLCDJJETAB ||'/'|| t1.PLCDMMETAB ||'/'|| t1.PLCDAAETAB PLCDATETAB,"
							+ " t1.PLCDJJECHE ||'/'|| t1.PLCDMMECHE ||'/'|| t1.PLCDAAECHE PLCDATECHE,"
							+ " t1.PLCDJJEFET ||'/'|| t1.PLCDMMEFET ||'/'|| t1.PLCDAAEFET PLCDATEFET,"
							+ " t1.PLCDJJEXPR ||'/'|| t1.PLCDMMEXPR ||'/'|| t1.PLCDAAEXPR PLCDATEXPR,"
							+ " t1.PLCETAPLCE, t1.PLCDJJOCUR ||'/'|| t1.PLCDMMOCUR ||'/'|| t1.PLCDAAOCUR PLCDATOCCU,"
							+ " t1.PLCDJJOCUR ||'/'|| t1.PLCDMMOCUR ||'/'|| t1.PLCDAAOCUR PLCDATOCCU, "
							+ " t1.PLCNATCNTR,t1.PLCDJJTRAN"
							+ " from BIBLMRPRO9.mrpplcpp00 t1 "
							+ " left join BIBLIARD.GNPINTPP00 t2 on t1.PLCCODINTR=t2.INTCODINTR "
							+ " left join BIBLMRPRO9.MRPPLGPP00 t4 on t1.PLCCODPLCE=t4.PLGCODPLCE "
							+ " where 1=1 ");

			sql.append(" and t4.PLGCODGART  = 5");
			sql.append(" and date(t1.PLCDAAOCUR || '-'|| t1.PLCDMMOCUR || '-'||t1.PLCDJJOCUR) =");
			sql.append(" ( ");
			sql.append(" select max(date(t3.PLCDAAOCUR || '-'|| t3.PLCDMMOCUR || '-'||t3.PLCDJJOCUR)) from BIBLMRPRO9.mrpplcpp00 t3 ");
			sql.append(" where t3.PLCCODPLCE=t1.PLCCODPLCE");
			sql.append(" ) ");

			stmt = (PreparedStatement) conn.prepareStatement(sql.toString());
            if (numPolice != null && !"".equals(numPolice.trim())) {
				sql.append(" and PLCCODPLCE like ?");
				stmt = (PreparedStatement) conn
						.prepareStatement(sql.toString());
				stmt.setString(1, numPolice.trim());
                if (codeInter != null && !"".equals(codeInter.trim())) {
					sql.append(" and INTCODINTR like ? ");
					stmt = (PreparedStatement) conn.prepareStatement(sql
							.toString());
					stmt.setString(1, numPolice.trim());
					stmt.setString(2, codeInter);
                    if (typeIter != null && !"".equals(typeIter.trim())) {
						sql.append(" and INTNATINTR = ?");
						stmt = (PreparedStatement) conn.prepareStatement(sql
								.toString());
						stmt.setString(1, numPolice.trim());
						stmt.setString(2, codeInter);
						stmt.setString(3, typeIter);
					}
				}

			}
			resultSet = stmt.executeQuery();
			int i = 0;
			while (resultSet.next()) {
				AlgoPoliceVO polres = new AlgoPoliceVO();
				result = new HashMap<String, Object>();
				result.put("forfaitOuRevis", "O");
				result.put("NumeroPolice", resultSet.getString("PLCCODPLCE"));

				result.put("NumeroClient",
						new Long(resultSet.getString("PLCCODCLNT")));
				result.put("NomClient", resultSet.getString("PLCNOMASSU")
						.trim());
				if (resultSet.getString("INTERMEDIAIRE") != null
						&& resultSet.getString("INTERMEDIAIRE").length() == 5) {
					result.put("TypeIntermediaire",
							resultSet.getString("INTERMEDIAIRE")
									.substring(0, 1));
					result.put("CodeIntermediaire", new Long(resultSet
							.getString("INTERMEDIAIRE").substring(1, 5)));
				}
				result.put("NomIntermediaire",
						resultSet.getString("INTNOMINTR"));
				result.put("DateEtablissement",
						resultSet.getString("PLCDATETAB"));
				result.put("DateEcheance", resultSet.getString("PLCDATECHE")
						.trim());
				result.put("DateEffet", resultSet.getString("PLCDATEFET")
						.trim());
				result.put("DateExpiration", resultSet.getString("PLCDATEXPR")
						.trim());
				result.put("Etat", new Long(resultSet.getString("PLCETAPLCE")));
				polres.setEtat(resultSet.getString("PLCETAPLCE"));
				result.put("DateEtat", resultSet.getString("PLCDATOCCU").trim());
				result.put("DateOccurence", resultSet.getString("PLCDATOCCU")
						.trim());

				Date date0 = dateFormat.parse(resultSet.getString("PLCDATOCCU")
						.trim());
				String s = dateFormat.format(date0);
				polres.setDateOccurence(s);

				result.put("TypeContrat", resultSet.getString("PLCNATCNTR")
						.trim());
				result.put("CodeEntrepriseParticulier",
						resultSet.getString("PLCDJJTRAN").trim());
				polres.setNum(i);
				resultList.add(polres);
				resultMap.add(i, result);
				i++;
			}
			j = chercherEtatETDateOccuPolice(resultList);

		} catch (Exception e) {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e2) {
					throw new FonctionnelleException(e2.getMessage(), e2);
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e2) {
					throw new FonctionnelleException(e2.getMessage(), e2);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e2) {
					throw new FonctionnelleException(e2.getMessage(), e2);
				}
			}
			throw new FonctionnelleException(e.getMessage(), e);

		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e2) {
					throw new FonctionnelleException(e2.getMessage(), e2);
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e2) {
					throw new FonctionnelleException(e2.getMessage(), e2);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e2) {
					throw new FonctionnelleException(e2.getMessage(), e2);
				}
			}
		}

		if (j != null) {
			return resultMap.get(j);
		} else {
			return null;
		}
	}

	public List<Map<String, Object>> getListPoliceInfo(
			Map<String, String> mapPolice) throws FonctionnelleException {

		List<Map<String, Object>> resultList = null;
		Connection conn = null;
		ResultSet resultSet = null;
		Statement stmt = null;
		try {
			DriverManager
					.registerDriver(new com.ibm.as400.access.AS400JDBCDriver());

			conn = DriverManager.getConnection(Config.getAdresseAs00SinAT(),
					Config.getAdresseAs00SinATLog(),
					Config.getAdresseAs00SinATPass());
			// driver@machineName:port:SID , userid, password

			stmt = (Statement) conn.createStatement();

			StringBuilder sql = new StringBuilder(
					"select distinct t4.RSQLISTENO,t1.PLCNUMPLCE ,t1.PLCNUMCLIE,t1.PLCNOMCLNT,"
							+ "t2.INTNATINTR || t2.INTCODINTR INTERMEDIAIRE,t2.INTNOMINTR,"
							+ " t1.PLCDJJETAB ||'/'|| t1.PLCDMMETAB ||'/'|| t1.PLCDAAETAB PLCDATETAB,"
							+ " t1.PLCDJJECHE ||'/'|| t1.PLCDMMECHE ||'/'|| t1.PLCDAAECHE PLCDATECHE,"
							+ " t1.PLCDJJEFET ||'/'|| t1.PLCDMMEFET ||'/'|| t1.PLCDAAEFET PLCDATEFET,"
							+ " t1.PLCDJJEXPR ||'/'|| t1.PLCDMMEXPR ||'/'|| t1.PLCDAAEXPR PLCDATEXPR,"
							+ " t1.PLCETATPLC, t1.PLCDJJETAT ||'/'|| t1.PLCDMMETAT ||'/'|| t1.PLCDAAETAT PLCDATETAT,"
							+ " t1.PLCDJJOCCU ||'/'|| t1.PLCDMMOCCU ||'/'|| t1.PLCDAAOCCU PLCDATOCCU, "
							+ " t1.PLCNATCNTR , t1.PLCDJJTRAN ,t1.PLCACTVITE ,t1.PLCCODCLAS,t1.PLCADRCLNT"
							+ " from BIBLATPRO9.atpplcpp00 t1 "
							+ " left join BIBLIARD.GNPINTPP00 t2 on t1.PLCCODINTR=t2.INTCODINTR"
							+ " left join biblatpro9.ATPRSQPP00 t4 on t1.PLCNUMPLCE=t4.RSQNUMPLCE"
							+ " where 1=1"
							+ " and t4.RSQCODOCCU=(select max(t5.RSQCODOCCU) from biblatpro9.ATPRSQPP00 t5 where t1.PLCNUMPLCE=t5.RSQNUMPLCE)");
			sql.append(" and date(t1.PLCDAAOCCU || '-'|| t1.PLCDMMOCCU || '-'||t1.PLCDJJOCCU) =");
			sql.append(" ( ");
			sql.append(" select max(date(t3.PLCDAAOCCU || '-'|| t3.PLCDMMOCCU || '-'||t3.PLCDJJOCCU)) from BIBLATPRO9.atpplcpp00 t3 ");
			sql.append(" where t3.PLCNUMPLCE=t1.PLCNUMPLCE ");
			sql.append(" ) ");

			if (mapPolice.get("PLCNUMPLCE") != null
					&& !mapPolice.get("PLCNUMPLCE").trim().equals("")) {
				sql.append(" and PLCNUMPLCE like '%")
						.append(mapPolice.get("PLCNUMPLCE").trim())
						.append("' ");
			}

			// EX RMA EXRMA
			if (EX_RMA.equals(mapPolice.get("EXRMA"))) {
				sql.append(" and PLCNUMPLCE like '")
						.append(CODE_DECENTRALISATION_EXRMA).append("%' ");
			}

			if (mapPolice.get("PLCETATPLC") != null
                    && !"".equals(mapPolice.get("PLCETATPLC").trim())) {
				sql.append(" and PLCETATPLC=")
						.append(mapPolice.get("PLCETATPLC").trim()).append(" ");
			}
			if (mapPolice.get("INTCODINTR") != null
                    && !"".equals(mapPolice.get("INTCODINTR").trim())) {
				if (mapPolice.get("INTCODINTR").trim().length() == 5) {
					sql.append(" and INTCODINTR=")
							.append(mapPolice.get("INTCODINTR").trim())
							.append(" ");
				} else if (StringUtils.isNumeric(mapPolice.get("INTCODINTR")
						.trim())) {
					sql.append(" and INTCODINTR=")
							.append(mapPolice.get("INTCODINTR").trim())
							.append(" ");
				}
			}
			if (mapPolice.get("INTNOMINTR") != null
                    && !"".equals(mapPolice.get("INTNOMINTR").trim())) {
				sql.append(" and INTNOMINTR like '%")
						.append(mapPolice.get("INTNOMINTR").trim())
						.append("%' ");
			}
			if (mapPolice.get("PLCNUMCLIE") != null
                    && !"".equals(mapPolice.get("PLCNUMCLIE").trim())) {
				sql.append(" and PLCNUMCLIE=")
						.append(mapPolice.get("PLCNUMCLIE").trim()).append(" ");
			}
			if (mapPolice.get("PLCNOMCLNT") != null
                    && !"".equals(mapPolice.get("PLCNOMCLNT").trim())) {
				sql.append(" and PLCNOMCLNT like '%")
						.append(mapPolice.get("PLCNOMCLNT").trim())
						.append("%' ");
			}

			if (mapPolice.get("nbrLignes") != null) {
				sql.append(" fetch first ").append(mapPolice.get("nbrLignes"))
						.append(" rows only ");
			}

			resultSet = stmt.executeQuery(sql.toString());

			resultList = new ArrayList<Map<String, Object>>();

			while (resultSet.next()) {
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("forfaitOuRevis", resultSet.getString("RSQLISTENO"));
				result.put("NumeroPolice", resultSet.getString("PLCNUMPLCE"));
				result.put("NumeroClient",
						new Long(resultSet.getString("PLCNUMCLIE")));
				result.put("NomClient", resultSet.getString("PLCNOMCLNT")
						.trim());

				if (resultSet.getString("PLCCODCLAS") != null) {
					result.put("CodeClassification",
							resultSet.getString("PLCCODCLAS"));
				}
				if (resultSet.getString("PLCADRCLNT") != null) {
					result.put("AdresseAssure",
							resultSet.getString("PLCADRCLNT"));
				}
				if (resultSet.getString("PLCACTVITE") != null) {
					result.put("CodeActivite",
							resultSet.getString("PLCACTVITE"));
				}

				if (resultSet.getString("INTERMEDIAIRE") != null
						&& resultSet.getString("INTERMEDIAIRE").length() == 5) {
					result.put("TypeIntermediaire",
							resultSet.getString("INTERMEDIAIRE")
									.substring(0, 1));
					result.put("CodeIntermediaire", new Long(resultSet
							.getString("INTERMEDIAIRE").substring(1, 5)));
				}
				result.put("NomIntermediaire",
						resultSet.getString("INTNOMINTR"));
				result.put(
						"DateEtablissement",
						getStringDateToString(resultSet.getString("PLCDATETAB")));
				result.put(
						"DateEcheance",
						getStringDateToString(resultSet.getString("PLCDATECHE")));
				result.put(
						"DateEffet",
						getStringDateToString(resultSet.getString("PLCDATEFET")));
				result.put(
						"DateExpiration",
						getStringDateToString(resultSet.getString("PLCDATEXPR")));
				result.put("Etat", new Long(resultSet.getString("PLCETATPLC")));
				result.put(
						"DateEtat",
						getStringDateToString(resultSet.getString("PLCDATETAT")));
				result.put(
						"DateOccurence",
						getStringDateToString(resultSet.getString("PLCDATOCCU")));

				result.put("TypeContrat", resultSet.getString("PLCNATCNTR")
						.trim());
				result.put("CodeEntrepriseParticulier",
						resultSet.getString("PLCDJJTRAN").trim());

				resultList.add(result);
			}

		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage(), e);

		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e2) {
					throw new FonctionnelleException(e2.getMessage(), e2);
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e2) {
					throw new FonctionnelleException(e2.getMessage(), e2);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e2) {
					throw new FonctionnelleException(e2.getMessage(), e2);
				}
			}
		}

		return resultList;
	}

	/**
	 * MFBO@EVO 611:Rechercher les polices multi-risque
	 * 
	 * @param mapPolice
	 * @return
	 * @throws FonctionnelleException
	 */

	public List<Map<String, Object>> getListPoliceMultiRisqueInfo(
			Map<String, String> mapPolice) throws FonctionnelleException {

		List<Map<String, Object>> resultList = null;
		Connection conn = null;
		ResultSet resultSet = null;
		Statement stmt = null;
		try {
			DriverManager
					.registerDriver(new com.ibm.as400.access.AS400JDBCDriver());

			conn = DriverManager.getConnection(Config.getAdresseAs00SinAT(),
					Config.getAdresseAs00SinATLog(),
					Config.getAdresseAs00SinATPass());
			// driver@machineName:port:SID , userid, password

			stmt = (Statement) conn.createStatement();
			StringBuilder sql = new StringBuilder(
					"select distinct t1.PLCCODPLCE ,t1.PLCCODCLNT,t1.PLCNOMASSU,"
							+ "t2.INTNATINTR || t2.INTCODINTR INTERMEDIAIRE,t2.INTNOMINTR,"
							+ " t1.PLCDJJETAB ||'/'|| t1.PLCDMMETAB ||'/'|| t1.PLCDAAETAB PLCDATETAB,"
							+ " t1.PLCDJJECHE ||'/'|| t1.PLCDMMECHE ||'/'|| t1.PLCDAAECHE PLCDATECHE,"
							+ " t1.PLCDJJEFET ||'/'|| t1.PLCDMMEFET ||'/'|| t1.PLCDAAEFET PLCDATEFET,"
							+ " t1.PLCDJJEXPR ||'/'|| t1.PLCDMMEXPR ||'/'|| t1.PLCDAAEXPR PLCDATEXPR,"
							+ " t1.PLCETAPLCE,"
							+ " t1.PLCDJJOCUR ||'/'|| t1.PLCDMMOCUR ||'/'|| t1.PLCDAAOCUR PLCDATOCCU, "
							+ " t1.PLCNATCNTR ,t1.PLCDJJTRAN"
							+ " from BIBLMRPRO9.mrpplcpp00 t1 "
							+ " left join BIBLIARD.GNPINTPP00 t2 on t1.PLCCODINTR=t2.INTCODINTR "
							+ " left join BIBLMRPRO9.MRPPLGPP00 t4 on t1.PLCCODPLCE=t4.PLGCODPLCE "

							+ " where 1=1 ");

			sql.append(" and t4.PLGCODGART  = 5 ");

			sql.append("and date(t1.PLCDAAOCUR || '-'|| t1.PLCDMMOCUR || '-'||t1.PLCDJJOCUR) =");
			sql.append(" ( ");
			sql.append(" select max(date(t3.PLCDAAOCUR || '-'|| t3.PLCDMMOCUR || '-'||t3.PLCDJJOCUR)) from BIBLMRPRO9.mrpplcpp00 t3 ");
			sql.append(" where t3.PLCCODPLCE=t1.PLCCODPLCE");
			sql.append(" ) ");

			if (mapPolice.get("PLCNUMPLCE") != null
					&& !mapPolice.get("PLCNUMPLCE").trim().equals("")) {
				sql.append(" and PLCCODPLCE like '%")
						.append(mapPolice.get("PLCNUMPLCE").trim())
						.append("%' ");
			}

			// EX RMA EXRMA
			if (EX_RMA.equals(mapPolice.get("EXRMA"))) {
				sql.append(" and PLCCODPLCE like '")
						.append(CODE_DECENTRALISATION_EXRMA).append("%' ");
			}

			if (mapPolice.get("PLCETATPLC") != null
                    && !"".equals(mapPolice.get("PLCETATPLC").trim())) {
				sql.append(" and PLCETATPLC=")
						.append(mapPolice.get("PLCETATPLC").trim()).append(" ");
			}
			if (mapPolice.get("INTCODINTR") != null
                    && !"".equals(mapPolice.get("INTCODINTR").trim())) {
				if (mapPolice.get("INTCODINTR").trim().length() == 5) {
					sql.append(" and INTCODINTR=")
							.append(mapPolice.get("INTCODINTR").trim())
							.append(" ");
				} else if (StringUtils.isNumeric(mapPolice.get("INTCODINTR")
						.trim())) {
					sql.append(" and INTCODINTR=")
							.append(mapPolice.get("INTCODINTR").trim())
							.append(" ");
				}
			}
			if (mapPolice.get("INTNOMINTR") != null
                    && !"".equals(mapPolice.get("INTNOMINTR").trim())) {
				sql.append(" and INTNOMINTR like '%")
						.append(mapPolice.get("INTNOMINTR").trim())
						.append("%' ");
			}
			if (mapPolice.get("PLCNUMCLIE") != null
                    && !"".equals(mapPolice.get("PLCNUMCLIE").trim())) {
				sql.append(" and PLCCODCLNT=")
						.append(mapPolice.get("PLCNUMCLIE").trim()).append(" ");
			}
			if (mapPolice.get("PLCNOMCLNT") != null
                    && !"".equals(mapPolice.get("PLCNOMCLNT").trim())) {
				sql.append(" and PLCNOMASSU like '%")
						.append(mapPolice.get("PLCNOMCLNT").trim())
						.append("%' ");
			}

			if (mapPolice.get("nbrLignes") != null) {
				sql.append(" fetch first ").append(mapPolice.get("nbrLignes"))
						.append(" rows only ");
			}

			String sqlStr = sql.toString();

			resultSet = stmt.executeQuery(sqlStr);

			resultList = new ArrayList<Map<String, Object>>();

			while (resultSet.next()) {
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("forfaitOuRevis", "O");
				result.put("NumeroPolice", resultSet.getString("PLCCODPLCE"));
				result.put("NumeroClient",
						new Long(resultSet.getString("PLCCODCLNT")));
				result.put("NomClient", resultSet.getString("PLCNOMASSU")
						.trim());
				if (resultSet.getString("INTERMEDIAIRE") != null
						&& resultSet.getString("INTERMEDIAIRE").length() == 5) {
					result.put("TypeIntermediaire",
							resultSet.getString("INTERMEDIAIRE")
									.substring(0, 1));
					result.put("CodeIntermediaire", new Long(resultSet
							.getString("INTERMEDIAIRE").substring(1, 5)));
				}
				result.put("NomIntermediaire",
						resultSet.getString("INTNOMINTR"));
				result.put(
						"DateEtablissement",
						getStringDateToString(resultSet.getString("PLCDATETAB")));
				result.put(
						"DateEcheance",
						getStringDateToString(resultSet.getString("PLCDATECHE")));
				result.put(
						"DateEffet",
						getStringDateToString(resultSet.getString("PLCDATEFET")));
				result.put(
						"DateExpiration",
						getStringDateToString(resultSet.getString("PLCDATEXPR")));
				result.put("Etat", new Long(resultSet.getString("PLCETAPLCE")));
				result.put(
						"DateEtat",
						getStringDateToString(resultSet.getString("PLCDATOCCU")));
				result.put(
						"DateOccurence",
						getStringDateToString(resultSet.getString("PLCDATOCCU")));
				result.put("TypeContrat", resultSet.getString("PLCNATCNTR")
						.trim());
				result.put("CodeEntrepriseParticulier",
						resultSet.getString("PLCDJJTRAN").trim());

				resultList.add(result);
			}

		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage(), e);

		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e2) {
					throw new FonctionnelleException(e2.getMessage(), e2);
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e2) {
					throw new FonctionnelleException(e2.getMessage(), e2);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e2) {
					throw new FonctionnelleException(e2.getMessage(), e2);
				}
			}
		}

		return resultList;
	}

	public String getStringDateToString(String datein) {
		Date date = null;
		String dateres = null;
		try {
			date = dateFormat.parse(datein);
		} catch (Exception e) {
            logger.log(Level.ERROR, "Exception", e);
			return null;
		}
		if (date != null) {
			dateres = dateFormat.format(date);
		}

		return dateres;

	}

	private String getListAffectationSinistreQuery(Sinistre s) {
        String sql = "Select s from Sinistre s where s.ipp <> 0 and s.id not in"
				+ " (select t.refSinistre.id from Transaction t) and s.id in "
				+ " (select c.refSinistre.id from CertificatMedical c where c.dateGuerison is not null and c.valide=1)";

		if (s != null && !StringUtils.isEmpty(s.getNumeroSinistre())) {
            sql += " and s.numeroSinistre like '" + s.getNumeroSinistre() + "'";
		}

		if (s != null && s.getDateCreation() != null) {
            sql += " and TO_DATE(s.dateCreation) = '"
					+ dateFormat.format(s.getDateCreation()) + "'";
		}
        return sql;
	}

	public Integer getNombreSinistreListeAffectation(Sinistre s)
			throws PersistenceException {
		Query query = getSession().createQuery(
				getListAffectationSinistreQuery(s));
		List list = query.list();
        if (list != null && !list.isEmpty()) {
			return Integer.valueOf(list.size());
		} else {
			return Integer.valueOf(0);
		}

	}

	public List getListAffectationSinistre(Sinistre s, PagerVO pagerVO)
			throws PersistenceException {
		if (s == null) {
            return new ArrayList();
		}
		Query query = getSession().createQuery(
				getListAffectationSinistreQuery(s));
		if (pagerVO != null) {
			Integer numpage = 0;
			if (Integer.valueOf(pagerVO.getNumPage()).intValue() > 0) {
				numpage = Integer.valueOf(pagerVO.getNumPage()) - 1;
			}
			return this.getPartCollectionByCondition(query, numpage,
					Integer.valueOf(pagerVO.getPageSize()));
		} else {
			return query.list();
		}
	}

	public List getListDecisionTransaction(Sinistre s, PagerVO pagerVO)
			throws PersistenceException {

		Query query = getSession().createQuery(
				getListDecisionTransactionQuery(s));
		if (pagerVO != null) {
			Integer numpage = 0;
			if (Integer.valueOf(pagerVO.getNumPage()).intValue() > 0) {
				numpage = Integer.valueOf(pagerVO.getNumPage()) - 1;
			}

			return this.getPartCollectionByCondition(query, numpage,
					Integer.valueOf(pagerVO.getPageSize()));
		} else {
			return query.list();
		}
	}

	private String getListDecisionTransactionQuery(Sinistre s) {
        String sql = "Select s from Sinistre s where s.id in"
				+ " (select t.refSinistre.id from Transaction t where t.typeLocalisation != null and isTransigible=null)";
		if (s != null && !StringUtils.isEmpty(s.getNumeroSinistre())) {
            sql += " and s.numeroSinistre like '" + s.getNumeroSinistre() + "'";
		}

		if (s != null && s.getDateCreation() != null) {
            sql += " and TO_DATE(s.dateCreation) = '"
					+ dateFormat.format(s.getDateCreation()) + "'";
		}
        return sql;
	}

	public Integer getNombreSinistreListeDecision(Sinistre s)
			throws PersistenceException {
		Query query = getSession().createQuery(
				getListDecisionTransactionQuery(s));
		List list = query.list();
        if (list != null && !list.isEmpty()) {
			return Integer.valueOf(list.size());
		} else {
			return Integer.valueOf(0);
		}

	}

	public List getListTraitementTransaction(Sinistre s, PagerVO pagerVO)
			throws PersistenceException {
		Query query = getSession().createQuery(
				getListTraitementTransactionQuery(s));
		if (pagerVO != null) {
			Integer numpage = 0;
			if (Integer.valueOf(pagerVO.getNumPage()).intValue() > 0) {
				numpage = Integer.valueOf(pagerVO.getNumPage()) - 1;
			}

			return this.getPartCollectionByCondition(query, numpage,
					Integer.valueOf(pagerVO.getPageSize()));
		} else {
			return query.list();
		}
	}

	private String getListTraitementTransactionQuery(Sinistre s) {
        String sql = "Select s from Sinistre s where s.id in"
				+ " (select t.refSinistre.id from Transaction t where t.typeLocalisation !=null  and isTransigible = 1 and t.id not in"
				+ " (select cast(coalesce(c.refTransaction.id,0) as long) from TransactionConvocation c) )";
		if (s != null && !StringUtils.isEmpty(s.getNumeroSinistre())) {
            sql += " and s.numeroSinistre like '" + s.getNumeroSinistre() + "'";
		}
        return sql;

	}

	public Integer getNombreSinistreListeTraitement(Sinistre s)
			throws PersistenceException {
		Query query = getSession().createQuery(
				getListTraitementTransactionQuery(s));
		List list = query.list();
        if (list != null && !list.isEmpty()) {
			return Integer.valueOf(list.size());
		} else {
			return Integer.valueOf(0);
		}
	}

	public Victime getVictime(String numeroSinistre)
			throws PersistenceException {
		StringBuffer hql = new StringBuffer(
				" select victime from Sinistre sin ");
		hql.append(" join sin.refVictime as victime ");
		hql.append(" where 1=1 ");
		// id
		if (numeroSinistre != null) {
			hql.append(" and sin.numeroSinistre='").append(numeroSinistre)
					.append("'");
		}

		Query query = getSession().createQuery(hql.toString());

		return (Victime) query.uniqueResult();
	}

    public List<Sinistre> getListSinistreByIpp(Sinistre sinistre, Map map,
			PagerVO pagerVO) throws PersistenceException,
			FonctionnelleException {

		if (sinistre == null) {
            return new ArrayList<Sinistre>();
		}
        Query query = this.getListSinistreQueryByIpp(sinistre, (HashMap) map);
		if (pagerVO != null) {
			Integer numpage = 0;
			if (Integer.valueOf(pagerVO.getNumPage()).intValue() > 0) {
				numpage = Integer.valueOf(pagerVO.getNumPage()) - 1;
			}

			return this.getPartCollectionByCondition(query, numpage,
					Integer.valueOf(pagerVO.getPageSize()));
		} else {
			return query.list();
		}
	}

    public Integer getNombreSinistreByIpp(Sinistre sinistre, Map map)
			throws FonctionnelleException, PersistenceException {
        Query query = this.getListSinistreQueryByIpp(sinistre, (HashMap) map);
		List list = query.list();
        if (list != null && !list.isEmpty()) {
			return Integer.valueOf(list.size());
		} else {
			return Integer.valueOf(0);
		}
	}

	private Query getListSinistreQueryByIpp(Sinistre sinistre,
            Map paramsDate) throws FonctionnelleException,
			PersistenceException {

		if (sinistre == null) {
			throw new FonctionnelleException(EXP_SIN_NULL);
		}

		StringBuffer hql = new StringBuffer("from Sinistre sinistre  ");

		hql.append(" where 1=1 and ipp >=10");
		// id
		if (sinistre.getId() != 0) {
			hql.append(" and sinistre.id=").append(sinistre.getId())
					.append(" ");
		}

		// numQuittance
		if (!StringUtils.isEmpty(sinistre.getNumeroSinistre())
				&& !StringUtils.isEmpty(sinistre.getNumeroSinistre().trim())) {
			hql.append(" and sinistre.numeroSinistre='")
					.append(sinistre.getNumeroSinistre().trim()).append("' ");
		}

		if (!StringUtils.isEmpty(sinistre.getNumeroGrave())
				&& !StringUtils.isEmpty(sinistre.getNumeroGrave().trim())) {
			hql.append(" and sinistre.numeroGrave='")
					.append(sinistre.getNumeroGrave().trim()).append("' ");
		}

		if (!StringUtils.isEmpty(sinistre.getNumeroPolice())
				&& !StringUtils.isEmpty(sinistre.getNumeroPolice().trim())) {
			hql.append(" and sinistre.numeroPolice='")
					.append(sinistre.getNumeroPolice().trim()).append("' ");
		}

		// montantQuittance
		if (sinistre.getRefVictime() != null) {
			if (!StringUtils.isEmpty(sinistre.getRefVictime().getNom())
					&& !StringUtils.isEmpty(sinistre.getRefVictime().getNom()
							.trim())) {
				hql.append(" and upper(sinistre.refVictime.nom) like '%")
						.append(StringUtils.upperCase(sinistre.getRefVictime()
								.getNom().trim())).append("%' ");
			}
			if (!StringUtils.isEmpty(sinistre.getRefVictime().getPrenom())
					&& !StringUtils.isEmpty(sinistre.getRefVictime()
							.getPrenom().trim())) {
				hql.append(" and upper(sinistre.refVictime.prenom) like '")
						.append(StringUtils.upperCase(sinistre.getRefVictime()
								.getPrenom().trim())).append("' ");
			}
			if (!StringUtils.isEmpty(sinistre.getRefVictime().getNumeroCIN())
					&& !StringUtils.isEmpty(sinistre.getRefVictime()
							.getNumeroCIN().trim())) {
				hql.append(" and sinistre.refVictime.numeroCIN = '")
						.append(sinistre.getRefVictime().getNumeroCIN().trim())
						.append("' ");
			}
		}
		if (!StringUtils.isEmpty(sinistre.getNomIntermediaire())
				&& !StringUtils.isEmpty(sinistre.getNomIntermediaire().trim())) {
			hql.append(" and upper(sinistre.nomIntermediaire)='")
					.append(StringUtils.upperCase(sinistre
							.getNomIntermediaire().trim())).append("' ");
		}
		if (!StringUtils.isEmpty(sinistre.getCodeIntermediaire())
				&& !StringUtils.isEmpty(sinistre.getCodeIntermediaire().trim())) {
			hql.append(" and upper(sinistre.codeIntermediaire)='")
					.append(StringUtils.upperCase(sinistre
							.getCodeIntermediaire().trim())).append("' ");
		}
		if (sinistre.getRefEvenement() != null) {
			if (!StringUtils.isEmpty(sinistre.getRefEvenement()
					.getNumeroEvenement())
					&& !StringUtils.isEmpty(sinistre.getRefEvenement()
							.getNumeroEvenement().trim())) {
				hql.append(" and sinistre.refEvenement.numeroEvenement='")
						.append(sinistre.getRefEvenement().getNumeroEvenement())
						.append("' ");
			}

			if (sinistre.getRefEvenement().getDateAccident() != null) {
				Date dateAccident = sinistre.getRefEvenement()
						.getDateAccident();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				hql.append(" and sinistre.refEvenement.dateAccident='")
						.append(sdf.format(dateAccident)).append("' ");

			}
		}

		if (sinistre.getIdRecours() != null) {

			hql.append(" and sinistre.idRecours=")
					.append(sinistre.getIdRecours()).append(" ");
		}

		/** Les dates */
		if (sinistre.getRefEvenement() != null) {
			hql.append(construireDcDate(sinistre.getRefEvenement()
					.getDateAccidentDebut(), sinistre.getRefEvenement()
					.getDateAccidentFin(), "sinistre.refEvenement.dateAccident"));
		}
		if (sinistre.getDateCreation() != null) {
			hql.append(" and dateCreation =to_date('")
					.append(dateFormat.format(sinistre.getDateCreation()))
					.append("')");

		}

		/*** limiter le nombre de ligne ï¿½ 50 ***/
		Query query = getSession().createQuery(hql.toString()).setMaxResults(
				Long.valueOf(IParam.MAX_SINISTRE).intValue() + 1);

		return query;
	}

	public Double calculCoutSinistre(Sinistre sinistre) throws Exception {

		Double cumulReserve = sinistre.getReserveGrave()
				+ sinistre.getReserveOrdinaire()
				+ sinistre.getReserveProthese();

		Double cumulReglement = sinistre.getCumulReglementAnne()
				+ sinistre.getCumulReglementAnnePrec();

		return new BigDecimal(cumulReglement + cumulReserve).setScale(2,
				BigDecimal.ROUND_HALF_EVEN).doubleValue();

	}

	public Sinistre creerMovementReglementCreation(Reglement reglement)
			throws FonctionnelleException {
		return creerMouvementReglement(reglement, IConstantes.MOTIF_PREQUITTANCE);
	}

	public Sinistre creerMovementReglementModification(Reglement reglement)
			throws FonctionnelleException {
		return creerMouvementReglement(reglement, IConstantes.MOTIF_REGLEMENT);
	}

	private Sinistre recupererSinistreAvantCreationAudience(Sinistre sinDB,
			long idAudience) throws FonctionnelleException {

		try {

			Mouvement mvtOld = getMvtByIdSinistre(sinDB);
			sinDB.setReserveGrave(mvtOld.getReserveGraveOld());

			sinDB.setReserveOrdinaire(mvtOld.getReserveOrdOld());
			sinDB.setReserveProthese(mvtOld.getReserveProtheseOld());
			sinDB.setDateModification(new Date());
			sinDB.getRefVictime().setSalaireUtile(mvtOld.getSalaireUtileOld());
			sinDB.getRefVictime()
					.setSalaireAnnuel(mvtOld.getSalaireAnnuelOld());
			sinDB.setIpp(mvtOld.getIppOld());
			sinDB.setIppJugement(mvtOld.getIppOld());
			sinDB.setSalaireJugement(Double.valueOf(0));
			sinDB.setDateDepartRente(null);

			return sinDB;

		} catch (Exception e) {
			logger.error(EXP_MODIF_SIN_REG, e);
			throw new FonctionnelleException(EXP_MODIF_SIN_REG, e);
		}

	}

	public AudienceJugement creerMovementAudianceSuppression(
			AudienceJugement audience) throws FonctionnelleException {
		return creerMouvementSuppresionAudiance2(audience,
				IConstantes.MOTIF_SUPPRESSION_AUDIENCE);

	}

	public Mouvement getLastMvtAUdByIdSinistre(Sinistre sin, long idAudience)
			throws PersistenceException, FonctionnelleException {
		String hql = null;
		if (sin.getId() == 0) {
			if (sin.getNumeroSinistre() == null
					|| "".equals(sin.getNumeroSinistre())) {
				throw new FonctionnelleException(EXP_NUM_SIN_REQUIRED);
			} else {
				hql = " from Mouvement mvt where  mvt.refSinistre.numeroSinistre='"
						+ sin.getNumeroSinistre()
						+ "'"
						+ "mvt.refMotif.id=3 "
						+ " and mvt.idAudience!="
						+ idAudience
						+ " order by mvt.id desc";
			}
		} else {
			hql = " from Mouvement mvt where  mvt.refSinistre=" + sin.getId()
					+ " and mvt.refMotif.id=3 " + " and mvt.idAudience!="
					+ idAudience + " order by mvt.id desc";
		}

		Query query = getSession().createQuery(hql);
		if (!query.list().isEmpty()) {
			return (Mouvement) query.list().get(0);
		}
		return null;
	}

	private AudienceJugement creerMouvementSuppresionAudiance2(
			AudienceJugement audience0, long codeMotif)
			throws FonctionnelleException {
		try {
			MotifMouvement motifMouv = new MotifMouvement();
			AudienceJugement audience = getAUdienceById(audience0.getId());
			ProcedureJudiciaire procedure = audience
					.getRefProcedureJudiciaire();
			Recours recours = procedure.getRefRecours();
			Sinistre sinistre = recours.getRefSinistre();
			sinistre.setUserModificateur(audience.getUserModificateur());
			sinistre.setDateModification(new Date());
			if ("1".equals(audience.getEtatEnCoursAUdience())) {
				sinistre = recupererSinistreAvantCreationAudience(sinistre,
						audience.getId());
			}
			Mouvement mvt = new Mouvement();
			Victime victime = sinistre.getRefVictime();
			Evenement evenement = sinistre.getRefEvenement();
			mvt.setIdAudience(audience.getId());
			mvt.setIppNew(sinistre.getIpp());
			mvt.setIttNew(sinistre.getItt());
			mvt.setDateNaissanceNew(victime.getDateNaissance());
			mvt.setDateAccidentNew(evenement.getDateAccident());
			mvt.setSalaireAnnuelNew(victime.getSalaireAnnuel());
			mvt.setSalaireUtileNew(victime.getSalaireUtile());
			mvt.setReserveGrave(sinistre.getReserveGrave());
			mvt.setReserveOrd(sinistre.getReserveOrdinaire());
			mvt.setReserveProthese(sinistre.getReserveProthese());
			mvt.setUserCreateur(sinistre.getUserModificateur());
			mvt.setRefEtatSinistre(sinistre.getRefEtatSinistre());
			motifMouv.setId(codeMotif);
			mvt.setRefMotif(motifMouv);
			mvt.setDateCreation(new Date());
			mvt.setRefSinistre(sinistre);
			if (sinistre.getRefEtatSinistre() != null
					&& sinistre.getRefEtatSinistre().getRefEtat() != null) {
				mvt.setCodeEtatNew(Long.valueOf(sinistre.getRefEtatSinistre()
						.getRefEtat().getCode()));
			}

			Mouvement mvtOld = getMvtByIdSinistre(sinistre);
			mvt.setByOld(mvtOld);
			dao.createObject(mvt);
			recours.setRefSinistre(sinistre);
			procedure.setRefRecours(recours);
			return audience;
		} catch (Exception e) {
			logger.error(EXP_MODIF_SIN_REG, e);
			throw new FonctionnelleException(EXP_MODIF_SIN_REG, e);
		}
	}

	public Sinistre creerMovementReglementAnnulation(Reglement reglement)
			throws FonctionnelleException {
		return creerMouvementReglement(reglement,
				IConstantes.MOTIF_ANNULATION_REGLEMENT_AVANT_VALIDATION);
	}

	public Sinistre creerMovementReglementSuppression(Reglement reglement)
			throws FonctionnelleException {
		try {
			MotifMouvement motifMouv = new MotifMouvement();
			Sinistre sinistre = reglement.getRefSinistre();
			if (sinistre == null) {
				throw new FonctionnelleException(EXP_OBJECT_ENTREE);
			}
			Sinistre sinDB = getSinistreById(Long.valueOf(sinistre.getId()));
			List<Mouvement> listeMouvement = getMouvementByReglement(reglement);
			if (listeMouvement != null && !listeMouvement.isEmpty()) {
				Mouvement nouveauMouvement = new Mouvement();
				// Calculer l'impact du rï¿½glement
				Double diffReserveGrave = getDiffReserveGrave(listeMouvement);
				Double diffReserveOrd = getDiffReserveOrd(listeMouvement);
				Double diffReserveProthese = getDiffReserveProthese(listeMouvement);
				// Sauvegarder les anciennes valeurs
				nouveauMouvement.setReserveGraveOld(sinDB.getReserveGrave());
				nouveauMouvement.setReserveOrdOld(sinDB.getReserveOrdinaire());
				nouveauMouvement.setReserveProtheseOld(sinDB
						.getReserveProthese());
				// Actualiser le sinistre avec les nouvelles valeurs
				sinDB.setReserveGrave(Double.valueOf(sinistre.getReserveGrave()
						.doubleValue() + diffReserveGrave.doubleValue()));
				sinDB.setReserveOrdinaire(sinistre.getReserveOrdinaire()
						+ diffReserveOrd);
				sinDB.setReserveProthese(Double.valueOf(sinistre
						.getReserveProthese().doubleValue()
						+ diffReserveProthese.doubleValue()));
				sinDB.setDateModification(new Date());
				sinDB.setUserModificateur(reglement.getNomUserModificateur());
				// Crï¿½ation nouveau mouvement
				copierSinistreMouvement(sinDB, nouveauMouvement);
				nouveauMouvement.setIdReglement(reglement.getId());
				motifMouv.setId(IConstantes.MOTIF_MOUVEMENT_SIN_REG_SUPP);
				nouveauMouvement.setRefMotif(motifMouv);
				nouveauMouvement.setRefEtatSinistre(sinDB.getRefEtatSinistre());
				if (sinDB.getRefEtatSinistre() != null
						&& sinDB.getRefEtatSinistre().getRefEtat() != null) {
					nouveauMouvement.setCodeEtatNew(Long.valueOf(sinDB
							.getRefEtatSinistre().getRefEtat().getCode()));
				}
				dao.createObject(nouveauMouvement);
			}
			return sinDB;
		} catch (Exception e) {
			logger.error(EXP_MODIF_SIN_REG, e);
			throw new FonctionnelleException(EXP_MODIF_SIN_REG, e);
		}

	}

	// Calcul de l'impact du rï¿½glement sur chaque reserve
	private Double getDiffReserveGrave(List<Mouvement> listeMouvement) {
		Double diffReserveGrave = Double.valueOf(0);
		int i = 0;
		while (i < listeMouvement.size()) {
			Mouvement mouvement = listeMouvement.get(i);
			diffReserveGrave += mouvement.getReserveGraveOld()
					- mouvement.getReserveGrave();
			i++;

		}
		return diffReserveGrave;
	}

	private Double getDiffReserveOrd(List<Mouvement> listeMouvement) {
		Double diffReserveOrd = Double.valueOf(0);
		int i = 0;
		while (i < listeMouvement.size()) {
			Mouvement mouvement = listeMouvement.get(i);
			diffReserveOrd += mouvement.getReserveOrdOld()
					- mouvement.getReserveOrd();
			i++;
		}
		return diffReserveOrd;

	}

	private Double getDiffReserveProthese(List<Mouvement> listeMouvement) {
		Double diffReserveProthese = Double.valueOf(0);
		int i = 0;
		while (i < listeMouvement.size()
				&& diffReserveProthese.equals(Double.valueOf(0))) {
			Mouvement mouvement = listeMouvement.get(i);
			diffReserveProthese += mouvement.getReserveProtheseOld()
					- mouvement.getReserveProthese();
			i++;
		}
		return diffReserveProthese;
	}

	private void copierSinistreMouvement(Sinistre sinistre, Mouvement mouvement) {
		Victime victime = sinistre.getRefVictime();
		Evenement evenement = sinistre.getRefEvenement();
		mouvement.setIppNew(sinistre.getIpp());
		mouvement.setIttNew(sinistre.getItt());
		mouvement.setDateNaissanceNew(victime.getDateNaissance());
		mouvement.setDateAccidentNew(evenement.getDateAccident());
		mouvement.setSalaireAnnuelNew(victime.getSalaireAnnuel());
		mouvement.setSalaireUtileNew(victime.getSalaireUtile());
		mouvement.setReserveGrave(sinistre.getReserveGrave());
		mouvement.setReserveOrd(sinistre.getReserveOrdinaire());
		mouvement.setReserveProthese(sinistre.getReserveProthese());
		mouvement.setUserCreateur(sinistre.getUserModificateur());
		mouvement.setMotifEtat(IMessageInfo.MOTIF_MOUVEMENT_SIN_REG_SUPP);
		mouvement.setDateCreation(new Date());
		mouvement.setIppOld(sinistre.getIpp());
		mouvement.setIttOld(sinistre.getItt());
		mouvement.setDateNaissanceOld(sinistre.getRefVictime()
				.getDateNaissance());
		mouvement.setDateAccidentOld(sinistre.getRefEvenement()
				.getDateAccident());
		mouvement.setSalaireAnnuelOld(sinistre.getRefVictime()
				.getSalaireAnnuel());
		mouvement
				.setSalaireUtileOld(sinistre.getRefVictime().getSalaireUtile());
		mouvement.setRefSinistre(sinistre);
	}

	private List getMouvementByReglement(Reglement reglement)
			throws FonctionnelleException {
		try {
			String hql = null;
			hql = (new StringBuilder(
					" from Mouvement mvt where  mvt.idReglement="))
					.append(reglement.getId()).append(" order by mvt.id desc")
					.toString();
			Query query = getSession().createQuery(hql);
			return query.list();
		} catch (PersistenceException e) {
			logger.error(EXP_MODIF_SIN_REG, e);
			throw new FonctionnelleException(EXP_MODIF_SIN_REG, e);
		}
	}

	public Sinistre creerMouvementReglement(Reglement reglement, long codeMotif)
			throws FonctionnelleException {

		try {

			MotifMouvement motifMouv = new MotifMouvement();
			Sinistre sinistre = reglement.getRefSinistre();
			sinistre.setUserModificateur(reglement.getNomUserCreateur() == null ? reglement.getNomUserModificateur() : reglement.getNomUserCreateur());
			sinistre = modifierSinistreRgl(sinistre);
			Mouvement mvt = new Mouvement();
			Victime victime = sinistre.getRefVictime();
			Evenement evenement = sinistre.getRefEvenement();
			mvt.setIppNew(sinistre.getIpp());
			mvt.setIttNew(sinistre.getItt());
			mvt.setDateNaissanceNew(victime.getDateNaissance());
			mvt.setDateAccidentNew(evenement.getDateAccident());
			mvt.setSalaireAnnuelNew(victime.getSalaireAnnuel());
			mvt.setSalaireUtileNew(victime.getSalaireUtile());
			mvt.setReserveGrave(sinistre.getReserveGrave());
			mvt.setReserveOrd(sinistre.getReserveOrdinaire());
			mvt.setReserveProthese(sinistre.getReserveProthese());
			mvt.setUserCreateur(sinistre.getUserModificateur());
			motifMouv.setId(codeMotif);
			mvt.setRefMotif(motifMouv);
			mvt.setRefEtatSinistre(sinistre.getRefEtatSinistre());
			motifMouv.setId(codeMotif);
			mvt.setRefMotif(motifMouv);
			mvt.setDateCreation(new Date());
			mvt.setRefSinistre(sinistre);
			mvt.setIdReglement(reglement.getId());
			if (sinistre.getRefEtatSinistre() != null
					&& sinistre.getRefEtatSinistre().getRefEtat() != null) {
				mvt.setCodeEtatNew(Long.valueOf(sinistre.getRefEtatSinistre()
						.getRefEtat().getCode()));
			}
			Mouvement mvtOld = getMvtByIdSinistre(sinistre);
			mvt.setByOld(mvtOld);
			getSession().save(mvt);
			return sinistre;

		} catch (Exception e) {
			logger.error(EXP_MODIF_SIN_REG, e);
			throw new FonctionnelleException(EXP_MODIF_SIN_REG, e);
		}
	}

	public Sinistre creerMouvementReglement(Object... objects)
			throws FonctionnelleException {

		Reglement reglement = null;
		Session sessionLocale = null;
		long codeMotif;
		if (objects == null) {
			return null;
		}

		try {
			if (objects.length == 3) {
				reglement = (Reglement) objects[0];
				codeMotif = (Long) objects[1];
				sessionLocale = (Session) objects[2];
			} else {
				reglement = (Reglement) objects[0];
				codeMotif = (Long) objects[1];
				sessionLocale = getSession();
			}

			MotifMouvement motifMouv = new MotifMouvement();
			Sinistre sinistre = reglement.getRefSinistre();
			sinistre.setUserModificateur(reglement.getNomUserCreateur());
			sinistre = modifierSinistreRgl(sinistre, sessionLocale);
			Mouvement mvt = new Mouvement();
			Victime victime = sinistre.getRefVictime();
			Evenement evenement = sinistre.getRefEvenement();
			mvt.setIppNew(sinistre.getIpp());
			mvt.setIttNew(sinistre.getItt());
			mvt.setDateNaissanceNew(victime.getDateNaissance());
			mvt.setDateAccidentNew(evenement.getDateAccident());
			mvt.setSalaireAnnuelNew(victime.getSalaireAnnuel());
			mvt.setSalaireUtileNew(victime.getSalaireUtile());
			mvt.setReserveGrave(sinistre.getReserveGrave());
			mvt.setReserveOrd(sinistre.getReserveOrdinaire());
			mvt.setReserveProthese(sinistre.getReserveProthese());
			mvt.setUserCreateur(sinistre.getUserModificateur());
			motifMouv.setId(codeMotif);
			mvt.setRefMotif(motifMouv);
			mvt.setRefEtatSinistre(sinistre.getRefEtatSinistre());
			motifMouv.setId(codeMotif);
			mvt.setRefMotif(motifMouv);
			mvt.setDateCreation(new Date());
			mvt.setRefSinistre(sinistre);
			mvt.setIdReglement(reglement.getId());
			if (sinistre.getRefEtatSinistre() != null
					&& sinistre.getRefEtatSinistre().getRefEtat() != null) {
				mvt.setCodeEtatNew(Long.valueOf(sinistre.getRefEtatSinistre()
						.getRefEtat().getCode()));
			}
			Mouvement mvtOld = getMvtByIdSinistre(sinistre, sessionLocale);
			mvt.setByOld(mvtOld);
			sessionLocale.save(mvt);
			return sinistre;

		} catch (Exception e) {
			logger.error(EXP_MODIF_SIN_REG, e);
			throw new FonctionnelleException(EXP_MODIF_SIN_REG, e);
		}
	}

	public ComplementQuittanceView getComplementQuittance(String numeroQuittance)
			throws PersistenceException {
        if (numeroQuittance == null || "".equals(numeroQuittance)) {
			return null;
		}
		String hql = " from eai.devass.sinistreat.appli.modele.metier.sinistre.ComplementQuittanceView complQtcView where complQtcView.numeroQuittance = '"
				+ numeroQuittance + "'";
		ComplementQuittanceView complementQtc = null;
		complementQtc = (ComplementQuittanceView) getSession().createQuery(hql)
				.uniqueResult();
		return complementQtc;
	}

	/*
	 * Mossï¿½b la somme des arrï¿½rages avant constitution rï¿½glï¿½s
	 */
	public double doSommeArrerageRegle(String sinistre)
			throws HibernateException, ProcessEntiteException, EntiteException,
			PersistenceException {

		String hql = "select sum(COALESCE(dr.montant,0)) from Sinistre s,Reglement r,DetailReglement dr where s.id="
				+ sinistre
				+ " and s.id = r.refSinistre and r.id = dr.refReglement and dr.refPrestation.code = 21 group by s.id order by s.id";
        IPersistenceService daoService = (IPersistenceService) ServicesFactory
				.getService(IPersistenceService.class);
        Query query = ((Session) daoService.getSession()).createQuery(hql);
		if (!query.list().isEmpty()) {
			Double res = ((Double) query.uniqueResult()).doubleValue();
			return res;
		} else {
			return Double.valueOf(0);
		}
	}

	/**
	 * Mise a jour de la fiche sinistre apres mise a jour du suite Jugement QC
	 * Nï¿½286
	 * 
	 * @param AudienceJugement
	 */

	public void updateSinistre(AudienceJugement audience)
			throws FonctionnelleException {
		Sinistre sinistre = null;
		try {
			audience.setIdSinistre(audience.getRefProcedureJudiciaire()
					.getRefRecours().getRefSinistre().getId());

			if (audience.getIdSinistre() != null) {
				sinistre = getSinistreById(audience.getIdSinistre());

				if (sinistre == null) {
					throw new FonctionnelleException(EXP_SINISTRE_INEXISTANT);
				}
				// IPP Jugement
				if (audience.getIppJugement() != null) {
					sinistre.setIpp(audience.getIppJugement());
				}
				// Salaire Jugement
				if (audience.getSalaireJugement() != null) {
					sinistre.setSalaireJugement(audience.getSalaireJugement());
				}

				// RG
				if (audience.getReserveGrave() != null) {
					sinistre.setReserveGrave(audience.getReserveGrave());
				}
				// RO
				if (audience.getReserveOrdinaire() != null) {
					sinistre.setReserveOrdinaire(audience.getReserveOrdinaire());
				}
				// RP
				if (audience.getReserveProthese() != null) {
					sinistre.setReserveProthese(audience.getReserveProthese());
				}

				// Date depart rente
				if (audience.getDateDepartRente() != null) {
					sinistre.setDateDepartRente(audience.getDateDepartRente());
				}
				dao.updateObject(sinistre);
			}
		} catch (FonctionnelleException e) {
			logger.error(EXP_MAJ_SINISTRE, e);
			throw new FonctionnelleException(EXP_MAJ_SINISTRE, e);
		} catch (PersistenceException e) {
			logger.error(EXP_MAJ_SINISTRE, e);
			throw new FonctionnelleException(EXP_MAJ_SINISTRE, e);
		}
	}

	public ProcedureJudiciaire creerMovementAudianceCreation(
			AudienceJugement audience) throws FonctionnelleException {
		return creerMouvementAudiance(audience, IConstantes.MOTIF_AUDIENCE);

	}

	private ProcedureJudiciaire creerMouvementAudiance(
			AudienceJugement audience, long codeMotif)
			throws FonctionnelleException {
		try {
			MotifMouvement motifMouv = new MotifMouvement();
			ProcedureJudiciaire procedure = audience
					.getRefProcedureJudiciaire();
			Recours recours = procedure.getRefRecours();
			Sinistre sinistre = recours.getRefSinistre();
			sinistre.setUserModificateur(audience.getUserModificateur());
			sinistre.setDateModification(new Date());
			sinistre = modifierSinistreAudiance(sinistre, audience);
			Mouvement mvt = new Mouvement();
			Victime victime = sinistre.getRefVictime();
			Evenement evenement = sinistre.getRefEvenement();
			mvt.setIdAudience(audience.getId());
			mvt.setIppNew(sinistre.getIpp());
			mvt.setIttNew(sinistre.getItt());
			mvt.setDateNaissanceNew(victime.getDateNaissance());
			mvt.setDateAccidentNew(evenement.getDateAccident());
			mvt.setSalaireAnnuelNew(victime.getSalaireAnnuel());
			mvt.setSalaireUtileNew(victime.getSalaireUtile());
			mvt.setReserveGrave(sinistre.getReserveGrave());
			mvt.setReserveOrd(sinistre.getReserveOrdinaire());
			mvt.setReserveProthese(sinistre.getReserveProthese());
			mvt.setUserCreateur(sinistre.getUserModificateur());
			mvt.setRefEtatSinistre(sinistre.getRefEtatSinistre());
			motifMouv.setId(codeMotif);
			mvt.setRefMotif(motifMouv);
			mvt.setDateCreation(new Date());
			mvt.setRefSinistre(sinistre);
			if (sinistre.getRefEtatSinistre() != null
					&& sinistre.getRefEtatSinistre().getRefEtat() != null) {
				mvt.setCodeEtatNew(Long.valueOf(sinistre.getRefEtatSinistre()
						.getRefEtat().getCode()));
			}

			Mouvement mvtOld = getMvtByIdSinistre(sinistre);
			mvt.setByOld(mvtOld);
			dao.createObject(mvt);
			recours.setRefSinistre(sinistre);
			procedure.setRefRecours(recours);
			return procedure;
		} catch (Exception e) {
			logger.error(EXP_MODIF_SIN_REG, e);
			throw new FonctionnelleException(EXP_MODIF_SIN_REG, e);
		}
	}

	public ProcedureJudiciaire creerMovementProcedureModification(
			ProcedureJudiciaire proc) throws FonctionnelleException {
		return creerMovementProcedure(proc, IConstantes.MOTIF_AUDIENCE);
	}

	private ProcedureJudiciaire creerMovementProcedure(
			ProcedureJudiciaire proc, long codeMotif)
			throws FonctionnelleException {
		try {
			MotifMouvement motifMouv = new MotifMouvement();
			Recours recours = proc.getRefRecours();
			Sinistre sinistre = recours.getRefSinistre();
			// la rï¿½gle de l'utilisateur modificateur
			sinistre.setUserModificateur(proc.getUserModificateur());
			sinistre.setDateModification(new Date());
			Mouvement mvt = new Mouvement();
			Victime victime = sinistre.getRefVictime();
			Evenement evenement = sinistre.getRefEvenement();
			GestionnaireRelance gestrelance = proc.getRefGestionnaire();
			mvt.setDateCreation(new Date());
			mvt.setIppNew(sinistre.getIpp());
			mvt.setIttNew(sinistre.getItt());
			mvt.setDateNaissanceNew(victime.getDateNaissance());
			mvt.setDateAccidentNew(evenement.getDateAccident());
			mvt.setSalaireAnnuelNew(victime.getSalaireAnnuel());
			mvt.setSalaireUtileNew(victime.getSalaireUtile());
			mvt.setReserveGrave(sinistre.getReserveGrave());
			mvt.setReserveOrd(sinistre.getReserveOrdinaire());
			mvt.setReserveProthese(sinistre.getReserveProthese());
			mvt.setUserCreateur(sinistre.getUserModificateur());
			mvt.setRefEtatSinistre(sinistre.getRefEtatSinistre());
			if (proc.getRefGestionnaire() != null && proc.getRefGestionnaire().getCode() != null) {
				gestrelance.setCode(proc.getRefGestionnaire().getCode());
			}
			
			motifMouv.setId(codeMotif);
			mvt.setRefMotif(motifMouv);
			mvt.setDateCreation(new Date());
			mvt.setRefSinistre(sinistre);
			if (sinistre.getRefEtatSinistre() != null
					&& sinistre.getRefEtatSinistre().getRefEtat() != null) {
				mvt.setCodeEtatNew(Long.valueOf(sinistre.getRefEtatSinistre()
						.getRefEtat().getCode()));
			}
			Mouvement mvtOld = getMvtByIdSinistre(sinistre);
			mvt.setByOld(mvtOld);
			dao.createObject(mvt);
			recours.setRefSinistre(sinistre);
			proc.setRefRecours(recours);
			return proc;

		} catch (Exception e) {
			logger.error(EXP_MODIF_SIN_REG, e);
			throw new FonctionnelleException(EXP_MODIF_SIN_REG, e);
		}
	}

	// Pour la partie batch Synchronisation des dossier sinistre avec BDC
	// session : pour gerer l'aspet transactionnelle du taritement BATCH
	public List<Sinistre> getListSinistreToSynchronise(int maxSelect,
			Session session) throws Exception {
		return session
				.createQuery("from Sinistre where dateSynchronisation is null")
				.setMaxResults(maxSelect).list();
	}

	public Long getNbrSinistreToSynchronise() throws Exception {
		return (Long) getSession()
				.createQuery(
						"select count(*) from Sinistre where dateSynchronisation is null")
				.uniqueResult();
	}

	public void updateDateSynchroSinistre(Date dateSynchro, String numSinistre,
			Session session) throws Exception {
		// IL faut utiliser jdbc, update="false" dans le fichier hbm,
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			// La connexion sera fermer lors de la fermeture de la session
			connection = session.connection();
			preparedStatement = connection
					.prepareStatement("update SIN_SINISTRE set DAT_SYNCHRO=? where NUMEROSINISTRE=?");
			preparedStatement.setDate(1,
					new java.sql.Date(dateSynchro.getTime()));
			preparedStatement.setString(2, numSinistre);
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			logger.error("Erreur base de donnï¿½e", e);
			throw new FonctionnelleException("Erreur base de donnï¿½e", e);

		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();

				} catch (SQLException e2) {
					logger.error("Erreur base de donnï¿½e", e2);

				}
				preparedStatement = null;
			}

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e2) {
					logger.error("Erreur base de donnï¿½e", e2);
					throw new FonctionnelleException("Erreur base de donnï¿½e",
							e2);
				} finally {
					connection = null;
				}
			}
		}

	}

	public List getNombreSinistre(String etatSinistre, String intermediaire,
			String typeCreation) throws HibernateException,
			PersistenceException {

		boolean ok = false;
		StringBuffer hql = new StringBuffer(
				" select count (*) from Sinistre sinistre , VEtatSinistre vEtat ");

		hql.append(" where 1=1 ");
		// id
		if (etatSinistre != null) {
			hql.append(" and vEtat.refEtat=?");
			hql.append(" and sinistre.id=vEtat.refSinistre.id");
			ok = true;
		}
        if (intermediaire != null && !"".equals(intermediaire)) {
			hql.append(" and sinistre.codeIntermediaire like '%")
					.append(intermediaire).append("%' ");
		}
        if (typeCreation != null && !"".equals(typeCreation)) {
			hql.append(" and sinistre.typeCreation='").append(typeCreation)
					.append("' ");
		}

		Query query = getSession().createQuery(hql.toString());
		if (ok) {
			query.setParameter(0, new EtatSin(etatSinistre));
		}

		return query.list();
	}

	public boolean isExRma(Sinistre sinistre) throws FonctionnelleException {
		if (sinistre.getNumeroPolice() == null
				|| sinistre.getNumeroPolice().length() < 5) {
			throw new FonctionnelleException(
					"Le numï¿½ro de la police est invalide !!");
		}

		if (sinistre.getNumeroPolice().startsWith(CODE_DECENTRALISATION_EXRMA)) {
			return true;
		} else {
			return false;
		}
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public List<EtatSinistre> getListEtatSinistre(SinistreVO sinistre)
			throws FonctionnelleException, HibernateException,
			PersistenceException {
		if (sinistre.getId() == null) {
			throw new FonctionnelleException(
					"Impossible de rï¿½cupï¿½rer le sinistre");
		}
		StringBuffer hql = new StringBuffer(" from EtatSinistre");

		hql.append(" where refSinistre.id='").append(sinistre.getId())
				.append("' ");

		hql.append("order by dateEtat");
		Query query = getSession().createQuery(hql.toString());

		return query.list();

	}
	
	public List<NotificationSms> getListNotifSinistre(SinistreVO sinistre)
			throws FonctionnelleException, HibernateException,
			PersistenceException {
		if (sinistre.getId() == null) {
			throw new FonctionnelleException(
					"Impossible de rï¿½cupï¿½rer le sinistre");
		}
		StringBuffer hql = new StringBuffer(" from NotificationSms");

		hql.append(" where refSinistre.id='").append(sinistre.getId())
				.append("' ");

		hql.append("order by datEnvoi");
		Query query = getSession().createQuery(hql.toString());

		return query.list();

	}

	/**
	 * getListMouvementSinistre: mouvement non migrï¿½
	 * 
	 * @param sin
	 * @return
	 * @throws FonctionnelleException
	 * @throws HibernateException
	 * @throws PersistenceException
	 */
	public List<Mouvement> getListMouvementSinistre(SinistreVO sin)
			throws FonctionnelleException, HibernateException,
			PersistenceException {

		if (sin.getId() == null) {
			throw new FonctionnelleException(
					"Impossible de rï¿½cupï¿½rer le sinistre");
		}
		StringBuffer hql = new StringBuffer(" from Mouvement");

		hql.append(" where refMotif.id <> '2'and refSinistre.id='")
				.append(sin.getId()).append("' ");

		hql.append("order by dateCreation,id");
		Query query = getSession().createQuery(hql.toString());

		return query.list();
	}

	/**
	 * getListMouvementSinistre: mouvement non migrï¿½
	 * 
	 * @param sin
	 * @return
	 * @throws FonctionnelleException
	 * @throws HibernateException
	 * @throws PersistenceException
	 */
	public List<Mouvement> getListMouvementSinistreMig(SinistreVO sin)
			throws FonctionnelleException, HibernateException,
			PersistenceException {

		if (sin.getId() == null) {
			throw new FonctionnelleException(
					"Impossible de rï¿½cupï¿½rer le sinistre");
		}
		StringBuffer hql = new StringBuffer(" from Mouvement");

		hql.append(" where refMotif.id = '2'and refSinistre.id='")
				.append(sin.getId()).append("' ");

		hql.append("order by dateCreation,id");
		Query query = getSession().createQuery(hql.toString());

		return query.list();
	}
	
	public List getAllMouvementSinistre(SinistreVO sin, PagerVO pagerVO)
            throws HibernateException, PersistenceException,
            FonctionnelleException {

		try {
			Object[] objects = this.getAllMouvementSinistreByQuery(sin);
			Query query =  getSession().createQuery((String) objects[0]);

			if (pagerVO != null) {
				Integer numpage = 0;
				if (Integer.valueOf(pagerVO.getNumPage()).intValue() > 0) {
					numpage = Integer.valueOf(pagerVO.getNumPage()) - 1;
				}
				return this.getPartCollectionByCondition(query, numpage,
						Integer.valueOf(pagerVO.getPageSize()));
			} else {
				query.setMaxResults(Integer.valueOf(IParam.MAX_SINISTRE));
				return query.list();
			}
		} catch (PersistenceException e) {
			logger.error(EXP_RECHERCHE_REGLEMENT, e);
			throw new FonctionnelleException(EXP_RECHERCHE_REGLEMENT, e);
		}
	}
	
    public Object[] getAllMouvementSinistreByQuery(SinistreVO sin)
            throws HibernateException, PersistenceException {
		StringBuffer hql = new StringBuffer(" from Mouvement m");
		
        hql.append(" where m.refSinistre.id='").append(sin.getId())
                .append("' ");
		hql.append(
				" and ( m.refMotif.id <> '2' OR (m.refMotif.id = '2' AND m.id IN (SELECT MAX (mvt.id ) FROM Mouvement mvt WHERE mvt.refMotif.id = '2' and mvt.refSinistre.id ='")
				.append(sin.getId()).append("')) ");
		hql.append(
				" OR (m.refMotif.id = '2' AND m.id IN (SELECT MIN (mvt.id ) FROM Mouvement mvt WHERE mvt.refMotif.id = '2' and mvt.refSinistre.id ='")
				.append(sin.getId()).append("'))) ");
	
		hql.append("order by m.dateCreation , m.id");
		Object[] objects = new Object[1];
		objects[0] = hql.toString();
		return objects;
	}

    public Integer getNombreMouvement(SinistreVO sin) throws Exception {

		Object[] objects = this.getAllMouvementSinistreByQuery(sin);
		String hql = "Select count (m.id) " + (String) objects[0];
		Query query =  getSession().createQuery(hql);
		Long nombreResultat = (Long) query.uniqueResult();
		return nombreResultat.intValue();
	}
	
	/**
	 * Creation Mouvement Sinistre suite ï¿½ une evolution GSR de
	 * synchronisation
	 * 
	 * @param sinistre
	 * @param useCase
	 * @param codeMotif
	 * @return
	 * @throws FonctionnelleException
	 */
	public Mouvement gsrCreerMouvement(Sinistre sinistre, String useCase,
			long codeMotif) throws FonctionnelleException {
		try {
			Mouvement mvt = new Mouvement();
			MotifMouvement motifMouv = new MotifMouvement();
			Victime victime = sinistre.getRefVictime();
			Evenement evenement = sinistre.getRefEvenement();
			Double salaireAnnuel = new BigDecimal(
					regleManager.calculSalaireAnnuel(sinistre)).setScale(2,
					BigDecimal.ROUND_HALF_EVEN).doubleValue();
			Double salaireUtile = new BigDecimal(
					regleManager.calculSalaireUtile(sinistre)).setScale(2,
					BigDecimal.ROUND_HALF_EVEN).doubleValue();
			// calcule reserve grave , ordinaire et prothese
			if (evenement != null) {
				mvt.setEvenementDec(evenement.getNumeroEvenement());
				mvt.setDateAccidentNew(evenement.getDateAccident());
			}
			if (victime != null) {
				victime.setSalaireAnnuel(salaireAnnuel);
				victime.setSalaireUtile(salaireUtile);
				mvt.setDateNaissanceNew(victime.getDateNaissance());
				mvt.setSalaireAnnuelNew(victime.getSalaireAnnuel());
				mvt.setSalaireUtileNew(victime.getSalaireUtile());
			}

			mvt.setIppNew(sinistre.getIpp());
			mvt.setIttNew(sinistre.getItt());
			mvt.setReserveGrave(sinistre.getReserveGrave());
			mvt.setReserveOrd(sinistre.getReserveOrdinaire());
			mvt.setReserveProthese(sinistre.getReserveProthese());
			mvt.setUserCreateur(sinistre.getUserModificateur());
			if (sinistre.getUserModificateur() != null) {
				mvt.setUserCreateur(sinistre.getUserModificateur());
			}
			mvt.setDateCreation(new Date());
			mvt.setRefSinistre(sinistre);
			motifMouv.setId(codeMotif);
			mvt.setRefMotif(motifMouv);
			mvt.setRefEtatSinistre(sinistre.getRefEtatSinistre());
			if (sinistre.getRefEtatSinistre() != null
					&& sinistre.getRefEtatSinistre().getRefEtat() != null) {
				mvt.setCodeEtatNew(Long.valueOf(sinistre.getRefEtatSinistre()
						.getRefEtat().getCode()));
			}
			// archiver les old valeurs
			Mouvement mvtOld = (Mouvement) getMvtByIdSinistre(sinistre);
			mvt.setByOld(mvtOld);
			dao.createObject(mvt);
			return mvt;
		} catch (FonctionnelleException e) {
			logger.error(EXP_CREER_MOUVEMENT, e);
			throw new FonctionnelleException(EXP_CREER_MOUVEMENT, e);
		} catch (PersistenceException e) {
			logger.error(EXP_CREER_MOUVEMENT, e);
			throw new FonctionnelleException(EXP_CREER_MOUVEMENT, e);
		} catch (Exception e) {
			logger.error(EXP_STAND_MESS, e);
			throw new FonctionnelleException(EXP_STAND_MESS, e);
		}

	}

	public AyantDroit calculReserveAyantDroit(AyantDroit ayantDroit,
			String numeroSinistre) throws FonctionnelleException {
		// Rï¿½cuperer le Sinistre
		Sinistre sinistre;
		sinistre = getSinistreByNum(numeroSinistre);

		if (sinistre == null) {
			throw new FonctionnelleException("Sinistre inexistant");
		}
		// date de calcul
		if (sinistre.getRefVictime() != null) {
			Evenement even = sinistre.getRefEvenement();
			if (even.getDateAccident() != null) {
				sinistre.setDateCalculReserve(even.getDateAccident());
			}
		}

		List<AyantDroit> listAyantDroit;
		try {
			listAyantDroit = getListAyantDroitBySinistre(sinistre
					.getNumeroSinistre());
			if (listAyantDroit != null) {

				listAyantDroit.add(ayantDroit);
				// calcul du taux de rente des ayant droits
                // La nouvelle loi pour tout sinistre survenu après le
                // 22/01/2015 :
				String dateSurvenu = "22/01/2015";
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date dateSur = sdf.parse(dateSurvenu);
				try {
					
                    if (sinistre.getRefEvenement().getDateAccident()
                            .compareTo(dateSur) < 0) {
						listAyantDroit = regleManager.calculTauxRente(
								listAyantDroit, sinistre);
                    } else if (sinistre.getRefEvenement().getDateAccident()
                            .compareTo(dateSur) >= 0) {
						listAyantDroit = regleManager.calculTauxRenteR(
								listAyantDroit, sinistre);
					}

				} catch (Exception e1) {
					logger.error(EXP_STAND_MESS, e1);
					throw new FonctionnelleException(EXP_STAND_MESS, e1);
				}

				// calcule reserve grave , ordinaire et prothese

				for (int i = 0; i < listAyantDroit.size(); i++) {
					AyantDroit ay = (AyantDroit) listAyantDroit.get(i);
					Double reserve;
					try {
						reserve = regleManager.getReserveGraveAY(ay, sinistre);
					} catch (Exception e) {
						logger.error(EXP_CALCUL_RESERVE, e);
						throw new FonctionnelleException(EXP_CALCUL_RESERVE, e);
					}
					reserve = new BigDecimal(reserve).setScale(2,
							BigDecimal.ROUND_HALF_EVEN).doubleValue();
					ay.setReserve(reserve);
				}
				for (AyantDroit ayantDroit2 : listAyantDroit) {
					if (ayantDroit.getId() == ayantDroit2.getId()) {
						ayantDroit = ayantDroit2;
						break;
					}
				}
			}

		} catch (Exception e1) {
			logger.error(EXP_STAND_MESS, e1);
			throw new FonctionnelleException(EXP_STAND_MESS, e1);
		}

		return ayantDroit;
	}

	public Sinistre reCalculResGravAvantCreerMouvement(Sinistre sinistre)
			throws FonctionnelleException {

		try {
			sinistre.setReserveGrave(regleManager.getReserveGrave(sinistre));
		}

		catch (Exception e) {
			logger.error(EXP_CALCUL_RESERVE, e);
			throw new FonctionnelleException(EXP_CALCUL_RESERVE, e);
		}
		return sinistre;

	}

	public Sinistre reCalculResProthesAvantCreerMouvement(Sinistre sinistre)
			throws FonctionnelleException {

		try {
			sinistre.setReserveProthese(regleManager
					.getReserveProthese(sinistre));
		}

		catch (Exception e) {
			logger.error(EXP_CALCUL_RESERVE, e);
			throw new FonctionnelleException(EXP_CALCUL_RESERVE, e);
		}
		return sinistre;

	}

	// CK : mettre à jour la quittance suite au Batch de synchronisation
	public void majReglement(EtatReglement etatReglement, String numCheque)
			throws Exception {
		StringBuilder hql = new StringBuilder(
				"update Reglement set refEtatReglement=:etatRgl,")
				.append("dateEtat=:dateSort,dateModification=:dateSort,SORT='Exécuté'");

		// Num cheque
		if (numCheque != null) {
			hql.append(",numCheque=:numCheque");
		}

		hql.append(" where refEtatReglement=:etatRglEmi and id=:idRgl");
		Query query = getSession().createQuery(hql.toString())
				.setParameter("etatRgl", etatReglement.getRefEtat())
				.setDate("dateSort", etatReglement.getDateEtat());

		// Num cheque
		if (numCheque != null) {
			query.setString("numCheque", numCheque);
		}

		query.setLong("idRgl", etatReglement.getRefReglement().getId())
				.setParameter("etatRglEmi", new EtatRgl("2"));
		query.executeUpdate();
		getSession().save(etatReglement);
	}

	// Ck : recherche simple du dossier sinistre pour le besoin de GED AT (au
	// lieu de la methode compliquee getSinistreByNum)
    public Sinistre getSinistreForGedByNum(String numSin)
            throws FonctionnelleException {
	 
		Sinistre sinistreGed = getSinistreForGedByNumQuery(numSin);
		// eliminer les caractères spéciaux : 
		
		// adresse
		if (sinistreGed.getAdresseAssure() != null) {
			String adresse = sinistreGed.getAdresseAssure();
			adresse = replaceSpecialCharacter.getSpecialCharacter(adresse);
			sinistreGed.setAdresseAssure(adresse);
		}

		// nom client
		if (sinistreGed.getNomClient() != null) {
			String nomClient = sinistreGed.getNomClient();
			nomClient = replaceSpecialCharacter.getSpecialCharacter(nomClient);
			sinistreGed.setNomClient(nomClient);
		}
		 return sinistreGed; 
		
	}

	public Sinistre getSinistreForGedByNumQuery(String numSin)
			throws FonctionnelleException {
		try {
			return (Sinistre) getSession()
					.createQuery(
							" from Sinistre sin where sin.numeroSinistre=?")
					.setString(0, numSin).uniqueResult();

		} catch (Exception e) {
			logger.error(EXP_RECHERCHE_SINISTRE, e);
			throw new FonctionnelleException(EXP_RECHERCHE_SINISTRE, e);
		}

	}

	public List<Object[]> Recherche() throws FonctionnelleException {
		try {

			String hql = "select distinct s.NUMEROGRAVE,ev.DATEACCIDENT,s.NOMCLIENT,(v.PRENOM ||' '|| v.NOM) as victime,"
					+ " dr.DATEDEBUTPRESTATION as dateITT,"
					+ " dr.DATEFINPRESTATION as dateReprise,"
					+ " to_char(v.SALAIREJOURNALIER) as salaireJournalier,s.NUMEROSINISTRE,dr.MONTANT"
					+ " from sinistreat.sin_victime v join sinistreat.sin_sinistre s on v.ID = s.IDVICTIME"
					+ " join sinistreat.sin_reglement r on s.ID = r.IDSINISTRE"
					+ " join sinistreat.sin_evenement ev on ev.ID = s.IDEVENEMENT"
					+ " join sinistreat.sin_detail_reglement dr on r.id = dr.IDREGLEMENT"
					+ " where r.ETATREGLEMENT = 1 and r.TYPEREGLEMENT = 1 and dr.CODEPRESTATION = 20 and to_char (r.DATEETAT,'DD/MM/YYYY') = (select TO_CHAR(sysdate,'DD/MM/YYYY') from dual)";

			Query query = getSession().createSQLQuery(hql.toString());
			return query.list();

		} catch (Exception e) {
			logger.error(EXP_RECHERCHE_QTC_ITT, e);
			throw new FonctionnelleException(EXP_RECHERCHE_QTC_ITT, e);
		}
	}

	public Boolean verifierDecisionCP(String numSin)
			throws FonctionnelleException {
		Boolean decisionCP = false;
		try {
			if (numSin == null) {
				return null;
			}
			StringBuffer hql = new StringBuffer(
					" select a from AudienceJugement a,ProcedureJudiciaire p, "
							+ " Recours r,Sinistre s ");

			hql.append(" where 1=1 ");
			hql.append(" and a.refProcedureJudiciaire.id=p.id ");
			hql.append(" and p.refRecours.id=r.id");
			hql.append(" and r.refSinistre.id=s.id");
			hql.append(" and s.numeroSinistre like'%")
					.append(numSin.replaceAll(" ", "")).append("%' ");
			Query query = getSession().createQuery(hql.toString());
			List<AudienceJugement> audiance = query.list();
			
			if (audiance != null && audiance.size() > 0) {
				for (AudienceJugement audienceJugement : audiance) {
					if (audienceJugement.getRefDecision()!= null
							&& "1".equals(audienceJugement.getRefDecision()
									.getCode())) {
						decisionCP = true;
						break;
					}
				}
			}
		} catch (Exception e) {
			logger.error(EXP_RECHERCHE_SINISTRE, e);
			throw new FonctionnelleException(EXP_RECHERCHE_SINISTRE, e);
		}
		return decisionCP;
	}
	
	public String getNumGraveById(Sinistre sinistre) {
		
		StringBuffer query = new StringBuffer(
				"select numeroGrave from Reglement reglement  ");
		query.append(" where 1=1 ");
		query.append(" and numeroGrave='").append(sinistre.getNumeroGrave()).append("' ");
		return query.toString();
	}
	
	/*public Sinistre getSinistreAtById(Sinistre s)
			throws FonctionnelleException, PersistenceException {
		Session sessionH = (Session) dao.getSession();

		if (s == null) {
			throw new FonctionnelleException("Sinistre Obligatoire");
		}
		StringBuffer sql = new StringBuffer("from Sinistre where id ='"
				+ String.valueOf(s.getId()) + "')");
		Query q = sessionH.createQuery(sql.toString());
		return (Sinistre) q.list().get(0);
	}*/
	
	public Sinistre getSinistreAtById(String  id)
			throws FonctionnelleException, PersistenceException {
		Sinistre sinistre;

		String sql = "from Sinistre where id ='" + String.valueOf(id)
				+ "' ";
		Query q = getSession().createQuery(sql.toString());
		sinistre = (Sinistre) q.uniqueResult();
		return sinistre;
	}

	public void supprimerSinAnterieur(Sinistre sindb, Sinistre sinistre) {
		// TODO Auto-generated method stub
		List<SinAnterieurVictime> listSinAnts = sindb.getRefVictime().getListSinistreAnterieur();
		List<SinAnterieurVictime> listSinAntsNouv = sinistre.getRefVictime().getListSinistreAnterieur();
		List<Long> listIds = new ArrayList<Long>();
		if(listSinAntsNouv != null) {
		for(SinAnterieurVictime ss : listSinAntsNouv){
			listIds.add(ss.getId());
			if(ss.getId() == Long.valueOf(0)) {
				try {						
					dao.createObject(ss);
			} catch (PersistenceException e) {
                    logger.error(
                            EXP_SUPPRESSION_AYANT_DROIT, e);
			}
			}
		}
	}
		if(listSinAnts != null) {
		for(SinAnterieurVictime ss : listSinAnts){
			if(!(listIds.contains(ss.getId()))) {
				try {
					dao.delete(ss);
				} catch (PersistenceException e) {
                        logger.error(
                                EXP_SUPPRESSION_AYANT_DROIT, e);
				}
			}
		}
		}
	}
	
	
}
