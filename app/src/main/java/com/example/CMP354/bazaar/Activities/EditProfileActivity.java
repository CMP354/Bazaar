package com.example.CMP354.bazaar.Activities;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.CMP354.bazaar.R;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EditProfileActivity extends Activity {

    private static final String DEFAULT_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DEFAULT_URL = "jdbc:oracle:thin:@coeoracle.aus.edu:1521:orcl";
    private static final String DEFAULT_USERNAME = "g00063299";
    private static final String DEFAULT_PASSWORD = "g00063299";

    private Connection connection;

    String loggedInFirstName=null;
    String loggedInLastName=null;
    String loggedInUserID=null;
    String loggedInNumber=null;
    String loggedInEmail=null;
    String password=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        this.setLoggedInUserID(getIntent().getStringExtra("User_ID"));
        this.setLoggedInFirstName(getIntent().getStringExtra("User_FName"));
        this.setLoggedInLastName(getIntent().getStringExtra("User_LName"));
        this.setLoggedInEmail(getIntent().getStringExtra("User_Email"));
        this.setLoggedInNumber(getIntent().getStringExtra("User_Num"));

        EditText fname= findViewById(R.id.edit_FName);
        EditText lname= findViewById(R.id.edit_LName);
        EditText phone= findViewById(R.id.edit_phoneNum);

        fname.setText(this.getLoggedInFirstName());
        lname.setText(this.getLoggedInLastName());
        phone.setText(this.getLoggedInNumber());

        Button cancelBtn= findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        Button editBtn= findViewById(R.id.edit_Profile_button);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateProfile();
                finish();

            }
        });
    }

    public void updateProfile(){

        EditText fname= findViewById(R.id.edit_FName);
        EditText lname= findViewById(R.id.edit_LName);
        EditText phone= findViewById(R.id.edit_phoneNum);
        EditText pass= findViewById(R.id.edit_Password);

        String newfname=fname.getText().toString();
        String newlname=lname.getText().toString();
        String newphone=phone.getText().toString();
        String newpass=pass.getText().toString();


        try {
            this.connection = createConnection();
            Statement stmt = connection.createStatement();
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(newpass.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String md5 = number.toString(16);

            while (md5.length() < 32) {
                md5 = "0" + md5;
            }

                ResultSet rs = stmt.executeQuery("update volunteers set firstname='"+newfname+"', lastname='"+
                        newlname+"', password='"+ md5+"', phonenum='"+newphone+"' where v_id='"+
                        getLoggedInUserID()+"'");

               Toast.makeText(this, "Profile Updated!", Toast.LENGTH_SHORT).show();
                connection.close();

        }catch (Exception e){
            Toast.makeText(this, "Error - Edits Were Not Saved", Toast.LENGTH_SHORT).show();
        }
    }
    public String   getLoggedInFirstName()                          { return loggedInFirstName; }
    public void     setLoggedInFirstName(String loggedInFirstName)  { this.loggedInFirstName = loggedInFirstName; }
    public String   getLoggedInLastName()                           { return loggedInLastName; }
    public void     setLoggedInLastName(String loggedInLastName)    { this.loggedInLastName = loggedInLastName; }
    public String   getLoggedInUserID()                             { return loggedInUserID; }
    public void     setLoggedInUserID(String loggedInUserID)        { this.loggedInUserID = loggedInUserID; }
    public String   getLoggedInNumber()                             { return loggedInNumber; }
    public void     setLoggedInNumber(String loggedInNumber)        { this.loggedInNumber = loggedInNumber; }
    public String   getLoggedInEmail()                              { return loggedInEmail; }
    public void     setLoggedInEmail(String loggedInEmail)          { this.loggedInEmail = loggedInEmail; }
    public String   getPassword()                                   { return password; }
    public void     setPassword(String password)                    { this.password = password; }

    public static Connection createConnection(String driver, String url, String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
    }
    public static Connection createConnection() throws ClassNotFoundException, SQLException {
        return createConnection(DEFAULT_DRIVER, DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }
}
