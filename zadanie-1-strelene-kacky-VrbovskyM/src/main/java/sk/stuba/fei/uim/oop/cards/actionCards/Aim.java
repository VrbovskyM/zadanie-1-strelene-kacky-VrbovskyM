package sk.stuba.fei.uim.oop.cards.actionCards;

import java.util.LinkedList;
import java.util.Objects;

import sk.stuba.fei.uim.oop.cards.duckAndWater.DuckAndWater;
import sk.stuba.fei.uim.oop.player.Player;

public class Aim extends ActionCard {
    public Aim() {
        super("Aim");
    }
    
    @Override
    public boolean use( LinkedList<DuckAndWater> duckAndWaterDeck, LinkedList<Boolean> aimBoard, 
                        LinkedList<DuckAndWater> duckBoard, Player currentPlayer) {
                if(!checkFreeAim(aimBoard)) {
                   if (checkAllAimCards(currentPlayer)) {
                        System.out.println("You cant play any card, draw a new one");
                        return true;
                   }
                   System.out.println("No available field to AIM on, choose different card to play");
                   return false;
                }
                int target;
                do {    // do, until you select available field to aim at.
                    target = getInput();
                    if(aimBoard.get(target)) {
                        System.out.println("That field is already targeted, choose other one!");
                    }
                } while(aimBoard.get(target));
                aimBoard.set(target, true);
                return true;
    }
    public boolean checkFreeAim(LinkedList<Boolean> aimBoard) {
        for (Boolean aBoolean : aimBoard) {
            if (!aBoolean) return true; // returns true if at least one field be aimed at
        }
        return false;    //returns false if you can't play AIM n any field
    }
    
    public boolean checkAllAimCards(Player currentPlayer) {
        for (int i = 0; i < 3; i++) {
            if(!Objects.equals(currentPlayer.getPlayersCard(i).getCardName(), "Aim")){
                return false;
            }
        }
        return true;
    }
}
