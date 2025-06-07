package eai.devass.gsr.appli.valueobjects.parametrage;

import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class BeanDTOVO implements IValueObject  {
	
	
	private static final long serialVersionUID = 1L;
	
	private String typeMouvement;
	private String idRentier;
	private List<String> listObject;
	
	
	
	public String getTypeMouvement() {
		return typeMouvement;
	}
	public void setTypeMouvement(String typeMouvement) {
		this.typeMouvement = typeMouvement;
	}
	public String getIdRentier() {
		return idRentier;
	}
	public void setIdRentier(String idRentier) {
		this.idRentier = idRentier;
	}
	public List<String> getListObject() {
		return listObject;
	}
	public void setListObject(List<String> listObject) {
		this.listObject = listObject;
	}
	
	
	
	
	
	
}
