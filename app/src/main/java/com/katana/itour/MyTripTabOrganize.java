package com.katana.itour;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by MCTamayo on 12/14/2016.
 */
public class MyTripTabOrganize extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.mytriptabbudget, container, false);
        // setting image resource from drawable

        final ImageView imageView = (ImageView) view.findViewById(R.id.imageView1);
        imageView.setImageResource(R.drawable.package8);

        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(imageView.getContext(), DetailedActivity.class);
                startActivity(intent);
            }
        });

        final ImageView imageView2 = (ImageView) view.findViewById(R.id.imageView2);
        imageView2.setImageResource(R.drawable.package9);

        imageView2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(imageView.getContext(), DetailedActivity.class);
                startActivity(intent);
            }
        });

        return  view;
    }
}