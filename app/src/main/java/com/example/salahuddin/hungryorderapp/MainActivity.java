package com.example.salahuddin.hungryorderapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.signupButton) Button signUpButton;
    @BindView(R.id.signInButton) Button signInButton;

    @BindView(R.id.sloganId)TextView sloganText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/SIXTY.TTF");
        sloganText.setTypeface(face);
    }

    @OnClick(R.id.signupButton)
    public void setSignUpButton(View view) {

        Intent signUp = new Intent(MainActivity.this,SignUp.class);
        startActivity(signUp);
    }

    @OnClick(R.id.signInButton)
    public void setSignInButton(View view) {

        Intent signIn = new Intent(MainActivity.this,SignIn.class);
        startActivity(signIn);
    }
}
