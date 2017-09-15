package example.com.finance;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class blog extends AppCompatActivity {
    EditText art,top,subj,blogname;
    EditText emailid;
    Button sub;
    String result;
    JsonClass js1;
    String url;
    public static final String Email = "emailKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);
        top = (EditText)
                findViewById(R.id.topic);
        subj = (EditText) findViewById(R.id.subject);
        art = (EditText) findViewById(R.id.article);

        blogname = (EditText) findViewById(R.id.blogn);
        emailid=(EditText)findViewById(R.id.email);
        sub = (Button) findViewById(R.id.submit);
        getPrefrenceData();

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t = top.getText().toString();
                String s = subj.getText().toString();
                String a = art.getText().toString();
                String b = blogname.getText().toString();
                String e=emailid.getText().toString();

                if (t.isEmpty() || t.length() < 3) {
                    top.setError("at least 3 characters");
                } else if (s.isEmpty() || s.length() < 7) {
                    subj.setError("at least 7 characters");
                } else {
                    if (a.isEmpty() || a.length() < 7) {
                        art.setError("at least 7 characters");
                    } else{
                        url = "http://financeplusgo.esy.es/b.php?topic="+t+"&subject="+s+"&blogname="+b+"&article="+a+"&emailid="+e;
                        GetData gd = new GetData();
                        gd.execute();
                    }
                }
            }
        });
    }
    class GetData extends AsyncTask<Void,Void,Void>
    {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(blog.this);
            pd.setMessage("Please Wait.....");
            pd.setTitle("Processing");
            pd.setIndeterminate(false);
            pd.setCancelable(true);
            pd.show();
            top.setText("");
            subj.setText("");
            art.setText("");
            emailid.setText("");
            blogname.setText("");


        }

        @Override
        protected Void doInBackground(Void... voids) {

            js1=new JsonClass();
            result= js1.getData(url);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(pd.isShowing()) {
                pd.dismiss();
            }
            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
        }
    }
    private void getPrefrenceData() {
        SharedPreferences spf = this.getSharedPreferences("MyPref", MODE_WORLD_READABLE);
        emailid.setText(spf.getString(Email, ""));
    }
}
