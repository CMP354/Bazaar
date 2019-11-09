package com.example.CMP354.bazaar.Classes;

import java.io.Serializable;
import java.util.Date;

public class EventItem implements Serializable {


    private String role;
    private String userID;
    private String ID;
    private String name;
    private String description;
    private String location;
    private Date date;
    private String start_time;
    private String end_time;
    private String QR_code;


    public String   getName()                           { return name; }
    public void     setName(String name)                { this.name = name; }
    public String   getQRCode()                         { return QR_code; }
    public void     setQRCode(String QR_Code)           { this.ID = QR_code; }
    public String   getID()                             { return ID; }
    public void     setID(String ID)                    { this.ID = ID; }
    public String   getDescription()                    { return description; }
    public void     setDescription(String description)  { this.description = description; }
    public String   getLocation()                       { return location; }
    public void     setLocation(String location)        { this.location = location; }
    public Date     getDate()                           { return date; }
    public void     setDate(Date date)                  { this.date = date; }
    public String   getStart_time()                     { return start_time; }
    public void     setStart_time(String start_time)    { this.start_time = start_time; }
    public String   getEnd_time()                       { return end_time; }
    public void     setEnd_time(String end_time)        { this.end_time = end_time; }
    public String   getQR_code()                        { return QR_code; }
    public void     setQR_code(String QR_code)          { this.QR_code = QR_code; }
    public String   getUserID()                         { return userID; }
    public void     setUserID(String userID)            { this.userID = userID; }
    public String   getRole()                           { return role; }
    public void     setRole(String role)                { this.role = role; }

    public EventItem() { }
}