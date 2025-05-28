
package eai.devass.gsr.appli.valueobjects.metier.reglement;

import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;


public class QuittancePositionnementVO implements IValueObject {

	
	private static final long serialVersionUID = 1L;
	private List<QuittanceGsrVO> listQuittanceGsrVO;
	private String action;
	private String operateur;
	private String refReglement;
	private String dateReglement;
	private String etat;
	private String montantPos;
	private String beneficaire;
	private String idReglement;
	private List<QuittancePositionnementVO> listQuittancePositionnementVO;
	
	
	public List<QuittanceGsrVO> getListQuittanceGsrVO() {
		return listQuittanceGsrVO;
	}
	public void setListQuittanceGsrVO(List<QuittanceGsrVO> listQuittanceGsrVO) {
		this.listQuittanceGsrVO = listQuittanceGsrVO;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getOperateur() {
		return operateur;
	}
	public void setOperateur(String operateur) {
		this.operateur = operateur;
	}
	public String getRefReglement() {
		return refReglement;
	}
	public void setRefReglement(String refReglement) {
		this.refReglement = refReglement;
	}
	public String getDateReglement() {
		return dateReglement;
	}
	public void setDateReglement(String dateReglement) {
		this.dateReglement = dateReglement;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public String getMontantPos() {
		return montantPos;
	}
	public void setMontantPos(String montantPos) {
		this.montantPos = montantPos;
	}
	public String getBeneficaire() {
		return beneficaire;
	}
	public void setBeneficaire(String beneficaire) {
		this.beneficaire = beneficaire;
	}
	public List<QuittancePositionnementVO> getListQuittancePositionnementVO() {
		return listQuittancePositionnementVO;
	}
	public void setListQuittancePositionnementVO(
			List<QuittancePositionnementVO> listQuittancePositionnementVO) {
		this.listQuittancePositionnementVO = listQuittancePositionnementVO;
	}
	public String getIdReglement() {
		return idReglement;
	}
	public void setIdReglement(String idReglement) {
		this.idReglement = idReglement;
	}
	
	
	
	
	


}

