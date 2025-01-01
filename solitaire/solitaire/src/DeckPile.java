import java.util.Random;

class DeckPile extends CardPile {
    private DiscardPile discardPile;


//Constructor, location and adds cards to deck
    DeckPile(int x, int y, DiscardPile discardPile) {
        super(x, y);
        try {
            this.discardPile = discardPile;

            CardPile tempPile = new CardPile(x, y);
            for (int suit = 0; suit < 4; suit++) {
                for (int rank = 0; rank < 13; rank++) {
                    tempPile.addCard(new Card(suit, rank));
                }
            }

            // Start a new thread to shuffle the deck
            ShuffleThread shuffleThread = new ShuffleThread(tempPile);
            shuffleThread.start();

            // Wait for the thread to finish
            shuffleThread.join();

            // Transfer shuffled cards to this pile
            while (!tempPile.isEmpty()) {
                addCard(tempPile.pop());
            }
        } catch (InterruptedException ex) {
            System.err.println("Thread interrupted during shuffle: " + ex.getMessage());
        } catch (IndexOutOfBoundsException ex) {
            System.err.println("Error during shuffle: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Unexpected error during deck initialization: " + ex.getMessage());
        }
    }

    // Inner class for shuffle thread
    private class ShuffleThread extends Thread {
        private final CardPile pile;

        ShuffleThread(CardPile pile) {
            this.pile = pile;
        }

        @Override
        public void run() {
            try {
                Random generator = new Random();
                for (int i = pile.thePile.size() - 1; i > 0; i--) {
                    int j = generator.nextInt(i + 1);
                    Card temp = pile.thePile.get(i);
                    pile.thePile.set(i, pile.thePile.get(j));
                    pile.thePile.set(j, temp);
                }
            } catch (Exception ex) {
                System.err.println("Error in shuffle thread: " + ex.getMessage());
            }
        }
    }

// Recycle cards from the discard pile back into the deck and add the card
    @Override
    public void select(int tx, int ty) {
        if (isEmpty()) {
            while (!discardPile.isEmpty()) {
                addCard(discardPile.pop());
            }
            System.out.println("Deck reshuffled from the discard pile.");
            return;
        }

        // Move top card to the discard pile
        discardPile.addCard(pop());
    }
}
