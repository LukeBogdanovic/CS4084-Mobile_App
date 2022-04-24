package com.ul.mobileappproject;

public class Post {

    private String profile;
    private String postText;
    private String imageURL;

    public Post(String profile, String postText, String imageURL) {
        this.profile = profile;
        this.postText = postText;
        this.imageURL = imageURL;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

}
