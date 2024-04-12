package sv.edu.udb.forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sv.edu.udb.beans.Estudiante;
import sv.edu.udb.datos.EstudiantesDatos;


public class AnadirEstudiante extends JFrame{
    private JPanel mainPanel;
    private JButton btnBack;
    private JTextField txtStudentName;
    private JTextField txtStudentLastName;
    private JTextField txtStudentAddress;
    private JTextField txtStudentPhone;
    private JButton btnAddStudent;




    public AnadirEstudiante() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(700, 450));
        this.setLocationRelativeTo(getParent());
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Inicio inicio = new Inicio();
                inicio.setVisible(true);
                dispose();  // Hide the current frame
            }
        });

        btnAddStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {addStudent();}
        });
    }

    private void addStudent(){
        int idStudent = 0;
        String StudentName = txtStudentName.getText();
        String StudentLastName = txtStudentLastName.getText();
        String StudentAddress = txtStudentAddress.getText();
        String StudentPhone = txtStudentPhone.getText();

        if(StudentName.isEmpty() || StudentName.matches("[0-9]")){
            JOptionPane.showMessageDialog(null, "El nombre del etudiante no puede estar vacio");
        }
        else if(StudentLastName.isEmpty() || StudentLastName.matches("[0-9]")){
            JOptionPane.showMessageDialog(null, "El apellido del estudiante no puede estar vacio");
        }
        else if(StudentAddress.isEmpty()){
            JOptionPane.showMessageDialog(null, "La direccion del estudiante no puede estar vacia");
        }
        else if(StudentPhone.isEmpty()){
            JOptionPane.showMessageDialog(null,"Debe agregar un numero de telefono en caso de emergencia");
        }
        Estudiante estudiante = new Estudiante(idStudent, StudentName, StudentLastName, StudentAddress, StudentPhone);
        EstudiantesDatos estudiantesDatos = new EstudiantesDatos();
        estudiantesDatos.insertStudent(estudiante);

    }

        public static void main(String[] args) {
            JFrame frame = new JFrame();
            frame.setContentPane(new AnadirEstudiante().mainPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);

        }


}
