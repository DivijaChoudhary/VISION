package com.example.vishal.dvds;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class Login2nd extends AppCompatActivity {


    static String email1=null;
    Databasehelper myDb;
    int vd=0;
    //Button b;EditText e;
    TextToSpeech t1;
Button btnabout;
    EditText editid7;
    EditText editid8;

    Button btnretrive;
    Button btnretrive2,signup;

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        if(t1!=null)
        {t1.stop();
            t1.shutdown();
        }

        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2nd);


        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status!=TextToSpeech.ERROR)
                { t1.setLanguage(Locale.US);}
                String o="Kindly Enter Your Email Address And Password And Sign In As User Or Worker";

                t1.speak(o,TextToSpeech.QUEUE_FLUSH,null);
            }
        });
        myDb = new Databasehelper(this);

        editid7 = (EditText) findViewById(R.id.emailtext);
        editid8 = (EditText) findViewById(R.id.passwordtext);
        //editid7.setText("");
        //editid8.setText("");
        btnabout=(Button)findViewById(R.id.btnabout);
        btnabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login2nd.this,About.class);
                startActivity(intent);
            }
        });


        btnretrive = (Button) findViewById(R.id.signinuser);

        btnretrive2 = (Button) findViewById(R.id.signinworker);

signup=(Button)findViewById(R.id.samesignup);

        retrive();
        retrive2();
        signup();
    }

    private void signup() {
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Login2nd.this,SignUp.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void retrive2() {

        btnretrive2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=editid7.getText().toString();
                String password=editid8.getText().toString();
                Cursor c=myDb.retriveemployee(email);
                if(c.moveToFirst()){
                    String pass=c.getString(1);
                    if(password.compareTo(pass)==0)
                    {
                        Intent intent= new Intent(Login2nd.this,UserDTWStatic.class);
                        startActivity(intent);
                        finish();


                    }
                    else
                    { Toast.makeText(getBaseContext(),"Wrong Credentials", Toast.LENGTH_LONG).show();}


                }
                else
                {
                    Toast.makeText(getBaseContext(),"Email Does Not Exist", Toast.LENGTH_LONG).show();

                }
            }
        });

    }




    public void retrive() {

        btnretrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = (editid7.getText().toString());
                String password = editid8.getText().toString();
                Cursor c = myDb.retriveemployee(email);
                if (c.moveToFirst()) {
                    String pass = c.getString(1);
                    if (password.compareTo(pass) == 0) {
                      //  onpause();
                        Intent intent = new Intent(Login2nd.this, MapsActivity.class);
                        startActivity(intent);
                        finish();
                        email1=email;
                     //   onpause();

                    } else {
                        Toast.makeText(getBaseContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(getBaseContext(), "Data Not Retrived", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
public void onpause()
    {
        if(t1!=null)
        {t1.stop();
        t1.shutdown();
        }
       // super.onPause();
    }

public static String getemail(){
        String em=email1;
        return em;

    }

}

