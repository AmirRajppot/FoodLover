package com.example.foodlover.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textclassifier.TextClassificationManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.foodlover.Activites.WishList;
import com.example.foodlover.Adapters.CartAdapter;
import com.example.foodlover.Adapters.CategoryAdapter;
import com.example.foodlover.Adapters.WishListAdapter;
import com.example.foodlover.HelperClass.AppConfig;
import com.example.foodlover.HelperClass.AppController;
import com.example.foodlover.Models.CartModel;
import com.example.foodlover.Models.CategoryModel;
import com.example.foodlover.Models.ProductModel;
import com.example.foodlover.Models.WishListModel;
import com.example.foodlover.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cart extends Fragment {
    final static String TAG = HomeFragment.class.getSimpleName();
    TextView total_amount_tv;
    RecyclerView recyclerView;
    CartAdapter cartAdapter;
    Button btn_checkout;
    String user_id,product_id;
    String cart_data;
    int total_price = 0;
    int total_quantity =0;
    SharedPreferences preferences;

    private final ArrayList<CartModel> cart_model = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerView = view.findViewById(R.id.cart_rv);
        btn_checkout = view.findViewById(R.id.cart_btn_checkout);
        total_amount_tv = view.findViewById(R.id.cart_total_amount_tv);
        preferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        user_id = preferences.getString("id", "");
        Log.e("onclick",user_id);

        get_cart(user_id);
        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Gson gson = new Gson();
                total_quantity = 0;
                cart_data = gson.toJson(cart_model);
                Log.e("onclick",cart_data);
                for (int j = 0; j < cart_model.size(); j++) {

                    total_quantity = total_quantity + cart_model.get(j).getQty();
                    product_id = String.valueOf(cart_model.get(j).getId());


                }
                Log.e("onclick", String.valueOf(total_quantity));
                confirm_order(user_id, String.valueOf(total_quantity),
                        total_amount_tv.getText().toString(), cart_data);
                Log.e("amount", String.valueOf(total_amount_tv));
                remove_from_cart(user_id, product_id);


            }
        });
        return view;
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
                        Toast.makeText(getActivity(), "Remove From Cart", Toast.LENGTH_SHORT).show();
                    } else {
                        String error_msg = jObj.getString("error_msg");
                        Toast.makeText(getActivity(), "Error is " + error_msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Volley Error: " + error.getMessage());
                Toast.makeText(getActivity(), "error of volley" + error.getMessage(), Toast.LENGTH_SHORT).show();
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

    private void get_cart(final String user_id_str) {
        String tag_str_req = "req_get_cart";
        Log.e("click", "i am calling");
        StringRequest strReq = new StringRequest(Request.Method.GET, AppConfig.GET_CART + "?user_id=" + user_id_str, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "1st Response:" + response);
                try {
                    JSONObject jObj = new JSONObject(response);
                    Log.e("Deal second response:", response);
                    boolean error = jObj.getBoolean("error");
                    //check for error node in json
                    if (!error) {
                        JSONArray array = jObj.getJSONArray("cart_list");
                        for (int i = 0; i < array.length(); i++) {
                            //Toast.makeText(getContext(), , Toast.LENGTH_SHORT).show();

                            cart_model.add(new CartModel(array.getJSONObject(i).getInt("id"), array.getJSONObject(i).getInt("price"), array.getJSONObject(i).getString("img").replace("~/images", ""), array.getJSONObject(i).getString("name")));
                        }
                        for (int j = 0; j < cart_model.size(); j++) {
                            total_quantity = total_quantity + cart_model.get(j).getQty();
                            total_price = total_price + (cart_model.get(j).getPrice() * cart_model.get(j).getQty());
                        }
                        total_amount_tv.setText(String.valueOf(total_price));
                        cartAdapter = new CartAdapter(cart_model, getActivity(), total_amount_tv);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        recyclerView.setAdapter(cartAdapter);
                        cartAdapter.notifyDataSetChanged();

                    } else {
                        String error_msg = jObj.getString("error_msg");
                        Toast.makeText(getContext(), error_msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Volley Error: " + error.getMessage());
                        Toast.makeText(getContext(), "error of volley" + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {

            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_str_req);
    }

    private void confirm_order(final String user_id, final String sub_quantity, final String sub_total, final String cart_model_str
    ) {
        String tag_str_req = "req_order_confirm";
        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.CONFIRM_ORDER,
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
                                total_amount_tv.setText("");
                                cartAdapter.notifyDataSetChanged();
                                cart_model.clear();

                                Toast.makeText(getActivity(), "Order is Confirmed", Toast.LENGTH_SHORT).show();

                            } else {
                                String error_msg = jObj.getString("error_msg");
                                Toast.makeText(getActivity(), "" + error_msg, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getActivity(), "error of volley" + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("user_id", user_id);
                params.put("sub_quantity", sub_quantity);
                params.put("sub_total", sub_total);
                params.put("data", cart_model_str);

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_str_req);
    }


}