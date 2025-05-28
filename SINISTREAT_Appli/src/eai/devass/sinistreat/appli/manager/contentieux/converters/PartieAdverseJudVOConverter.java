package eai.devass.sinistreat.appli.manager.contentieux.converters;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.PartieAdverseJudVO;

/**
 * Classe qui permet la convertion entre les objets PartieAdverseJudVO des
 * projets sinistre et recours entrant
 * 
 * @author elhacham
 * 
 */
public class PartieAdverseJudVOConverter implements IVOConverter {

	/**
	 * Permet de convertir un objet PartieAdverseJudVO de type sinistre au type
	 * recoursEntrant
	 * 
	 * @param partieAdverseVO
	 * @return eai.devass.recoursentrant.application.valueobjects.metier.
	 *         PartieAdverseJudVO
	 */
	public IValueObject sinistreToRecoursEntrant(IValueObject vo) {
		eai.devass.recoursentrant.application.valueobjects.metier.PartieAdverseJudVO partieAdverseRecours = new eai.devass.recoursentrant.application.valueobjects.metier.PartieAdverseJudVO();
		PartieAdverseJudVO partieAdverseVO = (PartieAdverseJudVO) vo;
		partieAdverseRecours.setAdresse(partieAdverseVO.getAdresse());
		partieAdverseRecours.setCodeAssurance(partieAdverseVO.getCodeAssurance());
		partieAdverseRecours.setCodeTiers(partieAdverseVO.getCodeTiers());
		partieAdverseRecours.setEmail(partieAdverseVO.getEmail());
		partieAdverseRecours.setId(partieAdverseVO.getIdPartieAdverse());
		partieAdverseRecours.setIndemnitePrevisionnelle(partieAdverseVO
				.getIndemnitePrevisionnelle());
		partieAdverseRecours.setLibelleAssurance(partieAdverseVO
				.getLibelleAssurance());
		partieAdverseRecours.setMntExecutionProvisoire(partieAdverseVO
				.getMntExecutionProvisoire());
		partieAdverseRecours.setMontantIndemnisation(partieAdverseVO
				.getMontantIndemnisation());
		partieAdverseRecours.setMontantRequette(partieAdverseVO
				.getMontantRequette());
		partieAdverseRecours.setNom(partieAdverseVO.getNom());
		partieAdverseRecours.setNumeroCIN(partieAdverseVO.getNumeroCIN());
		partieAdverseRecours.setNumeroPolice(partieAdverseVO.getNumeroPolice());
		partieAdverseRecours.setPrenom(partieAdverseVO.getPrenom());
		partieAdverseRecours.setRaisonSociale(partieAdverseVO.getRaisonSociale());
		partieAdverseRecours.setReference(partieAdverseVO.getReference());
		partieAdverseRecours.setSubstitution(partieAdverseVO.getSubstitution());
		partieAdverseRecours.setTelephone(partieAdverseVO.getTelephone());
		partieAdverseRecours.setType(partieAdverseVO.getPartieAdverseType());
		partieAdverseRecours.setVille(partieAdverseVO.getVille());
		return partieAdverseRecours;
	}

	/**
	 * Permet de convertir un objet PartieAdverseJudVO de type recoursEntrant au
	 * type sinistre
	 * 
	 * @param partieAdverseVO
	 * @return PartieAdverseJudVO
	 */
	public IValueObject recoursEntrantToSinistre(IValueObject vo) {
		PartieAdverseJudVO rPartieAdverseVO = new PartieAdverseJudVO();
		eai.devass.recoursentrant.application.valueobjects.metier.PartieAdverseJudVO partieAdverseRecours = (eai.devass.recoursentrant.application.valueobjects.metier.PartieAdverseJudVO) vo;
		rPartieAdverseVO.setAdresse(partieAdverseRecours.getAdresse());
		rPartieAdverseVO.setCodeAssurance(partieAdverseRecours.getCodeAssurance());
		rPartieAdverseVO.setCodeTiers(partieAdverseRecours.getCodeTiers());
		rPartieAdverseVO.setEmail(partieAdverseRecours.getEmail());
		rPartieAdverseVO.setIdPartieAdverse(partieAdverseRecours.getId());
		rPartieAdverseVO.setIndemnitePrevisionnelle(partieAdverseRecours
				.getIndemnitePrevisionnelle());
		rPartieAdverseVO.setLibelleAssurance(partieAdverseRecours
				.getLibelleAssurance());
		rPartieAdverseVO.setMntExecutionProvisoire(partieAdverseRecours
				.getMntExecutionProvisoire());
		rPartieAdverseVO.setMontantIndemnisation(partieAdverseRecours
				.getMontantIndemnisation());
		rPartieAdverseVO.setMontantRequette(partieAdverseRecours
				.getMontantRequette());
		rPartieAdverseVO.setNom(partieAdverseRecours.getNom());
		rPartieAdverseVO.setNumeroCIN(partieAdverseRecours.getNumeroCIN());
		rPartieAdverseVO.setNumeroPolice(partieAdverseRecours.getNumeroPolice());
		rPartieAdverseVO.setPrenom(partieAdverseRecours.getPrenom());
		rPartieAdverseVO.setRaisonSociale(partieAdverseRecours.getRaisonSociale());
		rPartieAdverseVO.setReference(partieAdverseRecours.getReference());
		rPartieAdverseVO.setSubstitution(partieAdverseRecours.getSubstitution());
		rPartieAdverseVO.setTelephone(partieAdverseRecours.getTelephone());
		rPartieAdverseVO.setPartieAdverseType(partieAdverseRecours.getType());
		rPartieAdverseVO.setVille(partieAdverseRecours.getVille());
		return rPartieAdverseVO;
	}

	/**
	 * Permet de convertir une liste d'objets PartieAdverseJudVO de type
	 * sinistre au type recoursEntrant
	 * 
	 * @param list
	 * @return List<eai.devass.recoursentrant.application.valueobjects.metier.
	 *         PartieAdverseJudVO>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List sinistreListToRecoursEntrantList(List sinistreList) {

		List<eai.devass.recoursentrant.application.valueobjects.metier.PartieAdverseJudVO> recoursEntrantList = new ArrayList<eai.devass.recoursentrant.application.valueobjects.metier.PartieAdverseJudVO>();

		List<PartieAdverseJudVO> sinistreListl = (List<PartieAdverseJudVO>) sinistreList;
		for (PartieAdverseJudVO partieAdverseJudVO : sinistreListl) {
			recoursEntrantList
					.add((eai.devass.recoursentrant.application.valueobjects.metier.PartieAdverseJudVO) sinistreToRecoursEntrant(partieAdverseJudVO));
		}
		return recoursEntrantList;
	}

	/**
	 * Permet de convertir une liste d'objets PartieAdverseJudVO de type
	 * recoursEntrant au type sinistre
	 * 
	 * @param list
	 * @return List<PartieAdverseJudVO>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List recoursEntrantListToSinistreList(List recoursEntrantList) {
		List<PartieAdverseJudVO> sinistreList = new ArrayList<PartieAdverseJudVO>();
		List<eai.devass.recoursentrant.application.valueobjects.metier.PartieAdverseJudVO> recoursEntrantListl = (List<eai.devass.recoursentrant.application.valueobjects.metier.PartieAdverseJudVO>) recoursEntrantList;
		for (eai.devass.recoursentrant.application.valueobjects.metier.PartieAdverseJudVO partieAdverseJudVO : recoursEntrantListl) {
			sinistreList
					.add((PartieAdverseJudVO) recoursEntrantToSinistre(partieAdverseJudVO));
		}
		return sinistreList;
	}
}
