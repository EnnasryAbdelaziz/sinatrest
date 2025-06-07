package eai.devass.sinistreat.appli.modele.metier.conciliation;

import java.util.Date;

import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.IEntiteFactory;

import eai.devass.sinistreat.appli.modele.metier.sinistre.AyantDroit;
import eai.devass.sinistreat.appli.modele.parametrage.DegreParente;

public class AyantDroitOffre implements IEntite {

    public long id;
    public boolean choix;
    public String nom;
    public String prenom;
    public Double coef;
    public DegreParente lienParente;
    public Date dateNaissance;
    public Double tauxRente;
    public Double reserveAyantDroit;
    public Double montantArrerageAvCons;
    public Double montantRente;
    public Date dateSignaturePv;
    public Offre refOffre;
    public AyantDroit refAyantDroit;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isChoix() {
        return choix;
    }

    public void setChoix(boolean choix) {
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

    public DegreParente getLienParente() {
        return lienParente;
    }

    public void setLienParente(DegreParente lienParente) {
        this.lienParente = lienParente;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
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

    public Date getDateSignaturePv() {
        return dateSignaturePv;
    }

    public void setDateSignaturePv(Date dateSignaturePv) {
        this.dateSignaturePv = dateSignaturePv;
    }

    public Offre getRefOffre() {
        return refOffre;
    }

    public void setRefOffre(Offre refOffre) {
        this.refOffre = refOffre;
    }

    public AyantDroit getRefAyantDroit() {
        return refAyantDroit;
    }

    public void setRefAyantDroit(AyantDroit refAyantDroit) {
        this.refAyantDroit = refAyantDroit;
    }

    public Double getCoef() {
        return coef;
    }

    public void setCoef(Double coef) {
        this.coef = coef;
    }

    public IEntiteFactory getFactory() {
        return null;
    }
}
