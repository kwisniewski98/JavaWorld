import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Random;

public abstract class Animal extends Organism {


    public static void setaggression(int aggression) {
        throw new NotImplementedException();

    }

    public boolean move() {
        Random rand = this.getRand();
        int x = this.position.getX() + ((rand.nextInt(3) - 1) * this.getSpeed());
        int y = this.position.getY() + ((rand.nextInt(3) - 1) * this.getSpeed());
        Position position = new Position(x, y);
        if (this.position.equals(position)) {
            return false;
        } else {
            this.position = position;
            return true;
        }
    }

    public String attack(Organism organism) {
        Random random = this.getRand();
        int number = random.nextInt(this.power + organism.power);

        if (number < power - 1) {
            world.remove(this);
            organism.setLife(organism.getLife() + organism.getLiveLength());
            return "lost";
        } else {
            this.life += this.getLiveLength();
            world.remove(organism);
            return "won";
        }
    }

    @Override
    public String makeAction() throws CloneNotSupportedException {
        this.life--;
        if (life < 1) {
            world.remove(this);
            return this.getClass().toString().split("\\s+")[1] + " died of old age at " + this.getPosition();
        }
        Random random = this.getRand();
        if (world.getNeighbours(this).size() > 0) {
            if (world.getNeighboursOfSameType(this).size() > 0 && random.nextInt(100) > reproduceChance) {
                Organism organism = this.reproduce();
                if (organism != null) {
                    return this.getClass().toString().split("\\s+")[1] + " spawned at " + organism.getPosition();
                } else {
                    return this.getClass().toString().split("\\s+")[1] + " tried to reproduce but didn't have enough space";
                }
            } else {
                if (random.nextInt(100) > this.getaggression()) {
                    Organism organism = world.getRandomNeighbour(this);
                    if (organism != null) {
                        String resultAttack = this.attack(organism);
                        return this.getClass().toString().split("\\s+")[1] + " attacked " +
                                organism.getClass().toString().split("\\s+")[1] + " and " + resultAttack;
                    }

                }
            }
        } else {
            if (this.move()) {
                return this.getClass().toString().split("\\s+")[1] + " moved to " + this.position;
            }
        }
        return "";
    }

    public abstract int getSpeed();

    public static void setSpeed(int speed) {
        throw new NotImplementedException();
    }

    public abstract int getaggression();
}
