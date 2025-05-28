package eai.devass.sinistreat.appli.valueobjects.metier;

import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.valueobjects.metier.bnej.DossierBnejVO;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.ConciliationVO;
import eai.devass.sinistreat.appli.valueobjects.metier.fichemedicale.CertificatMedicalVO;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.OrdonnancementVO;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.ReglementVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.VictimeVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.CourrierHybrideVO;

public class ListVO implements IValueObject {

	public List<VictimeVO> getListVictime() {
		return listVictime;
	}

	public void setListVictime(List<VictimeVO> listVictime) {
		this.listVictime = listVictime;
	}

	public List<SinistreVO> getListSinistre() {
		return listSinistre;
	}

	public void setListSinistre(List<SinistreVO> listSinistre) {
		this.listSinistre = listSinistre;
	}

	public List<ReglementVO> getListReglement() {
		return listReglement;
	}

	public void setListReglement(List<ReglementVO> listReglement) {
		this.listReglement = listReglement;
	}

	public void setListOrdonnancement(List<OrdonnancementVO> listOrdonnancement) {
		this.listOrdonnancement = listOrdonnancement;
	}

	public List<OrdonnancementVO> getListOrdonnancement() {
		return listOrdonnancement;
	}

	private List<VictimeVO> listVictime;
	private List<SinistreVO> listSinistre;
	private List<ReglementVO> listReglement;

	private List<OrdonnancementVO> listOrdonnancement;
	private List<CertificatMedicalVO> listCertificatMedical;
	private List<ConciliationVO> listConciliation;
	private List<DossierBnejVO> listDossierBnej;
	private String codeSas;
	private String codeUser;
	private List<CourrierHybrideVO> listCourrierHybrideVO;

	public List<CourrierHybrideVO> getListCourrierHybrideVO() {
		return listCourrierHybrideVO;
	}

	public void setListCourrierHybrideVO(List<CourrierHybrideVO> listCourrierHybrideVO) {
		this.listCourrierHybrideVO = listCourrierHybrideVO;
	}
	public String getCodeSas() {
		return codeSas;
	}

	public void setCodeSas(String codeSas) {
		this.codeSas = codeSas;
	}

	public String getCodeUser() {
		return codeUser;
	}

	public void setCodeUser(String codeUser) {
		this.codeUser = codeUser;
	}

	public void setListCertificatMedical(
			List<CertificatMedicalVO> listCertificatMedical) {
		this.listCertificatMedical = listCertificatMedical;
	}

	public List<CertificatMedicalVO> getListCertificatMedical() {
		return listCertificatMedical;
	}

	public void setListConciliation(List<ConciliationVO> listConciliation) {
		this.listConciliation = listConciliation;
	}

	public List<ConciliationVO> getListConciliation() {
		return listConciliation;
	}

	public List<DossierBnejVO> getListDossierBnej() {
		return listDossierBnej;
	}

	public void setListDossierBnej(List<DossierBnejVO> listDossierBnej) {
		this.listDossierBnej = listDossierBnej;
	}
}