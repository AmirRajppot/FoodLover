package com.example.foodlover.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.foodlover.Adapters.ProductAdapter;
import com.example.foodlover.HelperClass.AppConfig;
import com.example.foodlover.HelperClass.AppController;
import com.example.foodlover.Models.ProductModel;
import com.example.foodlover.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Product extends AppCompatActivity {
    private FragmentManager fragmentManager;
    int menu_Id;
    String product_id_str;
    RecyclerView product_rv;
    ProductAdapter productAdapter;
    private final ArrayList<ProductModel> productModels = new ArrayList<>();
    private static final String TAG = Login.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Bundle bundle = getIntent().getExtras();
        menu_Id = bundle.getInt("menu_id");
        get_products(String.valueOf(menu_Id));
        product_rv = findViewById(R.id.product_rv);
//
//        fragmentManager = getSupportFragmentManager();
//        final PaperOnboardingFragment paperOnboardingFragment = new PaperOnboardingFragment().newInstance(getDataForOnBoarding());
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.product_activity, paperOnboardingFragment);
//        fragmentTransaction.commit();
    }


    private void get_products(String menu_id) {
        String tag_str_req = "req_get_product";
        StringRequest strReq = new StringRequest(Request.Method.GET, AppConfig.GET_PRODUCTS + "?Menu_Id=" + menu_id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "1st Response:" + response);
                Log.e("api", AppConfig.GET_PRODUCTS + "?Menu_Id=" + menu_id);
                try {
                    JSONObject jObj = new JSONObject(response);
                    Log.e("Deal second response:", response);
                    boolean error = jObj.getBoolean("error");
                    //check for error node in json
                    if (!error) {
                        JSONArray array = jObj.getJSONArray("product");
                        productModels.clear();
                        for (int i = 0; i < array.length(); i++) {

                            productModels.add(new ProductModel(array.getJSONObject(i).getInt("id"),
                                    array.getJSONObject(i).getInt("price"),
                                    array.getJSONObject(i).getString("name"),
                                    array.getJSONObject(i).getString("des"),
                                    array.getJSONObject(i).getString("image").replace("~/images", "")
                                    ));

                            product_id_str = String.valueOf(array.getJSONObject(i).getInt("id"));
                        }


                        productAdapter = new ProductAdapter(productModels, Product.this);
                        product_rv.setLayoutManager(new GridLayoutManager(Product.this, 2, GridLayoutManager.VERTICAL, false));
                        product_rv.setAdapter(productAdapter);

                    } else {
                        String error_msg = jObj.getString("error_msg");
                        Toast.makeText(Product.this, error_msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Product.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Volley Error: " + error.getMessage());
                        Toast.makeText(Product.this, "error of volley" + error.getMessage(), Toast.LENGTH_SHORT).show();
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