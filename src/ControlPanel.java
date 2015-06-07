import javax.swing.*;

/**
 * Created by Scott on 19/04/2015.
 */
public class ControlPanel extends JPanel{
    World world;
    View view;

    public ControlPanel(View v, World w) {
        view = v;
        world = w;
        JButton nextMomentButton = new JButton("Next Moment");
        nextMomentButton.addActionListener( l -> {
            world.step();
            v.repaint();
        });
        add(nextMomentButton);
        world = w;
    }
}
