package com.example.foodlover.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.foodlover.Adapters.CategoryAdapter;
import com.example.foodlover.Adapters.PurchaseAdapter;
import com.example.foodlover.Adapters.WishListAdapter;
import com.example.foodlover.Fragments.HomeFragment;
import com.example.foodlover.HelperClass.AppConfig;
import com.example.foodlover.HelperClass.AppController;
import com.example.foodlover.Models.CategoryModel;
import com.example.foodlover.Models.PurchaseModel;
import com.example.foodlover.Models.WishListModel;
import com.example.foodlover.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WishList extends AppCompatActivity {
    final static String TAG = HomeFragment.class.getSimpleName();

    RecyclerView recyclerView;
    WishListAdapter wishListAdapter;
    ArrayList<WishListModel> wishListModels = new ArrayList<>();
    String user_id;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);
        recyclerView = findViewById(R.id.wish_list_rv);
        preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        user_id = preferences.getString("id", "");
//        wishListModels.add(new WishListModel(1, 200,R.drawable.pizza,"Pizza"));
//        wishListModels.add(new WishListModel(1, 200,R.drawable.pizza,"Pizza"));
//        wishListModels.add(new WishListModel(1, 200,R.drawable.pizza,"Pizza"));
//        wishListModels.add(new WishListModel(1, 200,R.drawable.pizza,"Pizza"));
//        wishListModels.add(new WishListModel(1, 200,R.drawable.pizza,"Pizza"));
        get_wishList();
        wishListAdapter = new WishListAdapter(wishListModels, WishList.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(WishList.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(wishListAdapter);
    }

    private void get_wishList() {
        String tag_str_req = "req_get_wishList";
        StringRequest strReq = new StringRequest(Request.Method.GET, AppConfig.GET_WISH_LIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "1st Response:" + response);
                try {
                    JSONObject jObj = new JSONObject(response);
                    Log.e("second response:", response);
                    boolean error = jObj.getBoolean("error");
                    //check for error node in json
                    if (!error) {
                        JSONArray array = jObj.getJSONArray("menu");
                        for (int i = 0; i < array.length(); i++) {

                            JSONObject jsonObject = array.getJSONObject(i);
                            wishListModels.add(new WishListModel(jsonObject.getInt("id"),
                                    jsonObject.getInt("price"),
                                    jsonObject.getString("product_name"),
                                    jsonObject.getString("Img")));
                        }

                        wishListAdapter = new WishListAdapter(wishListModels, WishList.this);
                        recyclerView.setLayoutManager(new LinearLayoutManager(WishList.this, LinearLayoutManager.VERTICAL, false));
                        recyclerView.setAdapter(wishListAdapter);

                    } else {
                        String error_msg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(), error_msg, Toast.LENGTH_SHORT).show();

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
                        Toast.makeText(getApplicationContext(), "error of volley" + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_str_req);
    }
}