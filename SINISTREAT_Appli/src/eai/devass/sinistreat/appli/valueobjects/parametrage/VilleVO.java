package eai.devass.sinistreat.appli.valueobjects.parametrage;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VilleVO extends ParamATVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// @Validate(obligatoire=true)

	private String code;
	private String codeVille;
	private String idCodeVille;

	private String libelle;
	private PaysVO refPays;

	public VilleVO() {

	}

	public VilleVO(String code) {
		this.code = code;
	}

	public VilleVO(String code, String libelle) {
		this.code = code;
		this.libelle = libelle;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		if (StringUtils.isEmpty(code)) {
			code = "99";
		}
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getCodeVille() {
		return codeVille;
	}

	public String getIdCodeVille() {
		return idCodeVille;
	}

	public void setCodeVille(String codeVille) {
		this.codeVille = codeVille;
	}

	public void setIdCodeVille(String idCodeVille) {
		this.idCodeVille = idCodeVille;
	}

	public PaysVO getRefPays() {
		return refPays;
	}

	public void setRefPays(PaysVO refPays) {
		this.refPays = refPays;
	}

	
}