package com.munson.david.baapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    private ArrayList<EventContainer> eventContainerList;
    private ArrayList<SocialPost> feedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        eventContainerList = new ArrayList<>();
        feedList = new ArrayList<>();
        Event sampleEvent = new Event();
        sampleEvent.setTitle("Sample Event");
        sampleEvent.setLocation("Death Star");
        sampleEvent.setDescription("a sample event for layour configuration");
        sampleEvent.setStartTime(new Date());
        sampleEvent.setEndTime(new Date());
        addEvent(sampleEvent);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }
            // Create a new Fragment to be placed in the activity layout
            PlannerFragment firstFragment = new PlannerFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }



    }

    protected <Primary extends Fragment> void changePrimaryFragment(Primary primary){
        // Create fragment and give it an argument specifying the article it should show



        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, primary);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();


    }

    public void onClickCreate(){
       CreateFragment newFragment = new CreateFragment();
        changePrimaryFragment(newFragment);
    }
    public void onClickPlanner(){
        PlannerFragment newFragment = new PlannerFragment();
        changePrimaryFragment(newFragment);
    }

    public void onClickSearch(){
        SearchFragment newFragment = new SearchFragment();
        changePrimaryFragment(newFragment);
    }

    public void onClickSocial(){
        SocialFragment newFragment = new SocialFragment();
        changePrimaryFragment(newFragment);
    }
    public void onClickRemove(Event e){
        for(int i = 0; i<eventContainerList.size(); i++){
            for (int j = 0;j<eventContainerList.get(i).getEvents().size(); j++){
                if (eventContainerList.get(i).getEvents().get(j).getId() == e.getId()) {
                    eventContainerList.get(i).getEvents().remove(j);
                }
            }
        }
    }

    public void onClickRemoveEvent(int eventId){

    }
    public ArrayList<SocialPost> getFeedList() {
        return feedList;
    }

    public ArrayList<EventContainer> getEventContainerList() {
        return eventContainerList;
    }

    public void addEvent(Event e) {
        if (e != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(e.getStartTime());
            EventContainer container = new EventContainer();

            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy");
            SimpleDateFormat times = new SimpleDateFormat("HH:mm");
            String eventDate = sdf.format(cal.getTime());
            for (int i = 0; i < eventContainerList.size(); i++) {
                if (eventDate.equals(eventContainerList.get(i).getDateTag())) {
                    eventContainerList.get(i).getEvents().add(e);
                    sortEvents(eventContainerList.get(i).getEvents());
                    return;
                }
            }
            container.setDateTag(eventDate);
            container.setDate(e.getStartTime());
            container.getEvents().add(e);
            eventContainerList.add(container);
            sortEventContainerList(eventContainerList);

            String eventPosting = "you are hosting " + e.getTitle() + " on " + eventDate + " from "
                    +times.format(e.getStartTime()) + " to " + times.format(e.getEndTime());
            SocialPost post = new SocialPost(eventPosting);
            feedList.add(post);
            sortPosts(feedList);


        }

    }
    private void sortEventContainerList(ArrayList<EventContainer> e){
        for(int i = 0; i<e.size(); i++){
            int min = i;
            for (int j = i; j<e.size();j++){
                if(e.get(j).getDate().before(e.get(min).getDate())){
                    min = j;
                }
                EventContainer temp = e.get(i);
                e.set(i,e.get(min));
                e.set(min, temp);
            }
        }
    }
    private void sortEvents(ArrayList<Event> e){
        for(int i = 0; i<e.size(); i++){
            int min = i;
            for (int j = i; j<e.size();j++){
                if(e.get(j).getStartTime().before(e.get(min).getStartTime())){
                    min = j;
                }
                Event temp = e.get(i);
                e.set(i,e.get(min));
                e.set(min, temp);
            }
        }
    }
    private void sortPosts(ArrayList<SocialPost> e) {
        for(int i = 0; i<e.size(); i++){
            int min = i;
            for (int j = i; j<e.size();j++){
                if(e.get(j).getDate().before(e.get(min).getDate())){
                    min = j;
                }
                SocialPost temp = e.get(i);
                e.set(i,e.get(min));
                e.set(min, temp);
            }
        }
    }
}
