package eai.devass.sinistreat.appli.restClient;

public class DocumentKappa {
    private String numSinistre = "";
    private String refVictime = "";
    private String refProcedure = "";
    private String titreDoc = "";
    private String fileName = "";

    public String getNumSinistre() {
        return numSinistre;
    }

    public void setNumSinistre(String numSinistre) {
        this.numSinistre = numSinistre;
    }

    public String getRefVictime() {
        return refVictime;
    }

    public void setRefVictime(String refVictime) {
        this.refVictime = refVictime;
    }

    public String getRefProcedure() {
        return refProcedure;
    }

    public void setRefProcedure(String refProcedure) {
        this.refProcedure = refProcedure;
    }

    public String getTitreDoc() {
        return titreDoc;
    }

    public void setTitreDoc(String titreDoc) {
        this.titreDoc = titreDoc;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "DocumentKappa{" +
                "numSinistre='" + numSinistre + '\'' +
                ", refVictime='" + refVictime + '\'' +
                ", refProcedure='" + refProcedure + '\'' +
                ", titreDoc='" + titreDoc + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
