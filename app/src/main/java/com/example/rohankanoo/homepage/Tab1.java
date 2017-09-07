package com.example.rohankanoo.homepage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import junit.framework.Test;

/**
 * Created by Rohan Kanoo on 13-06-2017.
 */
public class Tab1 extends android.support.v4.app.Fragment {

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        View view = inflater.inflate(R.layout.tab1, container, false);
        Button studInfo = (Button) view.findViewById(R.id.stud_info);
        studInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StudentInfoActivity.class);
                getActivity().startActivity(intent);
                /*Toast toast = Toast.makeText(getActivity(), "Hello!", Toast.LENGTH_LONG);
                toast.show();*/
            }
        });
        return view;
    }
}
