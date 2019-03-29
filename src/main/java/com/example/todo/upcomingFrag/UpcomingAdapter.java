package com.example.todo.upcomingFrag;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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
import com.example.todo.CompletedFragment.CompletedFrag;
import com.example.todo.R;
import com.example.todo.UpcomingDetail;

import java.util.ArrayList;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.ViewHolder> {

    ArrayList<Upcoming_Model> arrayList;
    Context context;

    public UpcomingAdapter(ArrayList<Upcoming_Model> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public UpcomingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.upcoming_items, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingAdapter.ViewHolder viewHolder, int i) {

        Upcoming_Model model = arrayList.get(i);
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

        TextView subject, date, day,title;
        Button viewDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            subject = itemView.findViewById(R.id.txt_subject);
            date = itemView.findViewById(R.id.txt_date);
            day = itemView.findViewById(R.id.txt_days);
            viewDetail = itemView.findViewById(R.id.btn_view);
            title=itemView.findViewById(R.id.txt_subjectt);
            viewDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Fragment fragment = new UpcomingDetail();
                    FragmentManager fragmentManager =((AppCompatActivity)context).getSupportFragmentManager();
                    // this is basically context of the class
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Bundle bundle=new Bundle();
                    bundle.putString("date",arrayList.get(getAdapterPosition()).getDeadline());
                    bundle.putString("remains",arrayList.get(getAdapterPosition()).getTimeremaining());
                    bundle.putString("desc",arrayList.get(getAdapterPosition()).getDescription());
                    bundle.putString("subject",arrayList.get(getAdapterPosition()).getSubject());
                    bundle.putString("title",arrayList.get(getAdapterPosition()).getTitle());//key and value
                    fragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fragment_maincontainer, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });

        }
    }
}
