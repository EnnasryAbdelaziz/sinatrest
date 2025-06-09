
package rma.notification.service.interfaces.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for creerSms complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="creerSms">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="smsVO" type="{http://ws.interfaces.service.notification.rma/}smsVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "creerSms", propOrder = {
    "smsVO"
})
public class CreerSms {

    protected SmsVO smsVO;

    /**
     * Gets the value of the smsVO property.
     * 
     * @return
     *     possible object is
     *     {@link SmsVO }
     *     
     */
    public SmsVO getSmsVO() {
        return smsVO;
    }

    /**
     * Sets the value of the smsVO property.
     * 
     * @param value
     *     allowed object is
     *     {@link SmsVO }
     *     
     */
    public void setSmsVO(SmsVO value) {
        this.smsVO = value;
    }

}
