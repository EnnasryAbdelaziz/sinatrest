package eai.devass.sinistreat.appli.valueobjects.serialisation;

import java.io.Serializable;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.securite.IUtilisateur;


 
public class GlobalVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Entete entete = new Entete();
	private IValueObject object;
	private IUtilisateur user;
	private PagerVO pager;
	
	
	
	public GlobalVO(ResultVO object) {
		this.object = object;
		
	}
	public GlobalVO() {
		
	}
	
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
	public IUtilisateur getUser() {
		return user;
	}
	public void setUser(IUtilisateur user) {
		this.user = user;
	}
}
