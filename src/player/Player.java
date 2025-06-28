package player;

import cards.*;
import cards.tactics.TacticCard;

import java.util.*;

public class Player {
    private String name;
    private List<Card> hand = new ArrayList<>();
    private List<TacticCard> tactics = new ArrayList<>();
    private Map<String, CronyCard> cabinetPositions = new HashMap<>();
    private CronyCard foreignAlly = null;

    private static final int MAX_CABINET_SIZE = 6;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public List<TacticCard> getTactics() {
        return tactics;
    }

    public Map<String, CronyCard> getCabinetPositions() {
        return cabinetPositions;
    }

    public CronyCard getForeignAlly() {
        return foreignAlly;
    }

    public boolean addToCabinet(CronyCard crony) {
        if (crony.isForeignAlly()) {
            if (foreignAlly != null) {
                System.out.println("You already have a foreign ally.");
                return false;
            }
            foreignAlly = crony;
            System.out.println(name + " added foreign ally: " + crony.getName());
            return true;
        }

        if (cabinetPositions.size() >= MAX_CABINET_SIZE) {
            System.out.println("Cabinet is full.");
            return false;
        }

        if (crony.isPresidentOnly()) {
            if (!cabinetPositions.containsKey("President")) {
                cabinetPositions.put("President", crony);
                System.out.println(name + " added PRESIDENT: " + crony.getName());
                return true;
            } else if (!cabinetPositions.containsKey("Vice President")) {
                cabinetPositions.put("Vice President", crony);
                System.out.println(name + " added VICE PRESIDENT: " + crony.getName());
                return true;
            } else {
                System.out.println("Both Pres and VP are filled.");
                return false;
            }
        }

        

        // Fallback: fill Pres or VP if not already
        if (!cabinetPositions.containsKey("President")) {
            cabinetPositions.put("President", crony);
            System.out.println(name + " assigned " + crony.getName() + " as PRESIDENT");
            return true;
        }
        if (!cabinetPositions.containsKey("Vice President")) {
            cabinetPositions.put("Vice President", crony);
            System.out.println(name + " assigned " + crony.getName() + " as VICE PRESIDENT");
            return true;
        }

        System.out.println("No valid cabinet slot for " + crony.getName());
        return false;
    }

    public CronyCard removeCabinetMember(String position) {
        return cabinetPositions.remove(position);
    }

    public CronyCard removeFirstCabinetMember() {
        if (cabinetPositions.isEmpty()) return null;
        String firstKey = cabinetPositions.keySet().iterator().next();
        return cabinetPositions.remove(firstKey);
    }

    public void drawCard(Card c) {
        hand.add(c);
    }

    public void showHand() {
        System.out.println(name + "'s hand:");
        for (int i = 0; i < hand.size(); i++) {
            System.out.println("[" + i + "] " + hand.get(i));
        }
    }

    public List<Card> selectBidCards() {
        List<Card> bid = new ArrayList<>();
        for (Card c : hand) {
            if (c instanceof InfluenceCard || c instanceof CorruptionCard) {
                bid.add(c);
                if (bid.size() >= 2) break;
            }
        }
        return bid;
    }

    public int totalVotes() {
        return cabinetPositions.values().stream().mapToInt(CronyCard::getVoteValue).sum();
    }
}