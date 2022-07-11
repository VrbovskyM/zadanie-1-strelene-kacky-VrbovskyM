package sk.stuba.fei.uim.oop.cards.actionCards;

import java.util.LinkedList;
import java.util.Objects;

import sk.stuba.fei.uim.oop.cards.duckAndWater.DuckAndWater;
import sk.stuba.fei.uim.oop.player.Player;

public class TurboDuck extends ActionCard{
    public TurboDuck() {
        super("Turbo Duck");
    }

    @Override
    public boolean use( LinkedList<DuckAndWater> duckAndWaterDeck, LinkedList<Boolean> aimBoard, 
                        LinkedList<DuckAndWater> duckBoard, Player currentPlayer) {
                int target;
                do {
                    target = getInput();
                } while(Objects.equals(duckBoard.get(target).getOwner(), "Water"));
                duckBoard.addFirst(duckBoard.get(target));
                duckBoard.remove(target+1);
                return true;
    }
 
}
