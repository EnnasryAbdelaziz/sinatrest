package eai.devass.gsr.appli.valueobjects.metier.dossierRente;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.EntiteException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;
import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRenteHisto;
import eai.devass.gsr.appli.utile.TypeConverter;
import eai.devass.sinistreat.appli.utils.Fonctions;

/**
 * Value Object Converter de DossierRenteHisto
 * 
 * @author Mossâb wassim
 */
public class DossierRenteHistoVOConverter implements IValueObjectConverter {

	public IValidator getValidator() {
		return null;
	}

	public List convertValueObjectToItems(Object ovo)
			throws ValidationException {
		DossierRenteHistoVO vo = (DossierRenteHistoVO) ovo;
		this.doValider(vo);
		DossierRenteHisto item = new DossierRenteHisto();
		voToItem(vo, item);
		// Infos version if set
		if (vo.getIdHistorisable() != 0) {
			item.setId(vo.getIdHistorisable());
		}
		List<DossierRenteHisto> itemList = new ArrayList<DossierRenteHisto>();
		itemList.add(item);
		return itemList;
	}

	public Object convertItemsToValueObject(List itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		DossierRenteHistoVO vo = new DossierRenteHistoVO();
		if (itemList.get(0) instanceof DossierRenteHisto) {
			itemToVo(vo, (DossierRenteHisto) itemList.get(0), itemList);
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
				DossierRenteHistoVO vo = new DossierRenteHistoVO();
				itemToVo(vo, (DossierRenteHisto) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}

	public void itemToVo(DossierRenteHistoVO vo, DossierRenteHisto item,
			List itemList) {
		if (item != null) {
			vo.setId(TypeConverter.getInstance().longToString(item.getId()));
			vo.setIdRente(TypeConverter.getInstance().longToString(
					item.getIdRente()));
			vo.setCompagnieRente(item.getCompagnieRente());
			vo.setNumeroRente(TypeConverter.getInstance().longToString(
					item.getNumeroRente()));
			vo.setIdDossierRente(TypeConverter.getInstance().doubleToString(
					item.getIdDossierRente()));
			vo.setEtat(TypeConverter.getInstance().longToString(item.getEtat()));
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

			vo.setRefSinistre(TypeConverter.getInstance().longToString(
					item.getRefSinistre()));
		}

	}

	public void itemToVoWithoutDetail(DossierRenteHistoVO vo,
			DossierRenteHisto item) throws EntiteException {
		if (item != null) {
			vo.setId(TypeConverter.getInstance().longToString(item.getId()));
			vo.setIdRente(TypeConverter.getInstance().longToString(
					item.getIdRente()));
			vo.setCompagnieRente(item.getCompagnieRente());
			vo.setNumeroRente(TypeConverter.getInstance().longToString(
					item.getNumeroRente()));
			vo.setIdDossierRente(TypeConverter.getInstance().doubleToString(
					item.getIdDossierRente()));
			vo.setEtat(TypeConverter.getInstance().longToString(item.getEtat()));
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

			vo.setRefSinistre(TypeConverter.getInstance().longToString(
					item.getRefSinistre()));

		}
	}

	public void voToItem(DossierRenteHistoVO vo, DossierRenteHisto item) {

		if (vo != null) {
			item.setId(TypeConverter.getInstance().stringToLong(vo.getId()));
			item.setIdRente(TypeConverter.getInstance().stringToLong(
					vo.getIdRente()));
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
			if (Fonctions.isNotEmpty(vo.getEtat())) {
				item.setEtat(TypeConverter.getInstance().stringToLong(
						vo.getEtat()));
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
			if (Fonctions.isNotEmpty(vo.getRefSinistre())) {
				item.setRefSinistre(TypeConverter.getInstance().stringToLong(
						vo.getRefSinistre()));
			}
		}

	}

	protected void doValider(DossierRenteHistoVO vo) throws ValidationException {

	}
}
