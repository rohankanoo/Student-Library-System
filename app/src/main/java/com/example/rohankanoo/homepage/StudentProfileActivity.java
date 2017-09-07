package com.example.rohankanoo.homepage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class StudentProfileActivity extends AppCompatActivity {

    TextView etsid, etname, etgender, etdob, etsdmsnumber, etplacement, etbatch, etssc;
    TextView etaddress, etpin, etaadhar, etemail, etcnumber, etjrole, etedulevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        final String fullname = getIntent().getExtras().getString("Fullname");

        etsid = (TextView) findViewById(R.id.vpid);
        etname = (TextView) findViewById(R.id.vpname);
        etgender = (TextView) findViewById(R.id.vpgender);
        etdob = (TextView) findViewById(R.id.vpdob);
        etsdmsnumber = (TextView) findViewById(R.id.vpsdms);
        etplacement = (TextView) findViewById(R.id.vpplacement);
        etbatch = (TextView) findViewById(R.id.vpbatch);
        etssc = (TextView) findViewById(R.id.vpssc);
        etaddress = (TextView) findViewById(R.id.vpaddress);
        etpin = (TextView) findViewById(R.id.vppin);
        etaadhar = (TextView) findViewById(R.id.vpaadhar);
        etemail = (TextView) findViewById(R.id.vpemail);
        etcnumber = (TextView) findViewById(R.id.vpcontact);
        etjrole = (TextView) findViewById(R.id.vpjrole);
        etedulevel = (TextView) findViewById(R.id.vpedulevel);

        etsid.setText(SharedPrefManager.getInstance(this).getUserid());
        etname.setText(fullname);
        etgender.setText(SharedPrefManager.getInstance(this).getGender());
        etdob.setText(SharedPrefManager.getInstance(this).getDOB());
        etsdmsnumber.setText(SharedPrefManager.getInstance(this).getSDMS());
        etplacement.setText(SharedPrefManager.getInstance(this).getPlacement());
        etbatch.setText(SharedPrefManager.getInstance(this).getBatch());
        etssc.setText(SharedPrefManager.getInstance(this).getSSC());
        etaddress.setText(SharedPrefManager.getInstance(this).getAddress());
        etpin.setText(SharedPrefManager.getInstance(this).getPin());
        etaadhar.setText(SharedPrefManager.getInstance(this).getAadhar());
        etemail.setText(SharedPrefManager.getInstance(this).getEmail());
        etcnumber.setText(SharedPrefManager.getInstance(this).getContact());
        etjrole.setText(SharedPrefManager.getInstance(this).getJob());
        etedulevel.setText(SharedPrefManager.getInstance(this).getEducation());
    }
}
