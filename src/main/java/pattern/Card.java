package pattern;

public class Card {

    String firstListId;
    String cardId;
    String cardName;



    public Card(String firstListId, String cardName, String cardId) {
        this.firstListId= firstListId;
        this.cardName = cardName;
        this.cardId=cardId;

    }

    public String getCardName() {
        return cardName;
    }

    public String getFirstListId() {
        return firstListId;
    }

    public String getCardId() {
        return cardId;
    }
}
