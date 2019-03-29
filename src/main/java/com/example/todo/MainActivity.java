package com.example.todo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.todo.AboutUsFragment.AboutUs;
import com.example.todo.CompletedFragment.CompletedFrag;
import com.example.todo.ExpiredFragment.ExpiredFrag;
import com.example.todo.Login.Login;
import com.example.todo.SupportFragment.Support;
import com.example.todo.TermsFragment.TermsFrag;
import com.example.todo.Utilities.SharedPreference;
import com.example.todo.upcomingFrag.UpcomingFrag;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView header_name, header_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Todo");

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        View view = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);

        header_name = view.findViewById(R.id.header_name);
        header_email = view.findViewById(R.id.header_email);
        header_name.setText(SharedPreference.getInstance(MainActivity.this).getString("Name", ""));
        header_email.setText(SharedPreference.getInstance(MainActivity.this).getString("Email", ""));
        //set first item in main activity always

        if (savedInstanceState == null) {

            Fragment fragment = new Home_Fragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_maincontainer, fragment);
            fragmentManager.popBackStack();
            fragmentTransaction.commit();

            navigationView.setCheckedItem(R.id.nav_home);

        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        Fragment fragment = null;

        int id = item.getItemId();

        if (id == R.id.nav_upcomming) {

            fragment = new UpcomingFrag();

            item.setTitle("Upcoming Task");

        } else if (id == R.id.nav_home) {

            fragment = new Home_Fragment();

            item.setTitle("Home");

        } else if (id == R.id.nav_expired) {

            fragment = new ExpiredFrag();

        } else if (id == R.id.nav_completed) {

            fragment = new CompletedFrag();

        } else if (id == R.id.nav_terms) {

            fragment = new TermsFrag();

        } else if (id == R.id.nav_aboutus) {

            fragment = new AboutUs();

        } else if (id == R.id.nav_support) {

            fragment = new Support();

        } else if (id == R.id.nav_logout) {

            logoutDialog();

        }

        if (fragment != null) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_maincontainer, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logoutDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(
                MainActivity.this);
        builder.setTitle("Confirm Logout");
        builder.setMessage("Are you sure to logout");
        builder.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
        builder.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        logoutActivity();

                    }
                });
        builder.show();
    }

    private void logoutActivity() {

        ConnectivityManager connMgr = (ConnectivityManager) MainActivity.this
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            if (!SharedPreference.getInstance(this).getString("UserId", "").equalsIgnoreCase("")) {
                SharedPreference.getInstance(this).deleteAllPreference();

                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                finish();

            } else {
                Snackbar.make(MainActivity.this.findViewById(android.R.id.content), "You Have Already Logout", Snackbar.LENGTH_LONG).show();
            }
        } else {
            Snackbar.make(MainActivity.this.findViewById(android.R.id.content), "No internet connection !", Snackbar.LENGTH_LONG).show();
        }

    }
}
