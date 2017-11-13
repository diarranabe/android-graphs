package com.diarranabe.graphics1.graphedm;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.savvisingh.colorpickerdialog.ColorPickerDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DrawableGraph myDraw;
    ImageView supportView;

    int downx = 0;
    int downy = 0;

    int upx = 0;
    int upy = 0;

    int umpx = 0;
    int umpy = 0;

    final static long LONG_TOUCH_DURATION = 1000;
    long touchStartTime = 0;

    public boolean optionPopupVisible = false;
    private Integer selectedColor;

    public Node selectedNode = null;


    static {
        graph = new Graph(4);
    }

    private static Graph graph;

    List<Integer> closestColorsList = new ArrayList<Integer>();


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myDraw = new DrawableGraph(graph);
        closestColorsList = myDraw.graph.getNodeColors();
        setContentView(R.layout.activity_main);
        supportView = (ImageView) findViewById(R.id.imageView);
        supportView.setImageDrawable(myDraw);

        supportView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downx = (int) event.getX();
                        downy = (int) event.getY();
                        touchStartTime = System.currentTimeMillis();
                        break;
                    case MotionEvent.ACTION_UP:
                        myDraw.setTempArcNull();
                        upx = (int) event.getX();
                        upy = (int) event.getY();
                        if ((Math.abs(upx - downx) < 10) || (Math.abs(upy - downy) < 10)) {
                            int number = 1 + myDraw.graph.getNoeuds().size();
                            Node node = new Node(upx, upy, number + "");
                            if (graph.addNode(node)) {

                            }
                            supportView.invalidate();
                        }
                        Node n1 = graph.selectedNode(downx, downy);
                        Node n2 = graph.selectedNode(upx, upy);
                        if ((n1 != null) && (n2 != null)) {
                            Arc a = new Arc(n1, n2);
                            graph.addArc(a);
                            supportView.invalidate();
                        } else if (n1 != null) {
                            n1.upadte(upx, upy);
                            supportView.invalidate();
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        umpx = (int) event.getX();
                        umpy = (int) event.getY();

                        Node startNode = graph.selectedNode(downx, downy);
                        Node tempNode = new Node(umpx, umpy);
                        if ((startNode != null) && (tempNode != null)) {
                            Arc tempArc = new Arc(startNode, tempNode);
                            myDraw.setTempArc(tempArc);
                            supportView.invalidate();
                        }
                        long time = System.currentTimeMillis() - touchStartTime;
                        if (time > LONG_TOUCH_DURATION) {
                            Log.d("XXXXT", " time diff = " + time);
                            if ((startNode != null)) {
                                if ((tempNode.overlap(startNode))) {// pas encore fais de mouvement ou pas encore loin
                                    if (!optionPopupVisible) {
                                        optionPopupVisible = true;
                                        selectedNode = startNode;
                                        showOptions();
                                    }
                                }
                                return true;
                            }
                        }
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                }
                return true;
            }
        });
    }

    /**
     * color for Node
     */
    public void showColorPopup() {
        final ColorPickerDialog dialog = ColorPickerDialog.newInstance(
                ColorPickerDialog.SELECTION_SINGLE,
                (ArrayList<Integer>) closestColorsList,
                3, // Number of columns
                ColorPickerDialog.SIZE_SMALL);

        dialog.show(getFragmentManager(), "some_tag");

        dialog.setOnDialodButtonListener(new ColorPickerDialog.OnDialogButtonListener() {
            @Override
            public void onDonePressed(ArrayList<Integer> mSelectedColors) {
                if (dialog.getSelectedColors().size() > 0) {
                    selectedColor = dialog.getSelectedColors().get(0);
                    selectedNode.setColor(selectedColor);
                    supportView.invalidate();
                    optionPopupVisible = false;
                }
            }

            @Override
            public void onDismiss() {

            }
        });
    }

    /**
     * Color for Arc
     */


    public void showColorPopupArc(final Arc arcc) {
        final ColorPickerDialog dialog = ColorPickerDialog.newInstance(
                ColorPickerDialog.SELECTION_SINGLE,
                (ArrayList<Integer>) closestColorsList,
                3, // Number of columns
                ColorPickerDialog.SIZE_SMALL);

        dialog.show(getFragmentManager(), "some_tag");

        dialog.setOnDialodButtonListener(new ColorPickerDialog.OnDialogButtonListener() {
            @Override
            public void onDonePressed(ArrayList<Integer> mSelectedColors) {
                if (dialog.getSelectedColors().size() > 0) {
                    selectedColor = dialog.getSelectedColors().get(0);
                    arcc.setColor(selectedColor);
                    supportView.invalidate();
                    optionPopupVisible = false;
                }
            }

            @Override
            public void onDismiss() {

            }
        });
    }


    public void showDefaultColorPopup() {
        final ColorPickerDialog dialog = ColorPickerDialog.newInstance(
                ColorPickerDialog.SELECTION_SINGLE,
                (ArrayList<Integer>) closestColorsList,
                3, // Number of columns
                ColorPickerDialog.SIZE_SMALL);

        dialog.show(getFragmentManager(), "some_tag");

        dialog.setOnDialodButtonListener(new ColorPickerDialog.OnDialogButtonListener() {
            @Override
            public void onDonePressed(ArrayList<Integer> mSelectedColors) {
                if (dialog.getSelectedColors().size() > 0) {
                    Node.DEFAULT_COLOR = dialog.getSelectedColors().get(0);
                }
            }

            @Override
            public void onDismiss() {

            }
        });
    }

    public void showOptions() {
        OptionDialogClass optionDialog = new OptionDialogClass(this);
        optionDialog.show();

    }

    public void showOptionsArc() {
        OptionDialogArc optionDialogArc = new OptionDialogArc(this);
        optionDialogArc.show();

    }

    public List<Arc> getSelectArc() {
        return graph.getArcOutOfMe(selectedNode);
    }

}


