package com.example.salahuddin.hungryorderapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.salahuddin.hungryorderapp.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUp extends AppCompatActivity {

    @BindView(R.id.signUpPphone)MaterialEditText userPhone;
    @BindView(R.id.signUpPname)MaterialEditText name;
    @BindView(R.id.signUpPassword)MaterialEditText password;
    @BindView(R.id.signUpButton)Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);
    }

    //initialize firebase
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference table_user = database.getReference("user");

    @OnClick(R.id.signUpButton)
    public void userSignUp(View view){

        final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
        mDialog.setMessage("Please Wait.....");
        mDialog.show();

        table_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //check if already registered
                if (dataSnapshot.child(userPhone.getText().toString()).exists()){

                    mDialog.dismiss();
                    Toast.makeText(SignUp.this,"Phone number already registered",Toast.LENGTH_LONG).show();

                }else {

                    mDialog.dismiss();
                    User user = new User(name.getText().toString(),password.getText().toString());
                    table_user.child(userPhone.getText().toString()).setValue(user);
                    Toast.makeText(SignUp.this,"SignUp Successfully done !",Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
