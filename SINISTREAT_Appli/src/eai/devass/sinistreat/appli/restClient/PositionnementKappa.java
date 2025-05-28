package eai.devass.sinistreat.appli.restClient;

public class PositionnementKappa {

    private String refSinistre = "";
    private String typeExec = "";
    private String NumDossierTrib = "";
    private String Procedure = "";
    private String NomTribunal = "";
    private String TypeProc = "";

    public String getRefSinistre() {
        return refSinistre;
    }

    public void setRefSinistre(String refSinistre) {
        this.refSinistre = refSinistre;
    }

    public String getTypeExec() {
        return typeExec;
    }

    public void setTypeExec(String typeExec) {
        this.typeExec = typeExec;
    }

    public String getNumDossierTrib() {
        return NumDossierTrib;
    }

    public void setNumDossierTrib(String numDossierTrib) {
        NumDossierTrib = numDossierTrib;
    }

    public String getProcedure() {
        return Procedure;
    }

    public void setProcedure(String procedure) {
        Procedure = procedure;
    }

    public String getNomTribunal() {
        return NomTribunal;
    }

    public void setNomTribunal(String nomTribunal) {
        NomTribunal = nomTribunal;
    }

    public String getTypeProc() {
        return TypeProc;
    }

    public void setTypeProc(String typeProc) {
        TypeProc = typeProc;
    }

    @Override
    public String toString() {
        return "ProcedureKappa{" +
                "refSinistre='" + refSinistre + '\'' +
                ", typeExec='" + typeExec + '\'' +
                ", NumDossiertrib='" + NumDossierTrib + '\'' +
                ", Procedure='" + Procedure + '\'' +
                ", NomTribunal='" + NomTribunal + '\'' +
                ", typeProc='" + TypeProc + '\'' +
                '}';
    }
}
