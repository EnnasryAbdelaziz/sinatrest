package eai.devass.sinistreat.appli.usecase.metier.instruction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eai.devass.edition.Utils.IConstantes;
import eai.devass.edition.valueobjects.EditionVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.instruction.Instruction;
import eai.devass.sinistreat.appli.usecase.edition.EditerJrxmlDocxUC;
import eai.devass.sinistreat.appli.utils.entites.IDate;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.dao.PersistenceException;

public class EditerInstructionUC extends EditerJrxmlDocxUC {
	private final SimpleDateFormat dateFormat = new SimpleDateFormat(
			IDate.FORMAT_SIMPLE);

	@Override
	protected String getCodeTemplate(EditionVO editionVO) {
		return null;
	}

	@Override
	protected boolean imprimer(IValueObject vo) {
		return false;
	}

	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo)
			throws FonctionnelleException {
		Map<String, Object> params = new HashMap<String, Object>();
		EditionVO editionVO = (EditionVO) vo;
		params.put("DATE_EDITION", dateFormat.format(new Date()));
		params.put("USER_CONNECTED", editionVO.getInstructionVO()
				.getUserEditeur());
		params.put("INIT_USER_CONNECTED", editionVO.getInstructionVO()
				.getUserEditeur());
		params.put("ID_INSTRUCTION", editionVO.getInstructionVO().getId());

		// Add to param list piece cochée
		List<String> pieces = instructionManager
				.getSelectedPiecesByInstruction(editionVO.getInstructionVO()
						.getId());
						
		if (pieces != null && !pieces.isEmpty()) {
	        pieces.remove("Autres");
	     }
		
		params.put("PIECES", pieces);
		
		// Add to param list rejets cochée
		List<String> rejets = instructionManager
				.getSelectedRejetsByInstruction(editionVO.getInstructionVO().getId());

		if (rejets != null && !rejets.isEmpty()) {
			rejets.remove("Autres");
	     }
		
		params.put("REJETS", rejets);

		return params;
	}

	@Override
	protected List<String> getCodesTemplate(EditionVO editionVO) {
		// le code template change selon la catégorie de l'instruction et le
		// type de la lettre.
		List<String> templates = new ArrayList<String>();
		Instruction i;
		try {
			i = instructionManager.getInstructionById(editionVO
					.getInstructionVO().getId());
			if ("1".equals(i.getCategorieInstruction().getCode())) {
				if ("1".equals(i.getTypeLettreInstruction().getCode())) {
					templates.add(IConstantes.CODE_TEMPLATE_LETTRE_RECLAMATION_DOCUMENT);
				}
				if ("2".equals(i.getTypeLettreInstruction().getCode())) {
					templates.add(IConstantes.CODE_TEMPLATE_LETTRE_MISSIONNEMENT);
				}
				if ("3".equals(i.getTypeLettreInstruction().getCode())) {
					templates.add(IConstantes.CODE_TEMPLATE_LETTRE_CONTREVISITE);
					templates.add(IConstantes.CODE_TEMPLATE_LETTRE_CONTREVISITE_MC);
				}
				if ("4".equals(i.getTypeLettreInstruction().getCode())) {
					templates.add(IConstantes.CODE_TEMPLATE_LETTRE_RECLAMATION_REJET);
				}
				if ("5".equals(i.getTypeLettreInstruction().getCode())) {
					templates.add(IConstantes.CODE_TEMPLATE_LETTRE_AVISCONTREVISITEPREM);
				}
				if ("6".equals(i.getTypeLettreInstruction().getCode())) {
					templates.add(IConstantes.CODE_TEMPLATE_LETTRE_AVISCONTREVISITESEC);
				}
				if ("7".equals(i.getTypeLettreInstruction().getCode())) {
					templates.add(IConstantes.CODE_TEMPLATE_LETTRE_AVISSUSPENSIONVICTIME);
					templates.add(IConstantes.CODE_TEMPLATE_LETTRE_AVISSUSPENSIONDELEGATION);
				}
			} else if ("2".equals(i.getCategorieInstruction().getCode())) {
				 if ("1".equals(i.getTypeInstruction().getCode())) {
					 templates.add(IConstantes.CODE_TEMPLATE_DEMANDE_DE_PIECE);
				} else if ("2".equals(i.getTypeInstruction().getCode())) {
					templates.add(IConstantes.CODE_TEMPLATE_REJET_DE_LA_DEMANDE);
				} else if ("3".equals(i.getTypeInstruction().getCode())) {
					templates.add(IConstantes.CODE_TEMPLATE_CLASSEMENT_POUR_GUERISON_SANS_IPP);
				} else if ("4".equals(i.getTypeInstruction().getCode())) {
					templates.add(IConstantes.CODE_TEMPLATE_OFFRE_AVANT_EXPERTISE);
				} else if ("5".equals(i.getTypeInstruction().getCode())) {
					templates.add(IConstantes.CODE_TEMPLATE_EXPERTISE);
				} else if ("6".equals(i.getTypeInstruction().getCode())) {
					templates.add(IConstantes.CODE_TEMPLATE_OFFRE_APRES_EXPERTISE_VTE); //Validation Taux expert
				} else if ("7".equals(i.getTypeInstruction().getCode())) {
					templates.add(IConstantes.CODE_TEMPLATE_OFFRE_APRES_EXPERTISE_PT); //proposition Taux
				} else if ("8".equals(i.getTypeInstruction().getCode())) {
					templates.add(IConstantes.CODE_TEMPLATE_ASSISTANCE_A_EXPERTISE_MEDECIN_CONSEIL);
				} else if ("9".equals(i.getTypeInstruction().getCode())) {
					templates.add(IConstantes.CODE_TEMPLATE_CONTESTATION_EXPERTISE);
				} else if ("10".equals(i.getTypeInstruction().getCode())) {
					templates.add(IConstantes.CODE_TEMPLATE_RECLAMATION_COPIE_RAPPORT_EXPERTISE);
				} else if ("11".equals(i.getTypeInstruction().getCode())) {
					templates.add(IConstantes.CODE_TEMPLATE_RECLAMATION_COPIE_DECISION);
				} else if ("15".equals(i.getTypeInstruction().getCode())) {
					templates.add(IConstantes.CODE_TEMPLATE_REJET_ABSENCE_CARACTERE_AT); /// vide
				} else if ("16".equals(i.getTypeInstruction().getCode())) {
					templates.add(IConstantes.CODE_TEMPLATE_DECHEANCE);
				} else if ("17".equals(i.getTypeInstruction().getCode())) {
					templates.add(IConstantes.CODE_TEMPLATE_MISE_HORS_DE_CAUSE);
				} else if ("18".equals(i.getTypeInstruction().getCode())) {
					templates.add(IConstantes.CODE_TEMPLATE_DECONSTITUTION);
				} else if ("19".equals(i.getTypeInstruction().getCode())) {
					templates.add(IConstantes.CODE_TEMPLATE_LETTRE_APPEL_DN); //Decision notifiée
				} else if ("20".equals(i.getTypeInstruction().getCode())) {
					templates.add(IConstantes.CODE_TEMPLATE_LETTRE_APPEL_DNN); //Decision non notifiée
				} else if ("21".equals(i.getTypeInstruction().getCode())) {
					templates.add(IConstantes.CODE_TEMPLATE_TROP_PERÇU);
				} else if ("22".equals(i.getTypeInstruction().getCode())) {
					templates.add(IConstantes.CODE_TEMPLATE_INDU_MISE_HORS_DE_CAUSE); //Indu (Mise hors de cause)
				} else if ("23".equals(i.getTypeInstruction().getCode())) {
					templates.add(IConstantes.CODE_TEMPLATE_CONSTITUTION_SA_TITRE_EXECUTOIRE);
				} else if ("24".equals(i.getTypeInstruction().getCode())) {
					templates.add(IConstantes.CODE_TEMPLATE_CONSTITUTION_SA_MAIN_LEVEE);
				} else if ("25".equals(i.getTypeInstruction().getCode())) {
					templates.add(IConstantes.CODE_TEMPLATE_MAIN_LEVEE_SA);	// vide
				} else if ("26".equals(i.getTypeInstruction().getCode())) {
					templates.add(IConstantes.CODE_TEMPLATE_POURVOI_EN_CASSATION); // vide
				} else if ("27".equals(i.getTypeInstruction().getCode())) {
					templates.add(IConstantes.CODE_TEMPLATE_HONORAIRE_DEJA_REGLEE);
				} else if ("28".equals(i.getTypeInstruction().getCode())) {
					templates.add(IConstantes.CODE_TEMPLATE_MP_NON_DECLAREE);
				} else if ("29".equals(i.getTypeInstruction().getCode())) {
					templates.add(IConstantes.CODE_TEMPLATE_AUTRE);
				}
			}
		} catch (FonctionnelleException e) {
			e.printStackTrace();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}

		return templates;
	}

}
