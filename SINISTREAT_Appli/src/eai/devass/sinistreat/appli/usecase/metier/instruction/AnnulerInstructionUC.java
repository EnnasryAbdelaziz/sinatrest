// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SupprimerReglementUC.java

package eai.devass.sinistreat.appli.usecase.metier.instruction;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.instruction.Instruction;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class AnnulerInstructionUC extends BaseUC
{

    public AnnulerInstructionUC()
    {
    }

    protected void executerUC(IValueObject entite, HashMap params)
        throws Exception
    {
        try
        {
            Instruction instruction = (Instruction)getItem(Instruction.class);
            instructionManager.annulerInsruction(instruction);
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
