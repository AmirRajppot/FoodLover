package com.example.foodlover.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.example.foodlover.Activites.Home;
import com.example.foodlover.Activites.Login;
import com.example.foodlover.Activites.WishList;
import com.example.foodlover.Fragments.HomeFragment;
import com.example.foodlover.HelperClass.AppConfig;
import com.example.foodlover.HelperClass.AppController;
import com.example.foodlover.Models.PurchaseModel;
import com.example.foodlover.Models.WishListModel;
import com.example.foodlover.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.ViewHolders> {
    ArrayList<WishListModel> data;
    Context ctx;
    String user_id, product_id;
    SharedPreferences preferences;
    final static String TAG = HomeFragment.class.getSimpleName();

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


        Glide.with(ctx).load(AppConfig.IMAGE_URL + data.get(position).getImg()).into(holder.Img);
        holder.Name.setText(data.get(position).getName());
        holder.Price.setText(String.valueOf(data.get(position).getPrice()) + " PKR");
        holder.btn_remove_wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences = ctx.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                user_id = preferences.getString("id", "");
                Log.e(TAG, "onClick: " + user_id);
                product_id = String.valueOf(data.get(position).getId());
                Log.e(TAG, "onClick: " + product_id);
                remove_from_wishlist(user_id, product_id);
                notifyItemChanged(position);
                notifyDataSetChanged();
                Intent intent = new Intent(ctx, WishList.class);
                ctx.startActivity(intent);            }


        });


    }

    private void remove_from_wishlist(final String user_id_str, final String product_id_str) {
        String tag_str_req = "req_get_login";


        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.REMOVE_FROM_WISHLIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "1st Response:" + response);
                try {
                    JSONObject jObj = new JSONObject(response);
                    Log.e("second response:", response);
                    boolean error = jObj.getBoolean("error");
                    //check for error node in json
                    if (!error) {
                        Toast.makeText(ctx, "Remove From Wishlist", Toast.LENGTH_SHORT).show();
                    } else {
                        String error_msg = jObj.getString("error_msg");
                        Toast.makeText(ctx, "Error is " + error_msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Volley Error: " + error.getMessage());
                Toast.makeText(ctx, "error of volley" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("u_id", user_id_str);
                params.put("p_id", product_id_str);

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_str_req);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolders extends RecyclerView.ViewHolder {
        TextView Name, Price;
        ImageView Img, btn_remove_wishlist;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.wish_list_name);
            Price = itemView.findViewById(R.id.wish_list_price);
            Img = itemView.findViewById(R.id.wishlist_img);
            btn_remove_wishlist = itemView.findViewById(R.id.deal_btn_wishlist_close);

        }
    }
}
