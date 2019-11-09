package com.example.CMP354.bazaar.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.CMP354.bazaar.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddEventFragment extends Fragment implements View.OnClickListener {

    private AddEventFragment.AddEventFragmentInteractionListener mListener;
    Button addEventBtn;
    Button cancelBtn;

    private static final String DEFAULT_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DEFAULT_URL = "jdbc:oracle:thin:@coeoracle.aus.edu:1521:orcl";
    private static final String DEFAULT_USERNAME = "g00063299";
    private static final String DEFAULT_PASSWORD = "g00063299";
    private Connection connection;

    public AddEventFragment() { }

    public static AddEventFragment newInstance() {
        AddEventFragment fragment = new AddEventFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

                if (getArguments() != null) { }

                if (android.os.Build.VERSION.SDK_INT > 9) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                }
            }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_event, container, false);

         final TextInputEditText EventTitle= view.findViewById(R.id.newEventTitle);
         final TextInputEditText EventDesc= view.findViewById(R.id.newEventDesc);
         final TextInputEditText EventDate= view.findViewById(R.id.newEventDate);
         final TextInputEditText EventLocation= view.findViewById(R.id.newEventLocation);
         final TextInputEditText EventStartTime= view.findViewById(R.id.newEventStartTime);
         final TextInputEditText EventEndTime= view.findViewById(R.id.newEventEndTime);


        addEventBtn = view.findViewById(R.id.addEventBtn);
        addEventBtn.setOnClickListener(new View.OnClickListener() {
             @Override
                public void onClick(View view) {

                 String eventTitle = (EventTitle.getText().toString());
                 String eventDesc = (EventDesc.getText().toString());
                 String eventDate = (EventDate.getText().toString());
                 String eventLocation = (EventLocation.getText().toString());
                 String eventEndTime = (EventEndTime.getText().toString());
                 String eventStartTime = (EventStartTime.getText().toString());


                 try {
                     connection = createConnection();
                     Statement stmt = connection.createStatement();
                     StringBuffer stringBuffer = new StringBuffer();

                     ResultSet rs = stmt.executeQuery("insert into events ( name, description, location, event_date, start_time, end_time) values('" +
                              eventTitle + "','" + eventDesc + "','" + eventLocation + "', DATE '" +
                             eventDate + "','" + eventStartTime + "','" + eventEndTime + "')");

                     Toast.makeText(getActivity(), "Event Added!", Toast.LENGTH_SHORT).show();
                     getFragmentManager().popBackStack();
                     connection.close();
                 } catch (Exception e) {
                     Toast.makeText(getActivity(), "Error! Event not Added", Toast.LENGTH_SHORT).show();
                 }
             }
        });

        cancelBtn = view.findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Event Discarded",Toast.LENGTH_SHORT).show();
                getFragmentManager().popBackStack();
            }
        });
        return view;
    }
    public void onButtonPressed(Uri uri) {
        if (mListener != null) { mListener.onAddEventFragmentInteraction(uri); }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AddEventFragment.AddEventFragmentInteractionListener) {
            mListener = (AddEventFragment.AddEventFragmentInteractionListener) context;
        } else { throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener"); }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public static Connection createConnection(String driver, String url, String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
    }
    public static Connection createConnection() throws ClassNotFoundException, SQLException {
        return createConnection(DEFAULT_DRIVER, DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }
    @Override
    public void onClick(View v) { }
    public interface AddEventFragmentInteractionListener { void onAddEventFragmentInteraction(Uri uri);}
    public interface OnFragmentInteractionListener { void onFragmentInteraction(Uri uri);}
}

