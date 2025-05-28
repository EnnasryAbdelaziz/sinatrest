package eai.devass.sinistreat.appli.modele.metier.conciliation;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserDelegation  implements Serializable{
	
	private long id;
	private Delegation refDelegation;
	private User refUser;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Delegation getRefDelegation() {
		return refDelegation;
	}
	public void setRefDelegation(Delegation refDelegation) {
		this.refDelegation = refDelegation;
	}
	public User getRefUser() {
		return refUser;
	}
	public void setRefUser(User refUser) {
		this.refUser = refUser;
	}

}
