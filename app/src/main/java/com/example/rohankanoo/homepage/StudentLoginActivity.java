package com.example.rohankanoo.homepage;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

/**
 * Created by Rohan Kanoo on 30-06-2017.
 */
public class StudentLoginActivity  extends android.support.v4.app.Fragment{

    Button btn;
    TextInputLayout usernameWrapper, passwordWrapper;
    CheckBox cbPassword;
    EditText password;
    private ProgressDialog progressDialog;

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        View view = inflater.inflate(R.layout.activity_student_login, container, false);

        /*if(SharedPrefManager.getInstance(getActivity()).isLoggedIn()){
            getActivity().finish();
            startActivity(new Intent(getActivity(),MainActivity.class));
        }*/

        usernameWrapper = (TextInputLayout) view.findViewById(R.id.usernameWrapper);
        passwordWrapper = (TextInputLayout) view.findViewById(R.id.passwordWrapper);
        password = passwordWrapper.getEditText();

        btn = (Button) view.findViewById(R.id.btn);

        progressDialog = new ProgressDialog(getActivity(), R.style.AppCompatAlertDialogStyle);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Logging in...");

        usernameWrapper.setHint("Username");
        passwordWrapper.setHint("Password");

        cbPassword = (CheckBox) view.findViewById(R.id.cbPassword);

        cbPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // checkbox status is changed from uncheck to checked.
                if (!isChecked) {
                    // show password
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hidekeyboard();

                final String username = usernameWrapper.getEditText().getText().toString().trim();
                final String password = passwordWrapper.getEditText().getText().toString().trim();

                progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
                progressDialog.show();
                Window window = progressDialog.getWindow();
                window.setLayout(900, 400);

                StringRequest stringRequest = new StringRequest(
                        Request.Method.POST,
                        Constants.URL_STUDENT_LOGIN,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressDialog.dismiss();
                                try {
                                    JSONObject obj = new JSONObject(response);
                                    if(!obj.getBoolean("error")){
                                        SharedPrefManager.getInstance(getActivity())
                                                .studentLogin(
                                                        "1",
                                                        obj.getString("id"),
                                                        obj.getString("firstname"),
                                                        obj.getString("lastname")
                                                );
                                        String fname = SharedPrefManager.getInstance(getActivity()).getFirstname();
                                        String lname = SharedPrefManager.getInstance(getActivity()).getLastname();;
                                        Toast.makeText(getActivity(), "Hello " + fname + " " + lname, Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(new Intent(getActivity(), StudentOptionsActivity.class));
                                        intent.putExtra("Username", username);
                                        intent.putExtra("Fullname", fname + " " + lname);
                                        startActivity(intent);
                                        getActivity().finish();
                                    }

                                    else if (username.length() == 0 || password.length() == 0){
                                        Toast.makeText(getActivity(), "Both field are required", Toast.LENGTH_LONG).show();
                                    }

                                    else {
                                        Toast.makeText(
                                                getActivity(),
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
                                progressDialog.dismiss();

                                Toast.makeText(
                                        getActivity(),
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
                        params.put("password",password);
                        return params;
                    }
                };

                RequestHandler.getInstance(getActivity()).addToRequestQueue(stringRequest);
            }
        });
        return view;
    }

    void hidekeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}

