package eai.devass.sinistreat.web.action.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.valueobjects.metier.reglement.ReglementVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;
import ma.co.omnidata.framework.services.businessInterface.IResult;
import ma.co.omnidata.framework.services.businessInterface.OMNIFacade;
import ma.co.omnidata.framework.services.securite.impl.OMNIAction;
@Path("/Quittance")
public class QuittanceController {
	private static Logger logger = Logger.getLogger("loggerSINAT_QuittanceController");

	@Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
	public List<ReglementVO> chercherListeQuittance(ReglementVO vo ) {
		logger.info("Appel methode recherche quitance consult quitance-> Begin");
		//ReglementVO vo = new ReglementVO();
		ReglementVO quitancevo = new ReglementVO();
		List<ReglementVO> listNreg =  new ArrayList<ReglementVO>();
		
		try {
			//OMNIFacade facade=new OMNIFacade(); 
			OMNIAction action = new OMNIAction();
			OMNIFacade facade = new OMNIFacade();
			HashMap params = new HashMap();
			action.setActionId("300");
			logger.info("______________________________________");
			IResult resultat = facade.invokeService(null, action, vo, params);
			
			if (resultat != null && resultat.getValueObject() != null) 
				{
				HashMap map = new HashMap();
                map = (HashMap) resultat.getValueObject();
                listNreg =  (List<ReglementVO>) map.get("listReglementVO");	
              ReglementVO f = listNreg.get(0);
              logger.info(f);
              
				}

		      } catch (Exception e) {
			logger.error("probl�me technique", e);
		}

		return listNreg;

	}
	
	
	@Path("/consult/{numeroQuittance}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
	public ReglementVO consulterQuittance(@PathParam("numeroQuittance") String numeroQuittance ) {
		logger.info("Appel methode recherche quitance consult quitance-> Begin");
		//ReglementVO vo = new ReglementVO();
		ReglementVO quitancevo = new ReglementVO();
		List<ReglementVO> listNreg =  new ArrayList<ReglementVO>();
		quitancevo.setNumeroQuittance(numeroQuittance);
		ReglementVO reglemnt = null;
		try {
			//OMNIFacade facade=new OMNIFacade(); 
			OMNIAction action = new OMNIAction();
			OMNIFacade facade = new OMNIFacade();
			HashMap params = new HashMap();
			action.setActionId("301");
			logger.info("______________________________________");
			IResult resultat = facade.invokeService(null, action, quitancevo, params);
			
			if (resultat != null && resultat.getValueObject() != null) 
				{
				HashMap map = new HashMap();
                map = (HashMap) resultat.getValueObject();
                listNreg =  (List<ReglementVO>) map.get("listReglementVO");	
               reglemnt = listNreg.get(0);
              logger.info(reglemnt);
              
				}

		      } catch (Exception e) {
			logger.error("probl�me technique", e);
		}

		return reglemnt;

	}
	/*
	 *  	@Path("consultQuittance/{numeroQuittance}")
   	@Produces(MediaType.APPLICATION_JSON)
   	@GET
   	public ReglementVO consulterQuittance(@PathParam("numeroQuittance") String numeroQuitt) {
   		ReglementVO vo = new ReglementVO();
   		ReglementVO quittance = new ReglementVO();
   		vo.setNumeroQuittance(numeroQuitt);
   		try {
   			// OMNIFacade facade = new OMNIFacade();k
   			OMNIAction action = new OMNIAction();
   			OMNIFacade facade = new OMNIFacade();
   			HashMap params = new HashMap();
   			action.setActionId("");
   			params.put("completeConversion",true);

    			IResult resultat = facade.invokeService(null, action, vo, params);
   			if (resultat != null && resultat.getValueObject() != null) {
   		//		HashMap map = new HashMap();
   			//	map = (HashMap) resultat.getValueObject();
   				quittance = (ReglementVO) resultat.getValueObject();
   			}

   		} catch (Exception e) {
   			 logger.error("probl�me technique",e);
   		}
   		return sinistre;
   	} 
   	
	 * @Path("listNationalite")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON)
	 * 
	 * @GET public List<NationaliteVO> listNationalite() {
	 * logger.info("Appel methode recherche sinistre consult -> Begin"); SinistreVO
	 * vo = new SinistreVO(); List<> listNationalite = new
	 * ArrayList<NationaliteVO>();; try { // OMNIFacade facade = new OMNIFacade();
	 * OMNIAction action = new OMNIAction(); OMNIFacade facade = new OMNIFacade();
	 * HashMap params = new HashMap(); action.setActionId("1023"); IResult resultat
	 * = facade.invokeService(null, action, vo, params); if (resultat != null &&
	 * resultat.getValueObject() != null) { HashMap map = new HashMap(); map =
	 * (HashMap) resultat.getValueObject();
	 * 
	 * listNationalite = (List<NationaliteVO>) map.get("listNationaliteVO"); }
	 * 
	 * } catch (Exception e) { logger.error("probl�me technique",e); }
	 * 
	 * return listNationalite;
	 * 
	 * }
	 */
}

