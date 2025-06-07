package eai.devass.sinistreat.appli.businessrule;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.text.StyledEditorKit.BoldAction;

import ma.co.omnidata.framework.services.businessInterface.IResult;
import ma.co.omnidata.framework.services.businessInterface.impl.InfoMessageItem;
import ma.co.omnidata.framework.services.businessInterface.impl.MessageItem;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.PersistenceException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rmawatanya.moyenpaiement.application.metier.services.ordonnoncement.pub.IServiceOrdonnoncement;
import com.rmawatanya.moyenpaiement.application.metier.usecases.commun.pub.INatureQuittance.NatureQuittance;
import com.rmawatanya.moyenpaiement.application.metier.util.IConstants;
import com.rmawatanya.moyenpaiement.application.metier.valueobjects.OrdOrdonnoncementVO;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.services.IAppelService;
import eai.devass.services.ServicesExternes;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.manager.reglement.ReglementManager;
import eai.devass.sinistreat.appli.manager.sinistre.SinistreManager;
import eai.devass.sinistreat.appli.modele.metier.reglement.ChequePrime;
import eai.devass.sinistreat.appli.modele.metier.reglement.ChequeSinistre;
import eai.devass.sinistreat.appli.modele.metier.reglement.ChequeSinistreBGD;
import eai.devass.sinistreat.appli.modele.metier.reglement.CompensationSinistre;
import eai.devass.sinistreat.appli.modele.metier.reglement.DetailReglement;
import eai.devass.sinistreat.appli.modele.metier.reglement.Positionnement;
import eai.devass.sinistreat.appli.modele.metier.reglement.Positionnement.TypeDestinataire;
import eai.devass.sinistreat.appli.modele.metier.reglement.QuittanceMoyenPaiement;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.AyantDroit;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Victime;
import eai.devass.sinistreat.appli.modele.parametrage.Banque;
import eai.devass.sinistreat.appli.modele.parametrage.EtatRgl;
import eai.devass.sinistreat.appli.modele.parametrage.TypeQuittance;
import eai.devass.sinistreat.appli.modele.parametrage.TypeReglement;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.utils.entites.IDate;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;

@SuppressWarnings("all")
public class SinistreRG {

	private Sinistre sinistre;
	private Sinistre sinistreDB;
	private final Logger logger = Logger.getLogger("loggerGSR");
	private List<String> listErrors = new ArrayList<String>();
	private final SinistreManager sinistreManager = (SinistreManager) ServicesFactory.getService(SinistreManager.class);
	
	public SinistreRG(Sinistre sinistre) {
		this.sinistre = sinistre;
	}
	
	public void setSinistreDataBaseByNum() throws Exception {
		
		sinistreDB= sinistreManager.getSinistreByNum(sinistre.getNumeroSinistre());
	}
	
	
	
	  public boolean verifierSiRachatExiste() throws FonctionnelleException {
		  if(sinistreDB==null){
			  
			  if(sinistre.getListReglement()!=null)
			  for(Reglement r : sinistre.getListReglement()){
		        	  for( DetailReglement d : r.getListDetailReglement()){
		        		if("22".equals(d.getCodePrestation())){
		        		if("2".equals(r.getRefEtatReglement().getCode()) || "10".equals(r.getRefEtatReglement().getCode())){
		        			return true;  
		        		  }
		        	  }
		        	   }
		           }
			  else
				  return false ;
		         }
			  else {
				  if(sinistreDB.getListReglement()!=null)
		        	   for(Reglement r : sinistreDB.getListReglement()){
			        	  for( DetailReglement d : r.getListDetailReglement()){
			        		  if("22".equals(d.getCodePrestation())){
			        		  if("2".equals(r.getRefEtatReglement().getCode()) || "10".equals(r.getRefEtatReglement().getCode())){
				        			return true;  
				        		  }
			        		  }
			        	   }
		        	   }
				  else
					  return false ;
	  }
		  return false ;
		  
		}
	
	
	
	
	
	
	
	
	
     public boolean verifierNouvelleDateSurvPostAnt22012015() throws FonctionnelleException {
    	 String aux = "22/01/2015";
    	 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
 		Date dateChangement;
		try {
			dateChangement = dateFormat.parse(aux);
		} catch (ParseException e) {
			throw new FonctionnelleException("Erreur parsing0...");
		}
		if(sinistreDB==null){
			return false;
		}
		if(sinistreDB.getRefEvenement().getDateAccident().compareTo(dateChangement)>=0 && sinistre.getRefEvenement().getDateAccident().compareTo(dateChangement)<0  ||  
				sinistreDB.getRefEvenement().getDateAccident().compareTo(dateChangement)<0 && sinistre.getRefEvenement().getDateAccident().compareTo(dateChangement)>=0	)
		{
			if("1".equals(sinistreDB.getRefEtatSinistre().getRefEtat().getCode())){
		throw new FonctionnelleException("La date de survenance ne peut être modifiée avec une date Antérieur/Postérieur du 22/01/2015");
			}
			if(sinistreDB.getRefVictime().getDeces() &&  "0".equals(sinistreDB.getRefEtatSinistre().getRefEtat().getCode())){
				return true;
					}
		}
		return false;
	}
	
	
	
	//correction defect 49
	public void verifierMiseAZeroReserves() throws FonctionnelleException {
		
		if(sinistreDB==null){
			return ;
		}
		if("1".equals(sinistreDB.getRefEtatSinistre().getRefEtat().getCode())){
			if(!"3".equals(sinistre.getEtatCible())){
			if(sinistre.getReserveGrave()==0){
				if(sinistre.getReserveOrdinaire()!=null){
				if(sinistre.getReserveOrdinaire()==0){
					throw new FonctionnelleException("La résèrve ordinaire et la résèrve grave sont à Zéro !");
				}
				}
			}
		}
		}
	}
	

	
	public Sinistre getSinistreDB() {
		return sinistreDB;
	}


	public void setSinistreDB(Sinistre sinistreDB) {
		this.sinistreDB = sinistreDB;
	}


}
