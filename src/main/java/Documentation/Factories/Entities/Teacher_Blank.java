package Documentation.Factories.Entities;

import java.util.ArrayList;

public class Teacher_Blank {

    private String firstName;
    private String lastName;
    private String patronym;

    private String phone;
    private String languages;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronym() {
        return patronym;
    }

    public void setPatronym(String patronym) {
        this.patronym = patronym;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<String> languagesArray) {
        for (int i = 0; i < languagesArray.size() - 1; i++) {
            languages += languagesArray.get(i) + ", ";
        }
        languages += languagesArray.get(languagesArray.size() - 1);
    }
}
