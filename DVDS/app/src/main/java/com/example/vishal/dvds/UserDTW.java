package com.example.vishal.dvds;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class UserDTW extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    int vd=0;
    EditText swork;
    TextToSpeech t1;
String iid=null;
Button button,btnnav,btncan;
Databasehelper myDb;
TextView uname;
TextView saddress,scontact;Switch switch2;
    NotificationCompat.Builder notification;Button confirmbtn;
    PendingIntent pIntent;
    NotificationManager manager;
    Intent resultIntent;
    TaskStackBuilder stackBuilder;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdtw);

        btncan = (Button) findViewById(R.id.btncan);
        btnnav = (Button) findViewById(R.id.btnnavigate);
        switch2 = (Switch) findViewById(R.id.switch1);
        switch2.setOnCheckedChangeListener(this);


        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                }
                String o = "Details Of User, ,If You Want To See The Route Then Click On ,Navigate Button, Else If You Want The Details Click On ,Show Notification Button";

                t1.speak(o, TextToSpeech.QUEUE_FLUSH, null);
            }
        });


        button = (Button) findViewById(R.id.button);
        uname = (TextView) findViewById(R.id.textView4);
        saddress = (TextView) findViewById(R.id.UAddress);
        scontact = (TextView) findViewById(R.id.UContact);
        swork = (EditText) findViewById(R.id.work);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDb = new Databasehelper(UserDTW.this);
                iid = PopGetId.getText();
                if (iid == null) {
                    Toast.makeText(getBaseContext(), "No booking", Toast.LENGTH_SHORT).show();
                }

                Cursor c = myDb.retriveemployee2(iid);
                if (c.moveToFirst()) {
                    btnnav.setEnabled(true);
                    String name = c.getString(2);
                    String address = c.getString(4);
                    String contact = c.getString(5);
                    uname.setText(name);
                    saddress.setText(address);
                    scontact.setText(contact);
                    String w = MapsActivity.getwork();
                    swork.setText(w);
                    //Toast.makeText(UserDTW.this,c.getString(0)+c.getString(1) ,Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getBaseContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                }


            }

        });
        btncan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UserDTW.this);
                builder.setMessage("Do You Want To Exit?").setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // onpause();
                        Intent intent = new Intent(UserDTW.this, Login2nd.class);
                        startActivity(intent);
                        finish();

                    }
                }).setNegativeButton("No", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

        btnnav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onpause();
                Intent intent = new Intent(UserDTW.this, MapsActivityWorker.class);
                startActivity(intent);
                finish();
                navigate();
            }

            private void navigate() {

                // protected void startNotification() {

                // TODO Auto-generated method stub
                //Creating Notification Builder
                notification = new NotificationCompat.Builder(UserDTW.this);
                //Title for Notification
                notification.setContentTitle("VISION");
                //Message in the Notification
                notification.setContentText("Notification For User!!!! ");
                //Alert shown when Notification is received
                notification.setTicker("New Message Alert!");
                //Icon to be set on Notification
                notification.setSmallIcon(R.drawable.ic_notifications_black_24dp);
                //Creating new Stack Builder
                stackBuilder = TaskStackBuilder.create(UserDTW.this);
                stackBuilder.addParentStack(UserDTW.class);
                //Intent which is opened when notification is clicked
                resultIntent = new Intent(UserDTW.this,Navmsg.class);
                stackBuilder.addNextIntent(resultIntent);
                pIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                notification.setContentIntent(pIntent);
                manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(0, notification.build());

            }


        });

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(switch2.isChecked())
        {
            button.setEnabled(false);
        }
        else
        {button.setEnabled(true);
        btnnav.setEnabled(true);
        }
    }
    @Override
    protected void onDestroy() {
        if(t1!=null)
        {t1.stop();
            t1.shutdown();
        }

        super.onDestroy();

    }


    //    super.onPause();

}

