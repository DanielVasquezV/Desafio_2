package sv.edu.udb.forms;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetalleEstudiante extends JFrame {
    private JPanel mainPanel;
    private JButton btnBack;
    private JTable tblSubjects;
    private JLabel lblStudentName;
    private JLabel lblStudentAddress;
    private JLabel lblStudentPhone;

    DefaultTableModel listSubjects = null;

    public DetalleEstudiante() {
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
            };
        };
        tblSubjects.setModel(listSubjects);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListarEstudiantes listarEstudiantes = new ListarEstudiantes();
                listarEstudiantes.setVisible(true);
                dispose();
            }
        });
    }
}
