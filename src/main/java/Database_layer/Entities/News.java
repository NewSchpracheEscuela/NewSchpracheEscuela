package Database_layer.Entities;

import sun.util.calendar.LocalGregorianCalendar;

/**
 * Created by angre on 10.04.2017.
 */
public class News {
    private int news_id;
    private String title;
    private String content;
    private User author;
    private java.util.Date date;

    public int getNews_id(){return news_id;}
    public String getTitle(){return title;}
    public String getContent(){return content;}
    public User getAuthor(){return author;}
    public java.util.Date getDate(){return date;}

    public void setNews_id(int value){news_id = value;}
    public void setTitle(String value){title = value;}
    public void setContent(String value){content = value;}
    public void setAuthor(User value){author = value;}
    public void setDate(java.util.Date value){date = value;}


}
