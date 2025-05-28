package eai.devass.gsr.appli.usecase.edition;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eai.devass.edition.Utils.IConstantes;
import eai.devass.edition.valueobjects.EditionVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.usecase.edition.EditerJrxmlDocxUC;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class EditerAutoCollantCNRAUC extends EditerJrxmlDocxUC {

	
	@Override
	protected boolean imprimer(IValueObject vo) {
		EditionVO editionVO = (EditionVO) vo;

		return Boolean.valueOf(editionVO.getImprimer());
	}
	
	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo) {
		Map<String, Object> params = new HashMap<String, Object>();
		EditionVO editionVO = (EditionVO) vo;
		params.put("TYPECONSIGNATION", editionVO.getTypeConsignation());
		params.put("DATE_DEBUT", editionVO.getDateDebut());
		params.put("DATE_FIN", editionVO.getDateFin());
		return params;
	}


	@Override 
	protected String getCodeTemplate(EditionVO editionVO)  {
		String codeAction="";
		// TODO Auto-generated method stub
		if( editionVO.getTypeConsignation().equals('1')){
		codeAction=IConstantes.CODE_TEMPLATE_AUTO_COLLANT_CNRA_NOUVELLE_LIQUIDATION;}
		if( editionVO.getTypeConsignation().equals('2')){
			codeAction=IConstantes.CODE_TEMPLATE_AUTO_COLLANT_CNRA_AGGRAVATION_HAUSSE;}
		if( editionVO.getTypeConsignation().equals('3')){
			codeAction=IConstantes.CODE_TEMPLATE_AUTO_COLLANT_CNRA_ATTENUATION_BAISSE;}
		return codeAction;
		
	}

	@Override
	protected List<String> getCodesTemplate(EditionVO editionVO) throws FonctionnelleException {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList();
		     if( "1".equals(editionVO.getTypeConsignation())) {
			    list.add(IConstantes.CODE_TEMPLATE_AUTO_COLLANT_CNRA_NOUVELLE_LIQUIDATION);}
			if( "2".equals(editionVO.getTypeConsignation())) {
				list.add(IConstantes.CODE_TEMPLATE_AUTO_COLLANT_CNRA_AGGRAVATION_HAUSSE);}
			if( "3".equals(editionVO.getTypeConsignation())) {
				list.add(IConstantes.CODE_TEMPLATE_AUTO_COLLANT_CNRA_ATTENUATION_BAISSE);}

		return list;
	}
	
	}


