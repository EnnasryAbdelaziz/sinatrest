package eai.devass.sinistreat.appli.modele.metier.mission;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.modele.parametrage.PrestationMission;

public class MissionPresation implements IValueObject {
	private long id;
	private double montantPaye;
	private double montantFacture;
	private MissionAT refMission;
	private PrestationMission refPrestation;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getMontantPaye() {
		return montantPaye;
	}
	public void setMontantPaye(double montantPaye) {
		this.montantPaye = montantPaye;
	}
	public double getMontantFacture() {
		return montantFacture;
	}
	public void setMontantFacture(double montantFacture) {
		this.montantFacture = montantFacture;
	}
	public MissionAT getRefMission() {
		return refMission;
	}
	public void setRefMission(MissionAT refMission) {
		this.refMission = refMission;
	}
	public PrestationMission getRefPrestation() {
		return refPrestation;
	}
	public void setRefPrestation(PrestationMission refPrestation) {
		this.refPrestation = refPrestation;
	}

}
