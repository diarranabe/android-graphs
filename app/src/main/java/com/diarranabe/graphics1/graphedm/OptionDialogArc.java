package com.diarranabe.graphics1.graphedm;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
        List<Arc> arcs = new ArrayList<Arc>();
        arcs = activity.getSelectArc();

        chooseColor = (Button) findViewById(R.id.arcsetcolor);
        no = (Button) findViewById(R.id.arcclose);

        Log.e("XXXX", " XZSE => " + arcs.toString());


        List<String> categories = new ArrayList<String>();
        for (Arc arc : arcs) {
            categories.add(" ==> " + arc.getFin().getEtiquete());
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
        chooseColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.showColorPopupArc((Arc) finalArcs.get((int) listspinner.getSelectedItemId()));
            }
        });

    }

}
