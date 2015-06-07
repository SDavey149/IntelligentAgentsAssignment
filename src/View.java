import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by Scott on 19/04/2015.
 */
public class View extends JComponent {
    World world;

    public View(World w) {
        world = w;
    }

    public void paintComponent(Graphics g) {
        List<Plant> plants = world.getPlants();
        List<Creature> creatures = world.getCreatures();

        for (Plant p: plants) {
            if (p instanceof MedicinalPlant)
                g.setColor(Color.pink);
            else
                g.setColor(Color.GREEN);
            if (!p.dead) {
                g.fill3DRect(p.xPos*20, p.yPos*20, 20, 20, true);
            }
        }

        g.setColor(Color.RED);
        for (Creature c: creatures) {
            if (!c.dead) {
                g.fill3DRect(c.xPos*20, c.yPos*20, 20, 20, true);
            }
        }
        g.setColor(Color.GRAY);
        for (int i = 1; i < 21; i++) {
            g.drawLine(i*20,0,i*20,20*20);
            g.drawLine(0, i*20, 20*20, i*20);
        }



    }

}
