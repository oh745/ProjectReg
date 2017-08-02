package com.example.preedaphongr.projectreg.profile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.preedaphongr.projectreg.BaseApplication;
import com.example.preedaphongr.projectreg.R;
import com.example.preedaphongr.projectreg.login.activity.LoginActivity;
import com.example.preedaphongr.projectreg.profile.model.PersonalRequest;
import com.example.preedaphongr.projectreg.profile.model.PersonalResponse;
import com.example.preedaphongr.projectreg.profile.service.ProfileAPI;
import com.example.preedaphongr.projectreg.register.activity.MainActivity;
import com.example.preedaphongr.projectreg.register.adapter.RegisterAdapter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        AppBarLayout.OnOffsetChangedListener{


    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS     = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION              = 200;

    private boolean mIsTheTitleVisible          = false;
    private boolean mIsTheTitleContainerVisible = true;


    @Bind(R.id.main_toolbar)Toolbar mToolbar;
    @Bind(R.id.main_textview_title)TextView mTitle;
    @Bind(R.id.main_linearlayout_title)LinearLayout mTitleContainer;
    @Bind(R.id.main_appbar)AppBarLayout mAppBarLayout;
    @Bind(R.id.drawer_layout)DrawerLayout drawerLayout;
    @Bind(R.id.name_textview)TextView mName;
    @Bind(R.id.major_textview)TextView mMajor;
    @Bind(R.id.list_registered)RecyclerView recyclerView;
    @Bind(R.id.nav_view)NavigationView navigationView;


    private RegisterAdapter adapter;

    @Inject
    Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        mAppBarLayout.addOnOffsetChangedListener(this);
        mToolbar.inflateMenu(R.menu.menu_main);
        startAlphaAnimation(mTitle, 0, View.INVISIBLE);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,drawerLayout, mToolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        ((BaseApplication)getApplication()).getProfileComponent()
                .inject(this);

        Intent intent = getIntent();
        String stdId = intent.getStringExtra("stdId");
        loadPersonal(stdId);


        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
    }

    @Override
    protected void onStart() {
        super.onStart();
        int size = navigationView.getMenu().size();
        for (int i = 0; i < size; i++) {
            navigationView.getMenu().getItem(i).setChecked(false);
        }
    }

    private void loadPersonal(String stdId){
        if(retrofit != null){
            ProfileAPI api = retrofit.create(ProfileAPI.class);
            Call call = api.getPersonalData(new PersonalRequest(stdId));
            call.enqueue(new Callback<PersonalResponse>() {
                @Override
                public void onResponse(Call<PersonalResponse> call, Response<PersonalResponse> response) {
                    if(response.isSuccessful()){
                        Log.d("@@@","******************success********************");
                        setShowData(response.body());
                    }
                    else {
                        Log.d("@@@","******************unsuccess********************");
                        Log.d("@@@",response.message());
                    }
                }

                @Override
                public void onFailure(Call<PersonalResponse> call, Throwable t) {
                    Log.d("@@@","******************Fail********************");
                }
            });
        }
    }

    private void setShowData(PersonalResponse personalResponse){

        mName.setText(personalResponse.getFirstName() + " " + personalResponse.getLastName());
        mMajor.setText("คณะ" + personalResponse.getMajorName());
        mTitle.setText(personalResponse.getFirstName() + " " + personalResponse.getLastName());

        adapter = new RegisterAdapter(personalResponse.getRegisteredList());
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;

        handleAlphaOnTitle(percentage);
        handleToolbarTitleVisibility(percentage);
    }

    private void handleToolbarTitleVisibility(float percentage) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {

            if(!mIsTheTitleVisible) {
                startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }

        } else {

            if (mIsTheTitleVisible) {
                startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if(mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;
            }

        } else {

            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
            }
        }
    }

    public static void startAlphaAnimation (View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_register) {
            Log.d("@@@","register");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_logout) {
            Log.d("@@@","logout");
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }


        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
