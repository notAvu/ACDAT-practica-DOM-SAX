package Parsers.Handlers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {

    public SAXHandler() {
        super();
    }
    @Override
    public void startDocument(){
        System.out.println("Comienzo del documento XML");
    }
    @Override
    public void endDocument(){
        System.out.println("Fin del documento XML");
    }
    @Override
    public void startElement(String uri, String nombre, String nombreC, Attributes att){
        System.out.println(nombreC );
    }
    @Override
    public void endElement(String uri, String nombre, String nombreC){
        System.out.println(nombreC );
    }
    @Override
    public void characters (char[] ch, int inicio, int longitud) {
        String cad = new String(ch, inicio, longitud);
        cad = cad.replaceAll("[\t\n]","");
        System.out.println( cad);
    }
}