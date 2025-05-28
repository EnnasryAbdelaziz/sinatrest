package eai.devass.gsr.appli.valueobjects.metier.dossierRente;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.modele.metier.dossierRente.Tuteur;
import eai.devass.gsr.appli.utile.TypeConverter;

/**
 * Value Object Converter de Tuteur
 * 
 * @author Nom Prenom (email)
 */
public class TuteurVOConverter implements IValueObjectConverter {

	public IValidator getValidator() {
		return null;
	}

	public List convertValueObjectToItems(Object ovo)
			throws ValidationException {
		TuteurVO vo = (TuteurVO) ovo;
		this.doValider(vo);
		Tuteur item = new Tuteur();
		voToItem(vo, item);
		// Infos version if set
		if (vo.getIdHistorisable() != 0) {
			item.setId(vo.getIdHistorisable());
		}
		List<Tuteur> itemList = new ArrayList<Tuteur>();
		itemList.add(item);
		return itemList;
	}

	public Object convertItemsToValueObject(List itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		TuteurVO vo = new TuteurVO();
		if (itemList.get(0) instanceof Tuteur) {
			itemToVo(vo, (Tuteur) itemList.get(0), itemList);
		}

		return vo;
	}

	public List<IValueObject> convertItemsToVos(List<IEntite> itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i) instanceof Tuteur) {
				TuteurVO vo = new TuteurVO();
				itemToVo(vo, (Tuteur) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}

	public void itemToVo(TuteurVO vo, Tuteur item, List itemList) {
		
		// if(item.getIdTuteur()!= null)
		// vo.setIdTuteur(item.getIdTuteur().toString());
		if (item != null) {
			vo.setId(TypeConverter.getInstance().longToString(item.getId()));
			if (item.getIdTuteur() != null) {
				vo.setIdTuteur(item.getIdTuteur().toString());
			}
		
		vo.setNom(item.getNom());
		vo.setNumeroCIN(item.getNumeroCIN());
		vo.setLienParente(TypeConverter.getInstance().doubleToString(
				item.getLienParente()));
		vo.setAdresse(item.getAdresse());
		vo.setCodePostale(TypeConverter.getInstance().doubleToString(
				item.getCodePostale()));
		vo.setVille(item.getVille());
		vo.setPays(item.getPays());
		vo.setEtat(TypeConverter.getInstance().doubleToString(item.getEtat()));
		vo.setDateEtat(TypeConverter.getInstance().calendarToString(
				item.getDateEtat()));
		vo.setValidation(TypeConverter.getInstance().booleanToString(
				item.getValidation()));
		vo.setDateValidation(TypeConverter.getInstance().calendarToString(
				item.getDateValidation()));
		vo.setPrenom(item.getPrenom());
		vo.setSexe(item.getSexe());
		vo.setDateCreation(TypeConverter.getInstance().calendarToString(
				item.getDateCreation()));
		vo.setDeleted(item.getDeleted());
		}
		/*
		 * if (itemList == null) return;
		 * 
		 * for (int i=1; i<itemList.size(); i++) { if (itemList.get(i)
		 * instanceof List) if (((List)itemList.get(i)).size() != 0) { } }
		 */
	}

	public void voToItem(TuteurVO vo, Tuteur item) {

		if (vo != null) {
			if (vo.getId() != null && !"".equals(vo.getId())) {
				item.setId(TypeConverter.getInstance().stringToLong(vo.getId()));
			}
			if (vo.getIdTuteur() != null) {
				item.setIdTuteur(TypeConverter.getInstance().stringToLong(
						vo.getIdTuteur()));
			}
			if (vo.getLienParente() != null && !"".equals(vo.getLienParente())) {
				item.setLienParente(TypeConverter.getInstance().stringToDouble(
						vo.getLienParente()));
			}
			if (vo.getCodePostale() != null && !"".equals(vo.getCodePostale())) {
				item.setCodePostale(TypeConverter.getInstance().stringToDouble(
						vo.getCodePostale()));
			}
			if (vo.getVille() != null) {
				item.setVille(vo.getVille());
			}
			if (vo.getPays() != null) {
				item.setPays(vo.getPays());
			}
			if (vo.getEtat() != null && !vo.getEtat().trim().equals("")) {
				item.setEtat(TypeConverter.getInstance().stringToDouble(
						vo.getEtat()));
			}
			if (vo.getDateEtat() != null) {
				item.setDateEtat(TypeConverter.getInstance().stringToCalendar(
						vo.getDateEtat()));
			}
			if (vo.getValidation() != null) {
				item.setValidation(TypeConverter.getInstance().stringToBoolean(
						vo.getValidation()));
			}
			if (vo.getDateValidation() != null) {
				item.setDateValidation(TypeConverter.getInstance()
						.stringToCalendar(vo.getDateValidation()));
			}
			if (vo.getDateCreation() != null) {
				item.setDateCreation(TypeConverter.getInstance()
						.stringToCalendar(vo.getDateCreation()));
			}
			if (vo.getDeleted() != null && !"".equals(vo.getDeleted())) {
				item.setDeleted(vo.getDeleted());
			}
			if (vo.getNewTuteur() != null && !"".equals(vo.getNewTuteur())) {
				item.setNewTuteur(vo.getNewTuteur());
			}
			if (vo.getNom() != null && !"".equals(vo.getNom())) {
				item.setNom(vo.getNom());
			}
			if (vo.getNumeroCIN() != null && !"".equals(vo.getNumeroCIN())) {
				item.setNumeroCIN(vo.getNumeroCIN());
			}
			if (vo.getAdresse() != null && !"".equals(vo.getAdresse())) {
				item.setAdresse(vo.getAdresse());
			}
			if (vo.getPrenom() != null && !"".equals(vo.getPrenom())) {
				item.setPrenom(vo.getPrenom());
			}
			if (vo.getSexe() != null && !"".equals(vo.getSexe())) {
				item.setSexe(vo.getSexe());
			}
		}
	}

	protected void doValider(TuteurVO vo) throws ValidationException {

	}
}
