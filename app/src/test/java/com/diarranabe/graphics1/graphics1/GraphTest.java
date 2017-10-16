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
        Node n0 = new Node(0, 0);
        Node n1 = new Node(20, 20);
        Node n2 = new Node(40, 20);
        Node n3 = new Node(40, 40);
        Node n4 = new Node(0, 20);
        Node n5 = new Node(40, 0);

        Node na = new Node(40, 60);
        Node n6 = new Node(10, 25);
        Node n7 = new Node(21, 22);
        Node n8 = new Node(44, 26);
        Node n9 = new Node(43, 41);
        Node nb = new Node(20, 60);

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

        Node.printNodes(graph.getNoeuds());
        System.out.println("----- size: " + graph.getNoeuds().size());

        assertEquals(graph.getNoeuds().size(), 8);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Test
    public void removeNode1() throws Exception {
        Graph g = new Graph(5);
        System.out.println("----------------Before Remove");
        Node.printNodes(g.getNoeuds());
        g.removeNode(g.getNoeuds().get(0));
        g.removeNode(g.getNoeuds().get(g.getNoeuds().size() - 1));
        System.out.println("----------------Remove begin & end items");
        Node.printNodes(g.getNoeuds());
        assertEquals(g.getNoeuds().size(), 3);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Test
    public void removeNode2() throws Exception {
        Graph g = new Graph(5);
        System.out.println("----------------Before Remove");
        Node.printNodes(g.getNoeuds());
        g.removeNode(4);
        g.removeNode(0);
        System.out.println("----------------Remove 0 & 4 items");
        Node.printNodes(g.getNoeuds());
        assertEquals(g.getNoeuds().size(), 3);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Test
    public void removeArc1() throws Exception {
        Graph g = new Graph(5);
        System.out.println("----------------Before Remove");
        g.addRandomArcs();
        int arcs = g.getArcs().size();
        Arc.printArcs(g.getArcs());
        System.out.println("----------------Remove 2 arcs");
        g.removeArc(g.getArcs().get(0));
        g.removeArc(g.getArcs().get(g.getArcs().size()-1));
        Arc.printArcs(g.getArcs());
        assertEquals(arcs-2, g.getArcs().size());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Test
    public void removeArc2() throws Exception {
        Graph g = new Graph(5);
        System.out.println("----------------Before Remove");
        g.addRandomArcs();
        int arcs = g.getArcs().size();
        Arc.printArcs(g.getArcs());
        System.out.println("----------------Remove 2 arcs");
        g.removeArc(0);
        g.removeArc(g.getArcs().size()-1);
        Arc.printArcs(g.getArcs());
        assertEquals(arcs-2, g.getArcs().size());
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Test
    public void addArcs() throws Exception {
        Node n1 = new Node(20, 20);
        Node n2 = new Node(40, 20);
        Node n3 = new Node(40, 40);
        Node n4 = new Node(0, 20);
        Node n5 = new Node(40, 0);
        Graph graph = new Graph();
        graph.addArc(new Arc(n1, n2));
        graph.addArc(new Arc(n2, n5));
        graph.addArc(new Arc(n1, n4));
        graph.addArc(new Arc(n3, n4));

        System.out.println("taille : " + graph.getArcs().size());
        Arc.printArcs(graph.getArcs());

        assertEquals(graph.getArcs().size(), 4);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Test
    public void addRandomNodes() throws Exception {
        System.out.println("----------- test generating random nodes");
        int nodesNum = 5;
        Graph graph = new Graph(nodesNum);
        Node.printNodes(graph.getNoeuds());
        assertEquals(graph.getNoeuds().size(), nodesNum);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Test
    public void addRandomNodesArcs() throws Exception {
        System.out.println("----------- test generating random arcs");
        int nodesNum = 5;
        Graph graph = new Graph(nodesNum);
        Node.printNodes(graph.getNoeuds());
        graph.addRandomArcs(4);
        Arc.printArcs(graph.getArcs());
        assertEquals(graph.getNoeuds().size(), nodesNum);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Test
    public void removeNodesArcs() throws Exception {
        System.out.println("----------- Before ");
        int nodesNum = 5;
        Graph graph = new Graph(nodesNum);
        graph.addRandomArcs();
        Node.printNodes(graph.getNoeuds());
        Arc.printArcs(graph.getArcs());
        System.out.println("----------- After remove 2 Nodes");
        graph.removeNode(graph.getNoeuds().get(0));

//        graph.removeNode(graph.getNoeuds().size()-1);
        Node.printNodes(graph.getNoeuds());
        Arc.printArcs(graph.getArcs());
        assertEquals(3,3);
    }
}
