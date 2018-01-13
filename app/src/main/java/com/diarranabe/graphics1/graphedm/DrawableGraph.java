package com.diarranabe.graphics1.graphedm;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.graphics.RectF;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by matok on 24/10/2017.
 */

public class DrawableGraph extends Drawable {

    private Paint nodePaint, painte, paintr, etiqPaint, paintEtiqArc;
    private Canvas canvas = new Canvas();

    private Path linePath = new Path();

    int downx = 50;
    int downy = 50;

    int upx = 0;
    int upy = 0;

    int umpx = 0;
    int umpy = 0;

    Graph graph;
    private Arc tempArc = null; // arc temporaire pour suivre les mouvements

    public Arc getTempArc() {
        return tempArc;
    }

    public void setTempArc(Arc tempArc) {
        this.tempArc = tempArc;
    }

    public void setTempArcNull() {
        this.tempArc = null;
    }

    public DrawableGraph(Graph graph) {
        this.graph = graph;

        graph.addRandomArcs();

        nodePaint = new Paint();
        painte = new Paint();
        etiqPaint = new Paint();
        nodePaint.setColor(Color.DKGRAY);
        paintr = new Paint();
        paintr.setColor(Color.RED);
        painte.setColor(Color.RED);
        etiqPaint.setColor(Color.WHITE);
        etiqPaint.setTextSize(30);
        etiqPaint.setFakeBoldText(true);

        paintEtiqArc = new Paint();
        paintEtiqArc.setColor(Color.RED);
        paintEtiqArc.setTextSize(30);
        paintEtiqArc.setFakeBoldText(true);

        initialize();


    }

    public void initialize() {

        linePath.moveTo(0, 0);
        linePath.moveTo(150, 150);
        canvas.drawPath(linePath, paintr);
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

        nodePaint.setColor(node.getColor());
        canvas.drawCircle(node.getX(), node.getY(), node.getWidth() + 9, etiqPaint);
        canvas.drawCircle(node.getX(), node.getY(), node.getWidth() + 3, nodePaint);

        //Afficher l'étiquette
        int xPos = node.getX() - (int) (etiqPaint.measureText(node.getEtiquete()) / 2);
        int yPos = (int) (node.getY() - ((etiqPaint.descent() + etiqPaint.ascent()) / 2));
        canvas.drawText(node.getEtiquete(), xPos, yPos, etiqPaint);
    }

    public void unDrawNode(Node node) {
        canvas.drawCircle(node.getX(), node.getY(), node.getWidth() + 3, painte);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        this.canvas = canvas;

        drawArcs();

        drawNodes();
    }

    private void drawNodes() {
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

    public void drawArcs() {
        Log.e("XXXX", " ===> ic = ARCS START ");
        for (Arc arcs : graph.getArcs()) {
            drawArc(arcs);
        }
        if (this.tempArc != null) {
            drawArc(tempArc);
        }
    }

    public void drawArc(Arc arc) {
        painte.setColor(arc.getColor());
        int x1 = arc.getDebut().getX();
        int x2 = arc.getFin().getX();
        int y1 = arc.getDebut().getY();
        int y2 = arc.getFin().getY();
        int arcWidth = 20;
        final Path path = new Path();
        int midX = x1 + ((x2 - x1) / 2);
        int midY = y1 + ((y2 - y1) / 2);
        float xDiff = midX - x1;
        float yDiff = midY - y1;
        double angle = (Math.atan2(yDiff, xDiff) * (180 / Math.PI)) - 90;
        double angleRadians = Math.toRadians(angle);
        int curveRadius = -180;
        float pointX = (float) (midX + curveRadius * Math.cos(angleRadians));
        float pointY = (float) (midY + curveRadius * Math.sin(angleRadians));

        /**
         * L'arc
         */
        path.moveTo(x1, y1);
        path.cubicTo(x1, y1, pointX, pointY, x2, y2);
        painte.setAntiAlias(true);
        painte.setStyle(Paint.Style.STROKE);
        painte.setStrokeWidth(arcWidth);
        canvas.drawPath(path, painte);

        /**
         * Etiquette
         */
        float[] midPoint = {0f, 0f};
        float[] tangent = {0f, 0f};
        PathMeasure pm = new PathMeasure(path, false);
        pm.getPosTan(pm.getLength() * 0.50f, midPoint, tangent);

        //Afficher l'étiquette
        int xPos = (int) midPoint[0] - (int) (etiqPaint.measureText("nabe") / 2);
        int yPos = (int) (midPoint[1] - ((etiqPaint.descent() + etiqPaint.ascent()) / 2));
        canvas.drawText("nabe", xPos, yPos, paintEtiqArc);

        //draw arrowhead on path start
      /*  path.moveTo(x2,y2 ); //move to the center of first circle
        path.lineTo(x2-arcRadius, y2-arcRadius);//draw the first arrowhead line to the left
        path.moveTo(x2,y2 );//move back to the center
        path.lineTo(x2+arcRadius, y2-arcRadius);//draw the next arrowhead line to the right
*/
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int i) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }
}
