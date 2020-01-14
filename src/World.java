import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class World {
    private ArrayList<Organism> organisms;

    public World() {
        this.organisms = new ArrayList<>();
    }

    public void remove(Organism organism) {
        this.organisms.remove(organism);
    }

    public void add(Organism organism) {
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
        return result;
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
        return result;
    }


    public Position randomAvailableNeighbourPosition(Position pos) {
        ArrayList<Position> positions = pos.neighbours();
        ArrayList<Position> results = new ArrayList<>();
        for (Iterator<Organism> it = this.organisms.iterator(); it.hasNext(); ) {
            Organism item = it.next();
            if (Math.abs(item.position.getX() - pos.getX()) < 2 &&
                    Math.abs(item.getPosition().getY() - pos.getY()) < 2) {
                positions.removeIf(position -> position.getY() == item.getPosition().getY() &&
                        position.getX() == item.getPosition().getX());

            }

        }
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
    //TODO Add logging
    //TODO Add printing
    //TODO Add configs
    //TODO
}
