package com.example.CMP354.bazaar.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.CMP354.bazaar.Classes.EventItem;
import com.example.CMP354.bazaar.Classes.EventItemAdapter;
import com.example.CMP354.bazaar.Fragments.EventFragment;
import com.example.CMP354.bazaar.Fragments.EventFragment_User;
import com.example.CMP354.bazaar.Fragments.HistoryFragment;
import com.example.CMP354.bazaar.Fragments.HomeFragment;
import com.example.CMP354.bazaar.Fragments.ProfileFragment;
import com.example.CMP354.bazaar.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        ProfileFragment.ProfileFragmentInteractionListener,
        HistoryFragment.HistoryFragmentInteractionListener,
        EventFragment.MainFragmentInteractionListener,
        HomeFragment.HomeFragmentInteractionListener {

    final ProfileFragment pfragment = new ProfileFragment();
    final HistoryFragment hfragment = new HistoryFragment();

    final HomeFragment homeFragment = new HomeFragment();
    final EventFragment_User eventFragment = new EventFragment_User();

    final FragmentManager fm = getSupportFragmentManager();


    Fragment active = homeFragment;


    private String FName = "";
    private String LName = "";
    private String ID = "";
    private String Number = "";
    private String Email = "";
    private Double CompletedHrs = 0.0;
    private Double PendingHrs = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        //initializeRecyclerView();
        setVals();

        fm.beginTransaction().add(R.id.fragment_container, pfragment, "3").hide(pfragment).commit();
        fm.beginTransaction().add(R.id.fragment_container, hfragment, "4").hide(hfragment).commit();
        fm.beginTransaction().add(R.id.fragment_container, homeFragment,"1").commit();


        final ImageButton homeBtn = this.findViewById(R.id.homeBtn);
        final ImageButton searchBtn = this.findViewById(R.id.searchBtn);
        final ImageButton profileBtn = this.findViewById(R.id.profileBtn);
        final ImageButton shopsBtn = this.findViewById(R.id.shopsBtn);


        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeBtn.setImageResource(R.drawable.ic_home_24dp_orange);
                searchBtn.setImageResource(R.drawable.ic_search_24dp_orange);
                shopsBtn.setImageResource(R.drawable.ic_list_24dp_orange);
                profileBtn.setImageResource(R.drawable.ic_person_24dp_aqua);
                loadProfileScreen();
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeBtn.setImageResource(R.drawable.ic_home_24dp_aqua);
                profileBtn.setImageResource(R.drawable.ic_person_24dp_orange);
                shopsBtn.setImageResource(R.drawable.ic_list_24dp_orange);
                searchBtn.setImageResource(R.drawable.ic_search_24dp_orange);
                loadMainScreen();
            }
        });
        shopsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeBtn.setImageResource(R.drawable.ic_home_24dp_orange);
                profileBtn.setImageResource(R.drawable.ic_person_24dp_orange);
                searchBtn.setImageResource(R.drawable.ic_search_24dp_orange);
                shopsBtn.setImageResource(R.drawable.ic_list_24dp_aqua);
                loadDashboardScreen();
            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeBtn.setImageResource(R.drawable.ic_home_24dp_orange);
                profileBtn.setImageResource(R.drawable.ic_person_24dp_orange);
                searchBtn.setImageResource(R.drawable.ic_search_24dp_aqua);
                shopsBtn.setImageResource(R.drawable.ic_list_24dp_orange);



            }
        });


    }


    private void initializeRecyclerViewEvent() {

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
    private void initializeRecyclerViewVolunteer()
    {

        RecyclerView recyclerViewEvent = findViewById(R.id.v_Event_History_RV);
        LinearLayoutManager linearLayoutManagerEvent = new LinearLayoutManager(this);
        linearLayoutManagerEvent.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewEvent.setLayoutManager(linearLayoutManagerEvent);

        List<EventItem> eventItemList = getRecyclerViewVolunteer();
        EventItemAdapter eventItemAdapter = new EventItemAdapter(eventItemList);
        recyclerViewEvent.setAdapter(eventItemAdapter);

        DividerItemDecoration dividerItemDecorationEvent = new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL);
        recyclerViewEvent.addItemDecoration(dividerItemDecorationEvent);

    }

    private List<EventItem> getRecyclerViewItemDtoListEvent() {
        List<EventItem> retEvent = new ArrayList<EventItem>();
        return retEvent;
    }

    private List<EventItem> getRecyclerViewVolunteer()
    {
        List<EventItem> retEvent = new ArrayList<EventItem>();
        return retEvent;
    }


    public void setVals() {
        this.setID(getIntent().getStringExtra("User_ID"));
        this.setFName(getIntent().getStringExtra("User_FName"));
        this.setLName(getIntent().getStringExtra("User_LName"));
        this.setEmail(getIntent().getStringExtra("User_Email"));
        this.setNumber(getIntent().getStringExtra("User_Num"));


    }

    public void loadProfileScreen() {
        TextView name = findViewById(R.id.profileName);
      //  TextView id = findViewById(R.id.profileID);
        TextView number = findViewById(R.id.profileNumber);
        TextView email = findViewById(R.id.profileEmail);
        name.setText(getFName() + " " + getLName());
   //     id.setText(getID());
        number.setText(getNumber());
        email.setText(getEmail());
        fm.beginTransaction().hide(active).show(pfragment).commit();
        active = pfragment;

        Button editBtn= findViewById(R.id.editBtn);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfile(getID(), getFName(), getLName(), getNumber(), getEmail());


                //finish();
            }
        });
    }
    public void loadHistoryScreen() {
        this.setVals();
        double hrs=0.0;
        String startT="";
        String endT="";
        initializeRecyclerViewVolunteer();

        TextView compTime= findViewById(R.id.completedHrs);
        compTime.setText(String.valueOf(getCompletedHrs()));
        TextView pendTime= findViewById(R.id.pendingHrs);
        pendTime.setText(String.valueOf(getPendingHrs()));
        fm.beginTransaction().hide(active).show(hfragment).commit();
        active=hfragment;
    }

    public void loadDashboardScreen() {
        initializeRecyclerViewEvent();
        fm.beginTransaction().hide(active).show(eventFragment).commit();
        active = eventFragment;
    }
    public void loadMainScreen() {
       // initializeRecyclerView();
        fm.beginTransaction().hide(active).show(homeFragment).commit();
        active = homeFragment;
    }

    public void editProfile(String id, String fname, String lname, String phonenum, String email){

                Intent intent = new Intent(MainActivity.this, EditProfileActivity.class);
                intent.putExtra("User_ID", id);
                intent.putExtra("User_FName", fname);
                intent.putExtra("User_LName", lname);
                intent.putExtra("User_Email", email);
                intent.putExtra("User_Num", phonenum);

                startActivity(intent);
                //finish();

    }

    public Double   getCompletedHrs()                       {   return CompletedHrs; }
    public void     setCompletedHrs(Double completedHrs)    { CompletedHrs = completedHrs; }
    public Double   getPendingHrs()                         { return PendingHrs; }
    public void     setPendingHrs(Double pendingHrs)        { PendingHrs = pendingHrs; }
    public String   getFName()                              { return FName;   }
    public void     setFName(String FName)                  { this.FName = FName; }
    public String   getLName()                              { return LName; }
    public void     setLName(String LName)                  { this.LName = LName; }
    public String   getID()                                 { return ID;    }
    public void     setID(String ID)                        { this.ID = ID;    }
    public String   getNumber()                             { return Number;    }
    public void     setNumber(String number)                { Number = number;    }
    public String   getEmail()                              { return Email;    }
    public void     setEmail(String email)                  { Email = email;    }



    @Override
    public void onMainFragmentInteraction(Uri uri) { }
    @Override
    public void onHistoryFragmentInteraction(Uri uri) { }
    @Override
    public void onProfileFragmentInteraction(Uri uri) { }
    @Override
    public void onHomeFragmentInteraction(Uri uri) {}
}