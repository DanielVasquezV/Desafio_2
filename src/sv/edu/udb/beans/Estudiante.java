package sv.edu.udb.beans;

public class Estudiante {
    private int idEstudiante;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono;

    public Estudiante(int idEstudiante, String nombres, String apellidos, String direccion, String telefono){
        this.idEstudiante = idEstudiante;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Estudiante() {

    }

    // Métodos Setter
    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // Métodos Getter
    public int getIdEstudiante() {
        return idEstudiante;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }
}
