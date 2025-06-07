package eai.devass.sinistreat.appli.valueobjects.metier.reglement;

import java.util.List;

public class ReglementRelanceITTVO {

	private String date;
	private List<ReglementVO> lisReglementVOs;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<ReglementVO> getLisReglementVOs() {
		return lisReglementVOs;
	}

	public void setLisReglementVOs(List<ReglementVO> lisReglementVOs) {
		this.lisReglementVOs = lisReglementVOs;
	}

}
