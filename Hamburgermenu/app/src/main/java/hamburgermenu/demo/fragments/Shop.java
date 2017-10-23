package hamburgermenu.demo.fragments;

/**
 * Created by Rasmus on 2017-10-17.
 */

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.File;
import java.io.InputStream;

@Root(name = "shop")
public class Shop {

    @Element(name = "shopId")
    private int shopId;

    @Element(name = "title")
    private String title;

    @Element(name = "info")
    private String info;

    @Element(name = "imageUrl")
    private String imageURL;

    @Element(name = "price")
    private int price;

    @Element(name = "customerIdFk", required = false)
    private Integer customerIdFk = null;


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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return shopId;
    }

    public void setId(int shopId) { this.shopId = shopId; }

    public int getCustomerIdFk() {
        return customerIdFk;
    }

    public void setCustomerIdFk(int customerIdFk) {
        this.customerIdFk = customerIdFk;
    }
}