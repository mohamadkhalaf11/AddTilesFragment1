package com.example.addtilesfragment;

import android.view.LayoutInflater;

import com.google.api.Context;

import java.util.List;

public class TileAdapter {
    private List<Tile> tData;
    private LayoutInflater tInflater;
    private Context context;
    private FirebaseServices fbs;

    public TileAdapter (List<Tile> tData,Context context)
    {
        this.tData = tData;
        this.context = context;
        this.fbs = FirebaseServices.getInstance();
    }


}
