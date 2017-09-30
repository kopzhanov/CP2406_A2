import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Player {
    private static int counter = 1;
    private int id = 1;
    private ArrayList<Card> hand = new ArrayList<>();
    private boolean pass;

    Player() {
        this.id = counter++;
    }

    int getID() {
        return id;
    }

    boolean isPass() {
        return pass;
    }

    void setPass(boolean pass) {
        this.pass = pass;
    }

    void addCard(Card card) {
        this.hand.add(card);
    }

    void removeCard(Card card) {
        this.hand.remove(card);
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    void passTurn() {
        pass = true;
        this.addCard(MineralSupertrumps.deck.get(0));
        MineralSupertrumps.deck.remove(0);
    }

    boolean hasMagnetite() {
        boolean isMagnetite = false;
        for (Card card : hand) {
            if (card.getName().equals("Magnetite"))
                isMagnetite = true;
        }
        return isMagnetite;
    }
}
