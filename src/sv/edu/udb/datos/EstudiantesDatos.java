package sv.edu.udb.datos;

import sv.edu.udb.beans.Estudiante;
import java.sql.*;
import java.util.regex.Pattern;
import javax.swing.*;

import sv.edu.udb.utils.DBConnection;
import sv.edu.udb.utils.ItemCombox;

public class EstudiantesDatos {

    public void insertStudent(Estudiante estudiante) {
        Connection conn;
        PreparedStatement stmt;
        try {
            conn = DBConnection.getConnection();

            // Validar el formato del número de teléfono
            if (!isPhoneFormatValid(estudiante.getTelefono())) {
                JOptionPane.showMessageDialog(null, "El formato del número de teléfono no es válido (debe ser 8 números dígitos).", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Validar los nombres
            if (!isNameValid(estudiante.getNombres())) {
                JOptionPane.showMessageDialog(null, "El nombre ingresado no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar los apellidos
            if (!isNameValid(estudiante.getApellidos())) {
                JOptionPane.showMessageDialog(null, "El apellido ingresado no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String SQL_Insert = "INSERT INTO estudiantes (IdEstudiante, nombres, apellidos, direccion, telefono) VALUES (?,?,?,?,?)";
            stmt = conn.prepareStatement(SQL_Insert);
            stmt.setInt(1, estudiante.getIdEstudiante());
            stmt.setString(2, estudiante.getNombres());
            stmt.setString(3, estudiante.getApellidos());
            stmt.setString(4, estudiante.getDireccion());
            stmt.setString(5, estudiante.getTelefono());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Estudiante agregado exitosamente");

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Estudiante no Insertado");
            System.out.println(e.getMessage());
        }
    }
    // Método para validar que un nombre o apellido contenga solo letras, pueda tener tildes y no sea menos de 3 caracteres
    public boolean isNameValid(String name) {
        // La expresión regular permite letras, espacios, apóstrofes y tildes, y no permite menos de 3 caracteres
        return name.matches("[a-zA-ZáéíóúÁÉÍÓÚüÜ\\s']{3,}");
    }
    public boolean isPhoneFormatValid(String phoneNumber) {
        // El número de teléfono debe tener exactamente 8 dígitos
        return phoneNumber.matches("\\d{8}");
    }

    public boolean isPhoneValid(Connection conn, String telefono) throws SQLException {
        String SQL_SELECT = "SELECT COUNT(*) FROM estudiantes WHERE telefono = ?";
        PreparedStatement stmt = conn.prepareStatement(SQL_SELECT);
        stmt.setString(1, telefono);

        ResultSet rs = stmt.executeQuery();
        if (rs.next() && rs.getInt(1) > 0) {
            return false; // Retorna falso si el telefono ya existe
        }
        return true;
    }

    /*public boolean isAddresValid(Connection conn, String direction) throws SQLException {
        String SQL_SELECT = "SELECT COUNT(*) FROM estudiantes WHERE direccion = ?";
        PreparedStatement stmt = conn.prepareStatement(SQL_SELECT);
        stmt.setString(1, direction);
        ResultSet rs = stmt.executeQuery();
        if (rs.next() && rs.getInt(1) > 0) {
            return false;
        }
        return true;
    }*/

    public DefaultComboBoxModel<ItemCombox> selectStudent() {
        DefaultComboBoxModel<ItemCombox> model = new DefaultComboBoxModel<>();
        Connection conn;
        PreparedStatement stmt;
        ResultSet rs;

        try {
            conn = DBConnection.getConnection();
            String SQL_SELECT = "SELECT IdEstudiante, nombres, apellidos FROM estudiantes ORDER BY IdEstudiante";
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                //Obtener primero los campos para crear la cadena con el nombre completo y pasarla como parametro unico
                String nombreCompleto = rs.getString("nombres") + " " + rs.getString("apellidos");
                model.addElement(new ItemCombox(rs.getInt("IdEstudiante"), nombreCompleto));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return model;
    }
}