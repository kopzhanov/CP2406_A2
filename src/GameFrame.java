import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame implements ActionListener {

    final ImageIcon CARDBACK_IMAGE = getScaledImage(new ImageIcon("images/Slide65.jpg"));
    final Dimension CARD_SIZE_DIMENSION = new Dimension(200, 300);
    final int DEFAULT_COLUMNS = 3;

    JPanel playerAmountChoice;
    JPanel hand;
    JPanel table;
    JPanel categoryChoice;
    JLabel lastPlayedCard;
    JLabel deck;
    JLabel status;
    JLabel newRoundNotification;

    public GameFrame(String title) throws HeadlessException {
        super(title);

        table = new JPanel(new BorderLayout());

        newRoundNotification = new JLabel("", SwingConstants.CENTER);

        lastPlayedCard = new JLabel("", SwingConstants.CENTER);
        lastPlayedCard.setPreferredSize(new Dimension(200, 320));
        lastPlayedCard.setText("Last played card");
        lastPlayedCard.setHorizontalTextPosition(JLabel.CENTER);
        lastPlayedCard.setVerticalTextPosition(JLabel.BOTTOM);

        status = new JLabel("Mineral Supertrumps", SwingConstants.CENTER);
        status.setOpaque(true);
        status.setBackground(new Color(230, 219, 224));
        deck = new JLabel(CARDBACK_IMAGE);
        deck.setText("Cards left: " + MineralSupertrumps.deck.size());
        deck.setHorizontalTextPosition(JLabel.CENTER);
        deck.setVerticalTextPosition(JLabel.BOTTOM);
        deck.setPreferredSize(new Dimension(200, 320));


        table.add(newRoundNotification, "North");
        table.add(status, "South");
        table.add(deck);
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

    void categoryPanel(Card card) {
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
        newRoundNotification.setText("Round " + MineralSupertrumps.roundNumber);

        if (MineralSupertrumps.lastPlayedCard != null) {
            lastPlayedCard.setIcon(getScaledImage(new ImageIcon("images/" + MineralSupertrumps.lastPlayedCard.getSlideID() + ".jpg")));
            table.add(lastPlayedCard, "North");
        }


        deck.setText("Cards left: " + MineralSupertrumps.deck.size());
        status.setText("It's player " + player.getID() + " turn");

        int row = (player.getHand().size() + 1) / DEFAULT_COLUMNS;
        if ((player.getHand().size() + 1) % DEFAULT_COLUMNS != 0) {
            row++;
        }

        hand = new JPanel(new GridLayout(row, DEFAULT_COLUMNS, 10, 10));
        hand.setBorder(new EmptyBorder(10, 10, 10, 10));


        JButton pass = new JButton("Pass");
        pass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.passTurn();
                MineralSupertrumps.turnFinished();
            }
        });
        pass.setFont(new Font("Serif", Font.PLAIN, 20));


        JButton[] cards = new JButton[player.getHand().size()];

        for (int x = 0; x < player.getHand().size(); x++) {
            cards[x] = new JButton(getScaledImage(new ImageIcon("images/" + player.getHand().get(x).getSlideID() + ".jpg")));
            cards[x].setPreferredSize(CARD_SIZE_DIMENSION);
            cards[x].setText(player.getHand().get(x).getName());

            cards[x].addActionListener(e -> {
                status.setText("Player " + player.getID() + " has chosen \"" + e.getActionCommand() + "\" card");

                Card chosenCard = null;
                for (Card card : player.getHand()) {
                    if (card.getName().equals(e.getActionCommand())) {
                        chosenCard = card;
                    }
                }

                player.removeCard(chosenCard);
                MineralSupertrumps.lastPlayedCard = chosenCard;

                if (MineralSupertrumps.firstTurn) {
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

    private ImageIcon getScaledImage(ImageIcon imgIcon) {
        Image image = imgIcon.getImage();
        Image newImg = image.getScaledInstance(200, 300, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
            MineralSupertrumps.dealCards();
            MineralSupertrumps.startGame();
        }
    }
}
