package eai.devass.sinistreat.appli.modele.metier.conciliation;

import java.io.Serializable;
import java.util.Date;

import eai.devass.sinistreat.appli.modele.parametrage.MotifConvocation;

@SuppressWarnings("all")
public class Convocation implements Serializable{
	
private long id;
private Date dateConvocation;
private Date dateVisite;
private Date dateCreation;
private Date dateModification;
private MotifConvocation refMotifConvocation;
private Conciliation refConciliation;

public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public Date getDateConvocation() {
	return dateConvocation;
}
public void setDateConvocation(Date dateConvocation) {
	this.dateConvocation = dateConvocation;
}
public Date getDateVisite() {
	return dateVisite;
}
public void setDateVisite(Date dateVisite) {
	this.dateVisite = dateVisite;
}
public Date getDateCreation() {
	return dateCreation;
}
public void setDateCreation(Date dateCreation) {
	this.dateCreation = dateCreation;
}
public Date getDateModification() {
	return dateModification;
}
public void setDateModification(Date dateModification) {
	this.dateModification = dateModification;
}
public MotifConvocation getRefMotifConvocation() {
	return refMotifConvocation;
}
public void setRefMotifConvocation(MotifConvocation refMotifConvocation) {
	this.refMotifConvocation = refMotifConvocation;
}
public Conciliation getRefConciliation() {
	return refConciliation;
}
public void setRefConciliation(Conciliation refConciliation) {
	this.refConciliation = refConciliation;
}


}
