package api;

import io.restassured.RestAssured;
import io.restassured.internal.http.HttpResponseException;
import io.restassured.response.Response;
import pattern.Board;
import pattern.Card;

import java.util.List;

public class TrelloApi {

    private final String key;
    private final String token;
    private final String baseUrl = "https://api.trello.com/1";
    public TrelloApi(String key,String token){
        this.key=key;
        this.token=token;
    }


    public Board createBoard(String boardName){
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .queryParam("name", boardName)
                .queryParam("key", key)
                .queryParam("token", token)
                .post(baseUrl + "/boards/");
        response.then().statusCode(200);
        System.out.println(response.getBody().asString());

        String boardId = response.jsonPath().getString("id");
        String boardNme= response.jsonPath().getString("name");
        return new Board(boardId,boardNme);
    }

    public String getFirstListIdOnBoard(String boardId){
        String firstListID="";
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .queryParam("key", key)
                .queryParam("token", token)
                .get(baseUrl + "/boards/"+boardId+"/lists");
        response.then().statusCode(200);
        return firstListID  = response.jsonPath().getString("[0].id");
    }

    public List<String> getCardIdsOnList(String listId) {
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .queryParam("key", key)
                .queryParam("token", token)
                .get(baseUrl + "/lists/" + listId + "/cards");
        response.then().statusCode(200);
        return response.jsonPath().getList("id");
    }
    public String getCardName(String cardId) {
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .queryParam("key", key)
                .queryParam("token", token)
                .get(baseUrl + "/cards/" + cardId );
        response.then().statusCode(200);
        return response.jsonPath().getString("name");
    }

    public Card createCard(String firstListID, String cardName){
        String  cardId;
        Response response = RestAssured.given()
                .header("Content-Type","application/json")
                .queryParam("idList", firstListID)
                .queryParam("name", cardName)
                .queryParam("key", key)
                .queryParam("token", token)
                .post(baseUrl + "/cards/");
        response.then().statusCode(200);
             cardId = response.jsonPath().getString("id");
            return new Card(firstListID, cardName,cardId);
    }
    public void updateCard(String cardId,String cardNewName){
        Response response = RestAssured.given()
                .header("Content-Type","application/json")
                .queryParam("name", cardNewName)
                .queryParam("key", key)
                .queryParam("token", token)
                .put(baseUrl + "/cards/"+cardId);
        response.then().statusCode(200);
    }
    public  void deleteCard(String cardId)
    {
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .queryParam("key", key)
                .queryParam("token", token)
                .delete(baseUrl+"/cards/"+cardId);
        response.then().statusCode(200);
    }
    public boolean  deleteBoard(String boardId)
    {
        boolean status=false;
        Response response= RestAssured.given()
                .header("Content-Type", "application/json")
                .queryParam("key",key)
                .queryParam("token", token)
                .delete(baseUrl+"/boards/"+boardId);
        return status= (response.getStatusCode() == 200) ? true : false;


    }


}
