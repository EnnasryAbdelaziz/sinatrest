package eai.devass.sinistreat.appli.authentification;

import java.io.Serializable;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.securite.impl.OMNIAction;


public class Fonctionnalite extends OMNIAction implements Serializable
{
   
	private static final long serialVersionUID = 1L;
	
   private Integer code;
   private String libelle;
   private String titre;
   private String type;

   private String actionClassName;
   private Boolean isSecure;
   
   
   public Boolean isSecure() {
		if(isSecure != null) {
			return isSecure;
		} else {
			return false;
		}
	}
   
   public Fonctionnalite() 
   {
    
   }
   
   public String getDescription(){
	   return code + " - " + libelle;
   }
   
   /**
    * Access method for the code property.
    * 
    * @return   the current value of the code property
    */
   public Integer getCode() 
   {
      return code;
   }
   
   /**
    * Sets the value of the code property.
    * 
    * @param aCode the new value of the code property
    */
   public void setCode(Integer aCode) 
   {
      code = aCode;
   }
   
   /**
    * Access method for the libelle property.
    * 
    * @return   the current value of the libelle property
    */
   public String getLibelle() 
   {
      return libelle;
   }
   
   /**
    * Sets the value of the libelle property.
    * 
    * @param aLibelle the new value of the libelle property
    */
   public void setLibelle(String aLibelle) 
   {
      libelle = aLibelle;
   }
   
   /**
    * Access method for the titre property.
    * 
    * @return   the current value of the titre property
    */
   public String getTitre() 
   {
      return titre;
   }
   
   /**
    * Sets the value of the titre property.
    * 
    * @param aTitre the new value of the titre property
    */
   public void setTitre(String aTitre) 
   {
      titre = aTitre;
   }
   

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return Returns the refProduitsValables.
	 
	public Collection getRefProduitsValables() {
		return refProduitsValables;
	}*/
	
	/**
	 * @param refProduitsValables The refProduitsValables to set.
	 
	public void setRefProduitsValables(Collection refProduitsValables) {
		this.refProduitsValables = refProduitsValables;
	}*/
	
	/* (non-Javadoc)
	 * @see ma.co.omnidata.framework.services.entites.IEntite#getFactory()
	 */
	public EntiteFactory getFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getActionClassName() {
		return actionClassName;
	}

	public void setActionClassName(String actionClassName) {
		this.actionClassName = actionClassName;
	}

	public Boolean getIsSecure() {
		return isSecure;
	}

	public void setIsSecure(Boolean isSecure) {
		this.isSecure = isSecure;
	}

//	public Long getId() {
//		return id;
//	}

//	public void setId(Long id) {
//		this.id = id;
//	}
	
	
	
}
