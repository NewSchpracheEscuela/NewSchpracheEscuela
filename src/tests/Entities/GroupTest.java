package Entities;

import org.junit.Test;

import static org.junit.Assert.*;

public class GroupTest {
    @Test(expected = IllegalArgumentException.class)
    public void setId() throws Exception {
        Group group = new Group();
        group.setId(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setLevel() throws Exception {
        Group group = new Group();
        group.setLevel("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCourse_id() throws Exception {
        Group group = new Group();
        group.setCourse_id(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setPerson_id() throws Exception {
        Group group = new Group();
        group.setPerson_id(-1);
    }

}