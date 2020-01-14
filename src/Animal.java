import java.util.Random;

public abstract class Animal extends Organism {
    protected static int speed;
    protected static int agresion; // 1 to 100

    private void move() {
        Random rand = new Random();
        this.position.setX(this.position.getX() + ((rand.nextInt(2) - 1) * rand.nextInt(speed)));
        this.position.setY(this.position.getY() + ((rand.nextInt(2) - 1) * rand.nextInt(speed)));
    }

    private void attack(Organism organism) {
        Random random = new Random();
        int number = random.nextInt(this.power + organism.power);
        if (number < power - 1) {
            world.remove(this);
        } else {
            world.remove(organism);
        }
    }

    @Override
    public void makeAction() throws CloneNotSupportedException {
        Random random = new Random();
        if (world.getNeighbours(this).size() > 0) {
            if (world.getNeighboursOfSameType(this).size() > 0 && random.nextInt(100) < reproduceChance) {
                this.reproduce();
            } else {
                if (random.nextInt(100) > agresion) {
                    this.attack(world.getRandomNeighbour(this));
                }
            }
        } else {
            this.move();
        }
        this.life--;
    }
}
