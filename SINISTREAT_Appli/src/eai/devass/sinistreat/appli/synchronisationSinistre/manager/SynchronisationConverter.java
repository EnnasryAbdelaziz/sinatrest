package eai.devass.sinistreat.appli.synchronisationSinistre.manager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.PersistenceException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.manager.ParametrageManager;
import eai.devass.sinistreat.appli.modele.metier.sinistre.EtatSinistre;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.parametrage.Cause;
import eai.devass.sinistreat.appli.modele.parametrage.TypeGarantie;
import eai.devass.sinistreat.appli.synchronisationSinistre.valueObjects.PstSynchroChargesVO;
import eai.devass.sinistreat.appli.synchronisationSinistre.valueObjects.PstSynchroSinistreVO;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;

public class SynchronisationConverter implements IMessageException {
	private Logger logger = Logger.getLogger("loggerSINAT");
	protected ParametrageManager parametrageManager = (ParametrageManager) ServicesFactory
			.getService(ParametrageManager.class);

	/**
	 * 
	 * @param sinistre
	 * @return
	 * @throws FonctionnelleException
	 */
	public PstSynchroSinistreVO chargerInfosSinistre(Sinistre sinistre)
			throws FonctionnelleException {
		PstSynchroSinistreVO pstSynchroSinistreVO = new PstSynchroSinistreVO();

		String numAffilie = "0";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String numeroSinistre = sinistre.getNumeroSinistre();
		if(numeroSinistre == null || numeroSinistre.length() != 21) {
			throw new FonctionnelleException("Le numéro de sinistre ne peut être <> de 21 : " + numeroSinistre);
		}
		
		
		EtatSinistre etatsinistre = sinistre.getRefEtatSinistre();
		String numgrave = sinistre.getNumeroGrave();
		String cause = getLibCause(sinistre);
		String numPolice = sinistre.getNumeroPolice();
		TypeGarantie typeGar = sinistre.getRefTypeGarantie();

		if (etatsinistre != null && etatsinistre.getRefEtat() != null) {
			String codeEtat = etatsinistre.getRefEtat().getCode();
			pstSynchroSinistreVO.setCodMvt(getCodeMvt(codeEtat, numgrave));
			
		} else {
			pstSynchroSinistreVO.setCodMvt(0);
		}

		// Bon de Pharmacie
		// If InStr(1, INTSINPP00.Name, "ATPSBP") = 0 Then
		// If INTMVTPP00.rdoColumns("MVTCODMVTS").Value = 1 Then
		// SINM_CODMVT = 1
		// Else
		// Select Case INTSINPP00.rdoColumns("SINCODETSN").Value
		// Case "E": SINM_CODMVT = 50
		// Case "T": SINM_CODMVT = IIf(INTSINPP00.rdoColumns("SINNUMGRAV").Value
		// = 0, 21, 24)
		// Case "S": SINM_CODMVT = IIf(INTSINPP00.rdoColumns("SINNUMGRAV").Value
		// = 0, 22, 25)
		// Case Else: SINM_CODMVT = 0
		// End Select
		// End If

		pstSynchroSinistreVO.setDatMvt(formatter
				.format(getDateMvt(etatsinistre)));
		pstSynchroSinistreVO.setDatpec(pstSynchroSinistreVO.getDatMvt());
		
		// TODO inseret le code sas de l'utilisateur cretateur
		String user = sinistre.getUserCreateur();
		if(user != null && user.length() > 8){
			user = sinistre.getUserCreateur().substring(0, 8);
		}
		pstSynchroSinistreVO.setRedact(user);

		pstSynchroSinistreVO.setTypsin(Integer.valueOf(numeroSinistre
				.substring(3, 4)));
		pstSynchroSinistreVO.setCd_bgd(Integer.valueOf(numeroSinistre
				.substring(4, 8)));
		pstSynchroSinistreVO.setCatsin(Integer.valueOf(numeroSinistre
				.substring(8, 11)));
		pstSynchroSinistreVO.setAnnsin(Integer.valueOf(numeroSinistre
				.substring(11, 15)));
		pstSynchroSinistreVO.setOrdsin(Integer.valueOf(numeroSinistre
				.substring(15, 21)));

		pstSynchroSinistreVO.setRefext(" ");
		pstSynchroSinistreVO.setNusin1(numeroSinistre);
		if (sinistre.getNumeroGrave() != null && sinistre.getNumeroGrave().equals("0")) {
			pstSynchroSinistreVO.setTypdoc(0);
			pstSynchroSinistreVO.setNumdoc("0000000000");
			pstSynchroSinistreVO.setCoddom(11);
			pstSynchroSinistreVO.setCtrres("1");
		} else {
			pstSynchroSinistreVO.setTypdoc(4);
			pstSynchroSinistreVO.setNumdoc(sinistre.getNumeroGrave());
			pstSynchroSinistreVO.setCoddom(12);
			pstSynchroSinistreVO.setCtrres("2");
		}
		pstSynchroSinistreVO.setNusin2(numeroSinistre);
		pstSynchroSinistreVO.setClibgd(sinistre.getCodeClient());
		pstSynchroSinistreVO.setNusin3(numeroSinistre);
		pstSynchroSinistreVO.setPolice(sinistre.getNumeroPolice());
		pstSynchroSinistreVO.setNusin4(numeroSinistre);
		pstSynchroSinistreVO.setDatsur(formatter.format(sinistre
				.getRefEvenement().getDateAccident()));
		pstSynchroSinistreVO.setCatsin1(pstSynchroSinistreVO.getCatsin());
		// TODO: TypeInter + CodeInter lors de l'insertion du sinistre
		pstSynchroSinistreVO.setCodapp(sinistre.getCodeIntermediaire());
		pstSynchroSinistreVO.setNusin5(numeroSinistre);

		// kch
		if (sinistre.getDateDeclaration() != null) {
			pstSynchroSinistreVO.setDatedec(formatter.format(sinistre
					.getDateDeclaration()));
		}

		pstSynchroSinistreVO.setAdres1(getCodeVille(sinistre));
		pstSynchroSinistreVO.setAdres2(getNomprenom(sinistre));
		pstSynchroSinistreVO.setAdres1(getCodeVille(sinistre));
		pstSynchroSinistreVO.setAdres2(getNomprenom(sinistre));
		if (cause != null) {
			pstSynchroSinistreVO.setLibcir1(cause.substring(0, 25));
			pstSynchroSinistreVO
					.setLibcir2(cause.substring(25, cause.length()));
		}
		pstSynchroSinistreVO.setCodbgd(pstSynchroSinistreVO.getCd_bgd());
		pstSynchroSinistreVO.setAnnsin1(pstSynchroSinistreVO.getAnnsin());
		pstSynchroSinistreVO.setOrdsin1(pstSynchroSinistreVO.getOrdsin());
		pstSynchroSinistreVO.setNumclc(Integer.valueOf(numPolice
				.substring(0, 5)));
		if(numgrave != null){
		pstSynchroSinistreVO.setCodeta(getCodeEtat(numgrave, etatsinistre));
		}

		// Bon de pharmacie
		// If InStr(1, INTSINPP00.Name, "ATPSBP") = 0 Then
		// Select Case INTMVTPP00.rdoColumns("MVTANCETAT").Value &
		// INTMVTPP00.rdoColumns("MVTNOVETAT").Value
		// Case "TE": SINM_CODETA = IARD_RG017(CodeBranche, "RT",
		// INTSINPP00.rdoColumns("SINNUMGRAV").Value)
		// Case "SE": SINM_CODETA = IARD_RG017(CodeBranche, "RS",
		// INTSINPP00.rdoColumns("SINNUMGRAV").Value)
		// Case Else: SINM_CODETA = IARD_RG017(CodeBranche,
		// INTMVTPP00.rdoColumns("MVTNOVETAT").Value,
		// INTSINPP00.rdoColumns("SINNUMGRAV").Value)
		// End Select
		if (etatsinistre != null) {
			pstSynchroSinistreVO.setDateta(formatter.format(etatsinistre
					.getDateEtat()));
		}
		pstSynchroSinistreVO.setCcateg(Integer.valueOf(numPolice
				.substring(5, 9)));

		pstSynchroSinistreVO.setTypctr(getTypeContrat(sinistre));
		// a impoter depuis l'existant table correspondance
		// classe/codeClasse
		// pstSynchroSinistreVO.setCodact(0);
		// Case 1, 3, 4, 5, 6, 7: SINM_CODACT =
		// Case 1: pcl_cactiv = Mid(fCheckCorr(CodeBranche, 5,
		// Format(INTPLCPP00("PLCCODCLAS").Value, "00"), "CodeAlw", 1,
		// "IntAlwPlc"), 1, 2)

		// a impoter depuis l'existant table correspondance
		// classe/codeClasse
		// pstSynchroSinistreVO.setSouact(99);
		// Case 1, 3, 4, 5, 6, 7: SINM_SOUACT =
		// Case 1: pcl_csacti = Mid(Format(fCheckCorr(CodeBranche, 5,
		// Format(INTPLCPP00("PLCCODCLAS").Value, "00"), "CodeAlw", 1,
		// "IntAlwPlc"), "00000"), 3, 2)

		// a impoter depuis l'existant code Validiter
		// pstSynchroSinistreVO.setEtapol(String.valueOf(situationContrat.getRefStatut().getId()));
		// Case 1, 3, 4, 5, 6: SINM_ETAPOL = Case 1: pcl_etapol =
		// Format(AT_RG05(INTPLCPP00("PLCETATPLC").Value,
		// INTPLCPP00("PLCCODVALD").Value), "00")
		if (sinistre.getDateEtatOuverture() != null) {
			pstSynchroSinistreVO.setDetpol(formatter.format(sinistre
					.getDateEtatOuverture()));
		}
		if (sinistre.getDateEffet()!= null) {
			pstSynchroSinistreVO.setDefpol(formatter.format(sinistre
					.getDateEffet()));
		}

		// a impoter depuis l'existant Date occurence
		// pstSynchroSinistreVO.setDatdbg(DateUtile.calendarToString(DATEFORMAT,
		// Case 1: SINM_DATDBG =Case 1: PCL_DATEVT =
		// (INTPLCPP00("PLCDAAOCCU").Value * 10000) +
		// (INTPLCPP00("PLCDMMOCCU").Value * 100) +
		// INTPLCPP00("PLCDJJOCCU").Value

		// pstSynchroSinistreVO.setDatfng();
		// a impoter depuis l'existant Date echeance
		// Case 1, 3, 4, 5, 6: Select Case INTPLCPP00("PLCNATCNTR").Value
		// Case "T": Select Case INTPLCPP00("PLCPRDPAIE").Value
		// Case "A": pcl_datech = (INTPLCPP00("PLCDAAECHE").Value * 10000) +
		// (INTPLCPP00("PLCDMMECHE").Value * 100) +
		// INTPLCPP00("PLCDJJECHE").Value
		// Case Else:
		// If INTPLCPP00("PLCDMMECHE").Value <> INTPLCPP00("PLCDMMECHA").Value
		// Then
		// pcl_datech = ((INTPLCPP00("PLCDAAECHE").Value + 1) * 10000) +
		// (INTPLCPP00("PLCDMMECHA").Value * 100) +
		// INTPLCPP00("PLCDJJECHA").Value
		// Else
		// pcl_datech = (INTPLCPP00("PLCDAAECHE").Value * 10000) +
		// (INTPLCPP00("PLCDMMECHE").Value * 100) +
		// INTPLCPP00("PLCDJJECHE").Value
		// End If
		// End Select
		// Case Else: pcl_datech = (INTPLCPP00("PLCDAAEXPR").Value * 10000) +
		// (INTPLCPP00("PLCDMMEXPR").Value * 100) +
		// INTPLCPP00("PLCDJJEXPR").Value
		// End Select

		pstSynchroSinistreVO.setCodgar2(getCodeGar(typeGar));
		pstSynchroSinistreVO.setRefpol(sinistre.getNumeroPolice());
		pstSynchroSinistreVO.setRefsin(numeroSinistre);
		pstSynchroSinistreVO.setBgdsin(numeroSinistre.substring(0, 9));
		pstSynchroSinistreVO.setDatpec1(pstSynchroSinistreVO.getHmnpec());
		pstSynchroSinistreVO.setAffilie(Double.valueOf(numAffilie));
		pstSynchroSinistreVO.setDate_branche(formatter.format(new Date()));

		return pstSynchroSinistreVO;

	}

	/**
	 * getCodeGar
	 * 
	 * @param victime
	 * @return
	 */
	private String getCodeGar(TypeGarantie typeGar) {
		String codeGaranti = "002";
		if (typeGar != null && typeGar.getCode().equals("2")) {
			codeGaranti = "000";
		}
		return codeGaranti;
	}

	/**
	 * getTypeContrat
	 * 
	 * @param sinistre
	 * @return
	 */
	private Integer getTypeContrat(Sinistre sinistre) {
		Integer typeContrat;
		String naturePolice = sinistre.getTypeContrat();
		if (naturePolice != null) {
			if (naturePolice.equals("TERME")) {
				typeContrat = 10;
			} else {
				typeContrat = 6;
			}
		} else {
			typeContrat = 6;
		}
		return typeContrat;
	}

	/**
	 * getCodeEtat
	 * 
	 * @param numgrave
	 * @param etatsinistre
	 * @return
	 * @throws FonctionnelleException
	 */
	private Integer getCodeEtat(String numgrave, EtatSinistre etatsinistre)
			throws FonctionnelleException {
		Integer code;
		if (etatsinistre.getRefEtat() != null) {
			String codeEtat = etatsinistre.getRefEtat().getCode();
			if (codeEtat.equals("1") || codeEtat.equals("2")) {
				code = 1;
			} else if (codeEtat.equals("4") || codeEtat.equals("0")) {
				if (numgrave.equals("0")) {
					code = 22;
				} else {
					code = 25;
				}
			} else if (codeEtat.equals("3")) {
				if (numgrave.equals("0")) {
					code = 21;
				} else {
					code = 24;
				}
			} else {
				code = 1;
			}
		} else {
			logger.error(EXP_ETAT);
			throw new FonctionnelleException(EXP_ETAT);
		}
		return code;
	}

	/**
	 * getLibCause
	 * 
	 * @param sinistre
	 * @return
	 * @throws FonctionnelleException
	 */
	private String getLibCause(Sinistre sinistre) throws FonctionnelleException {
		String libelleCause = null;
		try {
			List<Cause> listCause = (List) parametrageManager
					.getListCause(null);
			if (sinistre.getRefEvenement().getRefCause() != null) {
				String causeSinistre = sinistre.getRefEvenement().getRefCause()
						.getCode();
				for (Cause cause : listCause) {
					if (causeSinistre == cause.getCode()) {
						libelleCause = cause.getLibelle();
						return libelleCause;
					}
				}
			}
			// else {
			// logger.error(EXP_EVENEMENT);
			// throw new FonctionnelleException(EXP_EVENEMENT);
			// }
		} catch (PersistenceException e) {
			throw new FonctionnelleException(e);
		}
		return null;
	}

	/**
	 * getNomprenom
	 * 
	 * @param sinistre
	 * @return
	 * @throws FonctionnelleException
	 */
	private String getNomprenom(Sinistre sinistre)
			throws FonctionnelleException {
		String nomPrenom = null;
		if (sinistre.getRefVictime() != null) {
			String nom = sinistre.getRefVictime().getNomprenom();
			if(nom != null && nom.length()>25){
				nomPrenom = nom.substring(0,24);
			}
		} else {
			logger.error(EXP_VICTIME);
			throw new FonctionnelleException(EXP_VICTIME);
		}
		return nomPrenom;
	}

	/**
	 * getCodeVille
	 * 
	 * @param sinistre
	 * @return
	 * @throws FonctionnelleException
	 */
	private String getCodeVille(Sinistre sinistre)
			throws FonctionnelleException {
		String codeVille = null;
		if (sinistre.getRefVictime() != null) {
			codeVille = String.valueOf(sinistre.getRefVictime().getCodeVille());
		} else {
			logger.error(EXP_VILLE);
			throw new FonctionnelleException(EXP_VILLE);
		}

		return codeVille;
	}

	/**
	 * getDateMvt
	 * 
	 * @param etatsinistre
	 * @return
	 * @throws FonctionnelleException
	 */
	private Date getDateMvt(EtatSinistre etatsinistre) throws FonctionnelleException {
		Date date;
		if (etatsinistre != null) {
			date = etatsinistre.getDateEtat();
			
		} else {
			logger.error(EXP_DATEMVT);
			throw new FonctionnelleException(EXP_DATEMVT);
		}
		return date;
	}

	/**
	 * getCodeMvt
	 * 
	 * @param codeEtat
	 * @param numgrave
	 * @return
	 * @throws FonctionnelleException
	 */
	private int getCodeMvt(String codeEtat, String numgrave)
			throws FonctionnelleException {
		int codeMvt = 0;
		try {
			if (codeEtat.equals("1")) {
				codeMvt = 50;
			} else if (codeEtat.equals("3")) {
				if (numgrave == null || numgrave.equals("0")) {
					codeMvt = 21;
				} else {
					codeMvt = 24;
				}
			} else if (codeEtat.equals("4")) {
				if (numgrave == null || numgrave.equals("0")) {
					codeMvt = 22;
				} else {
					codeMvt = 25;
				}
			} else {
				codeMvt = 0;
			}
			return codeMvt;
		} catch (Exception e) {
			logger.error(EXP_CODEMVT, e);
			throw new FonctionnelleException(EXP_CODEMVT);
		}

	}

	/**
	 * chargerInfosCharges
	 * 
	 * @param synchroSinistreVO
	 * @return
	 * @throws Exception
	 */
	public PstSynchroChargesVO chargerInfosCharges(
			PstSynchroSinistreVO synchroSinistreVO) throws Exception {

		PstSynchroChargesVO pstSynchroChargesVO = new PstSynchroChargesVO();

		pstSynchroChargesVO.setCtrres(1);
		pstSynchroChargesVO.setResouv(0d);
		pstSynchroChargesVO.setCumpai(0d);
		pstSynchroChargesVO.setCumrec(0d);
		pstSynchroChargesVO.setFraisd(0d);
		pstSynchroChargesVO.setRcours(0d);
		pstSynchroChargesVO.setDatmaj(synchroSinistreVO.getDatMvt());
		pstSynchroChargesVO.setDatpec(synchroSinistreVO.getDatMvt());
		pstSynchroChargesVO.setCodsoc(2);
		pstSynchroChargesVO.setTypsin(Integer.valueOf(synchroSinistreVO.getTypsin()));
		pstSynchroChargesVO.setCd_bgd(Integer.valueOf(synchroSinistreVO.getCd_bgd()));
		pstSynchroChargesVO.setCatsin(Integer.valueOf(synchroSinistreVO.getCatsin()));
		pstSynchroChargesVO.setAnnsin(Integer.valueOf(synchroSinistreVO.getAnnsin()));
		pstSynchroChargesVO.setOrdsin(Integer.valueOf(synchroSinistreVO.getOrdsin()));
		pstSynchroChargesVO.setCatres(pstSynchroChargesVO.getCatsin());
		pstSynchroChargesVO.setDate_branche(synchroSinistreVO.getDate_branche());
		return pstSynchroChargesVO;
	}
}
