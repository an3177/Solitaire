class SuitPile extends CardPile {

    // Constructor to initialize SuitPile at a specific position
    SuitPile(int x, int y) {
        super(x, y);
    }

    // Determine if the given card can be placed in this SuitPile
    @Override
    public boolean canTake(Card aCard) {
        if (isEmpty()) {
            return aCard.rank() == 0; 
        }
        Card topCard = top();
        return aCard.suit() == topCard.suit() && aCard.rank() == topCard.rank() + 1;
    }
}

