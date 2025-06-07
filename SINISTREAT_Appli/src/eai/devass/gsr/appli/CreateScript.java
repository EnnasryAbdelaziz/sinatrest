package eai.devass.gsr.appli;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class CreateScript {
	
	public static void main(String[] args) {
		
		
		Configuration cfg = new Configuration().configure("/config.hbm.xml");
		
		SchemaExport schemaExport = new SchemaExport(cfg);
		schemaExport.setDelimiter(";");
		schemaExport.setOutputFile("SINATScript.sql");

		/**Just dump the schema SQLs to the console , but not execute them ***/
		schemaExport.create(true, false);
		
	}

}
