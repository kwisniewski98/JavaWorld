import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WorldTest {

    @Test
    void resize() {
        World world = new World(10, 10);
        world.resize(15, 15);
        assertEquals(world.getMaxX(), 15);
    }

    @Test
    void add() {
        World world = new World(10, 10);
        Wolf wolf = new Wolf(0, 0, world);
        Wolf wolf2 = new Wolf(0, 0, world);
        world.add(wolf);
        world.add(wolf2);
        assertEquals(world.getOrganisms().size(), 1);
    }

    @Test
    void getNeighbours() {
        World world = new World(10, 10);
        Wolf wolf = new Wolf(0, 0, world);
        Wolf wolf2 = new Wolf(0, 1, world);
        world.add(wolf);
        world.add(wolf2);
        assertEquals(world.getNeighbours(wolf).size(), 1);
    }

    @Test
    void getNeighboursOfSameType() {
        World world = new World(10, 10);
        Wolf wolf = new Wolf(0, 0, world);
        Sheep sheep = new Sheep(0, 1, world);
        world.add(wolf);
        world.add(sheep);
        assertEquals(world.getNeighboursOfSameType(wolf).size(), 0);
    }

    @Test
    void randomAvailableNeighbourPosition() {
        Random rand = Mockito.mock(Random.class);
        Mockito.when(rand.nextInt(Mockito.anyInt())).thenReturn(0);
        World world = new World(10, 10);
        world.setRandom(rand);
        Wolf wolf = new Wolf(1, 1, world);
        wolf.setRand(rand);
        world.add(wolf);
        assertEquals(world.randomAvailableNeighbourPosition(wolf.getPosition()), new Position(0, 0));

    }

    @Test
    void getRandomNeighbour() {
        Random rand = Mockito.mock(Random.class);
        Mockito.when(rand.nextInt(Mockito.anyInt())).thenReturn(0);
        World world = new World(10, 10);
        world.setRandom(rand);
        Wolf wolf = new Wolf(1, 1, world);
        wolf.setRand(rand);
        Wolf wolf2 = new Wolf(1, 2, world);
        world.add(wolf);
        world.add(wolf2);
        assertEquals(world.getRandomNeighbour(wolf), wolf2);
    }

    @Test
    void makeTurn() {
        World world = new World(10, 10);
        assertEquals(world.makeTurn(), "Turn: 0\n");
    }

    @Test
    void loadConfig() throws FileNotFoundException {
        World world = new World(10, 10);
        world.loadConfig("example.yaml");
        assertEquals(world.getMaxX(), 20);
    }
}