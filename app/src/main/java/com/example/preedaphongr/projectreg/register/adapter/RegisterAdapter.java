package com.example.preedaphongr.projectreg.register.adapter;

import android.content.Context;
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
    private Context mContext;
    public RegisterAdapter(List<Course> mValues ,Context mContext) {

        this.mValues = mValues;
        this.mContext = mContext;
    }

    @Override
    public RegisterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_register,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RegisterAdapter.ViewHolder holder, int position) {

        holder.mCourseId.setText(mValues.get(position).getCourseId());
        holder.mCourseName.setText(mValues.get(position).getCourseName());
        holder.mTerm.setText(mContext.getResources().getString(R.string.semester) + String.valueOf(mValues.get(position).getTerm()));
        holder.mYear.setText(mContext.getResources().getString(R.string.year) + mValues.get(position).getYear());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.courseId_textView)TextView mCourseId;
        @Bind(R.id.courseName_textView)TextView mCourseName;
        @Bind(R.id.term_textView)TextView mTerm;
        @Bind(R.id.year_textView)TextView mYear;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
