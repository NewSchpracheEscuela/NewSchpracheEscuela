package Entities;

import org.junit.Test;

public class TeacherTest {
    @Test(expected = IllegalArgumentException.class)
    public void setId() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setUser_Id() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setUser_id(-1);
    }
}
