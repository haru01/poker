package poker;

public class Card {
    private String suit;
    private String rank;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getRank() {
        return this.rank;
    }

    public String getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return "{" + this.suit + "," + this.rank + "}";
    }
}
