package com.diarranabe.graphics1.graphedm;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.diarranabe.graphics1.graphedm.Arc.ARC_WIDTH;

/**
 * Created by matok on 24/10/2017.
 */

public class DrawableGraph extends Drawable {

    private static final int LABEL_TEXT_SIZE = 30;
    private Paint nodePaint;
    private Paint arcPaint;
    private Paint whitePaint;
    private Paint arcEtiqBackPaint;
    private Paint paint;
    private Paint nodeEtiqPaint;
    private Paint arcEtiqPaint;
    private Canvas canvas = new Canvas();

    private Path linePath = new Path();
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
        nodePaint.setColor(Color.DKGRAY);

        arcPaint = new Paint();
        arcPaint.setAntiAlias(true);
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(ARC_WIDTH);
        arcPaint.setColor(Color.RED);


        whitePaint = new Paint();
        whitePaint.setAntiAlias(true);
        whitePaint.setStyle(Paint.Style.STROKE);
        whitePaint.setStrokeWidth(ARC_WIDTH+5);
        whitePaint.setColor(Color.WHITE);

        arcEtiqPaint = new Paint();
        arcEtiqPaint.setColor(Color.WHITE);
        arcEtiqPaint.setTextSize(LABEL_TEXT_SIZE);
        arcEtiqPaint.setFakeBoldText(true);

        paint = new Paint();
        paint.setColor(Color.RED);

        nodeEtiqPaint = new Paint();
        nodeEtiqPaint.setColor(Color.WHITE);
        nodeEtiqPaint.setTextSize(LABEL_TEXT_SIZE);
        nodeEtiqPaint.setFakeBoldText(true);


        arcEtiqBackPaint = new Paint();
        arcEtiqBackPaint.setColor(Color.GRAY);

    }

    public void drawNode(Node node) {

        nodePaint.setColor(node.getColor());
        canvas.drawCircle(node.getX(), node.getY(), node.getWidth() + 9, nodeEtiqPaint);
        canvas.drawCircle(node.getX(), node.getY(), node.getWidth() + 3, nodePaint);

        //Afficher l'étiquette
        int xPos = node.getX() - (int) (nodeEtiqPaint.measureText(node.getEtiquete()) / 2);
        int yPos = (int) (node.getY() - ((nodeEtiqPaint.descent() + nodeEtiqPaint.ascent()) / 2));
        canvas.drawText(node.getEtiquete(), xPos, yPos, nodeEtiqPaint);
    }

    public void unDrawNode(Node node) {
        canvas.drawCircle(node.getX(), node.getY(), node.getWidth() + 3, arcPaint);
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
            canvas.drawCircle(0, 0, 1500, arcPaint);
        }
    }

    public void drawArcs() {
        for (Arc arcs : graph.getArcs()) {
            drawArc(arcs);
        }
        if (this.tempArc != null) {
            drawArc(tempArc);
        }
    }

    public void drawArc(Arc arc) {
        arcPaint.setColor(arc.getColor());
        canvas.drawPath(arc.getPath(), whitePaint);
        canvas.drawPath(arc.getPath(), arcPaint);

        /**
         * Afficher l'étiquette
         */
        float[] midPoint = {0f, 0f};
        float[] tangent = {0f, 0f};
        PathMeasure pm = new PathMeasure(arc.getPath(), false);
        pm.getPosTan(pm.getLength() * 0.50f, midPoint, tangent);

        int xPos = (int) midPoint[0] - (int) (nodeEtiqPaint.measureText(arc.getLabel()) / 2);
        int yPos = (int) (midPoint[1] - ((nodeEtiqPaint.descent() + nodeEtiqPaint.ascent()) / 2));
        xPos +=50;
        float w = arcEtiqPaint.measureText(arc.getLabel())/2;
        float textSize = arcEtiqPaint.getTextSize();
        arcEtiqPaint.setTextAlign(Paint.Align.CENTER);
        arcEtiqBackPaint.setColor(arc.getDebut().getColor());
        canvas.drawRect(xPos-w, yPos - textSize, xPos + w, yPos, arcEtiqBackPaint);
        canvas.drawText(arc.getLabel(), xPos, yPos, arcEtiqPaint);
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
