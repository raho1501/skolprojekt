package hamburgermenu.demo.fragments;

/**
 * Created by linus on 2017-10-17.
 */

public class RepairEvent extends Event{
    Repair repair = new Repair();
    public RepairEvent()
    {
        setTitle("Reparation");
        super.setColor(170,150,150,255);
    }
    public Repair getRepair() {
        return repair;
    }
    public void setRepair(Repair repair) {
        this.repair = repair;
    }
    @Override
    public String getInfo()
    {
        return repair.getInfo();
    }
    @Override
    public void setInfo(String info)
    {
        repair.setInfo(info);
    }
}
