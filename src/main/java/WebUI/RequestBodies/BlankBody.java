package WebUI.RequestBodies;

/**
 * Created by angre on 30.04.2017.
 */
public class BlankBody {
    private int user_id;
    private int course_id;


    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int value) {
        course_id = value;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

}
