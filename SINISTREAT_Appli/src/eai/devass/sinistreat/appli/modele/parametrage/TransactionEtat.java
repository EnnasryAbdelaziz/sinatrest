package eai.devass.sinistreat.appli.modele.parametrage;

import java.io.Serializable;




/** @author Hibernate CodeGenerator */
public class TransactionEtat implements Serializable {

	private static final long serialVersionUID = 1L;
	
    /** identifier field */
    private Integer id;

    /** persistent field */
    private Long refProfil;
    
    /** persistent field */
    private String typeQuittance;
     
    /** persistent field */
    private Integer codSitQtc;
    
    /** persistent field */
    private Integer codSitCible;
    
     /** default constructor */
    public TransactionEtat() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
	public String getTypeQuittance() {
		return typeQuittance;
	}

	public void setTypeQuittance(String typeQuittance) {
		this.typeQuittance = typeQuittance;
	}

	public Long getRefProfil() {
		return refProfil;
	}

	public void setRefProfil(Long refProfil) {
		this.refProfil = refProfil;
	}

	public Integer getCodSitCible() {
		return codSitCible;
	}

	public void setCodSitCible(Integer codSitCible) {
		this.codSitCible = codSitCible;
	}

	public Integer getCodSitQtc() {
		return codSitQtc;
	}

	public void setCodSitQtc(Integer codSitQtc) {
		this.codSitQtc = codSitQtc;
	}

	
    

}
