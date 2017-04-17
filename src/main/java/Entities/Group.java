package Entities;

import Database_layer.Enumerations.Level;
/**
 * Created by alexb on 15-Apr-17.
 */
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

    public void setId(int newId){id = newId;}
    public void setLevel(String newLevel){level = Level.valueOf(newLevel);;}
    public void setAmount(int newAmount){amount = newAmount;}
    public void setCourse_id(int newCourse_id){course_id = newCourse_id;}
    public void setPerson_id(int  newPerson_id){person_id = newPerson_id;}


}
