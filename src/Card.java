public class Card {

    //Attributes
    private String type; 
    private String name; 
    private String position; //For Cronie cards.
    private int votes;  //For Cronie cards.
    private int numTaticCardsDrawn; //For Cronie cards.
    private String description; //For Tactic cards.
    private int cost; //For all cards.

    //Constructor
    public Card(String type, String name, String position, int votes, int numTaticCardsDrawn, String description, int cost) {
        this.type = type;
        this.name = name;
        this.position = position;
        this.votes = votes;
        this.numTaticCardsDrawn = numTaticCardsDrawn;
        this.description = description;
        this.cost = cost;
    }

    public String getInfo() {
        return "Type: " + type + ", Name: " + name + ", Position: "+ position + ", Votes: " + votes+ ", Description: " + description + ", Cost: " + cost + '\n';
    }
}
