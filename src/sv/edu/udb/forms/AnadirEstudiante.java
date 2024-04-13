package sv.edu.udb.forms;

import javax.swing.*;
import java.sql.*;
import java.awt.*;


import sv.edu.udb.beans.Estudiante;
import sv.edu.udb.datos.EstudiantesDatos;
import sv.edu.udb.utils.DBConnection;


public class AnadirEstudiante extends JFrame{
    private JPanel mainPanel;
    private JButton btnBack;
    private JTextField txtStudentName;
    private JTextField txtStudentLastName;
    private JTextField txtStudentAddress;
    private JTextField txtStudentPhone;
    private JButton btnAddStudent;
    EstudiantesDatos estudiantesDatos = new EstudiantesDatos();

    public AnadirEstudiante() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setMinimumSize(new Dimension(700, 450));
        this.setLocationRelativeTo(getParent());
        btnBack.addActionListener(e -> {
            Inicio inicio = new Inicio();
            inicio.setVisible(true);
            dispose();  // Hide the current frame
        });

        btnAddStudent.addActionListener(e -> addStudent());
    }

    private void addStudent(){
        int idStudent = 0;
        String StudentName = txtStudentName.getText();
        String StudentLastName = txtStudentLastName.getText();
        String StudentAddress = txtStudentAddress.getText();
        String StudentPhone = txtStudentPhone.getText();

        if(StudentName.isEmpty() || StudentName.matches("[0-9]")){
            JOptionPane.showMessageDialog(null, "El campo nombre del estudiante no puede estar vacío"
                    , "Error de validación", JOptionPane.ERROR_MESSAGE);
        }
        else if(StudentLastName.isEmpty() || StudentLastName.matches("[0-9]")){
            JOptionPane.showMessageDialog(null, "El apellido del estudiante no puede estar vacio",
                    "Error de validacion", JOptionPane.ERROR_MESSAGE);
        }
        else if(StudentAddress.isEmpty() ){
            JOptionPane.showMessageDialog(null, "La direccion del estudiante no puede estar vacia",
                    "Error de validacion", JOptionPane.ERROR_MESSAGE);
        }

        if (StudentPhone.isEmpty() || StudentPhone.length()>10 ) {
            JOptionPane.showMessageDialog(null, "El número de teléfono no cuenta con los digitos necesarios",
                    "Error de validacion", JOptionPane.ERROR_MESSAGE);
        } else {
            // validacion del numero de telefono
            try {
                if (estudiantesDatos.isPhoneValid(DBConnection.getConnection(), StudentPhone)) {
                    Estudiante estudiante = new Estudiante(idStudent, StudentName, StudentLastName, StudentAddress, StudentPhone);
                    estudiantesDatos.insertStudent(estudiante);
                } else {
                    JOptionPane.showMessageDialog(null, "El número de teléfono ya esta registrado" +
                            " agregue uno nuevo", "Error de validacion", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al validar el teléfono", "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println(e.getMessage());
            }
        }
    }
}
