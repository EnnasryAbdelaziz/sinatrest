/***********************************************************************
 * Module:  Etat.java
 * Author:  Administrateur
 * Purpose: Defines the Class Etat
 ***********************************************************************/

package eai.devass.sinistreat.appli.modele.parametrage;

import java.io.Serializable;
import java.util.Date;

/** @pdOid 1f8e6c5e-49a3-49b4-8e34-d868502fc3db */
public class LiaisonTypePiece implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	private Date dateCreation ;
	private Date dateSuppression ;	
	private Piece refPiece ;
	private TypePieceConciliation refTypePieceConciliation  ;
	
	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateSuppression() {
		return dateSuppression;
	}

	public void setDateSuppression(Date dateSuppression) {
		this.dateSuppression = dateSuppression;
	}

	public Piece getRefPiece() {
		return refPiece;
	}

	public void setRefPiece(Piece refPiece) {
		this.refPiece = refPiece;
	}
	
	public TypePieceConciliation getRefTypePieceConciliation() {
		return refTypePieceConciliation;
	}

	public void setRefTypePieceConciliation(
			TypePieceConciliation refTypePieceConciliation) {
		this.refTypePieceConciliation = refTypePieceConciliation;
	}

	public LiaisonTypePiece() {
		
	}

	public void setId(Long id) 
	{
		this.id = id;
	}
	
	public Long getId() 
	{
		return id;
	}
    
}