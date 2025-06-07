package eai.devass.sinistreat.appli.utils.xmlparsing;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/* @author kchakib : 10 déc. 10 */
public class EntityResolverImpl implements EntityResolver {

	public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
		
		if (publicId.equals("-//Hibernate/Hibernate Mapping DTD//EN")) {
			// this deactivates the open office DTD
            return new InputSource(new ByteArrayInputStream("<?xml version='1.0' encoding='UTF-8'?>".getBytes()));
		} else {
			return null;
		}

	}

}


