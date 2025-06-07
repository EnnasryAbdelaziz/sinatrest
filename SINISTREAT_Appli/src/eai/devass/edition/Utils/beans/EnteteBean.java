package eai.devass.edition.Utils.beans;

public class EnteteBean {
	private Integer height;
	private LigneTitreBean[] titreLignes;
	private String[] titreParametres;
	private String sousTitre;
	private ReportParameterBean[] parameters;
	
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public LigneTitreBean[] getTitreLignes() {
		return titreLignes;
	}
	public void setTitreLignes(LigneTitreBean[] titreLignes) {
		this.titreLignes = (LigneTitreBean[])titreLignes.clone();
	}
	public String[] getTitreParametres() {
		return titreParametres;
	}
	public void setTitreParametres(String[] titreParametres) {
		this.titreParametres = (String[])titreParametres.clone();
	}
	public String getSousTitre() {
		return sousTitre;
	}
	public void setSousTitre(String sousTitre) {
		this.sousTitre = sousTitre;
	}
	public ReportParameterBean[] getParameters() {
		return parameters;
	}
	public void setParameters(ReportParameterBean[] parameters) {
		this.parameters = (ReportParameterBean[])parameters.clone();
	}
}
