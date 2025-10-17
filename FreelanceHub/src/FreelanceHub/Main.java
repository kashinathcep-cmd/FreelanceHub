package FreelanceHub;

import java.awt.*;
import javax.swing.*;


@SuppressWarnings("serial")
class AnimatedPanel extends JPanel {
	
    private final String text = "FreelanceHub";
    private int x_coordinate;
    private int text_y_position;
    private int direction = 1; 
	
    public AnimatedPanel() {
    	try {
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
    	catch (Exception e) {
    		System.out.println("Error occured:"+e);
    	}
    }

    @Override
    protected void paintComponent(Graphics g) {
    	try {
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
    	catch (Exception e) {
    		System.out.println("Error occured:"+e);
    	}
    }
}


public class Main {
    public static void main(String[] args) {
        
    	FrameManager.initFrame();
    }
}