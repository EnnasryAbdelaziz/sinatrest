package eai.devass.sinistreat.appli.usecase.ihm;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;
/**
 * Ini
 * @author madraras
 *
 */

@SuppressWarnings("all")
public class InitValidationReglementSubordonneUC extends BaseUC {

	
	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		logger.info("Debut UC InitValidationReglementSubordonneUC");
		// Récupérer la class de l'objet BO correspondant à l'objet VO
		Reglement reglement = (Reglement) this.getItem(Reglement.class);
		PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);
		HashMap map = (HashMap) this.getItem(HashMap.class);
		// Tous les reglement dont l etat est :
		reglement.setCodeEtatRgl(new String[] {
				IConstantes.ETAT_REGLEMENT_EN_INSTANCE_DE_VALIDATION,IConstantes.ETAT_REGLEMENT_EN_INSTANCE_POSITIONNEMENT});
		try {
			BigDecimal seuil = BigDecimal.valueOf(99999999);
			BigDecimal seuilsub = BigDecimal.valueOf(0);
			if (pagerVO != null) {
				if (pagerVO.getNumPage() == null) {
					throw new FonctionnelleException(EXP_PAGER_NUMERO_PAGE_OBLIGATOIRE);
				} else if (pagerVO.getPageSize() == null) {
					throw new FonctionnelleException(EXP_PAGER_PAGE_SIZE_OBLIGATOIRE);
				}
								
				Integer nbreLignes = (Fonctions.isLong(pagerVO.getNbrLignes())) 
					? Integer.valueOf(pagerVO.getNbrLignes()) : null;

				if(nbreLignes == null) {
					nbreLignes = reglementManager.getNombreReglementAValider(
							reglement, map, seuil, seuilsub);
				}
				
				pagerVO.setNbrLignes(String.valueOf(nbreLignes));
				Integer pageSize = Integer.valueOf(pagerVO.getPageSize());
				if (pageSize != 0) {
					pagerVO.setNbrPages(String
							.valueOf((nbreLignes / pageSize) + 1));
				}
			}
	
			
			List<Reglement> listRegSeuil = (List<Reglement>) reglementManager
					.getListReglementReglementAValider(reglement, map, pagerVO,
							seuil, seuilsub);
			List<Reglement> listReglementSeuil = reglementManager.getListReglementByModeReglment(listRegSeuil, reglement.getModeReglement());
			
			this.addResultItem(listRegSeuil);
			if (pagerVO != null) {
				this.addResultItem(pagerVO);
			}

		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);

		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}
		logger.info("Fin UC InitValidationReglementSubordonneUC");
	}

	public boolean isTransactionnal() {
		return false;
	}

}
