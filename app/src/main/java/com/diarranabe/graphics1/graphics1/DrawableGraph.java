package com.diarranabe.graphics1.graphics1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by matok on 24/10/2017.
 */

public class DrawableGraph extends Drawable {

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

    Graph graph ;


    public DrawableGraph(Graph graph) {
        this.graph = graph;
        paint = new Paint();
        painte = new Paint();
        paint.setColor(Color.DKGRAY);
        paintr = new Paint();
        paintr.setColor(Color.RED);
        painte.setColor(Color.WHITE);
      //  this.draw(this.canvas);
        initialize();


    }



    public void initialize() {

        linePath.moveTo(0,0);
        linePath.moveTo(150,150);
        canvas.drawPath(linePath,paintr);
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
        Log.d("XXXX", " DRAW --> nodeH => " + node.getX() + " nodeW => " + node.getY() + " nodeName => " + node.getEtiquete() + " diametre +>" + node.getWidth());
        canvas.drawCircle(node.getX(),node.getY()  , node.getWidth() + 3, paint);

    }

    public void unDrawNode(Node node)  {
        canvas.drawCircle(node.getX(), node.getY(), node.getWidth() + 3, painte);
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


    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        this.canvas =  canvas ;
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

        if (tempArc){
            linePath.lineTo(umpx,umpy);
            this.canvas.drawPath(linePath,paintr);
            // linePath.reset();
        }

    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int i) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }
}
