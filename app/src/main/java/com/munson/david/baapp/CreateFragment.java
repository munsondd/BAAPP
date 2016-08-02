package com.munson.david.baapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;


public class CreateFragment extends Fragment {
    private static EditText titleInput;
    private static EditText descriptionInput;
    private static EditText startTimeInput;
    private static EditText endTimeInput;
    private static EditText dateInput;
    private static EditText locationInput;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_create, container, false);
        titleInput = (EditText) view.findViewById(R.id.eventTitleId);
        descriptionInput = (EditText) view.findViewById(R.id.descriptionId);
        startTimeInput = (EditText) view.findViewById(R.id.startTime);
        endTimeInput = (EditText) view.findViewById(R.id.endTime);
        dateInput = (EditText) view.findViewById(R.id.eventDate);
        locationInput = (EditText) view.findViewById(R.id.locationId);

        final Button createEventButton = (Button) view.findViewById(R.id.createNewEvent);
        createEventButton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        MainActivity activity = (MainActivity) getActivity();
                        Event e = newEvent();
                        if(e != null)
                            activity.addEvent(newEvent());
                    }
                });
        return view;
    }


    public Event newEvent(){
        Event e = new Event();
        e.setTitle(titleInput.getText().toString());
        e.setDescription(descriptionInput.getText().toString());
        e.setLocation(locationInput.getText().toString());
        try{
            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
            Date startDate = formatter.parse(dateInput.getText().toString() +
                    " "+ startTimeInput.getText().toString());
            Date endDate = formatter.parse(dateInput.getText().toString() +
                    " "+ endTimeInput.getText().toString());
            if(startDate.after(endDate))
                throw new ParseException("",1);

            e.setStartTime(startDate);
            e.setEndTime(endDate);

        }catch(ParseException ex) {
            System.out.println("invalid date format");
            MainActivity activity = (MainActivity) getActivity();
            Toast toast = Toast.makeText(activity,"invalid time or date", Toast.LENGTH_SHORT);
            toast.show();
            return null;
        }

        MainActivity activity = (MainActivity) getActivity();
        Toast toast = Toast.makeText(activity,"event created", Toast.LENGTH_SHORT);
        toast.show();
        return e;

    }

}
