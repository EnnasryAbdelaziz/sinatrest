package eai.devass.sinistreat.appli.usecase.metier.instruction;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.instruction.Instruction;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;

public class ListeInstructionUC extends BaseUC {

	@Override
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);
		Instruction i = (Instruction) this.getItem(Instruction.class);
		if (i == null) {
			throw new FonctionnelleException(EXP_INSTRUCTION_INEXISTANTE);
		}
		
		
		if (pagerVO != null && pagerVO.getNbrLignes() != null) {
			if (pagerVO.getNumPage() == null) {
				throw new FonctionnelleException(
						EXP_PAGER_NUMERO_PAGE_OBLIGATOIRE);
			} else if (pagerVO.getPageSize() == null) {
				throw new FonctionnelleException(
						EXP_PAGER_PAGE_SIZE_OBLIGATOIRE);
			}
			if ("1".equals(pagerVO.getNbrLignes())) {
				Integer nbreObject = instructionManager.getNombreInstructionByIdSinistre(i);
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
		
		List<Instruction> listInstruction = instructionManager.getInstructionByIdSinistre(i, pagerVO);

		addResultItem(listInstruction);
		
		if (pagerVO != null) {
			this.addResultItem(pagerVO);
		}
	}

}
