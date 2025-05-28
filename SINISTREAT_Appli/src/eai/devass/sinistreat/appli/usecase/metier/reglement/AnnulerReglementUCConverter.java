// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SupprimerReglementUCConverter.java

package eai.devass.sinistreat.appli.usecase.metier.reglement;

import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.ReglementVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

public class AnnulerReglementUCConverter extends ValueObjectConverterAbst
    implements IMessageException
{
	private Logger logger = Logger.getLogger("loggerSINAT");
    public AnnulerReglementUCConverter()
    {
        converterTools = ConverterTools.getInstance();
    }

    public Object doConvertItemsToValueObject(List listeItems)
    {
        if(listeItems == null || listeItems.isEmpty()) {
			return null;
		}
        Sinistre sinistre = null;
        SinistreVO sinVo = new SinistreVO();
        try
        {
            sinistre = (Sinistre)listeItems.get(0);
            sinVo = (SinistreVO)converterTools.convertToObjectVO(sinistre);
        }
        catch(Exception e)
        {
            logger.error("problème technique",e);
        }
        return sinVo;
    }

    public List doConvertValueObjectToItems(Object vo)
        throws ValidationUnitaireException, ValidationException
    {
        try
        {
            List lisrgl = new ArrayList();
            Reglement rgl = (Reglement)converterTools.convertToObjectBO((ReglementVO)vo);
            lisrgl.add(rgl);
            return lisrgl;
        }
        catch(Exception e)
        {
            throw new ValidationUnitaireException(e.getMessage());
        }
    }

    ConverterTools converterTools;
}
