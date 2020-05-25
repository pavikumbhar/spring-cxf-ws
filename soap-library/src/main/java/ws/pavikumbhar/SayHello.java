
package ws.pavikumbhar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sayHello complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sayHello">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="myname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sayHello", propOrder = {
    "myname"
})
public class SayHello {

    protected String myname;

    /**
     * Gets the value of the myname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMyname() {
        return myname;
    }

    /**
     * Sets the value of the myname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMyname(String value) {
        this.myname = value;
    }

}
