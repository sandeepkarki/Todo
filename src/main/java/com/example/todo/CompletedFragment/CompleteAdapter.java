package com.example.todo.CompletedFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.todo.CompletedDetail;
import com.example.todo.R;

import java.util.ArrayList;

public class CompleteAdapter extends RecyclerView.Adapter<CompleteAdapter.ViewHolder> {

    ArrayList<Complete_Model> arrayList;
    Context context;

    public CompleteAdapter(ArrayList<Complete_Model> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CompleteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.completed,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompleteAdapter.ViewHolder viewHolder, int i) {
        Complete_Model model=arrayList.get(i);
        viewHolder.date.setText(model.getDeadline());
        viewHolder.day.setText(model.getTimeremaining());
        viewHolder.subject.setText(model.getDescription());
        viewHolder.title.setText(model.getTitle());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView subject,date,day,title;
        Button view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            subject=itemView.findViewById(R.id.txt_subject);
            date=itemView.findViewById(R.id.txt_date);
            day=itemView.findViewById(R.id.txt_days);
            title=itemView.findViewById(R.id.txt_subjectt);
            view=itemView.findViewById(R.id.btn_view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Fragment fragment = new CompletedDetail();
                    FragmentManager fragmentManager =((AppCompatActivity)context).getSupportFragmentManager();
                    // this is basically context of the class
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Bundle bundle=new Bundle();
                    bundle.putString("date",arrayList.get(getAdapterPosition()).getDeadline());
                    bundle.putString("remains",arrayList.get(getAdapterPosition()).getTimeremaining());
                    bundle.putString("desc",arrayList.get(getAdapterPosition()).getDescription());
                    bundle.putString("subject",arrayList.get(getAdapterPosition()).getSubject());
                    bundle.putString("title",arrayList.get(getAdapterPosition()).getTitle());
                    fragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fragment_maincontainer, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
        }
    }
}
