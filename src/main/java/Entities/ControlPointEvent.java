package Entities;

/**
 * Created by angre on 23.04.2017.
 */
import Entities.ControlPoint;
import Entities.Teacher;
import Entities.Group;

public class ControlPointEvent {
    private int controlPointEvent_id;
    private Group group;
    private Teacher teacher;
    private ControlPoint controlPoint;

    public int getControlPointEvent_id()
    {
        return controlPointEvent_id;
    }
    public Group getGroup() { return group; }
    public Teacher getTeacher() { return teacher; }
    public ControlPoint getControlPoint() { return controlPoint; }

    public void setControlPointEvent_id(int value)
    {
        controlPointEvent_id = value;
    }
    public void setGroup(Group item)
    {
        group = item;
    }
    public void setTeacher(Teacher item)
    {
        teacher = item;
    }
    public void setControlPoint(ControlPoint item)
    {
        controlPoint = item;
    }
}
