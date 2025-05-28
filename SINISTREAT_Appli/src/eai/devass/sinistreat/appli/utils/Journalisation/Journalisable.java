package eai.devass.sinistreat.appli.utils.Journalisation;

import java.util.Date;


/* @author kchakib : 13 oct. 10 */
public abstract class Journalisable {
	
	private Long id;
	
	/** L'objet Journal Action  */
//	private JournalAction journalAction;
	private Date dateModification;

	
	
	public Journalisable() {
		
	}
	public Journalisable(Long id) {
		super();
		this.id = id;
	}


	public Date getDateModification() {
		return dateModification;
	}

	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	

}


