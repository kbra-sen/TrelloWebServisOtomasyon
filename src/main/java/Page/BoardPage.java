package Page;

import api.TrelloApi;
import pattern.Board;

public class BoardPage {

    TrelloApi trelloApi ;
    public  BoardPage(TrelloApi trelloApi){
        this.trelloApi = trelloApi;

    }

    public Board createBoard( String name)
    {
        return trelloApi.createBoard(name);

    }
    public  boolean deleteBoard(String boardId){
        return trelloApi.deleteBoard(boardId);
    }

}
