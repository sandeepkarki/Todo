package com.example.todo.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.todo.MainActivity;
import com.example.todo.R;
import com.example.todo.Utilities.Connectivity;
import com.example.todo.Utilities.ProgressBar;
import com.example.todo.Utilities.SharedPreference;
import com.example.todo.constant;
import com.example.todo.forgot_pass_frag.Forgot_pass;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    EditText edt_username, edt_password;
    Button login;
    LinearLayout toolbar;
    ProgressBar progress;
    TextView tooltext;
    String android_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        progress = new ProgressBar(Login.this);
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        android_id = Settings.Secure.getString(Login.this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        init();

    }

    private void init() {
        edt_username = findViewById(R.id.login_mobile_no);
        login = findViewById(R.id.btn_login);
        toolbar = findViewById(R.id.ll_login);
        edt_password = findViewById(R.id.login_password);
        tooltext = findViewById(R.id.loginid);
        tooltext.setText("Login");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Connectivity.isNetworkConnected(Login.this)) {
                    if (edt_username.getText().toString().trim().length() == 0) {

                        edt_username.setError("Enter User Id..");

                    } else if (edt_password.getText().toString().trim().length() == 0) {

                        edt_password.setError("Enter Password...");
                    } else {

                        hitUrlforLogin();

                    }

                } else {

//                    Toast.makeText(Login_Activity.this, "No internet connection !", Toast.LENGTH_SHORT).show();

                    Snackbar.make(Login.this.findViewById(android.R.id.content), "No internet connection !", Snackbar.LENGTH_LONG).show();
                }


            }
        });


    }


    private void hitUrlforLogin() {

        progress.show();

        StringRequest request = new StringRequest(Request.Method.POST, constant.LOGIN_API, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("LoginResponse",response);
                progress.hide();

                try {
                    JSONObject object=new JSONObject(response);

                    String status=object.getString("status");
                    String message=object.optString("message");
                    if (status.equalsIgnoreCase("1")){

                        JSONObject jsonObject=object.getJSONObject("data");

                        SharedPreference.getInstance(Login.this).putString("UserId",jsonObject.optString("user_id"));
                        SharedPreference.getInstance(Login.this).putString("Name",jsonObject.optString("name"));
                        SharedPreference.getInstance(Login.this).putString("Email",jsonObject.optString("user_email"));
                        SharedPreference.getInstance(Login.this).putString("MObile",jsonObject.optString("user_mobile"));

                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
                        finish();


                    }else {


                        Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progress.hide();

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("username", edt_username.getText().toString());
                params.put("mobile", edt_password.getText().toString());
                params.put("device_id", android_id);
                params.put("device_type", "Android");

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.getCache().clear();
        requestQueue.add(request);
    }

}
