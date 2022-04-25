package com.ul.mobileappproject;

public class Game {

    private String game;
    private String description;

    /**
     * Gets the description of the Game object.
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the name of the Game object.
     * @return String
     */
    public String getGame() {
        return game;
    }

    /**
     * Sets the description of the Game object.
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the name of the Game object.
     * @param game
     */
    public void setGame(String game) {
        this.game = game;
    }
}
