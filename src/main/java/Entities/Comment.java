package Entities;

import Entities.Serializers.JsonDateTimeSerializer;
import Entities.Deserializers.JSONDateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by angre on 10.04.2017.
 */
public class Comment {
    private int comment_id;
    private int author;
    private int course;
    private String author_name;
    private String course_name;
    private java.util.Date date;
    private String entity;

    public int getComment_id(){return comment_id;}
    public int getAuthor(){return author;}
    public int getCourse(){return course;}
    public String getAuthor_name(){return author_name;}
    public String getCourse_name(){return course_name;}

    @JsonSerialize(using= JsonDateTimeSerializer.class)
    public java.util.Date getDate(){return date;}
    public String getEntity(){return entity;}

    public void setComment_id(int value){comment_id = value;}
    public void setAuthor(int value){author = value;}
    public void setAuthor_name(String value){author_name = value;}
    public void setCourse_name(String value){course_name = value;}

    @JsonDeserialize(using = JSONDateTimeDeserializer.class)
    public void setDate(java.util.Date value){date = value;}
    public void setCourse(int value){course = value;}
    public void setEntity(String value){entity = value;}


}
