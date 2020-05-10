package laborator11.demo.repository;

import laborator11.demo.models.Game;
import laborator11.demo.models.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends MongoRepository<Game, String> {
}
