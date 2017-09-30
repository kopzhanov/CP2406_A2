import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame implements ActionListener {

    final ImageIcon CARDBACK_IMAGE = getScaledImage(new ImageIcon("images/Slide65.jpg"));
    final Dimension CARD_SIZE_DIMENSION = new Dimension(200,300);

    public GameFrame(String title) throws HeadlessException {
        super(title);
        setBackground( new Color(239,237,227) );
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(120,70);
        setResizable(false);
        setVisible(true);

    }
    
    void addTable(){
        JPanel table = new JPanel(new BorderLayout());
        JLabel deck = new JLabel(CARDBACK_IMAGE);
        deck.setText("Cards left: " + MineralSupertrumps.deck.size());
        deck.setHorizontalTextPosition(JLabel.CENTER);
        deck.setVerticalTextPosition(JLabel.BOTTOM);
        deck.setPreferredSize(new Dimension(200,300));
        table.add(deck);
        table.setBorder(new EmptyBorder(10,10,10,10));
        add(table,BorderLayout.EAST);
    }
    
    void addPlayer(){
        JPanel player = new JPanel(new GridLayout(2,2,10,10));
        player.setBorder(new EmptyBorder(10,10,10,10));
        JButton[][] cells = new JButton[2][2];

        cells[0][0] = new JButton(CARDBACK_IMAGE);
        cells[0][1] = new JButton(CARDBACK_IMAGE);
        cells[1][0] = new JButton(CARDBACK_IMAGE);
        cells[1][1] = new JButton(CARDBACK_IMAGE);
        cells[0][0].setPreferredSize(new Dimension(200,300));
        cells[0][1].setPreferredSize(new Dimension(200,300));
        cells[1][0].setPreferredSize(new Dimension(200,300));
        cells[1][1].setPreferredSize(new Dimension(200,300));

        player.add(cells[0][0]);
        player.add(cells[0][1]);
        player.add(cells[1][0]);
        player.add(cells[1][1]);

        add(player,BorderLayout.CENTER);
    }
    

    private ImageIcon getScaledImage(ImageIcon imgIcon){
        Image image = imgIcon.getImage();
        Image newImg = image.getScaledInstance(200, 300, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
