package sv.edu.udb.utils;

import java.sql.*;

public class DBConnection {
    private static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //El puerto es opcional
    private static String JDBC_URL = "jdbc:mysql://localhost:3306/desafio_poo";
    private static String JDBC_USER = "root";
    private static String JDBC_PASS = "12345";
    private static Driver driver = null;
    public static synchronized Connection getConnection()
            throws SQLException {
        Connection con = null;
        if (driver == null) {
            try {
                //Se registra el driver
                Class.forName(JDBC_DRIVER);
                con = DriverManager.getConnection (JDBC_URL,JDBC_USER, JDBC_PASS);

            } catch (Exception e) {
                System.out.println("Fallo en cargar el driver JDBC");
                System.out.println(e.getMessage());
            }
        }
        return DriverManager.getConnection(JDBC_URL, JDBC_USER,
                JDBC_PASS);
    }
    //Cierre del resultSet
    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException sql) {
            System.out.println(sql.getMessage());
        }
    }
    //Cierre del PrepareStatement
    public static void close(PreparedStatement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }
    //Cierre de la conexion
    public static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }


}
