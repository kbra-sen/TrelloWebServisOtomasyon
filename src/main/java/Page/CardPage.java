package Page;

import api.TrelloApi;
import pattern.Card;

import java.util.List;

public class CardPage {


    TrelloApi trelloApi;
    public CardPage(TrelloApi trelloApi){
      this.trelloApi= trelloApi ;

    }

    public Card createCard(String firstListID, String cardName)
    {
        return trelloApi.createCard( firstListID,  cardName);

    }
    public void updateCard(String cardId,String cardNewName)
    {
         trelloApi.updateCard( cardId,  cardNewName);
    }
    public  void deleteCard(String cardId)
    {
        trelloApi.deleteCard(cardId);
    }
    public List<String> getCardIdsOnList(String listId)
    {
       return trelloApi.getCardIdsOnList(listId);
    }
    public  String getCardName(String cardId)
    {
        return trelloApi.getCardName(cardId);
    }

}
