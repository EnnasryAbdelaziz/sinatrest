
package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.trace.ITracable;
import ma.co.omnidata.framework.services.trace.impl.TraceAttributesProcess;


/**
Value Object de MvtRecupeartion
@author Nom Prenom (email)
*/
public class MvtRecuperationVO extends  MouvementVO  implements ITracable{





	@Indexation(label="pourcentage",analyzed=false) 
	private String pourcentage;
	@Indexation(label="datDebut",analyzed=false) 
	private String datDebut;
	@Indexation(label="datFin",analyzed=false) 
	private String datFin;
	@Indexation(label="datRemiseBancaire",analyzed=false) 
	private String datRemiseBancaire;
	@Indexation(label="mntPreleveRente",analyzed=false) 
	private String mntPreleveRente;
	@Indexation(label="mntProrataRecuperation",analyzed=false) 
	private String mntProrataRecuperation;
	@Indexation(label="mntRecupere",analyzed=false) 
	private String mntRecupere;
	@Indexation(label="mntReliquat",analyzed=false) 
	private String mntReliquat;
	@Indexation(label="mntTropPercu",analyzed=false) 
	private String mntTropPercu;
	@Indexation(label="numRemiseBancaire",analyzed=false) 
	private String numRemiseBancaire;
//	@Indexation(label="dateCreation",analyzed=false) 
//	private String dateCreation;
	
	@Indexation(label="TypeAction",analyzed=false)  
	private String refTypeAction ;
	private String refTypeActionLabel ;
	
	@Indexation(label="TypeRecuperation",analyzed=false)  
	private String refTypeRecuperation ;
	private String refTypeRecuperationLabel ;
	private String typeParentA;
	private String typeParentB;



	public String getPourcentage() {
		return this.pourcentage;
	}

	public void setPourcentage(String pourcentage) {
		this.pourcentage = pourcentage;
	}
	public String getDatDebut() {
		return this.datDebut;
	}

	public void setDatDebut(String datDebut) {
		this.datDebut = datDebut;
	}
	public String getDatFin() {
		return this.datFin;
	}

	public void setDatFin(String datFin) {
		this.datFin = datFin;
	}
	public String getDatRemiseBancaire() {
		return this.datRemiseBancaire;
	}

	public void setDatRemiseBancaire(String datRemiseBancaire) {
		this.datRemiseBancaire = datRemiseBancaire;
	}
	public String getMntPreleveRente() {
		return this.mntPreleveRente;
	}

	public void setMntPreleveRente(String mntPreleveRente) {
		this.mntPreleveRente = mntPreleveRente;
	}
	public String getMntProrataRecuperation() {
		return this.mntProrataRecuperation;
	}

	public void setMntProrataRecuperation(String mntProrataRecuperation) {
		this.mntProrataRecuperation = mntProrataRecuperation;
	}
	public String getMntRecupere() {
		return this.mntRecupere;
	}

	public void setMntRecupere(String mntRecupere) {
		this.mntRecupere = mntRecupere;
	}
	public String getMntReliquat() {
		return this.mntReliquat;
	}

	public void setMntReliquat(String mntReliquat) {
		this.mntReliquat = mntReliquat;
	}
	public String getMntTropPercu() {
		return this.mntTropPercu;
	}

	public void setMntTropPercu(String mntTropPercu) {
		this.mntTropPercu = mntTropPercu;
	}
	public String getNumRemiseBancaire() {
		return this.numRemiseBancaire;
	}

	public void setNumRemiseBancaire(String numRemiseBancaire) {
		this.numRemiseBancaire = numRemiseBancaire;
	}
	
//	public String getDateCreation() {
//		return this.dateCreation;
//	}
//
//	public void setDateCreation(String dateCreation) {
//		this.dateCreation = dateCreation;
//	}

	public String getRefTypeAction() {
		return this.refTypeAction;
	}

	public void setRefTypeAction(String refTypeAction) {
		this.refTypeAction = refTypeAction;
	}
	public String getRefTypeActionLabel() {
		return this.refTypeActionLabel;
	}

	public void setRefTypeActionLabel(String refTypeActionLabel) {
		this.refTypeActionLabel = refTypeActionLabel;
	}	
	public String getRefTypeRecuperation() {
		return this.refTypeRecuperation;
	}

	public void setRefTypeRecuperation(String refTypeRecuperation) {
		this.refTypeRecuperation = refTypeRecuperation;
	}
	public String getRefTypeRecuperationLabel() {
		return this.refTypeRecuperationLabel;
	}

	public void setRefTypeRecuperationLabel(String refTypeRecuperationLabel) {
		this.refTypeRecuperationLabel = refTypeRecuperationLabel;
	}	
	public String getTraceInfo() {
		TraceAttributesProcess traceAtt = new TraceAttributesProcess();
		List listAttributes = new ArrayList();
			listAttributes.add("pourcentage");
			listAttributes.add("datDebut");
			listAttributes.add("datFin");
			listAttributes.add("datRemiseBancaire");
			listAttributes.add("mntPreleveRente");
			listAttributes.add("mntProrataRecuperation");
			listAttributes.add("mntRecupere");
			listAttributes.add("mntReliquat");
			listAttributes.add("mntTropPercu");
			//listAttributes.add("nouvMntRente");
			listAttributes.add("numRemiseBancaire");
			listAttributes.add("dateCreation");
		String rslt = "";
		try {
			rslt = traceAtt.getStringTraceInfo(this,listAttributes);
		} catch (Exception e) {
			logger.error("problème technique",e);
		}	
		return rslt;
	}

	public String getTypeParentA(){
		return typeParentA; 
	}
	
	public void setTypeParentA (String typeParentA){
		this.typeParentA = typeParentA;
		this.typeParentB = typeParentA;
	}

	public String getTypeParentB(){
		return typeParentB; 
	}
	
	public void setTypeParentB (String typeParentB){
		this.typeParentB = typeParentB;
	}


	
	


}

