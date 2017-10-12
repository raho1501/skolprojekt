package se.guitar_project.miun.appguitarofficial;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by linus on 2017-09-26.
 */

public class XmlParser
{
    private String xml = ""; //TODO Behövs den här efter konstrukturn har körts?
    Document doc = null;
    public XmlParser(String data)
    {
        xml = data;
    }
    public void parse()
    {
        try
        {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            InputSource input = new InputSource(new StringReader(xml));
            doc = dBuilder.parse(input);                                                            //Konstruerar ett document med element i.

            doc.getDocumentElement().normalize();                                                   //Inte nödvändingt men rekomenderat


        }
        catch(Exception e)
        {
            e.printStackTrace();;
        }
    }
    public Vector<Element> getElementList(String entityName)
    {
        Vector<Element> elementList = new Vector();
        NodeList list = doc.getElementsByTagName(entityName);                                //Val av vilket element vi ska titta i.
        int size = list.getLength();
        for (int i = 0; i < size; i++) {
            Node node = list.item(i);
            Element element = (Element) node;
            elementList.add(element);
        }
        return elementList; //TODO fixa så att entitetsnamnen och elementlisterna är i en map.
    }
    public String getElementByTag(String entityName, String tagName, int index)
    {
        String result = "";
        Vector<Element> elementList = getElementList(entityName);
        NodeList tag = elementList.get(index).getElementsByTagName(tagName);
        result = tag.item(0).getTextContent();

        return result;
    }
}
