package com.example.foodlover.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.foodlover.Adapters.CategoryAdapter;
import com.example.foodlover.Adapters.DealAdapter;
import com.example.foodlover.Adapters.FamousAdapter;
import com.example.foodlover.HelperClass.AppConfig;
import com.example.foodlover.HelperClass.AppController;
import com.example.foodlover.Models.CategoryModel;
import com.example.foodlover.Models.DealModel;
import com.example.foodlover.Models.ProductModel;
import com.example.foodlover.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.alterac.blurkit.BlurKit;
import io.alterac.blurkit.BlurLayout;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {
    final static String TAG = HomeFragment.class.getSimpleName();

    TextView category_title, famous_title, deal_title;
    RecyclerView category_recyclerView, famous_recyclerView, deal_recyclerView;
    CategoryAdapter categoryAdapter;
    FamousAdapter famousAdapter;
    DealAdapter dealAdapter;
    String user_id, menu_id_str;
    SharedPreferences preferences;
    BlurLayout blurLayout;
    private final ArrayList<ProductModel> famous_model = new ArrayList<>();
    private final ArrayList<CategoryModel> categoryModels = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        category_recyclerView = view.findViewById(R.id.category_rv);
        famous_recyclerView = view.findViewById(R.id.famous_rv);
        category_title = view.findViewById(R.id.category_title);
        famous_title = view.findViewById(R.id.famous_title);
        deal_title = view.findViewById(R.id.deal_title);
        deal_recyclerView = view.findViewById(R.id.deal_rv);
//        blurLayout = view.findViewById(R.id.blurLayout);


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
//        else {
//            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }


        preferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        user_id = preferences.getString("id", "");

        get_famous();
        get_menu();
        get_deals();


        return view;
    }

    ///menu
    private void get_menu() {
        String tag_str_req = "req_get_menu";
        StringRequest strReq = new StringRequest(Request.Method.GET, AppConfig.GET_MENU, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "1st Response:" + response);
                try {
                    JSONObject jObj = new JSONObject(response);
                    Log.e("second response:", response);
                    boolean error = jObj.getBoolean("error");
                    //check for error node in json
                    if (!error) {
                        JSONArray array = jObj.getJSONArray("menu_items");
                        for (int i = 0; i < array.length(); i++) {

                            JSONObject jsonObject = array.getJSONObject(i);
                            categoryModels.add(new CategoryModel(jsonObject.getInt("id"),
                                    jsonObject.getString("image").replace("~/Images", ""),
                                    jsonObject.getString("name")));
                        }
                        menu_id_str = String.valueOf(array.getJSONObject(0).getInt("id"));
                        categoryAdapter = new CategoryAdapter(categoryModels, getActivity());
                        category_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        category_recyclerView.setAdapter(categoryAdapter);

                    } else {
                        String error_msg = jObj.getString("error_msg");
                        Toast.makeText(getContext(), error_msg, Toast.LENGTH_SHORT).show();
                        category_title.setVisibility(View.GONE);
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

    ///Famous
    private void get_famous() {
        String tag_str_req = "req_get_famous";
        StringRequest strReq = new StringRequest(Request.Method.GET, AppConfig.GET_FAMOUS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "1st Response:" + response);
                try {
                    JSONObject jObj = new JSONObject(response);
                    Log.e("second response:", response);
                    boolean error = jObj.getBoolean("error");
                    //check for error node in json
                    if (!error) {
                        JSONArray array = jObj.getJSONArray("famous");
                        for (int i = 0; i < array.length(); i++) {

                            JSONObject jsonObject = array.getJSONObject(i);
                            famous_model.add(new ProductModel(jsonObject.getInt("id"),
                                    jsonObject.getInt("price"),
                                    jsonObject.getString("name"),
                                    jsonObject.getString("des"),
                                    jsonObject.getString("image").replace("~/Images", "")));
                        }
                        famousAdapter = new FamousAdapter(famous_model, getActivity());
                        famous_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        famous_recyclerView.setAdapter(famousAdapter);

                    } else {
                        String error_msg = jObj.getString("error_msg");
                        Toast.makeText(getContext(), error_msg, Toast.LENGTH_SHORT).show();
                        famous_title.setVisibility(View.INVISIBLE);
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

    // DEALs
    private void get_deals() {
        String tag_str_req = "req_get_deals";
        StringRequest strReq = new StringRequest(Request.Method.GET, AppConfig.GET_DEALS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "1st Response:" + response);
                try {
                    JSONObject jObj = new JSONObject(response);
                    Log.e("Deal second response:", response);
                    boolean error = jObj.getBoolean("error");
                    //check for error node in json
                    if (!error) {
                        JSONArray array = jObj.getJSONArray("deal");
                        ArrayList<DealModel> deal_model = new ArrayList<>();
                        for (int i = 0; i < array.length(); i++) {
                            //Toast.makeText(getContext(), , Toast.LENGTH_SHORT).show();

                            deal_model.add(new DealModel(array.getJSONObject(i).getInt("id"), array.getJSONObject(i).getInt("price"), array.getJSONObject(i).getString("name"), array.getJSONObject(i).getString("image").replace("~/Images", ""), array.getJSONObject(i).getString("des")));
                        }
                        deal_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                        dealAdapter = new DealAdapter(deal_model, getContext());
                        deal_recyclerView.setAdapter(dealAdapter);

                    } else {
                        String error_msg = jObj.getString("error_msg");
                        Toast.makeText(getContext(), error_msg, Toast.LENGTH_SHORT).show();
                        deal_title.setVisibility(View.GONE);
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

}