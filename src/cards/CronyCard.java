package cards;

import java.util.Set;

import java.util.*;

public class CronyCard extends Card {
    private int voteValue;
    private Set<String> specialties; // e.g., "Finance", "Justice"
    private boolean isForeignAlly;
    private boolean isPresidentOnly;
    private int cost;
    private int tacticPickup;
    public CronyCard(String name, int voteValue, Set<String> specialties, boolean isForeignAlly, boolean isPresidentOnly, int cost, int tacticPickup) {
        super(name);
        this.voteValue = voteValue;
        this.specialties = specialties;
        this.isForeignAlly = isForeignAlly;
        this.isPresidentOnly = isPresidentOnly;
        this.cost = cost;
        this.tacticPickup = tacticPickup;
    }

    public int getVoteValue() {
        return voteValue;
    }

    public Set<String> getSpecialties() {
        return specialties;
    }

    public boolean isForeignAlly() {
        return isForeignAlly;
    }

    public boolean isPresidentOnly() {
        return isPresidentOnly;
    }

    public boolean canFillPosition(String position) {
        if (isForeignAlly) return false;
        if (position.equalsIgnoreCase("President") || position.equalsIgnoreCase("Vice President")) {
            return true;
        }
        return specialties.contains(position);
    }

    @Override
    public String toString() {
        return name + " [Votes: " + voteValue +
            ", Specialties: " + specialties +
            (isPresidentOnly ? ", PRES-ONLY" : "") +
            (isForeignAlly ? ", FOREIGN ALLY" : "") + "]";
    }
}