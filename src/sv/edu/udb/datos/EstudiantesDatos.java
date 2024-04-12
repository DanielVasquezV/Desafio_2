package sv.edu.udb.datos;

import sv.edu.udb.beans.Estudiante;
import java.sql.*;
import javax.swing.JOptionPane;
import sv.edu.udb.utils.DBConnection;
public class EstudiantesDatos {

    public int insertStudent(Estudiante estudiante) {

        Connection conn;
        PreparedStatement stmt;
        ResultSet rs;
        int rows = 0;
        try {
            conn = DBConnection.getConnection();
            String SQL_Insert = "INSERT INTO estudiantes (IdEstudiante, nombre, apellido, direccion, telefono) VALUES (?,?,?,?,?)";
            stmt = conn.prepareStatement(SQL_Insert);
            stmt.setInt(1, estudiante.getIdEstudiante());
            stmt.setString(2,estudiante.getNombres());
            stmt.setString(3, estudiante.getApellidos());
            stmt.setString(4, estudiante.getDireccion());
            stmt.setString(5, estudiante.getTelefono());
            rows = stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Estudiante agregado exitosamente");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Estudiante no Insertado");

        }
        return rows;
    }
}
