// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SupprimerReglementUCConverter.java

package eai.devass.sinistreat.appli.usecase.metier.reglement;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.ReglementVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;

public class SupprimerQuittanceITTUCConverter extends ValueObjectConverterAbst
        implements IMessageException {

    public SupprimerQuittanceITTUCConverter() {
        converterTools = ConverterTools.getInstance();
    }

    public Object doConvertItemsToValueObject(List listeItems) {
        if(listeItems == null || listeItems.isEmpty()) {
			return null;
		}
        SinistreVO sinVo = new SinistreVO();
        return sinVo;
    }

    public List doConvertValueObjectToItems(Object vo)
            throws ValidationUnitaireException, ValidationException {
        try {
            List lisrgl = new ArrayList();
            Reglement rgl = (Reglement) converterTools
                    .convertToObjectBO((ReglementVO) vo);
            lisrgl.add(rgl);
            return lisrgl;
        } catch (Exception e) {
            throw new ValidationUnitaireException(e.getMessage());
        }
    }

    ConverterTools converterTools;
}
