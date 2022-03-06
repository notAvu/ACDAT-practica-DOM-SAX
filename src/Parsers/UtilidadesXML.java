package Parsers;

import Clases.Producto;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

public abstract class UtilidadesXML {
    //region generar documento
    private static Document getDocumento(boolean ingoreComments, boolean ignoreBlank) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setIgnoringComments(ingoreComments);
        dbf.setIgnoringElementContentWhitespace(ignoreBlank);
        DocumentBuilder documentBuilder;
        Document document=null;
        try {
            documentBuilder = dbf.newDocumentBuilder();
            document = documentBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return document;
    }

    public static Document generaDOMXML(List<Producto> listProducto) {
        Document domDoc = getDocumento(true, true);
        Element parent = domDoc.createElement("productos");
        domDoc.appendChild(parent);
        listProducto.forEach(p -> appendProduct(domDoc, parent, p));
        return domDoc;
    }

    private static void appendProduct(Document document, Element root, Producto producto) {
        Element pater = document.createElement("producto");
        Attr attributeID = document.createAttribute("id");
        attributeID.setValue(String.valueOf(producto.getId()));
        pater.setAttributeNode(attributeID);
        Element des = document.createElement("descripcion");
        des.setTextContent(producto.getDescripcion());
        pater.appendChild(des);
        Element precio = document.createElement("precio");
        precio.setTextContent(String.valueOf(producto.getPrecio()));
        pater.appendChild(precio);
        Element stock = document.createElement("stock");
        stock.setTextContent(String.valueOf(producto.getStock()));
        pater.appendChild(stock);
        root.appendChild(pater);
    }

    //endregion
    //region serializar documento
    public static void serializaDOMXML(Document document, String nombreFichero) {
        BufferedOutputStream bos;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(nombreFichero));
            writeXml(document, bos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void writeXml(Document doc, OutputStream output) {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        assert transformer != null;
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    //endregion
    public static void imprimirXMLSAX(String nombreFichero) {
        new CustomSAXParser(nombreFichero).run();
    }
}
