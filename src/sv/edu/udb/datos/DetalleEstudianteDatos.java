package sv.edu.udb.datos;

import sv.edu.udb.beans.Estudiante;
import sv.edu.udb.beans.Materia;
import sv.edu.udb.beans.Nota;
import sv.edu.udb.utils.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DetalleEstudianteDatos {
    public static void fetchStudentInfo(int studentId, DefaultTableModel listSubjects, JLabel lblStudentName, JLabel lblStudentAddress, JLabel lblStudentPhone, JLabel lblStudentLastName){
        //Fecth the student data
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            String query = "SELECT idEstudiante, nombres, apellidos, telefono, direccion FROM estudiantes WHERE idEstudiante = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, studentId); // Set the student ID as a parameter
            rs = stmt.executeQuery();

            if (rs.next()) {
                Estudiante estudiante = new Estudiante();

                estudiante.setNombres(rs.getString("nombres"));
                estudiante.setApellidos(rs.getString("apellidos"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setDireccion(rs.getString("direccion"));

                lblStudentName.setText(estudiante.getNombres());
                lblStudentLastName.setText(estudiante.getApellidos());
                lblStudentAddress.setText(estudiante.getTelefono());
                lblStudentPhone.setText(estudiante.getDireccion());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // Close resources
            DBConnection.close(rs);
            DBConnection.close(stmt);
            DBConnection.close(conn);
        }
        //Fetch the student grades
        try {
            conn = DBConnection.getConnection();
            String gradesQuery = "SELECT m.nombre AS materia, n.nota FROM notas n JOIN materias m ON n.IdMateria = m.IdMateria WHERE n.idEstudiante = ?";
            stmt = conn.prepareStatement(gradesQuery);
            stmt.setInt(1, studentId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String nombreMateria = rs.getString("materia");
                double nota = rs.getDouble("nota");

                // Initializing the objects
                Materia materia = new Materia(nombreMateria);
                Nota notaObj = new Nota(studentId, nota);

                // Adding the data to the table
                Object[] rowData = {materia.getMateria(), notaObj.getNota()};
                listSubjects.addRow(rowData);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DBConnection.close(rs);
            DBConnection.close(stmt);
            DBConnection.close(conn);
        }
    }
}
