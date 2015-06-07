import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by sdaveyb on 20/04/2015.
 */
public class AdvancedCreature extends Creature{
    List<Plant> nearbyPlants;

    public AdvancedCreature(int x, int y) {
        xPos = x;
        yPos = y;
        health = 100;
        energy = 100;
        size = 10 + random.nextInt(20);
    }

    @Override
    public Intention step(World world) {
        health -= 5;
        energy -= 5;
        if (health <= 0) {
            dead = true;
        }

        if (mission == null) {
            nearbyPlants = getPerception(world);
            mission = new Intention(this, Action.DO_NOTHING);
            if (nearbyPlants.size() > 0) {
                Plant closestPlant = getClosestPlant();
                mission = new Intention(this, Action.MOVE_TO_TARGET,closestPlant); //move to the plant!
            }else {
                mission = new Intention(this, move()); //move randomly if nothing in sight
            }
        }
        return mission;
    }

    @Override
    public List<Plant> getPerception(World world) {
        List<Plant> plantsFound = new ArrayList<Plant>();
        for (Plant p: world.getPlants()) {
            if (Math.abs(p.xPos - xPos) < 6 && Math.abs(p.yPos - yPos) < 6) {
                int distanceToPlant = Math.abs(p.xPos - xPos) + Math.abs(p.yPos - yPos);
                boolean correctVision = random.nextDouble() < (10-distanceToPlant)/10.0;
                if (correctVision) {
                    plantsFound.add(p);
                }

            }

        }
        return plantsFound;
    }

    public Plant getClosestPlant() {
        Plant minPlant = nearbyPlants.get(0);
        for (Plant p : nearbyPlants) {
            if (Math.abs(minPlant.xPos-xPos) + Math.abs(minPlant.yPos-yPos) > Math.abs(p.xPos-xPos) + Math.abs(p.yPos-yPos) + random.nextInt(2)) {
                minPlant = p;
            }
        }
        return minPlant;
    }


}
