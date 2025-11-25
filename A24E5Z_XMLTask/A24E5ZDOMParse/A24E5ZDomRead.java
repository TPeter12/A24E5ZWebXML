import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class A24E5ZDomRead
{
    public static void main(String argv[]) throws SAXException, IOException, ParserConfigurationException 
    {

        File xmlFile = new File("A24E5Z_XML.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());


        // ---------------------------- TULAJDONOS ----------------------------
        NodeList tulajList = doc.getElementsByTagName("Tulajdonos");

        for (int i = 0; i < tulajList.getLength(); i++) {
            Node nNode = tulajList.item(i);

            System.out.println("\nAktuális elem: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element elem = (Element) nNode;
                System.out.println("Okod: " + elem.getAttribute("okod"));

                System.out.println("Név: " + elem.getElementsByTagName("Nev").item(0).getTextContent());

                NodeList telszamok = elem.getElementsByTagName("Telefonszam");
                for (int t = 0; t < telszamok.getLength(); t++) {
                    System.out.println("Telefonszám: " + telszamok.item(t).getTextContent());
                }

                Element cim = (Element) elem.getElementsByTagName("Cim").item(0);
                System.out.println("Város: " + cim.getElementsByTagName("Varos").item(0).getTextContent());
                System.out.println("Utca: " + cim.getElementsByTagName("Utca").item(0).getTextContent());
                System.out.println("Házszám: " + cim.getElementsByTagName("Hazszam").item(0).getTextContent());
            }
        }


        // ---------------------------- GEPJARMU ----------------------------
        NodeList gepList = doc.getElementsByTagName("Gepjarmu");

        for (int i = 0; i < gepList.getLength(); i++) {
            Node nNode = gepList.item(i);

            System.out.println("\nAktuális elem: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                System.out.println("Gkód: " + elem.getAttribute("gkod"));
                System.out.println("Alvázszám: " + elem.getAttribute("alvazszam"));
                System.out.println("Rendszám: " + elem.getAttribute("rendszam"));
                System.out.println("Tulaj (O_G): " + elem.getAttribute("O_G"));

                System.out.println("Típus: " + elem.getElementsByTagName("Tipus").item(0).getTextContent());
                System.out.println("Gyártási év: " + elem.getElementsByTagName("GyartasiEv").item(0).getTextContent());
                System.out.println("Márka: " + elem.getElementsByTagName("Marka").item(0).getTextContent());
                System.out.println("Km óra: " + elem.getElementsByTagName("Km_ora").item(0).getTextContent());
            }
        }


        // ---------------------------- ALKATRÉSZ ----------------------------
        NodeList alkList = doc.getElementsByTagName("Alkatresz");

        for (int i = 0; i < alkList.getLength(); i++) {

            Node nNode = alkList.item(i);
            System.out.println("\nAktuális elem: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                System.out.println("Akód: " + elem.getAttribute("akod"));
                System.out.println("A_X: " + elem.getAttribute("A_X"));

                System.out.println("Név: " + elem.getElementsByTagName("Nev").item(0).getTextContent());
                System.out.println("Típus: " + elem.getElementsByTagName("Tipus").item(0).getTextContent());
                System.out.println("Cikkszám: " + elem.getElementsByTagName("Cikkszam").item(0).getTextContent());
                System.out.println("Darab: " + elem.getElementsByTagName("Darab").item(0).getTextContent());
                System.out.println("Gyártó: " + elem.getElementsByTagName("Gyarto").item(0).getTextContent());
                System.out.println("Származás: " + elem.getElementsByTagName("Szarmazas").item(0).getTextContent());
                System.out.println("Szolgáltatás: " + elem.getElementsByTagName("Szolgaltatas").item(0).getTextContent());
            }
        }


        // ---------------------------- TARTALMAZ ----------------------------
        NodeList tartList = doc.getElementsByTagName("Tartalmaz");

        for (int i = 0; i < tartList.getLength(); i++) {
            Node nNode = tartList.item(i);
            System.out.println("\nAktuális elem: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                System.out.println("Xkód: " + elem.getAttribute("xkod"));
                System.out.println("X_G: " + elem.getAttribute("X_G"));
                System.out.println("Ár: " + elem.getElementsByTagName("Ar").item(0).getTextContent());
                System.out.println("Jegyzőkönyv: " + elem.getElementsByTagName("Jegyzokonyv").item(0).getTextContent());
            }
        }


        // ---------------------------- ÜZEMANYAG ----------------------------
        NodeList uzemList = doc.getElementsByTagName("Uzemanyag");

        for (int i = 0; i < uzemList.getLength(); i++) {
            Node nNode = uzemList.item(i);
            System.out.println("\nAktuális elem: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                System.out.println("Ukód: " + elem.getAttribute("ukod"));
                System.out.println("U_G: " + elem.getAttribute("U_G"));
                System.out.println("Típus: " + elem.getElementsByTagName("Tipus").item(0).getTextContent());
                System.out.println("Ár: " + elem.getElementsByTagName("Ar").item(0).getTextContent());
                System.out.println("Gyártó: " + elem.getElementsByTagName("Gyarto").item(0).getTextContent());
            }
        }


        // ---------------------------- TANKOLÁS ----------------------------
        NodeList tankList = doc.getElementsByTagName("Tankolas");

        for (int i = 0; i < tankList.getLength(); i++) {

            Node nNode = tankList.item(i);
            System.out.println("\nAktuális elem: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                System.out.println("T_kód: " + elem.getAttribute("tkod"));
                System.out.println("T_G: " + elem.getAttribute("T_G"));

                System.out.println("Dátum: " + elem.getElementsByTagName("Datum").item(0).getTextContent());
                System.out.println("Liter: " + elem.getElementsByTagName("Liter").item(0).getTextContent());
                System.out.println("Összeg: " + elem.getElementsByTagName("Osszeg").item(0).getTextContent());
            }
        }

    }
}
