import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        String path = scan.nextLine();
        World world = new World(20, 20);
        world.loadConfig(path);
        for (int i = 0; i < 100; i++) {
            System.out.println(world.makeTurn());
            System.out.println(world);
            scan.nextLine();
        }
    }
}
