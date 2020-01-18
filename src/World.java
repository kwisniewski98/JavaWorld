import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class World {
    private ArrayList<Organism> organisms;
    private int turn;
    private int maxY;
    private int maxX;
    private String[][] layout;
    private int nothing = 0x1F5CC;
    private Random random = new Random();

    public World(int maxY, int maxX) {
        this.maxY = maxY;
        this.maxX = maxX;
        this.turn = 0;
        this.organisms = new ArrayList<>();
        this.layout = new String[maxY][maxX];
        this.resize(maxX, maxY);

    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public void resize(int maxX, int maxY) {
        this.maxY = maxY;
        this.maxX = maxX;
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                this.layout[y][x] = new String(Character.toChars(this.nothing));
            }
        }
        Position.setMaxX(maxX);
        Position.setMaxY(maxY);
    }

    public int getTurn() {
        return turn;
    }

    public void remove(Organism organism) {
        this.organisms.remove(organism);
    }

    public void add(Organism organism) {

        for (Iterator<Organism> it = this.organisms.iterator(); it.hasNext(); ) {
            Organism item = it.next();
            if (item.getPosition() == organism.getPosition()) {
                return;
            }
        }
        this.organisms.add(organism);
    }

    public ArrayList<Organism> getNeighbours(Organism organism) {
        ArrayList<Organism> result = new ArrayList<>();
        for (Iterator<Organism> it = this.organisms.iterator(); it.hasNext(); ) {
            Organism item = it.next();
            if (Math.abs(item.position.getX() - organism.getPosition().getX()) < 2 &&
                    Math.abs(item.getPosition().getY() - organism.getPosition().getY()) < 2) {
                result.add(item);
            }
        }
        result.remove(organism);
        return result;
    }

    public ArrayList<Organism> getOrganisms() {
        return organisms;
    }

    public ArrayList<Organism> getNeighboursOfSameType(Organism organism) {
        ArrayList<Organism> result = new ArrayList<>();

        for (Iterator<Organism> it = this.organisms.iterator(); it.hasNext(); ) {
            Organism item = it.next();
            if (Math.abs(item.position.getX() - organism.getPosition().getX()) < 2 &&
                    Math.abs(item.getPosition().getY() - organism.getPosition().getY()) < 2 &&
                    item.getClass() == organism.getClass()) {
                result.add(item);
            }
        }
        result.remove(organism);
        return result;
    }


    public Position randomAvailableNeighbourPosition(Position pos) {
        ArrayList<Position> positions = pos.neighbours();
        for (Iterator<Organism> it = this.organisms.iterator(); it.hasNext(); ) {
            Organism item = it.next();
            if (Math.abs(item.position.getX() - pos.getX()) < 2 &&
                    Math.abs(item.getPosition().getY() - pos.getY()) < 2) {
                positions.removeIf(position -> position.getY() == item.getPosition().getY() &&
                        position.getX() == item.getPosition().getX());

            }

        }
        positions.remove(pos);
        if (positions.size() > 0) {
            return positions.get(new Random().nextInt(positions.size()));
        } else {
            return new Position(-1, -1);
        }
    }


    public Organism getRandomNeighbour(Organism organism) {
        ArrayList<Organism> neighbours = this.getNeighbours(organism);
        if (neighbours.size() > 0) {
            return neighbours.get(new Random().nextInt(neighbours.size()));
        } else {
            return organism;
        }
    }

    public String makeTurn() {
        String result = "Turn: " + turn + "\n";
        ArrayList<Organism> organisms = new ArrayList<>();
        this.organisms.forEach(item -> organisms.add(item));
        for (Iterator<Organism> it = organisms.iterator(); it.hasNext(); ) {
            Organism item = it.next();
            try {
                result += item.makeAction() + "\n";
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

        this.turn++;
        this.worldLayout();
        return result;
    }

    public void worldLayout() {
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                this.layout[y][x] = new String(Character.toChars(this.nothing));
            }
        }
        for (Iterator<Organism> it = this.organisms.iterator(); it.hasNext(); ) {
            Organism item = it.next();
            this.layout[item.getPosition().getY()][item.getPosition().getX()] = item.getSign();
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                result += this.layout[y][x];
            }
            result += "\n";
        }
        return result;
    }

    public void loadConfig(String path) throws FileNotFoundException {
        try {
            Yaml yaml = new Yaml();
            InputStream inputStream = new FileInputStream(new File(path));
            Map<String, Object> obj = (Map<String, Object>) yaml.load(inputStream);
            String[] size = ((String) obj.get("WorldSize")).split(",");
            this.resize(Integer.parseInt(size[0]), Integer.parseInt(size[1]));
            Grass.setLiveLength(((Map<String, Integer>) obj.get("Grass")).get("liveLength"));
            Grass.setSpeciesPower(((Map<String, Integer>) obj.get("Grass")).get("speciesPower"));
            Grass.setSpeciesReproduceChance(((Map<String, Integer>) obj.get("Grass")).get("speciesReproduceChance"));
            Grass.setSign(((Map<String, Integer>) obj.get("Grass")).get("sign"));
            Mushroom.setLiveLength(((Map<String, Integer>) obj.get("Mushroom")).get("liveLength"));
            Mushroom.setSpeciesPower(((Map<String, Integer>) obj.get("Mushroom")).get("speciesPower"));
            Mushroom.setSpeciesReproduceChance(((Map<String, Integer>) obj.get("Mushroom")).get("speciesReproduceChance"));
            Mushroom.setSign(((Map<String, Integer>) obj.get("Mushroom")).get("sign"));
            Sheep.setLiveLength(((Map<String, Integer>) obj.get("Sheep")).get("liveLength"));
            Sheep.setSpeciesPower(((Map<String, Integer>) obj.get("Sheep")).get("speciesPower"));
            Sheep.setSpeciesReproduceChance(((Map<String, Integer>) obj.get("Sheep")).get("speciesReproduceChance"));
            Sheep.setSign(((Map<String, Integer>) obj.get("Sheep")).get("sign"));
            Sheep.setSpeed(((Map<String, Integer>) obj.get("Sheep")).get("speed"));
            Sheep.setAggression(((Map<String, Integer>) obj.get("Sheep")).get("aggression"));
            Snake.setLiveLength(((Map<String, Integer>) obj.get("Snake")).get("liveLength"));
            Snake.setSpeciesPower(((Map<String, Integer>) obj.get("Snake")).get("speciesPower"));
            Snake.setSpeciesReproduceChance(((Map<String, Integer>) obj.get("Snake")).get("speciesReproduceChance"));
            Snake.setSign(((Map<String, Integer>) obj.get("Snake")).get("sign"));
            Snake.setSpeed(((Map<String, Integer>) obj.get("Snake")).get("speed"));
            Snake.setAggression(((Map<String, Integer>) obj.get("Snake")).get("aggression"));
            Wolf.setLiveLength(((Map<String, Integer>) obj.get("Wolf")).get("liveLength"));
            Wolf.setSpeciesPower(((Map<String, Integer>) obj.get("Wolf")).get("speciesPower"));
            Wolf.setSpeciesReproduceChance(((Map<String, Integer>) obj.get("Wolf")).get("speciesReproduceChance"));
            Wolf.setSign(((Map<String, Integer>) obj.get("Wolf")).get("sign"));
            Wolf.setSpeed(((Map<String, Integer>) obj.get("Wolf")).get("speed"));
            Wolf.setAggression(((Map<String, Integer>) obj.get("Wolf")).get("aggression"));
            this.nothing = (int) obj.get("Nothing");

            if ((boolean) obj.get("RandomPositions")) {
                Map<String, Integer> layout = (Map<String, Integer>) obj.get("Layout");
                Random rand = new Random();

                while (this.organisms.size() < layout.get("Grass")) {
                    this.add(new Grass(rand.nextInt(maxX), rand.nextInt(maxY), this));
                }
                int i = this.organisms.size();
                while (this.organisms.size() < layout.get("Mushroom") + i) {
                    this.add(new Mushroom(rand.nextInt(maxX), rand.nextInt(maxY), this));
                }
                i = this.organisms.size();
                while (this.organisms.size() < layout.get("Sheep") + i) {
                    this.add(new Sheep(rand.nextInt(maxX), rand.nextInt(maxY), this));
                }
                i = this.organisms.size();
                while (this.organisms.size() < layout.get("Snake") + i) {
                    this.add(new Snake(rand.nextInt(maxX), rand.nextInt(maxY), this));
                }
                i = this.organisms.size();
                while (this.organisms.size() < layout.get("Wolf") + i) {
                    this.add(new Wolf(rand.nextInt(maxX), rand.nextInt(maxY), this));
                }
            } else {
                Map<String, ArrayList<String>> layout = (Map<String, ArrayList<String>>) obj.get("Layout");
                System.out.print(layout.get("Grass").getClass() + layout.get("Grass").toString());
                layout.get("Grass").forEach(item -> this.add(new Grass(Integer.parseInt(item.split(",")[0]),
                        Integer.parseInt(item.split(",")[1]), this)));
                layout.get("Mushroom").forEach(item -> this.add(new Mushroom(Integer.parseInt(item.split(",")[0]),
                        Integer.parseInt(item.split(",")[1]), this)));
                layout.get("Sheep").forEach(item -> this.add(new Sheep(Integer.parseInt(item.split(",")[0]),
                        Integer.parseInt(item.split(",")[1]), this)));
                layout.get("Snake").forEach(item -> this.add(new Snake(Integer.parseInt(item.split(",")[0]),
                        Integer.parseInt(item.split(",")[1]), this)));
                layout.get("Wolf").forEach(item -> this.add(new Wolf(Integer.parseInt(item.split(",")[0]),
                        Integer.parseInt(item.split(",")[1]), this)));

            }
        } catch (NullPointerException e) {
            System.out.print("Config is invalid");
            throw e;
        }

    }
}
