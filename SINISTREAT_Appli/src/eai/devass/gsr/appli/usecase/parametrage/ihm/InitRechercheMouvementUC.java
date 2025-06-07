package eai.devass.gsr.appli.usecase.parametrage.ihm;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.core.ServicesFactory;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.gsr.appli.manager.metier.transverse.TransverseManager;
import eai.devass.gsr.appli.modele.parametrage.CentreProthese;
import eai.devass.gsr.appli.modele.parametrage.ModeReglement;
import eai.devass.gsr.appli.modele.parametrage.TypeAction;
import eai.devass.gsr.appli.modele.parametrage.TypeCertificat;
import eai.devass.gsr.appli.modele.parametrage.TypeMouvement;
import eai.devass.gsr.appli.modele.parametrage.TypeMvtProthese;
import eai.devass.gsr.appli.modele.parametrage.TypeRgltGsr;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class InitRechercheMouvementUC extends BaseUC {

	@SuppressWarnings("rawtypes")
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {

		// Récupérer la class de l'objet BO correspondant à l'objet VO
		TransverseManager transverseManager = (TransverseManager) ServicesFactory
				.getService(TransverseManager.class);
		
		try {
//			TypeMouvementRechercherUC tm = new TypeMouvementRechercherUC();
//			TypeMouvementVO type = new TypeMouvementVO();
//			type.setPageSize(-1);
//			RechListeVO res = (RechListeVO) tm.executer(type, null)
//					.getValueObject();

//			TypeMvtProtheseRechercherUC tmp = new TypeMvtProtheseRechercherUC();
//			TypeMvtProtheseVO typeP = new TypeMvtProtheseVO();
//			typeP.setPageSize(-1);
//			RechListeVO res1 = (RechListeVO) tmp.executer(typeP, null)
//					.getValueObject();

			
//			MotifDefautRemiseRechercherUC mdr = new MotifDefautRemiseRechercherUC();
//			MotifDefautRemiseVO motif = new MotifDefautRemiseVO();
//			motif.setPageSize(-1);
//			RechListeVO res2 = (RechListeVO) mdr.executer(motif, null)
//					.getValueObject();
			
//			List<MotifDefautRemise> listMotifDefautRemise = transverseManager
//					.getSimilarObject(new MotifDefautRemise());

			
			
//			CertificatsVO certifVO = new CertificatsVO();
//			certifVO.setPageSize(RechListeVO.GET_ALL_DATA);			

			
			
			//initialisation list  type certificat
//			TypeCertificatRechercherUC tcm = new TypeCertificatRechercherUC();
//			TypeCertificatVO typeCertif = new TypeCertificatVO();
//			typeCertif.setPageSize(RechListeVO.GET_ALL_DATA);
//			RechListeVO  rechListTypeCertif = (RechListeVO) tcm.executer(typeCertif, null)
//					.getValueObject();
			//fin initialisation
			
			
			
//			ModeReglementRechercherUC modeReglement = new ModeReglementRechercherUC();
//			ModeReglementVO modeReglementVO = new ModeReglementVO();
//			modeReglementVO.setPageSize(RechListeVO.GET_ALL_DATA);
//			RechListeVO resMr = (RechListeVO) modeReglement.executer(
//					modeReglementVO, null).getValueObject();

			
//			TypeRgltGsrRechercherUC typeReglement = new TypeRgltGsrRechercherUC();
//			TypeRgltGsrVO typeReglementVO = new TypeRgltGsrVO();
//			typeReglementVO.setPageSize(RechListeVO.GET_ALL_DATA);
//			RechListeVO resTr = (RechListeVO) typeReglement.executer(
//					typeReglementVO, null).getValueObject();

			
			
//			MotifRenteEchueVO motifRenteEchueVO = new MotifRenteEchueVO();
//			motifRenteEchueVO.setPageSize(RechListeVO.GET_ALL_DATA);
//			RechListeVO motifeEcheance = (RechListeVO) (new MotifRenteEchueRechercherUC())
//					.executer(motifRenteEchueVO, null).getValueObject();
//			List<MotifDefautRemise> listMotifRenteEchue = transverseManager
//				.getSimilarObject(new MotifRenteEchue());

//			CentreProtheseVO centreProtheseVO = new CentreProtheseVO();
//			centreProtheseVO.setPageSize(RechListeVO.GET_ALL_DATA);
//			RechListeVO listCentreProthes = (RechListeVO) (new CentreProtheseRechercherUC())
//					.executer(centreProtheseVO, null).getValueObject();

//			TypeActionRechercherUC typeActionRechercherUC = new TypeActionRechercherUC();
//			TypeActionVO typeActionVO = new TypeActionVO();
//			typeActionVO.setPageSize(-1);
//			RechListeVO typeAction = (RechListeVO) typeActionRechercherUC
//					.executer(typeActionVO, null).getValueObject();

			this.addResultItem(parametrageManager.getListeEtatRecours(null));
			this.addResultItem(parametrageManager.getListEtatRentier(null));
			this.addResultItem(transverseManager.getSimilarObject(new TypeMouvement()));
			this.addResultItem(transverseManager.getSimilarObject(new TypeMvtProthese()));
			this.addResultItem(transverseManager.getSimilarObject(new ModeReglement()));
			this.addResultItem(transverseManager.getSimilarObject(new TypeRgltGsr()));			
			this.addResultItem(transverseManager.getSimilarObject(new TypeAction()));
			this.addResultItem(transverseManager.getSimilarObject(new CentreProthese()));
			this.addResultItem(parametrageManager.getListEtatRente(null));
			this.addResultItem(transverseManager.getSimilarObject(new TypeCertificat()));
			
			// ***********************************************************************
//			MotifComplementCNRAVO mtfCmplCNRA = new MotifComplementCNRAVO();
//			mtfCmplCNRA.setPageSize(RechListeVO.GET_ALL_DATA);
//			RechListeVO motifCmplCnra = (RechListeVO) (new MotifComplementCNRARechercherUC())
//					.executer(mtfCmplCNRA, null).getValueObject();
//			List<MotifComplementCNRA> listMotifComplementCNRA = transverseManager
//				.getSimilarObject(new MotifComplementCNRA());
//			this.addResultItem(listMotifComplementCNRA);
			
			
			//liste type certificats
			

			
//			MotifManuelSuspensionRenteVO motifManuelSuspensionRenteVO = new MotifManuelSuspensionRenteVO();
//			motifManuelSuspensionRenteVO.setPageSize(RechListeVO.GET_ALL_DATA);
//			RechListeVO motifs = (RechListeVO) (new MotifManuelSuspensionRenteRechercherUC())
//					.executer(motifManuelSuspensionRenteVO, null).getValueObject();
			
//			List<MotifManuelSuspensionRente> listMotifManuelSuspensionRente = transverseManager
//				.getSimilarObject(new MotifManuelSuspensionRente());
//			this.addResultItem(listMotifManuelSuspensionRente);
			
//			TypeCertificatVO typeCertificatVO = new TypeCertificatVO();
//			typeCertificatVO.setPageSize(RechListeVO.GET_ALL_DATA);
//			RechListeVO typesCertificats = (RechListeVO) (new TypeCertificatRechercherUC())
//					.executer(typeCertificatVO, null).getValueObject();
//			this.addResultItem(typesCertificats.getCurrentRes());


			
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);

		}

	}

	public boolean isTransactionnal() {
		return false;
	}

}