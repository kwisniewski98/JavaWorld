public class Wolf extends Animal {
    private static int liveLength;
    private static String sign;
    private static int speciesPower;
    private static int speciesReproduceChance;
    private static int speed;
    private static int aggression; // 1 to 100

    static {
        liveLength = 5;
        sign = new String(Character.toChars(0x1F43A));
        ;
        speciesPower = 50;
        speciesReproduceChance = 40;
        speed = 1;
        aggression = 70;
    }

    public Wolf(int x, int y, World world) {
        this.position = new Position(x, y);
        this.power = speciesPower;
        this.world = world;
        this.reproduceChance = speciesReproduceChance;
        this.life = liveLength;
    }

    public static void setSpeciesPower(int speciesPower) {
        Wolf.speciesPower = speciesPower;
    }

    public static void setSpeciesReproduceChance(int speciesReproduceChance) {
        Wolf.speciesReproduceChance = speciesReproduceChance;
    }

    public static void setAggression(int aggression) {
        Wolf.aggression = aggression;
    }

    public String getSign() {
        return sign;
    }

    public static void setSign(int sign) {
        Wolf.sign = new String(Character.toChars(sign));
    }

    public int getLiveLength() {
        return liveLength;
    }

    public static void setLiveLength(int liveLength) {
        Wolf.liveLength = liveLength;
    }

    protected Object clone() {
        return new Wolf(0, 0, world);
    }

    @Override
    public String toString() {
        return String.valueOf(sign);
    }

    public int getSpeed() {
        return speed;
    }

    public static void setSpeed(int speed) {
        Wolf.speed = speed;
    }

    public int getaggression() {
        return aggression;
    }
}