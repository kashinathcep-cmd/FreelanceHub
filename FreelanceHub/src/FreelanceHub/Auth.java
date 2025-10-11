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
                            
                            JFrame frame = new JFrame("Dashboard - " + user_ID);
                            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                            frame.setVisible(true);
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