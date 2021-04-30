package com.example.foodlover.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class HomeFragment extends Fragment {
    final static String TAG = HomeFragment.class.getSimpleName();

    TextView category_title, famous_title, deal_title;
    RecyclerView category_recyclerView, famous_recyclerView, deal_recyclerView;
    CategoryAdapter categoryAdapter;
    FamousAdapter famousAdapter;
    DealAdapter dealAdapter;
    private final ArrayList<ProductModel> famous_model = new ArrayList<>();
    private final ArrayList<DealModel> deal_model = new ArrayList<>();
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
        //deal
        deal_model.add(new DealModel(1, R.drawable.lisa, 1220, "Pasta", "If you also have adequate knowledge about Different Mouth watering Foods and its materials that come with a different taste and regions."));
        deal_model.add(new DealModel(1, R.drawable.pexcels, 1220, "Burger", "If you also have adequate knowledge about Different Mouth watering Foods and its materials that come with a different taste and regions."));
        deal_model.add(new DealModel(1, R.drawable.pizza, 1220, "Pizza", "If you also have adequate knowledge about Different Mouth watering Foods and its materials that come with a different taste and regions."));
        deal_model.add(new DealModel(1, R.drawable.lisa, 1220, "Macrooni", "If you also have adequate knowledge about Different Mouth watering Foods and its materials that come with a different taste and regions."));
        dealAdapter = new DealAdapter(deal_model, getActivity());
        deal_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        deal_recyclerView.setAdapter(dealAdapter);

        //famous
        famous_model.add(new ProductModel(1, 1220, R.drawable.salad, "Pasta", "If you also have adequate knowledge about Different Mouth watering Foods and its materials that come with a different taste and regions."));
        famous_model.add(new ProductModel(1, 1220, R.drawable.pizza, "Pixel", "If you also have adequate knowledge about Different Mouth watering Foods and its materials that come with a different taste and regions."));
        famous_model.add(new ProductModel(1, 1220, R.drawable.lisa, "Pasta", "If you also have adequate knowledge about Different Mouth watering Foods and its materials that come with a different taste and regions."));
        famous_model.add(new ProductModel(1, 1220, R.drawable.pexcels, "Pasta", "If you also have adequate knowledge about Different Mouth watering Foods and its materials that come with a different taste and regions."));

        famousAdapter = new FamousAdapter(famous_model, getActivity());
        famous_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        famous_recyclerView.setAdapter(famousAdapter);
        //menu
//        categoryModels.add(new CategoryModel(1, R.drawable.salad, "Pizza"));
//        categoryModels.add(new CategoryModel(2, R.drawable.lisa, "Pizza"));
//        categoryModels.add(new CategoryModel(3, R.drawable.pexcels, "Pizza"));
//        categoryModels.add(new CategoryModel(4, R.drawable.pizza, "Pizza"));
//        categoryModels.add(new CategoryModel(5, R.drawable.pexcels, "Pizza"));
        get_menu();

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
                        JSONArray array = jObj.getJSONArray("menu");
                        for (int i = 0; i < array.length(); i++) {

                            JSONObject jsonObject = array.getJSONObject(i);
                            categoryModels.add(new CategoryModel(jsonObject.getInt("id"),
                                    jsonObject.getString("name"),
                                    jsonObject.getString("image")));
                        }

                        categoryAdapter = new CategoryAdapter(categoryModels, getActivity());
                        category_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        category_recyclerView.setAdapter(categoryAdapter);

                    } else {
                        String error_msg = jObj.getString("error_msg");
                        Toast.makeText(getContext(), error_msg, Toast.LENGTH_SHORT).show();
                        category_title.setVisibility(View.INVISIBLE);
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
}