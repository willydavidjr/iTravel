package com.katana.itour;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.UUID;

public class UploadProfileActivity extends AppCompatActivity {

    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private View imageContainer;
    private ProgressBar progressBar;
    private Button uploadButton;
    private TextView downloadURL;
    StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_profile);

        imageContainer = findViewById(R.id.imgUpload);
        progressBar = (ProgressBar)findViewById(R.id.progbarUpload);
        progressBar.setVisibility(View.GONE);
        uploadButton = (Button)findViewById(R.id.btnUpload);
        downloadURL = (TextView)findViewById(R.id.txtUploadStatus);
        //uploadButton.setOnClickListener(new UploadOnClickListener());

        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://itour-f3daf.appspot.com");

        //URL url = new URL("https://firebasestorage.googleapis.com/v0/b/itour-f3daf.appspot.com/o/iTour%2F43e5bf0d-42bc-4d88-baef-dc321340aa1a.png?alt=media&token=4f55b6fe-a70e-4e05-aedc-119c734d2e0c");
        //ImageView imgView = (ImageView)findViewById(R.id.imgUpload);
        //Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        //imgView.setImageBitmap(bmp);

        //storageRef.child("iTour/tagaytayimg1.png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            //@Override
           // public void onSuccess(Uri uri) {
                // Got the download URL for 'users/me/profile.png'
                // Pass it to Picasso to download, show in ImageView and caching
                //Picasso.with(context).load(uri.toString()).into(imageView);

                ImageView imgView = (ImageView)findViewById(R.id.imgDownload);


                //Glide.with(this)
                // .using(new FirebaseImageLoader())
                //.load(storageRef)
                // .into(imgView);
                Glide.with(getBaseContext())
                        .load("http://nuuneoi.com/uploads/source/playstore/cover.jpg")
                        //.load(storageRef)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imgView);

            //}
        //}).addOnFailureListener(new OnFailureListener() {
           // @Override
           // public void onFailure(@NonNull Exception exception) {
                // Handle any errors
           // }
        //});
    }



    public void onChoose(View view)
    {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                //ImageView imgView = (ImageView) findViewById(R.id.imgUpload);
                ImageView imgView = (ImageView) findViewById(R.id.imgUpload);
                imageContainer  = (ImageView) findViewById(R.id.imgUpload);
                // Set the Image in ImageView after decoding the String
                imgView.setImageBitmap(BitmapFactory
                        .decodeFile(imgDecodableString));

            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }
    }

    public void onUpload(View view)
    {
        imageContainer.setDrawingCacheEnabled(true);
        imageContainer.buildDrawingCache();
        Bitmap bitmap = imageContainer.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        imageContainer.setDrawingCacheEnabled(false);
        byte[] data = baos.toByteArray();

        String path = "iTour/" + UUID.randomUUID() + ".png";
        StorageReference fireRef = storage.getReference(path);

        progressBar.setVisibility(View.VISIBLE);
        uploadButton.setEnabled(false);

        UploadTask uploadTask = fireRef.putBytes(data);
        uploadTask.addOnSuccessListener(UploadProfileActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressBar.setVisibility(View.GONE);
                uploadButton.setEnabled(true);

                Uri url = taskSnapshot.getDownloadUrl();
                downloadURL.setText(url.toString());
                downloadURL.setVisibility(View.VISIBLE);
            }
        });
    }
}
