import java.util.Random;

public abstract class Plant extends Organism {
    @Override
    public void makeAction(){
        if (this.reproduceChance < new Random().nextInt(100))
            this.life--;
    }
}
