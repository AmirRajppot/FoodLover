package com.example.foodlover.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodlover.Models.DealModel;
import com.example.foodlover.Models.ProductModel;
import com.example.foodlover.R;

import java.util.ArrayList;

public class DealAdapter extends RecyclerView.Adapter<DealAdapter.ViewHolders> {
    ArrayList<DealModel> data;
    Context ctx;

    public DealAdapter(ArrayList<DealModel> data, Context ctx) {
        this.data = data;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.deal_box, parent, false);
        return new ViewHolders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolders holder, int position) {
        Glide.with(ctx).load(data.get(position).getImg()).into(holder.img);
        holder.price.setText(String.valueOf(data.get(position).getPrice()) + "PKR");
        holder.name.setText(data.get(position).getName());
        holder.des.setText(data.get(position).getDes());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolders extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name, price, des;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.deal_img);
            name = itemView.findViewById(R.id.deal_name);
            price = itemView.findViewById(R.id.deal_price);
            des = itemView.findViewById(R.id.deal_description);
        }
    }
}
