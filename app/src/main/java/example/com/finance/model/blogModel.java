package example.com.finance.model;

/**
 * Created by JEEVANSHU on 01-12-2016.
 */
public class blogModel {

    //Declare all the types of variables matching in the db model in our hostinger phpmyadmin

    private String topic;
    private String blog_name;
    private String subject;
    private String main_text;
    private String image;
    private String email_id;
    private int likes;


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMain_text() {
        return main_text;
    }

    public void setMain_text(String main_text) {
        this.main_text = main_text;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getBlog_name() {
        return blog_name;
    }

    public void setBlog_name(String blog_name) {
        this.blog_name = blog_name;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
