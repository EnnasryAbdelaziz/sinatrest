
package eai.devass.gsr.appli.valueobjects.metier.reglement;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVO;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.trace.ITracable;
import ma.co.omnidata.framework.services.trace.impl.TraceAttributesProcess;

import org.apache.log4j.Logger;


/**
Value Object de OrderesVirements
@author Nom Prenom (email)
*/
public class OrderesVirementsVO extends   RechListeVO implements ITracable{

	private long id;
	private static final long serialVersionUID = 1L;
	private transient Logger logger = Logger.getLogger("loggerSINAT");



	@Indexation(label="Année",analyzed=false) 
	private String annee;
	@Indexation(label="Montant commission",analyzed=false) 
	private String commission;
	@Indexation(label="commissionH",analyzed=false) 
	private String commissionH;
	@Indexation(label="Date edition",analyzed=false) 
	private String dateEdition;
	@Indexation(label="edit",analyzed=false) 
	private String edit;
	@Indexation(label="Montant",analyzed=false) 
	private String montant;
	@Indexation(label="Nombre",analyzed=false) 
	private String nombre;
	@Indexation(label="Nom fichier",analyzed=false) 
	private String nomFichier;
	@Indexation(label="N° Virement",analyzed=false) 
	private String numeroVirement;
	@Indexation(label="Trimestre",analyzed=false) 
	private String trimestre;
	@Indexation(label="Type rente",analyzed=false) 
	private String typeRente;
	@Indexation(label="Type virement",analyzed=false) 
	private String typeVirement;
	@Indexation(label="dateCreation",analyzed=false) 
	private String dateCreation;
	private String typeParentA;
	private String typeParentB;



	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getAnnee() {
		return this.annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}
	public String getCommission() {
		return this.commission;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}
	public String getCommissionH() {
		return this.commissionH;
	}

	public void setCommissionH(String commissionH) {
		this.commissionH = commissionH;
	}
	public String getDateEdition() {
		return this.dateEdition;
	}

	public void setDateEdition(String dateEdition) {
		this.dateEdition = dateEdition;
	}
	public String getEdit() {
		return this.edit;
	}

	public void setEdit(String edit) {
		this.edit = edit;
	}
	public String getMontant() {
		return this.montant;
	}

	public void setMontant(String montant) {
		this.montant = montant;
	}
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNomFichier() {
		return this.nomFichier;
	}

	public void setNomFichier(String nomFichier) {
		this.nomFichier = nomFichier;
	}
	public String getNumeroVirement() {
		return this.numeroVirement;
	}

	public void setNumeroVirement(String numeroVirement) {
		this.numeroVirement = numeroVirement;
	}
	public String getTrimestre() {
		return this.trimestre;
	}

	public void setTrimestre(String trimestre) {
		this.trimestre = trimestre;
	}
	public String getTypeRente() {
		return this.typeRente;
	}

	public void setTypeRente(String typeRente) {
		this.typeRente = typeRente;
	}
	public String getTypeVirement() {
		return this.typeVirement;
	}

	public void setTypeVirement(String typeVirement) {
		this.typeVirement = typeVirement;
	}
	public String getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}
	public String getTraceInfo() {
		TraceAttributesProcess traceAtt = new TraceAttributesProcess();
		List listAttributes = new ArrayList();
			listAttributes.add("annee");
			listAttributes.add("commission");
			listAttributes.add("commissionH");
			listAttributes.add("dateEdition");
			listAttributes.add("edit");
			listAttributes.add("montant");
			listAttributes.add("nombre");
			listAttributes.add("nomFichier");
			listAttributes.add("numeroVirement");
			listAttributes.add("trimestre");
			listAttributes.add("typeRente");
			listAttributes.add("typeVirement");
			listAttributes.add("dateCreation");
		String rslt = "";
		try {
			rslt = traceAtt.getStringTraceInfo(this,listAttributes);
		} catch (Exception e) {
			logger.error("probl�me technique",e);
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

