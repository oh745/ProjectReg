package com.example.preedaphongr.projectreg.register.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.preedaphongr.projectreg.BaseApplication;
import com.example.preedaphongr.projectreg.R;
import com.example.preedaphongr.projectreg.register.activity.MainActivity;
import com.example.preedaphongr.projectreg.register.adapter.SearchCourseAdapter;
import com.example.preedaphongr.projectreg.register.model.Course;
import com.example.preedaphongr.projectreg.register.model.CourseResponse;
import com.example.preedaphongr.projectreg.register.presenter.SearchCoursePresenter;
import com.example.preedaphongr.projectreg.util.AddCourseEvent;
import com.example.preedaphongr.projectreg.util.RemoveCourseEvent;
import com.example.preedaphongr.projectreg.util.RemoveFromRegisterEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchCourseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchCourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchCourseFragment extends Fragment implements SearchCoursePresenter.View,SearchCourseAdapter.SearchAdapterCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String[] faculty = {"คณะวิทย์","คณะแพทย์","คณะรัฐศาสตร์","คณะนิติศาสตร์","เลือกคณะ"};
    String[] semester = {"ทั้งหมด","1","2","เลือกภาคเรียน"};

    private OnFragmentInteractionListener mListener;

    @Bind(R.id.spinner_major)
    Spinner spinnerMajor;

    @Bind(R.id.spinner_semester)
    Spinner spinnerSemester;

    @Bind(R.id.search_list)RecyclerView recyclerView;

    @Bind(R.id.search_button)Button searchButton;

    //@Inject
    SearchCoursePresenter searchCoursePresenter;

    private SearchCourseAdapter adapter;

    public static HashMap<String,Boolean> addcourse_hm = new HashMap<>();

    public SearchCourseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchCourseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchCourseFragment newInstance(String param1, String param2) {
        SearchCourseFragment fragment = new SearchCourseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_course, container, false);
        ButterKnife.bind(this,view);


        ArrayAdapter<String> adapterMajor = new ArrayAdapter<String>(getContext(),
                R.layout.support_simple_spinner_dropdown_item, faculty){

            @Override
            public int getCount() {
                return super.getCount()-1;
            }
        };
        adapterMajor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMajor.setAdapter(adapterMajor);
        spinnerMajor.setPrompt(getResources().getString(R.string.select_faculty));
        spinnerMajor.setSelection(adapterMajor.getCount());

        ArrayAdapter<String> adapterSemester = new ArrayAdapter<String>(getContext(),
                R.layout.support_simple_spinner_dropdown_item, semester){
            @Override
            public int getCount() {
                return super.getCount()-1;
            }
        };
        adapterSemester.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerSemester.setAdapter(adapterSemester);
        spinnerSemester.setSelection(adapterSemester.getCount());

        /*List<Course> list = new ArrayList<>();
        list.add(null);
        list.add(null);list.add(null);
        list.add(null);list.add(null);
        list.add(null);list.add(null);
        list.add(null);list.add(null);
        list.add(null);*/

        setSearchButton();




        searchCoursePresenter = new SearchCoursePresenter();
        searchCoursePresenter.setView(this);

        return view;
    }

    private void setSearchButton() {
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spinnerMajor.getSelectedItemPosition() < faculty.length - 1 &&
                        spinnerSemester.getSelectedItemPosition() < semester.length - 1) {
                    String major = spinnerMajor.getSelectedItem().toString();
                    int semester = spinnerSemester.getSelectedItemPosition();
                    //searchCoursePresenter.sendSearchCourseRequest(1,semester);
                    Log.d("@@@",String.valueOf(semester));
                    mainActivity.callRetrofit(semester);

                } else {
                    Toast.makeText(getContext(), "กรุณาเลือกคณะและภาคเรียน", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    MainActivity mainActivity;
    public void setMainActivity(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setAdapter(CourseResponse courseResponse) {
        adapter = new SearchCourseAdapter(getContext(),courseResponse.getCourseList(),this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onClickAdd(Course course) {
        EventBus.getDefault().post(new AddCourseEvent(course));
        addcourse_hm.put(course.getCourseId(),true);
        Log.d("@@@","******************event bus post*******************");
    }

    @Override
    public void onClickRemove(Course course) {
        EventBus.getDefault().post(new RemoveCourseEvent(course));
        addcourse_hm.remove(course.getCourseId());

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onRemoveFromRegisterEvent(RemoveFromRegisterEvent fromRegisterEvent){
        addcourse_hm.remove(fromRegisterEvent.course.getCourseId());
        adapter.notifyDataSetChanged();

    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
