package sk.stuba.fei.uim.oop.cards.actionCards.shooting;

import java.util.LinkedList;

import sk.stuba.fei.uim.oop.cards.actionCards.ActionCard;
import sk.stuba.fei.uim.oop.cards.duckAndWater.DuckAndWater;
import sk.stuba.fei.uim.oop.player.Player;

public class WildBill extends Shoot{
    public WildBill(LinkedList<Player> players,LinkedList<ActionCard> actionCardsDeck) {
        super(players, actionCardsDeck, "Wild Bill");
    }
    
    @Override
    public boolean use( LinkedList<DuckAndWater> duckAndWaterDeck, LinkedList<Boolean> aimBoard, 
                        LinkedList<DuckAndWater> duckBoard, Player currentPlayer) {
                killTarget(players, duckBoard, aimBoard, duckAndWaterDeck, getInput());
        return true;
    }
  
}
