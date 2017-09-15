package example.com.finance;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class   profile extends AppCompatActivity {

    TextView emailid;
    String url,result;
    JsonClass js;
    Button logout,upload,n,blog;

    public static final String Email = "emailKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        emailid = (TextView) findViewById(R.id.email);
        logout=(Button)findViewById(R.id.logout);
        upload = (Button) findViewById(R.id.upload);
        n=(Button)findViewById(R.id.news);
        blog=(Button)findViewById(R.id.view);
        getPrefrenceData();
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(profile.this, blog.class);
                startActivity(i);
            }
        });
        blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(profile.this, mainblog.class);
                startActivity(i);
            }
        });
        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(profile.this, news.class);
                startActivity(i);

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
               finish();
                Toast.makeText(getApplicationContext(), "Successfull Logout", Toast.LENGTH_SHORT).show();
            }
        });
        url = "http://financeplusgo.esy.es/my_profile.php?emailid=" + Email;
        GetData gd = new GetData();
        gd.execute();

    }

    class GetData extends AsyncTask<Void, Void, Void> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(profile.this);
            pd.setMessage("Please Wait.....");
            pd.setTitle("Processing");
            pd.setIndeterminate(false);
            pd.setCancelable(true);
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            js = new example.com.finance.JsonClass();
            result = js.getData(url);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            String s = result.trim();
            pd.dismiss();
            if (s.equalsIgnoreCase("success")) {

                finish();
            }
        }
    }

        private void getPrefrenceData() {
            SharedPreferences spf = this.getSharedPreferences("MyPref", MODE_WORLD_READABLE);
            emailid.setText("Welcome " + spf.getString(Email, ""));
        }

    }
