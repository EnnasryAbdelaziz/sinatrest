package eai.devass.sinistreat.appli.valueobjects.metier.conciliation;


import java.util.Date;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.valueobjects.parametrage.PieceVO;

@SuppressWarnings("all")
public class PiecesVO  implements IValueObject{

private long id ;
private String dateCreation ;
private String dateModification ;
private String dateReception ;
private String recu ;
private PieceVO refPiece ;

public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getDateCreation() {
	return dateCreation;
}
public void setDateCreation(String dateCreation) {
	this.dateCreation = dateCreation;
}
public String getDateModification() {
	return dateModification;
}
public void setDateModification(String dateModification) {
	this.dateModification = dateModification;
}	
public String getDateReception() {
	return dateReception;
}
public void setDateReception(String dateReception) {
	this.dateReception = dateReception;
}
	
public String getRecu() {
	return recu;
}
public void setRecu(String recu) {
	this.recu = recu;
}
public PieceVO getRefPiece() {
	return refPiece;
}
public void setRefPiece(PieceVO pi) {
	this.refPiece = pi;
}
	
}
