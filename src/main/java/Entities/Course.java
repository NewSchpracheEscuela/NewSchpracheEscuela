package Entities;

import Database_layer.Enumerations.Languages;
import Entities.Serializers.JsonDateYearSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by angre on 10.04.2017.
 */
public class Course {
    private int course_id;
    private String title;
    private Languages language;
    private String description;
    private int numberOfHours;
    private java.util.Date startDate;
    private float price;

    public int getCourse_id(){return course_id;}
    public String getTitle(){return title;}
    public String getLanguage(){return language.toString();}
    public String getDescription(){return description;}
    public int getNumberOfHours(){return numberOfHours;}

    @JsonSerialize(using = JsonDateYearSerializer.class)
    public java.util.Date getStartDate(){return startDate;}
    public float getPrice(){return price;}

    public void setCourse_id(int value){course_id=value;}
    public void setTitle(String value){title = value;}
    public void setLanguage(String value){language = Languages.valueOf(value);}
    public void setLanguage(Languages value){language = value;}
    public void setDescription(String value){description = value;}
    public void setNumberOfHours(int value){numberOfHours = value;}
    public void setStartDate(java.util.Date value){startDate = value;}
    public void setPrice(float value){price = value;}
}
