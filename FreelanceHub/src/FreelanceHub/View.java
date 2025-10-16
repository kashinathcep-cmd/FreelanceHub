package FreelanceHub;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.*;
import java.util.Vector;

public class View {

 
    private TableModel resultSetToTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();

        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column).toUpperCase());
        }

        Vector<Vector<Object>> data = new Vector<>();
        while (rs.next()) {
            Vector<Object> row = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                row.add(rs.getObject(columnIndex));
            }
            data.add(row);
        }

        return new DefaultTableModel(data, columnNames);
    }

   
    public TableModel view() {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();

            String[] mainOptions = {"Client", "Project", "Task"};
            String mainChoice = (String) JOptionPane.showInputDialog(
                    null,
                    "What do you want to view?",
                    "View Menu",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    mainOptions,
                    mainOptions[0]
            );

            if (mainChoice == null) {
                return null; 
            }

            switch (mainChoice) {
                case "Client": {
                    String[] subOptions = {"View all Clients", "Find a specific Client"};
                    String subChoice = (String) JOptionPane.showInputDialog(null,
                            "Select a client option:", "Client View Submenu",
                            JOptionPane.QUESTION_MESSAGE, null, subOptions, subOptions[0]);

                    if (subChoice == null) break; 

                    switch (subChoice) {
                        case "View all Clients": {
                            String sql = "SELECT * FROM clients";
                            try (Statement stmt = conn.createStatement();
                                 ResultSet rs = stmt.executeQuery(sql)) {
                                return resultSetToTableModel(rs); 
                            }
                        }
                        case "Find a specific Client": {
                            String idStr = JOptionPane.showInputDialog("Enter Client ID to find:");
                            if (idStr == null) break;

                            try {
                                int id = Integer.parseInt(idStr);
                                String sql = "SELECT * FROM clients WHERE client_id = ?";
                                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                                    ps.setInt(1, id);
                                    try (ResultSet rs = ps.executeQuery()) {
                                        return resultSetToTableModel(rs); 
                                    }
                                }
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Invalid ID format. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    break;
                }

                case "Project": {
                    String[] subOptions = {"View all Projects", "Find a specific Project"};
                    String subChoice = (String) JOptionPane.showInputDialog(null,
                            "Select a project option:", "Project View Submenu",
                            JOptionPane.QUESTION_MESSAGE, null, subOptions, subOptions[0]);

                    if (subChoice == null) break;

                    switch (subChoice) {
                        case "View all Projects": {
                            String sql = "SELECT * FROM project";
                            try (Statement stmt = conn.createStatement();
                                 ResultSet rs = stmt.executeQuery(sql)) {
                                return resultSetToTableModel(rs); 
                            }
                        }
                        case "Find a specific Project": {
                            String idStr = JOptionPane.showInputDialog("Enter Project ID to find:");
                            if (idStr == null) break;

                            try {
                                int id = Integer.parseInt(idStr);
                                String sql = "SELECT * FROM project WHERE project_id = ?";
                                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                                    ps.setInt(1, id);
                                    try (ResultSet rs = ps.executeQuery()) {
                                        return resultSetToTableModel(rs); 
                                    }
                                }
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Invalid ID format. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    break;
                }

                case "Task": {
                    String[] subOptions = {"View all Tasks", "Find a specific Task"};
                    String subChoice = (String) JOptionPane.showInputDialog(null,
                            "Select a task option:", "Task View Submenu",
                            JOptionPane.QUESTION_MESSAGE, null, subOptions, subOptions[0]);

                    if (subChoice == null) break;

                    switch (subChoice) {
                        case "View all Tasks": {
                            String sql = "SELECT * FROM task";
                            try (Statement stmt = conn.createStatement();
                                 ResultSet rs = stmt.executeQuery(sql)) {
                                return resultSetToTableModel(rs);
                            }
                        }
                        case "Find a specific Task": {
                            String idStr = JOptionPane.showInputDialog("Enter Task ID to find:");
                            if (idStr == null) break;

                            try {
                                int id = Integer.parseInt(idStr);
                                String sql = "SELECT * FROM task WHERE task_id = ?";
                                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                                    ps.setInt(1, id);
                                    try (ResultSet rs = ps.executeQuery()) {
                                        return resultSetToTableModel(rs); 
                                    }
                                 }
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Invalid ID format. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    break;
                }
            }
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "Database Error: " + se.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return null;
    }
}

