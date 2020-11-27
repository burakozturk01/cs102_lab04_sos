import java.awt.*;

import javax.swing.*;

/**
 * Test class
 * @author Burak Ozturk
 * @version 27.11.2020
 */
public class Test
{
    public static void main( String[] args) {
        // constants

        // variables
        JFrame popFrame, f;
        String p1, p2;
        SOS game;
        SOSCanvas c;
        SOSGUIPanel gp;
        int grid;

        // program code
        System.out.println( "Start...");

        popFrame = new JFrame();

        // getting grid size from user as grid >= 3
        do {
            String tmp = JOptionPane.showInputDialog(popFrame,"Please enter grid size", null);
            if (tmp == null) {
                return;
            }
            try {
                grid = Integer.parseInt(tmp);
            }
            catch (Exception e){
                System.out.println("Wrong input");
                grid = 0;
            }
        } while ( grid < 3);

        // getting player names from user
        p1   = JOptionPane.showInputDialog(popFrame,"What is First Player's name?", null);
        if (p1 == null) {return;}
        p2   = JOptionPane.showInputDialog(popFrame,"What is Second Player's name?", null);
        if (p2 == null) {return;}

        // JFrame size can be changed, code is adaptive
        f = new JFrame();
        f.setSize(480,480);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Instantiations of SOS, SOSGUIPanel and SOSCanvas classes
        game = new SOS(grid);

        gp = new SOSGUIPanel(game, p1, p2);
        f.add(gp, BorderLayout.SOUTH);

        // Canvas needs SOSGUIPanel too for getting selected letter and player names
        c = new SOSCanvas(game, gp);
        f.add(c, BorderLayout.CENTER);

        f.setVisible(true);

        System.out.println( "End.");
    }

}