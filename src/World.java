import java.util.ArrayList;

public class World {
    public void remove(Organism organism){
        //TODO implement World.remove
    }
    public void add(Organism organism){
        //TODO implement World.add
    }
    public ArrayList<Organism> getNeighbours(Organism organism)
    {
        //TODO implement World.getNeighbours
        return new ArrayList<>();
    }
    public ArrayList<Organism> getNeighboursOfSameType(Organism organism)
    {
        //TODO implement World.getNeighbours
        return new ArrayList<>();
    }


    public Position randomAvailableNeighbour(Position pos){
        //TODO implement World.randomAvailableNeighbour
        return new Position(-1, -1);
    }
    public Organism getRandomNeighbour(Organism organism){
        //TODO implement World.getRandomNeighbour
    }
}
