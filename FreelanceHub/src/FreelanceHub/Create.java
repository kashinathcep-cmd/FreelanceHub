package FreelanceHub;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Create {

    public void create() {
        String[] options = {"Client", "Project", "Task"};
        String choice = (String) JOptionPane.showInputDialog(
                null,
                "What do you want to create?",
                "Create",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice == null) return; 

        Connection conn = DatabaseConnection.getConnection();

        try {
            switch (choice) {
                case "Client":
                    String clientIdStr = JOptionPane.showInputDialog("Enter Client ID (number):");
                    String clientName = JOptionPane.showInputDialog("Enter Client Name:");
                    String contactInfo = JOptionPane.showInputDialog("Enter Contact Info:");

                    if (clientIdStr == null || clientName == null || contactInfo == null) return;

                    int clientId = Integer.parseInt(clientIdStr);

                    String insertClient = "INSERT INTO clients (client_id, client_name, contact_info) VALUES (?, ?, ?)";
                    PreparedStatement psClient = conn.prepareStatement(insertClient);
                    psClient.setInt(1, clientId);
                    psClient.setString(2, clientName);
                    psClient.setString(3, contactInfo);
                    psClient.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Client created successfully!");
                    break;

                case "Project":
                    String projectIdStr = JOptionPane.showInputDialog("Enter Project ID (number):");
                    String projectName = JOptionPane.showInputDialog("Enter Project Name:");
                    String projectDesc = JOptionPane.showInputDialog("Enter Description:");
                    String userId = JOptionPane.showInputDialog("Enter User ID (owner):");
                    String clientIdProjStr = JOptionPane.showInputDialog("Enter Client ID:");

                    if (projectIdStr == null || projectName == null || projectDesc == null || userId == null || clientIdProjStr == null) return;

                    int projectId = Integer.parseInt(projectIdStr);
                    int clientIdProj = Integer.parseInt(clientIdProjStr);

                    String insertProject = "INSERT INTO project (project_id, project_name, description, user_id, client_id) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement psProject = conn.prepareStatement(insertProject);
                    psProject.setInt(1, projectId);
                    psProject.setString(2, projectName);
                    psProject.setString(3, projectDesc);
                    psProject.setString(4, userId);
                    psProject.setInt(5, clientIdProj);
                    psProject.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Project created successfully!");
                    break;

                case "Task":
                    String taskIdStr = JOptionPane.showInputDialog("Enter Task ID (number):");
                    String taskName = JOptionPane.showInputDialog("Enter Task Name:");
                    String taskDesc = JOptionPane.showInputDialog("Enter Description:");
                    String taskStatus = JOptionPane.showInputDialog("Enter Status:");
                    String projectIdTaskStr = JOptionPane.showInputDialog("Enter Project ID:");

                    if (taskIdStr == null || taskName == null || taskDesc == null || taskStatus == null || projectIdTaskStr == null) return;

                    int taskId = Integer.parseInt(taskIdStr);
                    int projectIdTask = Integer.parseInt(projectIdTaskStr);

                    String insertTask = "INSERT INTO task (task_id, task_name, description, status, project_id) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement psTask = conn.prepareStatement(insertTask);
                    psTask.setInt(1, taskId);
                    psTask.setString(2, taskName);
                    psTask.setString(3, taskDesc);
                    psTask.setString(4, taskStatus);
                    psTask.setInt(5, projectIdTask);
                    psTask.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Task created successfully!");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid option!");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter valid numbers where required!");
        }
    }
}
