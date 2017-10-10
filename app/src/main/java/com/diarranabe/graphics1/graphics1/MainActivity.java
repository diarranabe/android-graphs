package com.diarranabe.graphics1.graphics1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView champs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        champs = (ImageView)findViewById(R.id.champs);

        champs.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d("click"," x - y ->" + motionEvent.getX()+ " - " + motionEvent.getY());
                return false;
            }
        });
    }
}
