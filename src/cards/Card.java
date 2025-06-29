package cards;

public abstract class Card {
    protected static String name;

    public Card(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Optional override for debugging
    @Override
    public String toString() {
        return name;
    }
}