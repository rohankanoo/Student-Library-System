package com.example.rohankanoo.homepage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

public class StudentOptionsActivity extends AppCompatActivity {

    Button btnStudVP , btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_options);

        if(SharedPrefManager.getInstance(this).isLoggedIn() == 0){
            finish();
            startActivity(new Intent(this,MainActivity.class));
            return;
        }
        else if (SharedPrefManager.getInstance(this).isLoggedIn() == -1){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
            return;
        }

        btnStudVP =(Button) findViewById(R.id.btnStudVP);
        btnLogout = (Button) findViewById(R.id.logoutStudent);
        btnStudVP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String username = getIntent().getExtras().getString("Username");
                final String fullname = getIntent().getExtras().getString("Fullname");
                StringRequest stringRequest = new StringRequest(
                        Request.Method.POST,
                        Constants.URL_STUDENT_PROFILE,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //progressDialog.dismiss();
                                try {
                                    JSONObject obj = new JSONObject(response);
                                    if(true){
                                        SharedPrefManager.getInstance(getApplicationContext())
                                                .studentDetails(
                                                        obj.getString("id"),
                                                        obj.getString("name"),
                                                        obj.getString("gender"),
                                                        obj.getString("dob"),
                                                        obj.getString("sdms"),
                                                        obj.getString("placement"),
                                                        obj.getString("batch"),
                                                        obj.getString("ssc"),
                                                        obj.getString("address"),
                                                        obj.getString("pin"),
                                                        obj.getString("aadhar"),
                                                        obj.getString("email"),
                                                        obj.getString("number"),
                                                        obj.getString("job"),
                                                        obj.getString("education")
                                                );
                                        //String fname = SharedPrefManager.getInstance(getActivity()).getFirstname();
                                        //String lname = SharedPrefManager.getInstance(getActivity()).getLastname();;
                                        //Toast.makeText(getApplicationContext(), "Hello " + fname + " " + lname, Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), StudentProfileActivity.class);
                                        intent.putExtra("Fullname", fullname);
                                        startActivity(intent);
                                        finish();
                                    }

                                    /*else if (username.length() == 0 || password.length() == 0){
                                        Toast.makeText(getActivity(), "Both field are required", Toast.LENGTH_LONG).show();
                                    }*/

                                    else {
                                        Toast.makeText(
                                                getApplicationContext(),
                                                obj.getString("message"),
                                                Toast.LENGTH_LONG
                                        ).show();

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //progressDialog.dismiss();

                                Toast.makeText(
                                        getApplicationContext(),
                                        error.getMessage(),
                                        Toast.LENGTH_LONG
                                ).show();

                            }
                        }
                ){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();
                        params.put("username",username);
                        return params;
                    }
                };

                RequestHandler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

                /*Intent iAdd = new Intent(StudentOptionsActivity.this, StudentProfileActivity.class);
                startActivity(iAdd);*/

            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefManager.getInstance(getApplicationContext()).logout();
                finish();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            SharedPrefManager.getInstance(this).logout();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
            //return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
