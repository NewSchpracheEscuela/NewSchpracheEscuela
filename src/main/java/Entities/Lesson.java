package Entities;

/**
 * Created by angre on 23.04.2017.
 */
import Database_layer.Enumerations.Days;
import Entities.Serializers.JsonTimeSerializer;
import Entities.Deserializers.JsonTimeDeserializer;
import Entities.Teacher;
import Entities.Group;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

public class Lesson {
    private int lesson_id;
    private Group group;
    private Teacher teacher;
    private String room;
    private Date time;
    private Days day;

    public int getLesson_id()
    {
        return lesson_id;
    }
    public Group getGroup() { return group; }
    public Teacher getTeacher() { return teacher; }
    public String getRoom()
    {
        return room;
    }

    @JsonSerialize(using = JsonTimeSerializer.class)
    public Date getTime(){return time;}
    public String getDay(){return day.toString();}

    public void setLesson_id(int value)
    {
        lesson_id = value;
    }
    public void setGroup(Group item)
    {
        group = item;
    }
    public void setTeacher(Teacher item)
    {
        teacher = item;
    }
    public void setRoom(String value)
    {
        room = value;
    }

    @JsonDeserialize(using = JsonTimeDeserializer.class)
    public void setTime(Date value)
    {
        time = value;
    }
    public void setDay(String value)
    {
        day = Days.valueOf(value);
    }
    public void setDay(Days value)
    {
        day = value;
    }
}
