package com.example.CMP354.bazaar.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.CMP354.bazaar.R;

import java.util.ArrayList;


public class SearchFragment extends Fragment  {

    private SearchFragmentInteractionListener mListener;
    public SearchFragment() { }

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
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

        View view= inflater.inflate(R.layout.fragment_search, container, false);
        ListView lstItems = (ListView)view.findViewById(R.id.searchListView);

        ArrayList<String> prueba = new ArrayList<String>();
        prueba.add("Element1");
        prueba.add("Element2");
        prueba.add("Element3");

        ArrayAdapter<String> allItemsAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_1,prueba);

        lstItems.setAdapter(allItemsAdapter);

        return view;
    }
    public void onButtonPressed(Uri uri) {
        if (mListener != null) { mListener.onSearchFragmentInteraction(uri); }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SearchFragmentInteractionListener) {
            mListener = (SearchFragmentInteractionListener) context;
        } else { throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener"); }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public interface SearchFragmentInteractionListener { void onSearchFragmentInteraction(Uri uri);}
    public interface OnFragmentInteractRionListener { void onFragmentInteraction(Uri uri);}
}