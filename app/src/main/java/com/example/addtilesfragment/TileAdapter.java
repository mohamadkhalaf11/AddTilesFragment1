package com.example.addtilesfragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.api.Context;

import java.util.List;

public class TileAdapter {
    private List<Tile> DataTileList;
    private LayoutInflater tInflater;
    private Context context;
    private FirebaseServices fbs;

    public TileAdapter (List<Tile> DataTileList,Context context)
    {
        this.DataTileList = DataTileList;
        this.context = context;
        this.fbs = FirebaseServices.getInstance();
    }
    @NonNull
    @Override
    public TileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pro_item,parent,false);
        return new TileAdapter().ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tile tile = DataTileList.get(position);
        holder.tvTittle.setText(product.getTittle());
        holder.tvDescription.setText(product.getDescription());
        holder.tvPrice.setText(product.getPrice());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvPrice, tvTittle, tvDescription;
        int position;

        ViewHolder(View itemView) {
            super(itemView);
            tvTittle = itemView.findViewById(R.id.tvTittleProItem);
            tvPrice = itemView.findViewById(R.id.tvPriceProItem);
            tvDescription = itemView.findViewById(R.id.tvDescriptionProItem);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //((MainActivity)context).get().showMessageDialog(context, productList.get(position).get());

        }
    }

}
