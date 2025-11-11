package doma24e5z1105;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMModify1A24E5Z {

    public static void main(String[] args) {

        try {
            File inputFile = new File("A24E5Z_orarend.xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList oraList = doc.getElementsByTagName("ora");

            for (int i = 0; i < oraList.getLength(); i++) {
                Node oraNode = oraList.item(i);

                if (oraNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element oraElement = (Element) oraNode;

                    NodeList oraadoList = oraElement.getElementsByTagName("óraadó");
                    if (oraadoList.getLength() == 0) {
                        Element oraado = doc.createElement("óraadó");

                        Node oktatoNode = oraElement.getElementsByTagName("oktato").item(0);
                        String oktatoNev = oktatoNode != null ? oktatoNode.getTextContent() : "Nincs oktato";

                        oraado.setTextContent(oktatoNev);
                        oraElement.appendChild(oraado);
                    }

                    NamedNodeMap attr = oraElement.getAttributes();
                    Node tipusNode = attr.getNamedItem("tipus");
                    if (tipusNode != null && "gyakorlat".equals(tipusNode.getTextContent())) {
                        tipusNode.setTextContent("eloadas");
                    }
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(doc);

            System.out.println("---Módosított fájl---");
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}