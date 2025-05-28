package eai.devass.sinistreat.appli.restClient;

public class ProcedureKappa {
    private String refSinistre = "";
    private String idProcedure = "";
    private String numDossierTrib = "";
    private String anneeDossierTrib = "";
    private String procedure = "";
    private String typeProc = "";
    private String nomJuge = "";
    private String nomTribunal = "";
    private String avocatAdv = "";
    private String AvocatCons = "";

    public String getRefSinistre() {
        return refSinistre;
    }

    public void setRefSinistre(String refSinistre) {
        this.refSinistre = refSinistre;
    }

    public String getIdProcedure() {
        return idProcedure;
    }

    public void setIdProcedure(String idProcedure) {
        this.idProcedure = idProcedure;
    }

    public String getNumDossierTrib() {
        return numDossierTrib;
    }

    public void setNumDossierTrib(String numDossierTrib) {
        this.numDossierTrib = numDossierTrib;
    }

    public String getAnneeDossierTrib() {
        return anneeDossierTrib;
    }

    public void setAnneeDossierTrib(String anneeDossierTrib) {
        this.anneeDossierTrib = anneeDossierTrib;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public String getTypeProc() {
        return typeProc;
    }

    public void setTypeProc(String typeProc) {
        this.typeProc = typeProc;
    }

    public String getNomJuge() {
        return nomJuge;
    }

    public void setNomJuge(String nomJuge) {
        this.nomJuge = nomJuge;
    }

    public String getNomTribunal() {
        return nomTribunal;
    }

    public void setNomTribunal(String nomTribunal) {
        this.nomTribunal = nomTribunal;
    }

    public String getAvocatAdv() {
        return avocatAdv;
    }

    public void setAvocatAdv(String avocatAdv) {
        this.avocatAdv = avocatAdv;
    }

    public String getAvocatCons() {
        return AvocatCons;
    }

    public void setAvocatCons(String avocatCons) {
        AvocatCons = avocatCons;
    }

    @Override
    public String toString() {
        return "ProcedureKappa{" +
                "refSinistre='" + refSinistre + '\'' +
                ", idProcedure='" + idProcedure + '\'' +
                ", numDossierTrib='" + numDossierTrib + '\'' +
                ", anneeDossierTrib='" + anneeDossierTrib + '\'' +
                ", procedure='" + procedure + '\'' +
                ", typeProc='" + typeProc + '\'' +
                ", nomJuge='" + nomJuge + '\'' +
                ", nomTribunal='" + nomTribunal + '\'' +
                ", avocatAdv='" + avocatAdv + '\'' +
                ", AvocatCons='" + AvocatCons + '\'' +
                '}';
    }
}
