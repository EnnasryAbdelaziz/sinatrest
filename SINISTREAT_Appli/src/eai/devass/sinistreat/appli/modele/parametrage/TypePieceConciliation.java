/***********************************************************************
 * Module:  Etat.java
 * Author:  Administrateur
 * Purpose: Defines the Class Etat
 ***********************************************************************/

package eai.devass.sinistreat.appli.modele.parametrage;

import java.util.List;

/** @pdOid 1f8e6c5e-49a3-49b4-8e34-d868502fc3db */
public class TypePieceConciliation implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	
	public enum TypePieceConciliationId {  
		  
	    DECES(2), VICTIME(1);  
	      
	     private long id ;  
	      
	     private TypePieceConciliationId(long id) {  
	         this.id = id ;  
	    }  
	      
	     public long getId() {  
	         return  this.id ;  
	    }  
	}
	
	public String getTypeListPiece() {
		return typeListPiece;
	}
	public TypePieceConciliation(Long id, String typeListPiece,
			List<LiaisonTypePiece> listTypePiece) {
		this.id = id;
		this.typeListPiece = typeListPiece;
		this.listTypePiece = listTypePiece;
	}


	public TypePieceConciliation(long id) {
		this.id = id;
	}


	public void setTypeListPiece(String typeListPiece) {
		this.typeListPiece = typeListPiece;
	}
	
	
	private Long id;
	/** @pdOid b39bf1f3-fef4-409f-b168-56ed37970d8a */
	private String typeListPiece;

	/** @pdOid 1de0474c-1b2b-4dd2-ac43-03723ca0a40e */
	
  private List<LiaisonTypePiece> listTypePiece ;
	
	
	
	public List<LiaisonTypePiece> getListTypePiece() {
		return listTypePiece;
	}



	public void setListTypePiece(List<LiaisonTypePiece> listTypePiece) {
		this.listTypePiece = listTypePiece;
	}



	/**
	 * Empty constructor which is required by Hibernate
	 * 
	 */
	public TypePieceConciliation() {
		
	}



	/** @pdOid 8f750112-d133-4a6a-b04e-95b2834e9380 */

	
	
	
	
	
	
	public void setId(Long id) 
	{
		this.id = id;
	}
	
	public Long getId() 
	{
		return id;
	}
    
}