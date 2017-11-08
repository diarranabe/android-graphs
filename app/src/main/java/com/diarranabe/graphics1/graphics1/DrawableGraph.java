package com.diarranabe.graphics1.graphics1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by matok on 24/10/2017.
 */

public class DrawableGraph extends Drawable {

    private Paint nodePaint, painte, paintr, etiqPaint;
    private Canvas canvas = new Canvas();

    private Path linePath = new Path();

    int downx = 50;
    int downy = 50;

    int upx = 0;
    int upy = 0;

    int umpx = 0;
    int umpy = 0;

    Graph graph ;
    private Arc tempArc = null; // arc temporaire pour suivre les mouvements

    public Arc getTempArc() {
        return tempArc;
    }

    public void setTempArc(Arc tempArc) {
        this.tempArc = tempArc;
    }

    public void setTempArcNull(){
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
        //Log.d("XXXX", " DRAW --> nodeH => " + node.getX() + " nodeW => " + node.getY() + " nodeName => " + node.getEtiquete() + " diametre +>" + node.getWidth());

        nodePaint.setColor(node.getColor());
        canvas.drawCircle(node.getX(),node.getY()  , node.getWidth() + 3, nodePaint);

        //Afficher l'étiquette
        int xPos = node.getX() - (int)(etiqPaint.measureText(node.getEtiquete())/2);
        int yPos = (int) (node.getY() - ((etiqPaint.descent() + etiqPaint.ascent()) / 2)) ;
        canvas.drawText(node.getEtiquete(), xPos, yPos, etiqPaint);
    }

    public void unDrawNode(Node node)  {
        canvas.drawCircle(node.getX(), node.getY(), node.getWidth() + 3, painte);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        this.canvas =  canvas ;

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

    public void drawArcs( ){
        Log.e("XXXX", " ===> ic = ARCS START ");
        for (Arc arcs : graph.getArcs()) {
            drawArc(arcs);
        }
        if (this.tempArc != null){
            drawArc(tempArc);
        }
    }

    public void drawArc(Arc arc){

        int x1 = arc.getDebut().getX();
        int x2 = arc.getFin().getX();
        int y1 = arc.getDebut().getY();
        int y2 = arc.getFin().getY();

        int mx =20+ (x1+x2)/2;
        int my =20+ (y1+y2)/2;

        float[] midPoint = {0f, 0f};
        float[] tangent = {0f, 0f};

        Path edgePath = new Path();
        edgePath.moveTo(x1, y1);

        PathMeasure pm = new PathMeasure(edgePath, false);
        pm.getPosTan(pm.getLength() * 0.50f, midPoint, tangent);

        edgePath.quadTo(mx, my, x2, y2);
        painte.setColor(arc.getDebut().getColor());
        canvas.drawPath(edgePath, painte);
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
