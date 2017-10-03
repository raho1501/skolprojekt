package se.guitar_project.miun.appguitarofficial;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by linus on 2017-09-30.
 */

public class XmlElement
{
    private Document document;
    private Element element;
    public XmlElement()
    {
        init();
    }
    public XmlElement(String tagName)
    {
        init();
        setTag(tagName);
    }
    public XmlElement(String tagName, String text)
    {
        init();
        setTag(tagName);
        setText(text);
    }
    public void setText(String text)
    {
        Text textNode = document.createTextNode(text);
        element.appendChild(textNode);
    }
    public void setTag(String tagName)
    {
        element = document.createElement(tagName);;
    }
    public void appendChild(XmlElement xmlElement)
    {
        Element temp = xmlElement.getElement();
        document.adoptNode(temp);
        element.appendChild(temp);
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
    public Element getElement()
    {
        return element;
    }
}
