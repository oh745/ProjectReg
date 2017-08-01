package com.example.preedaphongr.projectreg.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.preedaphongr.projectreg.BaseApplication;
import com.example.preedaphongr.projectreg.R;
import com.example.preedaphongr.projectreg.login.model.LoginRequest;
import com.example.preedaphongr.projectreg.login.model.LoginResponse;
import com.example.preedaphongr.projectreg.login.service.LoginAPI;
import com.example.preedaphongr.projectreg.profile.activity.ProfileActivity;
import com.example.preedaphongr.projectreg.register.activity.MainActivity;
import com.example.preedaphongr.projectreg.util.MD5;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {


    @Bind(R.id.login_button)Button loginButton;
    @Bind(R.id.username_edittext)EditText usernameEdt;
    @Bind(R.id.password_edittext)EditText passwordEdt;

    @Inject
    Retrofit retrofit;

    private String stdId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        ((BaseApplication)getApplication()).getLoginComponent()
                .inject(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stdId = usernameEdt.getText().toString();
                String password = passwordEdt.getText().toString();
                if (stdId.equals("") || password.equals("")) {
                    Toast.makeText(getBaseContext(), getResources().getText(R.string.login_alert), Toast.LENGTH_SHORT).show();
                } else {
                        loginRequest(stdId,password);
                }

            }
        });


    }

    private void loginRequest(String username,String password){
        password = MD5.encrypt(password);
        if(retrofit != null){
            LoginAPI api = retrofit.create(LoginAPI.class);
            Call call = api.getRegisterList(new LoginRequest(username,password));
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if(response.isSuccessful()){
                        Log.d("@@@","******************success********************");
                        if(response.body().getStatus().equals("1")){
                            loginSuccess(response.body());
                        }
                        else {
                            Toast.makeText(getBaseContext(), getResources().getText(R.string.login_alert2), Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Log.d("@@@","******************unsuccess********************");
                        Log.d("@@@",response.message());
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Log.d("@@@","******************Fail********************");
                }
            });
        }

    }

    private void loginSuccess(LoginResponse loginResponse){

        Intent intent = new Intent(this, ProfileActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("stdId",stdId);
        startActivity(intent);

        finish();

    }

}
