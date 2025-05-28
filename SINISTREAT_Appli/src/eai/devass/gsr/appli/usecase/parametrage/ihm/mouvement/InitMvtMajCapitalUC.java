package eai.devass.gsr.appli.usecase.parametrage.ihm.mouvement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.core.ServicesFactory;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.gsr.appli.manager.metier.transverse.TransverseManager;
import eai.devass.gsr.appli.usecase.parametrage.ihm.InitAbstractMouvementUC;
import eai.devass.gsr.appli.valueobjects.parametrage.BeanDTOVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;

public class InitMvtMajCapitalUC extends InitAbstractMouvementUC {

	@Override
	public void executeUC(IValueObject entite, HashMap params) throws Exception {
		// Récupérer la class de l'objet BO correspondant à l'objet VO
		TransverseManager transverseManager = (TransverseManager) ServicesFactory
				.getService(TransverseManager.class);

		try {

			BeanDTOVO beanDTOVO = (BeanDTOVO) entite;
			String idRentier = beanDTOVO.getIdRentier();
			if (!commonUtils.isNumeric(idRentier)) {
				throw new ExceptionMetier("Le rentier ne peut être vide (null)");
			}

			// Recuperer la date de la dernier echeance regle
			Date calLastEcheance = transverseManager
					.getDateDerniereEcheanceReglee(Long.valueOf(idRentier));

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			if (calLastEcheance != null) {
				Date trimestre = commonGsrUtils
						.getDateFinCurrentTrimestre(calLastEcheance);
				paramToConverter.put(
						"dateDerniereEcheance",
						(trimestre == null) ? "" : dateFormat
								.format(calLastEcheance));
			}

		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);

		}

	}

	public boolean isTransactionnal() {
		return false;
	}

}