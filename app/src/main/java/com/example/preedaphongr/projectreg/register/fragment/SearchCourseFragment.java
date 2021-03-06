package com.example.preedaphongr.projectreg.register.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.preedaphongr.projectreg.BaseApplication;
import com.example.preedaphongr.projectreg.R;
import com.example.preedaphongr.projectreg.profile.activity.ProfileActivity;
import com.example.preedaphongr.projectreg.register.activity.MainActivity;
import com.example.preedaphongr.projectreg.register.adapter.RegisterAdapter;
import com.example.preedaphongr.projectreg.register.adapter.SearchCourseAdapter;
import com.example.preedaphongr.projectreg.register.model.Course;
import com.example.preedaphongr.projectreg.register.model.CourseResponse;
import com.example.preedaphongr.projectreg.register.model.RegisterRequest;
import com.example.preedaphongr.projectreg.register.model.RegisterResponse;
import com.example.preedaphongr.projectreg.register.presenter.SearchCoursePresenter;
import com.example.preedaphongr.projectreg.register.service.RegisterAPI;
import com.example.preedaphongr.projectreg.util.AddCourseEvent;
import com.example.preedaphongr.projectreg.util.RegisteredCourseEvent;
import com.example.preedaphongr.projectreg.util.RemoveCourseEvent;
import com.example.preedaphongr.projectreg.util.RemoveFromRegisterEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

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

    String[] faculty = {"คณะนิติศาสตร์","คณะพาณิชยศาสตร์และการบัญชี","คณะรัฐศาสตร์","คณะเศรษฐศาสตร์","คณะสังคมสงเคราะห์ศาสตร์","คณะศิลปศาสตร์","คณะวารสารศาสตร์และสื่อสารมวลชน,","คณะแพทยศาสตร์","คณะวิทยาศาสตร์ศาสตร์และเทคโนโลยี","คณะวิศวกรรมศาสตร์","เลือกคณะ"};
    String[] semester = {"ทั้งหมด","ภาคเรียนที่ 1","ภาคเรียนที่ 2","เลือกภาคเรียน"};

    private OnFragmentInteractionListener mListener;

    @Bind(R.id.spinner_major)
    Spinner spinnerMajor;

    @Bind(R.id.spinner_semester)
    Spinner spinnerSemester;

    @Bind(R.id.search_list)RecyclerView recyclerView;

    @Bind(R.id.search_button)Button searchButton;
    @Bind(R.id.layout_next_button)LinearLayout linearLayoutNextbtn;
    @Bind(R.id.register_btn)Button regButton;
    @Bind(R.id.search_not_found_textView)TextView searchNotFoundTextView;

    @Inject
    Retrofit retrofit;

    //@Inject
    SearchCoursePresenter searchCoursePresenter;

    private SearchCourseAdapter adapter;

    public static HashMap<String,Course> addcourse_hm = new HashMap<>();
    private static List<Course> list_register = new ArrayList<>();
    private HashMap<String ,Boolean> registered_map;

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
        if(retrofit != null){
            //********** call service get all faculty

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (addcourse_hm.size() > 0) {
            linearLayoutNextbtn.setVisibility(View.VISIBLE);
        } else {
            linearLayoutNextbtn.setVisibility(View.GONE);
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
        //spinnerMajor.setSelection(adapterMajor.getCount());
        SharedPreferences prefs = getActivity().getSharedPreferences("mypref", MODE_PRIVATE);

        spinnerMajor.setSelection( prefs.getInt("majorStd",adapterMajor.getCount()+1)-1 );
    //Log.d("@@@", "major id "+ String.valueOf(prefs.getInt("majorStd",5)));
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


        setSearchButton();
        setRegButton();




        searchCoursePresenter = new SearchCoursePresenter();
        searchCoursePresenter.setView(this);

        return view;
    }

    private void setRegButton() {
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmDialog();
            }
        });

    }

    private void showConfirmDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = LayoutInflater.from(getContext());
        dialog.setTitle("รายวิชาที่ลง");
        dialog.setPositiveButton(getResources().getString(R.string.confirm), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Log.d("@@@","regis click");
                mainActivity.register(list_register);
            }
        });
        dialog.setNegativeButton(getResources().getString(R.string.cancel),null);

        final View viewInflate = inflater.inflate(R.layout.fragment_register, null);
        RecyclerView recyclerView = viewInflate.findViewById(R.id.list_register);
        list_register.clear();
        list_register.addAll(addcourse_hm.values());
        RegisterAdapter adapter = new RegisterAdapter(list_register,getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        dialog.setView(viewInflate);
        AlertDialog alert = dialog.create();
        alert.show();

    }

    public void showFailDialog(String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.create();
        alertDialog.show();
    }

    public void showSuccessDialog() {
        list_register.clear();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("ลงทะเบียนเรียบร้อย");
        alertDialog.setIcon(R.drawable.ic_check_circle_black_36dp);
        alertDialog.setPositiveButton(getResources().getString(R.string.confirm), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                list_register.clear();
                addcourse_hm.clear();
                SharedPreferences editor = getActivity().getSharedPreferences("mypref",MODE_PRIVATE);
                String stdId = editor.getString("stdId","");
                Intent intent = new Intent(getContext(), ProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("stdId",stdId);
                getContext().startActivity(intent);
            }
        });
        alertDialog.setCancelable(false);
        alertDialog.create();
        alertDialog.show();
    }

    private void setSearchButton() {
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spinnerMajor.getSelectedItemPosition() < faculty.length - 1 &&
                        spinnerSemester.getSelectedItemPosition() < semester.length - 1) {
                    int major = spinnerMajor.getSelectedItemPosition()+1;
                    int semester = spinnerSemester.getSelectedItemPosition();
                    //searchCoursePresenter.sendSearchCourseRequest(1,semester);
                    //Log.d("@@@",String.valueOf(semester));

                    mainActivity.callRetrofit(semester,major);

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

    public void setSearchNotFoundTextView(boolean found){
        if(found){
            searchNotFoundTextView.setVisibility(View.GONE);
        }
        else {
            searchNotFoundTextView.setVisibility(View.VISIBLE);
        }
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
        if(courseResponse.getCourseList().size() == 0){
            setSearchNotFoundTextView(false);
        }
        else {
            setSearchNotFoundTextView(true);
        }
        SharedPreferences editor = getActivity().getSharedPreferences("mypref",MODE_PRIVATE);
        int currentTerm = editor.getInt("currentTerm",1);
        adapter = new SearchCourseAdapter(getContext(),courseResponse.getCourseList(),this,currentTerm,registered_map);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    public void onClickAdd(Course course) {
        //EventBus.getDefault().post(new AddCourseEvent(course));
        addcourse_hm.put(course.getCourseId(),course);
        //Log.d("@@@","******************event bus post*******************");
        if (addcourse_hm.size() > 0) {
            linearLayoutNextbtn.setVisibility(View.VISIBLE);
        } else {
            linearLayoutNextbtn.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClickRemove(Course course) {
        //EventBus.getDefault().post(new RemoveCourseEvent(course));
        addcourse_hm.remove(course.getCourseId());
        if (addcourse_hm.size() > 0) {
            linearLayoutNextbtn.setVisibility(View.VISIBLE);
        } else {
            linearLayoutNextbtn.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFailAdd() {
        showFailDialog(getContext().getResources().getString(R.string.alert_wrong_term));
    }

    @Override
    public void onAlreadyAdd() {
        showFailDialog(getContext().getResources().getString(R.string.already_register));
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

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onRegisteredCourseEvent(RegisteredCourseEvent registeredCourseEvent){
        Log.d("@@@","******************event bus receive*******************");
        registered_map = registeredCourseEvent.course;
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
