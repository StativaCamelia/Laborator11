package com.example.lab11.controllers;

import com.example.lab11.models.Player;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.lab11.repository.PlayerRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private PlayerRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Player> getAllPlayers() {
        return this.repository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Player createPlayer(@Valid @RequestBody Player player) {
        player.set_id(ObjectId.get());
        repository.save(player);
        return player;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deletePlayer(@PathVariable ObjectId id) {
        repository.delete(repository.findBy_id(id));
        return "Succesfully deleted";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Player updatePlayer(@PathVariable ObjectId id, @Valid @RequestBody Player player) {
        player.set_id(id);
        repository.save(player);
        return player;
    }


}