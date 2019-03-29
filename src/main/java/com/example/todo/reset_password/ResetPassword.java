package com.example.todo.reset_password;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todo.AboutUsFragment.AboutUs;
import com.example.todo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResetPassword extends Fragment {

    ImageView backImage;
    TextView title;
    EditText edt_password,edt_conf_password;
    Button proceed;
    String passwd,confirmPassword;


    public ResetPassword() {
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
        View view=inflater.inflate(R.layout.fragment_reset_password, container, false);

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        init(view);
        title.setText("Reset Password");
        return view;
    }

    private void init(View view) {

        backImage=view.findViewById(R.id.img_back_arrow);
        title=view.findViewById(R.id.txt_frag_title);
        edt_password=view.findViewById(R.id.reset_password);
        edt_conf_password=view.findViewById(R.id.confirm_password);
        proceed=view.findViewById(R.id.btn_reset_password);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                passwd=edt_password.getText().toString().trim();
                confirmPassword=edt_conf_password.getText().toString().trim();


                if (!(edt_password.length() ==0) && !(edt_conf_password.length()==0)){
                    if (confirmPassword.equals(passwd)){


                        Intent intent=new Intent(getActivity(),Long.class);
                        startActivity(intent);
                        getActivity().finish();

//                        hitURLforResetPass();

                    }else {

                        Toast.makeText(getActivity(), "Wrong Password...", Toast.LENGTH_SHORT).show();
                    }
                }else {

                    Toast.makeText(getActivity(), "Please enter password...", Toast.LENGTH_SHORT).show();
                }

            }
        });
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
