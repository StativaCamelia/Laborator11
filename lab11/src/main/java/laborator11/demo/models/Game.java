package laborator11.demo.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class Game
{

    public String player1;
    public String player2;
    public String content;
    LocalDate date = LocalDate.now();
    public String winner;

    @Id
    public String id;

    public void setId(String id) { this.id = id; }

    public String getId() {
        return id;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public String getWinner() {
        return winner;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
