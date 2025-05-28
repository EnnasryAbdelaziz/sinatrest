/***********************************************************************
 * Module:  Etat.java
 * Author:  Administrateur
 * Purpose: Defines the Class Etat
 ***********************************************************************/

package eai.devass.gsr.appli.modele.parametrage;

import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.IEntiteFactory;
import ma.co.omnidata.framework.services.lock.ILockable;

/** @pdOid 1f8e6c5e-49a3-49b4-8e34-d868502fc3db */
public class CoefficientAge implements IEntite, ILockable          {

	private long id;
	private static final long serialVersionUID = 1L;
	
	/** @pdOid b39bf1f3-fef4-409f-b168-56ed37970d8a */
	private Long age;

	/** @pdOid 1de0474c-1b2b-4dd2-ac43-03723ca0a40e */
	private Double coefficient;

	private String type;

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	/**
	 * Empty constructor which is required by Hibernate
	 * 
	 */
	public CoefficientAge() {

	}

	public CoefficientAge(Long age) {
		this.age = age;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public Double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(Double coefficient) {
		this.coefficient = coefficient;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIdLockable() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLockableType() {
		// TODO Auto-generated method stub
		return null;
	}

	public IEntiteFactory getFactory() {
		// TODO Auto-generated method stub
		return null;
	}

}