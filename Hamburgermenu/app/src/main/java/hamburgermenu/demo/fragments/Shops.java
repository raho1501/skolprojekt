
package hamburgermenu.demo.fragments;

import org.simpleframework.xml.ElementList;

import java.util.ArrayList;
import java.util.List;/**
 * Created by Limeman on 10/19/2017.
 */

public class Shops {

    @ElementList(name = "shops", inline=true, required = false)
    private List<Shop> shopList = new ArrayList<>();
    public int size(){ return shopList.size(); }
    public Shop getshop(int index) { return shopList.get(index); }

}