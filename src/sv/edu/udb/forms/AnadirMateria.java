package sv.edu.udb.forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
