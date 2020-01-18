import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlantTest {

    @Test
    void makeAction() throws CloneNotSupportedException {
        Random rand = Mockito.mock(Random.class);
        Mockito.when(rand.nextInt(Mockito.anyInt())).thenReturn(100);
        World world = new World(20, 20);
        Grass grass = new Grass(0, 0, world);
        grass.setRand(rand);
        world.add(grass);
        grass.makeAction();
        assertEquals(world.getOrganisms().size(), 2);
    }
}