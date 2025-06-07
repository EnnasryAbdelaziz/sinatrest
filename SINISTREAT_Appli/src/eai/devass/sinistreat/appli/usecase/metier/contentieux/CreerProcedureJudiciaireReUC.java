package eai.devass.sinistreat.appli.usecase.metier.contentieux;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.contentieux.ProcedureJudiciaire;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.parametrage.EmailIntermediaire;
import eai.devass.sinistreat.appli.modele.parametrage.GestionnaireRelance;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.ProcedureJudiciaireVO;

public class CreerProcedureJudiciaireReUC extends BaseUC {

	public void executerUC(IValueObject entite, HashMap params) throws Exception {
		ProcedureJudiciaireVO pRvo = (ProcedureJudiciaireVO) (entite);
		
		 ObjectMapper mapper = new ObjectMapper();

         String json = mapper.writeValueAsString(pRvo);
         System.out.println("ResultingJSONstring = " + json);
		   logger.info("Appel methode recherche sinistre -> Fin");
		
		
		
		
		ProcedureJudiciaire proc = (ProcedureJudiciaire) this.getItem(ProcedureJudiciaire.class);
		Object procedureResult = null;
		try {
			// ne pas permettre la création d'une procédure judiciare avec
			// le même numéro dossier tribunal dans le même dossier sinistre
			Boolean trouver = contentieuxManager.validerDoubleProcedure(proc);
			if (trouver) {
				throw new FonctionnelleException(EXP_PROCEDURE_JUDICIAIRE_EXISTANTE);
			}
			procedureResult = contentieuxManager.creerProcedureJudiciaire(pRvo, proc);
			sinistreManager.creerMovementProcedureModification(proc);
			// Evolution zone a risque AT
			Sinistre sin = sinistreManager
					.getSinistreAtById(String.valueOf(proc.getRefRecours().getRefSinistre().getId()));
			if(sin.getTypeDeclaration() != null)
			{
			if (("1".equals(sin.getTypeDeclaration().getCode())) || ("2".equals(sin.getTypeDeclaration().getCode())
					&& "1".equals(sin.getSourceDeclaration().getCode()))) {
				List<String> ccs = new ArrayList<String>();
				List<String> tos = new ArrayList<String>();
				EmailIntermediaire emailInterm = parametrageManager.getEmailIntermediaire(sin.getCodeIntermediaire());
				if (proc.getRefGestionnaire() != null && proc.getRefGestionnaire().getCode() != null) {

					GestionnaireRelance mail1 = parametrageManager
							.getGestionnaireRelance(proc.getRefGestionnaire().getCode());
					String cc2 = mail1.getMail();
					if ("".equals(cc2) || cc2 == null) {
						logger.info("Email Gestionnaire non trouvé ");
					} else {
						ccs.add(cc2);
					}
				}
				
				String cc3 = "m.faddoul@rmaassurance.com";
				if(emailInterm != null) {
				String to = emailInterm.getEmail();
				ccs.add(cc3);				
				if (to != null) {
					String[] TOS = to.split(",");
					for (String s : TOS) {
						tos.add(s);
					}
				}
				if ("".equals(to)) {
					logger.info("Message non envoyé : Email Intermediaire non trouvé ");
				}}
				else {
					String to = "m.faddoul@rmaassurance.com";
					tos.add(to);
				}

				LocalDate dateObj = LocalDate.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				String date = dateObj.format(formatter);
				SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");

				String subject = null;
				String text = null;

				subject = "Nouvelle procédure N° : " + proc.getNumeroDossierTribunal() + " AT du "
						+ sm.format(sin.getRefEvenement().getDateAccident());
				text = "Bonjour, <br/> Une nouvelle procédure a été créée : <br/><br/>";
				text = text + "N/Ref : " + sin.getNumeroSinistre() + " X " + sin.getNumeroGrave() + "<br/>";
				text = text + "V/Ref : " + sin.getRefIntermediaire() + "<br/>";
				text = text + "Date Accident : " + sm.format(sin.getRefEvenement().getDateAccident()) + "<br/>";
				text = text + "Police : " + sin.getNumeroPolice() + " " + sin.getNomClient() + "<br/> Victime : "
						+ sin.getRefVictime().getNomprenom() + "<br/> " + " Ipp : " + sin.getIpp() + "<br/> "
						+ " Procédure N° :      <br/>" + proc.getNumeroDossierTribunal() + "<br/> " + " Juridiction  : "
						+ proc.getRefJuridiction().getLibelle() + "<br/> " + "Casablanca, le  " + date
						+ " <br/>";

				//mailUtils.sendMail(tos, ccs, subject, text);
			}
			if ("2".equals(sin.getTypeDeclaration().getCode()) && !"1".equals(sin.getSourceDeclaration().getCode())) {
				List<String> ccs1 = new ArrayList<String>();
				List<String> tos1 = new ArrayList<String>();
				EmailIntermediaire emailIntermed = parametrageManager.getEmailIntermediaire(sin.getCodeIntermediaire());
				if (proc.getRefGestionnaire() != null && proc.getRefGestionnaire().getCode() != null ) {
					GestionnaireRelance mail = parametrageManager
							.getGestionnaireRelance(proc.getRefGestionnaire().getCode());
					String cc1 = mail.getMail();

					if ("".equals(cc1) || cc1 == null) {
						logger.info("Email Gestionnaire non trouvé ");
					} else {
						ccs1.add(cc1);
					}
				}
				if (emailIntermed != null ) {
				String to = emailIntermed.getEmail();				
				if (to != null) {
					String[] TOS = to.split(",");
					for (String s : TOS) {
						tos1.add(s);
					}
				}
				if ("".equals(to)) {
					logger.info("Message non envoyé : Email Intermediaire non trouvé ");
				}}
				else {
					String to = "m.faddoul@rmaassurance.com";
					tos1.add(to);
				}
				String cc2 = "m.faddoul@rmaassurance.com";
				ccs1.add(cc2);

				LocalDate dateObj = LocalDate.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				String date = dateObj.format(formatter);
				SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");
				String subject1 = null;
				String text1 = null;
				subject1 = "Nouvelle procédure N° : " + proc.getNumeroDossierTribunal() + " AT du "
						+ sm.format(sin.getRefEvenement().getDateAccident());
				text1 = "Bonjour, <br/> Une nouvelle procédure a été créée : <br/><br/>";
				text1 = text1 + "N/Ref : " + sin.getNumeroSinistre() + " X " + sin.getNumeroGrave() + "<br/>";
				text1 = text1 + "V/Ref : " + sin.getRefIntermediaire() + "<br/>";
				text1 = text1 + "Date Accident : " + sm.format(sin.getRefEvenement().getDateAccident()) + "<br/>";
				text1 = text1+ "Police : " + sin.getNumeroPolice() + " " + sin.getNomClient() + "<br/> Victime : "
						+ sin.getRefVictime().getNomprenom() + "<br/> " + " Ipp : " + sin.getIpp() + "<br/> "
						+ " Procédure N° :      <br/>" + proc.getNumeroDossierTribunal() + "<br/> " + " Juridiction  : "
						+ proc.getRefJuridiction().getLibelle() + "<br/> " + "Casablanca, le  " + date
						+ " <br/>";
				//mailUtils.sendMail(tos1, ccs1, subject1, text1);
			}
		}
			// Fin evol
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		addResultItem(procedureResult);
	}

	public boolean isTransactionnal() {
		return true;
	}

}
