package example.com.finance;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class changepassword extends AppCompatActivity {




    public String pass,message,subject;
    public String value;
    private Button butsend;
    private EditText val;
    InputStream in = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("ema");
            //The key argument here must match that used in the other activity
        }


        butsend = (Button)findViewById(R.id.submit);
        val = (EditText)findViewById(R.id.newpass);

        butsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                pass = val.getText().toString();
                conne c = new conne();
                c.execute();

                sendemail();




            }
        });

    }

    private void sendemail()
    {
        subject="Finance Plus";
        message="Your randomly generated password for your Finance Plus account is 74y934ffn . \n Please Do change this on first login for Security.";
        SendMail sm = new SendMail(this, value,subject, message);
        sm.execute();
    }




    public class conne extends AsyncTask
    {

        @Override
        protected Object doInBackground(Object[] objects) {


            URL url = null;
            try {
                url = new URL("http://financeplusgo.esy.es/forgot.php?emailid=" + value + "&password=" + "74y934ffn");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection urlConnection = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                in = new BufferedInputStream(urlConnection.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

        }
    }




}
