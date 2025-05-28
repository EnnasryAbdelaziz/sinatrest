package eai.devass.sinistreat.appli.usecase.metier.reglement;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.businessrule.SinistreRG;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.reglement.DetailReglement;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.utils.Message.IMessage;
import eai.devass.sinistreat.appli.utils.entites.IParam;

public class ValiderReglementAvtCreationUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		List<Reglement> listRgl = this.getItems(Reglement.class);
		logger.info("Debut UC ValiderReglementAvtCreationUC");
		if (listRgl != null) {
			for (Reglement reglement : listRgl) {
				boolean bloquer = false;
				List<String> messages = new ArrayList<String>();
				if (reglement.getId() == null) {
					logger.info("Debut Method validerDoubleReglement");
					String numQuittance = reglementManager
							.validerDoubleReglement(reglement);
					logger.info("Fin Method validerDoubleReglement");
					if (numQuittance != null && !numQuittance.equals("0")) {
						messages.add(IMessage.MESSAGE_REGLEMENT_1
								+ numQuittance + ".");
					}
				}
				
                SinistreRG sinitreRG = new SinistreRG(
                        reglement.getRefSinistre());
				sinitreRG.setSinistreDataBaseByNum();
				sinitreRG.verifierMiseAZeroReserves();
				
				if (reglement.getDateNote() != null
						&& reglement.getRefSinistre() != null
						&& reglement.getRefSinistre().getRefEvenement() != null
						&& reglement.getRefSinistre().getRefEvenement()
								.getDateAccident() != null) {
					if (reglement.getDateNote().before(
							reglement.getRefSinistre().getRefEvenement()
									.getDateAccident())) {
						throw new FonctionnelleException(
								EXP_DATEACC_BEFORE_DATENOTE);
					}
				}
				if (reglement.getDateReglement() != null) {
					if (reglement.getDateReglement().after(new Date())) {
						throw new FonctionnelleException(
								EXP_DATEREG_BEFORE_DATEJOUR);
					}
				}
				if (reglement.getDateEtablissement() != null) {
					if (reglement.getDateEtablissement().after(new Date())) {
						throw new FonctionnelleException(
								EXP_DATEETAB_BEFORE_DATEJOUR);
					}
					if (reglement.getDateReglement().before(
							reglement.getDateEtablissement())) {
						throw new FonctionnelleException(
								EXP_DATEREG_BEFORE_DATEETAB);
					}

				}
				if (reglement.getDateReglement() != null) {
					if (reglement.getDateReglement().after(new Date())) {
						throw new FonctionnelleException(EXP_DATE_REGLEMENT);
					}
				}

				if (!reglementManager.validerMontantReglement(reglement)) {
					messages.add(IMessage.MESSAGE_REGLEMENT_4
							+ IParam.MONTANTMAXREGLEMENT + " Dhs.");
				}
				if (reglement.getListDetailReglement() == null
						|| reglement.getListDetailReglement().isEmpty()) {
					messages.add(IMessage.MESSAGE_REGLEMENT_5);
					bloquer = true;
				} else if (!reglementManager
						.validerMontantReserveReglement(reglement)) {
					messages.add(IMessage.MESSAGE_REGLEMENT_6);
					bloquer = true;
				}
				if (reglement.getRefTypeReglement() != null
						&& reglement
								.getRefTypeReglement()
								.getCode()
								.equals(IConstantes.TYPE_REGLEMENT_INTERMEDIAIRE)) {
					if (!reglementManager.validerDateEtablissement(reglement)) {
						messages.add(IMessage.MESSAGE_REGLEMENT_8);
						bloquer = true;
					}

				}
				if (reglement.getRefTypeReglement() != null
						&& (reglement.getRefTypeReglement().getCode()
								.equals(TYPE_REGLEMENT_DIRECT) || reglement
								.getRefTypeReglement().getCode()
								.equals(TYPE_REGLEMENT_INTERMEDIAIRE))
						&& !reglementManager.validerBeneficiaire(reglement)) {
					bloquer = false;
				}
				if (reglement.getRefTypeReglement() != null
						&& !reglement.getRefTypeReglement().getCode()
								.equals("6")) {
					Sinistre sin = reglement.getRefSinistre();
					sin.setEtatCible(sin.getEtatCible());
					String codetatcible = sin.getEtatCible();
					if (codetatcible == null) {
						codetatcible = sin.getRefEtatSinistre().getRefEtat()
								.getCode();
					}
					sin.setEtatCible(codetatcible);

				}
				if (reglement.getRefTypeReglement() != null
						&& (reglement.getRefTypeReglement().getCode()
								.equals(TYPE_REGLEMENT_AUXILIAIRE))) {
					// code auxiliaire
					// ref auxiliaire (meme si la ref est vide)
					// montant de la quittance (montant controle = montant rubrique – montant rejete)
					// letat  différent de « Annule »
					
					// getReglement : if not null add message with qtc and 
					Reglement rglAux = reglementManager.getReglementEnDouble(reglement);
					if(rglAux != null && rglAux.getId() != null){
						messages.add(IMessage.MESSAGE_REGLEMENT_10 + rglAux.getNumeroQuittance());
						bloquer = false;
					}
					
					//Verifier si un reglement auxiliaire comporte une rubrique TVA  06/2024
					if( reglement.getListDetailReglement() != null && !reglement.getListDetailReglement().isEmpty()) {
						boolean contientRasRub = false;
						boolean contientHonRub = false;
						String ras ="0";
						List<DetailReglement>  listDet = reglement.getListDetailReglement();
						for(DetailReglement d : listDet) {
							if(d.getCodePrestation().equals("83")) {
								contientRasRub = true;
							}
							if(d.getCodePrestation().equals("25")) {
								contientHonRub= true;
							}
						}

						ras = reglementManager.getRasAuxilliaireByCode(reglement.getCodeAuxiliaire());
						if(ras != null && ras.equals("1") && !contientRasRub && contientHonRub) {
								messages.add(IMessage.MESSAGE_REGLEMENT_11 + reglement.getCodeAuxiliaire());
								bloquer = true;
								}
						if(ras != null && ras.equals("0") && contientRasRub) {
							messages.add(IMessage.MESSAGE_REGLEMENT_12 + reglement.getCodeAuxiliaire());
							bloquer = true;
							    }
					}
					
					//Fin verifcation
					
				}
				this.addResultItem(messages);
				this.addResultItem(bloquer);
				logger.info("Fin UC ValiderReglementAvtCreationUC");
			}
		}
	}

	public boolean isTransactionnal() {
		return false;
	}

}
