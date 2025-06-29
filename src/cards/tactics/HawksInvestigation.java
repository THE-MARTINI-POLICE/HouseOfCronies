package cards.tactics;

import cards.Card;
import cards.CronyCard;
import player.Player;
import game.Game;

public class HawksInvestigation extends TacticCard {

    public HawksInvestigation(String name, int identifier) {
        super(name, identifier);
    }

    //@Override
    public boolean canPlay(Card lastPlayed, Player target, Game game) {
        return lastPlayed instanceof cards.CorruptionCard;
    }

    // Removed @Override because the superclass does not declare this method
    public void resolve(Player target, Game game) {
        if (target.getCabinetPositions().isEmpty()) {
            System.out.println(target.getName() + " has no cabinet cronies to remove.");
            return;
        }

        // Just remove first crony for now (you'll later replace this with selection logic)
        CronyCard removed = target.removeFirstCabinetMember();
        if (removed != null) {
            System.out.println("🦅 Hawks removed " + removed.getName() + " from " + target.getName() + "'s cabinet.");
        }
    }
}