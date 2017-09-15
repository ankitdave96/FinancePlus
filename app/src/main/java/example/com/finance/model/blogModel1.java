package example.com.finance.model;
/**
 * Created by ankit on 10/10/16.
 */

public class blogModel1 {

    //Declare all the types of variables matching in the db model in our hostinger phpmyadmin

    private String topic;
    private  String Subject;
    private String main_text;
    private String image;


    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getMain_text() {
        return main_text;
    }

    public void setMain_text(String main_text) {
        this.main_text = main_text;
    }




    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }





    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
