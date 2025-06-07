package eai.devass.gsr.appli.manager.metier.dossierRente;

import ma.co.omnidata.framework.services.entites.EntiteException;
import ma.co.omnidata.framework.services.entites.EntiteManagerAbst;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.ProcessEntiteException;
import ma.co.omnidata.framework.services.entites.ValidateEntiteException;
import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;
import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRenteHisto;

/**
 * Manager de l ' entité DossierRente
 * 
 * @author Nom Prenom (email)
 */
public class DossierRenteHistoManager extends EntiteManagerAbst {

	/**
	 * Completude de l ' objet avant sa creation
	 * 
	 * @param entite
	 *            lentité à completer
	 * @return returns lentité completé
	 * @throws ProcessEntiteException
	 *             problème lors de la complétude de l' entité
	 * @throws EntiteException
	 */

	protected IEntite doProcessCreateEntity(IEntite entite)
			throws ProcessEntiteException, EntiteException {
		DossierRenteHisto dossierRenteHisto = (DossierRenteHisto) entite;

		// dossierRente.setDateCreation(DateUtile.dateCalendarCourante());

		return dossierRenteHisto;

	}

	/**
	 * Validation de l'object avant sa suppression
	 * 
	 * @param entite
	 *            l' entité à valider avant sa suppression
	 * @throws ValidateEntiteException
	 *             Probleme lors de la validation de l 'entité
	 * @throws EntiteException
	 *             Probleme lors de la persistence
	 */
	protected void doValidateDeleteEntity(IEntite entite)
			throws ValidateEntiteException, EntiteException {
	}

	/**
	 * Validation de l'object avant sa creation
	 * 
	 * @param entite
	 *            l' entité à valider avant sa creation
	 * @throws ValidateEntiteException
	 *             Probleme lors de la validation de l 'entité
	 * @throws EntiteException
	 *             Probleme lors de la persistence
	 */
	protected void doValidateCreateEntity(IEntite entite)
			throws ValidateEntiteException, EntiteException {
		// if (((DossierRente) entite).getRefSinistre() == null) {
		// ValidateEntiteException ve = new ValidateEntiteException(
		// "FIELD.OBLIGATOIRE");
		// ve.setValues(new String[] { "Sinistre" });
		// throw ve;
		//
		// }

	}

	/**
	 * Validation de l' objet avant sa modification
	 * 
	 * @param entite
	 *            L 'entité à valider avant sa modification
	 * @throws ValidateEntiteException
	 *             Probleme lors de la validation de l 'entité
	 * @throws EntiteException
	 *             Probleme lors de la persistence
	 */
	protected void doValidateModifyEntity(IEntite entite)
			throws ValidateEntiteException, EntiteException {
		// if (((DossierRente) entite).getRefSinistre() == null) {
		// ValidateEntiteException ve = new ValidateEntiteException(
		// "FIELD.OBLIGATOIRE");
		// ve.setValues(new String[] { "Sinistre" });
		// throw ve;
		// }

	}

	/**
	 * Methode qui habille une entité avec ces entités refs completes pou ne
	 * pas ecraser les valeurs des refs avec des nulls
	 * 
	 * @param entite
	 *            lentité à habiller
	 * @throws EntiteException
	 *             Probleme lors de la persistence
	 */
	protected IEntite habiller(IEntite entite) throws EntiteException {
		DossierRenteHisto dossierRenteHisto = (DossierRenteHisto) entite;

		// Sinistre refSinistre1 = dossierRente.getRefSinistre();
		// Sinistre refSinistre = new Sinistre();
		//
		// if (refSinistre1 != null)
		// ((IEntite) refSinistre).setId(refSinistre1.getId());
		// if (refSinistre != null && refSinistre1 != null) {
		// if (((IEntite) refSinistre).getId() != 0) {
		// try {
		// refSinistre = new SinistreManager()
		// .getSinistreById(refSinistre.getId());
		// } catch (Exception e) {
		// logger.error("probl�me technique",e);
		// }
		//
		// dossierRente.setRefSinistre(refSinistre);
		// } else {
		// dossierRente.setRefSinistre(refSinistre1);
		// }
		// }
		return dossierRenteHisto;
	}

	/**
	 * Methode qui habille l' entité avant sa modification
	 * 
	 * @param entite
	 *            L 'entite à habiller
	 * @return l' entité habillé
	 * @throws ProcessEntiteException
	 *             problème lors de la complétude de l' entité
	 * @throws EntiteException
	 *             Probleme lors de la persistence
	 */
	protected IEntite doRevampModifyEntity(IEntite entite)
			throws ProcessEntiteException, EntiteException {
		return this.habiller(entite);
	}

	/**
	 * Methode qui habille l' entité avant sa creation
	 * 
	 * @param entite
	 *            L 'entite à habiller
	 * @return l' entité habillé
	 * @throws ProcessEntiteException
	 *             problème lors de la complétude de l' entité
	 * @throws EntiteException
	 *             Probleme lors de la persistence
	 */
	protected IEntite doRevampCreateEntity(IEntite entite)
			throws ProcessEntiteException, EntiteException {
		return this.habiller(entite);
	}

	/**
	 * Completude de l' objet avant sa modification
	 * 
	 * @param entite
	 *            L' entité à completer avant sa modification
	 * @returns L' entité completé
	 * @throws ProcessEntiteException
	 *             problème lors de la complétude de l' entité
	 * @throws EntiteException
	 *             Probleme lors de la persistence
	 */
	protected IEntite doProcessModifyEntity(IEntite entite)
			throws ProcessEntiteException, EntiteException {
		DossierRente dossierRente = (DossierRente) entite;

		return dossierRente;

	}

	// public int doUpdateEtatDossierRente(DossierRente dossierRente,
	// Long numeroRent) throws HibernateException, PersistenceException {
	// try {
	// String hql = "update DossierRente d set d.etat=?, d.dateEtat=?";
	// if (dossierRente.getEtat() == 4) {
	// hql += ", d.dateValidation=? , d.validation=?,d.numeroRente=?";
	// }
	// hql += " where d.id=?";
	// IPersistenceService dao = (IPersistenceService) ServicesFactory
	// .getService(IPersistenceService.class);
	// Query query = ((Session) dao.getSession()).createQuery(hql);
	// query.setDouble(0, dossierRente.getEtat());
	// Date toDaye = new Date();
	// query.setDate(1, toDaye);
	// int i = 2;
	// if (dossierRente.getEtat() == 4) {
	// query.setDate(2, toDaye);
	// query.setBoolean(3, true);
	//
	// query.setLong(4, numeroRent);
	// i = 5;
	// }
	// query.setLong(i, dossierRente.getId());
	// return query.executeUpdate();
	// } catch (Exception e) {
	// // TODO: handle exception
	// }
	// return -1;
	// }
	//
	// public List<DossierRente> doGetDossierRenteBySinistre(
	// DossierRente dossierRente, int numPage, int pageSize)
	// throws ProcessEntiteException, EntiteException, HibernateException,
	// PersistenceException {
	//
	// IPersistenceService dao = (IPersistenceService) ServicesFactory
	// .getService(IPersistenceService.class);
	// String hql = getListDossierRentQuery(dossierRente);
	// hql += " order by id";
	// Query query = ((Session) dao.getSession()).createQuery(hql);
	// if (numPage > 0) {
	// query.setFirstResult((numPage - 1) * pageSize);
	// query.setMaxResults(pageSize);
	// }
	// List<DossierRente> res = query.list();
	// return res;
	// }
	//
	// public DossierRente doGetDossierRenteValiderBySinistre(DossierRente
	// dossierRente)
	// throws ProcessEntiteException, EntiteException, HibernateException,
	// PersistenceException {
	//
	// IPersistenceService dao = (IPersistenceService) ServicesFactory
	// .getService(IPersistenceService.class);
	// String hql = getListDossierRentQueryValider(dossierRente);
	// hql += " order by id";
	// Query query = ((Session) dao.getSession()).createQuery(hql);
	//
	// return (DossierRente) query.uniqueResult();
	// }
	//
	// public int doGetNombreDossierRent(DossierRente dossierRente)
	// throws HibernateException, ProcessEntiteException, EntiteException,
	// PersistenceException {
	// String hql = "Select count(id) "
	// + getListDossierRentQuery(dossierRente);
	// IPersistenceService dao = (IPersistenceService) ServicesFactory
	// .getService(IPersistenceService.class);
	// Query query = ((Session) dao.getSession()).createQuery(hql);
	// return ((Long) query.uniqueResult()).intValue();
	// }
	//
	// public Long doGetLastNumRente(DossierRente dossierRente)
	// throws HibernateException, ProcessEntiteException, EntiteException,
	// PersistenceException {
	// String hql = "select  max(numeroRente) "
	// + getListDossierRentQuery(dossierRente);
	// IPersistenceService dao = (IPersistenceService) ServicesFactory
	// .getService(IPersistenceService.class);
	// Query query = ((Session) dao.getSession()).createQuery(hql);
	// Long res = (Long) (query.uniqueResult());
	// return res;
	//
	// }
	//
	// public List<DossierRente> doGetRentNoValider(DossierRente dossierRente,
	// int numPage, int pageSize) throws HibernateException,
	// ProcessEntiteException, EntiteException, PersistenceException {
	//
	// String hql = getListDossierRentQuery(dossierRente);
	// hql +=
	// " and this.id in (select r.refDossierRente.id from Rentier r where (r.refEtatRentier.id=1 or r.refEtatRentier.id=2) and r.refDossierRente.id=this.id) order by this.id";
	// IPersistenceService dao = (IPersistenceService) ServicesFactory
	// .getService(IPersistenceService.class);
	// Query query = ((Session) dao.getSession()).createQuery(hql);
	// if (numPage > 0) {
	// query.setFirstResult((numPage - 1) * pageSize);
	// query.setMaxResults(pageSize);
	// }
	// List<DossierRente> res = query.list();
	// return res;
	// }

	// public int doGetNbrRentNoValider(DossierRente dossierRente)
	// throws HibernateException, ProcessEntiteException, EntiteException,
	// PersistenceException {
	// String hql = "Select count(this.id) "
	// + getListDossierRentQuery(dossierRente);
	// hql +=
	// " and this.id in (select r.refDossierRente.id from Rentier r where (r.refEtatRentier.id=1 or r.refEtatRentier.id=2) and r.refDossierRente.id=this.id)";
	// IPersistenceService dao = (IPersistenceService) ServicesFactory
	// .getService(IPersistenceService.class);
	// Query query = ((Session) dao.getSession()).createQuery(hql);
	// return ((Long) query.uniqueResult()).intValue();
	// }
	//
	// private String getListDossierRentQuery(DossierRente dossierRente)
	// throws ProcessEntiteException, EntiteException,
	// PersistenceException, HibernateException {
	//
	// SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	// String hql = "from DossierRente this where 1=1";
	// if (dossierRente.getId() != 0) {
	// hql += " and this.id=" + dossierRente.getId();
	// }
	//
	// if (dossierRente.getNumeroRente() != null) {
	// hql += " and this.numeroRente = " + dossierRente.getNumeroRente();
	// }
	// if (dossierRente.getEtat() != 0) {
	// hql += " and this.etat=" + dossierRente.getEtat();
	// }
	// if (Fonctions.isNotEmpty(dossierRente.getCompagnieRente())) {
	// hql += " and this.compagnieRente='"
	// + dossierRente.getCompagnieRente() + "'";
	// }
	// if (dossierRente.getDateCreation() != null) {
	// hql += " and this.dateCreation = to_date('"
	// + dateFormat.format(dossierRente.getDateCreation()
	// .getTime()) + "', 'dd/MM/yyyy HH24:MI:SS')";
	// } else {
	// if (dossierRente.getDateCreationDebut() != null)
	// hql += " and this.dateCreation >= to_date('"
	// + dossierRente.getDateCreationDebut()
	// + " 00:00:00', 'dd/MM/yyyy HH24:MI:SS')";
	// if (dossierRente.getDateCreationFin() != null)
	// hql += " and this.dateCreation <= to_date('"
	// + dossierRente.getDateCreationFin()
	// + " 23:59:59', 'dd/MM/yyyy HH24:MI:SS')";
	// }
	//
	// if (dossierRente.getValidation() != null) {
	// hql += " and this.validation = " + dossierRente.getValidation();
	// }
	// if (dossierRente.getDateEtat() != null) {
	// hql += " and this.dateEtat = to_date('"
	// + dateFormat.format(dossierRente.getDateEtat().getTime())
	// + "', 'dd/MM/yyyy')";
	// }
	//
	// if (dossierRente.getDateValidation() != null) {
	// hql += " and this.dateValidation = to_date('"
	// + dateFormat.format(dossierRente.getDateValidation()
	// .getTime()) + "', 'dd/MM/yyyy')";
	// }
	// if (dossierRente.getIdDossierRente() != null) {
	// hql += " and this.idDossierRente = "
	// + dossierRente.getIdDossierRente();
	// }
	//
	// if (dossierRente.getRefSinistre() != null) {
	//
	// Sinistre refSinistre = dossierRente.getRefSinistre();
	//
	// if (refSinistre.getId() != 0) {
	// hql += " and this.refSinistre.id="
	// + dossierRente.getRefSinistre().getId();
	// }
	// if (Fonctions.isNotEmpty(refSinistre.getNumeroSinistre())) {
	// hql += " and this.refSinistre.numeroSinistre='"
	// + refSinistre.getNumeroSinistre() + "'";
	// }
	// if (Fonctions.isNotEmpty(refSinistre.getNumeroPolice())) {
	// hql += " and this.refSinistre.numeroPolice ='"
	// + refSinistre.getNumeroPolice() + "'";
	// }
	// if (Fonctions.isNotEmpty(refSinistre.getNumeroGrave())) {
	// hql += " and this.refSinistre.numeroGrave ='"
	// + refSinistre.getNumeroGrave() + "'";
	// }
	// if (Fonctions.isNotEmpty(refSinistre.getCodeIntermediaire())) {
	// hql += " and this.refSinistre.codeIntermediaire ="
	// + refSinistre.getCodeIntermediaire() + "'";
	// }
	// if (refSinistre.getRefEvenement() != null) {
	// if (refSinistre.getRefEvenement().getDateAccident() != null) {
	// hql += " and this.refSinistre.refEvenement.dateAccident = to_date('"
	// + dateFormat.format(refSinistre.getRefEvenement()
	// .getDateAccident().getTime())
	// + "', 'dd/MM/yyyy')";
	// }
	// }
	// if (refSinistre.getRefVictime() != null) {
	// Victime refVictime = refSinistre.getRefVictime();
	// if (Fonctions.isNotEmpty(refVictime.getNom())) {
	// hql += " and upper(this.refSinistre.refVictime.nom) like '%"
	// + StringUtils.upperCase(refVictime.getNom().trim())
	// + "%'";
	// }
	// if (Fonctions.isNotEmpty(refVictime.getNumeroCIN())) {
	// hql += " and this.refSinistre.refVictime.numeroCIN = '"
	// + refVictime.getNumeroCIN().trim() + "'";
	// }
	// }
	// }
	//
	// return hql;
	// }
	//
	// private String getListDossierRentQueryValider(DossierRente dossierRente)
	// throws ProcessEntiteException, EntiteException,
	// PersistenceException, HibernateException {
	//
	// String hql = "from DossierRente this where 1=1";
	// if (dossierRente.getId() != 0) {
	// hql += " and this.id=" + dossierRente.getId();
	// }
	//
	// if (dossierRente.getNumeroRente() != null) {
	// hql += " and this.numeroRente = " + dossierRente.getNumeroRente();
	// }
	// if (dossierRente.getEtat() != 0) {
	// hql += " and this.etat=" + dossierRente.getEtat();
	// }
	// if (Fonctions.isNotEmpty(dossierRente.getCompagnieRente())) {
	// hql += " and this.compagnieRente='"
	// + dossierRente.getCompagnieRente() + "'";
	// }
	//
	//
	// if (dossierRente.getValidation() != null) {
	// hql += " and this.validation = " + dossierRente.getValidation();
	// }
	// if (dossierRente.getIdDossierRente() != null) {
	// hql += " and this.idDossierRente = "
	// + dossierRente.getIdDossierRente();
	// }
	//
	// if (dossierRente.getRefSinistre() != null) {
	//
	// Sinistre refSinistre = dossierRente.getRefSinistre();
	//
	// if (refSinistre.getId() != 0) {
	// hql += " and this.refSinistre.id="
	// + dossierRente.getRefSinistre().getId();
	// }
	// if (Fonctions.isNotEmpty(refSinistre.getNumeroSinistre())) {
	// hql += " and this.refSinistre.numeroSinistre='"
	// + refSinistre.getNumeroSinistre() + "'";
	// }
	// if (Fonctions.isNotEmpty(refSinistre.getNumeroPolice())) {
	// hql += " and this.refSinistre.numeroPolice ='"
	// + refSinistre.getNumeroPolice() + "'";
	// }
	// if (Fonctions.isNotEmpty(refSinistre.getNumeroGrave())) {
	// hql += " and this.refSinistre.numeroGrave ='"
	// + refSinistre.getNumeroGrave() + "'";
	// }
	// if (Fonctions.isNotEmpty(refSinistre.getCodeIntermediaire())) {
	// hql += " and this.refSinistre.codeIntermediaire ="
	// + refSinistre.getCodeIntermediaire() + "'";
	// }
	// if (refSinistre.getRefVictime() != null) {
	// Victime refVictime = refSinistre.getRefVictime();
	// if (Fonctions.isNotEmpty(refVictime.getNom())) {
	// hql += " and upper(this.refSinistre.refVictime.nom) like '%"
	// + StringUtils.upperCase(refVictime.getNom().trim())
	// + "%'";
	// }
	// if (Fonctions.isNotEmpty(refVictime.getNumeroCIN())) {
	// hql += " and this.refSinistre.refVictime.numeroCIN = '"
	// + refVictime.getNumeroCIN().trim() + "'";
	// }
	// }
	// }
	//
	// return hql;
	// }
}