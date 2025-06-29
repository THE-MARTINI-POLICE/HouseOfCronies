package cards;

public class InfluenceCard extends Card {
    private int value;
    private int identifier;

    public InfluenceCard(String name, int value, int identifier) {
        super(name);
        this.value = value;
        this.identifier = identifier;
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