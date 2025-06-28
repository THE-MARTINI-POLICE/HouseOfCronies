package player;

import cards.*;

import java.util.ArrayList;
import java.util.List;


public class Hand {
    CronyCard president;
    CronyCard vicePresident;
    CronyCard finance;
    CronyCard justice;
    CronyCard service;
    CronyCard communication;
    CronyCard foreignAlly;
    List<InfluenceCard> influenceCards;
    List<CorruptionCard> corruptionCards;

    public Hand() {
        this.president = null;
        this.vicePresident = null;
        this.finance = null;
        this.justice = null;
        this.service = null;
        this.communication = null;
        this.foreignAlly = null;
        this.influenceCards = new ArrayList<>();
        this.corruptionCards = new ArrayList<>();
    }

}
