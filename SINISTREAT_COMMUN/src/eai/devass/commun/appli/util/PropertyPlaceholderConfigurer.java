package eai.devass.commun.appli.util;

import org.springframework.core.io.FileSystemResource;

/**
* Classe qui s'occupe de remplacer les palaceholder des fichiers de
* configuration Spring à partir d'un fichier application.properties
* 
 * @author RP001208
*
*/
public class PropertyPlaceholderConfigurer extends org.springframework.beans.factory.config.PropertyPlaceholderConfigurer  {

       public PropertyPlaceholderConfigurer() {
             super();
             // chemin du dossier contenant le fichier application.properties contenant les
             // valeurs des placeholders
             String folderProps = "/app/conf/sinistreat";

             this.setLocation(new FileSystemResource(folderProps + "/config.properties"));
       }

}

