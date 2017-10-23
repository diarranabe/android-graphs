package com.diarranabe.graphics1.graphics1;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RequiresPermission;
import android.support.annotation.StringDef;
import android.support.annotation.StyleRes;
import android.util.Log;
import android.view.Display;
import android.widget.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by diarranabe on 04/10/2017.
 */

public class  Graph {

    public static int MAX_X = 700;
    public static int MAX_Y = 1000;
    private List<Node> nodes = new ArrayList<Node>();
    private List<Arc> arcs = new ArrayList<Arc>();

    public Graph() {

    }

    public Graph(int n) {
        Random rand = new Random();
        for (int i=0; i<n; i++){
            int  x = Node.getRandomCoord(MAX_X);
            int  y = Node.getRandomCoord(MAX_Y);
            Node node = new Node(x,y);
            node = new Node(x,y);
            boolean add = addNode(node);
            while (!add ){
                x = Node.getRandomCoord(MAX_X);
                y = Node.getRandomCoord(MAX_Y);
                node = new Node(x,y);
            }
        }
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
        return !overlap; // prouve que le node à bien de l'espace
    }


    /**
     * Retourne le node selectionné
     * @param x
     * @param y
     * @return
     */
    public Node selectedNode(int x, int y) {
        Node node = new Node(x,y);
        boolean overlap = false;

        Iterator<Node> i=nodes.iterator();
        while(i.hasNext()){
            Node n = (Node)i.next();
            if(Node.overlap(n,node)){

                Log.i("XXXX", "Voici ton objey");
                return n;

            }
        }
        return null;
    }


    /**
     *  Ajoute un nouveau Node quand c'est possible
     * @param x
     * @param y
     * @return true quand le Node est ajouté
     */
    public boolean addNode(int x, int y) {
        Node node = new Node(x,y);
        boolean overlap = false;

        Iterator<Node> i=nodes.iterator();
        baka:while(i.hasNext()){
            Node n =  i.next();
            if(Node.overlap(n,node)){
                overlap = true;
                break baka;
            }
        }
        if (!overlap){
            Log.e("XXXX", " ok n'existe pas "+ overlap);
            this.nodes.add(node);
        }
        return !overlap; // prouve que le node à bien de l'espace
    }


    /**
     * Supprime un Node du graphe en suppriment aussi tous les arcs qui lui sont reliés
     * @param node
     */

    public void removeNode(Node node){
        try {
            for(Arc arc: arcs){
                if(arc.contains(node)){
                    arcs.remove(arc);
                }
            }
            nodes.remove(node);
        }catch (ConcurrentModificationException e){
            removeNode(node); // passe 1 fois sur 4 donc executé plusieurs fois par appel
        }
    }

    /**
     * Supprime le Node à la position nodeIndex du graphe en suppriment aussi tous les arcs qui lui sont reliés
     * @param nodeIndex
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void removeNode(int nodeIndex){
        if (nodeIndex<nodes.size()){
            Node node = nodes.get(nodeIndex);
            for(Arc arc: arcs){
                if(arc.contains(node)){
                    arcs.remove(arc);
                }
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
     * Ajoute un arc entre les nodes index1 et index2 de la liste de nodes
     * @param index1
     * @param index2
     */
    public void addArc(int index1, int index2) {
        if (index1 != index2){
            Node n1 = getNoeuds().get(index1);
            Node n2 = getNoeuds().get(index2);
            this.arcs.add(new Arc(n1,n2));
        }

    }

    /**
     * Supprime un Arc du graphe
     * @param arc
     */
    public void removeArc(Arc arc){
        arcs.remove(arc);
    }

    /**
     * Supprime l'Arc à la position arcIndex des arcs du graphe
     * @param arcIndex
     */
    public void removeArc(int arcIndex){
        arcs.remove(getArcs().get(arcIndex));
    }

    /**
     * Verifie si on peut ajouter à un un emplacement
     * @param x
     * @param y
     * @return true si possible
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean canDrawNode(int x, int y){
        for(Node node: nodes){
            if(node.isClose(x,y)){
                return false;
            }
        }
        return true;
    }

    /**
     * Retoune l'index d'un Node de manière aléatoire
     * @return un index
     */
    public int getRandomNodeIndex(){
        Random rand = new Random();
        int  x = rand.nextInt(getNoeuds().size());
        return x;
    }

    /**
     * Ajoute n arcs de manière aléatoire (n<=nombre de nodes)
     */
    public void addRandomArcs(){
        for (int i = 0; i<nodes.size(); i++){
            addArc(getRandomNodeIndex(),getRandomNodeIndex());
        }
    }

    /**
     * Ajoute n arcs de manière aléatoire (n<=nombre de nodes)
     * @param n si n > nombre de nodes il est ignoré, addRandomArcs() est executé
     *
     */
    public void addRandomArcs(int n){
        if (n<nodes.size()){
            for (int i = 0; i<n; i++){
                addArc(getRandomNodeIndex(),getRandomNodeIndex());
            }
        }
        else {
            addRandomArcs();
        }
    }
}
