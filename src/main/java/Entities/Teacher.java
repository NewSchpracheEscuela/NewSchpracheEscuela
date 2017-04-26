package Entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * Created by alexb on 15-Apr-17.
 */
@JsonAutoDetect
public class Teacher {
    private int id;
    private int user_id;

    public int getId(){return id;}
    public int getUser_id(){return user_id;}

    public void setId(int newId){
        if (newId < 1){
            throw new IllegalArgumentException();
        }
        id = newId;
    }
    public void setUser_id(int newUser_id){
        if (newUser_id < 1){
            throw new IllegalArgumentException();
        }
        user_id = newUser_id;
    }
}
