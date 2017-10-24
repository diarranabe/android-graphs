package com.diarranabe.graphics1.graphics1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

/**
 * Created by matok on 24/10/2017.
 */

public class DrawableGraph extends View implements View.OnTouchListener {


    Paint paint, painte;
    Canvas canvas;

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

    public DrawableGraph(Context context) {
        super(context);
        this.setOnTouchListener(this);

        paint = new Paint();
        painte = new Paint();
        paint.setColor(Color.DKGRAY);
        painte.setColor(Color.WHITE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas =  canvas ;
        if (tempArc){

        }


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
        this.invalidate();

    }


    public void drawNode(Node node) {
        Log.d("XXXX", "nodeH => " + node.getX() + " nodeW => " + node.getY() + " nodeName => " + node.getEtiquete());
        canvas.drawCircle(node.getX(), node.getY(), node.getDiameter(), paint);
        this.invalidate();
    }

    public void unDrawNode(Node node)  {
        canvas.drawCircle(node.getX(), node.getY(), node.getDiameter() + 3, painte);
    }



    public void drawArc(int ax,int ay, int bx,int by){

        Paint p = new Paint();
        // smooths
        p.setAntiAlias(true);
        p.setColor(Color.RED);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(5);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawArc(ax,ay,bx,by, 90, 45, true, p);
        }
        this.invalidate();

    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {


        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:

                downx = (int) event.getX();
                downy = (int) event.getY();
                Log.e("XXXX", " ===> Cool touch " );
                Node auxNode = graph.selectedNode(downx, downy);
                if (null != auxNode) {

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
                    drawArc(downx,downy,umpx,umpy);
                    Log.e("XXXX", " ===> draw arc =  " );
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

}
