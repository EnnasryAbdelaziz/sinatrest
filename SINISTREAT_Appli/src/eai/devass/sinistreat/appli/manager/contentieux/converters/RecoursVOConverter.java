package eai.devass.sinistreat.appli.manager.contentieux.converters;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.RecoursVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.EtatRecoursVO;

/**
 * Classe qui permet la convertion entre les objets recoursVO des projets
 * sinistre et recours entrant
 * 
 * @author elhacham
 * 
 */
public class RecoursVOConverter implements IVOConverter {

	/**
	 * Permet de convertir un recoursVO de type sinistre au type recoursEntrant
	 * 
	 * @param recoursVO
	 * @return eai.devass.recoursentrant.application.valueobjects.
	 *         metier.RecoursVO
	 */
	public IValueObject sinistreToRecoursEntrant(IValueObject vo) {
		RecoursVO recoursVO = (RecoursVO) vo;
		eai.devass.recoursentrant.application.valueobjects.metier.RecoursVO recoursService = new eai.devass.recoursentrant.application.valueobjects.metier.RecoursVO();
		recoursService.setAssure(recoursVO.getAssure());
		recoursService.setCodeBranche(recoursVO.getCodeBranche());
		recoursService.setCodeCoassurance(recoursVO.getCodeCoassurance());
		recoursService.setCodeIntermediaire(recoursVO.getCodeIntermediaire());
		recoursService.setDateCreation(recoursVO.getDateCreation());
		recoursService.setDatePrescription(recoursVO.getDatePrescription());
		recoursService.setDateSinistre(recoursVO.getDateSinistre());
		if (recoursVO.getRefEtatRecours() != null) {
			recoursService.setEtatRecours(recoursVO.getRefEtatRecours()
					.getCode());
		}
		recoursService.setEtatRecoursLast(recoursVO.getEtatRecoursLast());
		recoursService.setId(recoursVO.getIdRecours());
		recoursService.setMontantFinal(recoursVO.getMontantFinal());
		recoursService.setNumeroDossierInstruction(recoursVO
				.getNumeroDossierInstruction());
		recoursService.setNumeroPolice(recoursVO.getNumeroPolice());
		recoursService.setTypeProcedureInitial(recoursVO
				.getTypeProcedureInitial());
		if (recoursVO.getRefSinistre()!=null 
				&& !StringUtils.isEmpty(recoursVO.getRefSinistre().getNumeroSinistre())){
			recoursService.setNumeroSinistre(recoursVO.getRefSinistre().getNumeroSinistre());
		}
		
//		if(recoursVO.getListeProcedureJudiciaire()!=null
//				&& !recoursVO.getListeProcedureJudiciaire().isEmpty()){
//			recoursService
//				.setListeProcedureJudiciaire(new ProcedureJudiciaireVOConverter()
//					.sinistreListToRecoursEntrantList(recoursVO
//							.getListeProcedureJudiciaire()));
//		}
		return recoursService;
	}

	/**
	 * Permet de convertir un recoursVO de type recoursEntrant au type sinistre
	 * 
	 * @param recoursVO
	 * @return RecoursVO
	 */
	public IValueObject recoursEntrantToSinistre(IValueObject vo) {
		RecoursVO recoursVo = new RecoursVO();
		eai.devass.recoursentrant.application.valueobjects.metier.RecoursVO recoursService = (eai.devass.recoursentrant.application.valueobjects.metier.RecoursVO) vo;
		recoursVo.setAssure(recoursService.getAssure());
		recoursVo.setTypeProcedureInitial(recoursService.getChoixTypeProcedure());
		recoursVo.setCodeBranche(recoursService.getCodeBranche());
		recoursVo.setCodeCoassurance(recoursService.getCodeCoassurance());
		recoursVo.setCodeIntermediaire(recoursService.getCodeIntermediaire());
		recoursVo.setDateCreation(recoursService.getDateCreation());
		recoursVo.setDatePrescription(recoursService.getDatePrescription());
		recoursVo.setDateSinistre(recoursService.getDateSinistre());
		recoursVo.setRefEtatRecours(new EtatRecoursVO());
		recoursVo.getRefEtatRecours().setCode(recoursService.getEtatRecours());
		recoursVo.setEtatRecoursLast(recoursService.getEtatRecoursLast());
		recoursVo.setIdRecours(recoursService.getId());
		recoursVo.setMontantFinal(recoursService.getMontantFinal());
		recoursVo.setNumeroDossierInstruction(recoursService
				.getNumeroDossierInstruction());
		recoursVo.setNumeroPolice(recoursService.getNumeroPolice());
		recoursVo.setTypeProcedureInitial(recoursService.getTypeProcedureInitial());
		if (!StringUtils.isEmpty(recoursService.getNumeroSinistre())) {
			recoursVo.setRefSinistre(new SinistreVO());
			recoursVo.getRefSinistre().setNumeroSinistre(
					recoursService.getNumeroSinistre());
		}
		recoursVo.setMontantProvision(recoursService.getMontantFinal() );
//		if(recoursService.getListeProcedureJudiciaire()!=null
//				&& !recoursService.getListeProcedureJudiciaire().isEmpty()){
//			recoursVo
//				.setListeProcedureJudiciaire(new ProcedureJudiciaireVOConverter()
//					.recoursEntrantListToSinistreList(recoursService
//							.getListeProcedureJudiciaire()));
//		}
		return recoursVo;
	}

	public List sinistreListToRecoursEntrantList(List sinistreList) {
		// TODO Auto-generated method stub
		return null;
	}

	public List recoursEntrantListToSinistreList(List recoursEntrantList) {
		// TODO Auto-generated method stub
		return null;
	}
}
