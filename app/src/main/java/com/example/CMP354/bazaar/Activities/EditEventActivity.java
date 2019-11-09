package com.example.CMP354.bazaar.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.CMP354.bazaar.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EditEventActivity extends AppCompatActivity {

    private static final String DEFAULT_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DEFAULT_URL = "jdbc:oracle:thin:@coeoracle.aus.edu:1521:orcl";
    private static final String DEFAULT_USERNAME = "g00063299";
    private static final String DEFAULT_PASSWORD = "g00063299";

    private Connection connection;

    private String eventID = "";
    private String eventName = "";
    private String eventDesc = "";
    private String eventDate = "";
    private String eventStartTime = "";
    private String eventEndTime = "";
    private String eventLocation = "";
    private String userID = "";
    //private String role = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);


        this.setEventID(getIntent().getStringExtra("Event ID"));
        this.setEventName(getIntent().getStringExtra("EventTitle"));
        this.setEventDesc(getIntent().getStringExtra("EventDesc"));
        this.setEventDate(getIntent().getStringExtra("EventDate"));
        this.setEventStartTime(getIntent().getStringExtra("EventStartTime"));
        this.setEventEndTime(getIntent().getStringExtra("EventEndTime"));
        this.setEventLocation(getIntent().getStringExtra("EventLocation"));
        this.setUserID(getIntent().getStringExtra("userID"));



        EditText title = findViewById(R.id.edit_newEventTitle);
        EditText desc = findViewById(R.id.edit_newEventDesc);
        EditText date = findViewById(R.id.edit_newEventDate);
        EditText StartTime = findViewById(R.id.edit_newEventStartTime);
        EditText EndTime = findViewById(R.id.edit_newEventEndTime);
        EditText loc = findViewById(R.id.edit_newEventLocation);

        title.setText(this.getEventName());
        desc.setText(this.getEventDesc());
        date.setText(this.getEventDate());
        StartTime.setText(this.getEventStartTime());
        EndTime.setText(this.getEventEndTime());
        loc.setText(this.getEventLocation());



        Button cancelBtn = findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

        Button editBtn = findViewById(R.id.editEventBtn);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateEvent();
                finish();

            }
        });




    }

        public void updateEvent(){

        EditText title= findViewById(R.id.edit_newEventTitle);
        EditText desc= findViewById(R.id.edit_newEventDesc);
        EditText date= findViewById(R.id.edit_newEventDate);
        EditText StartTime= findViewById(R.id.edit_newEventStartTime);
        EditText EndTime= findViewById(R.id.edit_newEventEndTime);
        EditText loc= findViewById(R.id.edit_newEventLocation);

        String newTitle=title.getText().toString();
        String newDesc=desc.getText().toString();
        String newDate=date.getText().toString();
        String newStartTime=StartTime.getText().toString();
        String newEndTime=EndTime.getText().toString();
        String newLoc=loc.getText().toString();


        try {
            this.connection = createConnection();
            Statement stmt = connection.createStatement();

                ResultSet rs = stmt.executeQuery("update events set Name='"+
                        newTitle+"', Description='"+ newDesc+"', event_Date= DATE '"+ newDate +"', start_time='"+
                        newStartTime+"', end_time='" + newEndTime +"', Location='"+ newLoc+"' where event_ID="+Integer.valueOf(getEventID()));



               Toast.makeText(this, "Event Updated!", Toast.LENGTH_SHORT).show();
                connection.close();

        }catch (Exception e){
            Toast.makeText(this, "Error - Edits Were Not Saved", Toast.LENGTH_SHORT).show();
        }
    }


    public String   getUserID()                             { return userID; }
    public void     setUserID(String userID)                { this.userID = userID; }
    public String   getEventID()                            { return eventID; }
    public void     setEventID(String eventID)              { this.eventID = eventID; }
    public String   getEventName()                          { return eventName; }
    public void     setEventName(String eventName)          { this.eventName = eventName; }
    public String   getEventDesc()                          { return eventDesc; }
    public void     setEventDesc(String eventDesc)          { this.eventDesc = eventDesc; }
    public String   getEventDate()                          { return eventDate; }
    public void     setEventDate(String eventDate)          { this.eventDate = eventDate; }
    public String   getEventStartTime()                     { return eventStartTime; }
    public void     setEventStartTime(String eventStartTime){ this.eventStartTime = eventStartTime; }
    public String   getEventEndTime()                       { return eventEndTime; }
    public void     setEventEndTime(String eventEndTime)     { this.eventEndTime = eventEndTime; }
    public String   getEventLocation()                      { return eventLocation; }
    public void     setEventLocation(String eventLocation)  { this.eventLocation = eventLocation; }


    public static Connection createConnection(String driver, String url, String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
    }
    public static Connection createConnection() throws ClassNotFoundException, SQLException {
        return createConnection(DEFAULT_DRIVER, DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }
}
