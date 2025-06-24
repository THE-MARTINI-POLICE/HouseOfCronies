package deck;

import cards.Card;
import java.util.*;

public class Deck {
    private Stack<Card> drawPile;
    private List<Card> discardPile;

    public Deck(List<Card> cards) {
        Collections.shuffle(cards); // Shuffle once
        this.drawPile = new Stack<>();
        this.drawPile.addAll(cards);
        this.discardPile = new ArrayList<>();
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

    public void shuffleBackDiscards() {
        Collections.shuffle(discardPile);
        drawPile.addAll(discardPile);
        discardPile.clear();
    }
}