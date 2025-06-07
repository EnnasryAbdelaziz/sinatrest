package eai.devass.sinistreat.appli.valueobjects.metier.mission;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.valueobjects.parametrage.PrestationMissionVO;

public class MissionPresationVO implements IValueObject {
	private String id;
	private String montantPaye;
	private String montantFacture;
	private MissionATVO refMission;
	private PrestationMissionVO refPrestation;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMontantPaye() {
		return montantPaye;
	}
	public void setMontantPaye(String montantPaye) {
		this.montantPaye = montantPaye;
	}
	public String getMontantFacture() {
		return montantFacture;
	}
	public void setMontantFacture(String montantFacture) {
		this.montantFacture = montantFacture;
	}
	public MissionATVO getRefMission() {
		return refMission;
	}
	public void setRefMission(MissionATVO refMission) {
		this.refMission = refMission;
	}
	public PrestationMissionVO getRefPrestation() {
		return refPrestation;
	}
	public void setRefPrestation(PrestationMissionVO refPrestation) {
		this.refPrestation = refPrestation;
	}

}
