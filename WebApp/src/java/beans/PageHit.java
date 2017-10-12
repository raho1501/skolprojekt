/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Rasmus
 */
@Named(value = "pageHit")
@SessionScoped
public class PageHit implements Serializable {

    /**
     * Creates a new instance of PageHit
     */
    private int pageView;
    public PageHit() {
        pageView = 0;
    }
    public int getPageHit()
    {
        return ++pageView;
    }
    
}
