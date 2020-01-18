import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Random;

public abstract class Organism {
    protected int power;
    protected Position position;
    private Random rand = new Random();

    protected int reproduceChance; // 1 to 100;
    protected World world;
    protected int life;

    public static void setSpeciesPower(int speciesPower) {
        throw new NotImplementedException();
    }

    public static void setSpeciesReproduceChance(int speciesReproduceChance) {
        throw new NotImplementedException();
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract String getSign();

    public static void setSign(String sign) {
        throw new NotImplementedException();
    }

    public Organism reproduce() throws CloneNotSupportedException {
        Position pos = world.randomAvailableNeighbourPosition(this.position);
        if (pos.getX() != -1) {

            Organism organism = (this.getClass().cast(this.clone()));
            organism.setPosition(pos);
            world.add(organism);
            this.reproduceChance /= 2;
            return organism;
        } else {
            return null;
        }

    }

    public abstract int getLiveLength();

    public static void setLiveLength(int liveLength) {
        throw new NotImplementedException();
    }

    public abstract String makeAction() throws CloneNotSupportedException;

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Random getRand() {
        return rand;
    }

    public void setRand(Random rand) {
        this.rand = rand;
    }
}
