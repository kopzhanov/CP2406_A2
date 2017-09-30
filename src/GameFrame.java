import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame implements ActionListener {

    private final Dimension CARD_SIZE_DIMENSION = new Dimension(200, 300);

    JPanel hand;
    private JPanel table;
    private JPanel playerAmountChoice;
    private JPanel categoryChoice;
    private JLabel lastPlayedCard;
    private JLabel deck;
    private JLabel status;

    GameFrame(String title) throws HeadlessException {
        super(title);

        table = new JPanel(new BorderLayout());


        lastPlayedCard = new JLabel("", SwingConstants.CENTER);
        lastPlayedCard.setPreferredSize(new Dimension(200, 320));
        lastPlayedCard.setText("Last played card");
        lastPlayedCard.setHorizontalTextPosition(JLabel.CENTER);
        lastPlayedCard.setVerticalTextPosition(JLabel.BOTTOM);

        status = new JLabel("Mineral Supertrumps", SwingConstants.CENTER);
        status.setOpaque(true);
        status.setBackground(new Color(230, 219, 224));

        ImageIcon cardbackImage = getScaledImage(new ImageIcon("images/Slide65.jpg"));
        deck = new JLabel(cardbackImage);
        deck.setText("Cards left: " + MineralSupertrumps.deck.size());
        deck.setHorizontalTextPosition(JLabel.CENTER);
        deck.setVerticalTextPosition(JLabel.BOTTOM);
        deck.setPreferredSize(new Dimension(200, 320));


        table.add(status, "South");
        table.add(deck, "Center");
        table.setBorder(new EmptyBorder(10, 10, 10, 10));

        add(table, BorderLayout.EAST);

        setBackground(new Color(239, 237, 227));
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(120, 70);
        setResizable(false);
        setVisible(true);
    }

    void playerAmountPanel() {
        playerAmountChoice = new JPanel(new BorderLayout());

        JLabel prompt = new JLabel("How many players?");
        JButton three = new JButton("3");
        JButton four = new JButton("4");
        JButton five = new JButton("5");

        prompt.setFont(new Font("Serif", Font.PLAIN, 20));
        three.setPreferredSize(new Dimension(200, 200));
        four.setPreferredSize(new Dimension(200, 200));
        five.setPreferredSize(new Dimension(200, 200));

        three.addActionListener(this);
        four.addActionListener(this);
        five.addActionListener(this);

        playerAmountChoice.add(prompt, "North");
        playerAmountChoice.add(three, "West");
        playerAmountChoice.add(four, "Center");
        playerAmountChoice.add(five, "East");

        add(playerAmountChoice, BorderLayout.CENTER);
        pack();
        validate();
    }

    void categoryPanel() {
        categoryChoice = new JPanel(new GridLayout(5, 1));

        JButton hardness = new JButton("Hardness");
        JButton gravity = new JButton("Specific Gravity");
        JButton cleavage = new JButton("Cleavage");
        JButton abundance = new JButton("Crustal Abundance");
        JButton ecoValue = new JButton("Economic Value");

        hardness.addActionListener(e -> {
            MineralSupertrumps.category = 1;
            remove(categoryChoice);
            pack();
            MineralSupertrumps.trumpCardPlayed();
            MineralSupertrumps.turnFinished();
        });
        gravity.addActionListener(e -> {
            MineralSupertrumps.category = 2;
            remove(categoryChoice);
            pack();
            MineralSupertrumps.trumpCardPlayed();
            MineralSupertrumps.turnFinished();
        });
        cleavage.addActionListener(e -> {
            MineralSupertrumps.category = 3;
            remove(categoryChoice);
            pack();
            MineralSupertrumps.trumpCardPlayed();
            MineralSupertrumps.turnFinished();
        });
        abundance.addActionListener(e -> {
            MineralSupertrumps.category = 4;
            remove(categoryChoice);
            pack();
            MineralSupertrumps.trumpCardPlayed();
            MineralSupertrumps.turnFinished();
        });
        ecoValue.addActionListener(e -> {
            MineralSupertrumps.category = 5;
            remove(categoryChoice);
            pack();
            MineralSupertrumps.trumpCardPlayed();
            MineralSupertrumps.turnFinished();
        });
        categoryChoice.add(hardness);
        categoryChoice.add(gravity);
        categoryChoice.add(cleavage);
        categoryChoice.add(abundance);
        categoryChoice.add(ecoValue);

        add(categoryChoice, "West");
        pack();
    }

    private void categoryPanel(Card card) {
        categoryChoice = new JPanel(new GridLayout(5, 1));

        JButton hardness = new JButton("Hardness: " + card.getHardness());
        JButton gravity = new JButton("Specific Gravity: " + card.getGravity());
        JButton cleavage = new JButton("Cleavage: " + card.getCleavage());
        JButton abundance = new JButton("Crustal Abundance: " + card.getAbundance());
        JButton ecoValue = new JButton("Economic Value: " + card.getEcoValue());

        hardness.addActionListener(e -> {
            MineralSupertrumps.firstTurn = false;
            MineralSupertrumps.category = 1;
            remove(categoryChoice);
            pack();
            MineralSupertrumps.turnFinished();
        });
        gravity.addActionListener(e -> {
            MineralSupertrumps.firstTurn = false;
            MineralSupertrumps.category = 2;
            remove(categoryChoice);
            pack();
            MineralSupertrumps.turnFinished();
        });
        cleavage.addActionListener(e -> {
            MineralSupertrumps.firstTurn = false;
            MineralSupertrumps.category = 3;
            remove(categoryChoice);
            pack();
            MineralSupertrumps.turnFinished();
        });
        abundance.addActionListener(e -> {
            MineralSupertrumps.firstTurn = false;
            MineralSupertrumps.category = 4;
            remove(categoryChoice);
            pack();
            MineralSupertrumps.turnFinished();
        });
        ecoValue.addActionListener(e -> {
            MineralSupertrumps.firstTurn = false;
            MineralSupertrumps.category = 5;
            remove(categoryChoice);
            pack();
            MineralSupertrumps.turnFinished();
        });

        categoryChoice.add(hardness);
        categoryChoice.add(gravity);
        categoryChoice.add(cleavage);
        categoryChoice.add(abundance);
        categoryChoice.add(ecoValue);

        add(categoryChoice, "West");
        pack();
    }

    void updatePanel(Player player) {
        //If there is no last played card in a round
        if (MineralSupertrumps.lastPlayedCard != null) {
            lastPlayedCard.setIcon(getScaledImage(new ImageIcon("images/" + MineralSupertrumps.lastPlayedCard.getSlideID() + ".jpg")));
            table.add(lastPlayedCard, "North");
        } else {
            table.remove(lastPlayedCard);
        }


        deck.setText("Cards left: " + MineralSupertrumps.deck.size());
        status.setText("<html>Round: " + MineralSupertrumps.roundNumber + "<br>Category: " + MineralSupertrumps.getCategory() + "<br>It's player " + player.getID() + " turn</html>"
        );

        //Decision was to use 4 columns as a default, rows are dynamic
        int col = 4;
        int row = (player.getHand().size() + 1) / col;
        if ((player.getHand().size() + 1) % col != 0) {
            row++;
        }

        hand = new JPanel(new GridLayout(row, col, 10, 10));
        hand.setBorder(new EmptyBorder(10, 10, 10, 10));


        //Pass button to pass a turn
        JButton pass = new JButton("Pass");
        pass.addActionListener(e -> {
            player.passTurn();
            MineralSupertrumps.turnFinished();
        });
        pass.setFont(new Font("Serif", Font.PLAIN, 20));


        JButton[] cards = new JButton[player.getHand().size()];

        for (int x = 0; x < player.getHand().size(); x++) {
            cards[x] = new JButton(getScaledImage(new ImageIcon("images/" + player.getHand().get(x).getSlideID() + ".jpg")));
            cards[x].setPreferredSize(CARD_SIZE_DIMENSION);
            cards[x].setText(player.getHand().get(x).getName());

            //If the cards are lower than the value of previous card category -> make this card unclickable
            if (MineralSupertrumps.lastPlayedCard != null && !MineralSupertrumps.firstTurn) {
                if (!MineralSupertrumps.validCard(player.getHand().get(x))) {
                    cards[x].setEnabled(false);
                }
            }

            cards[x].addActionListener(e -> {
                status.setText("Player " + player.getID() + " has chosen \"" + e.getActionCommand() + "\" card");

                //Finds which card was chosen and saves it inside variable
                Card chosenCard = null;
                for (Card card : player.getHand()) {
                    if (card.getName().equals(e.getActionCommand())) {
                        chosenCard = card;
                    }
                }


                //when SuperTrump card played
                if (chosenCard != null && chosenCard.getInstruction() != null) {
                    if (MineralSupertrumps.firstTurn) {
                        MineralSupertrumps.firstTurn = false;
                    }
                    //if any trump card played make other cards unclickable
                    pass.setEnabled(false);
                    for (JButton button :
                            cards) {
                        button.setEnabled(false);
                    }
                    SuperTrumpCard.playCard(chosenCard, player.hasMagnetite(), player);
                }

                player.removeCard(chosenCard);

                assert chosenCard != null;
                //If card is not SuperTrump card
                if (chosenCard.getInstruction() == null) {
                    MineralSupertrumps.lastPlayedCard = chosenCard;
                }

                //If it is first turn of a round, choose a category and make other cards unclickable
                if (MineralSupertrumps.firstTurn && chosenCard.getInstruction() == null) {
                    pass.setEnabled(false);
                    for (JButton button :
                            cards) {
                        button.setEnabled(false);
                    }
                    categoryPanel(chosenCard);
                } else {
                    MineralSupertrumps.turnFinished();
                }
            });

            hand.add(cards[x]);
        }


        hand.add(pass);

        add(hand, BorderLayout.CENTER);
        validate();
        repaint();
        pack();
    }

    //Rescaling an image
    private ImageIcon getScaledImage(ImageIcon imgIcon) {
        Image image = imgIcon.getImage();
        Image newImg = image.getScaledInstance(200, 300, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //When a user choose player amount, set the player amount to an appropriate value
        if (e.getActionCommand().equals("3") || e.getActionCommand().equals("4") || e.getActionCommand().equals("5")) {
            String number = e.getActionCommand();
            switch (number) {
                case "3": {
                    MineralSupertrumps.setPlayers(3);
                    break;
                }
                case "4": {
                    MineralSupertrumps.setPlayers(4);
                    break;
                }
                case "5": {
                    MineralSupertrumps.setPlayers(5);
                    break;
                }
            }
            remove(playerAmountChoice);
            validate();
            repaint();
            pack();

            //called from this class to avoid multiple threads and so errors
            MineralSupertrumps.dealCards();
        }
    }

    //If there is only 1 player left, program finishes the game
    void endGame() {
        remove(hand);
        table.remove(lastPlayedCard);
        status.setText("Player " + MineralSupertrumps.players.get(0).getID() + " is the loser of this game!");
        JButton exit = new JButton("Exit");
        exit.addActionListener(e -> dispose());
        table.add(deck, "North");
        table.add(status, "Center");
        table.add(exit, "South");
        pack();
        validate();
    }
}
