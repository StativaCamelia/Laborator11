package laborator11.demo.gameComponents;

public class Player {
    String username = new String();
    String actualGame = new String();
    boolean inAGame = false;

    public Player(){

    }

    public boolean isInAGame() {
        return inAGame;
    }

    public void setInAGame(boolean inAGame) {
        this.inAGame = inAGame;
    }

    public String getActualGame() {
        return actualGame;
    }

    public void setActualGame(String actualGame) {
        this.actualGame = actualGame;
    }

    public Player(String name){
        this.username = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return this.username;
    }
}
