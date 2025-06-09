
package rma.notification.service.interfaces.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rma.notification.service.interfaces.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SendSMSScheduleResponse_QNAME = new QName("http://ws.interfaces.service.notification.rma/", "sendSMSScheduleResponse");
    private final static QName _SendSMSImmediate_QNAME = new QName("http://ws.interfaces.service.notification.rma/", "sendSMSImmediate");
    private final static QName _Exception_QNAME = new QName("http://ws.interfaces.service.notification.rma/", "Exception");
    private final static QName _SendSMSImmediateResponse_QNAME = new QName("http://ws.interfaces.service.notification.rma/", "sendSMSImmediateResponse");
    private final static QName _CreerSms_QNAME = new QName("http://ws.interfaces.service.notification.rma/", "creerSms");
    private final static QName _CreerSmsResponse_QNAME = new QName("http://ws.interfaces.service.notification.rma/", "creerSmsResponse");
    private final static QName _SendSMSSchedule_QNAME = new QName("http://ws.interfaces.service.notification.rma/", "sendSMSSchedule");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rma.notification.service.interfaces.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SendSMSSchedule }
     * 
     */
    public SendSMSSchedule createSendSMSSchedule() {
        return new SendSMSSchedule();
    }

    /**
     * Create an instance of {@link SendSMSImmediateResponse }
     * 
     */
    public SendSMSImmediateResponse createSendSMSImmediateResponse() {
        return new SendSMSImmediateResponse();
    }

    /**
     * Create an instance of {@link SendSMSImmediate }
     * 
     */
    public SendSMSImmediate createSendSMSImmediate() {
        return new SendSMSImmediate();
    }

    /**
     * Create an instance of {@link SmsResponseVO }
     * 
     */
    public SmsResponseVO createSmsResponseVO() {
        return new SmsResponseVO();
    }

    /**
     * Create an instance of {@link CreerSmsResponse }
     * 
     */
    public CreerSmsResponse createCreerSmsResponse() {
        return new CreerSmsResponse();
    }

    /**
     * Create an instance of {@link CreerSms }
     * 
     */
    public CreerSms createCreerSms() {
        return new CreerSms();
    }

    /**
     * Create an instance of {@link SendSMSScheduleResponse }
     * 
     */
    public SendSMSScheduleResponse createSendSMSScheduleResponse() {
        return new SendSMSScheduleResponse();
    }

    /**
     * Create an instance of {@link SmsVO }
     * 
     */
    public SmsVO createSmsVO() {
        return new SmsVO();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendSMSScheduleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.interfaces.service.notification.rma/", name = "sendSMSScheduleResponse")
    public JAXBElement<SendSMSScheduleResponse> createSendSMSScheduleResponse(SendSMSScheduleResponse value) {
        return new JAXBElement<SendSMSScheduleResponse>(_SendSMSScheduleResponse_QNAME, SendSMSScheduleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendSMSImmediate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.interfaces.service.notification.rma/", name = "sendSMSImmediate")
    public JAXBElement<SendSMSImmediate> createSendSMSImmediate(SendSMSImmediate value) {
        return new JAXBElement<SendSMSImmediate>(_SendSMSImmediate_QNAME, SendSMSImmediate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.interfaces.service.notification.rma/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendSMSImmediateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.interfaces.service.notification.rma/", name = "sendSMSImmediateResponse")
    public JAXBElement<SendSMSImmediateResponse> createSendSMSImmediateResponse(SendSMSImmediateResponse value) {
        return new JAXBElement<SendSMSImmediateResponse>(_SendSMSImmediateResponse_QNAME, SendSMSImmediateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreerSms }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.interfaces.service.notification.rma/", name = "creerSms")
    public JAXBElement<CreerSms> createCreerSms(CreerSms value) {
        return new JAXBElement<CreerSms>(_CreerSms_QNAME, CreerSms.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreerSmsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.interfaces.service.notification.rma/", name = "creerSmsResponse")
    public JAXBElement<CreerSmsResponse> createCreerSmsResponse(CreerSmsResponse value) {
        return new JAXBElement<CreerSmsResponse>(_CreerSmsResponse_QNAME, CreerSmsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendSMSSchedule }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.interfaces.service.notification.rma/", name = "sendSMSSchedule")
    public JAXBElement<SendSMSSchedule> createSendSMSSchedule(SendSMSSchedule value) {
        return new JAXBElement<SendSMSSchedule>(_SendSMSSchedule_QNAME, SendSMSSchedule.class, null, value);
    }

}
