package com.example.foodlover.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.ContextMenu;
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
import com.example.foodlover.Activites.Product;
import com.example.foodlover.Activites.WishList;
import com.example.foodlover.Fragments.Cart;
import com.example.foodlover.Fragments.HomeFragment;
import com.example.foodlover.HelperClass.AppConfig;
import com.example.foodlover.HelperClass.AppController;
import com.example.foodlover.Models.CartModel;
import com.example.foodlover.Models.CategoryModel;
import com.example.foodlover.Models.ProductModel;
import com.example.foodlover.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolders> {
    ArrayList<CartModel> data;
    Context ctx;
    int count;
    String user_id, product_id;
    SharedPreferences preferences;
    TextView total_amount_tv;
    int total_quantity, total_amount;

    final static String TAG = HomeFragment.class.getSimpleName();

    public CartAdapter(ArrayList<CartModel> data, Context ctx, TextView Total_Amount) {
        this.data = data;
        this.ctx = ctx;
        this.total_amount_tv = Total_Amount;
    }

    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_box, parent, false);
        return new CartAdapter.ViewHolders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolders holder, int position) {
        Glide.with(ctx).load(AppConfig.IMAGE_URL + data.get(position).getImg()).into(holder.img);
        holder.name.setText(data.get(position).getName());
        holder.price.setText(String.valueOf(data.get(position).getPrice()));
        holder.qty.setText(String.valueOf(data.get(position).getQty()));
        holder.btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences = ctx.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                user_id = preferences.getString("id", "");
                Log.e(TAG, "onClick: " + user_id);
                product_id = String.valueOf(data.get(position).getId());
                Log.e(TAG, "onClick: " + product_id);
                total_amount = Integer.parseInt(total_amount_tv.getText().toString()) - (data.get(position).getPrice() * data.get(position).getQty());
                total_amount_tv.setText(String.valueOf(total_amount));
                remove_from_cart(user_id, product_id);
                data.remove(position);
                notifyDataSetChanged();
            }
        });


        holder.plus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = data.get(position).getQty();
                count++;
                holder.qty.setText(String.valueOf(count));
                data.get(position).setQty(count);
                total_quantity = 0;
                total_amount = 0;

                for (int j = 0; j < data.size(); j++) {
                    total_quantity = total_quantity + data.get(j).getQty();
                    total_amount = total_amount + (data.get(j).getPrice() * data.get(j).getQty());
                }
                total_amount_tv.setText((String.valueOf(total_amount)));

            }
        });
        holder.minus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = Integer.parseInt(String.valueOf(holder.qty.getText()));
                if (count == 1) {
                    holder.qty.setText("1");
                } else {
                    count -= 1;
                    holder.qty.setText("" + count);
                    data.get(position).setQty(count);
                    total_quantity = 0;
                    total_amount = 0;

                    for (int j = 0; j < data.size(); j++) {
                            total_quantity = total_quantity + data.get(j).getQty();
                        total_amount = total_amount + (data.get(j).getPrice() * data.get(j).getQty());
                    }
                    total_amount_tv.setText((String.valueOf(total_amount)));
                }
            }

        });
    }

    private void remove_from_cart(final String user_id_str, final String product_id_str) {
        String tag_str_req = "req_get_remove";


        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.REMOVE_FROM_CART, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "1st Response:" + response);
                try {
                    JSONObject jObj = new JSONObject(response);
                    Log.e("second response:", response);
                    boolean error = jObj.getBoolean("error");
                    //check for error node in json
                    if (!error) {
                        Toast.makeText(ctx, "Remove From Cart", Toast.LENGTH_SHORT).show();
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
        TextView name, price, plus_btn, minus_btn, qty, btn_close;
        ImageView img;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.cart_img);
            name = itemView.findViewById(R.id.cart_name);
            price = itemView.findViewById(R.id.cart_price);
            plus_btn = itemView.findViewById(R.id.cart_btn_plus);
            minus_btn = itemView.findViewById(R.id.cart_btn_minus);
            qty = itemView.findViewById(R.id.cart_quantity_tv);
            btn_close = itemView.findViewById(R.id.btn_close);
        }
    }
}
