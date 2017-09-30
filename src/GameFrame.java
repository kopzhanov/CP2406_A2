import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame implements ActionListener {

    final ImageIcon CARDBACK_IMAGE = getScaledImage(new ImageIcon("images/Slide65.jpg"));
    final Dimension CARD_SIZE_DIMENSION = new Dimension(200,300);
    final int DEFAULT_COLUMNS = 4;

    JPanel playerAmountChoice = new JPanel(new BorderLayout());
    JPanel table = new JPanel(new BorderLayout());
    JPanel categoryChoice = new JPanel(new GridLayout(5,1));
    JLabel status = new JLabel("Mineral Supertrumps", SwingConstants.CENTER);

    public GameFrame(String title) throws HeadlessException {
        super(title);

        status.setOpaque(true);
        status.setBackground(new Color(230, 219, 224));
        JLabel deck = new JLabel(CARDBACK_IMAGE);
        deck.setText("Cards left: " + MineralSupertrumps.deck.size());
        deck.setHorizontalTextPosition(JLabel.CENTER);
        deck.setVerticalTextPosition(JLabel.BOTTOM);
        deck.setPreferredSize(new Dimension(200,320));

        table.setPreferredSize(new Dimension(300,400));
        table.add(status, "South");
        table.add(deck);
        table.setBorder(new EmptyBorder(10,10,10,10));

        add(table,BorderLayout.EAST);

        setBackground( new Color(239,237,227) );
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(120,70);
        setResizable(false);
        setVisible(true);
    }

    void playerAmountPanel(){
        JLabel prompt = new JLabel("How many players?");
        JButton three = new JButton("3");
        JButton four = new JButton("4");
        JButton five = new JButton("5");

        prompt.setFont(new Font("Serif", Font.PLAIN, 20));
        three.setPreferredSize(new Dimension(200,200));
        four.setPreferredSize(new Dimension(200,200));
        five.setPreferredSize(new Dimension(200,200));

        three.addActionListener(this);
        four.addActionListener(this);
        five.addActionListener(this);

        playerAmountChoice.add(prompt, "North");
        playerAmountChoice.add(three, "West");
        playerAmountChoice.add(four, "Center");
        playerAmountChoice.add(five, "East");

        add(playerAmountChoice, BorderLayout.CENTER);
        //setPreferredSize(new Dimension(1000,300));
        pack();
        validate();
    }

    void categoryPanel(Card card){
        JButton hardness = new JButton("Hardness: " + card.getHardness());
        JButton gravity = new JButton("Specific Gravity: " + card.getGravity());
        JButton cleavage = new JButton("Cleavage: " + card.getCleavage());
        JButton abundance = new JButton("Crustal Abundance: " + card.getAbundance());
        JButton ecoValue = new JButton("Economic Value: " + card.getEcoValue());

        categoryChoice.add(hardness);
        categoryChoice.add(gravity);
        categoryChoice.add(cleavage);
        categoryChoice.add(abundance);
        categoryChoice.add(ecoValue);

        add(categoryChoice, "West");
        pack();
    }

    void updatePanel(Player player){

        status.setText("It's player " + player.getID() + " turn");

        int row = player.getHand().size()/4;
        if (player.getHand().size()%4 != 0){
            row ++;
        }

        JPanel hand = new JPanel(new GridLayout(row,DEFAULT_COLUMNS,10,10));
        hand.setBorder(new EmptyBorder(10,10,10,10));
        JButton[] cards = new JButton[player.getHand().size()];


        for (int x = 0; x < player.getHand().size(); x++){
            cards [x] = new JButton(getScaledImage(new ImageIcon("images/" + player.getHand().get(x).getSlideID() + ".jpg")));
            cards [x].setPreferredSize(CARD_SIZE_DIMENSION);
            cards [x].setText(player.getHand().get(x).getName());

            cards [x].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (JButton button :
                            cards) {
                        button.setEnabled(false);
                    }

                    Card chosenCard = null;
                    for (Card card : player.getHand()) {
                        if (card.getName().equals(e.getActionCommand())){
                            chosenCard = card;
                        }
                    }
                    status.setText("Player " + player.getID() + " has chosen \"" + e.getActionCommand() + "\" card");
                    if (MineralSupertrumps.firstTurn){
                        categoryPanel(chosenCard);
                    }
                }
            });

            hand.add(cards[x]);
        }
        add(hand,BorderLayout.CENTER);
        pack();
    }
    

    private ImageIcon getScaledImage(ImageIcon imgIcon){
        Image image = imgIcon.getImage();
        Image newImg = image.getScaledInstance(200, 300, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("3")||e.getActionCommand().equals("4")||e.getActionCommand().equals("5")){
            String number = e.getActionCommand();
            switch (number){
                case "3":{
                    MineralSupertrumps.setPlayers(3);
                    break;
                }
                case "4":{
                    MineralSupertrumps.setPlayers(4);
                    break;
                }
                case "5":{
                    MineralSupertrumps.setPlayers(5);
                    break;
                }
            }
            remove(playerAmountChoice);
            validate();
            repaint();
            pack();
            MineralSupertrumps.dealCards();
            MineralSupertrumps.test();
            //MineralSupertrumps.runGame();
        }
    }
}
