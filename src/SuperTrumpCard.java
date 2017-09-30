import java.util.InputMismatchException;

class SuperTrumpCard extends Card {
    SuperTrumpCard(String name, String instruction) {
        super(name, instruction);
    }

    static void playCard(Card card, boolean hasMagnetite, Player player) {
        switch (card.getName()) {
            case "The Miner": {
                MineralSupertrumps.category = 5;
                MineralSupertrumps.trumpCardPlayed();
                MineralSupertrumps.turnFinished();
                break;
            }
            case "The Petrologist": {
                MineralSupertrumps.category = 4;
                MineralSupertrumps.trumpCardPlayed();
                MineralSupertrumps.turnFinished();
                break;
            }
            case "The Gemmologist": {
                MineralSupertrumps.category = 1;
                MineralSupertrumps.trumpCardPlayed();
                MineralSupertrumps.turnFinished();
                break;
            }
            case "The Mineralogist": {
                MineralSupertrumps.category = 3;
                MineralSupertrumps.trumpCardPlayed();
                MineralSupertrumps.turnFinished();
                break;
            }
            case "The Geophysicist": {
                MineralSupertrumps.category = 2;
                if (hasMagnetite) {
                    System.out.println("Player " + player.getID() + "won the hand by playing combo of \"The Geophysicist\" and \"Magnetite\"");
                    MineralSupertrumps.players.remove(player);
                }
                MineralSupertrumps.trumpCardPlayed();
                MineralSupertrumps.turnFinished();
                break;
            }
            case "The Geologist": {
                MineralSupertrumps.frame.categoryPanel();
                break;
            }
        }
    }
}
