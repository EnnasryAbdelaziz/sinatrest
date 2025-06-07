/**
 * 
 */
package eai.devass.sinistreat.appli.modele.metier.conciliation;

import java.util.Date;

import eai.devass.sinistreat.appli.modele.parametrage.EtatConciliation;

/**
 * @author karabima
 *
 */
public class SinEtatConciliation {
	
private long id ;
private String userCreateur ;
private Date dateEtat ;
private EtatConciliation refEtatConciliation;
private Conciliation refConciliation ;
	
	
public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}



public Date getDateEtat() {
	return dateEtat;
}

public void setDateEtat(Date dateEtat) {
	this.dateEtat = dateEtat;
}

public EtatConciliation getRefEtatConciliation() {
	return refEtatConciliation;
}

public void setRefEtatConciliation(EtatConciliation refEtatConciliation) {
	this.refEtatConciliation = refEtatConciliation;
}

public Conciliation getRefConciliation() {
	return refConciliation;
}

public void setRefConciliation(Conciliation refConciliation) {
	this.refConciliation = refConciliation;
}

public String getUserCreateur() {
	return userCreateur;
}

public void setUserCreateur(String userCreateur) {
	this.userCreateur = userCreateur;
}


}
