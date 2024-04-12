package sv.edu.udb.datos;

import sv.edu.udb.beans.Estudiante;
import sv.edu.udb.utils.DBConnection;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListarEstudiantesDatos {
    // Method to load students data from the database
    public static void loadStudentsData(DefaultTableModel listAllStudents) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            String query = "SELECT idEstudiante, nombres, apellidos, telefono FROM estudiantes";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Estudiante estudiante = new Estudiante();
                estudiante.setIdEstudiante(rs.getInt("IdEstudiante"));
                estudiante.setNombres(rs.getString("nombres"));
                estudiante.setApellidos(rs.getString("apellidos"));
                estudiante.setTelefono(rs.getString("telefono"));

                Object[] rowData = {estudiante.getIdEstudiante(), estudiante.getNombres(), estudiante.getApellidos(), estudiante.getTelefono()};
                listAllStudents.addRow(rowData);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // Close resources
            DBConnection.close(rs);
            DBConnection.close(stmt);
            DBConnection.close(conn);
        }
    }
}
