package Database_layer.Entities;

/**
 * Created by angre on 10.04.2017.
 */
public class Comment {
    private int comment_id;
    private User author;
    private java.util.Date date;

    public int getComment_id(){return comment_id;}
    public User getAuthor(){return author;}
    public java.util.Date getDate(){return date;}

    public void setComment_id(int value){comment_id = value;}
    public void setAuthor(User value){author = value;}
    public void setDate(java.util.Date value){date = value;}
}
