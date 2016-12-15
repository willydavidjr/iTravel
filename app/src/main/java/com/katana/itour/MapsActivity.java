package com.katana.itour;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    int REQUEST_PLACE_PICKER = 1;
    private static final String TAG = "iTour Team Katana";
    private DatabaseReference mDatabase;
    FirebaseDatabase myFirebaseRef;
    private static final int PLACE_PICKER_REQUEST = 1000;
    private GoogleApiClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // ATTENTION: This "addApi(AppIndex.API)"was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addApi(AppIndex.API).build();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        setUpMap();
        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mClient.connect();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Maps Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.katana.itour/http/host/path")
        );
        AppIndex.AppIndexApi.start(mClient, viewAction);
    }
    @Override
    protected void onStop() {
        mClient.disconnect();
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Maps Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.katana.itour/http/host/path")
        );
        AppIndex.AppIndexApi.end(mClient, viewAction);
    }

    private void setUpMap() {
        LatLng busLocation = new LatLng(37.783879,-122.401254);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(busLocation, 12));
        mMap.addMarker(new MarkerOptions()
                .position(busLocation)
                .title("iTour Team Katana")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.traveller)));
        //mMap.setMyLocationEnabled(true);
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        //if (mMap == null) {
        // Try to obtain the map from the SupportMapFragment.
        //mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
        //      .getMapAsync();
        // Check if we were successful in obtaining the map.
        //if (mMap != null) {
        //setUpMap();
        //}
    }

    public void onSearch(View view) {
        EditText txtLocation = (EditText)findViewById(R.id.txtAddress);
        String strLocation = txtLocation.getText().toString();
        List<Address> addressList = null;
        if (strLocation != null || strLocation != "")
        {
            Geocoder geo = new Geocoder(this);
            try {
                addressList = geo.getFromLocationName(strLocation, 1);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

            mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title("iTour Team Katana")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.traveller)));

            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }

    public void onChangeMap(View view){

        if (mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL)
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }
        else
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }

    }

    public void onZoomIn(View view){
        if (view.getId() == R.id.btnZoomIn)
        {
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
        }

        if (view.getId() == R.id.btnZoomOut){
            mMap.animateCamera(CameraUpdateFactory.zoomOut());
        }
    }

    public void onSuggest(View view)
    {
        try {
            PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
            Intent intent = intentBuilder.build(this);

            // Start the Intent by requesting a result, identified by a request code.
            startActivityForResult(intent, REQUEST_PLACE_PICKER);

        } catch (GooglePlayServicesRepairableException e) {
            GooglePlayServicesUtil.getErrorDialog(e.getConnectionStatusCode(),
                    this, 0);
        } catch (GooglePlayServicesNotAvailableException e) {
            Toast.makeText(this, "Google Play Services is not available.",
                    Toast.LENGTH_LONG).show();
        }
        //return true;
    }

    public void onSuggest2(View view){
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            Intent intent = builder.build(this);

            //startActivityForResult(builder.build(PlacePicker.this), PLACE_PICKER_REQUEST);
            startActivityForResult(intent, PLACE_PICKER_REQUEST);

        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }



    public void onSavePlace(View view){
        //mDatabase = FirebaseDatabase.getInstance().getReference();

        //String key = mDatabase.child("posts").push().getKey();
        //Post post = new Post(userId, username, title, body);
        //Map<String, Object> postValues = post.toMap();

        //Map<String, Object> childUpdates = new HashMap<>();
        //childUpdates.put("/posts/" + key, postValues);
        //childUpdates.put("/user-posts/" + userId + "/" + key, postValues);

        //mDatabase.updateChildren(childUpdates);


        //Map mLocation = new HashMap();

        //Map  mCoordinate = new HashMap();
        //mCoordinate.put(“latitude”, place.getLatLng().latitude);
        //mCoordinate.put(“longitude”, place.getLatLng().longitude);
        //mLocation.put("location", mCoordinate);

        // mLocation.put("place_id", place.getId());
        //mLocation.put("timestamp", mLastUpdateTime);
        // myFirebaseRef.push().setValue(mLocation);

        User myUser = new User("Willy", "willy@spi.com");
        myUser.writeNewUser("1", myUser.username, myUser.email);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                StringBuilder stBuilder = new StringBuilder();
                String placename = String.format("%s", place.getName());
                String latitude = String.valueOf(place.getLatLng().latitude);
                String longitude = String.valueOf(place.getLatLng().longitude);
                String address = String.format("%s", place.getAddress());
                stBuilder.append("Name: ");
                stBuilder.append(placename);
                stBuilder.append("\n");
                stBuilder.append("Latitude: ");
                stBuilder.append(latitude);
                stBuilder.append("\n");
                stBuilder.append("Logitude: ");
                stBuilder.append(longitude);
                stBuilder.append("\n");
                stBuilder.append("Address: ");
                stBuilder.append(address);
                EditText et = (EditText)findViewById(R.id.txtAddress);
                et.setText(stBuilder.toString());
            }
        }
    }

}
