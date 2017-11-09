package com.diarranabe.graphics1.graphics1;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by diarranabe on 09/11/2017.
 */

public class OptionDialogClass extends Dialog {

    public MainActivity activity;
    public Dialog dialog;
    private Button yes;
    private Button no;
    private Button chooseColor;
    private Button deleteNode;
    private Button defaultColorBtn;
    private Button arcColorBtn;
    private EditText labelInput;
    private TextView nodeLabel;

    public OptionDialogClass(MainActivity activity){
        super(activity);
        // TODO Auto-generated constructor stub
        this.activity = activity;
        this.activity.optionPopupVisible = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup);

        yes = (Button) findViewById(R.id.savebtn);
        no = (Button) findViewById(R.id.dismiss);
        chooseColor = (Button) findViewById(R.id.choosecolorbtn);
        deleteNode = (Button) findViewById(R.id.deletebtn);
        defaultColorBtn = (Button) findViewById(R.id.defaultcolorbtn);
        arcColorBtn = (Button) findViewById(R.id.arcsetting);
        labelInput = (EditText) findViewById(R.id.node_label_input);
        nodeLabel = (TextView) findViewById(R.id.nodelabel);

        labelInput.setText(activity.selectedNode.getEtiquete());
        nodeLabel.setText(activity.selectedNode.getEtiquete());

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.selectedNode.setEtiquete(labelInput.getText().toString());
                activity.optionPopupVisible = false;
                activity.selectedNode = null;
                dismiss();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {
                activity.optionPopupVisible = false;
                dismiss();
            }
        });

        chooseColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.showColorPopup();
            }
        });

        defaultColorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.showDefaultColorPopup();
            }
        });

        deleteNode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.myDraw.graph.removeNode(activity.selectedNode);
                activity.optionPopupVisible = false;
                activity.supportView.invalidate();
                dismiss();
            }
        });


        arcColorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.showOptionsArc();

            }
        });
    }

}
