import java.util.List;
import java.util.Random;

/**
 * Created by Scott on 23/03/2015.
 */
public abstract class Creature {
    int health;
    int energy;
    int size;
    int xPos;
    int yPos;
    boolean dead;
    Intention mission;
    Random random = new Random();

    public abstract Intention step(World world);

    public abstract List<Plant> getPerception(World world);

    public Action move() {
        int num = random.nextInt(4);
        Action[] availableMoves = {Action.MOVE_UP,
                Action.MOVE_DOWN,
                Action.MOVE_LEFT,
                Action.MOVE_RIGHT};
        return availableMoves[num];
    }

    public void up() {
        energy -= 5;
        if (yPos > 0)
            yPos--;
    }

    public void down() {
        energy -= 5;
        if (yPos < 19)
            yPos++;
    }

    public void right() {
        energy -= 5;
        if (xPos < 19)
            xPos++;
    }

    public void left() {
        energy -= 5;
        if (xPos > 0)
            xPos--;
    }

    public void eat(Plant p) {
        if (p instanceof EnergyPlant) {
            energy += ((EnergyPlant) p).energyGain;
            if (energy > 100)
                energy = 100;
        }
        else {
            health += ((MedicinalPlant) p).healthGain;
            if (health > 100)
                health = 100;
        }
        p.dead = true;

    }

    public void resetMission() {
        mission = null;
    }

    public boolean needEnergy() {
        if (energy < 60)
            return true;
        return false;
    }

    public void walkToTarget(Plant p) {
        if (xPos < p.xPos)
            right();
        else if (xPos > p.xPos)
            left();
        else if (yPos > p.yPos)
            up();
        else if (yPos < p.yPos)
            down();
        else {
            eat(p);
            resetMission();
        }
    }






}
