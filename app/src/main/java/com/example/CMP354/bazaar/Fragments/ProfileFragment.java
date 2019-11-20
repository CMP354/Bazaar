package com.example.CMP354.bazaar.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.CMP354.bazaar.R;

import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    private ProfileFragmentInteractionListener mListener;
    public ProfileFragment() { }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
    public void onButtonPressed(Uri uri) {
        if (mListener != null) { mListener.onProfileFragmentInteraction(uri); }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ProfileFragmentInteractionListener) {
            mListener = (ProfileFragmentInteractionListener) context;
        } else { throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener"); }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public interface ProfileFragmentInteractionListener { void onProfileFragmentInteraction(Uri uri);}
    public interface OnFragmentInteractionListener { void onFragmentInteraction(Uri uri);}
}
