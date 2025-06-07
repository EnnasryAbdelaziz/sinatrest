package eai.devass.gsr.appli.modele.metier.mouvements;
 
import java.util.Calendar;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.lock.ILockable;
import eai.devass.commun.appli.converter.AConverter;
import eai.devass.gsr.appli.manager.metier.mouvements.MvtRemiseEnCoursFactory;

/**
 * MvtRemiseEnCours:
 * 
 * @author JEFFAR Hicham (jeffarhi@eurafric-information.com)
 */
@AConverter(entiteMapping="eai.devass.gsr.appli.valueobjects.metier.mouvements.MvtRemiseEnCoursVO")
public class MvtRemiseEnCours extends  Mouvement implements IEntite, ILockable {

	private static final long serialVersionUID = 1L;


	/**
	motif   
	*/ 
	@Indexation(label="motif",analyzed=false)
	private String motif;
	/**
	datMiseEnVigeur   
	*/ 
	@Indexation(label="datMiseEnVigeur",analyzed=false)
	private Calendar datMiseEnVigeur;
	
	/**
	 * datSuspension
	 */
	@Indexation(label = "datSuspension", analyzed = false)
	private Calendar datSuspension;
	
	public String toString(){
		return super.toString();
	}

	/**
	Methode qui retourne l' instance de la factory d'une entité
	@returns L' entite Factory
	*/
	public EntiteFactory getFactory() {
		return new MvtRemiseEnCoursFactory();
	}



	public String getMotif() {
		return this.motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}
	public Calendar getDatMiseEnVigeur() {
		return this.datMiseEnVigeur;
	}

	public void setDatMiseEnVigeur(Calendar datMiseEnVigeur) {
		this.datMiseEnVigeur = datMiseEnVigeur;
	}

	public Calendar getDatSuspension() {
		return datSuspension;
	}

	public void setDatSuspension(Calendar datSuspension) {
		this.datSuspension = datSuspension;
	}

	/**
	Methode qui retourne l' Id du lockable
	@returns id du locakble
	*/
	public String getIdLockable() {
		return Long.toString(getId());
	}

	/**
	Methode qui retourne le type du lockable
	@returns type du locakble
	*/
	public String getLockableType() {
		return this.getClass().getName();
	}
	

}

