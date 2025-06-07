package eai.devass.sinistreat.appli.usecase.metier.reglement;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.apache.commons.lang.StringUtils;
import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.utils.entites.IDate;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.ReglementVO;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;


@SuppressWarnings("all")
public class RechercheReglementUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {

		ReglementVO reglementvo = (ReglementVO) entite;
		Reglement reglement = (Reglement) this.getItem(Reglement.class);
		List<Reglement> listQuittance = null;
		PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);
		HashMap mapDates = (HashMap) this.getItem(HashMap.class);

		try {
			if (reglement.getRefTypeReglement() != null
					&& reglement.getRefTypeReglement().getCode()
							.equals(IConstantes.TYPE_REGLEMENT_BGD)) {
				reglement
						.setCodeEtatRgl(new String[] {
								IConstantes.ETAT_REGLEMENT_EN_INSTANCE_POSITIONNEMENT,
								IConstantes.ETAT_REGLEMENT_EN_INSTANCE_DE_VALIDATION,
								IConstantes.ETAT_REGLEMENT_EN_INSTANCE_VALIDATION_SUPERIEURE,
//								IConstantes.ETAT_REGLEMENT_EN_INSTANCE_DE_REGLEMENT,
//								IConstantes.ETAT_REGLEMENT_QTC_ANNULEE,
//								IConstantes.ETAT_REGLEMENT_REGLE,
//								IConstantes.ETAT_REGLEMENT_SUPPRIME 
								});
			}

			if (reglementvo.getDateEtablissementDebut() != null
					&& !StringUtils.isEmpty(reglementvo
							.getDateEtablissementDebut())) {
				if (mapDates == null) {
					mapDates = new HashMap();
				}
				mapDates.put(IDate.DATE_DEBUT,
						reglementvo.getDateEtablissementDebut());
			}
			if (reglementvo.getDateEtablissementFin() != null
					&& !StringUtils.isEmpty(reglementvo
							.getDateEtablissementFin())) {
				if (mapDates == null) {
					mapDates = new HashMap();
				}
				mapDates.put(IDate.DATE_FIN,
						reglementvo.getDateEtablissementFin());
			}

			if (pagerVO != null) {
				if (pagerVO.getNumPage() == null) {
					throw new FonctionnelleException(
							EXP_PAGER_NUMERO_PAGE_OBLIGATOIRE);
				} else if (pagerVO.getPageSize() == null) {
					throw new FonctionnelleException(
							EXP_PAGER_PAGE_SIZE_OBLIGATOIRE);
				}
			}
			Integer nbreObject = reglementManager.getNombreQuittance(reglement,
					mapDates);
			if (nbreObject.compareTo(IParam.MAX_RESULT_REGLEMENT) > 0) {
				throw new FonctionnelleException(EXP_MAX_RESULT);
			}
			listQuittance = (List<Reglement>) reglementManager.getListReglement(reglement,
					mapDates, pagerVO);
			
			
			if (listQuittance != null && listQuittance.size() > 0){
				for (Reglement reg : listQuittance) {
					if (reg.getRefSinistre() != null) {
						reg.getRefSinistre().setListReglement(null);
					}
				}
			}
			if (pagerVO != null) {
				Integer pageSize = Integer.valueOf(pagerVO.getPageSize());
				if (pageSize != 0) {
					pagerVO.setNbrLignes(String
							.valueOf(nbreObject));
					pagerVO.setNbrPages(String
							.valueOf((nbreObject / pageSize) + 1));
				}
			}
			addResultItem(listQuittance);
			if (listQuittance != null
					&& listQuittance.size() == 1
					&& (reglementvo.getService() == null || (reglementvo
							.getService() != null && !reglementvo.getService()
							.equals("1")))) {
				Reglement reg = new Reglement();
				reg.setRefSinistre(((Reglement) listQuittance.get(0))
						.getRefSinistre());
				double cumulQuittanceEmise = reglementManager
						.getCumulQuittanceEmise(reg);
				reg.setRefSinistre(null);
				reg.setMontant(cumulQuittanceEmise);
				this.addResultItem(reg);
			}
			if (pagerVO != null) {
				this.addResultItem(pagerVO);
			}
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e);
		}

	}

	public boolean isTransactionnal() {
		return true;
	}

}
