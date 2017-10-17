package retrofit;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Anton on 2017-10-17.
 */

@Root (name = "Budget")
public class Budgets {

    @ElementList(name = "budgets", inline=true)
    private List<Budget> BudgetList;
    public int size(){
        return BudgetList.size();
    }
    public Budget getBudget(int index)
    {
        return BudgetList.get(index);
    }
}
