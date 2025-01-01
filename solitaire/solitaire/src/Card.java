import java.awt.*;

public class Card{

    // Public constants for card dimensions and suits
    final public static int width = 50;
    final public static int height = 70;
    final public static int heart = 0;
    final public static int spade = 1;
    final public static int diamond = 2;
    final public static int club = 3;
	private boolean highlighted = false;  

    // Internal data fields for rank and suit
    private boolean faceup;
    private int r; 
    private int s;

    // Constructor
    Card(int sv, int rv) {
        this.s = sv;  
        this.r = rv;     
        this.faceup = false;
    }

    // Access rank of the card
    public int rank() {
        return r; 
    }

    // Access suit of the card
    public int suit() {
        return s;
    }

    // Check if the card is face-up
    public boolean faceUp() {
        return faceup;
    }

    // Flip the card 
    public void flip() {
        faceup = !faceup;
    }

    // Return the color of the card
    public Color color() {
        if (s == heart || s == diamond) {
            return Color.red;
        } else {
            return Color.black; 
        }
    }

	//Set the highlight
	public void setHighlighted(boolean highlighted) {  
	   this.highlighted = highlighted;  
	}  
   
	//Check if highlighted
	public boolean isHighlighted() {  
	   return highlighted;  
	}  

    // Draw the card
    public void draw(Graphics g, int x, int y) {
        String names[] = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        // Clear rectangle, draw border
        g.clearRect(x, y, width, height);
        g.setColor(Color.blue);
        g.drawRect(x, y, width, height);

        // Draw body of card
        g.setColor(color());
        if (faceUp()) {
            g.drawString(names[rank()], x + 3, y + 15);
            if (suit() == heart) {
                g.drawLine(x + 25, y + 30, x + 35, y + 20);
                g.drawLine(x + 35, y + 20, x + 45, y + 30);
                g.drawLine(x + 45, y + 30, x + 25, y + 60);
                g.drawLine(x + 25, y + 60, x + 5, y + 30);
                g.drawLine(x + 5, y + 30, x + 15, y + 20);
                g.drawLine(x + 15, y + 20, x + 25, y + 30);
            } else if (suit() == spade) {
                g.drawLine(x + 25, y + 20, x + 40, y + 50);
                g.drawLine(x + 40, y + 50, x + 10, y + 50);
                g.drawLine(x + 10, y + 50, x + 25, y + 20);
                g.drawLine(x + 23, y + 45, x + 20, y + 60);
                g.drawLine(x + 20, y + 60, x + 30, y + 60);
                g.drawLine(x + 30, y + 60, x + 27, y + 45);
            } else if (suit() == diamond) {
                g.drawLine(x + 25, y + 20, x + 40, y + 40);
                g.drawLine(x + 40, y + 40, x + 25, y + 60);
                g.drawLine(x + 25, y + 60, x + 10, y + 40);
                g.drawLine(x + 10, y + 40, x + 25, y + 20);
            } else if (suit() == club) {
                g.drawOval(x + 20, y + 25, 10, 10);
                g.drawOval(x + 25, y + 35, 10, 10);
                g.drawOval(x + 15, y + 35, 10, 10);
                g.drawLine(x + 23, y + 45, x + 20, y + 55);
                g.drawLine(x + 20, y + 55, x + 30, y + 55);
                g.drawLine(x + 30, y + 55, x + 27, y + 45);
            }
        } else { // Face down
            g.drawLine(x + 15, y + 5, x + 15, y + 65);
            g.drawLine(x + 35, y + 5, x + 35, y + 65);
            g.drawLine(x + 5, y + 20, x + 45, y + 20);
            g.drawLine(x + 5, y + 35, x + 45, y + 35);
            g.drawLine(x + 5, y + 50, x + 45, y + 50);
        }
    }

}