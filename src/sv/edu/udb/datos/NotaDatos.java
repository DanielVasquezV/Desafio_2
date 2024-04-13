package sv.edu.udb.datos;

import sv.edu.udb.beans.Nota;
import sv.edu.udb.utils.DBConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NotaDatos {

    public void InsertGrade(Nota nota) {

        Connection conn;
        PreparedStatement stmt;
        try {
            conn = DBConnection.getConnection();
            String SQL_Insert = "INSERT INTO notas (IdEstudiante, IdMateria, nota) VALUES (?,?,?)";
            stmt = conn.prepareStatement(SQL_Insert);
            stmt.setInt(1, nota.getIdEstudiante());
            stmt.setInt(2,nota.getIdMateria());
            stmt.setDouble(3, nota.getNota());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Nota agregada con exito");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "nota no insertada");

        }
    }
    public boolean hasGrade(Connection conn, int idEstudiante, int idMateria) throws SQLException {
        String SQL_SELECT = "SELECT COUNT(*) FROM notas WHERE IdEstudiante = ? AND IdMateria = ?";
        PreparedStatement stmt = conn.prepareStatement(SQL_SELECT);
        stmt.setInt(1, idEstudiante);
        stmt.setInt(2, idMateria);

        ResultSet rs = stmt.executeQuery();
        return rs.next() && rs.getInt(1) > 0; // por si el estudiante ya tiene nota en la materia
    }

}
