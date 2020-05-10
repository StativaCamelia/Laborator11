package laborator11.demo.controllers;

import laborator11.demo.exceptions.ResourceNotFound;
import laborator11.demo.models.Game;
import laborator11.demo.models.Player;
import laborator11.demo.repository.GameRepository;
import laborator11.demo.repository.PlayerRepository;
import laborator11.demo.services.GameService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/player")
public class PlayerController {
    private GameService gameService = new GameService();
    @Autowired
    private PlayerRepository repository;

    @Autowired
    private GameRepository gameRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Player> getAllPlayers() {
        return this.repository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Player createPlayer(@Valid @RequestBody Player player) {
        repository.save(player);
        laborator11.demo.gameComponents.Player playerGame = new laborator11.demo.gameComponents.Player(player.getId());
        gameService.getPlayers().add(playerGame);
        return player;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deletePlayer(@PathVariable String id) throws ResourceNotFound {
        if(repository.existsById(id)) {
            repository.delete(repository.findById(id).orElse(new Player()));
            return "Succesfully deleted";
        }
        else
            throw new ResourceNotFound("Player-ul nu exista");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Player updatePlayer(@PathVariable String id, @Valid @RequestBody Player player) {
        player.setId(id);
        repository.save(player);
        return player;
    }

    @RequestMapping(value = "/create/{id}", method = RequestMethod.GET)
    public String createGame(@PathVariable String id){
        Game game = new Game();
        game.setPlayer1(id);
        game.setId(id);
        gameRepository.save(game);
        return gameService.createController(id, game.getId());

    }

    @RequestMapping(value = "/join/{idGame}/{idPlayer}", method = RequestMethod.GET)
    public String joinGame(@PathVariable String idGame, @PathVariable String idPlayer) throws ResourceNotFound{
        Optional<Game> game = gameRepository.findById(idGame);
        game.ifPresent(game1 -> {if(game1.getPlayer2() == null)
                    game1.setPlayer2(idPlayer);
        else
            throw new ResourceNotFound("Nu se poate face join la acest joc");
        });
        return gameService.joinController(idPlayer);

    }

    @RequestMapping(value = "/move/{idGame}/{idPlayer}/{x}/{y}", method = RequestMethod.PUT)
    public String move(@PathVariable String idGame, @PathVariable String idPlayer, @PathVariable String x, @PathVariable String y){
        Optional<Player> player = repository.findById(idPlayer);
        Player currentPlayer = player.orElse(new Player());
            return gameService.moveController(idPlayer, idGame,currentPlayer.getName(), Integer.parseInt(x), Integer.parseInt(y));
    }


}