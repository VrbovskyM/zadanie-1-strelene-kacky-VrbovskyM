package sk.stuba.fei.uim.oop.cards.actionCards;

import java.util.LinkedList;

import sk.stuba.fei.uim.oop.cards.duckAndWater.DuckAndWater;
import sk.stuba.fei.uim.oop.player.Player;

public class DuckMarch extends ActionCard{
    public DuckMarch() {
        super("Duck March");
    }
    
    @Override
    public boolean use( LinkedList<DuckAndWater> duckAndWaterDeck, LinkedList<Boolean> aimBoard, 
                        LinkedList<DuckAndWater> duckBoard, Player currentPlayer) {
        duckAndWaterDeck.addLast(duckBoard.pollFirst());
        duckBoard.addLast(duckAndWaterDeck.pollFirst());
        return true;
    }
  
}
