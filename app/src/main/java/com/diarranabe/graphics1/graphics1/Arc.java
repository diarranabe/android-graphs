package com.diarranabe.graphics1.graphics1;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Collection;
import java.util.List;

/**
 * Created by diarranabe on 04/10/2017.
 */

public class Arc {
    private Node debut;
    private Node fin;

    public Arc(Node debut, Node fin) {
        this.debut = debut;
        this.fin = fin;
    }

    public Node getDebut() {
        return debut;
    }

    public Node getFin() {
        return fin;
    }

    public void setFin(Node fin) {
        this.fin = fin;
    }

    /**
     * *
     *
     * @param nwNode Remplace le debut de l'arc par nwNode
     */
    public void setDebut(Node nwNode) {
        debut = nwNode;
    }

    /**
     * @param node
     * @return true si node est concerné par l'arc
     */
    public boolean contains(Node node) {
        return node instanceof Node && (Node.overlap(debut, node) || Node.overlap(fin, node));
    }

    /**
     * @param n1
     * @param n2
     * @return true si les Nodes n1 et n2 sont reliés par l'arc
     */
    public boolean concerns(Node n1, Node n2) {
        return contains(n1) && contains(n2);
    }

    public String toString() {
        return debut.toString() + " --->  " + fin.toString();
    }


    /**
     * Affiche les d'une collection d'arcs
     *
     * @param arcs
     */
    public static void printArcs(Collection<Arc> arcs) {
        for (Arc arc : arcs) {
            System.out.println(arc);
        }
        System.out.println(arcs.size() + " items -----------------");
    }
}
