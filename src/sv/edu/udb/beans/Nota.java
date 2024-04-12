package sv.edu.udb.beans;

public class Nota {
    private int idEstudiante;
    private int idMateria;
    private double nota;

    public Nota(int idEstudiante, double nota){
        this.idEstudiante = idEstudiante;
        this.idMateria = idMateria;
        this.nota = nota;
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
