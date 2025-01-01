import java.awt.*;
import java.util.ArrayList;

public class CardPile {
    protected int x, y;
    protected ArrayList<Card> thePile;

    CardPile(int xl, int yl) {
        x = xl;
        y = yl;
        thePile = new ArrayList<>();
    }

	// returns top card if not empty
    public final Card top() {
        if (thePile.isEmpty()) {
			return null;
		}
        return thePile.get(thePile.size() - 1);
    }

		// checks if it is empty
    public final boolean isEmpty() {
        return thePile.isEmpty();
    }

// removes and returns top card if not empty
    public final Card pop() {
		try {
			if (isEmpty()) {
				throw new Exception("Cannot pop a card from an empty pile.");
			}
			return thePile.remove(thePile.size() - 1);
		} 
		catch (Exception except) {
			System.out.println(except.getMessage());
			return null; 
		}
	}	

	//adds cards
    public void addCard(Card aCard) {
        thePile.add(aCard);
    }

	public boolean includes(int tx, int ty) {
		// Check if the given coordinates (tx, ty) are within the bounds of the card pile
		return x <= tx && tx <= x + Card.width && y <= ty && ty <= y + Card.height;
	}

	public void select(int tx, int ty) {
		// do nothing
	}


	public void display(Graphics g) {
		g.setColor(Color.blue);
		if (isEmpty()) {
			g.drawRect(x, y, Card.width, Card.height);
		} else {
			top().draw(g, x, y);
		}
	}

	//default value of false for taking a card
	public boolean canTake (Card aCard) {
		return false; 
	}
}