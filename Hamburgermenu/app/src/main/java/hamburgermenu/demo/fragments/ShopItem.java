package hamburgermenu.demo.fragments;

/**
 * Created by Rasmus on 2017-10-17.
 */

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.File;

@Root(name = "shopItem")
public class ShopItem {

    @Element(name = "id")
    private int id;

    @Element(name = "title")
    private String title;

    @Element(name = "info")
    private String info;

    @Element(name = "imageURL")
    private File imageURL;

    @Element(name = "price")
    private String price;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public File getImageURL() {
        return imageURL;
    }

    public void setImageURL(File imageURL) {
        this.imageURL = imageURL;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
