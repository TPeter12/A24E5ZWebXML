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

public class A24E5ZDomModify {

    public static void main(String[] args) {

               try {
            File inputFile = new File("A24E5Z_XML.xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.parse(inputFile);

            Node root = doc.getFirstChild();

            // ---- GEPJARMU KERESÉSE G002 ----
            NodeList carList = doc.getElementsByTagName("Gepjarmu");

            for (int i = 0; i < carList.getLength(); i++) {
                Node car = carList.item(i);

                NamedNodeMap attr = car.getAttributes();
                Node gkod = attr.getNamedItem("gkod");

                if ("G002".equals(gkod.getTextContent())) {

                    NodeList list = car.getChildNodes();

                    for (int j = 0; j < list.getLength(); j++) {
                        Node node = list.item(j);

                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) node;

                            if ("Marka".equals(eElement.getNodeName())) {
                                eElement.setTextContent("Toyota");
                            }

                            if ("Km_ora".equals(eElement.getNodeName())) {
                                eElement.setTextContent("75000");
                            }

                            if ("GyartasiEv".equals(eElement.getNodeName())) {
                                eElement.setTextContent("2020");
                            }
                        }
                    }
                }
            }

            // ---- UZEMANYAG ÁRAK MÓDOSÍTÁSA ----
            NodeList fuelList = doc.getElementsByTagName("Uzemanyag");

            for (int i = 0; i < fuelList.getLength(); i++) {

                Node fuel = fuelList.item(i);
                NodeList list = fuel.getChildNodes();

                String tipus = "";
                Node arElem = null;

                for (int j = 0; j < list.getLength(); j++) {
                    Node node = list.item(j);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) node;

                        if ("Tipus".equals(eElement.getNodeName())) {
                            tipus = eElement.getTextContent();
                        }

                        if ("Ar".equals(eElement.getNodeName())) {
                            arElem = node;
                        }
                    }
                }

                if ("Dízel".equals(tipus)) {
                    arElem.setTextContent("630");
                }

                if ("Benzin".equals(tipus)) {
                    arElem.setTextContent("600");
                }
            }


            // -------------------------------------------------------------------
            // KIÍRÁS
            // -------------------------------------------------------------------
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(doc);

            System.out.println("--- Módosított XML fájl ---");
            StreamResult result = new StreamResult(new File("A24E5Z_XMLA24E5Z.xml"));
            transformer.transform(source, result);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
