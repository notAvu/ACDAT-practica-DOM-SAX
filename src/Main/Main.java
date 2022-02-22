package Main;

import Clases.Producto;
import Parsers.UtilidadesXML;
import org.w3c.dom.Document;

import java.util.ArrayList;

public class Main {
    public final static String NOMBRE_FICHERO="productos.xml";
    public static void main(String[] args) {
        ArrayList<Producto> productsList= new ArrayList<>();
        productsList.add(new Producto(2344,"Pasta termica 4g",6F,36));
        productsList.add(new Producto(1098,"Placa base Asus",150F,14));
        productsList.add(new Producto(7643,"Amd Ryzen 9",6F,560));
        Document domDocument=UtilidadesXML.generaDOMXML(productsList);
        UtilidadesXML.serializaDOMXML(domDocument, NOMBRE_FICHERO);
        UtilidadesXML.imprimirXMLSAX(NOMBRE_FICHERO);
    }
}
