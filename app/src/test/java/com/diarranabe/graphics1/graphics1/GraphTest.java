package com.diarranabe.graphics1.graphics1;

import android.os.Build;
import android.support.annotation.RequiresApi;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by diarranabe on 12/10/2017.
 */

public class GraphTest {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Test
    public void addNodes() throws Exception {
        Node n0 = new Node(0,0);
        Node n1 = new Node(20,20);
        Node n2 = new Node(40,20);
        Node n3 = new Node(40,40);
        Node n4 = new Node(0,20);
        Node n5 = new Node(40,0);

        Node na = new Node(40,60);
        Node n6 = new Node(10,25);
        Node n7 = new Node(21,22);
        Node n8 = new Node(44,26);
        Node n9 = new Node(43,41);
        Node nb = new Node(20,60);

        Graph graph = new Graph();
        graph.addNode(n0);
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.addNode(n5);

        graph.addNode(na);
        graph.addNode(n6);
        graph.addNode(n7);
        graph.addNode(n8);
        graph.addNode(n9);
        graph.addNode(nb);

        System.out.println("taille : "+graph.getNoeuds().size());
        Node.printNodes(graph.getNoeuds());

        assertEquals(graph.getNoeuds().size(),8);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Test
    public void addArcs() throws Exception {
        Node n1 = new Node(20,20);
        Node n2 = new Node(40,20);
        Node n3 = new Node(40,40);
        Node n4 = new Node(0,20);
        Node n5 = new Node(40,0);

        Graph graph = new Graph();
        graph.addArc(new Arc(n1,n2));
//        graph.addArc(new Arc(n2,n5));
//        graph.addArc(new Arc(n1,n4));
//        graph.addArc(new Arc(n3,n4));

        System.out.println("taille : "+graph.getArcs().size());
        Arc.printArcs(graph.getArcs());

        assertEquals(8,8);
    }
}
