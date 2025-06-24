package utils;

import cards.*;
import cards.tactics.*;


import java.io.*;
import java.util.*;

public class DeckLoader {

    public static List<Card> loadMainDeck(String filePath) {
        List<Card> deck = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                String type = parts[0].trim();
                String name = parts[1].trim();
                int value = parts[2].isEmpty() ? 0 : Integer.parseInt(parts[2].trim());
                String specialtiesStr = parts[3].trim();
                boolean isForeign = Boolean.parseBoolean(parts[4].trim());
                boolean isPresOnly = Boolean.parseBoolean(parts[5].trim());
                String ability = parts.length > 6 ? parts[6].trim() : "";

                switch (type.toLowerCase()) {
                    case "influence":
                        deck.add(new InfluenceCard(value));
                        break;
                    case "corruption":
                        deck.add(new CorruptionCard(value));
                        break;
                    case "crony":
                        Set<String> specialties = new HashSet<>();
                        if (!specialtiesStr.isEmpty()) {
                            specialties.addAll(Arrays.asList(specialtiesStr.split(";")));
                        }
                        deck.add(new CronyCard(name, value, specialties, isForeign, isPresOnly, ability));
                        break;
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to load main deck: " + e.getMessage());
        }

        return deck;
    }

    public static List<TacticCard> loadTacticsDeck(String filePath) {
        List<TacticCard> tactics = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0].trim();

                // Hardcoded for now
                switch (name.toLowerCase()) {
                    case "hawks investigation":
                        tactics.add(new HawksInvestigation());
                        break;
                    // add more tactics here later
                    default:
                        System.out.println("Unknown tactic: " + name);
                        break;
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to load tactics deck: " + e.getMessage());
        }

        return tactics;
    }
}
