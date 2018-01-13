package com.diarranabe.graphics1.graphedm;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matok on 09/11/2017.
 */

public class OptionDialogArc extends Dialog {

    public MainActivity activity;
    private Button no;
    private Button chooseColor;
    private Spinner listspinner;
    private EditText labelEdit;
    private Button removeArcBtn;

    public OptionDialogArc(MainActivity activity) {
        super(activity);
        this.activity = activity;
        this.activity.optionPopupVisible = true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popuparc);
        listspinner = (Spinner) findViewById(R.id.spinner);
        labelEdit = (EditText) findViewById(R.id.arcLabelEditText);
        removeArcBtn = (Button) findViewById(R.id.removeArc);

        List<Arc> arcs = new ArrayList<Arc>();
        arcs = activity.getSelectArc();

        chooseColor = (Button) findViewById(R.id.arcsetcolor);
        no = (Button) findViewById(R.id.arcclose);

        List<String> categories = new ArrayList<String>();
        for (Arc arc : arcs) {
            categories.add(arc.getLabel());
//            categories.add(" -- > " + arc.getFin().getEtiquete());
        }

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_spinner_item, categories);


        // attaching data adapter to spinner
        listspinner.setAdapter(dataAdapter);

        no.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                activity.optionPopupVisible = false;
                dismiss();
            }
        });

        final List<Arc> finalArcs = arcs;

        removeArcBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                activity.optionPopupVisible = false;
                MainActivity.graph.getArcs().remove(finalArcs.get((int) listspinner.getSelectedItemId()));
                dismiss();
            }
        });

        labelEdit.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                finalArcs.get((int) listspinner.getSelectedItemId()).setLabel(labelEdit.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

            }
        });


        chooseColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.showColorPopupArc((Arc) finalArcs.get((int) listspinner.getSelectedItemId()));
            }
        });

    }

}
