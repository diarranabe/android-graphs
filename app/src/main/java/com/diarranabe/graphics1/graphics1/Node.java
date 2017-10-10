package com.diarranabe.graphics1.graphics1;


import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by diarranabe on 04/10/2017.
 */

@RequiresApi(api = Build.VERSION_CODES.O)

public class Node {
    private float x;
    private float y;
    private Color color;

    public static Color DEFAULT_COLOR = Color.valueOf(255,0,0);
    public  static int NODE_RADIUS = 20;

    public Node(float x, float y) {
        this.x = x;
        this.y = y;
        color = DEFAULT_COLOR;
    }

    public Node(float x, float y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    /**
     * Remplace les coodonnées d'un Node
     * @param x
     * @param y
     */
    public void upadte(float x, float y){
        this.x = x;
        this.y = y;
    }

    /**
     * Met un Node à jour
     * @param x
     * @param y
     * @param color
     */
    public void upadte(float x, float y, Color color){
        this.x = x;
        this.y = y;
        this.color = color;
    }

    /**
     * Vérifie si un Node chevauche un autre
     * @param n
     * @return true si oui
     */
    private boolean overlap(Node n){
        return (Math.abs(x - n.getX())< NODE_RADIUS) || (Math.abs(y - n.getY()) < NODE_RADIUS);
    }

    /**
     * Vérifie si deux Nodes se chevauchent
     * @param n1
     * @param n2
     * @return true si oui
     */
    public static boolean overlap(Node n1, Node n2){
        return n1.overlap(n2);
    }
}