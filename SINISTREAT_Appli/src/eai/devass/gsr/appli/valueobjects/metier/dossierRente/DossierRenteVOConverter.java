package eai.devass.gsr.appli.valueobjects.metier.dossierRente;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.entites.EntiteException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.log.ILog;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.parametrage.EtatRentier;
import eai.devass.gsr.appli.utile.CommonGsrUtils;
import eai.devass.gsr.appli.utile.TypeConverter;
import eai.devass.sinistreat.appli.manager.CacheParametrageManager;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.parametrage.Ville;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVOConverter;

/**
 * Value Object Converter de DossierRente
 * 
 * @author Nom Prenom (email)
 */

@SuppressWarnings("all")
public class DossierRenteVOConverter implements IValueObjectConverter {

	
	private CacheParametrageManager cacheParametrageManager = new CacheParametrageManager();
	
	public IValidator getValidator() {
		return null;
	}

	public List convertValueObjectToItems(Object ovo)
			throws ValidationException {
		DossierRenteVO vo = (DossierRenteVO) ovo;
		this.doValider(vo);
		DossierRente item = new DossierRente();
		voToItem(vo, item);
		// Infos version if set
		if (vo.getIdHistorisable() != 0) {
			item.setId(vo.getIdHistorisable());
		}
		List<DossierRente> itemList = new ArrayList<DossierRente>();
		itemList.add(item);
		return itemList;
	}

	public Object convertItemsToValueObject(List itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		DossierRenteVO vo = new DossierRenteVO();
		if (itemList.get(0) instanceof DossierRente) {
			itemToVo(vo, (DossierRente) itemList.get(0), itemList);
		}

		return vo;
	}

	public List<IValueObject> convertItemsToVos(List<IEntite> itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i) instanceof DossierRente) {
				DossierRenteVO vo = new DossierRenteVO();
				itemToVo(vo, (DossierRente) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}

	public void itemToVo(DossierRenteVO vo, DossierRente item, List itemList) {
		if (item != null) {
			vo.setId(TypeConverter.getInstance().longToString(item.getId()));
			vo.setCompagnieRente(item.getCompagnieRente());
			vo.setNumeroRente(TypeConverter.getInstance().longToString(
					item.getNumeroRente()));
			vo.setIdDossierRente(TypeConverter.getInstance().doubleToString(
					item.getIdDossierRente()));
			
			if(item.getRefEtatRentier() != null) {
				vo.setEtat(String.valueOf(item.getRefEtatRentier().getId()));
				vo.setLblEtat(item.getRefEtatRentier().getLibelle());
			}
			vo.setDateEtat(TypeConverter.getInstance().calendarToString(
					item.getDateEtat()));
			vo.setDateValidation(TypeConverter.getInstance().calendarToString(
					item.getDateValidation()));
			vo.setValidation(TypeConverter.getInstance().booleanToString(
					item.getValidation()));
			vo.setDateCreation(TypeConverter.getInstance().calendarToString(
					item.getDateCreation()));

			/**
			 * EVO Lot1
			 */
			vo.setUserSasCreateur(item.getUserSasCreateur());
			vo.setUserSasModificateur(item.getUserSasModificateur());
			vo.setDateModification(TypeConverter.getInstance()
					.calendarToString(item.getDateModification()));
			/**
			 * Fin EVO
			 */

			//Evo lot2
			vo.setNumeroOrdre(TypeConverter.getInstance().integerToString(item.getNumeroOrdre()));
			Sinistre sinistre = item.getRefSinistre();
			if (sinistre != null) {
				try {
					sinistre.setPropertiesToConvert(new String[] {"id","numeroSinistre","numeroGrave","refVictime.dateNaissance","refVictime.sexe","refEtatSinistre.refEtat.libelle","ipp",
							"refVictime.nom","refVictime.prenom","refVictime.id","refEvenement.dateAccident","numeroPolice"
							,"codeIntermediaire","refVictime.adresse","refVille.libelle","refVictime.deces","refVictime.dateDeces","reserveGrave","reserveOrdinaire","reserveProthese"});
					vo.setRefSinistre((SinistreVO) ConverterTools.getInstance()
							.convertToObjectVOWithoutList(sinistre));
				
				} catch(Exception e) {
					//throw new IllegalArgumentException("Impossible de convertire le dossier sinistre !");
					// log
				}
			}

			// Convert la liste des rentiers
			List<Rentier> listRentier = item.getRefsRentier();
			if(!CommonUtils.getInstance().isEmpty(listRentier)) {
				List<RentierVO> listRentierVO = null;
				try {
					Rentier firstRentier = listRentier.get(0);
					firstRentier.setPropertiesToConvert(new String[] {"id","validation","montantRenteTrimestrielle","lienParente",
							"nom","prenom","adresse","ville","tuteur","refTuteur.nom","refTuteur.prenom","dateNaissance",
							"refEtatRentier.code","refVictime.id","refAyantDroit.id"});
					listRentierVO =  ConverterTools.getInstance()
						.convertToListObjectVOWithoutList(listRentier);
					
					// Libelle ville 
					if(!Fonctions.isEmpty(listRentierVO)) {
						Ville ville = null;
						for(RentierVO curRentierVO : listRentierVO) {
							ville = cacheParametrageManager
									.getVille(curRentierVO.getVille());
							if(ville != null) {
								curRentierVO.setVilleLibelle(ville.getLibelle());
							}
						}
					}
				
				} catch(Exception e) {
					// LOGGER !!!!
				}
				vo.setRefsRentier(listRentierVO);
			}
			
			
//			if ((item.getRefsRentier() != null)
//					&& (item.getRefsRentier().size() > 0)) {
//				List refsRentier = new ArrayList();
//				Iterator iter = item.getRefsRentier().iterator();
//				while (iter.hasNext()) {
//					Rentier rentier = (Rentier) iter.next();
//					RentierVOConverter rentierVOConverter = new RentierVOConverter();
//					RentierVO rentierVO = new RentierVO();
//					rentierVOConverter.itemToVoWithoutRefDossierRente(
//							rentierVO, rentier, null);
//					refsRentier.add(rentierVO);
//				}
//				vo.setRefsRentier(refsRentier);
//			}
		}

		/*
		 * if (itemList == null) return;
		 * 
		 * for (int i=1; i<itemList.size(); i++) { if (itemList.get(i)
		 * instanceof List) if (((List)itemList.get(i)).size() != 0) { if
		 * (((List)itemList.get(i)).get(0) instanceof Rentier) {
		 * RentierVOConverter rentierVOConverter = new RentierVOConverter ();
		 * vo.
		 * setRefsRentier(rentierVOConverter.convertItemsToVos((List)itemList.
		 * get(i))); } } }
		 */
	}

	public void itemToVoWithoutDetail(DossierRenteVO vo, DossierRente item)
			throws EntiteException {
		if (item != null) {
			vo.setId(TypeConverter.getInstance().longToString(item.getId()));
			vo.setCompagnieRente(item.getCompagnieRente());
			vo.setNumeroRente(TypeConverter.getInstance().longToString(
					item.getNumeroRente()));
			vo.setIdDossierRente(TypeConverter.getInstance().doubleToString(
					item.getIdDossierRente()));
			
			if(item.getRefEtatRentier() != null) {
				vo.setEtat(String.valueOf(item.getRefEtatRentier().getId()));
				vo.setLblEtat(item.getRefEtatRentier().getLibelle());
			}
			vo.setDateEtat(TypeConverter.getInstance().calendarToString(
					item.getDateEtat()));
			vo.setDateValidation(TypeConverter.getInstance().calendarToString(
					item.getDateValidation()));
			vo.setValidation(TypeConverter.getInstance().booleanToString(
					item.getValidation()));
			vo.setDateCreation(TypeConverter.getInstance().calendarToString(
					item.getDateCreation()));
			/**
			 * EVO Lot1
			 */
			vo.setUserSasCreateur(item.getUserSasCreateur());
			vo.setUserSasModificateur(item.getUserSasModificateur());
			vo.setDateModification(TypeConverter.getInstance()
					.calendarToString(item.getDateModification()));
			/**
			 * Fin EVO
			 */

			//Evo Lot2
			vo.setNumeroOrdre(TypeConverter.getInstance().integerToString(item.getNumeroOrdre()));
			
			if (item.getRefSinistre() != null) {
				SinistreVO sinistreVo;
				try {
					sinistreVo = (SinistreVO) ConverterTools
							.getInstance()
							.convertToObjectVOWithoutList(item.getRefSinistre());
					vo.setRefSinistre(sinistreVo);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					ILog logger = (ILog) ServicesFactory.getService(ILog.class);
					logger.error(e.getMessage());

				}

			}

			if ((item.getRefsRentier() != null)
					&& (item.getRefsRentier().size() > 0)) {
				List<RentierVO> refsRentierVO = new ArrayList<RentierVO>();
				Iterator iter = item.getRefsRentier().iterator();
				while (iter.hasNext()) {
					Rentier rentier = (Rentier) iter.next();
					RentierVOConverter rentierVOConverter = new RentierVOConverter();
					RentierVO rentierVO = new RentierVO();
					rentierVOConverter.itemToVoWithoutRefVictime(rentierVO,
							rentier);
					refsRentierVO.add(rentierVO);
				}
				vo.setRefsRentier(refsRentierVO);
			}
		}
	}

	public void voToItem(DossierRenteVO vo, DossierRente item) {

		if (vo != null) {
			item.setId(TypeConverter.getInstance().stringToLong(vo.getId()));
			if (Fonctions.isNotEmpty(vo.getCompagnieRente())) {
				item.setCompagnieRente(vo.getCompagnieRente());
			}
			if (Fonctions.isNotEmpty(vo.getNumeroRente())) {
				item.setNumeroRente(TypeConverter.getInstance().stringToLong(
						vo.getNumeroRente()));
			}
			if (Fonctions.isNotEmpty(vo.getIdDossierRente())) {
				item.setIdDossierRente(TypeConverter.getInstance()
						.stringToDouble(vo.getIdDossierRente()));
			}
			
			if (Fonctions.isLong(vo.getEtat())) {
				item.setRefEtatRentier(new EtatRentier(Long.valueOf(vo.getEtat())));
			}
			
			if (Fonctions.isNotEmpty(vo.getDateEtat())) {
				item.setDateEtat(TypeConverter.getInstance().stringToCalendar(
						vo.getDateEtat()));
			}
			if (Fonctions.isNotEmpty(vo.getDateValidation())) {
				item.setDateValidation(TypeConverter.getInstance()
						.stringToCalendar(vo.getDateValidation()));
			}
			if (Fonctions.isNotEmpty(vo.getValidation())) {
				item.setValidation(TypeConverter.getInstance().stringToBoolean(
						vo.getValidation()));
			}
			if (Fonctions.isNotEmpty(vo.getDateCreation())) {
				item.setDateCreation(TypeConverter.getInstance()
						.stringToCalendar(vo.getDateCreation()));
			}
			if (Fonctions.isNotEmpty(vo.getDateCreationDebut())) {
				item.setDateCreationDebut(vo.getDateCreationDebut());
			}
			if (Fonctions.isNotEmpty(vo.getDateCreationFin())) {
				item.setDateCreationFin(vo.getDateCreationFin());
			}

			/** AZAS Ajout d'objet sinistre */

			if (vo.getRefSinistre() != null) {
				Sinistre refSinistre = new Sinistre();
				refSinistre = (Sinistre) new SinistreVOConverter().voToItem(
						vo.getRefSinistre(), refSinistre);
				if (refSinistre.getNomIntermediaire() != null
						&& Fonctions.isEmpty(refSinistre.getNomIntermediaire()
								.trim())) {
					refSinistre.setNomIntermediaire(null);
				}
				item.setRefSinistre(refSinistre);
			}

			/**
			 * EVO Lot1
			 */
			item.setUserSasCreateur(vo.getUserSasCreateur());
			item.setUserSasModificateur(vo.getUserSasModificateur());
			item.setDateModification(TypeConverter.getInstance()
					.stringToCalendar(vo.getDateModification()));
			/**
			 * Fin EVO
			 */

			//Evo lot2
			item.setNumeroOrdre(TypeConverter.getInstance().stringToInteger(vo.getNumeroOrdre()));
			
			// ============ Ajout pere et fils ====================
			if (Fonctions.isNotEmpty(vo.getRefsRentier())) {
				List refsRentierItems = new ArrayList();
				RentierVOConverter rentierVOConverter = new RentierVOConverter();
				Iterator iter = vo.getRefsRentier().iterator();
				while (iter.hasNext()) {
					RentierVO rentierVO = (RentierVO) iter.next();
					Rentier rentier = new Rentier();
					rentierVOConverter.voToItem(rentierVO, rentier);
					rentier.setRefDossierRente(item);
					refsRentierItems.add(rentier);
				}
				item.setRefsRentier(refsRentierItems);
			}
			// ====================================================
		}

	}

	protected void doValider(DossierRenteVO vo) throws ValidationException {

	}
}
