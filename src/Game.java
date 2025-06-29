import java.util.Stack;



import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

// Import necessary classes to shuffle the deck.
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Game {

    //Attributes
    private Stack<Card> deck = new Stack<>();
    @SuppressWarnings("unused")
    private Stack<Card> discardPile = new Stack<>(); //Discard pile for cards that are no longer in play
    private Card[] gameTable; //The five cards that are in play in the current round.
    

    //Constructor
    public Game() {
        initializeDeck();
        gameTable = new Card[5];
    }

    //Create the deck of cards.
    public void initializeDeck() {

        //To make code more readable.
        String two = "Influence/Corruption card with a value of 2.";
        String three = "Influence/Corruption card with a value of 3.";
        String four = "Influence/Corruption card with a value of 4.";
        String five = "Influence/Corruption card with a value of 5.";
        String six = "Influence/Corruption card with a value of 6.";
        String seven = "Influence/Corruption card with a value of 7.";
        
        //Insert Influence cards into the deck:
        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 6; j++) {
                if(i==0){
                    deck.push(new Card("Influence","Second Degree Influence","N/A",0,0,two,2));
                }else if(i==1){
                    deck.push(new Card("Influence","Third Degree Influence","N/A",0,0,three,3));
                }else if(i==2){
                    deck.push(new Card("Influence","Fourth Degree Influence","N/A",0,0,four,4));
                }else if(i==3){
                    deck.push(new Card("Influence","Fifth Degree Influence","N/A",0,0,five,5));
                }
            }
        }
       
        //Insert Corruption cards into the deck:
        deck.push(new Card("Corruption","Fourth Degree Corruption","N/A",0,0,four,4));
        for (int i = 0; i < 4; i++) {

             for (int j = 0; j < 3; j++) {
                if(i==0){
                    deck.push(new Card("Corruption","Fourth Degree Corruption","N/A",0,0,four,4));
                }else if(i==1){
                    deck.push(new Card("Corruption","Fifth Degree Corruption","N/A",0,0,five,5));
                }else if(i==2){
                    deck.push(new Card("Corruption","Sixth Degree Corruption","N/A",0,0,six,6));
                }else if(i==3){
                    deck.push(new Card("Corruption","Seventh Degree Corruption","N/A",0,0,seven,7));
                }
            }
        }
       
        //Insert Tactic cards into the deck:
        for (int i = 0; i < 4; i++) {
            deck.push(new Card("Tactic","Cabinet Reshuffle","N/A",0,0,"Insert Description",0));
            deck.push(new Card("Tactic","Floor Crossing","N/A",0,0,"Insert Description",0));
            deck.push(new Card("Tactic","Saxonworld Shebeen","N/A",0,0,"Insert Description",0));
            deck.push(new Card("Tactic","Diplomatic Incident","N/A",0,0,"Insert Description",0));
            deck.push(new Card("Tactic","Point of Order","N/A",0,0,"Insert Description",0));
            deck.push(new Card("Tactic","Hawks Investigation","N/A",0,0,"Insert Description",0));
        }

        //Insert Cronie cards:
        int numCronies = 28;
        File file = new File("ListOfCronies.txt");
        try{
            Scanner scanner = new Scanner(file);
            for(int i=0; i<numCronies ;i++){
                String data = scanner.nextLine();
                String[] parts = data.split("/"); // Split the line by "/"

                //Edge case: If TacticsDrawn is "-", set it to 0
                if(parts[4].equals("-")) {
                    parts[4] = "0"; // If TacticsDrawn is "-", set it to 0
                }

                //Name/Position/Votes/Cost/TacticsDrawn
                deck.push(new Card("Cronie", parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[4]), "N/A", Integer.parseInt(parts[3])));
            }

            scanner.close();
        }catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    //Shuffle the deck of cards.
    public void shuffleDeck() {
        //The shuffle function, shuffles lists. So the stack needs to be converted to a list first.
        
        for(int i=0; i<2;i++){
            Card[] deckArray = deck.toArray(new Card[0]); //Convert the stack to an array.
            List<Card> deckList = Arrays.asList(deckArray); //Convert the array to a list.
            Collections.shuffle(deckList); //Shuffle the list.
            Card[] shuffledArray = deckList.toArray(new Card[0]); //Convert shuffled deck back to array.
            deck.clear(); //Clear the deck that has not been shuffled.

            for (Card element : shuffledArray) {
                deck.push(element);
            }
        }  
    }

    //Display the deck. For testing purposes only.
    public void displayDeck() {
        // Display the deck
        if (deck.isEmpty()) {
            System.out.println("The deck is empty.");
            return;
        }
        System.out.println("=====================CURRENT DECK=====================" + '\n' + "There are " + deck.size() + " cards in the deck."+ '\n');
        
        for (Card card : deck) {
            System.out.println(card.displayCard());
        }
    }

    public void setGameTable() {
        for(int i=0;i<5;i++){
            gameTable[i] = deck.pop();
        }
    }

    public void displayGameTable(){
        System.out.println("=====================CARDS IN PLAY=====================" + '\n');
        for(int i=0;i<5;i++){
            System.out.println(gameTable[i].displayCard());
        }
    }











    //Testing the class.
    public static void main(String[] args) {
        Game testGame = new Game(); //Creates a deck of cards.
        testGame.shuffleDeck(); //Shuffles the deck of cards.
        testGame.displayDeck(); //Display the deck of cards.
        testGame.setGameTable();
        testGame.displayGameTable();

    }

}
