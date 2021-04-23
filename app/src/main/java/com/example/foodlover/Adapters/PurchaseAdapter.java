package com.example.foodlover.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        holder.id.setText(String.valueOf(data.get(position).getOrder_id()));
        holder.price.setText(String.valueOf(data.get(position).getPrice()));
        holder.date.setText(data.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolders extends RecyclerView.ViewHolder {
        TextView price, id, date;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.purchase_order_id);
            price = itemView.findViewById(R.id.purchase_price);
            date = itemView.findViewById(R.id.purchase_order_date);
        }
    }
}
