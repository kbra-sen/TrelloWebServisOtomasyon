package Page;

import api.TrelloApi;
import pattern.Board;

public class BoardPage {
    Board board;
    TrelloApi trelloApi ;
    public  BoardPage(TrelloApi TrelloApi){
        this.trelloApi = TrelloApi;

    }

    public Board createBoard( String name)
    {
        board=  trelloApi.createBoard(name);
        return  board;
    }
    public  boolean deleteBoard(String boardId){
        return trelloApi.deleteBoard(boardId);
    }

}
