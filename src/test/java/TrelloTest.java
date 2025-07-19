import Page.BoardPage;
import Page.CardPage;
import api.TrelloApi;
import org.junit.jupiter.api.*;
import pattern.Board;
import pattern.Card;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TrelloTest {

    TrelloApi trelloApi;
    BoardPage  bordPage;
    CardPage cardPage;
    Card firstCard,secondCard;
     Board board;
     String listID;
    private final String token = System.getenv("TRELLO_TOKEN");
    private final String key = System.getenv("TRELLO_KEY");

    @BeforeAll
    public  void setup(){
        trelloApi=new TrelloApi(key,token);
        bordPage =   new BoardPage(trelloApi);
        cardPage = new CardPage(trelloApi);
        board = bordPage.createBoard("Test board Güncel");
        if (board == null || board.getId().isEmpty()) {
            throw new RuntimeException("Board oluşturulamadı!");
        }
        listID = trelloApi.getFirstListIdOnBoard(board.getId());

    }
    @Test
    public  void createCard(){
        firstCard= cardPage.createCard(listID,"CARD A");
        secondCard= cardPage.createCard(listID,"CARD B");
        Assertions.assertNotNull(firstCard,"İlk card oluşturuldu.");
        Assertions.assertNotNull(secondCard,"İkinci card oluşturuldu.");
        Assertions.assertFalse(firstCard.getCardId().isEmpty(),"İlk card oluşturulamadı.");
        Assertions.assertFalse(secondCard.getCardId().isEmpty(),"İkinci card oluşturulamadı.");
    }
    @Test
    public void RandomSelectedCartUpdate()
    {
        firstCard= cardPage.createCard(listID,"CARD A");
        secondCard= cardPage.createCard(listID,"CARD B");
        Card selectedCard= (Math.random() > 0.5) ? firstCard : secondCard;
        String selectedCardId=selectedCard.getCardId();
        String newName="Yeni card ismi";
        cardPage.updateCard(selectedCardId,newName);
        String updateName= cardPage.getCardName(selectedCardId);
        Assertions.assertEquals(newName,updateName,"Card ismi başarılı güncellendi.");

    }
    @AfterAll
    public void deleteBoard()
    {
        boolean deleted = bordPage.deleteBoard(board.getId());
        if (!deleted) {
            throw new RuntimeException("Board silenemedi!");
        }

    }
  @AfterEach
    public void cleanCards()
    {
        List<String> cardIds = cardPage.getCardIdsOnList(listID);
        for (String cardId : cardIds) {
            cardPage.deleteCard(cardId);
        }
        List<String> cards = cardPage.getCardIdsOnList(listID);
        Assertions.assertTrue(cards.isEmpty(),"Tüm kartlar silinemedi"); //mesaj sadece test fail olursa görünür.
    }


}
