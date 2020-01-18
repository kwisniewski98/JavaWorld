import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositionTest {

    @Test
    void neighbours() {
        Position pos = new Position(1, 1);
        assertEquals(pos.neighbours().size(), 8);
    }

    @Test
    void constructor() {
        Position pos = new Position(-1, -1);
        assertEquals(pos.getX(), 0);
    }

    @Test
    void constructorMax() {
        Position.setMaxX(5);
        Position pos = new Position(6, -1);
        assertEquals(pos.getX(), 5);
    }
}