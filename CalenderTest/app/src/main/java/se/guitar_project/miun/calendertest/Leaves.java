package se.guitar_project.miun.calendertest;

import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by Albert on 17/10/16.
 */

public class Leaves {
    @ElementList(name = "leaves", inline=true)
    private List<Leave> leaveList;
    public int size(){ return leaveList.size(); }
    public Leave getLeave(int index) { return leaveList.get(index); }
}
