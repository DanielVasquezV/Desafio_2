package sv.edu.udb.forms;
import sv.edu.udb.datos.DetalleEstudianteDatos;
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

        //Fetch the student info...
        DetalleEstudianteDatos.fetchStudentInfo(studentId, listSubjects, lblStudentName, lblStudentAddress, lblStudentPhone, lblStudentLastName);

        btnBack.addActionListener(e -> {
            ListarEstudiantes listarEstudiantes = new ListarEstudiantes();
            listarEstudiantes.setVisible(true);
            dispose();
        });
    }
}