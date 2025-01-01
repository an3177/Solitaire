import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Solitaire {
    static public DeckPile deckPile;
    static public DiscardPile discardPile;
    static public TablePile tableau[];
    static public SuitPile suitPile[];
    static public CardPile allPiles[];

    public Solitaire() {
        JFrame window = new SolitaireFrame();
        init();
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void init() {
		// Arrays for piles
		allPiles = new CardPile[13];
		suitPile = new SuitPile[4];
		tableau = new TablePile[7];
	
		// Initialize discard pile first
		allPiles[1] = discardPile = new DiscardPile(268, 30);
	
		// Initialize other decks
		allPiles[0] = deckPile = new DeckPile(335, 30, discardPile);
	
		for (int i = 0; i < 4; i++) {
			allPiles[2 + i] = suitPile[i] = new SuitPile(15 + (Card.width + 10) * i, 30);
		}
		for (int i = 0; i < 7; i++) {
			allPiles[6 + i] = tableau[i] = new TablePile(15 + (Card.width + 5) * i, Card.height + 35, i + 1);
		}
	}
	

    private class SolitaireFrame extends JFrame {
        public SolitaireFrame() {
            setSize(400, 500);
            setTitle("Solitaire Game");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new BorderLayout());

            // Add Restart Button
            JButton restartButton = new JButton("New Game");
            restartButton.addActionListener(e -> {
                init();
                repaint();
            });
            add(restartButton, BorderLayout.SOUTH);

            // Add Mouse Listener
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    int x = e.getX();
                    int y = e.getY();
                    for (int i = 0; i < 13; i++) {
                        if (allPiles[i].includes(x, y)) {
                            allPiles[i].select(x, y);
                            repaint();
                        }
                    }
                }
            });
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            for (int i = 0; i < 13; i++) {
                allPiles[i].display(g);
            }
        }
    }

	//Run code
    public static void main(String[] args) {
        Solitaire myFrame = new Solitaire();
    }
}
