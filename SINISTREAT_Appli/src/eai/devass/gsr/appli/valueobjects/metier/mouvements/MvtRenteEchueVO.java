package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.indexer.utile.Indexation;
import ma.co.omnidata.framework.services.trace.ITracable;
import ma.co.omnidata.framework.services.trace.impl.TraceAttributesProcess;
import eai.devass.commun.appli.converter.AConverter;

/**
 * Value Object de MvtRenteEchue
 * 
 * @author Nom Prenom (email)
 */
@AConverter(entiteDist = "eai.devass.gsr.appli.modele.metier.mouvements.MvtRenteEchue")
public class MvtRenteEchueVO extends MouvementVO implements ITracable {

	@Indexation(label = "datFinRente", analyzed = false)
	@AConverter(propertyDist = "datFinRente", pattern = "yyyyMMdd")
	private String datFinRente;
	@Indexation(label = "mntProrata", analyzed = false)
	@AConverter(propertyDist = "mntProrata")
	private String mntProrata;
	@Indexation(label = "mntTropPercu", analyzed = false)
	@AConverter(propertyDist = "mntTropPercu")
	private String mntTropPercu;
	@Indexation(label = "dateMariage", analyzed = false)
	@AConverter(propertyDist = "dateMariage", pattern = "yyyyMMdd")
	private String dateMariage;
	@Indexation(label = "dateFinScolarite", analyzed = false)
	@AConverter(propertyDist = "dateFinScolarite", pattern = "yyyyMMdd")
	private String dateFinScolarite;
	@AConverter(propertyDist = "mntARegle")
	private String mntARegle;

	
	private String typeParentA;
	private String typeParentB;

	public String getDatFinRente() {
		return this.datFinRente;
	}

	public void setDatFinRente(String datFinRente) {
		this.datFinRente = datFinRente;
	}

	public String getMntProrata() {
		return this.mntProrata;
	}

	public void setMntProrata(String mntProrata) {
		this.mntProrata = mntProrata;
	}
	
	public String getMntTropPercu() {
		return mntTropPercu;
	}

	public void setMntTropPercu(String mntTropPercu) {
		this.mntTropPercu = mntTropPercu;
	}

	public String getDateMariage() {
		return dateMariage;
	}

	public void setDateMariage(String dateMariage) {
		this.dateMariage = dateMariage;
	}

	public String getDateFinScolarite() {
		return dateFinScolarite;
	}

	public void setDateFinScolarite(String dateFinScolarite) {
		this.dateFinScolarite = dateFinScolarite;
	}
	
	public String getTraceInfo() {
		TraceAttributesProcess traceAtt = new TraceAttributesProcess();
		List listAttributes = new ArrayList();
		listAttributes.add("datFinRente");
		listAttributes.add("mntProrata");
		listAttributes.add("dateMariage");
		listAttributes.add("dateFinScolarite");
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

	/**
	 * @return the mntARegle
	 */
	public String getMntARegle() {
		return mntARegle;
	}

	/**
	 * @param mntARegle the mntARegle to set
	 */
	public void setMntARegle(String mntARegle) {
		this.mntARegle = mntARegle;
	}
}
