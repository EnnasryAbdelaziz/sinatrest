package eai.devass.sinistreat.appli.valueobjects.metier.conciliation;


import eai.devass.sinistreat.appli.modele.parametrage.MotifConvocation;
import eai.devass.sinistreat.appli.valueobjects.parametrage.MotifConvocationVO;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

@SuppressWarnings("all")

public class ConvocationVO implements IValueObject{
	
	private long id;
	private String dateConvocation;
	private String dateVisite;
	private String dateCreation;
	private String dateModification;
	private MotifConvocationVO refMotifConvocation;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDateConvocation() {
		return dateConvocation;
	}
	public void setDateConvocation(String dateConvocation) {
		this.dateConvocation = dateConvocation;
	}
	public String getDateVisite() {
		return dateVisite;
	}
	public void setDateVisite(String dateVisite) {
		this.dateVisite = dateVisite;
	}
	public String getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}
	public String getDateModification() {
		return dateModification;
	}
	public void setDateModification(String dateModification) {
		this.dateModification = dateModification;
	}
	public MotifConvocationVO getRefMotifConvocation() {
		return refMotifConvocation;
	}
	public void setRefMotifConvocation(MotifConvocationVO refMotifConvocation) {
		this.refMotifConvocation = refMotifConvocation;
	}


}
