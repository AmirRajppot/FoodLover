package com.example.foodlover.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.foodlover.Adapters.DealAdapter;
import com.example.foodlover.HelperClass.AppConfig;
import com.example.foodlover.HelperClass.AppController;
import com.example.foodlover.Models.ProductModel;
import com.example.foodlover.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {
    TextView name_tv, email_tv, phone_tv, address_tv;
    Button btn_logout;
    SharedPreferences sharedPreferences;
    private static final String TAG = Login.class.getSimpleName();
    String user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        user_id = sharedPreferences.getString("id", "");

        get_profile(user_id);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout_user();
            }
        });
    }

    private void init() {

        name_tv = findViewById(R.id.profile_user_name);
        email_tv = findViewById(R.id.email_profile);
        btn_logout = findViewById(R.id.btn_logout);
        phone_tv = findViewById(R.id.profile_phone);
        address_tv = findViewById(R.id.address_profile);
    }

    private void get_profile(final String user_id) {
        String tag_str_req = "req_get_profile";
        StringRequest strReq = new StringRequest(Request.Method.GET, AppConfig.GET_PROFILE + "?user_id=" + user_id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    //check for error node in json
                    if (!error) {

                        name_tv.setText(jObj.getString("name"));
                        address_tv.setText(jObj.getString("address"));
                        email_tv.setText(jObj.getString("email"));
                        phone_tv.setText(jObj.getString("phone"));


                    } else {
                        String error_msg = jObj.getString("error_msg");
                        Toast.makeText(Profile.this, error_msg, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(Profile.this, "error of volley" + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_str_req);
    }

    public void logout_user() {
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        user_id = sharedPreferences.getString("id", "");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        finish();
        Intent intent = new Intent(Profile.this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}