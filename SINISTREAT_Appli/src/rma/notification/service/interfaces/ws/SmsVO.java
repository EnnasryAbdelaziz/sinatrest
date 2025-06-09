
package rma.notification.service.interfaces.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for smsVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="smsVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="application" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="branche" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codeEvent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codeEventDec" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="commentaire" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dateEnvoi" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="expediteur" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroGSM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="objet" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="service" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "smsVO", propOrder = {
    "application",
    "branche",
    "codeEvent",
    "codeEventDec",
    "commentaire",
    "dateEnvoi",
    "expediteur",
    "message",
    "numeroGSM",
    "objet",
    "service"
})
public class SmsVO {

    protected String application;
    protected String branche;
    protected String codeEvent;
    protected String codeEventDec;
    protected String commentaire;
    protected Long dateEnvoi;
    protected String expediteur;
    protected String message;
    protected String numeroGSM;
    protected String objet;
    protected String service;

    /**
     * Gets the value of the application property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplication() {
        return application;
    }

    /**
     * Sets the value of the application property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplication(String value) {
        this.application = value;
    }

    /**
     * Gets the value of the branche property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBranche() {
        return branche;
    }

    /**
     * Sets the value of the branche property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBranche(String value) {
        this.branche = value;
    }

    /**
     * Gets the value of the codeEvent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodeEvent() {
        return codeEvent;
    }

    /**
     * Sets the value of the codeEvent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodeEvent(String value) {
        this.codeEvent = value;
    }

    /**
     * Gets the value of the codeEventDec property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodeEventDec() {
        return codeEventDec;
    }

    /**
     * Sets the value of the codeEventDec property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodeEventDec(String value) {
        this.codeEventDec = value;
    }

    /**
     * Gets the value of the commentaire property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommentaire() {
        return commentaire;
    }

    /**
     * Sets the value of the commentaire property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommentaire(String value) {
        this.commentaire = value;
    }

    /**
     * Gets the value of the dateEnvoi property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDateEnvoi() {
        return dateEnvoi;
    }

    /**
     * Sets the value of the dateEnvoi property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDateEnvoi(Long value) {
        this.dateEnvoi = value;
    }

    /**
     * Gets the value of the expediteur property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpediteur() {
        return expediteur;
    }

    /**
     * Sets the value of the expediteur property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpediteur(String value) {
        this.expediteur = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the numeroGSM property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroGSM() {
        return numeroGSM;
    }

    /**
     * Sets the value of the numeroGSM property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroGSM(String value) {
        this.numeroGSM = value;
    }

    /**
     * Gets the value of the objet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjet() {
        return objet;
    }

    /**
     * Sets the value of the objet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjet(String value) {
        this.objet = value;
    }

    /**
     * Gets the value of the service property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getService() {
        return service;
    }

    /**
     * Sets the value of the service property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setService(String value) {
        this.service = value;
    }

}
