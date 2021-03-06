import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class MineralSupertrumps {
    private final static int INITIALHAND = 8;
    private final static String[] RANKING_CLEAVAGE = {"none", "poor/none", "1 poor", "2 poor", "1 good", "1 good/1 poor", "2 good",
            "3 good", "1 perfect", "1 perfect/1 good", "1 perfect/2 good", "2 perfect/1 good", "3 perfect", "4 perfect", "6 perfect"};
    private final static String[] RANKING_CRUSTAL_ABUNDANCE = {"ultratrace", "trace", "low", "moderate", "high", "very high"};
    private final static String[] RANKING_ECONOMIC_VALUE = {"trivial", "low", "moderate", "high", "very high", "i'm rich!"};

    static int roundNumber = 1;
    static boolean firstTurn = true;
    static ArrayList<Card> deck = new ArrayList<>();
    static Card lastPlayedCard = null;
    static ArrayList<Player> players = new ArrayList<>();
    private static int dealerIndex;
    private static int turnPlayerIndex;
    static int category = 0;

    static GameFrame frame;

    public static void main(String[] args) {
        parseCards();
        shuffleDeck();

        frame = new GameFrame("Mineral Supertrumps");
        frame.playerAmountPanel();
    }

    static void dealCards() {
        for (int i = 0; i < INITIALHAND; i++) {
            for (Player player : players) {
                player.addCard(deck.get(0));
                deck.remove(0);
            }
        }
        startGame();
    }

    static void setPlayers(int playerAmount) {
        for (int i = 0; i < playerAmount; i++) {
            Player player = new Player();
            players.add(player);
        }
        dealerIndex = ThreadLocalRandom.current().nextInt(0, playerAmount - 1);
    }

    private static void shuffleDeck() {
        Collections.shuffle(deck);
    }

    private static void parseCards() {
        String file = "card.txt";
        BufferedReader br = null;
        String line;
        String delimiter = ",";

        try {
            br = new BufferedReader(new FileReader(file));
            br.readLine(); // To read over first line of column titles
            while ((line = br.readLine()) != null) {
                String[] card = line.split(delimiter);
                deck.add(new Card(card[0], Double.parseDouble(card[1]), Double.parseDouble(card[2]), card[3], card[4], card[5]));
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File \"" + file + "\" has not found");
        } catch (IOException e) {
            System.out.println("General I/O exception, restart the program");
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.out.println("General I/O exception, restart the program");
            }
        }
        //Since the are no supertrump cards in the given file, they are added manually below
        deck.add(new SuperTrumpCard("The Miner", "Change trumps category to \"Economic value\""));
        deck.add(new SuperTrumpCard("The Petrologist", "Change trumps category to \"Crustal abundance\""));
        deck.add(new SuperTrumpCard("The Gemmologist", "Change trumps category to \"Hardness\""));
        deck.add(new SuperTrumpCard("The Mineralogist", "Change trumps category to \"Cleavage\""));
        deck.add(new SuperTrumpCard("The Geophysicist", "Change trumps category to \"Specific gravity\", or throw \"Magnetite\""));
        deck.add(new SuperTrumpCard("The Geologist", "Change trumps category of your choice"));
    }

    private static void startGame() {
        nextPlayer(dealerIndex);
        frame.updatePanel(players.get(turnPlayerIndex));
    }

    static void turnFinished() {
        if (players.get(turnPlayerIndex).getHand().size() == 0) {
            players.remove(turnPlayerIndex);
        }

        nextPlayer(turnPlayerIndex);
        frame.remove(frame.hand);

        if (roundEnd()) {
            roundNumber++;
            lastPlayedCard = null;
            for (Player player : players) {
                player.setPass();
            }
            firstTurn = true;
        }

        if (players.size() != 1) {
            frame.updatePanel(players.get(turnPlayerIndex));
        } else {
            frame.endGame();
        }
    }

    private static void nextPlayer(int index) {
        // return given int as index for player array
        if (players.size() - 1 <= index) {
            turnPlayerIndex = 0;
        } else {
            turnPlayerIndex = index + 1;
        }
        if (players.get(turnPlayerIndex).isPass()) {
            nextPlayer(turnPlayerIndex);
        }
    }

    private static boolean roundEnd() {
        int playerPassed = 0;
        for (Player player : players) {
            if (player.isPass()) {
                playerPassed++;
            }
        }
        return playerPassed == players.size() - 1;
    }

    static String getCategory() {
        switch (category) {
            case 1: {
                return "Hardness";
            }
            case 2: {
                return "Specific Gravity";
            }
            case 3: {
                return "Cleavage";
            }
            case 4: {
                return "Crustal Abundance";
            }
            case 5: {
                return "Economic Value";
            }
        }
        return "None";
    }

    static boolean validCard(Card card) {
        //if the card is not SuperTrumpCard
        if (card.getInstruction() == null && lastPlayedCard.getCleavage() != null && lastPlayedCard.getAbundance() != null && lastPlayedCard.getEcoValue() != null) {
            switch (category) {
                case 1: {
                    return (card.getHardness() > lastPlayedCard.getHardness());
                }
                case 2: {
                    return (card.getGravity() > lastPlayedCard.getGravity());
                }
                case 3: {
                    int firstIndex = Arrays.asList(RANKING_CLEAVAGE).indexOf(card.getCleavage());
                    int secondIndex = Arrays.asList(RANKING_CLEAVAGE).indexOf(lastPlayedCard.getCleavage());
                    return (firstIndex > secondIndex);
                }
                case 4: {
                    int firstIndex = Arrays.asList(RANKING_CRUSTAL_ABUNDANCE).indexOf(card.getAbundance());
                    int secondIndex = Arrays.asList(RANKING_CRUSTAL_ABUNDANCE).indexOf(lastPlayedCard.getAbundance());
                    return (firstIndex > secondIndex);
                }
                case 5: {
                    int firstIndex = Arrays.asList(RANKING_ECONOMIC_VALUE).indexOf(card.getEcoValue());
                    int secondIndex = Arrays.asList(RANKING_ECONOMIC_VALUE).indexOf(lastPlayedCard.getEcoValue());
                    return (firstIndex > secondIndex);
                }
            }
        } else {
            return true;
        }
        return false;
    }

    static void trumpCardPlayed() {
        for (Player player : players) {
            if (player.isPass()) {
                player.setPass();
            }
        }
    }
}
