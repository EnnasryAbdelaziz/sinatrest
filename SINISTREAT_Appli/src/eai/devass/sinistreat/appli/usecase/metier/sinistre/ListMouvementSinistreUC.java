package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Mouvement;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;

public class ListMouvementSinistreUC extends BaseUC {

	//@SuppressWarnings("all")
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		SinistreVO sin = (SinistreVO) entite;
		List<Mouvement> listMouvement = null;
		List<Mouvement> listMouvementMig = null;
		List<Mouvement> listMvt = new ArrayList<Mouvement>();
		PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);

		try {
			
			// Ancien code
//			listMouvement = sinistreManager.getListMouvementSinistre(sin);
//			listMouvementMig = sinistreManager.getListMouvementSinistreMig(sin);
//			if (listMouvementMig != null && listMouvementMig.size() != 0) {
//				// récupérer le premier et le dernier mouvement
//				listMvt.add(listMouvementMig.get(0));
//				int lastMvt = listMouvementMig.size() - 1;
//				listMvt.add(listMouvementMig.get(lastMvt));
//
//			}
//			if (listMouvement != null && listMouvement.size() != 0) {
//				for (Mouvement mouvement : listMouvement) {
////					if (isChangementReserve(mouvement)) {
//						listMvt.add(mouvement);
////					}
//				}
//			}
			
			// Nouveau code
			
			if (pagerVO != null && pagerVO.getNbrLignes() != null) {
				if (pagerVO.getNumPage() == null) {
					throw new FonctionnelleException(
							EXP_PAGER_NUMERO_PAGE_OBLIGATOIRE);
				} else if (pagerVO.getPageSize() == null) {
					throw new FonctionnelleException(
							EXP_PAGER_PAGE_SIZE_OBLIGATOIRE);
				}
				if ("1".equals(pagerVO.getNbrLignes())) {
					Integer nbreObject = sinistreManager.getNombreMouvement(sin);
					pagerVO.setNbrLignes(nbreObject.toString());
					Integer pageSize = Integer.valueOf(pagerVO.getPageSize());
					if(nbreObject.compareTo(pageSize)>=0){
					if ((nbreObject % pageSize) == 0) {
						pagerVO.setNbrPages(String.valueOf(nbreObject
								/ pageSize));
					} else {
						pagerVO.setNbrPages(String
								.valueOf((nbreObject / pageSize) + 1));

					}
					}
					else{
						pagerVO.setNbrPages("1");
					}
				}
			}
			
			listMvt = sinistreManager.getAllMouvementSinistre(sin, pagerVO);

			addResultItem(listMvt);

			if (pagerVO != null) {
				this.addResultItem(pagerVO);
			}
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}
	}

	private boolean isChangementReserve(Mouvement mouvement) {

		boolean changementRes = false;

		if (mouvement.getReserveGraveOld() == null
				|| mouvement.getReserveGraveOld().compareTo(
						mouvement.getReserveGrave()) != 0) {
			changementRes = true;
		} else if (mouvement.getReserveOrdOld() == null
			 || mouvement.getReserveOrdOld().compareTo(
						mouvement.getReserveOrd()) != 0) {
			changementRes = true;
		} else if (mouvement.getReserveProtheseOld() == null
				|| mouvement.getReserveProtheseOld().compareTo(
						mouvement.getReserveProthese()) != 0) {
			changementRes = true;
		}
		return changementRes;
	}

}
