package eai.devass.sinistreat.web.action.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.management.MXBean;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import eai.devass.gsr.appli.utile.HibernatePersistenceService;
import eai.devass.sinistreat.appli.manager.contentieux.converters.PartieAdverseJudVOConverter;
import eai.devass.sinistreat.appli.manager.sinistre.SinistreManager;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.metier.ListVO;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.PartieAdverseJudVO;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.ProcedureJudiciaireVO;
import eai.devass.sinistreat.appli.valueobjects.metier.instruction.InstructionPieceAtVO;
import eai.devass.sinistreat.appli.valueobjects.metier.instruction.InstructionVO;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.DetailReglementVO;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.ReglementVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.EtatSinistreVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.EvenementVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.MouvementVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.NotificationSmsVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.PoliceVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.VictimeVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.PrestataireVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.VilleVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.ZoneVO;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;
import ma.co.omnidata.framework.services.businessInterface.IResult;
import ma.co.omnidata.framework.services.businessInterface.OMNIFacade;
import ma.co.omnidata.framework.services.businessInterface.impl.Result;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;
import ma.co.omnidata.framework.services.securite.impl.OMNIAction;

@Path("searchSinistre")
public class RechercheSinistreController {
	
	private static Logger logger = Logger.getLogger("loggerSINAT");
	
       @GET
	   @Produces(MediaType.TEXT_PLAIN)
       @Path("salut/{username}")
	   public String hi(@PathParam("username") String username) {
		   logger.info("Appel methode hello -> Begin");

	       return "Hello " +username;
	   }
       
       
       

   	@Path("consult/{numeroSinistre}")
   	@Produces(MediaType.APPLICATION_JSON)
   	@GET
   	public SinistreVO consulterSinistre(@PathParam("numeroSinistre") String numeroSin) {
   		SinistreVO vo = new SinistreVO();
   		SinistreVO sinistre = new SinistreVO();
   		vo.setNumeroSinistre(numeroSin);
   		try {
   			// OMNIFacade facade = new OMNIFacade();
   			OMNIAction action = new OMNIAction();
   			OMNIFacade facade = new OMNIFacade();
   			HashMap params = new HashMap();
   			action.setActionId("115");
   			IResult resultat = facade.invokeService(null, action, vo, params);
   			if (resultat != null && resultat.getValueObject() != null) {
   		//		HashMap map = new HashMap();
   			//	map = (HashMap) resultat.getValueObject();
   				sinistre = (SinistreVO) resultat.getValueObject();
   			}

   		} catch (Exception e) {
   			 logger.error("probl�me technique",e);
   		}
   		return sinistre;
   	}
   	
   	@Path("consultPolice/{numeroPolice}")
   	@Produces(MediaType.APPLICATION_JSON)
   	@GET
   	public PoliceVO consulterContratSinistre(@PathParam("numeroPolice") String numeroPolice) {
   		PoliceVO vo = new PoliceVO();
   		PoliceVO polvo = new PoliceVO();
   		vo.setNumeroPolice(numeroPolice);
   		try {
   			// OMNIFacade facade = new OMNIFacade();
   			OMNIAction action = new OMNIAction();
   			OMNIFacade facade = new OMNIFacade();
   			HashMap params = new HashMap();
   			action.setActionId("113");
   			IResult resultat = facade.invokeService(null, action, vo, params);
   			if (resultat != null && resultat.getValueObject() != null) {
   		//		HashMap map = new HashMap();
   			//	map = (HashMap) resultat.getValueObject();
   				 polvo = (PoliceVO) resultat.getValueObject();
   			}

   		} catch (Exception e) {
   			 logger.error("probl�me technique",e);
   		}
   		return polvo;
   	}
   	
       
       
       
       @Path("/{numeroSinistre}/{nomVictime}")       
	   @Produces(MediaType.APPLICATION_JSON)
       @GET
public List<SinistreVO> rechercherSinistre(@PathParam("numeroSinistre") String numero, @PathParam("nomVictime") String nomVictime) throws JsonProcessingException {
	
		   logger.info("Appel methode recherche sinistre -> Begin");
	SinistreVO vo = new SinistreVO();
//	SinistreVO vo2 = new SinistreVO();
//	SinistreVO vo3 = new SinistreVO();
//	SinistreVO vo4 = new SinistreVO();

	vo.setNumeroSinistre(numero);
	List<SinistreVO> list = new ArrayList<SinistreVO>();
	try {
		//OMNIFacade facade = new OMNIFacade();
		 OMNIAction action = new OMNIAction();
			OMNIFacade facade = new OMNIFacade();

		 action.setActionId("101");
		 HashMap params = new HashMap();
		 PagerVO pager = new PagerVO();
		 pager.setNumPage("1");
		 pager.setPageSize("20");
		 params.put(IParam.PAGER, pager);
//		 vo.setAdresseAssure("casablanca");
//		 vo2.setAdresseAssure("Marrakech");
	//	vo.setDateEffet("10/05/2000");
		 if(numero != null && !numero.equals("null")) {
			vo.setNumeroSinistre(numero);
		 } else {
			 vo.setNumeroSinistre("");
		 }
//			vo.setCodeClient("23699");
//			vo.setId("188457851");
//			vo2.setNumeroSinistre("201209000000000000022");
//			vo2.setCodeClient("43699");
//			vo2.setId("188457850");
//			vo3.setNumeroSinistre("201209000000000000022");
//			vo3.setCodeClient("43699");
//			vo3.setId("188457850");
//			 vo3.setAdresseAssure("Rabat");
//			 vo4.setNumeroSinistre("201209000000000000022");
//				vo4.setCodeClient("43699");
//				vo4.setId("188457850");
//				 vo4.setAdresseAssure("Rabat");

//			
//			EvenementVO even = new EvenementVO();
   //   even.setDateAccident("26/04/2012");
			
//			vo.setRefEvenement(even);
//	
		 if(nomVictime != null && !nomVictime.equals("null")) {
			VictimeVO vic= new VictimeVO();
			vic.setNom(nomVictime);
			vo.setRefVictime(vic);
		 }
//			vic.setPrenom("dgdgd");
			//vic.setDateNaissance("20/06/1955");
			
//			AyantDroitVO ayvo = new AyantDroitVO();
//			ayvo.setNom("nom");
//		ayvo.setPrenom("prenom");
//			//ayvo.setDateNaissance("26/07/1980");
//			List listAYvo= new ArrayList();
//		listAYvo.add(ayvo);
		
			
//			vic.setListAyantDroit(listAYvo);
			
//		 list.add(vo);
//		 list.add(vo2);
//		 list.add(vo3);
//		 list.add(vo4);


		 
		 IResult resultat = facade.invokeService(null, action, vo, params);
		 if(resultat != null && resultat.getValueObject() != null){
			 HashMap map = new HashMap();
             map = (HashMap) resultat.getValueObject();
             list = (List<SinistreVO>) map.get("listSinistreVO");
//             ObjectMapper mapper = new ObjectMapper();
//             
//                 String json = mapper.writeValueAsString(list);
//                 System.out.println("ResultingJSONstring = " + json);
//                 out.print(json);
                 //System.out.println(json);
            
             
			// bytes = (byte[]) ((ArrayList) resultat.getValueObject()).get(0);
			 //list = (List<SinistreVO>) resultat.getValueObject();
		 }
		
	} catch (Exception e) {
		//logger.error("probl�me technique",e);
	}
		 ObjectMapper mapper = new ObjectMapper();

          String json = mapper.writeValueAsString(list);
          System.out.println("ResultingJSONstring = " + json);
		   logger.info("Appel methode recherche sinistre -> Fin");

	 return list;
	
}
       
       @Path("/search")       
	   @Produces(MediaType.APPLICATION_JSON)
       @Consumes(MediaType.APPLICATION_JSON)
       @POST
	/* @CrossOrigin */
public List<SinistreVO> searchSinistre(SinistreVO sinistre) throws JsonProcessingException {
	
    logger.info("Appel methode recherche sinistre avec post method -> Begin");
	List<SinistreVO> list = new ArrayList<SinistreVO>();
	try {
		//OMNIFacade facade = new OMNIFacade();
		 OMNIAction action = new OMNIAction();
			OMNIFacade facade = new OMNIFacade();

		 action.setActionId("101");
		 HashMap params = new HashMap();
		 PagerVO pager = new PagerVO();
		 pager.setNumPage("1");
		 pager.setPageSize("100");
		 params.put(IParam.PAGER, pager);
		 IResult resultat = facade.invokeService(null, action, sinistre, params);
		 if(resultat != null && resultat.getValueObject() != null){
			 HashMap map = new HashMap();
             map = (HashMap) resultat.getValueObject();
             list = (List<SinistreVO>) map.get("listSinistreVO");
//             ObjectMapper mapper = new ObjectMapper();
//             
//                 String json = mapper.writeValueAsString(list);
//                 System.out.println("ResultingJSONstring = " + json);
//                 out.print(json);
                 //System.out.println(json);
            
             
			// bytes = (byte[]) ((ArrayList) resultat.getValueObject()).get(0);
			 //list = (List<SinistreVO>) resultat.getValueObject();
		 }
		
	} catch (Exception e) {
		//logger.error("probl�me technique",e);
	}
		 ObjectMapper mapper = new ObjectMapper();

          String json = mapper.writeValueAsString(list);
          System.out.println("ResultingJSONstring = " + json);
		   logger.info("Appel methode recherche sinistre -> Fin");

	 return list;
	
}

	@Path("/rechercheQuittance")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	/* @CrossOrigin */
	public List<ReglementVO> rechercheQuittance(ReglementVO reg) throws JsonProcessingException {

		logger.info("Appel methode recherche quittance avec post method -> Begin");
		List<ReglementVO> list = new ArrayList<ReglementVO>();
		try {
			//OMNIFacade facade = new OMNIFacade();
			OMNIAction action = new OMNIAction();
			OMNIFacade facade = new OMNIFacade();

			action.setActionId("300");
			HashMap params = new HashMap();
			PagerVO pager = new PagerVO();
			pager.setNumPage("1");
			pager.setPageSize("20");
			params.put(IParam.PAGER, pager);
			IResult resultat = facade.invokeService(null, action, reg, params);
			if(resultat != null && resultat.getValueObject() != null){
				HashMap map = new HashMap();
				map = (HashMap) resultat.getValueObject();
				list = (List<ReglementVO>) map.get("listReglementVO");
//             ObjectMapper mapper = new ObjectMapper();
//
//                 String json = mapper.writeValueAsString(list);
//                 System.out.println("ResultingJSONstring = " + json);
//                 out.print(json);
				//System.out.println(json);


				// bytes = (byte[]) ((ArrayList) resultat.getValueObject()).get(0);
				//list = (List<SinistreVO>) resultat.getValueObject();
			}

		} catch (Exception e) {
			//logger.error("probl�me technique",e);
		}

		logger.info("Appel methode recherche Quittance -> Fin");

		return list;

	}
       
       @Path("/create")       
	   @Produces(MediaType.APPLICATION_JSON)
       @Consumes(MediaType.APPLICATION_JSON)
       @POST
public SinistreVO createSinistre(SinistreVO sinistre) throws JsonProcessingException {
	
    logger.info("Appel methode recherche sinistre avec post method -> Begin");
	List<SinistreVO> list = new ArrayList<SinistreVO>();
	SinistreVO sinResult = new SinistreVO();
	try {
		//OMNIFacade facade = new OMNIFacade();
		
		 OMNIAction action = new OMNIAction();
			OMNIFacade facade = new OMNIFacade();

			//sinistre.getRefEvenement().setDateAccident("12/05/2022");
			//sinistre.setDateDeclaration("12/05/2022");
			sinistre.setIppEstime("10");
			sinistre.setIpp("10");
		 action.setActionId("114");
		 HashMap params = new HashMap();
		 PagerVO pager = new PagerVO();
		 pager.setNumPage("1");
		 pager.setPageSize("20");
		 params.put(IParam.PAGER, pager);
		 IResult resultat = facade.invokeService(null, action, sinistre, params);
		 if(resultat != null && resultat.getValueObject() != null){
			 HashMap map = new HashMap();
             //map = (HashMap) resultat.getValueObject();
             sinResult = (SinistreVO) resultat.getValueObject();
//             ObjectMapper mapper = new ObjectMapper();
//             
//                 String json = mapper.writeValueAsString(list);
//                 System.out.println("ResultingJSONstring = " + json);
//                 out.print(json);
                 //System.out.println(json);
            
             
			// bytes = (byte[]) ((ArrayList) resultat.getValueObject()).get(0);
			 //list = (List<SinistreVO>) resultat.getValueObject();
		 }
		
	} catch (Exception e) {
		//logger.error("probl�me technique",e);
	}
		 ObjectMapper mapper = new ObjectMapper();

          String json = mapper.writeValueAsString(sinResult);
          System.out.println("ResultingJSONstring = " + json);
		   logger.info("Appel methode recherche sinistre -> Fin");

	 
	return sinResult;
	
}
       @Path("listParams")
      	@Produces(MediaType.APPLICATION_JSON)
      	@GET
      	public Map listParams() {
    		Map map= new HashMap();
    		SinistreVO vo = new SinistreVO();
      		try {
      			// OMNIFacade facade = new OMNIFacade();
      			OMNIAction action = new OMNIAction();
      			OMNIFacade facade = new OMNIFacade();
      			HashMap params = new HashMap();
      			action.setActionId("1000");
      			IResult resultat = facade.invokeService(null, action, vo, params);
      			if (resultat != null && resultat.getValueObject() != null) {
      		//		HashMap map = new HashMap();
      				map = (HashMap) resultat.getValueObject();
					System.out.println("ResultingJSONstring = ");
					System.out.println("ResultingJSONstring = ");

      			}

      		} catch (Exception e) {
      			 logger.error("probl�me technique",e);

      		}
      		
      		return map;
      		
      	}
    	
    	@Path("listPolices")
      	@Produces(MediaType.APPLICATION_JSON)
    	@Consumes(MediaType.APPLICATION_JSON)
      	@POST
      	public PoliceVO listPolices(PoliceVO police) {
    		PoliceVO pol = new PoliceVO();
      		try {
      			// OMNIFacade facade = new OMNIFacade();
      			OMNIAction action = new OMNIAction();
      			OMNIFacade facade = new OMNIFacade();
      			HashMap params = new HashMap();
      			action.setActionId("113");
      			IResult resultat = facade.invokeService(null, action, police, params);
      			if (resultat != null && resultat.getValueObject() != null) {
      		//		HashMap map = new HashMap();
      				pol = (PoliceVO) resultat.getValueObject();
      				
      			}

      		} catch (Exception e) {
      			 logger.error("probl�me technique",e);
      		}
      		return pol;
      	}
    	
    	@Path("listReglements")
      	@Produces(MediaType.APPLICATION_JSON)
    	@Consumes(MediaType.APPLICATION_JSON)
      	@POST
      	public List<ReglementVO> listReglements(ReglementVO reglement) {
    		List<ReglementVO> listRgl = new ArrayList<ReglementVO>();
      		try {
      			// OMNIFacade facade = new OMNIFacade();
      			OMNIAction action = new OMNIAction();
      			OMNIFacade facade = new OMNIFacade();
      			HashMap params = new HashMap();
      			action.setActionId("300");
      			PagerVO pager = new PagerVO();
      			pager.setNumPage("1");
      			pager.setPageSize("20");
      			params.put(IParam.PAGER, pager);
      			IResult resultat = facade.invokeService(null, action, reglement, params);
      			if (resultat != null && resultat.getValueObject() != null) {
      				HashMap map = new HashMap();
      	            map = (HashMap) resultat.getValueObject();
      	            listRgl = (List<ReglementVO>) map.get("listReglementVO"); 
      	            System.out.println("listRgl :"+listRgl.size());
      			}

      		} catch (Exception e) {
      			 logger.error("probl�me technique",e);
      		}
      		return listRgl;
      	}
    	
    	@Path("listHistoriqueEtats")
      	@Produces(MediaType.APPLICATION_JSON)
    	@Consumes(MediaType.APPLICATION_JSON)
      	@POST
      	public List<EtatSinistreVO> listHistoriqueEtats(SinistreVO sinistre) {
    		List<EtatSinistreVO> listEtats = new ArrayList<EtatSinistreVO>();
      		try {
      			// OMNIFacade facade = new OMNIFacade();
      			OMNIAction action = new OMNIAction();
      			OMNIFacade facade = new OMNIFacade();
      			HashMap params = new HashMap();
      			action.setActionId("123");
      			PagerVO pager = new PagerVO();
      			pager.setNumPage("1");
      			pager.setPageSize("20");
      			params.put(IParam.PAGER, pager);
      			IResult resultat = facade.invokeService(null, action, sinistre, params);
      			if (resultat != null && resultat.getValueObject() != null) {
      	          listEtats = (List<EtatSinistreVO>) resultat.getValueObject(); 
      			}

      		} catch (Exception e) {
      			 logger.error("probl�me technique",e);
      		}
      		return listEtats;
      	}
    	
    	@Path("listHistoriqueRervesEtMouvements")
      	@Produces(MediaType.APPLICATION_JSON)
    	@Consumes(MediaType.APPLICATION_JSON)
      	@POST
      	public List<MouvementVO> listHistoriqueRervesEtMouvements(SinistreVO sinistre) {
    		List<MouvementVO> listMouvements = new ArrayList<MouvementVO>();
      		try {
      			// OMNIFacade facade = new OMNIFacade();
      			OMNIAction action = new OMNIAction();
      			OMNIFacade facade = new OMNIFacade();
      			HashMap params = new HashMap();
      			action.setActionId("124");
      			PagerVO pager = new PagerVO();
      			pager.setNumPage("1");
      			pager.setPageSize("20");
      			params.put(IParam.PAGER, pager);
      			IResult resultat = facade.invokeService(null, action, sinistre, params);
      			if (resultat != null && resultat.getValueObject() != null) {
      				listMouvements = (List<MouvementVO>) resultat.getValueObject(); 
      			}

      		} catch (Exception e) {
      			 logger.error("probl�me technique",e);
      		}
      		return listMouvements;
      	}
    	
    	@Path("listHistoriqueNotificationSMS")
      	@Produces(MediaType.APPLICATION_JSON)
    	@Consumes(MediaType.APPLICATION_JSON)
      	@POST
      	public List<NotificationSmsVO> listHistoriqueNotificationSMS(SinistreVO sinistre) {
    		List<NotificationSmsVO> listNotificationSmsVO = new ArrayList<NotificationSmsVO>();
      		try {
      			// OMNIFacade facade = new OMNIFacade();
      			OMNIAction action = new OMNIAction();
      			OMNIFacade facade = new OMNIFacade();
      			HashMap params = new HashMap();
      			action.setActionId("125");
      			PagerVO pager = new PagerVO();
      			pager.setNumPage("1");
      			pager.setPageSize("20");
      			params.put(IParam.PAGER, pager);
      			IResult resultat = facade.invokeService(null, action, sinistre, params);
      			if (resultat != null && resultat.getValueObject() != null) {
      				listNotificationSmsVO = (List<NotificationSmsVO>) resultat.getValueObject(); 
      			}

      		} catch (Exception e) {
      			 logger.error("probl�me technique",e);
      		}
      		return listNotificationSmsVO;
      	}
    	
    	
    	
        @Path("/search/listProcedureJudiciaire")       
 	    @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        @POST
 	/* @CrossOrigin */
 public List<ProcedureJudiciaireVO> searchProcedureBySinistre(ProcedureJudiciaireVO proc) throws JsonProcessingException {
 	
    logger.info("Appel methode recherche procedure avec post method -> Begin");
	List<ProcedureJudiciaireVO> listProc = new ArrayList<ProcedureJudiciaireVO>();
 	try {
 		//OMNIFacade facade = new OMNIFacade()
 		 OMNIAction action = new OMNIAction();
 		 OMNIFacade facade = new OMNIFacade();
 		 HashMap params = new HashMap();
 		 action.setActionId("406");
 		 IResult resultat = facade.invokeService(null, action, proc, params);
 		 if(resultat != null && resultat.getValueObject() != null){
 			if (resultat != null && resultat.getValueObject() != null) {
 				listProc = (List<ProcedureJudiciaireVO>) resultat.getValueObject(); 
  			}
 		 }
 		
 	} catch (Exception e) {
			 logger.error("probleme technique",e);
 	}

 		   logger.info("Appel methode recherche procedure -> Fin");

 	 return listProc;
 	
 }
        
   
        
        
        @Path("/createInstruction")       
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        @POST
         public InstructionVO createInstruction(InstructionVO instr) throws JsonProcessingException {
         	
             logger.info("Appel methode creation Instruction avec post method -> Begin");
         	InstructionVO instResult = new InstructionVO();
         	try {
         		//OMNIFacade facade = new OMNIFacade();
         		/*
         		IPersistenceService dao = (IPersistenceService) ServicesFactory.getService(IPersistenceService.class);
        		Session sess=  (Session) dao.newSession();
         		
         		SinistreManager sinistreManager = (SinistreManager) ServicesFactory.getService(SinistreManager.class);
         		sinistreManager.setSession(sess);

         		Sinistre sin = sinistreManager.getSinistreForGedByNumQuery(instr.getSinistre().getNumeroSinistre());
         		instr.getSinistre().setId(String.valueOf(sin.getId()));
         		*/
         		OMNIAction action = new OMNIAction();
         		OMNIFacade facade = new OMNIFacade();
         		
         		ObjectMapper objectMapper = new ObjectMapper();
         		
         		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
         		List<InstructionPieceAtVO> listeinstructionPieceAt= objectMapper.convertValue(instr.getInstructionPieceAt(), new TypeReference<List<InstructionPieceAtVO>>() {});
         		instr.setInstructionPieceAt(listeinstructionPieceAt);
         		 action.setActionId("10");
         		 HashMap params = new HashMap();
         		 
         		 IResult resultat = facade.invokeService(null, action, instr, params);
         		 if(resultat != null && resultat.getValueObject() != null){
         			 HashMap map = new HashMap();
                      //map = (HashMap) resultat.getValueObject();
                      instResult = (InstructionVO) resultat.getValueObject();
         		 }
         		
         	} catch (Exception e) {
         		//logger.error("probl�me technique",e);
         	}
         		 ObjectMapper mapper = new ObjectMapper();

                   String json = mapper.writeValueAsString(instResult);
                   System.out.println("ResultingJSONstring = " + json);
         		   logger.info("Appel methode recherche sinistre -> Fin");

         	 
         	return instResult;
         	
         }



@Path("/createProcedure")       
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@POST
 public ProcedureJudiciaireVO createProcedure(ProcedureJudiciaireVO proc) throws JsonProcessingException {
 	
     logger.info("Appel methode creation Instruction avec post method -> Begin");
     ProcedureJudiciaireVO procResult = new ProcedureJudiciaireVO();
 	try {
 		//OMNIFacade facade = new OMNIFacade();
// 		IPersistenceService dao = (IPersistenceService) ServicesFactory.getService(IPersistenceService.class);
//		Session sess=  (Session) dao.newSession();
// 		
// 		SinistreManager sinistreManager = (SinistreManager) ServicesFactory.getService(SinistreManager.class);
// 		sinistreManager.setSession(sess);
//
// 		Sinistre sin = sinistreManager.getSinistreForGedByNumQuery(instr.getSinistre().getNumeroSinistre());
// 		instr.getSinistre().setId(String.valueOf(sin.getId()));
 		OMNIAction action = new OMNIAction();
 		OMNIFacade facade = new OMNIFacade();
 		 action.setActionId("403");
 		 HashMap params = new HashMap();
 		 
 		LinkedHashMap<String, Object> map2 = new LinkedHashMap();
 		
// 		List l = new ArrayList<>();
//        l= proc.getListePartieAdverses();
 		ObjectMapper objectMapper = new ObjectMapper();
 		List<PartieAdverseJudVO> listePartieAdverses = objectMapper.convertValue(proc.getListePartieAdverses(), new TypeReference<List<PartieAdverseJudVO>>() {});
 		proc.setListePartieAdverses(listePartieAdverses);
 		 IResult resultat = facade.invokeService(null, action, proc, params);
 		 if(resultat != null && resultat.getValueObject() != null){
 			 HashMap map = new HashMap();
              //map = (HashMap) resultat.getValueObject();
 			procResult = (ProcedureJudiciaireVO) resultat.getValueObject();
 		 }
 		
 	} catch (Exception e) {
 		//logger.error("probl�me technique",e);
 	}
 		 ObjectMapper mapper = new ObjectMapper();

           String json = mapper.writeValueAsString(procResult);
           System.out.println("ResultingJSONstring = " + json);
 		   logger.info("Appel methode recherche sinistre -> Fin");

 	 
 	return procResult;
 	
 }

    @Path("getZoneByVille/{codeVille}")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public ZoneVO getZoneByVille(@PathParam("codeVille") String codeVille) {
/*	try {
		getSubject(header);
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}*/
		SinistreVO vo = new SinistreVO();
	    ZoneVO zone = new ZoneVO();
	    EvenementVO even =  new EvenementVO();
	    VilleVO ville = new VilleVO();
	    ville.setCode(codeVille);
	    even.setRefVille(ville);
		vo.setRefEvenement(even);
		try {
			// OMNIFacade facade = new OMNIFacade();
			OMNIAction action = new OMNIAction();
			OMNIFacade facade = new OMNIFacade();
			HashMap params = new HashMap();
			//params.put("completeConversion",true);
			action.setActionId("1025");
			IResult resultat = facade.invokeService(null, action, vo, params);
			if (resultat != null && resultat.getValueObject() != null) {
		//		HashMap map = new HashMap();
			//	map = (HashMap) resultat.getValueObject();
				zone = (ZoneVO) resultat.getValueObject();
				zone.setLibelle(zone.getCode());
			}

		} catch (Exception e) {
			 logger.error("probl�me technique",e);
		}
		
		return zone;
		
	}
    
    @Path("/calculReserve")       
	   @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
public SinistreVO calculReserveSinistre(SinistreVO sinistre) throws JsonProcessingException {
	
 logger.info("Appel methode recherche sinistre avec post method -> Begin");
	List<SinistreVO> list = new ArrayList<SinistreVO>();
	SinistreVO sinResult = new SinistreVO();
	try {
		//OMNIFacade facade = new OMNIFacade();
		
		 OMNIAction action = new OMNIAction();
			OMNIFacade facade = new OMNIFacade();

			//sinistre.getRefEvenement().setDateAccident("12/05/2022");
			//sinistre.setDateDeclaration("12/05/2022");
			sinistre.setIppEstime("10");
			sinistre.setIpp("10");
		 action.setActionId("114");
		 HashMap params = new HashMap();
		 PagerVO pager = new PagerVO();
		 pager.setNumPage("1");
		 pager.setPageSize("20");
		 params.put(IParam.PAGER, pager);
		 IResult resultat = facade.invokeService(null, action, sinistre, params);
		 if(resultat != null && resultat.getValueObject() != null){
			 HashMap map = new HashMap();
          //map = (HashMap) resultat.getValueObject();
          sinResult = (SinistreVO) resultat.getValueObject();
//          ObjectMapper mapper = new ObjectMapper();
//          
//              String json = mapper.writeValueAsString(list);
//              System.out.println("ResultingJSONstring = " + json);
//              out.print(json);
              //System.out.println(json);
         
          
			// bytes = (byte[]) ((ArrayList) resultat.getValueObject()).get(0);
			 //list = (List<SinistreVO>) resultat.getValueObject();
		 }
		
	} catch (Exception e) {
		//logger.error("probl�me technique",e);
	}
		 ObjectMapper mapper = new ObjectMapper();

       String json = mapper.writeValueAsString(sinResult);
       System.out.println("ResultingJSONstring = " + json);
		   logger.info("Appel methode recherche sinistre -> Fin");
		   
		   if(sinResult.getSourceDeclaration() != null && sinResult.getSourceDeclaration().getCode() == null)
		   {
			   sinResult.setSourceDeclaration(null);
		   }
		   if(sinResult.getRefVictime().getRefTypeMaladie() != null && sinResult.getRefVictime().getRefTypeMaladie().getCode() == null)
		   {
			   sinResult.getRefVictime().setRefTypeMaladie(null); 
		   }
		   if(sinResult.getRefEvenement().getRefTypeAccident() != null && sinResult.getRefEvenement().getRefTypeAccident().getCode() == null)
		   {
			   sinResult.getRefEvenement().setRefTypeAccident(null);
		   }

	 
	return sinResult;
	
}
    
    
    @Path("/creerSinistreConfirm")       
	   @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
public SinistreVO createSinistreConf(SinistreVO sinistre, @HeaderParam(value = "user") String header) throws JsonProcessingException {
	
 logger.info("Appel methode recherche sinistre avec post method -> Begin");
	List<SinistreVO> list = new ArrayList<SinistreVO>();
	SinistreVO sinResult = new SinistreVO();
		IResult resultat = new Result(); ; 

	try {
		//OMNIFacade facade = new OMNIFacade();
		
		 OMNIAction action = new OMNIAction();
			OMNIFacade facade = new OMNIFacade();

			//sinistre.getRefEvenement().setDateAccident("12/05/2022");
			//sinistre.setDateDeclaration("12/05/2022");
			//sinistre.setIppEstime("10");
			//sinistre.setIpp("10");
			sinistre.setUserCreateur(header);
		 action.setActionId("102");
		 HashMap params = new HashMap();
		 PagerVO pager = new PagerVO();
		 pager.setNumPage("1");
		 pager.setPageSize("20");
		 params.put(IParam.PAGER, pager);
		 resultat = facade.invokeService(null, action, sinistre, params);
		 if(resultat != null && resultat.getValueObject() != null){
			 HashMap map = new HashMap();
          //map = (HashMap) resultat.getValueObject();
          sinResult = (SinistreVO) resultat.getValueObject();
//          ObjectMapper mapper = new ObjectMapper();
//          
//              String json = mapper.writeValueAsString(list);
//              System.out.println("ResultingJSONstring = " + json);
//              out.print(json);
              //System.out.println(json);
         
          
			// bytes = (byte[]) ((ArrayList) resultat.getValueObject()).get(0);
			 //list = (List<SinistreVO>) resultat.getValueObject();
		 }
		
	} catch (Exception e) {
		//logger.error("probl�me technique",e);
	}
		 ObjectMapper mapper = new ObjectMapper();

       String json = mapper.writeValueAsString(sinResult);
       System.out.println("ResultingJSONstring = " + json);
		   logger.info("Appel methode recherche sinistre -> Fin");

	 
	return sinResult;
	
}
    
    @Path("listParamsRegl")
	@Produces(MediaType.APPLICATION_JSON)
	@POST
//	@Cors
//	@CorsPreflight
	public Map listParamsReglm(ReglementVO reg) {
		Map map= new HashMap();
		try {
			// OMNIFacade facade = new OMNIFacade();
			OMNIAction action = new OMNIAction();
			OMNIFacade facade = new OMNIFacade();
			HashMap params = new HashMap();
			action.setActionId("1006");
			IResult resultat = facade.invokeService(null, action, reg, params);
			if (resultat != null && resultat.getValueObject() != null) {
		//		HashMap map = new HashMap();
				map = (HashMap) resultat.getValueObject();
				
			}

		} catch (Exception e) {
			 logger.error("probl�me technique",e);
		}
		
		return map;
		
	}
    
    
    
    @Path("getAuxByCode/{codeAux}")
   	@Produces(MediaType.APPLICATION_JSON)
   	@GET
   	public PrestataireVO getAuxByCode(@PathParam("codeAux") String codeAux,@HeaderParam(value = "user") String header) {
	/*	try {
			getSubject(header);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
    	PrestataireVO vo = new PrestataireVO();
    	List<PrestataireVO> listePrestataire = null;
    	vo.setCode(codeAux);
   		try {
   			// OMNIFacade facade = new OMNIFacade();
   			OMNIAction action = new OMNIAction();
   			OMNIFacade facade = new OMNIFacade();
   			HashMap params = new HashMap();
   			//params.put("completeConversion",true);
   			action.setActionId("205");
   			IResult resultat = facade.invokeService(null, action, vo, params);
   			if (resultat != null && resultat.getValueObject() != null) {
   		//		HashMap map = new HashMap();
   			//	map = (HashMap) resultat.getValueObject();
   				listePrestataire = (List<PrestataireVO>) resultat.getValueObject();
   				vo = listePrestataire.get(0);

   			}

   		} catch (Exception e) {
   			 logger.error("probleme technique",e);
   		}
   		
   		return vo;
   		
   	}
    
    @Path("getMandataireByCode/{codeMand}")
   	@Produces(MediaType.APPLICATION_JSON)
   	@GET
   	public PrestataireVO getMandataireByCode(@PathParam("codeMand") String codeMand,@HeaderParam(value = "user") String header) {
	/*	try {
			getSubject(header);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
    	PrestataireVO vo = new PrestataireVO();
    	List<PrestataireVO> listePrestataire = null;
    	//
    	vo.setCodeMandataire(codeMand);
    	vo.setCode(codeMand);
   		try {
   			// OMNIFacade facade = new OMNIFacade();
   			OMNIAction action = new OMNIAction();
   			OMNIFacade facade = new OMNIFacade();
   			HashMap params = new HashMap();
   			//params.put("completeConversion",true);
   			action.setActionId("205");
   			IResult resultat = facade.invokeService(null, action, vo, params);
   			if (resultat != null && resultat.getValueObject() != null) {
   		//		HashMap map = new HashMap();
   			//	map = (HashMap) resultat.getValueObject();
   				listePrestataire = (List<PrestataireVO>) resultat.getValueObject();
   				vo = listePrestataire.get(0);

   			}

   		} catch (Exception e) {
   			 logger.error("probleme technique",e);
   		}
   		
   		return vo;
   		
   	}
    
    
    @Path("validerReglement")
  	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
  	@POST
  	public Map validerReglement(ReglementVO reglement) {
		Map map= new HashMap();
  		try {
  			// OMNIFacade facade = new OMNIFacade();
  			OMNIAction action = new OMNIAction();
  			OMNIFacade facade = new OMNIFacade();
  			HashMap params = new HashMap();
  			action.setActionId("323");
  			ObjectMapper objectMapper = new ObjectMapper();
  			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  	 		List<DetailReglementVO> listDetailReglements = objectMapper.convertValue(reglement.getListDetailReglement(), new TypeReference<List<DetailReglementVO>>() {});
  	 		reglement.setListDetailReglement(listDetailReglements);
  			IResult resultat = facade.invokeService(null, action, reglement, params);
  			if (resultat != null && resultat.getValueObject() != null) {
  	            map = (HashMap) resultat.getValueObject();
//  	            listRgl = (List<ReglementVO>) map.get("listReglementVO"); 
//  	            System.out.println("listRgl :"+listRgl.size());
  			}

  		} catch (Exception e) {
  			 logger.error("probl�me technique",e);
  		}
  		return map;
  	}

    
    @Path("/creerReglementAuxiliaire")       
	   @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
public ReglementVO createRegAuxiliaire(ReglementVO reg, @HeaderParam(value = "user") String header) throws JsonProcessingException {
	
 logger.info("Appel methode creation reg  avec post method -> Begin");

	ReglementVO regResult = new ReglementVO();
		IResult resultat = new Result(); ; 

	try {
		//OMNIFacade facade = new OMNIFacade();
		ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	 		List<DetailReglementVO> listDetailReglements = objectMapper.convertValue(reg.getListDetailReglement(), new TypeReference<List<DetailReglementVO>>() {});
	 		reg.setListDetailReglement(listDetailReglements);
		 OMNIAction action = new OMNIAction();
			OMNIFacade facade = new OMNIFacade();

			//sinistre.getRefEvenement().setDateAccident("12/05/2022");
			//sinistre.setDateDeclaration("12/05/2022");
			//sinistre.setIppEstime("10");
			//sinistre.setIpp("10");
			//sinistre.setUserCreateur(header);
		 action.setActionId("302");
		 HashMap params = new HashMap();

		 resultat = facade.invokeService(null, action, reg, params);
		 if(resultat != null && resultat.getValueObject() != null){
			 HashMap map = new HashMap();
          //map = (HashMap) resultat.getValueObject();
			 regResult = (ReglementVO) resultat.getValueObject();
//          ObjectMapper mapper = new ObjectMapper();
//          
//              String json = mapper.writeValueAsString(list);
//              System.out.println("ResultingJSONstring = " + json);
//              out.print(json);
              //System.out.println(json);
         
          
			// bytes = (byte[]) ((ArrayList) resultat.getValueObject()).get(0);
			 //list = (List<SinistreVO>) resultat.getValueObject();
		 }
		
	} catch (Exception e) {
		//logger.error("probl�me technique",e);
	}
		 ObjectMapper mapper = new ObjectMapper();

       String json = mapper.writeValueAsString(regResult);
       System.out.println("ResultingJSONstring = " + json);
		   logger.info("Appel methode creation reg  -> Fin");

	 
	return regResult;
	
}
    
    
    @Path("listReglementAuxiliaireValider")
  	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
  	@POST
  	public List<ReglementVO> listReglementsAuxAvalider(ReglementVO reglement) {
		List<ReglementVO> listRgl = new ArrayList<ReglementVO>();
  		try {
  			// OMNIFacade facade = new OMNIFacade();
  			OMNIAction action = new OMNIAction();
  			OMNIFacade facade = new OMNIFacade();
  			HashMap params = new HashMap();
  			action.setActionId("1021");
  			PagerVO pager = new PagerVO();
  			pager.setNumPage("1");
  			pager.setPageSize("20");
  			params.put(IParam.PAGER, pager);
  			IResult resultat = facade.invokeService(null, action, reglement, params);
  			if (resultat != null && resultat.getValueObject() != null) {
  				listRgl = (List<ReglementVO>) resultat.getValueObject();
  	            System.out.println("listRgl :"+listRgl.size());
  			}

  		} catch (Exception e) {
  			 logger.error("probl�me technique",e);
  		}
  		return listRgl;
  	}
    
    
    @Path("listSinistreAValider")
  	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
  	@POST
  	public List<SinistreVO> listSinistreAvalider(SinistreVO sinistre) {
		List<SinistreVO> listSin = new ArrayList<SinistreVO>();
  		try {
  			// OMNIFacade facade = new OMNIFacade();
  			OMNIAction action = new OMNIAction();
  			OMNIFacade facade = new OMNIFacade();
  			HashMap params = new HashMap();
  			action.setActionId("1005");
  			PagerVO pager = new PagerVO();
  			pager.setNumPage("1");
  			pager.setPageSize("20");
  			params.put(IParam.PAGER, pager);
  			IResult resultat = facade.invokeService(null, action, sinistre, params);
  			if (resultat != null && resultat.getValueObject() != null) {
  				listSin = (List<SinistreVO>) resultat.getValueObject();
  	            System.out.println("listRgl :"+listSin.size());
  			}

  		} catch (Exception e) {
  			 logger.error("probl�me technique",e);
  		}
  		return listSin;
  	}
    
    @Path("validateListReglementAuxiliaire")
  	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
  	@POST
  	public Map validerListReglement(List<ReglementVO> listReg) {
		Map map= new HashMap();
  		try {
  			// OMNIFacade facade = new OMNIFacade();
  			OMNIAction action = new OMNIAction();
  			OMNIFacade facade = new OMNIFacade();
  			HashMap params = new HashMap();
  			action.setActionId("320");
  			
  			ListVO listRegAux = new ListVO();
  			listRegAux.setListReglement(listReg);
  			
  			IResult resultat = facade.invokeService(null, action, listRegAux, params);
  			if (resultat != null && resultat.getValueObject() != null) {
  	            map = (HashMap) resultat.getValueObject();
//  	            listRgl = (List<ReglementVO>) map.get("listReglementVO"); 
//  	            System.out.println("listRgl :"+listRgl.size());
  			}

  		} catch (Exception e) {
  			 logger.error("probl�me technique",e);
  		}
  		return map;
  	}
    
    
    
    
    @Path("validateListSinistre")
  	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
  	@POST
  	public ArrayList validerListSinistre(List<SinistreVO> listSinistres) {
		ArrayList res = new ArrayList<>();
  		try {
  			// OMNIFacade facade = new OMNIFacade();
  			OMNIAction action = new OMNIAction();
  			OMNIFacade facade = new OMNIFacade();
  			HashMap params = new HashMap();
  			action.setActionId("110");
  			
  			ListVO listSins = new ListVO();
  			listSins.setListSinistre(listSinistres);
  			
  			IResult resultat = facade.invokeService(null, action, listSins, params);
  			if (resultat != null && resultat.getValueObject() != null) {
  				res = (ArrayList) resultat.getValueObject();
//  	            listRgl = (List<ReglementVO>) map.get("listReglementVO"); 
//  	            System.out.println("listRgl :"+listRgl.size());
  			}

  		} catch (Exception e) {
  			 logger.error("probl�me technique",e);
  		}
  		return res;
  	}

    @Path("getParamsInstruction")
   	@Produces(MediaType.APPLICATION_JSON)
   	@GET
   	public Map getParamsInstruction() {
 		Map map= new HashMap();
 		InstructionVO vo = new InstructionVO();
   		try {
   			// OMNIFacade facade = new OMNIFacade();
   			OMNIAction action = new OMNIAction();
   			OMNIFacade facade = new OMNIFacade();
   			HashMap params = new HashMap();
   			action.setActionId("14");
   			IResult resultat = facade.invokeService(null, action, vo, params);
   			if (resultat != null && resultat.getValueObject() != null) {
   		//		HashMap map = new HashMap();
   				map = (HashMap) resultat.getValueObject();
   				
   			}

   		} catch (Exception e) {
   			 logger.error("problï¿½me technique",e);
   		}
   		
   		return map;
   		
   	}
        
        
}	

