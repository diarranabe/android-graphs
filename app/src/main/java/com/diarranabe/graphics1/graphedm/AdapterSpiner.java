package com.diarranabe.graphics1.graphedm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by matok on 09/11/2017.
 */

public class AdapterSpiner extends ArrayAdapter<Arc> {
    List<Arc> listArc;
    Arc arc;

    private final LayoutInflater mInflater;
    private final Context mContext;
    private final int mResource;


    public AdapterSpiner(@NonNull Context context, int resource, @NonNull List<Arc> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mResource = resource;
        this.listArc = objects;

    }

    @Override
    public int getCount() {
        return listArc.size();
    }

    @Override
    public Arc getItem(int i) {
        return listArc.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @SuppressLint("SetTextI18n")
    @Override

    public View getView(int i, View convertView, ViewGroup viewGroup) {
        return createItemView(i, convertView, viewGroup);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }


    @SuppressLint("SetTextI18n")
    private View createItemView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(mResource, parent, false);
        } else {
            view = convertView;
        }

        arc = listArc.get(position);


        // on se connecte a la vue pour charger les donnes
        TextView prixProduit = (TextView) view.findViewById(R.id.arc1);
        TextView nomProduit = (TextView) view.findViewById(R.id.arc2);


        // on populate les donnne c est a dire on les connecte a la vue

        prixProduit.setText("De : " + arc.getDebut().getEtiquete());
        nomProduit.setText("a : " + arc.getFin().getEtiquete());

        return convertView;
    }
}
