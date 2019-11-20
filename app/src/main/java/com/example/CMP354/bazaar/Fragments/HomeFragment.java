package com.example.CMP354.bazaar.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.CMP354.bazaar.R;

import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    private HomeFragment.HomeFragmentInteractionListener mListener;
    public HomeFragment() { }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
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
        return inflater.inflate(R.layout.fragment_shop, container, false);
    }
    public void onButtonPressed(Uri uri) {
        if (mListener != null) { mListener.onHomeFragmentInteraction(uri); }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HomeFragment.HomeFragmentInteractionListener) {
            mListener = (HomeFragment.HomeFragmentInteractionListener) context;
        } else { throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener"); }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public interface HomeFragmentInteractionListener { void onHomeFragmentInteraction(Uri uri);}
    public interface OnFragmentInteractionListener { void onFragmentInteraction(Uri uri);}
}
