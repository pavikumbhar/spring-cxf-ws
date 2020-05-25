package com.pavikumbhar.ws.client;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author pavikumbhar
 *
 */
@Slf4j
@UtilityClass
public class XMLMarshaller {

	/**
	 * Serialize object to XML string
	 * 
	 * @param object object
	 * @param        <T> type
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  <T> String marshal(T object) {
		try {
			StringWriter stringWriter = new StringWriter();
			JAXBContext jc = JAXBContext.newInstance(object.getClass());
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			QName qName = new QName(object.getClass().getCanonicalName(), object.getClass().getSimpleName());

			JAXBElement<T> root = new JAXBElement(qName, object.getClass(), object);

			m.marshal(root, stringWriter);
			return stringWriter.toString();
		} catch (Exception e) {
			log.error("Exception while  marshalling  : {} ", e);
		}
		return null;
	}

	/**
	 * 
	 * @param object
	 * @param namespaceURI
	 * @param localPart
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  <T> String marshal(T object, final String namespaceURI, final String localPart) {
		try {
			StringWriter stringWriter = new StringWriter();
			JAXBContext jc = JAXBContext.newInstance(object.getClass());
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			QName qName = new QName(namespaceURI, localPart);
			JAXBElement<T> root = new JAXBElement(qName, object.getClass(), object);

			m.marshal(root, stringWriter);
			return stringWriter.toString();
		} catch ( JAXBException e) {
			log.error("JAXBException while marshalling  :{} ", e);
		}catch (Exception e) {
			log.error("Exception while marshalling  : {} ", e);
		}
		return null;
	}

	/**
	 * Deserialize XML string back to object
	 * 
	 * @param content XML content
	 * @param clasz   class
	 * @param         <T> type
	 * @return
	 */
	public  <T> T unMarshal(final String content, final Class<T> clasz) {
		try {
			JAXBContext jc = JAXBContext.newInstance(clasz);
			Unmarshaller u = jc.createUnmarshaller();
			return u.unmarshal(new StreamSource(new StringReader(content)), clasz).getValue();
		} catch (JAXBException e) {
			log.error("JAXBException while unmarshalling  :{} ", e);
		}catch (Exception e) {
			log.error("Exception while marshalling  : {} ", e);
		}
		return null;
	}

}