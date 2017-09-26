package se.guitar_project.miun.appguitarofficial;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.StringReader;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by linus on 2017-09-26.
 */

public class XmlParser
{
    private String xml = ""; //TODO Behövs den här efter konstrukturn har körts?
    Vector<Element> elementList = new Vector();
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
            Document doc = dBuilder.parse(input);                                                   //Konstruerar ett document med element i.

            doc.getDocumentElement().normalize();                                                   //Inte nödvändingt men rekomenderat
            NodeList list = doc.getElementsByTagName("appointment");                                //Val av vilket element vi ska titta i.


            int size = list.getLength();
            for (int i = 0; i < size; i++)
            {
                Node node = list.item(i);
                Element element = (Element) node;
                elementList.add(element);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();;
        }
    }
    public String getElementByTag(String tagName, int index)
    {
        String result = "";
        int size = elementList.size();

        NodeList tag = elementList.get(index).getElementsByTagName(tagName);
        result = tag.item(0).getTextContent();

        return result;
    }
}
