package hamburgermenu.demo.fragments;



/**
 * Created by linus on 2017-10-17.
 */

public class LeaveEvent extends Event
{
    private Leave leave = new Leave();

    public LeaveEvent()
    {
        setTitle("Ledighet");
        setInfo("Ledighet");
    }
    public Leave getLeave() {
        return leave;
    }
    public void setLeave(Leave leave) {
        this.leave = leave;
    }
}
