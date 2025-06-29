package cards;

public class InfluenceCard extends Card {
    private int value;
    private int identifier;

    public InfluenceCard(int value, int identifier) {
        super("Influence (" + value + ")");
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    public int getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return "Influence: " + value;
    }
}