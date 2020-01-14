public abstract class Plant extends Organism {
    public void reproduce(){
        Position pos = world.randomAvailableNeighbour(this.position);
        if (pos.getX() != -1) {

        }
    }
    @Override
    public void makeAction(){
        this.life--;
    }
}
