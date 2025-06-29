package cards;

import java.util.*;

public class CronyCard extends Card {

    private int voteValue;
    private Set<String> specialties; // e.g., "Finance", "Justice"
    private boolean isPresidentOnly;
    private int cost;
    private int tacticPickup;
    private int identifier; // Unique identifier for the 
    
    public CronyCard(String name, int voteValue, Set<String> specialties, 
        boolean isForeignAlly, boolean isPresidentOnly, int cost, int tacticPickup, int identifier) {
        
        super(name);
        this.voteValue = voteValue;
        this.specialties = specialties;
        this.isPresidentOnly = isPresidentOnly;
        this.cost = cost;
        this.tacticPickup = tacticPickup;
        this.identifier = identifier;
    }

    public int getVoteValue() {
        return voteValue;
    }
    public Set<String> getSpecialties() {
        return specialties;
    }
    public int getIdentifier() {
        return identifier;
    }

    public int getCost() {
        return cost;
    }
    public int getTacticPickup() {
        return tacticPickup;
    }


    public boolean isPresidentOnly() {
        return isPresidentOnly;
    }

    public boolean canFillPosition(String position) {
        
        if (position.equalsIgnoreCase("President") || position.equalsIgnoreCase("Vice President")) {
            return true;
        }
        return specialties.contains(position);
    }

    @Override
    public String toString() {
        return name + " [Votes: " + voteValue +
            ", Specialties: " + specialties +
            (isPresidentOnly ? ", PRES-ONLY" : "");
            
    }
}