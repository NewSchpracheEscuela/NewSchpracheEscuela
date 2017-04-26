package Entities;

import org.junit.Test;

import static org.junit.Assert.*;

public class PersonTest {
    @Test(expected = IllegalArgumentException.class)
    public void setId() throws Exception {
        Person person = new Person();
        person.setId(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setUser_id() throws Exception {
        Person person = new Person();
        person.setUser_id(-1);
    }

}