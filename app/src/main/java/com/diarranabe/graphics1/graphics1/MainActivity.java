package com.diarranabe.graphics1.graphics1;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView zzst;
    Bitmap bitmap;
    Paint paint, painte;
    Canvas canvas;

    Boolean supOne = false;


    int downx = 50;
    int downy = 50;

    int upx = 0;
    int upy = 0;

    int umpx = 0;
    int umpy = 0;

    private static Graph graph;

    static {
        graph = new Graph(5);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //   DrawableGraph mygraph = new DrawableGraph(this) ;

        zzst = new ImageView(this);

        // mygraph.kamiDraw(graph);
        setContentView(R.layout.activity_main);


        // champs = (ImageView) findViewById(R.id.imageView);


        zzst = (ImageView) findViewById(R.id.imageView);


        Display currentDisplay = getWindowManager().getDefaultDisplay();

        Log.d("XXXX", "imgH pic => " + zzst.getWidth() + " imgW => " + zzst.getWidth());
        Log.d("XXXX", "imgH => " + currentDisplay.getWidth() + " imgW => " + currentDisplay.getHeight());
        bitmap = Bitmap.createBitmap(currentDisplay.getWidth(), currentDisplay.getHeight(),
                Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        canvas.save();
        paint = new Paint();
        painte = new Paint();
        painte.setColor(Color.WHITE);
        paint.setColor(Color.DKGRAY);

        zzst.setImageBitmap(bitmap);


        initialize();
        //  setContentView(this);

        zzst.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:

                        downx = (int) event.getX();
                        downy = (int) event.getY();

                        Node auxNode = graph.selectedNode(downx, downy);
                        if (null != auxNode) {
                           unDrawNode(auxNode);
                            graph.removeNode(auxNode);
                            initialize();
                            supOne = true;
                        }


                        break;
                    case MotionEvent.ACTION_MOVE:

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
                        if (!supOne) {
                            if ((Math.abs(upx - downx) < 10) && (Math.abs(upy - downy) < 10)) {
                                if (graph.addNode(upx, upy)) initialize();
                            }
                        } else {
                            supOne = false;
                        }
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

    }

    public void initialize() {

        int ic = 0;
        for (Node node : graph.getNoeuds()) {
            drawNode(node);
            ic++;
        }
        if (ic == 0) {
            canvas.drawCircle(0, 0, 1500, painte);
            Log.e("XXXX", " ===> Winter " + ic);
        }
        Log.e("XXXX", " ===> ic =  " + ic);


    }


    public void drawNode(Node node) {
        Log.d("XXXX", "nodeH => " + node.getX() + " nodeW => " + node.getY() + " nodeName => " + node.getEtiquete());
        canvas.drawCircle(node.getX(), node.getY(), node.getDiameter(), paint);
        zzst.invalidate();
    }

    public void unDrawNode(Node node)  {
        canvas.drawCircle(node.getX(), node.getY(), node.getDiameter() + 3, painte);
    }


}

