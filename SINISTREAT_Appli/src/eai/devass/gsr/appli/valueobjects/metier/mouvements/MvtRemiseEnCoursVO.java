
package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.trace.ITracable;
import ma.co.omnidata.framework.services.trace.impl.TraceAttributesProcess;
import eai.devass.commun.appli.converter.AConverter;


/**
Value Object de MvtRemiseEnCours
@author JEFFAR Hicham (jeffarhi@eurafric-information.com)
*/
@AConverter(entiteDist = "eai.devass.gsr.appli.modele.metier.mouvements.MvtRemiseEnCours")
public class MvtRemiseEnCoursVO extends MouvementVO implements ITracable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Indexation(label="datMiseEnVigeur",analyzed=false)
	@AConverter(propertyDist = "datMiseEnVigeur")
	private String datMiseEnVigeur;
	
	@Indexation(label = "datSuspension", analyzed = false)
	@AConverter(propertyDist = "datSuspension")
	private String datSuspension;
	
	private String typeParentA;
	private String typeParentB;

	public String getDatMiseEnVigeur() {
		return this.datMiseEnVigeur;
	}

	public void setDatMiseEnVigeur(String datMiseEnVigeur) {
		this.datMiseEnVigeur = datMiseEnVigeur;
	}

	public String getDatSuspension() {
		return datSuspension;
	}

	public void setDatSuspension(String datSuspension) {
		this.datSuspension = datSuspension;
	}

	public String getTraceInfo() {
		TraceAttributesProcess traceAtt = new TraceAttributesProcess();
		List listAttributes = new ArrayList();
			listAttributes.add("datMiseEnVigeur");
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

