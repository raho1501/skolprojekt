package com.example.limeman.alphaguitarrprojectsprint2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Vector;

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
    private Vector<Element> elementList = new Vector();
    public XmlDocument(){init(); }
    public XmlDocument(String xml, String tagName){ parse(xml, tagName); }
    public void appendChild(XmlElement element)
    {
        Element temp = element.getElement();
        document.adoptNode(temp);
        document.appendChild(temp);
        getElementList(element.getTag());
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
            throw new RuntimeException("Error converting to String", ex);                           //TODO logga det här istället?
        }
    }

    public int getVectorSize() { return elementList.size(); }

    private void parse(String xml, String tagname)
    {
        try
        {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            InputSource input = new InputSource(new StringReader(xml));
            document = dBuilder.parse(input);                                                       //Konstruerar ett document med element i.
            document.getDocumentElement().normalize();                                              //Inte nödvändingt men rekomenderat
            getElementList(tagname);
        }
        catch(Exception e)
        {
            e.printStackTrace();
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
    public Vector<Element> getElementList(String entityName)
    {
        elementList.clear();

        NodeList list = document.getElementsByTagName(entityName);                                  //Val av vilket element vi ska titta i.
        int size = list.getLength();
        for (int i = 0; i < size; i++) {
            Node node = list.item(i);
            Element element = (Element) node;
            elementList.add(element);
        }
        return elementList;                                                                         //TODO fixa så att entitetsnamnen och elementlisterna är i en map.
    }
    public String getElementByTag(String entityName, String tagName, int index)
    {
        String result = "";
        elementList = getElementList(entityName);
        NodeList tag = elementList.get(index).getElementsByTagName(tagName);
        result = tag.item(0).getTextContent();
        return result;
    }

}
