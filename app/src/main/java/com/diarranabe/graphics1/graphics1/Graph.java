package com.diarranabe.graphics1.graphics1;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by diarranabe on 04/10/2017.
 */

public class Graph {
    private Collection<Node> nodes;
    private Collection<Arc> arcs;

    public Collection<Node> getNoeuds() {
        return nodes;
    }

    public Collection<Arc> getArcs() {
        return arcs;
    }

    /**
     *  Ajoute un nouveau Node
     * @param node
     */
    public void addNode(Node node) {
        this.nodes.add(node);
    }


    /**
     * Supprime un Node du graphe
     * @param node
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void removeNode(Node node){
        for(Arc arc: arcs){
            if(arc.contains(node)){
                arcs.remove(arc);
            }
            nodes.remove(node);
        }
    }


    /**
     * Ajoute un Arc
     * @param arc
     */
    public void addArc(Arc arc) {
        this.arcs.add(arc);
    }

    /**
     * Supprime un Arc du graphe
     * @param arc
     */
    public void removeArc(Arc arc){
        arcs.remove(arc);
    }
}
