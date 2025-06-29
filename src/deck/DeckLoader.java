package deck;
import cards.*;
import cards.tactics.TacticCard;

import java.util.*;
import java.io.*;


public class DeckLoader {
    public static List<Card> loadCronyDeck(String filePath){
        List<Card> cronydeck = new ArrayList<>();
        int idCounter = 1000;
        boolean isPresidentOnly = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line = reader.readLine();
            line = reader.readLine(); // Skip header line
            while (line != null) {
                String[] parts = line.split(",");
                String name = parts[0].trim();
                String  specialtiesRaw = parts[1].trim();

                if (specialtiesRaw =="Executive") {
                    isPresidentOnly = true;
                } else {
                    isPresidentOnly = false;
                }

                Set<String> specialties = new HashSet<>(Arrays.asList(specialtiesRaw.split(";")));

                int votes = Integer.parseInt(parts[2].trim());
                int cost = Integer.parseInt(parts[3].trim());
                int tacticPickup = Integer.parseInt(parts[4].trim());

                CronyCard crony = new CronyCard(name, votes, specialties, false, isPresidentOnly, cost, tacticPickup, idCounter++);
                cronydeck.add(crony);
                line = reader.readLine();
            }
        
        } catch (IOException e) {
            e.printStackTrace();
        }

    return cronydeck;
    }

    public static List<Card> loadMoneyDeck(String filePath) {
        List<Card> moneyDeck = new ArrayList<>();
        int idCounter = 3000;

        try (BufferedReader reader =new BufferedReader(new FileReader(filePath))){
            String line = reader.readLine();
            line = reader.readLine(); // Skip header line
            while (line != null){
                String[] parts = line.split(",");
                String type = parts[0].trim();
                String name = parts[1].trim();
                int value = Integer.parseInt(parts[2].trim());
                int quantity = Integer.parseInt(parts[3].trim());

                for (int i = 0; i < quantity; i++) {
                    if (type.equalsIgnoreCase("Influence")) {
                        moneyDeck.add(new InfluenceCard(name, value, idCounter++));
                    } else{
                        moneyDeck.add(new CorruptionCard(name, value, idCounter++));
                    }
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return moneyDeck;
    }
    public static List<Card> loadTacticDeck(String filePath) {
        List<Card> tacticDeck = new ArrayList<>();
        int idCounter = 4000;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            line = reader.readLine(); // Skip header line
            while (line != null) {
                String[] parts = line.split(",");
                String name = parts[0].trim();
                int quantity = Integer.parseInt(parts[1].trim());
                for (int i = 0; i < quantity; i++) {
                    tacticDeck.add(new TacticCard(name, idCounter++));
                }
                line = reader.readLine();
                
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return tacticDeck;
    }

    public static List<Card> loadForeignAllyDeck(String filePath) {
        List<Card> foreignAllyDeck = new ArrayList<>();
        int idCounter = 2000;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            line = reader.readLine(); // Skip header line
            while (line != null) {
                String[] parts = line.split(",");
                String name = parts[0].trim();
                String description = parts[1];

                foreignAllyDeck.add(new ForeignAlly(name, description, idCounter++));
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }          

        return foreignAllyDeck;
    }
    public static List<Card> buildUnsortedDeck(String cronyFilePath, String moneyFilePath, String foreignAllyFilePath) {
        List<Card> allCards = new ArrayList<>();
        allCards.addAll(loadCronyDeck(cronyFilePath));
        allCards.addAll(loadMoneyDeck(moneyFilePath));
        allCards.addAll(loadForeignAllyDeck(foreignAllyFilePath));
        return allCards;
    }
    public static List<Card> shuffleUnsortedDeck(String cronyFilePath, String moneyFilePath, String foreignAllyFilePath) {
        List<Card> allCards = buildUnsortedDeck(cronyFilePath, moneyFilePath, foreignAllyFilePath);
        Collections.shuffle(allCards);
        return allCards;
    }

    public static void main(String[] args) {
        // Example usage
        List<Card> shuffledDeck = shuffleUnsortedDeck("src/csv's/cronies.csv", "src/csv's/money_cards.csv", "src/csv's/foreign_allies.csv");
        System.out.println("Shuffled Deck: " + shuffledDeck);
    }
}
