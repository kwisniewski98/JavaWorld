public class Mushroom extends Plant {
    private static int liveLength;
    private static String sign;
    private static int speciesPower;
    private static int speciesReproduceChance;

    static {
        liveLength = 5;
        sign = new String(Character.toChars(0x1F344));
        speciesPower = 25;
        speciesReproduceChance = 40;
    }

    public Mushroom(int x, int y, World world) {
        this.position = new Position(x, y);
        this.power = speciesPower;
        this.world = world;
        this.reproduceChance = speciesReproduceChance;
        this.life = liveLength;
    }

    public static void setSpeciesPower(int speciesPower) {
        Mushroom.speciesPower = speciesPower;
    }

    public static void setSpeciesReproduceChance(int speciesReproduceChance) {
        Mushroom.speciesReproduceChance = speciesReproduceChance;
    }

    public String getSign() {
        return sign;
    }

    public static void setSign(int sign) {
        Mushroom.sign = new String(Character.toChars(sign));
    }

    public int getLiveLength() {
        return liveLength;
    }

    public static void setLiveLength(int liveLength) {
        Mushroom.liveLength = liveLength;
    }

    @Override
    protected Object clone() {
        return new Mushroom(0, 0, world);
    }

    @Override
    public String toString() {
        return String.valueOf(sign);
    }
}
