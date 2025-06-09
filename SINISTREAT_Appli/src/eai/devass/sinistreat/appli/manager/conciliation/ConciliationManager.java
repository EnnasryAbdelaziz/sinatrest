package eai.devass.sinistreat.appli.manager.conciliation;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.gsr.appli.manager.metier.transverse.TransverseManager;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.manager.ParametrageManager;
import eai.devass.sinistreat.appli.manager.fichemedicale.CertificatMedicalManager;
import eai.devass.sinistreat.appli.manager.sinistre.SinistreManager;
import eai.devass.sinistreat.appli.modele.metier.conciliation.AyantDroitOffre;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Conciliation;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Convocation;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Delegation;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Offre;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Pieces;
import eai.devass.sinistreat.appli.modele.metier.conciliation.RelanceConciliation;
import eai.devass.sinistreat.appli.modele.metier.conciliation.SinAnterieurOffre;
import eai.devass.sinistreat.appli.modele.metier.conciliation.SinEditionPV;
import eai.devass.sinistreat.appli.modele.metier.conciliation.SinEtatConciliation;
import eai.devass.sinistreat.appli.modele.metier.conciliation.SinMotifOffre;
import eai.devass.sinistreat.appli.modele.metier.conciliation.SinResultatOffre;
import eai.devass.sinistreat.appli.modele.metier.conciliation.User;
import eai.devass.sinistreat.appli.modele.metier.conciliation.UserDelegation;
import eai.devass.sinistreat.appli.modele.metier.fichemedicale.CertificatMedical;
import eai.devass.sinistreat.appli.modele.metier.instruction.PieceAt;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.parametrage.CanalConciliation;
import eai.devass.sinistreat.appli.modele.parametrage.CoefficientAge;
import eai.devass.sinistreat.appli.modele.parametrage.DestinataireRelance;
import eai.devass.sinistreat.appli.modele.parametrage.EtatConciliation;
import eai.devass.sinistreat.appli.modele.parametrage.GestionnaireRelance;
import eai.devass.sinistreat.appli.modele.parametrage.MotifConvocation;
import eai.devass.sinistreat.appli.modele.parametrage.MotifOffre;
import eai.devass.sinistreat.appli.modele.parametrage.OrigineConciliation;
import eai.devass.sinistreat.appli.modele.parametrage.RelanceConciliationPiece;
import eai.devass.sinistreat.appli.modele.parametrage.ResultatOffre;
import eai.devass.sinistreat.appli.modele.parametrage.TypePieceConciliation;
import eai.devass.sinistreat.appli.modele.parametrage.TypeProcedure;
import eai.devass.sinistreat.appli.modele.parametrage.Ville;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.utils.entites.IDate;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.utils.exception.IMessageInfo;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.AyantDroitOffreVO;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.ConciliationVO;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.OffreVO;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;
import ma.co.omnidata.framework.services.dao.PersistenceException;
import ma.co.omnidata.framework.services.entites.EntiteManagerAbst;
import ma.co.omnidata.framework.utile.DateUtile;

public class ConciliationManager extends EntiteManagerAbst implements
		IConstantes, IMessageException, IMessageInfo {

	private static final String ETAT_CONCILIATION_ENCOURS = "1";
	IPersistenceService dao = (IPersistenceService) ServicesFactory
			.getService(IPersistenceService.class);
	SimpleDateFormat dateFormat = new SimpleDateFormat(IDate.FORMAT_SIMPLE);
	SimpleDateFormat dateFormat_ = new SimpleDateFormat(IDate.FORMAT_IHM);
	ParametrageManager parametrageManager = (ParametrageManager) ServicesFactory
			.getService(ParametrageManager.class);
	SinistreManager sinistreManager = (SinistreManager) ServicesFactory
			.getService(SinistreManager.class);
	CertificatMedicalManager certificatMedicalManager = (CertificatMedicalManager) ServicesFactory
			.getService(CertificatMedicalManager.class);
	TransverseManager transverseManager = (TransverseManager) ServicesFactory
			.getService(TransverseManager.class);
	private String ETAT_CONCILIATION_TERMINER = "2";
	private Long MOTIF_MOUVEMENT_CONCILIATION = 17L;
	private final Logger logger = Logger.getLogger("loggerAT");
	private Session getSession() throws PersistenceException {
		return (Session) dao.getSession();
	}
	GestionnaireRelance gest = new GestionnaireRelance("1", null, null);
	//relanceConciliation.setRefgestionnaire(new GestionnaireRelance("1", null, null));
    private static final Logger LOGGER = Logger.getLogger("loggerSINAT");

	public Conciliation createConciliation(Conciliation conciliation)
			throws HibernateException, PersistenceException {

		return (Conciliation) getSession().save(conciliation);

	}

	public Conciliation recupererConciliation(Sinistre s)
			throws HibernateException, PersistenceException {
		Conciliation c = new Conciliation(s, getSession());
		return c;
	}

	// RG : le changement de l'etat n'est autoriser manuellemnet que depuis
	// l'etat "En cours" vers l'etat "Sans suite" et vice versa
	public List<EtatConciliation> recupererListEtatConciliation()
			throws HibernateException, PersistenceException {
		return getSession().createQuery(
				"from EtatConciliation where code in (1,3) order by code")
				.list();

	}

	public List<Pieces> recupererListPiece(Sinistre s)
			throws HibernateException, PersistenceException {
		long typePiece = 0;
		if (s.getRefVictime() != null && s.getRefVictime().getDeces() != null) {
			if (!s.getRefVictime().getDeces()) {
				typePiece = TypePieceConciliation.TypePieceConciliationId.VICTIME
						.getId();
			} else {
				typePiece = TypePieceConciliation.TypePieceConciliationId.DECES
						.getId();
			}
		}

		Session sessionH = (Session) dao.getSession();
		StringBuffer sql = new StringBuffer("from Piece where CODE in ("
				+ "select refPiece.code from LiaisonTypePiece  ");
		sql.append(" where refTypePieceConciliation.id ='"
				+ String.valueOf(typePiece) + "')");
		Query q = sessionH.createQuery(sql.toString());
		return q.list();
	}

	public List<ResultatOffre> recupererListEtatResultat()
			throws PersistenceException {

		Session sessionH = (Session) dao.getSession();
		String sql = "from ResultatOffre";
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	public List<MotifOffre> getListMotifOffre() throws HibernateException,
			PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = "from MotifOffre";
		Query q = sessionH.createQuery(sql);
		return q.list();
	}
	
	//Evol lot1 RBA 01/12/2021
	public List<TypeProcedure> getListTypeProcedure() throws HibernateException,
		PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = "from TypeProcedure";
		Query q = sessionH.createQuery(sql);
		return q.list();
	}
	
	public void creerConciliation(Conciliation conciliation)
			throws PersistenceException, ParseException {
		String coefAgeConvert;
		conciliation.setDateCreation(new Date());
		// Liste des pièces
		if (conciliation.getListPieces() != null) {
			for (int i = 0; i < conciliation.getListPieces().size(); i++) {
				Pieces pieces = conciliation.getListPieces().get(i);
				pieces.setDateCreation(new Date());
				pieces.setRefConciliation(conciliation);
			}
		}
		// liste des convocations
		if (conciliation.getListConvocation() != null) {
			for (int i = 0; i < conciliation.getListConvocation().size(); i++) {
				Convocation conv = conciliation.getListConvocation().get(i);
				conv.setDateCreation(new Date());
				conv.setRefConciliation(conciliation);
			}
		}
		// liste des offres
		if (conciliation.getListOffre() != null) {
			for (int i = 0; i < conciliation.getListOffre().size(); i++) {
				Offre offre = conciliation.getListOffre().get(i);
				offre.setDateCreation(new Date());
				offre.setDateMotif(new Date());
				offre.setRefConciliation(conciliation);
				offre.setEtatOffreConciliation(IConstantes.ETAT_OFFRE_ORGINALE);
				//limiter les chiffres de coef d'age apres la virgule
				if(offre.getCoefficientAge()!=null) {
				coefAgeConvert = CommonUtils.getInstance().limiterchiffreVirgule(offre.getCoefficientAge());
				//coefAgeConvert = "12";
				offre.setCoefficientAge(Double.valueOf(coefAgeConvert));
				}
				List<AyantDroitOffre> listAyd = offre.getListAyantDroit();
				if (listAyd != null) {
					for (AyantDroitOffre ayd : listAyd) {
						ayd.setRefOffre(offre);
						dao.createObject(ayd);
					}
				}
				List<SinAnterieurOffre> listSinAnt = offre.getListSinistreAnterieur();
				if (listSinAnt != null) {
					for (SinAnterieurOffre sin : listSinAnt) {
						sin.setRefOffre(offre);
						dao.createObject(sin);
					}
				}
			}
		}
		dao.createObject(conciliation);

	}

	public void addEtatConciliation(Conciliation conciliation)
			throws PersistenceException {
		SinEtatConciliation etatConci = new SinEtatConciliation();
		EtatConciliation etat = new EtatConciliation();
		etat.setCode(conciliation.getEtat());
		etatConci.setDateEtat(new Date());
		etatConci.setRefConciliation(conciliation);
		etatConci.setRefEtatConciliation(etat);
		etatConci.setUserCreateur(conciliation.getUserCreateur());
		dao.createObject(etatConci);
	}

	public void addEtatMotifOffre(Conciliation conciliation)
			throws PersistenceException {
		SinMotifOffre sinMotif = new SinMotifOffre();
		List<Offre> listOffre = conciliation.getListOffre();
		Offre off = conciliation.getOffre();
		if (listOffre != null) {
			for (int i = 0; i < listOffre.size(); i++) {
				Offre of = (Offre) listOffre.get(i);
				sinMotif.setRefOffre(of);
				sinMotif.setRefMotifOffre(of.getRefMotifOffre());
				sinMotif.setDateEtat(new Date());
				sinMotif.setUserCreateur(conciliation.getCreateurOffre());
				sinMotif.setCodeSas(conciliation.getUserCreateur());
				dao.createObject(sinMotif);
			}
		}
	}

	public Boolean isConciliationEncours(Conciliation conciliation) {
		boolean existe = false;
		if (conciliation.getEtat().equals(ETAT_CONCILIATION_ENCOURS)) {
			existe = true;
		}
		return existe;
	}

	/**
	 * Recuperer list Conciliation par ID Sinistre
	 * 
	 * @param s
	 * @return
	 * @throws FonctionnelleException
	 * @throws PersistenceException
	 */
	public List<Conciliation> getConciliationByIdSinistre(Sinistre s)
			throws FonctionnelleException, PersistenceException {

		Session sessionH = (Session) dao.getSession();
		if (s == null) {
			throw new FonctionnelleException(EXP_SINISTRE_INEXISTANT);
		}
		StringBuffer sql = new StringBuffer(
				"from Conciliation where refSinistre.id ='"
						+ String.valueOf(s.getId()) + "')");
		Query q = sessionH.createQuery(sql.toString());
		return q.list();
	}
	
	//ajout methode listeconciliation ordonnée
	public List<Conciliation> getConciliationOrdByIdSinistre(Sinistre s)
			throws FonctionnelleException, PersistenceException {

		Session sessionH = (Session) dao.getSession();
		if (s == null) {
			throw new FonctionnelleException(EXP_SINISTRE_INEXISTANT);
		}
		StringBuffer sql = new StringBuffer(
				"from Conciliation where refSinistre.id ='"
						+ String.valueOf(s.getId()) + "' order by id desc)");
		Query q = sessionH.createQuery(sql.toString());
		return q.list();
	}

	/**
	 * Recuperer IPP valide (IPP certificat de contre Visite, si non IPP
	 * certificat Guerison)
	 * 
	 * @param sinistre
	 * @return
	 * @throws FonctionnelleException
	 * @throws PersistenceException 
	 */
	public Offre getIppValide(Sinistre sinistre) throws FonctionnelleException {
		Double ippValide = Double.valueOf(0);
		Date dateCalcul = null;
		Offre of = new Offre();
		List<CertificatMedical> listeCertificatValide = certificatMedicalManager
				.getValideCertificatBySin(sinistre.getId());
		if (listeCertificatValide != null) {
			int i = 0;
			boolean contreVisiteTrouve = false;
			boolean guerisonTrouve = false;
			CertificatMedical certifvalidipp = null;
			while (i < listeCertificatValide.size() && !contreVisiteTrouve) {
				if (listeCertificatValide.get(i).getRefType() != null
						&& !StringUtils.isEmpty(listeCertificatValide.get(i)
								.getRefType().getCode())) {
					String typeCertificatActuel = listeCertificatValide.get(i)
							.getRefType().getCode();
					if (typeCertificatActuel
							.equals(TYPE_CERTIFICAT_CONTREVISITE)) {
						certifvalidipp = listeCertificatValide.get(i);
						contreVisiteTrouve = true;
					} else if (typeCertificatActuel
							.equals(TYPE_CERTIFICAT_GUERISON)) {
						certifvalidipp = listeCertificatValide.get(i);
						dateCalcul = certifvalidipp.getDateGuerison();
						guerisonTrouve = true;
					}
				}
				i++;
			}
			if (certifvalidipp != null) {
				ippValide = certifvalidipp.getIPP();
			} else {
				throw new FonctionnelleException(AUCUN_IPP_CERTIFICAT_VALIDE);
			}
		}
		of.setIpp(ippValide);
		of.setDateCalcul(dateCalcul);
		return of;
	}

	/**
	 * Calcul Capital Rachat pour le cas IPP < 10
	 * 
	 * @param Offre
	 * @return Offre (avec capital rachat)
	 * @throws ParseException
	 * @throws FonctionnelleException
	 */
	public OffreVO calculCapitalRachat(OffreVO offre) throws ParseException,
			FonctionnelleException {
		Double ipp;
		Date dateAccident = null;
		/* RG */
		if (offre.getAutreTauxIpp() != null) {
			ipp = (offre.getAutreTauxIpp() != null) ? Double.valueOf(offre
					.getAutreTauxIpp()) / 100 : 0D;
		} else {
			ipp = (offre.getIpp() != null) ? Double.valueOf(offre.getIpp()) / 100
					: 0D;
		}
		Double salaireUtile = Double.valueOf(offre.getSalaireUtile());

		CoefficientAge coefficient;
		if (offre.getDateAccident() != null) {
			dateAccident = dateFormat.parse(offre.getDateAccident());
		} else {
			throw new FonctionnelleException("date accident obligatoire");
		}
		try {
			coefficient = getCoefAge(offre.getAge(), ipp);
			Double coefficientAge = coefficient.getCoefficient();
			Double ippReduit = getIppReduit(ipp, dateAccident);

			Double rente = salaireUtile * ippReduit;
			rente = new BigDecimal(rente).setScale(2,
					BigDecimal.ROUND_HALF_EVEN).doubleValue();
			Double capitalCalcule = rente * coefficientAge;
			capitalCalcule = new BigDecimal(capitalCalcule).setScale(2,
					BigDecimal.ROUND_HALF_EVEN).doubleValue();

			offre.setCapitalRachat(Double.toString(capitalCalcule));
			//SEL: 06052019 Début Evolution Conciliation
			offre.setCoefficientAge(coefficientAge.toString());
			//SEL: Fin Evolution Conciliation 
		} catch (Exception e) {
			throw new FonctionnelleException(
					"Une erreur est survenu lors du calcul du capital rachat : "
							+ e);
		}

		return offre;
	}

	/**
	 * Calcul Coef d'age pour IPP < 10
	 * 
	 * @param age
	 * @return
	 * @throws Exception
	 */
	public CoefficientAge getCoefAge(String age, Double ipp) throws Exception {
		CoefficientAge cofAge = null;
		// IPP < 10 pour une date d'accident superieur ou egal au 22012015
		String code = "7";
		code = "7";
		if (ipp < 0.1) {
			code = "1";
		} else {
			code = "7";
		}
		cofAge = parametrageManager.getCoefParType(Integer.valueOf(age), code);
		return cofAge;
	}

	public Double getIppReduit(double ipp, Date dateCalcul)
			throws FonctionnelleException {
		double ippReduit = 0;
		Date dateCompare = null;
		Date dateCompare1 = null;

		try {
			dateCompare = new SimpleDateFormat("dd/MM/yyyy")
					.parse("19/11/2002");
			dateCompare1 = new SimpleDateFormat("dd/MM/yyyy")
					.parse("18/06/2003");

			if (dateCalcul == null || dateCompare.compareTo(dateCalcul) >= 0) {
				if (ipp > 50) {
					ippReduit = ((ipp * 1.5) - 50);
				} else {
					ippReduit = ipp / 2;
				}
			} else {
				if (dateCompare.compareTo(dateCalcul) <= 0
						&& dateCompare1.compareTo(dateCalcul) >= 0) {
					ippReduit = ipp;
				} else {
					if (dateCompare.compareTo(dateCalcul) < 0) {
						if (ipp < 30) {
							ippReduit = ipp / 2;
						} else {
							if (ipp >= 30 && ipp <= 50) {
								ippReduit = ((ipp * 1.5) - 30);
							} else {
								if (ipp > 50) {
									ippReduit = ipp - 5;
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
            LOGGER.error("erreur lors du calcul ipp reduit", e);
			throw new FonctionnelleException("erreur lors du calcul ipp reduit");
		}
		return ippReduit;
	}

	/**
	 * Calcul Rente Trimestriel
	 * 
	 * @param offre
	 * @return
	 * @throws FonctionnelleException
	 */
	public OffreVO calculRenteAnnuel(OffreVO offre)
			throws FonctionnelleException {

		Date dateAccident = null;
		Double ipp = 0D;
		double ippReduit = 0;
		String su;

		try {
			if (offre.getDateAccident() != null) {
				dateAccident = dateFormat.parse(offre.getDateAccident());
			} else {
				throw new FonctionnelleException("date accident obligatoire");
			}

			if (offre.getAutreTauxIpp() != null) {
				ipp = (offre.getAutreTauxIpp() != null) ? Double.valueOf(offre
						.getAutreTauxIpp()) : 0D;
			} else {
				ipp = (offre.getIpp() != null) ? Double.valueOf(offre.getIpp())
						: 0D;
			}

			ippReduit = getIppReduit(ipp, dateAccident);
			// Fin Calcul IPP réduit

			// Début Calcul Rente trimestrielle
			su = offre.getSalaireUtile();
            Double rt = Double.valueOf(su) * (ippReduit / 100);
			rt = new BigDecimal(rt).setScale(2, BigDecimal.ROUND_HALF_EVEN)
					.doubleValue();
			offre.setMontantRente(rt.toString());
			// Fin Calcul Rente trimestrielle

		} catch (Exception e) {
            LOGGER.error("erreur lors du calcul de la rente trimestriel", e);
			throw new FonctionnelleException(
					"erreur lors du calcul de la rente trimestriel");
		}
		return offre;
	}

	/**
	 * Calcul Montant Reserve
	 * 
	 * @param offre
	 * @return
	 * @throws FonctionnelleException
	 */
	public OffreVO calculMontantReserve(OffreVO offre)
			throws FonctionnelleException {
		CoefficientAge coefficient;
		try {
			Double ipp;
			/* RG */
			if (offre.getAutreTauxIpp() != null) {
				ipp = (offre.getAutreTauxIpp() != null) ? Double.valueOf(offre
						.getAutreTauxIpp()) / 100 : 0D;
			} else {
				ipp = (offre.getIpp() != null) ? Double.valueOf(offre.getIpp()) / 100
						: 0D;
			}
			coefficient = getCoefAge(offre.getAge(), ipp);
			Double coefAge = coefficient.getCoefficient();
			Double rente = Double.valueOf(offre.getMontantRente());
			Double reserve = rente * coefAge;
			reserve = new BigDecimal(reserve).setScale(2,
					BigDecimal.ROUND_HALF_EVEN).doubleValue();

			offre.setMontantReserve(reserve.toString());
			//SEL: 06052019 Début Evolution Conciliation
			offre.setCoefficientAge(coefAge.toString());
			//SEL: Fin Evolution Conciliation 
		} catch (Exception e) {
            LOGGER.error("erreur lors du calcul de la reserve", e);
			throw new FonctionnelleException(
					"erreur lors du calcul de la reserve");
		}

		return offre;
	}

	/**
	 * Calcul Montant Arrerage avant constitution
	 * 
	 * @param offre
	 * @return
	 * @throws FonctionnelleException
	 */
	public OffreVO calculMontantArrerageAvConsti(OffreVO offre)
			throws FonctionnelleException {
		String su;
		double ipp = 0;
		double ippReduit;

		try {
			if (offre != null && offre.getDateSignaturePv() != null) {
				Date dateAccident = dateFormat.parse(offre.getDateAccident());
				Date dateSignaturePV = dateFormat.parse(offre
						.getDateSignaturePv());
				Date dateTrim = getEcheanceTrimestre(dateSignaturePV);
				String dateDepartRente = offre.getDateDepartRente();
				Date depar = dateFormat.parse(dateDepartRente);

				if (offre.getAutreTauxIpp() != null) {
					ipp = (offre.getAutreTauxIpp() != null) ? Double
							.valueOf(offre.getAutreTauxIpp()) : 0D;
				} else {
					ipp = (offre.getIpp() != null) ? Double.valueOf(offre
							.getIpp()) : 0D;
				}
				if (ipp < 10) {
					offre.setMontantArrerageAVC("0");
				} else {

					ippReduit = getIppReduit(ipp, dateAccident);
					su = offre.getSalaireUtile();

					// Nbr jour entre date depare et date trimestre
					long nbrJour = dateTrim.getTime() - depar.getTime();
					if (nbrJour > 0) {
						nbrJour = nbrJour / (1000 * 60 * 60 * 24) + 1;
					} else {
						nbrJour = 0;
					}

					Double montantArrage = Double.valueOf(su)
							* (ippReduit / 100) * (nbrJour - 1) / 360;
					montantArrage = new BigDecimal(montantArrage).setScale(2,
							BigDecimal.ROUND_HALF_EVEN).doubleValue();

					offre.setMontantArrerageAVC(montantArrage.toString());
				}
			}
		} catch (Exception e) {
            LOGGER.error("erreur lors du calcul d'arrerage Avant Consitution",
					e);
			throw new FonctionnelleException(
					"erreur lors du calcul d'arrerage Avant Consitution");
		}

		return offre;
	}

	/**
	 * Recuperer conciliation par ID
	 * 
	 * @param c
	 * @return
	 * @throws FonctionnelleException
	 * @throws PersistenceException
	 */
	public Conciliation getConciliationById(Conciliation c)
			throws FonctionnelleException, PersistenceException {
		Session sessionH = (Session) dao.getSession();

		if (c == null) {
			throw new FonctionnelleException("Conciliation Obligatoire");
		}
		StringBuffer sql = new StringBuffer("from Conciliation where id ='"
				+ String.valueOf(c.getId()) + "')");
		Query q = sessionH.createQuery(sql.toString());
		return (Conciliation) q.list().get(0);
	}

	/**
	 * Recuperer offre par id Conciliation
	 * 
	 * @param conciliation
	 * @return
	 * @throws PersistenceException
	 * @throws FonctionnelleException
	 */
	public List<Offre> getOffresByIdConciliation(ConciliationVO c)
			throws PersistenceException, FonctionnelleException {
		Session sessionH = (Session) dao.getSession();
		List<Offre> list = null;
		if (c == null) {
			throw new FonctionnelleException("Conciliation Obligatoire");
		}
		// pour évtiter la recherche des offres au moment de la creation
		if (c.getId() != 0) {
			StringBuffer sql = new StringBuffer(
					"from Offre where refConciliation.id ='"
							+ String.valueOf(c.getId()) + "')");
			Query q = sessionH.createQuery(sql.toString());
			list = (List<Offre>) q.list();
		}

		return list;
	}
	
	public List<Offre> getOffresByIdConciliationOrd(Conciliation c)
			throws PersistenceException, FonctionnelleException {
		Session sessionH = (Session) dao.getSession();
		List<Offre> list = null;
		if (c == null) {
			throw new FonctionnelleException("Conciliation Obligatoire");
		}
		// pour évtiter la recherche des offres au moment de la creation
		if (c.getId() != 0) {
			StringBuffer sql = new StringBuffer(
					"from Offre where refConciliation.id ='"
							+ String.valueOf(c.getId()) + "'  order by dateOffre desc");	
			
			Query q = sessionH.createQuery(sql.toString());
			list = (List<Offre>) q.list();
		}

		return list;
	}

	
	/**
	 * Recuperer offre par id Offre
	 * 
	 * @param offre
	 * @return
	 * @throws PersistenceException
	 * @throws FonctionnelleException
	 */
	public Offre getOffresById(String idOffre)
			throws PersistenceException, FonctionnelleException {
		Session sessionH = (Session) dao.getSession();
		Offre o = null;
		if (idOffre == null) {
			throw new FonctionnelleException("ID Offre Obligatoire");
		}
		StringBuffer sql = new StringBuffer("from Offre where id ='"
				+ String.valueOf(idOffre) + "')");
		Query q = sessionH.createQuery(sql.toString());
		o = (Offre) q.uniqueResult();

		return o;
	}
	public SinResultatOffre getResultatByIdOffre(Offre o)
			throws PersistenceException, FonctionnelleException {
		Session sessionH = (Session) dao.getSession();
		if (o == null) {
			throw new FonctionnelleException("Offre Obligatoire");
		}
		StringBuffer sql = new StringBuffer(
				"from SinResultatOffre where refOffre.id ='"
						+ String.valueOf(o.getId()) + "')");
		Query q = sessionH.createQuery(sql.toString());
		SinResultatOffre resultat = (SinResultatOffre) q.list().get(0);
		return resultat;
	}

	public List<AyantDroitOffreVO> calculRenteTrimestrielListAyD(OffreVO offre)
			throws FonctionnelleException {

		Double su = 0D;
		Double ipp = 0D;
		double ippReduit = 0;
		List<AyantDroitOffreVO> listAD = offre.getListAyantDroit();
		if (listAD == null || listAD.isEmpty()) {
			return null;
		}

        for (AyantDroitOffreVO ayD : listAD) {
			try {
                if (ayD.getChoix().equals("true")) {
                    ipp = ayD.getTauxRente();
					Date dateAccident = dateFormat.parse(offre
							.getDateAccident());
					ippReduit = getIppReduit(ipp, dateAccident);
					// Début Calcul Rente trimestrielle
					su = Double.valueOf(offre.getSalaireUtile());
					Double rt = (su * (ippReduit / 100)) / 4;
					rt = new BigDecimal(rt).setScale(2,
							BigDecimal.ROUND_HALF_EVEN).doubleValue();
                    ayD.setMontantRente(rt);
					// Fin Calcul Rente trimestrielle
				}
			} catch (Exception e) {
                LOGGER.error(IMessageException.EXP_STAND_MESS, e);
				throw new FonctionnelleException(
						IMessageException.EXP_STAND_MESS);
			}
		}
		return listAD;
	}

	public List<AyantDroitOffreVO> calculMontantReserveListAyD(
			List<AyantDroitOffreVO> listAD) throws FonctionnelleException {
		for (int k = 0; k < listAD.size(); k++) {
			try {
				AyantDroitOffreVO ayD = listAD.get(k);
				if (ayD.getChoix().equals("true")) {
					Double coefAge = Double.valueOf(ayD.getCoef());
					Double rente = ayD.getMontantRente();
					int v = 4;
					Double reserve = rente * v * coefAge;
					reserve = new BigDecimal(reserve).setScale(2,
							BigDecimal.ROUND_HALF_EVEN).doubleValue();
					ayD.setReserveAyantDroit(reserve);
				}
			} catch (Exception e) {
                LOGGER.error(
						"une erreur est survenu lors du calcul de la reserve Ayant Droit",
						e);
				throw new FonctionnelleException(
						"une erreur est survenu lors du calcul de la reserve Ayant Droit");
			}

		}
		return listAD;
	}

	public List<AyantDroitOffreVO> calculMontantArrerageAvConstiListAyD(
			OffreVO offre) throws FonctionnelleException {
		String dateDepartRente;
		Double ipp = 0D;
		double ippReduit = 0;
		Double su = Double.valueOf(offre.getSalaireUtile());
		dateDepartRente = offre.getDateDepartRente();

		List<AyantDroitOffreVO> listAD = offre.getListAyantDroit();

		for (int k = 0; k < listAD.size(); k++) {
			AyantDroitOffreVO ayD = listAD.get(k);
			try {
				if (ayD.getChoix().equals("true")
						&& ayD.getDateSignaturePv() != null) {
					Date dateAccident = dateFormat.parse(offre
							.getDateAccident());
					ipp = ayD.getTauxRente();
					ippReduit = getIppReduit(ipp, dateAccident);

					Date dateSignaturePV = dateFormat_.parse(ayD
							.getDateSignaturePv());
					Date dateTrim = getEcheanceTrimestre(dateSignaturePV);
					Date depar = dateFormat.parse(dateDepartRente);

					// Nbr jour entre date depare et date trimestre
					long nbrJour = dateTrim.getTime() - depar.getTime();
					if (nbrJour > 0) {
						nbrJour = nbrJour / (1000 * 60 * 60 * 24) + 1;
					} else {
						nbrJour = 0;
					}

					Double renteAyD = ayD.getMontantRente();
					Double arrerage = (renteAyD * (nbrJour - 1)) / 360;

					arrerage = new BigDecimal(arrerage).setScale(2,
							BigDecimal.ROUND_HALF_EVEN).doubleValue();
					ayD.setMontantArrerageAvCons(arrerage);
				}
			} catch (Exception e) {
                LOGGER.error(IMessageException.EXP_STAND_MESS, e);
				throw new FonctionnelleException(
						IMessageException.EXP_STAND_MESS);
			}

		}
		return listAD;
	}

	/**
	 * Recuperer la liste des dossiers conciliation à valider
	 * 
	 * @param
	 * @return liste des dossiers conciliation à valider
	 * @throws PersistenceException
	 * @throws FonctionnelleException
	 */
	public List<Conciliation> getConciliationAValider(PagerVO pagerVO)
			throws FonctionnelleException, PersistenceException {
		Session sessionH = (Session) dao.getSession();
		List list = new ArrayList();

		StringBuffer sql = new StringBuffer("from Conciliation c ")
				.append("join c.listOffre  ofr ")
				.append("join ofr.listSinMotifOffre mofr ")
				.append(" where c.etat = '1' ")
				.append(" and ofr.refMotifOffre = '1' ")
				.append(" and mofr.id = (select max(t.id) from SinMotifOffre t where t.refMotifOffre = '1' and t.refOffre = ofr))");

		Query q = sessionH.createQuery(sql.toString()).setMaxResults(20);
		List listResult = new ArrayList();
		if (pagerVO != null) {
			Integer numpage = 0;
			if (Integer.valueOf(pagerVO.getNumPage()).intValue() > 0) {
				numpage = Integer.valueOf(pagerVO.getNumPage()) - 1;
			}
			listResult= this.getPartCollectionByCondition(q, numpage,
					Integer.valueOf(pagerVO.getPageSize()));
		} else {
			listResult= q.list();
		}
		for (int i = 0; i < listResult.size(); i++) {

			Conciliation conciliation = (Conciliation) ((Object[]) listResult
					.get(i))[0];

			SinMotifOffre sinMotifOffre = (SinMotifOffre) ((Object[]) listResult
					.get(i))[2];

			List<SinMotifOffre> listSinMotifOffres = new ArrayList<SinMotifOffre>();
			listSinMotifOffres.add(sinMotifOffre);

			Offre offre = (Offre) ((Object[]) listResult.get(i))[1];

			List<Offre> listOffres = new ArrayList<Offre>();
			offre.setListSinMotifOffre(listSinMotifOffres);

			listOffres.add(offre);
			conciliation.setListOffre(listOffres);
			list.add(conciliation);

		}

		return list;
	}

	/**
	 * Mise à jour etat Offre: validation Offre
	 * 
	 * @param etatOffre
	 * @param Conciliation
	 * @throws PersistenceException
	 * @throws FonctionnelleException
	 */
	public void updateDossier(Conciliation conciliation, long etatOffre)
			throws PersistenceException {
		SinMotifOffre sinMotif = new SinMotifOffre();
		MotifOffre motifOffre = new MotifOffre();
		motifOffre.setId(etatOffre);
		List<Offre> listOffre = conciliation.getListOffre();
		if (listOffre != null) {
			for (int i = 0; i < listOffre.size(); i++) {
				Offre of = (Offre) listOffre.get(i);
				of = (Offre) getSession().load(Offre.class, of.getId());
				sinMotif.setUserCreateur(listOffre.get(i)
						.getListSinMotifOffre().get(0).getUserCreateur());
				sinMotif.setDateEtat(new Date());
				sinMotif.setRefOffre(of);
				sinMotif.setRefMotifOffre(motifOffre);
				of.setDateCreation(new Date());
				of.setRefMotifOffre(motifOffre);
				dao.createObject(sinMotif);
			}
		}
	}

	/**
	 * Modification Conciliation
	 * 
	 * @param conciliation
	 * @throws PersistenceException
	 */
	public void updateConciliation(Conciliation conciliation)
			throws PersistenceException {

		Conciliation concidb = null;
		String coefAgeConvert;
		try {
			if (conciliation == null) {
				throw new FonctionnelleException(
						"l'objet Conciliation est obligatoire");
			}
			if (conciliation.getId() == 0) {
				throw new FonctionnelleException(
						"l'identifiant de la Conciliation est obligatoire");
			}
			concidb = getConciliationById(conciliation);
			concidb.setDateModification(new Date());
			concidb.setRefOrigineConciliation(conciliation
					.getRefOrigineConciliation());
			concidb.setRefVilleAvocat(conciliation.getRefVilleAvocat());
			concidb.setNomAvocat(conciliation.getNomAvocat());
			concidb.setAdresseAvocat(conciliation.getAdresseAvocat());
			// Persister le changement d'etat et la date du changement d'etat
			if (concidb.getEtat().compareTo(conciliation.getEtat()) != 0) {
				concidb.setEtat(conciliation.getEtat());
				concidb.setDateEtat(new Date());
			}
			// Modifier les infos de la listes des pièces
			List<Pieces> listPieces = conciliation.getListPieces();
			if (listPieces != null) {
				for (int i = 0; i < listPieces.size(); i++) {
					Pieces pieces = listPieces.get(i);
					pieces.setDateModification(new Date());
					pieces.setRefConciliation(conciliation);
				}
				concidb.setListPieces(listPieces);
			}

			// Modifier les infos de la listes des convocation
			List<Convocation> listConvocation = conciliation
					.getListConvocation();
			if (listConvocation != null) {
				for (int i = 0; i < listConvocation.size(); i++) {
					Convocation conv = listConvocation.get(i);
					conv.setDateModification(new Date());
					conv.setRefConciliation(conciliation);
				}
				concidb.setListConvocation(listConvocation);
			}
//			for (Offre odb : concidb.getListOffre()){
//				odb.getListSinistreAnterieur().clear();
//			}
			// modifier la liste des offres
			List<Offre> listOffre = conciliation.getListOffre();
			if (listOffre != null) {
				for (int i = 0; i < listOffre.size(); i++) {
					Offre offre = conciliation.getListOffre().get(i);
					offre.setDateModification(new Date());
					offre.setRefConciliation(conciliation);
					List<AyantDroitOffre> listAyd = offre.getListAyantDroit();
					SinResultatOffre refResultatOffre = offre.getRefResultatOffre();
					if(conciliation.getRefOrigineConciliation().getCode().equals("3")) {
						offre.setEtatOffreConciliation(IConstantes.ETAT_OFFRE_DUPLICATA);
					}else  {
						offre.setEtatOffreConciliation(IConstantes.ETAT_OFFRE_ORGINALE);
					}
					//limiter les chiffres de coef d'age apres la virgule
					if(offre.getCoefficientAge()!=null) {
					coefAgeConvert = CommonUtils.getInstance().limiterchiffreVirgule(offre.getCoefficientAge());
					offre.setCoefficientAge(Double.valueOf(coefAgeConvert));
					}
					
					if (refResultatOffre != null
							&& refResultatOffre.getRefEtatResultat().getId() != null) {
						refResultatOffre.setDateResultat(new Date());
						dao.createObject(refResultatOffre);
					}
					if (listAyd != null) {
						for (AyantDroitOffre ayd : listAyd) {
							ayd.setRefOffre(offre);
						}
					}
					
					List<SinAnterieurOffre> listSinAnt = offre.getListSinistreAnterieur();
					if (listSinAnt != null) {
						for (SinAnterieurOffre sin : listSinAnt) {
							sin.setRefOffre(offre);
						}
					}else {
						offre.setListSinistreAnterieur(new ArrayList<SinAnterieurOffre>());
					}
					// Pour les offres nouvellement cerer
					if (offre.getId() == 0) {
						SinMotifOffre sinMotif = new SinMotifOffre();
						sinMotif.setRefOffre(offre);
						sinMotif.setRefMotifOffre(offre.getRefMotifOffre());
						sinMotif.setDateEtat(new Date());
						sinMotif.setUserCreateur(conciliation.getUserCreateur());
						List<SinMotifOffre> listSinMotifOffre = new ArrayList<SinMotifOffre>();
						listSinMotifOffre.add(sinMotif);
						offre.setListSinMotifOffre(listSinMotifOffre);
					}
				}
				concidb.setListOffre(listOffre);
			}

			dao.updateObject(concidb);

		} catch (Exception e) {
            LOGGER.error(e);
		}
	}

	/**
	 * Mise à jour sinistre aprés accord du résultat
	 * 
	 * @param offre
	 * @param conciliation
	 * @throws FonctionnelleException
	 */
	public void updateSinistre(Offre offre, Conciliation conciliation)
			throws FonctionnelleException {

		Sinistre sin = conciliation.getRefSinistre();
		Sinistre sinistreBd;
		if (sin == null) {
			throw new FonctionnelleException(
					"les informations sinisitres sont obligatoires");
		}
		try {
			Double mnt = 0D;
			sinistreBd = (Sinistre) getSession().load(Sinistre.class,
					sin.getId());
			// mise à jour sinistre
			sinistreBd.setIpp(offre.getIppOffre());
			sinistreBd.setUserModificateur(conciliation.getUserCreateur());
			sinistreBd.setProthese(offre.getProtheseRegle());
			sinistreBd.setPrixProthese(offre.getMontantAReglerProthese());
			// Evo Dans le cas de décès, l’impact de la réserve grave doit se
			// faire uniquement par le montant d’arrérage calculé pour chaque
			// ayant droit
			if (sinistreBd.getRefVictime() != null) {
				if (sinistreBd.getRefVictime().getDeces().equals(false)) {
					sinistreBd.setReserveGrave(offre.getReserveOffre());
				} else {
					sinistreBd.setReserveGrave(offre.getReserveOffre());
				}
			}

			// creation mouvement conciliation
			sinistreManager.creerMouvement(sinistreBd, null,
					MOTIF_MOUVEMENT_CONCILIATION);
		} catch (Exception e) {
            LOGGER.error(
					"une erreur est survenue lors de la mise à jour du dossier sinistre",
					e);
		}

	}

	public void cloturerConciliation(Conciliation conciliation) {

		Conciliation concidb = null;

		try {
			if (conciliation == null) {
				throw new FonctionnelleException(
						"l'objet Conciliation est obligatoire");
			}
			if (conciliation.getId() == 0) {
				throw new FonctionnelleException(
						"l'identifiant de la Conciliation est obligatoire");
			}
			concidb = getConciliationById(conciliation);
			concidb.setDateModification(new Date());
			// Persister le changement d'etat et la date du changement d'etat
			concidb.setEtat(ETAT_CONCILIATION_TERMINER);
			concidb.setDateEtat(new Date());
			// Persister le mouvement de changement d'etat
			SinEtatConciliation etatConci = new SinEtatConciliation();
			EtatConciliation etat = new EtatConciliation();
			etat.setCode(ETAT_CONCILIATION_TERMINER);
			etatConci.setDateEtat(new Date());
			etatConci.setRefConciliation(conciliation);
			etatConci.setRefEtatConciliation(etat);
			etatConci.setUserCreateur(conciliation.getUserCreateur());
			dao.createObject(etatConci);

		} catch (Exception e) {
            LOGGER.error(
					"une erreur est survenue lors de la clôture de la conciliation",
					e);
		}
	}

	/**
	 * Recuperer la liste des dossiers conciliation à valider avec
	 * ordonnancement
	 * 
	 * @param
	 * @return liste des dossiers conciliation à valider
	 * @throws FonctionnelleException
	 * @throws PersistenceException
	 */
	public List<Conciliation> getConciliationSupAValider(String codeSas, PagerVO pagerVO)
			throws FonctionnelleException, PersistenceException {
		Session sessionH = (Session) dao.getSession();
		List list = new ArrayList();
		List listDelegation = new ArrayList();

		//
		if (codeSas == null) {
			throw new FonctionnelleException(
					"Le code SAS utilisateur est obligatoire !!");
		}
		StringBuffer sqlUser = new StringBuffer("from User where codeSas ='"
				+ codeSas + "')");
		Query qUser = sessionH.createQuery(sqlUser.toString());
		User resultatU = (User) qUser.list().get(0);

		StringBuffer sqlDelegation = new StringBuffer(
				"from Delegation where refUser ='" + resultatU.getId() + "')");

		Query queryDelegation = sessionH.createQuery(sqlDelegation.toString());

		for (int i = 0; i < queryDelegation.list().size(); i++) {
			Delegation delagation = (Delegation) queryDelegation.list().get(i);
			listDelegation.add(delagation.getCodeSas());
		}

		StringBuffer sql = new StringBuffer("from Conciliation c ")
				.append(" join c.listOffre  ofr ")
				.append(" join ofr.listSinMotifOffre mofr ")
				.append(" where c.etat = '1' ")
				.append(" and ofr.refMotifOffre in ('1','4') ")
				.append(" and mofr.id = (select max(t.id) from SinMotifOffre t ")
                .append(" where t.refOffre = ofr and ((t.codeSas = '"
                        + codeSas
                        + "' and t.refMotifOffre in ('1')) or (t.codeSas in (:listDelegation) and t.refMotifOffre in ('4'))))");
		
		Query q = sessionH.createQuery(sql.toString())
				.setParameterList("listDelegation", listDelegation)
				.setMaxResults(20);

		List listResult = new ArrayList();
		if (pagerVO != null) {
			Integer numpage = 0;
			if (Integer.valueOf(pagerVO.getNumPage()).intValue() > 0) {
				numpage = Integer.valueOf(pagerVO.getNumPage()) - 1;
			}
			listResult= this.getPartCollectionByCondition(q, numpage,
					Integer.valueOf(pagerVO.getPageSize()));
		} else {
			listResult= q.list();
		}
		for (int i = 0; i < listResult.size(); i++) {

			Conciliation conciliation = (Conciliation) ((Object[]) listResult
					.get(i))[0];

			SinMotifOffre sinMotifOffre = (SinMotifOffre) ((Object[]) listResult
					.get(i))[2];

			List<SinMotifOffre> listSinMotifOffres = new ArrayList<SinMotifOffre>();
			listSinMotifOffres.add(sinMotifOffre);

			Offre offre = (Offre) ((Object[]) listResult.get(i))[1];

			List<Offre> listOffres = new ArrayList<Offre>();
			offre.setListSinMotifOffre(listSinMotifOffres);

			listOffres.add(offre);
			conciliation.setListOffre(listOffres);
			list.add(conciliation);

		}
		return list;
	}
	private List getPartCollectionByCondition(Query query, int page,
			int pageSize) throws PersistenceException {
		if (query != null) {
			query.setFirstResult(page * pageSize);
			query.setMaxResults(pageSize);
			return query.list();
		} else {
			return null;
		}
	}
	/**
	 * Mise à jour dossier conciliation valider
	 * 
	 * @param Conciliation
	 * @throws PersistenceException
	 * @throws FonctionnelleException
	 * @throws ExceptionMetier
	 */
	public void updateDossier(Conciliation conciliation, Double reserveOffre)
			throws PersistenceException, FonctionnelleException,
			ExceptionMetier {
		SinMotifOffre sinMotif = new SinMotifOffre();
		MotifOffre motifOffre = new MotifOffre();
		List<Offre> listOffre = conciliation.getListOffre();
		if (listOffre != null) {
			try {
				for (int i = 0; i < listOffre.size(); i++) {
					Offre of = (Offre) listOffre.get(i);
					of = (Offre) getSession().load(Offre.class, of.getId());
					String codeSas = listOffre.get(i).getListSinMotifOffre()
							.get(0).getCodeSas();

					/**
					 * Validation avec ordonancement
					 */
					Double seuilOperation = seuilOperation(codeSas);
					if (reserveOffre > seuilOperation) {
						motifOffre.setId(4L);
					} else {
						motifOffre.setId(2L);
					}
					sinMotif.setUserCreateur(listOffre.get(i)
							.getListSinMotifOffre().get(0).getUserCreateur());
					sinMotif.setCodeSas(codeSas);
					sinMotif.setDateEtat(new Date());
					sinMotif.setRefOffre(of);
					sinMotif.setRefMotifOffre(motifOffre);

					of.setDateModification(new Date());
					of.setRefMotifOffre(motifOffre);
					of.setCodeSas(codeSas);
					dao.createObject(sinMotif);
				}
			} catch (Exception e) {
				throw new FonctionnelleException(
						"Erreur GLOBAL lors du validation", e);
			}
		}
	}

	/**
	 * Mise à jour dossier conciliation Rejeter (update Offre)
	 * 
	 * @param Conciliation
	 * @throws PersistenceException
	 */
	public void updateDossierRejeter(Conciliation conciliation)
			throws PersistenceException {
		SinMotifOffre sinMotif = new SinMotifOffre();
		MotifOffre motifOffre = new MotifOffre();
		List<Offre> listOffre = conciliation.getListOffre();
		if (listOffre != null) {
			for (int i = 0; i < listOffre.size(); i++) {
				Offre of = (Offre) listOffre.get(i);
				of = (Offre) getSession().load(Offre.class, of.getId());
				motifOffre.setId(3L);

				sinMotif.setUserCreateur(listOffre.get(i)
						.getListSinMotifOffre().get(0).getUserCreateur());
				sinMotif.setDateEtat(new Date());
				sinMotif.setRefOffre(of);
				sinMotif.setRefMotifOffre(motifOffre);

				of.setDateCreation(new Date());
				of.setRefMotifOffre(motifOffre);
				dao.createObject(sinMotif);
			}
		}
	}

	/**
	 * Vérifier is supérieur
	 * 
	 * @param codeSas
	 * @return oui ou non
	 * @throws ExceptionMetier
	 * @throws FonctionnelleException
	 * @throws PersistenceException
	 */
	public Boolean isSuperieur(String codeSas) throws ExceptionMetier,
			FonctionnelleException, PersistenceException {

		Session sessionH = (Session) dao.getSession();
		if (codeSas == null) {
			throw new FonctionnelleException(
					"Le code SAS utilisateur est obligatoire !!");
		}
		StringBuffer sql = new StringBuffer("from User where codeSas ='"
				+ codeSas + "')");
		Query q = sessionH.createQuery(sql.toString());
		if (q.list().isEmpty()) {
			throw new FonctionnelleException("Le code SAS n'est pas configurer");
		}
		User resultatU = (User) q.list().get(0);

		StringBuffer sql1 = new StringBuffer("from Delegation where refUser ='"
				+ resultatU.getId() + "')");

		Query q1 = sessionH.createQuery(sql1.toString());
        if (q1.list().size() > 0 && !q1.list().isEmpty()) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Recuperer seuil operation
	 * 
	 * @param codeSas
	 * @return seuil
	 * @throws ExceptionMetier
	 * @throws FonctionnelleException
	 * @throws PersistenceException
	 */
	public Double seuilOperation(String codeSas) throws ExceptionMetier,
			FonctionnelleException, PersistenceException {
		Session sessionH = (Session) dao.getSession();
		if (codeSas == null) {
			throw new FonctionnelleException(
					"Le code SAS utilisateur est obligatoire !!");
		}
		StringBuffer sql = new StringBuffer("from User where codeSas ='"
				+ codeSas + "')");
		Query q = sessionH.createQuery(sql.toString());

		User resultatU = (User) q.list().get(0);

		StringBuffer sql1 = new StringBuffer(
				"from UserDelegation where refUser ='" + resultatU.getId()
						+ "')");

		Query q1 = sessionH.createQuery(sql1.toString());

		UserDelegation resultatUD = (UserDelegation) q1.list().get(0);
		long idDelegation = resultatUD.getRefDelegation().getId();

		StringBuffer sql2 = new StringBuffer("from Delegation where id ='"
				+ idDelegation + "')");

		Query q2 = sessionH.createQuery(sql2.toString());

		Delegation resultatD = (Delegation) q2.list().get(0);
		Double seuil = resultatD.getSeuilOperation();

		return seuil;

	}

	/**
	 * Recuperer la liste des délégations
	 * 
	 * @param
	 * @return liste des délégations
	 * @throws FonctionnelleException
	 * @throws PersistenceException
	 */
	public List<Delegation> getDelegation() throws FonctionnelleException,
			PersistenceException {
		Session sessionH = (Session) dao.getSession();
		List list = new ArrayList();

		StringBuffer sqlDelegation = new StringBuffer(
				"from Delegation d join d.refUser)");

		Query queryDelegation = sessionH.createQuery(sqlDelegation.toString());

		for (int i = 0; i < queryDelegation.list().size(); i++) {

			Delegation delegation = (Delegation) ((Object[]) queryDelegation
					.list().get(i))[0];

			User user = (User) ((Object[]) queryDelegation.list().get(i))[1];

			delegation.setRefUser(user);
			list.add(delegation);

		}

		return list;
	}

	/**
	 * Recuperer la liste des utilisateurs
	 * 
	 * @param id
	 * @param codeSas
	 * @return liste des utilisateurs
	 * @throws PersistenceException
	 */
	public List getListUser(long id, String codeSas)
			throws PersistenceException {

		Session sessionH = (Session) dao.getSession();
		String sqlDelegation = " from Delegation where refUser = " + id
				+ " and codeSas = '" + codeSas + "'";
		Query queryDelegation = sessionH.createQuery(sqlDelegation);
		Delegation resultatDelegation = (Delegation) queryDelegation.list()
				.get(0);
		double seuilOperation = resultatDelegation.getSeuilOperation();

		String sqlUser = " from User where seuilOperation >= " + seuilOperation
				+ " and isSuperieur = 1";
		Query queryUser = sessionH.createQuery(sqlUser);

		return queryUser.list();

	}

	/**
	 * Modifier le supérieur
	 * 
	 * @param id
	 * @param newSup
	 * @return
	 * @throws PersistenceException
	 */
	public void updateDelegation(long id, long newSup)
			throws PersistenceException {

		Delegation delegation = (Delegation) getSession().load(
				Delegation.class, id);
		User user = new User();
		user.setId(newSup);
		delegation.setRefUser(user);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public Date getEcheanceTrimestre(Date date) {
		if (date == null) {
			return null;
		}
		int mois = Integer.valueOf(DateUtile.dateToString("MM", date));
		String annee = DateUtile.dateToString("yyyy", date);
		if (mois >= 1 && mois <= 3) {
			return DateUtile.getDate("dd/MM/yyyy", "31/03/" + annee);
		} else if (mois >= 4 && mois <= 6) {
			return DateUtile.getDate("dd/MM/yyyy", "30/06/" + annee);
		} else if (mois >= 7 && mois <= 9) {
			return DateUtile.getDate("dd/MM/yyyy", "30/09/" + annee);
		} else if (mois >= 10 && mois <= 12) {
			return DateUtile.getDate("dd/MM/yyyy", "31/12/" + annee);
		}
		return null;
	}

	public List getListAyantDroitByIdSinistre(Sinistre s) {
		List listAydOffre = new ArrayList();
		try {
			List<Conciliation> listConci = getConciliationByIdSinistre(s);
			for (Conciliation conciliation : listConci) {
				ConciliationVO c = new ConciliationVO();
				c.setId(conciliation.getId());
				List<Offre> listOff = getOffresByIdConciliation(c);
				for (Offre offre : listOff) {
					// grisé lorsque
					// le statut de l'offre est validé sans résultat
					// ou bien offre valide avec résultat different de refus 
					if (offre.getRefMotifOffre().getId() == 2  && 
							(offre.getRefResultatOffre() == null || (offre.getRefResultatOffre() != null
									&& offre.getRefResultatOffre().getRefEtatResultat()!= null && !offre
									.getRefResultatOffre().getRefEtatResultat()
									.getId().equals("2")))) {
						for (int i = 0; i < offre.getListAyantDroit().size(); i++) {
							listAydOffre.add(offre.getListAyantDroit().get(i));
						}
					}
				}
			}
		} catch (Exception e) {
            LOGGER.error("problème technique", e);
		}
		return listAydOffre;
	}

	public List<OrigineConciliation> getListOrignineConciliation()
			throws FonctionnelleException {

		Session sessionH;
		List<OrigineConciliation> list = new ArrayList<OrigineConciliation>();
		try {
			sessionH = (Session) dao.getSession();
			String sql = new String("from OrigineConciliation");
			Query q = sessionH.createQuery(sql.toString());
			list = q.list();

		} catch (Exception e) {
			throw new FonctionnelleException("Erreur lors d'initialisation", e);
		}
		return list;

	}
	
	
		public List<Ville> getListVille()
			throws FonctionnelleException {
		
		Session sessionH;
		List<Ville> list = new ArrayList<Ville>();
		try {
			sessionH = (Session) dao.getSession();
			String sql = new String("from Ville");
			Query q = sessionH.createQuery(sql.toString());
			list = q.list();
		
		} catch (Exception e) {
			throw new FonctionnelleException("Erreur lors d'initialisation", e);
		}
		return list;
		
		}

	public List<MotifConvocation> getListMotifConvocation()
			throws FonctionnelleException {

		Session sessionH;
		List<MotifConvocation> list = new ArrayList<MotifConvocation>();
		try {
			sessionH = (Session) dao.getSession();
			String sql = new String("from MotifConvocation");
			Query q = sessionH.createQuery(sql.toString());
			list = q.list();

		} catch (Exception e) {
			throw new FonctionnelleException("Erreur lors du validation", e);
		}
		return list;
	}

	public Integer getNombreConciliationSupAValider(String codeSas)
		throws Exception {
		Session sessionH = (Session) dao.getSession();
		List listDelegation = new ArrayList();
		if (codeSas == null) {
			throw new FonctionnelleException(
					"Le code SAS utilisateur est obligatoire !!");
		}
		StringBuffer sqlUser = new StringBuffer("from User where codeSas ='"
				+ codeSas + "')");
		Query qUser = sessionH.createQuery(sqlUser.toString());
		User resultatU = (User) qUser.list().get(0);

		StringBuffer sqlDelegation = new StringBuffer(
				"from Delegation where refUser ='" + resultatU.getId() + "')");

		Query queryDelegation = sessionH.createQuery(sqlDelegation.toString());

		for (int i = 0; i < queryDelegation.list().size(); i++) {
			Delegation delagation = (Delegation) queryDelegation.list().get(i);
			listDelegation.add(delagation.getCodeSas());
		}

		String query = "Select count (*) from Conciliation c  join c.listOffre  ofr  join ofr.listSinMotifOffre mofr "+
				" where c.etat = '1'  and ofr.refMotifOffre in ('1','4')  and mofr.id = (select max(t.id) from SinMotifOffre t "+
                " where t.refOffre = ofr and ((t.codeSas = '"+ codeSas
                        + "' and t.refMotifOffre in ('1')) or (t.codeSas in (:listDelegation) and t.refMotifOffre in ('4'))))";
		
			Long nombreResultat = (Long) getSession().createQuery(query).setParameterList("listDelegation", listDelegation)
					.uniqueResult();

			return nombreResultat.intValue();
	}

	public Integer getNombreConciliationAValider() throws Exception {
		String query = "Select count (*) from Conciliation c join c.listOffre  ofr join ofr.listSinMotifOffre mofr  where c.etat = '1'  and ofr.refMotifOffre = '1' "+
		" and mofr.id = (select max(t.id) from SinMotifOffre t where t.refMotifOffre = '1' and t.refOffre = ofr))";
		Long nombreResultat = (Long) getSession().createQuery(query)
				.uniqueResult();
		return nombreResultat.intValue();
	}

	public List<SinAnterieurOffre> getSinistresAnterieurs(String idOffre) {
		List<SinAnterieurOffre> sinistreAnt = null;
		try {
			String sql = "from SinAnterieurOffre s where refOffre.id='"
					+ String.valueOf(idOffre) + "'";
			Query q = getSession().createQuery(sql.toString());
			sinistreAnt = q.list();
		} catch (PersistenceException e) {
			logger.error(EXP_SINISTRE_INEXISTANT, e);
		}

		return sinistreAnt;
	}

	public List<AyantDroitOffre> getListAyantDroitOffre(String idOffre) {
		List<AyantDroitOffre> ayantDroit = null;
		Offre offre = null;
		try {		
			String sql = "from AyantDroitOffre s where refOffre.id='"
					+ String.valueOf(idOffre) + "'";
			Query q = getSession().createQuery(sql.toString());
			ayantDroit = q.list();
		} catch (PersistenceException e) {
			logger.error(EXP_SINISTRE_INEXISTANT, e);
		}

		return ayantDroit;
	}

	public SinEditionPV creerEditionPV(SinEditionPV editionPV) throws HibernateException, PersistenceException {
		SinEditionPV edition = getLettreByIdOffre(editionPV.getRefOffre());
		
		edition.setDateEdition(new Date());
		edition.setRefOffre(editionPV.getRefOffre());
		edition.setUserEditeur(editionPV.getUserEditeur());
		edition.setNbrEdition(edition.getNbrEdition()!= null ? edition.getNbrEdition()+1 : 1);
		if ((edition.getEditionInermediaire()!= null && edition.getEditionInermediaire()) && editionPV.getEditionInermediaire()) {
			edition.setIsEdit(Boolean.TRUE);
		}
		else if ((edition.getEditionSiege()!= null && edition.getEditionSiege()) && editionPV.getEditionSiege()) {
			edition.setIsEdit(Boolean.TRUE);
		}
		else{
			edition.setIsEdit(Boolean.FALSE);
			if(editionPV.getEditionInermediaire())
			{
				edition.setEditionInermediaire(Boolean.TRUE);
			}
			if(editionPV.getEditionSiege())
			{
				edition.setEditionSiege(Boolean.TRUE);
			}
		}
		
		getSession().saveOrUpdate(edition);
		return edition;
		
	}

	private SinEditionPV getLettreByIdOffre(Offre refOffre) throws HibernateException, PersistenceException {
		StringBuffer hql = new StringBuffer(
				" from SinEditionPV where refOffre.id ='").append(refOffre.getId())
				.append("'");

		Query query = getSession().createQuery(hql.toString());
		SinEditionPV edit = (SinEditionPV) query.uniqueResult();
		if (edit == null) {
			return new SinEditionPV();
		}

		return edit;
	}
	
		public List<RelanceConciliation> getListRelanceConciliation()
			throws FonctionnelleException {
		
			Session sessionH;
			List<RelanceConciliation> list = new ArrayList<RelanceConciliation>();
			try {
				sessionH = (Session) dao.getSession();
				String sql = new String("from RelanceConciliation");
				Query q = sessionH.createQuery(sql.toString());
				list = q.list();
			
			} catch (Exception e) {
				throw new FonctionnelleException("Erreur lors d'initialisation", e);
			}
			return list;
		
		}
		
			public Object[] getQueryListConciliation(Conciliation conciliation)
			throws FonctionnelleException, PersistenceException {
			StringBuffer sql = new StringBuffer(" from Conciliation c join c.refSinistre sinn")
			.append(" left join c.listRelanceConciliation r")
			.append(" left join c.listOffre offre ")
			.append(" left join offre.refResultatOffre res  where ")
			.append(" c.etat = 1 and offre.id is null ")
			.append(" and ( ( r.id is not null and r.dateRelance +15 <= sysdate  and r.id = (Select max(rr.id) from RelanceConciliation rr where c.id = rr.refConciliation.id) and r.numeroRelance <=4)  ")
			.append(" or ( r.id is null  and c.dateCreation +15 <= sysdate) ) ")
			.append(" and c.dateCreation >= to_date('2020/07/28','yyyy/mm/dd')")
			.append(" and  sinn.id NOT IN ("
				       + "SELECT s1.id FROM Sinistre    s1,Recours rec,ProcedureJudiciaire rp WHERE rec.refSinistre.id= s1.id AND rp.refRecours.id = rec.id)")
			.append(" and c.userCreateur ='" + conciliation.getUserCreateur() + "'order by c.dateCreation desc");
							
			Object[] objects = new Object[2];
			Map<String, Object> values = new HashMap<String, Object>();
			objects[0] = sql.toString();
			objects[1] = values;
			return objects;
			}
			
			public Object[] getQueryListConciliationSignaturePV(Conciliation conciliation)
			throws FonctionnelleException, PersistenceException {
			StringBuffer sql = new StringBuffer(" from Conciliation c join c.refSinistre sinn")
			.append(" left join c.listRelanceConciliation r")
			.append(" left join c.listOffre offre where")
			.append(" c.etat = 1 and offre.id is not null and offre.refMotifOffre.id = 2 ")
			.append(" and ( ( r.id is not null and r.dateRelance +7 <= sysdate and r.id = (Select max(rr.id) from RelanceConciliation rr where c.id = rr.refConciliation.id) and r.numeroRelance <=4)  ")
			.append(" or ( r.id is null and offre.dateModification +7 <= sysdate) ) ")
			.append(" and offre.dateModification >= to_date('2020/07/28','yyyy/mm/dd')")
			.append(" and offre.refResultatOffre.id is null")
			.append(" and c.userCreateur ='" + conciliation.getUserCreateur() + "' order by c.dateCreation desc");					
			
			
			Object[] objects = new Object[2];
			Map<String, Object> values = new HashMap<String, Object>();
			objects[0] = sql.toString();
			objects[1] = values;
			return objects;
			}
			
			public Integer getNombreConciliation(Conciliation conciliation) 
			throws Exception {
				Session sessionH = (Session) dao.getSession();			
				Object[] objects = this.getQueryListConciliation(conciliation);
				String hql = "Select count (c.id) " + (String) objects[0];
				Query query = sessionH.createQuery(hql);
				Long nombreResultat = (Long) query.uniqueResult();
				return nombreResultat.intValue();
		}
			public Integer getNombreConciliationSignaturePV(Conciliation conciliation) 
			throws Exception {
				Session sessionH = (Session) dao.getSession();			
				Object[] objects = this.getQueryListConciliationSignaturePV(conciliation);
				String hql = "Select count (c.id) " + (String) objects[0];
				Query query = sessionH.createQuery(hql);
				Long nombreResultat = (Long) query.uniqueResult();
				return nombreResultat.intValue();
		}
			
			
			public List<Conciliation> getListConciliation(PagerVO pagerVO, Conciliation conciliation) throws Exception {
				
				Session sessionH = (Session) dao.getSession();			
				Object[] objects =	this.getQueryListConciliation(conciliation);
				String hql = "Select c  " + (String) objects[0];
				Query query = sessionH.createQuery(hql);
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
			
				public List<Conciliation> getListConciliationSignaturePV(PagerVO pagerVO, Conciliation conciliation) throws Exception {
				
				Session sessionH = (Session) dao.getSession();			
				Object[] objects =	this.getQueryListConciliationSignaturePV(conciliation);
				String hql = "Select c " + (String) objects[0];
				Query query = sessionH.createQuery(hql);
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
			
			public List<DestinataireRelance> getListDestinataireRelance()
			throws FonctionnelleException {

				Session sessionH;
				List<DestinataireRelance> list = new ArrayList<DestinataireRelance>();
				try {
					sessionH = (Session) dao.getSession();
					String sql = new String("from DestinataireRelance");
					Query q = sessionH.createQuery(sql.toString());
					list = q.list();
		
				} catch (Exception e) {
					throw new FonctionnelleException("Erreur lors d'initialisation", e);
				}
				return list;
		
			}
			
			public List<CanalConciliation> getListCanalConciliation()
			throws FonctionnelleException {

				Session sessionH;
				List<CanalConciliation> list = new ArrayList<CanalConciliation>();
				try {
					sessionH = (Session) dao.getSession();
					String sql = new String("from CanalConciliation");
					Query q = sessionH.createQuery(sql.toString());
					list = q.list();
		
				} catch (Exception e) {
					throw new FonctionnelleException("Erreur lors d'initialisation", e);
				}
				return list;
		
			}
			
			public List<GestionnaireRelance> getListGestionnaireRelance()
			throws FonctionnelleException {

				Session sessionH;
				List<GestionnaireRelance> list = new ArrayList<GestionnaireRelance>();
				try {
					sessionH = (Session) dao.getSession();
					String sql = new String("from GestionnaireRelance");
					Query q = sessionH.createQuery(sql.toString());
					list = q.list();
		
				} catch (Exception e) {
					throw new FonctionnelleException("Erreur lors d'initialisation", e);
				}
				return list;
		
			}
			
			public List<PieceAt> getListPiece()
			throws FonctionnelleException {

				Session sessionH;
				List<PieceAt> list = new ArrayList<PieceAt>();
				try {
					sessionH = (Session) dao.getSession();
					String sql = new String("from PieceAt");
					Query q = sessionH.createQuery(sql.toString());
					list = q.list();
		
				} catch (Exception e) {
					throw new FonctionnelleException("Erreur lors d'initialisation", e);
				}
				return list;
		
			}
			
			public RelanceConciliation RelancerConciliation(RelanceConciliation relanceConciliation)
			throws PersistenceException, ParseException, FonctionnelleException {
				System.out.println(relanceConciliation);
				if (relanceConciliation.getRelanceConciliationPiece() != null) {
					for (int i = 0; i < relanceConciliation.getRelanceConciliationPiece().size(); i++) {
						RelanceConciliationPiece relanceConciliationPiece = relanceConciliation
								.getRelanceConciliationPiece().get(i);
						relanceConciliationPiece.setRelanceConciliation(relanceConciliation);
					}
				}
				int numeroRelance = 1;
				Conciliation c = getConciliationById(relanceConciliation.getRefConciliation());
				if(c.getListRelanceConciliation() == null){
					numeroRelance = 1;
				}
				else{
					numeroRelance = c.getListRelanceConciliation().size()+1;
				}
				Date dateRelance = new Date(); 
				Date dateDerniereRelance = new Date(); 
				if(numeroRelance == 1){
					//dateRelance = c.getDateCreation();
					dateDerniereRelance = new Date();
					
				}else{
					//dateRelance = DateUtils.addDays(c.getListRelanceConciliation().get(0).getDateRelance(), 15);
					dateDerniereRelance = c.getListRelanceConciliation().get(0).getDateRelance();
				}
				relanceConciliation.setNumeroRelance(numeroRelance);
				relanceConciliation.setDateRelance(dateRelance);
				relanceConciliation.setDateDerniereRelance(dateDerniereRelance);
				setTypeRelanceConciliation(relanceConciliation);
				dao.createObject(relanceConciliation);
				return relanceConciliation;
	}
			
			public List<PieceAt> getSelectedPiecesByRelance(String id) {
				List<PieceAt>  selectedPieces = null;
				try {
					String sql = "Select distinct r.piece.libelle from RelanceConciliationPiece r , RelanceConciliation rc , Conciliation c ,PieceAt pt , Sinistre sin where c.id=rc.refConciliation.id  and c.refSinistre.id = sin.id and r.relanceConciliation.id = rc.id and pt.code = r.piece.code and r.cocher= '1' and r.relanceConciliation.refConciliation.id ='"
							+ String.valueOf(id) + "'";
					Query q = getSession().createQuery(sql.toString());
					selectedPieces = q.list();
				} catch (PersistenceException e) {
					logger.error(EXP_CREATION_REGLEMENT, e);
				}
				return selectedPieces;
			}
			
			public void setTypeRelanceConciliation(RelanceConciliation relanceConciliation){
				if(relanceConciliation.getTypeRelance().equals("1")){
					relanceConciliation.setTypeRelance(RELANCE_COMPLETUDE_DOSSIER);
					}
				if(relanceConciliation.getTypeRelance().equals("2")){
					relanceConciliation.setTypeRelance(RELANCE_SIGNATURE_PV);
				}
				
			}
			private Query getQuery(String hql, Map<String, Object> values)
			throws FonctionnelleException {

		// Query
		Query querySql = null;
		try {
			querySql = getSession().createQuery(hql);
			Set<Entry<String, Object>> params = values.entrySet();
			for (Entry<String, Object> curEntry : params) {
				if (curEntry.getKey().startsWith("list")) {
					querySql.setParameterList(curEntry.getKey(),
							(List) curEntry.getValue());

				} else {
					querySql.setParameter(curEntry.getKey(),
							curEntry.getValue());
				}
			}
		} catch (Exception e) {
			logger.error("Exception :", e);
			throw new FonctionnelleException(e.getMessage());
		}

		return querySql;
	}
    	private Object[] getListeRelanceConciliation(RelanceConciliation relanceConciliation, Map paramsDate)
			throws FonctionnelleException {
			
			
			if (relanceConciliation == null) {
				throw new FonctionnelleException(EXP_RECHERCHE_REGLEMENT);
			}
			
			Map<String, Object> values = new HashMap<String, Object>();
			StringBuffer query = new StringBuffer("from RelanceConciliation ");
			// Return
			Object[] objects = new Object[2];
			objects[0] = query.toString();
			objects[1] = values;
			return objects;
			}
			
    	public List getListRelanceConciliation(RelanceConciliation relanceConciliation, Map map)
			throws FonctionnelleException {
			Object[] objects = this.getListeRelanceConciliation(relanceConciliation, map);
			Query query = getQuery((String) objects[0],
					(Map<String, Object>) objects[1]);
			return query.list();
			}
    	public void creerConciliationAvecOffreDuplicate(Conciliation conciliation)
    			throws PersistenceException, ParseException {
    		String coefAgeConvert;
    		conciliation.setDateCreation(new Date());
    		// Liste des pièces
    		if (conciliation.getListPieces() != null) {
    			for (int i = 0; i < conciliation.getListPieces().size(); i++) {
    				Pieces pieces = conciliation.getListPieces().get(i);
    				pieces.setDateCreation(new Date());
    				pieces.setRefConciliation(conciliation);
    			}
    		}
    		// liste des convocations
    		if (conciliation.getListConvocation() != null) {
    			for (int i = 0; i < conciliation.getListConvocation().size(); i++) {
    				Convocation conv = conciliation.getListConvocation().get(i);
    				conv.setDateCreation(new Date());
    				conv.setRefConciliation(conciliation);
    			}
    		}
    		// liste des offres
    		if (conciliation.getListOffre() != null) {
    			for (int i = 0; i < conciliation.getListOffre().size(); i++) {
    				Offre offre = conciliation.getListOffre().get(i);
    				offre.setDateCreation(new Date());
    				offre.setDateMotif(new Date());
    				offre.setRefConciliation(conciliation);
    				offre.setEtatOffreConciliation(IConstantes.ETAT_OFFRE_DUPLICATA);
    				//limiter les chiffres de coef d'age apres la virgule
    				if(offre.getCoefficientAge()!=null) {
    				coefAgeConvert = CommonUtils.getInstance().limiterchiffreVirgule(offre.getCoefficientAge());
    				offre.setCoefficientAge(Double.valueOf(coefAgeConvert));
    				}
    				List<AyantDroitOffre> listAyd = offre.getListAyantDroit();
    				if (listAyd != null) {
    					for (AyantDroitOffre ayd : listAyd) {
    						ayd.setRefOffre(offre);
    						dao.createObject(ayd);
    					}
    				}
    				List<SinAnterieurOffre> listSinAnt = offre.getListSinistreAnterieur();
    				if (listSinAnt != null) {
    					for (SinAnterieurOffre sin : listSinAnt) {
    						sin.setRefOffre(offre);
    						dao.createObject(sin);
    					}
    				}
    			}
    		}
    		dao.createObject(conciliation);

    	}

    	
}
