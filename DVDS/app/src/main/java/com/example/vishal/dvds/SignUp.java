package com.example.vishal.dvds;

import android.content.DialogInterface;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.common.collect.Range;

import java.util.Locale;

public class SignUp extends AppCompatActivity {
    int vd=0;
    // Button b;EditText e;
    TextToSpeech t1;
    Databasehelper myDb;
//Button btnabout;
    EditText editid,editid1,editid2,editid3,editid4,editid5,editid6,editid7;
    Button btnAddData;
    AwesomeValidation awesomeValidation;
String work=null;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status!=TextToSpeech.ERROR)
                { t1.setLanguage(Locale.US);}
                String o="Kindly Fill Your Details";
                t1.speak(o,TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        editid6=(EditText)findViewById(R.id.editText7);
        Spinner mySpinner=(Spinner)findViewById(R.id.spinner2);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(SignUp.this, android.R.layout.
                simple_list_item_1, getResources().getStringArray(R.array.names));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        editid6.setText(null);
                        break;
                    case 1:
                        editid6.setText("Plumber");
                        break;
                    case 2:
                        editid6.setText("Electrician");
                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                editid6.setText("No Work Selected");}
            });

            //


       // editid=(EditText) findViewById(R.id.editText);
        //editid1=(EditText) findViewById(R.id.editText2);
        //editid2=(EditText) findViewById(R.id.editText3);
        //editid3=(EditText) findViewById(R.id.editText4);
        //editid4=(EditText) findViewById(R.id.editText5);
        //editid5=(EditText) findViewById(R.id.editText6);
        //editid6=(EditText) findViewById(R.id.editText7);


       // btnAddData = (Button) findViewById(R.id.save);

       // adddata();
updateUI();
//adddata();
        }

    @Override
    protected void onDestroy() { if(t1!=null)
    {t1.stop();
        t1.shutdown();
    }

        //super.onDestroy();
        super.onDestroy();
    }

    private void updateUI() {
        editid=(EditText) findViewById(R.id.editText);
        editid1=(EditText) findViewById(R.id.editText2);
        editid2=(EditText) findViewById(R.id.editText3);
        editid3=(EditText) findViewById(R.id.editText4);
        editid4=(EditText) findViewById(R.id.editText5);
        editid5=(EditText) findViewById(R.id.editText6);
      //  editid6=(EditText) findViewById(R.id.editText7);
        editid7=(EditText)findViewById(R.id.editText8);
        btnAddData=(Button)findViewById(R.id.save);

        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
        //String regexAddress = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?])";
        awesomeValidation.addValidation(SignUp.this,R.id.editText3,"[a-zA-Z\\s]+",R.string.fnameerr);
        //awesomeValidation.addValidation(SignUp.this,R.id.editText7,"[a-zA-Z\\s]+",R.string.work);
        awesomeValidation.addValidation(SignUp.this,R.id.editText,android.util.Patterns.EMAIL_ADDRESS,R.string.emailerr);
        awesomeValidation.addValidation(SignUp.this,R.id.editText6, RegexTemplate.TELEPHONE,R.string.phoneerr);
        awesomeValidation.addValidation(SignUp.this,R.id.editText2,regexPassword,R.string.pwderr);
        awesomeValidation.addValidation(SignUp.this,R.id.editText8,R.id.editText2,R.string.cpwderr);
        awesomeValidation.addValidation(SignUp.this,R.id.editText5,RegexTemplate.NOT_EMPTY,R.string.uwaddress);
        awesomeValidation.addValidation(SignUp.this,R.id.editText4,Range.closed(18,60),R.string.uwage);
     // adddata();






// public void adddata() {

                      /*  {
                            boolean isinserted = myDb.inserdata(editid.getText().
                                    toString(),editid1.getText().toString(),editid2.getText().
                                    toString(),editid3.getText().toString(),editid4.getText().toString()
                                    ,editid5.getText().toString(),editid6.getText().toString());
                            if (isinserted == true) {

                                Toast.makeText(SignUp.this,
                                        "Data Inserted", Toast.LENGTH_LONG).show();

                                openSignin();
                            } else {
                                Toast.makeText(SignUp.this,
                                        "Data Not Inserted", Toast.LENGTH_LONG).show();

                            }


                        }
                    }

                    private void openSignin() {
                        AlertDialog.Builder builder=new AlertDialog.Builder(SignUp.this);
                        builder.setMessage("Do You Want To Save?").setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                      //  onpause();
                        Intent intent=new Intent(SignUp.this,Login2nd.class);
                        startActivity(intent);
                            }
                        }).setNegativeButton("No",null);
                        AlertDialog alertDialog=builder.create();
                        alertDialog.show();*/


   // }

   // private void adddata() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (awesomeValidation.validate()) {
                            //Toast.makeText(SignUp.this, "data entresd successfully", Toast.LENGTH_SHORT).show();//adddata();

                            AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                            builder.setMessage("Do You Want To Save?").setPositiveButton("YES",
                                    new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                            myDb=new Databasehelper(SignUp.this);
                                    boolean isinserted = myDb.inserdata(editid.getText().
                                                    toString(), editid1.getText().toString(), editid2.getText().
                                                    toString(), editid3.getText().toString(), editid4.getText().toString()
                                            , editid5.getText().toString(), editid6.getText().toString());
                                    if (isinserted == true) {

                                        Toast.makeText(SignUp.this,
                                                "Data Inserted", Toast.LENGTH_LONG).show();

                                       //  onpause();
                                        Intent intent = new Intent(SignUp.this, Login2nd.class);
                                        startActivity(intent);
                                      //  onpause();
                                        finish();

                                    } else {
                                        Toast.makeText(SignUp.this,
                                                "db errr", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }).setNegativeButton("No", null);
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                            } else {
                             Toast.makeText(SignUp.this,
                                  "Enter Valid Data", Toast.LENGTH_LONG).show();
                        }

                    }
                });



      //  );

    //}




}
    //}
  //super.onPause();
    }


