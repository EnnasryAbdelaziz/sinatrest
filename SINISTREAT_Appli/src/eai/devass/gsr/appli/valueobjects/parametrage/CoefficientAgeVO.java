package eai.devass.gsr.appli.valueobjects.parametrage;

import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVO;
import ma.co.omnidata.framework.services.trace.ITracable;
import eai.devass.sinistreat.appli.utils.validation.Validate;

public class CoefficientAgeVO extends RechListeVO implements ITracable {

	private static final long serialVersionUID = 1L;
	@Validate(obligatoire = true)
	private String id;
	private String age;
	private String coefficient;
	private String type;

	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public CoefficientAgeVO() {

	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(String coefficient) {
		this.coefficient = coefficient;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTraceInfo() {
		// TODO Auto-generated method stub
		return null;
	}

}