package lab11.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/players")
public class Controller {
    private final List<Player> players = new ArrayList<>();

    @GetMapping
    public List<Player> getPlayers() {
        return players;
    }

    @GetMapping("/count")
    public int countPlayers() {
        return players.size();
    }
    @GetMapping("/{id}")
    public Player getPlayer(@PathVariable("id") int id) {
        return players.stream()
                .filter(p -> p.getId() == id).findFirst().orElse(null);

        //.orElseThrow( () -> new MyException("Player not found"));
    }

//////////////////////

    @PostMapping
    public int createPlayer(@RequestParam String name) {
        int id = 1 + players.size();
        players.add(new Player(id, name));
        return id;
    }

    @PostMapping(value = "/obj", consumes="application/json")
    public ResponseEntity<String> createProduct(@RequestBody Player player ) {
        players.add(player);
        return new
                ResponseEntity <>("Product created successfully", HttpStatus.CREATED );
    }

    public Player findById(int id){

           return players.get(id);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable int id) {
        Player player = findById(id);
        if (player == null) {
            return new ResponseEntity<>(
                    "Player not found", HttpStatus.GONE);
        }
        players.remove(player);
        return new ResponseEntity<>("Player removed", HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updatePlayer(@PathVariable int id,@RequestParam String name) {
        Player player = findById(id);
        if (player == null) {
            return new ResponseEntity<>(
                    "Player not found", HttpStatus.NOT_FOUND); //or GONE
        }
        player.setName(name);
        return new ResponseEntity<>(
                "Player updated successsfully", HttpStatus.OK);
    }

}