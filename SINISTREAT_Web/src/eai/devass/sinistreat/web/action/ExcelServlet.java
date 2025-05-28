package eai.devass.sinistreat.web.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.co.omnidata.framework.services.businessInterface.IResult;
import ma.co.omnidata.framework.services.businessInterface.OMNIFacade;
import ma.co.omnidata.framework.services.securite.impl.OMNIAction;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import eai.devass.sinistreat.appli.modele.metier.contentieux.Recours;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Evenement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.parametrage.Assurance;
import eai.devass.sinistreat.appli.modele.parametrage.EtatRecours;
import eai.devass.sinistreat.appli.utils.enums.RecoursType;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.RecoursVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.EvenementVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.AssuranceVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.EtatRecoursVO;




public class ExcelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	private Logger logger = Logger.getLogger("loggerGED");
	
	public ExcelServlet() {
		super();
	}
	
	public void doGet(HttpServletRequest request, 
		    HttpServletResponse response)
		      throws ServletException, IOException
		   {
		try{
		     String codeAction = (String) request.getParameter("codeAction");
			 OMNIFacade facade = new OMNIFacade();
			 OMNIAction action = new OMNIAction();
			 action.setActionId(codeAction);
			 RecoursVO vo = buildRecoursVO(request);
			 HashMap params = new HashMap();
			 
			 IResult resultat = facade.invokeService(null, action, vo, params);
	
			 List<RecoursVO> listRecours = (List<RecoursVO>)resultat.getValueObject();

			
			Calendar cal = Calendar.getInstance();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HHmm");
		    String strDate = sdf.format(cal.getTime());
		    logger.info("generation fichier excel contenant les recours est en cours");
		    HSSFWorkbook excelFile = generateFile(listRecours);
			
			//Setup the output
			String contentType = "application/vnd.ms-excel";
		    response.setHeader("Content-Disposition", 
		     "attachment; filename=Liste_recours_"+ strDate +".xls");
		     
		    logger.info("generation fichier excel contenant les recours est terminé avec succes");
		    
			response.setContentType(contentType);
			ServletOutputStream out = response.getOutputStream();
			excelFile.write(out);
			out.flush();
			out.close();

			} catch (Exception e) {
				logger.error("problème technique",e);
				logger.error("Une erreur est survenue lors de l'export des recours.", e);
			} 
		
		
		   }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	/**
	 * Construire une instance du RecoursVO à partir de la requête HTTP
	 * @param request
	 * @return
	 */
	private RecoursVO buildRecoursVO(HttpServletRequest request){
		RecoursVO vo = new RecoursVO();
		
		
		vo.setNumeroRecours(request.getParameter("numeroRecours")) ;
		vo.setTypeRecours(request.getParameter("typeRecours")) ;
		EvenementVO refEvenement = new EvenementVO();
		
		refEvenement.setDateAccident(request.getParameter("dateAccident"));
		refEvenement.setNumeroEvenement(request.getParameter("numeroEvenement"));
		SinistreVO refSinistre = new SinistreVO();
		refSinistre.setNumeroSinistre(request.getParameter("numeroSinistre"));
		refSinistre.setNumeroGrave(request.getParameter("numeroGrave"));
		refSinistre.setCodeClient(request.getParameter("assure"));
		refSinistre.setRefEvenement(refEvenement);
		vo.setRefSinistre(refSinistre)  ;
		
		EtatRecoursVO refEtatRecours = new EtatRecoursVO();
		refEtatRecours.setCode(request.getParameter("etatRecours"));
		AssuranceVO refCompagnie = new AssuranceVO();
		refCompagnie.setCode(request.getParameter("compagnie")) ;
        vo.setRefEtatRecours(refEtatRecours);
        vo.setRefCompagnie(refCompagnie);

		vo.setDateDebut(request.getParameter("dateDebut"));
		vo.setDateFin(request.getParameter("dateFin"));
	
		return vo;
	}
	/**
	 * 
	 * @param request
	 * @return
	 */
	private Recours buildRecours(HttpServletRequest request){
		
		Recours recours = new Recours();
		
		
		recours.setNumeroRecours(request.getParameter("numeroRecours")) ;
		recours.setTypeRecours(request.getParameter("typeRecours")) ;
		Evenement refEvenement = new Evenement();


		refEvenement.setNumeroEvenement(request.getParameter("numeroEvenement"));
		Sinistre refSinistre = new Sinistre();
		refSinistre.setNumeroSinistre(request.getParameter("numeroSinistre"));
		refSinistre.setNumeroGrave(request.getParameter("numeroGrave"));
		refSinistre.setCodeClient(request.getParameter("assure"));
		recours.setRefSinistre(refSinistre)  ;
		
		EtatRecours refEtatRecours = new EtatRecours();
		refEtatRecours.setCode(request.getParameter("etatRecours"));
		Assurance refCompagnie = new Assurance();
		refCompagnie.setCode(request.getParameter("compagnie")) ;
        recours.setRefEtatRecours(refEtatRecours);
        recours.setRefCompagnie(refCompagnie);

	
		return recours;
	}
	
	
	/**
	 * Construction du contenu du fichier Excel à partir de la liste des recours 
	 * @param listeRecours
	 * @return Objet de type :HSSFWorkbook
	 */
	private HSSFWorkbook generateFile(List<RecoursVO> listeRecours){
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Liste des recours");

		
		int rowNum = 0;

		// Create the column headings
		HSSFRow headerRow = sheet.createRow((short) rowNum);

		HSSFCellStyle headerStyle = workbook.createCellStyle();
			
	    HSSFFont font = workbook.createFont();
        font.setFontName(HSSFFont.FONT_ARIAL);
        font.setFontHeightInPoints((short) 13);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        font.setColor(HSSFColor.WHITE.index);
        headerStyle.setFont(font);
        
        headerStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        headerStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        headerStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        headerStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        
        headerStyle.setFillBackgroundColor(HSSFColor.BLUE_GREY.index);
        headerStyle.setFillPattern(HSSFCellStyle.BIG_SPOTS);
        headerStyle.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
        headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
       
    	HSSFCell cellH0 = headerRow.createCell(0);
		cellH0.setCellValue(" Numéro sinistre ");
		cellH0.setCellStyle(headerStyle);
		
		HSSFCell cellH1 = headerRow.createCell(1);
		cellH1.setCellValue(" Numéro Grave ");
		cellH1.setCellStyle(headerStyle);
		
		HSSFCell cellH2 = headerRow.createCell(2);
		cellH2.setCellValue(" Date accident ");
		cellH2.setCellStyle(headerStyle);
		
		HSSFCell cellH3 = headerRow.createCell(3);
		cellH3.setCellValue(" Numéro recours ");
		cellH3.setCellStyle(headerStyle);
		
		HSSFCell cellH4 = headerRow.createCell(4);
		cellH4.setCellValue(" Compagnie adverse ");
		cellH4.setCellStyle(headerStyle);
		
		HSSFCell cellH5 = headerRow.createCell(5);
		cellH5.setCellValue(" Type recours ");
		cellH5.setCellStyle(headerStyle);
		
		HSSFCell cellH6 = headerRow.createCell(6);
		cellH6.setCellValue(" Etat recours ");
		cellH6.setCellStyle(headerStyle);
		
		HSSFCellStyle style=workbook.createCellStyle();
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);



		if (listeRecours != null && listeRecours.size() > 0){
		for (RecoursVO recours : listeRecours) {

		// Create a new row in the sheet:
	    if(recours!=null){
		HSSFRow row = sheet.createRow(++rowNum);
		
		HSSFCell numeroSinistreCell = row.createCell(0);
		numeroSinistreCell.setCellValue(new HSSFRichTextString(recours.getRefSinistre() != null ? recours.getRefSinistre().getNumeroSinistre():"" ));
		
		numeroSinistreCell.setCellStyle(style);
		
		HSSFCell numeroGraveCell = row.createCell(1);
		numeroGraveCell.setCellValue(new HSSFRichTextString(recours.getRefSinistre() != null ? recours.getRefSinistre().getNumeroGrave() : ""));
		numeroGraveCell.setCellStyle(style);
		
		HSSFCell dateSinistreCell = row.createCell(2);
		dateSinistreCell.setCellValue(new HSSFRichTextString(recours.getRefSinistre().getRefEvenement() != null ? recours.getRefSinistre().getRefEvenement().getDateAccident().toString():""));
		dateSinistreCell.setCellStyle(style);
		
		HSSFCell numeroRecoursCell = row.createCell(3);
		numeroRecoursCell.setCellValue(new HSSFRichTextString(recours.getNumeroRecours() != null ? recours.getNumeroRecours():""));
		numeroRecoursCell.setCellStyle(style);
		
		HSSFCell compagnieAdverseCell = row.createCell(4);
		compagnieAdverseCell.setCellValue(new HSSFRichTextString(recours.getRefCompagnie()!= null? recours.getRefCompagnie().getLibelle():""));
		compagnieAdverseCell.setCellStyle(style);
		
		String typeRecours = getTypeRecoursByCode(Integer.valueOf(recours.getTypeRecours()));
			
		HSSFCell typeRecoursCell = row.createCell(5);
		typeRecoursCell.setCellValue(new HSSFRichTextString(typeRecours));
		typeRecoursCell.setCellStyle(style);
		
		HSSFCell etatRecoursCell = row.createCell(6);
		etatRecoursCell.setCellValue(new HSSFRichTextString(recours.getRefEtatRecours()!=null ? recours.getRefEtatRecours().getLibelle():""));
		etatRecoursCell.setCellStyle(style);
		}
		}
		}
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		sheet.autoSizeColumn(3);
		sheet.autoSizeColumn(4);
		sheet.autoSizeColumn(5);
		sheet.autoSizeColumn(6);
		return workbook;
	}
	
	/**
	 * 
	 * @param codeTypeRecours
	 * @return
	 */
	private String getTypeRecoursByCode(Integer codeTypeRecours){
		
		String typeRecours = null;
	    switch (codeTypeRecours) {
        case 1:
        	typeRecours = RecoursType.recoursType_1.toString();
             break;
        case 2:
        	typeRecours = RecoursType.recoursType_2.toString();
             break;
        case 3:
        	typeRecours = RecoursType.recoursType_3.toString();
             break;
        case 4:
        	typeRecours = RecoursType.recoursType_4.toString();
       }
	    return typeRecours;
		
	}
	
}