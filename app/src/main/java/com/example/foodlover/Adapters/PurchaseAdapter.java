package com.example.foodlover.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodlover.Activites.Home;
import com.example.foodlover.Activites.Login;
import com.example.foodlover.Activites.SignUp;
import com.example.foodlover.Models.CategoryModel;
import com.example.foodlover.Models.PurchaseModel;
import com.example.foodlover.Models.PurchaseModel;
import com.example.foodlover.R;

import java.util.ArrayList;

public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.ViewHolders> {

    ArrayList<PurchaseModel> data;
    Context ctx;

    public PurchaseAdapter(ArrayList<PurchaseModel> data, Context ctx) {
        this.data = data;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.purchase_history_box, parent, false);
        return new ViewHolders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolders holder, int position) {
        holder.price.setText(String.valueOf(data.get(position).getPrice()) + "PKR");
        holder.date.setText(data.get(position).getDate());
        holder.btn_reorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, Home.class);
              ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolders extends RecyclerView.ViewHolder {
        TextView price, date;
        Button btn_reorder;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.purchase_price);
            date = itemView.findViewById(R.id.purchase_order_date);
            btn_reorder = itemView.findViewById(R.id.reorder_btn);
        }
    }
}
