package eai.devass.gsr.appli.valueobjects.parametrage;

import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVO;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.trace.ITracable;
import eai.devass.commun.appli.converter.AConverter;

/**
 * Value Object de StatutHeritier
 * @author elfaismo
 *
 */
@AConverter(entiteDist="eai.devass.gsr.appli.valueobjects.parametrage.StatutHeritier")
public class StatutHeritierVO extends  RechListeVO implements ITracable{

	private static final long serialVersionUID = 1L;
	
	@AConverter(propertyDist="id")
	private long id;

	/**
	 * libelle
	 */ 
	@Indexation(label="libelle",analyzed=false)
	@AConverter(propertyDist="libelle")
	private String libelle;
	/**
	 * dateCreation
	 */
	@Indexation(label="dateCreation",analyzed=false) 
	@AConverter(propertyDist="dateCreation")
	private String dateCreation;

	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * @return the dateCreation
	 */
	public String getDateCreation() {
		return dateCreation;
	}

	/**
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTraceInfo() {
		// TODO Auto-generated method stub
		return null;
	}



	
	


}


