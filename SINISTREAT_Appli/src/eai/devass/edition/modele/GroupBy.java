package eai.devass.edition.modele;

import java.util.List;

import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.IEntiteFactory;
import ma.co.omnidata.framework.services.lock.ILockable;

public class GroupBy implements IEntite, ILockable {
	
	private long id;
	private String name;
	private String groupByExpression;
	private Boolean avecColonnesInHeader;
	private Integer ordreAffichage;
	private List<Champ> refsChamps;
	private List<Variable> refsFooterVariables;
	private List<Parametre> refsParameters;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGroupByExpression() {
		return groupByExpression;
	}
	public void setGroupByExpression(String groupByExpression) {
		this.groupByExpression = groupByExpression;
	}
	public Boolean getAvecColonnesInHeader() {
		return avecColonnesInHeader;
	}
	public void setAvecColonnesInHeader(Boolean avecColonnesInHeader) {
		this.avecColonnesInHeader = avecColonnesInHeader;
	}
	public Integer getOrdreAffichage() {
		return ordreAffichage;
	}
	public void setOrdreAffichage(Integer ordreAffichage) {
		this.ordreAffichage = ordreAffichage;
	}
	public List<Champ> getRefsChamps() {
		return refsChamps;
	}
	public void setRefsChamps(List<Champ> refsChamps) {
		this.refsChamps = refsChamps;
	}
	public List<Variable> getRefsFooterVariables() {
		return refsFooterVariables;
	}
	public void setRefsFooterVariables(List<Variable> refsFooterVariables) {
		this.refsFooterVariables = refsFooterVariables;
	}
	public List<Parametre> getRefsParameters() {
		return refsParameters;
	}
	public void setRefsParameters(List<Parametre> refsParameters) {
		this.refsParameters = refsParameters;
	}
	public String getIdLockable() {
		return Long.toString(getId());
	}
	public String getLockableType() {
		return this.getClass().getName();
	}
	public IEntiteFactory getFactory() {
		// TODO Auto-generated method stub
		return null;
	}
}
