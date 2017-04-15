package Database_layer.Entities;

/**
 * Created by alexb on 15-Apr-17.
 */
public class Person {
    private int id;
    private int user_id;

    public int getId(){return id;}
    public int getUser_id(){return user_id;}

    public void setId(int newId){id = newId;}
    public void setUser_id(int newUser_id){user_id = newUser_id;}
}
