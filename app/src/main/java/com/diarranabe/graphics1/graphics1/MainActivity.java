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

    DrawableGraph myDraw ;
    ImageView supportView;

    Paint paint, painte, paintr;
    Canvas canvas = new Canvas();

    Boolean supOne = false;
    Boolean tempArc = false;

    Path linePath = new Path();


    int downx = 0;
    int downy = 0;

    int upx = 0;
    int upy = 0;

    int umpx = 0;
    int umpy = 0;

    static {
        graph = new Graph(10);

    }

    private static Graph graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myDraw = new DrawableGraph(graph);
        setContentView(R.layout.activity_main);
        supportView = (ImageView) findViewById(R.id.imageView);

        supportView.setImageDrawable(myDraw);


        //supportView.invalidate();

        supportView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
               switch (event.getAction()){
                   case MotionEvent.ACTION_DOWN:
                       downx = (int) event.getX();
                       downy = (int) event.getY();
                       Node auxNode = graph.selectedNode(downy, downx);
                       if (auxNode != null){
                           Log.e("XXXX" , " you touch down a node on the screm on position = " + downx + " node.x "+ auxNode.getX()  + " y=>" +  downy + " mode.y " + auxNode.getY() ) ;
                       }
                       Log.d("XXXX" , " you touch down the screm on = " + downx + " - y = " + downy) ;
                       break;
                   case MotionEvent.ACTION_UP:
                       umpx =(int) event.getX();
                       umpy =(int) event.getY();
                       Log.d("XXXX" , " you touch up the screm") ;
                       break;
                   case MotionEvent.ACTION_MOVE:
                       int upx = (int) event.getX();;
                       int upy = (int) event.getY();;
                       Log.i("XXXX" , " you touch mouve the screm") ;
                       break;
                   case MotionEvent.ACTION_CANCEL:
                       Log.d("XXXX" , " you touch up the stop screm") ;
                       break;
               }

                return true;
            }
        });

    }
}



