package Parsers.Handlers;


import org.xml.sax.*;
import org.xml.sax.helpers.*;

import java.util.ArrayList;
import java.util.List;

public class HandlerTablaSAX extends DefaultHandler {
    private int fila =0;
    private int columna =0;
    private String[] filas = new String[4];
    private List<String> elementos = new ArrayList<>();

    @Override
    public void startElement(String uri, String localName, String nombreEtiqueta, Attributes attributes) throws SAXException {
        if(attributes.getLength()>0){
            elementos.add(attributes.getValue(0));
        }
        if(fila >=1 && columna <4) {
            if(columna ==0 && attributes.getLength()>0){
                filas[columna]=attributes.getLocalName(0);
            }
            else{
                filas[columna]=nombreEtiqueta;
            }
            columna++;
        }
        fila++;
    }

    @Override
    public void endDocument(){
        System.out.println(getTabla());
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        fila--;
    }

    @Override
    public void characters(char ch[], int start, int length) {
        String cadena=new String(ch, start, length);
        if (!(cadena.trim().length() == 0))
            elementos.add(cadena);
    }

    public String getTabla() {
        StringBuilder builder = new StringBuilder();
        for (String string : filas) {
            builder.append(String.format("%-20s", string.toUpperCase().charAt(0)+string.substring(1)));
        }
        builder.append(System.lineSeparator());
        for (int i = 1; i <= elementos.size(); i++) {
            builder.append(String.format("%-20s", elementos.get(i-1)));
            if (i%4==0)
                builder.append(System.lineSeparator());
        }
        return builder.toString();
    }
}
