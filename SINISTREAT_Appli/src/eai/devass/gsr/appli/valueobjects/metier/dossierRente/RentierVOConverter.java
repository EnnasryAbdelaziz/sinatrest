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
import org.apache.log4j.Logger;

import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;
import eai.devass.gsr.appli.modele.metier.dossierRente.Prothese;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.dossierRente.Tuteur;
import eai.devass.gsr.appli.modele.metier.reglement.ModePayement;
import eai.devass.gsr.appli.modele.parametrage.EtatRentier;
import eai.devass.gsr.appli.modele.parametrage.Nationalite;
import eai.devass.gsr.appli.modele.parametrage.SituationRentier;
import eai.devass.gsr.appli.modele.parametrage.SortGsr;
import eai.devass.gsr.appli.utile.TypeConverter;
import eai.devass.gsr.appli.valueobjects.metier.reglement.ModePayementVO;
import eai.devass.gsr.appli.valueobjects.metier.reglement.ModePayementVOConverter;
import eai.devass.gsr.appli.valueobjects.parametrage.EtatRentierVO;
import eai.devass.gsr.appli.valueobjects.parametrage.EtatRentierVOConverter;
import eai.devass.gsr.appli.valueobjects.parametrage.SortGsrVO;
import eai.devass.gsr.appli.valueobjects.parametrage.SortGsrVOConverter;
import eai.devass.sinistreat.appli.manager.CacheParametrageManager;
import eai.devass.sinistreat.appli.modele.metier.sinistre.AyantDroit;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Victime;
import eai.devass.sinistreat.appli.modele.parametrage.Pays;
import eai.devass.sinistreat.appli.modele.parametrage.Ville;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.AyantDroitVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.AyantDroitVOConverter;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.VictimeVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.VictimeVOConverter;

/**
 * Value Object Converter de Rentier
 * 
 * @author Nom Prenom (email)
 */
public class RentierVOConverter implements IValueObjectConverter {

	private CacheParametrageManager cacheParametrageManager = new CacheParametrageManager();
	
	public IValidator getValidator() {
		return null;
	}

	public List convertValueObjectToItems(Object ovo)
			throws ValidationException {
		RentierVO vo = (RentierVO) ovo;
		this.doValider(vo);
		Rentier item = new Rentier();
		voToItem(vo, item);
		// Infos version if set
		if (vo.getIdHistorisable() != 0) {
			item.setId(vo.getIdHistorisable());
		}
		List<Rentier> itemList = new ArrayList<Rentier>();
		itemList.add(item);
		return itemList;
	}

	public Object convertItemsToValueObject(List itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		RentierVO vo = new RentierVO();
		try {
			if (itemList.get(0) instanceof Rentier) {

				itemToVo(vo, (Rentier) itemList.get(0), itemList);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
			if (itemList.get(i) instanceof Rentier) {
				RentierVO vo = new RentierVO();
				itemToVo(vo, (Rentier) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}

	public void itemToVo(RentierVO vo, Rentier item, List itemList)
			throws EntiteException {
		vo.setId(TypeConverter.getInstance().longToString(item.getId()));

		vo.setLienParente(TypeConverter.getInstance().longToString(
				item.getLienParente()));
		vo.setNumeroCIN(item.getNumeroCIN());
		vo.setDateNaissance(TypeConverter.getInstance().calendarToString(
				item.getDateNaissance()));
		if(item.getRefSituationRentier() != null) {
			vo.setSituationRentier(String.valueOf(item.getRefSituationRentier()
					.getId()));
			vo.setLibelleSituationRentier(item.getRefSituationRentier()
					.getLibelle());
		}
		
		if(item.getRefNationalite() != null) {
			vo.setNationalite(String
					.valueOf(item.getRefNationalite().getCode()));
			vo.setLibelleNationalite(item.getRefNationalite().getLibelle());
		}
		
		vo.setNumeroTelephone(item.getNumeroTelephone());
		vo.setNumeroGSM(item.getNumeroGSM());
		vo.setEmail(item.getEmail());
		vo.setAdresse(item.getAdresse());
		vo.setCodePostale(TypeConverter.getInstance().doubleToString(
				item.getCodePostale()));
		vo.setVille((item.getVille()));
		
		// Libelle ville
		try {
			Ville ville = cacheParametrageManager.getVille(vo.getVille());
			if(ville != null) {
				vo.setVilleLibelle(ville.getLibelle());
			}
		
		} catch(Exception e) {
			Logger.getLogger("loggerGSR").fatal(
					"Erreur lors de la conversion !", e);
		}
		
		// Libelle pays
		vo.setPays(item.getPays());
		try {
			Pays pays = cacheParametrageManager.getPays(vo.getPays());
			if(pays != null) {
				vo.setLibellePays(pays.getLibelle());
			}
		
		} catch(Exception e) {
			Logger.getLogger("loggerGSR").fatal(
					"Erreur lors de la conversion !", e);
		}
		
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
		vo.setUserSas(item.getUserSas());

		/**
		 * Evo Lot1
		 */
		vo.setUserSasModificateur(item.getUserSasModificateur());
		vo.setDateModification(TypeConverter.getInstance().calendarToString(
				item.getDateModification()));
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

		if (item.getRefAyantDroit() != null) {
			AyantDroitVO refVO = new AyantDroitVO();
			AyantDroitVOConverter ayantDroitVOConverter = new AyantDroitVOConverter();
			refVO = (AyantDroitVO) ayantDroitVOConverter.itemToVo(refVO,
					item.getRefAyantDroit(), null);
			vo.setRefAyantDroit(refVO);
		}

		// Victime
		if (item.getRefVictime() != null) {
			VictimeVO refVO = new VictimeVO();
			VictimeVOConverter victimeVOConverter = new VictimeVOConverter();
			refVO = (VictimeVO) victimeVOConverter.itemToVo(refVO,
					item.getRefVictime(), null);
			vo.setRefVictime(refVO);

		}
		if (item.getRefTuteur() != null) {
			TuteurVO refVO = new TuteurVO();
			new TuteurVOConverter().itemToVo(refVO, item.getRefTuteur(), null);
			vo.setRefTuteur(refVO);
		}
		if (item.getRefEtatRentier() != null) {
			EtatRentierVO refVO = new EtatRentierVO();
			new EtatRentierVOConverter().itemToVo(refVO,
					item.getRefEtatRentier(), null);
			vo.setRefEtatRentier(refVO);
		}
		if (Fonctions.isNotEmpty(item.getMotifEtat())) {
			vo.setMotifEtat(item.getMotifEtat());
		}

		if (item.getRefModePayement() != null) {
			vo.setRefModePayement(new ModePayementVO());
			new ModePayementVOConverter().itemToVo(vo.getRefModePayement(),
					item.getRefModePayement(), null);
		}
		List refsProtheses = item.getRefsProtheses();
		if (refsProtheses != null) {
			List<ProtheseVO> refsProthesesVO = new ArrayList<ProtheseVO>();
			for (int i = 0; i < refsProtheses.size(); i++) {
				Prothese x = (Prothese) refsProtheses.get(i);
				ProtheseVO modeVO = new ProtheseVO();
				new ProtheseVOConverter().itemToVo(modeVO, x, null);
				refsProthesesVO.add(modeVO);
			}
			vo.setRefsProtheses(refsProthesesVO);
		}

		vo.setRefDossierRente(new DossierRenteVO());
		if (item.getRefDossierRente() != null) {
			new DossierRenteVOConverter().itemToVoWithoutDetail(
					vo.getRefDossierRente(), item.getRefDossierRente());
		}
		
		//EVO SORT RENTIER (CONSIGNATION)
		vo.setDateLimiteConsignation(TypeConverter.getInstance().calendarToString(
				item.getDateLimiteConsignation()));
		
		if (item.getRefSortGsr() != null) {
			SortGsrVO refVO = new SortGsrVO();
			new SortGsrVOConverter().itemToVo(refVO,
					item.getRefSortGsr(), null);
			vo.setRefSortGsr(refVO);
		}
		
		
	}

	public void itemToVoWithoutRefDossierRente(RentierVO vo, Rentier item,
			List itemList) {
		vo.setId(TypeConverter.getInstance().longToString(item.getId()));

		vo.setLienParente(TypeConverter.getInstance().longToString(
				item.getLienParente()));
		vo.setNumeroCIN(item.getNumeroCIN());
		vo.setDateNaissance(TypeConverter.getInstance().calendarToString(
				item.getDateNaissance()));
		
		if(item.getRefSituationRentier() != null) {
			vo.setSituationRentier(String.valueOf(item.getRefSituationRentier()
					.getId()));
			vo.setLibelleSituationRentier(item.getRefSituationRentier()
					.getLibelle());
		}
		
		if(item.getRefNationalite() != null) {
			vo.setNationalite(String
					.valueOf(item.getRefNationalite().getCode()));
			vo.setLibelleNationalite(item.getRefNationalite().getLibelle());
		}
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

		vo.setUserSas(item.getUserSas());

		/**
		 * Evo Lot1
		 */
		vo.setUserSasModificateur(item.getUserSasModificateur());
		vo.setDateModification(TypeConverter.getInstance().calendarToString(
				item.getDateModification()));
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

		if (item.getRefAyantDroit() != null) {
			AyantDroitVO refVO = new AyantDroitVO();
			vo.setRefAyantDroit((AyantDroitVO) new AyantDroitVOConverter()
					.itemToVo(refVO, item.getRefAyantDroit(), null));

		}
		if (item.getRefVictime() != null) {
			VictimeVO refVO = new VictimeVO();
			vo.setRefVictime((VictimeVO) new VictimeVOConverter().itemToVo(
					refVO, item.getRefVictime(), null));
		}
		if (item.getRefTuteur() != null) {
			TuteurVO refVO = new TuteurVO();
			new TuteurVOConverter().itemToVo(refVO, item.getRefTuteur(), null);
			vo.setRefTuteur(refVO);
		}
		if (item.getRefEtatRentier() != null) {
			EtatRentierVO refVO = new EtatRentierVO();
			new EtatRentierVOConverter().itemToVo(refVO,
					item.getRefEtatRentier(), null);
			vo.setRefEtatRentier(refVO);
		}
		if (Fonctions.isNotEmpty(item.getMotifEtat())) {
			vo.setMotifEtat(item.getMotifEtat());
		}
		if (item.getRefModePayement() != null) {
			vo.setRefModePayement(new ModePayementVO());
			new ModePayementVOConverter().itemToVo(vo.getRefModePayement(),
					item.getRefModePayement(), null);
		}
		List refsProtheses = item.getRefsProtheses();
		if (refsProtheses != null) {
			List<ProtheseVO> refsProthesesVO = new ArrayList<ProtheseVO>();
			for (int i = 0; i < refsProtheses.size(); i++) {
				Prothese x = (Prothese) refsProtheses.get(i);
				ProtheseVO modeVO = new ProtheseVO();
				new ProtheseVOConverter().itemToVo(modeVO, x, null);
				refsProthesesVO.add(modeVO);
			}
			vo.setRefsProtheses(refsProthesesVO);
		}
		
		//EVO SORT RENTIER (CONSIGNATION)
		vo.setDateLimiteConsignation(TypeConverter.getInstance().calendarToString(
				item.getDateLimiteConsignation()));
		
		if (item.getRefSortGsr() != null) {
			SortGsrVO refVO = new SortGsrVO();
			new SortGsrVOConverter().itemToVo(refVO,
					item.getRefSortGsr(), null);
			vo.setRefSortGsr(refVO);
		}

	}

	public void itemToVoWithoutRefVictime(RentierVO vo, Rentier item) {
		vo.setId(TypeConverter.getInstance().longToString(item.getId()));
		vo.setLienParente(TypeConverter.getInstance().longToString(
				item.getLienParente()));
		vo.setNumeroCIN(item.getNumeroCIN());
		vo.setDateNaissance(TypeConverter.getInstance().calendarToString(
				item.getDateNaissance()));
		
		if(item.getRefSituationRentier() != null) {
			vo.setSituationRentier(String.valueOf(item.getRefSituationRentier()
					.getId()));
			vo.setLibelleSituationRentier(item.getRefSituationRentier()
					.getLibelle());
		}
		if(item.getRefNationalite() != null) {
			vo.setNationalite(String
					.valueOf(item.getRefNationalite().getCode()));
			vo.setLibelleNationalite(item.getRefNationalite().getLibelle());
		}
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

		vo.setUserSas(item.getUserSas());

		/**
		 * Evo Lot1
		 */
		vo.setUserSasModificateur(item.getUserSasModificateur());
		vo.setDateModification(TypeConverter.getInstance().calendarToString(
				item.getDateModification()));
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

		if (item.getRefTuteur() != null) {
			TuteurVO refVO = new TuteurVO();
			new TuteurVOConverter().itemToVo(refVO, item.getRefTuteur(), null);
			vo.setRefTuteur(refVO);
		}
		if (item.getRefEtatRentier() != null) {
			EtatRentierVO refVO = new EtatRentierVO();
			new EtatRentierVOConverter().itemToVo(refVO,
					item.getRefEtatRentier(), null);
			vo.setRefEtatRentier(refVO);
		}
		if (Fonctions.isNotEmpty(item.getMotifEtat())) {
			vo.setMotifEtat(item.getMotifEtat());
		}
		if (item.getRefModePayement() != null) {
			vo.setRefModePayement(new ModePayementVO());
			new ModePayementVOConverter().itemToVo(vo.getRefModePayement(),
					item.getRefModePayement(), null);
		}

		List<Prothese> refsProtheses = item.getRefsProtheses();
		if (refsProtheses != null) {
			ProtheseVOConverter prothesesVOConverter = new ProtheseVOConverter();
			List<ProtheseVO> refsProthesesVO = new ArrayList<ProtheseVO>();
			for (Prothese cur : refsProtheses) {
				ProtheseVO prothesesVO = new ProtheseVO();
				prothesesVOConverter.itemToVo(prothesesVO, cur, null);
				refsProthesesVO.add(prothesesVO);
			}
			vo.setRefsProtheses(refsProthesesVO);
		}
		
		//EVO SORT RENTIER (CONSIGNATION)
		vo.setDateLimiteConsignation(TypeConverter.getInstance().calendarToString(
				item.getDateLimiteConsignation()));	
		if (item.getRefSortGsr() != null) {
			SortGsrVO refVO = new SortGsrVO();
			new SortGsrVOConverter().itemToVo(refVO,
					item.getRefSortGsr(), null);
			vo.setRefSortGsr(refVO);
		}
		
	}

	public void voToItem(RentierVO vo, Rentier item) {
		item.setId(TypeConverter.getInstance().stringToLong(vo.getId()));
		if (Fonctions.isLong(vo.getSituationRentier())) {
			item.setRefSituationRentier(new SituationRentier(Long.valueOf(vo
					.getSituationRentier())));
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
		if (Fonctions.isLong(vo.getNationalite())) {
			item.setRefNationalite(new Nationalite(Long.valueOf(vo
					.getNationalite())));
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
			// item.setIppTauxRente(TypeConverter.getInstance().stringToDouble(
			// vo.getIppTauxRente()));
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
			// item.setSalaireUtile(TypeConverter.getInstance().stringToDouble(
			// vo.getSalaireUtile()));
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

		item.setUserSas(vo.getUserSas());

		/**
		 * Evo Lot1
		 */
		item.setUserSasModificateur(vo.getUserSasModificateur());
		if (vo.getDateModification() != null
				&& !vo.getDateModification().equals("")) {
			item.setDateModification(TypeConverter.getInstance()
					.stringToCalendar(vo.getDateModification()));
		}
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

		if (vo.getDateCreation() != null && !vo.getDateCreation().equals("")) {
			item.setDateCreation(TypeConverter.getInstance().stringToCalendar(
					vo.getDateCreation()));
		}
		if (vo.getRefAyantDroit() != null) {
			AyantDroit refAD = new AyantDroit();
			refAD = (AyantDroit) new AyantDroitVOConverter().voToItem(
					vo.getRefAyantDroit(), refAD);
			item.setRefAyantDroit(refAD);
		}
		if (vo.getRefVictime() != null) {
			Victime refVictime = new Victime();
			refVictime = (Victime) new VictimeVOConverter().voToItem(
					vo.getRefVictime(), refVictime);
			item.setRefVictime(refVictime);
		}
		if (vo.getRefTuteur() != null) {
			Tuteur refTuteur = new Tuteur();
			new TuteurVOConverter().voToItem(vo.getRefTuteur(), refTuteur);
			item.setRefTuteur(refTuteur);
		}
		if (vo.getRefEtatRentier() != null) {
			EtatRentier refEtat = new EtatRentier();
			new EtatRentierVOConverter().voToItem(vo.getRefEtatRentier(),
					refEtat);
			item.setRefEtatRentier(refEtat);
		}

		if (Fonctions.isNotEmpty(vo.getMotifEtat())) {
			item.setMotifEtat(vo.getMotifEtat());
		}
		if (vo.getRefModePayement() != null) {
			ModePayement refModePayment = new ModePayement();
			new ModePayementVOConverter().voToItem(vo.getRefModePayement(),
					refModePayment);
			item.setRefModePayement(refModePayment);
		}

		List<ProtheseVO> refsProtheses = vo.getRefsProtheses();
		if (refsProtheses != null && !refsProtheses.isEmpty()) {
			for (int i = 0; i < refsProtheses.size(); i++) {
				Prothese x = new Prothese();
				new ProtheseVOConverter().voToItem(refsProtheses.get(i), x);
				item.getRefsProtheses().add(x);

			}
		}
		// if (vo.getRefDossierRente() instanceof DossierRenteVO) {
		item.setRefDossierRente(new DossierRente());
		if (vo.getRefDossierRente() != null
				&& ((vo.getRefDossierRente().getNumeroRente() != null && !""
						.equals(vo.getRefDossierRente().getNumeroRente().trim()))
						|| (vo.getRefDossierRente().getCompagnieRente() != null || !""
								.equals(vo.getRefDossierRente()
										.getCompagnieRente()))
						|| (vo.getRefDossierRente().getRefSinistre()
								.getNumeroSinistre() != null || !"".equals(vo
								.getRefDossierRente().getRefSinistre()
								.getNumeroSinistre())) || (vo
						.getRefDossierRente().getRefSinistre()
						.getRefEvenement().getDateAccident() != null || !""
						.equals(vo.getRefDossierRente().getRefSinistre()
								.getRefEvenement().getDateAccident())))) {

			new DossierRenteVOConverter().voToItem(vo.getRefDossierRente(),
					item.getRefDossierRente());
		}
		
		
		//EVO SORT RENTIER (CONSIGNATION)
		item.setDateLimiteConsignation(TypeConverter.getInstance().stringToCalendar(
				vo.getDateLimiteConsignation()));		
		if (vo.getRefSortGsr() != null) {
			SortGsr refSor = new SortGsr();
			new SortGsrVOConverter().voToItem(vo.getRefSortGsr(),
					refSor);
			item.setRefSortGsr(refSor);
		}
		
		
	}

	// }

	protected void doValider(RentierVO vo) throws ValidationException {

	}
}
