package com.munson.david.baapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.app.Activity;
import android.widget.Button;


public class BottomTabsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.bottom_tabs_fragment, container, false);

        final Button createButton = (Button) view.findViewById(R.id.create_button);

        createButton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        MainActivity activity = (MainActivity) getActivity();
                        activity.onClickCreate();

            }
        });

        final Button plannerButton = (Button) view.findViewById(R.id.planner_button);
        plannerButton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        MainActivity activity = (MainActivity) getActivity();
                        activity.onClickPlanner();

                    }
                });


        final Button searchButton = (Button) view.findViewById(R.id.search_button);
        searchButton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        MainActivity activity = (MainActivity) getActivity();
                        activity.onClickSearch();

                    }
                });

        final Button socialButton = (Button) view.findViewById(R.id.social_button);
        socialButton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        MainActivity activity = (MainActivity) getActivity();
                        activity.onClickSocial();

                    }
                });
        return view;
    }



}
