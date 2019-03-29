package com.example.todo.upcomingFrag;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.todo.Utilities.SharedPreference;
import com.example.todo.constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingFrag extends Fragment {

    RecyclerView recyclerView;
    UpcomingAdapter adapter;
    ArrayList<Upcoming_Model> arrayList;
    ImageView backImage;
    ProgressBar progressBar;
    TextView title;

    public UpcomingFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_upcoming2, container, false);
        progressBar = new ProgressBar(getActivity());
        recyclerView = view.findViewById(R.id.upcoming_recycler);
        hiturlforUpcoming();

        init(view);
        return view;
    }

    private void hiturlforUpcoming() {

        arrayList = new ArrayList<>();
        progressBar.show();

        StringRequest request = new StringRequest(Request.Method.POST, constant.UPCOMING_API, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("UpcomingResponse", response);
                progressBar.hide();

                try {

                    JSONObject object = new JSONObject(response);
                    String status = object.getString("status");
                    String message = object.optString("message");
                    if (status.equalsIgnoreCase("1")) {

                        JSONArray jsonArray = object.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Upcoming_Model model = new Upcoming_Model();
                            model.setDeadline(jsonObject.optString("deadline"));
                            model.setDescription(jsonObject.optString("description"));
                            model.setTimeremaining(jsonObject.optString("remaining"));
                            model.setSubject(jsonObject.optString("subject"));
                            model.setTitle(jsonObject.optString("title"));
                            arrayList.add(model);

                        }

                        adapter = new UpcomingAdapter(arrayList, getActivity());
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(adapter);


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
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("user_id", SharedPreference.getInstance(getActivity()).getString("UserId", ""));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.getCache().clear();
        requestQueue.add(request);
    }


    private void init(View view) {

        backImage = view.findViewById(R.id.img_back_arrow);
        title = view.findViewById(R.id.txt_frag_title);
        title.setText("Upcoming Task");
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
