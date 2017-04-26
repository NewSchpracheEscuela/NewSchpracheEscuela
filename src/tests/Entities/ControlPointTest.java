package Entities;

import org.junit.Test;

public class ControlPointTest {
    @Test(expected = IllegalArgumentException.class)
    public void setId() throws Exception {
        ControlPoint controlPoint = new ControlPoint();
        controlPoint.setId(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setDate() throws Exception {
        ControlPoint controlPoint = new ControlPoint();
        controlPoint.setDate(null);
    }
}
