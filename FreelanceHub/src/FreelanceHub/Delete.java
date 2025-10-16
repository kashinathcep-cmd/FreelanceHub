package FreelanceHub;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {

    public void delete() {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();

            String[] options = {"Client", "Project", "Task", "User"};
            String choice = (String) JOptionPane.showInputDialog(
                    null,
                    "What do you want to delete?",
                    "Delete Menu",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (choice == null) {
                return;
            }

            switch (choice) {
                case "Client": {
                    String idStr = JOptionPane.showInputDialog("Enter Client ID to delete:");
                    if (idStr == null) break; 

                    try {
                        int id = Integer.parseInt(idStr);
                        String sql = "DELETE FROM clients WHERE client_id = ?";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setInt(1, id);

                        int rowsAffected = ps.executeUpdate();
                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(null, "Client deleted successfully!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Client with ID " + id + " not found.");
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid ID format. Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
                case "Project": {
                    String idStr = JOptionPane.showInputDialog("Enter Project ID to delete:");
                    if (idStr == null) break;

                    try {
                        int id = Integer.parseInt(idStr);
                        String sql = "DELETE FROM project WHERE project_id = ?";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setInt(1, id);

                        int rowsAffected = ps.executeUpdate();
                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(null, "Project deleted successfully!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Project with ID " + id + " not found.");
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid ID format. Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
                case "Task": {
                    String idStr = JOptionPane.showInputDialog("Enter Task ID to delete:");
                    if (idStr == null) break; 
                    try {
                        int id = Integer.parseInt(idStr);
                        String sql = "DELETE FROM task WHERE task_id = ?";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setInt(1, id);

                        int rowsAffected = ps.executeUpdate();
                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(null, "Task deleted successfully!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Task with ID " + id + " not found.");
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid ID format. Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
                case "User": {
                    String userId = JOptionPane.showInputDialog("Enter User ID to delete:");
                    if (userId == null || userId.trim().isEmpty()) break;

                    String sql = "DELETE FROM users WHERE user_id = ?";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, userId);

                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "User deleted successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "User with ID '" + userId + "' not found.");
                    }
                    break;
                }
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option selected.");
                    break;
            }

        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "Database Error: " + se.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

