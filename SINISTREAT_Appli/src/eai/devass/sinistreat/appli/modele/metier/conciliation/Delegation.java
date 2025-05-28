package eai.devass.sinistreat.appli.modele.metier.conciliation;

import java.io.Serializable;
import java.util.List;


@SuppressWarnings("serial")
public class Delegation  implements Serializable{
	
	private long id;
	private Double seuilOperation;
	private String codeSas;
	private User refUser;
	private List <UserDelegation> listUserDelegation;
	private transient String[] propertiesToConvert;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setSeuilOperation(Double seuilOperation) {
		this.seuilOperation = seuilOperation;
	}
	public Double getSeuilOperation() {
		return seuilOperation;
	}
	public void setCodeSas(String codeSas) {
		this.codeSas = codeSas;
	}
	public String getCodeSas() {
		return codeSas;
	}
	public User getRefUser() {
		return refUser;
	}
	public void setRefUser(User refUser) {
		this.refUser = refUser;
	}
	public void setListUserDelegation(List <UserDelegation> listUserDelegation) {
		this.listUserDelegation = listUserDelegation;
	}
	public List <UserDelegation> getListUserDelegation() {
		return listUserDelegation;
	}
	public void setPropertiesToConvert(String[] propertiesToConvert) {

		String[] copyPropertiesToConvert = null;
		if (propertiesToConvert != null) {
			copyPropertiesToConvert = propertiesToConvert.clone();
		}

		this.propertiesToConvert = copyPropertiesToConvert;
	}

	public String[] getPropertiesToConvert() {
		return propertiesToConvert;
	}
	
}
