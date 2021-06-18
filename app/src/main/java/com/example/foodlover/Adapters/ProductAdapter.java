package com.example.foodlover.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.example.foodlover.Activites.Product;
import com.example.foodlover.Activites.WishList;
import com.example.foodlover.Fragments.HomeFragment;
import com.example.foodlover.HelperClass.AppConfig;
import com.example.foodlover.HelperClass.AppController;
import com.example.foodlover.Models.ProductModel;
import com.example.foodlover.Models.WishListModel;
import com.example.foodlover.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolders> {
    ArrayList<ProductModel> data;
    Context ctx;
    String user_id, product_id;
    RecyclerView recyclerView;
    SharedPreferences preferences;
    final static String TAG = HomeFragment.class.getSimpleName();


    public ProductAdapter(ArrayList<ProductModel> data, Context ctx) {
        this.data = data;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_box, parent, false);
        return new ProductAdapter.ViewHolders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolders holder, int position) {

        preferences = ctx.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        user_id = preferences.getString("id", "");
        Glide.with(ctx).load(AppConfig.IMAGE_URL + data.get(position).getImg()).into(holder.img);
        holder.name.setText(data.get(position).getName());
        holder.price.setText(String.valueOf(data.get(position).getPrice() + " Pkr"));

        holder.btn_wishlist_light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product_id = String.valueOf(data.get(position).getId());
                Log.e("dark", product_id);
                holder.btn_wishlist_light.setVisibility(View.GONE);
                holder.btn_wishlist_dark.setVisibility(View.VISIBLE);
                add_to_wishlist(user_id, product_id);
            }


        });
        holder.btn_wishlist_dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product_id = String.valueOf(data.get(position).getId());
                Log.e("", product_id);
                holder.btn_wishlist_dark.setVisibility(View.GONE);
                holder.btn_wishlist_light.setVisibility(View.VISIBLE);
                remove_from_wishlist(user_id, product_id);

            }


        });
        holder.btn_add_to_cart_light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product_id = String.valueOf(data.get(position).getId());

                holder.btn_add_to_cart_light.setVisibility(View.GONE);
                holder.btn_add_to_cart_dark.setVisibility(View.VISIBLE);
                add_to_cart(user_id, product_id);
            }


        });
        holder.btn_add_to_cart_dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product_id = String.valueOf(data.get(position).getId());

                holder.btn_add_to_cart_dark.setVisibility(View.GONE);
                holder.btn_add_to_cart_light.setVisibility(View.VISIBLE);
                remove_from_cart(user_id, product_id);
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

    private void remove_from_wishlist(final String user_id_str, final String product_id_str) {
        String tag_str_req = "req_remove_from_wishlist";


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

    private void add_to_wishlist(final String user_id_str, final String product_id_str) {
        String tag_str_req = "req_add_to_wishlist";
        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.ADD_TO_WISHLIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "1st Response:" + response);
                        try {
                            JSONObject jObj = new JSONObject(response);
                            Log.e("second response:", response);
                            boolean error = jObj.getBoolean("error");
                            //check for error node in json

                            if (!error) {

                                Toast.makeText(ctx, "Add to wishlist", Toast.LENGTH_SHORT).show();

                            } else {
                                String error_msg = jObj.getString("error_msg");
                                Toast.makeText(ctx, "" + error_msg, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
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


    private void add_to_cart(final String user_id_str, final String product_id_str
    ) {
        String tag_str_req = "req_add_to_cart";
        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.ADD_TO_CART,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "1st Response:" + response);
                        try {
                            JSONObject jObj = new JSONObject(response);
                            Log.e("second response:", response);
                            boolean error = jObj.getBoolean("error");
                            //check for error node in json

                            if (!error) {

                                Toast.makeText(ctx, "Add to Cart", Toast.LENGTH_SHORT).show();

                            } else {
                                String error_msg = jObj.getString("error_msg");
                                Toast.makeText(ctx, "" + error_msg, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Volley Error: " + error.getMessage());
                        Toast.makeText(ctx, "error of volley" + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("user_id", user_id_str);
                params.put("product_id", product_id_str);


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
        ImageView img, btn_wishlist_light, btn_wishlist_dark, btn_add_to_cart_light, btn_add_to_cart_dark;
        TextView name, price;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.product_img);
            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            btn_wishlist_light = itemView.findViewById(R.id.product_btn_wishlist_light);
            btn_wishlist_dark = itemView.findViewById(R.id.product_btn_wishlist_dark);
            btn_add_to_cart_light = itemView.findViewById(R.id.product_btn_plus_light);
            btn_add_to_cart_dark = itemView.findViewById(R.id.product_btn_plus_dark);
            recyclerView = itemView.findViewById(R.id.cart_rv);
        }
    }
}
