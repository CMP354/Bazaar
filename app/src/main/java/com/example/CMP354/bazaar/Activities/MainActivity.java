package com.example.CMP354.bazaar.Activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.CMP354.bazaar.Classes.ShopItem;
import com.example.CMP354.bazaar.Fragments.ShopsFragment;
import com.example.CMP354.bazaar.Fragments.SearchFragment;
import com.example.CMP354.bazaar.Fragments.HomeFragment;
import com.example.CMP354.bazaar.Fragments.ProfileFragment;
import com.example.CMP354.bazaar.R;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends FragmentActivity implements
        ProfileFragment.ProfileFragmentInteractionListener,
        SearchFragment.SearchFragmentInteractionListener,
        ShopsFragment.ShopsFragmentInteractionListener,
        HomeFragment.HomeFragmentInteractionListener {

    final ProfileFragment profileFragment = new ProfileFragment();
    final SearchFragment searchFragment = new SearchFragment();
    final HomeFragment myShopFragment = new HomeFragment();
    final ShopsFragment shopsFragment = new ShopsFragment();

    final FragmentManager fm = getSupportFragmentManager();

    private Fragment active = myShopFragment;

    private String FName = "";
    private String LName = "";
    private String ID = "";
    private String Number = "";
    private String Email = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        //initializeRecyclerView();
        setVals();

        fm.beginTransaction().add(R.id.fragment_container, profileFragment, "3").hide(profileFragment).commit();
        fm.beginTransaction().add(R.id.fragment_container, searchFragment, "4").hide(searchFragment).commit();
        fm.beginTransaction().add(R.id.fragment_container, shopsFragment, "2").hide(shopsFragment).commit();

        fm.beginTransaction().add(R.id.fragment_container, myShopFragment,"1").commit();

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
                loadMyShopScreen();
            }
        });
        shopsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeBtn.setImageResource(R.drawable.ic_home_24dp_orange);
                profileBtn.setImageResource(R.drawable.ic_person_24dp_orange);
                searchBtn.setImageResource(R.drawable.ic_search_24dp_orange);
                shopsBtn.setImageResource(R.drawable.ic_list_24dp_aqua);
                loadShopsScreen();
            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeBtn.setImageResource(R.drawable.ic_home_24dp_orange);
                profileBtn.setImageResource(R.drawable.ic_person_24dp_orange);
                searchBtn.setImageResource(R.drawable.ic_search_24dp_aqua);
                shopsBtn.setImageResource(R.drawable.ic_list_24dp_orange);
                loadSearchScreen();


            }
        });


    }


//    private void initializeRecyclerViewShops() {
//
//        RecyclerView recyclerViewShops = findViewById(R.id.shops_recycler_view);
//        LinearLayoutManager linearLayoutManagerShops = new LinearLayoutManager(this);
//        linearLayoutManagerShops.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerViewShops.setLayoutManager(linearLayoutManagerShops);
//
//        List<ShopItem> ShopsItemList = getRecyclerViewListShops();
//        ShopsItemAdapter ShopsItemAdapter = new ShopsItemAdapter(ShopsItemList);
//        recyclerViewShops.setAdapter(ShopsItemAdapter);
//
//        DividerItemDecoration dividerItemDecorationShops = new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL);
//        recyclerViewShops.addItemDecoration(dividerItemDecorationShops);
//
//    }

//    private void initializeRecyclerViewVolunteer()
//    {
//
//        RecyclerView recyclerViewShops = findViewById(R.id.v_Shops_History_RV);
//        LinearLayoutManager linearLayoutManagerShops = new LinearLayoutManager(this);
//        linearLayoutManagerShops.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerViewShops.setLayoutManager(linearLayoutManagerShops);
//
//        List<ShopsItem> ShopsItemList = getRecyclerViewVolunteer();
//        ShopsItemAdapter ShopsItemAdapter = new ShopsItemAdapter(ShopsItemList);
//        recyclerViewShops.setAdapter(ShopsItemAdapter);
//
//        DividerItemDecoration dividerItemDecorationShops = new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL);
//        recyclerViewShops.addItemDecoration(dividerItemDecorationShops);
//
//    }

//    private List<ShopItem> getRecyclerViewListShops() {
//        List<ShopItem> retShops = new ArrayList<ShopItem>();
//        return retShops;
//    }
//
//    private List<ShopsItem> getRecyclerViewVolunteer()
//    {
//        List<ShopsItem> retShops = new ArrayList<ShopsItem>();
//        return retShops;
//    }


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
        fm.beginTransaction().hide(active).show(profileFragment).commit();
        active = profileFragment;

        Button editBtn= findViewById(R.id.editBtn);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfile(getID(), getFName(), getLName(), getNumber(), getEmail());


                //finish();
            }
        });
    }
    public void loadSearchScreen() {
       // initializeRecyclerViewShops();
        fm.beginTransaction().hide(active).show(searchFragment).commit();
        active = searchFragment;
    }

    public void loadShopsScreen() {
      // initializeRecyclerViewShops();
        fm.beginTransaction().hide(active).show(shopsFragment).commit();
        active = shopsFragment;
    }
    public void loadMyShopScreen() {
       // initializeRecyclerView();
        fm.beginTransaction().hide(active).show(myShopFragment).commit();
        active = myShopFragment;
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
    public void onSearchFragmentInteraction(Uri uri) { }
    @Override
    public void onProfileFragmentInteraction(Uri uri) { }
    @Override
    public void onHomeFragmentInteraction(Uri uri) {}
}