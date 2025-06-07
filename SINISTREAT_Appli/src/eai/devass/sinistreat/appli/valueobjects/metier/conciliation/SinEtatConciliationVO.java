package eai.devass.sinistreat.appli.valueobjects.metier.conciliation;


import java.util.Date;

import eai.devass.sinistreat.appli.valueobjects.parametrage.EtatConciliationVO;

/**
 * @author karabima
 *
 */
public class SinEtatConciliationVO {
	
private long id ;
private String userCreateur ;
private Date dateEtat ;
private EtatConciliationVO refEtatConciliation;
private ConciliationVO refConciliation ;

public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getUserModificateur() {
	return userCreateur;
}
public void setUserModificateur(String userModificateur) {
	this.userCreateur = userModificateur;
}
public Date getDateEtat() {
	return dateEtat;
}
public void setDateEtat(Date dateEtat) {
	this.dateEtat = dateEtat;
}
public EtatConciliationVO getRefEtatConciliation() {
	return refEtatConciliation;
}
public void setRefEtatConciliation(EtatConciliationVO refEtatConciliation) {
	this.refEtatConciliation = refEtatConciliation;
}
public ConciliationVO getRefConciliation() {
	return refConciliation;
}
public void setRefConciliation(ConciliationVO refConciliation) {
	this.refConciliation = refConciliation;
}



}
