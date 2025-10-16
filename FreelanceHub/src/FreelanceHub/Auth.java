package FreelanceHub;
import java.util.*;
import java.awt.*;
import java.awt.event.* ;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;

import FreelanceHub.DatabaseConnection;

public class Auth {
	
	
	void login()
	{
		String user_ID = JOptionPane.showInputDialog("Enter your USER ID:");
		if (user_ID != null) {
            String password = JOptionPane.showInputDialog("Enter Password");
            if (password != null) {
                Connection conn = DatabaseConnection.getConnection();

                if (conn == null) {
                    System.out.println("Failed to make connection!");
                } else {
                    PreparedStatement pstmt = null;
                    java.sql.ResultSet rs = null;
                    try {
                        String sql = "SELECT COUNT(*) FROM users WHERE user_id = ? AND password = ?";
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, user_ID);
                        pstmt.setString(2, password);
                        rs = pstmt.executeQuery();
                        int count = 0;
                        if (rs.next()) {
                            count = rs.getInt(1);
                        }

                        if (count > 0) {
                            
                        	JFrame frame = FrameManager.getFrame();
                        	frame.setTitle("Dashboard - " + user_ID);

                        	frame.getContentPane().removeAll();
                        	frame.setLayout(new BorderLayout(10, 10));
                        	frame.getContentPane().setBackground(Color.LIGHT_GRAY);

                        	JPanel northPanel = new JPanel();
                        	northPanel.setBackground(Color.LIGHT_GRAY);
                        	northPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

                        	JLabel titleLabel = new JLabel("Welcome to FreelanceHub");
                        	titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
                        	northPanel.add(titleLabel);

                        	frame.add(northPanel, BorderLayout.NORTH);

                        	JPanel westPanel = new JPanel();
                        	westPanel.setLayout(new GridLayout(4, 1, 5, 5)); 
                        	westPanel.setBackground(Color.LIGHT_GRAY);
                        	westPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

                        	westPanel.setBorder(BorderFactory.createCompoundBorder(
                        	        BorderFactory.createLineBorder(Color.BLACK, 2),
                        	        BorderFactory.createEmptyBorder(20, 20, 20, 20)
                        	));

                        	Font buttonFont = new Font("Times New Roman", Font.BOLD, 18);

                        	JButton createBtn = new JButton("<html><div style='text-align:center;font-family:Courier New;font-size:12'>Add a task/client/project</div> </html>");
                        	JButton deleteBtn = new JButton("<html><div style='text-align:center;font-family:Courier New;font-size:12'>Delete a task/client/project</div> </html>");
                        	JButton viewBtn = new JButton("<html><div style='text-align:center;font-family:Courier New;font-size:12'>View project/task/clients</body> </div>");
                        	JButton updateBtn = new JButton("<html><div style='text-align:center;font-family:Courier New;font-size:12'>Update a task/client/project</div> </html>");

                        	JButton[] buttons = {createBtn, deleteBtn, viewBtn, updateBtn};
                        	for (JButton btn : buttons) {
                        	    btn.setFont(buttonFont);
                        	
                        	    btn.setBackground(Color.WHITE);
                        	    westPanel.add(btn);
                        	}

                        	frame.add(westPanel, BorderLayout.WEST);

                        	JPanel centerPanel = new JPanel();
                        	centerPanel.setBackground(Color.LIGHT_GRAY);
                        	

                        	frame.add(centerPanel, BorderLayout.CENTER);

                        	JPanel southPanel = new JPanel();
                        	southPanel.setBackground(Color.LIGHT_GRAY);
                        	southPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

                        	JLabel footerLabel = new JLabel("<html><body> Made with love ❤️</body> </html>");
                        	footerLabel.setFont(new Font("Times New Roman", Font.ITALIC, 16));
                        	southPanel.add(footerLabel);

                        	frame.add(southPanel, BorderLayout.SOUTH);

                        	frame.revalidate();
                        	frame.repaint();
                        	createBtn.addActionListener(e -> {
                        	    create(); 
                        	});

                        	deleteBtn.addActionListener(e -> {
                        	    delete(); 
                        	});

                        	viewBtn.addActionListener(e -> {
                        	    view(); 
                        	});

                        	updateBtn.addActionListener(e -> {
                        	    update(); 
                        	});

                        } else {
                            JOptionPane.showMessageDialog(null, "User doesn't exist, register please!");
                        }
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Login failed: " + e.getMessage());
                        e.printStackTrace();
                    } finally {
                        try {
                            if (rs != null) rs.close();
                            if (pstmt != null) pstmt.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }
	}
	void register()
	{
		String user_ID = JOptionPane.showInputDialog("Create your USER ID:");
        if (user_ID != null) {
            String password = JOptionPane.showInputDialog("Create Password:");
            if (password != null) {
                Connection conn = DatabaseConnection.getConnection();
                if (conn != null) {
                    try {
                        String sql = "INSERT INTO users (user_id, password) VALUES (?, ?)";
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, user_ID);
                        pstmt.setString(2, password);
                        pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Registration successful!");
                        pstmt.close();
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Registration failed: " + e.getMessage());
                        e.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Database connection failed!");
                }
            }
        }
	}

}