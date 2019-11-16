package com.example.CMP354.bazaar.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.CMP354.bazaar.Classes.EmailValidator;
import com.example.CMP354.bazaar.R;

public class LoginActivity extends AppCompatActivity {


    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;

    // The validator for the email input field.
    private EmailValidator mEmailValidator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        mEmailView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);


        final Toast loginError= Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT);
        final Toast emptyFields= Toast.makeText(this, "Username or Password Fields Missing ", Toast.LENGTH_SHORT);

        Button mUserSignInButton = findViewById(R.id.user_login_in_button);
        mUserSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = mEmailView.getText().toString();
                String password = mPasswordView.getText().toString();
                // Setup field validators.
                mEmailValidator = new EmailValidator();
                mEmailView.addTextChangedListener(mEmailValidator);
                if (password.equals("")) {
                    mPasswordView.setError("Password field can not be blank");
                    emptyFields.show();
                }
                if (email.equals("")) {
                    mEmailView.setError("Password field can not be blank");
                    emptyFields.show();
                    if (!mEmailValidator.isValid()) {
                        mEmailView.setError("Invalid email");
                    }
                }
                login("user", "123456","Sakhnini ", "Dara ", "dara@aus.edu", "123456789");
            }

        });



/*        Button mAdminSignInButton = findViewById(R.id.admin_login_in_button);
        mAdminSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = mEmailView.getText().toString();
                String password = mPasswordView.getText().toString();

                if (password.equals("")) {
                    mPasswordView.setError("Password field can not be blank");
                    emptyFields.show();
                }
                if (email.equals("")) {
                    mEmailView.setError("Email field can not be blank");
                    emptyFields.show();
                    if (!mEmailValidator.isValid()) {
                        mEmailView.setError("Invalid email");
                    }
                }
                login("admin", "123456","Sakhnini ", "Dara ", "dara@aus.edu", "123456789");

            }
            });*/


                mEmailValidator = new EmailValidator();
                mEmailView.addTextChangedListener(mEmailValidator);


                Button mSignUpButton = findViewById(R.id.email_sign_up_button);
                mSignUpButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                        startActivity(intent);
                    }
                });
            }


    public void login(String type, String id, String fname, String lname, String email, String num){

        Intent intent=null;

            intent = new Intent(LoginActivity.this, MainActivity.class);

        intent.putExtra("User_ID", id);
        intent.putExtra("User_FName", fname);
        intent.putExtra("User_LName", lname);
        intent.putExtra("User_Email", email);
        intent.putExtra("User_Num", num);
        startActivity(intent);
    }

}
