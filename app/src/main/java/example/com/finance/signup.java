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

public class signup extends AppCompatActivity {
    EditText name;
    EditText pass;
    EditText confirm;
    EditText emailid;
    EditText blog;
    EditText phone;
    Button Sign,login;
    String result;
    JsonClass1 js;
    String url;
    public static final String Email = "emailKey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = ( EditText) findViewById(R.id. name);
        pass = ( EditText) findViewById(R.id. password);
        confirm = ( EditText) findViewById(R.id. cpassword);
         emailid= ( EditText) findViewById(R.id.email);
        blog = ( EditText) findViewById(R.id.blogn);
        phone = ( EditText) findViewById(R.id. phone);
        Sign = (Button) findViewById(R.id.signup);
        login = (Button) findViewById(R.id.login);
        Sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = name.getText(). toString();
                String p = pass.getText(). toString();
                String c = confirm.getText(). toString();
                String e = emailid.getText(). toString();
                String b = blog.getText(). toString();
                String ph = phone.getText().toString();


                if (n.isEmpty() || n.length() < 2) {
                    name.setError("at least 2 characters");
                } else
                if (p.isEmpty() || p.length() < 4 || p.length() > 10) {
                    pass.setError("between 4 and 10 alphanumeric characters");
                } else
                if (c.isEmpty() || c.length() < 4 || c.length() > 10)
                    confirm.setError("between 4 and 10 alphanumeric characters");
                else if (c.compareToIgnoreCase(p)!=0){
                    confirm.setError("Password does not match");
                }
                else
                if (b.isEmpty() || b.length() < 3) {
                    blog.setError("at least 3 characters");
                } else
                if (e.isEmpty() || e.length() < 7 ) {
                    emailid.setError("at least 7 characters");
                } else
                if (ph.isEmpty() ) {
                    phone.setText("");
                }
                else if ( ph.length()!=10   )
                {
                    phone.setError(" 10 digits");
                }else {
                    setPrefrance();

                    url="http://financeplusgo.esy.es/s.php?fullname="+n+"&emailid="+e+"&password="+p+"&blogname="+b+"&phone="+ph;
                    GetData gd=new GetData();
                    gd.execute();
                }
                }
            });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(signup.this, login.class);
                startActivity(i);

            }
        });
    }

class GetData extends AsyncTask<Void,Void,Void>
{
    ProgressDialog pd;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(signup.this);
        pd.setMessage("Please Wait.....");
        pd.setTitle("Processing");
        pd.setIndeterminate(false);
        pd.setCancelable(true);
        pd.show();
        name.setText("");
        pass.setText("");
        confirm.setText("");
        emailid.setText("");
        phone.setText("");
        blog.setText("");
    }

    @Override
    protected Void doInBackground(Void... voids) {

        js=new JsonClass1();
        result= js.getData(url);

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if(pd.isShowing()) {
            pd.dismiss();
        }
        Toast.makeText(getApplicationContext(),"New Account Created Successfully",Toast.LENGTH_LONG).show();
        Intent i=new Intent(signup.this,profile.class);
        startActivity(i);
    }
}
    private void setPrefrance()
    {
        SharedPreferences sp = this.getSharedPreferences( "MyPref", MODE_WORLD_READABLE);
        SharedPreferences.Editor spEditor = sp.edit();
        spEditor.putString( Email, emailid.getText().toString());
        spEditor.apply();
    }
}
