/**
 * Created by sdaveyb on 14/04/2015.
 */
public class Intention {
    Creature creature;
    Action action;
    Plant plant; //if action is eating

    public Intention(Creature c, Action act) {
        creature = c;
        action = act;
    }

    public Intention(Creature c, Action act, Plant p) {
        creature = c;
        action = act;
        plant = p;
    }

    public int xPosIfCommitted() {
        switch (action) {
            case MOVE_LEFT:
                return creature.xPos - 1;
            case MOVE_RIGHT:
                return creature.xPos + 1;
            case MOVE_TO_TARGET:
                //messy solution here
                int x = creature.xPos;
                int y = creature.yPos;
                creature.walkToTarget(plant);
                int xPosCommit = creature.xPos;
                //reset creature
                creature.xPos = x;
                creature.yPos = y;
                return xPosCommit;
        }
        return creature.xPos;
    }

    public int yPosIfCommitted() {
        switch (action) {
            case MOVE_DOWN:
                return creature.yPos + 1;
            case MOVE_UP:
                return creature.yPos - 1;
            case MOVE_TO_TARGET:
                //messy solution here
                int x = creature.xPos;
                int y = creature.yPos;
                creature.walkToTarget(plant);
                int yPosCommit = creature.yPos;
                //reset creature
                creature.xPos = x;
                creature.yPos = y;
                return yPosCommit;
        }
        return creature.yPos;
    }

    public void commitIntention() {
        switch (action) {
            case MOVE_UP:
                creature.up();
                creature.resetMission();
                break;
            case MOVE_DOWN:
                creature.down();
                creature.resetMission();
                break;
            case MOVE_LEFT:
                creature.right();
                creature.resetMission();
                break;
            case MOVE_RIGHT:
                creature.left();
                creature.resetMission();
                break;
            case EAT:
                creature.eat(plant);
                creature.resetMission();
                break;
            case DO_NOTHING:
                creature.resetMission();
                break;
            case MOVE_TO_TARGET:
                creature.walkToTarget(plant);
                break;
        }
    }
}
