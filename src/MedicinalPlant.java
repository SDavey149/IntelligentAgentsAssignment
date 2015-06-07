/**
 * Created by sdaveyb on 20/04/2015.
 */
public class MedicinalPlant extends Plant {
    int healthGain;

    public MedicinalPlant(int x, int y) {
        super(x,y);
        healthGain = 10 + random.nextInt(11);

    }

    public void step() {
        healthGain += random.nextInt(3);
    }
}
