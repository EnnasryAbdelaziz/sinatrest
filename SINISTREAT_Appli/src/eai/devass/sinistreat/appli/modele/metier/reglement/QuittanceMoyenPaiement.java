package eai.devass.sinistreat.appli.modele.metier.reglement;

import java.util.List;

import com.rmawatanya.moyenpaiement.application.metier.usecases.commun.pub.INatureQuittance.NatureQuittance;

import eai.devass.commun.appli.converter.AConverter;

@AConverter(entiteDist = "com.rmawatanya.moyenpaiement.application.metier.valueobjects.QuittanceVO")
public class QuittanceMoyenPaiement {
	
	@AConverter(propertyDist = "numQuittance")
	private String numQuittance;
	
	@AConverter(propertyDist = "numDossier")
	private String numDossier;
	
	@AConverter(propertyDist = "natureQuittance")
	private NatureQuittance natureQuittance;
	
	@AConverter(propertyDist = "codeBranche")
	private String codeBranche;
	
	@AConverter(propertyDist = "listDetailPrestationVO", customerConverter = "ConvertListDetailReglement")
	private List<DetailReglement> listDetailReglement;

	public String getNumQuittance() {
		return numQuittance;
	}

	public void setNumQuittance(String numQuittance) {
		this.numQuittance = numQuittance;
	}

	public String getNumDossier() {
		return numDossier;
	}

	public void setNumDossier(String numDossier) {
		this.numDossier = numDossier;
	}

	public NatureQuittance getNatureQuittance() {
		return natureQuittance;
	}

	public void setNatureQuittance(NatureQuittance natureQuittance) {
		this.natureQuittance = natureQuittance;
	}

	public String getCodeBranche() {
		return codeBranche;
	}

	public void setCodeBranche(String codeBranche) {
		this.codeBranche = codeBranche;
	}

	public List<DetailReglement> getListDetailReglement() {
		return listDetailReglement;
	}

	public void setListDetailReglement(List<DetailReglement> listDetailReglement) {
		this.listDetailReglement = listDetailReglement;
	}

	
	

}
