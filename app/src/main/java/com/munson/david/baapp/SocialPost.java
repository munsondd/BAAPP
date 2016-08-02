package com.munson.david.baapp;

import java.util.Date;
/**
 * Created by David on 8/1/2016.
 */
public class SocialPost {
    private String post;
    private Date date;

    public SocialPost(String _post){
        post = _post;
        date = new Date();
    }

    public String getPost() {
        return post;
    }

    public Date getDate() {
        return date;
    }

    public void setPost(String post) {
        this.post = post;
    }

}
