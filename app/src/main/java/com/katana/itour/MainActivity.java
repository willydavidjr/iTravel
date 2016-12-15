package com.katana.itour;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        // setting image resource from drawable
        final ImageView imageView = (ImageView) findViewById(R.id.imageView1);
        imageView.setImageResource(R.drawable.tagaytayimg1);

        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(imageView.getContext(), DetailedActivity.class);
                startActivity(intent);
            }
        });


        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView2.setImageResource(R.drawable.tagaytayimg2);

        imageView2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(imageView.getContext(), DetailedActivity.class);
                startActivity(intent);
            }
        });


        ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);
        imageView3.setImageResource(R.drawable.tagaytayimg3);

        imageView2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(imageView.getContext(), DetailedActivity.class);
                startActivity(intent);
            }
        });


        ImageView imageView4 = (ImageView) findViewById(R.id.imageView4);
        imageView4.setImageResource(R.drawable.tagaytayimg4);

        imageView2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(imageView.getContext(), DetailedActivity.class);
                startActivity(intent);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_ProfSetting) {
            Intent intent = new Intent(getApplicationContext(), UploadProfileActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.action_Menu) {
            Intent intent = new Intent(getApplicationContext(), Mainmenu.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_TourMe) {
            Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_CreateOwnTrip) {
            Intent intent = new Intent(getApplicationContext(), OwnTripMain.class);
            startActivity(intent);

        } else if (id == R.id.nav_Budget) {
            Intent intent = new Intent(getApplicationContext(), BudgetTourActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_myTrip) {
            Intent intent = new Intent(getApplicationContext(), MyTripMainActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_EmailSetting) {
            Intent intent = new Intent(getApplicationContext(), CreateActivity.class);
            startActivity(intent);
        }
         else if (id == R.id.nav_logout) {
            auth.signOut();

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
