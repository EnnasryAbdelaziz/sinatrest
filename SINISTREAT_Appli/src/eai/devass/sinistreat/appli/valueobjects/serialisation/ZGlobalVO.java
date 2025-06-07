package eai.devass.sinistreat.appli.valueobjects.serialisation;

import java.io.Serializable;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.authentification.Utilisateur;

 
public class ZGlobalVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Entete entete;
	private IValueObject object;
	private Utilisateur user;
	private PagerVO pager;
	
	
	
	public Entete getEntete() {
		return entete;
	}
	public void setEntete(Entete entete) {
		this.entete = entete;
	}
	public IValueObject getObject() {
		return object;
	}
	public void setObject(IValueObject object) {
		this.object = object;
	}
	public PagerVO getPager() {
		return pager;
	}
	public void setPager(PagerVO pager) {
		this.pager = pager;
	}
	public Utilisateur getUser() {
		return user;
	}
	public void setUser(Utilisateur user) {
		this.user = user;
	}
}
