package sk.stuba.fei.uim.oop.duckHunt;

import java.util.Collections;
import java.util.LinkedList;
import sk.stuba.fei.uim.oop.cards.actionCards.*;
import sk.stuba.fei.uim.oop.cards.actionCards.shooting.*;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;
import sk.stuba.fei.uim.oop.cards.duckAndWater.*;

public class DuckHunt {
    private final LinkedList<Player> players;
    private LinkedList<ActionCard> actionCardsDeck;
    private LinkedList<DuckAndWater> duckAndWaterDeck;
    private LinkedList<DuckAndWater> duckBoard;
    private LinkedList<Boolean> aimBoard;
    
    public DuckHunt() {
         this.players = new LinkedList<>();
        System.out.println("---------- DUCK HUNT!!! (light version) ----------\n");
        int playerCount = 0;
        while(playerCount < 2 || playerCount > 6) {
            playerCount = KeyboardInput.readInt("Enter number of players (2-6)");
        }
        this.createActionCardDeck();
        for (int i = 0; i < playerCount; i++) {
            Player player = new Player(KeyboardInput.readString("Enter a name for player: "+(i+1)), i);
            this.players.add(player);
            for (int j = 0; j < 3; j++) {
                this.players.get(i).drawCard(actionCardsDeck);
            }
        }
        this.createDuckAndWaterDeck();
        this.createBoard();
        this.startGame();
    }
    
    public void startGame() {
        int indexOfCard;
        boolean cardWasPlayed;
        System.out.println("\n---------- LET THE HUNT BEGIN! ----------");
        for (int round = 0; alivePlayers() > 1; round++) {
            System.out.println("\n---------- ROUND " + (round + 1) + " ----------");
            for (int i = 0; i < players.size() && alivePlayers() > 1; i++) {
                Player currentPlayer = players.get(i);
                if(currentPlayer.getLives() == 0){
                    continue;
                } 
                System.out.println("\n\u001B[32m " + currentPlayer.getName() + "'s turn \u001B[0m\n");
                this.printBoard();
                this.printCards(currentPlayer);
                do {    //do, until card is played.
                    do {
                    indexOfCard = (KeyboardInput.readInt("Choose card")-1);
                    } while(indexOfCard < 0 || indexOfCard > 2);
                    cardWasPlayed = currentPlayer.getPlayersCard(indexOfCard).use(this.duckAndWaterDeck, 
                                                            this.aimBoard, this.duckBoard, currentPlayer);
                } while(!cardWasPlayed);
                currentPlayer.switchCard(actionCardsDeck, indexOfCard);
            }
        }
        this.printWinner();

    }
    
    public void createBoard() {
        this.duckBoard = new LinkedList<>();
        this.aimBoard = new LinkedList<>();
        for (int i = 0; i < 6; i++) {
            this.duckBoard.add(duckAndWaterDeck.pollFirst());
            this.aimBoard.add(false);
        }

    }

    public void createDuckAndWaterDeck() {
        this.duckAndWaterDeck = new LinkedList<>();
        for (Player player : this.players) {  // Create Ducks
            for (int j = 1; j <= 5; j++) {
                Duck duck = new Duck(player.getName(), player.getPlayerID());
                duckAndWaterDeck.add(duck);
            }
        }
        for (int i = 0; i < 5; i++) {   // Create Water
            Water water = new Water();
            duckAndWaterDeck.add(water);
        }
        Collections.shuffle(duckAndWaterDeck);
    }

    public void createActionCardDeck() {
        this.actionCardsDeck = new LinkedList<>();
        for (int i = 0; i < 10; i++) {  // AIM CARDS
            Aim aim = new Aim();
            actionCardsDeck.add(aim);
         }
        for (int i = 0; i < 12; i++) {  // SHOOT CARDS
            Shoot shoot = new Shoot(players, actionCardsDeck, "Shoot");
            actionCardsDeck.add(shoot);
        } 
        for (int i = 0; i < 2; i++) {   // WILD BILL CARDS
            WildBill wildBill = new WildBill(players, actionCardsDeck);
            actionCardsDeck.add(wildBill); 
        }
        for (int i = 0; i < 6; i++) {   // DUCK MARCH CARDS
            DuckMarch duckMarch = new DuckMarch();
            actionCardsDeck.add(duckMarch);
        } 
        TurboDuck turboDuck = new TurboDuck();  // TURBO DUCK CARD
        actionCardsDeck.add(turboDuck);

        for (int i = 0; i < 2; i++) {   // SCATTER CARDS
            Scatter scatter = new Scatter();
            actionCardsDeck.add(scatter);
        }
        DuckDance duckDance = new DuckDance();  // DUCK DANCE CARD
        actionCardsDeck.add(duckDance);
        Collections.shuffle(actionCardsDeck);
    }
    
    public void printBoard() {
        for (int i = 0; i < 6; i++) {
             System.out.print((i+1) + ". ");
             if(this.aimBoard.get(i)){
                 System.out.print("  AIMED  ");
             }
             else System.out.print("NOT AIMED");
             System.out.print(" --- " + this.duckBoard.get(i).getOwner() + "\n");
        } 
    }
     
    public void printCards(Player player) {
        System.out.println("\nYour cards:");
        for (int i = 0; i < 3; i++) {
            System.out.println((i+1) + ". " + player.getPlayersCard(i).getCardName());
        }
    }
    
    public int alivePlayers() {
        int alive = 0;
        for (Player player : players) {
            if (player.getLives() > 0) {
                alive++;
            }
        }
        return alive;
    }

    public void printWinner() {
        for (Player player : players) {
            if(player.getLives() > 0) {
                System.out.println("\n---------- THE GAME HAS ENDED ----------\n"
                                    + player.getName() + " is the winner !!!");
            }
        }
    }
}

