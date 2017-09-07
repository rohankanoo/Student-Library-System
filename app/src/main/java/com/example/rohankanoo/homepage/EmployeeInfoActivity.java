package com.example.rohankanoo.homepage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EmployeeInfoActivity extends AppCompatActivity {

    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_info);

        btnAdd = (Button) findViewById(R.id.btnEmpAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EmployeeSignupActivity.class);
                startActivity(intent);
            }
        });
    }

    /*@Override
    public void onBackPressed() {
        Intent intent = new Intent(EmployeeInfoActivity.this, MainActivity.class);
        startActivity(intent);
    }*/
}
