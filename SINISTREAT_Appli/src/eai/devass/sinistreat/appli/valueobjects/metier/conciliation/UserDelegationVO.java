package eai.devass.sinistreat.appli.valueobjects.metier.conciliation;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

@SuppressWarnings("serial")
public class UserDelegationVO implements IValueObject{

	private long id;
	private DelegationVO refDelegation;
	private UserVO refUser;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setRefDelegation(DelegationVO refDelegation) {
		this.refDelegation = refDelegation;
	}

	public DelegationVO getRefDelegation() {
		return refDelegation;
	}

	public void setRefUser(UserVO refUser) {
		this.refUser = refUser;
	}

	public UserVO getRefUser() {
		return refUser;
	}

}
