package eai.devass.sinistreat.appli.utils.sqlquery;

import java.text.NumberFormat;
import java.util.Locale;

import org.apache.log4j.Logger;

import eai.devass.commun.appli.util.CommonUtils;

public class Test {
	
	public static void main(String[] args) {
		
		Logger logger = Logger.getLogger("loggerSINAT");
		//PstSynchroChargesVO chargesVO = new PstSynchroChargesVO();
//		chargesVO.setAnnqtc(12);
//		chargesVO.setCodcoa(342);
//		chargesVO.setAssure("tetete");
//		DecimalFormat decimalFormat = new DecimalFormat("#000000000");
		
		 try {
//			GenerateSqlQuery generateSqlQuery = GenerateSqlQuery.getInstance();
//			String sql = generateSqlQuery.generateInsertQuery(chargesVO);
//			System.out.println(sql);
			
			Double double1 = 1523652.263D;
			//System.out.println(Long.valueOf(decimalFormat.format(double1)));
			//System.out.println(CommonUtils.format(double1, 2, 5));
			logger.info(CommonUtils.format(double1, 2, 5));
			
			Locale locale = new Locale("fr", "FR");
			NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
			//System.out.println(fmt.format(1457520.00));
			logger.info(fmt.format(1457520.00));
			//System.out.println(fmt.format(double1).replace('€',' '));
			logger.info(fmt.format(double1).replace('€',' '));
			
			
			//System.out.println(CommonUtils.formatterMontant(4545824989.35969));
			logger.info(CommonUtils.formatterMontant(4545824989.35969));
			
		 } catch(Exception e) {
			 //logger.error(e.getMessage());
			 logger.error("problème technique",e);
		 }
	}

}
