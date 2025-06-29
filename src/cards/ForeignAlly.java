package cards;


public class ForeignAlly extends Card {
    private String description;
    private int identifier; // Unique identifier for the card
    public ForeignAlly(String name, String description, int identifier) {
        super(name);
        this.description = description;
        this.identifier = identifier;
    }
    public String getDescription() {
        return description;
    }
    public int getIdentifier() {
        return identifier;
    }

}
