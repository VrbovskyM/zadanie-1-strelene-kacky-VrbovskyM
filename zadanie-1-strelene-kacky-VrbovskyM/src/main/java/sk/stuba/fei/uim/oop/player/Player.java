package sk.stuba.fei.uim.oop.player;

import java.util.LinkedList;
import sk.stuba.fei.uim.oop.cards.actionCards.ActionCard;

public class Player {
    private final String name;
    private final int playerID;
    private int lives;
    private final LinkedList<ActionCard> playerCards;

    public Player(String name, int id) {
        this.name = name;
        this.playerID = id;
        this.lives = 5;
        this.playerCards = new LinkedList<>();
    }
    public String getName(){
        return this.name;
    }
    public int getPlayerID() {
        return this.playerID;
    }
    public int getLives(){
        return lives;
    }
    public void decrementLives(){
        this.lives--;
    }
    public ActionCard getPlayersCard(int indexOfCard) {
        return playerCards.get(indexOfCard);
        
    }
    public void drawCard(LinkedList<ActionCard> actionCardsDeck) {
        this.playerCards.add(actionCardsDeck.pollFirst()); // Draw card to player's hand
    }
    public void switchCard(LinkedList<ActionCard> actionCardDeck, int indexOfCard) {    
        actionCardDeck.addLast(playerCards.get(indexOfCard));  // Puts a card from player to the deck
        playerCards.remove(indexOfCard);    // Removes that card from players hand
        drawCard(actionCardDeck);
    }
}
