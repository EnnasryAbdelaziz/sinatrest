package eai.devass.sinistreat.appli.usecase.metier.reglement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;

public class ListePreQuittanceUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {

		// Récupérer la class de l'objet BO correspondant à l'objet VO
		Reglement reglement = (Reglement) this.getItem(Reglement.class);
		PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);
		HashMap map = (HashMap) this.getItem(HashMap.class);
		Boolean editer = Boolean.TRUE;
		try {
			BigDecimal seuil = BigDecimal.valueOf(999999);
			BigDecimal seuilsub = BigDecimal.valueOf(0);

			// Tous les reglement dont l etat est :

			reglement
					.setCodeEtatRgl(new String[] { IConstantes.ETAT_PRE_QUITTANCE_VALIDEE });

			if (pagerVO != null) {
				if (pagerVO.getNumPage() == null) {
					throw new FonctionnelleException(
							EXP_PAGER_NUMERO_PAGE_OBLIGATOIRE);
				} else if (pagerVO.getPageSize() == null) {
					throw new FonctionnelleException(
							EXP_PAGER_PAGE_SIZE_OBLIGATOIRE);
				}
                Integer nbreLignes = reglementManager.getNombreReglement(
                        reglement, map, editer);

				pagerVO.setNbrLignes(nbreLignes.toString());
				Integer pageSize = Integer.valueOf(pagerVO.getPageSize());
				if (pageSize != 0) {

					int rest = nbreLignes % pageSize;
					if (rest != 0) {
						pagerVO.setNbrPages(String
								.valueOf((nbreLignes / pageSize) + 1));
					} else {
						pagerVO.setNbrPages(String
								.valueOf((nbreLignes / pageSize)));
					}
				}

			}
			
			List<Reglement> listReglement = new ArrayList<Reglement>();
			
            Iterator itr = ((List) reglementManager.getListQuittance(reglement,
                    map, pagerVO, editer)).iterator();
			while(itr.hasNext()){
			   Object[] obj = (Object[]) itr.next();
			   Reglement reg = (Reglement) obj[0];
			   listReglement.add(reg);
			}
			
			for (Reglement reg : listReglement) {
				if (reg.getRefSinistre() != null) {
					reg.getRefSinistre().setListReglement(null);
				}
			}

			addResultItem(listReglement);

			if (pagerVO != null) {
				this.addResultItem(pagerVO);
			}

		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);

		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

	}

	public boolean isTransactionnal() {
		return false;
	}

}
