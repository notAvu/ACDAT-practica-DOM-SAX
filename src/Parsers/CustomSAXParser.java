package Parsers;

import Parsers.Handlers.SAXHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomSAXParser {
    private XMLReader procesadorXML;
    private SAXHandler gestor;
    private InputSource archivoXML;

    public CustomSAXParser(String nombreArchivo) {
        try {
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXParser parser = parserFactory.newSAXParser();
            procesadorXML = parser.getXMLReader();
        } catch (SAXException | ParserConfigurationException ex) {
            Logger.getLogger(CustomSAXParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        gestor = new SAXHandler();
        procesadorXML.setContentHandler(gestor);
        archivoXML = new InputSource(nombreArchivo);
    }

    void run() {
        try {
            procesadorXML.parse(archivoXML);
        } catch (IOException | SAXException ex) {
            Logger.getLogger(CustomSAXParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
