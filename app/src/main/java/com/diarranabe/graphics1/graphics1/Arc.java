package com.diarranabe.graphics1.graphics1;

import android.os.Build;
import android.support.annotation.RequiresApi;

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

    public void setDebut(Node debut) {
        this.debut = debut;
    }

    public Node getFin() {
        return fin;
    }

    public void setFin(Node fin) {
        this.fin = fin;
    }

    /**
     * *
     * @param nwNode
     * Remplace le debut de l'arc par nwNode
     */
    public void updateDebut (Node nwNode){
        debut = nwNode;
    }

    /**
     * *
     * @param nwNode
     * Remplace la fin de l'arc par nwNode
     */
    public void updateFin (Node nwNode){
        fin = nwNode;
    }

    /**
     *
     * @param node
     * @return true si node est concerné par l'arc
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean contains(Node node){
        return node instanceof Node && (Node.overlap(debut, node) || Node.overlap(fin, node));
    }

    /**
     *
     * @param n1
     * @param n2
     * @return true si les Nodes n1 et n2 sont reliés par l'arc
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean concerns(Node n1, Node n2){
        return contains(n1) && contains(n2);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String toString (){
        return debut.toString()+" --->  "+fin.toString();
    }
}
