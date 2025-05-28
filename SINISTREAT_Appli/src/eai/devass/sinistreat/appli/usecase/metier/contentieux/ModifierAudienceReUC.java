//package eai.devass.sinistreat.appli.usecase.metier.contentieux;
//
//import java.util.HashMap;
//
//import ma.co.omnidata.framework.services.businessInterface.IValueObject;
//
//import org.hibernate.exception.ConstraintViolationException;
//
//import eai.devass.sinistreat.appli.exception.FonctionnelleException;
//import eai.devass.sinistreat.appli.modele.metier.contentieux.AudienceJugement;
//import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
//import eai.devass.sinistreat.appli.usecase.BaseUC;
//import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.AudienceJugementVO;
//import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;
//
//public class ModifierAudienceReUC extends BaseUC {
//	boolean isUpdate;
//
//	protected void executerUC(IValueObject entite, HashMap params)
//			throws Exception {
//		AudienceJugement audience=(AudienceJugement)this.getItem(AudienceJugement.class);
//		AudienceJugementVO audienceVO = (AudienceJugementVO) (entite);
//
//		AudienceJugementVO audienceResult = null;
//		try {
//			Sinistre sinistre = sinistreManager.getSinistreById(Long
//					.parseLong(audienceVO.getIdSinistre()));
//			sinistre.setReserveGrave(Double.valueOf(audienceVO.getReserveGrave()));
//			sinistre.setReserveOrdinaire(Double.valueOf(audienceVO.getReserveOrdinaire()));
//			sinistre.setReserveProthese(Double.valueOf(audienceVO.getReserveProthese()));
//			sinistreManager.modifierSinistreM(sinistre);
//			audienceResult = (AudienceJugementVO) contentieuxManager
//					.modifierAudience(audienceVO,audience);
//		} catch (ConstraintViolationException e) {
//			throw new FonctionnelleException(e);
//		} catch (Exception e) {
//			throw new FonctionnelleException(e.getMessage());
//		}
//
//		addResultItem(audienceResult);
//
//	}
//
//	public boolean isTransactionnal() {
//		return true;
//	}
//
//}

package eai.devass.sinistreat.appli.usecase.metier.contentieux;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Conciliation;
import eai.devass.sinistreat.appli.modele.metier.contentieux.AudienceJugement;
import eai.devass.sinistreat.appli.modele.metier.contentieux.ProcedureJudiciaire;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.parametrage.RelanceConciliationPiece;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.entites.IDate;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.AudienceJugementVO;

public class ModifierAudienceReUC extends BaseUC {
	private final SimpleDateFormat dateFormat = new SimpleDateFormat(
			IDate.FORMAT_SIMPLE);
	protected void executerUC(IValueObject entite, HashMap params)
	throws Exception {
			
		
		AudienceJugementVO audienceVO = (AudienceJugementVO)(entite);
		AudienceJugement audienceResult = null;
		AudienceJugement audience = (AudienceJugement) this.getItem(AudienceJugement.class);
		ProcedureJudiciaire refProcedureJudiciaire = null;
		
		
		try {
			audienceResult = (AudienceJugement) contentieuxManager.modifierAudience(audienceVO,audience);
			refProcedureJudiciaire = sinistreManager.creerMovementAudianceCreation(audience);
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}
		
		audienceResult.setRefProcedureJudiciaire(refProcedureJudiciaire);
		addResultItem(audienceResult);
	
		if ( audienceResult.getRefCieCondamnee() != null && audienceResult.getRefCieCondamnee().getCode() != null)
		{
			if("1".equals(audienceResult.getRefCieCondamnee().getCode())){
			Sinistre sinistre = audienceResult.getRefProcedureJudiciaire().getRefRecours().getRefSinistre();
			try {
				
				List<String> tos = new ArrayList<String>();
				List<String> ccs = new ArrayList<String>();
				// Get email Intermediaire
				//String to = "R.BADAOUI@rmaassurance.com";
				String to = "BELAID@rmaassurance.com";
				String to2 = "M.Hamama@rmaassurance.com";
				// Get email Gestionnaire connecté
				//String cc ="R.BADAOUI@rmaassurance.com";
				String cc = "A.AJIG@rmaassurance.com";
				String cc2 = "s.najiz@rmaassurance.com";

				if ("".equals(to)) {
					logger.info("Message non envoyé : Email Intermediaire non trouvé ");
				}
				String subject = "";
			
			if("1".equals(audienceResult.getRefTypeRente().getCode()))
		    {
				subject ="Note ouverture rente "+sinistre.getNumeroSinistre()+ " X "+ sinistre.getNumeroGrave()+ " "+ sinistre.getRefVictime().getNomprenom() + " c/"
						+ sinistre.getNomClient();
		    }
			
			if("2".equals(audienceResult.getRefTypeRente().getCode()))
		    {
				subject ="Note Aggravation/Révision à la hausse rente "+sinistre.getNumeroSinistre()+ " X "+ sinistre.getNumeroGrave()+ " "+ sinistre.getRefVictime().getNomprenom() + " c/"
						+ sinistre.getNomClient();
		    }
			
			if("3".equals(audienceResult.getRefTypeRente().getCode()))
		    {
				subject ="Note Atténuation/Révision à la baisse "+sinistre.getNumeroSinistre()+ " X "+ sinistre.getNumeroGrave()+ " "+ sinistre.getRefVictime().getNomprenom() + " c/"
						+ sinistre.getNomClient();
		    }
			
		    String text = "Ref : "+ sinistre.getNumeroSinistre()+ " X "+ sinistre.getNumeroGrave()+"<br/>";
		    text = text +"Date AT : "+dateFormat.format(sinistre.getRefEvenement().getDateAccident())+"<br/>"+
		    "Victime : "+sinistre.getNomClient()+" "+ sinistre.getRefVictime().getNomprenom()+"<br/>"+
		    "Juridiction : "+audienceResult.getRefProcedureJudiciaire().getRefJuridiction().getLibelle()+"<br/>"+
			" N Procédure : "+audienceResult.getRefProcedureJudiciaire().getNumeroDossierTribunal()+"<br/>"+
		    "Date décision : "+dateFormat.format(audienceResult.getDateDecision())+"<br/><br/>"+
		    "<div align='justify'>Bonjour,<br/><br/>";
		    ////
		    if("1".equals(audienceResult.getRefTypeRente().getCode())){
		    	
		    	text =text+ "Nous prenons votre attache au sujet du dossier visé en marge pour vous demander de procéder à l’ouverture d’un dossier rente au titre de cette affaire :<br/><ul> ";
		    	
		    			text = text + "<li> Montant rente annuelle : "+ audienceResult.getMontantRente()+"</li>";
		    			text = text + "<li> Quote-part : "+ audienceResult.getQuotePart()+"</li>";
		    			text = "</ul>" +text;
		    			text = text +"</div>";
				
		    }
		    
		    if("2".equals(audienceResult.getRefTypeRente().getCode())){
		    	
		    	text =text+ "Nous prenons votre attache au sujet du dossier visé pour vous demander de procéder à la mise à jour de la rente au titre de cette affaire :<br/><ul> ";
		    	
		    			text = text + "<li> Montant rente annuelle : "+ audienceResult.getMontantRente()+"</li>";
		    			text = text + "<li> Quote-part : "+ audienceResult.getQuotePart()+"</li>";
		    			text = "</ul>" +text;
		    	text = text + "</div>Il y a lieu de procéder au règlement du complément arrérages depuis la date de constitution de ladite rente.<br/><br/>";
				
		    }
		    
		    if("3".equals(audienceResult.getRefTypeRente().getCode())){
		    	
		    	text =text+ "Nous prenons votre attache au sujet du dossier visé pour vous demander de procéder à la mise à jour de la rente au titre de cette affaire :<br/><ul> ";
		    	
		    			text = text + "<li> Montant rente annuelle : "+ audienceResult.getMontantRente()+"</li>";
		    			text = text + "<li> Quote-part : "+ audienceResult.getQuotePart()+"</li>";
		    			text = "</ul>" +text;
		    	text = text + "</div>Il y a lieu de déterminer le montant du trop-perçu en vue d’assigner le rentier.<br/><br/>";
				
		    }
			
		    text = text +"Cordialement,<br/><br/>";
		   // +"Avertissement : Cet e-mail est généré de façon automatique, nous vous remercions de ne pas utiliser l'adresse d'origine de ce mail pour nous contacter.";
		    if(to != null ) {
		    	tos.add(to);
		    }
		    if(to2 != null ) {
		    	tos.add(to2);
		    }
		    if(cc != null ) {
		    	ccs.add(cc);
		    }
		    if(cc2 != null ) {
		    	ccs.add(cc2);
		    }
		    mailUtils.sendMail(tos, ccs, subject, text);
		    }catch(Exception e){
		    	logger.error("Message non envoyé", e);			    	
		    }
		}
		}
	
	}
	
	

	public boolean isTransactionnal() {
		return true;
	}
}

