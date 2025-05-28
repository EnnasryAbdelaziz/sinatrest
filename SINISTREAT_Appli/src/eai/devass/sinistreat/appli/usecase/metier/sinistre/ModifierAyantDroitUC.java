package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.apache.commons.lang.StringUtils;
import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.AyantDroit;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Evenement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.AyantDroitVO;

public class ModifierAyantDroitUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {

		AyantDroitVO ayvo = (AyantDroitVO) entite;
		AyantDroit ayantDroit = (AyantDroit) this.getItem(AyantDroit.class);
		AyantDroit ayResult = null;
		Sinistre sinResult = null;
		try {
			if (ayantDroit.getId() == 0) {
				throw new FonctionnelleException("EXP_ID_AY_REQUIRED");
			}
			if (StringUtils.isEmpty(ayantDroit.getNom())) {
				throw new FonctionnelleException("EXP_NOM_AY_REQUIRED");
			}
			if (StringUtils.isEmpty(ayantDroit.getPrenom())) {
				throw new FonctionnelleException("EXP_PRENOM_AY_REQUIRED");
			}
			sinResult = sinistreManager.getSinistreByNum(ayvo
					.getNumeroSinistre());
			if (sinResult == null) {
				throw new FonctionnelleException("EXP_SINISTRE_INEXISTANT");
			}
			ayResult = (AyantDroit) sinistreManager
					.modifierAyantDroit(ayantDroit);
			// mvt=(Mouvement)sinistreManager.getMvtByIdSinistre(sinResult);//Le
			// dernier mouvement correspond à ce sinistre
			// if(mvt!=null){

			// if(sinistreManager.isMvt(mvt,sinResult)){
			if (sinResult.getRefVictime() != null) {
				Evenement even = sinResult.getRefEvenement();
				if (even.getDateAccident() != null) {
					sinResult.setDateCalculReserve(even.getDateAccident());
				}
				sinistreManager.creerMouvementAY(sinResult , IConstantes.MOTIF_MODIFICATION_SINISTRE);
			}

			// }else{
			// mvtResult=(Mouvement)sinistreManager.creerMouvementAY(sinResult);
			// }
			// }
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		addResultItem(ayResult);

	}

	public boolean isTransactionnal() {
		return true;
	}

}
