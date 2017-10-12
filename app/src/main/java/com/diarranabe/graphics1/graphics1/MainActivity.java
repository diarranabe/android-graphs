package com.diarranabe.graphics1.graphics1;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView champs;
    Graph graph  =  new Graph() ;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        champs = (ImageView) findViewById(R.id.imageView);
        initialize() ;

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void initialize(){
        Node n1 = new Node(250,250," N1 ", Color.valueOf(255,25,144));
        graph.addNode(n1);
        Node n2 = new Node(250,250," N1 ", Color.valueOf(255,0,144));graph.addNode(n2);
        Node n3 = new Node(250,250," N1 ", Color.valueOf(255,25,0));graph.addNode(n3);
        Node n4 = new Node(250,250," N1 ", Color.valueOf(0,25,144));graph.addNode(n4);
        Node n5 = new Node(250,250," N1 ", Color.valueOf(255,255,144));graph.addNode(n5);
        Node n6 = new Node(250,250," N1 ", Color.valueOf(255,25,255));graph.addNode(n6);
        Node n7 = new Node(250,250," N1 ", Color.valueOf(255,0,255));graph.addNode(n7);
        Node n8 = new Node(250,250," N1 ", Color.valueOf(155,25,144));graph.addNode(n8);
        Node n9 = new Node(250,250," N1 ", Color.valueOf(21,25,0));graph.addNode(n9);

        Arc a1 = new Arc(n1,n3) ;graph.addArc(a1);
        Arc a2 = new Arc(n1,n2) ;graph.addArc(a2);
        Arc a3 = new Arc(n2,n4) ;graph.addArc(a3);
        Arc a4 = new Arc(n3,n5) ;graph.addArc(a4);








    }


}
