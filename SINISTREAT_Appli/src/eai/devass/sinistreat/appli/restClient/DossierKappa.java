package eai.devass.sinistreat.appli.restClient;

public class DossierKappa {

    private String RefSinistre = "";
    private String NumPolice = "";
    private String NumDec = "";
    private String DateSin = "";
    private String Immat = "";
    private String TypeDec = "";
    private String NomAssure = "";
    private String SexeAssure = "";
    private String AdresseAssure = "";
    private String NumDoc = "";
    private String VilleSurv = "";
    private String ConduSouscripteur = "";
    private String Avocat = "";
    private Conducteur Conducteur;
    private String dateDeclaration = "";
    private String typeClient = "";

    public String getRefSinistre() {
        return RefSinistre;
    }

    public void setRefSinistre(String refSinistre) {
        RefSinistre = refSinistre;
    }

    public String getNumPolice() {
        return NumPolice;
    }

    public void setNumPolice(String numPolice) {
        NumPolice = numPolice;
    }

    public String getNumDec() {
        return NumDec;
    }

    public void setNumDec(String numDec) {
        NumDec = numDec;
    }

    public String getDateSin() {
        return DateSin;
    }

    public void setDateSin(String dateSin) {
        DateSin = dateSin;
    }

    public String getImmat() {
        return Immat;
    }

    public void setImmat(String immat) {
        Immat = immat;
    }

    public String getTypeDec() {
        return TypeDec;
    }

    public void setTypeDec(String typeDec) {
        TypeDec = typeDec;
    }

    public String getNomAssure() {
        return NomAssure;
    }

    public void setNomAssure(String nomAssure) {
        NomAssure = nomAssure;
    }

    public String getSexeAssure() {
        return SexeAssure;
    }

    public void setSexeAssure(String sexeAssure) {
        SexeAssure = sexeAssure;
    }

    public String getAdresseAssure() {
        return AdresseAssure;
    }

    public void setAdresseAssure(String adresseAssure) {
        AdresseAssure = adresseAssure;
    }

    public String getNumDoc() {
        return NumDoc;
    }

    public void setNumDoc(String numDoc) {
        NumDoc = numDoc;
    }

    public String getVilleSurv() {
        return VilleSurv;
    }

    public void setVilleSurv(String villeSurv) {
        VilleSurv = villeSurv;
    }

    public String getConduSouscripteur() {
        return ConduSouscripteur;
    }

    public void setConduSouscripteur(String conduSouscripteur) {
        ConduSouscripteur = conduSouscripteur;
    }

    public String getAvocat() {
        return Avocat;
    }

    public void setAvocat(String avocat) {
        Avocat = avocat;
    }

    public Conducteur getConducteur() {
        return Conducteur;
    }

    public void setConducteur(Conducteur conducteur) {
        Conducteur = conducteur;
    }

    public String getDateDeclaration() {
        return dateDeclaration;
    }

    public void setDateDeclaration(String dateDeclaration) {
        this.dateDeclaration = dateDeclaration;
    }

    public String getTypeClient() {
        return typeClient;
    }

    public void setTypeClient(String typeClient) {
        this.typeClient = typeClient;
    }
}
