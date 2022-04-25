package com.ul.mobileappproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class GameInstructionsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    SearchView searchView;

    /**
     * Initializes the user interface elements from the elements in the xml file.
     * Initializes the navigation drawer user interface.
     * Initializes the Recycler view and sets the adapter for the recycler view.
     * Initializes the Search view and sets the listener for the search view to filter the
     * recycler view list.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_instructions);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Games");
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        searchView = findViewById(R.id.mSearch);
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // Sets the adapter to use the games ArrayList
        final Adapter adapter = new Adapter(this, getGames());
        recyclerView.setAdapter(adapter);
        // Sets listener for change of the text entered by the user
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            /**
             * Filters the list using the entered character sequence
             * @param query
             * @return boolean
             */
            @Override
            public boolean onQueryTextChange(String query) {
                adapter.getFilter().filter(query);
                return false;
            }
        });
    }

    /**
     * Creates the data for the games ArrayList and adds all created items to the
     * ArrayList.
     *
     * @return ArrayList
     */
    public ArrayList<Game> getGames() {
        ArrayList<Game> games = new ArrayList<>();
        Game game = new Game();
        game.setGame("Buzz");
        game.setDescription("Buzz is a fast-paced game which becomes more challenging as the game goes on. To start playing Buzz, the youngest player or whoever the group decides to be the starting player will start the count and say 1. The player to the first player’s left continues to count upwards by saying 2, and this continues until someone makes a mistake with the count.\n" +
                "\n" +
                "Someone messes up the count when they fail to say “buzz” for certain numbers.\n" +
                "\n" +
                "Here are some examples of rules when you can replace numbers with the word “buzz”: - Numbers with a 7 in it (7, 17, 27…) - Multiples of 7 (7, 14, 21…) - Double-digit repeating numbers (11, 22, 33…)\n" +
                "\n" +
                "When someone messes up a buzz, everyone drinks!");
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
        game.setDescription("Set the cards face down at the center of the table and let each player take turns picking a card from the deck. Your cards can surround a mug/glass of beer which would be your Kings Cup. Players then take turns picking a card and doing the action assigned to that particular card.\n" +
                "\n" +
                "What makes Kings Cup so much fun are the rules/actions that players need to follow whenever they pick a card.");
        games.add(game);

        game = new Game();
        game.setGame("Most Likely To");
        game.setDescription("Most Likely To is one example of party drinking games that is also a good way to discover new things about your friends. This game is all about answering questions about who is most likely to do a particular thing. Don’t forget that hearing the story behind the answers takes the conversations to a whole other level!\n" +
                "\n" +
                "How to Play Most Likely To\n" +
                "The main rule of Most Likely To is that players must choose which one among the players is most likely to do a particular action. Depending on how obvious or how shocking the answers are, talking about the answers add more fun to the game!");
        games.add(game);

        game = new Game();
        game.setGame("Cheers to the Governor");
        game.setDescription("Gather in a circle and have your beers ready. The game starts with a player starting the count and saying 1. The person to their left goes up next and says 2. This goes on until one player says 21. At this point, all players yell “Cheers to the Governor!” and drink.\n" +
                "\n" +
                "The person who said 21 has the privilege of making up a rule to be added to the next round.");
        games.add(game);

        game = new Game();
        game.setGame("Would You Rather");
        game.setDescription("Would You Rather is a game where players need to choose between two difficult scenarios. Questions could easily go from easy decisions to tough dilemmas in an instant! It’s your chance to ask all sorts of questions in the name of fun!");
        games.add(game);

        game = new Game();
        game.setGame("Jenga as a Drinking Game");
        game.setDescription("If you’re playing Jenga as a drinking game, most of the mechanics remain the same as how you would play regular Jenga. Players still need to stack the blocks and build the highest tower possible.\n" +
                "\n" +
                "One thing that is not the same is that the individual wooden blocks or stones will be labeled with actions that players need to perform. If a player picks one of these labeled stones, he/she must do the action associated with the label.\n" +
                "\n" +
                "The game goes on with players stacking the blocks and doing the actions on the labels until the tower collapses. The one who causes the tower to fall loses the game and must drink!");
        games.add(game);

        game = new Game();
        game.setGame("Truth or Dare");
        game.setDescription("The main rule when playing Truth or Dare is that each player takes turns choosing between answering a question truthfully or doing a dare.");
        games.add(game);

        game = new Game();
        game.setGame("Yahtzee as a Drinking Game");
        game.setDescription("When playing Yahtzee, players can roll the dice up to 3 times. After the first and second rolls, a player must decide which dice to keep, if any, and roll the dice not kept. After the third roll, the player must choose a box to score on the scorecard.\n" +
                "\n" +
                "Once the player finishes their turn by writing something on the scorecard, their opponent takes a turn. This goes on until both players have filled all 13 boxes on their scorecard. After which, the final tally can be made. The player with the highest score wins the game!\n" +
                "\n" +
                "If you’re playing Yahtzee as a drinking game, the mechanics are the same as when you would play regular Yahtzee except with some additional dice rules that will make people drink!");
        games.add(game);

        game = new Game();
        game.setGame("Never Have I Ever");
        game.setDescription("To play Never Have I Ever, players take must take turns giving statements starting with “Never have I ever…” and add an action that they have never done before.");
        games.add(game);

        game = new Game();
        game.setGame("Bite the Bag");
        game.setDescription("Put an empty brown paper bag open on the floor and have the players form a circle around it. Without letting anything except your feet touch the floor, players take turns bending down and biting the bag only using their mouth and not making use of their hands.\n" +
                "\n" +
                "After all the players have bitten the bag at least once, it’s time for round two! Cut off one inch off the top of the bag and all the players bite the bag again. This goes on until there is nothing left of the bag.\n" +
                "\n" +
                "The lower the paper bag gets, the harder it will be for players to go down and bite it while not using their hands. If a player fails to pick up the bag with their mouth and bite it, he/she must take a drink and start the game over!");
        games.add(game);

        game = new Game();
        game.setGame("The Alphabet Game");
        game.setDescription("In this fun drinking game, each player takes turns to recite words that begin with each letter of the alphabet. However, all the words must have a theme.\n" +
                "\n" +
                "For example (if the theme is movies)\n" +
                "\n" +
                "The first person could say A Quiet Place\n" +
                "\n" +
                "The second person could say Back to the Future\n" +
                "\n" +
                "The third person could say Captain America: Civil War\n" +
                "\n" +
                "This goes on until there is a player who can’t give a word, and he/she must take a drink. The game then starts over with a new theme or category.");
        games.add(game);

        game = new Game();
        game.setGame("Medusa Game");
        game.setDescription("Each player will begin the game with their head on the table or with eyes closed. One player then starts counting backward 3, 2, 1.\n" +
                "\n" +
                "After the count, all the players look up at another player. If a player is looking at another player and that other player is also looking at them, both players need to shout “Medusa!” and must take a shot of alcohol.\n" +
                "\n" +
                "If a player doesn’t have anyone looking at him/her, they can skip taking the shot.\n" +
                "\n" +
                "Once everyone has taken shots, the players repeat the same process and the game continues until all the shots are gone.");
        games.add(game);

        game = new Game();
        game.setGame("UNO as a Drinking Game");
        game.setDescription("You play UNO as you normally would, but there are additional rules that will make people drink throughout the game.\n" +
                "\n" +
                "Here are some rules when playing UNO as a drinking game.\n" +
                "\n" +
                "Drink when you draw the same color as the player in front of you.\n" +
                "Drink when you draw the same number as the player in front of you.\n" +
                "If you draw a suspension card, you may choose a player to drink.\n" +
                "In a draw-two card, the player who next draws a card must drink. (If your Draw Two card is the same color as the previous card, you must drink as well.)\n" +
                "For a plus-four-card, everybody has to drink.");
        games.add(game);

        game = new Game();
        game.setGame("21 Drinking Game");
        game.setDescription("First, gather the players and make sure that you have enough alcohol for several rounds. The players must sit down in a circle and everyone should be able to look into each other’s eyes. The more players, the better!\n" +
                "\n" +
                "Next, the youngest player starts to count. All the player should count aloud in a clockwise direction from 1 to 21. Every player says one number, and the player who says the number 21 drinks and makes up a rule that can be added to the next round.");
        games.add(game);

        game = new Game();
        game.setGame("Mr. Freeze");
        game.setDescription("For this game, one person will be appointed as Mr. Freeze at the start of a party. During the party, this person can randomly choose to “freeze” at any time, and everyone that notices must stop and do the same. The last person to follow the freeze takes a shot.\n" +
                "\n" +
                "The first person that noticed the freeze is now the new Mr. Freeze, and everyone can play again. Be careful! You never know when the new Mr. Freeze will become “frozen”!");
        games.add(game);

        game = new Game();
        game.setGame("Cup Swap");
        game.setDescription("Prepare two cups and a spoon for each player. Fill one cup with an alcoholic beverage and leave the other cup empty.\n" +
                "\n" +
                "When the game starts, each player has exactly 1 minute to pour as much of the drink as they can into the empty cup using only a spoon. Once the minute is over, the players must drink the remainder of the original cup. You better be handy with that spoon!");
        games.add(game);

        return games;
    }

    /**
     * Closes the navigation drawer if it is open.
     * Otherwise uses the parent class function.
     */
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Starts the activity of the selected menuItem from the navigation drawer.
     *
     * @param menuItem
     * @return boolean
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Intent homeIntent = new Intent(GameInstructionsActivity.this, DashboardActivity.class);
                startActivity(homeIntent);
                break;
            case R.id.nav_profile:
                Intent profileIntent = new Intent(GameInstructionsActivity.this, ProfileActivity.class);
                startActivity(profileIntent);
                break;
            case R.id.nav_timer:
                Intent clockIntent = new Intent(GameInstructionsActivity.this, ClockActivity.class);
                startActivity(clockIntent);
                break;
            case R.id.nav_checklist:
                Intent checklistIntent = new Intent(GameInstructionsActivity.this, ChecklistActivity.class);
                startActivity(checklistIntent);
                break;
            case R.id.nav_counter:
                Intent counterIntent = new Intent(GameInstructionsActivity.this, DrinksCountActivity.class);
                startActivity(counterIntent);
                break;
            case R.id.nav_games:
                break;
            case R.id.nav_drinkaware:
                Intent drinkawareIntent = new Intent(GameInstructionsActivity.this, DrinkawareActivity.class);
                startActivity(drinkawareIntent);
                break;
            case R.id.nav_logout:
                Intent logoutIntent = new Intent(GameInstructionsActivity.this, MainActivity.class);
                logoutIntent.setFlags(logoutIntent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(logoutIntent);
                FirebaseAuth.getInstance().signOut();
                break;
            case R.id.nav_map:
                Intent mapsIntent = new Intent(GameInstructionsActivity.this, MapsActivity.class);
                startActivity(mapsIntent);
                break;
        }
        return true;
    }
}
