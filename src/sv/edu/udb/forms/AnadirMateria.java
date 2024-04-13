package sv.edu.udb.forms;

import sv.edu.udb.beans.Materia;
import sv.edu.udb.datos.MateriaDatos;
import sv.edu.udb.utils.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;


public class AnadirMateria extends JFrame{
    private JPanel mainPanel;
    private JButton btnBack;
    private JTextField txtSubjectName;
    private JButton btnAddSubject;
    private JPanel contentPanel;
    MateriaDatos materiaDatos = new MateriaDatos();


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
        }else {
            try {
                if (materiaDatos.isNameValid(DBConnection.getConnection(), subjectName)){
                    Materia materia = new Materia(subjectName);
                    materiaDatos.addSubject(materia);

                }else {
                    JOptionPane.showMessageDialog(null,"La materia ya existe", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }


    }

}
