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

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    ImageView zzst;
    Bitmap bitmap;
    Paint paint;
    Canvas canvas;


    float downx = 50;
    float downy = 50;

    float upx = 0;
    float upy = 0;

    Graph graph = new Graph();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();

        //   DrawableGraph mygraph = new DrawableGraph(this) ;

        zzst = new ImageView(this);

        // mygraph.kamiDraw(graph);
        setContentView(R.layout.activity_main);


        // champs = (ImageView) findViewById(R.id.imageView);


        zzst = (ImageView) findViewById(R.id.imageView);

        Display currentDisplay = getWindowManager().getDefaultDisplay();

        Log.d("XXXX", "imgH => " + currentDisplay.getWidth() + " imgW => " + currentDisplay.getHeight());
        bitmap = Bitmap.createBitmap(currentDisplay.getWidth(), currentDisplay.getHeight(),
                Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        if (
                zzst == null
                ) {
            Log.e("XXXX", " BAKA TORA");
        }
        zzst.setImageBitmap(bitmap);

        zzst.setOnTouchListener(this);

        List<Node> nodes = graph.getNoeuds();
        for (Node node : nodes) {
            drawNode(node);
        }
        //  setContentView(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void initialize() {
        Node n1 = new Node(50, 50, " N1 ", Color.BLACK);

        Node n2 = new Node(50, 50, " N2 ", Color.BLACK );
        Node n3 = new Node(50, 110, " N3 ",  Color.BLACK);
        Node n4 = new Node(50, 200, " N4 ",  Color.BLACK);
        Node n5 = new Node(120, 50, " N5 ",  Color.BLACK);
        Node n6 = new Node(120, 110, " N6 ",  Color.BLACK);
        Node n7 = new Node(120, 200, " N7 ",  Color.BLACK);
        Node n8 = new Node(250, 250, " N8 ",  Color.BLACK);
        Node n9 = new Node(500, 500, " N9 ",  Color.BLACK);
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.addNode(n5);
        graph.addNode(n6);
        graph.addNode(n7);
        graph.addNode(n8);
        graph.addNode(n9);

        Arc a1 = new Arc(n1, n3);
        graph.addArc(a1);
        Arc a2 = new Arc(n1, n2);
        graph.addArc(a2);
        Arc a3 = new Arc(n2, n4);
        graph.addArc(a3);
        Arc a4 = new Arc(n3, n5);
        graph.addArc(a4);


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void drawNode(Node node) {


        Log.d("XXXX", "nodeH => " + node.getX() + " nodeW => " + node.getY() + " nodeName => " + node.getEtiquete());
        canvas.drawCircle(node.getX(), node.getY(), 60, paint);
        zzst.invalidate();
    }


    @Override
    public boolean onTouch(View view, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downx = event.getX();
                downy = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                upx = event.getX();
                upy = event.getY();
                if (upx == downx && upy == downy) {
                    Log.d("XXXX", "nodeH => " +upx + " nodeW => " + upy + " nodeName touch");
                    canvas.drawCircle(upx, upy, 60, paint);
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
}
