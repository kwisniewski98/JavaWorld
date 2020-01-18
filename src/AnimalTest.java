import org.mockito.Mockito;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AnimalTest {

    @org.junit.jupiter.api.Test
    void move() {
        Random rand = Mockito.mock(Random.class);
        Mockito.when(rand.nextInt(Mockito.anyInt())).thenReturn(2);
        World world = new World(20, 20);
        Wolf wolf = new Wolf(0, 0, world);
        wolf.setRand(rand);
        wolf.move();
        assertEquals(wolf.getPosition().getX(), 1);
    }

    @org.junit.jupiter.api.Test
    void attack() {
        World world = new World(20, 20);
        Wolf wolf = new Wolf(0, 0, world);
        Wolf wolf1 = new Wolf(0, 1, world);
        world.add(wolf);
        world.add(wolf1);
        wolf.attack(wolf1);
        assertEquals(world.getOrganisms().size(), 1);

    }

    @org.junit.jupiter.api.Test
    void makeActionReproduce() throws CloneNotSupportedException {
        Random rand = Mockito.mock(Random.class);
        Mockito.when(rand.nextInt(Mockito.anyInt())).thenReturn(100);
        World world = new World(20, 20);
        Wolf wolf = new Wolf(0, 0, world);
        Wolf wolf1 = new Wolf(0, 1, world);
        wolf.setRand(rand);
        world.add(wolf);
        world.add(wolf1);
        wolf.makeAction();
        assertEquals(world.getOrganisms().size(), 3);
    }

    @org.junit.jupiter.api.Test
    void makeActionAttack() throws CloneNotSupportedException {
        Random rand = Mockito.mock(Random.class);
        Mockito.when(rand.nextInt(Mockito.anyInt())).thenReturn(0).thenReturn(100);
        World world = new World(20, 20);
        Wolf wolf = new Wolf(0, 0, world);
        Wolf wolf1 = new Wolf(0, 1, world);
        wolf.setRand(rand);
        world.add(wolf);
        world.add(wolf1);
        wolf.makeAction();
        assertEquals(world.getOrganisms().size(), 1);
    }

    @org.junit.jupiter.api.Test
    void makeActionMove() throws CloneNotSupportedException {
        Random rand = Mockito.mock(Random.class);
        Mockito.when(rand.nextInt(Mockito.anyInt())).thenReturn(2);
        World world = new World(20, 20);
        Wolf wolf = new Wolf(0, 0, world);
        wolf.setRand(rand);
        world.add(wolf);
        wolf.makeAction();
        assertEquals(wolf.getPosition().getX(), 1);
    }

    @org.junit.jupiter.api.Test
    void makeActionRemove() throws CloneNotSupportedException {
        Random rand = Mockito.mock(Random.class);
        Mockito.when(rand.nextInt(Mockito.anyInt())).thenReturn(2);
        World world = new World(20, 20);
        Wolf wolf = new Wolf(0, 0, world);
        for (int i = 0; i < 6; i++) {
            wolf.makeAction();
        }
        assertEquals(world.getOrganisms().size(), 0);
    }
}