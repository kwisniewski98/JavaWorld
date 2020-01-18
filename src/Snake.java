public class Snake extends Animal {
    private static int liveLength;
    private static String sign;
    private static int speciesPower;
    private static int speciesReproduceChance;
    private static int speed;
    private static int aggression; // 1 to 100

    static {
        liveLength = 20;
        sign = new String(Character.toChars(0x1F40D));
        speciesPower = 50;
        speciesReproduceChance = 40;
        speed = 1;
        aggression = 70;
    }

    public Snake(int x, int y, World world) {
        this.position = new Position(x, y);
        this.power = speciesPower;
        this.world = world;
        this.reproduceChance = speciesReproduceChance;
        this.life = liveLength;
    }

    public static void setSpeciesPower(int speciesPower) {
        Snake.speciesPower = speciesPower;
    }

    public static void setSpeciesReproduceChance(int speciesReproduceChance) {
        Snake.speciesReproduceChance = speciesReproduceChance;
    }

    public static void setAggression(int aggression) {
        Snake.aggression = aggression;
    }

    public String getSign() {
        return sign;
    }

    public static void setSign(int sign) {
        Snake.sign = new String(Character.toChars(sign));
    }

    public int getLiveLength() {
        return liveLength;
    }

    public static void setLiveLength(int liveLength) {
        Snake.liveLength = liveLength;
    }

    @Override
    public String toString() {
        return String.valueOf(sign);
    }

    public int getSpeed() {
        return speed;
    }

    public static void setSpeed(int speed) {
        Snake.speed = speed;
    }

    public int getaggression() {
        return aggression;
    }

    @Override
    protected Object clone() {
        return new Snake(0, 0, world);
    }
}