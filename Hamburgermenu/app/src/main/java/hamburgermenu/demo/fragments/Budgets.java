package hamburgermenu.demo.fragments;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

import hamburgermenu.demo.fragments.Budget;

/**
 * Created by Anton on 2017-10-17.
 */

@Root (name = "budgets")
public class Budgets {

    @ElementList(name = "budgets", inline=true, required = false)
    private List<Budget> BudgetList = new ArrayList<>();
    public int size(){
        return BudgetList.size();
    }
    public Budget getBudget(int index)
    {
        return BudgetList.get(index);
    }
}