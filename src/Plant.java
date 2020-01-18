public abstract class Plant extends Organism {
    @Override
    public String makeAction() throws CloneNotSupportedException {
        this.life--;
        if (this.life < 1) {
            world.remove(this);
            return this.getClass().toString().split("\\s+")[1] + " died of old age at " + this.getPosition();
        }
        if (this.reproduceChance < this.getRand().nextInt(100)) {
            Organism organism = this.reproduce();
            if (organism != null) {
                return organism.getClass().toString().split("\\s+")[1] + " spawned at " + organism.getPosition();
            } else {
                return organism.getClass().toString().split("\\s+")[1] + " tried to reproduce but didn't have enough space";
            }
        }
        return "";
    }
}
