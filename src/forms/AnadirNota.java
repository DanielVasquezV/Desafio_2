package forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnadirNota extends JFrame{
    private JPanel mainPanel;
    private JButton btnBack;
    private JComboBox cmbSubjects;
    private JComboBox cmbStudents;
    private JTextField txtSubjectGrade;
    private JButton btnAddGrade;

    public AnadirNota() {
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
