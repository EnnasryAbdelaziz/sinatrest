package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Evenement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Victime;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;

public class CreateSinistreRepriseUCConverter extends ValueObjectConverterAbst
		implements IMessageException {

	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	public Object doConvertItemsToValueObject(List listeItems) {

		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}

		SinistreVO sinvo = new SinistreVO();
		try {
			Sinistre sin = (Sinistre) listeItems.get(0);
			sinvo = (SinistreVO) converterTools
					.convertToObjectVOWithoutList(sin);

		} catch (Exception e) {
			logger.error("problème technique",e);
		}

		return sinvo;
	}

	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {

		List listOut = new ArrayList();
		SinistreVO sinvo = (SinistreVO) vo;
		try {

			Sinistre sin = (Sinistre) converterTools.convertToObjectBO(sinvo);
			Evenement even = sin.getRefEvenement();
			Victime vic = (Victime) sin.getRefVictime();
			if (sin.getRefTypeGarantie() == null
					|| sin.getRefTypeGarantie().getCode() == null) {
				throw new FonctionnelleException(EXP_GARANTIE_OBLIGATOIRE);
			}
			if (sin.getIpp().compareTo(Double.valueOf(100)) > 0
					&& vic.getDeces() == false) {
				throw new FonctionnelleException(EXP_IPP_SUPERIEUR_100);
			}
			if (even != null && vic != null) {
				if (StringUtils.isEmpty(sin.getNumeroPolice())) {
					throw new FonctionnelleException(EXP_POLICE_OBLIGATOIRE);
				}
				if (StringUtils.isEmpty(vic.getNom())) {
					throw new FonctionnelleException(EXP_NOM_OBLIGATOIRE);
				}
				if(even.getDateAccident()== null){
					throw new FonctionnelleException(EXP_DATE_ACCIDENT);
				}
				if (vic.getDateNaissance() != null && vic.getAge(even.getDateAccident()) < IConstantes.AGE_MIN) {
					throw new FonctionnelleException(EXP_AGE_MINIMUM_12);
				}
//				if (vic.getDateNaissance() != null && vic.getAge(even.getDateAccident()) > IConstantes.AGE_MAX) {
//					throw new FonctionnelleException(EXP_AGE_MAXIMUM_65);
//				}
				if (!before(vic.getDateNaissance(), vic.getDateDeces())) {
					throw new FonctionnelleException(
							EXP_DATENAIS_BEFORE_DATEDEC);
				}
				if (!before(even.getDateAccident(), vic.getDateDeces())) {
					throw new FonctionnelleException(EXP_DATEACC_BEFORE_DATEDEC);
				}
				if (!before(vic.getDateEmbauche(), vic.getDateDeces())) {
					throw new FonctionnelleException(EXP_DATEEMB_BEFORE_DATEDEC);
				}
				if (!before(vic.getDateNaissance(), even.getDateAccident())) {
					throw new FonctionnelleException(
							EXP_DATENAIS_BEFORE_DATEACC);
				}
				if (!before(vic.getDateEmbauche(), even.getDateAccident())) {
					throw new FonctionnelleException(EXP_DATEEMB_BEFORE_DATEACC);
				}
				if (!before(vic.getDateNaissance(), vic.getDateEmbauche())) {
					throw new FonctionnelleException(
							EXP_DATENAIS_BEFORE_DATEEMB);
				}
				if (!before(vic.getDateEmbauche(), new Date())) {
					throw new FonctionnelleException(EXP_DATEEMB_BEFORE_DATESYS);
				}
				if (!before(vic.getDateNaissance(), new Date())) {
					throw new FonctionnelleException(
							EXP_DATENAIS_BEFORE_DATESYS);
				}
				if (!before(even.getDateAccident(), new Date())) {
					throw new FonctionnelleException(EXP_DATEACC_BEFORE_DATESYS);
				}
				if (!before(vic.getDateDeces(), new Date())) {
					throw new FonctionnelleException(EXP_DATEDEC_BEFORE_DATESYS);
				}
				if (vic.getDeces() == false) {
					vic.setDateDeces(null);
				} else if (vic.getDeces() == true) {
					sin.setIpp(null);
					sin.setItt(null);
				}
			}
			sin.setId(null);
			if (even != null) {
				even.setId(null);
			}
			if (vic != null) {
				vic.setId(null);
				if (StringUtils.isEmpty(vic.getNationalite())) {
					vic.setNationalite("1");
				}
			}
			listOut.add(sin);

		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}

	private boolean before(Date debut, Date fin) {
		if (debut == null || fin == null) {
			return true;
		}

		if (debut.before(fin) || debut.compareTo(fin) == 0) {
			return true;
		} else {
			return false;
		}

	}

}
