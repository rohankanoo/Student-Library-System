package com.example.rohankanoo.homepage;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeSignupActivity extends AppCompatActivity {

    EditText eesid, etfname, etlname,  etcategory;
    EditText etemail, etcnumber, etpassword, etconfpass;
    Button btnsignup;
    private ProgressDialog progressDialog;

    int which = 0;
    String streid, strfname, strlname;
    String strcategory;
    String  stremail, strcnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_signup);

        btnsignup = (Button) findViewById(R.id.signup);
        eesid = (EditText) findViewById(R.id.eeid);
        etfname = (EditText) findViewById(R.id.efname);
        etlname = (EditText) findViewById(R.id.elname);
        etemail = (EditText) findViewById(R.id.email);
        etcnumber = (EditText) findViewById(R.id.ecnumber);
        etpassword = (EditText) findViewById(R.id.eepass) ;
        etconfpass = (EditText) findViewById(R.id.eeconfpass);
        progressDialog = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);

        //Validation
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean Valid = SignUp_Validation();

                if(Valid) {

                    final String str_id = eesid.getText().toString().trim();
                    final String str_password = etpassword.getText().toString().trim();
                    final String str_confpassword = etconfpass.getText().toString().trim();
                    final String str_fname = etfname.getText().toString().trim();
                    final String str_lname = etlname.getText().toString().trim();
                    final String str_category = etcategory.getText().toString().trim();
                    final String str_email = etemail.getText().toString().trim();
                    final String str_number = etcnumber.getText().toString().trim();


                    progressDialog.setCancelable(false);
                    progressDialog.setTitle("Please Wait");
                    progressDialog.setMessage("Registering Employee..");
                    progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
                    progressDialog.show();
                    StringRequest stringRequest = new StringRequest(Request.Method.POST,
                            Constants.URL_EMPLOYEE_REGISTER,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    progressDialog.dismiss();

                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        if(!jsonObject.getBoolean("error")) {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(
                                                    EmployeeSignupActivity.this);

                                            builder.setMessage("You've Successfully Registered.");
                                            builder.setCancelable(false);
                                            builder.setPositiveButton("Okay",
                                                    new DialogInterface.OnClickListener() {

                                                        public void onClick(DialogInterface dialog,
                                                                            int which) {
                                                            Intent iInfo = new Intent(EmployeeSignupActivity.this, EmployeeInfoActivity.class);
                                                            startActivity(iInfo);
                                                        }
                                                    });

                                            AlertDialog alert = builder.create();
                                            alert.show();
                                        }

                                        else {
                                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    progressDialog.hide();
                                    Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_LONG).show();

                                }
                            }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<>();
                            params.put("id",str_id);
                            params.put("password",str_password);
                            params.put("confpass",str_confpassword);
                            params.put("firstname",str_fname);
                            params.put("lastname",str_lname);
                            params.put("category",str_category);
                            params.put("email",str_email);
                            params.put("contact",str_number);
                            return params;
                        }
                    };

                    RequestHandler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
                }
            }
        });
        //category
        etcategory= (EditText) findViewById(R.id.ecategory);
        etcategory.setInputType(InputType.TYPE_NULL);
        etcategory.requestFocus();
        setCategory();
    }

    //category
    private void setCategory() {

        etcategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        EmployeeSignupActivity.this);

                final CharSequence[] items = {"Director", "Faculty", "Others"};
                builder.setTitle("Select Your Emp Category:");

                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int pos) {
                        which = pos;
                        // TODO Auto-generated method stub
                        switch (which) {
                            case 0:
                                etcategory.setText(items[which]);
                                break;
                            case 1:
                                etcategory.setText(items[which]);
                                break;
                            case 2:
                                etcategory.setText(items[which]);
                                break;
                        }
                    }
                });

                if (view == etcategory) {
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });
    }


    public boolean SignUp_Validation(){

        String str_sid = eesid.getText().toString();
        String str_fname = etfname.getText().toString();
        String str_lname = etlname.getText().toString();
        String str_category = etcategory.getText().toString();
        String str_email = etemail.getText().toString();
        String str_cnumber = etcnumber.getText().toString();

        if(str_sid.length()<1) {
            eesid.setError("Employee Id Required");
            streid="no";}
        else
            streid="yes";

        if(str_fname.length()<1) {
            etfname.setError("First Name Required");
            strfname="no";}
        else
            strfname="yes";

        if(str_lname.length()<1) {
            etlname.setError("Last Name Required");
            strlname="no";}
        else
            strlname="yes";

        if(str_category.equals("Select")) {
            etcategory.setError("Select Your Emp Category");
            strcategory="no";}
        else if(!str_category.equals("Select")) {
            etcategory.setError(null);
            strcategory="yes";}
        else
            strcategory="yes";


        if(!isValidEmail(str_email)) {
            etemail.setError("Enter Correct Email");
            stremail="no";}
        else
            stremail="yes";

        if(str_cnumber.length()!=10) {
            etcnumber.setError("Enter Correct Mobile Number");
            strcnumber="no";}
        else
            strcnumber="yes";


        if(streid.equals("yes") && strfname.equals("yes") && strlname.equals("yes") && strcategory.equals("yes")
                && stremail.equals("yes") && strcnumber.equals("yes")) {
            return true;}
        else
            return false;
    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
