package eai.devass.sinistreat.appli.usecase.metier.conciliation;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.conciliation.AyantDroitOffre;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Conciliation;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Offre;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;

public class RechercherConciliationAValiderUC extends BaseUC {

	@Override
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		List<Conciliation> listConciliation = null;
		/**
		 * Validation avec ordonancement
		 */
		Conciliation conciliation = (Conciliation) this
				.getItem(Conciliation.class);
		PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);
		if (Fonctions.isEmpty(conciliation.getUserCreateur())) {
			throw new FonctionnelleException(
					"Le code SAS utilisateur est obligatoire !!");
		}

		if (pagerVO != null) {
			if (pagerVO.getNumPage() == null) {
				throw new FonctionnelleException(
						EXP_PAGER_NUMERO_PAGE_OBLIGATOIRE);
			} else if (pagerVO.getPageSize() == null) {
				throw new FonctionnelleException(
						EXP_PAGER_PAGE_SIZE_OBLIGATOIRE);
			}
			// vérification si le USER connecter est supperieur
			String codeSas = conciliation.getUserCreateur();
			Boolean isSup = conciliationManager.isSuperieur(codeSas);
			if (isSup.equals(true)) {

				try {
					Integer nbreLignes = conciliationManager
							.getNombreConciliationSupAValider(codeSas);
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
					listConciliation = (List<Conciliation>) conciliationManager
							.getConciliationSupAValider(codeSas, pagerVO);
				} catch (ConstraintViolationException e) {
					throw new FonctionnelleException(e);
				} catch (Exception e) {
					throw new FonctionnelleException(e.getMessage());
				}
			} else {
				/**
				 * Fin
				 */

				try {

					Integer nbreLignes = conciliationManager
							.getNombreConciliationAValider();
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
					listConciliation = (List<Conciliation>) conciliationManager
							.getConciliationAValider(pagerVO);

				} catch (ConstraintViolationException e) {
					throw new FonctionnelleException(e);
				} catch (Exception e) {
					throw new FonctionnelleException(e.getMessage());
				}
			}
			if (listConciliation != null) {
				for (Conciliation conc : listConciliation) {
					if (conc.getListOffre() != null) {
						for (Offre o : conc.getListOffre()) {
							if (o.getListAyantDroit() != null) {
								/* An1: le montant rente est erroné : */
								double montantRente = 0;
								for (AyantDroitOffre ayd : o
										.getListAyantDroit()) {
									if (ayd.isChoix()) {
										montantRente += ayd.getMontantRente();
									}
									o.setMontantRente(montantRente);
								}
							}

						}
					}

				}
			}

			addResultItem(listConciliation);
			if (pagerVO != null) {
				this.addResultItem(pagerVO);
			}
		}
	}
}
