package eai.devass.gsr.appli.valueobjects.metier.dossierRente;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.modele.metier.dossierRente.Certificats;
import eai.devass.gsr.appli.utile.TypeConverter;

/**
 * Value Object Converter de Certificats
 * 
 * @author Nom Prenom (email)
 */
public class CertificatsVOConverter implements IValueObjectConverter {

	public IValidator getValidator() {
		return null;
	}

	public List convertValueObjectToItems(Object ovo)
			throws ValidationException {
		CertificatsVO vo = (CertificatsVO) ovo;
		this.doValider(vo);
		Certificats item = new Certificats();
		voToItem(vo, item);
		List<Certificats> itemList = new ArrayList<Certificats>();
		itemList.add(item);
		return itemList;
	}

	public Object convertItemsToValueObject(List itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		CertificatsVO vo = new CertificatsVO();
		if (itemList.get(0) instanceof Certificats) {
			itemToVo(vo, (Certificats) itemList.get(0), itemList);
		}

		return vo;
	}

	public List<IValueObject> convertItemsToVos(List<IEntite> itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ArrayList<IValueObject> list = new ArrayList<IValueObject>();
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i) instanceof Certificats) {
				CertificatsVO vo = new CertificatsVO();
				itemToVo(vo, (Certificats) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}

	public void itemToVo(CertificatsVO vo, Certificats item, List itemList) {
		vo.setId(item.getId());

		vo.setDateReception(TypeConverter.getInstance().calendarToString(
				item.getDateReception()));
		vo.setObservation(item.getObservation());
		vo.setTypeCertificat(TypeConverter.getInstance().doubleToString(
				item.getTypeCertificat()));
		vo.setIdCertificat(TypeConverter.getInstance().doubleToString(
				item.getIdCertificat()));
		vo.setNumeroCertificat(TypeConverter.getInstance().doubleToString(
				item.getNumeroCertificat()));
		vo.setPeriodeDU(TypeConverter.getInstance().calendarToString(
				item.getPeriodeDU()));
		vo.setPeriodeAU(TypeConverter.getInstance().calendarToString(
				item.getPeriodeAU()));
		vo.setEtat(TypeConverter.getInstance().doubleToString(item.getEtat()));
		vo.setDateEtat(TypeConverter.getInstance().calendarToString(
				item.getDateEtat()));
		vo.setValidation(TypeConverter.getInstance().booleanToString(
				item.getValidation()));
		vo.setDateValidation(TypeConverter.getInstance().calendarToString(
				item.getDateValidation()));
		vo.setDateCreation(TypeConverter.getInstance().calendarToString(
				item.getDateCreation()));

		/*
		 * if (itemList == null) return;
		 * 
		 * for (int i=1; i<itemList.size(); i++) { if (itemList.get(i)
		 * instanceof List) if (((List)itemList.get(i)).size() != 0) { } }
		 */
	}

	public void voToItem(CertificatsVO vo, Certificats item) {

		if (vo != null) {
			item.setId(vo.getId());
			if (vo.getDateReception() != null) {
				item.setDateReception(TypeConverter.getInstance()
						.stringToCalendar(vo.getDateReception()));
			}
			item.setObservation(vo.getObservation());
			if (vo.getTypeCertificat() != null) {
				item.setTypeCertificat(TypeConverter.getInstance()
						.stringToDouble(vo.getTypeCertificat()));
			}
			if (vo.getIdCertificat() != null) {
				item.setIdCertificat(TypeConverter.getInstance()
						.stringToDouble(vo.getIdCertificat()));
			}
			if (vo.getNumeroCertificat() != null) {
				item.setNumeroCertificat(TypeConverter.getInstance()
						.stringToDouble(vo.getNumeroCertificat()));
			}
			if (vo.getPeriodeDU() != null) {
				item.setPeriodeDU(TypeConverter.getInstance().stringToCalendar(
						vo.getPeriodeDU()));
			}
			if (vo.getPeriodeAU() != null) {
				item.setPeriodeAU(TypeConverter.getInstance().stringToCalendar(
						vo.getPeriodeAU()));
			}
			if (vo.getEtat() != null) {
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
		}

	}

	protected void doValider(CertificatsVO vo) throws ValidationException {

	}
}
