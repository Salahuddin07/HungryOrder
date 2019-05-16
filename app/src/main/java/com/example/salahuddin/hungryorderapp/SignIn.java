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

public class SignIn extends AppCompatActivity {
    @BindView(R.id.phone)MaterialEditText phone;
    @BindView(R.id.password)MaterialEditText password;
    @BindView(R.id.signInButton)Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ButterKnife.bind(this);
    }
    //initialize firebase
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference table_user = database.getReference("user");

        @OnClick(R.id.signInButton)
        public void signInButton (View view){

            final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
            mDialog.setMessage("Please Wait.....");
            mDialog.show();

            table_user.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    //check if user does not exist database
                    if (dataSnapshot.child(phone.getText().toString()).exists()) {
                        //Get Uwser Information
                        mDialog.dismiss();
                        User user = dataSnapshot.child(phone.getText().toString()).getValue(User.class);
                        if (user.getPassword().equals(password.getText().toString())) {
                            Toast.makeText(SignIn.this, "SignIn Successful", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(SignIn.this, "SignIn failed", Toast.LENGTH_LONG).show();
                        }
                    }else {
                        mDialog.dismiss();
                        Toast.makeText(SignIn.this,"User not exit in database",Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
}
