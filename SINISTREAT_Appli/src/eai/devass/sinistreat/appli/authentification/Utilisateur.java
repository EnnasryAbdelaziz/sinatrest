package eai.devass.sinistreat.appli.authentification;

import static eai.devass.sinistreat.appli.utils.validation.Validate.Context.CREATE;
import static eai.devass.sinistreat.appli.utils.validation.Validate.Context.UPDATE;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.organisation.IUniteOrganisationnelle;
import ma.co.omnidata.framework.services.securite.IRole;
import ma.co.omnidata.framework.services.securite.IUtilisateur;
import eai.devass.sinistreat.appli.utils.validation.Validate;
import eai.devass.sinistreat.appli.utils.validation.Validate.Type;
import eai.devass.sinistreat.appli.valueobjects.parametrage.EntiteOrgVO;

/**
* 
* 
*/
public class Utilisateur implements IUtilisateur,IValueObject {
	
	private static final long serialVersionUID = 1L;
	
	@Validate(autoGenerate=true, type=Type.LONG)
	private Long idUser;
	@Validate(obligatoire=true, context={UPDATE,CREATE})
	private String login;
	@Validate(obligatoire=true, context={UPDATE,CREATE})
	private String codeUser;
	private String nomUser;
	@Validate(obligatoire=true, context={UPDATE,CREATE})
	private String motdepasse;
	private Boolean interne;
	private List<Profil> refProfils;
	private List<Fonctionnalite> refFonctionnalite; 
	private String mail;
	private String gsm;
	private EntiteOrgVO refEntite;
	private String matricule;
	@Validate(obligatoire=true, context={UPDATE,CREATE})
	private String typeUser;	
	@Validate(obligatoire=true, context={UPDATE})
	private String actif;
	
	public Utilisateur() {
	
	}
	
	
	public Utilisateur(String codeUser) {
		this.codeUser = codeUser;
	}


	public Utilisateur(String codeUser, String nomUser) {
		this.codeUser = codeUser;
		this.nomUser = nomUser;
	}

	

	public void addRole(IRole role) {
		// TODO Auto-generated method stub
		
	}

	

	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public List getRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean isBlocked() {
		// TODO Auto-generated method stub
		return false;
	}

	public Boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	public void removeRole(IRole role) {
		// TODO Auto-generated method stub
		
	}

	public void setBlocked(boolean blocked) {
		// TODO Auto-generated method stub
		
	}

	public void setConnected(boolean connected) {
		// TODO Auto-generated method stub
		
	}	
	

	public String getCodeUser() {
		return codeUser;
	}

	public void setCodeUser(String codeUser) {
		this.codeUser = codeUser;
	}

	public String getNomUser() {
		return nomUser;
	}

	public void setNomUser(String nomUser) {
		this.nomUser = nomUser;
	}


	public List<Profil> getRefProfils() {
		return refProfils;
	}


	public void setRefProfils(List<Profil> refProfils) {
		this.refProfils = refProfils;
	}
	
	
	public Boolean getInterne() {
		return interne;
	}


	public void setInterne(Boolean interne) {
		this.interne = interne;
	}
	


	public List<Fonctionnalite> getRefFonctionnalite() {
		return refFonctionnalite;
	}


	public void setRefFonctionnalite(List<Fonctionnalite> refFonctionnalite) {
		this.refFonctionnalite = refFonctionnalite;
	}
	


	public String getGsm() {
		return gsm;
	}


	public void setGsm(String gsm) {
		this.gsm = gsm;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public EntiteOrgVO getRefEntite() {
		return refEntite;
	}


	public void setRefEntite(EntiteOrgVO refEntite) {
		this.refEntite = refEntite;
	}


	public String getMotdepasse() {
		return motdepasse;
	}
	public void setMotdepasse(String motdepasse) {
		this.motdepasse = motdepasse;
	}
	/**
	 * Ajouter un nouveau Profil dans la listes des profils utilisateurs 
	 * @param profil
	 */
	public void addProfil(Profil profil) {
		if(getRefProfils() == null) {
			setRefProfils(new ArrayList<Profil>());
		}
		
		getRefProfils().add(profil);
		
	}


	public String getMatricule() {
		return matricule;
	}


	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public Long getIdUser() {
		return idUser;
	}


	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}


	public String getTypeUser() {
		return typeUser;
	}


	public void setTypeUser(String typeUser) {
		this.typeUser = typeUser;
	}


	public String getActif() {
		return actif;
	}


	public void setActif(String actif) {
		this.actif = actif;
	}


	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}


	public void setId(long id) {
		// TODO Auto-generated method stub
		
	}

	public String getConsultation(){
		return "<IMG src=\"img/view.gif\" style=\"cursor:pointer\" onclick=\"execAction('gestionUtilisateur.do?actions=consultationUtilisateur&idUser=" + this.getIdUser() +"')\"/>";
	}


	public String getConnectedRole() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getMotdepasseClair() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getNom() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getPrenom() {
		// TODO Auto-generated method stub
		return null;
	}


	public IUniteOrganisationnelle getRefUniteOrganisationnelle() {
		// TODO Auto-generated method stub
		return null;
	}


	public void setBlocked(Boolean arg0) {
		// TODO Auto-generated method stub
		
	}


	public void setConnected(Boolean arg0) {
		// TODO Auto-generated method stub
		
	}


	public void setConnectedRole(String arg0) {
		// TODO Auto-generated method stub
		
	}


	public void setMotdepasseClair(String arg0) {
		// TODO Auto-generated method stub
		
	}


	public void setNom(String arg0) {
		// TODO Auto-generated method stub
		
	}


	public void setPrenom(String arg0) {
		// TODO Auto-generated method stub
		
	}


	public void setRefUniteOrganisationnelle(IUniteOrganisationnelle arg0) {
		// TODO Auto-generated method stub
		
	}


	public void setRoles(List arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}
