package com.example.foodlover.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodlover.Activites.Product;
import com.example.foodlover.Activites.Profile;
import com.example.foodlover.Fragments.HomeFragment;
import com.example.foodlover.Models.CategoryModel;
import com.example.foodlover.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolders> {

    ArrayList<CategoryModel> data;
    Context ctx;

    public CategoryAdapter(ArrayList<CategoryModel> data, Context ctx) {
        this.data = data;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_box, parent, false);
        return new ViewHolders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolders holder, int position) {

        Glide.with(ctx).load(data.get(position).getImg()).into(holder.img);
        holder.name.setText(data.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolders extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.category_img);
            name = itemView.findViewById(R.id.category_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ctx, Product.class);
                    ctx.startActivity(intent);
                }
            });
        }
    }
}
