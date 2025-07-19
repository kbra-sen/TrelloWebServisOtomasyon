package pattern;

public class Board {

    private String  id;
    private String boardName;
    public Board(String id, String boardName)
    {
        this.boardName=boardName;
        this.id=id;
    }

    public String getBoardName() {
        return boardName;
    }

    public String getId() {
        return id;
    }
}
