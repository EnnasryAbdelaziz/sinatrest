package eai.devass.sinistreat.appli.manager.contentieux.converters;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.apache.commons.lang.StringUtils;

import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.ProcedureJudiciaireVO;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.RecoursVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.NatureDossierVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.TribunalVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.TypeJuridictionVO;

/**
 * Classe qui permet la convertion entre les objets procedureJudiciareVO des
 * projets sinistre et recours entrant
 * 
 * @author elhacham
 * 
 */
public class ProcedureJudiciaireVOConverter implements IVOConverter {

	/**
	 * Permet de convertir une procedureJudiciareVO de type sinistre au type
	 * recoursEntrant
	 * 
	 * @param procedureJudiciaireVO
	 * @return eai.devass.recoursentrant.application.valueobjects.
	 *         metier.ProcedureJudiciaireVO
	 */
	public IValueObject sinistreToRecoursEntrant(IValueObject vo) {
		eai.devass.recoursentrant.application.valueobjects.metier.ProcedureJudiciaireVO procedureJudiciareRecours = new eai.devass.recoursentrant.application.valueobjects.metier.ProcedureJudiciaireVO();
		ProcedureJudiciaireVO procedureJudiciaireVO = (ProcedureJudiciaireVO) vo;
		procedureJudiciareRecours.setActionCivile(procedureJudiciaireVO
				.getActionCivile());
		procedureJudiciareRecours.setActionPublique(procedureJudiciaireVO
				.getActionPublique());
		procedureJudiciareRecours.setCodeAvocatConseil(procedureJudiciaireVO
				.getCodeAvocatConseil());
		procedureJudiciareRecours
				.setCompetenceTerritorial(procedureJudiciaireVO
						.getCompetenceTerritorial());
		procedureJudiciareRecours.setCompteRenduAvocat(procedureJudiciaireVO
				.getCompteRenduAvocat());
		procedureJudiciareRecours.setDateAcceptRglAmiable(procedureJudiciaireVO
				.getDateAcceptRglAmiable());
		procedureJudiciareRecours.setDateCreation(procedureJudiciaireVO
				.getDateCreation());
		procedureJudiciareRecours.setDateCreationDebut(procedureJudiciaireVO
				.getDateCreationDebut());
		procedureJudiciareRecours.setDateCreationFin(procedureJudiciaireVO
				.getDateCreationFin());
		procedureJudiciareRecours.setDateDepot(procedureJudiciaireVO
				.getDateDepot());
		procedureJudiciareRecours.setDateReception(procedureJudiciaireVO
				.getDateReception());
		procedureJudiciareRecours.setDateReceptionCopie(procedureJudiciaireVO
				.getDateReceptionCopie());
		procedureJudiciareRecours.setDecisionAdverse(procedureJudiciaireVO
				.getDecisionAdverse());
		procedureJudiciareRecours.setDecisionCompagnie(procedureJudiciaireVO
				.getDecisionCompagnie());
		procedureJudiciareRecours.setEtatProcJud(procedureJudiciaireVO
				.getEtatProcJud());
		procedureJudiciareRecours.setId(procedureJudiciaireVO.getIdProcedure());
		if (procedureJudiciaireVO.getRefRecours() != null) {
			procedureJudiciareRecours.setIdRecours(procedureJudiciaireVO
					.getRefRecours().getIdRecours());
		}
		if (procedureJudiciaireVO.getRefJuridiction() != null) {
			procedureJudiciareRecours.setJuridiction(procedureJudiciaireVO
					.getRefJuridiction().getId());
			procedureJudiciareRecours
					.setLibelleJuridiction(procedureJudiciaireVO
							.getRefJuridiction().getLibelle());
		}
		if (procedureJudiciaireVO.getRefTypeJuridiction() != null) {
			procedureJudiciareRecours.setTypeJuridiction(procedureJudiciaireVO
					.getRefTypeJuridiction().getCode());
			procedureJudiciareRecours
					.setLibelleTypeJuridiction(procedureJudiciaireVO
							.getRefTypeJuridiction().getLibelle());
		}
		if (procedureJudiciaireVO.getRefNatureDossier() != null) {
			procedureJudiciareRecours.setNatureDossier(procedureJudiciaireVO
					.getRefNatureDossier().getCode());
			procedureJudiciareRecours
					.setLibelleNatureDossier(procedureJudiciaireVO
							.getRefNatureDossier().getLibelle());
		}
		procedureJudiciareRecours.setLettreAgent(procedureJudiciaireVO
				.getLettreAgent());
		procedureJudiciareRecours.setLettreInstruction(procedureJudiciaireVO
				.getLettreInstruction());
		procedureJudiciareRecours.setLienCausalite(procedureJudiciaireVO
				.getLienCausalite());
		procedureJudiciareRecours
				.setMontantActionPublique(procedureJudiciaireVO
						.getMontantActionPublique());
		procedureJudiciareRecours.setMotifDecisionCie(procedureJudiciaireVO
				.getMotifDecisionCie());
		procedureJudiciareRecours.setNomAvocatAdverse(procedureJudiciaireVO
				.getNomAvocatAdverse());
		procedureJudiciareRecours
				.setNumeroDossierTribunal(procedureJudiciaireVO
						.getNumeroDossierTribunal());
		procedureJudiciareRecours.setPrescription(procedureJudiciaireVO
				.getPrescription());
		procedureJudiciareRecours.setReglementAmiable(procedureJudiciaireVO
				.getReglementAmiable());
		procedureJudiciareRecours.setRequeteAdversaire(procedureJudiciaireVO
				.getRequeteAdversaire());
		procedureJudiciareRecours.setRequeteAvocat(procedureJudiciaireVO
				.getRequeteAvocat());
		procedureJudiciareRecours.setResultatJugement(procedureJudiciaireVO
				.getResultatJugement());
		procedureJudiciareRecours.setStatutRglAmiable(procedureJudiciaireVO
				.getStatutRglAmiable());
		if (procedureJudiciaireVO.getListePartieAdverses() != null
				&& !procedureJudiciaireVO.getListePartieAdverses().isEmpty()) {
			procedureJudiciareRecours
					.setListeParties(new PartieAdverseJudVOConverter()
							.sinistreListToRecoursEntrantList(procedureJudiciaireVO
									.getListePartieAdverses()));
		}
		return procedureJudiciareRecours;
	}

	/**
	 * Permet de convertir une procedureJudiciareVO de type recoursEntrant au
	 * type sinistre
	 * 
	 * @param procedureJudiciaireVO
	 * @return ProcedureJudiciaireVO
	 */
	public IValueObject recoursEntrantToSinistre(IValueObject vo) {
		ProcedureJudiciaireVO procedureJudiciareVO = new ProcedureJudiciaireVO();
		eai.devass.recoursentrant.application.valueobjects.metier.ProcedureJudiciaireVO procedureJudiciareRecours = (eai.devass.recoursentrant.application.valueobjects.metier.ProcedureJudiciaireVO) vo;
		procedureJudiciareVO.setActionCivile(procedureJudiciareRecours
				.getActionCivile());
		procedureJudiciareVO.setActionPublique(procedureJudiciareRecours
				.getActionPublique());
		procedureJudiciareVO.setCodeAvocatConseil(procedureJudiciareRecours
				.getCodeAvocatConseil());
		procedureJudiciareVO.setCompetenceTerritorial(procedureJudiciareRecours
				.getCompetenceTerritorial());
		procedureJudiciareVO.setCompteRenduAvocat(procedureJudiciareRecours
				.getCompteRenduAvocat());
		procedureJudiciareVO.setDateAcceptRglAmiable(procedureJudiciareRecours
				.getDateAcceptRglAmiable());
		procedureJudiciareVO.setDateCreation(procedureJudiciareRecours
				.getDateCreation());
		procedureJudiciareVO.setDateCreationDebut(procedureJudiciareRecours
				.getDateCreationDebut());
		procedureJudiciareVO.setDateCreationFin(procedureJudiciareRecours
				.getDateCreationFin());
		procedureJudiciareVO.setDateDepot(procedureJudiciareRecours
				.getDateDepot());
		procedureJudiciareVO.setDateReception(procedureJudiciareRecours
				.getDateReception());
		procedureJudiciareVO.setDateReceptionCopie(procedureJudiciareRecours
				.getDateReceptionCopie());
		procedureJudiciareVO.setDecisionAdverse(procedureJudiciareRecours
				.getDecisionAdverse());
		procedureJudiciareVO.setDecisionCompagnie(procedureJudiciareRecours
				.getDecisionCompagnie());
		procedureJudiciareVO.setEtatProcJud(procedureJudiciareRecours
				.getEtatProcJud());
		procedureJudiciareVO.setId(procedureJudiciareRecours.getId());
		if (!StringUtils.isEmpty(procedureJudiciareRecours.getIdRecours())) {
			procedureJudiciareVO.setRefRecours(new RecoursVO());
			procedureJudiciareVO.getRefRecours().setId(
					procedureJudiciareRecours.getIdRecours());
		}

		if (!StringUtils.isEmpty(procedureJudiciareRecours.getJuridiction())) {
			procedureJudiciareVO.setRefJuridiction(new TribunalVO());
			procedureJudiciareVO.getRefJuridiction().setId(
					procedureJudiciareRecours.getJuridiction());
			procedureJudiciareVO.getRefJuridiction().setLibelle(
					procedureJudiciareRecours.getLibelleJuridiction());
		}
		procedureJudiciareVO.setLettreAgent(procedureJudiciareRecours
				.getLettreAgent());
		procedureJudiciareVO.setLettreInstruction(procedureJudiciareRecours
				.getLettreInstruction());
		procedureJudiciareVO.setLibellePrescription(procedureJudiciareRecours
				.getLibellePrescription());
		if (!StringUtils
				.isEmpty(procedureJudiciareRecours.getTypeJuridiction())) {
			procedureJudiciareVO.setRefTypeJuridiction(new TypeJuridictionVO());
			procedureJudiciareVO.getRefTypeJuridiction().setCode(
					procedureJudiciareRecours.getTypeJuridiction());
			procedureJudiciareVO.getRefTypeJuridiction().setLibelle(
					procedureJudiciareRecours.getLibelleTypeJuridiction());
		}
		procedureJudiciareVO.setLienCausalite(procedureJudiciareRecours
				.getLienCausalite());
		procedureJudiciareVO.setMontantActionPublique(procedureJudiciareRecours
				.getMontantActionPublique());
		procedureJudiciareVO.setMotifDecisionCie(procedureJudiciareRecours
				.getMotifDecisionCie());
		if (!StringUtils.isEmpty(procedureJudiciareRecours.getNatureDossier())) {
			procedureJudiciareVO.setRefNatureDossier(new NatureDossierVO());
			procedureJudiciareVO.getRefNatureDossier().setCode(
					procedureJudiciareRecours.getNatureDossier());
			procedureJudiciareVO.getRefNatureDossier().setLibelle(
					procedureJudiciareRecours.getLibelleNatureDossier());
		}

		procedureJudiciareVO.setNomAvocatAdverse(procedureJudiciareRecours
				.getNomAvocatAdverse());
		procedureJudiciareVO.setNumeroDossierTribunal(procedureJudiciareRecours
				.getNumeroDossierTribunal());
		procedureJudiciareVO.setPrescription(procedureJudiciareRecours
				.getPrescription());
		procedureJudiciareVO.setReglementAmiable(procedureJudiciareRecours
				.getReglementAmiable());
		procedureJudiciareVO.setRequeteAdversaire(procedureJudiciareRecours
				.getRequeteAdversaire());
		procedureJudiciareVO.setRequeteAvocat(procedureJudiciareRecours
				.getRequeteAvocat());
		procedureJudiciareVO.setResultatJugement(procedureJudiciareRecours
				.getResultatJugement());
		procedureJudiciareVO.setStatutRglAmiable(procedureJudiciareRecours
				.getStatutRglAmiable());
		if (procedureJudiciareRecours.getListeParties() != null
				&& !procedureJudiciareRecours.getListeParties().isEmpty()) {
			procedureJudiciareVO
					.setListePartieAdverses(new PartieAdverseJudVOConverter()
							.recoursEntrantListToSinistreList(procedureJudiciareRecours
									.getListeParties()));
		}
		return procedureJudiciareVO;
	}

	public List sinistreListToRecoursEntrantList(List sinistreList) {
		List<eai.devass.recoursentrant.application.valueobjects.metier.ProcedureJudiciaireVO> recoursEntrantList = new ArrayList<eai.devass.recoursentrant.application.valueobjects.metier.ProcedureJudiciaireVO>();

		List<ProcedureJudiciaireVO> sinistreListl = (List<ProcedureJudiciaireVO>) sinistreList;
		for (ProcedureJudiciaireVO procedureVO : sinistreListl) {
			recoursEntrantList
					.add((eai.devass.recoursentrant.application.valueobjects.metier.ProcedureJudiciaireVO) sinistreToRecoursEntrant(procedureVO));
		}
		return recoursEntrantList;
	}

	public List recoursEntrantListToSinistreList(List recoursEntrantList) {

		List<ProcedureJudiciaireVO> sinistreList = new ArrayList<ProcedureJudiciaireVO>();
		List<eai.devass.recoursentrant.application.valueobjects.metier.ProcedureJudiciaireVO> audienceList = (List<eai.devass.recoursentrant.application.valueobjects.metier.ProcedureJudiciaireVO>) recoursEntrantList;
		for (eai.devass.recoursentrant.application.valueobjects.metier.ProcedureJudiciaireVO procedureVO : audienceList) {
			sinistreList
					.add((ProcedureJudiciaireVO) recoursEntrantToSinistre(procedureVO));
		}
		return sinistreList;
	}

}
