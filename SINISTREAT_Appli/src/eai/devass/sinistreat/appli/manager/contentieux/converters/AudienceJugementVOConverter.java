package eai.devass.sinistreat.appli.manager.contentieux.converters;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import eai.devass.recoursentrant.application.valueobjects.metier.ConvocationVO;
import eai.devass.recoursentrant.application.valueobjects.metier.JugementVO;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.AudienceJugementVO;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.ProcedureJudiciaireVO;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.RecoursVO;

/**
 * Classe qui permet la convertion entre les objets audienceJugementVO des
 * projets sinistre et recours entrant
 * 
 * @author elhacham
 * 
 */
public class AudienceJugementVOConverter implements IVOConverter {
	
	private Logger logger = Logger.getLogger("loggerSINAT");
	/**
	 * Permet de convertir un objet audienceJugementVO de type sinistre au type
	 * recoursEntrant
	 * 
	 * @param audienceJugementVO
	 * @return eai.devass.recoursentrant.application.valueobjects.metier.
	 *         AudienceJugementVO
	 */
	public IValueObject sinistreToRecoursEntrant(IValueObject vo) {

		AudienceJugementVO audienceJugementVO = (AudienceJugementVO) vo;
		ConvocationVO convocationVO = new ConvocationVO();
		convocationVO.setId(audienceJugementVO.getIdAudience());
		if (audienceJugementVO.getRefProcedureJudiciaire() != null) {
			convocationVO.setIdProcedureJudiciaire(audienceJugementVO
					.getRefProcedureJudiciaire().getIdProcedure());
		}
		convocationVO.setTypeConvocation("1");
		convocationVO.setLibelleTypeConvocation("Convocation audience");
		convocationVO.setDateConvocation(audienceJugementVO
				.getDateConvocation());
		convocationVO.setHeureConvocation(audienceJugementVO
				.getHeureConvocation());
		convocationVO.setNumeroSalleAudience(audienceJugementVO
				.getNumeroSalleAudience());
		convocationVO.setAdresse(audienceJugementVO.getAdresse());
		convocationVO.setVille(audienceJugementVO.getVille());
		convocationVO.setDateCreation(audienceJugementVO.getDateCreation());
		convocationVO.setDateModification(audienceJugementVO
				.getDateModification());
		convocationVO.setUtilisateur(audienceJugementVO.getUserCreateur());
		convocationVO.setDateCreationDebut(audienceJugementVO
				.getDateCreationDebut());
		convocationVO.setDateCreationFin(audienceJugementVO
				.getDateCreationFin());

		return convocationVO;
	}

	/**
	 * Permet de convertir un objet de type AudienceJugementVO recoursEntrant au
	 * type sinistre
	 * 
	 * @param audienceJugementVO
	 * @return AudienceJugementVO
	 */
	public IValueObject recoursEntrantToSinistre(IValueObject vo) {

		ConvocationVO convocationVO = (ConvocationVO) vo;
		try {
			AudienceJugementVO rAudienceJugementVO = new AudienceJugementVO();

			rAudienceJugementVO.setIdAudience(convocationVO.getId());
			if (!StringUtils.isEmpty(convocationVO.getIdProcedureJudiciaire())) {
				rAudienceJugementVO
						.setRefProcedureJudiciaire(new ProcedureJudiciaireVO());
				rAudienceJugementVO.getRefProcedureJudiciaire().setIdProcedure(
						convocationVO.getIdProcedureJudiciaire());
			}
			rAudienceJugementVO.setDateConvocation(convocationVO
					.getDateConvocation());
			rAudienceJugementVO.setHeureConvocation(convocationVO
					.getHeureConvocation());
			rAudienceJugementVO.setNumeroSalleAudience(convocationVO
					.getNumeroSalleAudience());
			rAudienceJugementVO.setAdresse(convocationVO.getAdresse());
			rAudienceJugementVO.setVille(convocationVO.getVille());
			rAudienceJugementVO
					.setDateCreation(convocationVO.getDateCreation());
			rAudienceJugementVO.setDateModification(convocationVO
					.getDateModification());
			rAudienceJugementVO.setUserCreateur(convocationVO.getUtilisateur());
			rAudienceJugementVO.setDateCreationDebut(convocationVO
					.getDateCreationDebut());
			rAudienceJugementVO.setDateCreationFin(convocationVO
					.getDateCreationFin());
			return rAudienceJugementVO;
		} catch (Exception e) {
			logger.error("problème technique",e);
			return null;
		}
	}

	public List sinistreListToRecoursEntrantList(List sinistreList) {
		List<ConvocationVO> recoursEntrantList = new ArrayList<ConvocationVO>();

		List<AudienceJugementVO> sinistreListl = (List<AudienceJugementVO>) sinistreList;
		for (AudienceJugementVO audienceVO : sinistreListl) {
			recoursEntrantList
					.add((ConvocationVO) sinistreToRecoursEntrant(audienceVO));
		}
		return recoursEntrantList;
	}

	public List recoursEntrantListToSinistreList(List recoursEntrantList) {
		try {
			List<AudienceJugementVO> sinistreList = new ArrayList<AudienceJugementVO>();
			List<ConvocationVO> audienceList = (List<ConvocationVO>) recoursEntrantList;
			for (ConvocationVO audienceVO : audienceList) {
				sinistreList
						.add((AudienceJugementVO) recoursEntrantToSinistre(audienceVO));
			}
			return sinistreList;
		} catch (Exception e) {
			logger.error("problème technique",e);
			return null;
		}

	}

	public IValueObject sinistreToJugementRecoursEntrant(IValueObject vo) {
		AudienceJugementVO audienceJugementVO = (AudienceJugementVO) vo;
		JugementVO jugementVO = new JugementVO();
		jugementVO.setId(audienceJugementVO.getIdJugement());
		if (audienceJugementVO.getRefProcedureJudiciaire() != null) {
			jugementVO.setIdProcedureJudiciaire(audienceJugementVO
					.getRefProcedureJudiciaire().getIdProcedure());
			if (audienceJugementVO.getRefProcedureJudiciaire().getRefRecours() != null) {
				jugementVO.setIdRecours(audienceJugementVO
						.getRefProcedureJudiciaire().getRefRecours()
						.getIdRecours());
			}
		}
		jugementVO.setTypeJugement(audienceJugementVO.getTypeJugement());
		jugementVO.setDateJugement(audienceJugementVO.getDateJugement());
		jugementVO.setNomJuge(audienceJugementVO.getNomJuge());
		jugementVO.setDateReceptionNotification(audienceJugementVO
				.getDateReceptionNotification());
		jugementVO.setDecisionCompagnie(audienceJugementVO
				.getDecisionCompagnie());
		jugementVO.setMotifDecisionCompagnie(audienceJugementVO
				.getMotifDecisionCompagnie());
		jugementVO.setDateCreation(audienceJugementVO.getDateCreation());
		jugementVO.setEtat(audienceJugementVO.getEtatJugement());
		jugementVO.setMotifAnnulation(audienceJugementVO.getMotifAnnulation());
		jugementVO.setTauxResponsabiliteAssureCie(audienceJugementVO
				.getTauxResponsabiliteAssureCie());
		jugementVO.setResumeCR(audienceJugementVO.getResumeCR());
		jugementVO.setEvaluationCR(audienceJugementVO.getEvaluationCR());
		jugementVO.setReceptionCR(audienceJugementVO.getReceptionCR());
		jugementVO.setDateReceptionCR(audienceJugementVO.getDateReceptionCR());
		jugementVO.setMotifRejetCR(audienceJugementVO.getMotifRejetCR());
		jugementVO.setTypeCompteRendu(audienceJugementVO.getTypeCompteRendu());
		jugementVO.setTypeJugementADD(audienceJugementVO.getTypeJugementADD());
		jugementVO.setTypeJugementCS(audienceJugementVO.getTypeJugementCS());
		jugementVO
				.setArretConfirmatif(audienceJugementVO.getArretConfirmatif());
		jugementVO.setResumeJugementFond(audienceJugementVO
				.getResumeJugementFond());
		jugementVO.setCodePartieAdverse(audienceJugementVO
				.getCodePartieAdverse());
		jugementVO.setCodeExpertJudiciaire(audienceJugementVO
				.getCodeExpertJudiciaire());
		jugementVO.setDateCreationDebut(audienceJugementVO
				.getDateCreationDebut());
		jugementVO.setDateCreationFin(audienceJugementVO.getDateCreationFin());
		jugementVO.setMontantIndemnisation(audienceJugementVO
				.getMontantIndemnisation());
		jugementVO.setExecutionProvisoire(audienceJugementVO
				.getExecutionProvisoire());
		jugementVO.setPourcentageExecution(audienceJugementVO
				.getPourcentageExecution());
		jugementVO.setNumeroJugement(audienceJugementVO.getNumeroJugement());
		
		return jugementVO;
	}

	public IValueObject recoursEntrantJugementToSinistre(IValueObject jugement,
			IValueObject audienceJugement) {
		JugementVO jugementVO = (JugementVO) jugement;
		AudienceJugementVO audienceJugementVO = (AudienceJugementVO) audienceJugement;
		if (audienceJugementVO == null) {
			audienceJugementVO = new AudienceJugementVO();
		}
		audienceJugementVO.setIdJugement(jugementVO.getId());
		if (!StringUtils.isEmpty(jugementVO.getIdProcedureJudiciaire())) {
			if (audienceJugementVO.getRefProcedureJudiciaire() == null) {
				audienceJugementVO
						.setRefProcedureJudiciaire(new ProcedureJudiciaireVO());
			}
			audienceJugementVO.getRefProcedureJudiciaire().setIdProcedure(
					jugementVO.getIdProcedureJudiciaire());
			if (!StringUtils.isEmpty(jugementVO.getIdRecours())) {
				if (audienceJugementVO.getRefProcedureJudiciaire().getRefRecours()==null) {
					audienceJugementVO.getRefProcedureJudiciaire().setRefRecours(new RecoursVO());
				}
				audienceJugementVO.getRefProcedureJudiciaire().getRefRecours().setIdRecours(
						jugementVO.getIdRecours());
			}
		}
		audienceJugementVO.setTypeJugement(jugementVO.getTypeJugement());
		audienceJugementVO.setDateJugement(jugementVO.getDateJugement());
		audienceJugementVO.setNomJuge(jugementVO.getNomJuge());
		audienceJugementVO.setDateReceptionNotification(jugementVO
				.getDateReceptionNotification());
		audienceJugementVO.setDecisionCompagnie(jugementVO
				.getDecisionCompagnie());
		audienceJugementVO.setMotifDecisionCompagnie(jugementVO
				.getMotifDecisionCompagnie());
		audienceJugementVO.setDateCreation(jugementVO.getDateCreation());
		audienceJugementVO.setEtatJugement(jugementVO.getEtat());
		audienceJugementVO.setMotifAnnulation(jugementVO.getMotifAnnulation());
		audienceJugementVO.setTauxResponsabiliteAssureCie(jugementVO
				.getTauxResponsabiliteAssureCie());
		audienceJugementVO.setResumeCR(jugementVO.getResumeCR());
		audienceJugementVO.setEvaluationCR(jugementVO.getEvaluationCR());
		audienceJugementVO.setReceptionCR(jugementVO.getReceptionCR());
		audienceJugementVO.setDateReceptionCR(jugementVO.getDateReceptionCR());
		audienceJugementVO.setMotifRejetCR(jugementVO.getMotifRejetCR());
		audienceJugementVO.setTypeCompteRendu(jugementVO.getTypeCompteRendu());
		audienceJugementVO.setTypeJugementADD(jugementVO.getTypeJugementADD());
		audienceJugementVO.setTypeJugementCS(jugementVO.getTypeJugementCS());
		audienceJugementVO
				.setArretConfirmatif(jugementVO.getArretConfirmatif());
		audienceJugementVO.setResumeJugementFond(jugementVO
				.getResumeJugementFond());
		audienceJugementVO.setCodePartieAdverse(jugementVO
				.getCodePartieAdverse());
		audienceJugementVO.setCodeExpertJudiciaire(jugementVO
				.getCodeExpertJudiciaire());
		audienceJugementVO.setDateCreationDebut(jugementVO
				.getDateCreationDebut());
		audienceJugementVO.setDateCreationFin(jugementVO.getDateCreationFin());
		audienceJugementVO.setMontantIndemnisation(jugementVO
				.getMontantIndemnisation());
		audienceJugementVO.setExecutionProvisoire(jugementVO
				.getExecutionProvisoire());
		audienceJugementVO.setPourcentageExecution(jugementVO
				.getPourcentageExecution());
		audienceJugementVO.setNumeroJugement(jugementVO.getNumeroJugement());
		return audienceJugementVO;
	}

}
