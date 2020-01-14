public abstract class Organism {
    protected int power;
    protected Position position;
    protected static int liveLength;
    protected static char sign;
    protected int reproduceChance; // 1 to 100;
    protected World world;
    protected int life;

    public void reproduce() throws CloneNotSupportedException {
        Position pos = world.randomAvailableNeighbour(this.position);
        if (pos.getX() != -1) {

            Organism organism = (this.getClass().cast(this.clone()));
            organism.setPosition(pos);
            world.add(organism);


        }
    }
    @Override
    public String toString() {
        return String.valueOf(sign);
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

    public int getLiveLength() {
        return liveLength;
    }

    public void setLiveLength(int liveLength) {
        this.liveLength = liveLength;
    }

    public abstract void makeAction() throws CloneNotSupportedException;


}
