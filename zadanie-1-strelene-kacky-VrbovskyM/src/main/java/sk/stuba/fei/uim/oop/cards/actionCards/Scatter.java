package sk.stuba.fei.uim.oop.cards.actionCards;

import java.util.Collections;
import java.util.LinkedList;

import sk.stuba.fei.uim.oop.cards.duckAndWater.DuckAndWater;
import sk.stuba.fei.uim.oop.player.Player;

public class Scatter extends ActionCard{
    public Scatter() {
        super("Scatter");
    }

    @Override
    public boolean use( LinkedList<DuckAndWater> duckAndWaterDeck, LinkedList<Boolean> aimBoard, 
                        LinkedList<DuckAndWater> duckBoard, Player currentPlayer) {
                Collections.shuffle(duckBoard);
        return true;
    }
  
}
