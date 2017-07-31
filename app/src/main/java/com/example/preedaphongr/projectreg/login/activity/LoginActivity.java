package com.example.preedaphongr.projectreg.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.preedaphongr.projectreg.R;
import com.example.preedaphongr.projectreg.profile.ProfileActivity;
import com.example.preedaphongr.projectreg.register.activity.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {


    @Bind(R.id.login_button)Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginSuccess();
            }
        });


    }

    public void loginSuccess(){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);

    }

}
