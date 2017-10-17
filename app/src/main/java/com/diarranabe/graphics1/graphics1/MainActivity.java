package com.diarranabe.graphics1.graphics1;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageView zzst;
    Bitmap bitmap;
    Paint paint;
    Canvas canvas;


    int downx = 50;
    int downy = 50;

    int upx = 0;
    int upy = 0;

    Graph graph = new Graph(5);

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

        paint = new Paint();
        paint.setColor(Color.BLACK);

        zzst.setImageBitmap(bitmap);
        zzst.setColorFilter(Color.BLUE);


        initialize();
        //  setContentView(this);

        zzst.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        downx = (int)event.getX();
                        downy = (int)event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:

                        break;
                    case MotionEvent.ACTION_UP:
                        upx = (int)event.getX();
                        upy = (int)event.getY();
                        if( (Math.abs(upx - downx)< 10) ||  (Math.abs(upy - downy)< 10)) {
                            if (graph.addNode(upx,upy))initialize();
                            Log.d("XXXX", "nodeH => " +upx + " nodeW => " + upy + " nodeName touch");
                            canvas.drawCircle(upx, upy, 45, paint);
                        } else {


                        }
                        zzst.invalidate();
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
        for (Node node : graph.getNoeuds()) {
            drawNode(node);
        }


    }


    public void drawNode(Node node) {
        Log.d("XXXX", "nodeH => " + node.getX() + " nodeW => " + node.getY() + " nodeName => " + node.getEtiquete());
        canvas.drawCircle(node.getX(), node.getY(), 45, paint);
        zzst.invalidate();
    }
}
