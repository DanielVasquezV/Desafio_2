package sv.edu.udb.datos;

import sv.edu.udb.beans.Estudiante;
import java.sql.*;
import javax.swing.*;

import sv.edu.udb.utils.DBConnection;
import sv.edu.udb.utils.ItemCombox;

public class EstudiantesDatos {

    public void insertStudent(Estudiante estudiante) {

        Connection conn;
        PreparedStatement stmt;
        try {
            conn = DBConnection.getConnection();
            String SQL_Insert = "INSERT INTO estudiantes (IdEstudiante, nombres, apellidos, direccion, telefono) VALUES (?,?,?,?,?)";
            stmt = conn.prepareStatement(SQL_Insert);
            stmt.setInt(1, estudiante.getIdEstudiante());
            stmt.setString(2,estudiante.getNombres());
            stmt.setString(3, estudiante.getApellidos());
            stmt.setString(4, estudiante.getDireccion());
            stmt.setString(5, estudiante.getTelefono());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Estudiante agregado exitosamente");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Estudiante no Insertado");
            System.out.println(e.getMessage());

        }
    }

    public DefaultComboBoxModel<ItemCombox> selectStudent() {
        DefaultComboBoxModel<ItemCombox> model = new DefaultComboBoxModel<>();
        Connection conn;
        PreparedStatement stmt;
        ResultSet rs;

        try {
            conn = DBConnection.getConnection();
            String SQL_SELECT = "SELECT IdEstudiante, nombres FROM estudiantes ORDER BY IdEstudiante";
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                model.addElement( new ItemCombox(rs.getInt(1) ,rs.getString(2)));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return model;
    }
}
