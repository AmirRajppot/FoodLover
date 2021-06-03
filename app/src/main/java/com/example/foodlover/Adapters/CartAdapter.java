package com.example.foodlover.Adapters;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodlover.Activites.Product;
import com.example.foodlover.Models.CartModel;
import com.example.foodlover.Models.CategoryModel;
import com.example.foodlover.Models.ProductModel;
import com.example.foodlover.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolders> {
    ArrayList<CartModel> data;
    Context ctx;

    public CartAdapter(ArrayList<CartModel> data, Context ctx) {
        this.data = data;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_box, parent, false);
        return new CartAdapter.ViewHolders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolders holder, int position) {
        Glide.with(ctx).load(data.get(position).getImg()).into(holder.img);
        holder.name.setText(data.get(position).getName());
        holder.price.setText(String.valueOf(data.get(position).getPrice()));
        holder.quantity_tv.setText(String.valueOf(data.get(position).getQty()));
        holder.btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                t_quantity = Integer.parseInt(total_quantity_tv.getText().toString()) - data.get(position).getQty();
//                t_price = Integer.parseInt(total_price_tv.getText().toString()) - (data.get(position).getPrice() * data.get(position).getQty());
//
//                total_quantity_tv.setText(String.valueOf(t_quantity));
//                total_price_tv.setText(String.valueOf(t_price));

                data.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.plus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(String.valueOf(holder.quantity_tv.getText()));
                count++;
                holder.quantity_tv.setText("" + count);

//                t_quantity = data.get(position).getQty() + Integer.parseInt(total_quantity_tv.getText().toString());
//                t_price = (data.get(position).getPrice() * data.get(position).getQty()) + Integer.parseInt(total_price_tv.getText().toString());
//                total_quantity_tv.setText((String.valueOf(t_quantity)));
//                total_price_tv.setText((String.valueOf(t_price)));

            }
        });
        holder.minus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(String.valueOf(holder.quantity_tv.getText()));
                if (count == 1) {
                    holder.quantity_tv.setText("1");
                } else {
                    count -= 1;
                    holder.quantity_tv.setText("" + count);

//                    t_quantity = Integer.parseInt(total_quantity_tv.getText().toString()) - data.get(position).getQty();
//                    t_price = Integer.parseInt(total_price_tv.getText().toString()) - (data.get(position).getPrice() * data.get(position).getQty());
//                    total_quantity_tv.setText((String.valueOf(t_quantity)));
//                    total_price_tv.setText((String.valueOf(t_price)));
                }
            }

        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolders extends RecyclerView.ViewHolder {
        TextView name, price, plus_btn, minus_btn, quantity_tv, btn_close;
        ImageView img;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.cart_img);
            name = itemView.findViewById(R.id.cart_name);
            price = itemView.findViewById(R.id.cart_price);
            plus_btn = itemView.findViewById(R.id.cart_btn_plus);
            minus_btn = itemView.findViewById(R.id.cart_btn_minus);
            quantity_tv = itemView.findViewById(R.id.cart_quantity_tv);
            btn_close = itemView.findViewById(R.id.btn_close);
        }
    }
}
