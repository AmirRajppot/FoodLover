package com.example.foodlover.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.foodlover.Fragments.Account;
import com.example.foodlover.Fragments.Cart;
import com.example.foodlover.Fragments.HomeFragment;
import com.example.foodlover.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import io.alterac.blurkit.BlurLayout;

public class Home extends AppCompatActivity {
    private Fragment homeFragment = new HomeFragment();
    private Fragment cartFragment = new Cart();
    private Fragment accountFragment = new Account();
    private static BottomNavigationView navView;
    BlurLayout blurLayout;
    private Fragment active = homeFragment;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home_nv:
//                    title.setText(R.string.app_name);
                    loadFragment(homeFragment);
                    break;
                case R.id.cart_nv:
//                    title.setText("My Cart");
                    loadFragment(cartFragment);
                    break;
                case R.id.Account_nv:

                    loadFragment(accountFragment);
                    break;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        check_connection();
        navView = findViewById(R.id.bottom_nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, homeFragment, "home").commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, cartFragment, "cart").hide(cartFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, accountFragment, "account").hide(accountFragment).commit();

        loadFragment(homeFragment);

    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            {
                getSupportFragmentManager().beginTransaction().hide(active).show(fragment).commit();
                active = fragment;
            }
        }
        return false;
    }

    ///internet
    public void check_connection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (null != networkInfo) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                Toast.makeText(this, "Data Network Enabled", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_LONG).show();

        }

    }
}