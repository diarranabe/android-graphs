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
    ImageView supportView ;

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


        supportView.invalidate();
        supportView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:

                        downx = (int) event.getX();
                        downy = (int) event.getY();
                        linePath.reset();
                        Log.e("XXXX", " ===> Cool touch " );
                        Node auxNode = graph.selectedNode(downx, downy);
                        if (null != auxNode) {

                            Log.e("XXXX", " ===> Cool somthing touch " );
                            linePath.moveTo(downx, downy);
                         /*  unDrawNode(auxNode);
                            graph.removeNode(auxNode);
                            initialize();*/

                            supOne = true;
                        }


                        break;
                    case MotionEvent.ACTION_MOVE:
                        umpx = (int) event.getX();
                        umpy = (int) event.getY();
                        if (supOne){
                            tempArc = true ;
                            Log.e("XXXX", " ===> draw arc true " );
                        }
                        /* umpx = (int) event.getX();
                        umpy = (int) event.getY();

                        Node auxNode = graph.selectedNode(downx, downy);
                        if (auxNode != null)
                            unDrawNode(auxNode);
                        auxNode.setX(umpx);
                        auxNode.setY(umpy);
                        drawNode(auxNode);
                        zzst.invalidate();*/


                        break;
                    case MotionEvent.ACTION_UP:
                        upx = (int) event.getX();
                        upy = (int) event.getY();
                        Log.e("XXXX", " ===> Cool upp 1 " );
                      //  if (!supOne) {
                            tempArc =false ;
                            if ((Math.abs(upx - downx) < 10) && (Math.abs(upy - downy) < 10)) {
                                if (graph.addNode(upx, upy)) ;

                                Log.e("XXXX", " ===> Cool upp 2 " );
                                myDraw.initialize();
                                supportView.invalidate();

                         }
                        /*   } else {
                            supOne = false;
                        }*/
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                    default:
                        break;
                }
                return false ;
            }});


    }


}

