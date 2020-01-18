import java.util.ArrayList;
import java.util.Objects;

public class Position {
    private int x;
    private int y;
    private static int maxX;
    private static int maxY;

    public Position(int x, int y) {
        if (x < maxX && x >= 0) {
            this.x = x;
        } else {
            if (x < 0) {
                this.x = 0;
            } else {
                this.x = maxX;
            }
        }
        if (y < maxY && y >= 0) {
            this.y = y;
        } else {
            if (y < 0) {
                this.y = 0;
            } else {
                this.y = maxY;
            }
        }

    }

    public static void setMaxX(int maxX) {
        Position.maxX = maxX;
    }

    public int getX() {
        return x;
    }

    public static void setMaxY(int maxY) {
        Position.maxY = maxY;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public ArrayList<Position> neighbours() {
        ArrayList<Position> positions = new ArrayList<>();
        for (int i = -1; i < 2; i++) {
            if (this.x + i < maxX && this.x + i > -1) {
                for (int j = -1; j < 2; j++) {
                    if (this.y + j < maxY && this.y + j > -1) {
                        positions.add(new Position(this.x + i, this.y + j));
                    }
                }
            }
        }
        positions.removeIf(item -> item.getX() == this.x && item.getY() == this.y);
        return positions;
    }
}
