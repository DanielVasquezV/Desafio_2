package sv.edu.udb.forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inicio extends JFrame{
    private JPanel mainPanel;
    private JButton bntAddSubject;
    private JButton btnAddStudent;
    private JButton btnAddGrades;
    private JButton btnLogout;
    private JPanel contentPanel;
    private JButton btnViewStudents;


    public Inicio() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setMinimumSize(new Dimension(700, 450));
        this.setLocationRelativeTo(getParent());
        bntAddSubject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AnadirMateria anadirMateria = new AnadirMateria();
                anadirMateria.setVisible(true);
                dispose();  // Hide the current frame
            }
        });
        btnAddStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AnadirEstudiante anadirEstudiante = new AnadirEstudiante();
                anadirEstudiante.setVisible(true);
                dispose();
            }
        });
        btnAddGrades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AnadirNota anadirNota = new AnadirNota();
                anadirNota.setVisible(true);
                dispose();
            }
        });
        btnViewStudents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListarEstudiantes listarEstudiantes = new ListarEstudiantes();
                listarEstudiantes.setVisible(true);
                dispose();
            }
        });
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });

    }
}