package cards;


public class ForeignAlly extends Card {
    private String description;

    public ForeignAlly(String name, String description) {
        super(name);
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
    
}
