package com.example.vishal.dvds;

import android.app.Activity;
import android.os.Bundle;

public class About extends Activity {
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
}
