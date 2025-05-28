package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.trace.ITracable;
import ma.co.omnidata.framework.services.trace.impl.TraceAttributesProcess;
import eai.devass.commun.appli.converter.AConverter;

/**
 * Value Object de MvtMajCapital
 * 
 * @author Nom Prenom (email)
 */

@AConverter(entiteDist = "eai.devass.gsr.appli.modele.metier.mouvements.MvtMajCapital")
public class MvtMajCapitalVO extends MouvementVO implements ITracable {

	// @Indexation(label = "datSuspension", analyzed = false)
	// @AConverter(propertyDist = "datSuspension", pattern = "yyyyMMdd")
	// private String datSuspension;
	// @Indexation(label = "datRemise", analyzed = false)
	// @AConverter(propertyDist = "datRemise", pattern = "yyyyMMdd")
	// private String datRemise;
	@Indexation(label = "nouvTaux", analyzed = false)
	@AConverter(propertyDist = "nouvTaux")
	private String nouvTaux;
	@Indexation(label = "arreragesDus", analyzed = false)
	@AConverter(propertyDist = "arreragesDus")
	private String arreragesDus;
	@Indexation(label = "arreragesPercus", analyzed = false)
	@AConverter(propertyDist = "arreragesPercus")
	private String arreragesPercus;
	@Indexation(label = "nouvSalaire", analyzed = false)
	@AConverter(propertyDist = "nouvSalaire")
	private String nouvSalaire;
	
	// @Indexation(label = "datDebutArrerage", analyzed = false)
	// @AConverter(propertyDist = "datDebutArrerage", pattern = "yyyyMMdd")
	// private String datDebutArrerage;
	// @Indexation(label = "datFinArrerage", analyzed = false)
	// @AConverter(propertyDist = "datFinArrerage", pattern = "yyyyMMdd")
	// private String datFinArrerage;
	// @Indexation(label = "mntProrata", analyzed = false)
	// @AConverter(propertyDist = "mntProrata")
	// private String mntProrata;
	@Indexation(label = "motif", analyzed = false)
	@AConverter(propertyDist = "motif")
	private String motif;
	@Indexation(label = "mntTropPercu", analyzed = false)
	@AConverter(propertyDist = "mntTropPercu")
	private String mntTropPercu;
	@Indexation(label = "mntDiff", analyzed = false)
	@AConverter(propertyDist = "mntDiff")
	private String mntDiff;
	@Indexation(label = "nouvCapitalConstitutif", analyzed = false)
	@AConverter(propertyDist = "nouvCapitalConstitutif")
	private String nouvCapitalConstitutif;
	@Indexation(label = "nouvDatNaissance", analyzed = false)
	@AConverter(propertyDist = "nouvDatNaissance", pattern = "yyyyMMdd")
	private String nouvDatNaissance;
	@AConverter(propertyDist = "nouvDateDepartRente", pattern = "yyyyMMdd")
	private String nouvDateDepartRente;
	// @Indexation(label="dateCreation",analyzed=false)
	// private String dateCreation;

	@Indexation(label = "TypeMajCapital", analyzed = false)
	@AConverter(propertyDist = "refTypeMajCapital.id")
	private String refTypeMajCapital;
	@Indexation(label = "TypeMajCapital", analyzed = false)
	@AConverter(propertyDist = "refTypeMajCapital.libelle")
	private String refTypeMajCapitalLabel;
	private String typeParentA;
	private String typeParentB;
	
	@AConverter(propertyDist = "renteJugement")
	private String renteJugement;
	@AConverter(propertyDist = "complementCC")
	private String complementCC;

	// public String getDatSuspension() {
	// return this.datSuspension;
	// }
	//
	// public void setDatSuspension(String datSuspension) {
	// this.datSuspension = datSuspension;
	// }
	//
	// public String getDatRemise() {
	// return this.datRemise;
	// }
	//
	// public void setDatRemise(String datRemise) {
	// this.datRemise = datRemise;
	// }

	public void setNouvTaux(String nouvTaux) {
		this.nouvTaux = nouvTaux;
	}

	public String getNouvTaux() {
		return nouvTaux;
	}

	public String getArreragesDus() {
		return this.arreragesDus;
	}

	public void setArreragesDus(String arreragesDus) {
		this.arreragesDus = arreragesDus;
	}

	public String getArreragesPercus() {
		return this.arreragesPercus;
	}

	public void setArreragesPercus(String arreragesPercus) {
		this.arreragesPercus = arreragesPercus;
	}

	
	public String getNouvSalaire() {
		return this.nouvSalaire;
	}

	public void setNouvSalaire(String nouvSalaire) {
		this.nouvSalaire = nouvSalaire;
	}

	// public String getDatDebutArrerage() {
	// return this.datDebutArrerage;
	// }
	//
	// public void setDatDebutArrerage(String datDebutArrerage) {
	// this.datDebutArrerage = datDebutArrerage;
	// }
	//
	// public String getDatFinArrerage() {
	// return this.datFinArrerage;
	// }
	//
	// public void setDatFinArrerage(String datFinArrerage) {
	// this.datFinArrerage = datFinArrerage;
	// }

	// public String getMntProrata() {
	// return this.mntProrata;
	// }
	//
	// public void setMntProrata(String mntProrata) {
	// this.mntProrata = mntProrata;
	// }

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public String getMotif() {
		return motif;
	}

	public void setMntTropPercu(String mntTropPercu) {
		this.mntTropPercu = mntTropPercu;
	}

	public String getMntTropPercu() {
		return mntTropPercu;
	}

	public String getMntDiff() {
		return this.mntDiff;
	}

	public void setMntDiff(String mntDiff) {
		this.mntDiff = mntDiff;
	}

	public String getNouvCapitalConstitutif() {
		return this.nouvCapitalConstitutif;
	}

	public void setNouvCapitalConstitutif(String nouvCapitalConstitutif) {
		this.nouvCapitalConstitutif = nouvCapitalConstitutif;
	}

	public String getNouvDatNaissance() {
		return this.nouvDatNaissance;
	}

	public void setNouvDatNaissance(String nouvDatNaissance) {
		this.nouvDatNaissance = nouvDatNaissance;
	}

	public void setNouvDateDepartRente(String nouvDateDepartRente) {
		this.nouvDateDepartRente = nouvDateDepartRente;
	}

	public String getNouvDateDepartRente() {
		return nouvDateDepartRente;
	}

	// public String getDateCreation() {
	// return this.dateCreation;
	// }
	//
	// public void setDateCreation(String dateCreation) {
	// this.dateCreation = dateCreation;
	// }
	public String getRefTypeMajCapital() {
		return this.refTypeMajCapital;
	}

	public void setRefTypeMajCapital(String refTypeMajCapital) {
		this.refTypeMajCapital = refTypeMajCapital;
	}

	public String getRefTypeMajCapitalLabel() {
		return this.refTypeMajCapitalLabel;
	}

	public void setRefTypeMajCapitalLabel(String refTypeMajCapitalLabel) {
		this.refTypeMajCapitalLabel = refTypeMajCapitalLabel;
	}

	public String getTraceInfo() {
		TraceAttributesProcess traceAtt = new TraceAttributesProcess();
		List listAttributes = new ArrayList();
		listAttributes.add("nouvTaux");
		listAttributes.add("datSuspension");
		listAttributes.add("datRemise");
		listAttributes.add("arreragesDus");
		listAttributes.add("arreragesPercus");
		//listAttributes.add("nouvMntRente");
		listAttributes.add("nouvSalaire");
		//listAttributes.add("nouvIPP");
		listAttributes.add("datDebutArrerage");
		listAttributes.add("datFinArrerage");
		listAttributes.add("mntProrata");
		listAttributes.add("mntDiff");
		listAttributes.add("nouvCapitalConstitutif");
		listAttributes.add("nouvDatNaissance");
		listAttributes.add("dateCreation");
		String rslt = "";
		try {
			rslt = traceAtt.getStringTraceInfo(this, listAttributes);
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		return rslt;
	}

	public String getTypeParentA() {
		return typeParentA;
	}

	public void setTypeParentA(String typeParentA) {
		this.typeParentA = typeParentA;
		this.typeParentB = typeParentA;
	}

	public String getTypeParentB() {
		return typeParentB;
	}

	public void setTypeParentB(String typeParentB) {
		this.typeParentB = typeParentB;
	}

	public void setRenteJugement(String renteJugement) {
		this.renteJugement = renteJugement;
	}

	public String getRenteJugement() {
		return renteJugement;
	}

	public void setComplementCC(String complementCC) {
		this.complementCC = complementCC;
	}

	public String getComplementCC() {
		return complementCC;
	}

}
