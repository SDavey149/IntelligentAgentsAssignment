import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Scott on 11/04/2015.
 */
public class SimpleCreature extends Creature{
    private Random random = new Random();

    public SimpleCreature(int x, int y) {
        xPos = x;
        yPos = y;
        health = 100;
        energy = 50;
        size = 10 + random.nextInt(20);
    }

    @Override
    public Intention step(World world) {
        health -= 5;
        energy -= 2;
        List<Plant> nearbyPlants = getPerception(world);
        if (health <= 0)
            dead = true;
        if (energy <= 0)
            return new Intention(this, Action.DO_NOTHING);
        //in this case, no need to check if plant is at our position due to perception implementation
        if (nearbyPlants.size() > 0) {
            return new Intention(this, Action.EAT, nearbyPlants.get(0));
        }
        else {
            //move at random
            return new Intention(this, move());
        }
    }

    @Override
    public List<Plant> getPerception(World world) {
        //check if a plant is at the same position as us
        List<Plant> plantsFound = new ArrayList<Plant>();
        for (Plant p: world.getPlants()) {
            if (p.xPos == xPos && p.yPos == yPos)
                plantsFound.add(p);
        }
        return plantsFound;
    }


}
