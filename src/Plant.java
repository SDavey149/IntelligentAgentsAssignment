import java.util.Random;

/**
 * Created by Scott on 23/03/2015.
 */
public abstract class Plant {
    int xPos;
    int yPos;
    boolean dead = false;
    Random random = new Random();

    public Plant(int x, int y) {
        xPos = x;
        yPos = y;
    }

    public abstract void step();
}
