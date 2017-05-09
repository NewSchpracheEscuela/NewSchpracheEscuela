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
    private int group;
    private int teacher;
    private String room;
    private Date time;
    private Days day;
    private String course_title;
    private String course_language;
    private String group_level;

    public int getLesson_id()
    {
        return lesson_id;
    }
    public int getGroup() { return group; }
    public int getTeacher() { return teacher; }
    public String getRoom()
    {
        return room;
    }
    public String getCourse_title()
    {
        return course_title;
    }
    public String getCourse_language()
    {
        return course_language;
    }
    public String getGroup_level()
    {
        return group_level;
    }

    @JsonSerialize(using = JsonTimeSerializer.class)
    public Date getTime(){return time;}
    public String getDay(){return day.toString();}

    public void setLesson_id(int value)
    {
        lesson_id = value;
    }
    public void setGroup(int item)
    {
        group = item;
    }
    public void setTeacher(int item)
    {
        teacher = item;
    }
    public void setRoom(String value)
    {
        room = value;
    }
    public void setCourse_title(String value)
    {
        course_title = value;
    }
    public void setCourse_language(String value)
    {
        course_language = value;
    }
    public void setGroup_level(String value)
    {
        group_level = value;
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
