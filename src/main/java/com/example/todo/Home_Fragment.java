package com.example.todo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todo.CompletedFragment.CompletedFrag;
import com.example.todo.ExpiredFragment.ExpiredFrag;
import com.example.todo.upcomingFrag.UpcomingFrag;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home_Fragment extends Fragment implements View.OnClickListener {

    CardView upcoming, expired, completed;
    public Home_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home_, container, false);

        upcoming = view.findViewById(R.id.cv_upcoming);
        expired = view.findViewById(R.id.cv_expired);
        completed =view.findViewById(R.id.cv_completed);
        upcoming.setOnClickListener(this);
        expired.setOnClickListener(this);
        completed.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.cv_upcoming:

                Fragment fragment = new UpcomingFrag();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_maincontainer, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
//                replaceFragment(UpcomingFrag.newInstance());
                break;

            case R.id.cv_completed:


                Fragment fragment1 = new CompletedFrag();
                FragmentManager fragmentManager1 = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                fragmentTransaction1.replace(R.id.fragment_maincontainer, fragment1);
                fragmentTransaction1.addToBackStack(null);
                fragmentTransaction1.commit();

                break;

            case R.id.cv_expired:

                Fragment fragment2 = new ExpiredFrag();
                FragmentManager fragmentManager2 = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                fragmentTransaction2.replace(R.id.fragment_maincontainer, fragment2);
                fragmentTransaction2.addToBackStack(null);
                fragmentTransaction2.commit();

                break;

        }
        }
}
