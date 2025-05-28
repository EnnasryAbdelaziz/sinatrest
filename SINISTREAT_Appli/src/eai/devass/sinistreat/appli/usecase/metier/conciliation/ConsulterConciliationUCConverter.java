package eai.devass.sinistreat.appli.usecase.metier.conciliation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.conciliation.Conciliation;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Offre;
import eai.devass.sinistreat.appli.modele.metier.instruction.PieceAt;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.ConciliationVO;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.ConvocationVO;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.OffreVO;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.PiecesVO;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.RelanceConciliationVO;
import eai.devass.sinistreat.appli.valueobjects.metier.instruction.PieceAtVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.OrigineConciliationVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.VilleVO;

public class ConsulterConciliationUCConverter extends ValueObjectConverterAbst {

	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	//@SuppressWarnings("all")
	public Object doConvertItemsToValueObject(List listeItems) {

		Map map = new HashMap();
		ConciliationVO conciVO = new ConciliationVO();
		List<PieceAtVO> listPieceCocherVO = new ArrayList<PieceAtVO>();
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}

		try {
			Conciliation conciliation = (Conciliation) listeItems.get(0);
		
			List<Offre> listOff = conciliation.getListOffre();
			

			if (conciliation != null && conciliation instanceof Conciliation) {
				
				// convertion manuelle : pour ne convertir que les elements necessaire pour l'IHM
				conciVO.setId(conciliation.getId());
				conciVO.setDateEtat(dateFormat.format(conciliation.getDateEtat()));
				conciVO.setUserCreateur(conciliation.getUserCreateur());
				conciVO.setUserModificateur(conciliation.getUserModificateur());
				conciVO.setEtat(conciliation.getEtat());
				conciVO.setRefOrigineConciliation((OrigineConciliationVO) ConverterTools.getInstance().convertToObjectVO(conciliation.getRefOrigineConciliation()));
				conciVO.setNomAvocat(conciliation.getNomAvocat());
				conciVO.setAdresseAvocat(conciliation.getAdresseAvocat());
				if (conciliation.getRefVilleAvocat() != null) {
					conciVO.setRefVilleAvocat((VilleVO) ConverterTools
							.getInstance().convertToObjectVO(
									conciliation.getRefVilleAvocat()));
				}
					
				
				conciVO.setDateCreation(dateFormat.format(conciliation.getDateCreation()));
				// Pièces
				if (conciliation.getListPieces()!=null && conciliation.getListPieces().size()!=0){
					List<PiecesVO> listPieces = (List) ConverterTools.getInstance().convertToListObjectVOWithoutList(conciliation.getListPieces());
					conciVO.setListPieces(listPieces);
				}
				// Convocation
				if (conciliation.getListConvocation()!=null && conciliation.getListConvocation().size()!= 0){
					List<ConvocationVO> listConvocation = (List) ConverterTools.getInstance().convertToListObjectVOWithoutList(conciliation.getListConvocation());
					conciVO.setListConvocation(listConvocation);
				}
				// Offre
				if (listOff != null  && listOff.size()!= 0) {
					Offre offre = listOff.get(0);
					if (offre instanceof Offre) {
						List listayd = offre.getListAyantDroit();
						((Offre) offre).setPropertiesToConvert(new String[] {
								"id", "salaireAnnuel", "salaireUtile",
								"dateCreation", "ipp", "autreTauxIpp",
								"refMotifOffre.id", "dateOffre", "datemotif",
								"capitalRachat", "dateDepartRente",
								"renteTrimistriel", "refMotifOffre.libelle",
								"observation", "observationOffre",
								"listAyantDroit", "reserveOffre", "ippOffre",
								"montantRente", "montantReserve",
								"montantArrerageAVC", "refResultatOffre.id",
								"refResultatOffre.libelle", "sommeReserve",
								"listSinistreAnterieur", "montantARegler",
								"ittRegle", "avp", "codeMedecinControleur",
								"nomMedecinControleur","coefficientAge","dateCompletudeDossier"});
						List<OffreVO> listOffre = (List<OffreVO>) ConverterTools
								.getInstance().convertToListObjectVO(listOff);
						conciVO.setListOffre(listOffre);
					}
				}
				//Relance Conciliation
				if (conciliation.getListRelanceConciliation()!=null && conciliation.getListRelanceConciliation().size()!= 0){
					List<RelanceConciliationVO> listRelanceConciliation = (List) ConverterTools.getInstance().convertToListObjectVOWithoutList(conciliation.getListRelanceConciliation());
					conciVO.setListRelanceConciliation(listRelanceConciliation);
				}
				
				if (conciVO != null) {
					map.put("list" + conciVO.getClass().getSimpleName(),
							conciVO);
				}
				List<PieceAt> listPieceCocher = (List<PieceAt> ) listeItems.get(1);
				listPieceCocherVO = (List<PieceAtVO>)ConverterTools.getInstance().convertToListObjectVO(listPieceCocher);
				map.put("listPieceCocherVO", listPieceCocherVO);

				return map;
			}

		} catch (Exception e) {
			logger.error("problème technique", e);
		}
		return null;
	}

	@Override
	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {
		List listOut = new ArrayList();
		ConciliationVO conVO = (ConciliationVO) vo;

		try {
			Conciliation conciliation = (Conciliation) converterTools
					.convertToObjectBO(conVO);
			listOut.add(conciliation);
		} catch (Exception e) {
			Logger.getLogger("loggerSinat").fatal(
					"Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}

}
