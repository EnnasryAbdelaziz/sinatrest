package eai.devass.sinistreat.appli.valueobjects.metier.conciliation;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Offre;
import eai.devass.sinistreat.appli.modele.metier.sinistre.AyantDroit;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.AyantDroitVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.DegreParenteVO;

public class AyantDroitOffreVO implements IValueObject{
	
	 public long id;
	 public String choix;
	 public String nom;
	 public String prenom;
	 public String coef;
	 public DegreParenteVO lienParente;
	 public String dateNaissance;
	 public Double tauxRente;
	 public Double reserveAyantDroit;
	 public Double montantArrerageAvCons;
	 public Double montantRente;
	 public String dateSignaturePv;
	 public Offre refOffre ;
	 public AyantDroitVO refAyantDroit ;
	 
	 
	 
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getChoix() {
		return choix;
	}
	public void setChoix(String choix) {
		this.choix = choix;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public DegreParenteVO getLienParente() {
		return lienParente;
	}
	public void setLienParente(DegreParenteVO lienParente) {
		this.lienParente = lienParente;
	}	
	public String getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public Double getTauxRente() {
		return tauxRente;
	}
	public void setTauxRente(Double tauxRente) {
		this.tauxRente = tauxRente;
	}
	public Double getReserveAyantDroit() {
		return reserveAyantDroit;
	}
	public void setReserveAyantDroit(Double reserveAyantDroit) {
		this.reserveAyantDroit = reserveAyantDroit;
	}
	public Double getMontantArrerageAvCons() {
		return montantArrerageAvCons;
	}
	public void setMontantArrerageAvCons(Double montantArrerageAvCons) {
		this.montantArrerageAvCons = montantArrerageAvCons;
	}
	public Double getMontantRente() {
		return montantRente;
	}
	public void setMontantRente(Double montantRente) {
		this.montantRente = montantRente;
	}
	public String getDateSignaturePv() {
		return dateSignaturePv;
	}
	public void setDateSignaturePv(String dateSignaturePv) {
		this.dateSignaturePv = dateSignaturePv;
	}
	public Offre getRefOffre() {
		return refOffre;
	}
	public void setRefOffre(Offre refOffre) {
		this.refOffre = refOffre;
	}
	public AyantDroitVO getRefAyantDroit() {
		return refAyantDroit;
	}
	public void setRefAyantDroit(AyantDroitVO refAyantDroit) {
		this.refAyantDroit = refAyantDroit;
	}
	public String getCoef() {
		return coef;
	}
	public void setCoef(String coef) {
		this.coef = coef;
	}
}
