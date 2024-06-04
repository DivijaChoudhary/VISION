package com.example.vishal.dvds;



import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.Locale;

public class ViewWorkerDetail extends AppCompatActivity{

    NotificationCompat.Builder notification;Button confirmbtn;
    PendingIntent pIntent;
    NotificationManager manager;
    Intent resultIntent;
    TaskStackBuilder stackBuilder;

    Button btndeny;ImageButton imageButton2;
    int vd=0;
    //Button b;EditText e;
    TextToSpeech t1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewworkerdetail);
        imageButton2=findViewById(R.id.imageButton2);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status!=TextToSpeech.ERROR)
                { t1.setLanguage(Locale.US);}
                String o="Details Of Worker, You Need To Call Them ";
                t1.speak(o,TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // onpause();
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+917050842364"));
                startActivity(intent);//onpause();
               // finish();
                //onBackPressed();
            }
        });
//Rating Bar Code start
        RatingBar rating_bar=(RatingBar)findViewById(R.id.ratingBar);
        rating_bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(ViewWorkerDetail.this, "Worker Rating Is : "+rating, Toast.LENGTH_LONG).show();
            }
        });

//Rating Bar Code Over..

        btndeny=(Button) findViewById(R.id.btndeny);

        btndeny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(ViewWorkerDetail.this);
                builder.setMessage("Do You Want To Exit?").setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //onpause();
                        Intent intent=new Intent(ViewWorkerDetail.this,Login2nd.class);
                        startActivity(intent);
                      //  finish();

                    }
                }).setNegativeButton("No",null);
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });

        confirmbtn=(Button)findViewById(R.id.confirm);
        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewWorkerDetail.this, "Booking  is confirmed", Toast.LENGTH_LONG).show();
                startNotification();
            }
        });




    }

    protected void startNotification() {

        // TODO Auto-generated method stub
        //Creating Notification Builder
        notification = new NotificationCompat.Builder(ViewWorkerDetail.this);
        //Title for Notification
        notification.setContentTitle("notification");
        //Message in the Notification
        notification.setContentText("New Post on Android Notification.");
        //Alert shown when Notification is received
        notification.setTicker("New Message Alert!");
        //Icon to be set on Notification
        notification.setSmallIcon(R.drawable.ic_notifications_black_24dp);
        //Creating new Stack Builder
        stackBuilder = TaskStackBuilder.create(ViewWorkerDetail.this);
        stackBuilder.addParentStack(UserDTW.class);
        //Intent which is opened when notification is clicked
        resultIntent = new Intent(ViewWorkerDetail.this,UserDTW.class);
        stackBuilder.addNextIntent(resultIntent);
        pIntent =  stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pIntent);
        manager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, notification.build());

    }


    @Override
    protected void onDestroy() {
        if(t1!=null)
        {t1.stop();
            t1.shutdown();
        }

        super.onDestroy();
        //super.onDestroy();
    }


}

/*import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.Locale;

public class ViewWorkerDetail extends AppCompatActivity{



    Button btndeny;ImageButton imageButton2;
    int vd=0;
    //Button b;EditText e;
    TextToSpeech t1;

    NotificationCompat.Builder notification;Button confirmbtn;
    PendingIntent pIntent;
    NotificationManager manager;
    Intent resultIntent;
    TaskStackBuilder stackBuilder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewworkerdetail);
        imageButton2=findViewById(R.id.imageButton2);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status!=TextToSpeech.ERROR)
                { t1.setLanguage(Locale.US);}
                String o="Details Of Worker, You Need To Call Them ";

                t1.speak(o,TextToSpeech.QUEUE_FLUSH,null);
            }
        });
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // onpause();
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+917050842364"));
                startActivity(intent);//onpause();
                finish();
            }
        });
//Rating Bar Code start
               RatingBar rating_bar=(RatingBar)findViewById(R.id.ratingBar);
                 rating_bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        Toast.makeText(ViewWorkerDetail.this, "Worker Rating Is : "+rating, Toast.LENGTH_LONG).show();
    }
});

//Rating Bar Code Over..

       btndeny=(Button) findViewById(R.id.btndeny);

        btndeny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(ViewWorkerDetail.this);
                builder.setMessage("Do You Want To Exit?").setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       //onpause();
                        Intent intent=new Intent(ViewWorkerDetail.this,Login2nd.class);
                        startActivity(intent);
                        finish();

                    }
                }).setNegativeButton("No",null);
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });






    }

    @Override
    protected void onDestroy() {
        if(t1!=null)
        {t1.stop();
            t1.shutdown();
        }

        super.onDestroy();
        //super.onDestroy();
    }


}
*/