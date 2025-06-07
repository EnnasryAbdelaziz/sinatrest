package eai.devass.sinistreat.appli.valueobjects.metier.conciliation;

import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

@SuppressWarnings("serial")
public class DelegationVO  implements IValueObject{

	private long id;
	private String seuilOperation;
	private String codeSas;
	private UserVO refUser;
	private List<UserDelegationVO> listUserDelegation;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSeuilOperation() {
		return seuilOperation;
	}

	public void setSeuilOperation(String seuilOperation) {
		this.seuilOperation = seuilOperation;
	}



	public void setCodeSas(String codeSas) {
		this.codeSas = codeSas;
	}

	public String getCodeSas() {
		return codeSas;
	}

	public UserVO getRefUser() {
		return refUser;
	}

	public void setRefUser(UserVO refUser) {
		this.refUser = refUser;
	}

	public List<UserDelegationVO> getListUserDelegation() {
		return listUserDelegation;
	}

	public void setListUserDelegation(List<UserDelegationVO> listUserDelegation) {
		this.listUserDelegation = listUserDelegation;
	}

}
