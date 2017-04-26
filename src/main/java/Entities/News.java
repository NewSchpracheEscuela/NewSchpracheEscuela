package Entities;

/**
 * Created by angre on 10.04.2017.
 */
import Entities.Serializers.JsonDateTimeSerializer;
import Entities.Deserializers.JSONDateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class News {
    private int news_id;
    private String title;
    private String content;
    private int author;
    private java.util.Date date;

    public int getNews_id(){return news_id;}
    public String getTitle(){return title;}
    public String getContent(){return content;}
    public int getAuthor(){return author;}

    @JsonSerialize(using = JsonDateTimeSerializer.class)
    public java.util.Date getDate(){return date;}

    public void setNews_id(int value){news_id = value;}
    public void setTitle(String value){title = value;}
    public void setContent(String value){content = value;}
    public void setAuthor(int value){author = value;}

    @JsonDeserialize(using = JSONDateTimeDeserializer.class)
    public void setDate(java.util.Date value){date = value;}


}
