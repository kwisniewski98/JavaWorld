public class Sheep extends Animal {
    private static int liveLength;
    private static String sign;
    private static int speciesPower;
    private static int speciesReproduceChance;
    private static int speed;
    private static int aggression; // 1 to 100

    static {
        liveLength = 5;
        sign = new String(Character.toChars(0x1F411));
        speciesPower = 5;
        speciesReproduceChance = 80;
        speed = 1;
        aggression = 10;
    }

    public Sheep(int x, int y, World world) {
        this.position = new Position(x, y);
        this.power = speciesPower;
        this.world = world;
        this.reproduceChance = speciesReproduceChance;
        this.life = liveLength;
    }

    public static void setSpeciesPower(int speciesPower) {
        Sheep.speciesPower = speciesPower;
    }

    public static void setSpeciesReproduceChance(int speciesReproduceChance) {
        Sheep.speciesReproduceChance = speciesReproduceChance;
    }

    public static void setAggression(int aggression) {
        Sheep.aggression = aggression;
    }

    public String getSign() {
        return sign;
    }

    public static void setSign(int sign) {
        Sheep.sign = new String(Character.toChars(sign));
    }

    @Override
    protected Object clone() {
        return new Sheep(0, 0, world);
    }

    public int getLiveLength() {
        return liveLength;
    }

    public static void setLiveLength(int liveLength) {
        Sheep.liveLength = liveLength;
    }

    @Override
    public String toString() {
        return String.valueOf(sign);
    }

    public int getSpeed() {
        return speed;
    }

    public static void setSpeed(int speed) {
        Sheep.speed = speed;
    }

    public int getaggression() {
        return aggression;
    }
}
