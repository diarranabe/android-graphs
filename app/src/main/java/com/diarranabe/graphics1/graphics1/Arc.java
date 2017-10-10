package com.diarranabe.graphics1.graphics1;

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

    private void updateDebut (Node nwNode){
        debut = nwNode;
    }

    private void updateFin (Node nwNode){
        fin = nwNode;
    }

    public boolean contains(Node node){
        return node instanceof Node && (Node.superpo(debut, node) || Node.superpo(fin, node));
    }
}
