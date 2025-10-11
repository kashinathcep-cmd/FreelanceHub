package FreelanceHub;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
class AnimatedPanel extends JPanel {
    private final String text = "Freelance Hub";
    private int x_coordinate;
    private int text_y_position;
    private int direction = 1; 

    public AnimatedPanel() {
        this.setBackground(Color.DARK_GRAY);
        this.setPreferredSize(new Dimension(0, 60)); 

        
        Timer timer = new Timer(15, e -> {
            FontMetrics fm = getFontMetrics(getFont());
            x_coordinate += direction;
            
            if (x_coordinate + fm.stringWidth(text) > getWidth()) {
                direction = -1;
            } else if (x_coordinate < 0) {
                direction = 1;
            }
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(new Font("Arial", Font.BOLD, 36));
        g2d.setColor(Color.WHITE);

        
        if (text_y_position == 0) {
            FontMetrics fm = g2d.getFontMetrics();
            text_y_position = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
        }

        g2d.drawString(text, x_coordinate, text_y_position);
    }
}


public class Main {
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("FreelanceHub");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
            frame.setLayout(new BorderLayout(10, 10));
            frame.getContentPane().setBackground(Color.LIGHT_GRAY);

            
            frame.add(new AnimatedPanel(), BorderLayout.NORTH);

            
            JPanel centerPanel = new JPanel();
            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
            centerPanel.setBackground(Color.LIGHT_GRAY);

            ImageIcon icon = new ImageIcon(Main.class.getResource("/images/freelance2-Photoroom.png"));
            JLabel label1 = new JLabel(icon);
            label1.setAlignmentX(Component.CENTER_ALIGNMENT);

            
            JTextPane text = new JTextPane();
            text.setContentType("text/html");
            text.setText("<html><div style='text-align:center;font-family:Courier New;font-size:24'>"
                    + "<strong> FreelanceHub </strong> is a Java-based project built to explore how"
                    + " projects can be organized and managed through software.<br>"
                    + " It focuses on basics of object-oriented programming concepts"
                    + " along with database connectivity.<br><br>"
                    + " Though not a commercial product, <strong>FreelanceHub</strong> serves as a<br>"
                    + " learning project that demonstrates how logic can be applied<br>"
                    + " to real-world scenarios."
                    + "</div></html>");
            text.setEditable(false);
            text.setBackground(Color.LIGHT_GRAY);
            text.setMaximumSize(new Dimension(800, 300)); 
            centerPanel.add(Box.createVerticalGlue()); 
            centerPanel.add(label1);
            centerPanel.add(Box.createVerticalStrut(20)); 
            centerPanel.add(text);
            centerPanel.add(Box.createVerticalGlue()); 
            centerPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));

            frame.add(centerPanel, BorderLayout.CENTER);
            JPanel westPanel = new JPanel();
            westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
            JTextPane text2 = new JTextPane();
            text2.setContentType("text/html");
            text2.setText("<html><div style='text-align:center;font-family:Courier New;font-size:18'>"
                    + "Collaborators (GitHub)<br><br>"
                    + "justin roy (justinroy-01)<br><br>"
                    + "Sharath H<br><br>"
                    + "Islam S Mytheen<br><br>"
                    + "Kashinath<br><br>"
                    + "Kailasnath<br><br>"
                    + "</div></html>");
            text2.setEditable(false);
            text2.setBackground(Color.LIGHT_GRAY);
            westPanel.add(text2);
            
            westPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            frame.add(westPanel, BorderLayout.WEST);


           
            JPanel eastPanel = new JPanel();
            eastPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
            eastPanel.setBackground(Color.LIGHT_GRAY);
            
            eastPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

            Font buttonFont = new Font("Times New Roman", Font.BOLD, 20);
            Dimension buttonSize = new Dimension(150, 50);

            JButton button = new JButton("Login !!");
            button.addActionListener(e -> {
                 Auth a = new Auth();
                 a.login();
               
            });
           
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMaximumSize(buttonSize);
            button.setFont(buttonFont);

            
            JButton button2 = new JButton("Register!!");
            button2.addActionListener(e -> { 
                 Auth a = new Auth();
                 a.register();
                 
            });
            button2.setAlignmentX(Component.CENTER_ALIGNMENT);
            button2.setMaximumSize(buttonSize);
            button2.setFont(buttonFont);
            
            eastPanel.add(Box.createVerticalGlue()); 
            eastPanel.add(button);
            eastPanel.add(Box.createVerticalStrut(20));
            eastPanel.add(button2);
            eastPanel.add(Box.createVerticalGlue()); 
            eastPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));

            frame.add(eastPanel, BorderLayout.EAST);
            frame.setVisible(true);
        });
    }
}