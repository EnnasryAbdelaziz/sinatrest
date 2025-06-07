package eai.devass.gsr.appli.valueobjects.parametrage;

import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class ListVO implements IValueObject{
	
	private static final long serialVersionUID = 1L;
	private List<IValueObject> listObjects;

	public void setListObjects(List<IValueObject> listObjects) {
		this.listObjects = listObjects;
	}

	public List<IValueObject> getListObjects() {
		return listObjects;
	}

}
