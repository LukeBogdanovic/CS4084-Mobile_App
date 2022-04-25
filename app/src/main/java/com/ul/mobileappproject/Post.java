package com.ul.mobileappproject;

public class Post {

    private String profile;
    private String postText;
    private String imageURL;

    /**
     * Constructor of the Post object.
     * Initializes the profile, postText and imageUrl Strings.
     *
     * @param profile
     * @param postText
     * @param imageURL
     */
    public Post(String profile, String postText, String imageURL) {
        this.profile = profile;
        this.postText = postText;
        this.imageURL = imageURL;
    }

    /**
     * Gets the Profile Name of the post object.
     *
     * @return String
     */
    public String getProfile() {
        return profile;
    }

    /**
     * Sets the Profile name of the post object.
     *
     * @param profile
     */
    public void setProfile(String profile) {
        this.profile = profile;
    }

    /**
     * Gets the PostText of the post object.
     *
     * @return String
     */
    public String getPostText() {
        return postText;
    }

    /**
     * Sets the PostText of the post object
     *
     * @param postText
     */
    public void setPostText(String postText) {
        this.postText = postText;
    }

    /**
     * Gets the ImageUrl of the post object.
     *
     * @return String
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * Sets the imageURL of the post object.
     *
     * @param imageURL
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

}
