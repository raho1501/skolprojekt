package se.guitar_project.miun.appguitarofficial;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by linus on 2017-09-30.
 */

public class XmlDocument
{
    private Document document;
    public XmlDocument()
    {
        init();
    }
    public void appendChild(XmlElement element)
    {
        Element temp = element.getElement();
        document.adoptNode(temp);
        document.appendChild(temp);
    }
    public String toString()
    {
        StringWriter sw = new StringWriter();
        try
        {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            transformer.transform(new DOMSource(document), new StreamResult(sw));
            return sw.toString();
        }
        catch (Exception ex)
        {
            throw new RuntimeException("Error converting to String", ex); //TODO logga det här istället?
        }
    }
    private void init()
    {
        try
        {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            document = db.newDocument();
        }
        catch(Exception e)
        {
            //TODO logga det här.
        }
    }
}
