/**
 * Created by sdaveyb on 20/04/2015.
 */
public class EnergyPlant extends Plant{
    int energyGain;

    public EnergyPlant(int x, int y) {
        super(x,y);
        energyGain = 10 + random.nextInt(11);
    }

    public void step() {
        energyGain += random.nextInt(3);
    }
}
