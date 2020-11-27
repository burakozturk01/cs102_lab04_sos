import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A simple Java class!
 */
public class SOSCanvas extends JPanel
{
    // properties
    private SOS sos;
    private SOSGUIPanel gp;
    private JLabel[][] letters; // letters [y][x] grid
    private boolean gameOver;

    private int dimension; // n*n board
    private int w,h; // width and height of Panel
    private int[] x; // reference points for drawing the grid
    private int[] y; // reference points for drawing the grid

    // constructors
    public SOSCanvas(SOS sgame, SOSGUIPanel gp)
    {
        super();

        this.gp = gp;
        gameOver = false;

        // Mouse listener for grid
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                Point pos = getMousePosition();

                // mouse click position
                double clkX = pos.getX();
                double clkY = pos.getY();
                char ltr;

                // grid x,y index in range lx, ly = [0, dimension-1]
                int LX,LY;
                LX = -1; LY = -1;

                if (gp.getLetter() > 0)
                {
                    // determine lx
                    for (int i = 0; i < x.length; i++)
                    {
                        if (clkX < x[i]) {
                            LX = i;
                            break;
                        }
                        else if (clkX < w)
                            LX = i+1;
                    }

                    // determine ly
                    for (int i = 0; i < y.length; i++)
                    {
                        if (clkY < y[i]) {
                            LY = i;
                            break;
                        }
                        else if (clkY < h)
                            LY = i+1;
                    }

                    // if a letter selected, play with it and update SOSGUIPanel
                    if (gp.getLetter() == 1) ltr = 's'; else ltr = 'o';
                    play(ltr, LX, LY);
                    gp.update();
                }
                else
                    System.out.println("No letter selected");

                gameOver = sos.isGameOver();

                // isGameOver check
                if (gameOver)
                    gameOver();
            }

            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });

        // SOS instance and grid dimensions
        sos = sgame;
        dimension = sos.getDimension();

        this.setLayout(new GridLayout(dimension, dimension));

        // instanciating and setting letter Labels
        letters = new JLabel[dimension][dimension];

        for (int y = 0; y < letters.length; y++)
        {
            for (int x = 0; x < letters[y].length; x++)
            {
                letters[y][x] = new JLabel("");
                letters[y][x].setHorizontalAlignment(SwingConstants.CENTER);
                letters[y][x].setFont(new Font("Verdana", Font.BOLD, 40));
                this.add(letters[y][x]);
            }
        }
    }

    // methods
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        setBackground(Color.YELLOW);

        x = new int[dimension - 1];
        y = new int[dimension - 1];

        w = this.getWidth();
        h = this.getHeight();

        // x-axis reference points
        for (int i = 0; i < x.length; i++)
            x[i] = Math.round(w / dimension) * (i+1);

        // y-axis reference points
        for (int i = 0; i < y.length; i++)
            y[i] = Math.round(h / dimension) * (i+1);

        // drawing lines
        g.setColor(Color.BLACK);
        for (int i = 0; i < x.length; i++)
        {
            g.drawLine(x[i], 0, x[i], h);
            g.drawLine(0, y[i], w, y[i]);
        }
    }

    public int play(char letter, int x, int y)
    {

        // playing a letter to SOS instance
        int out = sos.play(letter, x+1, y+1);

        // writing result to grid
        if (out > -1) {
            letters[y][x].setText((letter + "").toUpperCase());
        }

        // not used
        return out;
    }

    public void gameOver()
    {
        // scores
        int sc1 = sos.getPlayerScore1();
        int sc2 = sos.getPlayerScore2();
        String msg = "";

        // deciding who won
             if (sc1 > sc2)
            msg = "Winner is " + gp.getP1() + "!";
        else if (sc1 < sc2)
            msg = "Winner is " + gp.getP2() + "!";
        else
            msg = "It's a draw!";

        // Message pane
        int res = JOptionPane.showOptionDialog(null, msg, "Game Over!", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, null, null);

        // disposing main Frame
        if (res < 10) {
            JFrame window = (JFrame) SwingUtilities.getWindowAncestor(this);
            window.dispose();
        }
    }
}