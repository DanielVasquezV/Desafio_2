package sv.edu.udb.datos;

import sv.edu.udb.beans.Materia;
import sv.edu.udb.utils.DBConnection;
import sv.edu.udb.utils.ItemCombox;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MateriaDatos {

    public void addSubject(Materia materia){
        Connection conn;
        PreparedStatement ps;

        try {
            conn = DBConnection.getConnection();
            String SQL_ADD = "INSERT INTO materias (IdMateria, nombre) VALUES (?,?)";
            ps = conn.prepareStatement(SQL_ADD);
            ps.setInt(1, materia.getIdMateria());
            ps.setString(2, materia.getMateria());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Materia adicionada con éxito");
        }catch (SQLException e){
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al agregar la materia");
        }
    }

    public boolean isNameValid(Connection conn, String nombre) throws SQLException {
        String SQL_SELECT = "SELECT COUNT(*) FROM materias WHERE nombre = ?";
        PreparedStatement stmt = conn.prepareStatement(SQL_SELECT);
        stmt.setString(1, nombre);

        ResultSet rs = stmt.executeQuery();
        return !rs.next() || rs.getInt(1) <= 0; // Retorna falso si el nombre ya existe ya existe
    }

    //Objeto para poder obtener todas las materias que estan guardadas en la base de datos
    public DefaultComboBoxModel<ItemCombox> slectAllSubjetcs() {
        DefaultComboBoxModel<ItemCombox> model = new DefaultComboBoxModel<>();
        Connection conn;
        PreparedStatement stmt;
        ResultSet rs;

        try {
            conn = DBConnection.getConnection();
            String SQL_SELECT = "SELECT IdMateria, nombre FROM materias  ORDER BY IdMateria";
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