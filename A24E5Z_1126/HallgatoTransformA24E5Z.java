import java.io.File;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class HallgatoTransformA24E5Z {

    public static void main(String[] args) {
        String xmlFile   = "hallgatoA24E5Z.xml";
        String xslFile   = "hallgatoA24E5Z.xsl";
        String outXml    = "hallgatoA24E5Z.out.xml";
        String outHtml   = "hallgatoA24E5Z.html";      

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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
