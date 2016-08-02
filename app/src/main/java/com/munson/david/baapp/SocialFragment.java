package com.munson.david.baapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class SocialFragment extends Fragment {
    MainActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_social, container, false);
        ListView feedListView = (ListView) view.findViewById(R.id.listView);
        activity = (MainActivity) getActivity();
        SocialListAdapter listAdapter = new SocialListAdapter(activity,activity.getFeedList());

        feedListView.setAdapter(listAdapter);
        return view;
    }



}
