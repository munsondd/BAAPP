package com.munson.david.baapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;


public class PlannerFragment extends Fragment {
    MainActivity activity;
    View view;
    ExpandableListAdapter listAdapter;
    ExpandableListView expandableListView;
    ArrayList<String> listDataHeader;
    HashMap<String, ArrayList<Event>> listDataChild;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        view = inflater.inflate(R.layout.fragment_planner, container, false);



        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        expandableListView = (ExpandableListView) view.findViewById(R.id.expandablePlannerList);
        // preparing list data
        prepareListData();
        listAdapter = new ExpandableListAdapter(activity, listDataHeader, listDataChild);
        expandableListView.setAdapter(listAdapter);

       /*final Button removeButton = (Button) view.findViewById(R.id.removeButton);
        removeButton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){

                        //activity.onClickRemoveEvent(listAdapter.getChild());

                    }
                });*/

    }

    private void prepareListData() {
        ArrayList<EventContainer>  data;
        data = activity.getEventContainerList();
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();
        for(int i =0; i<data.size(); i++){
                listDataHeader.add(data.get(i).getDateTag());
                listDataChild.put(data.get(i).getDateTag(), data.get(i).getEvents()
                );
            }

    }


}
