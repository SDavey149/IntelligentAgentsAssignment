import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by sdaveyb on 20/04/2015.
 */
public class ComplexCreature extends Creature{
    List<Plant> nearbyPlants;
    Action direction = null;
    Plant target = null;

    public ComplexCreature(int x, int y) {
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

        //get new perception each movement
        nearbyPlants = getPerception(world);
        for (Plant p: nearbyPlants) {
            direction = null;
            if (p.xPos == xPos && p.yPos == yPos) {
                //at same position as a plant, so eat it
                target = null;
                return new Intention(this, Action.EAT, p);
            }
        }

        //switch targets if necessary
        Plant newTarget = getPlantTarget();
        if (target == null) {
            target = newTarget;
        }
        else if (newTarget != null) {
            if (target.xPos == xPos && target.yPos == yPos) {
                target = newTarget;
            }
            else {
                target = closestPlantPair(target, newTarget);
            }
            return new Intention(this, Action.MOVE_TO_TARGET, target);
        }


        //if we get here, no plants in sight so what next?
        //moving random usually spins you in circles, so choose a direction
        //go in that direction until plants found, if hit the edge, try a new direction
        if (direction == null)
            direction = move();
        changeDirectionBasedOnEdges(world); //at edge of world
        return new Intention(this, direction);

    }

    private Plant getPlantTarget() {
        Plant newTarget;
        if (health > energy) {
            //energy is priority
            newTarget = getClosestEnergyPlant();
        }
        else {
            //health is priority
            newTarget = getClosestMedicinalPlant();
        }

        if (newTarget == null) {
            newTarget = getClosestPlant(); //doesnt care what plant
        }
        return newTarget;
    }

    private void changeDirectionBasedOnEdges(World world) {
        if (xPos == 0 && direction == Action.MOVE_LEFT) {
            Action[] allowedMoves = {Action.MOVE_DOWN, Action.MOVE_UP, Action.MOVE_RIGHT};
            direction = chooseRandom(allowedMoves);
        }
        else if (xPos == 19 && direction == Action.MOVE_RIGHT) {
            Action[] allowedMoves = {Action.MOVE_DOWN, Action.MOVE_UP, Action.MOVE_LEFT};
            direction = chooseRandom(allowedMoves);
        }
        else if (yPos == 0 && direction == Action.MOVE_UP) {
            Action[] allowedMoves = {Action.MOVE_DOWN, Action.MOVE_RIGHT, Action.MOVE_LEFT};
            direction = chooseRandom(allowedMoves);
        }
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
            if (random.nextDouble() < 0.5) {
                //50% chance of seeing a fake plant
                if (random.nextInt(2) == 0)
                    plantsFound.add(new EnergyPlant(xPos +random.nextInt(4)+1, yPos+random.nextInt(4)+1));
                else
                    plantsFound.add(new MedicinalPlant(xPos +random.nextInt(4)+1, yPos+random.nextInt(4)+1));
            }

        }
        return plantsFound;
    }

    public Plant getClosestMedicinalPlant() {
        Plant minPlant = null;
        for (Plant p : nearbyPlants) {
            if (!p.dead) {
                if (p instanceof MedicinalPlant && minPlant == null)
                    minPlant = p;
                else if (p instanceof MedicinalPlant && Math.abs(minPlant.xPos-xPos) + Math.abs(minPlant.yPos-yPos) > Math.abs(p.xPos-xPos) + Math.abs(p.yPos-yPos)) {
                    minPlant = p;
                }
            }

        }
        return minPlant;
    }

    public Plant getClosestEnergyPlant() {
        Plant minPlant = null;
        for (Plant p : nearbyPlants) {
            if (!p.dead) {
                if (p instanceof EnergyPlant && minPlant == null)
                    minPlant = p;
                else if (p instanceof EnergyPlant && Math.abs(minPlant.xPos-xPos) + Math.abs(minPlant.yPos-yPos) > Math.abs(p.xPos-xPos) + Math.abs(p.yPos-yPos)) {
                    minPlant = p;
                }
            }

        }
        return minPlant;
    }

    public Plant getClosestPlant() {
        Plant minPlant = nearbyPlants.get(0);
        for (Plant p : nearbyPlants) {
            if (!p.dead) {
                if (Math.abs(minPlant.xPos-xPos) + Math.abs(minPlant.yPos-yPos) > Math.abs(p.xPos-xPos) + Math.abs(p.yPos-yPos) + random.nextInt(2)) {
                    minPlant = p;
                }
            }

        }
        return minPlant;
    }

    public Plant closestPlantPair(Plant p1, Plant p2) {
        Plant minPlant = p1;

        if ( Math.abs(minPlant.xPos-xPos) + Math.abs(minPlant.yPos-yPos) > Math.abs(p2.xPos-xPos) + Math.abs(p2.yPos-yPos)) {
            minPlant = p2;
        }
        return minPlant;
    }


    public Action chooseRandom(Action[] actions) {
        int num = random.nextInt(actions.length);
        return actions[num];
    }
}
