package deck;

import cards.Card;
import java.util.*;

public class Deck {
    private Stack<Card> drawPile;
    private List<Card> discardPile;
    private Stack<Card> tacticCards;
    private List<Card> drawnCards;

    public Deck(List<Card> cards, List<Card> TacticCards) {
        Collections.shuffle(cards); // Shuffle once
        this.drawPile = new Stack<>();
        this.drawPile.addAll(cards);
        this.tacticCards = new Stack<>();
        this.tacticCards.addAll(TacticCards);
        this.discardPile = new ArrayList<>();
        this.drawnCards = new ArrayList<>();
    }

    public boolean isEmpty() {
        return drawPile.isEmpty();
    }

    public int size() {
        return drawPile.size();
    }

    public Card draw() {
        if (isEmpty()) {
            System.out.println("⚠️ Deck is empty.");
            return null;
        }
        return drawPile.pop();
    }

    public List<Card> draw(int count) {
        List<Card> drawn = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Card c = draw();
            if (c != null) {
                drawn.add(c);
            } else {
                break;
            }
        }
        return drawn;
    }

    public void discard(Card c) {
        discardPile.add(c);
    }

    public void discardAll(List<Card> cards) {
        discardPile.addAll(cards);
    }

    public void returnToDrawPile(List<Card> cards) {
        drawPile.addAll(cards);
    }
    public void drawTable(List<Card> cards) {
        List<Card> drawn = draw(5);
        drawnCards.addAll(drawn);
    }
    public List<Card> getDrawnCards() {
        return new ArrayList<>(drawnCards);
    }
    public void discardTable(List<Card> cards) {
        discardAll(cards);
        drawnCards.removeAll(cards);
    }

    public void shuffleBackDiscards() {
        Collections.shuffle(discardPile);
        drawPile.addAll(discardPile);
        discardPile.clear();
    }
}