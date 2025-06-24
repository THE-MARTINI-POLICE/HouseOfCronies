package cards;

public class InfluenceCard extends Card {
    private int value;

    public InfluenceCard(int value) {
        super("Influence (" + value + ")");
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Influence: " + value;
    }
}