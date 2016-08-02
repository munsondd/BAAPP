package com.munson.david.baapp;



    import java.text.SimpleDateFormat;
    import java.util.ArrayList;
    import java.util.HashMap;

    import android.content.Context;
    import android.graphics.Typeface;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.BaseExpandableListAdapter;
    import android.widget.Button;
    import android.widget.TextView;

    public class ExpandableListAdapter extends BaseExpandableListAdapter {

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        private Context _context;
        private ArrayList<String> _listDataHeader; // header titles
        // child data in format of header title, child title
        private HashMap<String, ArrayList<Event>> _listDataChild;

        public ExpandableListAdapter(Context context, ArrayList<String> listDataHeader,
                                     HashMap<String, ArrayList<Event>> listChildData) {
            this._context = context;
            this._listDataHeader = listDataHeader;
            this._listDataChild = listChildData;
        }

        @Override
        public Object getChild(int groupPosition, int childPosititon) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                    .get(childPosititon);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, final int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {

            final Event child = (Event) getChild(groupPosition, childPosition);

            final String childTitle = child.getTitle();

            final String childTimeLocal = formatter.format(child.getStartTime())+" to "
                    +formatter.format(child.getEndTime()) + " at " + child.getLocation();

            final String childDescription = child.getDescription();

            if (convertView == null) {
                LayoutInflater Inflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = Inflater.inflate(R.layout.planner_event, null);
            }

            TextView childTitleTxt = (TextView) convertView
                    .findViewById(R.id.listEventTitle);

            childTitleTxt.setText(childTitle);

            TextView childTimeLocalTxt = (TextView) convertView
                    .findViewById(R.id.listTimeLocal);

            childTimeLocalTxt.setText(childTimeLocal);

            TextView childDescriptionTxt = (TextView) convertView
                    .findViewById(R.id.listEventDescription);

            childDescriptionTxt.setText(childDescription);

            //insert listener for remove that takes parameter (child)
            final Button removeButton = (Button) convertView.findViewById(R.id.removeButton);
            removeButton.setOnClickListener(
                    new View.OnClickListener(){
                        public void onClick(View v){
                            MainActivity activity = (MainActivity) v.getContext();
                            activity.onClickRemove(child);

                        }
                    });

            return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                    .size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return this._listDataHeader.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return this._listDataHeader.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            String headerTitle = (String) getGroup(groupPosition);
            if (convertView == null) {
                LayoutInflater Inflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = Inflater.inflate(R.layout.planner_parent, null);
            }

            TextView lblListHeader = (TextView) convertView
                    .findViewById(R.id.plannerParent);
            lblListHeader.setTypeface(null, Typeface.BOLD);
            lblListHeader.setText(headerTitle);

            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }


