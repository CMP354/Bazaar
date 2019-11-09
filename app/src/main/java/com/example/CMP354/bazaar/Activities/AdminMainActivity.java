package com.example.CMP354.bazaar.Activities;

import android.net.Uri;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.CMP354.bazaar.Classes.EventItem;
import com.example.CMP354.bazaar.Classes.EventItemAdapter;
import com.example.CMP354.bazaar.Fragments.AddEventFragment;
import com.example.CMP354.bazaar.Fragments.EventFragment;
import com.example.CMP354.bazaar.Fragments.HomeFragment;
import com.example.CMP354.bazaar.Fragments.ProfileFragment;
import com.example.CMP354.bazaar.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminMainActivity extends AppCompatActivity implements
        ProfileFragment.ProfileFragmentInteractionListener,
        EventFragment.MainFragmentInteractionListener,
        AddEventFragment.AddEventFragmentInteractionListener,
        HomeFragment.HomeFragmentInteractionListener {

    private static final String DEFAULT_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DEFAULT_URL = "jdbc:oracle:thin:@coeoracle.aus.edu:1521:orcl";
    private static final String DEFAULT_USERNAME = "g00068368";
    private static final String DEFAULT_PASSWORD = "g00068368";
    private Connection connection;

    final ProfileFragment pfragment=new ProfileFragment();
    final EventFragment eventFragment = new EventFragment();
    final HomeFragment homeFragment = new HomeFragment();

    final FragmentManager fm = getSupportFragmentManager();


    Fragment active = homeFragment;

    private String FName="";
    private String LName="";
    private String ID="";
    private String Number="";
    private String Email="";

    //----------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setVals();

        setContentView(R.layout.activity_admin_main);

        final FloatingActionButton floatingActionButton = findViewById(R.id.floating_action_button);

        floatingActionButton.hide();

        fm.beginTransaction().add(R.id.fragment_container, pfragment, "3").hide(pfragment).commit();
        fm.beginTransaction().add(R.id.fragment_container, homeFragment, "1").commit();


        final ImageButton homeBtn = this.findViewById(R.id.homeBtn);
        final ImageButton dashboardBtn = this.findViewById(R.id.dashboardBtn);
        final ImageButton profileBtn = this.findViewById(R.id.profileBtn);


        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeBtn.setImageResource(R.drawable.ic_home_24dp_orange);
                profileBtn.setImageResource(R.drawable.ic_person_24dp_aqua);
                dashboardBtn.setImageResource(R.drawable.ic_list_24dp_orange);
                loadProfileScreen();
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeBtn.setImageResource(R.drawable.ic_home_24dp_aqua);
                profileBtn.setImageResource(R.drawable.ic_person_24dp_orange);
                dashboardBtn.setImageResource(R.drawable.ic_list_24dp_orange);
                loadMainScreen(floatingActionButton);
            }
        });
        dashboardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeBtn.setImageResource(R.drawable.ic_home_24dp_orange);
                profileBtn.setImageResource(R.drawable.ic_person_24dp_orange);
                dashboardBtn.setImageResource(R.drawable.ic_list_24dp_aqua);
                loadDashboardScreen(floatingActionButton);
            }
        });
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }
    private void initializeRecyclerViewEvent()
    {
        RecyclerView recyclerViewEvent = findViewById(R.id.admin_news_recycler_view);
        LinearLayoutManager linearLayoutManagerEvent = new LinearLayoutManager(this);
        linearLayoutManagerEvent.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewEvent.setLayoutManager(linearLayoutManagerEvent);
        List<EventItem> eventItemList = getRecyclerViewItemDtoListEvent();
        EventItemAdapter eventItemAdapter = new EventItemAdapter(eventItemList);
        recyclerViewEvent.setAdapter(eventItemAdapter);
        DividerItemDecoration dividerItemDecorationEvent = new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL);
        recyclerViewEvent.addItemDecoration(dividerItemDecorationEvent);
    }

    private List<EventItem> getRecyclerViewItemDtoListEvent()
    {
        List<EventItem> retEvent = new ArrayList<EventItem>();
        try {
            this.connection = createConnection();
            Statement stmt=connection.createStatement();
            ResultSet rs=stmt.executeQuery("select * from events");
            while(rs.next()) {
                EventItem item= new EventItem();
                item.setID(rs.getString("event_id"));
                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setLocation(rs.getString("location"));
                item.setDate(rs.getDate("event_date"));
                item.setStart_time(rs.getString("start_time"));
                item.setEnd_time(rs.getString("end_time"));
                item.setUserID(getID());
                item.setRole("admin");
                retEvent.add(item);
            }
            connection.close();
        }
        catch (Exception e) {
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return retEvent;
    }

//    private List<NewsItem> getRecyclerViewItemDtoList()
//    {
//        List<NewsItem> ret = new ArrayList<NewsItem>();
//        try {
//            this.connection = createConnection();
//            Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
//            Statement stmt=connection.createStatement();
//            ResultSet rs=stmt.executeQuery("select * from news");
//            while(rs.next()) {
//                NewsItem item= new NewsItem();
//                item.setTitle(rs.getString("title"));
//                item.setDesc(rs.getString("description"));
//                ret.add(item);
//            }
//
//            connection.close();
//        }
//        catch (Exception e) {
//            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }
//        return ret;
//    }

    public void loadProfileScreen() {

        Button editBtn= findViewById(R.id.editBtn);
        editBtn.setVisibility(View.INVISIBLE);

        TextView name= findViewById(R.id.profileName);
    //    TextView id= findViewById(R.id.profileID);
        TextView number= findViewById(R.id.profileNumber);
        TextView email= findViewById(R.id.profileEmail);

        name.setText(getFName()+" "+getLName());
     //   id.setText(getID());
        number.setText(getNumber());
        email.setText(getEmail());

        fm.beginTransaction().hide(active).show(pfragment).commit();
        active=pfragment;
    }
    public void setVals(){
        this.setID(getIntent().getStringExtra("User_ID"));
        this.setFName(getIntent().getStringExtra("User_FName"));
        this.setLName(getIntent().getStringExtra("User_LName"));
        this.setEmail(getIntent().getStringExtra("User_Email"));
        this.setNumber(getIntent().getStringExtra("User_Num"));
    }
    public void loadDashboardScreen(FloatingActionButton btn) {
        initializeRecyclerViewEvent();
        btn.show();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAddEventScreen();
            }
        });
        fm.beginTransaction().hide(active).show(eventFragment).commit();
        active=eventFragment;
    }

    public void loadAddEventScreen() {
        AddEventFragment addFragment = new AddEventFragment();
        this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, addFragment).addToBackStack(null).commit();
        active=addFragment;
    }
    public void loadMainScreen(FloatingActionButton btn){
        fm.beginTransaction().hide(active).show(homeFragment).commit();
        active=homeFragment;
        btn.hide();
    }

    public static Connection createConnection(String driver, String url, String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
    }
    public static Connection createConnection() throws ClassNotFoundException, SQLException {
        return createConnection(DEFAULT_DRIVER, DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }

    public String   getFName()                  { return FName; }
    public void     setFName(String FName)      { this.FName = FName; }
    public String   getLName()                  { return LName; }
    public void     setLName(String LName)      { this.LName = LName; }
    public String   getID()                     { return ID;    }
    public void     setID(String ID)            { this.ID = ID; }
    public String   getNumber()                 { return Number; }
    public void     setNumber(String number)    { Number = number; }
    public String   getEmail()                  { return Email; }
    public void     setEmail(String email)      { Email = email; }

    @Override
    public void onMainFragmentInteraction(Uri uri) {}
    @Override
    public void onAddEventFragmentInteraction(Uri uri) {}
    @Override
    public void onProfileFragmentInteraction(Uri uri) {}
    @Override
    public void onHomeFragmentInteraction(Uri uri) {}
}