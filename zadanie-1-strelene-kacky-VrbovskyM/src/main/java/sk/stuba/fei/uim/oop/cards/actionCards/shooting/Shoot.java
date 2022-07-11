package sk.stuba.fei.uim.oop.cards.actionCards.shooting;

import java.util.LinkedList;
import java.util.Objects;

import sk.stuba.fei.uim.oop.cards.actionCards.ActionCard;
import sk.stuba.fei.uim.oop.cards.duckAndWater.DuckAndWater;
import sk.stuba.fei.uim.oop.player.Player;

public class Shoot extends ActionCard {
    protected LinkedList<Player> players;
    protected LinkedList<ActionCard> actionCardsDeck;

    public Shoot(LinkedList<Player> players, LinkedList<ActionCard> actionCardsDeck, String cardName) {
        super(cardName);
        this.players = players;
        this.actionCardsDeck = actionCardsDeck;
    }

    @Override
    public boolean use( LinkedList<DuckAndWater> duckAndWaterDeck, LinkedList<Boolean> aimBoard, 
                        LinkedList<DuckAndWater> duckBoard, Player currentPlayer) {
                if(!checkCanShoot(aimBoard)) {
                    if (checkAllShootCards(currentPlayer)) {
                        System.out.println("You cant play any card, draw a new one");
                        return true;
                    }
                    System.out.println("No available field to SHOOT at, choose different card to play");
                    return false;
                }
                int target;
                do {    // do, until you select available field to shoot at.
                    target = getInput();
                    if(!aimBoard.get(target)) {
                        System.out.println("That field is not AIMED at, choose other one!");
                    }
                } while(!aimBoard.get(target));
                killTarget(players, duckBoard, aimBoard, duckAndWaterDeck, target);
                return true;
    } 
    
    public boolean checkCanShoot(LinkedList<Boolean> aimBoard) {
        for (Boolean aBoolean : aimBoard) {
            if (aBoolean) return true; // returns true if at least one field can be shot at
        }
        return false;    //returns false if you can't play shoot card
    }

    public boolean checkAllShootCards(Player currentPlayer) {
        for (int i = 0; i < 3; i++) {
            if(!Objects.equals(currentPlayer.getPlayersCard(i).getCardName(), "Shoot")){
                return false;
            }
        }
        return true;
    }

    public void killTarget(LinkedList<Player> players, LinkedList<DuckAndWater> duckBoard, LinkedList<Boolean> aimBoard, 
            LinkedList<DuckAndWater> duckAndWaterDeck, int target) {
        if(!Objects.equals(duckBoard.get(target).getOwner(), "Water")) {
            for(Player player: players){    // Decrement lives to player who lost duck
                if(player.getPlayerID() == duckBoard.get(target).getDuckID()) {
                    player.decrementLives();
                    checkIfDead(actionCardsDeck, player);
                    break;
                }
            }
            duckBoard.remove(target);
            duckBoard.add(duckAndWaterDeck.pollFirst());   
        }
        aimBoard.set(target, false); 
    }

    public void checkIfDead(LinkedList<ActionCard> actionCardDeck, Player targetedPlayer) {
        if(targetedPlayer.getLives() == 0) {
            System.out.println("\nPlayer " + targetedPlayer.getName() + " has been eliminated\n"
                                + "His cards will be returned to the deck");
            for (int i = 0; i < 3; i++) {
                actionCardDeck.addLast(targetedPlayer.getPlayersCard(i));
            }
        }
     }
}
