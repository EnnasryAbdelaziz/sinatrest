package eai.devass.sinistreat.appli.usecase.edition;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.apache.commons.lang.StringUtils;

import eai.devass.edition.Utils.IConstantes;
import eai.devass.edition.beans.SinistreBean;
import eai.devass.gsr.appli.utile.ConvertierMontantEnLettre;
import eai.devass.gsr.appli.utile.TypeConverter;

public class EditerLettreQuittanceITTUC extends EditerJavaBeanJrxmlUC{

	@Override
	protected String getCodeTemplate() {
		return IConstantes.CODE_TEMPLATE_LETTRE_QUITTANCE_ITT;
	}

	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo){
		Map<String, Object> params = new HashMap<String, Object>();
		return params;
	}

	@Override
	protected List<SinistreBean> pupulateBeans(IValueObject vo){
		
		DateFormat dateFormat_Defaut = new SimpleDateFormat("dd/MM/yyyy");
		List<SinistreBean> results = null;
		try {
	
			/**
             * RAF WASSIM calcule salaire (salaire journalier * (2/3)) calc dif
             * des jours entre date itt et reprise (date itt - date reprise)
			 * problème des caractére sp (à, é,è...)
			 * 
			**/
			List<Object[]> listSinistre = null;
				
				listSinistre = sinistreManager.Recherche();
				
			if(listSinistre!=null && listSinistre.size()>0){
				
				results= new ArrayList<SinistreBean>();
				for(Object[] sinistre:listSinistre){
					Long nbrJour = 0L;
					SinistreBean sinEdition= new SinistreBean();		
					if(sinistre[0]!=null){
						sinEdition.setNumeroGrave((String) sinistre[0]);
					}else{
						sinEdition.setNumeroGrave("");
					}
					if(sinistre[1]!=null){
                        sinEdition.setDateAccident(dateFormat_Defaut
                                .format(sinistre[1]));
					}else{
						sinEdition.setDateAccident("");
					}
					if(sinistre[2]!=null){
						sinEdition.setNomClient((String) sinistre[2]);
					}else{
						sinEdition.setNomClient("");
					}
					if(sinistre[3]!=null){
						sinEdition.setVictime((String) sinistre[3]);
					}else{
						sinEdition.setVictime("");
					}
					//début calc
					if(sinistre[4]!=null){
                        sinEdition.setDateITT(dateFormat_Defaut
                                .format(sinistre[4]));
					}else{
						sinEdition.setDateITT("");
					}
					if(sinistre[5]!=null){
                        sinEdition.setDateReprise(dateFormat_Defaut
                                .format(sinistre[5]));
					}else{
						sinEdition.setDateReprise("");
					}					
                    if ((sinEdition.getDateITT() != null && !sinEdition
                            .getDateITT().equals(""))
                            && (sinEdition.getDateReprise() != null && !sinEdition
                                    .getDateReprise().equals(""))) {
                        Date dateITT = convertStringToDate(sinEdition
                                .getDateITT());
                        Date dateReprise = convertStringToDate(sinEdition
                                .getDateReprise());
						nbrJour = dateReprise.getTime() - dateITT.getTime();
						if (nbrJour > 0){
							nbrJour = nbrJour/ (1000*60*60*24);
							if (nbrJour == 1){
                                sinEdition.setNombreJour(Long.toString(nbrJour)
                                        + " jour");
								}else{
                                sinEdition.setNombreJour(Long.toString(nbrJour)
                                        + " jours");
									}
							}else {
								sinEdition.setNombreJour("0 jour");
								}		
						}else{
							sinEdition.setNombreJour("0 jour");
							}
					//Fin calc
					if(sinistre[6]!=null){
                        sinEdition.setSalaireJournalier(StringUtils.replace(
                                StringUtils.replace((String) sinistre[6], " ",
                                        ""), ",", "."));
					}else{
						sinEdition.setSalaireJournalier("0");
					}
					if(sinistre[8]!=null){
                        sinEdition.setMontant(StringUtils.replace(StringUtils
                                .replace(sinistre[8].toString(), " ", ""), ",",
                                "."));
					}else{
						sinEdition.setMontant("0");
					}
					
					if(sinistre[0]!=null){
						sinEdition.setNumeroSinistre((String) sinistre[7]);
					}else{
						sinEdition.setNumeroSinistre("");
					}
					
                    if (TypeConverter.getInstance().stringToDouble(
                            sinEdition.getMontant()) > 0) {
                        sinEdition.setTotalEnLettre(ConvertierMontantEnLettre
                                .montantToLettre(sinEdition.getMontant(),
                                        "dirhams", "cts"));
						sinEdition.setTotal(sinEdition.getMontant());
					}else{
						sinEdition.setTotalEnLettre("zero dirhams");
						sinEdition.setTotal("0");
					}
					results.add(sinEdition);
				}
			}
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		return results;
	}

	@Override
	protected boolean imprimer(IValueObject vo) {
		return false;
	}
	
    public Date convertStringToDate(String dateString) {
	    Date date = null;
	    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	    try{
	        date = df.parse(dateString);
        } catch (Exception ex) {
	        System.out.println(ex);
	    }
	    return date;
	}
}
