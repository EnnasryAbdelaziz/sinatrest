package eai.devass.edition.modele;

import java.util.List;

import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.IEntiteFactory;
import eai.devass.edition.manager.RapportFactory;

public class Rapport implements IEntite {
	
	private long id;
	private String code;
	private String description;
	private Template refTemplate;
	private Entete refEntete;
	private String requeteSql;
	private List<Champ> refsChamps;
	private List<Parametre> refsParametres;
	private List<GroupBy> refsGroupBys;
	private RecapPage refRecapPage;
	private RecapGenerale refRecapGenerale;
	
	public Rapport() {
		super();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Template getRefTemplate() {
		return refTemplate;
	}
	public void setRefTemplate(Template refTemplate) {
		this.refTemplate = refTemplate;
	}
	public Entete getRefEntete() {
		return refEntete;
	}
	public void setRefEntete(Entete refEntete) {
		this.refEntete = refEntete;
	}
	public String getRequeteSql() {
		return requeteSql;
	}
	public void setRequeteSql(String requeteSql) {
		this.requeteSql = requeteSql;
	}
	public List<Champ> getRefsChamps() {
		return refsChamps;
	}
	public void setRefsChamps(List<Champ> refsChamps) {
		this.refsChamps = refsChamps;
	}
	public List<Parametre> getRefsParametres() {
		return refsParametres;
	}
	public void setRefsParametres(List<Parametre> refsParametres) {
		this.refsParametres = refsParametres;
	}
	public List<GroupBy> getRefsGroupBys() {
		return refsGroupBys;
	}
	public void setRefsGroupBys(List<GroupBy> refsGroupBys) {
		this.refsGroupBys = refsGroupBys;
	}
	public RecapPage getRefRecapPage() {
		return refRecapPage;
	}
	public void setRefRecapPage(RecapPage refRecapPage) {
		this.refRecapPage = refRecapPage;
	}
	public RecapGenerale getRefRecapGenerale() {
		return refRecapGenerale;
	}

	public void setRefRecapGenerale(RecapGenerale refRecapGenerale) {
		this.refRecapGenerale = refRecapGenerale;
	}

	public String getIdLockable() {
		return Long.toString(getId());
	}
	public String getLockableType() {
		return this.getClass().getName();
	}
	public IEntiteFactory getFactory() {
		return new RapportFactory();
	}
}
