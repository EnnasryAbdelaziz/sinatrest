package eai.devass.gsr.appli.valueobjects.metier.dossierRente;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.validation.IValidator;

import org.apache.commons.lang.StringUtils;

import eai.devass.gsr.appli.modele.metier.dossierRente.Prothese;
import eai.devass.gsr.appli.modele.parametrage.CentreProthese;
import eai.devass.gsr.appli.modele.parametrage.EtatProthese;
import eai.devass.gsr.appli.modele.parametrage.NatureProthese;
import eai.devass.gsr.appli.utile.TypeConverter;
import eai.devass.gsr.appli.valueobjects.parametrage.CentreProtheseVO;
import eai.devass.gsr.appli.valueobjects.parametrage.CentreProtheseVOConverter;
import eai.devass.gsr.appli.valueobjects.parametrage.EtatProtheseVO;
import eai.devass.gsr.appli.valueobjects.parametrage.EtatProtheseVOConverter;
import eai.devass.gsr.appli.valueobjects.parametrage.NatureProtheseVO;
import eai.devass.gsr.appli.valueobjects.parametrage.NatureProtheseVOConverter;

/**
 * Value Object Converter de Protheses
 * 
 * @author Nom Prenom (email)
 */
public class ProtheseVOConverter implements IValueObjectConverter {

	public IValidator getValidator() {
		return null;
	}

	public List convertValueObjectToItems(Object ovo)
			throws ValidationException {
		ProtheseVO vo = (ProtheseVO) ovo;
		this.doValider(vo);
		Prothese item = new Prothese();
		voToItem(vo, item);
		// Infos version if set
		if (vo.getIdHistorisable() != 0) {
			item.setId(vo.getIdHistorisable());
		}
		List<Prothese> itemList = new ArrayList<Prothese>();
		itemList.add(item);
		return itemList;
	}

	public Object convertItemsToValueObject(List itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ProtheseVO vo = new ProtheseVO();
		if (itemList.get(0) instanceof Prothese) {
			itemToVo(vo, (Prothese) itemList.get(0), itemList);
		}

		return vo;
	}

	public List<ProtheseVO> convertItemsToVos(List<IEntite> itemList) {
		if ((itemList == null) || (itemList.size() == 0)) {
			return null;
		}
		ArrayList<ProtheseVO> list = new ArrayList<ProtheseVO>();
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i) instanceof Prothese) {
				ProtheseVO vo = new ProtheseVO();
				itemToVo(vo, (Prothese) itemList.get(i), null);
				list.add(vo);
			}
		}
		return list;
	}

	public void itemToVo(ProtheseVO vo, Prothese item, List itemList) {
		if (item != null) {
			vo.setId(TypeConverter.getInstance().longToString(item.getId()));
			if (item.getRefNatureProthese() != null) {
				NatureProtheseVO natureVO = new NatureProtheseVO();
				vo.setRefNatureProthese(natureVO);
				new NatureProtheseVOConverter().itemToVo(natureVO,
						item.getRefNatureProthese(), null);
			}
			
			if (item.getRefCentreProthese() != null) {
				CentreProtheseVO centreVO = new CentreProtheseVO();
				vo.setRefCentreProthese(centreVO);
				new CentreProtheseVOConverter().itemToVo(centreVO,
						item.getRefCentreProthese(), null);
			}
			if (item.getRefEtatProthese() != null) {	
				EtatProtheseVO etatVO = new EtatProtheseVO();
				vo.setRefEtatProthese(etatVO);
				new EtatProtheseVOConverter().itemToVo(etatVO,
						item.getRefEtatProthese(), null);
			}
			
			if (item.getRefMvtProthese() != null) {	
				
				Long idMvt = item.getRefMvtProthese().getId();
				vo.setRefMvtProthese(TypeConverter.getInstance().longToString(idMvt));
			
			}

			vo.setReserveProthese(TypeConverter.getInstance().doubleToString(
					item.getReserveProthese()));
//			vo.setRefEtatProthese(TypeConverter.getInstance().doubleToString(
//					item.getEtatProthese()));
			vo.setDateEtat(TypeConverter.getInstance().calendarToString(
					item.getDateEtat()));
			vo.setValidation(TypeConverter.getInstance().booleanToString(
					item.getValidation()));
			vo.setDateValidation(TypeConverter.getInstance().calendarToString(
					item.getDateValidation()));
			vo.setDateProthese(TypeConverter.getInstance().calendarToString(
					item.getDateProthese()));
			vo.setMontantProthese(TypeConverter.getInstance().doubleToString(
					item.getMontantProthese()));
			vo.setIdProthese(TypeConverter.getInstance().longToString(
					item.getIdProthese()));
			vo.setNumeroProthese(TypeConverter.getInstance().longToString(
					item.getNumeroProthese()));
			vo.setDateCreation(TypeConverter.getInstance().calendarToString(
					item.getDateCreation()));
			vo.setMontantEstime(TypeConverter.getInstance().booleanToString(
					item.getMontantEstime()));
			vo.setMntFraisAppareillage(TypeConverter.getInstance().doubleToString(
					item.getMntFraisAppareillage()));
			vo.setStatut("default");
		}
		/*
		 * if (itemList == null) return;
		 * 
		 * for (int i=1; i<itemList.size(); i++) { if (itemList.get(i)
		 * instanceof List) if (((List)itemList.get(i)).size() != 0) { } }
		 */
	}

	public void voToItem(ProtheseVO vo, Prothese item) {
		if (vo != null) {
			if (vo.getId() != null && !"".equals(vo.getId())) {
				item.setId(TypeConverter.getInstance().stringToLong(vo.getId()));
			}
			if (vo.getRefNatureProthese() != null) {
				NatureProthese nature = new NatureProthese();
				new NatureProtheseVOConverter().voToItem(
						vo.getRefNatureProthese(), nature);
				item.setRefNatureProthese(nature);
			}
			
			if (vo.getRefCentreProthese()!= null) {
				CentreProthese centre = new CentreProthese();
				new CentreProtheseVOConverter().voToItem(
						vo.getRefCentreProthese(), centre);
				item.setRefCentreProthese(centre);
			}
			
			
			if (vo.getRefEtatProthese() != null){
				EtatProthese  etat = new EtatProthese();
				new EtatProtheseVOConverter().voToItem(
						vo.getRefEtatProthese(), etat);
				item.setRefEtatProthese(etat);
			}
			
			if (vo.getReserveProthese() != null
					&& !"".equals(vo.getReserveProthese())) {
				item.setReserveProthese(TypeConverter.getInstance()
						.stringToDouble(
								StringUtils.replace(StringUtils.replace(
										vo.getReserveProthese(), " ", ""), ",",
										".")));
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
			if (vo.getDateProthese() != null) {
				item.setDateProthese(TypeConverter.getInstance()
						.stringToCalendar(vo.getDateProthese()));
			}
			if (vo.getMontantProthese() != null) {
				item.setMontantProthese(TypeConverter.getInstance()
						.stringToDouble(
								StringUtils.replace(StringUtils.replace(
										vo.getMontantProthese(), " ", ""), ",",
										".")));
			}
			
			if (vo.getReserveProthese() != null) {
				item.setReserveProthese(TypeConverter.getInstance()
						.stringToDouble(
								StringUtils.replace(StringUtils.replace(
										vo.getReserveProthese(), " ", ""), ",",
										".")));
			}
			
			if (vo.getMntFraisAppareillage() != null && !"".equals(vo.getMntFraisAppareillage()))
			{
				item.setMntFraisAppareillage(TypeConverter.getInstance()
						.stringToDouble(
								StringUtils.replace(StringUtils.replace(
										vo.getMntFraisAppareillage(), " ", ""), ",",
										".")));
			}
			else
				
			{
				item.setMntFraisAppareillage(0D);
			}
			
			if (vo.getIdProthese() != null && !"".equals(vo.getIdProthese())) {
				item.setIdProthese(TypeConverter.getInstance().stringToLong(
						vo.getIdProthese()));
			}
			if (vo.getNumeroProthese() != null
					&& !"".equals(vo.getNumeroProthese())) {
				item.setNumeroProthese(TypeConverter.getInstance()
						.stringToLong(vo.getNumeroProthese()));
			}
			if (vo.getDateCreation() != null) {
				item.setDateCreation(TypeConverter.getInstance()
						.stringToCalendar(vo.getDateCreation()));
			}
			if (vo.getMontantEstime() != null) {
				item.setMontantEstime(TypeConverter.getInstance()
						.stringToBoolean(vo.getMontantEstime()));
			}
			item.setStatut(vo.getStatut());
		}
	}

	protected void doValider(ProtheseVO vo) throws ValidationException {

	}
}
