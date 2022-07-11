package sk.stuba.fei.uim.oop.cards.actionCards;

import java.util.LinkedList;

import sk.stuba.fei.uim.oop.cards.duckAndWater.DuckAndWater;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;

public abstract class ActionCard {
    protected final String cardName;
    protected ActionCard(String name) {
        this.cardName = name;
    }
    public String getCardName(){ 
        return cardName;
    }

    public abstract boolean use(LinkedList<DuckAndWater> duckAndWaterDeck,
                                LinkedList<Boolean> aimBoard,
                                LinkedList<DuckAndWater> duckBoard,
                                Player currentPlayer);


    
    protected int getInput() {
        int target;
        do {
            target = (KeyboardInput.readInt("Choose field")-1);
            } while(target < 0 || target > 5);
        return target;
    }
}
