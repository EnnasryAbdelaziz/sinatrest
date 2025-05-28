package eai.devass.sinistreat.appli.usecase.ihm;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.core.ServicesFactory;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.commun.appli.uc.SimpleBaseUC;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.manager.reglement.ReglementManager;
import eai.devass.sinistreat.appli.modele.metier.reglement.DetailReglement;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.DetailReglementVO;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.ReglementRelanceITTVO;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.ReglementVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.EtatRglVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.TypeReglementVO;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;

public class InitRelanceITTUC extends SimpleBaseUC {

	protected ReglementManager reglementManager = (ReglementManager) ServicesFactory.getService(ReglementManager.class);
	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	protected void execute(IValueObject valueObject, HashMap params)
			throws Exception {
		ReglementVO reglement = (ReglementVO) valueObject;
		HashMap map = (HashMap) this.getItem(HashMap.class);
		PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);
		try {
			
			BigDecimal seuil = BigDecimal.valueOf(999999);
			BigDecimal seuilsub = BigDecimal.valueOf(0);
			List<Date> listeDateCreation= new ArrayList<Date>();
			
			logger.info("Start extracting data from BDD ");
			if("1".equals(reglement.getRappel()))
			{
				listeDateCreation = reglementManager.getListDateEcheanceReglementAValider(reglement, map, seuil,seuilsub,pagerVO);
			}else{
				if (pagerVO != null && pagerVO.getNbrLignes() != null) {
					if (pagerVO.getNumPage() == null) {
						throw new FonctionnelleException(
								"EXP_PAGER_NUMERO_PAGE_OBLIGATOIRE");
					} else if (pagerVO.getPageSize() == null) {
						throw new FonctionnelleException(
								"EXP_PAGER_PAGE_SIZE_OBLIGATOIRE");
					}
					
					if ("1".equals(pagerVO.getNbrLignes())) {
						logger.info("Get Number Of Dates");
						Integer nbreObject = reglementManager.getNombreDateCreation(reglement);
						logger.info("The Number Of Dates is "+ nbreObject);
						pagerVO.setNbrLignes(nbreObject.toString());
						Integer pageSize = Integer.valueOf(pagerVO.getPageSize());
						if(nbreObject.compareTo(pageSize)>=0){
						if ((nbreObject % pageSize) == 0) {
							pagerVO.setNbrPages(String.valueOf(nbreObject
									/ pageSize));
						} else {
							pagerVO.setNbrPages(String
									.valueOf((nbreObject / pageSize) + 1));

						}
	                    } else {
							pagerVO.setNbrPages("1");
						}
					}

				}
				listeDateCreation = reglementManager.getListDateReglementAValider(reglement,pagerVO);
			}

			if (reglement.getRefTypeReglement() != null) {
				reglement.setCodeEtatRgl(new String[] { IConstantes.ETAT_PRE_QUITTANCE_EMISSION_BATCH_ITT });
			}
			List<Reglement> listReglement = (List<Reglement>) reglementManager.getListReglementITTAValider(reglement, listeDateCreation);

			logger.info("End of extracting data from BDD ");
			
			List<ReglementRelanceITTVO> reglementRelance = new ArrayList<ReglementRelanceITTVO>();
			for (Date date : listeDateCreation) {
				date = dateFormat.parse(dateFormat.format(date));
				ReglementRelanceITTVO rglRelance = new ReglementRelanceITTVO();
				List<ReglementVO> listRgl = new ArrayList<ReglementVO>();
				rglRelance.setDate(dateFormat.format(date));
				for (Reglement rgl : listReglement) {
					ReglementVO rglVO = new ReglementVO();
					SinistreVO sinVO = new SinistreVO();
					Date dateCreationRgl = dateFormat.parse(dateFormat.format(rgl.getDateCreation()));
					if("1".equals(reglement.getRappel()))
					{
						dateCreationRgl = dateFormat.parse(dateFormat.format(rgl.getDateProchaineEcheance()));
					}
					if(date.equals(dateCreationRgl)){
						rglVO.setId(String.valueOf(rgl.getId()));
						sinVO.setNumeroSinistre(rgl.getRefSinistre().getNumeroSinistre());
						sinVO.setId(String.valueOf(rgl.getRefSinistre().getId()));
						rglVO.setRefSinistre(sinVO);
						if(rgl.getDateProchaineEcheance() != null) {
						rglVO.setDateProchaineEcheance(dateFormat.format(rgl.getDateProchaineEcheance()));
						}
						rglVO.setSalaireMensuel(String.valueOf(rgl.getSalaireMensuel()));
						rglVO.setDateEtat(dateFormat.format(rgl.getDateEtat()));
						rglVO.setDateEtablissement(dateFormat.format(rgl.getDateEtablissement()));
						rglVO.setDateReglement(dateFormat.format(rgl.getDateReglement()));
						rglVO.setService(rgl.getService());
						rglVO.setNomBeneficiaireLettre(rgl.getNomBeneficiaireLettre());
						rglVO.setTypeBeneficiaireLettre(rgl.getTypeBeneficiaireLettre());
						rglVO.setCodeIntermediaire(rgl.getCodeIntermediaire());
						rglVO.setCreatorFirstQtc(rgl.getCreatorFirstQtc());
						rglVO.setNomIntermediaire(rgl.getNomIntermediaire());
						rglVO.setDateCreation(dateFormat.format(rgl.getDateCreation()));
						rglVO.setMontant(String.valueOf(rgl.getMontant()));
						rglVO.setNomBeneficiaire(rgl.getNomBeneficiaire());
						rglVO.setTypeBeneficiaire(rgl.getTypeBeneficiaire());
						rglVO.setRefEtatReglement(new EtatRglVO(rgl.getRefEtatReglement().getCode(),rgl.getRefEtatReglement().getLibelle()));
						rglVO.setRefTypeReglement(new TypeReglementVO(rgl.getRefTypeReglement().getCode(),rgl.getRefTypeReglement().getLibelle()));
						rglVO.setEmissionITT(String.valueOf(rgl.getEmissionITT()));
						rglVO.setRappel(String.valueOf(rgl.getRappel()));
						rglVO.setSmig(String.valueOf(rgl.getSmig()));
						List<DetailReglementVO> listDregVO = new ArrayList<DetailReglementVO>();
						for (DetailReglement dreg : rgl.getListDetailReglement()) {
							DetailReglementVO dregVO = new DetailReglementVO();
							dregVO.setCodeGarantie(dreg.getCodeGarantie());
							dregVO.setCodePrestation(dreg.getCodePrestation());
							dregVO.setContentieux(String.valueOf(dreg.getContentieux()));
							if(dreg.getDateDebutPrestation() != null)
							dregVO.setDateDebutPrestation(dateFormat.format(dreg.getDateDebutPrestation()));
							if(dreg.getDateFinPrestation() != null)
							dregVO.setDateFinPrestation(dateFormat.format(dreg.getDateFinPrestation()));
							dregVO.setId(String.valueOf(dreg.getId()));
							dregVO.setLibellePrestation(dreg.getLibellePrestation());
							dregVO.setMontant(String.valueOf(dreg.getMontant()));
							dregVO.setMontantRejete(String.valueOf(dreg.getMontantRejete()));
							listDregVO.add(dregVO);
						}
						rglVO.setListDetailReglement(listDregVO);
						listRgl.add(rglVO);
					}
				}
				rglRelance.setLisReglementVOs(listRgl);
				reglementRelance.add(rglRelance);
				addResultItem(rglRelance);
			}

//			this.addResultItem(reglementRelance);			
			Map mapOut = new HashMap();
			mapOut.put("listreglementRelanceVO", reglementRelance);

		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);

		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}
		
	}

	public boolean isTransactionnal() {
		return false;
	}



}
