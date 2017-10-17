package retrofit;

import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by Albert on 17/10/16.
 */

public class Repairs {
    @ElementList(name = "repairs", inline=true)
    private List<Repair> repairList;
    public int size(){ return repairList.size(); }
    public Repair getRepair(int index)
    {
        return repairList.get(index);
    }

}
