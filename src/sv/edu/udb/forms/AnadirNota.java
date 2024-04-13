package sv.edu.udb.forms;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import sv.edu.udb.beans.Nota;
import sv.edu.udb.datos.MateriaDatos;
import sv.edu.udb.datos.NotaDatos;
import sv.edu.udb.utils.DBConnection;
import sv.edu.udb.utils.ItemCombox;
import sv.edu.udb.datos.EstudiantesDatos;

public class AnadirNota extends JFrame{
    private JPanel mainPanel;
    private JButton btnBack;
    private JComboBox cmbSubjects;
    private JComboBox cmbStudents;
    private JTextField txtSubjectGrade;
    private JButton btnAddGrade;
    EstudiantesDatos estudiantesDatos = new EstudiantesDatos();
    MateriaDatos materiaDatos = new MateriaDatos();
    NotaDatos notaDatos = new NotaDatos();

    public AnadirNota() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setMinimumSize(new Dimension(700, 450));
        this.setLocationRelativeTo(getParent());
        btnBack.addActionListener(e -> {
            Inicio inicio = new Inicio();
            inicio.setVisible(true);
            dispose();  // Hide the current frame
        });
        cmbSubjects.setModel(materiaDatos.slectAllSubjetcs());
        cmbSubjects.setRenderer(renderizadorItemComboxBoxPersonalizado());
        cmbStudents.setModel(estudiantesDatos.selectStudent());
        cmbStudents.setRenderer(renderizadorItemComboxBoxPersonalizado());// selecciona y renderiza la informacion de la base de datos


        btnAddGrade.addActionListener(e -> addGrade());
    }

    /**
     * Metodo para cambiar el tipo de renderizado de cada uno de los option del ComboBox ya que se ocupa la clase ItemComboBox
     *
     */
    private DefaultListCellRenderer renderizadorItemComboxBoxPersonalizado(){
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof ItemCombox item) {
                    // Obtener la descripción del Item
                    value = item.getDescription();
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        };
    }

    private void addGrade(){
        ItemCombox itemStudent = (ItemCombox)cmbStudents.getSelectedItem();
        ItemCombox itemSubject = (ItemCombox)cmbSubjects.getSelectedItem();
        double grade =Double.parseDouble(txtSubjectGrade.getText()); //obtengo lo que esta dentro del TextField

        assert itemStudent != null;
        assert itemSubject != null;
        try (Connection conn = DBConnection.getConnection()) {
            if (!notaDatos.hasGrade(conn, itemStudent.getId(), itemSubject.getId())) {
                Nota nota = new Nota(itemStudent.getId(), itemSubject.getId(), grade);
                notaDatos.InsertGrade(nota);
            } else {
                JOptionPane.showMessageDialog(null, "El estudiante ya tiene una nota para esta materia");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al verificar o insertar la nota", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }

    }

}
