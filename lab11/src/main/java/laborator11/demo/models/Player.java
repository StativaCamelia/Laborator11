package laborator11.demo.models;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Player {
    @Id
    public String id;
    public String name;

    // Constructors
    public Player() {}

    public Player(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public void setId(String _id) { this.id = _id; }

    public String getName() { return name; }
    public void setName(String username) { this.name = username; }

}