
import sun.swing.SwingUtilities2;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

/**
 * Created by sdaveyb on 14/04/2015.
 */
public class SectionThreeMain extends JFrame {
    public static void main(String[] args) {
        World world = new World();
        world.spawnEnergyPlants(20);
        world.spawnMedicinalPlants(20);
        world.spawnComplexCreatures(5);
        SectionThreeMain main = new SectionThreeMain(world);
        main.setVisible(true);


    }

    public SectionThreeMain(World w) {
        setTitle("Scott Davey 1202354");
        setSize(440,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        View view = new View(w);
        add(view, BorderLayout.CENTER);

        JPanel controlPanel = new ControlPanel(view,w);
        add(controlPanel, BorderLayout.SOUTH);

    }

}
