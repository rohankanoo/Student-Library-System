package com.example.rohankanoo.homepage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Rohan Kanoo on 13-06-2017.
 */


public class Tab3 extends android.support.v4.app.Fragment {

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        View view = inflater.inflate(R.layout.tab3, container, false);
        Button empInfo = (Button) view.findViewById(R.id.emp_info);
        empInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EmployeeInfoActivity.class);
                getActivity().startActivity(intent);
                /*Toast toast = Toast.makeText(getActivity(), "Hello!", Toast.LENGTH_LONG);
                toast.show();*/
            }
        });
        return view;
    }
}
