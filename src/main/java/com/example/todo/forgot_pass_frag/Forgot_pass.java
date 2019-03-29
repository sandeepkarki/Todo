package com.example.todo.forgot_pass_frag;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.todo.AboutUsFragment.AboutUs;
import com.example.todo.R;
import com.example.todo.TermsFragment.TermsFrag;
import com.example.todo.reset_password.ResetPassword;

/**
 * A simple {@link Fragment} subclass.
 */
public class Forgot_pass extends Fragment {

    ImageView backImage;
    TextView title;
    EditText edt_otp;
    Button submit;


    public Forgot_pass() {
        // Required empty public constructor
    }


    public static Fragment newInstance() {

        AboutUs fragment = new AboutUs();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_forgot_pass, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        init(view);
        title.setText("Forgot Password");

        return view;
    }

    private void init(View view) {

        backImage=view.findViewById(R.id.img_back_arrow);
        title=view.findViewById(R.id.txt_frag_title);
        edt_otp=view.findViewById(R.id.forgot_otp_no);
        submit=view.findViewById(R.id.btn_submit);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                replaceFragment(ResetPassword.newInstance());

            }
        });
    }


    public void addFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.fragment_maincontainer, fragment);
        ft.commit();
    }

    public void replaceFragment(Fragment fragment) {
        Log.d("replaceFragment", "replaceFragment: " +getActivity().getSupportFragmentManager().getBackStackEntryCount());
        String backStateName = fragment.getClass().getSimpleName();
        String fragmentTag = backStateName;

        FragmentManager manager = getActivity().getSupportFragmentManager();
        //fragment not in back stack, create it.
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.fragment_maincontainer, fragment, fragmentTag);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(backStateName);
        ft.commit();

    }


    public void popAllstack() {

        FragmentManager manager = getActivity().getSupportFragmentManager();
        for (int i = 0; i < manager.getBackStackEntryCount(); i++) {
            manager.popBackStack();
        }
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
