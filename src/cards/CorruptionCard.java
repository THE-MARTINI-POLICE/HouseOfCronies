package cards;

public class CorruptionCard extends Card {
    private int value;
    private int identifier;

    public CorruptionCard(int value, int identifier) {
        super("Corruption (" + value + ")");
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
        return "Corruption value: " + value;
    }
}