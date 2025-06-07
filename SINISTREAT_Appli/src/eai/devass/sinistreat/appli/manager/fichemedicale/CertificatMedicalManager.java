package eai.devass.sinistreat.appli.manager.fichemedicale;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;
import ma.co.omnidata.framework.services.dao.PersistenceException;
import ma.co.omnidata.framework.services.entites.EntiteManagerAbst;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.fichemedicale.CertificatMedical;
import eai.devass.sinistreat.appli.modele.metier.fichemedicale.CertificatMedicalHistorique;
import eai.devass.sinistreat.appli.utils.ConverterMetier;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.utils.entites.IDate;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.utils.exception.IMessageInfo;

/**
 * Manager de l ' entité CertificatMedical
 * 
 * @author Nom Prenom (email)
 */
public class CertificatMedicalManager extends EntiteManagerAbst implements
		IConstantes, IMessageException, IMessageInfo {

	IPersistenceService dao = (IPersistenceService) ServicesFactory
			.getService(IPersistenceService.class);
	SimpleDateFormat dateFormat = new SimpleDateFormat(IDate.FORMAT_SIMPLE);
	ConverterMetier converterMetier = ConverterMetier.getInstance();
	ConverterTools converterTools = ConverterTools.getInstance();
	static String MOTIFCREATION = "Creation du certificat.";
	static String MOTIFMODIFICATION = "Modification du certificat.";
	static String MOTIFVALIDATION = "Validation du certificat.";
	static String MOTIFINVALIDATION = "Invalidation du certificat.";
    private static final Logger logger = Logger.getLogger("loggerSINAT");
    private static final String  EXP_CERTIFICAT_NOT_VALIDE ="EXP_CERTIFICAT_NOT_VALIDE";

	// SinistreManager

	public CertificatMedical creerCertificatMedical(
			CertificatMedical certificatMedical) throws FonctionnelleException {
		try {
			if (certificatMedical.getCodePrestataire() != null) {
				certificatMedical.setCodePrestataire(certificatMedical
						.getCodePrestataire().toUpperCase());
			}
			certificatMedical.setDateCreation(new Date());
			dao.createObject(certificatMedical);
			creerHistorique(certificatMedical, MOTIFCREATION);
			return certificatMedical;
		} catch (PersistenceException e) {
			logger.error(EXP_CREATION_CERTIFICAT, e);
			throw new FonctionnelleException(EXP_CREATION_CERTIFICAT,e);
		}
	}

	private void creerHistorique(CertificatMedical certificatMedical,
			String motif) throws PersistenceException {
		CertificatMedicalHistorique historique = new CertificatMedicalHistorique();
		historique.setRefCertificat(certificatMedical);
		historique.setMotif(motif);
		historique.setDateEvenement(new Date());
		dao.createObject(historique);
	}

	public CertificatMedical modifierCertificatMedical(
			CertificatMedical certificatMedical) throws PersistenceException,
			FonctionnelleException {

		try {
			if (certificatMedical.getId() == null) {
                throw new FonctionnelleException(EXP_CERTIFICAT_NOT_VALIDE);
			}
			CertificatMedical certificatDB = getCertificatbyId(certificatMedical
					.getId());
			if (certificatDB == null) {
                throw new FonctionnelleException(EXP_CERTIFICAT_NOT_VALIDE);
			}

			converterMetier.copyCertificatMedical(certificatDB,
					certificatMedical);
			certificatMedical = certificatDB;
			if (certificatMedical.getCodePrestataire() != null) {
				certificatMedical.setCodePrestataire(certificatMedical
						.getCodePrestataire().toUpperCase());
			}
			dao.updateObject(certificatMedical);
			creerHistorique(certificatMedical, MOTIFMODIFICATION);
			return certificatMedical;
		} catch (PersistenceException e) {
			logger.error("Error modification certificat medical BD", e);
            throw new FonctionnelleException(
                    "Error modification certificat medical BD1", e);
		} catch (Exception e) {
			logger.error("Error modification certificat medical", e);
            throw new FonctionnelleException(
                    "Error modification certificat medical1", e);
		}

	}

	public CertificatMedical supprimerCertificatMedical(
			CertificatMedical certificatMedical) throws PersistenceException,
			FonctionnelleException {

		try {
			if (certificatMedical.getId() == null) {
                throw new FonctionnelleException(EXP_CERTIFICAT_NOT_VALIDE);
			}
			CertificatMedical certificatDB = getCertificatbyId(certificatMedical
					.getId());
			if (certificatDB == null) {
                throw new FonctionnelleException(EXP_CERTIFICAT_NOT_VALIDE);
			}

			certificatMedical = certificatDB;
			dao.delete(certificatMedical);

			return certificatMedical;
		} catch (PersistenceException e) {
			logger.error("Error suppression certificat medical BD", e);
            throw new FonctionnelleException(
                    "Error suppression certificat medical BD1", e);
		} catch (Exception e) {
			logger.error("Error suppression certificat medical", e);
            throw new FonctionnelleException(
                    "Error suppression certificat medical1", e);
		}

	}

	public CertificatMedical rechercherCertificatMedical(
			CertificatMedical certificatMedical) throws FonctionnelleException {

		try {
			CertificatMedical certificatDB = getCertificatbyId(certificatMedical
					.getId());
			if (certificatDB == null) {
                throw new FonctionnelleException(EXP_CERTIFICAT_NOT_VALIDE);
			}

			certificatMedical = certificatDB;

			return certificatMedical;
		} catch (Exception e) {
			logger.error("Error recherche certificat medical", e);
            throw new FonctionnelleException(
                    "Error recherche certificat medical1", e);
		}

	}

	public List<CertificatMedical> getListDerniersCertificat(
			CertificatMedical certificatMedical) throws Exception {

		if (certificatMedical == null) {
			throw new Exception(
					"l'objet certificatMedical en entrée ne peut être null");
		}

		if (certificatMedical.getRefSinistre() != null
				&& certificatMedical.getRefSinistre().getId() != 0) {
			String sql = "from CertificatMedical c where c.valide= '1' and c.refSinistre.id ='"
					+ certificatMedical.getRefSinistre().getId()+"'";
			if (certificatMedical.getRefType() != null
					&& certificatMedical.getRefType().getCode() != "") {
				sql += " and c.refType.code = '"
						+ certificatMedical.getRefType().getCode() + "'";
			}
			List certificatList = getSession().createQuery(sql).list();
			if (certificatList == null || certificatList.isEmpty()) {
				sql = "select c.id from SIN_CERTIFICAT c "
						+ " where c.refSinistre = "
						+ certificatMedical.getRefSinistre().getId()
						+ " and c.DATECERTIFICAT = (select max(c1.DATECERTIFICAT) from SIN_CERTIFICAT c1  where  c1.refSinistre ="
						+ certificatMedical.getRefSinistre().getId()
						+ " and c1.TypeCertificat =c.TypeCertificat)";
				Query query = getSession().createSQLQuery(sql);
				List<BigDecimal> ids = query.list();
				List<CertificatMedical> result = new ArrayList<CertificatMedical>();
				for (BigDecimal id : ids) {
					CertificatMedical cert = getCertificatbyId(id.longValue());
					result.add(cert);
				}
				return result;
			}
			return certificatList;
		}

		return null;

	}

	public Date getChampCertificatSinistre(Long id) throws HibernateException,
			PersistenceException {
        String hql = "select c.dateGuerison from CertificatMedical c where c.id=("
				+ " select max (c1.id) from CertificatMedical c1 where c1.dateGuerison is not null and c1.refSinistre.id ="
				+ id + " and c1.valide=1)";
        Query query = getSession().createQuery(hql.toString());

		return (Date) query.uniqueResult();
	}

	public Date getDateCreation(long id) throws HibernateException,
			PersistenceException {
        String hql = "select c.dateCreation from CertificatMedical c where c.id=("
				+ " select max (c1.id) from CertificatMedical c1 where c1.dateGuerison is not null and c1.refSinistre.id ="
				+ id + " and c1.valide=1)";
        Query query = getSession().createQuery(hql.toString());
		return (Date) query.uniqueResult();
	}

	public List<CertificatMedical> getListCertificat(
			CertificatMedical certificatMedical) throws Exception {

		if (certificatMedical == null) {
			throw new Exception(
					"l'objet certificatMedical en entrée ne peut être null");
		}

        StringBuffer hql = new StringBuffer(
				"from CertificatMedical certificatMedical  ");

        hql.append(" where 1=1 ");

		if (certificatMedical.getId() != null) {
            hql.append(" and certificatMedical.id=")
					.append(certificatMedical.getId()).append(" ");
		}

		// TypeCertificat
		if (certificatMedical.getRefType() != null
				&& certificatMedical.getRefType().getCode() != null) {
            hql.append(" and certificatMedical.refType.code='")
					.append(certificatMedical.getRefType().getCode())
					.append("' ");
		}

		if (certificatMedical.getRefSinistre() != null
				&& certificatMedical.getRefSinistre().getId() != 0) {
            hql.append(" and certificatMedical.refSinistre.id='")
					.append(certificatMedical.getRefSinistre().getId())
					.append("' ");
		}
        hql.append(" order by certificatMedical.id desc");
        Query query = getSession().createQuery(hql.toString());

		return query.list();
	}

	public CertificatMedical getCertificatbyId(Long id)
			throws FonctionnelleException {
		try {
			if (id == null) {
				return null;
			}
            String hql = " from CertificatMedical certificat where certificat.id="
					+ id;
            return (CertificatMedical) getSession().createQuery(hql)
					.uniqueResult();
		} catch (PersistenceException e) {
			logger.error(EXP_RECHERCHE_CERTIFICAT, e);
			throw new FonctionnelleException(EXP_RECHERCHE_CERTIFICAT,e);
		}

	}

	/**
	 * Récupérer la Session hibernate via la DAO
	 * 
	 * @return
	 * @throws PersistenceException
	 */
	public Session getSession() throws PersistenceException {
		return (Session) dao.getSession();
	}

	public List getListCertificatsByType(String type, Long idSinistre)
			throws HibernateException, PersistenceException {

        StringBuffer hql = new StringBuffer(
				"from CertificatMedical certificatMedical  ");
        hql.append(" where certificatMedical.refType.code='").append(type)
				.append("' ");
        hql.append(" and certificatMedical.refSinistre.id=").append(idSinistre);
        Query query = getSession().createQuery(hql.toString());

		return query.list();
	}

	public List getValideCertificatBySin(Long idSinistre)
			throws FonctionnelleException {
		try {
            StringBuffer hql = new StringBuffer(
					"from CertificatMedical certificatMedical  ");
            hql.append(" where certificatMedical.valide='1'");
            hql.append(" and certificatMedical.refSinistre.id=")
					.append(idSinistre)
					.append(" order by certificatMedical.refType.code");
            Query query = getSession().createQuery(hql.toString());

			return query.list();
		} catch (PersistenceException e) {
			logger.error(EXP_RECHERCHE_CERTIFICAT, e);
			throw new FonctionnelleException(EXP_RECHERCHE_CERTIFICAT,e);
		}
	}

	public boolean invaliderCertificatParId(CertificatMedical certificatMedical)
			throws FonctionnelleException {
		try {
			int numSucces = 0;
			long idSinistre;
			String typeCertificat;
            if (certificatMedical.getRefSinistre() == null
                    || certificatMedical.getRefSinistre().getId() == 0) {
				CertificatMedical certificatMedicalBD = getCertificatbyId(certificatMedical
						.getId());
				if (certificatMedicalBD == null) {
					throw new FonctionnelleException(EXP_CERTIFICAT_INEXISTANT);
				}
				idSinistre = certificatMedicalBD.getRefSinistre().getId();
			}else{
				idSinistre= certificatMedical.getRefSinistre().getId();
			}
            if (certificatMedical.getRefType() == null
                    || StringUtils.isEmpty(certificatMedical.getRefType()
                            .getCode())) {
				CertificatMedical certificatMedicalBD = getCertificatbyId(certificatMedical
						.getId());
				if (certificatMedicalBD == null) {
					throw new FonctionnelleException(EXP_CERTIFICAT_INEXISTANT);
				}
				typeCertificat = certificatMedicalBD.getRefType().getCode();
			}else{
				typeCertificat= certificatMedical.getRefType().getCode();
			}
            List<CertificatMedical> liste = getListCertificatsByType(
                    typeCertificat, idSinistre);
			if (liste != null && !liste.isEmpty()) {
				if (liste.size() == 1) {
					CertificatMedical certificat = (CertificatMedical) liste
							.get(0);
					certificat.setValide(true);
				} else if (liste.size() > 1) {
					if (certificatMedical.getValide() != null
							&& certificatMedical.getValide()) {
						for (CertificatMedical certificat : liste) {
							if (certificat.getId().equals(
									certificatMedical.getId())) {
								certificat.setValide(true);
							} else {
								if (certificat.getValide() == null
										|| (certificat.getValide() != null && certificat
												.getValide())) {
									certificat.setValide(false);
									dao.updateObject(certificat);
									creerHistorique(certificat,
											MOTIFINVALIDATION);
								}
							}
							numSucces++;
						}
					} else {
						boolean existevalide = false;
						int num = 0;
						int numcer = 0;
						for (CertificatMedical certificat : liste) {
							num++;
							if (certificat.getId().equals(
									certificatMedical.getId())) {
								numcer = num - 1;
								continue;
							}
							if (certificat.getValide() == null
									|| (certificat.getValide() != null && certificat
											.getValide())) {
								existevalide = true;
							}
						}
						if (!existevalide) {
							CertificatMedical certificat = (CertificatMedical) liste
									.get(numcer);
							certificat.setValide(true);
						}
					}
				}
				return numSucces == liste.size();
			}
		} catch (PersistenceException e) {
			logger.error(EXP_INVALIDATION_CERTIFICAT, e);
			throw new FonctionnelleException(EXP_INVALIDATION_CERTIFICAT,e);
		}
		return false;
	}

	public boolean invaliderCertificatParIpp(CertificatMedical certificatMedical)
			throws FonctionnelleException {
		try {
			int numSucces = 0;
			long idSinistre;
			String typeCertificat;
            if (certificatMedical.getRefSinistre() == null
                    || certificatMedical.getRefSinistre().getId() == 0) {
				CertificatMedical certificatMedicalBD = getCertificatbyId(certificatMedical
						.getId());
				if (certificatMedicalBD == null) {
					throw new FonctionnelleException(EXP_CERTIFICAT_INEXISTANT);
				}
				idSinistre = certificatMedicalBD.getRefSinistre().getId();
			}else{
				idSinistre= certificatMedical.getRefSinistre().getId();
			}
            if (certificatMedical.getRefType() == null
                    || StringUtils.isEmpty(certificatMedical.getRefType()
                            .getCode())) {
				CertificatMedical certificatMedicalBD = getCertificatbyId(certificatMedical
						.getId());
				if (certificatMedicalBD == null) {
					throw new FonctionnelleException(EXP_CERTIFICAT_INEXISTANT);
				}
				typeCertificat = certificatMedicalBD.getRefType().getCode();
			}else{
				typeCertificat= certificatMedical.getRefType().getCode();
			}
            List<CertificatMedical> liste = getListCertificatsByType(
                    typeCertificat, idSinistre);
			if (liste != null && !liste.isEmpty()) {
				if (liste.size() == 1) {
					CertificatMedical certificat = (CertificatMedical) liste
							.get(0);
					certificat.setValide(true);
				} else if (liste.size() > 1) {
					CertificatMedical lastIpp = liste.get(0);
					if (lastIpp.getIPP() == null) {
						lastIpp.setIPP(new Double(0));
					}
					for (CertificatMedical certificat : liste) {
						// Ipp de certificat > dernier IPP
						if (certificat.getIPP() != null
								&& certificat.getIPP().compareTo(
										lastIpp.getIPP()) > 0) {
							lastIpp = certificat;
							// Ipp de certificat = dernier IPP => Comparer date
							// certificat
						} else if (certificat.getIPP() != null
								&& certificat.getIPP().compareTo(
										lastIpp.getIPP()) == 0) {
							if (certificat.getDateCertificat() != null
									&& lastIpp.getDateCertificat() != null
									&& certificat
											.getDateCertificat()
											.compareTo(
													lastIpp.getDateCertificat()) >= 0) {

								lastIpp = certificat;
							} else if (certificat.getDateCertificat() != null
									&& lastIpp.getDateCertificat() == null) {
								lastIpp = certificat;
							}
						}
					}
					for (CertificatMedical certificat : liste) {
						if (certificat.getId().equals(lastIpp.getId())) {
							if (certificat.getValide() == null
									|| (certificat.getValide() != null && !certificat
											.getValide())) {
								certificat.setValide(true);
								creerHistorique(certificat, MOTIFVALIDATION);
								dao.updateObject(certificat);
							}
						} else {
							if (certificat.getValide() == null
									|| (certificat.getValide() != null && certificat
											.getValide())) {
								certificat.setValide(false);
								creerHistorique(certificat, MOTIFINVALIDATION);
								dao.updateObject(certificat);
							}
						}

						numSucces++;
					}
				}
				return numSucces == liste.size();
			}
		} catch (PersistenceException e) {
			logger.error(EXP_INVALIDATION_CERTIFICAT, e);
			throw new FonctionnelleException(EXP_INVALIDATION_CERTIFICAT,e);
		}
		return false;
	}

	public List getHistoriqueCertificat(CertificatMedical certificat)
			throws FonctionnelleException {
		try {
			if (certificat == null || certificat.getId() == null
					|| certificat.getId() == 0) {
				throw new FonctionnelleException(EXP_CHAMP_REQUIRED + " ID.");
			}
            String hql = "from CertificatMedicalHistorique where refCertificat.id="
					+ certificat.getId();
            return getSession().createQuery(hql).list();
		} catch (PersistenceException e) {
			logger.error(EXP_RECHERCHE, e);
			throw new FonctionnelleException(EXP_RECHERCHE,e);
		}
	}

	public List<CertificatMedical> ajouterListeCertificats(
			List<CertificatMedical> listCertificatMedical)
			throws FonctionnelleException {
		List<CertificatMedical> resultList = null;
		if (listCertificatMedical != null && !listCertificatMedical.isEmpty()) {
			resultList = new ArrayList<CertificatMedical>();
			for (CertificatMedical certificat : listCertificatMedical) {
				resultList.add(creerCertificatMedical(certificat));
			}
		}
		return resultList;
	}

	public List<CertificatMedical> invaliderListeCertificats(
			List<CertificatMedical> listCertificatMedical)
			throws FonctionnelleException {
		if (listCertificatMedical != null && !listCertificatMedical.isEmpty()) {
			for (CertificatMedical certificat : listCertificatMedical) {
				String typeCertificat = certificat.getRefType().getCode();
				if (typeCertificat.equals(TYPE_CERTIFICAT_CONSTATATION)
						|| typeCertificat.equals(TYPE_CERTIFICAT_AGGRAVATION)
						|| typeCertificat.equals(TYPE_CERTIFICAT_RECHUTE)) {
					if (certificat.getValide()) {
						invaliderCertificatParId(certificat);
					}
				} else if (typeCertificat.equals(TYPE_CERTIFICAT_GUERISON)
						|| typeCertificat.equals(TYPE_CERTIFICAT_CONTREVISITE)
						|| typeCertificat
								.equals(TYPE_CERTIFICAT_EXPERTISEJUDICIAIRE)
						|| typeCertificat
								.equals(TYPE_CERTIFICAT_EXPERTISECONSEIL)) {
					invaliderCertificatParIpp(certificat);
				}
			}
		}
		return listCertificatMedical;
	}

	public Double getIppValide(Long idSinistre) throws FonctionnelleException {
		Double ippValide = Double.valueOf(0);
		List<CertificatMedical> listeCertificatValide = getValideCertificatBySin(idSinistre);
		if (listeCertificatValide != null && !listeCertificatValide.isEmpty()) {
			int i = 0;
			boolean expertiseTrouve = false;
			boolean guerisonTrouve = false;
			CertificatMedical certifvalidipp = null;
			while (i < listeCertificatValide.size() && !expertiseTrouve) {
				if (listeCertificatValide.get(i).getRefType() != null
						&& !StringUtils.isEmpty(listeCertificatValide.get(i)
								.getRefType().getCode())) {
					String typeCertificatActuel = listeCertificatValide.get(i)
							.getRefType().getCode();
					if (typeCertificatActuel
							.equals(TYPE_CERTIFICAT_EXPERTISEJUDICIAIRE)) {
						certifvalidipp = listeCertificatValide.get(i);
						expertiseTrouve = true;
					} else if (typeCertificatActuel
							.equals(TYPE_CERTIFICAT_GUERISON)) {
						certifvalidipp = listeCertificatValide.get(i);
						guerisonTrouve = true;
					} else if (typeCertificatActuel
							.equals(IConstantes.TYPE_CERTIFICAT_CONTREVISITE)
							&& !guerisonTrouve) {
						if (listeCertificatValide.get(i).getIPP() > listeCertificatValide
								.get(i).getRefSinistre().getIpp()) {
							certifvalidipp = listeCertificatValide.get(i);
						}
					}
				}
				i++;
			}
			if (certifvalidipp != null) {
				ippValide = certifvalidipp.getIPP();
			}
		}
		return ippValide;
	}
}
