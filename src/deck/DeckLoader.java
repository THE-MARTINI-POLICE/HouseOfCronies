package deck;
import cards.*;

import java.util.*;
import java.io.*;


public class DeckLoader {
    public static List<Card> loadCronyDeck(String filePath){
        List<Card> cronydeck = new ArrayList<>();
        int idCounter = 3000;
        boolean isPresidentOnly = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
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
            }
        
        } catch (IOException e) {
            e.printStackTrace();
        }

    return cronydeck;
    }

    public static List<Card> loadMoneyDeck(String filePath) {
        List<Card> moneyDeck = new ArrayList<>();
        int idCounter = 1000;

        try (BufferedReader reader =new BufferedReader(new FileReader(filePath))){
            String line = reader.readLine();
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return moneyDeck;
    }


}
