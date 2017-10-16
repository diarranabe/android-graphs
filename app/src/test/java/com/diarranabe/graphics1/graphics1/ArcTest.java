package com.diarranabe.graphics1.graphics1;

import android.os.Build;
import android.support.annotation.RequiresApi;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by diarranabe on 11/10/2017.
 */

public class ArcTest {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Test
    public void arc() throws Exception {
        Node n1 = new Node(15,12);
        Node n2 = new Node(15,12);
        Arc arc = new Arc(n1,n2);
        System.out.println(arc);
        assertEquals(n1.getX(),15);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Test
    public void setDebut() throws Exception {
        Node n1 = new Node(5,2);
        Node n2 = new Node(15,12);
        Node n3 = new Node(85,52);
        Arc arc = new Arc(n1,n2);
        System.out.println("Avant update : "+arc);
        arc.setDebut(n3);
        System.out.println("Après update : "+arc);
        assertNotEquals(arc.getDebut().getX(),n1.getX());
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Test
    public void setFin() throws Exception {
        Node n1 = new Node(5,2);
        Node n2 = new Node(15,12);
        Node n3 = new Node(85,52);
        Arc arc = new Arc(n1,n2);
        System.out.println("Avant update : "+arc);
        arc.setFin(n3);
        System.out.println("Après update : "+arc);
        assertNotEquals(arc.getDebut().getX(),n2.getX());
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Test
    public void contains1() throws Exception {
        Node n1 = new Node(5,2);
        Node n2 = new Node(15,12);
        Node n3 = new Node(85,52);
        Arc arc = new Arc(n1,n2);
        assertNotEquals(arc.contains(n3),true);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Test
    public void contains2() throws Exception {
        Node n1 = new Node(5,2);
        Node n2 = new Node(15,12);
        Arc arc = new Arc(n1,n2);
        assertEquals(arc.contains(n2),true);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Test
    public void concerns1() throws Exception {
        Node n1 = new Node(5,2);
        Node n2 = new Node(15,12);
        Node n3 = new Node(85,52);
        Arc arc = new Arc(n1,n2);
        assertEquals(arc.concerns(n1,n2),true);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Test
    public void concerns2() throws Exception {
        Node n1 = new Node(5,2);
        Node n2 = new Node(15,12);
        Node n3 = new Node(85,52);
        Arc arc = new Arc(n1,n2);
        assertNotEquals(arc.concerns(n1,n3),true);
    }
}
