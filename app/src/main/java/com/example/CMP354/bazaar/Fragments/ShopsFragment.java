package com.example.CMP354.bazaar.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.CMP354.bazaar.Classes.ShopItem;
import com.example.CMP354.bazaar.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShopsFragment extends Fragment implements View.OnClickListener{

    int[] types = new int[]{
            R.drawable.logo_haha,
    };
    private ListView itemsListView;
    private ShopsFragmentInteractionListener mListener;
    public ShopsFragment() {}

    public static ShopsFragment newInstance() {
        ShopsFragment fragment = new ShopsFragment();
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
        View view=inflater.inflate(R.layout.fragment_shops_list, container, false);

         itemsListView = (ListView)view.findViewById(R.id.shops_list_view);

        updateDisplay();

//        ArrayList<ShopItem> prueba = new ArrayList<ShopItem>();
//        prueba.add(new ShopItem("hi","hhh","OKKK"));
//        prueba.add(new ShopItem("hi","hhh","OKKK"));
//        prueba.add(new ShopItem("hi","hhh","OKKK"));
//
//        ArrayAdapter<ShopItem> allItemsAdapter = new ArrayAdapter<ShopItem>(getActivity().getBaseContext(),R.layout.fragment_shopitem,prueba);
//
//        lstItems.setAdapter(allItemsAdapter);

        return view;
    }

    public void updateDisplay()
    {
        // get the items for the feed
       // List<RSSItem> items = feed.getAllItems();
        ArrayList<ShopItem> items = new ArrayList<ShopItem>();
        items.add(new ShopItem("hi","hhh","OKKK"));
        items.add(new ShopItem("hi","hhh","OKKK"));
        items.add(new ShopItem("hi","hhh","OKKK"));

        // create a List of Map<String, ?> objects
        ArrayList<HashMap<String, String>> data =
                new ArrayList<HashMap<String, String>>();
        for (ShopItem item : items) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("name", item.getShopName());
            map.put("des", item.getShopDes());
            map.put("type", Integer.toString(types[item.getShopImg()]) );            data.add(map);
        }

        // create the resource, from, and to variables
        int resource = R.layout.fragment_shopitem;
        String[] from = {"name", "des", "type"};
        int[] to = { R.id.shop_name, R.id.shop_des, R.id.shopTypeImage};

        // create and set the adapter
        SimpleAdapter adapter =
                new SimpleAdapter(getActivity().getBaseContext(), data, resource, from, to);
        itemsListView.setAdapter(adapter);

        Log.d("News reader", "Feed displayed");
    }

    @Override
    public void onClick(View v) { }
    public void onButtonPressed(Uri uri) {
        if (mListener != null) { mListener.onMainFragmentInteraction(uri); }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ShopsFragmentInteractionListener) {
            mListener = (ShopsFragmentInteractionListener) context;
        } else { throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener"); }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public interface ShopsFragmentInteractionListener { void onMainFragmentInteraction(Uri uri);}
}
