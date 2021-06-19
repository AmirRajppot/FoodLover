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

    private Fragment active = homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        check_connection();
        navView = findViewById(R.id.bottom_nav_view);
        navView.setOnNavigationItemSelectedListener(navListener);
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        }
    }

    BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.home_nv:
                    selectedFragment = homeFragment;
                    break;
                case R.id.cart_nv:
                    selectedFragment = cartFragment;
                    break;
                case R.id.Account_nv:

                    selectedFragment = accountFragment;
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };

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