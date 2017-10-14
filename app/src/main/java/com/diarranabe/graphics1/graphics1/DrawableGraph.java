package com.diarranabe.graphics1.graphics1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by matok on 11/10/2017.
 */

public class DrawableGraph extends View {
    public DrawableGraph(Context context) {
        super(context);
    }

    private Paint paint;
    int x = 50;
    int y = 50;

    Canvas canvas ;



    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        this.canvas = canvas ;
        int radius;
        radius = 25;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        // Use Color.parseColor to define HTML colors
        paint.setColor(Color.parseColor("#CD5C5C"));
        canvas.drawCircle(x , y , radius, paint);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void kamiDraw(Graph graph){
        List<Node> nodes = graph.getNoeuds();
        List<Arc> arcs = graph.getArcs() ;
        for (Node node: nodes ) {
            drawNode(node);
        }
        for (Arc arc : arcs ) {
                 drawArc(arc);
        }


    }

    private void drawArc(Arc arc){


    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void drawNode(Node node){
        int radius;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            radius = node.getDiameter();
        }else {
            radius =75;
        }
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

       // canvas.drawPaint(paint);
        // Use Color.parseColor to define HTML colors
        paint.setColor(Color.parseColor("#CD5C5C"));
        canvas.drawCircle(node.getX() , node.getY() , radius, paint);

    }
}
