package Entities;

/**
 * Created by angre on 23.04.2017.
 */
import Entities.ControlPoint;
import Entities.Teacher;
import Entities.Group;

public class ControlPointEvent {
    private int controlPointEvent_id;
    private int group;
    private int teacher;
    private int controlPoint;

    public int getControlPointEvent_id()
    {
        return controlPointEvent_id;
    }
    public int getGroup() { return group; }
    public int getTeacher() { return teacher; }
    public int getControlPoint() { return controlPoint; }

    public void setControlPointEvent_id(int value)
    {
        controlPointEvent_id = value;
    }
    public void setGroup(int item)
    {
        group = item;
    }
    public void setTeacher(int item)
    {
        teacher = item;
    }
    public void setControlPoint(int item)
    {
        controlPoint = item;
    }
}
