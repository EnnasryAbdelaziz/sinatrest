package eai.devass.sinistreat.appli.valueobjects.metier.conciliation;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.OrigineConciliationVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.VilleVO;

@SuppressWarnings("all")
public class ConciliationVO implements IValueObject {

	private long id;
	private OrigineConciliationVO refOrigineConciliation;
	private String dateCreation;
	private String etat;
	private String dateEtat;
	private SinistreVO refSinistre;
	private String userCreateur;
	private List<PiecesVO> listPieces = new ArrayList<PiecesVO>(0);
	private List<ConvocationVO> listConvocation;
	private List<OffreVO> listOffre;
	private OffreVO offre;
	private String changementEtat;
	private String createurOffre;
	private String nomAvocat;
	private VilleVO refVilleAvocat;
	private String adresseAvocat;
	private List<RelanceConciliationVO> listRelanceConciliation;
	private String typeRelance;
	private String userModificateur;

	public List<PiecesVO> getListPieces() {
		return listPieces;
	}

	public void setListPieces(List<PiecesVO> listPieces) {
		this.listPieces = listPieces;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public OrigineConciliationVO getRefOrigineConciliation() {
		return refOrigineConciliation;
	}

	public void setRefOrigineConciliation(
			OrigineConciliationVO refOrigineConciliation) {
		this.refOrigineConciliation = refOrigineConciliation;
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public SinistreVO getRefSinistre() {
		return refSinistre;
	}

	public void setRefSinistre(SinistreVO refSinistre) {
		this.refSinistre = refSinistre;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getDateEtat() {
		return dateEtat;
	}

	public void setDateEtat(String dateEtat) {
		this.dateEtat = dateEtat;
	}

	public String getUserCreateur() {
		return userCreateur;
	}

	public void setUserCreateur(String userCreateur) {
		this.userCreateur = userCreateur;
	}

	public List<ConvocationVO> getListConvocation() {
		return listConvocation;
	}

	public void setListConvocation(List<ConvocationVO> listConvocation) {
		this.listConvocation = listConvocation;
	}

	public List<OffreVO> getListOffre() {
		return listOffre;
	}

	public void setListOffre(List<OffreVO> listOffre) {
		this.listOffre = listOffre;
	}

	public OffreVO getOffre() {
		return offre;
	}

	public void setOffre(OffreVO offre) {
		this.offre = offre;
	}

	public String getChangementEtat() {
		return changementEtat;
	}

	public void setChangementEtat(String changementEtat) {
		this.changementEtat = changementEtat;
	}

	public String getCreateurOffre() {
		return createurOffre;
	}

	public void setCreateurOffre(String createurOffre) {
		this.createurOffre = createurOffre;
	}

	public String getNomAvocat() {
		return nomAvocat;
	}

	public void setNomAvocat(String nomAvocat) {
		this.nomAvocat = nomAvocat;
	}

	public String getAdresseAvocat() {
		return adresseAvocat;
	}

	public void setAdresseAvocat(String adresseAvocat) {
		this.adresseAvocat = adresseAvocat;
	}

	public VilleVO getRefVilleAvocat() {
		return refVilleAvocat;
	}

	public void setRefVilleAvocat(VilleVO refVilleAvocat) {
		this.refVilleAvocat = refVilleAvocat;
	}

	public List<RelanceConciliationVO> getListRelanceConciliation() {
		return listRelanceConciliation;
	}

	public void setListRelanceConciliation(
			List<RelanceConciliationVO> listRelanceConciliation) {
		this.listRelanceConciliation = listRelanceConciliation;
	}

	public String getTypeRelance() {
		return typeRelance;
	}

	public void setTypeRelance(String typeRelance) {
		this.typeRelance = typeRelance;
	}

	public String getUserModificateur() {
		return userModificateur;
	}

	public void setUserModificateur(String userModificateur) {
		this.userModificateur = userModificateur;
	}
	
}
