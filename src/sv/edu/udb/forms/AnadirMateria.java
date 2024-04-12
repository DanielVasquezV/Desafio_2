package sv.edu.udb.forms;

import sv.edu.udb.beans.Materia;
import sv.edu.udb.datos.MateriaDatos;

import javax.swing.*;
import java.awt.*;


public class AnadirMateria extends JFrame{
    private JPanel mainPanel;
    private JButton btnBack;
    private JTextField txtSubjectName;
    private JButton btnAddSubject;
    private JPanel contentPanel;

    public AnadirMateria() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setMinimumSize(new Dimension(700, 450));
        this.setLocationRelativeTo(getParent());
        btnBack.addActionListener(e -> {
            Inicio inicio = new Inicio();
            inicio.setVisible(true);
            dispose();  // Hide the current frame
        });


        btnAddSubject.addActionListener(e -> addSubject());
    }
    private void addSubject(){
        String subjectName = txtSubjectName.getText();

        if(subjectName.isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo nombre está vacío", "Error de validación", JOptionPane.ERROR_MESSAGE);
        }

        Materia materia = new Materia(subjectName);
        MateriaDatos materiaDatos = new MateriaDatos();
        materiaDatos.addSubject(materia);
    }

}
