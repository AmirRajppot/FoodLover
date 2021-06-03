package com.example.foodlover.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.foodlover.Adapters.FamousAdapter;
import com.example.foodlover.Adapters.PurchaseAdapter;
import com.example.foodlover.Fragments.HomeFragment;
import com.example.foodlover.HelperClass.AppConfig;
import com.example.foodlover.HelperClass.AppController;
import com.example.foodlover.Models.CategoryModel;
import com.example.foodlover.Models.ProductModel;
import com.example.foodlover.Models.PurchaseModel;
import com.example.foodlover.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PurchaseHistory extends AppCompatActivity {
    final static String TAG = HomeFragment.class.getSimpleName();
    RecyclerView recyclerView;
    PurchaseAdapter purchaseAdapter;
    ArrayList<PurchaseModel> purchaseModels = new ArrayList<>();
    SharedPreferences preferences;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_history);
        recyclerView = findViewById(R.id.purchase_rv);

        preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        user_id = preferences.getString("id", "");
//        purchaseModels.add(new PurchaseModel(1, 2000, 3, "10/20/2021"));
//        purchaseModels.add(new PurchaseModel(1, 2000, 4, "10/20/2021"));
//        purchaseModels.add(new PurchaseModel(1, 2000, 7, "10/20/2021"));

        get_purchase_history(user_id);

    }

    private void get_purchase_history(final String user_id) {
        String tag_str_req = "req_get_purchase_history";
        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.GET_PURCHASE_HISTORY +"?user_id=" + user_id , new Response.Listener<String>() {
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
                            purchaseModels.add(new PurchaseModel(jsonObject.getInt("id"),
                                    jsonObject.getInt("id"),
                                    jsonObject.getInt("total"),
                                    jsonObject.getString("order_date")));
                        }

                        purchaseAdapter = new PurchaseAdapter(purchaseModels, PurchaseHistory.this);
                        recyclerView.setLayoutManager(new LinearLayoutManager(PurchaseHistory.this, LinearLayoutManager.VERTICAL, false));
                        recyclerView.setAdapter(purchaseAdapter);

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