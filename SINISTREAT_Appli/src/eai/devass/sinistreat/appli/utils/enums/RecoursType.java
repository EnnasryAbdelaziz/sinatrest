/**
 * 
 */
package eai.devass.sinistreat.appli.utils.enums;

/**
 * @author elfaismo
 *
 */
public enum RecoursType {
	
	  recoursType_1("Recours judiciaire"), recoursType_2("Recours"),recoursType_3("Recoursamiable"), recoursType_4("Recours sans voie entamée");
      private String value;

      private RecoursType(String value) {
              this.value = value;
      }
      
      public String toString(){
    	    return value;
    	  }

}
