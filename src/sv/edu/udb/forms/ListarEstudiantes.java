package sv.edu.udb.forms;

import sv.edu.udb.beans.Estudiante;
import sv.edu.udb.utils.DBConnection;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListarEstudiantes extends JFrame {

    private JPanel mainPanel;
    private JButton btnBack;
    private JTable tblStudents;
    private JButton btnViewSelection;

    DefaultTableModel listAllStudents = null;

    public ListarEstudiantes() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setMinimumSize(new Dimension(700, 450));
        this.setLocationRelativeTo(getParent());
        //Table Header
        String [] listColums = {"ID","Nombres", "Apellidos", "Teléfono"};
        //Table model definitions
        listAllStudents = new DefaultTableModel(listColums, 0){
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        tblStudents.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblStudents.setColumnSelectionAllowed(false);
        tblStudents.setRowSelectionAllowed(true);
        tblStudents.setModel(listAllStudents);

        // Fetch students data from database and populate the table
        loadStudentsData();

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Inicio inicio = new Inicio();
                inicio.setVisible(true);
                dispose();
            }
        });
        btnViewSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblStudents.getSelectedRow();
                if (selectedRow != -1) {
                    int studentId = (int) tblStudents.getValueAt(selectedRow, 0);
                    DetalleEstudiante detalleEstudiante = new DetalleEstudiante(studentId);
                    detalleEstudiante.setVisible(true);
                    dispose();
                }
            }
        });
    }
    // Method to load students data from the database
    private void loadStudentsData() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            String query = "SELECT idEstudiante, nombres, apellidos, telefono FROM estudiantes";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Estudiante estudiante = new Estudiante();
                estudiante.setIdEstudiante(rs.getInt("IdEstudiante"));
                estudiante.setNombres(rs.getString("nombres"));
                estudiante.setApellidos(rs.getString("apellidos"));
                estudiante.setTelefono(rs.getString("telefono"));

                Object[] rowData = {estudiante.getIdEstudiante(), estudiante.getNombres(), estudiante.getApellidos(), estudiante.getTelefono()};
                listAllStudents.addRow(rowData);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // Close resources
            DBConnection.close(rs);
            DBConnection.close(stmt);
            DBConnection.close(conn);
        }
    }
}
