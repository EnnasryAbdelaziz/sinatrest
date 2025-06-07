package eai.devass.edition.modele;

import java.sql.Blob;

import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.IEntiteFactory;
import ma.co.omnidata.framework.services.lock.ILockable;
import eai.devass.edition.manager.TemplateFactory;

public class Template implements IEntite, ILockable {
	
	private long id;
	private String code;
	private Blob fichierTemplate; 
	private String description;
	
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
	public Blob getFichierTemplate() {
		return fichierTemplate;
	}
	public void setFichierTemplate(Blob fichierTemplate) {
		this.fichierTemplate = fichierTemplate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getIdLockable() {
		return Long.toString(getId());
	}
	public String getLockableType() {
		return this.getClass().getName();
	}
	public IEntiteFactory getFactory() {
		return new TemplateFactory();
	}
}
