package eai.devass.sinistreat.appli.modele.metier.reglement;

import java.util.Date;

import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.IEntiteFactory;

import eai.devass.sinistreat.appli.modele.parametrage.CategorieExAAA;
import eai.devass.sinistreat.appli.modele.parametrage.IntermediaireExAAA;

public class QuittanceExAAA implements IEntite {

    private static final long serialVersionUID = 1L;

    private long id;
    private Integer compagnie;
    private String numeroSinistre;
    private Integer debutCheque;
    private Date dateSinistre;
    private String assure;
    private CategorieExAAA refCodeCategorie;
    private String codePrestataire;
    private String typePrestataire;
    private IntermediaireExAAA refCodeIntermediaire;
    private Date dateEffetPolice;
    private Date dateReglement;
    private Date dateReglementComptable;
    private String montantCheque;
    private String numeroCheque;
    private String numeroPolice;
    private String ordrePolice;
    private String rang;
    private String rubriqueReglement;
    private String trimestre;
    private String sequence;
    private String branche;

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id == null) {
            this.id = 0;
        } else {
            this.id = id.longValue();
        }
    }

    public void setId(long arg0) {
        this.id = arg0;

    }

    public Integer getCompagnie() {
        return compagnie;
    }

    public void setCompagnie(Integer compagnie) {
        this.compagnie = compagnie;
    }

    public String getNumeroSinistre() {
        return numeroSinistre;
    }

    public void setNumeroSinistre(String numeroSinistre) {
        this.numeroSinistre = numeroSinistre;
    }

    public Integer getDebutCheque() {
        return debutCheque;
    }

    public void setDebutCheque(Integer debutCheque) {
        this.debutCheque = debutCheque;
    }

    public Date getDateSinistre() {
        return dateSinistre;
    }

    public void setDateSinistre(Date dateSinistre) {
        this.dateSinistre = dateSinistre;
    }

    public String getAssure() {
        return assure;
    }

    public void setAssure(String assure) {
        this.assure = assure;
    }

    public CategorieExAAA getRefCodeCategorie() {
        return refCodeCategorie;
    }

    public void setRefCodeCategorie(CategorieExAAA refCodeCategorie) {
        this.refCodeCategorie = refCodeCategorie;
    }

    public String getCodePrestataire() {
        return codePrestataire;
    }

    public void setCodePrestataire(String codePrestataire) {
        this.codePrestataire = codePrestataire;
    }

    public String getTypePrestataire() {
        return typePrestataire;
    }

    public void setTypePrestataire(String typePrestataire) {
        this.typePrestataire = typePrestataire;
    }

    public IntermediaireExAAA getRefCodeIntermediaire() {
        return refCodeIntermediaire;
    }

    public void setRefCodeIntermediaire(IntermediaireExAAA refCodeIntermediaire) {
        this.refCodeIntermediaire = refCodeIntermediaire;
    }

    public Date getDateEffetPolice() {
        return dateEffetPolice;
    }

    public void setDateEffetPolice(Date dateEffetPolice) {
        this.dateEffetPolice = dateEffetPolice;
    }

    public Date getDateReglement() {
        return dateReglement;
    }

    public void setDateReglement(Date dateReglement) {
        this.dateReglement = dateReglement;
    }

    public Date getDateReglementComptable() {
        return dateReglementComptable;
    }

    public void setDateReglementComptable(Date dateReglementComptable) {
        this.dateReglementComptable = dateReglementComptable;
    }

    public String getMontantCheque() {
        return montantCheque;
    }

    public void setMontantCheque(String montantCheque) {
        this.montantCheque = montantCheque;
    }

    public String getNumeroCheque() {
        return numeroCheque;
    }

    public void setNumeroCheque(String numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    public String getNumeroPolice() {
        return numeroPolice;
    }

    public void setNumeroPolice(String numeroPolice) {
        this.numeroPolice = numeroPolice;
    }

    public String getOrdrePolice() {
        return ordrePolice;
    }

    public void setOrdrePolice(String ordrePolice) {
        this.ordrePolice = ordrePolice;
    }

    public String getRang() {
        return rang;
    }

    public void setRang(String rang) {
        this.rang = rang;
    }

    public String getRubriqueReglement() {
        return rubriqueReglement;
    }

    public void setRubriqueReglement(String rubriqueReglement) {
        this.rubriqueReglement = rubriqueReglement;
    }

    public String getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(String trimestre) {
        this.trimestre = trimestre;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public IEntiteFactory getFactory() {
        return null;
    }

    public String getBranche() {
        return branche;
    }

    public void setBranche(String branche) {
        this.branche = branche;
    }

}
