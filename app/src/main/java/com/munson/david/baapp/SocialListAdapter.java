package com.munson.david.baapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;



public class SocialListAdapter extends ArrayAdapter {


    public SocialListAdapter(Context context, ArrayList<SocialPost> resource) {
        super(context,R.layout.social_post, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater socialInflater = LayoutInflater.from(getContext());
        View postView = socialInflater.inflate(R.layout.social_post, parent, false);
        SocialPost singlePost = (SocialPost) getItem(position);
        TextView singlePostTxt = (TextView) postView.findViewById(R.id.post);
        //ImageView  postImage = (ImageView) postView.findViewById(R.id.postImage)

        singlePostTxt.setText(singlePost.getPost());
        //postImage.setText(singlePost.getImage())

        return postView;

    }
}
