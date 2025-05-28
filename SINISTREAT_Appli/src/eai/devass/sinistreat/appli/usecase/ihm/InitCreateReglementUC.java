package eai.devass.sinistreat.appli.usecase.ihm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.AyantDroit;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.parametrage.Palier;
import eai.devass.sinistreat.appli.modele.parametrage.TypeBeneficiaire;
import eai.devass.sinistreat.appli.modele.parametrage.TypeReglement;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.IConstantes;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class InitCreateReglementUC extends BaseUC {

	@Override
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		Reglement reglement = (Reglement) this.getItem(Reglement.class);
		try {
			
			TypeReglement typeReglement = reglement.getRefTypeReglement();
			if (typeReglement.getCode() != null
					&& typeReglement.getCode().equals(
							IConstantes.TYPE_REGLEMENT_BGD)) {
				List listBanque = (List) parametrageManager.getListBanque(null);
				this.addResultItem(listBanque);
			} else {
				//Liste destinataire
				List listDestinataire = (List) instructionManager.getListDestinataire();
				this.addResultItem(listDestinataire);
				//Liste destinataire Chèque
				List listDestinataireCheque = (List) reglementManager.getListDestinataireCheque();
				this.addResultItem(listDestinataireCheque);
				// Evol MAD 17/05/2021
				List listModeReglementDirect = (List) parametrageManager.getlistModeReglementDirect();
				this.addResultItem(listModeReglementDirect);
				List listPlafondMAD = (List) parametrageManager.getlistParamPlafondMAD();
				this.addResultItem(listPlafondMAD);
				
				List listTypeFrais = (List) reglementManager.getListTypeFrais();
				this.addResultItem(listTypeFrais);
				
				List listTypeFuneraire = (List) reglementManager.getListTypeFuneraire();
				this.addResultItem(listTypeFuneraire);
				
				// Etats sinistre
				List listEtatSinistre = (List) parametrageManager
						.getListEtatSinistreSansInstance(null);
				this.addResultItem(listEtatSinistre);
				// Sinistre
				Sinistre sin = (Sinistre) sinistreManager
						.getSinistreById(reglement.getRefSinistre().getId());
				this.addResultItem(sin);
				
				// Cumul quittance
				Reglement reg = new Reglement();
				reg.setRefSinistre(sin);
				double cumulQuittanceEmise = reglementManager
						.getCumulQuittanceEmise(reg);
				reg.setRefSinistre(null);
				reg.setMontant(cumulQuittanceEmise);
				Palier palier = parametrageManager.getPalier(sin.getRefEvenement()
						.getDateAccident());
				reg.setSalaireSmig(palier.getSalaireMinLeg()/(double)12);
				this.addResultItem(reg);

				List listeChoixRubrique = null;
				if (sin != null
						&& sin.getRefEtatSinistre() != null
						&& sin.getRefEtatSinistre().getRefEtat() != null
						&& sin.getRefEtatSinistre().getRefEtat().getCode()
								.equals(IConstantes.ETAT_SINISTRE_JUSTICE)) {
					listeChoixRubrique = (List) parametrageManager
							.getPresta(IConstantes.ETAT_SINISTRE_JUSTICE);
				} else {
					if (typeReglement.getCode().equals(
							IConstantes.TYPE_REGLEMENT_AUXILIAIRE)) {
						listeChoixRubrique = (List) parametrageManager
								.getlistPrestation(typeReglement);
					} else if (reglement.getModeReglement() != null
							&& !StringUtils.isEmpty(reglement
									.getModeReglement())) {
						if (typeReglement.getCode().equals(
								IConstantes.TYPE_REGLEMENT_INTERMEDIAIRE))
						{
							listeChoixRubrique = (List) parametrageManager
									.getlistPresta(reglement.getModeReglement());
						}
					} else {
						listeChoixRubrique = (List) parametrageManager
								.getlistPrestation(null);
					}
				}
				this.addResultItem(listeChoixRubrique);
				// ReglementDirect
				if (typeReglement.getCode().equals(
						IConstantes.TYPE_REGLEMENT_DIRECT)) {
					List listTypeBeneficiaire = null;
					if(sin != null){
					if (sin.getRefVictime() != null
							&& !sin.getRefVictime().getDeces()) {
						TypeBeneficiaire tb = new TypeBeneficiaire();
						tb.setCode(BENEFICIAIRE_AYANT_DROIT);
						listTypeBeneficiaire = (List) parametrageManager
								.getlistTypeBeneficiaire(tb);
					} else {
						listTypeBeneficiaire = (List) parametrageManager
								.getlistTypeBeneficiaire(null);
						List listAyDroit = (List) sinistreManager
								.getListAyantDroitByIdSinistre(sin.getId());
						listAyDroit = (List) ConcatListOfAyantDroit((List<AyantDroit>)listAyDroit);
						this.addResultItem(listAyDroit);
					}
					}
					this.addResultItem(listTypeBeneficiaire);
				} else if (typeReglement.getCode().equals(
						IConstantes.TYPE_REGLEMENT_INTERMEDIAIRE)) {
					List listBanque = (List) parametrageManager
							.getListBanque(null);
					this.addResultItem(listBanque);
					
					List listTypeBeneficiaire = null;
					if(sin != null){
					if (sin.getRefVictime() != null
							&& !sin.getRefVictime().getDeces()) {
						TypeBeneficiaire tb = new TypeBeneficiaire();
						tb.setCode(BENEFICIAIRE_AYANT_DROIT);
						listTypeBeneficiaire = (List) parametrageManager
								.getlistTypeBeneficiaire(tb);
					} else {
						listTypeBeneficiaire = (List) parametrageManager
								.getlistTypeBeneficiaire(null);
						List listAyDroit = (List) sinistreManager
								.getListAyantDroitByIdSinistre(sin.getId());
						listAyDroit = (List) ConcatListOfAyantDroit((List<AyantDroit>)listAyDroit);
						this.addResultItem(listAyDroit);
					}
					}
					this.addResultItem(listTypeBeneficiaire);
				}
			}
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);

		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

	}

	protected List<AyantDroit> ConcatListOfAyantDroit(List<AyantDroit> listAyDroit) {
		List<AyantDroit> nomprenomconcatListOfAyantDroit = new ArrayList<AyantDroit>();
		for (AyantDroit aydroit : listAyDroit) {
			if(aydroit.getNomprenom() == null) {
				aydroit.setNomprenom(aydroit.getNom()+' '+aydroit.getPrenom());
			}
			nomprenomconcatListOfAyantDroit.add(aydroit);
		}
		return nomprenomconcatListOfAyantDroit;
		
	}
}
