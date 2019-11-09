package com.example.CMP354.bazaar.Activities;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.CMP354.bazaar.Classes.EmailValidator;
import com.example.CMP354.bazaar.R;


public class SignUpActivity  extends AppCompatActivity {

    // The validator for the email input field.
    private EmailValidator mEmailValidator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final Toast SignupCancel= Toast.makeText(this, "Sign Up Cancelled", Toast.LENGTH_SHORT);



        final AutoCompleteTextView Fname=(AutoCompleteTextView)findViewById(R.id.signup_FName);
        final AutoCompleteTextView Lname=(AutoCompleteTextView)findViewById(R.id.signup_LName);
     //   final AutoCompleteTextView ID=(AutoCompleteTextView)findViewById(R.id.signup_ID);
        final AutoCompleteTextView Number=(AutoCompleteTextView)findViewById(R.id.signup_phoneNum);
        final AutoCompleteTextView Email=(AutoCompleteTextView)findViewById(R.id.signup_Email);
        final AutoCompleteTextView pass=(AutoCompleteTextView)findViewById(R.id.signup_Password);

        final Toast SignupError= Toast.makeText(this, "Sign Up Was Unsuccessful", Toast.LENGTH_SHORT);
        final Toast SuccessfulSignUp= Toast.makeText(this, "Successful Sign Up!", Toast.LENGTH_SHORT);


        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_up_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String fname = (Fname.getText().toString());
                String lname = (Lname.getText().toString());
                //    String id = (ID.getText().toString());
                String num = (Number.getText().toString());
                String email = (Email.getText().toString());
                String password = (pass.getText().toString());


                mEmailValidator = new EmailValidator();
                Email.addTextChangedListener(mEmailValidator);

                if (password.equals("")) {
                    pass.setError("Password field can not be blank");
                }
                if (email.equals("")) {
                    Email.setError("Password field can not be blank");
                    if (!mEmailValidator.isValid()) {
                        Email.setError("Invalid email");
                    }
                }
                if (num.equals("")) {
                    Number.setError("Number can not be blank");
                }
                if (fname.equals("")) {
                    Fname.setError("First Name can not be blank");
                }
                if (lname.equals("")) {
                    Lname.setError("Number can not be blank");
                }
            }
        });


        Button mCancelButton = (Button) findViewById(R.id.cancelBtn);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignupCancel.show();
                finish();
            }
        });
    }
}