import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by Scott on 23/03/2015.
 */
public class World {
    private List<Creature> creatures;
    private List<Plant> plants;
    Random random = new Random();

    public World() {
        creatures = new ArrayList<Creature>();
        plants = new ArrayList<Plant>();
    }

    public void spawnSimpleCreatures(int amount) {
        for (int i = 0; i < amount; i++) {
            creatures.add(new SimpleCreature(random.nextInt(20), random.nextInt(20)));
        }
    }

    public void spawnAdvancedCreatures(int amount) {
        for (int i = 0; i < amount; i++) {
            creatures.add(new AdvancedCreature(random.nextInt(20), random.nextInt(20)));
        }
    }

    public void spawnComplexCreatures(int amount) {
        for (int i = 0; i < amount; i++) {
            creatures.add(new ComplexCreature(random.nextInt(20), random.nextInt(20)));
        }
    }

    public void spawnMedicinalPlants(int amount) {
        for (int i = 0; i < amount; i++) {
            int posX = random.nextInt(20);
            int posY = random.nextInt(20);
            while (isPlantExistingAt(posX, posY)) {
                posX = random.nextInt(20);
                posY = random.nextInt(20);
            }
            plants.add(new MedicinalPlant(posX, posY));
        }
    }

    public void spawnEnergyPlants(int amount) {
        for (int i = 0; i < amount; i++) {
            int posX = random.nextInt(20);
            int posY = random.nextInt(20);
            while (isPlantExistingAt(posX, posY)) {
                posX = random.nextInt(20);
                posY = random.nextInt(20);
            }
            plants.add(new EnergyPlant(posX, posY));
        }
    }

	public List<Creature> getCreatures() {
		return creatures;
	}

	public List<Plant> getPlants() {
		return plants;
	}

    public boolean isPlantExistingAt(int x, int y) {
        for (Plant p : plants) {
            if (p.xPos == x && p.yPos == y)
                return true;
        }
        return false;
    }

    public void step() {
        removeDeadPlants();
        removeDeadCreatures();
        for (Plant p: plants) {
            p.step(); //grow the plants
        }

        List<Intention> intentions = new ArrayList<Intention>();
		for (Creature creature : creatures) {
            Intention inten = creature.step(this);
            intentions.add(inten);
		}
        resolveConflicts(intentions);
        for (Intention intention: intentions) {
            intention.commitIntention();
        }
    }

    public void removeDeadPlants() {
        Iterator<Plant> it = plants.iterator();
        while (it.hasNext()) {
            if (it.next().dead)
                it.remove();
        }
    }

    public void removeDeadCreatures() {
        Iterator<Creature> it = creatures.iterator();
        while (it.hasNext()) {
            if (it.next().dead)
                it.remove();
        }
    }

	public void resolveConflicts(List<Intention> intentions) {
		for (int i = 0; i < intentions.size(); i++) {
            Intention currentIntention = intentions.get(i);
            int xPos = currentIntention.xPosIfCommitted();
            int yPos = currentIntention.yPosIfCommitted();
            for (int j = i+1; j < intentions.size(); j++) {
                Intention otherCreatureIntention = intentions.get(j);
                int conflictXPos = otherCreatureIntention.xPosIfCommitted();
                int conflictYPos = otherCreatureIntention.yPosIfCommitted();
                if (conflictXPos == xPos && conflictYPos == yPos) {
                    //conflict between 2 creatures intentions
                    //TODO needs fixing
                    System.out.println("CONFLICT");
                    if (otherCreatureIntention.action == Action.DO_NOTHING) {
                        //already holds the pos
                        currentIntention.action = Action.DO_NOTHING;
                    }
                    else if (currentIntention.action == Action.DO_NOTHING) {
                        otherCreatureIntention.action = Action.DO_NOTHING;
                    }
                    else if (currentIntention.creature.size > otherCreatureIntention.creature.size) {
                        //other loses because it is smaller
                        otherCreatureIntention.action = Action.DO_NOTHING;
                    }
                    else if (currentIntention.creature.size == otherCreatureIntention.creature.size) {
                        //none of them win, so both lose the move
                        otherCreatureIntention.action = Action.DO_NOTHING;
                        currentIntention.action = Action.DO_NOTHING;
                    }
                    else {
                        currentIntention.action = Action.DO_NOTHING;
                    }

                }
            }

		}
	}

    public void outputPositions() {
        for (Creature creature : creatures) {
            System.out.println("Creature at pos: " + creature.xPos + "," + creature.yPos);
        }
        for (Plant plant : plants) {
            System.out.println("Plant at pos: " + plant.xPos + "," + plant.yPos);
        }
    }

    public boolean isCreatureAtPos(int x, int y) {
        for (Creature c : creatures) {
            if (c.xPos == x && c.yPos == y)
                return true;
        }
        return false;
    }
}
