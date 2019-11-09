package com.example.CMP354.bazaar.Classes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.CMP354.bazaar.Activities.EventDetailsActivity;
import com.example.CMP354.bazaar.R;

public class EventItemHolder extends RecyclerView.ViewHolder{

    private String role="";
    private String userID="";
    private String eventID="";
    private String eventDesc="";
    private String eventDate="";
    private String eventTime="";
    private String eventLocation="";

    private TextView eventTitle=null;

    Context context = itemView.getContext();

    public EventItemHolder(final View itemView) {
        super(itemView);
        if(itemView!=null) {
            eventTitle = itemView.findViewById(R.id.event_item_title);
            ImageButton moreBtn = itemView.findViewById(R.id.expandEventBtn);
           // FloatingActionButton deleteBtn = (FloatingActionButton) itemView.findViewById(R.id.event_deleteBtn);

            moreBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, EventDetailsActivity.class);


                    intent.putExtra("Role", role);
                    intent.putExtra("EventID", eventID);
                    intent.putExtra("EventTitle", eventTitle.getText().toString());
                    intent.putExtra("EventDesc", eventDesc);
                    intent.putExtra("EventDate", eventDate);
                    intent.putExtra("EventTime", eventTime);
                    intent.putExtra("EventLocation", eventLocation);
                    intent.putExtra("userID", userID);

                    context.startActivity(intent);


                }
            });
/*
            if (role.equals("user")) {
                deleteBtn.hide();
            } else {
                deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EventItemAdapter.deleteItem(getAdapterPosition());
                        }
                });
            }

        */
        }
    }

    public String   getRole()                               { return role; }
    public void     setRole(String role)                    { this.role = role; }
    public String   getUserID()                             { return userID;   }
    public void     setUserID(String userID)                { this.userID = userID; }
    public String   getEventDesc()                          { return eventDesc; }
    public void     setEventDesc(String eventDesc)          { this.eventDesc = eventDesc; }
    public String   getEventDate()                          { return eventDate; }
    public void     setEventDate(String eventDate)          { this.eventDate = eventDate; }
    public String   getEventTime()                          { return eventTime; }
    public void     setEventTime(String eventTime)          { this.eventTime = eventTime; }
    public String   getEventLocation()                      { return eventLocation; }
    public void     setEventLocation(String eventLocation)  { this.eventLocation = eventLocation; }
    public String   getEventID()                            { return eventID; }
    public void     setEventID(String eventID)              { this.eventID = eventID; }
    public TextView getEventTitle()                         { return eventTitle; }
    public void     setEventTitle(TextView eventTitle)      { this.eventTitle = eventTitle; }

}
