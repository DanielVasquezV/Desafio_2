package sv.edu.udb.beans;

public class Nota {
    private int idEstudiante;
    private int idMateria;
    private double nota;

    public Nota(int idEstudiante,int idMateria, double nota){
        this.idEstudiante = idEstudiante;
        this.idMateria = idMateria;
        this.nota = nota;
    }

    public Nota() {

    }

    public Nota(int studentId, double nota) {
        this.idEstudiante = studentId;
        this.nota=nota;
    }

    // Métodos Setter
    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    // Métodos Getter
    public int getIdEstudiante() {
        return idEstudiante;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public double getNota() {
        return nota;
    }
}
