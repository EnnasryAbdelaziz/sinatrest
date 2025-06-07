/**
 * 
 */
package eai.devass.gsr.appli.usecase.metier.mouvements;

import ma.co.omnidata.framework.services.dao.Condition;
import ma.co.omnidata.framework.services.entites.IConditionBuilder;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.impl.DeepDefaultConditionBuilder;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;

/**
 * @author elfaismo
 * 
 */
public class ConditionBuild implements IConditionBuilder {
	
    public Condition buildCondition(IEntite entite) {
        
    	if(!(entite instanceof Mouvement)) {
    		throw new IllegalArgumentException();
    	}
    	
    	Mouvement mouvement = (Mouvement) entite;    	
    	mouvement.setOperateur(null);
    	DeepDefaultConditionBuilder cb = new DeepDefaultConditionBuilder();
        Condition cond = cb.buildCondition(mouvement);
        cond.addElement(Condition.NOTEQUALTO,"refEtatMvt.id",new Long("4"));
        cond.addOrderElement(Condition.ORDERBY, "dateCreation",Condition.ORDERBYDESCENDING );
        return cond;
  }


}
