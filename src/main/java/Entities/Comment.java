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
    private User author;
    private Course course;
    private java.util.Date date;
    private String entity;

    public int getComment_id(){return comment_id;}
    public User getAuthor(){return author;}
    public Course getCourse(){return course;}

    @JsonSerialize(using= JsonDateTimeSerializer.class)
    public java.util.Date getDate(){return date;}
    public String getEntity(){return entity;}

    public void setComment_id(int value){comment_id = value;}
    public void setAuthor(User value){author = value;}

    @JsonDeserialize(using = JSONDateTimeDeserializer.class)
    public void setDate(java.util.Date value){date = value;}
    public void setCourse(Course value){course = value;}
    public void setEntity(String value){entity = value;}


}
