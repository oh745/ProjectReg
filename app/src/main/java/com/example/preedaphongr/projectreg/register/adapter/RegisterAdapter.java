package com.example.preedaphongr.projectreg.register.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.preedaphongr.projectreg.R;
import com.example.preedaphongr.projectreg.register.model.Course;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by preedaphong.r on 21/07/2560.
 */

public class RegisterAdapter extends RecyclerView.Adapter<RegisterAdapter.ViewHolder> {

    private List<Course> mValues;
    public RegisterAdapter(List<Course> mValues) {
        this.mValues = mValues;
    }

    @Override
    public RegisterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_register,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RegisterAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.courseId_textView)TextView mCourseId;
        @Bind(R.id.courseName_textView)TextView mCourseName;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
