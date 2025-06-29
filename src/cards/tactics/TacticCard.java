package cards.tactics;

import cards.Card;

public class TacticCard extends Card {
    private int id;

    public TacticCard(String name, int id) {
        super(name);
        this.id = id;
    }
    public int getId() {
        return id;
    }

}
