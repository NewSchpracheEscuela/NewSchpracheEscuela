package Entities;


import Entities.Serializers.JsonDateSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by alexb on 09-Apr-17.
 */
@JsonAutoDetect
public class ControlPoint {

    private int id;
    private java.util.Date date;

    public int getId(){return id;}

    @JsonSerialize(using=JsonDateSerializer.class)
    public java.util.Date getDate(){return date;}

    public void setId(int newId){
        if (newId < 1){
            throw new IllegalArgumentException();
        }
        id = newId;
    }

    public void setDate(java.util.Date newDate){
        if (newDate == null){
            throw new IllegalArgumentException();
        }
        date = newDate;
    }
}
