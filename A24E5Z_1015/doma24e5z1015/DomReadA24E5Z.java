package doma24e5z1015;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class DomReadA24E5Z {

    public static void main(String[] args) {
        try {

            File xmlFile = new File("A24E5Z_orarend.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            document.getDocumentElement().normalize();

            System.out.println("Root element: " + document.getDocumentElement().getNodeName());
            System.out.println("----------------------------------");


            printNode(document.getDocumentElement(), 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printNode(Node node, int indent) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {

            printIndent(indent);
            System.out.print("<" + node.getNodeName());

            NamedNodeMap attributes = node.getAttributes();
            if (attributes != null) {
                for (int i = 0; i < attributes.getLength(); i++) {
                    Node attr = attributes.item(i);
                    System.out.print(" " + attr.getNodeName() + "=\"" + attr.getNodeValue() + "\"");
                }
            }
            System.out.println(">");

            NodeList children = node.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                if (child.getNodeType() == Node.ELEMENT_NODE) {
                    printNode(child, indent + 2);
                } else if (child.getNodeType() == Node.TEXT_NODE) {
                    String text = child.getTextContent().trim();
                    if (!text.isEmpty()) {
                        printIndent(indent + 2);
                        System.out.println(text);
                    }
                }
            }

            printIndent(indent);
            System.out.println("</" + node.getNodeName() + ">");
        }
    }

    private static void printIndent(int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print(" ");
        }
    }
}
