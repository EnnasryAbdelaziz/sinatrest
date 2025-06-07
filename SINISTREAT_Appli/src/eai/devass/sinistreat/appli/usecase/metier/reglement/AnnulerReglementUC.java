// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SupprimerReglementUC.java

package eai.devass.sinistreat.appli.usecase.metier.reglement;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.IConstantes;

public class AnnulerReglementUC extends BaseUC
{

    public AnnulerReglementUC()
    {
    }

    protected void executerUC(IValueObject entite, HashMap params)
        throws Exception
    {
        try
        {
            Reglement rgl = (Reglement)getItem(Reglement.class);
            rgl = reglementManager.annulerReglement(rgl);
            if(rgl!=null){
            reglementManager.addHistoriqueEtat(rgl, IConstantes.MOTIF_ANNULATION_REGLEMENT);
            }else{
            	
            	throw new FonctionnelleException("Un problème est survenue lors de l'appel au service de quittancement.");
            }
        }
        catch(Exception e)
        {
            throw new FonctionnelleException(e.getMessage(), new String[0]);
        }
    }

    public boolean isTransactionnal()
    {
        return true;
    }
}
