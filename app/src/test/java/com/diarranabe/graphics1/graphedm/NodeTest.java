package com.diarranabe.graphics1.graphedm;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by diarranabe on 11/10/2017.
 */

public class NodeTest {

    
    @Test
    public void getX() throws Exception {
        Node node = new Node(15,12);
        assertEquals(node.getX(),15);
    }

    
    @Test
    public void getY() throws Exception {
        Node node = new Node(15,12);
        assertEquals(node.getY(),12);
    }

    
    @Test
    public void update1() throws Exception {
        Node node = new Node(15,12);
        node.upadte(122,2);
        assertEquals(node.getX(),122);
    }

    
    @Test
    public void overlap1() throws Exception {
        Node n2 = new Node(15,12);
        Node n1 = new Node(15,102);
        assertNotEquals(n1.overlap(n2),true);
    }

    
    @Test
    public void overlap2() throws Exception {
        Node n2 = new Node(15,12);
        Node n1 = new Node(15,2);
        assertEquals(n1.overlap(n2),true);
    }

    
    @Test
    public void overlap3() throws Exception {
        Node n2 = new Node(15,12);
        Node n1 = new Node(100,12);
        assertNotEquals(n1.overlap(n2),true);
    }

    
    @Test
    public void overlap4() throws Exception {
        Node n2 = new Node(15,12);
        Node n1 = new Node(15,152);
        assertNotEquals(Node.overlap(n1,n2),true);
    }

    
    @Test
    public void overlap5() throws Exception {
        Node n2 = new Node(15,12);
        Node n1 = new Node(15,20);
        assertEquals(Node.overlap(n1,n2),true);
    }


    
    @Test
    public void isClose() throws Exception {
        Node n2 = new Node(175,12);
        assertEquals(n2.isClose(14,12),true);
    }
}
