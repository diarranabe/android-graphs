package com.diarranabe.graphics1.graphics1;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    DrawableGraph myDraw = new DrawableGraph();
    ImageView supportView;

    Paint paint, painte, paintr;
    Canvas canvas = new Canvas();

    Boolean supOne = false;
    Boolean tempArc = false;

    Path linePath = new Path();


    int downx = 50;
    int downy = 50;

    int upx = 0;
    int upy = 0;

    int umpx = 0;
    int umpy = 0;

    static {
        graph = new Graph(5);

    }

    private static Graph graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        supportView = (ImageView) findViewById(R.id.imageView);

        supportView.setImageDrawable(myDraw);


        //supportView.invalidate();

        supportView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
               switch (event.getAction()){
                   case MotionEvent.ACTION_DOWN:

                       Log.d("XXXX" , " you touch down the screm") ;
                       break;
                   case MotionEvent.ACTION_UP:
                       Log.e("XXXX" , " you touch up the screm") ;
                       break;
                   case MotionEvent.ACTION_MOVE:
                       Log.e("XXXX" , " you touch mouve the screm") ;
                       break;
                   case MotionEvent.ACTION_CANCEL:
                       Log.e("XXXX" , " you touch up the stop screm") ;
                       break;
               }

                return true;
            }
        });

    }
}



