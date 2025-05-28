package eai.devass.sinistreat.appli.usecase.parametrage;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;


import org.apache.log4j.Logger;

import eai.devass.missionnement.valueobjects.parametrage.ActivitePrestataireVO;
import eai.devass.missionnement.valueobjects.parametrage.DomaineActiviteVO;
import eai.devass.missionnement.valueobjects.parametrage.PrestataireDetailVO;
import eai.devass.missionnement.valueobjects.parametrage.VilleVO;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.parametrage.ActivitePrestVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.DomainePrestVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.PrestataireVO;

@SuppressWarnings("all")
public class RecherchePrestataireUCConverter extends  ValueObjectConverterAbst implements IMessageException {

	protected Fonctions functions = new Fonctions();	
	//ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	public Object doConvertItemsToValueObject(List listeItems) {
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		
		List<PrestataireVO> listPrestaVO= new ArrayList<PrestataireVO>();
		try {
			List<PrestataireDetailVO> listPresta = (List<PrestataireDetailVO>)listeItems.get(0);
			
			int i = 0;
			PrestataireVO prestataireVO = null;
			for (PrestataireDetailVO curDetailVO : listPresta) {
				// if(++i <= 200) {
				prestataireVO = new PrestataireVO();
				prestataireVO.setAdresse(curDetailVO.getAdresse());
				prestataireVO.setCode(curDetailVO.getCode());
				// QC : 172 si personne physique raison social = nom + prenom
				// si personne morale raison social
				if (curDetailVO.getType()!= null && curDetailVO.getType().equals("P")) {
					prestataireVO.setNomRaisonSocial(curDetailVO.getTitre() +" "+ curDetailVO.getNom()
							+ " "  + curDetailVO.getPrenom());
				} else {
					prestataireVO.setNomRaisonSocial(curDetailVO
							.getNomRaisonSocial());
				}

				prestataireVO
						.setRefVille(new eai.devass.sinistreat.appli.valueobjects.parametrage.VilleVO(
								curDetailVO.getCodeVille(), curDetailVO
										.getLibelleVille()));
				prestataireVO.setRefActivite(new ActivitePrestVO(curDetailVO
						.getIdActivite(), curDetailVO.getLibelleActivite()));
				prestataireVO.setAdresse(curDetailVO.getAdresse());
				prestataireVO.setRefDomaineActivite(new DomainePrestVO(
						curDetailVO.getCodeDomaineActivite(), curDetailVO
								.getLibelleDomaineActivite()));
				prestataireVO.setMail(curDetailVO.getMail());
				prestataireVO.setFax(curDetailVO.getFax());
				prestataireVO.setTelephone(curDetailVO.getTelephone());
				prestataireVO.setMobile(curDetailVO.getGsm());
				prestataireVO.setRas(curDetailVO.getRas());
				listPrestaVO.add(prestataireVO);
				// } else {
				// break;
				// }
			}
			
			
			
			//listPrestaVO = (List)converterTools.convertToListObjectVO(listPresta);
		
		} catch (Exception e) {
			logger.error("problème technique",e);
		}

		return listPrestaVO;
	}

	
	public List doConvertValueObjectToItems(Object vo) throws ValidationUnitaireException, ValidationException {
		
		List listOut = new ArrayList();
		PrestataireVO prestaVO = (PrestataireVO) vo;
		try{
			// Convert to prestataire missionnement
			eai.devass.missionnement.valueobjects.parametrage.PrestataireVO prestataireVO = 
						new eai.devass.missionnement.valueobjects.parametrage.PrestataireVO();
			
			// Code activite
			String codeActivite = null;
			if(prestaVO.getRefActivite() != null) {
				if(!"0".equals(prestaVO.getRefActivite().getCode())) {
					codeActivite = prestaVO.getRefActivite().getCode();
					prestataireVO.setRefActivite(new ActivitePrestataireVO(codeActivite));
					
				} else if(prestaVO.getRefActivite().getRefDomaineActivite() != null 
						&& !"0".equals(prestaVO.getRefActivite().getRefDomaineActivite().getCode())) {
					codeActivite = prestaVO.getRefActivite().getRefDomaineActivite().getCode();
					ActivitePrestataireVO activitePrestataireVO = new ActivitePrestataireVO();
					activitePrestataireVO.setRefDomaineActivite(new DomaineActiviteVO(codeActivite));
					prestataireVO.setRefActivite(activitePrestataireVO);
				}
			} 
							
			// Code ville
			if(prestaVO.getRefVille() != null && prestaVO.getRefVille().getCode() != null) {
				VilleVO villeVO = new VilleVO();
				
				// Code ville = id | codeVille
				StringTokenizer tokenizer = new StringTokenizer(prestaVO.getRefVille().getCode(), "|");		
				tokenizer.nextToken();
				villeVO.setCode(tokenizer.nextToken());
				prestataireVO.setRefVille(villeVO);			
			}
			
			// Raison sociale
			if(prestaVO.getNomRaisonSocial() != null) {
				prestataireVO.setNomRaisonSocial(prestaVO.getNomRaisonSocial());
			}
			
			// nom Prestataire
			if(prestaVO.getNom() != null) {
				prestataireVO.setNom(prestaVO.getNom());
			}
			
			// prenom Prestataire
			if(prestaVO.getPrenom() != null) {
				prestataireVO.setPrenom(prestaVO.getPrenom());
			}
			
			if(prestaVO.getCode() != null) {
				prestataireVO.setCode(prestaVO.getCode());
			}
			
			//Prestataire presta=(Prestataire)converterTools.convertToObjectBO(prestaVO);
			listOut.add(prestataireVO);	
			
		} catch(Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}
						
		return listOut;
	}
	

}
