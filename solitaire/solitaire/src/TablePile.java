import java.awt.*;
import java.util.ArrayList;

class TablePile extends CardPile {

    // Initialize the parent class with coordinates and add cards to table
    TablePile(int x, int y, int c) {
        super(x, y); 


        for (int i = 0; i < c; i++) {
            Card card = Solitaire.deckPile.pop();
            addCard(card);
        }

    
        if (!isEmpty()) {
            top().flip();
        }
    }
// Only Kings can be placed on an empty pile, add cards in decreasing and alternate colors
    @Override
    public boolean canTake(Card aCard) {
        try {
            if (isEmpty()) {
                if (aCard.rank() != 12) { 
                    throw new IllegalArgumentException("Only Kings can be placed on an empty Tableau pile.");
                }
                return true;
            }
    
            Card topCard = top();
            if (aCard.color() == topCard.color()) {
                throw new IllegalArgumentException("Cards must alternate colors.");
            }
    
            if (aCard.rank() != topCard.rank() - 1) {
                throw new IllegalArgumentException("Card ranks must decrease sequentially.");
            }
    
            return true; // The move is valid
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false; // Invalid move
        }
    }    

    //Check if card is included in range
    @Override
    public boolean includes(int tx, int ty) {
        return x <= tx && tx <= x + Card.width && y <= ty;
    }

    //Search for specific card
    public Card search(Card targetCard) {  
        for (Card card : thePile) {  
           if (card.equals(targetCard)) {  
             return card;  
           }  
        }  
        return null;  
     }
     
     // Find empty table
    public int findEmptyTableau() {
        for (int i = 0; i < Solitaire.tableau.length; i++) {
            if (Solitaire.tableau[i].isEmpty()) {
                return i;
            }
        }
        return -1; 
    }

    // Check if the card is a King
    public boolean isValidKingMove(Card card) {
    if (card.rank() != 12) { 
        return false;
    }

    return findEmptyTableau() != -1;
}

    
    // Do nothing if the pile is empty,flip the card and add to suit pile, add king to empty pile
    @Override
    public void select(int tx, int ty) {
        if (isEmpty()) {
            return; 
        }

        int cardIndex = (ty - y) / 35;
        if (cardIndex < 0 || cardIndex >= thePile.size()) {  
            return; 
        }

        Card clickedCard = thePile.get(cardIndex);
        Card searchedCard = search(clickedCard);  
        
        if (searchedCard != null) {  
            // Highlight the searched card  
            for (Card card : thePile) {  
              card.setHighlighted(false);  
            }  
            searchedCard.setHighlighted(true);  
         } 

        ArrayList<Card> movableCards = new ArrayList<>();  
        for (int i = cardIndex; i < thePile.size(); i++) {  
            movableCards.add(thePile.get(i));  
        } 
        
        for (int i = 0; i < 7; i++) {  
            if (Solitaire.tableau[i] != this && Solitaire.tableau[i].canTake(clickedCard)) {  
              for (Card card : movableCards) {  
                 Solitaire.tableau[i].addCard(card);  
              }  
              for (int j = 0; j < movableCards.size(); j++) {  
                 thePile.remove(thePile.size() - 1);  
              }  
              return;  
            }  
        }  
        

        Card topCard = top();
        if (!topCard.faceUp()) {
            topCard.flip();
            return;
        }

  
        topCard = pop();
        for (int i = 0; i < 4; i++) {
            if (Solitaire.suitPile[i].canTake(topCard)) {
                Solitaire.suitPile[i].addCard(topCard);
                return;
            }
        }

        
        if (isValidKingMove(topCard)) {
            int emptyPileIndex = findEmptyTableau(); 
            if (emptyPileIndex != -1) {
                Solitaire.tableau[emptyPileIndex].addCard(topCard);
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

    @Override
    public void display(Graphics g) {
        int localY = y;
        for (Card aCard : thePile) {
            aCard.draw(g, x, localY);
            if (aCard.isHighlighted()) {  
                g.setColor(Color.YELLOW);  
                g.drawRect(x, localY, Card.width, Card.height);  
            }  
            localY += 35;
        }
    }
}
    