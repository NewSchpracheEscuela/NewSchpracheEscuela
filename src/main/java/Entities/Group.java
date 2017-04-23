package Entities;

import Database_layer.Enumerations.Level;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by alexb on 15-Apr-17.
 */
@JsonAutoDetect
public class Group {
    private int id;
    private Level level;
    private int amount;
    private int course_id;
    private int person_id;

    public int getId(){return id;}
    public String getLevel(){return level.name();}
    public int getAmount(){return amount;}
    public int getCourse_id(){return course_id;}
    public int getPerson_id(){return person_id;}

    public void setId(int newId){
        if (newId < 1){
            throw new IllegalArgumentException();
        }
        id = newId;
    }
    public void setLevel(String newLevel){
        if (newLevel == null || newLevel.length() == 0){
            throw new IllegalArgumentException();
        }
        level = Level.valueOf(newLevel);
    }
    public void setAmount(int newAmount){amount = newAmount;}

    public void setCourse_id(int newCourse_id){
        if (newCourse_id < 1){
            throw new IllegalArgumentException();
        }
        course_id = newCourse_id;
    }
    public void setPerson_id(int  newPerson_id){
        if (newPerson_id < 1){
            throw new IllegalArgumentException();
        }
        person_id = newPerson_id;
    }


}
