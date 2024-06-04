package com.example.vishal.dvds;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    int vd = 0;
    Button b;
    EditText e;
    TextToSpeech t1;

    protected int splashTime = 4000;
    TextView tvl;
    int timer = 0;

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
        setContentView(R.layout.activity_main);


        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                }
                String o ="WELCOME";

                t1.speak(o, TextToSpeech.QUEUE_FLUSH, null);
            }
        });


        tvl = (TextView) findViewById(R.id.textView);
        Thread th = new Thread() {

            @Override
            public void run() {
                try {

                    Thread.sleep(4000);
                   // onpause();
                    Intent intent = new Intent(MainActivity.this,Language.class);
                    startActivity(intent);
                  //  onpause();
                    finish();


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        th.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome__window, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


   public void onpause() {
        if (t1 != null) {
            t1.stop();
            t1.shutdown();
        }
        //super.onPause();
    }
}






