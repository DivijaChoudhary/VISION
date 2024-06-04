package com.example.vishal.dvds;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class Language extends AppCompatActivity {

   Button btnapply;
    //int vd=0;
    // Button b;EditText e;
    TextToSpeech t1;
    //static String iid=null;

  //  static EditText editid;


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

        // super.onDestroy();
        super.onDestroy();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

       /* t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status!=TextToSpeech.ERROR)
                { t1.setLanguage(Locale.US);}
                String o="Choose A Language";

                t1.speak(o,TextToSpeech.QUEUE_FLUSH,null);
            }
        });*/
        //editid= (EditText) (TextView) findViewById(R.id.textid);
        //textView.setText("");
        btnapply = (Button) findViewById(R.id.btnapply);
        btnapply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onpause();
                Intent intent = new Intent(Language.this,Login2nd.class);
                startActivity(intent);
               // finish();
//onpause();
            }

        });

/*
        DisplayMetrics ds = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(ds);

        int width = ds.widthPixels;
        int height = ds.heightPixels;

        getWindow().setLayout((int) (width * .9), (int) (height * .2));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

    }

   // public static String getText() {

     //   iid= editid.getText().toString();
       // return iid;


    //}
    public void onpause()
    {
        if(t1!=null)
        {t1.stop();t1.shutdown();
        }
        //super.onPause();*/
    }
}






