package eai.devass.gsr.appli.modele.parametrage;
 
import eai.devass.commun.appli.modele.EntiteBO;





/**
 Etat rente:  Remariage
Rachat
@author Nom Prenom (email)
*/
public class TransitionEtat extends EntiteBO         {

	
	private static final long serialVersionUID = 1L;
	private EtatRentier refEtatActuel;
	private EtatRentier refEtatCible;
	private TypeMouvement refTypeMouvement;
	
	
	public EtatRentier getRefEtatActuel() {
		return refEtatActuel;
	}
	public void setRefEtatActuel(EtatRentier refEtatActuel) {
		this.refEtatActuel = refEtatActuel;
	}
	public EtatRentier getRefEtatCible() {
		return refEtatCible;
	}
	public void setRefEtatCible(EtatRentier refEtatCible) {
		this.refEtatCible = refEtatCible;
	}
	public TypeMouvement getRefTypeMouvement() {
		return refTypeMouvement;
	}
	public void setRefTypeMouvement(TypeMouvement refTypeMouvement) {
		this.refTypeMouvement = refTypeMouvement;
	}
	
	
	

}

