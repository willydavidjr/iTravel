package com.katana.itour;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class BudgetTourMainMenuActivity extends Activity {
    FirebaseStorage storage = FirebaseStorage.getInstance();

    ListView list;
    String[] web = {
            "Package 1 with Maximum of 10-20 \nperson P2,000 EACH",
            "Package 2 with Maximum of 10-20 person P20,000 EACH",
            "Package 3 with Maximum of 10-20 person P25,000 EACH",
            "Package 4 with Maximum of 10-20 person P22,000 EACH",
            "Package 5 with Maximum of 10-20 person P24,000 EACH",
            "Package 6 with Maximum of 10-20 person P2,000 EACH",
            "Package 7 with Maximum of 10-20 person P2,000 EACH",
            "Package 8 with Maximum of 10-20 person P2,000 EACH",
            "Package 9 with Maximum of 10-20 person P2,000 EACH",
            "Package 10 with Maximum of 10-20 person P2,000 EACH",
            "Package 11 with Maximum of 10-20 person P2,000 EACH",
            "Package 12 with Maximum of 10-20 person P1,000 EACH",
            "Package 13 with Maximum of 10-20 person P1000 EACH",
            "Package 14 with Maximum of 10-20 person P800 EACH",
            "Package 15 with Maximum of 10-20 person P1,000 EACH",
            "Package 16 with Maximum of 10-20 person P3,000 EACH",
            "Package 17 with Maximum of 10-20 person P2,000 EACH",
            "Package 18 with Maximum of 10-20 person P2,000 EACH"

    };
    Integer[] imageId = {
            R.drawable.package1,
            R.drawable.package2,
            R.drawable.package3,
            R.drawable.package4,
            R.drawable.package5,
            R.drawable.package6,
            R.drawable.package7,
            R.drawable.package8,
            R.drawable.package9,
            R.drawable.package10,
            R.drawable.package11,
            R.drawable.package12,
            R.drawable.package13,
            R.drawable.package14,
            R.drawable.package15,
            R.drawable.package16,
            R.drawable.package18,
            R.drawable.package17

    };
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budget_mainmenu_details);
        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        CustomListActivity adapter = new
                CustomListActivity(BudgetTourMainMenuActivity.this, web, imageId);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Toast.makeText(BudgetTourMainMenuActivity.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), DetailedActivity.class);
                startActivity(intent);
            }
        });


    }


    @Override
    public void onBackPressed() {

            super.onBackPressed();

    }
}
