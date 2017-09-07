package com.example.rohankanoo.homepage;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentSignupActivity extends AppCompatActivity {

    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    EditText etsid, etpass, etconfpass, etfname, etlname, etgender, etdob, etsdmsnumber, etplacement, etbatch, etssc;
    EditText etaddress, etpin, etaadhar, etemail, etcnumber, etjrole, etedulevel;
    Button btnsignup;
    ProgressDialog progressDialog;

    boolean [] checker = new boolean[16];
    int counter = 0;
    boolean valid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_signup);

        btnsignup = (Button) findViewById(R.id.signup);
        etsid = (EditText) findViewById(R.id.esid);
        etpass = (EditText) findViewById(R.id.espass);
        etconfpass = (EditText) findViewById(R.id.esconfpass);
        etfname = (EditText) findViewById(R.id.efname);
        etlname = (EditText) findViewById(R.id.elname);
        etsdmsnumber = (EditText) findViewById(R.id.esdmsnumber);
        etaddress = (EditText) findViewById(R.id.eaddress);
        etpin = (EditText) findViewById(R.id.epin);
        etaadhar = (EditText) findViewById(R.id.eaadhar);
        etemail = (EditText) findViewById(R.id.email);
        etcnumber = (EditText) findViewById(R.id.ecnumber);
        progressDialog = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);

        //Validation
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valid = SignUp_Validation();

                if(valid){
                    final String str_id = etsid.getText().toString().trim();
                    final String str_password = etpass.getText().toString().trim();
                    final String str_confpassword = etconfpass.getText().toString().trim();
                    final String str_fname = etfname.getText().toString().trim();
                    final String str_lname = etlname.getText().toString().trim();
                    final String str_gender = etgender.getText().toString().trim();
                    final String str_dob = etdob.getText().toString().trim();
                    final String str_sdms = etsdmsnumber.getText().toString().trim();
                    final String str_placement = etplacement.getText().toString().trim();
                    final String str_batch = etbatch.getText().toString().trim();
                    final String str_ssc = etssc.getText().toString().trim();
                    final String str_address = etaddress.getText().toString().trim();
                    final String str_pincode = etpin.getText().toString().trim();
                    final String str_aadhar = etaadhar.getText().toString().trim();
                    final String str_email = etemail.getText().toString().trim();
                    final String str_contact = etcnumber.getText().toString().trim();
                    final String str_job = etjrole.getText().toString().trim();
                    final String str_edulevel = etedulevel.getText().toString().trim();


                    progressDialog.setCancelable(false);
                    progressDialog.setTitle("Please Wait");
                    progressDialog.setMessage("Registering Student..");
                    progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
                    progressDialog.show();
                    StringRequest stringRequest = new StringRequest(Request.Method.POST,
                            Constants.URL_STUDENT_REGISTER,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    progressDialog.dismiss();

                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        if(!jsonObject.getBoolean("error")) {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(
                                                    StudentSignupActivity.this);

                                            builder.setMessage("You've Successfully Registered.");
                                            builder.setCancelable(false);
                                            builder.setPositiveButton("Okay",
                                                    new DialogInterface.OnClickListener() {

                                                        public void onClick(DialogInterface dialog,
                                                                            int which) {
                                                            Intent iInfo = new Intent(StudentSignupActivity.this, StudentInfoActivity.class);
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
                            params.put("firstname",str_fname);
                            params.put("lastname",str_lname);
                            params.put("password",str_password);
                            params.put("confpassword",str_confpassword);
                            params.put("gender",str_gender);
                            params.put("dob",str_dob);
                            params.put("sdms",str_sdms);
                            params.put("placement",str_placement);
                            params.put("batch",str_batch);
                            params.put("ssc",str_ssc);
                            params.put("address",str_address);
                            params.put("pincode",str_pincode);
                            params.put("aadhar",str_aadhar);
                            params.put("email",str_email);
                            params.put("contact",str_contact);
                            params.put("job",str_job);
                            params.put("education",str_edulevel);
                            return params;
                        }
                    };

                    RequestHandler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
                }
            }
        });

        //DOB
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        etdob = (EditText) findViewById(R.id.edob);
        etdob.setInputType(InputType.TYPE_NULL);
        etdob.requestFocus();
        setDateTimeField();

        //Gender
        etgender = (EditText) findViewById(R.id.egender);
        etgender.setInputType(InputType.TYPE_NULL);
        etgender.requestFocus();
        setGender();

        //Placement Status
        etplacement = (EditText) findViewById(R.id.eplacement);
        etplacement.setInputType(InputType.TYPE_NULL);
        etplacement.requestFocus();
        setPlacement();

        //Batch
        etbatch = (EditText) findViewById(R.id.ebatch);
        etbatch.setInputType(InputType.TYPE_NULL);
        etbatch.requestFocus();
        setBatch();

        //Sector Skill Counsil
        etssc = (EditText) findViewById(R.id.essc);
        etssc.setInputType(InputType.TYPE_NULL);
        etssc.requestFocus();
        setSsc();

        //Job Role
        etjrole = (EditText) findViewById(R.id.ejrole);
        etjrole.setInputType(InputType.TYPE_NULL);
        etjrole.requestFocus();
        setJrole();

        //Education Level
        etedulevel = (EditText) findViewById(R.id.edulevel);
        etedulevel.setInputType(InputType.TYPE_NULL);
        etedulevel.requestFocus();
        setEdulevel();
    }

    //Date Of Birth Picker
    private void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, R.style.datepicker, new DatePickerDialog.OnDateSetListener()
        {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                etdob.setText(dateFormatter.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        etdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == etdob) {
                    fromDatePickerDialog.show();
                }
            }
        });
    }

    //Gender
    private void setGender() {

        etgender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        StudentSignupActivity.this);

                CharSequence[] items = {"Male", "Female", "Other"};
                builder.setTitle("Select Your Gender:");

                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
                            case 0:
                                etgender.setText("Male");
                                break;
                            case 1:
                                etgender.setText("Female");
                                break;
                            case 2:
                                etgender.setText("Other");
                                break;
                        }
                    }
                });

                if (view == etgender) {
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });
    }


    //Placement Status
    private void setPlacement() {

        etplacement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        StudentSignupActivity.this);

                CharSequence[] items = {"Yes", "No"};
                builder.setTitle("Placement Status:");

                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
                            case 0:
                                etplacement.setText("Yes");
                                break;
                            case 1:
                                etplacement.setText("No");
                                break;
                        }
                    }
                });

                if (view == etplacement) {
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });
    }


    //Batch
    private void setBatch() {

        etbatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        StudentSignupActivity.this);

                CharSequence[] items = {"Batch 1", "Batch 2", "Batch 3"};
                builder.setTitle("Select Your Batch:");

                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
                            case 0:
                                etbatch.setText("Batch 1");
                                break;
                            case 1:
                                etbatch.setText("Batch 2");
                                break;
                            case 2:
                                etbatch.setText("Batch 3");
                                break;
                        }
                    }
                });

                if (view == etbatch) {
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });
    }


    //SSC
    private void setSsc() {

        etssc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        StudentSignupActivity.this);

                CharSequence[] items = {"IT/ IT eS", "BFSI", "Management"};
                builder.setTitle("Select Your SSC:");

                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
                            case 0:
                                etssc.setText("IT/ IT eS");
                                break;
                            case 1:
                                etssc.setText("BFSI");
                                break;
                            case 2:
                                etssc.setText("Management");
                                break;
                        }
                    }
                });

                if (view == etssc) {
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });
    }


    //Job Role
    private void setJrole() {

        etjrole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        StudentSignupActivity.this);

                CharSequence[] items = {"ITSD", "DDEO", "JSD"};
                builder.setTitle("Select Your Job Role:");

                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
                            case 0:
                                etjrole.setText("ITSD");
                                break;
                            case 1:
                                etjrole.setText("DDEO");
                                break;
                            case 2:
                                etjrole.setText("JSD");
                                break;
                        }
                    }
                });

                if (view == etjrole) {
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });
    }


    //Education Level
    private void setEdulevel() {

        etedulevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        StudentSignupActivity.this);

                CharSequence[] items = {"10th", "12th", "Graduation", "Post-Graduation"};
                builder.setTitle("Education Level:");

                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
                            case 0:
                                etedulevel.setText("10th");
                                break;
                            case 1:
                                etedulevel.setText("12th");
                                break;
                            case 2:
                                etedulevel.setText("Graduation");
                                break;
                            case 3:
                                etedulevel.setText("Post-Graduation");
                                break;
                        }
                    }
                });

                if (view == etedulevel) {
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });
    }

    public boolean  SignUp_Validation(){

        String str_sid = etsid.getText().toString();
        String str_fname = etfname.getText().toString();
        String str_lname = etlname.getText().toString();
        String str_gender = etgender.getText().toString();
        String str_dob = etdob.getText().toString();
        String str_sdmsnumber = etsdmsnumber.getText().toString();
        String str_placement = etplacement.getText().toString();
        String str_batch = etbatch.getText().toString();
        String str_ssc = etssc.getText().toString();
        String str_address = etaddress.getText().toString();
        String str_pin = etpin.getText().toString();
        String str_aadhar = etaadhar.getText().toString();
        String str_email = etemail.getText().toString();
        String str_cnumber = etcnumber.getText().toString();
        String str_jrole = etjrole.getText().toString();
        String str_edulevel = etedulevel.getText().toString();


        if(str_sid.length()<1) {
            etsid.setError("Student Id Required");
            checker[0] = false;
        }
        else
            checker[0] = true;

        if(str_fname.length()<1) {
            etfname.setError("First Name Required");
            checker[1] = false;
        }
        else
            checker[1] = true;

        if(str_lname.length()<1) {
            etlname.setError("Last Name Required");
            checker[2] = false;
        }
        else
            checker[2] = true;

        if(str_gender.equals("Select")) {
            etgender.setError("Select Gender");
            checker[3] = false;
        }
        else
            checker[3] = true;

        if(str_dob.equals("Select")) {
            etdob.setError("Select Your DOB");
            checker[4] = false;
        }
        else
            checker[4] = true;

        if(str_sdmsnumber.length()<1) {
            etsdmsnumber.setError("SDMS Number Required");
            checker[5] = false;
        }
        else
            checker[5] = true;

        if(str_placement.equals("Select")) {
            etplacement.setError("Select Your Placement Status");
            checker[6] = false;
        }
        else
            checker[6] = true;

        if(str_batch.equals("Select")) {
            etbatch.setError("Select Your Batch");
            checker[7] = false;
        }
        else
            checker[7] = true;

        if(str_ssc.equals("Select")) {
            etssc.setError("Select Your SSC");
            checker[8] = false;
        }
        else
            checker[8] = true;

        if(str_address.length()<1) {
            etaddress.setError("Address Required");
            checker[9] = false;
        }
        else
            checker[9] = true;

        if(str_pin.length()!=6) {
            etpin.setError("Enter Valid Pin Code");
            checker[10] = false;
        }
        else
            checker[10] = true;

        if(str_aadhar.length()!=12) {
            etaadhar.setError("Enter Valid Aadhar Number");
            checker[11] = false;
        }
        else
            checker[11] = true;

        if(!isValidEmail(str_email)){
            etemail.setError("Enter Correct Email");
            checker[12] = false;
        }
        else
            checker[12] = true;

        if(str_cnumber.length()!=10) {
            etcnumber.setError("Enter Correct Mobile Number");
            checker[13] = false;
        }
        else
            checker[13] = true;

        if(str_jrole.equals("Select")) {
            etjrole.setError("Select Your Job Role");
            checker[14] = false;
        }
        else
            checker[14] = true;

        if(str_edulevel.equals("Select")) {
            etedulevel.setError("Select Your Education Level");
            checker[15] = false;
        }
        else
            checker[15] = true;

        for (int i=0; i<16; i++){
            if(checker[i])
                counter++;
        }
        if (counter == 16)
            return true;
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
