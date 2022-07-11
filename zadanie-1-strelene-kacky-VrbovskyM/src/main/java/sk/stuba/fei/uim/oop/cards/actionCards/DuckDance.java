package sk.stuba.fei.uim.oop.cards.actionCards;

import java.util.Collections;
import java.util.LinkedList;

import sk.stuba.fei.uim.oop.cards.duckAndWater.DuckAndWater;
import sk.stuba.fei.uim.oop.player.Player;

public class DuckDance extends ActionCard{
    public DuckDance() {
        super("Duck Dance");
    }
    
    @Override
    public boolean use( LinkedList<DuckAndWater> duckAndWaterDeck, LinkedList<Boolean> aimBoard, 
                        LinkedList<DuckAndWater> duckBoard, Player currentPlayer) {
        duckAndWaterDeck.addAll(duckBoard);
        duckBoard.removeAll(duckBoard);
        Collections.shuffle(duckAndWaterDeck);
        for (int i = 0; i < 6; i++) {
            duckBoard.add(duckAndWaterDeck.pollFirst());
        }
        return true;
    }
}
