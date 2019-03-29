package com.example.todo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.todo.ExpiredFragment.Expired_Model;
import com.example.todo.upcomingFrag.Upcoming_Model;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingDetail extends Fragment {

    TextView subject, date, description, remaining_days, title;
    ImageView back_img;
//    Upcoming_Model upcomingModel;
//    Bundle bundle;


    public UpcomingDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_upcoming_detail, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        Bundle b = getArguments();
        init(view);
        subject.setText(b.getString("subject"));
        date.setText(b.getString("date"));
        description.setText(b.getString("desc"));
        remaining_days.setText(b.getString("remains"));

        return view;
    }

    private void init(View view) {

        subject = view.findViewById(R.id.txt_subjectt);
        date = view.findViewById(R.id.txt_date);
        description = view.findViewById(R.id.txt_subject);
        remaining_days = view.findViewById(R.id.txt_days);
        title = view.findViewById(R.id.txt_frag_title);
        back_img = view.findViewById(R.id.img_back_arrow);
        title.setText("Upcoming Details");
        back_img.setOnClickListener(new View.OnClickListener() {
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
