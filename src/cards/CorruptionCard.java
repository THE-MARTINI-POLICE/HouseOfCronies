package cards;

public class CorruptionCard extends Card {
    private int value;
    private int identifier;

    public CorruptionCard(String name, int value, int identifier) {
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
        return "Corruption value: " + value;
    }
}