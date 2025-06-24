package cards;

public class CorruptionCard extends Card {
    private int value;

    public CorruptionCard(int value) {
        super("Corruption (" + value + ")");
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Corruption: " + value;
    }
}