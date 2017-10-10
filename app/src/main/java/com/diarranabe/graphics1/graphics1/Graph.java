package com.diarranabe.graphics1.graphics1;

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

    public void addNode(Node node) {
        this.nodes.add(node);
    }
    
    public void removeNode(Node node){
        for(Arc arc: arcs){
            if(arc.contains(node)){
                arcs.remove(arc);
            }
            nodes.remove(node);
        }
    }

    public void addArc(Arc arc) {
        this.arcs.add(arc);
    }
    
    public void removeArc(Arc arc){
        arcs.remove(arc);
    }

    public Collection<Arc> getArcs() {
        return arcs;
    }
}
