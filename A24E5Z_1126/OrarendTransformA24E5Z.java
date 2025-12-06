import java.io.File;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class OrarendTransformA24E5Z {

    public static void main(String[] args) {
        String xmlFile = "A24E5Z_orarend.xml";
        String xslFile = "A24E5Z_orarend.xsl";
        String outXml  = "A24E5Z_orarend.out.xml";
        String outHtml = "A24E5Z_orarend.html";

        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(
                    new StreamSource(new File(xslFile)));

            transformer.transform(
                    new StreamSource(new File(xmlFile)),
                    new StreamResult(new File(outXml)));

            transformer.transform(
                    new StreamSource(new File(xmlFile)),
                    new StreamResult(new File(outHtml)));

            System.out.println("Kész fájlok:");
            System.out.println(" - " + outXml);
            System.out.println(" - " + outHtml);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
