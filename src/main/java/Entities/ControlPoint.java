package Entities;


/**
 * Created by alexb on 09-Apr-17.
 */
public class ControlPoint {
    private int id;
    private java.util.Date date;

    public int getId(){return id;}
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
