import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import java.util.HashSet;

public class A24E5ZDomQuery {

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse("A24E5Z_XML.xml");
            doc.getDocumentElement().normalize();

            System.out.println("Gyökérelem: " + doc.getDocumentElement().getNodeName());
            System.out.println("\nAktuális elem:");
            System.out.println("Nyilvántartás");
            System.out.println("Tulajdonos és jármű adatok\n");

            // -------------------------------------------------------------------
            // 1. feladat – minden nagyobb elem kiírása
            // -------------------------------------------------------------------

            System.out.println("1. feladat - Minden jármű teljes adatainak kilistázása:\n");

            NodeList gepList = doc.getElementsByTagName("Gepjarmu");
            for (int i = 0; i < gepList.getLength(); i++) {
                Element g = (Element) gepList.item(i);

                System.out.println("Gépjármű kód: " + g.getAttribute("gkod"));
                System.out.println("  Alvázszám: " + g.getAttribute("alvazszam"));
                System.out.println("  Rendszám: " + g.getAttribute("rendszam"));
                System.out.println("  Tulaj (O_G): " + g.getAttribute("O_G"));
                System.out.println("  Típus: " + g.getElementsByTagName("Tipus").item(0).getTextContent());
                System.out.println("  Gyártási év: " + g.getElementsByTagName("GyartasiEv").item(0).getTextContent());
                System.out.println("  Márka: " + g.getElementsByTagName("Marka").item(0).getTextContent());
                System.out.println("  Km óra: " + g.getElementsByTagName("Km_ora").item(0).getTextContent());
                System.out.println();
            }

            // -------------------------------------------------------------------
            // 2. feladat – lekérdezések
            // -------------------------------------------------------------------

            System.out.println("\n2. feladat - Lekérdezések:\n");

            // 2.a – összes márka listázása (halmaz)
            System.out.println("1. Minden előforduló jármű márka:");
            HashSet<String> markak = new HashSet<>();
            NodeList markaNodes = doc.getElementsByTagName("Marka");

            for (int i = 0; i < markaNodes.getLength(); i++) {
                markak.add(markaNodes.item(i).getTextContent().trim());
            }

            System.out.println(markak + "\n");

            // 2.b – az első gépjármű strukturált kiírása
            System.out.println("2. Az első gépjármű strukturált formában:");
            if (gepList.getLength() > 0) {
                Element elso = (Element) gepList.item(0);
                System.out.println("<Gepjarmu gkod=\"" + elso.getAttribute("gkod") +
                                   "\" alvazszam=\"" + elso.getAttribute("alvazszam") +
                                   "\" rendszam=\"" + elso.getAttribute("rendszam") + "\">");
                System.out.println("    <Tipus>" + elso.getElementsByTagName("Tipus").item(0).getTextContent() + "</Tipus>");
                System.out.println("    <GyartasiEv>" + elso.getElementsByTagName("GyartasiEv").item(0).getTextContent() + "</GyartasiEv>");
                System.out.println("    <Marka>" + elso.getElementsByTagName("Marka").item(0).getTextContent() + "</Marka>");
                System.out.println("    <Km_ora>" + elso.getElementsByTagName("Km_ora").item(0).getTextContent() + "</Km_ora>");
                System.out.println("</Gepjarmu>\n");
            }

            // 2.c – összes tankolás dátumainak listája
            System.out.println("3. Minden tankolás dátuma:");
            NodeList tankList = doc.getElementsByTagName("Tankolas");
            for (int i = 0; i < tankList.getLength(); i++) {
                Element t = (Element) tankList.item(i);
                String datum = t.getElementsByTagName("Datum").item(0).getTextContent();
                System.out.println("  - " + datum);
            }
            System.out.println();

            // -------------------------------------------------------------------
            // 3. feladat – összetett lekérdezés
            // -------------------------------------------------------------------

            System.out.println("3. Összetett lekérdezés:");
            System.out.println("   Hány alkatrész tartozik motor típushoz?");

            int motorDb = 0;
            NodeList alkList = doc.getElementsByTagName("Alkatresz");
            for (int i = 0; i < alkList.getLength(); i++) {
                Element a = (Element) alkList.item(i);
                String tipus = a.getElementsByTagName("Tipus").item(0).getTextContent().trim();
                if (tipus.equalsIgnoreCase("Motor")) {
                    motorDb++;
                }
            }

            System.out.println("   Motorhoz tartozó alkatrészek száma: " + motorDb);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
