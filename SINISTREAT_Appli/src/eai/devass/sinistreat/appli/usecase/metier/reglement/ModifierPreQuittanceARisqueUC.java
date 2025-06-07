package eai.devass.sinistreat.appli.usecase.metier.reglement;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.reglement.DetailReglement;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.modele.metier.reglement.ReglementPieceAt;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.parametrage.EmailIntermediaire;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.IConstantes;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class ModifierPreQuittanceARisqueUC extends BaseUC {

	public ModifierPreQuittanceARisqueUC() {
	}

	@SuppressWarnings("rawtypes")
	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		logger.info("Debut UC ModifierPreQuittanceARisqueUC");

		try {
			List listRgl = getItems(Reglement.class);
			Reglement rgl = (Reglement) listRgl.get(0);
			if (listRgl != null && !listRgl.isEmpty()) {
				boolean envoyerEmail = false;
				List<String> ListOfPieces = new ArrayList<String>();
				StringBuilder builder = new StringBuilder();
				if ("C".equals(rgl.getActionPreQuitt())) {
					reglementManager.updateAttenteComplement(rgl);
					reglementManager.addHistoriqueEtat(rgl, IConstantes.MOTIF_COMPLEMENT_REGLEMENT);
					envoyerEmail = true;
				}
				if ("V".equals(rgl.getActionPreQuitt())) {
					reglementManager.validateEtatReglement(rgl);
					reglementManager.addHistoriqueEtat(rgl, IConstantes.MOTIF_REGLER_PREQUITTANCE);
				}
				if ("S".equals(rgl.getActionPreQuitt())) {
					reglementManager.updateNePasRegler(rgl);
					reglementManager.addHistoriqueEtat(rgl, IConstantes.MOTIF_NEPASREGLER_REGLEMENT);
				}
				if (envoyerEmail) {
					// La liste des pieces
					for (ReglementPieceAt rp : rgl.getReglementPieceAt()) {
						if (rp.getCocher() == Boolean.TRUE) {
							rp.getPieceReglement().getLibelle();
							String pieceValue = parametrageManager
									.getLibellePieceReglementByCode(rp.getPieceReglement().getCode());
							ListOfPieces.add(pieceValue);
						}
					}
					for (String value : ListOfPieces) {
						builder.append("<br/> - " + value);
					}
					List<String> ccs = new ArrayList<String>();
					List<String> tos = new ArrayList<String>();
					EmailIntermediaire emailInterm = parametrageManager
							.getEmailIntermediaire(rgl.getCodeIntermediaire());
					String cc3 = "m.faddoul@rmaassurance.com";
					String to = emailInterm.getEmail(); // 19082022

					if (to != null) {
						String[] TOS = to.split(",");
						for (String s : TOS) {
							tos.add(s);
						}
					}
					if ("".equals(to)) {
						logger.info("Message non envoyé : Email Intermediaire non trouvé ");
					}
					if ("".equals(cc3) || cc3 == null) {
						logger.info("Email non trouvé ");
					 } else {
					 ccs.add(cc3);
					 }
					LocalDate dateObj = LocalDate.now();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					String date = dateObj.format(formatter);
					String subject = null;
					String text = null;
					SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");
					Sinistre sin = sinistreManager.getSinistreAtById(String.valueOf(rgl.getRefSinistre().getId()));

					subject = "Règlement quittance " + rgl.getNumeroQuittance() + " AT du "
							+ sm.format(sin.getRefEvenement().getDateAccident());

					text = " <b> N/Ref : </b> " + sin.getNumeroSinistre() + " X " + sin.getNumeroGrave() + "<br/>";
					text = text + "<b> V/Ref : </b> " + sin.getRefIntermediaire() + "<br/>";
					text = text + " <b> Police : </b> " + sin.getNumeroPolice() + " " + sin.getNomClient() + "<br/> "
							+ " <b>Victime :</b> " + sin.getRefVictime().getNomprenom() + "<br/> "
							+ "Pour compléter votre dossier, prière de nous adresser : \r\n" + builder.toString()
							+ " \r\n" + "<br/><br/><br/>";

					if(rgl.getListDetailReglement() != null) {
							text= text + "<table width=\"100%\"  cellpadding=\"1\" cellspacing=\"1\" border=\"1\" style=\"border-collapse: collapse; border: 1px solid #55ff99;\">"
							+ "<tr><th align=\"center\"> Nom Assuré</th>"
							+ "<th align=\"center\"> Date Accident </th><th align=\"center\">Date début d'ITT</th><th align=\"center\">Date de fin d'ITT</th><th align=\"center\">Prestation</th><th>Montant</th></tr>";
									
					for(DetailReglement dr : rgl.getListDetailReglement() ) {
						
						text = text + "<tr><td align=\"center\">" + rgl.getRefSinistre().getNomClient()
								+ "</td> <td align=\"center\">" + sm.format(sin.getRefEvenement().getDateAccident())
								+ "</td> <td align=\"center\">" + sm.format(dr.getDateDebutPrestation())
								+ "</td> <td align=\"center\">" + sm.format(dr.getDateFinPrestation())
								+ "</td> <td align=\"center\">" + dr.getLibellePrestation() + "</td> <td align=\"center\">"
								+ dr.getMontant() + "</tr> ";

					}

					
					text = text + "</table>";
					}
						text =text	+ " <br/>" + "Casablanca, le  " + date
							+ " <br/>";
					mailUtils.sendMail(tos, ccs, subject, text);

				}

			}

			logger.info("Fin UC ModifierPreQuittanceARisqueUC");
		}

		catch (Exception e) {
			throw new FonctionnelleException(e.getMessage(), new String[0]);
		}

	}

	public boolean isTransactionnal() {
		return true;
	}

}
