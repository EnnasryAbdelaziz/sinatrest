package eai.devass.sinistreat.appli.usecase.metier.conciliation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.conciliation.RelanceConciliation;
import eai.devass.sinistreat.appli.modele.metier.instruction.PieceAt;
import eai.devass.sinistreat.appli.modele.parametrage.CanalConciliation;
import eai.devass.sinistreat.appli.modele.parametrage.DestinataireRelance;
import eai.devass.sinistreat.appli.modele.parametrage.Ville;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.RelanceConciliationVO;
import eai.devass.sinistreat.appli.valueobjects.metier.instruction.PieceAtVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.CanalConciliationVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.DestinataireRelanceVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.VilleVO;

@SuppressWarnings("all")
public class RelancerConciliationUCConverter extends ValueObjectConverterAbst
implements IMessageException{
	
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	
	public Object doConvertItemsToValueObject(List listeItems) {
		Map map = new HashMap();
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		//DestinataireRelance
		//CanalConciliation
		List<DestinataireRelanceVO> listDestinataireRelanceVO = new ArrayList<DestinataireRelanceVO>();
		List<CanalConciliationVO> listCanalConciliationVO = new ArrayList<CanalConciliationVO>();
		List<VilleVO> listVilleVO = new ArrayList<VilleVO>();
		List<PieceAtVO> listPieceVO = new ArrayList<PieceAtVO>();
		List<PieceAtVO> listPieceCocherVO = new ArrayList<PieceAtVO>();
		try {
			List<DestinataireRelance> listDestinataireRelance = (List<DestinataireRelance> ) listeItems.get(0);
			listDestinataireRelanceVO = (List<DestinataireRelanceVO>)ConverterTools.getInstance().convertToListObjectVO(listDestinataireRelance);
			
			List<CanalConciliation> listCanalConciliation = (List<CanalConciliation> ) listeItems.get(1);
			listCanalConciliationVO = (List<CanalConciliationVO>)ConverterTools.getInstance().convertToListObjectVO(listCanalConciliation);
			
			List<Ville> listVille = (List<Ville> ) listeItems.get(2);
			listVilleVO = (List<VilleVO>)ConverterTools.getInstance().convertToListObjectVO(listVille);
			
			List<PieceAt> listPiece = (List<PieceAt> ) listeItems.get(3);
			listPieceVO = (List<PieceAtVO>)ConverterTools.getInstance().convertToListObjectVO(listPiece);
			
			List<PieceAt> listPieceCocher = (List<PieceAt> ) listeItems.get(4);
			listPieceCocherVO = (List<PieceAtVO>)ConverterTools.getInstance().convertToListObjectVO(listPieceCocher);
			
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		map.put("listCanalConciliationVO", listCanalConciliationVO);
		map.put("listDestinataireRelanceVO", listDestinataireRelanceVO);
		map.put("listVilleVO", listVilleVO);
		map.put("listPieceVO", listPieceVO);
		map.put("listPieceCocherVO", listPieceCocherVO);
		
		return map;
	}
	
	//RelanceConciliation
	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {
	
		List listOut = new ArrayList();
		RelanceConciliationVO relanceConVO = (RelanceConciliationVO) vo;
	

		try {
			RelanceConciliation relanceConciliation = (RelanceConciliation) converterTools.convertToObjectBO(relanceConVO);
			listOut.add(relanceConciliation);
		} catch (Exception e) {
			Logger.getLogger("loggerSinat").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}

}
