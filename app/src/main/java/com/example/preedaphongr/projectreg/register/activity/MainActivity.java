package com.example.preedaphongr.projectreg.register.activity;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.preedaphongr.projectreg.BaseApplication;
import com.example.preedaphongr.projectreg.R;
import com.example.preedaphongr.projectreg.register.fragment.RegisterFragment;
import com.example.preedaphongr.projectreg.register.fragment.SearchCourseFragment;
import com.example.preedaphongr.projectreg.register.model.CourseRequest;
import com.example.preedaphongr.projectreg.register.model.CourseResponse;
import com.example.preedaphongr.projectreg.register.service.SearchCourseAPI;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements SearchCourseFragment.OnFragmentInteractionListener, RegisterFragment.OnFragmentInteractionListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private SearchCourseFragment searchCourseFragment;
    private RegisterFragment registerFragment;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private TabLayout tabLayout;

    @Bind(R.id.toolbar)Toolbar toolbar;


    @Inject
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_register);
        ButterKnife.bind(this);


        // Create the adapter that will return a fragment for each of the three
        setSupportActionBar(toolbar);
        // primary sections of the activity.
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        //final AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbarLayout.getLayoutParams();

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        ((BaseApplication)getApplication()).getSearchCourseComponent()
                .inject(this);


    }


    public void callRetrofit(int term){
        if(retrofit != null){
            //Toast.makeText(getBaseContext(),"ok",Toast.LENGTH_SHORT).show();
            SearchCourseAPI api = retrofit.create(SearchCourseAPI.class);
            Call call = api.getCourseList(new CourseRequest(term,1));
            call.enqueue(new Callback<CourseResponse>() {
                @Override
                public void onResponse(Call<CourseResponse> call, Response<CourseResponse> response) {
                    if(response.isSuccessful()){
                        Log.d("@@@","******************success********************");
                        searchCourseFragment.setAdapter(response.body());
                    }
                    else {
                        Log.d("@@@","******************unsuccess********************");
                        Log.d("@@@",response.message());
                    }
                }

                @Override
                public void onFailure(Call<CourseResponse> call, Throwable t) {
                    Log.d("@@@","******************Fail********************");
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if(id == android.R.id.home){
            super.onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position){
                case 0: searchCourseFragment = SearchCourseFragment.newInstance("","");
                    setConnection();
                    return searchCourseFragment;

                case 1: registerFragment = RegisterFragment.newInstance("","");
                    return registerFragment;

                default:return null;
            }


        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getResources().getString(R.string.search_course);
                case 1:
                    return getResources().getString(R.string.register);

            }
            return null;
        }
    }
    public void setConnection(){
        searchCourseFragment.setMainActivity(this);
    }
}
