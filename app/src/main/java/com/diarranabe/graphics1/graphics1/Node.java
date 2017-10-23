package com.diarranabe.graphics1.graphics1;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.Collection;
import java.util.Random;

/**
 * Created by diarranabe on 04/10/2017.
 */


public class Node {
    private int x;
    private int y;
    private String etiquete;
    private int color;
    private int diameter;

    public static int DEFAULT_COLOR = Color.BLACK;
    public static int DEFAULT_RADIUS = 45;
    public static int CHAR_LENGTH = 12;
    public static String DEFAULT_ETIQ = "";

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.color = DEFAULT_COLOR;
        this.etiquete = DEFAULT_ETIQ;
        setRadiaus();
    }

    public Node(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.etiquete = DEFAULT_ETIQ;
        setRadiaus();
    }

    public Node(int x, int y, String etiquete) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.etiquete = etiquete;
        setRadiaus();
    }

    public Node(int x, int y, String etiquete, int color) {
        this.x = x;
        this.y = y;
        this.etiquete = etiquete;
        this.color = Color.BLACK;
        setRadiaus();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getEtiquete() {
        return etiquete;
    }

    public void setEtiquete(String etiquete) {
        this.etiquete = etiquete;
        setRadiaus();
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getDiameter() {
        return diameter;
    }

    /**
     * Met un rayon en fonction de l'etiquete
     */
    public void setRadiaus() {
        switch (etiquete) {
            case "":
                this.diameter = DEFAULT_RADIUS;
                break;
            default:
                this.diameter = etiquete.length() * CHAR_LENGTH;
        }
    }

    /**
     * Remplace les coodonnées d'un Node
     *
     * @param x
     * @param y
     */
    public void upadte(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Met un Node à jour
     *
     * @param x
     * @param y
     * @param color
     */
    public void upadte(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    /**
     * Met un Node à jour
     *
     * @param x
     * @param y
     * @param etiquete
     * @param color
     */
    public void upadte(int x, int y, String etiquete, int color) {
        this.x = x;
        this.y = y;
        this.etiquete = etiquete;
        this.color = color;
        setRadiaus();
    }

    /**
     * Vérifie si un Node chevauche un autre
     *
     * @param n
     * @return true si oui
     */
    public boolean overlap(Node n) {
        int sx = Math.abs(y - n.getY());
        int sy = Math.abs(x - n.getX());
        if ((sx < this.diameter+30) && (sy < this.diameter+30)) {
            Log.e("XXXX", " ===> surperpos dx= " + sx + " dy= "+ sy);
        }

        return ((sx < this.diameter+30) && (sy < this.diameter+30));

    }

    /**
     * Vérifie si deux Nodes se chevauchent
     *
     * @param n1
     * @param n2
     * @return true si oui
     */
    public static boolean overlap(Node n1, Node n2) {
        return n1.overlap(n2) && n2.overlap(n1);
    }

    /**
     * Verifie si un Node est trop proche de d'une position
     *
     * @param x
     * @param y
     * @return true ou false
     */
    public boolean isClose(int x, int y) {
        return (Math.abs(this.x - x) < diameter) || (Math.abs(this.y - y) < diameter);
    }

    public String toString() {
        return "{(" + x + "," + y + ")," + etiquete + ",color:" + color + "}";
    }

    /**
     * Affiche les d'une collection
     *
     * @param nodes
     */
    public static void printNodes(Collection<Node> nodes) {
        for (Node node : nodes) {
            System.out.println(node);
        }
        System.out.println(nodes.size() + " items -----------------");
    }

    public static int getRandomCoord(int max) {
        Random rand = new Random();
        int x = rand.nextInt(max) + 1;
        return (x / DEFAULT_RADIUS) * DEFAULT_RADIUS;
    }
}