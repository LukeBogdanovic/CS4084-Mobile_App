package com.ul.mobileappproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GameInstructionsActivity extends AppCompatActivity {

    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_instructions);

        searchView = findViewById(R.id.mSearch);
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        final Adapter adapter = new Adapter(this, getGames());
        recyclerView.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                adapter.getFilter().filter(query);
                return false;
            }
        });
    }

    private ArrayList<Game> getGames() {
        ArrayList<Game> games = new ArrayList<>();
        Game game = new Game();
        game.setGame("Buzz");
        game.setDescription("Buzz is a fast-paced game which becomes more challenging as the game goes on. To start playing Buzz, the youngest player or whoever the group decides to be the starting player will start the count and say 1. The player to the first player’s left continues to count upwards by saying 2, and this continues until someone makes a mistake with the count.");
        games.add(game);

        game = new Game();
        game.setGame("Beer Pong Beirut");
        game.setDescription("As its name implies, Beer Pong is played similar to how a regular ping pong game is played although of course, you don’t need a table tennis table or paddle to play!\n" +
                "\n" +
                "In Beer Pong, players must face each other with a table in between them. Each side of the table will have 6 cups of beer which should be arranged in a triangle (3-2-1) formation. Players take turns throwing a ping pong ball across the table and trying to shoot the ball into one of their opponent’s beer-filled cups.\n" +
                "\n" +
                "Once a player successfully shoots a ping pong ball into a cup, their opponent has to drink the cup where the ball landed and that cup gets taken off the table. The first player to successfully hit all of their opponent’s cups wins the game! The losing player must drink the remaining cups.");
        games.add(game);

        game = new Game();
        game.setGame("Kings Cup");
        game.setDescription("Set the cards face down at the center of the table and let each player take turns picking a card from the deck. Your cards can surround a mug/glass of beer which would be your Kings Cup. Players then take turns picking a card and doing the action assigned to that particular card");
        games.add(game);

        game = new Game();
        game.setGame("");
        game.setDescription("");
        games.add(game);

        game = new Game();
        game.setGame("");
        game.setDescription("");
        games.add(game);

        return games;
    }

}