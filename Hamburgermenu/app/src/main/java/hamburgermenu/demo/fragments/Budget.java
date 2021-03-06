package hamburgermenu.demo.fragments;

import org.simpleframework.xml.Element;


/**
 * Created by Anton on 2017-10-17.
 */

public class Budget {
    @Element(name = "budgetId", required = false)
    private int budgetId;
    @Element(name = "info", required = false)
    private String info;
    @Element(name = "dateTime", required = true)
    private String dateTime;
    @Element(name = "amount", required = false)
    private  int amount;


    public int getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(int budgetId) {
        this.budgetId = budgetId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}