package com.example.CMP354.bazaar.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.CMP354.bazaar.R;

public class EventFragment_User extends Fragment implements View.OnClickListener{

    private EventFragment.MainFragmentInteractionListener mListener;
    public EventFragment_User() {}

    public static EventFragment newInstance() {
        EventFragment fragment = new EventFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) { }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.event_list, container, false);
        return view;
    }
    @Override
    public void onClick(View v) { }
    public void onButtonPressed(Uri uri) {
        if (mListener != null) { mListener.onMainFragmentInteraction(uri); }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof EventFragment.MainFragmentInteractionListener) {
            mListener = (EventFragment.MainFragmentInteractionListener) context;
        } else { throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener"); }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public interface MainFragmentInteractionListener { void onMainFragmentInteraction(Uri uri);}
}
