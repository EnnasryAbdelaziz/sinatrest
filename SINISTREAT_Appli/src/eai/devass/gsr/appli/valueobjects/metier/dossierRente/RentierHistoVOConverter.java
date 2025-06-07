package eai.devass.gsr.appli.valueobjects.metier.dossierRente;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.entites.EntiteException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.log.ILog;
import ma.co.omnidata.framework.services.validation.IValidator;

import org.apache.commons.lang.StringUtils;

import eai.devass.gsr.appli.modele.metier.dossierRente.RentierHisto;
import eai.devass.gsr.appli.utile.TypeConverter;
import eai.devass.sinistreat.appli.utils.Fonctions;

/**
 * Value Object Converter de Rentier
 * 
 * @author Nom Prenom (email)
 */
public class RentierHistoVOConverter implements IValueObjectConverter {

	public IValidator getValidator() {
		return null;
	}

	public List convertValueObjectToItems(Object ovo)
			throws ValidationException {
		RentierHistoVO vo = (RentierHistoVO) ovo;
		this.doValider(vo);
		RentierHisto item = new RentierHisto();
		voToItem(vo, item);
		// Infos version if set
		if (vo.getIdHistorisable() != 0) {
			item.setId(vo.getIdHistorisable());
		}
		List<RentierHisto> itemList = new ArrayList<RentierHisto>();
		itemList.add(item);
		return itemList;
	}

	public Object convertItemsToValueObject(List itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		RentierHistoVO vo = new RentierHistoVO();
		try {
			if (itemList.get(0) instanceof RentierHisto) {

				itemToVo(vo, (RentierHisto) itemList.get(0), itemList);
			}
		} catch (Exception e) {
			ILog logger = (ILog) ServicesFactory.getService(ILog.class);
			logger.error(e.getMessage());

		}

		return vo;
	}

	public List<IValueObject> convertItemsToVos(List<IEntite> itemList)
			throws EntiteException {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i) instanceof RentierHisto) {
				RentierHistoVO vo = new RentierHistoVO();
				itemToVo(vo, (RentierHisto) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}

	public void itemToVo(RentierHistoVO vo, RentierHisto item, List itemList)
			throws EntiteException {
		vo.setId(TypeConverter.getInstance().longToString(item.getId()));
		vo.setLienParente(TypeConverter.getInstance().longToString(
				item.getLienParente()));
		vo.setNumeroCIN(item.getNumeroCIN());
		vo.setDateNaissance(TypeConverter.getInstance().calendarToString(
				item.getDateNaissance()));
		vo.setSituationRentier(TypeConverter.getInstance().longToString(
				item.getSituationRentier()));
		vo.setNationalite(TypeConverter.getInstance().longToString(
				item.getNationalite()));
		vo.setNumeroTelephone(item.getNumeroTelephone());
		vo.setNumeroGSM(item.getNumeroGSM());
		vo.setEmail(item.getEmail());
		vo.setAdresse(item.getAdresse());
		vo.setCodePostale(TypeConverter.getInstance().doubleToString(
				item.getCodePostale()));
		vo.setVille((item.getVille()));
		vo.setPays(item.getPays());
		vo.setProthese(TypeConverter.getInstance().booleanToString(
				item.getProthese()));
		vo.setRentierARisque(TypeConverter.getInstance().booleanToString(
				item.getRentierARisque()));
		vo.setRenteConforme(TypeConverter.getInstance().booleanToString(
				item.getRenteConforme()));
		vo.setDateEtat(TypeConverter.getInstance().calendarToString(
				item.getDateEtat()));
		vo.setValidation(TypeConverter.getInstance().booleanToString(
				item.getValidation()));
		vo.setCapitalConstitutif(TypeConverter.getInstance().doubleToString(
				item.getCapitalConstitutif()));
		vo.setDateConstitution(TypeConverter.getInstance().calendarToString(
				item.getDateConstitution()));
		vo.setDateDepartRente(TypeConverter.getInstance().calendarToString(
				item.getDateDepartRente()));
		vo.setIppTauxRente(TypeConverter.getInstance().doubleToString(
				item.getIppTauxRente()));
		vo.setReserveMathematique(TypeConverter.getInstance().doubleToString(
				item.getReserveMathematique()));
		vo.setDateValidation(TypeConverter.getInstance().calendarToString(
				item.getDateValidation()));
		vo.setSalaireUtile(TypeConverter.getInstance().doubleToString(
				item.getSalaireUtile()));
		vo.setApprobationQuittance(TypeConverter.getInstance().booleanToString(
				item.getApprobationQuittance()));
		vo.setMontantRenteTrimestrielle(TypeConverter.getInstance()
				.doubleToString(item.getMontantRenteTrimestrielle()));
		vo.setPeriodicite(item.getPeriodicite());
		vo.setTuteur(TypeConverter.getInstance().booleanToString(
				item.getTuteur()));
		vo.setOrphelinPur(TypeConverter.getInstance().booleanToString(
				item.getOrphelinPur()));
		vo.setCivilite(item.getCivilite());
		vo.setNom(item.getNom());
		vo.setPrenom(item.getPrenom());
		vo.setSexe(item.getSexe());
		vo.setObservationConforme(item.getObservationConforme());
		vo.setDateCreation(TypeConverter.getInstance().calendarToString(
				item.getDateCreation()));

		vo.setDateModification(TypeConverter.getInstance().calendarToString(
				item.getDateModification()));
		vo.setIdRentier(TypeConverter.getInstance().longToString(
				item.getIdRentier()));

		vo.setRefAyantDroit(TypeConverter.getInstance().longToString(
				item.getRefAyantDroit()));
		vo.setRefDossierRente(TypeConverter.getInstance().longToString(
				item.getRefDossierRente()));
		vo.setRefEtatRentier(TypeConverter.getInstance().longToString(
				item.getRefEtatRentier()));
		vo.setRefModePayement(TypeConverter.getInstance().longToString(
				item.getRefModePayement()));
		vo.setRefTuteur(TypeConverter.getInstance().longToString(
				item.getRefTuteur()));
		vo.setRefVictime(TypeConverter.getInstance().longToString(
				item.getRefVictime()));
		vo.setUserSas(item.getUserSas());

		if (Fonctions.isNotEmpty(item.getMotifEtat())) {
			vo.setMotifEtat(item.getMotifEtat());
		}

		/**
		 * Evo Lot1
		 */
		vo.setUserSasModificateur(item.getUserSasModificateur());
		vo.setArrerageAvantConstitution(TypeConverter.getInstance()
				.doubleToString(item.getArrerageAvantConstitution()));
		vo.setArrerages(TypeConverter.getInstance().doubleToString(
				item.getArrerages()));
		
		vo.setDonneeConforme(TypeConverter.getInstance().booleanToString(
				item.getDonneeConforme()));
		vo.setObservationDonneeConforme(item.getObservationDonneeConforme());
		/**
		 * Fin Evo
		 */

	}

	public void itemToVoWithoutRefDossierRente(RentierHistoVO vo,
			RentierHisto item, List itemList) {
		vo.setId(TypeConverter.getInstance().longToString(item.getId()));

		vo.setLienParente(TypeConverter.getInstance().longToString(
				item.getLienParente()));
		vo.setNumeroCIN(item.getNumeroCIN());
		vo.setDateNaissance(TypeConverter.getInstance().calendarToString(
				item.getDateNaissance()));
		vo.setSituationRentier(TypeConverter.getInstance().longToString(
				item.getNationalite()));
		vo.setNationalite(TypeConverter.getInstance().longToString(
				item.getNationalite()));
		vo.setNumeroTelephone(item.getNumeroTelephone());
		vo.setNumeroGSM(item.getNumeroGSM());
		vo.setEmail(item.getEmail());
		vo.setAdresse(item.getAdresse());
		vo.setCodePostale(TypeConverter.getInstance().doubleToString(
				item.getCodePostale()));
		vo.setVille(item.getVille());
		vo.setPays(item.getPays());
		vo.setProthese(TypeConverter.getInstance().booleanToString(
				item.getProthese()));
		vo.setRentierARisque(TypeConverter.getInstance().booleanToString(
				item.getRentierARisque()));
		vo.setRenteConforme(TypeConverter.getInstance().booleanToString(
				item.getRenteConforme()));
		vo.setDateEtat(TypeConverter.getInstance().calendarToString(
				item.getDateEtat()));
		vo.setValidation(TypeConverter.getInstance().booleanToString(
				item.getValidation()));
		vo.setCapitalConstitutif(TypeConverter.getInstance().doubleToString(
				item.getCapitalConstitutif()));
		vo.setDateConstitution(TypeConverter.getInstance().calendarToString(
				item.getDateConstitution()));
		vo.setDateDepartRente(TypeConverter.getInstance().calendarToString(
				item.getDateDepartRente()));
		vo.setIppTauxRente(TypeConverter.getInstance().doubleToString(
				item.getIppTauxRente()));
		vo.setReserveMathematique(TypeConverter.getInstance().doubleToString(
				item.getReserveMathematique()));
		vo.setDateValidation(TypeConverter.getInstance().calendarToString(
				item.getDateValidation()));
		vo.setSalaireUtile(TypeConverter.getInstance().doubleToString(
				item.getSalaireUtile()));
		vo.setApprobationQuittance(TypeConverter.getInstance().booleanToString(
				item.getApprobationQuittance()));
		vo.setMontantRenteTrimestrielle(TypeConverter.getInstance()
				.doubleToString(item.getMontantRenteTrimestrielle()));
		vo.setPeriodicite(item.getPeriodicite());
		vo.setTuteur(TypeConverter.getInstance().booleanToString(
				item.getTuteur()));
		vo.setOrphelinPur(TypeConverter.getInstance().booleanToString(
				item.getOrphelinPur()));
		vo.setCivilite(item.getCivilite());
		vo.setNom(item.getNom());
		vo.setPrenom(item.getPrenom());
		vo.setSexe(item.getSexe());
		vo.setObservationConforme(item.getObservationConforme());
		vo.setDateCreation(TypeConverter.getInstance().calendarToString(
				item.getDateCreation()));

		vo.setDateModification(TypeConverter.getInstance().calendarToString(
				item.getDateModification()));
		vo.setIdRentier(TypeConverter.getInstance().longToString(
				item.getIdRentier()));
		vo.setUserSas(item.getUserSas());

		vo.setRefAyantDroit(TypeConverter.getInstance().longToString(
				item.getRefAyantDroit()));
		vo.setRefDossierRente(TypeConverter.getInstance().longToString(
				item.getRefDossierRente()));
		vo.setRefEtatRentier(TypeConverter.getInstance().longToString(
				item.getRefEtatRentier()));
		vo.setRefModePayement(TypeConverter.getInstance().longToString(
				item.getRefModePayement()));
		vo.setRefTuteur(TypeConverter.getInstance().longToString(
				item.getRefTuteur()));
		vo.setRefVictime(TypeConverter.getInstance().longToString(
				item.getRefVictime()));

		if (Fonctions.isNotEmpty(item.getMotifEtat())) {
			vo.setMotifEtat(item.getMotifEtat());
		}

		/**
		 * Evo Lot1
		 */
		vo.setUserSasModificateur(item.getUserSasModificateur());
		vo.setArrerageAvantConstitution(TypeConverter.getInstance()
				.doubleToString(item.getArrerageAvantConstitution()));
		vo.setArrerages(TypeConverter.getInstance().doubleToString(
				item.getArrerages()));
		
		vo.setDonneeConforme(TypeConverter.getInstance().booleanToString(
				item.getDonneeConforme()));
		vo.setObservationDonneeConforme(item.getObservationDonneeConforme());
		/**
		 * Fin Evo
		 */

	}

	public void itemToVoWithoutRefVictime(RentierHistoVO vo, RentierHisto item) {
		vo.setId(TypeConverter.getInstance().longToString(item.getId()));
		vo.setLienParente(TypeConverter.getInstance().longToString(
				item.getLienParente()));
		vo.setNumeroCIN(item.getNumeroCIN());
		vo.setDateNaissance(TypeConverter.getInstance().calendarToString(
				item.getDateNaissance()));
		vo.setSituationRentier(TypeConverter.getInstance().longToString(
				item.getNationalite()));
		vo.setNationalite(TypeConverter.getInstance().longToString(
				item.getNationalite()));
		vo.setNumeroTelephone(item.getNumeroTelephone());
		vo.setNumeroGSM(item.getNumeroGSM());
		vo.setEmail(item.getEmail());
		vo.setAdresse(item.getAdresse());
		vo.setCodePostale(TypeConverter.getInstance().doubleToString(
				item.getCodePostale()));
		vo.setVille(item.getVille());
		vo.setPays(item.getPays());
		vo.setProthese(TypeConverter.getInstance().booleanToString(
				item.getProthese()));
		vo.setRentierARisque(TypeConverter.getInstance().booleanToString(
				item.getRentierARisque()));
		vo.setRenteConforme(TypeConverter.getInstance().booleanToString(
				item.getRenteConforme()));
		vo.setDateEtat(TypeConverter.getInstance().calendarToString(
				item.getDateEtat()));
		vo.setValidation(TypeConverter.getInstance().booleanToString(
				item.getValidation()));
		vo.setCapitalConstitutif(TypeConverter.getInstance().doubleToString(
				item.getCapitalConstitutif()));
		vo.setDateConstitution(TypeConverter.getInstance().calendarToString(
				item.getDateConstitution()));
		vo.setDateDepartRente(TypeConverter.getInstance().calendarToString(
				item.getDateDepartRente()));
		vo.setIppTauxRente(TypeConverter.getInstance().doubleToString(
				item.getIppTauxRente()));
		vo.setReserveMathematique(TypeConverter.getInstance().doubleToString(
				item.getReserveMathematique()));
		vo.setDateValidation(TypeConverter.getInstance().calendarToString(
				item.getDateValidation()));
		vo.setSalaireUtile(TypeConverter.getInstance().doubleToString(
				item.getSalaireUtile()));
		vo.setApprobationQuittance(TypeConverter.getInstance().booleanToString(
				item.getApprobationQuittance()));
		vo.setMontantRenteTrimestrielle(TypeConverter.getInstance()
				.doubleToString(item.getMontantRenteTrimestrielle()));
		vo.setPeriodicite(item.getPeriodicite());
		vo.setTuteur(TypeConverter.getInstance().booleanToString(
				item.getTuteur()));
		vo.setOrphelinPur(TypeConverter.getInstance().booleanToString(
				item.getOrphelinPur()));
		vo.setCivilite(item.getCivilite());
		vo.setNom(item.getNom());
		vo.setPrenom(item.getPrenom());
		vo.setSexe(item.getSexe());
		vo.setObservationConforme(item.getObservationConforme());
		vo.setDateCreation(TypeConverter.getInstance().calendarToString(
				item.getDateCreation()));

		vo.setDateModification(TypeConverter.getInstance().calendarToString(
				item.getDateModification()));
		vo.setIdRentier(TypeConverter.getInstance().longToString(
				item.getIdRentier()));
		vo.setUserSas(item.getUserSas());

		vo.setRefAyantDroit(TypeConverter.getInstance().longToString(
				item.getRefAyantDroit()));
		vo.setRefDossierRente(TypeConverter.getInstance().longToString(
				item.getRefDossierRente()));
		vo.setRefEtatRentier(TypeConverter.getInstance().longToString(
				item.getRefEtatRentier()));
		vo.setRefModePayement(TypeConverter.getInstance().longToString(
				item.getRefModePayement()));
		vo.setRefTuteur(TypeConverter.getInstance().longToString(
				item.getRefTuteur()));
		vo.setRefVictime(TypeConverter.getInstance().longToString(
				item.getRefVictime()));

		if (Fonctions.isNotEmpty(item.getMotifEtat())) {
			vo.setMotifEtat(item.getMotifEtat());
		}

		/**
		 * Evo Lot1
		 */
		vo.setUserSasModificateur(item.getUserSasModificateur());
		vo.setArrerageAvantConstitution(TypeConverter.getInstance()
				.doubleToString(item.getArrerageAvantConstitution()));
		vo.setArrerages(TypeConverter.getInstance().doubleToString(
				item.getArrerages()));
		
		vo.setDonneeConforme(TypeConverter.getInstance().booleanToString(
				item.getDonneeConforme()));
		vo.setObservationDonneeConforme(item.getObservationDonneeConforme());
		/**
		 * Fin Evo
		 */

	}

	public void voToItem(RentierHistoVO vo, RentierHisto item) {
		item.setId(TypeConverter.getInstance().stringToLong(vo.getId()));
		if (vo.getSituationRentier() != null
				&& !"".equals(vo.getSituationRentier())) {
			item.setSituationRentier(TypeConverter.getInstance().stringToLong(
					vo.getSituationRentier()));
		}
		if (vo.getLienParente() != null
				&& !"".equals(vo.getLienParente().trim())) {
			item.setLienParente(TypeConverter.getInstance().stringToLong(
					vo.getLienParente()));
		}
		item.setNumeroCIN(vo.getNumeroCIN());
		if (vo.getDateNaissance() != null && !"".equals(vo.getDateNaissance())) {
			item.setDateNaissance(TypeConverter.getInstance().stringToCalendar(
					vo.getDateNaissance()));
		}
		if (vo.getNationalite() != null && !vo.getNationalite().equals("")) {
			item.setNationalite(TypeConverter.getInstance().stringToLong(
					vo.getNationalite()));
		}
		item.setNumeroTelephone(vo.getNumeroTelephone());
		item.setNumeroGSM(vo.getNumeroGSM());
		item.setEmail(vo.getEmail());
		item.setAdresse(vo.getAdresse());
		if (vo.getCodePostale() != null && !vo.getCodePostale().equals("")) {
			item.setCodePostale(TypeConverter.getInstance().stringToDouble(
					vo.getCodePostale()));
		}
		if (vo.getVille() != null && !vo.getVille().equals("")) {
			item.setVille(vo.getVille());
		}
		if (vo.getPays() != null && !vo.getPays().equals("")) {
			item.setPays(vo.getPays());
		}
		if (vo.getProthese() != null && !vo.getProthese().equals("")) {
			item.setProthese(TypeConverter.getInstance().stringToBoolean(
					vo.getProthese()));
		}
		if (vo.getRentierARisque() != null
				&& !vo.getRentierARisque().equals("")) {
			item.setRentierARisque(TypeConverter.getInstance().stringToBoolean(
					vo.getRentierARisque()));
		}
		if (vo.getRenteConforme() != null && !vo.getRenteConforme().equals("")) {
			item.setRenteConforme(TypeConverter.getInstance().stringToBoolean(
					vo.getRenteConforme()));
		}
		if (vo.getDateEtat() != null && !vo.getDateEtat().equals("")) {
			item.setDateEtat(TypeConverter.getInstance().stringToCalendar(
					vo.getDateEtat()));
		}
		if (vo.getValidation() != null && !vo.getValidation().equals("")) {
			item.setValidation(TypeConverter.getInstance().stringToBoolean(
					vo.getValidation()));
		}
		if (vo.getCapitalConstitutif() != null
				&& !vo.getCapitalConstitutif().equals("")) {
			item.setCapitalConstitutif(TypeConverter.getInstance()
					.stringToDouble(
							StringUtils.replace(StringUtils.replace(
									vo.getCapitalConstitutif(), " ", ""), ",",
									".")));
		}
		if (vo.getDateConstitution() != null
				&& !vo.getDateConstitution().equals("")) {
			item.setDateConstitution(TypeConverter.getInstance()
					.stringToCalendar(vo.getDateConstitution()));
		}
		if (vo.getDateDepartRente() != null
				&& !vo.getDateDepartRente().equals("")) {
			item.setDateDepartRente(TypeConverter.getInstance()
					.stringToCalendar(vo.getDateDepartRente()));
		}
		if (vo.getIppTauxRente() != null && !vo.getIppTauxRente().equals("")) {
			item.setIppTauxRente(TypeConverter.getInstance().stringToDouble(
					StringUtils.replace(
							StringUtils.replace(vo.getIppTauxRente(), " ", ""),
							",", ".")));
		}
		if (vo.getReserveMathematique() != null
				&& !vo.getReserveMathematique().equals("")) {
			item.setReserveMathematique(TypeConverter.getInstance()
					.stringToDouble(
							StringUtils.replace(StringUtils.replace(
									vo.getReserveMathematique(), " ", ""), ",",
									".")));
		}
		if (vo.getDateValidation() != null
				&& !vo.getDateValidation().equals("")) {
			item.setDateValidation(TypeConverter.getInstance()
					.stringToCalendar(vo.getDateValidation()));
		}
		if (vo.getSalaireUtile() != null && !vo.getSalaireUtile().equals("")) {
			item.setSalaireUtile(TypeConverter.getInstance().stringToDouble(
					StringUtils.replace(
							StringUtils.replace(vo.getSalaireUtile(), " ", ""),
							",", ".")));
		}
		if (vo.getApprobationQuittance() != null
				&& !vo.getApprobationQuittance().equals("")) {
			item.setApprobationQuittance(TypeConverter.getInstance()
					.stringToBoolean(vo.getApprobationQuittance()));
		}
		if (vo.getMontantRenteTrimestrielle() != null
				&& !vo.getMontantRenteTrimestrielle().equals("")) {
			item.setMontantRenteTrimestrielle(TypeConverter.getInstance()
					.stringToDouble(
							StringUtils.replace(
									StringUtils.replace(
											vo.getMontantRenteTrimestrielle(),
											" ", ""), ",", ".")));
		}
		item.setPeriodicite(vo.getPeriodicite());
		if (vo.getTuteur() != null && !vo.getTuteur().equals("")) {
			item.setTuteur(TypeConverter.getInstance().stringToBoolean(
					vo.getTuteur()));
		}
		if (vo.getOrphelinPur() != null && !vo.getOrphelinPur().equals("")) {
			item.setOrphelinPur(TypeConverter.getInstance().stringToBoolean(
					vo.getOrphelinPur()));
		}
		item.setCivilite(vo.getCivilite());
		item.setNom(vo.getNom());
		item.setPrenom(vo.getPrenom());
		item.setSexe(vo.getSexe());
		item.setObservationConforme(vo.getObservationConforme());
		if (vo.getDateCreation() != null && !vo.getDateCreation().equals("")) {
			item.setDateCreation(TypeConverter.getInstance().stringToCalendar(
					vo.getDateCreation()));
		}

		if (vo.getDateModification() != null
				&& !vo.getDateModification().equals("")) {
			item.setDateModification(TypeConverter.getInstance()
					.stringToCalendar(vo.getDateModification()));
		}
		if (vo.getIdRentier() != null && !vo.getIdRentier().equals("")) {
			item.setIdRentier(TypeConverter.getInstance().stringToLong(
					vo.getIdRentier()));
		}

		if (vo.getUserSas() != null && !vo.getUserSas().equals("")) {
			item.setUserSas(vo.getUserSas());
		}

		if (vo.getRefAyantDroit() != null && !vo.getRefAyantDroit().equals("")) {
			item.setRefAyantDroit(TypeConverter.getInstance().stringToLong(
					vo.getRefAyantDroit()));
		}
		if (vo.getRefDossierRente() != null
				&& !vo.getRefDossierRente().equals("")) {
			item.setRefDossierRente(TypeConverter.getInstance().stringToLong(
					vo.getRefDossierRente()));
		}
		if (vo.getRefEtatRentier() != null
				&& !vo.getRefEtatRentier().equals("")) {
			item.setRefEtatRentier(TypeConverter.getInstance().stringToLong(
					vo.getRefEtatRentier()));
		}
		if (vo.getRefModePayement() != null
				&& !vo.getRefModePayement().equals("")) {
			item.setRefModePayement(TypeConverter.getInstance().stringToLong(
					vo.getRefModePayement()));
		}
		if (vo.getRefTuteur() != null && !vo.getRefTuteur().equals("")) {
			item.setRefTuteur(TypeConverter.getInstance().stringToLong(
					vo.getRefTuteur()));
		}
		if (vo.getRefVictime() != null && !vo.getRefVictime().equals("")) {
			item.setRefVictime(TypeConverter.getInstance().stringToLong(
					vo.getRefVictime()));
		}

		if (Fonctions.isNotEmpty(vo.getMotifEtat())) {
			item.setMotifEtat(vo.getMotifEtat());
		}

		/**
		 * Evo Lot1
		 */
		item.setUserSasModificateur(vo.getUserSasModificateur());
		if (vo.getArrerageAvantConstitution() != null
				&& !vo.getArrerageAvantConstitution().equals("")) {
			item.setArrerageAvantConstitution(TypeConverter.getInstance()
					.stringToDouble(
							StringUtils.replace(
									StringUtils.replace(
											vo.getArrerageAvantConstitution(),
											" ", ""), ",", ".")));
		}
		if (vo.getArrerages() != null && !vo.getArrerages().equals("")) {
			item.setArrerages(TypeConverter.getInstance().stringToDouble(
					StringUtils.replace(
							StringUtils.replace(vo.getArrerages(), " ", ""),
							",", ".")));
		}
		
        if (vo.getDonneeConforme() != null
                && !vo.getDonneeConforme().equals("")) {
			item.setDonneeConforme(TypeConverter.getInstance().stringToBoolean(
					vo.getDonneeConforme()));
		}
		item.setObservationDonneeConforme(vo.getObservationDonneeConforme());
		/**
		 * Fin Evo
		 */

	}

	protected void doValider(RentierHistoVO vo) throws ValidationException {

	}
}
