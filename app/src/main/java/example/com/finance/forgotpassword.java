package example.com.finance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class forgotpassword extends AppCompatActivity {

        public EditText email;
        private Button butgo;
        public String str;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_forgotpassword);

            email = (EditText)findViewById(R.id.email);
            butgo = (Button)findViewById(R.id.go);

            butgo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    str = email.getText().toString();
                    Intent intent;
                    intent = new Intent(forgotpassword.this, changepassword.class);
                    intent.putExtra("ema",str);
                    startActivity(intent);

                }
            });


        }

    }

