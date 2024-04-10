package forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        this.setContentPane(mainPanel);
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
    }
}
