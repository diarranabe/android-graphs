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
    private String etiquete;
    private Color color;
    private int radiaus;

    public static Color DEFAULT_COLOR = Color.valueOf(255,0,0);
    public  static int DEFAULT_RADIUS = 20;
    public  static int CHAR_LENGTH = 1;
    public static String DEFAULT_ETIQ = "";

    public Node(float x, float y) {
        this.x = x;
        this.y = y;
        this.color = DEFAULT_COLOR;
        this.etiquete = DEFAULT_ETIQ;
        setRadiaus();
    }

    public Node(float x, float y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.etiquete = DEFAULT_ETIQ;
        setRadiaus();
    }

    public Node(float x, float y, String etiquete) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.etiquete = etiquete;
        setRadiaus();
    }


    public Node(float x, float y, String etiquete, Color color) {
        this.x = x;
        this.y = y;
        this.etiquete = etiquete;
        this.color = color;
        setRadiaus();
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

    public String getEtiquete() {
        return etiquete;
    }

    public void setEtiquete(String etiquete) {
        this.etiquete = etiquete;
        setRadiaus();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getRadiaus() {
        return radiaus;
    }

    /**
     * Met un rayon en fonction de l'etiquete
     */
    public void setRadiaus() {
        switch (etiquete){
            case "":
                this.radiaus = DEFAULT_RADIUS;
                break;
            default:
                this.radiaus = etiquete.length()*CHAR_LENGTH;
        }
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
     * Met un Node à jour
     * @param x
     * @param y
     * @param etiquete
     * @param color
     */
    public void upadte(float x, float y, String etiquete, Color color){
        this.x = x;
        this.y = y;
        this.etiquete = etiquete;
        this.color = color;
        setRadiaus();
    }

    /**
     * Vérifie si un Node chevauche un autre
     * @param n
     * @return true si oui
     */
    private boolean overlap(Node n){
        return (Math.abs(x - n.getX())< DEFAULT_RADIUS) || (Math.abs(y - n.getY()) < DEFAULT_RADIUS);
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

    /**
     * Verifie si un Node est trop proche de d'une position
     * @param x
     * @param y
     * @return true ou false
     */
    public boolean isClose(int x, int y){
        return (Math.abs(this.x - x)< DEFAULT_RADIUS) || (Math.abs(this.y - y)< DEFAULT_RADIUS);
    }
}