package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.impl.RechListeVO;
import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.trace.ITracable;
import ma.co.omnidata.framework.services.trace.impl.TraceAttributesProcess;

import org.apache.log4j.Logger;


/**
Value Object de ConsomReserveRente
@author Nom Prenom (email)
*/
public class ConsomReserveRenteVO extends   RechListeVO implements ITracable{

	private long id;
	private static final long serialVersionUID = 1L;

	private Logger logger = Logger.getLogger("loggerSINAT");


	@Indexation(label="numeroQuittance",analyzed=false) 
	private String numeroQuittance;
	@Indexation(label="reserveApres",analyzed=false) 
	private String reserveApres;
	@Indexation(label="reserveAvant",analyzed=false) 
	private String reserveAvant;
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
	public String getNumeroQuittance() {
		return this.numeroQuittance;
	}

	public void setNumeroQuittance(String numeroQuittance) {
		this.numeroQuittance = numeroQuittance;
	}
	public String getReserveApres() {
		return this.reserveApres;
	}

	public void setReserveApres(String reserveApres) {
		this.reserveApres = reserveApres;
	}
	public String getReserveAvant() {
		return this.reserveAvant;
	}

	public void setReserveAvant(String reserveAvant) {
		this.reserveAvant = reserveAvant;
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
			listAttributes.add("numeroQuittance");
			listAttributes.add("reserveApres");
			listAttributes.add("reserveAvant");
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

