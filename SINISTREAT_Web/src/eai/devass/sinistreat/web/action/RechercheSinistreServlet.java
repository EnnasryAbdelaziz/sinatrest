package eai.devass.sinistreat.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import eai.devass.sinistreat.appli.utils.Config;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.utils.serialisation.SerialisationTools;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;
import eai.devass.sinistreat.appli.valueobjects.serialisation.GlobalVO;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;
import ma.co.omnidata.framework.services.businessInterface.IResult;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.OMNIFacade;
import ma.co.omnidata.framework.services.securite.impl.OMNIAction;

public class RechercheSinistreServlet extends HttpServlet {

	protected Config configApp =Config.getInstance();
	/**
	 * 
	 */
	private static Logger logger = Logger.getLogger("loggerSINAT");
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor of the object.
	 */
	public RechercheSinistreServlet() {
		super();
	}

	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		SinistreVO vo = new SinistreVO();
		response.setContentType("text/xml");
		response.setCharacterEncoding("Windows-1252");
		PrintWriter out = response.getWriter();

//		try {
//			Class classe = Class.forName (request.getParameter("class"));
//			
//			GlobalVO globalIn =  new GlobalVO();
//			globalIn.setObject((IValueObject) classe.newInstance());
//			IValueObject myvo = globalIn.getObject();
//			//IValueObject myvo = globalIn.getObject();
//		} catch (ClassNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		List<SinistreVO> list = new ArrayList<SinistreVO>();
		try {
			//OMNIFacade facade = new OMNIFacade();
			 OMNIAction action = new OMNIAction();
				OMNIFacade facade = new OMNIFacade();

			 action.setActionId(request.getParameter("codeAction"));
			 HashMap params = new HashMap();
			 PagerVO pager = new PagerVO();
			 pager.setNumPage("1");
			 pager.setPageSize("20");
			 params.put(IParam.PAGER, pager);
			 vo.setNumeroSinistre(request.getParameter("numeroSinistre"));
			 IResult resultat = facade.invokeService(null, action, vo, params);
			 if(resultat != null && resultat.getValueObject() != null){
				 HashMap map = new HashMap();
                 map = (HashMap) resultat.getValueObject();
                 list = (List<SinistreVO>) map.get("listSinistreVO");
                 ObjectMapper mapper = new ObjectMapper();
                 
                     String json = mapper.writeValueAsString(list);
                     System.out.println("ResultingJSONstring = " + json);
                     out.print(json);
                     //System.out.println(json);
                
                 
				// bytes = (byte[]) ((ArrayList) resultat.getValueObject()).get(0);
				 //list = (List<SinistreVO>) resultat.getValueObject();
			 }
		} catch (Exception e) {
			//logger.error("problème technique",e);
		}

	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occure
	 */

	
	

}
