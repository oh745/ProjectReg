package com.example.preedaphongr.projectreg.register.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.preedaphongr.projectreg.R;
import com.example.preedaphongr.projectreg.register.fragment.SearchCourseFragment;
import com.example.preedaphongr.projectreg.register.model.Course;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by preedaphong.r on 20/07/2560.
 */

public class SearchCourseAdapter extends RecyclerView.Adapter<SearchCourseAdapter.ViewHolder> {


    List<Course> mValues;
    Context mContext;
    SearchAdapterCallback callback;
    public SearchCourseAdapter(Context mContext,List<Course> mValues,SearchAdapterCallback callback) {
        this.mContext = mContext;
        this.mValues = mValues;
        this.callback = callback;
    }

    public interface SearchAdapterCallback{
        void onClickAdd(Course course);
        void onClickRemove(Course course);
    }

    @Override
    public SearchCourseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_search_course,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchCourseAdapter.ViewHolder holder, int position) {

        Course course = mValues.get(position);
        if (SearchCourseFragment.addcourse_hm.get(course.getCourseId()) != null) {
            holder.mAddCourse.setButtonDrawable(R.drawable.ic_add_circle_black_24dp);
            holder.mAddCourse.setSelected(true);
        }
        else {
            holder.mAddCourse.setButtonDrawable(R.drawable.ic_add_circle_outline_black_24dp);
            holder.mAddCourse.setSelected(false);
        }

        holder.mCourseId.setText(course.getCourseId());
        holder.mCourseName.setText(course.getCourseName());
        holder.mTerm.setText(String.valueOf(course.getTerm()));
        holder.mYear.setText(course.getYear());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @Bind(R.id.add_favorite_checkBox)CheckBox mAddCourse;
        @Bind(R.id.courseId_textView)TextView mCourseId;
        @Bind(R.id.courseName_textView)TextView mCourseName;
        @Bind(R.id.term_textView)TextView mTerm;
        @Bind(R.id.year_textView)TextView mYear;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            mAddCourse.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.add_favorite_checkBox:
                    if (mAddCourse.isSelected()) {
                        mAddCourse.setSelected(false);
                        mAddCourse.setButtonDrawable(R.drawable.ic_add_circle_outline_black_24dp);
                        callback.onClickRemove(mValues.get(getAdapterPosition()));
                    } else {
                        mAddCourse.setSelected(true);
                        mAddCourse.setButtonDrawable(R.drawable.ic_add_circle_black_24dp);
                        callback.onClickAdd(mValues.get(getAdapterPosition()));
                    }


            }
        }
    }
}
