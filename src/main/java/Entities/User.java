package Entities;

import Database_layer.Enumerations.Roles;

/**
 * Created by alexb on 14-Mar-17.
 */

public class User {

    private int user_id;
    private String email;
    private String login;
    private String password_hash;
    private Roles role;
    private String firstName;
    private String lastName;
    private String patronym;
    private String contactInfo;

    public String getLogin() {
        return login;
    }
    public String getEmail() {return email;}
    public String getRole() {
        return role.toString();
    }
    public int getUser_id() {
        return user_id;
    }
    public String getFirstName(){return  firstName;}
    public String getLastName(){return lastName;}
    public String getPatronym(){return patronym;}
    public String getPassword_hash(){return password_hash;}
    public String getContactInfo(){return contactInfo;}

    public void setUser_id(int value){user_id = value;}
    public void setEmail(String value){email = value;}
    public void setLogin(String value){login = value;}
    public void setPassword_hash(String value){password_hash = value;}
    public void setRole(String value){role = Roles.valueOf(value);}
    public void setRole(Roles value){role = value;}
    public void setFirstName(String value){firstName = value;}
    public void setLastName(String value){lastName = value;}
    public void setPatronym(String value){patronym = value;}
    public void setContactInfo(String value){contactInfo = value;}
}
