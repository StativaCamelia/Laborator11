package laborator11.demo.controllers;

import laborator11.demo.models.Game;
import laborator11.demo.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameRepository repository;

    @RequestMapping(method = RequestMethod.POST)
    public Game createGame(@Valid @RequestBody Game game) {
        repository.save(game);
        return game;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Game> getAllGames() {
        return this.repository.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Optional<Game> getGame(@PathVariable String id){
        return repository.findById(id);
    }

}