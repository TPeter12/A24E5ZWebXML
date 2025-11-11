package doma24e5z1105;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class DOMQueryA24E5Z {

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse("A24E5Z_orarend.xml");

            doc.getDocumentElement().normalize();

            System.out.println("Gyökérelem: " + doc.getDocumentElement().getNodeName());
            System.out.println("\nAktuális elem: ");
            System.out.println("hallgató");
            System.out.println("neve: Tóth Péter");
            System.out.println();

            System.out.println("2.a feladat - A24E5Z_orarend.xml minden órájának kiírása:\n");

            NodeList oraList = doc.getElementsByTagName("ora");

            for (int i = 0; i < oraList.getLength(); i++) {
                Node node = oraList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element ora = (Element) node;

                    String id = ora.getAttribute("id");
                    String tipus = ora.getAttribute("tipus");
                    String targy = ora.getElementsByTagName("targy").item(0).getTextContent();
                    String nap = ora.getElementsByTagName("nap").item(0).getTextContent();
                    String tol = ora.getElementsByTagName("tol").item(0).getTextContent();
                    String ig = ora.getElementsByTagName("ig").item(0).getTextContent();
                    String helyszin = ora.getElementsByTagName("helyszin").item(0).getTextContent();
                    String oktato = ora.getElementsByTagName("oktato").item(0).getTextContent();

                    System.out.printf("Óra ID: %s (%s)%n", id, tipus);
                    System.out.printf("   Tárgy: %s%n", targy);
                    System.out.printf("   Időpont: %s %s-%s%n", nap, tol, ig);
                    System.out.printf("   Helyszín: %s%n", helyszin);
                    System.out.printf("   Oktató: %s%n%n", oktato);
                }
            }

            System.out.println("2.b feladat - Lekérdezések az órarendből:\n");

            System.out.println("1. Kurzusnév: [");
            java.util.HashSet<String> kurzusok = new java.util.HashSet<>();
            NodeList targyNodes = doc.getElementsByTagName("targy");
            for (int i = 0; i < targyNodes.getLength(); i++) {
                kurzusok.add(targyNodes.item(i).getTextContent().trim());
            }
            String[] kTomb = kurzusok.toArray(new String[0]);
            for (int i = 0; i < kTomb.length; i++) {
                System.out.print("    " + kTomb[i]);
                if (i < kTomb.length - 1) System.out.print(",");
                System.out.println();
            }
            System.out.println("]\n");

            System.out.println("2. Az első példány strukturált formában:");
            if (oraList.getLength() > 0) {
                Element elso = (Element) oraList.item(0);
                System.out.println("<ora id=\"" + elso.getAttribute("id") + "\" tipus=\"" + elso.getAttribute("tipus") + "\">");
                System.out.println("    <targy>" + elso.getElementsByTagName("targy").item(0).getTextContent() + "</targy>");
                System.out.println("    <idopont>");
                System.out.println("        <nap>" + elso.getElementsByTagName("nap").item(0).getTextContent() + "</nap>");
                System.out.println("        <tol>" + elso.getElementsByTagName("tol").item(0).getTextContent() + "</tol>");
                System.out.println("        <ig>" + elso.getElementsByTagName("ig").item(0).getTextContent() + "</ig>");
                System.out.println("    </idopont>");
                System.out.println("    <helyszin>" + elso.getElementsByTagName("helyszin").item(0).getTextContent() + "</helyszin>");
                System.out.println("    <oktato>" + elso.getElementsByTagName("oktato").item(0).getTextContent() + "</oktato>");
                System.out.println("    <szak>" + elso.getElementsByTagName("szak").item(0).getTextContent() + "</szak>");
                System.out.println("</ora>\n");
            }

            System.out.println("3. Oktatók nevei listába:");
            java.util.HashSet<String> oktatok = new java.util.HashSet<>();
            NodeList oktatoNodes = doc.getElementsByTagName("oktato");
            for (int i = 0; i < oktatoNodes.getLength(); i++) {
                oktatok.add(oktatoNodes.item(i).getTextContent().trim());
            }
            String[] oTomb = oktatok.toArray(new String[0]);
            System.out.print("Oktatók: [");
            for (int i = 0; i < oTomb.length; i++) {
                System.out.print(oTomb[i]);
                if (i < oTomb.length - 1) System.out.print(", ");
            }
            System.out.println("]\n");

            System.out.println("4. Összetett lekérdezés: Hány óra van hétfőn?");
            int hetfoDb = 0;
            for (int i = 0; i < oraList.getLength(); i++) {
                Element ora = (Element) oraList.item(i);
                String nap = ora.getElementsByTagName("nap").item(0).getTextContent().trim();
                if (nap.equalsIgnoreCase("hetfo")) {
                    hetfoDb++;
                }
            }
            System.out.println("Hétfőn összesen " + hetfoDb + " óra van.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}