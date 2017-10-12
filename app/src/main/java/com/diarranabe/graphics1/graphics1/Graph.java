package com.diarranabe.graphics1.graphics1;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by diarranabe on 04/10/2017.
 */

public class Graph {
    private List<Node> nodes;
    private List<Arc> arcs;

    public Graph() {
        this.nodes = new ArrayList<Node>();
        this.arcs = new ArrayList<Arc>();
    }

    public List<Node> getNoeuds() {
        return nodes;
    }

    public List<Arc> getArcs() {
        return arcs;
    }

    /**
     *  Ajoute un nouveau Node quand c'est possible
     * @param node
     * @return true quand le Node est ajouté
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean addNode(Node node) {
        boolean overlap = false;

        Iterator<Node> i=nodes.iterator();
        while(i.hasNext()){
            Node n = (Node)i.next();
            if(Node.overlap(n,node)){
                overlap = true;
                break;
            }
        }
        if (!overlap){
            this.nodes.add(node);
        }
        return overlap;
    }


    /**
     * Supprime un Node du graphe en suppriment aussi tous les arcs qui lui sont reliés
     * @param node
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void removeNode(Node node){
        for(Arc arc: arcs){
            if(arc.contains(node)){
                arcs.remove(arc);
            }
        }
        nodes.remove(node);
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean canDrawNode(int x, int y){
        for(Node node: nodes){
            if(node.isClose(x,y)){
                return false;
            }
        }
        return true;
    }
}
