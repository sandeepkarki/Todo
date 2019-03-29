package com.example.todo.SupportFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.todo.AboutUsFragment.AboutUs;
import com.example.todo.R;
import com.example.todo.Utilities.ProgressBar;
import com.example.todo.constant;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class Support extends Fragment {

    ImageView backImage;
    TextView title,txt_desc;
    ProgressBar progressBar;
    String st_title,st_desc;


    public Support() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_support, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();


        progressBar=new ProgressBar(getActivity());
        hitUrlForData();
        init(view);
        return view;
    }

    private void hitUrlForData() {
        progressBar.show();

        StringRequest request = new StringRequest(Request.Method.GET, constant.PRIVACY_API, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("SupportResponse", response);
                progressBar.hide();

                try {
                    JSONObject object = new JSONObject(response);

                    String status = object.getString("status");
                    String message = object.optString("message");
                    if (status.equalsIgnoreCase("1")) {

                        JSONObject jsonObject = object.getJSONObject("data");
                        st_title=jsonObject.optString("cms_title");
                        st_desc=jsonObject.optString("cms_descripion");
                        title.setText(st_title);
                        txt_desc.setText(st_desc);

                    } else {


                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.hide();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.getCache().clear();
        requestQueue.add(request);

    }

    private void init(View view) {

        backImage=view.findViewById(R.id.img_back_arrow);
        title=view.findViewById(R.id.txt_frag_title);
        txt_desc=view.findViewById(R.id.privacy);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }
    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

    }

