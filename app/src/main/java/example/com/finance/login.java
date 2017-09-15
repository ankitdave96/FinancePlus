package example.com.finance;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    Button Login,signup,news,forgot;
    EditText email;
    EditText pass;
    String result;
    JsonClass1 js;
    String url;
    public static final String Email = "emailKey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=(EditText)findViewById(R.id.email);
        pass=(EditText)findViewById(R.id.password);
        signup=(Button) findViewById(R.id.signup);
        Login=(Button) findViewById(R.id.login);
        forgot=(Button)findViewById(R.id.forgot);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(login.this, signup.class);
                startActivity(i);

            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(login.this, forgotpassword.class);
                startActivity(i);

            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailid = email.getText().toString();
                String passw = pass.getText().toString();


                if (emailid.isEmpty() || emailid.length() < 7) {
                    email.setError("at least 7 characters");
                } else {
                    email.setError(null);
                }
                if (passw.isEmpty() || passw.length() < 4) {
                    pass.setError("atleast 4 alphanumeric characters");
                } else {
                    setPrefrance();

                    url = "http://financeplusgo.esy.es/l.php?emailid=" + emailid + "&password=" + passw;
                    GetData gd = new login.GetData();
                    gd.execute();

                }

            }
        });

    }

    class GetData extends AsyncTask<Void, Void, Void> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(login.this);
            pd.setMessage("Please Wait.....");
            pd.setTitle("Processing");
            pd.setIndeterminate(false);
            pd.setCancelable(true);
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            js = new example.com.finance.JsonClass1();
            result = js.getData(url);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            String s = result.trim();
            pd.dismiss();
            if (s.equalsIgnoreCase("success")) {

                Intent intent = new Intent(login.this, profile.class);

                finish();
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Invalid User Name or Password", Toast.LENGTH_LONG).show();
            }
        }
    }
    private void setPrefrance()
    {
        SharedPreferences sp = this.getSharedPreferences( "MyPref", MODE_WORLD_READABLE);
        SharedPreferences.Editor spEditor = sp.edit();
        spEditor.putString( Email, email.getText().toString());
        spEditor.apply();
    }
    }
