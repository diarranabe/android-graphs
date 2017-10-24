package com.diarranabe.graphics1.graphics1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    DrawableGraph myDraw ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDraw = new DrawableGraph(this);
        setContentView(myDraw);
      //  myDraw.initialize();
    }



}

