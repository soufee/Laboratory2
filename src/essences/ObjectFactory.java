
package essences;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the essences package. 
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

    private final static QName _Gamer_QNAME = new QName("", "Gamer");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: essences
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Gamers }
     * 
     */
    public Gamers createGamers() {
        return new Gamers();
    }

    /**
     * Create an instance of {@link Gamer }
     * 
     */
    public Gamer createGamer() {
        return new Gamer();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Gamer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Gamer")
    public JAXBElement<Gamer> createGamer(Gamer value) {
        return new JAXBElement<Gamer>(_Gamer_QNAME, Gamer.class, null, value);
    }

}
