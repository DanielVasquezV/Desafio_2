package sv.edu.udb.forms;

import sv.edu.udb.beans.Estudiante;
import sv.edu.udb.beans.Materia;
import sv.edu.udb.beans.Nota;
import sv.edu.udb.utils.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DetalleEstudiante extends JFrame {
    private JPanel mainPanel;
    private JButton btnBack;
    private JTable tblSubjects;
    private JLabel lblStudentName;
    private JLabel lblStudentAddress;
    private JLabel lblStudentPhone;
    private JLabel lblStudentLastName;

    DefaultTableModel listSubjects;

    public DetalleEstudiante(int studentId) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setMinimumSize(new Dimension(700, 450));
        this.setLocationRelativeTo(getParent());
        //Table Header
        String [] listColums = {"Materia", "Nota"};
        //Table Model Definitions
        listSubjects = new DefaultTableModel(listColums, 0){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblSubjects.setModel(listSubjects);
        //Fecth the student data
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            String query = "SELECT idEstudiante, nombres, apellidos, telefono, direccion FROM estudiantes WHERE idEstudiante = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, studentId); // Set the student ID as a parameter
            rs = stmt.executeQuery();

            if (rs.next()) {
                Estudiante estudiante = new Estudiante();

                estudiante.setNombres(rs.getString("nombres"));
                estudiante.setApellidos(rs.getString("apellidos"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setDireccion(rs.getString("direccion"));

                lblStudentName.setText(estudiante.getNombres());
                lblStudentLastName.setText(estudiante.getApellidos());
                lblStudentAddress.setText(estudiante.getTelefono());
                lblStudentPhone.setText(estudiante.getDireccion());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // Close resources
            DBConnection.close(rs);
            DBConnection.close(stmt);
            DBConnection.close(conn);
        }
        //Fetch the student grades
        try {
            conn = DBConnection.getConnection();
            String gradesQuery = "SELECT m.nombre AS materia, n.nota FROM notas n JOIN materias m ON n.IdMateria = m.IdMateria WHERE n.idEstudiante = ?";
            stmt = conn.prepareStatement(gradesQuery);
            stmt.setInt(1, studentId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String nombreMateria = rs.getString("materia");
                double nota = rs.getDouble("nota");

                // Initializing the objects
                Materia materia = new Materia(nombreMateria);
                Nota notaObj = new Nota(studentId, nota);

                // Adding the data to the table
                Object[] rowData = {materia.getMateria(), notaObj.getNota()};
                listSubjects.addRow(rowData);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DBConnection.close(rs);
            DBConnection.close(stmt);
            DBConnection.close(conn);
        }

        btnBack.addActionListener(e -> {
            ListarEstudiantes listarEstudiantes = new ListarEstudiantes();
            listarEstudiantes.setVisible(true);
            dispose();
        });
    }
}