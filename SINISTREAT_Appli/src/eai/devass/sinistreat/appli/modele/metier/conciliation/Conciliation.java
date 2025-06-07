package eai.devass.sinistreat.appli.modele.metier.conciliation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Session;

import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.parametrage.LiaisonTypePiece;
import eai.devass.sinistreat.appli.modele.parametrage.OrigineConciliation;
import eai.devass.sinistreat.appli.modele.parametrage.TypePieceConciliation;
import eai.devass.sinistreat.appli.modele.parametrage.Ville;

@SuppressWarnings("all")
public class Conciliation implements Serializable {

	private long id;
	private OrigineConciliation refOrigineConciliation;
	private Date dateCreation;
	private String etat;
	private Date dateEtat;
	private Sinistre refSinistre;
	private String userCreateur;
	private List<Pieces> listPieces = new ArrayList<Pieces>(0);
	private List<Convocation> listConvocation = new ArrayList<Convocation>(0);
	private List<Offre> listOffre = new ArrayList<Offre>(0);
	private Date dateModification;
	private transient Offre offre; 
	private transient Boolean changementEtat;
	private transient String createurOffre;
	private String nomAvocat;
	private Ville refVilleAvocat;
	private String adresseAvocat;
	private List<RelanceConciliation> listRelanceConciliation = new ArrayList<RelanceConciliation>(0);
	private String typeRelance;
	private String userModificateur;
	
	// Pour la convertion (VO BO)
	private transient String[] propertiesToConvert;

	public List<Pieces> getListPieces() {
		return listPieces;
	}

	public void setListPieces(List<Pieces> listPieces) {
		this.listPieces = listPieces;
	}

	public Conciliation() {

	}

	public Conciliation(Sinistre sinistreParent, Session session) {
		super();
		if (sinistreParent.getRefVictime() != null
				&& sinistreParent.getRefVictime().getDeces() != null) {
			this.setRefSinistre(sinistreParent);
			TypePieceConciliation typePiece = null;
			if (!sinistreParent.getRefVictime().getDeces()) {
				typePiece = (TypePieceConciliation) session.get(
						TypePieceConciliation.class,
						TypePieceConciliation.TypePieceConciliationId.VICTIME
								.getId());
			} else {
				typePiece = (TypePieceConciliation) session.get(
						TypePieceConciliation.class,
						TypePieceConciliation.TypePieceConciliationId.DECES
								.getId());
			}
			if (typePiece != null) {
				if (typePiece.getListTypePiece() != null
						&& typePiece.getListTypePiece().size() > 0) {
					for (LiaisonTypePiece p : typePiece.getListTypePiece()) {
						Pieces pieceConciliation = new Pieces();
						pieceConciliation.setRefPiece(p.getRefPiece());
						pieceConciliation.setDateCreation(new Date());
						pieceConciliation.setRecu(false);
						this.listPieces.add(pieceConciliation);
					}
				}
			}
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public OrigineConciliation getRefOrigineConciliation() {
		return refOrigineConciliation;
	}

	public void setRefOrigineConciliation(OrigineConciliation refOrigineConciliation) {
		this.refOrigineConciliation = refOrigineConciliation;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Sinistre getRefSinistre() {
		return refSinistre;
	}

	public void setRefSinistre(Sinistre refSinistre) {
		this.refSinistre = refSinistre;

	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Date getDateEtat() {
		return dateEtat;
	}

	public void setDateEtat(Date dateEtat) {
		this.dateEtat = dateEtat;
	}

	public String getUserCreateur() {
		return userCreateur;
	}

	public void setUserCreateur(String userCreateur) {
		this.userCreateur = userCreateur;
	}

	public List<Convocation> getListConvocation() {
		return listConvocation;
	}

	public void setListConvocation(List<Convocation> listConvocation) {
		this.listConvocation = listConvocation;
	}

	public List<Offre> getListOffre() {
		return listOffre;
	}

	public void setListOffre(List<Offre> listOffre) {
		this.listOffre = listOffre;
	}
	public String[] getPropertiesToConvert() {
		return propertiesToConvert;
	}
	public Offre getOffre() {
		if (getListOffre() == null || getListOffre().isEmpty()) {
			return this.offre;
		} else if (this.offre != null) {
			return this.offre;
		} else {
			return (Offre) CollectionUtils.get(getListOffre(),getListOffre().size() - 1);
		}
	}

	public void setOffre(Offre offre) {
		this.offre = offre;
	}

	public void setPropertiesToConvert(String[] propertiesToConvert) {

		String[] copyPropertiesToConvert = null;
		if (propertiesToConvert != null) {
			copyPropertiesToConvert = propertiesToConvert.clone();
		}

		this.propertiesToConvert = copyPropertiesToConvert;
	}

	public Date getDateModification() {
		return dateModification;
	}

	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}

	public Boolean getChangementEtat() {
		return changementEtat;
	}

	public void setChangementEtat(Boolean changementEtat) {
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


	public Ville getRefVilleAvocat() {
		return refVilleAvocat;
	}

	public void setRefVilleAvocat(Ville refVilleAvocat) {
		this.refVilleAvocat = refVilleAvocat;
	}

	public String getAdresseAvocat() {
		return adresseAvocat;
	}

	public void setAdresseAvocat(String adresseAvocat) {
		this.adresseAvocat = adresseAvocat;
	}

	public List<RelanceConciliation> getListRelanceConciliation() {
		return listRelanceConciliation;
	}

	public void setListRelanceConciliation(
			List<RelanceConciliation> listRelanceConciliation) {
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
