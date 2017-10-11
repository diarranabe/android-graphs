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
    public void isClose() throws Exception {
        Node n0 = new Node(0,0);
        Node n1 = new Node(20,20);
        Node n2 = new Node(40,20);
        Node n3 = new Node(40,40);
        Node n4 = new Node(0,20);
        Node n5 = new Node(40,0);
        assertEquals(n2.overlap(n3),true);
    }
}
