package com.example.todo.AboutUsFragment;


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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.todo.R;
import com.example.todo.Utilities.ProgressBar;
import com.example.todo.constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUs extends Fragment {

    ImageView backImage;
    TextView title, description;
    ProgressBar progressBar;
    String title_txt,txt_desc;

    public AboutUs() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        progressBar = new ProgressBar(getActivity());
        hiturlforData();
        init(view);

        return view;
    }

    private void hiturlforData() {


        progressBar.show();

        StringRequest request = new StringRequest(Request.Method.GET, constant.ABOUT_US, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("AboutResponse", response);
                progressBar.hide();

                try {
                    JSONObject object = new JSONObject(response);

                    String status = object.getString("status");
                    String message = object.optString("message");
                    if (status.equalsIgnoreCase("1")) {

                        JSONObject jsonObject = object.getJSONObject("data");
                        title_txt=jsonObject.optString("cms_title");
                        txt_desc=jsonObject.optString("cms_descripion");
                        description.setText(txt_desc);
                        title.setText(title_txt);

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

        backImage = view.findViewById(R.id.img_back_arrow);
        title = view.findViewById(R.id.txt_frag_title);
        description=view.findViewById(R.id.about_description);
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
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

}
