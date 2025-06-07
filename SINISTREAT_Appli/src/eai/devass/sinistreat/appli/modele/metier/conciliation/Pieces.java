package eai.devass.sinistreat.appli.modele.metier.conciliation;

import java.io.Serializable;
import java.util.Date;

import eai.devass.commun.appli.converter.AConverter;
import eai.devass.sinistreat.appli.modele.parametrage.Piece;

@SuppressWarnings("all")
public class Pieces implements Serializable{

private long id ;
private Date dateCreation ;
private Date dateModification ;
private Date dateReception ;
private Boolean recu ;
private Conciliation refConciliation ;
private Piece refPiece ;
//Pour la convertion (VO BO)
private transient String[] propertiesToConvert;


public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
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

public Conciliation getRefConciliation() {
	return refConciliation;
}
public void setRefConciliation(Conciliation refConciliation) {
	this.refConciliation = refConciliation;
}

public Date getDateReception() {
	return dateReception;
}
public void setDateReception(Date dateReception) {
	this.dateReception = dateReception;
}
	
public Boolean getRecu() {
	return recu;
}
public void setRecu(Boolean recu) {
	this.recu = recu;
}
public Piece getRefPiece() {
	return refPiece;
}
public void setRefPiece(Piece refPiece) {
	this.refPiece = refPiece;
}
public String[] getPropertiesToConvert() {
	return propertiesToConvert;
}

public void setPropertiesToConvert(String[] propertiesToConvert) {

	String[] copyPropertiesToConvert = null;
	if (propertiesToConvert != null) {
		copyPropertiesToConvert = propertiesToConvert.clone();
	}

	this.propertiesToConvert = copyPropertiesToConvert;
}

}
