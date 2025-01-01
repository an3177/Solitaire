class DiscardPile extends CardPile {

    // Constructor to initialize discard pile at a specific position
    DiscardPile(int x, int y) {
        super(x, y);
    }

	// Method to sort the discard pile using insertion sort
    public void sortPile() {
        for (int i = 1; i < thePile.size(); i++) {
            Card key = thePile.get(i);
            int j = i - 1;

            while (j >= 0 && compareCards(thePile.get(j), key) > 0) {
                thePile.set(j + 1, thePile.get(j));
                j--;
            }
            thePile.set(j + 1, key);
        }
        System.out.println("Discard pile sorted.");
    }

    // Helper method to compare two cards
    private int compareCards(Card card1, Card card2) {
        if (card1.rank() != card2.rank()) {
            return card1.rank() - card2.rank();
        }
        return card1.suit() - card2.suit();
    }

    // Add a card to the discard pile, flipping it face-up
	@Override
    public void addCard(Card aCard) {
        aCard.flip(); 
        super.addCard(aCard); 
    }

    // Select method to handle card transfers
	@Override
    public void select(int tx, int ty) {
        if (isEmpty()) {
            return;
        }
        Card topCard = pop();
        for (int i = 0; i < 4; i++) {
            if (Solitaire.suitPile[i].canTake(topCard)) {
                Solitaire.suitPile[i].addCard(topCard);
                return;
            }
        }
        for (int i = 0; i < 7; i++) {
            if (Solitaire.tableau[i].canTake(topCard)) {
                Solitaire.tableau[i].addCard(topCard);
                return;
            }
        }
 
        addCard(topCard);
    }
}