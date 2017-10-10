package com.diarranabe.graphics1.graphics1;

import android.graphics.Color;

/**
 * Created by diarranabe on 04/10/2017.
 */

public class Node {
    private float x;
    private float y;
    private Color color;

    public  static int RAYON = 20;

    public Node(float x, float y) {
        this.x = x;
        this.y = y;
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

    public void upadte(float x, float y){
        this.x = x;
        this.y = y;
    }

    private boolean superpo(Node n){
        return (Math.abs(x - n.getX())<RAYON) || (Math.abs(y - n.getY()) <RAYON);
    }

    public static boolean superpo(Node n1, Node n2){
        return n1.superpo(n2);
    }
}
