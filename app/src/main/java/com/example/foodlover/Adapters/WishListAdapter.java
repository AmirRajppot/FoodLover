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
import com.example.foodlover.Models.PurchaseModel;
import com.example.foodlover.Models.WishListModel;
import com.example.foodlover.R;

import java.util.ArrayList;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.ViewHolders> {
    ArrayList<WishListModel> data;
    Context ctx;

    public WishListAdapter(ArrayList<WishListModel> data, Context ctx) {
        this.data = data;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wish_list_box, parent, false);
        return new WishListAdapter.ViewHolders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolders holder, int position) {
        Glide.with(ctx).load(data.get(position).getImg()).into(holder.Img);
        holder.Name.setText(data.get(position).getName());
        holder.Price.setText(String.valueOf(data.get(position).getPrice()) + " PKR");


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolders extends RecyclerView.ViewHolder {
        TextView Name, Price;
        ImageView Img;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.wish_list_name);
            Price = itemView.findViewById(R.id.wish_list_price);
            Img = itemView.findViewById(R.id.wishlist_img);
        }
    }
}
