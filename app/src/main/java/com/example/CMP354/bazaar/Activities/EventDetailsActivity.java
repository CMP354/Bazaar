package com.example.CMP354.bazaar.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.CMP354.bazaar.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EventDetailsActivity extends Activity {

    private String eventID="";
    private String eventName="";
    private String eventDesc="";
    private String eventDate="";
    private String eventTime="";
    private String eventLocation="";
    private String userID="";
    private String role="";
    private int count=0;

   SurfaceView surfaceView;
    private static final String DEFAULT_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DEFAULT_URL = "jdbc:oracle:thin:@coeoracle.aus.edu:1521:orcl";
    private static final String DEFAULT_USERNAME = "g00063299";
    private static final String DEFAULT_PASSWORD = "g00063299";
    private Connection connection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_details);

//
//        final FloatingActionButton floatingActionButton = findViewById(R.id.event_signupBtn);
//        final FloatingActionButton unsignupBtn = findViewById(R.id.event_UnsignupBtn);
//        final FloatingActionButton deleteBtn = findViewById(R.id.event_DeleteBtn);

        this.setEventID(getIntent().getStringExtra("EventID"));
        this.setEventName(getIntent().getStringExtra("EventTitle"));
        this.setEventDesc(getIntent().getStringExtra("EventDesc"));
        this.setEventDate(getIntent().getStringExtra("EventDate"));
        this.setEventTime(getIntent().getStringExtra("EventTime"));
        this.setEventLocation(getIntent().getStringExtra("EventLocation"));
        this.setUserID(getIntent().getStringExtra("userID"));
        this.setRole(getIntent().getStringExtra("Role"));

        TextView title= findViewById(R.id.eventTitle);
        TextView desc= findViewById(R.id.eventDesc);
        TextView date= findViewById(R.id.eventDate);
        TextView time= findViewById(R.id.eventTime);
        TextView loc= findViewById(R.id.eventLocation);
        TextView eventCount= findViewById(R.id.numofV);
        TextView eventCountTitle= findViewById(R.id.numofVTitle);


        title.setText(this.getEventName());
        desc.setText(this.getEventDesc());
        date.setText(this.getEventDate());
        time.setText(this.getEventTime());

        loc.setText(this.getEventLocation());
        eventCount.setText(String.valueOf(this.getCount()));


        if(role.equals("user")) {
          //  checkUserSignUp(userID, eventID);
            eventCount.setVisibility(View.INVISIBLE);
            eventCountTitle.setVisibility(View.INVISIBLE);
//            deleteBtn.hide();
//            floatingActionButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    signUpVolun(userID, eventID);
//                }
//            });
        }
        if(role.equals("admin")){
            eventCount.setVisibility(View.VISIBLE);
            eventCountTitle.setVisibility(View.VISIBLE);
            checkDeleteEvent(eventID);
            countV(getEventID());
            eventCount.setText(String.valueOf(this.getCount()));
           // floatingActionButton.setImageResource(R.drawable.ic_qr_add_clear_24dp);
//            floatingActionButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(getBaseContext(), "QR Button Clicked", Toast.LENGTH_SHORT).show();
//                }
//            });
//            unsignupBtn.setImageResource(R.drawable.ic_edit_24dp);
//            unsignupBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(getBaseContext(), "Edit Button Clicked", Toast.LENGTH_SHORT).show();
//                    editEvent(eventID,eventName,eventDesc,eventDate,eventTime, eventLocation,userID);
//                }
//            });



        }
    }
//    public void checkUserSignUp(final String userID, final String eventID){
//
//        try {
//            final FloatingActionButton signupBtn = findViewById(R.id.event_signupBtn);
//            final FloatingActionButton unsignupBtn = findViewById(R.id.event_UnsignupBtn);
//
//            this.connection = createConnection();
//            Statement stmt=connection.createStatement();
//
//            boolean found=false;
//            boolean attended=false;
//
//            ResultSet rs=stmt.executeQuery("select * from event_volunteer");
//            while(rs.next()) {
//                if(rs.getString("v_id").equals(userID) && rs.getString("event_id").equals(eventID)){
//                    found=true;
//                    if(rs.getInt("attendance")==1){
//                        attended=true;
//                    }
//                }
//            }
//            if(found==false){ //if they're not signup for an event, show the signup button -> go to sign up function
//
//                signupBtn.show();
//                unsignupBtn.hide();
//                signupBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        signUpVolun(userID, eventID);
//                    }
//                });
//            }
//            if(found==true && attended==false){ //if they're signed up and have not marked their attendance - allow them to remove themselves
//                                                //-> go to unsignup function
//
//                signupBtn.hide();
//                unsignupBtn.show();
//                unsignupBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        UnsignUpVolun(userID, eventID);
//                    }
//                });
//            }
//            if(found==true && attended==true){ // hide options from events the user attended
//
//                signupBtn.hide();
//                unsignupBtn.hide();
//            }
//            connection.close();
//
//        }
//        catch (Exception e) {
//            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }
//
//    }

    public void checkDeleteEvent(final String eventID){
//        final FloatingActionButton deleteBtn = findViewById(R.id.event_DeleteBtn);
//        final FloatingActionButton editBtn = findViewById(R.id.event_UnsignupBtn);
        boolean attended=false;

        try {
            this.connection = createConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from event_volunteer where attendance=1");

            while (rs.next()){

                if(rs.getInt("event_id")==Integer.valueOf(eventID)) {
                    attended=true;

                }
            }
        }
        catch (Exception e) {
        }
//        if( attended==false){
//            deleteBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    deleteEvent(eventID);
//                }
//            });
//        }
//        if(attended==true){
//            deleteBtn.hide();
//            editBtn.hide();
//        }

    }
    public void deleteEvent(String eventID){
        try {
            this.connection = createConnection();
            Statement stmt=connection.createStatement();
            ResultSet rs=stmt.executeQuery("delete from events where event_id="+eventID);
            Toast.makeText(this, "Event Was Successfully Deleted!", Toast.LENGTH_SHORT).show();

            connection.close();
        }
        catch (Exception e) {
            Toast.makeText(this, "Error - Event Was Not Deleted", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    public void UnsignUpVolun(String userID, String eventID){
        try {
            this.connection = createConnection();
            Statement stmt=connection.createStatement();
            ResultSet rs=stmt.executeQuery("select * from event_volunteer");
            ResultSet rs2;
            while(rs.next()) {
                if(rs.getString("v_id").equals(userID) && rs.getString("event_id").equals(eventID)){
                        rs2 = stmt.executeQuery("delete from event_volunteer where v_id = '" + userID + "' and event_id = '" + eventID + "'");
                        Toast.makeText(this, "Volunteer Was Successfully Removed", Toast.LENGTH_SHORT).show();

                }
            }
            connection.close();
        }
        catch (Exception e) {
            Toast.makeText(this, "Error - Volunteer Was Not Removed", Toast.LENGTH_SHORT).show();
        }

    }
    public void countV(String eventID){
        try {
            this.connection = createConnection();
            Statement stmt=connection.createStatement();
            ResultSet rs=stmt.executeQuery("select count(*) as total from event_volunteer where event_id="+eventID+"");
            rs.next();

            setCount(rs.getInt("total"));

            connection.close();

        }
        catch (Exception e) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }

    }

    public void signUpVolun(String userID, String eventID){
        try {
            this.connection = createConnection();
            Statement stmt=connection.createStatement();
            boolean found=false;
            ResultSet rs=stmt.executeQuery("select * from event_volunteer");
            while(rs.next()) {
                if(rs.getString("v_id").equals(userID) && rs.getString("event_id").equals(eventID)){
                    found=true;
                }
            }
            if(found==false){
                stmt.executeQuery("insert into event_volunteer (v_id, event_id) values('"+userID+"','"+eventID+"')");
                Toast.makeText(this, "Sign Up Successful!",
                        Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "User Already Signed Up For This Event", Toast.LENGTH_SHORT).show();
            }
            connection.close();
        }
        catch (Exception e) {
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        }




public void editEvent(String eventID, String eventName, String eventDesc, String eventDate,String eventTime, String eventLocation,String userID) {
    Intent intent=new Intent(EventDetailsActivity.this,EditEventActivity.class);
    intent.putExtra("Event ID", eventID);
    intent.putExtra("EventTitle", eventName);
    intent.putExtra("EventDesc", eventDesc);
    intent.putExtra("EventDate",eventDate);
    String[] parts = eventTime.split(" - ");
    String eventStartTime = parts[0];
    String eventEndTime = parts[1];
    intent.putExtra("EventStartTime",eventStartTime);
    intent.putExtra("EventEndTime", eventEndTime);
    intent.putExtra("EventLocation",eventLocation);
    intent.putExtra("userID",userID);
    startActivity(intent);
    finish();
}
    public String   getRole()                               {  return role; }
    public void     setRole(String role)                    { this.role = role; }
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
    public String   getEventTime()                          { return eventTime; }
    public void     setEventTime(String eventTime)       {this.eventTime = eventTime; }
    public String   getEventLocation()                      { return eventLocation; }
    public void     setEventLocation(String eventLocation)  { this.eventLocation = eventLocation; }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static Connection createConnection(String driver, String url, String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
    }
    public static Connection createConnection() throws ClassNotFoundException, SQLException {
        return createConnection(DEFAULT_DRIVER, DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }
}
