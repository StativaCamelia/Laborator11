package laborator11.demo.services;

import laborator11.demo.gameComponents.Game;
import laborator11.demo.gameComponents.Player;

import java.util.ArrayList;
import java.util.List;

import static sun.audio.AudioPlayer.player;

public class GameService {

    List<Game> games = new ArrayList<>();
    List<Player> players = new ArrayList<>();


    public List<Game> getGame() {
        return games;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public String moveController(String playerId , String gameId, String username, int x, int y){
        String raspuns;
        Player player = new Player();
        for(Player playerL: players)
            if(playerL.getUsername() == playerId)
            {
                player = playerL;
                break;
            }
        Game actualGame = new Game();
        for(Game game : games){
            if(game.getId() == gameId)
            {
                actualGame = game;
                break;
            }
        }
        int winner;
            if(!actualGame.isGameEnd()) {
                if (actualGame.getTurn() == 1) {
                    if (username.equals(actualGame.getPlayers().get(0).getUsername())) {
                        raspuns = actualGame.getGameBoard().addPiece(1, x, y);
                        if (raspuns.startsWith("[OK]")) {
                            winner = actualGame.checkWinner();
                            if (winner != 0) {
                                actualGame.setGameEnd(true);
                                return "A CASTIGAT JOCUL: " + winner;

                            }
                            actualGame.setTurn(2);
                        }
                        System.out.println(actualGame.getGameBoard().toString());
                        return raspuns;
                    } else {
                        return "NU este randul tau";
                    }
                } else {
                    if (username.equals(actualGame.getPlayers().get(1).getUsername())) {
                        raspuns = actualGame.getGameBoard().addPiece(2, x, y);
                        if (raspuns.startsWith("[OK]")) {
                            winner = actualGame.checkWinner();
                            if (winner != 0) {
                                player.setInAGame(false);
                                actualGame.setGameEnd(true);
                                return "ATI CASTIGAT JOCUL: " + winner;

                            }
                            actualGame.setTurn(1);
                        }
                        System.out.println(actualGame.getGameBoard().toString());
                        return raspuns;
                    } else {
                        return "Nu este randul tau";
                    }
                }
            }
            else {
                player.setInAGame(false);
                return "JOCUL A FOST CASTIGAT DE ADVERSAR";
            }
    }


    public String createController(String username, String id){
        Player player = new Player(username);
        Game newGame = new Game(player, id);
        this.games.add(newGame);
        return "Un nou joc a fost creat asteptati pana este gasit un oponent... ";
    }


    /**
     * Verifica in lista de jocuri daca exista un joc care are momentan doar un player, daca exista un astfel de joc adauga noul jucator la acest joc
     * si il avertizeaza pe celalalt jucator ca poate incepe sa joace
     * @param username
     * @return
     */

    public String joinController( String username){

            boolean succes = false;
            String otherPlayer = new String();
            Player player = new Player(username);
            for (Game game : games) {
                otherPlayer = game.getPlayers().get(0).getUsername();
                if (!otherPlayer.equals(player.getUsername())) {
                    game.addPlayer(player);
                    games.remove(game);
                    succes = true;
                    for (Player client: players) {
                        if (client.getUsername().equals(otherPlayer)) {
                            client.setInAGame(true);
                            client.setActualGame(game.getId());
                        }
                        if (client.getUsername().equals(player.getUsername())) {
                            client.setInAGame(true);
                            client.setActualGame(game.getId());
                        }
                    }
                    break;
                }
            }
            if (succes) {

                return "[GAME]Ati inceput un joc. Alaturi de jucatorul " + otherPlayer + " veti fi numarul 2 pe tabla:";
            } else {
                return "Nu exista nici un joc la care puteti participa in acest moment";
            }
    }

}
