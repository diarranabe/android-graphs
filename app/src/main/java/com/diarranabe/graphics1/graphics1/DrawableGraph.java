package com.diarranabe.graphics1.graphics1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by matok on 11/10/2017.
 */

public class DrawableGraph extends View {
    public DrawableGraph(Context context) {
        super(context);
    }

    private Paint paint;
    int x = 0;
    int y = 0;

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
        canvas.drawCircle(x / 2, y / 2, radius, paint);
    }

    public void kamiDraw(Graph graph){


    }

    private void drawArc(Arc arc){

    }
    private void drawNode(Node node){

    }
}
