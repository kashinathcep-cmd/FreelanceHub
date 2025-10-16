package FreelanceHub;

import javax.swing.*;
import java.sql.*;

public class Update {

    public void update() {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();

            String[] mainOptions = {"Client", "Project", "Task"};
            String choice = (String) JOptionPane.showInputDialog(null,
                    "What do you want to update?",
                    "Update Menu",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    mainOptions,
                    mainOptions[0]);

            if (choice == null) return;

            switch (choice) {
                case "Client": {
                    String clientIdStr = JOptionPane.showInputDialog("Enter Client ID to update:");
                    if (clientIdStr == null) break;
                    int clientId = Integer.parseInt(clientIdStr);

                    PreparedStatement chkClient = conn.prepareStatement("SELECT * FROM clients WHERE client_id=?");
                    chkClient.setInt(1, clientId);
                    ResultSet rsClient = chkClient.executeQuery();
                    if (!rsClient.next()) {
                        JOptionPane.showMessageDialog(null, "Client not found!");
                        break;
                    }

                    String[] clientFields = {"Client Name", "Client Contact"};
                    String clientField = (String) JOptionPane.showInputDialog(null,
                            "Select field to update:",
                            "Client Submenu",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            clientFields,
                            clientFields[0]);
                    if (clientField == null) break;

                    switch (clientField) {
                        case "Client Name": {
                            String newName = JOptionPane.showInputDialog("Enter new Client Name:");
                            if (newName == null) break;
                            PreparedStatement ps = conn.prepareStatement(
                                    "UPDATE clients SET client_name=? WHERE client_id=?");
                            ps.setString(1, newName);
                            ps.setInt(2, clientId);
                            int rows = ps.executeUpdate();
                            JOptionPane.showMessageDialog(null, rows > 0 ? "Updated successfully!" : "Update failed!");
                            break;
                        }
                        case "Client Contact": {
                            String newContact = JOptionPane.showInputDialog("Enter new Contact Info:");
                            if (newContact == null) break;
                            PreparedStatement ps = conn.prepareStatement(
                                    "UPDATE clients SET contact_info=? WHERE client_id=?");
                            ps.setString(1, newContact);
                            ps.setInt(2, clientId);
                            int rows = ps.executeUpdate();
                            JOptionPane.showMessageDialog(null, rows > 0 ? "Updated successfully!" : "Update failed!");
                            break;
                        }
                        default :
                        	JOptionPane.showMessageDialog(null, "No option selected.");
                    }
                    break;
                }

                case "Project": {
                    String projectIdStr = JOptionPane.showInputDialog("Enter Project ID to update:");
                    if (projectIdStr == null) break;
                    int projectId = Integer.parseInt(projectIdStr);

                    PreparedStatement chkProject = conn.prepareStatement("SELECT * FROM project WHERE project_id=?");
                    chkProject.setInt(1, projectId);
                    ResultSet rsProject = chkProject.executeQuery();
                    if (!rsProject.next()) {
                        JOptionPane.showMessageDialog(null, "Project not found!");
                        break;
                    }

                    String[] projectFields = {"Project Name", "Description", "User ID", "Client ID"};
                    String projectField = (String) JOptionPane.showInputDialog(null,
                            "Select field to update:",
                            "Project Submenu",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            projectFields,
                            projectFields[0]);
                    if (projectField == null) break;

                    switch (projectField) {
                        case "Project Name": {
                            String newName = JOptionPane.showInputDialog("Enter new Project Name:");
                            if (newName == null) break;
                            PreparedStatement ps = conn.prepareStatement(
                                    "UPDATE project SET project_name=? WHERE project_id=?");
                            ps.setString(1, newName);
                            ps.setInt(2, projectId);
                            int rows = ps.executeUpdate();
                            JOptionPane.showMessageDialog(null, rows > 0 ? "Updated successfully!" : "Update failed!");
                            break;
                        }
                        case "Description": {
                            String newDesc = JOptionPane.showInputDialog("Enter new Description:");
                            if (newDesc == null) break;
                            PreparedStatement ps = conn.prepareStatement(
                                    "UPDATE project SET description=? WHERE project_id=?");
                            ps.setString(1, newDesc);
                            ps.setInt(2, projectId);
                            int rows = ps.executeUpdate();
                            JOptionPane.showMessageDialog(null, rows > 0 ? "Updated successfully!" : "Update failed!");
                            break;
                        }
                        case "User ID": {
                            String newUserId = JOptionPane.showInputDialog("Enter new User ID:");
                            if (newUserId == null) break;
                            PreparedStatement ps = conn.prepareStatement(
                                    "UPDATE project SET user_id=? WHERE project_id=?");
                            ps.setString(1, newUserId);
                            ps.setInt(2, projectId);
                            int rows = ps.executeUpdate();
                            JOptionPane.showMessageDialog(null, rows > 0 ? "Updated successfully!" : "Update failed!");
                            break;
                        }
                        case "Client ID": {
                            String newClientIdStr = JOptionPane.showInputDialog("Enter new Client ID:");
                            if (newClientIdStr == null) break;
                            int newClientId = Integer.parseInt(newClientIdStr);
                            PreparedStatement ps = conn.prepareStatement(
                                    "UPDATE project SET client_id=? WHERE project_id=?");
                            ps.setInt(1, newClientId);
                            ps.setInt(2, projectId);
                            int rows = ps.executeUpdate();
                            JOptionPane.showMessageDialog(null, rows > 0 ? "Updated successfully!" : "Update failed!");
                            break;
                        }
                        default : JOptionPane.showMessageDialog(null, "No option selected.");
                    }
                    break;
                }

                case "Task": {
                    String taskIdStr = JOptionPane.showInputDialog("Enter Task ID to update:");
                    if (taskIdStr == null) break;
                    int taskId = Integer.parseInt(taskIdStr);

                    PreparedStatement chkTask = conn.prepareStatement("SELECT * FROM task WHERE task_id=?");
                    chkTask.setInt(1, taskId);
                    ResultSet rsTask = chkTask.executeQuery();
                    if (!rsTask.next()) {
                        JOptionPane.showMessageDialog(null, "Task not found!");
                        break;
                    }

                    String[] taskFields = {"Task Name", "Description", "Project ID", "Status"};
                    String taskField = (String) JOptionPane.showInputDialog(null,
                            "Select field to update:",
                            "Task Submenu",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            taskFields,
                            taskFields[0]);
                    if (taskField == null) break;

                    switch (taskField) {
                        case "Task Name": {
                            String newName = JOptionPane.showInputDialog("Enter new Task Name:");
                            if (newName == null) break;
                            PreparedStatement ps = conn.prepareStatement(
                                    "UPDATE task SET task_name=? WHERE task_id=?");
                            ps.setString(1, newName);
                            ps.setInt(2, taskId);
                            int rows = ps.executeUpdate();
                            JOptionPane.showMessageDialog(null, rows > 0 ? "Updated successfully!" : "Update failed!");
                            break;
                        }
                        case "Description": {
                            String newDesc = JOptionPane.showInputDialog("Enter new Description:");
                            if (newDesc == null) break;
                            PreparedStatement ps = conn.prepareStatement(
                                    "UPDATE task SET description=? WHERE task_id=?");
                            ps.setString(1, newDesc);
                            ps.setInt(2, taskId);
                            int rows = ps.executeUpdate();
                            JOptionPane.showMessageDialog(null, rows > 0 ? "Updated successfully!" : "Update failed!");
                            break;
                        }
                        case "Project ID": {
                            String newProjIdStr = JOptionPane.showInputDialog("Enter new Project ID:");
                            if (newProjIdStr == null) break;
                            int newProjId = Integer.parseInt(newProjIdStr);
                            PreparedStatement ps = conn.prepareStatement(
                                    "UPDATE task SET project_id=? WHERE task_id=?");
                            ps.setInt(1, newProjId);
                            ps.setInt(2, taskId);
                            int rows = ps.executeUpdate();
                            JOptionPane.showMessageDialog(null, rows > 0 ? "Updated successfully!" : "Update failed!");
                            break;
                        }
                        case "Status": {
                            String newStatus = JOptionPane.showInputDialog("Enter new Status:");
                            if (newStatus == null) break;
                            PreparedStatement ps = conn.prepareStatement(
                                    "UPDATE task SET status=? WHERE task_id=?");
                            ps.setString(1, newStatus);
                            ps.setInt(2, taskId);
                            int rows = ps.executeUpdate();
                            JOptionPane.showMessageDialog(null, rows > 0 ? "Updated successfully!" : "Update failed!");
                            break;
                        }
                        default : JOptionPane.showMessageDialog(null, "No option selected.");
                    }
                    break;
                }

                default:
                    JOptionPane.showMessageDialog(null, "Invalid option selected!");
            }

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Invalid number format: " + nfe.getMessage());
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "SQL Error: " + se.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } 
    }
}
