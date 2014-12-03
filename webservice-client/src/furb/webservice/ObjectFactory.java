
package furb.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the furb.webservice package. 
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

    private final static QName _GetPlayer_QNAME = new QName("http://webservice.furb/", "getPlayer");
    private final static QName _GetPlayerResponse_QNAME = new QName("http://webservice.furb/", "getPlayerResponse");
    private final static QName _RegisterPlayerResponse_QNAME = new QName("http://webservice.furb/", "registerPlayerResponse");
    private final static QName _RegisterPlayer_QNAME = new QName("http://webservice.furb/", "registerPlayer");
    private final static QName _UpdatePlayerName_QNAME = new QName("http://webservice.furb/", "updatePlayerName");
    private final static QName _UpdatePlayerNameResponse_QNAME = new QName("http://webservice.furb/", "updatePlayerNameResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: furb.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UpdatePlayerNameResponse }
     * 
     */
    public UpdatePlayerNameResponse createUpdatePlayerNameResponse() {
        return new UpdatePlayerNameResponse();
    }

    /**
     * Create an instance of {@link UpdatePlayerName }
     * 
     */
    public UpdatePlayerName createUpdatePlayerName() {
        return new UpdatePlayerName();
    }

    /**
     * Create an instance of {@link RegisterPlayer }
     * 
     */
    public RegisterPlayer createRegisterPlayer() {
        return new RegisterPlayer();
    }

    /**
     * Create an instance of {@link RegisterPlayerResponse }
     * 
     */
    public RegisterPlayerResponse createRegisterPlayerResponse() {
        return new RegisterPlayerResponse();
    }

    /**
     * Create an instance of {@link GetPlayerResponse }
     * 
     */
    public GetPlayerResponse createGetPlayerResponse() {
        return new GetPlayerResponse();
    }

    /**
     * Create an instance of {@link GetPlayer }
     * 
     */
    public GetPlayer createGetPlayer() {
        return new GetPlayer();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPlayer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.furb/", name = "getPlayer")
    public JAXBElement<GetPlayer> createGetPlayer(GetPlayer value) {
        return new JAXBElement<GetPlayer>(_GetPlayer_QNAME, GetPlayer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPlayerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.furb/", name = "getPlayerResponse")
    public JAXBElement<GetPlayerResponse> createGetPlayerResponse(GetPlayerResponse value) {
        return new JAXBElement<GetPlayerResponse>(_GetPlayerResponse_QNAME, GetPlayerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterPlayerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.furb/", name = "registerPlayerResponse")
    public JAXBElement<RegisterPlayerResponse> createRegisterPlayerResponse(RegisterPlayerResponse value) {
        return new JAXBElement<RegisterPlayerResponse>(_RegisterPlayerResponse_QNAME, RegisterPlayerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterPlayer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.furb/", name = "registerPlayer")
    public JAXBElement<RegisterPlayer> createRegisterPlayer(RegisterPlayer value) {
        return new JAXBElement<RegisterPlayer>(_RegisterPlayer_QNAME, RegisterPlayer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePlayerName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.furb/", name = "updatePlayerName")
    public JAXBElement<UpdatePlayerName> createUpdatePlayerName(UpdatePlayerName value) {
        return new JAXBElement<UpdatePlayerName>(_UpdatePlayerName_QNAME, UpdatePlayerName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePlayerNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.furb/", name = "updatePlayerNameResponse")
    public JAXBElement<UpdatePlayerNameResponse> createUpdatePlayerNameResponse(UpdatePlayerNameResponse value) {
        return new JAXBElement<UpdatePlayerNameResponse>(_UpdatePlayerNameResponse_QNAME, UpdatePlayerNameResponse.class, null, value);
    }

}
