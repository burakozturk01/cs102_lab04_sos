import javax.swing.*;
import java.awt.*;

public class SOSGUIPanel extends JPanel {
    // properties
    private SOS sos;

    private String p1, p2;
    private JLabel pl1, pl2;
    private int turnOfP, letter;

    private JRadioButton letterS, letterO;
    private ButtonGroup turnOf;

    // constructors
    public SOSGUIPanel(SOS sgame, String player1, String player2)
    {
        this.setLayout(new GridLayout(1,4));

        sos = sgame;
        p1 = player1; p2 = player2;
        letter = 0;

        // score Labels with default texts
        pl1 = new JLabel("[ " + p1 + ": 0 ]"); pl2 = new JLabel(p2 + ": 0");

        // letters selection buttons, actionlisteners and grouping
        letterS = new JRadioButton("S"); letterO = new JRadioButton("O");

        letterS.addActionListener(e -> {update(); letter = 1;});
        letterO.addActionListener(e -> {update(); letter = 2;});

        turnOf = new ButtonGroup(); turnOf.add(letterS); turnOf.add(letterO);

        // alignment on layout
        pl1.setHorizontalAlignment(SwingConstants.RIGHT);
        letterS.setHorizontalAlignment(SwingConstants.RIGHT);
        letterO.setHorizontalAlignment(SwingConstants.LEFT);
        pl2.setHorizontalAlignment(SwingConstants.LEFT);

        pl1.setVerticalAlignment(SwingConstants.NORTH);
        letterS.setVerticalAlignment(SwingConstants.NORTH);
        letterO.setVerticalAlignment(SwingConstants.NORTH);
        pl2.setVerticalAlignment(SwingConstants.NORTH);

        this.add(pl1);
        this.add(letterS);
        this.add(letterO);
        this.add(pl2);
    }

    // methods
    public void update()
    {
        // updating scores
        turnOfP = sos.getTurn();
        pl1.setText(p1 + ": " + sos.getPlayerScore1());
        pl2.setText(p2 + ": " + sos.getPlayerScore2());

        // showing turn of player with "[]"
        if (turnOfP == 1) {
            pl1.setText("[ "+pl1.getText()+" ]");
        }
        else if (turnOfP == 2) {
            pl2.setText("[ "+pl2.getText()+" ]");
        }
    }

    public int getLetter() {return letter;} // 1 for 'S', 2 for 'O' and 0 for "not selected yet"

    // get methods for player names
    public String getP1() {return p1;}
    public String getP2() {return p2;}
}
